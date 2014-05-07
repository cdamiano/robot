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
                {Direction.NORTH, Direction.EAST},
                {Direction.EAST, Direction.SOUTH},
                {Direction.SOUTH, Direction.WEST},
                {Direction.WEST, Direction.NORTH}
        });
    }

    @Override
    public void executeTurn() throws Exception {
        robot.turnRight();
    }
}
