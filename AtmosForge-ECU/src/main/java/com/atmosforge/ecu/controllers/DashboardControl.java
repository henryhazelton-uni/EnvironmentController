package com.atmosforge.ecu.controllers;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DashboardControl extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Top bar
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: #2c3e50;");
        Label title = new Label("Environment Control Unit");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 28px; -fx-font-weight: bold;");
        topBar.getChildren().add(title);

        //Side menu
        VBox sideMenu = new VBox(15);
        sideMenu.setPadding(new Insets(15));
        sideMenu.setStyle("-fx-background-color: #34495e;");
        sideMenu.setPrefWidth(200);

        Button btnTemperature = new Button("Temperature Control");
        Button btnHumidity = new Button("Humidity Control");
        Button btnPressure = new Button("Pressure");

        sideMenu.getChildren().addAll(btnTemperature, btnHumidity, btnPressure);

        //Main content area
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(20));
        mainContent.setFillWidth(true);
        VBox.setVgrow(mainContent, Priority.ALWAYS);

        Label welcomeLabel = new Label("Welcome to the Environment Control Dashboard");
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        mainContent.getChildren().add(welcomeLabel);

        //Status indicator row
        HBox statusRow = new HBox(40);
        statusRow.setPadding(new Insets(10));
        statusRow.setStyle("-fx-background-color: #ecf0f1;");
        statusRow.setPrefHeight(Region.USE_COMPUTED_SIZE);

        statusRow.getChildren().addAll(
            createStatusIndicator("Temperature", "#2ecc71", "22.5°C"),
            createStatusIndicator("Pressure", "#f1c40f", "101 kPa"),
            createStatusIndicator("Humidity", "#e74c3c", "78%")
        );

        mainContent.getChildren().add(statusRow);

        //Space for potential logging system area if we add
        VBox loggingPanel = new VBox();
        loggingPanel.setPadding(new Insets(10));
        loggingPanel.setStyle("-fx-background-color: #bdc3c7;");
        Label logLabel = new Label("Space for logging system");
        logLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50;");
        loggingPanel.getChildren().add(logLabel);
        VBox.setVgrow(loggingPanel, Priority.ALWAYS);

        mainContent.getChildren().add(loggingPanel);

        //Layout setup
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sideMenu);
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Environment Control Unit");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //method to create labeled status indicators with value
    private Node createStatusIndicator(String labelText, String colorHex, String valueText) {
        VBox indicatorBox = new VBox(10);
        indicatorBox.setPadding(new Insets(10));
        indicatorBox.setStyle("-fx-alignment: center;");
        indicatorBox.setMaxWidth(Double.MAX_VALUE);
        indicatorBox.setPrefWidth(0);
        HBox.setHgrow(indicatorBox, Priority.ALWAYS);

        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Circle statusLight = new Circle(14);
        statusLight.setFill(Color.web(colorHex));

        Label valueLabel = new Label(valueText);
        valueLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");

        indicatorBox.getChildren().addAll(label, statusLight, valueLabel);
        return indicatorBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
