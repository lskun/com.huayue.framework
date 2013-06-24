package com.huayue.sms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.framework.util.MD5;
import com.huayue.sms.service.SmsSendService;


public class SendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger log = Logger.getLogger(SendController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String mobile = null;
		String content = null;
		
		try
		{
			mobile = request.getParameter("phoneNumber").trim();
			content = request.getParameter("content").trim();
			
			SmsSendService.send(mobile, content,MD5.MD5Encode(mobile.substring(7)) );											
			request.setAttribute("result", "OK");
		}catch(Exception ex){
			log.error(ex);
			request.setAttribute("result", ex.toString());
		}
		request.getRequestDispatcher("/jsp/feedback.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
