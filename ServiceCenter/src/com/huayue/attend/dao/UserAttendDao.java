package com.huayue.attend.dao;

import java.util.*;

import com.huayue.attend.entity.AnalysisAttendData;
import com.huayue.attend.entity.Attendance;
import com.huayue.attend.entity.UserInfo;

public interface UserAttendDao {
	
	public void update(final UserInfo user);
	
	public List<UserInfo> showAll();
	
	public UserInfo getMessageById(int id);
	
	public LinkedHashMap<String,Attendance> calculateAttendData(int id,String startTime,String endTime)throws Exception;
	
	public List<Attendance> showUserAttendData(int id, String startTime,String endTime)throws Exception;
	
	public Map<Integer,String> getUserMap();
	
	public Map<Integer,String> getDepartmentMap();
	
	public Map<Integer,String> getLeaveTypeMap();
	
	public Map<Integer,Integer> getDeptMap();
	
	public Map<Integer,Integer> getUserDefaultDeptMap();
	
	public AnalysisAttendData calculateAndAnalysisAttendData(int id,String monthDate)throws Exception;
	
	public Map<Integer,String> getSymbolMap();
}
