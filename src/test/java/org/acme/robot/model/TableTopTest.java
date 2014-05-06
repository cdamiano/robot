package org.acme.robot.model;

import org.acme.robot.model.TableTop;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class TableTopTest {

    private TableTop tableTop;

    private Point point;
    private boolean result;

    public TableTopTest(Point point, boolean expected) {
        this.point = point;
        this.result = expected;
    }

    @Before
    public void createTableTop() {
        tableTop = new TableTop(5,5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void widthShouldBePositive() {
        tableTop = new TableTop(0, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void heightShouldBePositive() {
        tableTop = new TableTop(5, 0);
    }

    @Test
    public void shouldValidatePointAgainstTable() {
        assertThat(tableTop.isValidPosition(point), is(equalTo(result)));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> addedNumbers() {
        return Arrays.asList( new Object[][] {
                {new Point(5,5), TRUE},   //0
                {new Point(5,0), TRUE},   //1
                {new Point(0,5), TRUE},   //2
                {new Point(0,0), TRUE},   //3
                {new Point(6,0), FALSE},  //4
                {new Point(5,6), FALSE},  //5
                {new Point(-1,5), FALSE}, //6
                {new Point(0,-1), FALSE}, //7
                {null, FALSE}             //8
        });
    }
}
