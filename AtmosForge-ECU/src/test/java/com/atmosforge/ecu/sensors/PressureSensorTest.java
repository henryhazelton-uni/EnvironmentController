package com.atmosforge.ecu.sensors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PressureSensorTest {
    @Test
    void testPressureWithinRange() {
        PressureSensor pressureSensor = new PressureSensor("sensor", 0, 0);
        pressureSensor.activateSensor();
        pressureSensor.setValue(100);
        assertEquals(100, pressureSensor.getValue(), "Pressure should be 100kPa");
    }

    @Test
    void testInvalidPressureTooHigh() {
        PressureSensor pressureSensor = new PressureSensor("sensor", 100, 5);
        pressureSensor.activateSensor();
        pressureSensor.setValue(200);
        double value = pressureSensor.getValue();
        
        assertEquals(200, value,  "pressureSensor should record the value");
        assertTrue(value > pressureSensor.getHighRange(), "Sensor reading should be higher than allowed range");
    }

    @Test
    void testInvalidPressureTooLow() {
        PressureSensor pressureSensor = new PressureSensor("sensor", 100, 5);
        pressureSensor.activateSensor();
        pressureSensor.setValue(30);
        double value = pressureSensor.getValue();
        
        assertEquals(30, value,  "pressureSensor should record the value");
        assertTrue(value < pressureSensor.getLowRange(), "Sensor reading should be lower than allowed range");
    }

    @Test
    void testOnEdgeHigh() {
        PressureSensor pressureSensor = new PressureSensor("sensor", 100, 10);
        pressureSensor.activateSensor();
        pressureSensor.setValue(110);
        double value = pressureSensor.getValue();

        assertEquals(110, value,  "pressureSensor should record the value");
        assertTrue(value == pressureSensor.getHighRange(), "Sensor reading should be on edge of allowed range");
    }

    @Test
    void testOnEdgeLow() {
        PressureSensor pressureSensor = new PressureSensor("sensor", 100, 10);
        pressureSensor.activateSensor();
        pressureSensor.setValue(90);
        double value = pressureSensor.getValue();

        assertEquals(90, value,  "pressureSensor should record the value");
        assertTrue(value == pressureSensor.getLowRange(), "Sensor reading should be on edge of allowed range");
    }
}