# Toy Robot
The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 units x 5 units.

*   There are no other obstructions on the table surface.

*   The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. Any movement
that would result in the robot falling from the table must be prevented, however further valid movement commands must still
be allowed.

The application that can read in commands of the following form -
PLACE X,Y,F
MOVE
LEFT
RIGHT
REPORT

### Constraints
The toy robot must not fall off the table during movement. This also includes the initial placement of the toy robot.
Any move that would cause the robot to fall must be ignored.

* PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.
* The origin (0,0) can be considered to be the SOUTH WEST most corner.
* The first valid command to the robot is a PLACE command, after that, any sequence of commands may be issued, in any order, including another PLACE command. The application should discard all commands in the sequence until a valid PLACE command has been executed.
* MOVE will move the toy robot one unit forward in the direction it is currently facing.
* LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot.
* REPORT will announce the X,Y and F of the robot.


# Build Project

The project is built using the  [gradle](http://www.gradle.org/ "Title") build system.

## Build Application JAR


    ./gradlew assemble


This will result in a jar being creating under the `build/libs/` directory.  The jar name will be `robot-1.0.jar`.

## Run Code Coverage Report

The application uses the cobertura plugin to generate code coverage report.  To generate this report run the following command:


    ./gradlew cobertura

The report can be found under `build/reports/cobertura`.  You can open the `index.html` report in a
browser to see the code coverage of the project.

## Usage

The application can read commands either from standard input or via a file.

### Standard Input

To run the application to read command from standard input use the following command:

    java -jar build/libs/robot-1.0.jar

The application will then wait for commands to be inputted.  To exit the application use CTRL-C.

### File Input

To run the application with file input containing commands use the following command:

    java -jar build/libs/robot-1.0.jar -f <path-to-file>

The application will then issue the commands in the file, to the robot.  There are example scenarios in the parent directory
of the project.

1. scenario001.txt
2. scenario002.txt
3. scenario003.txt

## Example Input and Output
a.

    PLACE 0,0,NORTH
    MOVE
    REPORT
    Output: 0,1,NORTH

b.

    PLACE 0,0,NORTH
    LEFT
    REPORT
    Output: 0,0,WEST

c.

    PLACE 1,2,EAST
    MOVE
    MOVE
    LEFT
    MOVE
    REPORT
    Output: 3,3,NORTH


