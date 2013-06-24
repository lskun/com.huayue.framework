<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>book information</title>   
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<link rel="stylesheet" type="text/css" href="/css/admin.css" />
    <link rel="stylesheet" type="text/css" href="/css/theme.css" />
	<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
  </head>  
  <body>
     <div class="box-positon">
		<div class="rpos">当前位置: 书籍内容 - 详情</div>
			<input class="ropt return-button" style="margin:5px 0 0 5px" type="submit" onclick="javascript:history.back(-1);" value="返回列表"/>
		<div class="clear"></div>
	</div>
	<div class="body-box" style="margin-top:10px;">
	<table style="font-size:13px;" width=100% border=1 align=center cellpadding="3" cellspacing="1" bgcolor="#555555">
		<tr><td height=28 colspan=8><strong style="color: #98CAEF">&nbsp;&nbsp;书籍详细信息</strong></td></tr>
		<tr><td bgcolor=#FFFFFF colspan=8><div style="margin:5px;" >
		<table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
			<tr>
        		     <td width="13%" style="background-color:#E6F2FE">书名:</td>
        		     <td style="text-align:left">${book.name }</td>
        		   	 <td width="13%" style="background-color:#E6F2FE">书籍ID:</td>
        		     <td width="37%" style="text-align:left">${book.id }</td>
        	</tr>
        	<tr>
        			<td width="13%" style="background-color:#E6F2FE">作者:</td>
        			<td style="text-align:left">${book.author }</td>
        			<td width="13%" style="background-color:#E6F2FE">出版社:</td>
        			<td width="37%" style="text-align:left">${book.publisher }</td>
        	</tr>
        	<tr>
        		<td width="13%" style="background-color:#E6F2FE">所属类别:</td>
        		<td style="text-align:left">${categoryStr }</td>
        		<td width="13%" style="background-color:#E6F2FE">出版日期:</td>
        		<td style="text-align:left">
        			<c:if test="${book.publishDate != 0 }"><fmt:formatDate value="${book.publishDateText }" type="date"/></c:if>   			
        		</td>
        	</tr>
			<tr>
				<td width="13%" style="background-color:#E6F2FE">创建时间:</td>
				<td style="text-align:left"><fmt:formatDate value="${book.createTimeText }" type="date"/></td>
				<td width="13%" style="background-color:#E6F2FE">书籍描述:</td>
				<td style="text-align:left">${bokk.bookDescription }</td>
			</tr>
		</table>
		</div>
		</td>
		</tr>
		<tr><td bgcolor=#FFFFFF colspan=8>
			<div style="margin:5px;" >
				<table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
					<tr style="font-weight: bold;text-align:center;" background="../images/search.gif"><td>ID</td>
						<td>文章篇名</td>
						<td>作者</td>
						<td>国别</td>
						<td>体裁</td>
						<td>表达方式</td>
						<td>创建时间</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${book.acticles }" var="acticle">
					<tr height=26 style="text-align:center;" bgcolor=#FFFFFF onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
						<td>${acticle.id }</td>
						<td>${acticle.name }</td>
						<td>${acticle.author }</td>
						<td>${countries[acticle.countryId] }</td>
						<td>${acticle.genre }</td>
						<td>${acticle.presentation }</td>
						<td><fmt:formatDate value="${acticle.createDateTime}" type="date"/></td>
						<td> <a class="pn-opt" style="color:green" href="${pageContext.request.contextPath}/acticle/findByID.do?id=${acticle.id}">查看详情</a> </td>
					</tr>
					</c:forEach> 
				</table>
			</div>
			</td>
		</tr>
		</table>
	</div>
	</body>	