package com.huayue.apply.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.ExportController;
import com.huayue.framework.util.ServletUtils;


public class DeleteUnit extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(DeleteUnit.class);
	
	public DeleteUnit() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id ;
		
		try{
			id = ServletUtils.getInt(request, "id");
			new ExportController().delUnit(id);
			ServletUtils.logOK(request);
		}catch(Exception ex){
			log.error(ex);
			ServletUtils.logError(request, ex);
		}
		ServletUtils.forwardTo("/apply/save_status.jsp", request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
