package com.atmosforge.ecu.interfaces;

import com.atmosforge.ecu.abstracts.Sensor;

public interface ControllerInterface 
{
    //Interface to provide structure for controllers in the ECU.
    //Check the value of the sensor's variable is within the acceptable range of the target value
    boolean checkWithinRange(Sensor sensor);
    
    //Alter the value using actuator
    void alter();

    //Log the current value of sensor variable when in an acceptable range.
    void informSystem();

    //Alert that the current value of sensor variable is not in the acceptable range and change is needed.
    void alertSystem();
}