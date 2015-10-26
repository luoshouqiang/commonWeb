package com.boka.insurance.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;


/**
 * 日期处理类
 * @author luosq
 *
 */
public class DateUtils {
	// ~ Static fields/initializers
	// =============================================

	public static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm";
	
	public static String DD_MM_PATTERN = "yyyy-MM-dd HH";
	
	public static String timePattern = "HH:mm";
	/** 日期格式yyyy-MM字符串常量 */
	public static final String MONTH_FORMAT = "yyyy-MM";
	/** 日期格式yyyy-MM-dd字符串常量 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	/** 日期格式MM-dd字符串常量 */
	public static final String DAY_FORMAT = "MM-dd";
	/** 日期格式HH:mm:ss字符串常量 */
	public static final String HOUR_FORMAT = "HH:mm:ss";	
	/** 日期格式HH:mm字符串常量 */
	public static final String MINUTE_FORMAT = "HH:mm";
	/** 日期格式yyyy-MM-dd HH:mm:ss字符串常量 */
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final int[] littleMonths=new int[]{4,6,9,11};
	
	private static final int[] bigMonths=new int[]{1,3,5,7,8,10,12};
	// ~ Methods
	// ================================================================

	private DateUtils() {
	}

	public static String formatDate(Date date, String format) {
		if (date == null) {
			return "";
		}
		if (format == null) {
			format = DEFAULT_PATTERN;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);

	};

	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		return sdf.format(date);

	};
	
	public static Date parseString(String timeStr) {
		if (timeStr == null||timeStr.trim().isEmpty()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		try {
			return sdf.parse(timeStr);
		} catch (ParseException e) {
			return null;
		}

	};
	
	public static Date parseString(String timeStr,String format) {
		if (timeStr == null||timeStr.trim().isEmpty()) {
			return null;
		}
		if(format==null||format.trim().isEmpty()){
			format=DEFAULT_PATTERN;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(timeStr);
		} catch (ParseException e) {
			return null;
		}

	};
	
	public static int getYear(Date date){
		if(date==null){
			date=new Date();
		}
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(date);		
		int year=calendar.get(Calendar.YEAR);
		return year;
	}
	
	/**
	 * 在原时间基础上增加了天数，
	 * @param startDate
	 * @param addDays 为负数，就是减少天数
	 * @return
	 */
	public static Date addDay(Date startDate,int addDays){
		if(startDate==null){
			startDate=new Date();
		}
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(startDate);		
		calendar.add(Calendar.DAY_OF_YEAR, addDays);
		return calendar.getTime();
	}
	
	/**
	 * 在原时间基础上增加了月份，
	 * @param startDate
	 * @param addDays 为负数，就是减少月份
	 * @return
	 */
	public static Date addMonth(Date startDate,int month){
		
		if(startDate==null){
			startDate=new Date();
		}		
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(startDate);	
		int oldDay=calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.MONTH, month);	
	    if(getMaxDayOfMonth(startDate)==oldDay){
	    	calendar.set(Calendar.DAY_OF_MONTH, getMaxDayOfMonth(calendar.getTime()));
	    }
		return calendar.getTime();
	   
	}
	
	/**
	 * 在原时间基础上增加了分钟，
	 * @param startDate
	 * @param addDays 为负数，就是减少分钟数
	 * @return
	 */
	public static Date addMin(Date startDate,int minute){
		if(startDate==null){
			startDate=new Date();
		}
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(startDate);		
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}
	
	/**
	 * 在原时间的设置小时
	 * @param startDate
	 * @param hours
	 * @return
	 */
	public static Date setHours(Date startDate,int hours){
		if(startDate==null){
			startDate=new Date();
		}
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hours, 0,0);		
		return calendar.getTime();
	}
	
 /**
  * 今天就是显示时间
  * 否则就是显示日期
 * @param date
 * @return
 */
public static String todayFormat(Date date){
	if(date==null){
		date=new Date();
	}
	Calendar calendar =Calendar.getInstance();
	calendar.set(Calendar.HOUR_OF_DAY, 0);
	calendar.set(Calendar.MINUTE, 0);
	calendar.set(Calendar.MILLISECOND, 0);
	if(date.after(calendar.getTime())){
		return formatDate(date,MINUTE_FORMAT);
	}else{
		return formatDate(date,DAY_FORMAT);
	}
 }

public static int getMaxDayOfMonth(Date date){
	DateTime dateTime=new DateTime(date.getTime());
	int month=dateTime.getMonthOfYear();
	org.joda.time.DateTime.Property monthProperty = dateTime.monthOfYear(); 
	
	if(month==2&&monthProperty.isLeap()){
		return 29;
	}
	if(month==2&&!monthProperty.isLeap()){
		return 28;
	}
	if(isBigMonth(month)){
		return 31;
	}
	return 30;
	
}

private static boolean isBigMonth(int bigMonth){
	for(int month:bigMonths){
		if(bigMonth==month){
			return true;
		}
	}
	return false;
}

public static void main(String[] args) {
	
//	DateTime dateTime=new DateTime(parseString("2015-2-28",DATE_FORMAT));
////	dateTime.plusMonths(1);
//	System.out.println(dateTime.monthOfYear().isLeap());
	Calendar c=Calendar.getInstance();
	c.set(Calendar.DAY_OF_MONTH, 30);
	System.out.println(c);
	c.add(Calendar.MONTH, 1);
	System.out.println(c);
//	System.out.println(DateUtils.addMonth(parseString("2015-4-30",DATE_FORMAT), 4));
//	System.out.println(String.format("%s %s:00", DateUtils.formatDate(new Date(), DATE_FORMAT),9));
}

}