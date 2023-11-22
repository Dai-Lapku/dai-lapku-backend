package ua.edu.ukma.dailapku.dailapkubackend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentTime() {
        return dateTimeFormatter().format(LocalDateTime.now());
    }
}
