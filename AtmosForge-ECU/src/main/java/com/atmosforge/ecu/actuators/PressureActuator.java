package com.atmosforge.ecu.actuators;

import com.atmosforge.ecu.abstracts.Actuator;
import com.atmosforge.ecu.core.LoggingManager;
import com.atmosforge.ecu.interfaces.LoggerInterface;

public class PressureActuator extends Actuator
{
    public PressureActuator(String name)
    {
        super(name);
    }
}