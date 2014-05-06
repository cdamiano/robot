package org.acme.robot.exception;

public class IllegalMovementException extends IllegalCommandException {
    public IllegalMovementException(String message) {
        super(message);
    }
}
