<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户信息导出</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<script type="text/javascript" src="/js/Address.js"></script>
	<link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.1.2.2.js"></script>
    <style type="text/css">
    	*{margin:0;padding:0;list-style-type:none;}
		a,img{border:0;}
		.demo{margin:100px auto 0 auto;width:400px;text-align:center;font-size:18px;}
		.demo .action{color:#3366cc;text-decoration:none;font-family:"微软雅黑","宋体";}
		
		.overlay{position:fixed;top:0;right:0;bottom:0;left:0;z-index:998;width:100%;height:100%;_padding:0 20px 0 0;background:#f6f4f5;display:none;}
		.showbox{position:fixed;top:0;left:50%;z-index:9999;opacity:0;filter:alpha(opacity=0);margin-left:-80px;}
		*html,*html body{background-image:url(about:blank);background-attachment:fixed;}
		*html .showbox,*html .overlay{position:absolute;top:expression(eval(document.documentElement.scrollTop));}
		#AjaxLoading{border:1px solid #8CBEDA;color:#37a;font-size:12px;font-weight:bold;}
		#AjaxLoading div.loadingWord{width:180px;height:50px;line-height:50px;border:2px solid #D6E7F2;background:#fff;}
		#AjaxLoading img{margin:10px 15px;float:left;display:inline;}
    </style>
	
  </head>
  
  <body>
    <div class="box-positon">
		<div class="rpos">当前位置: 客户档案管理 - 客户信息导出</div>
		<div class="clear"></div>
	</div>
	<div style="margin:10px;padding:10px;border: 1px solid #98CAEF;">
		<form name="form1" id="form1" action="/data/exportToFile.do" method="post">
			所在地:
			<select id="deliverprovince" name="deliverprovince"></select>
			<select id="delivercity" name="delivercity"></select>
			<select id="deliverarea" name="deliverarea"></select>
			<script>
				window.onload=function(){
					new PCAS("deliverprovince","delivercity","deliverarea", "","","");
				}
			</script>
			单位名称：<input id=unit name="unit" class="input_box" type="text" style="height: 20px;line-height: 20px;width:200px;vertical-align: middle;color: #555555;"> 
			<br/><br/>
			<span style="float:left;">客户类别：</span><c:forEach items="${categories}" var="v">
        		     		<input type="checkbox" name="categoryId" id="${v.key}" value="${v.key }" style="width:3%;float:left" /><span class="spa"><label for="${v.key}">${v.value}</label></span>
        		     </c:forEach>
			<input id="exportsubmit" class="query" type="submit" value="导出" />
		</form>			
	</div>
	<div class="overlay"></div>

			<div id="AjaxLoading" class="showbox">
				<div class="loadingWord"><img src="/images/waiting.gif">导出中，请稍候...</div>
			</div>
			
			<div style="height:1200px;"></div>
	<script type="text/javascript">
		$("#exportsubmit").click(function(){
				$("#form1").submit(function(){
				$(".overlay").css({'display':'block','opacity':'0.8'});						
				$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},200);

				setTimeout(function(){
					
					$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},400);
					
					$(".overlay").css({'display':'none','opacity':'0'});
					
				},2000);
			});
			});
			
		
	</script>
  </body>
</html>
