package com.atmosforge.ecu.core;

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
        System.out.println(timestamp() + " [INFO] " + message);
    }

    @Override
    public void logWarning(String message) 
    {
        System.out.println(timestamp() + " [WARNING] " + message);
    }

    @Override
    public void logError(String message) 
    {
        System.out.println(timestamp() + " [ERROR] " + message);
    }

    @Override
    public void logDebug(String message) 
    {
        System.out.println(timestamp() + " [DEBUG] " + message);
    }
}
