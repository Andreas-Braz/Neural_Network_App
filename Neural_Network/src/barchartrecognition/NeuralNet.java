package barchartrecognition;

// Class to handle button and mouse events and the neural network

import java.util.Random;

import canvaschartrecognition.CharacterButtons;
import canvaschartrecognition.FFBP;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class NeuralNet extends VBox {
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


    //butons name
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


    //button size.
    private static final int BWIDTH = 160;
    private static final int BHEIGHT = 20;

    //initialize the gridpane
    private GridPane grid = null;

    //initialize the barchart
    private OutputChart outputChart = null;

    //initialize the 2 separators in the interface
    private final Separator separator = new Separator();
    private final Separator separator2 = new Separator();

    //initialize the nn
    private static FFBP nn = null;

    // add buttons + chart to the VBOX and handle their events
    public NeuralNet(GridPane grid) {
        super(5);

        this.grid=grid;

        //new network initialized
        createNewNet();

        //define button sizes
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

        // barchart
        outputChart = new OutputChart(nn);

        // buttons relating to the nn new net + learn buttons calling their respective methods
        bNEW_NET.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                ControlInterface.createGrid(grid);
                createNewNet();
            }

        });

        bLEARN.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                useNN();
            }

        });

        //handle events for each letter button when pressed
        bA.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                ControlInterface.createGrid(grid);
                ControlInterface.paintCharacter(CharacterButtons.getCharacterA(bNOISE.isSelected()),grid);
                addNewData();

            }
        });

        bB.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                ControlInterface.createGrid(grid);
                ControlInterface.paintCharacter(CharacterButtons.getCharacterB(bNOISE.isSelected()),grid);
                addNewData();

            }
        });

        bC.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                ControlInterface.createGrid(grid);
                ControlInterface.paintCharacter(CharacterButtons.getCharacterC(bNOISE.isSelected()),grid);
                addNewData();
            }
        });

        bD.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                ControlInterface.createGrid(grid);
                ControlInterface.paintCharacter(CharacterButtons.getCharacterD(bNOISE.isSelected()),grid);
                addNewData();
            }
        });

        bE.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                ControlInterface.createGrid(grid);
                ControlInterface.paintCharacter(CharacterButtons.getCharacterE(bNOISE.isSelected()),grid);
                addNewData();

            }
        });

        bF.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                ControlInterface.createGrid(grid);
                ControlInterface.paintCharacter(CharacterButtons.getCharacterF(bNOISE.isSelected()),grid);
                addNewData();

            }
        });

        bG.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                ControlInterface.createGrid(grid);
                ControlInterface.paintCharacter(CharacterButtons.getCharacterG(bNOISE.isSelected()),grid);
                addNewData();

            }
        });

        bH.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                ControlInterface.createGrid(grid);
                ControlInterface.paintCharacter(CharacterButtons.getCharacterH(bNOISE.isSelected()),grid);
                addNewData();
            }
        });

        //event handler to drag and draw.
        grid.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me) {
                ControlInterface.handleEvent(grid, me);
                addNewData();
            }
        });

        // event handler to click.
        grid.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me) {
                ControlInterface.handleEvent(grid, me);
                addNewData();
                //event handler to erase.
                if (me.isSecondaryButtonDown()) {
                    ControlInterface.createGrid(grid);
                    addNewData();
                };
            }
        });


        // extra specified inset to move only the top button
        Insets insetTop = new Insets(5,0,0,10);

        VBox.setMargin(bNEW_NET, insetTop);

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
        VBox.setMargin(outputChart, insetRight);
        VBox.setMargin(separator, insetRight);
        VBox.setMargin(separator2, insetRight);

        //add all buttons to the VBox for display
        super.getChildren().addAll(bNEW_NET, bNOISE,bLEARN, separator,
                bA,bB,bC,bD,bE,bF,bG,bH, separator2, outputChart
        );

    }

    //method initialising neural network with given parameters
    public void createNewNet() {

        int[] layout = {256, 16, 8};
        nn = new FFBP(layout);
        nn.randomize(-0.1, +0.1);
        nn.setEta(0.5);
        nn.setAlpha(0.5);
        nn.activateInputAndFeedForward(ControlInterface.flattenMatrix(CharacterButtons.getEmptyMatrix()));
        //show the initial data
        if (outputChart != null) {
            outputChart.setChart(nn);
        }
    }

    //method used to train the network with 500 cycles
    public void useNN() {

        //define initial input vectors
        double[] ivA = ControlInterface.flattenMatrix(CharacterButtons.getCharacterA(bNOISE.isSelected()));
        double[] ivB = ControlInterface.flattenMatrix(CharacterButtons.getCharacterB(bNOISE.isSelected()));
        double[] ivC = ControlInterface.flattenMatrix(CharacterButtons.getCharacterC(bNOISE.isSelected()));
        double[] ivD = ControlInterface.flattenMatrix(CharacterButtons.getCharacterD(bNOISE.isSelected()));
        double[] ivE = ControlInterface.flattenMatrix(CharacterButtons.getCharacterE(bNOISE.isSelected()));
        double[] ivF = ControlInterface.flattenMatrix(CharacterButtons.getCharacterF(bNOISE.isSelected()));
        double[] ivG = ControlInterface.flattenMatrix(CharacterButtons.getCharacterG(bNOISE.isSelected()));
        double[] ivH = ControlInterface.flattenMatrix(CharacterButtons.getCharacterH(bNOISE.isSelected()));

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

        nn.activateInputAndFeedForward(ControlInterface.flattenMatrix(ControlInterface.getInput()));
        outputChart.showNewData();
    }
}

