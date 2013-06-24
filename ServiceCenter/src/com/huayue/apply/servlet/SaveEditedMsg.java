package com.huayue.apply.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.domain.ComprehensData;
import com.huayue.framework.util.ServletUtils;


public class SaveEditedMsg extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SaveEditedMsg.class);

	public SaveEditedMsg() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//单位信息
		String unit ;
		String unitAddress;
		String post_code;
		int is_stay;
		int is_together;
		int roomNum;
		String invoiceAddr;
		String invoiceContent;
		String invoiceRemark;
		
		//成员信息
		String name;
		int gender;
		String nation;
		String position;
		String phone_number;
		long mobile;
		String email;
		
		int id;
		int unit_id;
		ComprehensData comp = null;
		try
		{
			id = ServletUtils.getInt(request, "id");
			unit_id = ServletUtils.getInt(request, "unit_id");
			
			unit = ServletUtils.getString(request, "unit");
			unitAddress = ServletUtils.getString(request, "unitAddress");
			post_code = ServletUtils.getString(request, "post_code", "\\d{6}","");
			is_stay = ServletUtils.getInt(request, "isStay");
			is_together = ServletUtils.getInt(request, "isTogether");
			roomNum = ServletUtils.getInt(request, "roomNum");
			invoiceAddr = ServletUtils.getString(request, "invoiceAddr");
			invoiceContent = ServletUtils.getString(request, "invoiceContent");
			invoiceRemark = ServletUtils.getString(request, "invoiceRemark");
			
			name = ServletUtils.getString(request, "name");
			gender = ServletUtils.getInt(request, "gender");
			nation = ServletUtils.getString(request, "nation");
			position = ServletUtils.getString(request, "position");
			phone_number = ServletUtils.getString(request, "phone_number");
			mobile = ServletUtils.getLong(request, "mobile", 0L);
			email = ServletUtils.getString(request, "email");
			
			comp = new ComprehensData();
			
			comp.setId(id);
			comp.setUnit_id(unit_id);
			comp.setUnit_name(unit);
			comp.setUnitAddr(unitAddress);
			comp.setPost_code(post_code);
			comp.setIsStay(is_stay);
			comp.setIsTogether(is_together);
			comp.setRonnNum(roomNum);
			comp.setInvoiceAddr(invoiceAddr);
			comp.setInvoiceContent(invoiceContent);
			comp.setInvoiceRemark(invoiceRemark);
			
			comp.setName(name);
			comp.setGender(gender);
			comp.setNation(nation);
			comp.setPosition(position);
			comp.setPhoneNumber(phone_number);
			comp.setMobile(mobile);
			comp.setEmail(email);
			
			new com.huayue.apply.ExportController().saveEditedProject(comp);
			ServletUtils.logOK(request);
		}catch(Exception ex){
			log.error(ex);
			ServletUtils.logError(request, ex);
		}
		ServletUtils.forwardTo("/apply/save_status.jsp", request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
