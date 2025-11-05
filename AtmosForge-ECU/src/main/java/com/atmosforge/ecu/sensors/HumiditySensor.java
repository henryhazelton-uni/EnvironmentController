package com.atmosforge.ecu.sensors;

import com.atmosforge.ecu.abstracts.Sensor;
import com.atmosforge.ecu.core.LoggingManager;
import com.atmosforge.ecu.interfaces.LoggerInterface;;

public class HumiditySensor extends Sensor
{

    // Initalise a logger for humidity sensor
    private static final LoggerInterface logger = LoggingManager.getLogger();

    //Makes a new humidity sensor.
    public HumiditySensor(String name, double target, double tolerance) 
    {
        super(name, target, tolerance);
    }
}