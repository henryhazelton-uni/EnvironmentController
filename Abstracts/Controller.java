package Abstracts;

import Interfaces.ControllerInterface;
import Interfaces.SensorInterface;

public class Controller implements ControllerInterface
{
    //General controller functionality
    
    protected String controllerName;
    protected SensorInterface sensorInterface;

    public Controller(String name, SensorInterface sensor)
    {
        this.controllerName = name;
        this.sensorInterface = sensor;
    }

    //TODO Fix this method
    public void checkSensor()
    {
        if (sensorInterface.checkWithinRange() == true)
        {
            
        }
        else
        {

        }
    }

    //TODO Add functionality
    public void alter()
    {

    }

}
