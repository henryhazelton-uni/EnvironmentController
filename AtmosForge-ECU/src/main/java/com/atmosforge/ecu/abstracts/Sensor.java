package com.atmosforge.ecu.abstracts;

import com.atmosforge.ecu.core.LoggingManager;
import com.atmosforge.ecu.interfaces.LoggerInterface;
import com.atmosforge.ecu.interfaces.SensorInterface;

public class Sensor implements SensorInterface
{
    //These values will be set when the class is called.
    private String sensorName;
    private double targetValue;
    private double targetTolerance;
    private double value;
    private boolean sensorOn;
    
    // Initalise a logger for each sensor
    protected static final LoggerInterface logger = LoggingManager.getLogger();

    //Constructor needs these values to be set when calling the class, and setting them to the above when it is called.
    public Sensor (String name, double target, double tolerance)
    {
        this.sensorName = name;
        this.targetValue = target;
        this.targetTolerance = tolerance;
    }    
    
    @Override
    public void activateSensor()
    {
        sensorOn=true;
    }
    @Override
    public void deactivateSensor()
    {
        sensorOn=false;
    }

    public boolean isSensorActive()
    {
        return sensorOn;
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

    // Getters and setters.

    @Override
    public double getValue() 
    {
        return value;
    }

    @Override
    public void setValue(double value) 
    {
        this.value = value;
        logger.logInfo(sensorName + " reading set to " + value);
    }

    @Override
    public String getName() 
    {
        return sensorName;
    }

    public void setName(String name) 
    {
        this.sensorName = name;
        logger.logInfo("Name set to:" + name);
    }

    @Override
    public double getTargetValue() 
    {
        return targetValue;
    }

    //For dashboard control.
    public void setTargetValue(double targetValue) 
    {
        this.targetValue = targetValue;
        logger.logInfo(sensorName + " target set to: " + targetValue);
    }

    @Override
    public double getTargetTolerance() 
    {
        return targetTolerance;
    }

    //For dashboard control.
    public void setTargetTolerance(double tolerance) 
    {
        this.targetTolerance = tolerance;
        logger.logInfo(sensorName + " target tolerance set to " + tolerance);
    }
}
