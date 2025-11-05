package com.atmosforge.ecu.actuators;

import com.atmosforge.ecu.abstracts.Actuator;
import com.atmosforge.ecu.core.LoggingManager;
import com.atmosforge.ecu.interfaces.LoggerInterface;

public class HumidityActuator extends Actuator
{
    // Initalise logger for humidity actuator
    private static final LoggerInterface logger = LoggingManager.getLogger();

    public HumidityActuator()
    {
        
    }
    
}