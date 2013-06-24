package com.huayue.framework.util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateUtils
{
    public static final long MILLISECONDS_OF_DAY = 86400000L;
    public static final long MILLISECONDS_OF_HOUR = 3600000L;

    public static final String FIRST_DAY = "1970-01-01 00:00:00";

    public static Date getToday()
    {
        Date date = new Date();
        return new Date(date.getYear(), date.getMonth(), date.getDate());
    }

    public static Date getTomorrow()
    {
        return getTomorrowOfDay(new Date());
    }

    public static Date getTomorrowOfDay(Date date)
    {
        return new Date(date.getYear(), date.getMonth(), date.getDate() + 1);
    }

    public static Date getYesterday()
    {
        return getYesterdayOfDay(new Date());
    }

    public static Date getYesterdayOfDay(Date date)
    {
        return new Date(date.getYear(), date.getMonth(), date.getDate() - 1);
    }

    public static Date getDate(String date) throws Exception
    {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public static Date getDate(String date, String format) throws Exception
    {
        return new SimpleDateFormat(format).parse(date);
    }

    public static String formatDateTo(Date date, String format)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static String formatDateTo(long time, String format)
    {
        return formatDateTo(new Date(time), format);
    }

    public static Date dateAdd(Date date, int days)
    {
        return new Date(date.getTime() + days * MILLISECONDS_OF_DAY);
    }
    
    public static long getTwoMonthAgo()
    {
    	long time = new Date().getTime() - 2*30*24*60*60*1000;
    	return time;
    }
}
