package com.cn.hnust.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static long getIsBefore(Date dt1, Date dt2) {
        long sub = dt1.getTime() - dt2.getTime();
        return sub / (60 * 60 * 24 * 1000);
    }


    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 根据当前时间得到前两周的时间
     *
     * @return
     */
    public static String getTwoWeeksDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去两周
        c.setTime(new Date());
        c.add(Calendar.DATE, -14);
        Date d = c.getTime();
        String date = format.format(d);
        return date;
    }

    /**
     * 根据当前时间得到指定时间间隔的时间字符串
     *
     * @return
     */
    public static String getIntervalDate(int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, day);
        return format.format(c.getTime());
    }


    /**
     * 获取上个月日期
     *
     * @param date
     * @return Date
     * @Title getPrevMonthDate
     * @Description
     */
    public static Date getPrevMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);                                       // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        return date;
    }

}
