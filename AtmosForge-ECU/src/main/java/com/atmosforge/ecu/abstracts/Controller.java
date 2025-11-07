package com.atmosforge.ecu.abstracts;

import com.atmosforge.ecu.core.LoggingManager;
import com.atmosforge.ecu.interfaces.ControllerInterface;
import com.atmosforge.ecu.interfaces.LoggerInterface;

public class Controller implements ControllerInterface
{

    // Initalise a logger for each sensor
    protected static final LoggerInterface logger = LoggingManager.getLogger();

    private String controllerName;
    private boolean controllerOn;
    
    public Controller(String name) 
    {
        this.controllerName = name;
    }

    @Override
    public void activateController()
    {
        controllerOn = true;
    }

    @Override
    public void deactivateController()
    {
        controllerOn = false;
    }

    public boolean isControllerActive()
    {
        return controllerOn;        
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
            logger.logError(value + "is outside of valid range!");
            return false;
        }
        
    }

    @Override
    public void monitor(Actuator actuator, Sensor sensor) 
    {
        //Use actuators to change value towards target value.   
        actuator.simulateValueChange(sensor);
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
