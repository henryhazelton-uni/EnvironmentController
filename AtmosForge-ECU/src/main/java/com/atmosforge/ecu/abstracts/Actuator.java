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
        
        if(!actuatorOn)
        {
            //If actuator isn't on, there should be no way to make changes.
            return;
        }
        else if (currentValue == target)
        {
            // Log that we are at target and do nothing
            logger.logInfo(sensor + " is at selected target");

            return;
        }
        else if (currentValue < upperBound && currentValue > target)
        {
            //Within range, fine tune but check change will not go under target.
            if (currentValue - tolerance/10 >= target)
            {
                //TODO: Log small adjustment
                
                newValue = currentValue - tolerance/10;
                logger.logWarning("Approaching lower limit of " + target);
            }
            else
            {
                //Within 1/10 tolerance of target, turn off actuator for now.
                logger.logInfo(actuatorName + "STATUS: Off");
                return;
            }
        }
        else if (currentValue > lowerBound && currentValue < target) 
        {
            //Within range, fine tune but check change will not go over target.
            if (currentValue + tolerance/10 <= target)
            {
                //TODO: Log small adjustment

                newValue = currentValue + tolerance/10;
                logger.logInfo(actuatorName + "STATUS: On");
            } 
            else
            {
                //Within 1/10 tolerance of target, do nothing. 
                return;
            }
        }
        else if (currentValue == upperBound)
        {
            //Dashboard should show yellow and warn, then make adjustment.
            newValue = currentValue - tolerance/5;
            logger.logWarning(sensor + " Approaching Upper Limit");
        }
        else if (currentValue == lowerBound) 
        {
            //Dashboard should show yellow and warn, then make adjustment.
            newValue = currentValue + tolerance/5;
            logger.logWarning(sensor + " Approaching Lower Limit");
        }
        else if (currentValue > upperBound) 
        {
            //Dashboard should show red and error, then make adjustment.
            newValue = currentValue - tolerance/2;
            logger.logError(sensor + " BREACHED Upper Bound - ACT IMMEDIATLEY");
        }
        else if (currentValue < lowerBound)
        {
            //Dashboard should show red and error, then make adjustment.
            newValue = currentValue + tolerance/2;
            logger.logError(sensor + " BREACHED Lower Bound - ACT IMMEDIATLEY");
        }

        //Set new value on sensor.
        sensor.setValue(newValue);
        logger.logInfo(sensor + " set value to " + newValue);
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
