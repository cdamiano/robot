package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import org.acme.robot.model.Direction;
import org.acme.robot.model.Robot;
import org.acme.robot.model.TableTop;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class PlaceCommandTest {

    private PlaceCommand placeCommand;
    private EventBus eventBus;

    @Before
    public void setup() {
        eventBus = new EventBus("test");
    }

    @Test
    public void shouldCreatePlaceCommand() {
        //given
        Robot robot = new Robot(new TableTop(5, 5));
        placeCommand = new PlaceCommand(eventBus, new Point(1, 1), Direction.North);

        //when
        placeCommand.execute(robot);

        //then
        assertThat(robot.getPosition(), is(equalTo(new Point(1, 1))));
        assertThat(robot.facingDirection(), is(Direction.North));
    }
}
