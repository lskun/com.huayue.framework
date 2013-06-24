<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>考勤补充审核列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />	
	<link rel="stylesheet" type="text/css" href="/css/style.css"> 
	<script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>	
  </head>
  
  <body>
    <div class="box-positon">
		<div class="rpos">当前位置: 考勤补充-审核列表 </div>
		<div class="clear"></div>
	</div>
	<div style="margin:10px 42px;">
	<form action="/extra/showExact.do" method="post">
	<strong>请选择部门 ：</strong>&nbsp;&nbsp;<select name="deptId">
	<c:choose>
		<c:when test="${sessionScope.login_user.permission == 2 }">
			<option value="">---请选择---</option>
				<c:forEach items="${departMap}" var="map">
					<option <c:if test="${deptId == map.key }">selected</c:if> value="${map.key }">${map.value }</option>
				</c:forEach>
		</c:when>
		<c:otherwise>
			<option value="${deptId }">${departMap[deptId] }</option>
		</c:otherwise>
	</c:choose>		
			</select> &nbsp;&nbsp;<strong>状态：</strong>
			<select name="status">
				<option value="">---请选择---</option>
				<option <c:if test="${status == 0 }">selected</c:if> value="0">未审核</option>
				<option <c:if test="${status == 1 }">selected</c:if> value="1">已审核</option>
			</select>&nbsp;&nbsp;
			<strong>申请时间段 ：</strong> <input id="startTime" name="startTime"  value="<c:if test="${sTime != null }">${sTime }</c:if>" type="text" class="input_box" size="30" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/> 
			&nbsp;<strong>到</strong>&nbsp; <input id="endTime" name="endTime" value="<c:if test="${eTime != null }">${eTime }</c:if>" type="text" class="input_box" size="30" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
			<input type="submit" value="查询" class="sblue_btn" />
	</form>		
	</div>	
	<table width="95%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#555555">
        <tr background="../images/bg.gif">
            <td height="26" colspan="8">
                &nbsp;&nbsp;<strong style="color: #98CAEF">员工: <span style="color:yellow;">${userMap[userId] }</span> ----考勤审核列表</strong>
            </td>                         
        </tr>     
        <tr>
            <td bgcolor="#FFFFFF" id="_chl_0" colspan="2">
				<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
				    <tr bgcolor="#FFFFFF" style="font-weight: bold" background="../images/search.gif">
				    	<td align="center">ID</td>
				    	<td align="center">员工姓名</td>
				    	<td align="center">考勤打卡时间</td>
				    	<td align="center">补充原因</td>
				    	<td align="center">操作人</td>
				    	<td align="center">申请时间</td>
				    	<td align="center">审核状态</td>
				    	<td align="center">审核人</td>
				    	<td align="center">是否新增记录</td>
				    	<td align="center" width="15%">操作选项</td>				  
				    </tr>
				    <c:forEach items="${exactList}" var="el">
				    <tr bgcolor="#FFFFFF" onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
				    	<td align="center">${el.exactId }</td>
				    	<td align="center">${userMap[el.userId] }</td>
				    	<td align="center">${el.checkTime }</td>
				    	<td align="center">${el.reason }</td>
				    	<td align="center">${el.modifyBy }</td>
				    	<td align="center">${el.date }</td>
				    	<td align="center"><c:choose><c:when test="${el.status == 0 }"><span style="color:red;">未审核</span></c:when><c:otherwise><span style="color:green;">已审核</span></c:otherwise></c:choose></td>
				    	<td align="center"><c:choose><c:when test="${el.checkId == 0}">-</c:when><c:otherwise>${userMap[el.checkId]}</c:otherwise></c:choose></td>
				    	<td align="center"><c:if test="${el.isAdd == 1}">是</c:if></td>
				    	<td align="center">
				    	<c:choose>
				    		<c:when test="${sessionScope.login_user.permission >= 1 && el.status == 0}">
				    		<input type="button" class="sblue_btn" value="审核" onclick="javascript:if(confirm('确认提交审核记录吗?')) {location.href='${requestContext.request.contextPath}/extra/checkExact.do?id=${el.exactId }&checkId=${sessionScope.login_user.id }';}"/>
				    		&nbsp;&nbsp;<input type="button" class="red_btn" value="撤销" onclick="javascript:if(confirm('确认撤销该审核记录吗?')) { location.href='${requestContext.request.contextPath}/extra/delExactReq.do?id=${el.exactId }';}"/></c:when>
				    		<c:otherwise>-</c:otherwise>
				    	</c:choose></td>
				    </tr>
				    </c:forEach>
				</table>
			</td>
		</tr>
	</table>
	<br/>
	<br/>		    
  </body>
</html>
