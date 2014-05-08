package org.acme.robot.event;

import org.acme.robot.model.Direction;

import java.awt.Point;

/**
 * Class is used to send report notification of the robot via the {@link com.google.common.eventbus.EventBus}.  Any subscribers
 * can then receive a report about the robot position.
 */
public class ReportNotificationEvent {
    public final Point robotPosition;
    public final Direction robotFacing;

    public ReportNotificationEvent(Point robotPosition, Direction robotFacing) {
        this.robotPosition = robotPosition;
        this.robotFacing = robotFacing;
    }
}
