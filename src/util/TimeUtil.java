package src.util;

// File: util/TimeUtil.java
// Description: Utility class for date-time operations using Java Time API

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    // Define a common date-time format for all system logs and reports
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Get current timestamp as formatted string
    public static String getCurrentTimestamp() {
        return LocalDateTime.now().format(FORMATTER);
    }

    // Convert LocalDateTime to formatted string
    public static String formatDateTime(LocalDateTime time) {
        return time.format(FORMATTER);
    }

    // Parse string back into LocalDateTime
    public static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }

    // Calculate duration between two times in minutes
    public static long getDurationMinutes(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toMinutes();
    }

    // Check if a given time is in the past
    public static boolean isPast(LocalDateTime time) {
        return time.isBefore(LocalDateTime.now());
    }

    // Add minutes to a given time
    public static LocalDateTime addMinutes(LocalDateTime base, long minutes) {
        return base.plusMinutes(minutes);
    }

    // Display a friendly time difference (e.g., "2 hours 15 minutes ago")
    public static String getTimeDifference(LocalDateTime pastTime) {
        Duration diff = Duration.between(pastTime, LocalDateTime.now());
        long hours = diff.toHours();
        long minutes = diff.toMinutes() % 60;

        if (hours > 0) {
            return hours + " hour(s) " + minutes + " minute(s) ago";
        } else if (minutes > 0) {
            return minutes + " minute(s) ago";
        } else {
            return "just now";
        }
    }
}

