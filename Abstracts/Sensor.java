package Abstracts;

import Interfaces.SensorInterface;

public class Sensor implements SensorInterface 
{
    //General sensor functionality.


    protected String sensorName;
    protected int value;
    protected int acceptableLowValue;
    protected int acceptableHighValue;



    public Sensor(String name, int currentValue, int acceptableMinValue, int acceptableMaxValue)
    {
        this.sensorName = name;
        this.value = currentValue;
        this.acceptableLowValue = acceptableMinValue;
        this.acceptableHighValue = acceptableMaxValue;
    }

    @Override
    public int getValue()
    {
        return value;
    }
    
    @Override
    public void setValue(int value)
    {
        this.value = value;
    }

    @Override
    public boolean checkWithinRange()
    {
        if (value >= acceptableLowValue && value <= acceptableHighValue)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void informSystem()
    {
        //Inform system
    }

    @Override
    public void alertSystem()
    {
        //Alert system
    }

}


