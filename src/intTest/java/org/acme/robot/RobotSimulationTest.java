package org.acme.robot;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class RobotSimulationTest {

    private String commands;

    private ByteArrayOutputStream outputStream;

    @Before
    public void setup() {
        commands = "PLACE 0,0,NORTH\n" +
                   "RIGHT\n" +
                   "MOVE\n" +
                   "REPORT";

        InputStream inputStream = new ByteArrayInputStream(commands.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

    }


    @Test
    public void shouldRunFromFile() {
        //given
        URL url = getClass().getClassLoader().getResource("scenario001.txt");

        //when
        RobotSimulator.main(new String[] {"-f", url.getFile()});

        //then
        assertThat(outputStream.toString(), containsString("0,1,NORTH"));
    }

    @Test
    public void shouldRunFromStandardInput() {
        //given

        //when
        RobotSimulator.main(new String[] {});

        //then
        assertThat(outputStream.toString(), containsString("1,0,EAST"));

    }

    @Test
    public void shouldUseStandardInputWhenFileNotFound() {
        //given

        //when
        RobotSimulator.main(new String[] {"-f", "empty.txt"});

        //then
        assertThat(outputStream.toString(), containsString("1,0,EAST"));
    }

}
