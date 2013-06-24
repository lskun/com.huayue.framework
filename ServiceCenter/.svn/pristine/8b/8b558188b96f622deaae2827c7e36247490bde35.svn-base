<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>在线互动</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="/css/style.css" rel="stylesheet" type="text/css" />
    <script language="javascript" type="text/javascript" src="/js/core.js"></script>
    <script language="javascript" type="text/javascript" src="/js/dialog.js"></script>
    <script type="text/javascript" src="/js/module.js"></script>
	<script language="javascript" type="text/javascript">
		function $(obj){return document.getElementById(obj);}

		function loadData(){
			//var index = $('index').options[$('index').selectedIndex].text;
			var index = $('index').value;
			if(index == null || index == '') {
				alert('请选择会场!');
				return false;
			}
			new Module().Load(
			(function(args,el,text){
				var list = eval(text);
        		var shtml = '<table width="99%" cellpadding="4" cellspacing="1" align="right" bgcolor="#ffffff">';
        		shtml += '<tr><td>手机号码</td><td>内容</td><td>时间</td></tr>' ;
				for(var i = 0; i < list.length - 1; i ++){
					shtml += '<tr><td> ' + list[i].Mobile + '</td><td>' + list[i].Content + '</td><td>' + list[i].Time + '</td></tr>';
				}
				shtml = '</table>';
				$('content').innerHTML = shtml;
			}),null,null,'/servlet/InteractiveController?index=' + index ,AJAX_TYPE_TEXT );
		}
	</script>
	
  </head>
  
  <body>
  	会场选择<select id="index"><option value="">请选择</option><option value="1">会场A</option><option value="2">会场B</option><option value="3">会场C</option></select>
  	<br/>
  	<div id="content"></div>
  	<input type="button" onclick="loadData();" value="刷新">
  	
  </body>
</html>
