package com.atmosforge.ecu.interfaces;

import com.atmosforge.ecu.abstracts.Actuator;
import com.atmosforge.ecu.abstracts.Sensor;

public interface ControllerInterface 
{
    //Interface to provide structure for controllers in the ECU.

    //Set controller as active
    void activateController();

    //Set controller as inactive
    void deactivateController();

    //Check the value of the sensor's variable is within the acceptable range of the target value
    boolean checkWithinRange(Sensor sensor);
    
    //Monitor the value and make necessary changes.
    void monitor(Actuator actuator, Sensor sensor);

    //Log the current value of sensor variable when in an acceptable range.
    void informSystem();

    //Alert that the current value of sensor variable is not in the acceptable range and change is needed.
    void alertSystem();
}