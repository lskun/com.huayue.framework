<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户重复资料列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />	
	<link rel="stylesheet" type="text/css" href="/css/style.css" />
	<link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.1.2.2.js"></script>	
	<script type="text/javascript" src="/js/Address.js"></script>
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
			
		 function checkJunk(obj) {
                var ids = document.getElementsByName("ids");
                if(obj.checked) {
                    for(var i=0; i<ids.length; i++) {
                        ids[i].checked="checked";
                    }
                } else {
                    for(var i=0; i<ids.length; i++) {
                    	ids[i].checked="";
                    }
                }
           }
			
			function simpleDel(id){
				if(confirm('确定要删除吗?')) {
					var val = new Array(id,null);
					$.ajax({
						url: "${pageContext.request.contextPath}/crm/delete.do",
						type: "Get",
						data: "ids=" + val,
						dataType:"json",
						beforeSend:function(){
							$(".overlay").css({'display':'block','opacity':'0.8'});						
							$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},10000);
						},
						success:function(data){
							if(data.del=="true"){
								$.messager.alert("提示信息","数据删除成功!","info");
								$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},400);			
								$(".overlay").css({'display':'none','opacity':'0'});
								location.href='${pageContext.request.contextPath}/crm/duplicateRecordManager.do';
							}else{
								$.messager.alert("提示信息","数据删除失败!","error");
								$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},400);			
								$(".overlay").css({'display':'none','opacity':'0'});
							}
						}
					});
				}				
			}
			
			function optDelete(){
				var ids = $('[name=ids]');
			    var tmp = 0;
			    var val = "";
				for(var i=0; i<ids.length; i++){
						if(ids[i].checked) {
							tmp+=1;
							val += ids[i].value;
							val +=",";
						}
					}				
				if(tmp == 0 ) {
					$.messager.alert("提示信息","请选择要删除的记录!","warning");
					return;
				}
				//alert(val);
				$.ajax({
					url: "${pageContext.request.contextPath}/crm/delete.do",
					type: "Get",
					data: "ids=" + val,
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
							location.href='${pageContext.request.contextPath}/crm/duplicateRecordManager.do';
						}else{
							$.messager.alert("提示信息","数据删除失败!","error");
							$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},400);			
							$(".overlay").css({'display':'none','opacity':'0'});
						}
					}
				});	
			}
			
	</script>
  </head>
  
  <body>
  	<div class="box-positon">
		<div class="rpos">当前位置: 客户重复资料列表 </div>
		<div class="clear"></div>
	</div>
	<div style="margin:10px;">
	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#555555">
        <tr background="../images/bg.gif">
            <td height="26" colspan="8">
                &nbsp;&nbsp;<strong style="color: #98CAEF">客户重复资料信息</strong>
            </td>                         
        </tr>     
        <tr>
            <td bgcolor="#FFFFFF" id="_chl_0" colspan="2">
				<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
				    <tr bgcolor="#FFFFFF" style="font-weight: bold" background="../images/search.gif">
				    	<td width="2%" align="center"><input id="selectAll" name="selectAll" type="checkbox" onclick="checkJunk(this);"></td>
				    	<td align="center">ID</td>
				    	<td align="center">单位名称</td>
				    	<td align="center">姓名</td>
				    	<td align="center">性别</td>
				    	<td align="center">办公电话</td>
				    	<td align="center">手机</td>
				    	<td align="center" width="8%">相关操作</td>
					</tr>
					<c:forEach items="${recordsMap}" var="map">
						<c:set var="list" value="${map.value}"></c:set>
						
						<c:forEach items="${list}" var="client">
							<tr bgcolor="#FFFFFF" onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
								<td align="center"><input type="checkbox" value="${client.id }" name="ids"></td>
								<td align="center">${client.id }</td>
								<td align="center">${client.unit }</td>
								<td align="center">${map.key }</td>
								<td align="center">${client.sex }</td>
								<td align="center">${client.phone }</td>
								<td align="center">${client.mobile }</td>
								<td align="center"><a href='/crm/editClient.do?id=${client.id }'><span style="color:blue;">编辑</span></a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='/crm/showInfo.do?id=${client.id }'><span style="color:blue;">查看</span></a>
								  &nbsp;&nbsp;|&nbsp;&nbsp;<a onclick="simpleDel('${client.id}');"><span style="color:blue;">删除</span></a></td>
							</tr>
						</c:forEach>				
					</c:forEach>
					<tr bgcolor="#FFFFFF"><td colspan="8"><div style="float:left;margin-left:5px;"><input type="button" class="sblue_btn" value="删除" onclick="optDelete();" /></div></td></tr>
				</table>
			</td>
		</tr>
	</table> 
	</div>
	
	<div id="divResult" style="margin:10px;font-family: 宋体,Arial Narrow,arial,serif;font-size: 13px;"></div>
	 <div class="overlay"></div>
			<div id="AjaxLoading" class="showbox">
				<div class="loadingWord"><img src="/images/waiting.gif">加载中，请稍候...</div>
			</div>
</body>