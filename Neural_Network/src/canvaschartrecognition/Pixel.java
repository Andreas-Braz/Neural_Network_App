package canvaschartrecognition;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Pixel extends Rectangle {

    // width and height of the squares
    private static final double RWIDTH = 32.0;
    private static final double RHEIGHT = 32.0;

    private double width;
    private double height;

    Pixel(double width, double height, Color color) {
        this.width= width;
        this.height = height;
        //this.seqNum = row * Main.COL_COUNT + col;

        // Setting a geometric parameters of the pixel
        setWidth(RWIDTH);
        setHeight(RHEIGHT);
        // Setting a color of the pixel
        setFill(color);
    }
}
