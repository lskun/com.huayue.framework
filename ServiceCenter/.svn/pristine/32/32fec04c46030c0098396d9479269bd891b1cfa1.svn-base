package com.huayue.attend.dao;

import java.util.List;
import java.util.TreeMap;

import com.huayue.attend.entity.CheckExact;
import com.huayue.attend.entity.OverTimeInfo;
import com.huayue.attend.entity.UserSpeday;

public interface UserSpedayDao {
	
	public void add(UserSpeday speday);
	
	public List<UserSpeday> listExtraApply(int flag,int deptId,String sTime,String eTime);
	
	public void verifyForm(UserSpeday speday);
	
	public void vefifyAttendData(CheckExact cet);
	
	public List<CheckExact> listModifyDatas(int deptId,String sTime,String eTime,int status);
	
	public void checkForLeave(int id,int checkId) throws Exception;
	
	public void checkForExact(int id,int checkId);
	
	public void delLeaveReq(int id) throws Exception;
	
	public void delExactReq(int id) throws Exception;
	
	public List<OverTimeInfo> showOverTimeList(long userId,String sTime,String eTime) throws Exception;
	
	public TreeMap<Integer,Long> showTotalOverTimeList(String sTime,String eTime)throws Exception;
	
	public void addOverTimeApply(final UserSpeday speday);
}
