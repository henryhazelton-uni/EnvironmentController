package com.atmosforge.ecu.sensors;

import com.atmosforge.ecu.abstracts.Sensor;

public class PressureSensor extends Sensor
{
    //Makes a new pressure sensor. 

    //Need to overwrite the abstracts as we are using int for pressure

    private int pressure;
    private int targetValue;
    private int targetTolerance;

    public PressureSensor(String name, int target, int tolerance)
    {
        this.setName(name);
        this.setTargetValue(target);
        this.setTargetTolerance(tolerance);

        //Add line to get initial value for pressure from test case.
    }

    //Calculates the lower boundary for the acceptable range.
    public int getLowRange()
    {
        int acceptableLow = this.targetValue - this.targetTolerance;
        return = acceptableLow;
    }

    //Calculates the higher boundary for the acceptable range.
    public int getHighRange()
    {
        int acceptableHigh = this.targetValue + this.targetTolerance;
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

    //Getter and Setter

  public int getValue()
    {
        return pressure;
    }

    public void setValue(int currentPressure)
    {
        this.pressure = currentPressure;
    }

}
