package com.huayue.apply;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.huayue.apply.domain.ComprehensData;
import com.huayue.framework.util.PageInfo;
import com.huayue.framework.util.Utils;


public class ExportController extends com.huayue.sms.data.DBAccess {
	
	public static final int DEFAULT_SELECT = -1;
	
	public static final String[] DEFAULT_INVOICE_CONTENT = {"培训费","会务费","资料费"}; 
	
	public static final String[] DEFAULT_UNIT_SURVEY = {"人民教育","中国教育新闻网","中国教育在线","中国教育报","信函","其他"};
	
	private static final Logger log = Logger.getLogger(ExportController.class);
	
	private ComprehensData comp ;
	
	public ExportController(ComprehensData comp){
		this.comp = comp;
	}
	
	public ExportController(){}
	
	public PageInfo queryForList(int isStay,int isTogether, String unit ,long startTime , long endTime ,int pageIndex ,int pageSize,String member,int category_id)throws Exception
	{
		PageInfo pInfo = null;
		String sql ;
		String countSql;
		PreparedStatement prod = null;
		ResultSet rst = null;
		StringBuilder factor = new StringBuilder();
		
		try{
			countSql = "SELECT COUNT(*) AS NUM FROM UNIT_MEMBER_INFO T1,WEB_PLATFORM_UNIT T2 WHERE T1.UNIT_ID = T2.ID ";
			sql = "SELECT T1.ID ,T1.UNIT_ID ,T1.NAME ,T1.GENDER ,T1.POSITION ,T1.NATION ,T1.PHONE_NUMBER ,T1.MOBILE ,T1.EMAIL ,T2.IS_STAY ,T2.IS_TOGETHER ,T2.UNIT ,T2.APPLY_TIME FROM UNIT_MEMBER_INFO T1,WEB_PLATFORM_UNIT T2 WHERE T1.UNIT_ID = T2.ID AND T1.IS_DELETED = 0 AND T2.IS_DELETED = 0";
			
			if( isStay != DEFAULT_SELECT ) 				factor.append(" AND T2.IS_STAY = " + isStay);
			if( isTogether != DEFAULT_SELECT) 			factor.append(" AND T2.IS_TOGETHER = " + isTogether);
			if(!"".equals(unit) && unit != null) 		factor.append(" AND T2.UNIT LIKE '%" + unit + "%'");
			if(startTime > 0) 							factor.append(" AND T2.APPLY_TIME >= " + startTime);
			if(endTime > 0) 							factor.append(" AND T2.APPLY_TIME < " + endTime);
			if(!"".equals(member) && member != null) 	factor.append(" AND T1.NAME LIKE '%" + member + "%'");
			if(category_id > 0)							factor.append(" AND T2.CATEGORY_ID = " + category_id);
			//factor += " ORDER BY T2.APPLY_TIME DESC";
			
			openConnection();	
			prod = prepareStatement(countSql + factor.toString());
			rst = prod.executeQuery();
			if(!rst.next()) throw new Exception("查询失败...");
			pInfo = new PageInfo(pageIndex, rst.getInt("NUM"), pageSize);
			closeStatement(rst, null);
			
			prod = prepareStatement(Utils.getPageMysql("*", sql + factor.toString(), pageIndex, pageSize));
			rst = prod.executeQuery();
		
			while(rst.next())
			{
				ComprehensData per = new ComprehensData();
				per.setId(rst.getInt("ID"));
				per.setUnit_id(rst.getInt("UNIT_ID"));
				per.setName(rst.getString("NAME"));
				per.setGender(rst.getInt("GENDER"));
				per.setPosition(rst.getString("POSITION"));
				per.setNation(rst.getString("NATION"));
				per.setPhoneNumber(rst.getString("PHONE_NUMBER"));
				per.setMobile(rst.getLong("MOBILE"));
				per.setEmail(rst.getString("EMAIL"));
				per.setIsStay(rst.getInt("IS_STAY"));
				per.setIsTogether(rst.getInt("IS_TOGETHER"));
				per.setUnit_name(rst.getString("UNIT"));
				per.setApplyTime(rst.getLong("APPLY_TIME"));
				
				pInfo.add(per);
			}
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
		
		return pInfo;
	}
	
	public ArrayList<ComprehensData> queryToExport(int is_stay ,int is_together ,String unit ,long start_time ,long end_time ,int standard,int category_id)throws Exception{
		ArrayList<ComprehensData> datas = new ArrayList<ComprehensData>();
		ResultSet rst = null;
		PreparedStatement prod = null;
		
		int tmp;
		int index = 0;
		
		StringBuilder sql = new StringBuilder();
		try{
			sql.append("SELECT T1.NAME ,T1.GENDER ,T1.POSITION ,T1.NATION ,T1.PHONE_NUMBER ,T1.MOBILE ,T1.EMAIL ,T2.ROOM_NUM ,T2.UNIT_SURVEY ,T2.INVOICE_REMARK ,T2.POST_CODE , T2.INVOICE_ADDRESS ,T2.UNIT_ADDRESS, T2.IS_STAY ,T2.IS_TOGETHER ,T2.UNIT ,T2.CHARGE_STANDARD ,T2.ID FROM UNIT_MEMBER_INFO T1,WEB_PLATFORM_UNIT T2 WHERE T1.UNIT_ID = T2.ID AND T1.IS_DELETED = 0 AND T2.IS_DELETED = 0 AND T1.IS_DELETED = 0");
			if( is_stay != DEFAULT_SELECT ) 		sql.append(" AND T2.IS_STAY = " + is_stay);
			if( is_together != DEFAULT_SELECT) 		sql.append(" AND T2.IS_TOGETHER = " + is_together);
			if(!"".equals(unit) && unit != null) 	sql.append(" AND T2.UNIT = '" + unit + "'");
			if(start_time > 0) 						sql.append(" AND T2.APPLY_TIME >= " + start_time);
			if(end_time > 0) 						sql.append(" AND T2.APPLY_TIME < " + end_time);
			if(standard > 0) 						sql.append(" AND T2.CHARGE_STANDARD = " + standard);
			if(category_id > 0)						sql.append(" AND T2.CATEGORY_ID = " + category_id);
			sql.append(" ORDER BY T2.APPLY_TIME ASC");
			openConnection();
			prod = prepareStatement(sql.toString());
			rst = prod.executeQuery();
			rst.next();
			tmp = rst.getInt(18);
			rst.beforeFirst();
			while(rst.next()){
				ComprehensData cpre = new ComprehensData();
				cpre.setName(rst.getString(1));
				cpre.setGender(rst.getInt(2));
				cpre.setPosition(rst.getString(3));
				cpre.setNation(rst.getString(4));
				cpre.setPhoneNumber(rst.getString(5));
				cpre.setMobile(rst.getLong(6));
				cpre.setEmail(rst.getString(7));
				
				if(tmp == rst.getInt(18)){
					index ++ ;
					if(index == 1) {cpre.setRonnNum(rst.getInt(8));}
					else cpre.setRonnNum(0);
				}else if(tmp != rst.getInt(18)){
					cpre.setRonnNum(rst.getInt(8));
					index = 1;
					tmp = rst.getInt(18);
				}

				cpre.setUnitSurvey(rst.getString(9));
				cpre.setInvoiceRemark(rst.getString(10));
				cpre.setPost_code(rst.getString(11));
				cpre.setInvoiceAddr(rst.getString(12));
				cpre.setUnitAddr(rst.getString(13));
				cpre.setIsStay(rst.getInt(14));
				cpre.setIsTogether(rst.getInt(15));
				cpre.setUnit_name(rst.getString(16));
				cpre.setCharge_standard(rst.getDouble(17));
				datas.add(cpre);
			}
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
		return datas;
	}
	
	public static String changeArr(String arr ,final String[] templete){
		if(arr == null || "".equals(arr)) return null;
		String result = "";
		arr = arr.replaceAll("[\\[\\]]", "");
		String[] array = arr.split(",");
		for(String temp : array){
			if(temp.trim().matches("[1-9]")){
			result += templete[Integer.parseInt(temp.trim())-1] + "、";
			}
		}
		return result;
	}
	
	public ComprehensData getMemberMsgById(int id)throws Exception{
		ComprehensData cpre = null;
		PreparedStatement prod = null;
		ResultSet rst = null;
		
		try{
			openConnection();
			prod = prepareStatement("SELECT T1.NAME ,T1.GENDER ,T1.POSITION ,T1.NATION ,T1.PHONE_NUMBER ,T1.MOBILE ,T1.EMAIL ,T2.ROOM_NUM ,T2.UNIT_SURVEY ,T2.INVOICE_REMARK ,T2.POST_CODE ,T2.INVOICE_CONTENT, T2.INVOICE_ADDRESS ,T2.UNIT_ADDRESS, T2.IS_STAY ,T2.IS_TOGETHER ,T2.UNIT FROM UNIT_MEMBER_INFO T1,WEB_PLATFORM_UNIT T2 WHERE T1.UNIT_ID = T2.ID AND T1.ID = ?");
			prod.setInt(1, id);
			rst = prod.executeQuery();
			
			cpre = new ComprehensData();
			while(rst.next()){
				cpre.setName(rst.getString(1));
				cpre.setGender(rst.getInt(2));
				cpre.setPosition(rst.getString(3));
				cpre.setNation(rst.getString(4));
				cpre.setPhoneNumber(rst.getString(5));
				cpre.setMobile(rst.getLong(6));
				cpre.setEmail(rst.getString(7));
				cpre.setRonnNum(rst.getInt(8));
				cpre.setUnitSurvey(rst.getString(9));
				cpre.setInvoiceRemark(rst.getString(10));
				cpre.setPost_code(rst.getString(11));
				cpre.setInvoiceContent(rst.getString(12));
				cpre.setInvoiceAddr(rst.getString(13));
				cpre.setUnitAddr(rst.getString(14));
				cpre.setIsStay(rst.getInt(15));
				cpre.setIsTogether(rst.getInt(16));
				cpre.setUnit_name(rst.getString(17));
			}
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
		return cpre;
	}
	
	public void saveEditedProject(ComprehensData comp)throws Exception{
		PreparedStatement prod = null;
		try{
			openConnection();
			beginTransaction();
			
			prod = prepareStatement("UPDATE UNIT_MEMBER_INFO SET NAME = ?,GENDER = ?,POSITION = ?,NATION = ?,PHONE_NUMBER = ?,MOBILE = ?,EMAIL = ? WHERE ID = ?");
			prod.setString(1, comp.getName());
			prod.setInt(2, comp.getGender());
			prod.setString(3, comp.getPosition());
			prod.setString(4, comp.getNation());
			prod.setString(5, comp.getPhoneNumber());
			prod.setLong(6, comp.getMobile());
			prod.setString(7, comp.getEmail());
			prod.setInt(8, comp.getId());
			
			if(prod.executeUpdate()!= 1) throw new Exception("数据更新失败...");
			
			prod = prepareStatement("UPDATE WEB_PLATFORM_UNIT SET IS_STAY = ? ,IS_TOGETHER = ? ,UNIT = ? ,ROOM_NUM = ? ,INVOICE_REMARK = ? ,POST_CODE = ? ,INVOICE_CONTENT = ?,INVOICE_ADDRESS = ?,UNIT_ADDRESS = ? WHERE ID = ?");
			prod.setInt(1, comp.getIsStay());
			prod.setInt(2, comp.getIsTogether());
			prod.setString(3, comp.getUnit_name());
			prod.setInt(4,comp.getRonnNum());
			prod.setString(5, comp.getInvoiceRemark());
			prod.setString(6, comp.getPost_code());
			prod.setString(7, comp.getInvoiceContent());
			prod.setString(8, comp.getInvoiceAddr());
			prod.setString(9, comp.getUnitAddr());
			prod.setInt(10, comp.getUnit_id());
			
			prod.executeUpdate();
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
	
	public void addUnitMember() throws Exception{
		PreparedStatement prod = null;
		
		try{
			openConnection();
			beginTransaction();
			
			prod = prepareStatement("INSERT INTO UNIT_MEMBER_INFO (NAME,GENDER,POSITION,NATION,PHONE_NUMBER,MOBILE,EMAIL,UNIT_ID)VALUES(?,?,?,?,?,?,?,?)");
			prod.setString(1, comp.getName());
			prod.setInt(2, comp.getGender());
			prod.setString(3, comp.getPosition());
			prod.setString(4, comp.getNation());
			prod.setString(5, comp.getPhoneNumber());
			prod.setLong(6, comp.getMobile());
			prod.setString(7, comp.getEmail());
			prod.setInt(8, comp.getUnit_id());
			
			prod.executeUpdate();
			endTransaction(true);
		}catch(Exception ex){
			endTransaction(false);
			log.error(ex);
			throw ex;
		}finally{
			closeConnection();
			closeStatement(null,prod);
		}
	}
	
	public void delUnit(int id)throws Exception{
		PreparedStatement prod = null;
		
		try{
			openConnection();
			beginTransaction();
			
			prod = prepareStatement("UPDATE WEB_PLATFORM_UNIT SET IS_DELETED = 1 WHERE ID = ?");
			prod.setInt(1, id);
			prod.executeUpdate();
			endTransaction(true);
		}catch(Exception ex){
			endTransaction(false);
			log.error(ex);
			throw ex;
		}finally{
			closeConnection();
			closeStatement(null,prod);
		}
	}
	
	public void delUnitMember(String[] tmp) throws Exception{
		PreparedStatement prod = null;
		
		try{
			openConnection();
			beginTransaction();
			
			prod = prepareStatement("UPDATE UNIT_MEMBER_INFO SET IS_DELETED = 1 WHERE ID = ?");
			for(String id : tmp){
				prod.setInt(1, Integer.parseInt(id));
				prod.addBatch();
			}
			prod.executeBatch();
			endTransaction(true);
		}catch(Exception ex){
			endTransaction(false);
			log.error(ex);
			throw ex;
		}finally{
			closeConnection();
			closeStatement(null,prod);
		}
	}
	
	public ComprehensData getComp() {
		return comp;
	}

	public void setComp(ComprehensData comp) {
		this.comp = comp;
	}
}