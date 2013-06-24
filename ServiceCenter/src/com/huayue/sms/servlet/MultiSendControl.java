package com.huayue.sms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.framework.util.MD5;
import com.huayue.platform.entity.Message;
import com.huayue.sms.operator.Constants;
import com.huayue.sms.service.MultiSender;
import com.huayue.sms.service.SmsSendService;


public class MultiSendControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(MultiSendControl.class);

	public MultiSendControl() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mobile = null;
		String content = null;
		
		try{
			mobile = request.getParameter("mobile").trim();
			content = String.valueOf(request.getSession().getAttribute("content"));
			
			log.info("multisendcontrol :" + content);
			SmsSendService.send(mobile, content,MD5.MD5Encode(mobile.substring(7)) );
			new MultiSender().updateRecord(Long.parseLong(request.getSession().getAttribute("pid").toString()));
			response.getWriter().print("OK");
			response.getWriter().flush();
		}catch(Exception ex){
			Message msg = new Message();
			msg.setContent(content);
			msg.setPhoneNumber(mobile);
			msg.setSend_time(new java.util.Date().getTime());
			msg.setSend_state(Constants.SEND_FAILED);
			
			try {new SmsSendService(msg).updateSmsRecord();}catch(Exception e){}
			log.error(ex);
			response.getWriter().print(ex.toString());
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
