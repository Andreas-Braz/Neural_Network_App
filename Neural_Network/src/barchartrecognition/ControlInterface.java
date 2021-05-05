package barchartrecognition;


import java.util.Random;

import barchartrecognition.Pixel;
import canvaschartrecognition.CharacterButtons;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

//class handling methods that help control buttons / mouse events

public class ControlInterface {
    //set number of squares on the canvas.
    private static final int CWIDTH = 16;
    private static final int CHEIGHT = 16;

    // width and height of the squares
    private static final double RWIDTH = 32.0;
    private static final double RHEIGHT = 32.0;

    // colours for the pixels
    private static final Color INIT_COLOR = Color.WHITE;
    private static final Color PAINT_COLOR = Color.BLACK;

    // gap between squares.
    private static final int VGAP = 1;
    private static final int HGAP = 1;

    // matrix to store the drawn character on the grid
    private static double[][] inputMatrix = null;

    // method drawing a character corresponding to a button
    public static void paintCharacter(double[][] character,GridPane grid) {
        for (int column = 0; column < 16; column++) {
            for (int row = 0; row < 16; row++) {
                if (character[column][row] == 1) {
                    Pixel pixel = new Pixel(RWIDTH,RHEIGHT,PAINT_COLOR);
                    grid.add(pixel, row, column);
                }
            }
        }
        inputMatrix = character; // method will also store the matrix of said character
    }

    // method creating noise to a character corresponding to a button
    public static double[][] createNoise(double[][] character) {
        Random rand = new Random();
        for (int column = 0; column < 16; column++) {
            for (int row = 0; row < 16; row++) {
                if (rand.nextInt(10) == 1) {
                    character[column][row] =flipPixel(character[column][row]);
                }
            }
        }
        return character;
    }

    // method flipping pixels on the grid when noise is created
    public static double flipPixel(double pixel) {
        if (pixel == 0) {
            return 1;
        }
        return 0;
    }

    // method to handle mouse when the mouse is pressed and dragged. It gets the coordinates
    // of the mouse (X and Y) and creates a square.
    public static void handleEvent(GridPane grid, MouseEvent me) {
        if (me.isPrimaryButtonDown()) {

            int column = (int) ((me.getX())/(RHEIGHT+HGAP));
            int row = (int) ((me.getY())/(RWIDTH+VGAP));

            //column and row are the coordinates of X and Y respectively. To draw a square, it must follow 2 conditions.
            // 1- it must be equal or bigger than 0, which is the first index of each column/row. This way, we avoid errors with
            //negative coordinates. 2 - it must be less than the maximun index of the columns and rows (CWIDTH and CHEIGHT). This
            //way, we avoid painting outside the grid limits.

            if ((column >= 0 && row >= 0) && (column < CWIDTH && row < CHEIGHT)) {
                double rowIndex = row ;

                double columnIndex = column ;
                inputMatrix[(int) rowIndex][(int) columnIndex] = 1;

                Pixel pixel = new Pixel(RWIDTH,RHEIGHT,PAINT_COLOR);
                grid.add(pixel, column, row);

            }
        }
    }

    // 2 nested loops to add a square at each coordinate of the gridpane
    public static void createGrid(GridPane grid) {
        for (int column = 0; column < CWIDTH; column++) {
            for (int row = 0; row< CHEIGHT;row++) {
                Pixel pixel = new Pixel(RWIDTH,RHEIGHT,INIT_COLOR);
                grid.add(pixel, column, row);
            }
        } inputMatrix = CharacterButtons.getEmptyMatrix(); // matrix set to 0 in every cell
    }

    // method to allowing other methods to get the data on the screen
    public static double[][] getInput() {
        return inputMatrix;
    }

    // method to turn the 2D matrix into 1D matrix so it's usable for FFBP methods
    public static double [] flattenMatrix (double[][] array) {
        double newArray[] = new double[array.length*array[0].length]; // define size of the 1D matrix
        for(int i = 0; i < array.length; i++) {
            double[] row = array[i];  // fill a new array with data from the initial 2D array
            for(int j = 0; j < row.length; j++) { // loop over this array while setting the contents of each cell into the output array
                double number = array[i][j];  //obtain data at array[i][j]
                newArray[i*row.length+j] = number; //add the value of cell array[i][j] in series to the new array
            }
        }
        return newArray;
    }

}
