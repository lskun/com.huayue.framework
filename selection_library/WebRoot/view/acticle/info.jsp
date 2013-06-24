<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>acticle information</title>   
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<link rel="stylesheet" type="text/css" href="/css/admin.css" />
    <link rel="stylesheet" type="text/css" href="/css/theme.css" />
	<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
  </head>  
  <body>
     <div class="box-positon">
		<div class="rpos">当前位置: 文章 - 查看内容</div>
		<input class="ropt return-button" style="margin:5px 0 0 5px" type="submit" onclick="javascript:history.back(-1);" value="返回列表"/>
		<div class="clear"></div>
	</div>
	<div class="body-box" style="margin-top:10px;">
        		 <table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
        		   <tr>
        		     <td width="13%" style="background-color:#E6F2FE">文章篇名:</td>
        		     <td style="text-align:left">${acticle.name }</td>
        		   	 <td width="13%" style="background-color:#E6F2FE">作者:</td>
        		     <td width="37%" style="text-align:left">${acticle.author }</td>
        		   </tr>
        		   <tr>
        		     <td width="13%" style="background-color:#E6F2FE">国别:</td>
        		     <td style="text-align:left">  
        		     	${countries[acticle.countryId]}        		     	
					</td>
					<td width="13%" style="background-color:#E6F2FE">体裁:</td>
					<td style="text-align:left">			
						${acticle.genre}
        		   	</td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE;">表达方式: </td>
        		     <td style="text-align:left">
        		     	${acticle.presentation}
        		     </td>
        		     <td style="background-color:#E6F2FE">来源: </td>
        		     <td style="text-align:left">
        		     	${acticle.origin }
        		     </td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">一级内容:</td>
        		     <td style="text-align:left">
						${contentMap[acticle.firConId]}
        		     </td>
        		     <td style="background-color:#E6F2FE">二级内容：</td>
        		   	 <td style="text-align:left">
        		   	 	${contentMap[acticle.secConId]}
        		   	 </td>      		     
        		   </tr>
        		   <tr>        		     
        		     <td width="13%" style="background-color:#E6F2FE">描述：</td>
	        		     <td style="text-align:left" width="37%">
	        		     	${acticle.comment }
	        		     </td>
        		     <td width="13%" style="background-color:#E6F2FE">名家名篇:</td>
        		     <td style="text-align:left" width="37%">
        		     	${acticle.famousMasterpiece}
        		     </td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">时代:</td>
        		     <td style="text-align:left">
        		     	${acticle.times}
        		     </td>
        		     <td style="background-color:#E6F2FE">版本:</td>
        		     <td style="text-align:left">${acticle.version }</td>
        		   </tr> 
        		   <tr>
        		   		<td style="background-color:#E6F2FE;text-align:center">引用此文章的书籍:</td>
        		   		<td colspan="3">
        		   			<div>
							<table id="tabmodel" class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
								<tbody>
								<tr style="font-weight: bold;text-align:center;" background="/images/search.gif">
									<td>ID</td>
									<td>书名</td>
									<td>出版社</td>
									<td>描述</td>
									<td>所属类别</td>
									<td>操作</td>
								</tr>
								<c:forEach items="${acticle.books }" var="book">
									<tr height=26 style="text-align:center;" bgcolor=#FFFFFF onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
										<td>${book.id }</td>
										<td>${book.name }</td>
										<td>${book.publisher }</td>
										<td>${book.bookDescription }</td>
										<td>${nodeMap[book.categoryId] }</td>
										<td><a class="pn-opt" style="color:green" href="${pageContext.request.contextPath }/book/showDetail.do?id=${book.id}">查看详情</a></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>	
        		   </tr>       		         		          		   
        		 </table>
        		 <c:if test="${acticle.directory != null && acticle.directory !=''}">
        		 	<div style="margin:5px;"><input type="button" value="下载正文" class="save-order" onclick="location.href='${pageContext.request.contextPath}/acticle/downFile.do?id=${acticle.id }'" /></div>
        		 </c:if>
        		 <c:if test="${fileContent != null}">
        		 <table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
        		 	<tr>
        		 		<td style="background-color:#E6F2FE" colspan=4 align=center><strong>正文内容</strong></td>
        		 	</tr>
        		 	<tr>
        		   		<td colspan="4" style="text-align:left;">
        		   			${fileContent }
        		   		</td>
        		   </tr> 
        		 </table>
        		 </c:if>
        		 <br/>        		  
        	</div>
  </body>
</html>
