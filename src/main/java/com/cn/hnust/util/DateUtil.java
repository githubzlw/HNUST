package com.cn.hnust.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	* 字符串转换成日期
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
	/**
	 * 根据当前时间得到前两周的时间
	 * @return
	 */
	public static String getTwoWeeksDate(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去两周
        c.setTime(new Date());
        c.add(Calendar.DATE, - 14);
        Date d = c.getTime();
        String date = format.format(d);
		return date;
	}
	

	/**
	 * 获取上个月日期
	 * @Title getPrevMonthDate 
	 * @Description
	 * @param date
	 * @return
	 * @return Date
	 */
	public static Date getPrevMonthDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);                                       // 设置为当前时间
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
		date = calendar.getTime();
		return date;
	}
	
}
