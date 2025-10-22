package com.atmosforge.ecu.interfaces;

public interface SensorInterface
{
    //Interface to provide structure for sensors in the ECU.

    //Check the value of the sensor's variable is within the acceptable range of the target value
    boolean checkWithinRange();

    //Log the current value of sensor variable when in an acceptable range.
    void informSystem();

    //Alert that the current value of sensor variable is not in the acceptable range and change is needed.
    void alertSystem();

}
