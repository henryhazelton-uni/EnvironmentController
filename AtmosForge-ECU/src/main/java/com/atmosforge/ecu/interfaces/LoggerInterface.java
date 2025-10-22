package com.atmosforge.ecu.interfaces;


/**
 * Logger interface defines a contact for logging system events
 */
public interface LoggerInterface
{
    /**
     * LogInfo will log an informational message
     * @param message The message log
     */
    void logInfo(String message);
    
    /**
     * LogWarning will log a warning message
     * @param message The warning message
     */
    void logWarning(String message);

    /**
     * LogError will log a critical system error message
     * @param message The critical error message
     */
    void logError(String message);

    /**
     * LogDebug will log a debug message
     * @param message The debug message
     */
    void logDebug(String message);
}