package org.acme.robot.model;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RobotTurnLeftTest extends AbstractRobotTurnTest {

    public RobotTurnLeftTest(Direction start, Direction end) {
        this.startDirection = start;
        this.expectedDirection = end;
    }

    @Override
    public void executeTurn() throws Exception {
        robot.turnLeft();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testParameters() {
        return Arrays.asList(new Object[][]{
                {Direction.North, Direction.West},
                {Direction.West, Direction.South},
                {Direction.South, Direction.East},
                {Direction.East, Direction.North}
        });
    }

}
