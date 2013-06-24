package com.huayue.sms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.sms.service.MultiSender;


public class RecordSaver extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(RecordSaver.class);
	/**
	 * Constructor of the object.
	 */
	public RecordSaver() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		try{
			String nums = request.getParameter("phoneNumber").trim();
			String content = request.getParameter("content");
			
			String[] arr = nums.split("\r\n");
			
			request.getSession().setAttribute("pid", new MultiSender().initializingRecord(arr, content));
			request.setAttribute("nums", arr);
			request.getSession().setAttribute("content", content);
			request.setAttribute("result", "OK");
		}catch(Exception ex){
			log.error(ex);
			request.setAttribute("result", ex.toString());
		}
		request.getRequestDispatcher("/sms/keep_sending.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
