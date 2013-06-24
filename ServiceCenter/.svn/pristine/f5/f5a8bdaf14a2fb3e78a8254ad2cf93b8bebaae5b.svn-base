package com.huayue.apply.smsservice;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.apache.log4j.Logger;

import com.huayue.platform.entity.Message;
import com.huayue.platform.entity.Mobile;
import com.huayue.sms.data.DBAccess;
import com.huayue.sms.operator.Constants;


public class InterativeService extends DBAccess{
	
	private static Logger log = Logger.getLogger(InterativeService.class);
	public static Map<String, String> map = new HashMap<String, String>();
	public static final String COM_MAPPING_PATH = "/com_mapping.properties";
	
	/**
	 * 初始化串口对应号码映射关系Map
	 */
	static{
		
		Properties properties = new Properties();
		InputStream in = InterativeService.class.getResourceAsStream(COM_MAPPING_PATH);
		try {
			properties.load(in);
			map.put(properties.getProperty("number_one"), properties.getProperty("com_one"));
			map.put(properties.getProperty("number_two"), properties.getProperty("com_two"));
			map.put(properties.getProperty("number_three"), properties.getProperty("com_three"));
			map.put(properties.getProperty("number_four"), properties.getProperty("com_four"));
			map.put(properties.getProperty("number_five"), properties.getProperty("com_five"));
			map.put(properties.getProperty("number_six"), properties.getProperty("com_six"));
			
			//map.put("18201147465", "modem.com3");
			//map.put("18201143943", "modem.com4");
			//map.put("18201147443", "modem.com5");
			//map.put("13126896739", "modem.com6");
			//map.put("15510262091", "modem.com8"); ---空号
			//map.put("18601931356", "modem.com9");	---空号
		} catch (IOException e) {
			log.info(" ======= no: /com_mapping.properties ============ ");
			log.error(e);
		}			

	}
	
	public ArrayList<Message> listReceiveMsgs(String[] mobiles)throws Exception{
		ArrayList<Message> msgs = null;
		ResultSet rst = null;
		PreparedStatement prod = null;
		String mobile;
		String sql = "";
		try{
			if(mobiles.length > 0){
				sql += "AND GATEWAY_ID IN (";
				for(int i = 0; i < mobiles.length; i++ )
				{
					if(!map.containsKey(mobiles[i])) throw new Exception(Constants.MOBILE_NO_EXIST);
					if(i == (mobiles.length - 1)) sql += "'"  + map.get(mobiles[i]) +  "'";
					else sql += "'"  + map.get(mobiles[i]) +  "',";
				}
				sql += ")";
			}
			
			openConnection();
			prod = prepareStatement("SELECT ID ,MOBILE_NUMBER ,MOBILE_CONTENT,RECEIVE_TIME FROM SMS_RECEIVE_INFO WHERE IS_DELETED = 0 " + sql + " ORDER BY ID DESC");
			
			rst = prod.executeQuery();
			msgs = new ArrayList<Message>();
			while(rst.next()){
				mobile = rst.getString("MOBILE_NUMBER");
				if(!mobile.startsWith("86")) continue;
				msgs.add(new Message(rst.getInt("ID"),packagingNum(mobile),rst.getString("MOBILE_CONTENT"),rst.getLong("RECEIVE_TIME")));
			}
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
		return msgs;
	}
	
	/**
	 * 获取绑定号码上的短信记录
	 * @param mobiles  	绑定的手机号码
	 * @param id		短信记录Id
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Message> listReceiveMsgsById(String[] mobiles,int id)throws Exception{
		ArrayList<Message> msgs = null;
		ResultSet rst = null;
		PreparedStatement prod = null;
		String mobile;
		String sql = "";
		
		try{
			if(mobiles.length > 0){
				sql += "AND GATEWAY_ID IN (";
				for(int i = 0; i < mobiles.length; i++ )
				{
					if(!map.containsKey(mobiles[i])) throw new Exception(Constants.MOBILE_NO_EXIST);
					if(i == (mobiles.length - 1)) sql += "'"  + map.get(mobiles[i]) +  "'";
					else sql += "'"  + map.get(mobiles[i]) +  "',";
				}
				sql += ")";
			}
			sql += "AND ID > " + id;
			
			openConnection();
			prod = prepareStatement("SELECT ID ,MOBILE_NUMBER ,MOBILE_CONTENT,RECEIVE_TIME FROM SMS_RECEIVE_INFO WHERE IS_DELETED = 0 " + sql + " ORDER BY ID DESC");
			
			rst = prod.executeQuery();
			msgs = new ArrayList<Message>();
			while(rst.next()){
				mobile = rst.getString("MOBILE_NUMBER");
				if(!mobile.startsWith("86")) continue;
				msgs.add(new Message(rst.getInt("ID"),packagingNum(mobile),rst.getString("MOBILE_CONTENT"),rst.getLong("RECEIVE_TIME")));
			}
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
		return msgs;
		
	}
	
	public void refleshStatus(String[] mobiles)throws Exception{
		PreparedStatement prod = null;
		
		String sql = "";
		
		try{
			openConnection();
			beginTransaction();
			if(mobiles.length > 0){
				sql += "AND GATEWAY_ID IN (";
				for(int i = 0; i< mobiles.length; i++){
					if(!map.containsKey(mobiles[i])) throw new Exception(Constants.MOBILE_NO_EXIST);
					if(i == (mobiles.length - 1)) sql += "'" + map.get(mobiles[i]) + "'";
					else sql += "'" + map.get(mobiles[i]) + "',";
				}
				sql += ")";
			}else throw new Exception(Constants.MOBILES_LENGTH_ERROR);
			
			prod = prepareStatement("UPDATE SMS_RECEIVE_INFO SET IS_DELETED = 1 WHERE IS_DELETED = 0 " + sql);
			prod.executeUpdate();
			prod = prepareStatement("UPDATE WEB_PLATFORM_MOBILE_MAPPING SET IS_SELECTED = 0 WHERE MOBILE = ?");
		
			for(int i = 0; i < mobiles.length ; i++){
				prod.setString(1, mobiles[i].trim());
				prod.addBatch();
			}
			prod.executeBatch();
			endTransaction(true);
		}catch(Exception ex){
			log.error(ex);
			endTransaction(false);
			throw ex;
		}finally{
			closeConnection();
			closeStatement(null,prod);
		}
	}
	
	public void removeBinder(String[] mobiles)throws Exception{
		PreparedStatement prod = null;
		try{
			openConnection();
			beginTransaction();
			prod = prepareStatement("UPDATE WEB_PLATFORM_MOBILE_MAPPING SET IS_SELECTED = 0 WHERE MOBILE = ?");
			
			for(int i = 0; i < mobiles.length ; i++){
				prod.setString(1, mobiles[i].trim());
				prod.addBatch();
			}
			prod.executeBatch();
			endTransaction(true);
		}catch(Exception ex){
			log.error(ex);
			endTransaction(false);
			throw ex;
		}finally{
			closeConnection();
			closeStatement(null,prod);
		}
	}
	
	public void markStatus(String[] mobiles)throws Exception{
		PreparedStatement prod = null;
		
		String sql = "";
		try{
			openConnection();
			beginTransaction();
			if(mobiles.length > 0){
				sql += "AND GATEWAY_ID IN (";
				for(int i = 0; i< mobiles.length; i++){
					if(!map.containsKey(mobiles[i])) throw new Exception(Constants.MOBILE_NO_EXIST);
					if(i == (mobiles.length - 1)) sql += "'" + map.get(mobiles[i]) + "'";
					else sql += "'" + map.get(mobiles[i]) + "',";
				}
				sql += ")";
			}else throw new Exception(Constants.MOBILES_LENGTH_ERROR);
			prod = prepareStatement("UPDATE SMS_RECEIVE_INFO SET IS_DELETED = 1 WHERE IS_DELETED = 0 " + sql);
			prod.executeUpdate();
		}catch(Exception ex){
			log.error(ex);
			endTransaction(false);
			throw ex;
		}finally{
			closeConnection();
			closeStatement(null,prod);
		}
	}
	
	public void bindMobile(String mobile)throws Exception{
		PreparedStatement prod = null;
		ResultSet rst = null;
		
		mobile = mobile.trim();
		try{
			if(!map.containsKey(mobile)) throw new Exception(Constants.NO_MOBILE_ERROR);
			openConnection();
			beginTransaction();
			prod = prepareStatement("SELECT IS_SELECTED FROM WEB_PLATFORM_MOBILE_MAPPING WHERE MOBILE = ?");
			prod.setString(1, mobile);
			rst = prod.executeQuery();
			rst.next();
			if(0 != rst.getInt("IS_SELECTED")) {
				throw new Exception(Constants.MOBILE_SELECTED);
			}
			else{				
				prod = prepareStatement("UPDATE WEB_PLATFORM_MOBILE_MAPPING SET IS_SELECTED = 1 WHERE MOBILE = ?");
				prod.setString(1, mobile);
				prod.executeUpdate();
				endTransaction(true);
			}
		}catch(Exception ex){
			//ex.printStackTrace();
			endTransaction(false);			
			log.error(ex);
			throw ex;
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
	}
	
	public ArrayList<Mobile> listUnSelectMobiles()throws Exception{
		PreparedStatement prod = null;
		ResultSet rst = null;
		
		ArrayList<Mobile> mobiles = null;
		try{
			openConnection();
			prod = prepareStatement("SELECT MOBILE ,TYPE FROM WEB_PLATFORM_MOBILE_MAPPING WHERE IS_SELECTED = 0");
			rst = prod.executeQuery();
			mobiles = new ArrayList<Mobile>();
			while(rst.next()){
				mobiles.add(new Mobile(rst.getString("MOBILE"), typeToStr(rst.getInt("TYPE"))));
			}
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
		return mobiles;
	}
	
	public static String typeToStr(int type){
		if(type == 1) return "移动";
		if(type == 2) return "联通";
		return "未知";
	}
	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public static String packagingNum(String mobile){
		if(mobile.startsWith("86")){ 
			mobile = mobile.substring(2);
			mobile = mobile.replace(mobile.substring(3,7), "****");
		}else if(mobile.length() == 11){
			mobile = mobile.replace(mobile.substring(3,7), "****");
		}
		return mobile;
	}
	
	public static void main(String[] args)throws Exception{
		System.out.println(packagingNum("13845451212"));
	}
}
