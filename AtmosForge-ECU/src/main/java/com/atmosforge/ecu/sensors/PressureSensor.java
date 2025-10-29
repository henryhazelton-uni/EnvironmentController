package com.atmosforge.ecu.sensors;

import com.atmosforge.ecu.abstracts.Sensor;

public class PressureSensor extends Sensor
{
    //Makes a new pressure sensor. 

    public PressureSensor(String name, double target, double tolerance)
    {
        super(name, target, tolerance);
    }
}
