package org.acme.robot.command;

import com.google.common.eventbus.Subscribe;
import org.acme.robot.event.ReportNotificationEvent;
import org.acme.robot.event.RobotNotOnBoardEvent;
import org.acme.robot.handler.ReportNotificationEventHandler;
import org.acme.robot.handler.ReportRobotNotOnTableEventHandler;
import org.acme.robot.model.Direction;

import java.awt.Point;

public class MockReportNotificationEventHandler implements ReportNotificationEventHandler, ReportRobotNotOnTableEventHandler {
    public Point robotPosition;
    public Direction robotFacing;
    public boolean robotNotBoardWasCalled;

    @Subscribe
    @Override
    public void handleEvent(ReportNotificationEvent event) {
        robotPosition = event.robotPosition;
        robotFacing = event.robotFacing;
    }

    @Subscribe
    @Override
    public void handleEvent(RobotNotOnBoardEvent event) {
        robotNotBoardWasCalled = true;

    }

}
