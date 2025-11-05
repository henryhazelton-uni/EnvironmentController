package com.atmosforge.ecu.actuators;

import com.atmosforge.ecu.abstracts.Actuator;
import com.atmosforge.ecu.core.LoggingManager;
import com.atmosforge.ecu.interfaces.LoggerInterface;

public class PressureActuator extends Actuator
{

    // Initalise logger for pressure actuator
    private static final LoggerInterface logger = LoggingManager.getLogger();

    public PressureActuator()
    {
        
    }
    
}