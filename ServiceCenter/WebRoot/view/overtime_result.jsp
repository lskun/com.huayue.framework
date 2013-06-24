<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>加班统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />	
	<link rel="stylesheet" type="text/css" href="/css/style.css"> 
	<script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function totalCompute(){
			location.href = '/extra/totalOverWork.do?sTime=' + document.getElementById('startTime').value + '&eTime=' + document.getElementById('endTime').value;
		}
	</script>	
  </head>
  
  <body>
  <div class="box-positon">
		<div class="rpos">当前位置: 加班统计 </div>
		<div class="clear"></div>
	</div>
	<div style="margin:10px 42px;">
		<form action="/extra/overWork.do" method="post">
			<strong>查询时间段 ：</strong> <input id="startTime" name="startTime"  value="<c:if test="${sTime != null }">${sTime }</c:if>" type="text" class="input_box" size="30" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/> 
			&nbsp;<strong>到</strong>&nbsp; <input id="endTime" name="endTime" value="<c:if test="${eTime != null }">${eTime }</c:if>" type="text" class="input_box" size="30" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
			&nbsp;&nbsp;请选择员工：
        		     	<select name="userId">
						<option value="">--请选择--</option>
						<c:forEach items="${userMap}" var="u">
							<option value="${u.key }" <c:if test="${u.key == userId }">selected</c:if>>${u.value }</option>
						</c:forEach>
						</select>
			<input type="submit" value="查询" class="sblue_btn" />
		</form>
			
	</div>
	<table width="95%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#555555">
        <tr background="../images/bg.gif">
            <td height="26" colspan="8">
                &nbsp;&nbsp;<strong style="color: #98CAEF">加班统计列表</strong>
            </td>                         
        </tr>     
        <tr>
            <td bgcolor="#FFFFFF" id="_chl_0" colspan="2">
				<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
				    <tr bgcolor="#FFFFFF" style="font-weight: bold" background="../images/search.gif">
				    	<td align="center">ID</td>
				    	<td align="center">员工姓名</td>
				    	<td align="center">加班起始时间</td>
				    	<td align="center">加班截止时间</td>
				    	<td align="center">个人合计加班(小时)</td>
				    	<td align="center">审核人</td>
				    	<td align="center">原因</td>
				    	<td align="center">申请时间</td>
				    	<td align="center">操作</td>
				    </tr>
				    <c:set var="sum" value="0"></c:set>
				    <c:forEach items="${list}" var="ot">
				    <tr bgcolor="#FFFFFF" onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
				    	<td align="center">${ot.id }</td>
				    	<td align="center">${userMap[ot.userId] }</td>
				    	<td align="center">${ot.startTime}</td>
				    	<td align="center">${ot.endTime} </td>
				    	<td align="center">${ot.totalTime / 60}</td>			    	
				    	<td align="center">${userMap[ot.checkId] }</td>
				    	<td align="center">${ot.reason }</td>
				    	<td align="center">${ot.applyTime }</td>
				    	<td align="center">-</td>
				    </tr>
				    <c:set value="${sum + ot.totalTime}" var="sum"></c:set>
				    </c:forEach>
				    <tr bgcolor="#FFFFFF" >
				    	<td colspan="10" align="right"><span style="color:green;">统计加班(小时):</span> ${sum / 60} </td>
				    </tr>
				</table>
			</td> 
		</tr>
	</table>
	<c:if test="${sessionScope.login_user.permission == 2}"><div style="margin:20px 40px;"><input value="加班统计" type="button" class="blue_btn" onclick="totalCompute();"></div></c:if>	
	<br/>		
  </body>
</html>
