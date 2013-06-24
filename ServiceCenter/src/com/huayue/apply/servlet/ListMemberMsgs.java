package com.huayue.apply.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.ExportController;
import com.huayue.framework.util.DateUtil;
import com.huayue.framework.util.ServletUtils;



public class ListMemberMsgs extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ListMemberMsgs.class);
	
	public ListMemberMsgs() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		int is_stay ;
		int is_together;
		String unit;
		int pageIndex;
		int pageSize;
		String member;
		int category_id;
		
		try
		{
			is_stay = ServletUtils.getInt(request, "is_stay" ,ExportController.DEFAULT_SELECT);
			is_together = ServletUtils.getInt(request, "is_together" ,ExportController.DEFAULT_SELECT);			
			
			long start_time = (request.getParameter("start_time") == null || "".equals(request.getParameter("start_time"))) ? 0L : DateUtil.stringtoDate(request.getParameter("start_time"), DateUtil.FORMAT_ONE).getTime();
			long end_time = (request.getParameter("end_time") == null || "".equals(request.getParameter("end_time"))) ? 0L : DateUtil.stringtoDate(request.getParameter("end_time"), DateUtil.FORMAT_ONE).getTime();
			
			unit = ServletUtils.getString(request, "unit");
			pageIndex = ServletUtils.getInt(request, "pageIndex", 1);
			pageSize = ServletUtils.getInt(request, "pageSize", 30);
			member = ServletUtils.getString(request, "member");
			category_id = ServletUtils.getInt(request, "category_id", 0);
			
			ServletUtils.set(request,"DataCollection",
					new ExportController().queryForList(is_stay, is_together, unit, start_time, end_time, pageIndex, pageSize ,member ,category_id) );
			ServletUtils.set(request, "unit", unit);
			ServletUtils.set(request, "member", member);
			ServletUtils.set(request, "is_stay", is_stay);
			ServletUtils.set(request, "is_together", is_together);
			ServletUtils.set(request, "start_time", start_time);
			ServletUtils.set(request, "end_time", end_time);
			ServletUtils.set(request, "category_id", category_id);
			
			ServletUtils.logOK(request);
		}catch(Exception ex){
			log.error(ex);
			ServletUtils.logError(request, ex);
		}
		ServletUtils.forwardTo("/apply/search.jsp", request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
