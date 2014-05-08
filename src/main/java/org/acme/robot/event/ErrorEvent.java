package org.acme.robot.event;

/**
 * This class is used to send a notification of when an error on the {@link com.google.common.eventbus.EventBus}, so
 * that the subscribers can handle the event.
 */
public class ErrorEvent {

    public final Exception exception;
    public final String tag;

    public ErrorEvent(String tag, Exception ex) {
        this.exception = ex;
        this.tag = tag;
    }

}
