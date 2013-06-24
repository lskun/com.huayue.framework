/**
 * 
 */
package com.huayue.apply.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.InfoManager;
import com.huayue.framework.util.ServletUtils;


/**
 * @author lsk0414
 *
 */
public class FormUpdater extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger log = Logger.getLogger(FormUpdater.class);
	
	public void destory(){
		super.destroy();
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String unit = null;
		int isStay ;
		int isTogether = 0;
		int roomNum = 0;
		String columnList = null;
		
		String unitAddr = null;
		String post_code = null;
		
		String invoiceAddr = null;
		String invoiceRemark = null;
		String contactWay = null;
		int category_id;
		
		int unitId;
		
		try{
			isStay = ServletUtils.getInt(request, "isStay");
			if(isStay != 0){
				isTogether = ServletUtils.getInt(request, "isTogether" , 0);
				roomNum = ServletUtils.getInt(request, "roomNum" , 0);
			}
			unitAddr = ServletUtils.getString(request, "unitAddress");
			post_code = ServletUtils.getString(request, "postcode","\\d{6}","");
			
			invoiceAddr = ServletUtils.getString(request, "invoiceAddr");
			invoiceRemark = ServletUtils.getString(request, "invoiceRemark");
			unit = ServletUtils.getString(request, "unit");
			columnList = ServletUtils.getString(request, "columnList");							
			contactWay = ServletUtils.getString(request, "contactWay", "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$", "");
			category_id = ServletUtils.getInt(request, "category_id", 0);
			unitId = ServletUtils.getInt(request, "unitId");
			log.info("MESSAGE_UPDATER ------- unit :" + unit + " isStay :" + isStay + " isTogether:" + isTogether + " roomNum: " + roomNum + " unitAddress :" + unitAddr + " post_code :" + post_code + " invoiceAddr :" + invoiceAddr + " invoiceRemark :" + invoiceRemark + " columnList :" + columnList);
			
			new InfoManager().updateMessageByVerfication(unitId, unit, isStay, isTogether, roomNum, columnList, invoiceRemark, invoiceAddr, post_code, unitAddr, contactWay, category_id);
			ServletUtils.logOK(request);
		}catch(Exception ex){
			log.error(ex);
			ex.printStackTrace();
			
			ServletUtils.logError(request, ex);
		}
		ServletUtils.forwardTo("/apply/back_status.jsp", request, response);
	}
}
