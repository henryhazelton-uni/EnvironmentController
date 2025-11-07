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
        logger.logInfo(controllerName + " activated");
        controllerOn = true;
    }

    @Override
    public void deactivateController()
    {
        logger.logInfo(controllerName + " is inactive");
        controllerOn = false;
    }

    public boolean isControllerActive()
    {
        logger.logInfo(controllerName + " is active");
        return controllerOn;        
    }

    @Override
    public void checkSensor(Sensor sensor) 
    {
        double value = sensor.getValue();
        double target = sensor.getTargetValue();
        double lowerBound = sensor.getLowRange();
        double upperBound = sensor.getHighRange();
        String sensorName = sensor.getName();

        if (value == target)
        {    
            logger.logInfo(sensorName + " is eqaual to  target value: " + target);
        }
        else if (value == lowerBound)
        {
            logger.logWarning(sensorName + " lower bound hit! Value: " + value);
        }
        else if (value == upperBound)
        {
            logger.logWarning(sensorName + " upper bound hit! Value: " + value);
        }
        else if (value > target && value < upperBound)
        {
            logger.logInfo(sensorName + " within range above target. Value: " + value);
        }
        else if(value < target && value > lowerBound)
        {
            logger.logInfo(sensorName + " within range below target. Value: " + value);
        }
        else if (value > upperBound)
        {
            logger.logError(sensorName + " above valid range! Value: " + value);
        }
        else
        {
            logger.logError(sensorName + " below valid range! Value: " + value);
        }
    }
    @Override
    public void monitor(Actuator actuator, Sensor sensor) 
    {
        //Use actuators to change value towards target value.   
        checkSensor(sensor);
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
