package com.cn.hnust.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 处理日期工具类
 *
 * @author wangpeng
 * @date 2018/5/8
 */
public class CalendarUtil {

    /**
     * 日期格式化默认格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 时间格式化默认格式
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    /**
     * 获得当前年份
     *
     * @return
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获得指定日期年份
     *
     * @param calendar
     * @return
     */
    public static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获得当前月份
     *
     * @return
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 获得指定日期月份
     *
     * @param calendar
     * @return
     */
    public static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日期是当前年第几天
     *
     * @return
     */
    public static int getDayOfYear() {
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取指定日期是当前年第几天
     *
     * @param calendar
     * @return
     */
    public static int getDayOfYear(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取当前日期是当前月第几天
     *
     * @return
     */
    public static int getDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期是当前月第几天
     *
     * @param calendar
     * @return
     */
    public static int getDayOfMonth(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前日期是当前星期第几天
     *
     * @return
     */
    public static int getDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取指定日期是当前星期第几天
     *
     * @param calendar
     * @return
     */
    public static int getDayOfWeek(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得今天是这个月的第几周
     *
     * @return
     */
    public static int getWeekOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * 获得指定日期是这个月的第几周
     *
     * @param calendar
     * @return
     */
    public static int getWeekOfMonth(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * 将日期转换成字符串
     *
     * @param date
     * @return
     */
    public static String convertDateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(date);
    }

    /**
     * 将日期转换成字符串
     *
     * @param date
     * @return
     */
    public static String convertDateToString(Date date, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    /**
     * 将时间转换成字符串
     *
     * @param dateTime
     * @return
     */
    public static String convertDateTimeToString(Date dateTime) {
        SimpleDateFormat df = new SimpleDateFormat(DATETIME_FORMAT);
        return df.format(dateTime);
    }

    /**
     * 根据特定格式将字符串转换成Date
     *
     * @param date
     * @param dateFormat
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String date, String dateFormat) throws ParseException {
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.parse(date);
    }

    /**
     * 将Date转换成Calendar
     *
     * @param date
     * @return
     */
    public static Calendar convertDateToCalendar(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 将Calendar转换成String
     *
     * @param calendar
     * @return
     */
    public static String convertCalendarToDateString(Calendar calendar) {
        Date date = calendar.getTime();
        return convertDateToString(date);
    }

    /**
     * 根据特定格式将字符串转换成Calendar
     *
     * @param date
     * @param dateFormat
     * @return
     * @throws ParseException
     */
    public static Calendar convertStringToCalendar(String date, String dateFormat) throws ParseException {
        return convertDateToCalendar(convertStringToDate(date, dateFormat));
    }

    /**
     * 获取两个日期间隔天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDaysBetweenTwoDay(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000));
    }

    /**
     * 获取当月第一天日期
     *
     * @return Calendar
     */
    public static Calendar getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        //设为当前月的1号
        calendar.set(Calendar.DATE, 1);
        return calendar;
    }

    /**
     * 获取指定月第一天日期
     *
     * @param calendar
     * @return Calendar
     */
    public static Calendar getFirstDayOfMonth(Calendar calendar) {
        //设为当前月的1号
        calendar.set(Calendar.DATE, 1);
        return calendar;
    }

    /**
     * 获取当月最后一天日期
     *
     * @return Calendar
     */
    public static Calendar getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        //设为当前月的1号
        calendar.set(Calendar.DATE, 1);
        //加一个月，变为下月的1号
        calendar.add(Calendar.MONTH, 1);
        //减去一天，变为当月最后一天
        calendar.add(Calendar.DATE, -1);
        return calendar;
    }

    /**
     * 获取指定月最后一天日期
     *
     * @param calendar
     * @return Calendar
     */
    public static Calendar getLastDayOfMonth(Calendar calendar) {
        //设为当前月的1号
        calendar.set(Calendar.DATE, 1);
        //加一个月，变为下月的1号
        calendar.add(Calendar.MONTH, 1);
        //减去一天，变为当月最后一天
        calendar.add(Calendar.DATE, -1);
        return calendar;
    }

    /**
     * 获取上月第一天日期
     *
     * @return Calendar
     */
    public static Calendar getFirstDayOfPreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        //设为当前月的1号
        calendar.set(Calendar.DATE, 1);
        //减一个月，变为上月的1号
        calendar.add(Calendar.MONTH, -1);
        return calendar;
    }

    /**
     * 是否闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        return new GregorianCalendar().isLeapYear(year);
    }

    /**
     * 获取某年某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getLastDayOfMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        }
        return 0;
    }
}
