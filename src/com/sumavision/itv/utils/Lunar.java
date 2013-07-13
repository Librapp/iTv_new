package com.sumavision.itv.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author 闫志丹
 * @createTime 2013-04-23
 * @description 日历类
 * @changeLog
 * 
 */
public class Lunar
{

	private static String[] days = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	
	public static String getDate(Date curDate)
	{

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		return formatter.format(curDate);
	}
	
	public static String getDay(Date curDate)
	{

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		return formatter.format(curDate);
	}

	public static String getTime(Date curDate)
	{

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		return formatter.format(curDate);
	}

	public static String getDayOfWeek(Date curDate)
	{

		Calendar c = Calendar.getInstance();
		c.setTime(curDate);
		return days[c.get(Calendar.DAY_OF_WEEK) - 1];
	}
	
	public static int getCurrentDuration()
	{
		return getDurationByTime(getCurrentTime());
	}
	
	public static String getCurrentDate()
	{
		return getDay(new Date(System.currentTimeMillis()));
	}
	
	public static String getCurrentTime()
	{
		return getTime(new Date(System.currentTimeMillis()));
	}

	public static String getTimeByDuration(int duration)
	{
		if(duration < 0 || duration > 48)
			return "";
		String hour = String.valueOf(duration * 30 / 60);
		hour = hour.length() == 1 ? "0" + hour : hour;
		String min = String.valueOf(duration * 30 % 60);
		min = min.length() == 1 ? "0" + min : min;
		return hour + ":" + min;
	}
	
	public static int getDurationByTime(String time)
	{
		String[] dates = time.split(":");
		if(dates == null || dates.length != 2)
			return -1;
		int hour = Integer.valueOf(dates[0]);
		int min = Integer.valueOf(dates[1]);
		int duration = 2 * hour + min / 30;
		return duration;
	}
	
	public static String getStartDate()
	{

		Date date = new Date(new Date().getTime() - 3600 * 1000 * 24 * 2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date) + " 00:00";
	}

	public static String getEndDate()
	{

		Date date = new Date(new Date().getTime() + 3600 * 1000 * 24);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date) + " 23:59";
	}

}
