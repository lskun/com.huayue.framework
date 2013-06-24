package com.huayue.attend.entity;

public class UserSpeday {
	
	private int id;
	
	private int userId;				//员工ID号
	
	private String startTime;		//开始日期
	
	private String endTime;			//结束日期
	
	private int dataId;				//--例外类型，999为公出，-1为注释
	
	private String reason;			//--例外的原因
	
	private double totalTime;
	
	private double outTime;
	
	private String date;			//--登记/输入记录的时间
	
	private int checkerId;			//审核人ID	
	
	private int status;				//审核状态
	
	public UserSpeday(){}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(int checkerId) {
		this.checkerId = checkerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * @return the outTime
	 */
	public double getOutTime() {
		return outTime;
	}

	/**
	 * @param outTime the outTime to set
	 */
	public void setOutTime(double outTime) {
		this.outTime = outTime;
	}
	
	
}
