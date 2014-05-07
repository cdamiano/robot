package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.acme.robot.event.ReportNotificationEvent;
import org.acme.robot.handler.ReportNotificationEventHandler;
import org.acme.robot.handler.ReportRobotNotOnTableEventHandler;
import org.acme.robot.event.RobotNotOnBoardEvent;
import org.acme.robot.model.Direction;
import org.acme.robot.model.Robot;
import org.acme.robot.model.TableTop;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReportCommandTest {

    private ReportCommand reportCommand;
    private MockReportNotificationEventHandler reportHandler;

    @Before
    public void setup() {
        EventBus eventBus = new EventBus("test");
        reportHandler = new MockReportNotificationEventHandler();
        eventBus.register(reportHandler);
        reportCommand = new ReportCommand(eventBus);
    }

    @Test
    public void shouldReportRobotPosition() {
        //given
        Robot robot = new Robot(new TableTop(5, 5));
        robot.placeOnTable(new Point(3, 5), Direction.NORTH);

        //when
        reportCommand.execute(robot);

        //then
        assertThat(reportHandler.robotPosition, is(equalTo(robot.getPosition())));
        assertThat(reportHandler.robotFacing, is(equalTo(robot.facingDirection())));
    }

    @Test
    public void shouldReportRobotNotOnBoardWhenReportCommandExecuted() {
        //given
        Robot robot = new Robot(new TableTop(5, 5));

        //when
        reportCommand.execute(robot);

        //then
        assertThat(reportHandler.robotNotBoardWasCalled, is(true));
    }

    private static class MockReportNotificationEventHandler implements ReportNotificationEventHandler, ReportRobotNotOnTableEventHandler {
        public Point robotPosition;
        public Direction robotFacing;
        public boolean robotNotBoardWasCalled;

        @Subscribe
        @Override
        public void handleEvent(ReportNotificationEvent event) {
            robotPosition = event.robotPosition;
            robotFacing = event.robotFacing;
        }

        @Subscribe
        @Override
        public void handleEvent(RobotNotOnBoardEvent event) {
            robotNotBoardWasCalled = true;

        }
    }

}
