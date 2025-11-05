package com.atmosforge.ecu.sensors;

import com.atmosforge.ecu.abstracts.Sensor;
import com.atmosforge.ecu.core.LoggingManager;
import com.atmosforge.ecu.interfaces.LoggerInterface;

public class PressureSensor extends Sensor
{
    // Initalise logger for pressure sensor
    private static final LoggerInterface logger = LoggingManager.getLogger();

    //Makes a new pressure sensor. 

    public PressureSensor(String name, double target, double tolerance)
    {
        super(name, target, tolerance);
    }
}
