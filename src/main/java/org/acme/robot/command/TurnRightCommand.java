package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import org.acme.robot.model.Robot;

public class TurnRightCommand extends AbstractRobotCommandWithEventBus {

    public TurnRightCommand(EventBus eventBus) {
        super(eventBus);
    }

    @Override
    protected void executeCommand(Robot robot) throws Exception {
        robot.turnRight();
    }

    @Override
    protected String getTag() {
        return TurnRightCommand.class.getSimpleName();
    }

}
