package com.huayue.apply.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.ExportController;
import com.huayue.apply.domain.ComprehensData;
import com.huayue.framework.util.ServletUtils;


public class AddMember extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AddMember.class);
	public AddMember() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int unit_id;
		//成员信息
		String name;
		int gender;
		String nation;
		String position;
		String phone_number;
		long mobile;
		String email;
		
		ComprehensData comp = null;
		
		
		try{
			unit_id = ServletUtils.getInt(request, "unit_id");
			name = ServletUtils.getString(request, "name");
			gender = ServletUtils.getInt(request, "gender");
			nation = ServletUtils.getString(request, "nation");
			position = ServletUtils.getString(request, "position");
			phone_number = ServletUtils.getString(request, "phone_number");
			mobile = ServletUtils.getLong(request, "mobile", 0L);
			email = ServletUtils.getString(request, "email");
			
			comp = new ComprehensData();
			
			comp.setUnit_id(unit_id);
			comp.setName(name);
			comp.setGender(gender);
			comp.setNation(nation);
			comp.setPosition(position);
			comp.setPhoneNumber(phone_number);
			comp.setMobile(mobile);
			comp.setEmail(email);
			
			new ExportController(comp).addUnitMember();
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
