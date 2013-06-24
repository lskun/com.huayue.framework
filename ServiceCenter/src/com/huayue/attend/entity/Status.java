/**
 * 
 */
package com.huayue.attend.entity;

/**
 * @author lsk0414
 *
 */
public class Status {
	
	private String dateStr;
	
	private int dataId;
	
	public Status(String str,int id){
		this.dateStr = str;
		this.dataId = id;
	}
	
	public Status(){}

	/**
	 * @return the dateStr
	 */
	public String getDateStr() {
		return dateStr;
	}

	/**
	 * @param dateStr the dateStr to set
	 */
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	/**
	 * @return the dataId
	 */
	public int getDataId() {
		return dataId;
	}

	/**
	 * @param dataId the dataId to set
	 */
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	
	
	
}
