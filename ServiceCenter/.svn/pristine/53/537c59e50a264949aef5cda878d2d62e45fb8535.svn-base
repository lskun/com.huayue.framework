package com.huayue.sms.servlet.list;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.framework.util.Format;
import com.huayue.sms.service.SmsReceiveService;


public class ListInboundMsgs extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ListInboundMsgs.class);
	
	public ListInboundMsgs() {
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
		try{
			if(Format.isInteger(str = request.getParameter("pageIndex"))) pageIndex = Integer.parseInt(str);
			request.setAttribute("PageInfo", new SmsReceiveService().listInboundMessages(pageIndex, 10));
			request.setAttribute("result","OK" );
		}catch(Exception ex){
			request.setAttribute("result", ex.toString());
			log.error(ex);
		}
		request.getRequestDispatcher("/jsp/rev_msgs.jsp").forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
