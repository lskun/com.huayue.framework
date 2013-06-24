package com.huayue.attend.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.RowCallbackHandler;


import com.huayue.attend.dao.*;
import com.huayue.attend.entity.AnalysisAttendData;
import com.huayue.attend.entity.Attendance;
import com.huayue.attend.entity.Status;
import com.huayue.attend.entity.UserInfo;
import com.huayue.attend.util.AttendUtil;
import com.huayue.framework.util.DateUtil;

import javax.annotation.Resource;

@Repository
public class UserAttendDaoImpl implements UserAttendDao{
	
	private static Logger log = Logger.getLogger(UserAttendDaoImpl.class);
	
	private static final int IS_NEGLECT = -1;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Resource(name="dataSource1")
	public void setDatasource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Transactional
	public void update(final UserInfo user){
		
		String sql = "UPDATE USERINFO SET BADGERNUMBER = ? ,NAME = ?,GENDER = ?,VERIFICATIONMETHOD = ?,DEFAULTDEPTID = ?, SECURITYFLAGS = ?,ATT = ?,INLATE = ?,OUTEARLY = ? ,OVERTIME = ?,HOLIDAY = ? WHERE USERID = ?";
		
		jdbcTemplate.update(sql, new PreparedStatementSetter(){

			public void setValues(PreparedStatement rs) throws SQLException {
				// TODO Auto-generated method stub
				rs.setString(1,user.getBadgeNumber());
				rs.setString(2, user.getName());
				rs.setString(3, user.getGender());
				rs.setInt(4, user.getVerificationMethod());
				rs.setInt(5, user.getDefaultDeptid());
				rs.setInt(6, user.getSecurityFlags());
				rs.setInt(7, user.getAtt());
				rs.setInt(8, user.getInLate());
				rs.setInt(9, user.getOutEarly());
				rs.setInt(10, user.getOverTime());
				rs.setInt(11, user.getHoliday());
				rs.setInt(12, user.getUserId());
			}
			
		});
	}
	
	public UserInfo getMessageById(final int id) {
		final UserInfo user = new UserInfo();
		String sql = "SELECT BADGERNUMBER ,NAME ,GENDER ,VERIFICATIONMETHOD ,DEFAULTDEPTID, SECURITYFLAGS ,ATT ,INLATE ,OUTEARLY ,OVERTIME ,HOLIDAY FROM USERINFO WHERE ID = ?";
		jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler(){

			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				user.setUserId(id);
				user.setBadgeNumber(rs.getString("BADGERNUMBER"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setVerificationMethod(rs.getInt("VERIFICATIONMETHOD"));
				user.setDefaultDeptid(rs.getInt("DEFAULTDEPTID"));
				user.setAtt(rs.getInt("ATT"));
				user.setInLate(rs.getInt("INLATE"));
				user.setOutEarly(rs.getInt("OUTEARLY"));
				user.setOverTime(rs.getInt("OVERTIME"));
				user.setHoliday(rs.getInt("HOLIDAY"));
			}			
		});
		return user;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<UserInfo> showAll() {
		List<UserInfo> list = null;
		
		String sql = "SELECT T1.USERID, T1.BADGENUMBER ,T1.NAME ,T1.GENDER ,T1.VERIFICATIONMETHOD ,T2.DEPTNAME, T1.SECURITYFLAGS ,T1.ATT ,T1.INLATE ,T1.OUTEARLY ,T1.OVERTIME ,T1.HOLIDAY FROM USERINFO T1,DEPARTMENTS T2 WHERE T1.DEFAULTDEPTID = T2.DEPTID";
		
		list = jdbcTemplate.query(sql, new RowMapper(){

			public Object mapRow(ResultSet rs, int i) throws SQLException {
				// TODO Auto-generated method stub
				UserInfo user = new UserInfo();
				
				user.setUserId(rs.getInt("USERID"));
				user.setBadgeNumber(rs.getString("BADGENUMBER"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setVerificationMethod(rs.getInt("VERIFICATIONMETHOD"));
				user.setDeptName(rs.getString("DEPTNAME"));
				user.setSecurityFlags(rs.getInt("SECURITYFLAGS"));
				user.setAtt(rs.getInt("ATT"));
				user.setInLate(rs.getInt("INLATE"));
				user.setOutEarly(rs.getInt("OUTEARLY"));
				user.setOverTime(rs.getInt("OVERTIME"));
				user.setHoliday(rs.getInt("HOLIDAY"));
				
				return user;
			}
			
		});
		
		return list;
	}	

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, Attendance> calculateAttendData(int id ,String startTime,String endTime) throws Exception{
		// TODO Auto-generated method stub
		String comeTime ,leaveTime ,deptName = "" ;
		
		String sql = "SELECT DISTINCT CONVERT(VARCHAR(100),T2.STARTTIME,8) ST,CONVERT(VARCHAR(100),T2.ENDTIME,8) ET FROM USER_OF_RUN T1 ,NUM_RUN_DEIL T2 WHERE T1.USERID = ? AND T1.NUM_OF_RUN_ID = T2.NUM_RUNID ";
		
		Map<String,Object> map = jdbcTemplate.queryForMap(sql, new Object[]{id});
		if(map.size() == 0 ) throw new Exception("NO MAPPING RESULT IS FOUND !");
		comeTime = (String)map.get("ST");
		leaveTime = (String)map.get("ET");
		
		sql = "SELECT CONVERT(VARCHAR(100),T1.CHECKTIME,120) CHECKTIME ,T2.DEPTNAME FROM CHECKINOUT T1 ,DEPARTMENTS T2 WHERE T1.USERID = ? AND ( T1.CHECKTIME > ? AND T1.CHECKTIME < ? ) AND T2.DEPTID IN (SELECT DEFAULTDEPTID FROM USERINFO WHERE USERID = ?) ORDER BY T1.CHECKTIME ASC";
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[]{id ,startTime ,endTime ,id}, new int[]{Types.INTEGER, Types.VARCHAR, Types.VARCHAR,Types.INTEGER});
				
		List<Map<String,Object>> extraList = jdbcTemplate.queryForList("SELECT CONVERT(VARCHAR(100),T1.CHECKTIME,120) CHECKTIME FROM CHECKEXACT_TMP T1 WHERE T1.USERID = ? AND T1.DATE > ? AND T1.DATE < ? AND T1.STATUS = 1", new Object[]{id,startTime,endTime});
		
		deptName = (String)list.get(0).get("DeptNAME");
		list.addAll(extraList);
		
		List<Map<String,Object>> tL = jdbcTemplate.queryForList("SELECT STARTTIME,ENDTIME,DATAID FROM USER_SPEDAY_TEMP WHERE USERID = ? AND STARTTIME > ? AND ENDTIME < ? AND STATUS = 1", new Object[]{id,startTime,endTime});
		List<Status> array = null;
		if(tL.size() != 0 ){
			array = new ArrayList<Status>();
			for(Map<String,Object> o : tL){
				ArrayList<Status> a = AttendUtil.excuteAttendLeaveForStatus(o.get("STARTTIME").toString().split(" ")[0],(String)o.get("ENDTIME").toString().split(" ")[0],(Short)o.get("DATAID"));
				array.addAll(a);
			}
		}
	
		String[] tmp ;
		
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		
		c1.setTime(format.parse(comeTime));
		c2.setTime(format.parse(leaveTime));
		c3.setTime(format.parse("12:00:00"));
		
		LinkedHashMap<String,ArrayList<String>> store = AttendUtil.excuteDate(startTime, endTime);
		
		for(int i = 0,j = list.size(); i < j;i ++){
			Map<String ,Object> o = list.get(i);
			
			//if(i == 0) deptName = (String)o.get("DEPTNAME");
			
			tmp = o.get("CHECKTIME").toString().split(" ");
					
			if(store.containsKey(tmp[0])){
				store.get(tmp[0]).add(tmp[1]);
			}
		}
		LinkedHashMap<String,Attendance> collection = new LinkedHashMap<String,Attendance>();
		
		for(Map.Entry<String, ArrayList<String>> entry : store.entrySet()){
			ArrayList<String> times = entry.getValue();
			Collections.sort(times);
			Calendar c = Calendar.getInstance();
			Calendar cx = Calendar.getInstance();
			
			Attendance instance = new Attendance();

			instance.setComeTime(comeTime);
			instance.setLeaveTime(leaveTime);
			instance.setUserId(id);
			instance.setDeptName(deptName);
			String dayForWeek = AttendUtil.dayForWeek(entry.getKey());
			instance.setDayForWeek(dayForWeek);
			
			//判断请假/公出情况
			if(array != null){
				for(Status status: array)
				if(status.getDateStr().equals(entry.getKey())){
					instance.setLeaveCondition(status.getDataId());
				}
			}
			if(times.size() == 0 ){
				if(!"星期天".equals(dayForWeek)&&!"星期六".equals(dayForWeek)){
					instance.setIsNeglectWork(IS_NEGLECT);
				}
			}
			else if(times.size() == 1){
			//一天只存在一次打卡情况	
				String time = times.get(0);
				c.setTime(format.parse(time));
				if(instance.getLeaveCondition()== 0)
					instance.setIsNeglectWork(IS_NEGLECT);
				
				if(c.compareTo(c1) <= 0 )  {
					
					instance.setCheckInTime(time);
										
				}else if(c.compareTo(c2) >= 0){
					instance.setCheckOutTime(time);
					
				}else if( c.compareTo(c1) > 0 && c.compareTo(c3) < 0 ){
					instance.setCheckInTime(time);
					instance.setLateTime((c.getTimeInMillis() - c1.getTimeInMillis())/(1000 * 60));
				}else if( c.compareTo(c3) > 0 && c.compareTo(c2) < 0 ){
					
					instance.setCheckOutTime(time);
					instance.setEarlyTime((c2.getTimeInMillis()- c.getTimeInMillis())/(1000 * 60));
				}
			}else if(times.size() == 2){
				//一天中存在2条打卡记录	
				String time0 = times.get(0);
				String time1 = times.get(1);
				
				
				//记录1
				c.setTime(format.parse(time0));
				//记录2
				cx.setTime(format.parse(time1));
				
				if(cx.compareTo(c3) < 0){
					
					instance.setIsNeglectWork(IS_NEGLECT);	//旷工
					if(c.compareTo(c1) > 0){
						instance.setLateTime((c2.getTimeInMillis()- c.getTimeInMillis())/(1000 * 60));
					}
				}
				else if(cx.compareTo(c3) > 0){
					if(c.compareTo(c3) > 0) instance.setIsNeglectWork(IS_NEGLECT); //旷工
					
					if(c.compareTo(c1) > 0 && c.compareTo(c3) < 0){
						instance.setLateTime((c.getTimeInMillis() - c1.getTimeInMillis())/(1000 * 60));
					}
					if(cx.compareTo(c2) < 0){
						instance.setEarlyTime((c2.getTimeInMillis()- cx.getTimeInMillis())/ (1000 * 60));
					}
					
				}
				instance.setCheckInTime(time0);
				instance.setCheckOutTime(time1);
				
			}else if(times.size() > 2){
				//一天中打卡记录超过2条
				String time0 = times.get(0);
				String time1 = times.get(times.size() - 1);
				
				c.setTime(format.parse(time0));
				//记录2
				cx.setTime(format.parse(time1));
				
				if(cx.compareTo(c3) < 0){
					instance.setIsNeglectWork(IS_NEGLECT);	//旷工
					if(c.compareTo(c1) > 0)
						instance.setLateTime((c2.getTimeInMillis()- cx.getTimeInMillis())/(1000 * 60));

				}else if(cx.compareTo(c3) > 0){
					if(c.compareTo(c3) > 0) instance.setIsNeglectWork(IS_NEGLECT); //旷工
					
					if(c.compareTo(c1) > 0 && c.compareTo(c3) < 0){
						instance.setLateTime((c.getTimeInMillis() - c1.getTimeInMillis())/(1000 * 60));
					}
					if(cx.compareTo(c2) < 0){
						instance.setEarlyTime((c2.getTimeInMillis()- cx.getTimeInMillis())/(1000 * 60));
					}
				}
				instance.setCheckInTime(time0);
				instance.setCheckOutTime(time1);
			}
			collection.put(entry.getKey(),instance );
		}	

		return collection;
	}
	
	public Map<String ,Object> queryToMap(String sql,Object[] objs){
	    try{    
            return jdbcTemplate.queryForMap(sql ,objs);    
        }catch (EmptyResultDataAccessException e) {    
            return null;    
        }    
	}
	
	/**
	 * 计算分析考勤数据结果
	 * @param userId
	 * @param monthDate 某年某月 (dateformat : yyyy-MM)
	 * @return
	 */
	public AnalysisAttendData calculateAndAnalysisAttendData(int id,String monthDate)throws Exception{
		AnalysisAttendData attendResult = null;
		String comeTime ,leaveTime ,deptName="";
		
		String startTime = DateUtil.getMonthFirstDay(monthDate) + " 00:00:00";
		String endTime = DateUtil.getMonthLastDay(monthDate) + " 23:59:00";
		
		String sql = "SELECT DISTINCT CONVERT(VARCHAR(100),T2.STARTTIME,8) ST,CONVERT(VARCHAR(100),T2.ENDTIME,8) ET FROM USER_OF_RUN T1 ,NUM_RUN_DEIL T2 WHERE T1.USERID = ? AND T1.NUM_OF_RUN_ID = T2.NUM_RUNID ";
		
		Map<String,Object> map = queryToMap(sql, new Object[]{id});
		if(map == null) return attendResult;
		comeTime = (String)map.get("ST");
		leaveTime = (String)map.get("ET");
		
		sql = "SELECT CONVERT(VARCHAR(100),T1.CHECKTIME,120) CHECKTIME ,T2.DEPTNAME FROM CHECKINOUT T1 ,DEPARTMENTS T2 WHERE T1.USERID = ? AND ( T1.CHECKTIME > ? AND T1.CHECKTIME < ? ) AND T2.DEPTID IN (SELECT DEFAULTDEPTID FROM USERINFO WHERE USERID = ?) ORDER BY T1.CHECKTIME ASC";
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[]{id ,startTime ,endTime ,id}, new int[]{Types.INTEGER, Types.VARCHAR, Types.VARCHAR,Types.INTEGER});
				
		List<Map<String,Object>> extraList = jdbcTemplate.queryForList("SELECT CONVERT(VARCHAR(100),T1.CHECKTIME,120) CHECKTIME FROM CHECKEXACT_TMP T1 WHERE T1.USERID = ? AND T1.DATE > ? AND T1.DATE < ? AND T1.STATUS = 1", new Object[]{id,startTime,endTime});
		
		if(list.size() != 0) deptName = (String)list.get(0).get("DEPTNAME");
		attendResult = new AnalysisAttendData();
		attendResult.setDeptName(deptName);
		attendResult.setUserId(id);
		list.addAll(extraList);
		
		List<Map<String,Object>> tL = jdbcTemplate.queryForList("SELECT STARTTIME,ENDTIME,DATAID,TOTALTIME,OUTTIME FROM USER_SPEDAY_TEMP WHERE USERID = ? AND STARTTIME > ? AND ENDTIME < ? AND STATUS = 1", new Object[]{id,startTime,endTime});
		List<Status> array = null;
		if(tL.size() != 0 ){
			array = new ArrayList<Status>();
			for(Map<String,Object> o : tL){
				if(o.get("TOTALTIME") != null){
					attendResult.setOverTime(
							attendResult.getOverTime() + (Double)o.get("TOTALTIME"));
				}
				if(o.get("OUTTIME") != null){
					attendResult.setLeaveWork(
							attendResult.getLeaveWork() + (Double)o.get("OUTTIME"));
				}
				ArrayList<Status> a = AttendUtil.excuteAttendLeaveForStatus(o.get("STARTTIME").toString().split(" ")[0],(String)o.get("ENDTIME").toString().split(" ")[0],(Short)o.get("DATAID"));
				array.addAll(a);
			}
		}	
		String[] tmp ;
		
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		
		c1.setTime(format.parse(comeTime));
		c2.setTime(format.parse(leaveTime));
		c3.setTime(format.parse("12:00:00"));
		
		LinkedHashMap<String,ArrayList<String>> store = AttendUtil.excuteDate(startTime, endTime);
		
		for(int i = 0,j = list.size(); i < j;i ++){
			Map<String ,Object> o = list.get(i);			
			//if(i == 0) deptName = (String)o.get("DEPTNAME");			
			tmp = o.get("CHECKTIME").toString().split(" ");
					
			if(store.containsKey(tmp[0])){
				store.get(tmp[0]).add(tmp[1]);
			}
		}
		
		List<Integer> conditions = new ArrayList<Integer>(store.size());
		for(int i = 0;i < store.size();i++ ){
			conditions.add(0);
		}
		attendResult.setConditions(conditions);
		attendResult.setShouldWorkDay(store.size());
		
		int index = 0;
		for(Map.Entry<String, ArrayList<String>> entry : store.entrySet()){
			ArrayList<String> times = entry.getValue();
			Collections.sort(times);
			Calendar c = Calendar.getInstance();
			Calendar cx = Calendar.getInstance();
			
			String dayForWeek = AttendUtil.dayForWeek(entry.getKey());
			
			//如果该天无打卡记录,且不为周末,则视为旷工
			if(times.size() == 0 ){
				if(!"星期天".equals(dayForWeek)&&!"星期六".equals(dayForWeek)){
					attendResult.getConditions().set(index, IS_NEGLECT);
					//neglectWorkDay ++;
					//instance.setIsNeglectWork(IS_NEGLECT);
				}
			}
			else if(times.size() == 1){
			//一天只存在一次打卡情况	
				String time = times.get(0);
				c.setTime(format.parse(time));
				//if(instance.getLeaveCondition()== 0)
				//	instance.setIsNeglectWork(IS_NEGLECT);
				attendResult.getConditions().set(index, IS_NEGLECT);
				if( c.compareTo(c1) > 0 && c.compareTo(c3) < 0 ){
					attendResult.setTotalLateTime(
							attendResult.getTotalLateTime() + (c.getTimeInMillis() - c1.getTimeInMillis())/(1000 * 60));
					//instance.setLateTime((c.getTimeInMillis() - c1.getTimeInMillis())/(1000 * 60));
				}else if( c.compareTo(c3) > 0 && c.compareTo(c2) < 0 ){					
					//instance.setEarlyTime((c2.getTimeInMillis()- c.getTimeInMillis())/(1000 * 60));
					attendResult.setTotalEarlyTime(
							attendResult.getTotalEarlyTime() + (c2.getTimeInMillis()- c.getTimeInMillis())/(1000 * 60));
				}
			}else if(times.size() == 2){
				//一天中存在2条打卡记录	
				String time0 = times.get(0);
				String time1 = times.get(1);		
				//记录1
				c.setTime(format.parse(time0));
				//记录2
				cx.setTime(format.parse(time1));
				
				if(cx.compareTo(c3) < 0){
					attendResult.getConditions().set(index, IS_NEGLECT);
					//instance.setIsNeglectWork(IS_NEGLECT);	//旷工
					if(c.compareTo(c1) > 0){
						//instance.setLateTime((c2.getTimeInMillis()- c.getTimeInMillis())/(1000 * 60));
						attendResult.setTotalLateTime(
								attendResult.getTotalLateTime() + (c2.getTimeInMillis()- c.getTimeInMillis())/(1000 * 60));
					}
				}
				else if(cx.compareTo(c3) > 0){
					if(c.compareTo(c3) > 0){ //instance.setIsNeglectWork(IS_NEGLECT); //旷工
						attendResult.getConditions().set(index, IS_NEGLECT);
					}
					if(c.compareTo(c1) > 0 && c.compareTo(c3) < 0){
						attendResult.setTotalLateTime(
								attendResult.getTotalLateTime() + (c.getTimeInMillis() - c1.getTimeInMillis())/(1000 * 60));
						//instance.setLateTime((c.getTimeInMillis() - c1.getTimeInMillis())/(1000 * 60));
					}
					if(cx.compareTo(c2) < 0){
						//instance.setEarlyTime((c2.getTimeInMillis()- cx.getTimeInMillis())/ (1000 * 60));
						attendResult.setTotalEarlyTime(
								attendResult.getTotalEarlyTime() + (c2.getTimeInMillis()- cx.getTimeInMillis())/ (1000 * 60));
					}
					
				}
				
			}else if(times.size() > 2){
				//一天中打卡记录超过2条
				String time0 = times.get(0);
				String time1 = times.get(times.size() - 1);
				
				c.setTime(format.parse(time0));
				//记录2
				cx.setTime(format.parse(time1));
				
				if(cx.compareTo(c3) < 0){
					//instance.setIsNeglectWork(IS_NEGLECT);	//旷工
					attendResult.getConditions().set(index, IS_NEGLECT);
					if(c.compareTo(c1) > 0){
						attendResult.setTotalLateTime(
								attendResult.getTotalLateTime() + (c2.getTimeInMillis()- cx.getTimeInMillis())/(1000 * 60));
					}

				}else if(cx.compareTo(c3) > 0){
					if(c.compareTo(c3) > 0){
						attendResult.getConditions().set(index, IS_NEGLECT);	
					}
					if(c.compareTo(c1) > 0 && c.compareTo(c3) < 0){
						attendResult.setTotalLateTime(
								attendResult.getTotalLateTime() + (c.getTimeInMillis() - c1.getTimeInMillis())/(1000 * 60));
					}
					if(cx.compareTo(c2) < 0){
						attendResult.setTotalEarlyTime(
								attendResult.getTotalEarlyTime() + (c2.getTimeInMillis()- cx.getTimeInMillis())/(1000 * 60));
					}
				}
			}
			
			if(array != null){
				for(Status status: array){
					if(status.getDateStr().equals(entry.getKey())){
						//赋值例外情况
						attendResult.getConditions().set(index, status.getDataId());
					}
				}					
			}
			
			if("星期天".equals(dayForWeek) || "星期六".equals(dayForWeek)){
				attendResult.setShouldWorkDay(attendResult.getShouldWorkDay() - 1);
				attendResult.getConditions().set(index, 0);
			}
			index ++ ;
		}	
		return attendResult;
	}
	
	public static void main(String[] args) throws ParseException {
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2012-03-01");
        startCalendar.setTime(startDate);
        Date endDate = df.parse("2012-04-05");
        endCalendar.setTime(endDate);
        while(true){
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
            if(startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()){//TODO 转数组或是集合
            System.out.println(df.format(startCalendar.getTime()));
        }else{
            break;
        }
        }
}

	public List<Attendance> showUserAttendData(int id, String startTime,String endTime) throws Exception 
	{
		// TODO Auto-generated method stub
		String sql = "SELECT CONVERT(VARCHAR(100),T1.CHECKTIME,120) CHECK_TIME ,T2.DEFAULTDEPTID ,T1.SENSORID ,T1.VERIFYCODE FROM CHECKINOUT T1 ,USERINFO T2 WHERE T1.USERID = ? AND ( T1.CHECKTIME > ? AND T1.CHECKTIME < ? ) AND T1.USERID = T2.USERID ORDER BY T1.CHECKTIME ASC";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, new Object[]{id,startTime,endTime}, new int[]{Types.INTEGER,Types.VARCHAR,Types.VARCHAR});
		
		List<Attendance> attendDatas = new ArrayList<Attendance>(list.size());

		for(int i = 0 ,j = list.size();i < j;i++ ){
			Map<String,Object> map = list.get(i);
			
			Attendance instance = new Attendance();
			instance.setUserId(id);
			instance.setDeptId(Short.valueOf(map.get("DEFAULTDEPTID").toString()));
			instance.setCheckTime(map.get("CHECK_TIME").toString());
	
			instance.setSensorId(map.get("SENSORID") == null ? "" : map.get("SENSORID").toString());
			instance.setVerifyCode((Integer)map.get("VERIFYCODE"));
			
			attendDatas.add(instance);
		}
		return attendDatas;
	}

	public Map<Integer, String> getUserMap() {
		// TODO Auto-generated method stub
		List list = jdbcTemplate.queryForList("SELECT USERID ,NAME FROM USERINFO WHERE ATT = 1");
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(int i=0,j = list.size();i < j;i ++ ){
			Map o = (Map)list.get(i);

			map.put((Integer)o.get("USERID"), (String)o.get("NAME"));
		}
		return map;
	}

	public Map<Integer, String> getDepartmentMap() {
		// TODO Auto-generated method stub
		List list = jdbcTemplate.queryForList("SELECT DEPTID ,DEPTNAME FROM DEPARTMENTS ");
				//"WHERE DEPTID IN (SELECT DISTINCT SUPDEPTID FROM DEPARTMENTS)");
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		for(int i=0,j = list.size();i < j;i ++){
			Map o = (Map)list.get(i);
			map.put((Integer)o.get("DEPTID"), (String)o.get("DEPTNAME"));
		}
		return map;
	}

	public Map<Integer, String> getLeaveTypeMap() {
		// TODO Auto-generated method stub
		List list = jdbcTemplate.queryForList("SELECT LEAVEID ,LEAVENAME FROM LEAVECLASS1 "); 
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		for(int i=0,j = list.size(); i < j ; i++ ){
			Map o = (Map)list.get(i);
			map.put((Integer)o.get("LEAVEID"),(String)o.get("LEAVENAME"));
		}
		map.put(-1, "旷工");
		map.put(0, "");
		map.put(1, "病假");
		map.put(2, "事假");
		map.put(3, "探亲假");
		map.put(4, "带薪年假");
		return map;
	}
	
	public Map<Integer,String> getSymbolMap(){
		List list = jdbcTemplate.queryForList("SELECT LEAVEID ,REPORTSYMBOL FROM LEAVECLASS1 "); 
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		for(int i=0,j = list.size(); i < j ; i++ ){
			Map o = (Map)list.get(i);
			map.put((Integer)o.get("LEAVEID"),(String)o.get("REPORTSYMBOL"));
		}
		map.put(-1, "旷");
		map.put(0, "");
		map.put(1, "B");
		map.put(2, "S");
		map.put(3, "T");
		map.put(4, "-");
		return map;
	}
	
	public Map<Integer, Integer> getDeptMap() {
		// TODO Auto-generated method stub
		List list = jdbcTemplate.queryForList("SELECT DEPTID , SUPDEPTID FROM DEPARTMENTS");
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		for(int i = 0,j = list.size(); i < j ;i++ ){
			Map o = (Map)list.get(i);
			map.put((Integer)o.get("DEPTID"), (Integer)o.get("SUPDEPTID"));
		}
		return map;
	}
	
	public Map<Integer,Integer> getUserDefaultDeptMap(){
		List list = jdbcTemplate.queryForList("SELECT USERID ,DEFAULTDEPTID FROM USERINFO");
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		for(int i = 0,j = list.size(); i < j ;i++ ){
			Map o = (Map)list.get(i);
			map.put((Integer)o.get("USERID"), (Integer)o.get("DEFAULTDEPTID"));
		}
		return map;
	}

}
