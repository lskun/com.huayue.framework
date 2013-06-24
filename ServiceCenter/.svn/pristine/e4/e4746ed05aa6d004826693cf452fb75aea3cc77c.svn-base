<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cache Manager</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
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
    	var srcTime;
    	$(function(){
    		AjaxGetCache();
    	});
    	
    	function AjaxGetCache(){
    		$.ajax({
    			url:"${pageContext.request.contextPath}/manage/listCache.do",
    			type:"Get",
    			dataType:"json",
    			beforeSend : function(XMLHttpRequest) {                    	  
                    	$(".overlay").css({'display':'block','opacity':'0.8'});						
						$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},200);
                    },  
                success:function(cache){
                	var html = "";
                	html += "<table style=\"font-size:13px;\" width=100% border=1 align=center cellpadding=\"3\" cellspacing=\"1\" bgcolor=\"#555555\">";
                         html += "<tr><td height=28 colspan=5><strong style=\"color: #98CAEF\">&nbsp;&nbsp;缓存信息列表</strong></td></tr>";
                         html += "<tr><td bgcolor=#FFFFFF colspan=12><div style=\"margin:5px;\" >";
                         html += "<table style=\"font-size:13px;\" width=100% border=1 cellpadding=3 cellspacing=1 bgcolor=\"#CCCCCC\">";
                         html += "<tr height=26 bgcolor=\"#FFFFFF\" style=\"font-weight: bold;text-align:center;\" background=\"../images/search.gif\"><td width=\"5%\">缓存ID</td><td width=\"10%\">来源号码</td><td width=\"60%\">短信内容</td><td width=\"15%\">接收时间</td><td>相关操作</td></tr>";            
                         html += "<tbody>";  
                    for(var i = 0;i < cache.length; i++ ){
                    	var obj = cache[i];
                    	html += "<tr height=26 style=\"text-align:center;\" bgcolor=#FFFFFF onMouseOver=\"style.backgroundColor='#fff8d8'\" onMouseOut=\"style.backgroundColor='#ffffff';\">";
						html += "<td>"+obj.id+"</td>";
                       	html += "<td>"+obj.phoneNumber+"</td>";
                        html += "<td>"+obj.content+"</td>";
                        html += "<td>"+new Date(obj.receive_time).toLocaleString()+"</td>";
                        html += "<td><input type=\"button\" class=\"del-button\" value=\"删除\" onclick=\"deleteCache(" + obj.id + ");\" /></td>";                    
                    } 
                    html += "</tbody>";
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
    		function deleteCache(id){
    			$.messager.confirm("系统提示","确定要删除此条缓存记录吗?",function(flag){
    				if(flag){
	    				$.ajax(
	    				{
	    					url	:"${pageContext.request.contextPath}/manage/delCache.do",
	    					type:"Get",
	    					data:"id=" + id,
	    					dataType:"json",
	    					beforeSend:function(){
								$(".overlay").css({'display':'block','opacity':'0.8'});						
								$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},200);
							},
							success:function(data){
								if(data.del=="true"){
									$.messager.alert("提示信息","数据删除成功!","info");
									$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},400);			
									$(".overlay").css({'display':'none','opacity':'0'});
									AjaxGetCache();
								}else{
									$.messager.alert("提示信息","数据删除失败!","error");
									$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},400);			
									$(".overlay").css({'display':'none','opacity':'0'});
								}
							}
	    				});
	    			}
    			});
    		}
    		
    		function deleteAll(){
    			$.messager.confirm("系统提示","确定要清空所有记录吗?",function(flag){
    				if(flag){
	    				$.ajax({
	    					url:"${pageContext.request.contextPath}/manage/clear.do",
	    					type:"Get",
	    					dataType:"json",
	    					beforeSend:function(){
								$(".overlay").css({'display':'block','opacity':'0.8'});						
								$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},200);
							},
							success:function(data){
								if(data.del=="true"){
									$.messager.alert("提示信息","数据删除成功!","info");
									$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},400);			
									$(".overlay").css({'display':'none','opacity':'0'});
									AjaxGetCache();
								}else{
									$.messager.alert("提示信息","数据删除失败!","error");
									$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},400);			
									$(".overlay").css({'display':'none','opacity':'0'});
								}
							}
	    				});
    				}
    			});
    		}
    		
    		function startInterval(){
    			$.messager.confirm("系统提示","确定要开始自动刷新吗?",function(flag){
    				if(flag){
    					$("#srcTime").val("刷新中...") ;
					$("#srcTime").attr({"disabled":"disabled"});
					$("#srcTime").css({"color":"red"});
    					srcTime = setInterval(function(){
    						AjaxGetCache();
    					},10000);
    				}
    			})
    		}
    		
    		function stopInterval(){
    			$.messager.confirm("系统提示","确定要停止刷新吗?",function(flag){
    				if(flag){
    					$("#srcTime").val("开始刷新") ;
					$("#srcTime").css({"color":"white"});
					$("#srcTime").removeAttr("disabled");
    					clearInterval(srcTime);
    				}
    			})
    		}
    </script>
</head>
<body bgcolor="#eef2fb">
    <div class="box-positon">
		<div class="rpos">当前位置: 短信过滤 - 列表</div>
		<div class="clear"></div>
	</div>
	<div id="divResult" style="margin:10px;font-family: 宋体,Arial Narrow,arial,serif;font-size: 13px;"></div>
	<div style="float:left;margin-left:8px;">
		<input id="srcTime" type="button" class="sblue_btn" value="自动刷新" onclick="startInterval()">
		<input type="button" class="sblue_btn" value="停止刷新" onclick="stopInterval()">
	</div> 
	<div style="float:right;margin-right:8px;"><input type="button" value="清空缓存" class="sblue_btn" onclick="deleteAll()"></div>
	 <div class="overlay"></div>
			<div id="AjaxLoading" class="showbox">
				<div class="loadingWord"><img src="/images/waiting.gif">加载中，请稍候...</div>
			</div>			
			<div style="height:1200px;"></div>
</body>
</html>