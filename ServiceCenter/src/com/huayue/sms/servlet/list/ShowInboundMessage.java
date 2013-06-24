package com.huayue.sms.servlet.list;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.sms.service.SmsReceiveService;


public class ShowInboundMessage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(ShowInboundMessage.class);
	
	public ShowInboundMessage() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id;
		try{
			id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("InboundMessage", new SmsReceiveService().showInboundMessage(id));
			request.setAttribute("result", "OK");
		}catch(Exception ex){
			log.error(ex);
			request.setAttribute("result", ex.toString());
		}
		request.getRequestDispatcher("/content/InboundMsg.jsp").forward(request, response);
	}
	
	public void init() throws ServletException {
		// Put your code here
	}

}
