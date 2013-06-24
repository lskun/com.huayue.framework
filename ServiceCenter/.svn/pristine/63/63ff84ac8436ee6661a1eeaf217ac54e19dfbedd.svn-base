package com.huayue.sms.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.huayue.platform.entity.User;
import com.huayue.sms.data.DBAccess;


public class UserService extends DBAccess{
	
	private static final Logger log = Logger.getLogger(UserService.class);
	
	public ArrayList<User> getUserMessages() throws Exception{
		PreparedStatement prod = null;
		ResultSet rst = null;
		ArrayList<User> list = new ArrayList<User>();
		
		try{
			openConnection();
			prod = prepareStatement("SELECT ID ,USER_NAME , PASSWORD ,DEPTID,PERMISSION FROM SMS_USER");
			rst = executeQuery(prod);
			while(rst.next()){
				list.add(new User(rst.getLong("ID"),rst.getString("USER_NAME"),rst.getString("PASSWORD"),rst.getInt("DEPTID"),rst.getInt("PERMISSION")));
			}
			
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
		return list;
	}
	
	public void updatePassword(String newpass,long id)throws Exception
	{
		PreparedStatement prod = null;
		
		try
		{
			openConnection();
			beginTransaction();
			prod = prepareStatement("UPDATE SMS_USER SET PASSWORD = ? WHERE ID = ?");
			prod.setString(1, newpass);
			prod.setLong(2, id);
			
			if(prod.executeUpdate() != 1) throw new Exception("update password failed");
			endTransaction(true);
		}
		catch(Exception ex){
			log.error(ex);
			endTransaction(false);
			throw ex;
		}finally{
			closeConnection();
			closeStatement(null,prod);
		}
	}
}
