package com.atmosforge.ecu.abstracts;

import com.atmosforge.ecu.interfaces.ControllerInterface;

public class Controller implements ControllerInterface{

    private String controllerName;

    public Controller(String name) 
    {
        this.controllerName = name;
    }

    @Override
    public boolean checkWithinRange(Sensor sensor) 
    {
        double value = sensor.getValue();
        
        if (value <= sensor.getHighRange() || value >= sensor.getLowRange())
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }

    @Override
    public void alter(Actuator actuator, Sensor sensor) 
    {
        //Use actuators to change value towards target value.   
        actuator.simulateValueChange(sensor);

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
    

    public String getName()
    {
        return controllerName;
    }

    public void setName(String name)
    {
        this.controllerName = name;
    }

}
