package org.acme.robot.handler;

import org.acme.robot.event.ErrorEvent;
import org.acme.robot.handler.PrintStreamErrorEventHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class PrintStreamErrorEventHandlerTest {

    private PrintStreamErrorEventHandler errorHandler;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setup() {
        outputStream = new ByteArrayOutputStream();
        errorHandler = new PrintStreamErrorEventHandler(new PrintStream(outputStream));
    }

    @Test
    public void shouldOutputErrorEventToPrintStream() {
        //given
        ErrorEvent errorEvent = new ErrorEvent("test", new Exception("Problem has Occurred"));

        //when
        errorHandler.handleEvent(errorEvent);

        //then
        assertThat(outputStream.toString(), containsString(errorEvent.tag));
        assertThat(outputStream.toString(), containsString(errorEvent.exception.getMessage()));
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotConstructErrorHandler() {
        new PrintStreamErrorEventHandler(null);
    }
}
