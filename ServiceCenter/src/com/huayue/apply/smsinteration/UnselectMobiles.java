package com.huayue.apply.smsinteration;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.smsservice.InterativeService;
import com.huayue.framework.util.Tool;
import com.huayue.platform.entity.Mobile;
import com.huayue.sms.operator.Constants;



public class UnselectMobiles extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UnselectMobiles.class);
	
	public UnselectMobiles() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Mobile> list = null;
		response.setCharacterEncoding("UTF-8");
		
		try{
			list = new InterativeService().listUnSelectMobiles();
			if(list.size() == 0) response.getWriter().println(Constants.MOBILE_NO_LEFT);
			else response.getWriter().println(Tool.convertToJson(list));
		}catch(Exception ex){
			ex.printStackTrace();
			response.getWriter().println(Constants.SQL_EXCEPTION);
			log.error(ex);
		}finally{
			response.getWriter().flush();
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
