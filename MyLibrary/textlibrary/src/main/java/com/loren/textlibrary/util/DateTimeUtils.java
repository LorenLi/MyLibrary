package com.loren.textlibrary.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.util.Log;

/**
 * 
 * @author: Loren
 * @create: 2016-7-4下午7:43:05
 * @desc: 日期时间工具类
 *
 */
@SuppressLint({ "SimpleDateFormat", "UseValueOf", "DefaultLocale" })
public class DateTimeUtils {
	private final static String TAG = DateTimeUtils.class.getSimpleName();
	private final static String YYYYMMDD = "yyyy/MM/dd";
	@SuppressLint("SimpleDateFormat")
	private final static SimpleDateFormat sf = new SimpleDateFormat(YYYYMMDD);
	
    /** 返回当前时间的Date */
    public static Date nowDate() {
        return new Date();
    }

    /**
     * 字符串转为字符串符合标准格式"YYYY-MM-DD HH:MM:SS"
     * 
     * @param dateTime
     *            标准时间格式 "YYYY-MM-DD HH:MM:SS"
     * @return java.util.Date
     */
    public static Date toDate(String dateTime) {
        int index = dateTime.indexOf(" ");
        String date = dateTime.substring(0, index);
        String time = dateTime.substring(index + 1);

        return toDate(date, time);
    }

    /**
     * 字符串转为字符串符合标准格式"YYYY-MM-DD HH:MM:SS"
     * 
     * @param date
     *            标准日期格式 "YYYY-MM-DD"
     * @param time
     *            标准时间格式 "HH:MM:SS"
     * @return java.util.Date
     */
    public static Date toDate(String date, String time) {
        if (date == null || time == null) {
            return null;
        }

        int dateSlash1 = date.indexOf("-");
        int dateSlash2 = date.lastIndexOf("-");

        if (dateSlash1 <= 0 || dateSlash1 == dateSlash2) {
            return null;
        }

        int timeColon1 = time.indexOf(":");
        int timeColon2 = time.lastIndexOf(":");

        if (timeColon1 <= 0 || timeColon1 == timeColon2) {
            return null;
        }

        String year = date.substring(0, dateSlash1);
        String month = date.substring(dateSlash1 + 1, dateSlash2);
        String day = date.substring(dateSlash2 + 1);

        String hour = time.substring(0, timeColon1);
        String minute = time.substring(timeColon1 + 1, timeColon2);
        String second = time.substring(timeColon2 + 1);

        return toDate(year, month, day, hour, minute, second);
    }

    /**
     * 通过标准时间字符串输入生成java.util.Date
     * 
     * @param yearStr
     * @param monthStr
     * @param dayStr
     * @param hourStr
     * @param minuteStr
     * @param secondStr
     * @return java.util.Date
     */
    public static Date toDate(String yearStr, String monthStr,
            String dayStr, String hourStr, String minuteStr, String secondStr) {
        int year, month, day, hour, minute, second;

        try {
            year = Integer.parseInt(yearStr);
            month = Integer.parseInt(monthStr);
            day = Integer.parseInt(dayStr);
            hour = Integer.parseInt(hourStr);
            minute = Integer.parseInt(minuteStr);
            second = Integer.parseInt(secondStr);
        } catch (Exception e) {
            return null;
        }
        return toDate(year, month, day, hour, minute, second);
    }

    /**
     * 通过标准时间int 输入生成java.util.Date
     * 
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return java.util.Date
     */
    public static Date toDate(int year, int month, int day, int hour,
            int minute, int second) {
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.set(year, month - 1, day, hour, minute, second);
        } catch (Exception e) {
            return null;
        }
        return calendar.getTime();
    }

    /**
     * 生成标准格式的字符串 格式 "MM-DD-YYYY HH:MM:SS"
     * 
     * @param date
     *            The Date
     * @return 生成默认格式的字符串 格式 "MM-DD-YYYY HH:MM:SS"
     */
    public static String toDateTimeString(Date date) {
        if (date == null) {
            return "";
        }

        String dateString = toDateString(date);
        String timeString = toTimeString(date);

        if (dateString == null || timeString == null) {
            return "";
        }

        return dateString + " " + timeString;
    }

    /**
     * 生成标准日期,格式YYYY+spe+MM+spe+DD
     * 
     * @param date
     *            The Date
     * @return 生成日期,格式YYYY+spe+MM+spe+DD
     */
    public static String toDateString(Date date, String spe) {
        if (date == null) {
            return "";
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        String monthStr = "" + month;
        String dayStr = "" + day;
        String yearStr = "" + year;

        if (month < 10) {
            monthStr = "0" + month;
        }

        if (day < 10) {
            dayStr = "0" + day;
        }

        return yearStr + spe + monthStr + spe + dayStr;
    }

    /**
     * 生成标准日期,格式?YYYY-MM-DD
     * 
     * @param date
     *            The Date
     * @return 生成日期,格式YYYY-MM-DD
     */
    public static String toDateString(Date date) {
        return toDateString(date, "-");
    }

    /**
     * 根据输入的时间生成时间格式 HH:MM:SS
     * 
     * @param date
     *            java.util.Date
     * @return 生成时间格式HH:MM:SS
     */
    public static String toTimeString(Date date) {
        if (date == null) {
            return "";
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        return toTimeString(calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
    }

    /**
     * 根据输入的时,生成时间格式 HH:MM:SS
     * 
     * @param hour
     * @param minute
     * @param second
     * @return 生成时间格式HH:MM:SS
     */
    public static String toTimeString(int hour, int minute, int second) {
        String hourStr = "" + hour;
        String minuteStr = "" + minute;
        String secondStr = "" + second;

        if (hour < 10) {
            hourStr = "0" + hour;
        }

        if (minute < 10) {
            minuteStr = "0" + minute;
        }

        if (second < 10) {
            secondStr = "0" + second;
        }

        return hourStr + ":" + minuteStr + ":" + secondStr;
    }

    /**
     * 取得给定日历,给定格式的日期字符串
     * 
     * @param calendar
     *            日历,给定日历
     * @return String 取得默认的日期时间字符串"yyyy-MM-dd"
     */
    public static String toDateString(Calendar calendar) {
        String format = "yyyy-MM-dd";
        return toDateTimeString(calendar.getTime(), format);
    }

    /**
     * 取得给定日历,给定格式的日期时间字符串
     * 
     * @param calendar
     *            日历,给定日历
     * @return String 取得默认的日期时间字符串"yyyy-MM-dd HH:mm:ss"
     */
    public static String toDateTimeString(Calendar calendar) {
        String format = "yyyy-MM-dd HH:mm:ss";
        return toDateTimeString(calendar.getTime(), format);
    }

    /**
     * 取得给定日历,给定格式的日期时间字符串
     * 
     * @param calendar
     *            日历,给定日历
     * @param format
     *            格式,如String format = "yyyy-MM-dd HH:mm:ss";
     * @return String 取得给定日历,给定格式的日期时间字符串
     */
    public static String toDateTimeString(Calendar calendar, String format) {
        return toDateTimeString(calendar.getTime(), format);
    }

    /**
     * 取得给定时间,给定格式的日期时间字符串,标准格式:"yyyy-MM-dd HH:mm:ss";
     * 
     * @param datetime
     *            日期,给定时间的毫秒数
     * @return String 取得给定时间,给定格式的日期时间字符串
     */
    public static String toDateTimeString(long datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(datetime));
    }

    public static String toDateTimeStringSSS(long datetime){
    	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
           return sdf.format(new Date(datetime));
    }
    
    public static String toDateTimeStringSSS(Date date){
 	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        return sdf.format(date);
    }
    /**
     * 取得给定时间,给定格式的日期时间字符串
     * 
     * @param datetime
     *            日期,给定时间的毫秒数
     * @param format
     *            格式,如String format = "yyyy-MM-dd HH:mm:ss";
     * @return String 取得给定时间,给定格式的日期时间字符串
     */
    public static String toDateTimeString(long datetime, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(datetime));
    }

    /**
     * 取得给定时间,给定格式的日期时间字符串
     * 
     * @param date
     *            日期,给定时间
     * @param format
     *            格式,如String format = "yyyy-MM-dd HH:mm:ss";
     * @return String 取得给定时间,给定格式的日期时间字符串
     */
    public static String toDateTimeString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 取得当前的日期时间字符串
     * 
     * @param format
     *            格式,如String format = "yyyy-MM-dd HH:mm:ss";
     * @return String 取得当前的日期时间字符串
     */
    public static String getDateTimeString(String format) {
        return toDateTimeString(new Date(), format);
    }

    /**
     * 取得当前的日期时间字符串YYYY-MM-DD HH:mm:ss
     * 
     * @return String 取得当前的日期时间字符串YYYY-MM-DD HH:mm:ss
     */
    public static String getDateTimeString() {
        String format = "yyyy-MM-dd HH:mm:ss";
        return getDateTimeString(format);
    }

    /**
     * 取得当前的日期时间字符串YYYY/MM/DD HH:mm:ss (移动)
     * 
     * @return String 取得当前的日期时间字符串YYYY/MM/DD HH:mm:ss
     */
    public static String getDateTimeString2() {
        String format = "yyyy/MM/dd HH:mm:ss";
        return getDateTimeString(format);
    }

    /**
     * 取得当前的日期时间字符串YYYY/MM/DD (移动)
     * 
     * @return String 取得当前的日期时间字符串YYYY/MM/DD
     */
    public static String getDateString2() {
        String format = "yyyy/MM/dd";
        return getDateTimeString(format);
    }

    /**
     * 取得当前的日期时间字符串YYYYMMDDHHMISS
     * 
     * @return String 取得当前的日期时间字符串YYYYMMDDHHMISS
     */
    public static String getDateTime14String() {
        String format = "yyyyMMddHHmmss";
        return getDateTimeString(format);
    }

    /**
     * 取得当前的日期时间字符串YYMMDDHHMISS
     * 
     * @return String 取得当前的日期时间字符串YYMMDDHHMISS
     */
    public static String getDateTime12String() {
        String format = "yyMMddHHmmss";
        return getDateTimeString(format);
    }

    /**
     * 取得当前的日期时间字符串YYYYMMDD
     * 
     * @return String 取得当前的日期时间字符串
     */
    public static String getDateTime8String() {
        String format = "yyyyMMdd";
        return getDateTimeString(format);
    }

    /**
     * 取得当前的日期时间字符串YYYYMM
     * 
     * @return String 取得当前的日期时间字符串
     */
    public static String getDateTime6String() {
        String format = "yyyyMM";
        return getDateTimeString(format);
    }

    /**
     * 取得当前的日期时间字符串YYYY-MM-DD
     * 
     * @return String 取得当前的日期时间字符串
     */
    public static String getDateString() {
        String format = "yyyy-MM-dd";
        return getDateTimeString(format);
    }

    /**
     * 取得当前的日期时间字符串HH:mm:ss
     * 
     * @return String 取得当前的日期时间字符串
     */
    public static String getTimeString() {
        String format = "HH:mm:ss";
        return getDateTimeString(format);
    }

    /**
     * 取得当前的日期对应格式的毫秒数
     * 
     */
    public static int[] getDateTimes() {
        int[] dates = new int[7];
        Calendar calendar = Calendar.getInstance();
        dates[0] = calendar.get(Calendar.YEAR);
        dates[1] = calendar.get(Calendar.MONTH) + 1;
        dates[2] = calendar.get(Calendar.DAY_OF_MONTH);
        dates[3] = calendar.get(Calendar.HOUR_OF_DAY);
        dates[4] = calendar.get(Calendar.MINUTE);
        dates[5] = calendar.get(Calendar.SECOND);
        dates[6] = calendar.get(Calendar.MILLISECOND);
        return dates;
    }

    /**
     * 通过标准时间输入生成java.util.Date
     * 
     * @param yearStr
     * @param monthStr
     * @param dayStr
     * @param hourStr
     * @param minuteStr
     * @param secondStr
     * @return Calendar
     */
    public static Calendar toCalendar(String yearStr, String monthStr,
            String dayStr, String hourStr, String minuteStr, String secondStr) {
        int year, month, day, hour, minute, second;

        try {
            year = Integer.parseInt(yearStr);
            month = Integer.parseInt(monthStr);
            day = Integer.parseInt(dayStr);
            hour = Integer.parseInt(hourStr);
            minute = Integer.parseInt(minuteStr);
            second = Integer.parseInt(secondStr);
        } catch (Exception e) {
            return null;
        }

        return toCalendar(year, month, day, hour, minute, second);
    }

    /**
     * 通过String,组织日历
     * 
     * @param dates
     * @return 通过整型数组,返回日历
     */
    public static Calendar toCalendar(String datetime) {
        Date date = toDateFromStr(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 通过整型数组,组织日历
     * 
     * @param dates
     * @return 通过整型数组,返回日历
     */
    public static Calendar toCalendar(int[] dates) {
        if (dates == null || dates.length < 6) {
            return null;
        }

        return toCalendar(dates[0], dates[1], dates[2], dates[3], dates[4],
                dates[5]);
    }

    /**
     * 通过标准时间输入生成java.util.Date
     * 
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return Calendar
     */
    public static Calendar toCalendar(int year, int month, int day, int hour,
            int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, day);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);

        return c;
    }

    /**
     * 通过整型数组,组织日期
     * 
     * @param dates
     * @return 通过整型数组,组织日期
     */
    public static Date toDate(int[] dates) {
        if (dates == null || dates.length < 6) {
            return null;
        }

        return toCalendar(dates).getTime();
    }

    /**
     * 取得当前的日期时
     * 
     * @param str
     *            字符
     * @param format
     *            格式
     * @return 取得当前的日期时�?如果格式不对则返回null
     */
    public static Date toDateFromStr(String str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 取得当前的日期时按默认格式YYYY-MM-DD HH:mm:ss不对则返回null
     * 
     * @param str
     *            字符
     * @return 取得当前的日期时按默认格式不对则返回null
     */
    public static Date toDateFromStr(String str) {
        try {
            String format = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当前
     * 
     * @return 当前
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     * 
     * @return 月份
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日期
     * 
     * @return 当前日期
     */
    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取当前
     * 
     * @return 当前时间
     */
    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        return hour;
    }

    /**
     * 获取当前
     * 
     * @return 当前
     */
    public static int getCurrentMinute() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.MINUTE);

        return hour;
    }

    /**
     * 获取当前时间的星期数
     * @return 周数
     */
    public static int getCurrentWeek() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        week = week - 1;
        if (week == 0) {
            week = 7;
        }

        return week;
    }

    /**
     * 获取两个日期对象相差年数
     * 
     * @parma date1 日期对象
     * @param date2
     *            日期对象
     * @return int 年份差
     */
    public static int compareYear(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);

        return year1 - year2;
    }

    /**
     * 获取两个日期对象相差月数
     * 
     * @param date1
     *            日期对象
     * @param date2
     *            日期对象
     * @return int 月份差
     */
    public static int compareMonth(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }

        int year = compareYear(date1, date2);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int month1 = calendar.get(Calendar.MONTH);

        calendar.setTime(date2);
        int month2 = calendar.get(Calendar.MONTH);

        /* 进行比较 */
        return 12 * year + (month1 - month2);

    }

    /**
     * 获取两个日期对象相差天数
     * 
     * @param date1
     *            String yyyy-MM-dd
     * @param date2
     *            String yyyy-MM-dd
     * @return int 日差
     */
    public static int compareDay(String date1str, String date2str) {
        if (date1str == null || date2str == null) {
            return 0;
        }

        Date date1 = toDate(date1str, "00:00:01");
        Date date2 = toDate(date2str, "00:00:00");

        return compareDay(date1, date2);
    }

    /**
     * 获取两个日期对象相差天数
     * 
     * @param date1
     *            日期对象
     * @param date2
     *            日期对象
     * @return int 日差
     */
    public static int compareDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }

        long time1 = date1.getTime();
        long time2 = date2.getTime();

        long margin = time1 - time2;

        /* 转化成天 */
        int ret = (int) Math.floor((double) margin / (1000 * 60 * 60 * 24));

        return ret;
    }

    /**
     * 获取两个日期对象相差的小时数
     * 
     * @param date1
     *            日期对象
     * @param date2
     *            日期对象
     * @return int 相差小时
     */
    public static int compareHour(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }

        long time1 = date1.getTime();
        long time2 = date2.getTime();

        long margin = time1 - time2;

        int ret = (int) Math.floor((double) margin / (1000 * 60 * 60));

        return ret;
    }

    /**
     * 获取两个日期对象相差的分钟数
     * 
     * @param date1
     *            日期对象
     * @param date2
     *            日期对象
     * @return int 相差分钟
     */
    public static int compareMinute(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }

        long time1 = date1.getTime();
        long time2 = date2.getTime();

        long margin = time1 - time2;

        int ret = (int) Math.floor((double) margin / (1000 * 60));

        return ret;
    }

    public static int compareMinute(String date1Str, String date2Str) {
        if (date1Str == null || date2Str == null) {
            return 0;
        }
        Date date1 = toDate(date1Str);
        Date date2 = toDate(date2Str);
        long time1 = date1.getTime();
        long time2 = date2.getTime();

        long margin = time1 - time2;

        int ret = (int) Math.floor((double) margin / (1000 * 60));

        return ret;
    }

    /**
     * 获取两个日期对象相差秒数
     * 
     * @param date1
     *            日期对象
     * @param date2
     *            日期对象
     * @return int 相差秒数
     */
    public static int compareSecond(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }

        long time1 = date1.getTime();
        long time2 = date2.getTime();

        long margin = time1 - time2;

        Long longValue = new Long(margin / (1000));

        return longValue.intValue();
    }

    /**
     * 获取和当前时间毫秒差
     * 
     * @param dateTime
     *            YYYY-MM-DD hh:mm:ss
     * @return 毫秒
     */
    public static long getTimeMargin(String dateTime) {
        int index = dateTime.indexOf(" ");
        String date = dateTime.substring(0, index);
        String time = dateTime.substring(index + 1);

        int dateSlash1 = date.indexOf("-");
        int dateSlash2 = date.lastIndexOf("-");

        if (dateSlash1 <= 0 || dateSlash1 == dateSlash2) {
            return -1;
        }

        int timeColon1 = time.indexOf(":");
        int timeColon2 = time.lastIndexOf(":");

        if (timeColon1 <= 0 || timeColon1 == timeColon2) {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();

        try {
            int year = Integer.parseInt(date.substring(0, dateSlash1));
            int month = Integer.parseInt(date.substring(dateSlash1 + 1,
                    dateSlash2));
            int day = Integer.parseInt(date.substring(dateSlash2 + 1));

            int hour = Integer.parseInt(time.substring(0, timeColon1));
            int minute = Integer.parseInt(time.substring(timeColon1 + 1,
                    timeColon2));
            int second = Integer.parseInt(time.substring(timeColon2 + 1));

            calendar.set(year, month - 1, day, hour, minute, second);
        } catch (Exception e) {
            return -1;
        }

        return System.currentTimeMillis() - calendar.getTimeInMillis();
    }

    /**
     * 获取当前时间的前或数天的年月日，并以数组形式还回数组0为年为月为日
     * 
     * @param year
     *            当前
     * @param month
     *            当前
     * @param day
     *            当前日期
     * @param days
     *            相差天数
     * @return 年月日数
     */
    public static int[] getPreviousDay(int year, int month, int day, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);

        long longDate = (calendar.getTime()).getTime()
                - (1000 * 60 * 60 * 24 * days);
        Date date = new Date(longDate);
        calendar.setTime(date);

        int[] rtn = new int[3];
        rtn[0] = calendar.get(Calendar.YEAR);
        rtn[1] = calendar.get(Calendar.MONTH) + 1;
        rtn[2] = calendar.get(Calendar.DATE);

        return rtn;
    }

    /**
     * 获取前几天对应的当前时间
     * 
     * @param format
     *            格式化如 yyyy-MM-dd
     * @return String
     */
    public static String getPreviousDateString(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -days);

        return toDateString(calendar);
    }

    /**
     * 获取前几天对应的当前时间
     * 
     * @param format
     *            格式化如 yyyy-MM-dd
     * @return String
     */
    public static String getPreviousDateTimeString(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -days);

        return toDateTimeString(calendar);
    }

    /**
     * 获取前几小时对应的当前时
     * 
     * @param format
     *            格式化如 yyyy-MM-dd
     * @return String
     */
    public static String getPreviousDateByHourString(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -hours);

        return toDateString(calendar);
    }

    /**
     * 获取前几小时对应的当前时
     * 
     * @param format
     *            格式化如 yyyy-MM-dd
     * @return String
     */
    public static String getPreviousDateTimeByHourString(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -hours);

        return toDateTimeString(calendar);
    }

    /**
     * 获取前几秒对应的当前时间
     * 
     * @param format
     *            格式化如 yyyy-MM-dd
     * @return String
     */
    public static String getPreviousDateBySecondString(int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -second);

        return toDateString(calendar);
    }

    /**
     * 获取前几秒对应的当前时间
     * 
     * @param format
     *            格式化如 yyyy-MM-dd
     * @return String
     */
    public static String getPreviousDateTimeBySecondString(int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -second);

        return toDateTimeString(calendar);
    }

    /**
     * 获取前一天对应的当前时间,采用标准格式yyyy-MM-dd
     * 
     * @return String
     */
    public static String getPreviousDateString() {

        return getPreviousDateTimeString("yyyy-MM-dd");
    }

    /**
     * 获取上一个月日期,2011-01
     * 
     * @return
     */
    public static String getPreviousMonthString() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);

        return toDateTimeString(calendar, "yyyy-MM");
    }

    /**
     * 获取上周
     * 
     * @return
     */
    public static String getPreviousWeekString() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        return toDateTimeString(calendar, "yyyy-MM-dd");
    }

    /**
     * 获取前一天对应的当前时间,采用短信格式yyyy/MM/dd
     * 
     * @return String
     */
    public static String getPreviousDateString2() {

        return getPreviousDateTimeString("yyyy/MM/dd");
    }

    /**
     * 获取前一天对应的当前时间
     * 
     * @param format
     *            格式化如 yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String getPreviousDateTimeString(String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        return toDateTimeString(calendar, format);
    }

    /**
     * 获取前一天对应的当前时间,采用标准格式yyyy-MM-dd HH:mm:ss
     * 
     * @return String
     */
    public static String getPreviousDateTimeString() {

        return getPreviousDateTimeString("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取前一天对应的当前时间,采用短信格式yyyy/MM/dd HH:mm:ss
     * 
     * @return String
     */
    public static String getPreviousDateTimeString2() {

        return getPreviousDateTimeString("yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 获取后一天的Date String
     * 
     * @param spe
     *            分隔�?
     * @return YYYY+spe+MM+spe+DD
     */
    public static String getNextDateStr(String spe) {
        Calendar calendar = Calendar.getInstance();

        long longDate = (calendar.getTime()).getTime()
                + (1000 * 60 * 60 * 24 * 1);
        Date date = new Date(longDate);
        calendar.setTime(date);

        return toDateString(calendar.getTime(), spe);
    }

    /**
     * 获取指定时间的后的Date String
     * 
     * @param spe
     *            分隔
     * @return YYYY+spe+MM+spe+DD
     */
    public static String getNextDateString(String currentDate) {
        Calendar calendar = toCalendar(currentDate + " 00:00:01");
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        return toDateString(calendar);
    }

    /**
     * 获取后几天对应的当前时间
     * 
     * @param format
     *            格式化如 yyyy-MM-dd
     * @return String
     */
    public static String getNextDateString(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days);

        return toDateString(calendar);
    }

    /**
     * 获取后几天对应的当前时间
     * 
     * @param format
     *            格式化如 yyyy-MM-dd
     * @return String
     */
    public static String getNextDateTimeString(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days);

        return toDateTimeString(calendar);
    }

    /**
     * 获取后几小时对应的当前时�?
     * 
     * @param format
     *            格式化如 yyyy-MM-dd
     * @return String
     */
    public static String getNextDateStringByHour(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, hours);

        return toDateString(calendar);
    }

    /**
     * 获取后几小时对应的当前时
     * 
     * @param format
     *            格式化如 yyyy-MM-dd
     * @return String
     */
    public static String getNextDateTimeStringByHour(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, hours);

        return toDateTimeString(calendar);
    }

    /**
     * 获取后几分对应的当前时间
     * 
     * @param format
     *            格式化如 yyyy-MM-dd
     * @return String
     */
    public static String getNextDateTimeStringByMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);

        return toDateTimeString(calendar);
    }

    /**
     * 获取后几秒对应的当前时间
     * 
     * @param format
     *            格式化如 yyyy-MM-dd
     * @return String
     */
    public static String getNextDateTimeStringBySecond(int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, seconds);

        return toDateTimeString(calendar);
    }

    /**
     * 获取后一天的Date String
     * 
     * @param format
     *            格式
     * @return YYYY+spe+MM+spe+DD
     */
    public static String getNextDateTimeStr(String format) {
        Calendar calendar = Calendar.getInstance();

        long longDate = (calendar.getTime()).getTime() + (1000 * 60 * 60 * 24);
        Date date = new Date(longDate);
        calendar.setTime(date);

        return toDateTimeString(calendar.getTime(), format);
    }

    /**
     * 获取后一天String
     * 
     * @param year
     *            当前
     * @param month
     *            当前
     * @param day
     *            当前日期
     * @param days
     *            相差天数
     * @return 年月日数
     */
    public static int[] getNextDay() {
        Calendar calendar = Calendar.getInstance();

        long longDate = (calendar.getTime()).getTime() + (1000 * 60 * 60 * 24);
        Date date = new Date(longDate);
        calendar.setTime(date);

        int[] rtn = new int[3];
        rtn[0] = calendar.get(Calendar.YEAR);
        rtn[1] = calendar.get(Calendar.MONTH) + 1;
        rtn[2] = calendar.get(Calendar.DATE);

        return rtn;
    }

    /**
     * 获取当前时间的后或数天的年月日，并以数组形式还回数组0为年为月为日
     * 
     * @param year
     *            当前
     * @param month
     *            当前
     * @param day
     *            当前日期
     * @param days
     *            相差天数
     * @return 年月日数
     */
    public static int[] getNextDay(int year, int month, int day, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);

        long longDate = (calendar.getTime()).getTime()
                + (1000 * 60 * 60 * 24 * days);
        Date date = new Date(longDate);
        calendar.setTime(date);

        int[] rtn = new int[3];
        rtn[0] = calendar.get(Calendar.YEAR);
        rtn[1] = calendar.get(Calendar.MONTH) + 1;
        rtn[2] = calendar.get(Calendar.DATE);

        return rtn;
    }

    /**
     * 获取指定时间周的第一天的时间
     * 
     * @param year
     * @param month
     * @param day
     * @return 年月日数
     */
    public static int[] getDayOfWeek(int year, int month, int day) {
        int[] rtn = new int[6];
        int week = 0;
        long longDate = 0;

        Date date = null;
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);

        week = calendar.get(Calendar.DAY_OF_WEEK);
        longDate = (calendar.getTime()).getTime()
                - (60 * 1000 * 60 * 24 * (week - 1));
        date = new Date(longDate);
        calendar1.setTime(date);

        rtn[0] = calendar1.get(Calendar.YEAR);
        rtn[1] = calendar1.get(Calendar.MONTH) + 1;
        rtn[2] = calendar1.get(Calendar.DATE);

        longDate = (calendar.getTime()).getTime()
                + (60 * 1000 * 60 * 24 * (7 - week));
        date = new Date(longDate);
        calendar2.setTime(date);
        rtn[3] = calendar2.get(Calendar.YEAR);
        rtn[4] = calendar2.get(Calendar.MONTH) + 1;
        rtn[5] = calendar2.get(Calendar.DATE);

        return rtn;
    }

    /*********************************************************/
    // 以下为数据库使用的日期方Timestamp ,java.sql.Date
    /*********************************************************/

    /** 返回当前时间的Timestamp */
    public static Timestamp nowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /** 返回从当日开始的Timestamp */
    public static Timestamp getDayStart(Timestamp stamp) {
        return getDayStart(stamp, 0);
    }

    /** 返回多少天后的Timestamp */
    public static Timestamp getDayStart(Timestamp stamp, int daysLater) {
        Calendar tempCal = Calendar.getInstance();

        tempCal.setTime(new Date(stamp.getTime()));
        tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH),
                tempCal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        tempCal.add(Calendar.DAY_OF_MONTH, daysLater);
        return new Timestamp(tempCal.getTime().getTime());
    }

    /** 返回下一天开始的Timestamp */
    public static Timestamp getNextDayStart(Timestamp stamp) {
        return getDayStart(stamp, 1);
    }

    /** 返回从当日结束的Timestamp */
    public static Timestamp getDayEnd(Timestamp stamp) {
        return getDayEnd(stamp, 0);
    }

    /** 返回从多少日后结束的Timestamp */
    public static Timestamp getDayEnd(Timestamp stamp, int daysLater) {
        Calendar tempCal = Calendar.getInstance();

        tempCal.setTime(new Date(stamp.getTime()));
        tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH),
                tempCal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        tempCal.add(Calendar.DAY_OF_MONTH, daysLater);
        return new Timestamp(tempCal.getTime().getTime());
    }

    /**
     * String到java.sql.Date的转标准格式:YYYY-MM-DD
     * 
     * @param date
     *            The date String
     * @return java.sql.Date
     */
    public static java.sql.Date toSqlDate(String date) {
        Date newDate = toDate(date, "00:00:00");

        if (newDate == null) {
            return null;
        }

        return new java.sql.Date(newDate.getTime());
    }

    /**
     * 生成java.sql.Date,通过传入year, month, day
     * 
     * @param yearStr
     * @param monthStr
     * @param dayStr
     * @return A java.sql.Date
     */
    public static java.sql.Date toSqlDate(String yearStr, String monthStr,
            String dayStr) {
        Date newDate = toDate(yearStr, monthStr, dayStr, "0", "0",
                "0");

        if (newDate == null) {
            return null;
        }

        return new java.sql.Date(newDate.getTime());
    }

    /**
     * 生成java.sql.Date,通过传入year, month, day
     * 
     * @param year
     * @param month
     * @param day
     * @return A java.sql.Date
     */
    public static java.sql.Date toSqlDate(int year, int month, int day) {
        Date newDate = toDate(year, month, day, 0, 0, 0);

        if (newDate == null) {
            return null;
        }

        return new java.sql.Date(newDate.getTime());
    }

    /**
     * 转换String �?java.sql.Time,格式:"HH:MM:SS"
     * 
     * @param time
     *            The time String
     * @return A java.sql.Time
     */
    public static java.sql.Time toSqlTime(String time) {
        Date newDate = toDate("1970-1-1", time);

        if (newDate == null) {
            return null;
        }

        return new java.sql.Time(newDate.getTime());
    }

    /**
     * 生成 java.sql.Time 通过输入
     * 
     * @param hourStr
     * @param minuteStr
     * @param secondStr
     * @return A java.sql.Time
     */
    public static java.sql.Time toSqlTime(String hourStr, String minuteStr,
            String secondStr) {
        Date newDate = toDate("0", "0", "0", hourStr, minuteStr,
                secondStr);

        if (newDate == null) {
            return null;
        }

        return new java.sql.Time(newDate.getTime());
    }

    /**
     * 生成 java.sql.Time 通过输入
     * 
     * @param hour
     * @param minute
     * @param second
     * @return A java.sql.Time
     */
    public static java.sql.Time toSqlTime(int hour, int minute, int second) {
        Date newDate = toDate(0, 0, 0, hour, minute, second);

        if (newDate == null) {
            return null;
        }

        return new java.sql.Time(newDate.getTime());
    }

    /**
     * 转换String �?java.sql.Timestamp,格式:"YYYY-MM-DD HH:MM:SS"
     * 
     * @param dateTime
     *            格式:"YYYY-MM-DD HH:MM:SS"
     * @return Timestamp
     */
    public static Timestamp toTimestamp(String dateTime) {

        Date newDate = toDate(dateTime);

        if (newDate == null) {
            return null;
        }

        return new Timestamp(newDate.getTime());
    }

    /**
     * 转换String �?java.sql.Timestamp,格式:"YYYY-MM-DD HH:MM:SS"
     * 
     * @param date
     *            The date String: YYYY-MM-DD
     * @param time
     *            The time String: HH:MM:SS
     * @return Timestamp
     */
    public static Timestamp toTimestamp(String date, String time) {
        Date newDate = toDate(date, time);

        if (newDate == null) {
            return null;
        }

        return new Timestamp(newDate.getTime());
    }

    /**
     * 生成 Timestamp 通过输入
     * 
     * @param yearStr
     * @param monthStr
     * @param dayStr
     * @param hourStr
     * @param minuteStr
     * @param secondStr
     * @return Timestamp
     */
    public static Timestamp toTimestamp(String yearStr, String monthStr,
            String dayStr, String hourStr, String minuteStr, String secondStr) {
        Date newDate = toDate(yearStr, monthStr, dayStr, hourStr,
                minuteStr, secondStr);

        if (newDate == null) {
            return null;
        }

        return new Timestamp(newDate.getTime());
    }

    /**
     * 生成 Timestamp 通过输入
     * 
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return Timestamp
     */
    public static Timestamp toTimestamp(int year, int month, int day, int hour,
            int minute, int second) {
        Date newDate = toDate(year, month, day, hour, minute, second);

        if (newDate == null) {
            return null;
        }

        return new Timestamp(newDate.getTime());
    }

    /**
     * 没有毫秒的时间转成带毫秒的时间
     * 
     * @param time
     * @return
     */
    public static long timeIntToLong(int time) {
        return (long) time * 1000;
    }

    /**
     * 有毫秒的时间转成不带毫秒的时间
     * 
     * @param time
     * @return
     */
    public static int timeLongToInt(long time) {
        return (int) time / 1000;
    }

    /**
     * 长整形转日期
     * 
     * @param time
     * @return
     */
    public static Date longToDate(long time) {
        Date date = new Date(time);
        return date;
    }

    /**
     * int转日期
     * 
     * @param time
     * @return
     */
    public static Date intToDate(int time) {
        long timeL = timeIntToLong(time);
        Date date = new Date(timeL);
        return date;
    }

    /**
     * 将秒转换为小时，分钟，秒钟
     * 
     * @param second
     * @return
     */
    public static String secondToHHMMSS(int second) {
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;
        return String.format("%02d:%02d:%02d", hh, mm, ss);
    }

    /**
     * 
     * @param datetime
     * @return
     */
    public static String toYYYMMDD(long datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(datetime));
    }

    /**
     * 
     * @param datetime
     * @return
     */
    public static String toYYYMMDD(Date datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(datetime);
    }

    /**
     * 
     * @param datetime
     * @return
     */
    public static String toYYYMM(long datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(new Date(datetime));
    }

    /**
     * 将秒转换为小时，分钟，秒钟
     * 
     * @param second
     * @return
     */
    public static String secondToHHMM(long second) {
        long hh = second / 3600;
        long mm = second % 3600 / 60;
        return String.format("%02d:%02d", hh, mm);
    }

    /**
     * 
     * @param yyyyMMDD
     * @return
     */
    public static Date getDayEnd(final String yyyyMMDD) {
    	Date date=null;
		try {
			date = sf.parse(yyyyMMDD);
			return dayEnd(date);
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage(), e);
		}  
    	return date;
    }
    
    /**
     * 
     * @param date
     * @return
     */
    public static Date dayEnd(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }
    
    /**
     * 
     * @param yyyyMMDD
     * @return
     */
    public static Date getDayStart(final String yyyyMMDD) {
    	Date date=null;
		try {
			date = sf.parse(yyyyMMDD);
			return dayStart(date);
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage(), e);
		}  
    	return date;
    }
    
    /**
     * 
     * @param date
     * @return
     */
    public static Date dayStart(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }
    
    /**
     * 今天开始
     * @return
     */
    public static Date todayStart() {
	    Calendar date = Calendar.getInstance();
	    //设置时间为 xx-xx-xx 00:00:00
	    date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE), 0, 0, 0);
	    return date.getTime();
    }
    
    /**
     * 今天结束
     * @return
     */
    public static Date todayEnd() {
	    Calendar date = Calendar.getInstance();
	    //设置时间为 xx-xx-xx 23:59:59
	    date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE), 23, 59, 59);
	    return date.getTime();
    }
    
    /**
     * 
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(final Date date, int second) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(Calendar.SECOND, second);
	    return c.getTime();
    }
}
