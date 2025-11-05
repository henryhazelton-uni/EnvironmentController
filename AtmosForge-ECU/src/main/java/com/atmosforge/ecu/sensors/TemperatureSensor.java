package com.atmosforge.ecu.sensors;

import com.atmosforge.ecu.abstracts.Sensor;
import com.atmosforge.ecu.core.LoggingManager;
import com.atmosforge.ecu.interfaces.LoggerInterface;

public class TemperatureSensor extends Sensor
{
    
    // Initalise logger for temperature sensor
    private static final LoggerInterface logger = LoggingManager.getLogger();

    //Makes a new temperature sensor.

    public TemperatureSensor(String name, double target, double tolerance)
    {
        super(name, target, tolerance);        
    }
}