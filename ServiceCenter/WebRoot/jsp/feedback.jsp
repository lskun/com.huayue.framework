<%@ page language="java" pageEncoding="utf-8"%>
<%
	String result = String.valueOf(request.getAttribute("result"));
	if("OK".equals(result))
	{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", "短信发送成功!" , "/main.jsp"));   	
	}
	else{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", "短信发送失败!" + String.valueOf(request.getAttribute("result")) , "/jsp/sms_send.jsp")); 
	}
%>