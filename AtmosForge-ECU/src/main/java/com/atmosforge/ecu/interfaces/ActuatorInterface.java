package com.atmosforge.ecu.interfaces;

import com.atmosforge.ecu.abstracts.Sensor;

public interface ActuatorInterface
{
    //Interface to provide structure for the actuators in the ECU. 

    //Activate 
    void activateActuator();

    //Deactivate 
    void deactivateActuator();

    //Simulate the change in value from the actuator for a sensor to read.
    void simulateValueChange(Sensor sensor);


}
