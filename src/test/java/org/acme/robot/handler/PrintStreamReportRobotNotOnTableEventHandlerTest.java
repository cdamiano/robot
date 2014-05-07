package org.acme.robot.handler;

import org.acme.robot.event.RobotNotOnBoardEvent;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class PrintStreamReportRobotNotOnTableEventHandlerTest {

    private PrintStreamReportRobotNotOnTableEventHandler handler;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setup() {
        outputStream = new ByteArrayOutputStream();
        handler = new PrintStreamReportRobotNotOnTableEventHandler(new PrintStream(outputStream));
    }

    @Test
    public void shouldAnnounceRobot() {
        //when
        handler.handleEvent(new RobotNotOnBoardEvent());

        //then
        assertThat(outputStream.toString(), containsString("Robot is not placed on board"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotConstructHandlerWithNullPrintStream() {
        new PrintStreamReportRobotNotOnTableEventHandler(null);
    }

}
