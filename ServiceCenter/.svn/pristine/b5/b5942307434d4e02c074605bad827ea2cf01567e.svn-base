package com.huayue.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.framework.util.Tool;
import com.huayue.platform.entity.Message;
import com.huayue.sms.service.SmsReceiveService;


public class SmsReciever extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(SmsReciever.class);
	
	public SmsReciever() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String start_time ;
		String end_time;
		PrintWriter out = response.getWriter();
		List<Message> msgs = null;
		String str = null;
		response.setCharacterEncoding("UTF-8");
		
		try{
			start_time = request.getParameter("start_time");
			end_time = request.getParameter("end_time");
			
			start_time = null == start_time ? "" : start_time;
			end_time = null == end_time ? "" : end_time;
			
			msgs = new SmsReceiveService().getMessages(start_time, end_time);
			str = Tool.convertToJson(msgs);
			out.println(str);
			out.flush();
		}catch(Exception ex){
			log.error(ex);
			out.println(ex.toString());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
