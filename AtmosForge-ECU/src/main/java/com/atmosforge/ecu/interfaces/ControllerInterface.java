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

    //Check the value of the sensor's variable and log it appropriately
    void checkSensor(Sensor sensor);
    
    //Monitor the value and make necessary changes.
    void monitor(Actuator actuator, Sensor sensor);

}