package com.huayue.attend.util;

import java.text.SimpleDateFormat;
import java.util.*;

import com.huayue.attend.entity.Status;

public class AttendUtil 
{
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static LinkedHashMap<String ,ArrayList<String>> excuteDate(String startTime,String endTime)throws Exception{
		
		LinkedHashMap<String,ArrayList<String>> map = new LinkedHashMap<String,ArrayList<String>>();
		Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse(startTime.split(" ")[0]);
        startCalendar.setTime(startDate);
        Date endDate = df.parse(endTime.split(" ")[0]);
        endCalendar.setTime(endDate);
        
        while(true){
            if(startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()){
            	//TODO 转集合
            	map.put(df.format(startCalendar.getTime()), new ArrayList<String>());
            	//System.out.println(df.format(startCalendar.getTime()));
            }else{
            	break;
            }
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);

        }
		return map;
	}
	
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<Status> excuteAttendLeaveForStatus(String startTime,String endTime,int dataId)throws Exception{
		ArrayList<Status> list = new ArrayList<Status>();
		Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse(startTime.split(" ")[0]);
        startCalendar.setTime(startDate);
        Date endDate = df.parse(endTime.split(" ")[0]);
        endCalendar.setTime(endDate);
        
        while(true){
            if(startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()){
            	//TODO 转集合
            	list.add(new Status(df.format(startCalendar.getTime()),dataId));
            	//System.out.println(df.format(startCalendar.getTime()));
            }else{
            	break;
            }
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
		return list;
	}
	
	public static ArrayList<String> excuteAttendLeave(String startTime,String endTime)throws Exception{
		ArrayList<String> list = new ArrayList<String>();
		Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse(startTime.split(" ")[0]);
        startCalendar.setTime(startDate);
        Date endDate = df.parse(endTime.split(" ")[0]);
        endCalendar.setTime(endDate);
        
        while(true){
            if(startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()){
            	//TODO 转集合
            	list.add(df.format(startCalendar.getTime()));
            	//System.out.println(df.format(startCalendar.getTime()));
            }else{
            	break;
            }
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);

        }
		return list;
	}
	
    /**
     * 
     * 判断当前日期是星期几<br>
     * <br>
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
	 public static String dayForWeek(String pTime) throws Exception 
	 {
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar c = Calendar.getInstance();
		  c.setTime(format.parse(pTime));
		  int dayForWeek = 0;
		  if(c.get(Calendar.DAY_OF_WEEK) == 1){
		   dayForWeek = 7;
		  }else{
		   dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		  }
		  
		  switch(dayForWeek){
		    
		    case 1:
		    	return "星期一";
		    case 2:
		    	return "星期二";
		    case 3:
		    	return "星期三";
		    case 4:
		    	return "星期四";
		    case 5:
		    	return "星期五";
		    case 6:
		    	return "星期六";
		    case 7:
		    	return "星期天";
		  }
		  return "";
		  //return dayForWeek;
	 }
	 
	 public static void main(String[] args)throws Exception{
		 Calendar startCalendar = Calendar.getInstance();
	        Calendar endCalendar = Calendar.getInstance();
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        Date startDate = df.parse("2012-12-21");
	        startCalendar.setTime(startDate);
	        Date endDate = df.parse("2012-12-22");
	        endCalendar.setTime(endDate);
	        while(true){
	            if(startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()){
	            	//TODO 转集合
	            	//map.put(df.format(startCalendar.getTime()), new ArrayList<String>());
	            	System.out.println(df.format(startCalendar.getTime()));
	            }else{
	            	break;
	            }
	            startCalendar.add(Calendar.DAY_OF_MONTH, 1);

	        }
	 }
}
