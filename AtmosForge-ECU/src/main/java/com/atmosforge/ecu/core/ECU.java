package com.atmosforge.ecu.core;

import com.atmosforge.ecu.abstracts.Actuator;
import com.atmosforge.ecu.abstracts.Controller;
import com.atmosforge.ecu.abstracts.Sensor;
import com.atmosforge.ecu.actuators.HumidityActuator;
import com.atmosforge.ecu.actuators.PressureActuator;
import com.atmosforge.ecu.actuators.TemperatureActuator;
import com.atmosforge.ecu.controllers.DashboardControl;
import com.atmosforge.ecu.controllers.HumidityController;
import com.atmosforge.ecu.controllers.PressureController;
import com.atmosforge.ecu.controllers.TemperatureController;
import com.atmosforge.ecu.sensors.HumiditySensor;
import com.atmosforge.ecu.sensors.PressureSensor;
import com.atmosforge.ecu.sensors.TemperatureSensor;


public class ECU {

    private final TemperatureSensor temperatureSensor;
    private final PressureSensor pressureSensor;
    private final HumiditySensor humiditySensor;

    private final TemperatureController temperatureController;
    private final PressureController pressureController;
    private final HumidityController humidityController;

    private final TemperatureActuator temperatureActuator;
    private final PressureActuator pressureActuator;
    private final HumidityActuator humidityActuator;

    private final DashboardControl dashboardControl;

    private Thread ecuLoop;

    private boolean ecuActive;

    public ECU() {
        this.temperatureSensor = new TemperatureSensor("Temperature Sensor 1", 21, 2);
        this.pressureSensor = new PressureSensor("Pressure Sensor 1", 75, 1.5);
        this.humiditySensor = new HumiditySensor("Humidity Sensor 1", 20, 5);

        this.temperatureController = new TemperatureController("Temperature Controller");
        this.pressureController = new PressureController("Pressure Controller");
        this.humidityController = new HumidityController("Humidity Controller");
    
        this.temperatureActuator = new TemperatureActuator("Air Conditioning");
        this.pressureActuator = new PressureActuator("Pressure Actuator");
        this.humidityActuator = new HumidityActuator("Humidity Actuator");

        this.dashboardControl = new DashboardControl();
    }


   public static void main(String[] args) 
   {
        System.out.println("Starting AtmosForge Environment Control Unit...");

        //Later: initialize ECU modules or JavaFX app here
        ECU ecu = new ECU();

        DashboardControl.setEcu(ecu);
        DashboardControl.main(args);

        
    }

    public void activateECU()
    {
        ecuActive = true;
        
        activateSystem(getTemperatureSensor(), getTemperatureController(), getTemperatureActuator());
        activateSystem(getPressureSensor(), getPressureController(), getPressureActuator());
        activateSystem(getHumiditySensor(), geHumidityController(), getHumidityActuator());

        loopECU();
    }

    public boolean isActive() {
        return ecuActive;
    }

    public void deactivateECU()
    {
        ecuActive = false;

        ecuLoop.interrupt();

        deactivateSystem(temperatureSensor, temperatureController, temperatureActuator);
        deactivateSystem(pressureSensor, pressureController, pressureActuator);
        deactivateSystem(humiditySensor, humidityController, humidityActuator);

        
    }

    public void loopECU()
    {
        ecuLoop = new Thread(() -> 
        {
            while (ecuActive)
            {
            runSystem(getTemperatureSensor(), getTemperatureController(), getTemperatureActuator());
            runSystem(getPressureSensor(), getPressureController(), getPressureActuator());
            runSystem(getHumiditySensor(), geHumidityController(), getHumidityActuator());

            
            this.dashboardControl.updateDashboardValues();
            

            try 
            {
                Thread.sleep(3000);
            } 
            catch (InterruptedException e) 
            {
                //Logger
            }
            }
        }, "ECU Loop");
        
        ecuLoop.start();
    }

    public void runSystem(Sensor sensor, Controller controller, Actuator actuator)
    {
        if (!sensor.isSensorActive() || !controller.isControllerActive() || !actuator.isActuatorActive())
        {
            //One of the components is not running so the system will not work, log error.
            return;
        }
        else
        {
            //Running system
            controller.monitor(actuator, sensor);  
        }
    }

    public void activateSystem(Sensor sensor, Controller controller, Actuator actuator)
    {
        try
        {
        sensor.activateSensor();
        controller.activateController();
        actuator.activateActuator();
        }
        catch(Exception e)
        {
            //Logger logic here.
        }
    }

    public void deactivateSystem(Sensor sensor, Controller controller, Actuator actuator)
    {
        try
        {
        sensor.deactivateSensor();
        controller.deactivateController();
        actuator.deactivateActuator();
        }
        catch (Exception e)
        {
            //Logger logic here.
        }
    }

    public TemperatureSensor getTemperatureSensor() {
        return temperatureSensor;
    }

    public PressureSensor getPressureSensor() {
        return pressureSensor;
    }

    public HumiditySensor getHumiditySensor() {
        return humiditySensor;
    }
    
    public TemperatureController getTemperatureController() {
        return temperatureController;
    }

    public PressureController getPressureController()
    {
        return pressureController;
    }

    public HumidityController geHumidityController()
    {
        return humidityController;
    }

    public TemperatureActuator getTemperatureActuator()
    {
        return temperatureActuator;
    }

    public PressureActuator getPressureActuator()
    {
        return pressureActuator;   
    }

    public HumidityActuator getHumidityActuator()
    {
        return humidityActuator;
    }

}
