package org.acme.robot.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.awt.Point;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public abstract class AbstractRobotTurnTest {

    protected Robot robot;

    protected Direction startDirection;
    protected Direction expectedDirection;

    @Before
    public void createRobot() {
        robot = new Robot(new TableTop(5, 5));
    }

    @Test
    public void shouldTurn() throws Exception {
        robot.placeOnTable(new Point(0, 0), startDirection);

        executeTurn();

        assertThat(robot.getFacingDirection(), is(equalTo(expectedDirection)));
    }

    public abstract void executeTurn() throws Exception;
}

