package src.util;

// File: util/LoggerUtil.java
// Description: Logging utility for system-wide messages, warnings, and errors

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
// import java.time.LocalDateTime;

public class LoggerUtil {

    private static final String LOG_FILE = "logs/system_log.txt";

    // Log info message
    public static void info(String message) {
        log("INFO", message);
    }

    // Log warning message
    public static void warn(String message) {
        log("WARN", message);
    }

    // Log error message
    public static void error(String message, Exception e) {
        log("ERROR", message + " | Exception: " + e.getMessage());
    }

    // Core logging method
    private static void log(String level, String message) {
        String timestamp = TimeUtil.getCurrentTimestamp();
        String logEntry = "[" + timestamp + "] [" + level + "] " + message;

        // Print to console
        System.out.println(logEntry);

        // Write to log file
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(logEntry);
        } catch (IOException e) {
            System.out.println("⚠️ Failed to write to log file: " + e.getMessage());
        }
    }
}

