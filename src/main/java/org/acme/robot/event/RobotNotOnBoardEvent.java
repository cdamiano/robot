package org.acme.robot.event;

/**
 * An event to handle the case of when the command is issued to the robot when it has not been placed on the board.
 * The event is posted on the {@link com.google.common.eventbus.EventBus} for subscribers to handle.
 */
public class RobotNotOnBoardEvent {
}
