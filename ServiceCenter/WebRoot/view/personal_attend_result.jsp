<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>每月日常出勤时间统计</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />	
	
	<script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>	
	<link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.1.2.2.js"></script>
	<style type="text/css">
		table.dwmc {
		    border-left: 1px solid #CCCCCC;
		    border-top: 1px solid #CCCCCC;
		    width: 100%;
		}
		table.dwmc td {
		    border-bottom: 1px solid #CCCCCC;
		    border-right: 1px solid #CCCCCC;
		    font-size: 12px;
		    height: 20px;
		    line-height: 20px;
		    padding: 5px;
		    text-align: center;
		}
		table.dwmc td input {
		    float: left;
		    height: 24px;
		    line-height: 24px;
		}
	</style>
    <link rel="stylesheet" type="text/css" href="/css/style.css"> 
  </head>
  <body>
  	<div style="margin-left:15px;margin-top:5px">
		<span style="color:green;font-weight:bold;font-size:15px">正常 = "", 旷工 = "旷" ,加班 = "+" ,出差 = "L" ,公出  = "G" ,病假 = "B" ,事假 = "S" , 探亲假 = "T" ,带薪假 = "-" ,调休 = "X"</span>
	</div>
  	<div style="margin-top:10px;">
  	<table width="98%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#555555">
        <tr background="../images/bg.gif">
            <td height="26" colspan="8">
                &nbsp;&nbsp;<strong style="color: #98CAEF">${monthDate }月日常出勤时间统计</strong>
            </td>                         
        </tr>     
        <tr>
            <td bgcolor="#FFFFFF" id="_chl_0" colspan="2">
				<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
				    <tr align="center" bgcolor="#FFFFFF" style="font-weight: bold" background="../images/search.gif">
						<td>姓名</td>
						<td>部门名称</td>
						<c:forEach items="${daysMapping}" var="entry">
						<td>${entry.key }<br>${entry.value }</td>	
						</c:forEach>
						<td>应到工作日</td>
						<td>旷工工作日</td>
						<td>迟到总时间</td>
						<td>早退总时间</td>
						<td>加班总计</td>
						<td>请假总计</td>
					</tr>
					<tr bgcolor="#FFFFFF" align="center">
						<td>${userMap[analysisAttendData.userId] }</td>
						<td>${analysisAttendData.deptName }</td>
						<c:set value="0" var="count"></c:set>
						<c:forEach items="${analysisAttendData.conditions }" var="condition">
							<td>${symbolMap[condition]}</td>
							<c:if test="${condition == -1}"><c:set value="${count + 1}" var="count"></c:set></c:if>
						</c:forEach>
						<td>${analysisAttendData.shouldWorkDay }</td>
						<td><c:if test="${count != 0}">${count }</c:if></td>
						<td><c:if test="${analysisAttendData.totalLateTime != 0}">${analysisAttendData.totalLateTime }</c:if></td>
						<td><c:if test="${analysisAttendData.totalEarlyTime != 0 }">${analysisAttendData.totalEarlyTime }</c:if></td>
						<td><c:if test="${analysisAttendData.overTime != 0.0}">${analysisAttendData.overTime}</c:if></td>
						<td><c:if test="${analysisAttendData.leaveWork != 0}">${analysisAttendData.leaveWork}</c:if></td>
					</tr>
				</table>
			</td>
		</tr>
	</table></div>				
  </body>
</html>  