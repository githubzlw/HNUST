package com.cn.hnust.util;

import java.util.Calendar;
import java.util.Date;

public class Demo {

	public static void main(String args[]){
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		int dayOfWeek=calendar1.get(Calendar.DAY_OF_WEEK)-1;
		int offset1=1-dayOfWeek;
		int offset2=7-dayOfWeek;
		calendar1.add(Calendar.DATE, offset1+7);
		calendar2.add(Calendar.DATE, offset2-5);
		System.out.println(calendar1.getTime());//last Monday
		System.out.println(calendar2.getTime());//last Sunday
	}
	
}
