<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>员工出勤记录</title>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />	
	<link rel="stylesheet" type="text/css" href="/css/style.css"> 
  </head>  
  <body>
	<div class="box-positon">
		<div class="rpos">当前位置: 考勤记录 </div>
		<div class="clear"></div>
	</div>
	<br><br>
	<table width="95%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#555555">
        <tr background="../images/bg.gif">
            <td height="26" colspan="8">
                &nbsp;&nbsp;<strong style="color: #98CAEF">员工考勤信息详情列表</strong>
            </td>                         
        </tr>     
        <tr>
            <td bgcolor="#FFFFFF" id="_chl_0" colspan="2">
				<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
				    <tr bgcolor="#FFFFFF" style="font-weight: bold" background="../images/search.gif">
				    	<td>员工ID</td>
				    	<td>所属部门</td>
				    	<td>门禁打卡时间</td>
				    	<td>考勤终端/设备ID</td>
				    	<td>验证方式</td> 
					</tr>
					<c:forEach items="${USER_MSG}" var="user">
					<tr bgcolor="#FFFFFF" onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
						<td>${user.userId }</td>
						<td><c:out value="${departMap[user.deptId]}" />  </td>
						<td>${user.checkTime }</td>
						<td>${user.sensorId }</td>
						<td>${user.verifyCode }</td>
					</tr>
					</c:forEach>
			</table>
			</td>
		</tr>
	</table>		    		
  </body>
</html>
