package com.smartresourcemanagement.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LoggerUtil {

    private static final String LOG_FILE = "system.log";

    // Log to console + file
    public static void log(String message) {
        String timestampedMessage = "[" + TimeUtil.getCurrentTimestamp() + "] " + message;
        
        // Print to console
        System.out.println(timestampedMessage);

        // Save to log file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(timestampedMessage);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to write log: " + e.getMessage());
        }
    }
}
