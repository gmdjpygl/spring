package com.dataManage.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class DateUtil {

	@Deprecated
	public static final Pattern patternDate = Pattern.compile("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$");

	@Deprecated
	public static final Pattern patternDateTime = Pattern
			.compile("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}$");

	@Deprecated
	public static final Pattern patternDateTimestamp = Pattern
			.compile("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}\\.[0-9]{1,3}$");

	@Deprecated
	public static final Pattern patternTimeLong = Pattern.compile("^[0-9]+$");
	public static final Pattern PATTERN_DATE = Pattern.compile("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$");
	public static final Pattern PATTERN_DATE_TIME = Pattern
			.compile("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}$");
	public static final Pattern PATTERN_DATE_TIMESTAMP = Pattern
			.compile("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}\\.[0-9]{1,3}$");
	public static final Pattern PATTERN_TIME_LONG = Pattern.compile("^[0-9]+$");
	public static final String DATE_AND_TIME_FORMATSTR = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_AND_TIME_WITHMS_FORMATSTR = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATE_FORMATSTR = "yyyy-MM-dd";

	public static String format() {
		return format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String format(String pattern) {
		return format(new Date(), pattern);
	}

	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String format(Date date, String pattern) {
		if (null == date) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
	}

	public static java.sql.Date toSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static Date parse(String str) {
		return parse(str, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date parse(String str, String pattern) {
		if (str != null && str.trim().length() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);

			try {
				return sdf.parse(str);
			} catch (ParseException var4) {
				var4.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public static Date getDate(Integer beforeSecond) {
		return getDate(new Date(), beforeSecond);
	}

	public static Date getDate(Date date, Integer beforeSecond) {
		Long time = date.getTime() - (long) (beforeSecond * 1000);
		Date result = new Date(time);
		result.setTime(time);
		return result;
	}

	public static Date getDate(Long beforeSecond) {
		return getDate(new Date(), beforeSecond);
	}

	public static Date getDate(Date date, Long beforeSecond) {
		Long time = date.getTime() - beforeSecond * 1000L;
		Date result = new Date(time);
		result.setTime(time);
		return result;
	}

	public static Date getYearFirst(Calendar calendar) {
		int year = calendar.get(1);
		calendar.clear();
		calendar.set(1, year);
		return calendar.getTime();
	}
	public static Date getYearFirst(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getYearFirst(calendar);
	}
	
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(1, year);
		return calendar.getTime();
	}
	/**
	 * 年最后时刻
	 * @param calendar
	 * @return
	 */
	public static Date getYearLast(Calendar calendar) {
		int year = calendar.get(1);
		calendar.clear();
		calendar.set(1, year+1);
		calendar.add(Calendar.SECOND, -1);
		return calendar.getTime();
	}
	/**
	 * 年最后时刻,
	 * @param calendar
	 * @return
	 */
	public static Date getYearLast(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getYearLast(calendar);
	}
	
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(1, year);
		return getYearLast(calendar);
	}
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	public static Date getMonthFirst(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getMonthFirst(calendar);
	}
	
	public static Date getMonthFirst(Calendar calendar) {
		int year = calendar.get(1);
		int month = calendar.get(Calendar.MONTH);
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		return calendar.getTime();
	}
	/**
	 * 月最后时刻,
	 * @param calendar
	 * @return
	 */
	public static Date getMonthLast(Calendar calendar) {
		int year = calendar.get(1);
		int month = calendar.get(Calendar.MONTH);
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month+1);
		calendar.add(Calendar.SECOND, -1);
		return calendar.getTime();
	}
	public static Date getMonthLast(Date  date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getMonthLast(calendar);
	}
	/**
	 *天开始时刻
	 * @param calendar
	 * @return
	 */
	public static Date getDayFirst(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return  getDayFirst(calendar);
	}
	/**
	 *天最后时刻
	 * @param calendar
	 * @return
	 */
	public static Date getDayFirst(Calendar calendar) {
		int year = calendar.get(1);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
	/**
	 *天最后时刻
	 * @param calendar
	 * @return
	 */
	public static Date getDayLast(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return  getDayLast(calendar);
	}
	/**
	 *天最后时刻
	 * @param calendar
	 * @return
	 */
	public static Date getDayLast(Calendar calendar) {
		int year = calendar.get(1);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day+1);
		calendar.add(Calendar.SECOND, -1);
		return calendar.getTime();
	}
	/**
	 * 增加日期    方法是基于calendar.add(field, amount); 
	 * @param field  基于Calendar.YEAR MONTH WEEK_OF_MONTH....
	 * @param amount 增加的数量(负数时减掉相应数量)
	 * @param date
	 * @return
	 */
	public static Date addDate(int field, int amount,Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}
	public static Date addDate(int field, int amount,Calendar calendar) {
		calendar.add(field, amount);
		return calendar.getTime();
	}
	/**
	 * 获取年份
	 * @param date
	 * @return
	 */
	public static int getDayOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}
	public DateUtil() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获取本周第一天
	 * @param date
	 * @return
	 */
	public static Date getWeekFirst() {
		Calendar curStartCal = Calendar.getInstance();
		Calendar cal = (Calendar) curStartCal.clone();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return getDayFirst(cal);
	}
	/**
	 * 获取本周最后一天
	 * @param date
	 * @return
	 */
	public static Date getWeekEnd() {
		Calendar curStartCal = Calendar.getInstance();
		Calendar cal = (Calendar) curStartCal.clone();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DATE, 6);
		return getDayLast(cal);
	}
	   
	public static List getDatesBetweenDate(Date beginDate, Date endDate) { 
		List lDate = new ArrayList(); 
		lDate.add(beginDate);// 把开始时间加入集合  
	    Calendar cal = Calendar.getInstance();  
		 // 使用给定的 Date 设置此 Calendar 的时间  
        cal.setTime(beginDate);  
        boolean bContinue = true;  
        while (bContinue) { 
        	 // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.DAY_OF_MONTH, 1);  
            // 测试此日期是否在指定日期之后  
            if (endDate.after(cal.getTime())) { 
            	 lDate.add(cal.getTime());  
            }else {
            	break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合  
		 return lDate; 
	}


}