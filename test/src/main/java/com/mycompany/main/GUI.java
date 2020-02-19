package com.mycompany.main;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class GUI extends Application {
	public BarChartSample barChart;
	public Object search;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fXMLLoader;
			Parent root = FXMLLoader.load(getClass().getResource("GUIBuild.fxml"));
			primaryStage.setTitle("Project");
			primaryStage.setScene(new Scene(root, 1159, 760));
			primaryStage.show();
				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML // Display StockCharts FXML
    private void DisplayStockCharts(ActionEvent event) throws IOException {
   	
   	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StockCharts.fxml"));
   	Parent root1 = (Parent) fxmlLoader.load();
   	Stage stage = new Stage();	
   	stage.setScene(new Scene(root1)); 
   	
   	stage.show();
   	
   }
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
