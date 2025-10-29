package com.atmosforge.ecu.interfaces;

public interface SensorInterface
{
    //Interface to provide structure for sensors in the ECU.

    //For controller to get the name of the sensor
    String getName();

    //For controlelr to read the value of sensor's variable
    double getValue();

    //For simulation to set the value of sensor's variable
    void setValue(double value);

    //For controller to get the target value of sensor's variable
    double getTargetValue();

    //For controller to get the target tolerance for sensor's variable
    double getTargetTolerance();

}
