package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import org.acme.robot.exception.IllegalCommandException;
import org.acme.robot.model.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CommandInterpreterPlaceTest {

    private CommandInterpreter interpreter;

    @Before
    public void setup() {
        interpreter = new CommandInterpreter(new EventBus());
    }

    @Test
    public void shouldCreatePlaceCommandWithParameters() throws Exception {
        //given
        String commandString = "PLACE 3,4,NORTH";

        //when
        PlaceCommand command = (PlaceCommand) interpreter.fromString(commandString);

        //then
        assertThat(command.getX(), is(equalTo(3)));
        assertThat(command.getY(), is(equalTo(4)));
        assertThat(command.getDirection(), is(equalTo(Direction.NORTH)));
    }

    @Test(expected = IllegalCommandException.class)
    public void invalidXValue() throws Exception {
        interpreter.fromString("PLACE B,4,NORTH");
    }

    @Test(expected = IllegalCommandException.class)
    public void invalidYValue() throws Exception {
        interpreter.fromString("PLACE 1,B,NORTH");
    }

    @Test(expected = IllegalCommandException.class)
    public void invalidDirection() throws Exception {
        interpreter.fromString("PLACE 1,4,NORTHWEST");
    }

    @Test(expected = IllegalCommandException.class)
    public void incompletePlaceCommand() throws Exception {
        interpreter.fromString("PLACE 1,4");
    }

}
