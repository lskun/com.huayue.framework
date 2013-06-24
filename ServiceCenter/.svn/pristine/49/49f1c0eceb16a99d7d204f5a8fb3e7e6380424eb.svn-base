package com.huayue.sms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.framework.util.ServletUtils;
import com.huayue.sms.service.MultiSender;


public class ListSendRecord extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ListSendRecord.class);
	
	public ListSendRecord() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try{
			ServletUtils.set(request, "list",new MultiSender().listSendRecord());
			ServletUtils.logOK(request);
		}catch(Exception ex){
			log.error(ex);
			ServletUtils.logError(request, ex);
		}
		ServletUtils.forwardTo("/sms/record_list.jsp", request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}
}
