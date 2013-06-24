<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String result = (String)request.getAttribute("result");
	if("OK".equals(result)){
		out.println(com.huayue.framework.util.Utils.getTipHtml("提示信息","已撤销该条记录!",null));
	}else{
		out.println(com.huayue.framework.util.Utils.getTipHtml("提示信息","该条记录撤销失败! Caused by:" + result,null));
	}
%>
