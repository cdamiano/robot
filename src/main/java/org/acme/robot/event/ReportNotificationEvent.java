package org.acme.robot.event;

import org.acme.robot.model.Direction;

import java.awt.Point;

public class ReportNotificationEvent {
    public final Point robotPosition;
    public final Direction robotFacing;

    public ReportNotificationEvent(Point robotPosition, Direction robotFacing) {
        this.robotPosition = robotPosition;
        this.robotFacing = robotFacing;
    }
}
