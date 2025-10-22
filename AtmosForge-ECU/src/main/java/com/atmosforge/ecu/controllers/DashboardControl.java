package com.atmosforge.ecu.controllers;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardControl extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Top bar
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #2c3e50;");
        Label title = new Label("Environment Control Unit");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
        topBar.getChildren().add(title);

        // Side menu
        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(10));
        sideMenu.setStyle("-fx-background-color: #34495e;");
        sideMenu.setPrefWidth(180);

        Button btnTemperature = new Button("Temperature Control");
        Button btnHumidity = new Button("Humidity Control");
        Button btnPressure = new Button("Pressure");

        sideMenu.getChildren().addAll(btnTemperature, btnHumidity, btnPressure);

        // Main content area
        VBox mainContent = new VBox();
        mainContent.setPadding(new Insets(20));
        Label welcomeLabel = new Label("Welcome to the Environment Control Dashboard");
        welcomeLabel.setStyle("-fx-font-size: 16px;");
        mainContent.getChildren().add(welcomeLabel);

        // Layout setup
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sideMenu);
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Environment Control Unit");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //test to see if branch works

}