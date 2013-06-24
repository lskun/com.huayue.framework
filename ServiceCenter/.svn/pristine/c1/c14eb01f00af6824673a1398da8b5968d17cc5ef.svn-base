<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String status = (String)request.getAttribute("status");
	if("1".equals(status)){
		out.print(com.huayue.framework.util.Utils.getTipHtml("提示信息","文件已导入成功!","/crm/client_manager.do"));
	}else{
		out.print(com.huayue.framework.util.Utils.getTipHtml("提示信息","文件导入失败!" + status,null));
	}
%>
