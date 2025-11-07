package com.atmosforge.ecu.abstracts;

import com.atmosforge.ecu.interfaces.ActuatorInterface;

public class Actuator implements ActuatorInterface
{
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
    }

    @Override
    public void deactivateActuator() 
    {
        //Add logging and dashboard interactions to show Actuator is inactive.
        actuatorOn = false;
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
        else if (currentValue < upperBound && currentValue > target)
        {
            //Within range, fine tune but check change will not go under target.
            if (currentValue - tolerance/10 >= target)
            {
                newValue = currentValue - tolerance/10;
            }
            else
            {
                //Either at target or within 1/10 tolerance of target, turn off actuator for now.
                return;
            }
        }
        else if (currentValue > lowerBound && currentValue < target) 
        {
            //Within range, fine tune but check change will not go over target.
            if (currentValue + tolerance/10 <= target)
            {
                newValue = currentValue + tolerance/10;
            } 
            else
            {
                //Either at target or within 1/10 tolerance of target, do nothing. 
                return;
            }
        }
        else if (currentValue == upperBound)
        {
            //Dashboard should show yellow and warn, then make adjustment.
            newValue = currentValue - tolerance/5;
        }
        else if (currentValue == lowerBound) 
        {
            //Dashboard should show yellow and warn, then make adjustment.
            newValue = currentValue + tolerance/5;
        }
        else if (currentValue > upperBound) 
        {
            //Dashboard should show red and error, then make adjustment.
            newValue = currentValue + tolerance/2;
        }
        else if (currentValue < lowerBound)
        {
            //Dashboard should show red and error, then make adjustment.
            newValue = currentValue + tolerance/2;
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
