package com.huayue.apply.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.UnitManager;
import com.huayue.framework.util.ServletUtils;


public class MultiFormEditor extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(MultiFormEditor.class);
	
	public MultiFormEditor() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		double standard;
		String[] ids;
		
		try{
			ids = request.getParameterValues("ids");
			if(ids.length == 0) throw new Exception("请选择要修改的单位.");
			standard = Double.parseDouble(request.getParameter("standard"));
			new UnitManager().updateChargeStandard(ids, standard);
			ServletUtils.logOK(request);
		}catch(Exception ex){
			log.error(ex);
			ServletUtils.logError(request, ex);
		}
		ServletUtils.forwardTo("/apply/data_update_status.jsp", request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
