package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import org.acme.robot.model.Direction;
import org.acme.robot.model.Robot;
import org.acme.robot.model.TableTop;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public abstract class AbstractMovementCommandTest {

    private RobotCommand robotCommand;
    private MockErrorEventHandler mockErrorHandler;

    protected Robot robot;

    protected abstract RobotCommand newCommand(EventBus eventBus);
    protected abstract void assertCommandOutcome();

    @Before
    public void setup() {
        EventBus eventBus = new EventBus("test");
        mockErrorHandler = new MockErrorEventHandler();
        eventBus.register(mockErrorHandler);
        robotCommand = newCommand(eventBus);
        robot = new Robot(new TableTop(5,5));
    }

    @Test
    public void shouldExecute() {
        //given
        robot.placeOnTable(new Point(0,0), Direction.North);

        //when
        robotCommand.execute(robot);

        //then
        assertCommandOutcome();
        assertThat(mockErrorHandler.handleEventCalled, is(false));
    }

    @Test
    public void shouldIgnoreCommand() {
        //when
        robotCommand.execute(robot);

        //then
        assertThat(mockErrorHandler.handleEventCalled, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotConstructCommandWithNullEventBus() {
        newCommand(null);
    }

}
