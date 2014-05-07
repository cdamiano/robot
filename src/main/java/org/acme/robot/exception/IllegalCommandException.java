package org.acme.robot.exception;

public class IllegalCommandException extends Exception {

    public IllegalCommandException(String commandString) {
        super("Unknown command: [" + commandString + "]");
    }

    public IllegalCommandException(String msg, Exception ex) {
        super(msg, ex);
    }
}
