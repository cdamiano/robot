package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import org.acme.robot.model.Direction;
import org.acme.robot.model.Robot;

import java.awt.Point;

public class PlaceCommand extends AbstractRobotCommandWithEventBus {

    private Point point;
    private Direction direction;

    public PlaceCommand(EventBus eventBus, Point point, Direction direction) {
        super(eventBus);
        this.point = point;
        this.direction = direction;
    }

    @Override
    protected void executeCommand(Robot robot) throws Exception {
        robot.placeOnTable(point, direction);
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }
}
