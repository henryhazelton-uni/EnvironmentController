package com.atmosforge.ecu.sensors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class humiditySensorTest {
    @Test
    void testPressureWithinRange() {
        HumiditySensor humiditySensor = new HumiditySensor("sensor", 30, 0);
        humiditySensor.activateSensor();
        humiditySensor.setValue(30);
        assertEquals(30, humiditySensor.getValue(), "Humidity should be 30");
    }

    @Test
    void testInvalidPressureTooHigh() {
        HumiditySensor humiditySensor = new HumiditySensor("sensor", 30, 5);
        humiditySensor.activateSensor();
        humiditySensor.setValue(50);
        double value = humiditySensor.getValue();
        
        assertEquals(50, value,  "humiditySensor should record the value");
        assertTrue(value > humiditySensor.getHighRange(), "Sensor reading should be higher than allowed range");
    }

    @Test
    void testInvalidPressureTooLow() {
        HumiditySensor humiditySensor = new HumiditySensor("sensor", 30, 5);
        humiditySensor.activateSensor();
        humiditySensor.setValue(20);
        double value = humiditySensor.getValue();
        
        assertEquals(20, value,  "humiditySensor should record the value");
        assertTrue(value < humiditySensor.getLowRange(), "Sensor reading should be lower than allowed range");
    }

    @Test
    void testOnEdgeHigh() {
        HumiditySensor humiditySensor = new HumiditySensor("sensor", 30, 10);
        humiditySensor.activateSensor();
        humiditySensor.setValue(40);
        double value = humiditySensor.getValue();

        assertEquals(40, value,  "humiditySensor should record the value");
        assertTrue(value == humiditySensor.getHighRange(), "Sensor reading should be on edge of allowed range");
    }

    @Test
    void testOnEdgeLow() {
        HumiditySensor humiditySensor = new HumiditySensor("sensor", 40, 10);
        humiditySensor.activateSensor();
        humiditySensor.setValue(30);
        double value = humiditySensor.getValue();

        assertEquals(30, value,  "humiditySensor should record the value");
        assertTrue(value == humiditySensor.getLowRange(), "Sensor reading should be on edge of allowed range");
    }
}