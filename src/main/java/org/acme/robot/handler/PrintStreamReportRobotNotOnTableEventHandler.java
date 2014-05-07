package org.acme.robot.handler;

import org.acme.robot.event.RobotNotOnBoardEvent;

import java.io.PrintStream;

import static com.google.common.base.Preconditions.checkArgument;

public class PrintStreamReportRobotNotOnTableEventHandler implements ReportRobotNotOnTableEventHandler{

    private PrintStream printStream;

    public PrintStreamReportRobotNotOnTableEventHandler(PrintStream printStream) {
        checkArgument(printStream != null, "PrintStream can not be null.");
        this.printStream = printStream;
    }

    @Override
    public void handleEvent(RobotNotOnBoardEvent event) {
        printStream.println("Robot is not placed on board");
    }
}
