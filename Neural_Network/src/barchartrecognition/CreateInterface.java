package barchartrecognition;



import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;



//Group members : Andrei Martins, Josh Kornfeld

//Class makes interface

public class CreateInterface extends Application  {

    // gap between squares.
    private static final int VGAP = 1;
    private static final int HGAP = 1;

    // width and height of the borders
    private static final double TOP_INSET = 5;
    private static final double RIGHT_INSET = 5;
    private static final double BOTTOM_INSET = 30;
    private static final double LEFT_INSET = 0;

    // define title of application
    private static final String TITLE = "Monitored Assignment 2";

    public static void main(String[] args) {

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    public void start(Stage myStage) {

        // set title of application
        myStage.setTitle(TITLE);

        // HBox containing entire project
        HBox applicationNode = new HBox(10);

        // HBox for the drawing pane of the project
        HBox drawingPane = new HBox(15);

        // Set border around the drawing space
        drawingPane.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT, new Insets(TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET))));

        //create the grid pane and set in on the scene.
        GridPane grid = new GridPane();
        // gap between pixels.
        grid.setVgap(VGAP);
        grid.setHgap(HGAP);

        //setting the size of the canvas and creating a square in each iteration in a location of the gridpane.
        ControlInterface.createGrid(grid);
        drawingPane.getChildren().addAll(grid);

        //add the primary pane to the scene
        Scene myScene = new Scene(applicationNode, drawingPane.getMinWidth(), drawingPane.getMinHeight());

        // creating the buttons and adding to VBox.
        NeuralNet controlNode = new NeuralNet(grid);


        // add the buttons, chart and drawing pane to the primary pane
        applicationNode.getChildren().addAll(controlNode, drawingPane);
        myStage.setScene(myScene);
        myStage.sizeToScene();
        myStage.setResizable(false);

        // display the application
        myStage.show();

    }


}