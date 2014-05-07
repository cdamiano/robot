package org.acme.robot.model;

import org.acme.robot.exception.IllegalMovementException;

import java.awt.Point;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Robot {

    private final TableTop tableTop;

    private Point position;
    private Direction factionDirection;

    private boolean placed = false;

    public Robot(TableTop tableTop) {
        this.tableTop = tableTop;
    }

    public void placeOnTable(Point point, Direction direction) {
        checkArgumentsForPlaceOnTable(point, direction);
        this.position = point;
        this.factionDirection = direction;
        this.placed = true;
    }

    public Point getPosition() {
        return position;
    }

    public Direction facingDirection() {
        return factionDirection;
    }

    public void moveForward() throws IllegalMovementException {
        checkRobotIsOnTable();
        Point newPosition = executeMove();
        if (tableTop.isValidPosition(newPosition)) {
            this.position = newPosition;
        }
    }

    public void turnRight() throws IllegalMovementException {
        checkRobotIsOnTable();
        int index = (factionDirection.ordinal() + 1) % Direction.values().length;
        factionDirection = Direction.values()[index];
    }

    public void turnLeft() throws IllegalMovementException {
        checkRobotIsOnTable();
        int index = (factionDirection.ordinal() - 1) % Direction.values().length;
        if (index < 0) {
            factionDirection = Direction.West;
        } else {
            factionDirection = Direction.values()[index];
        }
    }

    private void checkArgumentsForPlaceOnTable(Point point, Direction direction) {
        checkNotNull(point, "Point can not be null.");
        checkNotNull(direction, "Point can not be null.");
        checkArgument(tableTop.isValidPosition(point), "Point is invalid.");
    }

    private void checkRobotIsOnTable() throws IllegalMovementException {
        if (isNotPlaced()) throw new IllegalMovementException("Robot is not on table.");
    }

    public boolean isPlaced() {
        return placed;
    }

    private boolean isNotPlaced() {
        return !isPlaced();
    }

    private Point executeMove() {
        Point newPoint = new Point(this.position);
        switch(factionDirection) {
            case North:
                newPoint.setLocation(this.position.getX(), this.position.getY() + 1);
                break;
            case West:
                newPoint.setLocation(this.position.getX() - 1, this.position.getY());
                break;
            case East:
                newPoint.setLocation(this.position.getX() + 1, this.position.getY());
                break;
            case South:
                newPoint.setLocation(this.position.getX(), this.getPosition().getY() - 1);
                break;
        }
        return newPoint;
    }
}
