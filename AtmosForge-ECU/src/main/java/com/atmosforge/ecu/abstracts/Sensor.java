package com.atmosforge.ecu.abstracts;

import com.atmosforge.ecu.interfaces.SensorInterface;

public class Sensor implements SensorInterface
{
    private String sensorName;
    private int targetValue;
    private int targetTolerance;
    
    public Sensor (String name, int target, int tolerance)
    {
        this.sensorName = name;
        this.targetValue = target;
        this.targetTolerance = tolerance;
    }
    
    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
    
    public int getLowRange()
    {
        new int acceptableLow = this.targetValue - this.targetTolerance;
        return = acceptableLow;
    }

    public int getHighRange()
    {
        new int acceptableHigh = this.targetValue + this.targetTolerance;
        return acceptableHigh;
    }

    public boolen checkWithinRange()
    {
        if(this.getValue() > this.getLowRange() && this.getValue() < this.getHighRange())
        {
            informSystem();
            return true;
        }
        else
        {
            alertSystem();
            return false;
        }
    }

    public void informSystem()
    {
        //TODO Add code
    }

    public void alertSystem()
    {
        //TODO Add code
    }

    //TODO Add getters/setters.
}
