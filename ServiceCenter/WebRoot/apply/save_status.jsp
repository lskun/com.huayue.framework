<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String result = String.valueOf(request.getAttribute("result"));
	if("OK".equals(result))
	{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", "数据更新成功!", null));   	
	}else{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", "表单更新失败!" + result, null)); 
	}
%>