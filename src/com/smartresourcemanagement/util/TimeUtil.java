package com.smartresourcemanagement.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Returns current timestamp as string
    public static String getCurrentTimestamp() {
        return LocalDateTime.now().format(formatter);
    }

    // Format custom LocalDateTime
    public static String format(LocalDateTime time) {
        return time.format(formatter);
    }
}
