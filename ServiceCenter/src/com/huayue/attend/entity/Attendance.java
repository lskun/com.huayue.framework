package com.huayue.attend.entity;

public class Attendance {
	
	private int userId;				//用户ID
	
	private String name;			//用户姓名	
	
	private String rate;			//日期
	
	private String comeTime;		//上班时间
	
	private String leaveTime;		//下班时间
	
	private String checkInTime;		//签到时间
	
	private String checkOutTime;	//签退时间
	
	private long lateTime;			//迟早分钟数
	
	private long earlyTime;			//早退分钟数
	
	private String overTime;		//加班时间
	
	private int deptId;				//部门ID
	
	private int isNeglectWork;		//是否旷工
	
	private int leaveCondition;		//例外情况
	
	private String deptName ;		//部门名称
	
	private String checkTime;		//考勤时间（不分签到/签退）
	
	private String sensorId;		//机器编号
	
	private int verifyCode ;		//验证方式
	
	private String dayForWeek;
	
	public Attendance(){}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getComeTime() {
		return comeTime;
	}

	public void setComeTime(String comeTime) {
		this.comeTime = comeTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public long getLateTime() {
		return lateTime;
	}

	public void setLateTime(long lateTime) {
		this.lateTime = lateTime;
	}

	public long getEarlyTime() {
		return earlyTime;
	}

	public void setEarlyTime(long earlyTime) {
		this.earlyTime = earlyTime;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getIsNeglectWork() {
		return isNeglectWork;
	}

	public void setIsNeglectWork(int isNeglectWork) {
		this.isNeglectWork = isNeglectWork;
	}

	public int getLeaveCondition() {
		return leaveCondition;
	}

	public void setLeaveCondition(int leaveCondition) {
		this.leaveCondition = leaveCondition;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public int getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getDayForWeek() {
		return dayForWeek;
	}

	public void setDayForWeek(String dayForWeek) {
		this.dayForWeek = dayForWeek;
	}
	
	
}
