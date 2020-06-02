package com.cn.hnust.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取当前时间
 * 
 * @since 2013-12-03
 */
public class DateFormat {

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static String format()
	{
		long time = System.currentTimeMillis();
	  	Date date = new Date(time);    	
	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  	String currentTime = sdf.format(date);
		
		
		return currentTime;
	}
	public static String formatDate1(String date) throws ParseException
	{
//		long time = System.currentTimeMillis();
//		Date date = new Date(time);    	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time = sdf.parse(date); 
		String currentTime = sdf.format(time);
		
		
		return currentTime;
	}

	public static String date2String(Date date) throws ParseException{
		
		String time ="";
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			 time = sdf.format(date);
		}
	
		return time;
		
	}
	

	public static String formatDate2(String date) throws ParseException
	{
//		long time = System.currentTimeMillis();
//		Date date = new Date(time);    	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(date); 
		String currentTime = sdf.format(time);
		
		
		return currentTime;
	}
	public static String currentDate() throws ParseException
	{
	    long time = System.currentTimeMillis();
	    Date date = new Date(time);    	
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String currentTime = sdf.format(date);		
		return currentTime;
	}
	
	public static String weekLaterDate() throws ParseException
	{
		   Calendar calendar = Calendar.getInstance();  
	       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);  
	       Date today = calendar.getTime();  
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	       String result = format.format(today);  
	       return result;
	}
	
	public static Date formatToDate(Date date) throws ParseException
	{ 	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		return sdf.parse(str);
	}
	
	
	public static String addDays(String dateTime,int days) throws ParseException
	{ 	

         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
          Date  currdate = format.parse(dateTime);
          System.out.println("现在的日期是：" + currdate);
          Calendar ca = Calendar.getInstance();
          ca.setTime(currdate);
          ca.add(Calendar.DATE, days);// num为增加的天数，可以改变的
          currdate = ca.getTime();
          String enddate = format.format(currdate);
          System.out.println("增加天数以后的日期：" + enddate);
          return enddate;
	}
	
	
	public static Date addMonth(Date currdate,int month) throws ParseException
	{ 	
          Calendar ca = Calendar.getInstance();
          ca.setTime(currdate);
          ca.add(Calendar.MONTH, month);// num为增加的天数，可以改变的
          currdate = ca.getTime();
          return currdate;
	}
	
	
	
	
	public static int calHours(String date) throws ParseException
	{ 	

     SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
     String currentDate = simpleFormat.format(new Date());  
     String publishDate = simpleFormat.format(date);  
     long from = simpleFormat.parse(currentDate).getTime();  
     long to = simpleFormat.parse(publishDate).getTime();  
     int hours = (int) ((from - to)/(1000 * 60 * 60));  
     return hours;
	}
	
	/**
	 * 计算天数
	 * @Title calDays 
	 * @Description
	 * @param date
	 * @return
	 * @throws ParseException
	 * @return int
	 */
	public static int calDays(String date1,String date2) throws ParseException
	{ 	

     SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
//     String d1 = simpleFormat.format(date1);  
//     String d2 = simpleFormat.format(date2);  
     long from = simpleFormat.parse(date1).getTime();  
     long to = simpleFormat.parse(date2).getTime();  
     int days = (int) ((from - to)/(1000 * 60 * 60 * 24));  
     return days;
	}
	
	
	
	/**
	 * 计算天数
	 * @Title calDays 
	 * @Description
	 * @param date
	 * @return
	 * @throws ParseException
	 * @return int
	 */
	public static int calDays(Object date1,Object date2) throws ParseException
	{ 	

     SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
     String d1 = simpleFormat.format(date1);  
     String d2 = simpleFormat.format(date2);  
     long from = simpleFormat.parse(d1).getTime();  
     long to = simpleFormat.parse(d2).getTime();  
     int days = (int) ((from - to)/(1000 * 60 * 60 * 24));  
     return days;
	}
	
	/**
	 * 计算天数
	 * @Title calDays 
	 * @Description
	 * @param date
	 * @return
	 * @throws ParseException
	 * @return int
	 */
	public static int calDays(Date date1,Date date2) throws ParseException
	{ 	
     long from = date1.getTime();  
     long to = date2.getTime();  
     int days = (int) ((from - to)/(1000 * 60 * 60 * 24));  
     return days;
	}
	
	
	public static Boolean compareDate(Date d1,Date d2,int days) throws ParseException
	{ 	
          Calendar ca = Calendar.getInstance();
          ca.setTime(d1);
          ca.add(Calendar.DATE, days);// num为增加的天数，可以改变的
          d1 = ca.getTime();
          if(d1.getTime() >= d2.getTime()){
        	  return true;
          }else{
        	  return false;
          }
         
	}
	
	
	public static Date addDays(Date date,int days) throws ParseException
	{ 	
          Calendar ca = Calendar.getInstance();
          ca.setTime(date);
          ca.add(Calendar.DATE, days);// num为增加的天数，可以改变的
          date = ca.getTime();
          return date;
	}
}

