package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import org.acme.robot.event.ReportNotificationEvent;
import org.acme.robot.event.RobotNotOnBoardEvent;
import org.acme.robot.model.Robot;

public class ReportCommand extends AbstractRobotCommandWithEventBus {

    public ReportCommand(EventBus eventBus) {
        super(eventBus);
    }

    @Override
    protected void executeCommand(Robot robot) throws Exception {
        if (robot.isPlaced()) {
            getEventBus().post(new ReportNotificationEvent(robot.getPosition(), robot.facingDirection()));
        } else {
            getEventBus().post(new RobotNotOnBoardEvent());
        }
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }
}
