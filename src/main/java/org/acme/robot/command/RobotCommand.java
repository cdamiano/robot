package org.acme.robot.command;

import org.acme.robot.model.Robot;

public interface RobotCommand {

    void execute(Robot robot);
}
