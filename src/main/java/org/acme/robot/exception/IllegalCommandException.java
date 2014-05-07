package org.acme.robot.exception;

public class IllegalCommandException extends Exception {

    public IllegalCommandException(String commandString) {
        super(commandString);
    }

}
