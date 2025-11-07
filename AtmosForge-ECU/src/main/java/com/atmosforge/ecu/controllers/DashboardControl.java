package com.atmosforge.ecu.controllers;

import com.atmosforge.ecu.core.ECU;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class DashboardControl extends Application {

    private static VBox loggingPanelReference;

    // Static ECU reference (so main can set it before JavaFX launch)
    private static ECU ecu;

    private Label temperatureLabel;
    private Label pressureLabel;
    private Label humidityLabel;

    // Setter method to assign ECU before launch
    public static void setEcu(ECU ecuInstance) {
        ecu = ecuInstance;
    }

    @Override
    public void start(Stage primaryStage) {
        // Top bar
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: #2c3e50;");
        Label title = new Label("Environment Control Unit");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 28px; -fx-font-weight: bold;");
        topBar.getChildren().add(title);

        // Side menu
        VBox sideMenu = new VBox(15);
        sideMenu.setPadding(new Insets(15));
        sideMenu.setStyle("-fx-background-color: #34495e;");
        sideMenu.setPrefWidth(250);

        Label testTitle = new Label("Test Controls");
        testTitle.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        // Temperature
        Label tempLabel = new Label("Temperature (°C):");
        tempLabel.setStyle("-fx-text-fill: white;");
        TextField tempField = new TextField();

        // Pressure
        Label pressureLabelField = new Label("Pressure (kPa):");
        pressureLabelField.setStyle("-fx-text-fill: white;");
        TextField pressureField = new TextField();

        // Humidity
        Label humidityLabelField = new Label("Humidity (%):");
        humidityLabelField.setStyle("-fx-text-fill: white;");
        TextField humidityField = new TextField();

        // Apply changes button
        Button updateButton = new Button("Apply Changes");
        updateButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");
        updateButton.setMaxWidth(Double.MAX_VALUE);

        // Validation and update logic
        updateButton.setOnAction(e -> {
            try {
                double newTemp = Double.parseDouble(tempField.getText());
                double newPressure = Double.parseDouble(pressureField.getText());
                double newHumidity = Double.parseDouble(humidityField.getText());

                // Get ranges from sensors
                double tempLow = ecu.getTemperatureSensor().getLowRange();
                double tempHigh = ecu.getTemperatureSensor().getHighRange();

                double pressureLow = ecu.getPressureSensor().getLowRange();
                double pressureHigh = ecu.getPressureSensor().getHighRange();

                double humidityLow = ecu.getHumiditySensor().getLowRange();
                double humidityHigh = ecu.getHumiditySensor().getHighRange();

                // Validate ranges
                boolean tempValid = newTemp >= tempLow && newTemp <= tempHigh;
                boolean pressureValid = newPressure >= pressureLow && newPressure <= pressureHigh;
                boolean humidityValid = newHumidity >= humidityLow && newHumidity <= humidityHigh;

                if (tempValid && pressureValid && humidityValid) {
                    ecu.getTemperatureSensor().setValue(newTemp);
                    ecu.getPressureSensor().setValue(newPressure);
                    ecu.getHumiditySensor().setValue(newHumidity);
                    updateDashboardValues();
                    System.out.println("Values updated successfully.");
                } else {
                    StringBuilder errorMsg = new StringBuilder("Invalid input:\n");
                    if (!tempValid) errorMsg.append("Temperature must be between ")
                            .append(tempLow).append(" and ").append(tempHigh).append("\n");
                    if (!pressureValid) errorMsg.append("Pressure must be between ")
                            .append(pressureLow).append(" and ").append(pressureHigh).append("\n");
                    if (!humidityValid) errorMsg.append("Humidity must be between ")
                            .append(humidityLow).append(" and ").append(humidityHigh).append("\n");

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Validation Error");
                    alert.setHeaderText("Invalid Values");
                    alert.setContentText(errorMsg.toString());
                    alert.showAndWait();
                }

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please enter numeric values only.");
                alert.showAndWait();
            }
        });

        // Add elements to side menu
        sideMenu.getChildren().addAll(
                testTitle,
                tempLabel, tempField,
                pressureLabelField, pressureField,
                humidityLabelField, humidityField,
                updateButton
        );



        //Main content area
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(20));
        mainContent.setFillWidth(true);
        VBox.setVgrow(mainContent, Priority.ALWAYS);

        Label welcomeLabel = new Label("Environment Control Dashboard");
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        mainContent.getChildren().add(welcomeLabel);

        //Status indicator row
        HBox statusRow = new HBox(40);
        statusRow.setPadding(new Insets(10));
        statusRow.setStyle("-fx-background-color: #ecf0f1;");
        statusRow.setPrefHeight(Region.USE_COMPUTED_SIZE);

        //temp box
        VBox tempBox = new VBox(10);
        tempBox.setPadding(new Insets(10));
        tempBox.setStyle("-fx-alignment: center;");
        Label tempTitle = new Label("Temperature");
        tempTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Circle tempLight = new Circle(14, Color.web("#2ecc71"));
        temperatureLabel = new Label(String.format("%.1f°C", ecu.getTemperatureSensor().getValue()));
        temperatureLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");
        tempBox.getChildren().addAll(tempTitle, tempLight, temperatureLabel);

        //pressure box
        VBox pressureBox = new VBox(10);
        pressureBox.setPadding(new Insets(10));
        pressureBox.setStyle("-fx-alignment: center;");
        Label pressureTitle = new Label("Pressure");
        pressureTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Circle pressureLight = new Circle(14, Color.web("#f1c40f"));
        pressureLabel = new Label(String.format("%.1f kPa", ecu.getPressureSensor().getValue()));
        pressureLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");
        pressureBox.getChildren().addAll(pressureTitle, pressureLight, pressureLabel);

        //humidity box
        VBox humidityBox = new VBox(10);
        humidityBox.setPadding(new Insets(10));
        humidityBox.setStyle("-fx-alignment: center;");
        Label humidityTitle = new Label("Humidity");
        humidityTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Circle humidityLight = new Circle(14, Color.web("#e74c3c"));
        humidityLabel = new Label(String.format("%.1f%%", ecu.getHumiditySensor().getValue()));
        humidityLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");
        humidityBox.getChildren().addAll(humidityTitle, humidityLight, humidityLabel);

        //Add all indicators to the row
        statusRow.getChildren().addAll(tempBox, pressureBox, humidityBox);


        mainContent.getChildren().add(statusRow);

        //Logging area
        VBox loggingPanel = new VBox();
        loggingPanelReference = loggingPanel;
        loggingPanel.setPadding(new Insets(10));
        loggingPanel.setStyle("-fx-background-color: #bdc3c7;");
        Label logLabel = new Label("System Logs:");
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

    private void updateDashboardValues() {
        if (ecu == null) {
            System.out.println("ECU not initialised — cannot update dashboard values.");
            return;
        }

        temperatureLabel.setText(String.format("%.1f°C", ecu.getTemperatureSensor().getValue()));
        pressureLabel.setText(String.format("%.1f kPa", ecu.getPressureSensor().getValue()));
        humidityLabel.setText(String.format("%.1f%%", ecu.getHumiditySensor().getValue()));
    }

    public static void addLogMessages(String message)
    {

        // Check if panel exists, if not return
        if (loggingPanelReference == null)
        {
            return;
        }

        Label logEntry = new Label(message);
        logEntry.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 12px;");

        javafx.application.Platform.runLater(() -> {
            loggingPanelReference.getChildren().add(logEntry);

            // Add auto scrolling if logs overflow
            if (loggingPanelReference.getChildren().size() > 100) {
                loggingPanelReference.getChildren().remove(1);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

