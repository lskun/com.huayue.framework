package com.huayue.platform.entity;

public class Mobile {
	private String mobile ;
	private String type;
	
	public Mobile(){}
	
	public Mobile(String mobile,String type){
		this.mobile = mobile;
		this.type = type;
	}
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		return "\"" + "mobile" + "\"" + ":" + "\"" + mobile + "\"," + "\"" + "type" + "\"" + ":" + "\"" + type + "\"";
	}
}
