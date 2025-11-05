package com.atmosforge.ecu.actuators;

import com.atmosforge.ecu.abstracts.Actuator;
import com.atmosforge.ecu.core.LoggingManager;
import com.atmosforge.ecu.interfaces.LoggerInterface;

public class TemperatureActuator extends Actuator
{
    public TemperatureActuator(String name)
    {
        super(name);
    }
}
