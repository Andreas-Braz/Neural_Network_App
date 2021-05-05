package canvaschartrecognition;


import java.util.Arrays;
import java.util.Random;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;


//Group members : Andrei Martins, Josh Kornfeld
//Class makes interface + handling neural network & it's output

public class InterfaceCreation extends Application  {

    // gap between squares.
    private static final int VGAP = 1;
    private static final int HGAP = 1;

    // width and height of the borders
    private static final double TOP_INSET = 5;
    private static final double RIGHT_INSET = 5;
    private static final double BOTTOM_INSET = 30;
    private static final double LEFT_INSET = 0;

    // title of the application
    private static final String TITLE = "Monitored Assignment 2";

    //buttons
    private Button bNEW_NET = null;
    private ToggleButton bNOISE = null;
    private Button bLEARN = null;
    private Button bA = null;
    private Button bB = null;
    private Button bC = null;
    private Button bD = null;
    private Button bE = null;
    private Button bF = null;
    private Button bG = null;
    private Button bH = null;

    // buttons name
    private static final String bNEW_NET_NAME = "New Net";
    private static final String bNOISE_NAME = "Noise";
    private static final String bLEARN_NAME = "Learn 500 Cycles";
    private static final String bA_NAME = "A";
    private static final String bB_NAME = "B";
    private static final String bC_NAME = "C";
    private static final String bD_NAME = "D";
    private static final String bE_NAME = "E";
    private static final String bF_NAME = "F";
    private static final String bG_NAME = "G";
    private static final String bH_NAME = "H";


    // button size.
    private static final int BWIDTH = 160;
    private static final int BHEIGHT = 20;

    // barchart canvas
    Canvas pixelCanvas = new Canvas(CANVASW, CANVASH);
    GraphicsContext gc = pixelCanvas.getGraphicsContext2D();
    private static final int CANVASW = 160;
    private static final int CANVASH = 200;

    // barchart labels
    private String labels = "A    B    C    D    E    F    G   H";

    // barchart variable
    private double aHeight;
    private double bHeight;
    private double cHeight;
    private double dHeight;
    private double eHeight;
    private double fHeight;
    private double gHeight;
    private double hHeight;

    //define a gridpane
    GridPane grid = null;



    // separators in the interface
    private final Separator separator = new Separator();
    private final Separator separator2 = new Separator();

    // define a neural network
    private FFBP nn = null;


    public static void main(String[] args) {

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    public void start(Stage myStage) {

        // set applications title
        myStage.setTitle(TITLE);

        // create a HBox to hold all components
        HBox mainNode = new HBox(10);

        // create HBox for the gridpane to draw on
        HBox rootNode = new HBox(15);

        //VBox for the buttons menu + barchart.
        VBox buttonNode = new VBox(5);

        // Set border around the gridpane
        rootNode.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT, new Insets(TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET))));

        // create the grid pane and set in on the scene.
        GridPane grid = new GridPane();

        // gap between pixels.
        grid.setVgap(VGAP);
        grid.setHgap(HGAP);

        //setting the size of the canvas and creating a square in each iteration in a location of the gridpane.
        InterfaceControl.createGrid(grid);

        // set the neural network
        createNewNet();

        // add the main node to scene
        Scene myScene = new Scene(mainNode, rootNode.getMinWidth(), rootNode.getMinHeight());

        // creating the buttons and adding to VBox.
        bNEW_NET = new Button(bNEW_NET_NAME);
        bNEW_NET.setMinWidth(BWIDTH);
        bNEW_NET.setMaxHeight(BHEIGHT);
        bNOISE = new ToggleButton(bNOISE_NAME);
        bNOISE.setMinWidth(BWIDTH);
        bNOISE.setMaxHeight(BHEIGHT);
        bLEARN = new Button(bLEARN_NAME);
        bLEARN.setMinWidth(BWIDTH);
        bLEARN.setMaxHeight(BHEIGHT);
        bA = new Button(bA_NAME);
        bA.setMinWidth(BWIDTH);
        bA.setMaxHeight(BHEIGHT);
        bB = new Button(bB_NAME);
        bB.setMinWidth(BWIDTH);
        bB.setMaxHeight(BHEIGHT);
        bC = new Button(bC_NAME);
        bC.setMinWidth(BWIDTH);
        bC.setMaxHeight(BHEIGHT);
        bD = new Button(bD_NAME);
        bD.setMinWidth(BWIDTH);
        bD.setMaxHeight(BHEIGHT);
        bE = new Button(bE_NAME);
        bE.setMinWidth(BWIDTH);
        bE.setMaxHeight(BHEIGHT);
        bF = new Button(bF_NAME);
        bF.setMinWidth(BWIDTH);
        bF.setMaxHeight(BHEIGHT);
        bG = new Button(bG_NAME);
        bG.setMinWidth(BWIDTH);
        bG.setMaxHeight(BHEIGHT);
        bH = new Button(bH_NAME);
        bH.setMinWidth(BWIDTH);
        bH.setMaxHeight(BHEIGHT);

        // display the barchart
        setBarHeight();


        //handle events for each button when pressed
        bNEW_NET.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                InterfaceControl.createGrid(grid); // clear whats on screen
                createNewNet();	// reinitialize the net
                addNewData(); // add the new data to the chart
            }

        });

        bLEARN.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                useNN(); // learn 500 cycles
                addNewData(); // add the new data to the chart
            }

        });

        //handle events for character buttons, same procedure for all
        //clear screen so only 1 character on screen
        //paint the character, optionally with noise
        //add the data to the chart
        bA.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                InterfaceControl.createGrid(grid);
                InterfaceControl.paintCharacter(CharacterButtons.getCharacterA(bNOISE.isSelected()),grid);
                addNewData();
            }

        });

        bB.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                InterfaceControl.createGrid(grid);
                InterfaceControl.paintCharacter(CharacterButtons.getCharacterB(bNOISE.isSelected()),grid);
                addNewData();
            }
        });

        bC.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {

                InterfaceControl.createGrid(grid);
                InterfaceControl.paintCharacter(CharacterButtons.getCharacterC(bNOISE.isSelected()),grid);
                addNewData();
            }
        });

        bD.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {

                InterfaceControl.createGrid(grid);
                InterfaceControl.paintCharacter(CharacterButtons.getCharacterD(bNOISE.isSelected()),grid);
                addNewData();
            }
        });

        bE.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {

                InterfaceControl.createGrid(grid);
                InterfaceControl.paintCharacter(CharacterButtons.getCharacterE(bNOISE.isSelected()),grid);
                addNewData();

            }
        });

        bF.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {

                InterfaceControl.createGrid(grid);
                InterfaceControl.paintCharacter(CharacterButtons.getCharacterF(bNOISE.isSelected()),grid);
                addNewData();

            }
        });

        bG.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {

                InterfaceControl.createGrid(grid);
                InterfaceControl.paintCharacter(CharacterButtons.getCharacterG(bNOISE.isSelected()),grid);
                addNewData();

            }
        });

        bH.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {

                InterfaceControl.createGrid(grid);
                InterfaceControl.paintCharacter(CharacterButtons.getCharacterH(bNOISE.isSelected()),grid);

                addNewData();
            }
        });

        // paint pixels while mouse dragged
        // add new data to the chart
        grid.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me) {
                InterfaceControl.handleEvent(grid, me);
                addNewData();
            }
        });

        // paint pixels with left click, clear pixels with right click
        // add new data to the chart
        grid.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me) {
                InterfaceControl.handleEvent(grid, me);
                addNewData();
                //event handler to erase.
                if (me.isSecondaryButtonDown()) {
                    InterfaceControl.createGrid(grid);
                    addNewData();
                };
            }
        });


        //move VBox away from edges
        Insets insetRight = new Insets(0,0,0,10);
        VBox.setMargin(bNOISE, insetRight);
        VBox.setMargin(bLEARN, insetRight);
        VBox.setMargin(bA, insetRight);
        VBox.setMargin(bB, insetRight);
        VBox.setMargin(bC, insetRight);
        VBox.setMargin(bD, insetRight);
        VBox.setMargin(bE, insetRight);
        VBox.setMargin(bF, insetRight);
        VBox.setMargin(bG, insetRight);
        VBox.setMargin(bH, insetRight);
        VBox.setMargin(pixelCanvas, insetRight);
        VBox.setMargin(separator, insetRight);
        VBox.setMargin(separator2, insetRight);

        // extra specified inset to move only the top button
        Insets insetTop = new Insets(5,0,0,10);
        VBox.setMargin(bNEW_NET, insetTop);

        //add the objects to the VBox interface
        buttonNode.getChildren().addAll(bNEW_NET, bNOISE,bLEARN, separator,
                bA,bB,bC,bD,bE,bF,bG,bH, separator2,
                pixelCanvas);


        // add grid to the HBox interface
        rootNode.getChildren().addAll(grid);

        // add both panes to the main pane
        mainNode.getChildren().addAll(buttonNode, rootNode);
        myStage.setScene(myScene);
        myStage.sizeToScene();
        myStage.setResizable(false);



        //show the application
        myStage.show();

    }

    //method initialising neural network with given parameters
    public void createNewNet() {

        int[] layout = {256, 16, 8};
        nn = new FFBP(layout);
        nn.randomize(-0.1, +0.1);
        nn.setEta(0.5);
        nn.setAlpha(0.5);
        nn.activateInputAndFeedForward(InterfaceControl.flattenMatrix(CharacterButtons.getEmptyMatrix()));
    }

    //method used to train the network with 500 cycles
    public void useNN() {

        //define initial input vectors
        double[] ivA = InterfaceControl.flattenMatrix(CharacterButtons.getCharacterA(bNOISE.isSelected()));
        double[] ivB = InterfaceControl.flattenMatrix(CharacterButtons.getCharacterB(bNOISE.isSelected()));
        double[] ivC = InterfaceControl.flattenMatrix(CharacterButtons.getCharacterC(bNOISE.isSelected()));
        double[] ivD = InterfaceControl.flattenMatrix(CharacterButtons.getCharacterD(bNOISE.isSelected()));
        double[] ivE = InterfaceControl.flattenMatrix(CharacterButtons.getCharacterE(bNOISE.isSelected()));
        double[] ivF = InterfaceControl.flattenMatrix(CharacterButtons.getCharacterF(bNOISE.isSelected()));
        double[] ivG = InterfaceControl.flattenMatrix(CharacterButtons.getCharacterG(bNOISE.isSelected()));
        double[] ivH = InterfaceControl.flattenMatrix(CharacterButtons.getCharacterH(bNOISE.isSelected()));

        //define desired output vectors
        double[] ovA = {1,0,0,0,0,0,0,0};
        double[] ovB = {0,1,0,0,0,0,0,0};
        double[] ovC = {0,0,1,0,0,0,0,0};
        double[] ovD = {0,0,0,1,0,0,0,0};
        double[] ovE = {0,0,0,0,1,0,0,0};
        double[] ovF = {0,0,0,0,0,1,0,0};
        double[] ovG = {0,0,0,0,0,0,1,0};
        double[] ovH = {0,0,0,0,0,0,0,1};

        //random number generator
        Random r = new Random();

        //500 cycles, 1 of 8 random inputs fed in each cycle and compared with desired output
        for (int i = 0; i <500; ++i) {
            switch (r.nextInt(8)) {
                case 0:
                    nn.activateInputAndFeedForward(ivA);
                    nn.applyDesiredOutputAndPropagateBack(ovA);
                    break;
                case 1:
                    nn.activateInputAndFeedForward(ivB);
                    nn.applyDesiredOutputAndPropagateBack(ovB);
                    break;
                case 2:
                    nn.activateInputAndFeedForward(ivC);
                    nn.applyDesiredOutputAndPropagateBack(ovC);
                    break;
                case 3:
                    nn.activateInputAndFeedForward(ivD);
                    nn.applyDesiredOutputAndPropagateBack(ovD);
                    break;
                case 4:
                    nn.activateInputAndFeedForward(ivE);
                    nn.applyDesiredOutputAndPropagateBack(ovE);
                    break;
                case 5:
                    nn.activateInputAndFeedForward(ivF);
                    nn.applyDesiredOutputAndPropagateBack(ovF);
                    break;
                case 6:
                    nn.activateInputAndFeedForward(ivG);
                    nn.applyDesiredOutputAndPropagateBack(ovG);
                    break;
                case 7:
                    nn.activateInputAndFeedForward(ivH);
                    nn.applyDesiredOutputAndPropagateBack(ovH);
                    break;

            }
            addNewData();
        }

    }

    // feed data from grid into the network and display the output on chart after 500 cycles
    public void addNewData() {
        nn.activateInputAndFeedForward(InterfaceControl.flattenMatrix(InterfaceControl.getInput()));
        setBarHeight();
    }

    // obtain the output data from the nn and use data to determine height of each bar in barchart
    public void getData() {
        for (int i = 0; i <8;i++) {
            if (i == 0) {
                aHeight = (nn.getOutput()[i] * 180);
            }
            if (i == 1) {
                bHeight = (nn.getOutput()[i] * 180);
            }
            if (i == 2) {
                cHeight = (nn.getOutput()[i] * 180);
            }
            if (i == 3) {
                dHeight = (nn.getOutput()[i] * 180);
            }
            if (i == 4) {
                eHeight = (nn.getOutput()[i] * 180);
            }
            if (i == 5) {
                fHeight = (nn.getOutput()[i] * 180);
            }
            if (i == 6) {
                gHeight = (nn.getOutput()[i] * 180);
            }
            if (i == 7) {
                hHeight = (nn.getOutput()[i] * 180);

            }
        }
    }

    //display the output of the nn
    public Node setBarHeight() {

        // set labels of bars
        gc.setFill(Color.BLACK);
        gc.fillText(labels, 5, 195);

        // clear canvas
        clearChart();

        // repaint canvas with new data
        // formatting (different horizontal line
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 180, 1);
        gc.fillRect(0, 180, 180, 2);
        gc.setFill(Color.GREY);
        double lineWidth = 2;
        double lineLength = 180;
        gc.fillRect(0, 0, lineLength, lineWidth);
        gc.fillRect(0, 180, lineLength, lineWidth);
        gc.setFill(Color.GREY);
        gc.fillRect(0, 30, lineLength, lineWidth);
        gc.fillRect(0, 60, lineLength, lineWidth);
        gc.fillRect(0, 90, lineLength, lineWidth);
        gc.fillRect(0, 120, lineLength, lineWidth);
        gc.fillRect(0, 150, lineLength, lineWidth);

        // paint the bars with the most recent data of the nn and return it
        gc.setFill(Color.GREEN);
        double width = 10;
        getData();
        gc.fillRect(5,0,width,aHeight); // A
        gc.fillRect(25,0,width,bHeight); // B
        gc.fillRect(45,0,width,cHeight); // C
        gc.fillRect(65,0,width,dHeight); // D
        gc.fillRect(85,0,width,eHeight); // E
        gc.fillRect(105,0,width,fHeight); // F
        gc.fillRect(125,0,width,gHeight); // G
        gc.fillRect(145,0,width,hHeight); // H

        return pixelCanvas;

    }

    // clear the entire canvas so it can repainted with new data
    public void clearChart() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, CANVASW, 180);
    }
}