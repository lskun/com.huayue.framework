<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String result = String.valueOf(request.getAttribute("result"));
	if("OK".equals(result))
	{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", "数据更新成功!","/servlet/ListUnitMessages"));   	
	}
	else{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", "数据更新失败!" + result, null)); 
	}
%>