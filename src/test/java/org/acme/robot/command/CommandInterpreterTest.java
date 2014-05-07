package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import org.acme.robot.exception.IllegalCommandException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class CommandInterpreterTest {

    private CommandInterpreter commandInterpreter;

    private String commandString;
    private Class commandClass;

    public CommandInterpreterTest(String commandString, Class commandClass) {
        this.commandString = commandString;
        this.commandClass = commandClass;
    }

    @Before
    public void setup() {
        EventBus eventBus = new EventBus("Test");
        commandInterpreter = new CommandInterpreter(eventBus);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNull() throws Exception {
        commandInterpreter.fromString(null);
    }

    @Test(expected = IllegalCommandException.class)
    public void shouldThrowIllegalCommandExceptionWhenUnknownCommand() throws Exception {
        commandInterpreter.fromString("unknownCommand");
    }

    @Test
    public void shouldInterpretMoveCommand() throws Exception {
        //when
        RobotCommand command = commandInterpreter.fromString(commandString);

        //then
        assertThat(command, instanceOf(commandClass));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testParameters() {
        return Arrays.asList(new Object[][]{
                {"MOVE", MoveCommand.class},
                {"move", MoveCommand.class},
                {"LEFT", TurnLeftCommand.class},
                {"left", TurnLeftCommand.class},
                {"RIGHT", TurnRightCommand.class},
                {"Right", TurnRightCommand.class},
                {"REPORT", ReportCommand.class},
                {"Report", ReportCommand.class}
        });
    }
}
