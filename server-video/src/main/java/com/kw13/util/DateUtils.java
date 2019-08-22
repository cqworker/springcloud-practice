package com.kw13.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Desc 时间工具类
 * @Author yejx
 * @Date 2019/3/8
 */
public class DateUtils {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DAY_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
    public static final String DEFAULT_FULL_COMPACT_PATTERN = "yyyyMMddHHmmssSSS";
    public static final String DEFAULT_COMPACT_PATTERN = "yyyyMMddHHmmss";
    public static final String DAY_PATTERN = "yyyyMMdd";
    public static final String DAY_SLASH_PATTERN = "yyyy/MM/dd";
    public static final String DEFAULT_YEAR = "yyyy";

    /**
     * 将日期转换为字符串
     *
     * @param date    时间
     * @param pattern 输出格式
     * @return 时分秒
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 比较两个日期之间的大小
     *
     * @param s
     * @param e
     * @return
     * @throws ParseException
     */
    public static Integer compareDate(String s, String e) throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        if (format.parse(s) == null || format.parse(e) == null) {
            throw new Exception("获取比较参数,出现异常");
        }
        long l = format.parse(s).getTime() - format.parse(e).getTime();
        long days = l / 1000 / 60 / 60 / 24 + 1;
        return (int) (days);
    }

    /**
     * 将字符串转为时间日期
     *
     * @param dateString 字符串格式时间
     * @param pattern    转化格式
     * @return 时间日期
     * @throws ParseException 转化异常
     */
    public static Date stringToDate(String dateString, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateString);
    }

    /**
     * 时间小时级别加减操作，正往后加 负数往后减
     *
     * @param date  时间
     * @param hours 小时数
     * @return 新的时间
     */
    public static Date addHours(Date date, int hours) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    public static Date addMin(Date date, int min) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, min);
        return calendar.getTime();
    }

    public static Date addSec(Date date, int sec) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, sec);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int year) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    public static Date addDay(Date date, int day) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

}
