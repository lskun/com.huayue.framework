package com.huayue.apply.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.FormSubmitor;
import com.huayue.apply.TokenGen;
import com.huayue.framework.util.ServletUtils;


public class FormExcutor extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FormExcutor() {
		super();
	}
	
	public static final Logger log = Logger.getLogger(FormExcutor.class); 
	
	public void destroy() {
		super.destroy(); 
		// Put your code here
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] surveys = null;
		String unit = null;
		int isStay = 0;
		int isTogether = 0;
		int roomNum = 0;
		String columnList = null;
		
		String unitAddr = null;
		String post_code = null;
		
		String invoiceAddr = null;
		//String[] invoiceContent = null;
		String invoiceRemark = null;
		String contactWay = null;
		int category_id;
		
		FormSubmitor formSub = null;

		try
		{
			if(TokenGen.getInstance().isTokenValid(request))
			{
				unit = ServletUtils.getString(request, "unit");
				formSub = new FormSubmitor();
				if(!formSub.isExistUnitId(unit)){         //判断是否存在该单位报名记录
					isStay = ServletUtils.getInt(request, "isStay");
					if(isStay != 0){
						isTogether = ServletUtils.getInt(request, "isTogether" , 0);
						roomNum = ServletUtils.getInt(request, "roomNum" , 0);
					}
					unitAddr = ServletUtils.getString(request, "unitAddress");
					post_code = ServletUtils.getString(request, "postcode","\\d{6}","");
					
					invoiceAddr = ServletUtils.getString(request, "invoiceAddr");
					//invoiceContent = ServletUtils.getStringArray(request, "invoiceContent"); //已忽略
					invoiceRemark = ServletUtils.getString(request, "invoiceRemark");
					
					surveys = ServletUtils.getStringArray(request, "tree_id");
					columnList = ServletUtils.getString(request, "columnList");							
					contactWay = ServletUtils.getString(request, "contactWay", "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$", "");
					category_id = ServletUtils.getInt(request, "category_id", 0);
					
					log.info("unit :" + unit + " isStay :" + isStay + " isTogether:" + isTogether + " roomNum: " + roomNum + " unitAddress :" + unitAddr + " post_code :" + post_code + " invoiceAddr :" + invoiceAddr + " invoiceRemark :" + invoiceRemark + " columnList :" + columnList);
					
					formSub.submitForm(unit, isStay, isTogether, roomNum, surveys, columnList, invoiceRemark, invoiceAddr, post_code,  unitAddr, contactWay ,category_id);
					
					ServletUtils.logOK(request);
				}else{
					throw new Exception("该单位已经提交过报名表...");
				}
			}else{
				throw new Exception("请勿重复提交表单。");
			}			
		}catch(Exception ex){
			log.error(ex);
			ServletUtils.logError(request, ex);
		}finally{
			request.getSession().removeAttribute(TokenGen.TOKEN_FOR_SUBMIT);
		}
		ServletUtils.forwardTo("/apply/back_status.jsp", request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
