<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String result = (String)request.getAttribute("result");
	if("OK".equals(result)){
		out.println(com.huayue.framework.util.Utils.getTipHtml("提示信息","该条记录审核成功!","/extra/show.do"));
	}else{
		out.println(com.huayue.framework.util.Utils.getTipHtml("提示信息","该条记录审核失败! Caused by:" + result,null));
	}
%>
