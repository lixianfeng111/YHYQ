package com.yhhl.yhyq.util;

public class TimeUtil {
    public static String removeHMS(String s){
        return s.substring(0,s.indexOf(" "));
    }
}
