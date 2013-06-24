package com.huayue.apply.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.domain.RegisterUnit;
import com.huayue.apply.util.ExceptionConstants;
import com.huayue.framework.util.ServletUtils;



public class VerificationExecuter extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VerificationExecuter.class);
	
	public VerificationExecuter() {
		super();
	}

	public void destroy() {
		super.destroy();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String code = null;
		RegisterUnit rUnit = null;
		
		try
		{		
			code = request.getParameter("code") == null ? "" : request.getParameter("code").trim();
			if(code.length() > 8) throw new Exception(ExceptionConstants.SIZE_OUT_ERROR);
			
			rUnit = new com.huayue.apply.InfoManager().getMessageByVerification(code);
			ServletUtils.set(request, "RegisterUnit", rUnit);
			ServletUtils.logOK(request);
		}
		catch(Exception ex)
		{
			ServletUtils.logError(request, ex);
			log.error(ex);
		}
		ServletUtils.forwardTo("/apply/info_edit.jsp", request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
