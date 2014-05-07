package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import org.acme.robot.exception.IllegalCommandException;
import org.acme.robot.model.Direction;

import java.awt.Point;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.regex.Pattern.compile;

public class CommandInterpreter {
    private static final Pattern PLACE_PATTERN = compile("^\\s*PLACE\\s+(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\w+)\\s*");
    private static final int X_COORDINATE = 1;
    private static final int Y_COORDINATE = 2;
    private static final int DIRECTION = 3;

    private EventBus eventBus;

    public CommandInterpreter(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public RobotCommand fromString(String commandString) throws IllegalCommandException {
        checkArgument(commandString != null, "Command string can not be null.");
        if (commandString.toUpperCase().startsWith(Command.PLACE.name())) {
            return newPlaceCommand(commandString);
        } else {
            return simpleCommand(commandString);
        }
    }

    private RobotCommand simpleCommand(String commandString) throws IllegalCommandException {
        Command command = parseCommand(commandString);
        switch (command) {
            case MOVE:
                return new MoveCommand(eventBus);
            case LEFT:
                return new TurnLeftCommand(eventBus);
            case RIGHT:
                return new TurnRightCommand(eventBus);
            case REPORT:
                return new ReportCommand(eventBus);
            default:
                throw new IllegalCommandException(commandString);
        }
    }

    private Command parseCommand(String commandString) throws IllegalCommandException {
        try {
            return Command.valueOf(commandString.toUpperCase());
        } catch(IllegalArgumentException ex) {
            throw new IllegalCommandException("Unknown command: [" + commandString + "]");
        }
    }

    private RobotCommand newPlaceCommand(String commandString) throws IllegalCommandException {
        Matcher matcher = PLACE_PATTERN.matcher(commandString.toUpperCase());

        if (exactMatch(matcher)) {
            int x = parseInteger(matcher.group(X_COORDINATE));
            int y = parseInteger(matcher.group(Y_COORDINATE));
            String direction = matcher.group(DIRECTION);
            return new PlaceCommand(eventBus, new Point(x, y), parseDirection(direction));
        } else {
            throw new IllegalCommandException("Unknown command: [" + commandString + "]");
        }

    }

    private Direction parseDirection(String direction) throws IllegalCommandException {
        try {
            return Direction.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalCommandException("PLACE command has an invalid direction [" + direction + "]");
        }
    }

    private int parseInteger(String value) {
        return Integer.valueOf(value);
    }

    private boolean exactMatch(Matcher matcher) {
        return matcher.find() && matcher.hitEnd();
    }

    private enum Command {
        MOVE,
        LEFT,
        RIGHT,
        REPORT,
        PLACE
    }
}
