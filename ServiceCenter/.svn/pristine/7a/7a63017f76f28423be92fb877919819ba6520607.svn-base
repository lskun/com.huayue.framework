<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String result = (String)request.getAttribute("result");
	if("OK".equals(result)){
		out.println(com.huayue.framework.util.Utils.getTipHtml("提示信息","客户信息更新成功!","/crm/clientListAjax.do"));
	}else{
		out.println(com.huayue.framework.util.Utils.getTipHtml("提示信息","客户信息更新失败! Caused by:" + result,null));
	}
%>
