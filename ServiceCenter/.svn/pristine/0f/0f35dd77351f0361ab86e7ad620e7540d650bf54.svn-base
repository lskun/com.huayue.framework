package com.huayue.sms.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.huayue.platform.entity.SenderRecord;
import com.huayue.sms.data.DBAccess;


public class MultiSender extends DBAccess{
	
	private static final Logger log = Logger.getLogger(MultiSender.class);
	
	public long initializingRecord(String[] arr,String content)throws Exception
    {
    	PreparedStatement prod = null;
    	ResultSet rst = null;
    	long id ;
    	
    	try
    	{
    		openConnection();
    		beginTransaction();
    		prod = generateStatement("INSERT INTO HY_MULTI_SMS (BEGIN_TIME,WHOLE_COUNT,CONTENT,MOBILES) VALUES (?,?,?,?)"); 
    		prod.setLong(1, System.currentTimeMillis());
    		prod.setLong(2, arr.length);
    		prod.setString(3, content);
    		prod.setString(4, Arrays.toString(arr));
    		
    		prod.executeUpdate();
    		rst = prod.getGeneratedKeys();
    		rst.next();
    		id = rst.getInt(1);
    		
    		endTransaction(true);
    		
    	}
    	catch(Exception ex)
    	{
    		log.error(ex);
    		endTransaction(false);
    		throw ex;
    	}
    	finally
    	{
    		closeConnection();
    		closeStatement(rst,prod);
    	}
    	return id;
    }
	
	public void updateRecord(long id)throws Exception
    {
    	PreparedStatement prod = null;
    	
    	try
    	{
    		openConnection();
    		beginTransaction();
    		prod = prepareStatement("update hy_multi_sms set success_count = success_count + 1 where id = ?");    
    		prod.setLong(1, id);
    		prod.executeUpdate();
    		endTransaction(true);
    	}
    	catch(Exception ex){
    		endTransaction(false);
    		throw ex;
    	}
    	finally
    	{
    		closeConnection();
    		closeStatement(null,prod);
    	}
    }
	
	public String[] getUnSendRecord(long id )throws Exception
    {
    	String[] nums = null;
    	PreparedStatement prod = null;
    	ResultSet rst = null;
    	
    	try
    	{
    		openConnection();
    		prod = prepareStatement("select success_count ,whole_count ,mobiles from hy_multi_sms where id = ?");
    		prod.setLong(1, id);
    		rst = prod.executeQuery();
    		if(!rst.next()) throw new Exception("无此记录!");
    		int count = rst.getInt("success_count");
    		int whole = rst.getInt("whole_count");
    		String str = rst.getString("mobiles");
    		String tmp[] = str.replaceAll("\\[|\\]", "").split(",");
    		nums = new String[whole - count];
    		for(int i = count ,j = 0; i < whole  ;i++ ,j++ )
    		{
    			nums[j] = tmp[i];
    		}
    	}   	
    	finally
    	{
    		closeStatement(rst,prod);
    		closeConnection();
    	}   	
    	return nums;
    }
	
	public void endRecord(long id) throws Exception
    {
    	PreparedStatement prod = null;
    	
    	try
    	{
    		openConnection();
    		beginTransaction();
    		prod = prepareStatement("update hy_multi_sms set success_count = success_count + 1 ,end_time = ? ,mobiles = ? where id = ?");    
    		prod.setLong(1,System.currentTimeMillis());
    		prod.setString(2,null);
    		prod.setLong(3, id);
    		prod.executeUpdate();
    		endTransaction(true);
    	}
    	catch(Exception ex){
    		endTransaction(false);
    		throw ex;
    	}
    	finally
    	{
    		closeConnection();
    		closeStatement(null,prod);
    	}
    }
	
    public List<SenderRecord> listSendRecord() throws Exception
    {
    	List<SenderRecord> list = new ArrayList<SenderRecord>();
    	PreparedStatement prod = null;
    	ResultSet rst = null;
    	try
    	{
    		openConnection();
    		prod = prepareStatement("select id, begin_time ,success_count ,whole_count,content from hy_multi_sms where success_count < whole_count order by begin_time desc");
    		rst = prod.executeQuery();
    		
    		while(rst.next())
    		{
    			String content = rst.getString("content");
    			
    			list.add(
    						new SenderRecord
    						(
    							rst.getLong("success_count"),rst.getLong("whole_count"),rst.getLong("id"),rst.getLong("begin_time"),content.substring(0, content.length() < 20 ? content.length(): 20 )
    						)
    					);
    		}
    	}
    	finally
    	{
    		closeStatement(rst,prod);
    		closeConnection();
    	}
    	return list;
    }
    
    public String importData(String unit,int is_stay)throws Exception{
    	StringBuffer shtml = new StringBuffer();
    	String factor = "";
    	PreparedStatement prod = null;
    	ResultSet rst = null;
    	
    	try
    	{
    		if(!"".equals(unit)) factor += " AND T2.UNIT = " + unit;
    		if(is_stay != 0) factor += " AND T2.IS_STAY = " + is_stay;
    		
    		openConnection();
    		prod = prepareStatement("SELECT T1.MOBILE FROM UNIT_MEMBER_INFO T1,WEB_PLATFORM_UNIT T2 WHERE T1.UNIT_ID = T2.ID" + factor);
    		rst = prod.executeQuery();
    		while(rst.next()){
    			if(rst.getLong("MOBILE") == 0) continue;
    			shtml.append(rst.getLong("MOBILE"));
    			shtml.append("\r\n");
    		}
    	}
    	finally
    	{
    		closeConnection();
    		closeStatement(rst,prod);
    	}
    	return shtml.toString();
    }
    
}
