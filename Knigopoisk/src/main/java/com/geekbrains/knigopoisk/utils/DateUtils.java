package com.geekbrains.knigopoisk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    public static Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").parse(dateString);
        } catch (Exception e) {
            return GregorianCalendar.getInstance().getTime();
        }
    }
}
