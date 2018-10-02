package com.buzzhive.luqman.definedClases;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateHelper {

    static final String DATE_FORMAT = "dd/MM/yyyy";
    static final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
    public static boolean validateDate(Date date) {
        return date.after(new java.util.Date());
    }
    public static Date getDateFromString(String stringDate) throws ParseException {
        stringDate.replace('-','/');
        java.util.Date parsedDate = format.parse(stringDate);
        return new Date(parsedDate.getTime());
    }
    public static Date getNowSQLDate() {
        java.util.Date nowDate = new java.util.Date();
        return new Date(nowDate.getTime());
    }
}
