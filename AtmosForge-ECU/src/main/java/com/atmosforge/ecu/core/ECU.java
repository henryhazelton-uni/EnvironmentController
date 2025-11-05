package com.atmosforge.ecu.core;

import com.atmosforge.ecu.actuators.HumidityActuator;
import com.atmosforge.ecu.actuators.PressureActuator;
import com.atmosforge.ecu.actuators.TemperatureActuator;
import com.atmosforge.ecu.controllers.DashboardControl;
import com.atmosforge.ecu.controllers.HumidityController;
import com.atmosforge.ecu.controllers.PressureController;
import com.atmosforge.ecu.controllers.TemperatureController;
import com.atmosforge.ecu.sensors.HumiditySensor;
import com.atmosforge.ecu.sensors.PressureSensor;
import com.atmosforge.ecu.sensors.TemperatureSensor;

public class ECU {

    private final TemperatureSensor temperatureSensor;
    private final PressureSensor pressureSensor;
    private final HumiditySensor humiditySensor;

    private final TemperatureController temperatureController;
    private final PressureController pressureController;
    private final HumidityController humidityController;

    private final TemperatureActuator temperatureActuator;
    private final PressureActuator pressureActuator;
    private final HumidityActuator humidityActuator;

    public ECU() {
        this.temperatureSensor = new TemperatureSensor("Temperature Sensor 1", 21, 2);
        this.pressureSensor = new PressureSensor("Pressure Sensor 1", 75, 1.5);
        this.humiditySensor = new HumiditySensor("Humidity Sensor 1", 20, 5);

        this.temperatureController = new TemperatureController("Temperature Controller");
        this.pressureController = new PressureController("Pressure Controller");
        this.humidityController = new HumidityController("Humidity Controller");
    
        this.temperatureActuator = new TemperatureActuator("Air Conditioning");
        this.pressureActuator = new PressureActuator("Pressure Actuator");
        this.humidityActuator = new HumidityActuator("Humidity Actuator");
    }


    public static void main(String[] args) {
        System.out.println("Starting AtmosForge Environment Control Unit...");
        // Later: initialize your ECU modules or JavaFX app here
        ECU ecu = new ECU();
        DashboardControl.setEcu(ecu);
        DashboardControl.main(args);
    }
 
    public TemperatureSensor getTemperatureSensor() {
        return temperatureSensor;
    }

    public PressureSensor getPressureSensor() {
        return pressureSensor;
    }

    public HumiditySensor getHumiditySensor() {
        return humiditySensor;
    }
    
    public static void main(String[] args) {
        System.out.println("Starting AtmosForge Environment Control Unit...");
        //Later: initialize ECU modules or JavaFX app here
        ECU ecu = new ECU();
        //Set initial test values
        ecu.getTemperatureSensor().setValue(25);
        ecu.getPressureSensor().setValue(101);
        ecu.getHumiditySensor().setValue(60);
        DashboardControl.setEcu(ecu);
        DashboardControl.main(args);
    
    }

}
