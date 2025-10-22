package com.atmosforge.ecu.abstracts;

import com.atmosforge.ecu.interfaces.SensorInterface;

public class Sensor implements SensorInterface
{
    //These values will be set when the class is called.
    private String sensorName;
    private double targetValue;
    private double targetTolerance;
    private double value;
    
    //Constructor needs these values to be set when calling the class, and setting them to the above when it is called.
    public Sensor (String name, double target, double tolerance)
    {
        this.sensorName = name;
        this.targetValue = target;
        this.targetTolerance = tolerance;
    }    
    
    //Calculates the lower boundary for the acceptable range.
    public double getLowRange()
    {
        double acceptableLow = this.targetValue - this.targetTolerance;
        return acceptableLow;
    }

    //Calculates the higher boundary for the acceprable range.
    public double getHighRange()
    {
        double acceptableHigh = this.targetValue + this.targetTolerance;
        return acceptableHigh;
    }

    //Checks the value is in the acceptable range, informs/alerts accordingly and sets true/false.
    @Override
    public boolean checkWithinRange()
    {
        if(this.getValue() >= this.getLowRange() && this.getValue() <= this.getHighRange())
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

    //Logger to inform the system we are in acceptable ranges and what the current value is.
    @Override
    public void informSystem()
    {
        //TODO Add code
    }

    //Logger to alert the system we are not in acceptable ranges and what the current value is.
    @Override
    public void alertSystem()
    {
        //TODO Add code
    }

    // Getters and setters.

  public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public String getName()
    {
        return sensorName;
    }

    public void setName(String name)
    {
        this.sensorName = name;
    }

    public double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }

    public double getTargetTolerance() {
        return targetTolerance;
    }

    public void setTargetTolerance(double tolerance) {
        this.targetTolerance = tolerance;
    }
}
