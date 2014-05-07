package org.acme.robot.model;

import org.acme.robot.exception.IllegalMovementException;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RobotTest {

    private Robot robot;

    @Before
    public void createRobot() {
        robot = new Robot(new TableTop(5, 5));
    }

    @Test
    public void shouldPlaceRobotOnBoard() {
        //given
        Point point = new Point(3, 2);

        //when
        robot.placeOnTable(point, Direction.NORTH);

        //then
        assertThat(robot.getPosition(), is(equalTo(point)));
        assertThat(robot.facingDirection(), is(equalTo(Direction.NORTH)));
        assertThat(robot.isPlaced(), is(true));
    }

    @Test
    public void shouldNotBePlaced() {
        assertThat(robot.isPlaced(), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenPointNull() {
        robot.placeOnTable(null, Direction.NORTH);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenDirectionNull() {
        robot.placeOnTable(new Point(0,0), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenInvalidPoint() {
        robot.placeOnTable(new Point(-1,9), Direction.NORTH);
    }

    @Test(expected = IllegalMovementException.class)
    public void shouldNotMoveWhenNotPlaced() throws Exception {
        robot.moveForward();
    }

    @Test(expected = IllegalMovementException.class)
    public void shouldNotTurnRightWhenNotPlaced() throws Exception {
        robot.turnRight();
    }

    @Test(expected = IllegalMovementException.class)
    public void shouldNotTurnLeftWhenNotPlaced() throws Exception {
        robot.turnLeft();
    }

    @Test
    public void shouldMoveOneUnitNorth() throws Exception {
        //given
        robot.placeOnTable(new Point(0, 0), Direction.NORTH);

        //when
        robot.moveForward();

        //then
        assertThat(robot.getPosition(), is(equalTo(new Point(0, 1))));
    }

    @Test
    public void shouldMoveOneUnitWest() throws Exception {
        //given
        robot.placeOnTable(new Point(1, 0), Direction.WEST);

        //when
        robot.moveForward();

        //then
        assertThat(robot.getPosition(), is(equalTo(new Point(0, 0))));
    }

    @Test
    public void shouldMoveOneUnitEast() throws Exception {
        //given
        robot.placeOnTable(new Point(4, 5), Direction.EAST);

        //when
        robot.moveForward();

        //then
        assertThat(robot.getPosition(), is(equalTo(new Point(5, 5))));
    }

    @Test
    public void shouldMoveOneUnitSouth() throws Exception {
        //given
        robot.placeOnTable(new Point(5, 5), Direction.SOUTH);

        //when
        robot.moveForward();

        //then
        assertThat(robot.getPosition(), is(equalTo(new Point(5, 4))));
    }

    @Test
    public void shouldNotMoveOffTableTop() throws Exception {
        //given
        robot.placeOnTable(new Point(5, 5), Direction.NORTH);

        //when
        robot.moveForward();

        //then
        assertThat(robot.getPosition(), is(equalTo(new Point(5, 5))));
    }


}
