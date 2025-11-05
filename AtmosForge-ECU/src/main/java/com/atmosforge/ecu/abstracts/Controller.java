package com.atmosforge.ecu.abstracts;

import com.atmosforge.ecu.core.LoggingManager;
import com.atmosforge.ecu.interfaces.ControllerInterface;
import com.atmosforge.ecu.interfaces.LoggerInterface;

public class Controller implements ControllerInterface
{

    // Initalise a logger for each sensor
    protected static final LoggerInterface logger = LoggingManager.getLogger();

    @Override
    public boolean checkWithinRange(Sensor sensor) 
    {
        double targetTolerance = sensor.getTargetTolerance();
        double targetValue = sensor.getTargetValue();
        double value = sensor.getValue();
        
        if (value <= targetValue + targetTolerance || value >= targetValue - targetTolerance)
        {
            return true;
        }
        else
        {
            logger.logError(value + "is outside of valid range!");
            return false;
        }
        
    }

    @Override
    public void alter() 
    {
        //Use actuators to change value towards target value.   
        
        //TODO Add code
    }

    //Logger to inform the system we are in acceptable ranges and what the current value is.
    @Override
    public void informSystem()
    {
        //TODO Add code
    }

    //Logger to alert the system we are not in acceptable ranges and what the current value is.
    @Override
    public void alertSystem()
    {
        //TODO Add code
    }
    
}
