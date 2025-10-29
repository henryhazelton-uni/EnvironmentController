package com.atmosforge.ecu.abstracts;

import com.atmosforge.ecu.interfaces.ControllerInterface;

public class Controller implements ControllerInterface{

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
