<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
  <head>    
    <title>数据导出</title>
    
    <link rel="stylesheet" type="text/css" href="../css/style.css">
	<script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<script type="text/javascript">
		function $(idx){return document.getElementById(idx);}
		function doExport(){
			location.href="/servlet/ExportToCSV?unit=" + encodeURI($('unit').value) + "&is_stay=" + $('is_stay').value + "&is_together=" + $('is_together').value + "&start_time=" + $('start_time').value + "&end_time=" + $('end_time').value + "&standard=" + $('standard').value;
		}
	</script>
  </head>
  
  <body style="background-color:#EEF2FB">
	<div class="box-positon">
		<div class="rpos">当前位置: 报名后台管理 - 数据导出</div>
		<div class="clear"></div>
	</div>
   	<div style="margin: 5px 0px;">
  		<input id="pageIndex" type="hidden" name="pageIndex" value="1">
  		<table width="95%" border="0" align="center" cellpadding="3" cellspacing="1">
  		<tr><td>		  		
  		 <strong>单位名称：</strong><input id="unit" name="unit" type="text" class="input_box" style="width: 200px;" /> 	
  		 <strong>收费标准: </strong><select id="standard" name="charge_standard"><option value="-1">请选择</option><option value="380">380</option><option value="480">480</option></select> 		  		 	
  		 <strong>是否安排住宿：</strong><select id="is_stay" name="is_stay"><option value="">请选择</option><option value="0">否</option><option value="1">是</option></select>
  		 <strong>是否同意调剂：</strong><select id="is_together" name="is_together"><option value="">请选择</option><option value="0">否</option><option value="1">是</option></select>
  		 <strong>会议类别:</strong><select name="category_id"><option value="">请选择</option><option value="1">第三届新学校论坛</option></select>&nbsp;  		 
  		 <strong>报名起始时间：</strong><input id="start_time" name="start_time" type="text" class="input_box" size="20" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
  		 <strong>报名截止时间：</strong><input id="end_time" name="end_time" type="text" class="input_box" size="20" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />  		 
  		 <input value="导出CSV文件" class="blue_btn" type="button" onclick="doExport();"></td></tr>
  		</table>
  	</div>
  </body>
</html>
