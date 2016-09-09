package com.galaxy.star.newsbox.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {
	private static DateTools dateTools = null;
	private DateFormat dateFormat;
	
	private DateTools(){
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public static synchronized DateTools get(){
		if(dateTools==null){
			dateTools = new DateTools();
		}
		return dateTools;
	}
	
	/**
	 * 取得给定时间对应的字符串
	 * @return
	 */
	public String getFormatDateTime(Date date){
		return dateFormat.format(date);
	}
	
	/**
	 * 取得当前毫秒数
	 */
	public Long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 取得当前时间对应的字符串
	 * @return
	 */
	public String getCurrentDateTime(){
		return dateFormat.format(new Date());
	}
	
	/**
	 * 取得当前年份
	 * @return
	 */
	public int getCurrentYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	/**
	 * 取得当前月份
	 * @return
	 */
	public int getCurrentMonth(){
		return Calendar.getInstance().get(Calendar.MONTH)+1;
	}
	
	/**
	 * 取得当前日期
	 * @return
	 */
	public int getCurrentDay(){
		return Calendar.getInstance().get(Calendar.DATE);
	}
	
	/**
	 * 取得两位日期（如果日为单数则前面补0）
	 * @return
	 */
	public String getCurrentDay2Str(){
		int curDay = getCurrentDay();
		return curDay<10?("0"+curDay):(String.valueOf(curDay));
	}
	
	/**
	 * 取得两位月份（如果日为单数则前面补0）
	 * @return
	 */
	public String getCurrentMonth2Str(){
		int curMonth = getCurrentMonth();
		return curMonth<10?("0"+curMonth):(String.valueOf(curMonth));
	}
	
	/**
	 * 当前小时
	 * @return
	 */
	public String getCurrentHour(){
		int hour = Calendar.getInstance().get(Calendar.HOUR);
		return hour<10?("0"+hour):(String.valueOf(hour));
	}
	
	/**
	 * 当前分
	 * @return
	 */
	public String getCurrentMinute(){
		int minute = Calendar.getInstance().get(Calendar.MINUTE);
		return minute<10?("0"+minute):(String.valueOf(minute));
	}
	
	/**
	 * 当前秒
	 * @return
	 */
	public String getCurrentSecond(){
		int second = Calendar.getInstance().get(Calendar.SECOND);
		return second<10?("0"+second):(String.valueOf(second));
	}
	
	/**
	 * 取得当前时间
	 * @return
	 */
	public String getCurrentTime(){
		StringBuffer sb = new StringBuffer();
		sb.append(getCurrentHour()).append(":").append(getCurrentMinute()).append(":").append(getCurrentSecond());
		return sb.toString();
	}
	
    public Date parse(String dateStr) {
    	try{
    		return dateFormat.parse(dateStr);
    	}catch(Exception e){
    		return new Date();
    	}
    }
	
	
	
	
}