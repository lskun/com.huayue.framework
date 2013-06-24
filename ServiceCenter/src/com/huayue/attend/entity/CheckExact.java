package com.huayue.attend.entity;

public class CheckExact {
	
	private int exactId;		//修改日志ID
	
	private int userId;			//员工ID号
	
	private String checkTime;	//签到/签退时间
	
	private String checkType;	//原来的签到/签退标志：I-签到，O-签退
	
	private int isAdd;			//是否新增的记录
	
	private String reason;		//修改考勤记录的原因
		
	private int isModify;		//是否仅仅是修改原始记录
	
	private int isDelete;		//是否是删除的记录
	
	private int inCount;		//是否恢复
	
	private String modifyBy;	//操作员
	
	private int checkId;
	
	private int status;
	
	private String date;		//操作时间
	
	public CheckExact(){}

	public int getExactId() {
		return exactId;
	}

	public void setExactId(int exactId) {
		this.exactId = exactId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public int getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(int isAdd) {
		this.isAdd = isAdd;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getIsModify() {
		return isModify;
	}

	public void setIsModify(int isModify) {
		this.isModify = isModify;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public int getInCount() {
		return inCount;
	}

	public void setInCount(int inCount) {
		this.inCount = inCount;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCheckId() {
		return checkId;
	}

	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
