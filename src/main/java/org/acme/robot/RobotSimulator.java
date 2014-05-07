package org.acme.robot;

public class RobotSimulator {

    public static void main(String[] args) {
        RobotController controller = new RobotController();
        controller.run(System.in);
    }
}