package com.jichuang.secret.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by KIM on 2015/5/17.
 */
public class TimeUtil {

    public static String getStringDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd-hh:mm:ss");
        return dateFormat.format(date.getTime() + date.getHours()
                + date.getMinutes() + date.getSeconds());
    }

    public static Date getDate() {
        return new Date();
    }

    public static String formatDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd hh:mm:ss");
        return dateFormat.format(date.getTime() + date.getHours()
                + date.getMinutes() + date.getSeconds());
    }

    public static Date formatStringToDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd hh:mm:ss");
        return dateFormat.parse(date);
    }
}
