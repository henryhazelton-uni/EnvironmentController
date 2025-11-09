package com.atmosforge.ecu.sensors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureSensorTest {
    @Test
    void testTemperatureWithinRange() {
        TemperatureSensor tempSensor = new TemperatureSensor("sensor", 0, 0);
        tempSensor.activateSensor();
        tempSensor.setValue(25);
        assertEquals(25, tempSensor.getValue(), "Temperature should be 25C");
    }

    @Test
    void testInvalidTemperatureTooHigh() {
        TemperatureSensor tempSensor = new TemperatureSensor("sensor", 25, 5);
        tempSensor.activateSensor();
        tempSensor.setValue(40);
        double value = tempSensor.getValue();
        
        assertEquals(40, value, "tempSensor should record the value");
        assertTrue(value >=tempSensor.getHighRange(), "Sensor reading should be higher than allowed range");
    }
}