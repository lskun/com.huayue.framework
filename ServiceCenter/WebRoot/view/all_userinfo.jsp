<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Alluser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/css/dev.css" />
	<link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
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
    <script type="text/javascript">
    	
    	function AjaxGetData(){
    		$.ajax({
    			url: "${pageContext.request.contextPath}/attend/show.do",
    			type: "Get",
    			beforeSend : function(XMLHttpRequest) {                    	  
                    	$(".overlay").css({'display':'block','opacity':'0.8'});						
						$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},200);
                    },  
                success: function(data){
                	var html = "";
                     
                         html += "<table style=\"font-size:13px;\" width=100% border=1 align=center cellpadding=\"3\" cellspacing=\"1\" bgcolor=\"#555555\">";
                         html += "<tr><td height=28 colspan=11><strong style=\"color: #98CAEF\">&nbsp;&nbsp;员工考勤信息列表</strong></td></tr>";
                         html += "<tr><td bgcolor=#FFFFFF colspan=12><div style=\"margin:5px;\" >";
                         html += "<table style=\"font-size:13px;\" width=100% border=1 cellpadding=3 cellspacing=1 bgcolor=\"#CCCCCC\">";
                         html += "<tr height=26 bgcolor=\"#FFFFFF\" style=\"font-weight: bold;text-align:center;\" background=\"../images/search.gif\"><td>员工ID号</td><td>考勤号码</td><td>姓名</td><td>性别</td><td>验证方式</td><td>所属部门</td><td>管理员标志</td><td>考勤有效</td><td>计迟到</td><td>计早退</td><td>计加班</td><td>假日休息</td><td>操作选项</td></tr>";            
                         html += "<tbody>";  
                    for(var i = 0; i < data.length;i++ ){
                    	var obj = data[i];
                    	
                    	   html += "<tr height=26 style=\"text-align:center;\" bgcolor=#FFFFFF onMouseOver=\"style.backgroundColor='#fff8d8'\" onMouseOut=\"style.backgroundColor='#ffffff';\">";
                       	   html += "<td>"+obj.userId+"</td>";
                       	   html += "<td>"+obj.badgeNumber+"</td>"; 
                           html += "<td>"+obj.name+"</td>";
                           html += "<td>"+obj.gender+"</td>";
                           //html += "<td>"+obj.verificationMethod+"</td>";
                           html += "<td>-</td>";
                           html += "<td>"+obj.deptName +"</td>";
                           html += "<td>"+(obj.securityFlags == 0 ? '-' : obj.securityFlags)+"</td>" ;
                           html += "<td>"+(obj.att == 0 ? '否' : '是')+ "</td>";
                           html += "<td>"+(obj.inLate == 1 ? '是' : '否') + "</td>";
                           html += "<td>"+(obj.outEarly == 1 ? '是' : '否')+ "</td>";
                           html += "<td>"+(obj.overTime == 1 ? '是' : '否')+ "</td>";
                           html += "<td>"+(obj.holiday == 1 ? '是' : '否')+ "</td>";
                           html += "<td><a href='/attend/data.do?id="+obj.userId+"'><span style=\"color:blue;\">编辑</span></a>&nbsp;|&nbsp;<a id =\"showAttend\" href='/view/attend_calculate.jsp?id=" + obj.userId + "&deptName=" + obj.deptName + "&userName=" + obj.name + "'><span style=\"color:blue;\">查看考勤记录</span></a></td>";
                           html += "</tr>";
                       }
                       html += "</tbody>";
                       html += "</table></td></tr>";
                       html += "</table>";
                       
                       $(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},400);			
					   $(".overlay").css({'display':'none','opacity':'0'});			
					     
                       $('#divResult').html("");
                       $('#divResult').html(html); 
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    
                        $(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},400);			
						$(".overlay").css({'display':'none','opacity':'0'});		
  						$.messager.alert("提示信息","数据加载失败"+ errorThrown,"error");
                    }    
    			
    		});
    	}
    	
    	$(function(){    		
    		AjaxGetData();    		
    	});
    </script>	
  </head>
  
  <body bgcolor="#eef2fb">
    <div class="box-positon">
		<div class="rpos">当前位置: 员工考勤信息管理 - 列表</div>
		<div style="float:right;margin-right:5px;margin-top:3px;"><input class="add" type="button" onclick="location.href='${pageContext.request.contextPath}/view/client_add.jsp'" value="添加"></div>
		<div class="clear"></div>
	</div>
	<div id="divResult" style="margin:10px;font-family: 宋体,Arial Narrow,arial,serif;font-size: 13px;"></div>
	
	
	 <div class="overlay"></div>

			<div id="AjaxLoading" class="showbox">
				<div class="loadingWord"><img src="/images/waiting.gif">加载中，请稍候...</div>
			</div>
			
			<div style="height:1200px;"></div>
  </body>
</html>
