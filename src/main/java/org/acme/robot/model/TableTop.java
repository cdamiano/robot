package org.acme.robot.model;

import java.awt.Point;

import static com.google.common.base.Preconditions.checkArgument;

public class TableTop {

    private final Integer width;
    private final Integer height;

    public TableTop(int width, int height) {
        checkArgument(width > 0, "Table Top width must be greater then zero.");
        checkArgument(height > 0, "Table Top width must be greater then zero.");
        this.width = width;
        this.height = height;
    }

    public boolean isValidPosition(Point point) {
        return point != null && isValidYCoordinate(point) && isValidXCoordinate(point);
    }

    private boolean isValidXCoordinate(Point point) {
        return point.x >= 0 && point.x <= width;
    }

    private boolean isValidYCoordinate(Point point) {
        return point.y >= 0 && point.y <= height;
    }
}
