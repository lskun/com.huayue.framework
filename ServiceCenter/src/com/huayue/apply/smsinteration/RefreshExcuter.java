package com.huayue.apply.smsinteration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.smsservice.InterativeService;
import com.huayue.apply.smsservice.SmsCacheContainer;
import com.huayue.sms.operator.Constants;



public class RefreshExcuter extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(RefreshExcuter.class);
	
	public RefreshExcuter() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String param ;
		try{
			String[] mobiles = request.getParameter("mobiles") == null ? new String[0] : request.getParameter("mobiles").trim().split(",");
			param = request.getParameter("key") == null ? "" : request.getParameter("key").trim();
			if(mobiles.length == 0) throw new Exception(Constants.MOBILE_LENGTH_NULL);
			if("".equals(param)){
				new InterativeService().refleshStatus(mobiles);
				SmsCacheContainer.removeCache(mobiles);
			}				
			else if("1".equals(param))
				new InterativeService().removeBinder(mobiles);
			else if("2".equals(param)){
				new InterativeService().markStatus(mobiles);
				SmsCacheContainer.removeCache(mobiles);
			}	
			response.getWriter().println("OK");
			response.getWriter().flush();
		}catch(Exception ex){
			//ex.printStackTrace();
			log.error(ex);
			if(ex.toString().endsWith(Constants.MOBILES_LENGTH_ERROR)) response.getWriter().println(Constants.MOBILES_LENGTH_ERROR);
			else if(ex.toString().endsWith(Constants.MOBILE_NO_EXIST)) response.getWriter().println(Constants.MOBILE_NO_EXIST);
			else if(ex.toString().endsWith(Constants.MOBILE_LENGTH_NULL)) response.getWriter().println(Constants.MOBILE_LENGTH_NULL);
			else response.getWriter().println(Constants.SQL_EXCEPTION);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
