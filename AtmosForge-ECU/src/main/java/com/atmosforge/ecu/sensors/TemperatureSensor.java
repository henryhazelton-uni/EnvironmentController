package com.atmosforge.ecu.sensors;

import com.atmosforge.ecu.abstracts.Sensor;

public class TemperatureSensor extends Sensor
{
    //Makes a new temperature sensor.

    public TemperatureSensor(String name, double target, double tolerance)
    {
        this.setName(name);
        this.setTargetValue(target);
        this.setTargetTolerance(tolerance);
        
        //Add line to get initial value from test case.
    }
}