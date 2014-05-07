package org.acme.robot;

import org.acme.robot.command.MockErrorEventHandler;
import org.acme.robot.command.MockReportNotificationEventHandler;
import org.acme.robot.model.Direction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class RobotControllerTest {

    private RobotController controller;
    private MockErrorEventHandler mockErrorEventHandler;
    private MockReportNotificationEventHandler mockReportHandler;

    private String commands;
    private int expectedX;
    private int expectedY;
    private Direction expectedDirection;

    public RobotControllerTest(String commands, int expectedX, int expectedY, Direction expectedDirection) {
        this.commands = commands;
        this.expectedX = expectedX;
        this.expectedY = expectedY;
        this.expectedDirection = expectedDirection;
    }

    @Before
    public void setup() {
        this.controller = new RobotController();
        setUpEventHandlers();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInputStreamNull() {
        controller.run(null);
    }

    @Test
    public void shouldRun() {
        //given
        InputStream inputStream = new ByteArrayInputStream(commands.getBytes(StandardCharsets.UTF_8));

        //when
        controller.run(inputStream);

        //then
        assertThat(mockReportHandler.robotPosition.x, is(equalTo(expectedX)));
        assertThat(mockReportHandler.robotPosition.y, is(equalTo(expectedY)));
        assertThat(mockReportHandler.robotFacing, is(expectedDirection));
    }

    @Test
    public void invalidCommand() {
        //given
        String commands = CommandSequence.firstCommand("blah").build();
        InputStream inputStream = new ByteArrayInputStream(commands.getBytes(StandardCharsets.UTF_8));

        //when
        controller.run(inputStream);

        //then
        assertThat(mockErrorEventHandler.handleEventCalled, is(true));

    }

    @Parameterized.Parameters
    public static Collection<Object[]> testParameters() {
        return Arrays.asList(new Object[][]{
                {
                        CommandSequence.firstCommand("PLACE 0,0,NORTH").next("MOVE").next("REPORT").build(),
                        0,
                        1,
                        Direction.NORTH
                },
                {
                        CommandSequence.firstCommand("PLACE 0,0,NORTH").next("LEFT").next("REPORT").build(),
                        0,
                        0,
                        Direction.WEST
                },
                {
                        CommandSequence.firstCommand("PLACE 1,2,EAST").next("MOVE").next("MOVE").next("LEFT").next("move").next("report").build(),
                        3,
                        3,
                        Direction.NORTH
                }


        });
    }

    private void setUpEventHandlers() {
        mockErrorEventHandler = new MockErrorEventHandler();
        mockReportHandler = new MockReportNotificationEventHandler();
        this.controller.setErrorEventHandler(mockErrorEventHandler);
        this.controller.setReportNotificationEventHandler(mockReportHandler);
        this.controller.setReportRobotNotOnTableEventHandler(mockReportHandler);
    }

    private static class CommandSequence {
        List<String> commands;

        public CommandSequence() {
            commands = new ArrayList<String>();
        }

        public static CommandSequence firstCommand(String command) {
            CommandSequence commands = new CommandSequence();
            commands.commands.add(command);
            return commands;
        }

        public CommandSequence next(String command) {
            commands.add(command);
            return this;
        }

        public String build() {
            StringBuilder builder = new StringBuilder();
            for (String command : commands) {
                builder.append(command);
                builder.append("\n");
            }
            return builder.toString();
        }

    }
}
