<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>category information</title>   
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
  </head>  
  <body>
     <div class="box-positon">
		<div class="rpos">当前位置: 类别 - 编辑</div>
		<div class="clear"></div>
	</div>
	<div class="body-box" style="margin-top:10px;">
			<form action="${pageContext.request.contextPath }/category/update/${category.id}.do" method="post">
				<input type="hidden" name="id" value="${category.id }"/> 
				<table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
					<tr>
		        		     <td width="13%" style="background-color:#E6F2FE">类别名称:</td>
		        		     <td style="text-align:left"><input name="name" value="${category.name }" class="input_box"/></td>
		        		   	 <td width="13%" style="background-color:#E6F2FE">类别ID:</td>
		        		     <td width="37%" style="text-align:left">${category.id }</td>
		        	</tr>
		        	<tr>
		        		<td width="13%" style="background-color:#E6F2FE">上一级:</td>
		        		<td style="text-align:left">${nodeMap[category.parentId] }</td>
		        		<td width="13%" style="background-color:#E6F2FE">创建时间:</td>
		        		<td style="text-align:left"><fmt:formatDate value="${category.createDateTime }" type="date"/></td>
		        	</tr>
					<tr>
						<td width="13%" style="background-color:#E6F2FE">创建人:</td>
						<td style="text-align:left">${userMap[category.userId] }</td>
						<td width="13%" style="background-color:#E6F2FE">类别描述:</td>
						<td style="text-align:left"><input name="description" class="input_box" value="${category.description }"/></td>
					</tr>
				</table>
				<div style="margin:5px;text-align:right;"><input style="cursor: pointer;" type="submit" class="submit" value="提交" onclick="javascript:if(!confirm('确定提交该信息吗?')) return false;"></div>
			</form>
	</div>
  </body>			