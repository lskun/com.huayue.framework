<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.huayue.framework.util.DateUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户详细信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
  </head>
  
  <body>
    <div class="box-positon">
		<div class="rpos">当前位置: 客户档案管理 - 客户详细信息</div>
		<div class="clear"></div>
	</div>
	<div class="body-box">
	<br>
		<table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
        		   <tr>
        		     <td width="13%" style="background-color:#E6F2FE">单位名称:</td>
        		     <td style="text-align:left">${Client.unit} </td>
        		   	 <td width="13%" style="background-color:#E6F2FE">邮编:</td>
        		     <td style="text-align:left" width="37%">${Client.postCode} </td>
        		   </tr>
        		   <tr>
        		     <td width="13%" style="background-color:#E6F2FE">所在地:</td>
        		     <td style="text-align:left">${Client.deliverprovince} ${Client.delivercity } ${Client.deliverarea }</td>
        		     <td width="13%" style="background-color:#E6F2FE">单位地址:</td>
        		     <td style="text-align:left">			
							${Client.address }
        		   	 </td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE;">姓名: </td>
        		     <td style="text-align:left">${Client.name }</td>
        		     <td style="background-color:#E6F2FE">性别: </td>
        		     <td style="text-align:left">${Client.sex }</td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">民族:</td>
        		     <td style="text-align:left">
        		       <c:set var="key" value="${Client.nation}" />
						${nations[key]} 					
        		     </td>
        		     <td style="background-color:#E6F2FE">类别:</td>
        		     <td style="text-align:left">
        		     ${ClientCategory} 
        		     </td>
        		   </tr>
        		   <tr>        		     
        		     <td width="13%" style="background-color:#E6F2FE">称呼：</td>
        		     <td style="text-align:left" width="37%">${Client.callName }</td>
        		     <td width="13%" style="background-color:#E6F2FE">职务:</td>
        		     <td style="text-align:left" width="37%">${Client.duty }</td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">办公电话:</td>
        		     <td style="text-align:left">${Client.phone }</td>
        		     <td style="background-color:#E6F2FE">手机号码:</td>
        		     <td style="text-align:left">${Client.mobile }</td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">电子邮箱:</td>
        		     <td style="text-align:left">${Client.email }</td>
        		     <td style="background-color:#E6F2FE">传真:</td>
        		     <td style="text-align:left">${Client.fax }</td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">参加过的活动</td>
        		     <td style="text-align:left">${Client.joinedActivity }</td>
        		     <td style="background-color:#E6F2FE">信息录入时间</td>
        		     <td style="text-align:left">
        		     <c:set var="time" value="${Client.registerTime}"></c:set>
        		     <%=DateUtil.dateToString(new java.util.Date(Long.valueOf(pageContext.getAttribute("time").toString())),DateUtil.MONTG_DATE_FORMAT) %></td>
        		   </tr>			
        		 </table> 
        		 <br>
				 <div style="text-align:right;"><input type="button" class="sblue_btn" value="返回上一页" onclick="javascript:history.go(-1);"/></div>	
	</div>
  </body>
</html>
