<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
	String result = String.valueOf(request.getAttribute("result"));
	if(!"OK".equals(result))
	{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", "群发短信初始化失败! Caused By" + result, "/sms/multi_send.jsp"));
		return ;
	}
	String[] nums = (String[])request.getAttribute("nums");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>       
    <title>短信群发</title>   
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="/js/core.js"></script>
	<style type="text/css">
		.graphbox{ 
			border:1px solid #e7e7e7; 
			padding:10px 200px; 
			background-color:#f8f8f8; 
			margin:5px 0; 
			text-align="center";
		} 
		.graph{ 
			position:relative; 
			background-color:#F0EFEF; 
			border:1px solid #cccccc; 
			padding:2px; 
			font-size:12px; 
			height:18px;	
		} 
		.graph .blue{background-color:#3399CC; } 
		
	</style>
	<script type="text/javascript" src="/js/module.js"></script>
	<script type="text/javascript">
		var len ,count = 0,idx = 0 ,lose = 0;
		var nums = new Array();
		function $(id) { return document.getElementById(id); }
		<% for(int i = 0 ; i < nums.length; i++ )  { %>
			nums[<%=i %>] = <%=nums[i] %>;
		<% } %>
		
		len = nums.length;
				
		function Go() { SendSMS(); }
		
		function SendSMS()
		{
			new Module().Load((function(args, el, text)
		    {
		    	idx += 1;
		        if ('OK' == text){ count += 1;}else{ lose += 1;}
		        
		        var w = parseInt(100 * (idx) / len);				
				$('progress').style.width = w + '%';
				$('idx').innerHTML = '短信发送进度完成 ' + w + '%';
				$('len').innerHTML = '短信群发总数:' + format(len);
				$('success_count').innerHTML = '成功发送数量:' + format(count);
				$('lose_count').innerHTML = '发送失败数量:' + format(lose);
				if(w > 100 || idx == (len )) { return; }
		        SendSMS();
		    }), null, null, '/servlet/MultiSendControl?mobile=' + nums[idx] , AJAX_TYPE_TEXT );
		    		    
		    // new Module().Load(func, args, el, url, type);
		}
		
		function format(number)
		{
			/*
			var tmp = "", tags = "" ;
			tmp += number;
			for(i = 0;i < space - tmp.length; i++)
			{
				tags += "&nbsp;" ;
			}
			return tags;
			*/
			return ("xxxxxxxxxxxxxxxxxxxxxx" + number).replace(/^.*(.{10})$/gi, "$1").replace(/x/gi, "&nbsp;");
		}
		
	</script>
  </head> 
  <body onload="javascript:Go()" bgcolor="#ffffff">
  <div class="graphbox">  	
  	<div id="idx" class="graph"></div> 
	<div class="graph">			
		<div class="blue" style="width:80px;height:18px;" id="progress"></div>
    </div>
    <div class="graph" id="len"></div>
    <div class="graph" id="success_count"></div>
    <div class="graph" id="lose_count"></div>
  </div>
  <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
  </body>
</html>
