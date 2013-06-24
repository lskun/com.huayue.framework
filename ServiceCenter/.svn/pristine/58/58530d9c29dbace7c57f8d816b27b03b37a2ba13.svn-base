package com.huayue.platform.entity;

import java.util.Date;

import com.huayue.framework.util.DateUtil;


public class Message {
	private String content;
	
	private String phoneNumber;
	
	private int id;
	
	private long send_time;
	
	private long user_id;
	
	private long receive_time;
	
	private int send_state;
	
	public Message(){}
	
	public Message(int id,String num,String content,long time){
		this.id = id;
		this.phoneNumber = num;
		this.content = content;
		this.receive_time = time;
	}
	
	public Message(String num,String content,long time){		
		this.phoneNumber = num;
		this.content = content;
		this.receive_time = time;
	}
	
	public long getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(long receive_time) {
		this.receive_time = receive_time;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getSend_time() {
		return send_time;
	}

	public void setSend_time(long send_time) {
		this.send_time = send_time;
	}
	
	public int getSend_state() {
		return send_state;
	}

	public void setSend_state(int sendState) {
		send_state = sendState;
	}
	
	public String getTimeText(){
		return DateUtil.dateToString(new Date(receive_time),DateUtil.FORMAT_ONE);
	}
	
	public String toString(){
		return "\"" + "id" + "\"" + ":" + "\"" + id + "\"," +  "\"" + "mobile" + "\"" + ":" + "\"" + phoneNumber + "\"," + "\"" + "content" + "\"" + ":" + "\"" + content.trim().replaceAll("[\r\n]", "<br/>").replaceAll("\\\\","\\\\\\\\").replaceAll("\"", "\\\\\"") + "\"," + "\"" + "time" + "\":" + "\"" + getTimeText() + "\"";		
	}
	
}
