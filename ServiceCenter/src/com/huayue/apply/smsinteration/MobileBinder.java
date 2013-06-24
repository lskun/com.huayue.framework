package com.huayue.apply.smsinteration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.smsservice.InterativeService;
import com.huayue.sms.operator.Constants;



public class MobileBinder extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(MobileBinder.class);
	public MobileBinder() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mobile;
		try{
			mobile = request.getParameter("mobile");
			new InterativeService().bindMobile(mobile);
			response.getWriter().println("OK");
		}catch(Exception ex){
			if(ex.toString().endsWith(Constants.NO_MOBILE_ERROR)) response.getWriter().println(Constants.NO_MOBILE_ERROR);
			else if(ex.toString().endsWith(Constants.MOBILE_SELECTED)) response.getWriter().println(Constants.MOBILE_SELECTED);			
			log.error(ex);
		}
		response.getWriter().flush();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
