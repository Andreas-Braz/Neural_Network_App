package barchartrecognition;

import canvaschartrecognition.FFBP;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Font;

// class implementing the output of the application ( barchart)

public class OutputChart extends BarChart<String, Number> {

    private static final int CHARTWIDTH = 160;
    private static final int CHARTHEIGHT = 200;

    private FFBP nn= null;

    private XYChart.Series<String, Number> series = null;

    private static XYChart.Data<String, Number>[] inputData = null;


    public OutputChart(FFBP nn) {
        super(new CategoryAxis(), new NumberAxis(0.0, 1.0, 0.2));
        this.nn = nn;

        series = new XYChart.Series<>();
        inputData = new XYChart.Data[8];
        String labels = "ABCDEFGH";

        // add data for each bar at their label
        for (int i = 0; i < 8; i++) {
            inputData[i] = new XYChart.Data<>(labels.substring(i, i + 1), nn.getOutput()[i]);
            series.getData().add(inputData[i]);
        }

        //formatting and colouring of the barchart
        getData().add(series);
        setLegendVisible(false);
        setVerticalGridLinesVisible(true);
        getYAxis().setTickLabelsVisible(true);
        getYAxis().setTickMarkVisible(true);
        getYAxis().setOpacity(100);
        getXAxis().setTickMarkVisible(true);
        getXAxis().setTickLabelsVisible(true);
        getXAxis().setOpacity(100);
        setMaxSize(CHARTWIDTH, CHARTHEIGHT);
        setMinWidth(CHARTWIDTH);
        setPadding(new Insets(0,-10,0,-10));
        setCategoryGap(6);
        setBarGap(0);
        setAnimated(false);
        getXAxis().setTickLength(10);
        getXAxis().setTickLabelFont(new Font(12));

        Node n = super.lookup(".data0.chart-bar");
        n.setStyle("-fx-bar-fill: forestgreen");

        n = super.lookup(".data1.chart-bar");
        n.setStyle("-fx-bar-fill: forestgreen");

        n = super.lookup(".data2.chart-bar");
        n.setStyle("-fx-bar-fill: forestgreen");

        n = super.lookup(".data3.chart-bar");
        n.setStyle("-fx-bar-fill: forestgreen");

        n = super.lookup(".data4.chart-bar");
        n.setStyle("-fx-bar-fill: forestgreen");

        n = super.lookup(".data5.chart-bar");
        n.setStyle("-fx-bar-fill: forestgreen");

        n = super.lookup(".data6.chart-bar");
        n.setStyle("-fx-bar-fill: forestgreen");

        n = super.lookup(".data7.chart-bar");
        n.setStyle("-fx-bar-fill: forestgreen");

    }

    // obtain the output data of the nn
    public void showNewData() {
        for (int i = 0; i < 8; i++) {
            inputData[i].setYValue(nn.getOutput()[i]);
        }
    }

    //set chart with data from the nn
    public void setChart(FFBP nn) {
        this.nn = nn;
        showNewData();
    }

}