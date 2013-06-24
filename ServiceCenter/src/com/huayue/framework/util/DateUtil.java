package com.huayue.framework.util;

//java日期处理bean 

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class DateUtil {

    // 格式：年－月－日 小时：分钟：秒
    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

    // 格式：年－月－日 小时：分钟
    public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";

    // 格式：年月日 小时分钟秒
    public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";

    // 格式：年－月－日
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";

    // 格式：月－日
    public static final String SHORT_DATE_FORMAT = "MM-dd";

    // 格式：小时：分钟：秒
    public static final String LONG_TIME_FORMAT = "HH:mm:ss";

    //格式：年-月
    public static final String MONTG_DATE_FORMAT = "yyyy-MM";

    // 年的加减
    public static final int SUB_YEAR = Calendar.YEAR;

    // 月加减
    public static final int SUB_MONTH = Calendar.MONTH;

    // 天的加减
    public static final int SUB_DAY = Calendar.DATE;

    // 小时的加减
    public static final int SUB_HOUR = Calendar.HOUR;

    // 分钟的加减
    public static final int SUB_MINUTE = Calendar.MINUTE;

    // 秒的加减
    public static final int SUB_SECOND = Calendar.SECOND;

    public static final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四",
            "星期五", "星期六" };
    
    public static final String[] DAYS = {"日","一","二","三","四","五","六"};
    
    @SuppressWarnings("unused")
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public DateUtil() {
    }

    /**
     * 把符合日期格式的字符串转换为日期类型
     */
    public static java.util.Date stringtoDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            // log.error(e);
            d = null;
        }
        return d;
    }

    /**
     * 把符合日期格式的字符串转换为日期类型
     */
    public static java.util.Date stringtoDate(String dateStr, String format,
            ParsePosition pos) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr, pos);
        } catch (Exception e) {
            d = null;
        }
        return d;
    }

    /**
     * 把日期转换为字符串
     */
    public static String dateToString(java.util.Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 获取当前时间的指定格式
     */
    public static String getCurrDate(String format) {
        return dateToString(new Date(), format);
    }

    public static String dateSub(int dateKind, String dateStr, int amount) {
        Date date = stringtoDate(dateStr, FORMAT_ONE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateKind, amount);
        return dateToString(calendar.getTime(), FORMAT_ONE);
    }

    /**
     * 两个日期相减
     * @return 相减得到的秒数
     */
    public static long timeSub(String firstTime, String secTime) {
        long first = stringtoDate(firstTime, FORMAT_ONE).getTime();
        long second = stringtoDate(secTime, FORMAT_ONE).getTime();
        return (second - first) / 1000;
    }

    /**
     * 获得某月的天数
     */
    public static int getDaysOfMonth(String year, String month) {
        int days = 0;
        if (month.equals("1") || month.equals("3") || month.equals("5")
                || month.equals("7") || month.equals("8") || month.equals("10")
                || month.equals("12")) {
            days = 31;
        } else if (month.equals("4") || month.equals("6") || month.equals("9")
                || month.equals("11")) {
            days = 30;
        } else {
            if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0)
                    || Integer.parseInt(year) % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        }

        return days;
    }

    /**
     * 获取某年某月的天数
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得当前日期
     */
    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获得当前月份
     */
    public static int getToMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得当前年份
     */
    public static int getToYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的天
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的年
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的月份，1-12
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
     */
    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }

    /**
     * 比较两个日期的年差
     */
    public static int yearDiff(String before, String after) {
        Date beforeDay = stringtoDate(before, LONG_DATE_FORMAT);
        Date afterDay = stringtoDate(after, LONG_DATE_FORMAT);
        return getYear(afterDay) - getYear(beforeDay);
    }

    /**
     * 比较指定日期与当前日期的差
     */
    public static int yearDiffCurr(String after) {
        Date beforeDay = new Date();
        Date afterDay = stringtoDate(after, LONG_DATE_FORMAT);
        return getYear(beforeDay) - getYear(afterDay);
    }
    
    /**
     * 比较指定日期与当前日期的差
     */
    public static long dayDiffCurr(String before) {
    	String datestr = "";
        Date currDate = DateUtil.stringtoDate(datestr, LONG_DATE_FORMAT);
        Date beforeDate = stringtoDate(before, LONG_DATE_FORMAT);
        return (currDate.getTime() - beforeDate.getTime()) / 86400000;

    }

    /**
     * 获取每月的第一周
     */
    public static int getFirstWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
        c.set(year, month - 1, 1);
        return c.get(Calendar.DAY_OF_WEEK);
    }
    /**
     * 获取每月的最后一周
     */
    public static int getLastWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
        c.set(year, month - 1, getDaysOfMonth(year, month));
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得当前日期字符串，格式"yyyy_MM_dd_HH_mm_ss"
     * 
     * @return
     */
    public static String getCurrent() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        StringBuffer sb = new StringBuffer();
        /*
        sb.append(year).append("_").append(StringUtil.addzero(month, 2))
                .append("_").append(StringUtil.addzero(day, 2)).append("_")
                .append(StringUtil.addzero(hour, 2)).append("_").append(
                        StringUtil.addzero(minute, 2)).append("_").append(
                        StringUtil.addzero(second, 2));
       */
        return sb.toString();
    }

    /**
     * 获得当前日期字符串，格式"yyyy-MM-dd HH:mm:ss"
     * 
     * @return
     */
    public static String getNow() {
        Calendar today = Calendar.getInstance();
        return dateToString(today.getTime(), FORMAT_ONE);
    }

    /**
     * 判断日期是否有效,包括闰年的情况
     * 
     * @param date
     *          YYYY-mm-dd
     * @return
     */
    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer(
                "^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }
    
    /*
     *	获取当月的第一天 
     */
    public static String getMonthFirstDay(){

    	   Calendar cal = Calendar.getInstance();

    	   Calendar f = (Calendar)cal.clone();

    	   f.clear();

    	f.set(Calendar.YEAR,cal.get(Calendar.YEAR));

    	f.set(Calendar.MONTH,cal.get(Calendar.MONTH));

    	String firstday = new SimpleDateFormat("yyyy-MM-dd").format(f.getTime());

    	return firstday;

    	}
    
    /*
     * 获取当月的最后一天
     */
    public static String getMonthLastDay(){

    	   Calendar cal = Calendar.getInstance();

    	   Calendar l = (Calendar)cal.clone();

    	   l.clear();

    	   l.set(Calendar.YEAR,cal.get(Calendar.YEAR));

    	   l.set(Calendar.MONTH,cal.get(Calendar.MONTH)+1);

    	   l.set(Calendar.MILLISECOND,-1);

    	   String lastday = new SimpleDateFormat("yyyy-MM-dd").format(l.getTime());

    	    return lastday;

    	}
    
    /**
     * 获取月份对应的日期-星期映射，如: 01-一
     * 
     * @param month  dataformat: yyyy-MM
     * @return
     */
    public static Map<Integer,String> getMonthMapping(String month){
    	Date date = stringtoDate(month, MONTG_DATE_FORMAT);
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int dayCount = getDaysOfMonth(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+ 1);
    	Map<Integer,String> map = new LinkedHashMap<Integer,String>(dayCount);
    	for(int i = 1; i <= dayCount ;i++ ){
    		cal.set(Calendar.DAY_OF_MONTH, i);
    		map.put(i, getWeekMapping(cal.get(Calendar.DAY_OF_WEEK)));
    	}
    	return map;
    }
    
    public static String getWeekMapping(int dayForWeek){
    	switch(dayForWeek){
    	case 1:
    		return "日";
    	case 2:
    		return "一";
    	case 3:
    		return "二";
    	case 4:
    		return "三";
    	case 5:
    		return "四";
    	case 6:
    		return "五";
    	case 7:
    		return "六";
    	default:
    		return "";
    	}
    }
    
    /*
     *	获取某年某月的第一天 
     */
    public static String getMonthFirstDay(String monthDate){

    	Calendar cal = Calendar.getInstance();

    	Calendar f = (Calendar)cal.clone();

    	f.clear();
    	   
    	Date date = stringtoDate(monthDate, MONTG_DATE_FORMAT);
    	
    	f.setTime(date);
    	
    	//f.set(Calendar.YEAR,cal.get(Calendar.YEAR));

    	//f.set(Calendar.MONTH,cal.get(Calendar.MONTH));

    	String firstday = new SimpleDateFormat("yyyy-MM-dd").format(f.getTime());

    	return firstday;

    	}
    /*
     * 获取某年某月的最后一天
     */
    public static String getMonthLastDay(String monthDate){

    	   Calendar cal = Calendar.getInstance();

    	   Calendar l = (Calendar)cal.clone();

    	   l.clear();
    	   
    	   Date date = stringtoDate(monthDate ,MONTG_DATE_FORMAT);
    	   
    	   l.setTime(date);
    	   
    	   l.set(Calendar.YEAR,l.get(Calendar.YEAR));

    	   l.set(Calendar.MONTH,l.get(Calendar.MONTH)+1);

    	   l.set(Calendar.MILLISECOND,-1);

    	   String lastday = new SimpleDateFormat("yyyy-MM-dd").format(l.getTime());

    	    return lastday;

    }
    
    public static void main(String[] args){
    	List a = new ArrayList(30);
    	a.set(0, 1);
    }
    
}   
   
