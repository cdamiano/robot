package org.acme.robot.handler;

import org.acme.robot.event.ReportNotificationEvent;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.acme.robot.model.Direction.North;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class PrintStreamReportNotificationEventHandlerTest {

    private PrintStreamReportNotificationEventHandler handler;
    private ByteArrayOutputStream outputStream;


    @Before
    public void setup() {
        outputStream = new ByteArrayOutputStream();
        handler = new PrintStreamReportNotificationEventHandler(new PrintStream(outputStream));
    }

    @Test
    public void shouldAnnounceRobot() {
        //given
        Point point = new Point(3,5);
        ReportNotificationEvent event = new ReportNotificationEvent(point, North);

        //when
        handler.handleEvent(event);

        //then
        assertThat(outputStream.toString(), containsString(Integer.toString(event.robotPosition.x)));
        assertThat(outputStream.toString(), containsString(Integer.toString(event.robotPosition.y)));
        assertThat(outputStream.toString(), containsString(North.name().toUpperCase()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotConstructHandlerWithNullPrintStream() {
        new PrintStreamReportNotificationEventHandler(null);
    }

}
