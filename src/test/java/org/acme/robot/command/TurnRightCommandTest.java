package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import org.acme.robot.model.Direction;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TurnRightCommandTest extends AbstractMovementCommandTest {

    @Override
    protected RobotCommand newCommand(EventBus eventBus) {
        return new TurnRightCommand(eventBus);
    }

    @Override
    protected void assertCommandOutcome() {
        assertThat(robot.facingDirection(), is(Direction.East));
    }

}
