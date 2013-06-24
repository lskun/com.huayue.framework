package com.huayue.apply.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.framework.util.ServletUtils;


public class EditMemberMsgById extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(EditMemberMsgById.class);
	/**
	 * Constructor of the object.
	 */
	public EditMemberMsgById() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		try{
			int id = ServletUtils.getInt(request, "id");
			int unit_id = ServletUtils.getInt(request, "unit_id");
			ServletUtils.set(request, "MemberMessage", new com.huayue.apply.ExportController().getMemberMsgById(id));
			ServletUtils.set(request, "id", id);
			ServletUtils.set(request, "unit_id", unit_id);
			ServletUtils.logOK(request);
		}catch(Exception ex){			
			log.error(ex);
			ServletUtils.logError(request, ex);
		}
		ServletUtils.forwardTo("/apply/edit_message.jsp", request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
