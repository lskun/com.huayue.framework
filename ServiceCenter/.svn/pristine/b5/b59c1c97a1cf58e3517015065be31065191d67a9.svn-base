package com.huayue.sms.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.huayue.framework.util.PageInfo;
import com.huayue.framework.util.Utils;
import com.huayue.platform.entity.Message;
import com.huayue.sms.data.DBAccess;
import com.huayue.sms.operator.Constants;


public class SmsSendService extends DBAccess{
	
	private static final Logger log = Logger.getLogger(SmsSendService.class);
	
	private Message msg;
		
	public SmsSendService(){}
	
	public SmsSendService(Message msg){
		this.msg = msg;
	}
	
	@SuppressWarnings("deprecation")
	public static void send(String mobile ,String content ,String key)throws Exception
	{
		String temp;
		
		temp = java.net.URLEncoder.encode(content);
		//String encrypt_Mobile = AES.encode(mobile, Constants.AES_KEY_CONTACT);
		//temp = sms.framework.util.Tool.loadUrl("http://b.52xyzt.com/servlet/SmsSender?mobile=" + mobile + "&content=" + temp + "&key=" + key, null, "UTF-8");
		temp = com.huayue.framework.util.Tool.loadUrl("http://b.52xyzt.com/servlet/SmsSender?mobile=" + mobile + "&content=" + temp + "&key=" + key, null, "UTF-8");
		if(!"OK".equals(temp.trim())) throw new Exception("发送短信失败 ：" + temp.trim());		
	}
	
	public void updateSmsRecord()throws Exception{
		PreparedStatement prod = null;
				
		try
		{
			openConnection();
			prod = prepareStatement("INSERT INTO SMS_SEND_INFO (MOBILE_NUMBER,MOBILE_CONTENT,SEND_STATE) VALUES (?,?,?)");
			prod.setString(1, msg.getPhoneNumber());
			prod.setString(2, msg.getContent());
			prod.setInt(3, msg.getSend_state());
			prod.executeUpdate();
			
		}
		catch(Exception ex)
		{
			log.error(ex);
			throw ex;
		}
		finally
		{
			closeConnection();
			closeStatement(null , prod);
		}
	}
	
	public void updateOutboundStatus(long id) throws Exception{
		PreparedStatement prod = null;
		
		try
		{
			openConnection();
			beginTransaction();
			prod = prepareStatement("UPDATE SMS_SEND_INFO SET SEND_STATE = ? ,SEND_TIME= ? WHERE ID = ?");
			prod.setInt(1, Constants.SEND_SUCCESS);
			prod.setLong(2, System.currentTimeMillis());
			prod.setLong(3, id);
			prod.executeUpdate();
			endTransaction(true);
		}
		catch(Exception ex)
		{
			endTransaction(false);
			log.error(ex);
			throw ex;
		}
		finally
		{
			closeConnection();
			closeStatement(null , prod);
		}
	}
	
	public List<Message> getMessages(String start_time,String end_time)throws Exception
	{
		List<Message> msgs = null;
		PreparedStatement prod = null;
		ResultSet rst = null;
		String sql = "SELECT MOBILE_NUMBER,MOBILE_CONTENT,RECEIVE_TIME FROM SMS_RECEIVE_INFO ";
		
		try
		{
			msgs = new ArrayList<Message>();
			if("".equals(start_time) && !"".equals(end_time)) sql += "WHERE RECEIVE_TIME <= " + end_time; 
			else if(!"".equals(start_time)&& "".equals(end_time)) sql += "WHERE RECEIVE_TIME >= " + start_time;
			else if(!"".equals(start_time) && !"".equals(end_time) && (Long.parseLong(start_time) < Long.parseLong(end_time))) sql += "WHERE RECEIVE_TIME BETWEEN " + start_time + " AND " + end_time ;
			openConnection();
			prod = prepareStatement(sql);
			rst = prod.executeQuery();
			while(rst.next()){
				msgs.add(new Message(rst.getString("MOBILE_NUMBER"),rst.getString("MOBILE_CONTENT"),rst.getLong("RECEIVE_TIME")));
			}
		}
		finally
		{
			closeConnection();
			closeStatement(rst,prod);
		}
		return msgs;
	}
	
	public PageInfo listOutboundMessages(int pageIndex,int pageSize) throws Exception{
		PageInfo pInfo = null;
		ResultSet rst = null;
		PreparedStatement prod = null;
		
		try
		{
			openConnection();
			prod = prepareStatement("SELECT COUNT(*) AS CC FROM SMS_SEND_INFO ");
			rst = prod.executeQuery();
			if(!rst.next()) throw new Exception("无法获取记录总数..");
            pInfo = new PageInfo(pageIndex, rst.getInt("CC"), pageSize);
            rst.close();
			
            prod = prepareStatement(Utils.getPageMysql("MOBILE_NUMBER,ID,MOBILE_CONTENT,SEND_TIME,SEND_STATE ","SELECT * FROM SMS_SEND_INFO ", pageIndex, pageSize));
			rst = prod.executeQuery();
			while(rst.next())
			{
				Message msg = new Message();
				msg.setId(rst.getInt("ID"));
				msg.setPhoneNumber(rst.getString("MOBILE_NUMBER"));
				msg.setContent(rst.getString("MOBILE_CONTENT"));
				msg.setSend_time(rst.getLong("SEND_TIME"));				
				msg.setSend_state(rst.getInt("SEND_STATE"));
				
				pInfo.add(msg);
			}
		}
		finally
		{
			closeConnection();
			closeStatement(rst,prod);
		}
		return pInfo;
	}
	
	public Message showOutboundMessage(int id)throws Exception{
		Message msg = null;
		PreparedStatement prod = null;
		ResultSet rst = null;
		
		try
		{
			openConnection();
			prod = prepareStatement("SELECT ID,MOBILE_NUMBER,MOBILE_CONTENT,SEND_TIME FROM SMS_SEND_INFO WHERE ID = ?");
			prod.setInt(1, id);
			rst = prod.executeQuery();
			if(!rst.next()) throw new Exception("没有该条记录...");
			msg = new Message();
			msg.setContent(rst.getString("MOBILE_CONTENT"));
			msg.setPhoneNumber(rst.getString("MOBILE_NUMBER"));
			msg.setSend_time(rst.getLong("SEND_TIME"));
			msg.setId(rst.getInt("ID"));
		}
		finally
		{
			closeConnection();
			closeStatement(rst,prod);
		}
		return msg;
	}
	
	public ArrayList<Message> getWaitingOutboundMessages()throws Exception{
		ArrayList<Message> outboundMessages = null;
		Message msg = null;
		PreparedStatement prod = null;
		ResultSet rst = null;
		
		try
		{
			openConnection();
			beginTransaction();
			prod = prepareStatement("SELECT MOBILE_NUMBER,ID,MOBILE_CONTENT FROM SMS_SEND_INFO WHERE SEND_STATE = " + Constants.SEND_WAIT + " ORDER BY SEND_TIME ASC");
			rst = prod.executeQuery();
			
			outboundMessages = new ArrayList<Message>();
			
			while(rst.next())
			{
				msg = new Message();
				msg.setId(rst.getInt("ID"));
				msg.setPhoneNumber(rst.getString("MOBILE_NUMBER"));
				msg.setContent(rst.getString("MOBILE_CONTENT"));
				
				outboundMessages.add(msg);
			}
			if(outboundMessages.size() == 0) return outboundMessages;
			
			prod = prepareStatement("UPDATE SMS_SEND_INFO SET SEND_STATE = " + Constants.SEND_EXCUTE + " WHERE SEND_STATE = " + Constants.SEND_WAIT);
			prod.executeUpdate();
			endTransaction(true);
		}
		catch(Exception ex)
		{
			endTransaction(false);
			log.error(ex);
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
		return outboundMessages ;
	}
	
	public static String getStateText(int tag) throws Exception{
		switch(tag)
		{
			case Constants.SEND_SUCCESS :
				return "<span style=\"color: green\">成功</span>";
			case Constants.SEND_EXCUTE :
				return "<span style=\"color: gray\">处理中</span>";
			case Constants.SEND_WAIT :
				return "<span style=\"color: blue\">等待发送</span>";
			case Constants.SEND_FAILED :
				return "<span style=\"color: red\">失败</span>";
		}
		return null;
	}
}
