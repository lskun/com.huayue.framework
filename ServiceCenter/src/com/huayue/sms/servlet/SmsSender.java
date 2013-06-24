package com.huayue.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.framework.util.Format;
import com.huayue.framework.util.MD5;
import com.huayue.platform.entity.Message;
import com.huayue.sms.operator.Constants;
import com.huayue.sms.service.SmsSendService;


/**
 * Servlet implementation class SmsSender
 */
public class SmsSender extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = Logger.getLogger(SmsSender.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SmsSender() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		Message msg = null;
		String content = null;
		String mobile = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		try
		{
			mobile = request.getParameter("mobile") == null ? "" : request.getParameter("mobile");
			content = request.getParameter("content") == null ? "" : request.getParameter("content");
			String key = request.getParameter("key");
	
			if("".equals(mobile) || !Format.isMobileNO(mobile)){
				log.error("IP:" + request.getRemoteAddr());
				throw new Exception("mobile value: " + mobile + " <Param Error...>" );
			}
			
			if(!MD5.MD5Encode(mobile.substring(7)).equals(key)) throw new Exception("密钥不匹配...");
			
			if("".equals(content) || null == content) throw new Exception("短信内容不能为空。");
			
			//SendReadMsger sender = SendReadMsger.newInstance();
			//sender.sendMsg(content, mobile);
			msg = new Message();
			msg.setContent(content);
			msg.setPhoneNumber(mobile);
			msg.setSend_state(Constants.SEND_WAIT);
			
			new SmsSendService(msg).updateSmsRecord(); 
			out.print("OK");
			out.flush();
		}catch(Exception ex){
			msg = new Message();
			msg.setContent(content);
			msg.setPhoneNumber(mobile);
			msg.setSend_time(new java.util.Date().getTime());
			msg.setSend_state(Constants.SEND_FAILED);
			ex.printStackTrace();
			try { new SmsSendService(msg).updateSmsRecord(); }catch(Exception e){ log.error(e);}
			log.error(ex);
			out.println(ex.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
	
	 public void init() throws ServletException { 

	 }
}
