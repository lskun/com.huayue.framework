package com.huayue.sms.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.smslib.InboundMessage;

import com.huayue.apply.smsservice.SmsCacheContainer;
import com.huayue.framework.smslib.InboundMessageActionListener;
import com.huayue.framework.util.PageInfo;
import com.huayue.framework.util.Utils;
import com.huayue.platform.entity.Message;
import com.huayue.sms.data.DBAccess;


public class SmsReceiveService extends DBAccess implements InboundMessageActionListener {
	
	private static final Logger log = Logger.getLogger(SmsReceiveService.class);
	
	private List<InboundMessage> imsgs ;
	
	private InboundMessage msg ;
	
	public SmsReceiveService(List<InboundMessage> list){
		this.imsgs = list;
	}
	
	public SmsReceiveService(InboundMessage msg){
		this.msg = msg;
	}
	
	public SmsReceiveService(){}
	
	public void saveMessages()throws Exception{
		PreparedStatement prod = null;
		
		try{
			openConnection();
			beginTransaction();
			String sql = "INSERT INTO SMS_RECEIVE_INFO (MOBILE_NUMBER,MOBILE_CONTENT,RECEIVE_TIME) VALUES (?,?,?)";
			prod = prepareStatement(sql);
			for(int i = 0,j = imsgs.size(); i < j ;i++){
				InboundMessage msg = imsgs.get(i);
				prod.setString(1,msg.getSmscNumber());
				prod.setString(2, msg.getText());
				prod.setLong(3, System.currentTimeMillis());
				
				prod.addBatch();
			}
			
			int[] temp = prod.executeBatch();
			if(temp.length != imsgs.size()) throw new Exception("Save to WEBDB Failed...");
			endTransaction(true);
		}catch(Exception ex){
			endTransaction(false);
			throw ex;
		}finally{
			closeConnection();
			closeStatement(null,prod);
		}
	}
	
	public void saveSimpleMessage()throws Exception{
		PreparedStatement prod = null;
		
		try{
			openConnection();
			beginTransaction();
			String sql = "INSERT INTO SMS_RECEIVE_INFO (MOBILE_NUMBER,MOBILE_CONTENT,RECEIVE_TIME,GATEWAY_ID) VALUES (?,?,?,?)";
			prod = prepareStatement(sql);
			prod.setString(1, msg.getOriginator());
			prod.setString(2, msg.getText());
			prod.setLong(3, msg.getDate().getTime());
			prod.setString(4, msg.getGatewayId());
			
			prod.executeUpdate();
			endTransaction(true);
		}catch(Exception ex){
			endTransaction(false);
			log.error(ex);
		}finally{
			closeConnection();
			closeStatement(null,prod);
		}
	}
	
	public List<Message> getMessages(String start_time,String end_time)throws Exception{

	List<Message> msgs = null;
	PreparedStatement prod = null;
	ResultSet rst = null;
	String sql = "SELECT MOBILE_NUMBER,MOBILE_CONTENT,RECEIVE_TIME FROM SMS_RECEIVE_INFO ";
	try{
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
	}finally{
		closeConnection();
		closeStatement(rst,prod);
	}
	return msgs;
	}
	
	/**
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageInfo listInboundMessages(int pageIndex,int pageSize) throws Exception{
		PageInfo pInfo = null;
		ResultSet rst = null;
		PreparedStatement prod = null;
		
		try{
			openConnection();
			prod = prepareStatement("SELECT COUNT(*) AS CC FROM SMS_RECEIVE_INFO ");
			rst = prod.executeQuery();
			if(!rst.next()) throw new Exception("无法获取记录总数..");
            pInfo = new PageInfo(pageIndex, rst.getInt("CC"), pageSize);
            rst.close();
			
            prod = prepareStatement(Utils.getPageMysql("ID,MOBILE_NUMBER,MOBILE_CONTENT,RECEIVE_TIME","SELECT * FROM SMS_RECEIVE_INFO ", pageIndex, pageSize));
			rst = prod.executeQuery();
			while(rst.next()){
				Message msg = new Message();
				msg.setId(rst.getInt("ID"));
				msg.setPhoneNumber(rst.getString("MOBILE_NUMBER"));
				msg.setContent(rst.getString("MOBILE_CONTENT"));
				msg.setReceive_time(rst.getLong("RECEIVE_TIME"));
				
				pInfo.add(msg);
			}
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
		return pInfo;
	}
	
	public Message showInboundMessage(int id) throws Exception{
		Message msg = null;
		PreparedStatement prod = null;
		ResultSet rst = null;
		try{
			openConnection();
			prod = prepareStatement("SELECT ID,MOBILE_NUMBER,MOBILE_CONTENT,RECEIVE_TIME FROM SMS_RECEIVE_INFO WHERE ID = ?");
			prod.setInt(1, id);
			rst = prod.executeQuery();
			
			if(!rst.next()) throw new Exception("没有该条记录...");
			msg = new Message();
			msg.setContent(rst.getString("MOBILE_CONTENT"));
			msg.setPhoneNumber(rst.getString("MOBILE_NUMBER"));
			msg.setReceive_time(rst.getLong("RECEIVE_TIME"));
			msg.setId(rst.getInt("ID"));
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
		return msg;
	}
	
	/**
	 * modem接收到消息时会自动执行此方法.
	 * @param msg 消息对象
	 */
	public void actionPerformedMsg(InboundMessage msg) {
		try{
			new SmsReceiveService(msg).saveSimpleMessage();
			SmsCacheContainer.pushToContainer(msg); //放入CacheContainer中
			log.info("发送者: " + msg.getOriginator() + " 内容: " + msg.getText() + " 时间: " + msg.getDate() + "gateWay :" + msg.getGatewayId());
		}catch(Exception ex){
			log.error(ex);
		}
	}
	
	public List<InboundMessage> getImsgs() {
		return imsgs;
	}

	public void setImsgs(List<InboundMessage> imsgs) {
		this.imsgs = imsgs;
	}

	public InboundMessage getMsg() {
		return msg;
	}

	public void setMsg(InboundMessage msg) {
		this.msg = msg;
	}
}
