<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.huayue.framework.util.Utils"%>
<%
	String result = (String)request.getAttribute("result");
	if("OK".equals(result)) {
		out.println(Utils.getTipHtml("提示信息","客户数据已添加成功!","/view/client_manager.jsp"));
	}else{
		out.println(Utils.getTipHtml("提示信息","添加失败!Caused By :" + result,null));
	}
%>

