package com.huayue.apply;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.huayue.apply.domain.RegisterUnit;
import com.huayue.apply.domain.UnitMember;
import com.huayue.apply.util.ExceptionConstants;
import com.huayue.framework.util.Format;



public class InfoManager extends com.huayue.sms.data.DBAccess
{
	private static final Logger log = Logger.getLogger(InfoManager.class);
	
	/**
	 * 通过验证码获取在线报名信息
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public RegisterUnit getMessageByVerification(String code)throws Exception{
		
		RegisterUnit rUnit = null;
		PreparedStatement prod = null;
		ResultSet rst = null;
		String sql = "";
		List<UnitMember> members = null;
		UnitMember member = null;
		
		try
		{
			sql = "SELECT * FROM WEB_PLATFORM_UNIT WHERE SECURITY_CODE = ? AND IS_DELETED = 0";
			openConnection();
			prod = prepareStatement(sql);
			prod.setString(1, code);
			rst = prod.executeQuery();
			if(!rst.next()) throw new Exception(ExceptionConstants.NO_MAPPING_RESULT);
			rUnit = new RegisterUnit();
			rUnit.setId(rst.getInt("ID"));
			rUnit.setName(rst.getString("UNIT"));
			rUnit.setIsStay(rst.getInt("IS_STAY"));
			rUnit.setIsTogether(rst.getInt("IS_TOGETHER"));
			rUnit.setRonnNum(rst.getInt("ROOM_NUM"));
			rUnit.setInvoiceAddr(rst.getString("INVOICE_ADDRESS"));
			rUnit.setInvoiceRemark(rst.getString("INVOICE_REMARK"));
			rUnit.setUnitSurvey(rst.getString("UNIT_SURVEY"));
			rUnit.setContactWay(rst.getString("CONTACT_WAY"));
			rUnit.setPost_code(rst.getString("POST_CODE"));
			rUnit.setUnitAddr(rst.getString("UNIT_ADDRESS"));
			closeStatement(rst,null);
			sql = "SELECT * FROM UNIT_MEMBER_INFO WHERE UNIT_ID = ? AND IS_DELETED = 0";
			prod = prepareStatement(sql);
			prod.setInt(1, rUnit.getId());
			rst = prod.executeQuery();
			members = new ArrayList<UnitMember>();
			
			while(rst.next())
			{
				member = new UnitMember();
				
				member.setId(rst.getInt("ID"));
				member.setName(rst.getString("NAME"));
				member.setGender(rst.getInt("GENDER"));
				member.setMobile(rst.getLong("MOBILE"));
				member.setNation(rst.getString("NATION"));
				member.setPhoneNumber(rst.getString("PHONE_NUMBER"));
				member.setEmail(rst.getString("EMAIL"));
				member.setPosition(rst.getString("POSITION"));
				
				members.add(member);
			}
			
			rUnit.setMembers(members);
		}
		catch(Exception ex)
		{
			log.error(ex);
			throw ex;
		}
		finally
		{
			closeStatement(rst,prod);
			closeConnection();
		}
		return rUnit;
	}
	
	/**
	 * 保存修改的报名信息
	 * @param unitId     	报名单位Id
	 * @param unit			单位名称
	 * @param isStay		是否住宿
	 * @param isTogether	是否同意调剂
	 * @param roomNum		标间数
	 * @param columnList	成员信息
	 * @param invoiceRemark	备注
	 * @param invoiceAddr	发票单位地址
	 * @param post_code		邮编
	 * @param unitAddr		单位地主
	 * @param contactWay	报名人联系方式
	 * @param category_id	会议类别
	 * @throws Exception
	 */
	public void updateMessageByVerfication(int unitId,String unit,int isStay ,int isTogether,int roomNum,String columnList, String invoiceRemark, String invoiceAddr,String post_code, String unitAddr ,String contactWay ,int category_id)throws Exception{
		PreparedStatement prod = null;
		String sql = null;
		
		String[] unitMems = null;       
		String[] memArr = null;
		
		try{
			openConnection();
			beginTransaction();
			sql = "UPDATE WEB_PLATFORM_UNIT SET UNIT = ?,IS_STAY = ?,IS_TOGETHER = ?,ROOM_NUM = ?,INVOICE_REMARK = ?,INVOICE_ADDRESS = ?,POST_CODE = ?,UNIT_ADDRESS = ?,CONTACT_WAY = ? WHERE ID = ?";
			prod = prepareStatement(sql);
			prod.setString(1, unit);
			prod.setInt(2, isStay);
			prod.setInt(3, isTogether);
			prod.setInt(4, roomNum);
			prod.setString(5, invoiceRemark);
			prod.setString(6, invoiceAddr);
			prod.setString(7, post_code);
			prod.setString(8, unitAddr);
			prod.setString(9, contactWay);
			prod.setInt(10, unitId);
			
			if(prod.executeUpdate()!= 1) throw new Exception("修改报名信息失败...");
			
			sql = "UPDATE UNIT_MEMBER_INFO SET IS_DELETED = 1 WHERE UNIT_ID = " + unitId;
			prod = prepareStatement(sql);
			prod.executeUpdate();			
			//if(prod.executeUpdate() != 1) throw new Exception("Error occurs for updating data...");
			
			sql = "INSERT INTO UNIT_MEMBER_INFO (NAME, GENDER, NATION ,POSITION ,PHONE_NUMBER ,MOBILE ,EMAIL ,UNIT_ID) VALUES (?,?,?,?,?,?,?,?)";
			prod = prepareStatement(sql);
			
			unitMems = columnList.split("\\|");
			
			String temp;
			for(int i = 0,j = unitMems.length - 1; i <= j ; i ++){
				memArr = unitMems[i].split(",");

				prod.setString(1, memArr[0]);
				prod.setInt(2,  Integer.parseInt(memArr[6]));
				prod.setString(3, Format.isEmpty(memArr[1]) ? "" : memArr[1]);
				prod.setString(4, Format.isEmpty(memArr[2]) ? "" : memArr[2]);
				prod.setString(5, Format.Q2BChange(memArr[3].trim(),true));
				prod.setLong(6, FormSubmitor.strToLong(Format.Q2BChange(memArr[4].trim(),true)));			
				prod.setString(7, Format.isEmail(temp = Format.Q2BChange(memArr[5].trim(),true)) ? temp : "" );
				prod.setInt(8, unitId);
				
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
	
}
