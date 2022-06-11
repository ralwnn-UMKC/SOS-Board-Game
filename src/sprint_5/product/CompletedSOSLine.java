package sprint_5.product;

import java.awt.Color;

public class CompletedSOSLine {
    private final Color lineColor;
    private final Coordinates startCoordinate, endCoordinate;

    public CompletedSOSLine(Color lineColor, Coordinates startCoordinate,
            Coordinates endCoordinate) {
        this.lineColor = lineColor;
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
    }

    public Color getLineColor() {
        return lineColor; }

    public Coordinates getStartCoordinate() {
        return startCoordinate; }

    public Coordinates getEndCoordinate() {
        return endCoordinate; }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LineSegment [lineColor=");
        builder.append(lineColor);
        builder.append(", startCoordinate=");
        builder.append(startCoordinate.toString());
        builder.append(", endCoordinate=");
        builder.append(endCoordinate.toString());
        builder.append("]");
        return builder.toString();
    }
}

