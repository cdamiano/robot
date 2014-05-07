package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import org.acme.robot.model.Robot;

public class MoveCommand extends AbstractRobotCommandWithEventBus {

    public MoveCommand(EventBus eventBus) {
        super(eventBus);
    }

    @Override
    protected void executeCommand(Robot robot) throws Exception {
        robot.moveForward();
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

}
