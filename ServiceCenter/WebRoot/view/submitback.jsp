<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String result = (String)request.getAttribute("result");
	if("OK".equals(result)){
		out.println(com.huayue.framework.util.Utils.getTipHtml("提示信息","表单提交成功!","/attend/req.do"));
	}else{
		out.println(com.huayue.framework.util.Utils.getTipHtml("提示信息","表单提交失败! 原因:" + result,null));
	}
%>
