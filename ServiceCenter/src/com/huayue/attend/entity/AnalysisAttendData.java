/**
 * 
 */
package com.huayue.attend.entity;

import java.util.Collections;
import java.util.List;

/**
 * @author lsk0414
 * 
 * 考勤结果统计领域类
 */
public class AnalysisAttendData {
	
	//员工ID
	private int userId;
	
	//部门名称
	private String deptName;
	
	//早退总时间
	private long totalEarlyTime;
	
	//迟到总时间
	private long totalLateTime;
	
	//旷工工作日
	private long neglectWorkDay;
	
	//实际工作日
	private long actualWorkDay;
	
	//应到工作日
	private long shouldWorkDay;
	
	//加班总计
	private double overTime;
	
	//请假总计
	private double leaveWork;
	
	//例外情况
	private List<Integer> conditions ;
	
	public AnalysisAttendData(){}

	/**
	 * @return the totalEarlyTime
	 */
	public long getTotalEarlyTime() {
		return totalEarlyTime;
	}

	/**
	 * @param totalEarlyTime the totalEarlyTime to set
	 */
	public void setTotalEarlyTime(long totalEarlyTime) {
		this.totalEarlyTime = totalEarlyTime;
	}

	/**
	 * @return the totalLateTime
	 */
	public long getTotalLateTime() {
		return totalLateTime;
	}

	/**
	 * @param totalLateTime the totalLateTime to set
	 */
	public void setTotalLateTime(long totalLateTime) {
		this.totalLateTime = totalLateTime;
	}

	/**
	 * @return the neglectWorkDay
	 */
	public long getNeglectWorkDay() {
		return neglectWorkDay;
	}

	/**
	 * @param neglectWorkDay the neglectWorkDay to set
	 */
	public void setNeglectWorkDay(long neglectWorkDay) {
		this.neglectWorkDay = neglectWorkDay;
	}

	/**
	 * @return the actualWorkDay
	 */
	public long getActualWorkDay() {
		return actualWorkDay;
	}

	/**
	 * @param actualWorkDay the actualWorkDay to set
	 */
	public void setActualWorkDay(long actualWorkDay) {
		this.actualWorkDay = actualWorkDay;
	}

	/**
	 * @return the shouldWorkDay
	 */
	public long getShouldWorkDay() {
		return shouldWorkDay;
	}

	/**
	 * @param shouldWorkDay the shouldWorkDay to set
	 */
	public void setShouldWorkDay(long shouldWorkDay) {
		this.shouldWorkDay = shouldWorkDay;
	}

	/**
	 * @return the overTime
	 */
	public double getOverTime() {
		return overTime;
	}

	/**
	 * @param overTime the overTime to set
	 */
	public void setOverTime(double overTime) {
		this.overTime = overTime;
	}

	/**
	 * @return the leaveWork
	 */
	public double getLeaveWork() {
		return leaveWork;
	}

	/**
	 * @param leaveWork the leaveWork to set
	 */
	public void setLeaveWork(double leaveWork) {
		this.leaveWork = leaveWork;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the conditions
	 */
	public List<Integer> getConditions() {
		return conditions;
	}

	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(List<Integer> conditions) {
		this.conditions = conditions;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
}
