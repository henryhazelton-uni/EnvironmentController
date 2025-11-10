package com.atmosforge.ecu.abstracts;

import com.atmosforge.ecu.core.LoggingManager;
import com.atmosforge.ecu.interfaces.ActuatorInterface;
import com.atmosforge.ecu.interfaces.LoggerInterface;


public class Actuator implements ActuatorInterface
{
    // Initalise a logger for each actuator
    protected static final LoggerInterface logger = LoggingManager.getLogger();

    private String actuatorName;
    private boolean actuatorOn;

    public Actuator(String name)
    {
        this.actuatorName = name;
    }

    @Override
    public void activateActuator() 
    {
        //Add logging and dashboard interactions to show Actuator is active.
        actuatorOn = true;
        logger.logInfo(actuatorName + " STATUS: Active");
    }

    @Override
    public void deactivateActuator() 
    {
        //Add logging and dashboard interactions to show Actuator is inactive.
        actuatorOn = false;
        logger.logInfo(actuatorName + " STATUS: Inactive");
    }

    public boolean isActuatorActive()
    {
        return actuatorOn;
    }

    /* 
    In theory, in place of simulateValueChange, actuator would have a way to increase/decrease value using hardware.

    For example, the temperature actuator would be an Air Conditioning (A/C) unit.
    This A/C based on sensor.checkSensor() would decide whether to idle or circulate hot/cold air. 

    This method simulates said value change and sets it on the sensor for testing purposes. 

    The ECU would theoretically not have a sensor.setValue() as this would be directly read from the environment to the sensors.
    */ 


    @Override
    public void simulateValueChange(Sensor sensor) 
    {
        //New value this will set for the sensor
        double newValue = sensor.getValue();
        
        //Current sensor values to make calculations
        double currentValue=sensor.getValue();
        double target = sensor.getTargetValue();
        double tolerance = sensor.getTargetTolerance();
        double lowerBound = sensor.getLowRange();
        double upperBound = sensor.getHighRange();
        
        if(!isActuatorActive())
        {
            logger.logError(actuatorName + " is currently inactive.");
            return;
        }
        if (!sensor.isSensorActive())
        {
            logger.logError(sensor.getName() + " is currently inactive.");
            return;
        }

        if (currentValue == target)
        {
            //Make no change at target, here the actuator would likely have an idle/maintain state.
            return;
        }
        else if (currentValue < upperBound && currentValue > target)
        {
            //Within range above target, fine tune but check change will not go under target.
            if (currentValue - tolerance/10 >= target)
            {
                newValue = currentValue - tolerance/10;
                logger.logWarning("Small adjustment towards " + target + " New value set to: " + newValue);
            }
            else
            {
                //Within 1/10 tolerance of target, final adjustment to target.
                newValue = target;
                return;
            }
        }
        else if (currentValue > lowerBound && currentValue < target) 
        {
            //Within range below target value, fine tune but check change will not go over target.
            if (currentValue + tolerance/10 <= target)
            {
                newValue = currentValue + tolerance/10;
                logger.logWarning("Small adjustment towards " + target + " New value set to: " + newValue);
            } 
            else
            {
                //Within 1/10 tolerance of target, final adjustment to target.
                newValue = target;
                return;
            }
        }
        else if (currentValue == upperBound)
        {
            //Dashboard should show yellow and warn, then make adjustment.
            newValue = currentValue - tolerance/5;
            logger.logWarning(sensor.getName() + " At Upper Limit" + " Adjusting to New Value: " + newValue);
        }
        else if (currentValue == lowerBound) 
        {
            //Dashboard should show yellow and warn, then make adjustment.
            newValue = currentValue + tolerance/5;
            logger.logWarning(sensor.getName() + " At Lower Limit" + " Adjusting to New Value: " + newValue);
        }
        else if (currentValue > upperBound) 
        {
            //Dashboard should show red and error, then make adjustment.
            newValue = currentValue - tolerance/2;
            logger.logError(sensor.getName() + " BREACHED Upper Bound - Attempting to Correct. New Value: " + newValue);
        }
        else if (currentValue < lowerBound)
        {
            //Dashboard should show red and error, then make adjustment.
            newValue = currentValue + tolerance/2;
            logger.logError(sensor.getName() + " BREACHED Lower Bound - Attempting to Correct. New Value: " + newValue);
        }

        //Set new value on sensor.
        sensor.setValue(newValue);
    }
    
    public String getName()
    {
        return actuatorName;
    }

    public void setName(String name)
    {
        this.actuatorName = name;
    }
}
