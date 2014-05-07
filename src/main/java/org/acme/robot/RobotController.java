package org.acme.robot;

import com.google.common.eventbus.EventBus;
import org.acme.robot.command.CommandInterpreter;
import org.acme.robot.command.RobotCommand;
import org.acme.robot.event.ErrorEvent;
import org.acme.robot.exception.IllegalCommandException;
import org.acme.robot.handler.ErrorEventHandler;
import org.acme.robot.handler.PrintStreamErrorEventHandler;
import org.acme.robot.handler.PrintStreamReportNotificationEventHandler;
import org.acme.robot.handler.PrintStreamReportRobotNotOnTableEventHandler;
import org.acme.robot.handler.ReportNotificationEventHandler;
import org.acme.robot.handler.ReportRobotNotOnTableEventHandler;
import org.acme.robot.model.Robot;
import org.acme.robot.model.TableTop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.io.Closeables.closeQuietly;

public class RobotController {

    private EventBus eventBus;
    private ErrorEventHandler errorEventHandler = new PrintStreamErrorEventHandler(System.err);
    private ReportNotificationEventHandler reportNotificationEventHandler = new PrintStreamReportNotificationEventHandler(System.out);
    private ReportRobotNotOnTableEventHandler reportRobotNotOnTableEventHandler = new PrintStreamReportRobotNotOnTableEventHandler(System.out);
    private CommandInterpreter commandInterpreter;
    private Robot robot;

    public RobotController() {
        eventBus = new EventBus("AcmeRobot");
        commandInterpreter = new CommandInterpreter(eventBus);
        robot = new Robot(new TableTop(5,5));
        System.out.println("Table initialised (5 x 5)...");
        System.out.println("Robot initialised...");
    }

    public void setErrorEventHandler(ErrorEventHandler errorEventHandler) {
        this.errorEventHandler = errorEventHandler;
    }

    public void setReportNotificationEventHandler(ReportNotificationEventHandler reportNotificationEventHandler) {
        this.reportNotificationEventHandler = reportNotificationEventHandler;
    }

    public void setReportRobotNotOnTableEventHandler(ReportRobotNotOnTableEventHandler reportRobotNotOnTableEventHandler) {
        this.reportRobotNotOnTableEventHandler = reportRobotNotOnTableEventHandler;
    }


    public void run(InputStream inputStream) {
        checkArgument(inputStream != null, "InputStream can not be null");
        registerEventHandlers();
        executeRobotCommandsFrom(inputStream);
    }

    private void executeRobotCommandsFrom(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String commandString;
            System.out.println("Awaiting commands...");
            while ((commandString = reader.readLine()) != null) {
                executeCommand(commandString);
            }
        } catch (IOException e) {
            eventBus.post(new ErrorEvent(getClass().getSimpleName(), e));
            closeQuietly(reader);
        }
    }

    private void executeCommand(String commandString) {
        try {
            RobotCommand robotCommand = commandInterpreter.fromString(commandString);
            robotCommand.execute(robot);
        } catch (IllegalCommandException ex) {
            eventBus.post(new ErrorEvent(getClass().getSimpleName(), ex));
        }
    }

    private void registerEventHandlers() {
        eventBus.register(errorEventHandler);
        eventBus.register(reportNotificationEventHandler);
        eventBus.register(reportRobotNotOnTableEventHandler);
    }
}
