<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String result = String.valueOf(request.getAttribute("result"));
	if("OK".equals(result))
	{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", "报名申请单提交成功!", "http://www.52xyzt.com"));   	
	}
	else{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", "报名申请单提交失败,请重新申请!" + result.replaceAll("java.lang.Exception:",""), "http://www.52xyzt.com")); 
	}
%>