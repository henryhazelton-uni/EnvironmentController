package com.atmosforge.ecu.core;

// Sensors
import com.atmosforge.ecu.sensors.TemperatureSensor;
import com.atmosforge.ecu.sensors.PressureSensor;
import com.atmosforge.ecu.sensors.HumiditySensor;

// Controllers
import com.atmosforge.ecu.controllers.TemperatureController;
import com.atmosforge.ecu.controllers.PressureController;
import com.atmosforge.ecu.controllers.HumidityController;

public class ECU {
    public static void main(String[] args) {
        // Initialise sensors
        TemperatureSensor tempSensor = new TemperatureSensor();
        PressureSensor pressureSensor = new PressureSensor();
        HumiditySensor humiditySensor = new HumiditySensor();

        // Initialise controllers
        TemperatureController tempController = new TemperatureController();
        PressureController pressureController = new PressureController();
        HumidityController humidityController = new HumidityController();

        // Pass to UI
        DashboardControl.setComponents(tempSensor, pressureSensor, humiditySensor,
                                       tempController, pressureController, humidityController);

        DashboardControl.launchUI(args);
    }
}
