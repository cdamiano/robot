package org.acme.robot.handler;

import org.acme.robot.event.ReportNotificationEvent;

import java.io.PrintStream;

import static com.google.common.base.Preconditions.checkArgument;

public class PrintStreamReportNotificationEventHandler implements ReportNotificationEventHandler {

    private PrintStream printStream;

    public PrintStreamReportNotificationEventHandler(PrintStream printStream) {
        checkArgument(printStream != null, "PrintStream can not be null");
        this.printStream = printStream;
    }

    @Override
    public void handleEvent(ReportNotificationEvent event) {
        printStream.println(
                String.format(
                        "Output: %d,%d,%s",
                        event.robotPosition.x,
                        event.robotPosition.y,
                        event.robotFacing.name().toUpperCase()
                )
        );
    }
}
