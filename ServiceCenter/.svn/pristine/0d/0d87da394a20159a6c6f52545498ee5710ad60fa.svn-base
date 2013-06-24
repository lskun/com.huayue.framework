package com.huayue.apply.smsinteration;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.smsservice.InterativeService;
import com.huayue.apply.smsservice.SmsCacheContainer;
import com.huayue.framework.util.ServletUtils;
import com.huayue.framework.util.Tool;
import com.huayue.platform.entity.Message;
import com.huayue.sms.operator.Constants;


public class InteractiveController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(InteractiveController.class);
	
	public InteractiveController() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] mobiles;
		//ArrayList<Message> list = null;
		int lastNo;
        response.setCharacterEncoding("UTF-8");
        
		try
		{
			mobiles = request.getParameter("mobiles") == null ? new String[0] : request.getParameter("mobiles").trim().split(",");
			lastNo  = ServletUtils.getInt(request, "id", 0);
			/**
			if(mobiles.length == 0) throw new Exception(Constants.MOBILE_LENGTH_NULL);
			if(id == 0)
				list = new InterativeService().listReceiveMsgs(mobiles);			
			else
				list = new InterativeService().listReceiveMsgsById(mobiles, id);
			
			System.out.println(list);
			**/	
			response.getWriter().println(SmsCacheContainer.listReceiveMsgs(mobiles, lastNo));
			response.getWriter().flush();
		}catch(Exception ex){
			//ex.printStackTrace();
			if(ex.toString().endsWith(Constants.MOBILE_NO_EXIST)) response.getWriter().println(Constants.MOBILE_NO_EXIST);
			else if(ex.toString().endsWith(Constants.MOBILE_LENGTH_NULL)) response.getWriter().println(Constants.MOBILE_LENGTH_NULL);
			else response.getWriter().println(Constants.SQL_EXCEPTION);
			log.error(ex);
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
