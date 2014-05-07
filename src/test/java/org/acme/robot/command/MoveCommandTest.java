package org.acme.robot.command;

import com.google.common.eventbus.EventBus;

import java.awt.Point;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class MoveCommandTest extends AbstractMovementCommandTest {

    @Override
    protected RobotCommand newCommand(EventBus eventBus) {
        return new MoveCommand(eventBus);
    }

    @Override
    protected void assertCommandOutcome() {
        assertThat(robot.getPosition(), is(equalTo(new Point(0,1))));
    }

}
