package com.huayue.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.sms.service.MultiSender;


public class ImportMobileData extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ImportMobileData.class);
	public ImportMobileData() {
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
		
		PrintWriter out = response.getWriter();		
		String unit = null;
		int is_stay ;
		
		try{
			unit = request.getParameter("unit") == null ? "" : request.getParameter("unit").trim();
			is_stay = Integer.parseInt(request.getParameter("isStay"));
			out.println(new MultiSender().importData(unit, is_stay));
			out.flush();
		}catch(Exception ex){
			out.println(ex.toString());
			log.error(ex);
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
