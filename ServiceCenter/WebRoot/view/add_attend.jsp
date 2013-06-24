<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>考勤补充</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
  </head>
  
  <body>
  	<div class="box-positon">
		<div class="rpos">当前位置: 考勤补充 - 忘签到/签退处理</div>
		<div class="clear"></div>
	</div>
	<div style="margin:10px;">
		<form action="/extra/modifyAttend.do" method="post">
		<input type="hidden" name="userId" value="${param.userId }"/>
		<input type="hidden" name="date" value="${param.date }"/>
			<table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
				<tr>
        		     <td width="16%" style="background-color:#E6F2FE">日期:</td>
        		     <td style="text-align:left;margin-left:5px;">${param.date} </td>
        		   	 <td width="16%" style="background-color:#E6F2FE">员工姓名:</td>
        		     <td style="text-align:left;margin-left:5px;" width="34%">${param.userName}</td>
        		</tr>
        		<tr>
        			<td width="16%" style="background-color:#E6F2FE">考勤补充原因</td>
        			<td style="text-align:left">
        				<input name="reason" maxlength="50" id="unit" class="input_box" type="text" size="40" style="vertical-align: middle;color: #555555;width:70%;float:left;margin-left:5px;"/>
        			</td>
        			<td width="16%" style="background-color:#E6F2FE">增加记录时间</td>
        			<td style="text-align:left">
        				<input name="time" type="text" class="input_box" style="width:35%;float:left;margin-left:5px;" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm:ss'})" />
        			</td>        			
        		</tr>
        	</table>
        	<div style="float:right;margin-top:10px;"><input class="sblue_btn" type="submit" value="提交申请" onclick="return confirm('确认提交该申请单吗?')" /></div>
			&nbsp;&nbsp;&nbsp;&nbsp;<div style="float:right;margin-top:10px;"><input type="button" value="返回上一层" class="sblue_btn" onclick="javascript:location.href='${pageContext.request.contextPath}/attend/req.do';" /></div>
			
		</form>
	</div>
	
  </body>
</html>