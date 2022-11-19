package com.vn.utils;

import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GenDateTime {
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
}
