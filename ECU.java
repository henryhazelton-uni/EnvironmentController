import Sensors.TemperatureSensor;
import Sensors.PressureSensor;
import Sensors.HumiditySensor;
import Controllers.TemperatureController;
import Controllers.PressureController;
import Controllers.HumidityController;

public class ECU {
    public static void main(String[] args) {
        // Initialise sensors
        TemperatureSensor tempSensor = new TemperatureSensor();
        PressureSensor pressureSensor = new PressureSensor();
        HumiditySensor humiditySensor = new HumiditySensor();

        // Initialise controllers
        TemperatureController tempController = new TemperatureController();
        PressureController pressureController = new PressureController();
        HumidityController humidityController = new HumidityController();

        // Pass to UI
        DashboardControl.setComponents(tempSensor, pressureSensor, humiditySensor,
                                       tempController, pressureController, humidityController);

        DashboardControl.launchUI(args);
    }
}
