<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.huayue.framework.util.Utils"%>
<%
	String result = String.valueOf(request.getAttribute("result"));
	out.println(Utils.getTipHtml("提示信息","操作失败! 异常原因 :" + result,null));
%>
