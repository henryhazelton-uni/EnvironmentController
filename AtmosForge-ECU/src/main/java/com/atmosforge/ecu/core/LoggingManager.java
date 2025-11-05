package com.atmosforge.ecu.core;

import com.atmosforge.ecu.controllers.DashboardControl;
import com.atmosforge.ecu.interfaces.LoggerInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LoggingManager provides a simple implementation 
 * of the LoggerInterface that outputs a message to 
 * system console
 */
public class LoggingManager implements LoggerInterface
{

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String timestamp() 
    {
        return "[" + LocalDateTime.now().format(formatter) + "]";
    }

    @Override
    public void logInfo(String message) 
    {
        DashboardControl.addLogMessages(timestamp() + " [INFO] " + message);
    }

    @Override
    public void logWarning(String message) 
    {
        DashboardControl.addLogMessages(timestamp() + " [WARNING] " + message);
    }

    @Override
    public void logError(String message) 
    {
        DashboardControl.addLogMessages(timestamp() + " [ERROR] " + message);
    }

    @Override
    public void logDebug(String message) 
    {
        DashboardControl.addLogMessages(timestamp() + " [DEBUG] " + message);
    }

    // getter for logging manager
    public static LoggerInterface getLogger() 
    {
        return new LoggingManager();
    }
}
