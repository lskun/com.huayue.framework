<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>加班统计</title>
	<link rel="stylesheet" type="text/css" href="/css/admin.css" />
    <link rel="stylesheet" type="text/css" href="/css/theme.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" /> 
    <link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function openWindow(){
			$('#w').window({
				title: '加班明细',
    			width: 800,
    			modal: true,
                shadow: true,
                closed: true,
                //height: 530,
                resizable:false
			});			
		}
		
		function closeWindow(){
			$('#w').window('close');
		}
		
		function showDetail(id){
			$('#w').window('open');
			$.ajax({
				url:'${pageContext.request.contextPath}/extra/showOverWorkDetail.do',
				type:'get',
				data:'id=' + id + '&sTime=' + $('#sTime').val() + '&eTime=' + $('#eTime').val(),
				dataType:'json',
				beforeSend: function(XMLHttpRequest){
    				$.messager.progress({
    					title: 'please waiting...',
    					msg: 'Loading data'
    				});
    			},
    			success:function(data){
    				var html = "";
    				html += "<table style=\"font-size:13px;\" width=100% border=1 align=center cellpadding=\"3\" cellspacing=\"1\" bgcolor=\"#555555\">";
    				html += "<tr><td height=28><strong style=\"color: #98CAEF\">&nbsp;&nbsp;加班明细列表</strong></td></tr>";;
    				html += "<tr><td bgcolor=#FFFFFF colspan=9><div style=\"margin-top:5px;\" >";
    				html += "<table style=\"font-size:13px;\" width=100% border=1 cellpadding=3 cellspacing=1 bgcolor=\"#EFEFEF\">";
    				html += "<tr height=26 bgcolor=\"#FFFFFF\" style=\"font-weight: bold;text-align:center;\" background=\"../images/search.gif\"><td width=\"4%\">ID</td><td>加班起始时间</td><td>加班截止时间</td><td>个人合计(小时)</td><td>原因</td><td>审核时间</td></tr>";
    				html += "<tbody>";
    				for(var i = 0;i < data.length;i++ ){
    					var obj = data[i];
    					html += "<tr height=26 style=\"text-align:center;\" bgcolor=#FFFFFF onMouseOver=\"style.backgroundColor='#fff8d8'\" onMouseOut=\"style.backgroundColor='#ffffff';\">";
    					html += "<td>"+ obj.id +"</td>";
    					html += "<td>"+ obj.startTime +"</td>";
    					html += "<td>"+ obj.endTime+"</td>";
    					html += "<td>"+ obj.totalTime / 60+"</td>";
    					html += "<td>"+ obj.reason+"</td>";
    					html += "<td>"+ obj.applyTime + "</td></tr>";
    				}
    				html += "</tbody>";
                    html += "</table></td></tr></td></tr></table>";
					$.messager.progress('close');
                    $('#divResult').html("");
                    $('#divResult').html(html);
    			},
    			error: function (XMLHttpRequest, textStatus, errorThrown) {                     	
  						$.messager.alert("提示信息","数据加载失败"+ errorThrown,"error");
                    }
    			
			});
		}
		
		$(function(){
			openWindow();
			
			$('#btnEp').click(function(){
				closeWindow();
			});
			
			$('#btnCancel').click(function(){
				closeWindow();
			})
		});
		
		
	</script>
  </head>
  
  <body>
    <div class="box-positon">
		<div class="rpos">当前位置: 加班统计 </div>
		<div class="clear"></div>
	</div><br>
	<div style="margin:10px 42px;">
		<form action="/extra/totalOverWork.do" method="post">
			<strong>查询时间段 ：</strong> <input id="sTime" name="sTime"  value="<c:if test="${sTime != null }">${sTime }</c:if>" type="text" class="input_box" size="30" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/> 
			&nbsp;<strong>到</strong>&nbsp; <input id="eTime" name="eTime" value="<c:if test="${eTime != null }">${eTime }</c:if>" type="text" class="input_box" size="30" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>		
			<input type="submit" value="查询" class="sblue_btn" />
		</form>			
	</div>
	<div style="margin:5px">
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
				    	<td align="center">总计加班(小时)</td>	
				    	<td align="center">统计起始时间</td>
				    	<td align="center">统计截止时间</td>
				    	<td align="center">操作选项</td>	    					    		    	
				    </tr>
				    <c:forEach items="${map}" var="entry">
				    	<tr bgcolor="#FFFFFF" onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
				    		<td align="center">${entry.key}</td>
				    		<td align="center">${userMap[entry.key] }</td>
				    		<td align="center">${entry.value /60}</td>
				    		<td align="center">${sTime}</td>	    	
				    		<td align="center">${eTime}</td>
				    		<td align="center"><input value="查看明细" type="button" class="blue_btn" onclick="javascript:showDetail(${entry.key})"/></td>	    					    		
				    	</tr>
				    </c:forEach>
				</table>
			</td>
		</tr>
	</table>
	</div>
	
	<!-- jiaban mingxi chuangkou -->			
	<div id="w" class="easyui-window" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 800px; height: 500px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            	<div id="divResult" style="margin-top:5px;font-family: 宋体,Arial Narrow,arial,serif;font-size: 13px;"></div>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton"  href="javascript:void(0)" >
                                确定</a> <a id="btnCancel" class="easyui-linkbutton"  href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>        
  </body>
</html>
