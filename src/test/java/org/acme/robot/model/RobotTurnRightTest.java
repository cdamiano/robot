package org.acme.robot.model;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RobotTurnRightTest extends AbstractRobotTurnTest {

    public RobotTurnRightTest(Direction start, Direction end) {
        startDirection = start;
        expectedDirection = end;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testParameters() {
        return Arrays.asList(new Object[][]{
                {Direction.North, Direction.East},
                {Direction.East, Direction.South},
                {Direction.South, Direction.West},
                {Direction.West, Direction.North}
        });
    }

    @Override
    public void executeTurn() throws Exception {
        robot.turnRight();
    }
}
