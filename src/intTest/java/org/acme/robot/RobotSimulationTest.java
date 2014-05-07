package org.acme.robot;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class RobotSimulationTest {

    @Test
    public void shouldRunFromFile() {
        //given
        URL url = getClass().getClassLoader().getResource("scenario001.txt");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        //when
        RobotSimulator.main(new String[] {"-f", url.getFile()});

        //then
        assertThat(outputStream.toString(), containsString("0,1,NORTH"));
    }
}
