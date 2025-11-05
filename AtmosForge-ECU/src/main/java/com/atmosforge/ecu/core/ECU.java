package com.atmosforge.ecu.core;

import com.atmosforge.ecu.controllers.DashboardControl;
import com.atmosforge.ecu.sensors.HumiditySensor;
import com.atmosforge.ecu.sensors.PressureSensor;
import com.atmosforge.ecu.sensors.TemperatureSensor;

public class ECU {

    private final TemperatureSensor temperatureSensor;
    private final PressureSensor pressureSensor;
    private final HumiditySensor humiditySensor;
 
    public ECU() {
        this.temperatureSensor = new TemperatureSensor("Temp Sensor 1", 21, 2);
        this.pressureSensor = new PressureSensor("Pressure Sensor 1", 75, 1.5);
        this.humiditySensor = new HumiditySensor("Humidity Sensor", 20, 5);
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
