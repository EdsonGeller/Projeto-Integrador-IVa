package com.example.integradorIVa.TaskMasters.service;

import java.time.Duration;

public class DurationConverter {

    public static Duration convertToDuration(int days, int hours, int minutes) {
        return Duration.ofHours(hours).plusMinutes(minutes);
    }

    public static String convertToIso8601(int days, int hours, int minutes) {
        Duration duration = Duration.ofDays(days).plusHours(hours).plusMinutes(minutes);
        return duration.toString(); 
    }
}