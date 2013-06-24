<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.huayue.framework.util.Utils"%>
<%
	String info = String.valueOf(request.getAttribute("result"));
	out.println(Utils.getTipHtml("提示信息", info, 
		request.getAttribute("url")== null ? null : String.valueOf(request.getAttribute("url"))));
%>
