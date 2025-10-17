package com.atmosforge.ecu.interfaces;

public interface SensorInterface
{
    //Interface to provide structure for sensors in the ECU.

    int getValue();
    void setValue(int value);
    boolean checkWithinRange();
    void informSystem();
    void alertSystem();

}
