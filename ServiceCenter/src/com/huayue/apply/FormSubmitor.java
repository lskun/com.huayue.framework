package com.huayue.apply;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.huayue.apply.util.SecurityCode;
import com.huayue.framework.util.Format;
import com.huayue.framework.util.ServletUtils;
import com.huayue.platform.entity.Message;
import com.huayue.sms.operator.Constants;
import com.huayue.sms.service.SmsSendService;


public class FormSubmitor extends com.huayue.sms.data.DBAccess {
	
	private static final Logger log = Logger.getLogger(FormSubmitor.class);
	
	/**
	 * 
	 * @param unit
	 * @param isStay
	 * @param isTogether
	 * @param roomNum
	 * @param survey
	 * @param columnList
	 * @param invoiceRemark
	 * @param invoiceAddr
	 * @param post_code
	 * @param unitAddr
	 * @param contactWay
	 * @param category_id
	 * @throws Exception
	 */
	public void submitForm(String unit,int isStay ,int isTogether,int roomNum,String[] survey ,String columnList, String invoiceRemark, String invoiceAddr,String post_code, String unitAddr ,String contactWay ,int category_id) throws Exception{
		PreparedStatement prod = null;
		ResultSet rst = null;
		String sql = null;
		
		String[] unitMems = null;       
		String[] memArr = null;
		int unitId ;
		String security_code = null;
		
		Message msg = null;
		String content = null;
		
		try
		{
			//生成验证码
			security_code = SecurityCode.produce();
			
			openConnection();
			beginTransaction();
			sql = "INSERT INTO WEB_PLATFORM_UNIT (UNIT, IS_STAY, IS_TOGETHER, ROOM_NUM, UNIT_SURVEY, INVOICE_REMARK ,INVOICE_ADDRESS ,POST_CODE ,UNIT_ADDRESS ,APPLY_TIME,CONTACT_WAY,SECURITY_CODE,CATEGORY_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			prod = prepareStatement(sql);
			
			prod.setString(1, unit);
			prod.setInt(2, isStay);
			prod.setInt(3, isTogether);
			prod.setInt(4, roomNum);
			prod.setString(5, splitSurv(survey));
			prod.setString(6, invoiceRemark);
			prod.setString(7, invoiceAddr);
			prod.setString(8, post_code);
			prod.setString(9, unitAddr);
			prod.setLong(10, System.currentTimeMillis());
			prod.setString(11, contactWay);
			prod.setString(12, security_code);
			prod.setInt(13, category_id);
			
			if(prod.executeUpdate()!= 1) throw new Exception("保存记录失败...");
			prod = prepareStatement("SELECT ID FROM WEB_PLATFORM_UNIT WHERE UNIT = ?");
			prod.setString(1, unit);
			rst = prod.executeQuery();
			if(!rst.next()) throw new Exception("无该单位记录....");
			unitId = rst.getInt("ID");
			
			prod = prepareStatement("INSERT INTO UNIT_MEMBER_INFO (NAME, GENDER, NATION ,POSITION ,PHONE_NUMBER ,MOBILE ,EMAIL ,UNIT_ID) VALUES (?,?,?,?,?,?,?,?)");
			unitMems = columnList.split("\\|");
			
			String temp;
			for(int i = 0,j = unitMems.length - 1; i <= j ; i ++){
				memArr = unitMems[i].split(",");

				prod.setString(1, memArr[0]);
				prod.setInt(2,  Integer.parseInt(memArr[6]));
				prod.setString(3, Format.isEmpty(memArr[1]) ? "" : memArr[1]);
				prod.setString(4, Format.isEmpty(memArr[2]) ? "" : memArr[2]);
				prod.setString(5, Format.Q2BChange(memArr[3].trim(),true));
				prod.setLong(6, strToLong(Format.Q2BChange(memArr[4].trim(),true)));
				
				prod.setString(7, Format.isEmail(temp = Format.Q2BChange(memArr[5].trim(),true)) ? temp : "" );
				prod.setInt(8, unitId);
				
				prod.addBatch();
			}
			prod.executeBatch();
			endTransaction(true);
			
			msg = new Message();
			content = "您好，您填写的报名信息已经提交成功!信息修改验证码为" + security_code + "，可凭此验证码在网站上修改您填写的报名单位信息。【北京新学校研究院】";
			msg.setContent(content);
			msg.setPhoneNumber(contactWay);
			msg.setSend_state(Constants.SEND_WAIT);
			
			new SmsSendService(msg).updateSmsRecord(); 
		}catch(Exception ex){
			endTransaction(false);
			log.error(ex);
			throw ex;
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
	}
	
	public boolean isExistUnitId(String unit)throws Exception{
		ResultSet rst = null;
		PreparedStatement prod = null;
		
		try{
			openConnection();
			prod = prepareStatement("SELECT ID FROM WEB_PLATFORM_UNIT WHERE UNIT = ? AND IS_DELETED = 0");
			prod.setString(1, unit);
			rst = prod.executeQuery();
			if(!rst.next()) return false;
			return true;
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
		
	}
	
	public static long strToLong(String str){
		if(str == null || "".equals(str)) return 0;
		if(str.matches(ServletUtils.FM_INT)) return Long.parseLong(str);
		return 0;
	}
	
	public static String splitSurv(String[] survey) throws Exception{
		if(survey.length == 0) return null;
		if(survey.length == 1) return survey[0];
		if(survey.length > 0 ) return Arrays.toString(survey);
		return null;
	}

}
