package org.acme.robot.handler;

import com.google.common.eventbus.Subscribe;
import org.acme.robot.event.ErrorEvent;

import java.io.PrintStream;

import static com.google.common.base.Preconditions.checkNotNull;

public class PrintStreamErrorEventHandler implements ErrorEventHandler {

    private PrintStream printStream;

    public PrintStreamErrorEventHandler(PrintStream printStream) {
        checkNotNull(printStream);
        this.printStream = printStream;
    }

    @Subscribe
    @Override
    public void handleEvent(ErrorEvent error) {
        printStream.print(String.format("%s: %s\n", error.tag, error.exception.getMessage()));
        error.exception.printStackTrace(printStream);
    }
}
