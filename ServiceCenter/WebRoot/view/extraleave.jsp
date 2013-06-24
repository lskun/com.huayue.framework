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
    
    <title>请假/公出申请</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
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
		<div class="rpos">当前位置: 请假/公出申请 </div>
		<div class="clear"></div>
	</div>
	<div class="body-box">
	<div style="margin:10px">
        	<form name="_form" id="_form" method="post" action="/extra/add.do" onsubmit="return isValidateTime();">
        		 <table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
        		 	<tr>
        		 		<td width="13%" style="background-color:#E6F2FE">起始时间:</td>
        		 		<td width="37%"><input id="startTime" name="startTime" type="text" class="input_box" style="width:25%;float:left;margin-left:5px;" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
        		 		<td width="13%" style="background-color:#E6F2FE">结束时间:</td>
        		 		<td width="37%"><input id="endTime" name="endTime" type="text" class="input_box" style="width:25%;float:left;margin-left:5px;" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
        		 	</tr>
        		 	<tr>
        		 		<td width="13%" style="background-color:#E6F2FE">
        		 			请选择公出或请假的类型:
        		 		</td>
        		 		<td>
        		 			<select id="type" style="float:left;margin-left:5px;" name="dataId" onChange="javascript:checkForType(this)">
        		 				<option value="">---请选择---</option>       		 	
        		 				<c:forEach items="${leaveTypeMap}" var="map">
        		 					<option value="${map.key }">${map.value }</option>
        		 				</c:forEach>
        		 			</select>
        		 			<div style="float:right;display:none" id="qj">
        		 				<input type="text" style="width:40px;margin-left:60px" class="input_box" name="out_Time" id="qj_time" /><span style="color:green">请假小时(请输入阿拉伯数字)</span>
        		 			</div>
        		 		</td>
        		 		<td width="13%" style="background-color:#E6F2FE">
        		 			原因:
        		 		</td>
        		 		<td>
        		 			<input name="reason" maxlength="50" id="unit" class="input_box" type="text" size="40" style="vertical-align: middle;color: #555555;width:50%;float:left;margin-left:5px;"/>
        		 		</td>
        		 	</tr>  		 			       		 
        		 </table>
        		  <div style="float:right;margin-top:10px;"><input type="button" value="返回上一层" class="sblue_btn" onclick="javascript:location.href='${pageContext.request.contextPath}/attend/req.do';" /></div>&nbsp;&nbsp;&nbsp;&nbsp;
        		 <div style="float:right;margin-right:10px;margin-top:10px"><input id="datasubmit" class="sblue_btn" type="submit" value="提交申请" onclick="return confirm('确认提交该申请单吗?')" /></div>
        		
        	</form>
        	</div>
     </div>   		 
  </body>
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
			var type = $('#type').val();
			if( type== 1 || type == 2 || type ==3){
				var time = $('#qj_time').val(); 
				if( time == ''){
					$.messager.alert('提示信息','该项不能为空!','warning');
					return false;
				}
				if(!time.match(/(\d+.\d+)|(\d+)/)){
					$.messager.alert("提示信息","请假时间格式错误","warning");
					return false;
				}
			}
			return true;
		}
		
		function checkForType(obj){
			var type= obj.value;
			if(type == 1 || type == 2 || type == 3){
				$('#qj').css({'display':'block'});
			}else{
				$('#qj').css({'display':'none'});
			}
		}
			$('#datasubmit').click(function(){
				var flag = isValidateTime();
				
				if(flag){
						$("#_form").submit(function(){
								$(".overlay").css({'display':'block','opacity':'0.8'});						
								$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},200);
							});			
					}			
			});
		
  </script>
</html>