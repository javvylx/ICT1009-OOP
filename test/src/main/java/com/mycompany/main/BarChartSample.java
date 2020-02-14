package com.mycompany.main;

import java.awt.event.WindowEvent;
import java.beans.EventHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class BarChartSample extends Application {
    final static String austria = "Australia";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";
    final static String sg = "SG";
    Stage s;
    @Override public void start(Stage stage) {
        s=stage;
        stage.setTitle("Stock Price");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Stock Price"); //Title of BarChart 
        xAxis.setLabel("Country");       //x-axis chart
        yAxis.setLabel("Stock rate"); //y-axis chart
 
        XYChart.Series series1 = new XYChart.Series();
        //For single line bar char for multi line we have to make more series and give it more values
        
        series1.setName("2020");       
        series1.getData().add(new XYChart.Data(austria, 25601.34));
        series1.getData().add(new XYChart.Data(brazil, 20148.82));
        series1.getData().add(new XYChart.Data(france, 10000));
        series1.getData().add(new XYChart.Data(italy, 35407.15));
        series1.getData().add(new XYChart.Data(usa, 12000));  
        series1.getData().add(new XYChart.Data(sg, 15333));  
        
        Scene scene  = new Scene(bc,500,600);// Create Window Scene
         Platform.setImplicitExit(false);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        
        stage.show();
        stage.setOnCloseRequest(new javafx.event.EventHandler<javafx.stage.WindowEvent>() {
            @Override
            public void handle(javafx.stage.WindowEvent t) {
                Platform.exit(); // Program Exit
                System.exit(0);
            }
        });
        
    }
    public static void main(String[] args) {
        launch(null); //Application is launch only once in Java
        
    }
}