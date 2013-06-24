<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.huayue.framework.util.Utils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String result = (String)request.getAttribute("result");
	if(!"OK".equals(result)){
		out.println(Utils.getTipHtml("提示信息","异常原因 :" + result,null));
		return;
	}
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>考勤结果统计</title>
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
    <div class="box-positon">
		<div class="rpos">当前位置: 考勤统计 </div>
		<div class="clear"></div>
	</div>
		<div style="margin:10 40px;">	
	<form id="form1" action="${pageContext.request.contextPath}/attend/req.do" method="post" onsubmit="return isValidateTime();">
		<table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
				<tr>
        		     <td width="10%" style="background-color:#E6F2FE">请选择员工：</td>
        		     <td style="text-align:left">
        		     	<select id="userId" name="userId">
						<option value="">--请选择--</option>
						<c:forEach items="${userMap}" var="u">
							<option value="${u.key }" <c:if test="${u.key == userId }">selected</c:if>>${u.value }</option>
						</c:forEach>
						</select>
        		     </td>
        		   	 <td width="10%" style="background-color:#E6F2FE">请选择考勤时间段: </td>
        		     <td width="40%">
        		     <input id="startTime" name="startTime"  value="${startTime }" type="text" class="input_box" size="30" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>到 <span style="float:right;margin-right:150px;">
				<input id="endTime" name="endTime" value="${endTime }" type="text" class="input_box" size="30" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
        		</span>     
        		     </td>
        		</tr>
        		<tr>
        			<td colspan="4">
        				<input style="float:right;margin-right:5px" id="datasubmit" type="submit" class="sblue_btn" value="考勤计算">
        			</td>
        		</tr>									
		</table>
	</form>	
	</div>
	
	<table width="95%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#555555">
        <tr background="../images/bg.gif">
            <td height="26" colspan="8">
            	<c:set value="${userId}" var="userId"></c:set>
                &nbsp;&nbsp;<strong style="color: #98CAEF">员工 ----考勤统计信息</strong>
            </td>                         
        </tr>     
        <tr>
            <td bgcolor="#FFFFFF" id="_chl_0" colspan="2">
				<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
				    <tr bgcolor="#FFFFFF" style="font-weight: bold" background="../images/search.gif">
				    	<td align="center">员工ID</td>
				    	<td align="center">员工姓名</td>
				    	<td align="center">所属部门名称</td>
				    	<td align="center">上班打卡时间</td>
				    	<td align="center">下班打卡时间</td>
				    	<td align="center">日期</td>
				    	<td align="center">星期</td>
				    	<td align="center">签到时间</td>
				    	<td align="center">签退时间</td> 
				    	<td align="center">迟到分钟数</td>
				    	<td align="center">早退分钟数</td>
				    	<td align="center">例外情况</td>
				    	<td align="center">是否旷工</td>
				    	<td align="center">操作选项</td>
					</tr>
					<c:forEach items="${AttendMap}" var="map">
					<c:set var="attend" value="${map.value}"></c:set>
					<tr bgcolor="#FFFFFF" onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
						<td align="center">${attend.userId }</td>
						<td align="center">${userMap[attend.userId]} </td>
						<td align="center">${attend.deptName } </td>
						<td align="center">${attend.comeTime }</td>
						<td align="center">${attend.leaveTime }</td>
						<td align="center">${map.key }</td>
						<td align="center">${attend.dayForWeek }</td>
						<td align="center" <c:if test="${attend.checkInTime == null }" >style='background:#E2EDFB'</c:if> >${attend.checkInTime }</td>
						<td align="center" <c:if test="${attend.checkOutTime == null }" >style='background:#EFEFEF'</c:if> >${attend.checkOutTime }</td>
						<td align="center">
							<c:choose><c:when test="${attend.lateTime == 0 }">-</c:when><c:otherwise>${attend.lateTime}</c:otherwise> </c:choose></td>
						<td align="center"><c:choose>
							<c:when test="${attend.earlyTime == 0 }">-</c:when><c:otherwise>${attend.earlyTime}</c:otherwise></c:choose></td>
						<td align="center">
							<c:choose><c:when test="${attend.leaveCondition != 0}"><span style="color:green;">${leaveTypeMap[attend.leaveCondition] }</span></c:when><c:otherwise>-</c:otherwise></c:choose>
						</td>
						<td align="center">
							<c:choose><c:when test="${attend.isNeglectWork == -1 && attend.leaveCondition == 0}"><div style="color:red;">旷工</div></c:when><c:otherwise>-</c:otherwise></c:choose> 
						</td>
						<td align="center"><a href="${pageContext.request.contextPath }/view/add_attend.jsp?userId=${attend.userId}&userName=${userMap[attend.userId]}&date=${map.key}" >漏打卡补充 </a>&nbsp;&nbsp;|&nbsp;&nbsp; <a href="/view/extraleave.jsp" >请假/公出补充</a></td>
					</tr>
					</c:forEach>
			</table>
			</td>
		</tr>
	</table>
	<div class="overlay"></div>

			<div id="AjaxLoading" class="showbox">
				<div class="loadingWord"><img src="/images/waiting.gif">统计中，请稍候...</div>
			</div>
			
			<div style="height:1200px;"></div>	
  <script type="text/javascript">
  var h = $(document).height();
		$(".overlay").css({"height": h });	
		function isValidateTime(){
			var $startTime = $('#startTime').val();
			var $endTime = $('#endTime').val();
			if($startTime == ''){
				$.messager.alert("提示信息","请选择起始时间","warning");
				return false;
			}
			if($endTime == ''){
				$.messager.alert("提示信息","请选择截止时间","warning");
				return false;
			}
			
			var sDate = new Date($startTime.replace(/\-/g, "\/"));
			var eDate = new Date($endTime.replace(/\-/g, "\/"));
			
			if(sDate > eDate){
				$.messager.alert("提示信息","起始时间不能大于截止时间","warning");
				return false;
			} 
			return true;
		}
		/***
			$('#datasubmit').click(function(){
				var flag = isValidateTime();
				
				if(flag){
						$("#form1").submit(function(){
								$(".overlay").css({'display':'block','opacity':'0.8'});						
								$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},200);
							});			
					}			
			});
		****/
  </script>		    		
  </body>
</html>
