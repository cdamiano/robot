package org.acme.robot.event;

public class ErrorEvent {

    public final Exception exception;
    public final String tag;

    public ErrorEvent(String tag, Exception ex) {
        this.exception = ex;
        this.tag = tag;
    }

}
