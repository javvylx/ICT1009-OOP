package com.mycompany.main;

import javafx.application.Application;
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
			Parent root = FXMLLoader.load(getClass().getResource("GUIBuild.fxml"));
			primaryStage.setTitle("A 1009 OOP Project");
			primaryStage.setScene(new Scene(root, 1159, 760));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}