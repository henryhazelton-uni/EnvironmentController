package com.atmosforge.ecu.abstracts;

import com.atmosforge.ecu.interfaces.ActuatorInterface;

public class Actuator implements ActuatorInterface
{

    @Override
    public void activateActuator() 
    {
        
    }

    @Override
    public void deactivateActuator() 
    {
        
    }

    @Override
    public void simulateValueChange(Sensor sensor) 
    {
        //Initiate new value
        double newValue = sensor.getValue();

        if (sensor.getValue() <= sensor.getHighRange() && sensor.getValue() > sensor.getTargetValue())
        {
            //If sensor reading is within the acceptable range on the high side, make small additions to the value.

            //Firstly check making a small addition will not take it over target tolerance

            if (sensor.getValue() + sensor.getTargetTolerance()/10 <= sensor.getTargetValue())
            {
                //Making the addition will not take it over target, so do it. 
                newValue = sensor.getValue() + sensor.getTargetTolerance()/10;
            }
            else
            {
                // Sensor value must be within 1/10 tolerance of the target value, so do nothing.
            }
        }

        else if (sensor.getValue() >= sensor.getLowRange() && sensor.getValue() < sensor.getTargetValue())
        {
            //If sensor reading is wihtin acceptable range on the low side, , make small subtractions from the value

            //Firstly check making a small subtraction will not take it below target tolerance

            if (sensor.getValue() - sensor.getTargetTolerance()/10 >= sensor.getTargetValue())
            {
                //Making the subtraction will not take it below target, so do it.
                newValue = sensor.getValue() - sensor.getTargetTolerance()/10;
            }
            else
            {
                // Sensor value must be within 1/10 tolerance of the target value, so do nothing.
            }
        }

        else if (sensor.getValue() < sensor.getLowRange())
        {
            //If sensor reading is below the low acceptable range, make large additions to the value

            newValue = sensor.getValue() + sensor.getTargetTolerance()/2;

        }

        else if (sensor.getValue() > sensor.getHighRange())
        {
            //If sensor reading is above the high acceptable range, make large subtractions from the value

            newValue = sensor.getValue() - sensor.getTargetTolerance()/2;

        }

        else 
        {
            //Sensor will be at target, do nothing for now.
        }
        
        sensor.setValue(newValue);
    }
    
}
