package com.huayue.sms.servlet.list;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.framework.util.Format;
import com.huayue.sms.service.SmsSendService;


public class ListOutboundMsgs extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ListOutboundMsgs.class);
	public ListOutboundMsgs() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageIndex = 1;
		String str;
		
		try
		{
			if(Format.isInteger(str = request.getParameter("pageIndex"))) pageIndex = Integer.parseInt(str);
			request.setAttribute("PageInfo", new SmsSendService().listOutboundMessages(pageIndex, 10));
			request.setAttribute("result","OK" );
		}catch(Exception ex){
			log.error(ex);
			request.setAttribute("result", ex.toString());
		}
		request.getRequestDispatcher("/jsp/send_msgs.jsp").forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
