package com.huayue.crm.base.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.apache.log4j.Logger;

import com.huayue.sms.data.DBAccess;
import com.huayue.sms.data.DBManager;


public class ClientCategory extends DBAccess
{	
	public static Map<String,Integer> map = new HashMap<String,Integer>();
	
	public static Map<Integer,String> cmap = new HashMap<Integer,String>();
	
	public static final Logger log = Logger.getLogger(ClientCategory.class);
	
	static
	{
		Connection conn = null;
		PreparedStatement prod = null;
		ResultSet rst = null;
		
		try
		{
			conn = DBManager.getInstance().getConnection("idb");
			prod = conn.prepareStatement("SELECT ID,NAME FROM CRM_CLIENT_CATEGORY");
			rst = prod.executeQuery();
			
			while(rst.next()){
				map.put(rst.getString("NAME"),rst.getInt("ID"));
				cmap.put(rst.getInt("ID"), rst.getString("NAME"));
			}
		}
		catch(Exception ex)
		{
			log.error(ex);
		}
		finally
		{
			try { rst.close(); } catch (Exception e) { }
	        try { prod.close(); } catch (Exception e) { }
		}
	}
	
	public static void main(String[] args)throws Exception{
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
	}
}
