package com.atmosforge.ecu.sensors;

import com.atmosforge.ecu.abstracts.Sensor;

public class HumiditySensor extends Sensor
{
    //Makes a new humidity sensor.

    public HumiditySensor(String name, double target, double tolerance)
    {
        this.setName(name);
        this.setTargetValue(target);
        this.setTargetTolerance(tolerance);
        
        //Add line to get initial value from test case.
    }
}