package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import org.acme.robot.model.Robot;

public class TurnLeftCommand extends AbstractRobotCommandWithEventBus {

    public TurnLeftCommand(EventBus eventBus) {
        super(eventBus);
    }

    @Override
    protected void executeCommand(Robot robot) throws Exception {
        robot.turnLeft();
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }
}
