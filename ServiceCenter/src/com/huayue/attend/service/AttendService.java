package com.huayue.attend.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayue.attend.dao.UserAttendDao;
import com.huayue.attend.entity.AnalysisAttendData;
import com.huayue.attend.entity.Attendance;
import com.huayue.attend.entity.UserInfo;

@Service("attendService")
public class AttendService {
	
	@Autowired
	UserAttendDao userAttendDao;
	
	public List<UserInfo> showAllUserInfo()
	{
		return userAttendDao.showAll();
	}
	
	public UserInfo getMessageById(final int id){
		return userAttendDao.getMessageById(id);
	}
	
	public void updateUserInfo(UserInfo user){
		userAttendDao.update(user);
	}
	
	public List<Attendance> showUserAttendData(int id,String startTime,String endTime)throws Exception{
		return userAttendDao.showUserAttendData(id, startTime, endTime);
	}
	
	public LinkedHashMap<String, Attendance> calculateAttendData(int id ,String startTime,String endTime) throws Exception{
		return userAttendDao.calculateAttendData(id, startTime, endTime);
	}
	
	public Map<Integer,String> getUserMap(){
		return userAttendDao.getUserMap();
	}
	
	public Map<Integer,String> getDepartmentMap(){
		return userAttendDao.getDepartmentMap();
	}
	
	public Map<Integer,String> getLeaveTypeMap(){
		return userAttendDao.getLeaveTypeMap();
	}
	
	public Map<Integer,Integer> getDeptMap(){
		return userAttendDao.getDeptMap();
	}
	
	public Map<Integer,Integer> getUserDefaultDeptMap(){
		return userAttendDao.getUserDefaultDeptMap();
	}
	
	public AnalysisAttendData calculateAndAnalysisAttendData(String monthDate,int userId) throws Exception{
		return userAttendDao.calculateAndAnalysisAttendData(userId, monthDate);
	}
	
	public List<AnalysisAttendData> calculateAll(String monthDate,Integer[] ids) throws Exception{
		List<AnalysisAttendData> list = new ArrayList<AnalysisAttendData>(ids.length);
		for(int i = 0 ;i< ids.length; i++ ){
			AnalysisAttendData aad = userAttendDao.calculateAndAnalysisAttendData(ids[i], monthDate);
			if(aad != null && !"".equals(aad.getDeptName()))
				list.add(aad);
		}
		return list;
	}
	
	public Map<Integer,String> getSymbolMap(){
		return userAttendDao.getSymbolMap();		
	}
}	
