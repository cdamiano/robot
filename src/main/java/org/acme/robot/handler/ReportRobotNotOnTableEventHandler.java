package org.acme.robot.handler;

import com.google.common.eventbus.Subscribe;
import org.acme.robot.event.RobotNotOnBoardEvent;

public interface ReportRobotNotOnTableEventHandler {

    @Subscribe
    void handleEvent(RobotNotOnBoardEvent event);
}
