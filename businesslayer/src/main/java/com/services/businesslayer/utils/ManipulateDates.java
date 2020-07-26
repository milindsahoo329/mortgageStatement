package com.services.businesslayer.utils;

import java.util.Calendar;
import java.util.Date;

public class ManipulateDates {

    public static Date addMonths(Date date, int months)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

}
