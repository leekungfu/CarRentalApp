package com.vn.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static String genDate(String date) {
        if (date.equals(""))
            return LocalDate.now().toString();
        return date;
    }

    public static String genTime(String time) {
        if (time.equals("")) {
            LocalTime localTime = LocalTime.now();
            String t = (localTime.getMinute() < 10) ? ":0" : ":";
            String s = localTime.getHour() + t + localTime.getMinute();
            System.out.println(s);
            return s;
        }
        return time;
    }
    public static LocalDate genD(String date){
        if(date.equals(""))
            return LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return  LocalDate.parse(date,formatter);
    }
    public static LocalDateTime getDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(date, formatter);
    }
}
