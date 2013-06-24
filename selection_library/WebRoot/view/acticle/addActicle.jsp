<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>文章信息添加</title>
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<link rel="stylesheet" type="text/css" href="/css/theme.css" />
	<link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
	<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="/js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript">
		function submitForm(){
			$.messager.confirm('提示信息','确定要提交该表单吗?',function(r){
					if(verifyForm(r)){
						$('#_form').submit();
					}
			});
		}
		
		function verifyForm(isValid){
			if($('#name').val()==''){
				$.messager.alert('提示信息','请输入文章篇名!','warning');
				document._form.name.focus();
				isValid = false;
			}			
			return isValid;
		}
		
		$(function(){
			$('#doAction').click(function(){
				submitForm();
			});
		});
	</script>
</head>
<body>
    <div class="box-positon">
		<div class="rpos">当前位置: 文章 - 添加</div>
		<div class="clear"></div>
	</div>
    <div class="body-box" style="margin-top:10px;">
    	<form name="_form" id="_form" method="post" enctype="multipart/form-data" action="/acticle/addActicle.do">
        		 <table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
        		   <tr>
        		     <td width="13%" style="background-color:#E6F2FE">文章篇名:</td>
        		     <td ><input id="name" name="name" maxlength="50" class="input_box" type="text" size="40" style="vertical-align: middle;color: #555555;width:50%;float:left;margin-left:5px;"/><span style="color:red;float:left;">&nbsp;*</span></td>
        		   	 <td width="13%" style="background-color:#E6F2FE">作者:</td>
        		     <td width="37%"><input name="author" maxlength="10" class="input_box" id="post_code" type="text" style="width:25%;float:left;margin-left:5px;"/></td>
        		   </tr>
        		   <tr>
        		     <td width="13%" style="background-color:#E6F2FE">国别:</td>
        		     <td>  
        		     	<select name="countryId" style="float:left;margin-left:5px;width:20%;">
        		     		<option value="-1">--请选择--</option>
        		     		<c:forEach items="${countries}" var="item" varStatus="status">
        		     			<option value="${status.count - 1}">${item}</option>
        		     		</c:forEach>
        		     	</select>
					</td>
					<td width="13%" style="background-color:#E6F2FE">体裁:</td>
					<td>			
						<select name="genre" style="float:left;margin-left:5px;width:20%;">
							<option value="">--请选择--</option>
							<c:forEach items="${genres}" var="v">
								<option value="${v}">${v}</option>
							</c:forEach>
						</select>	
        		   	</td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE;">表达方式: </td>
        		     <td style="text-align:left">
        		     	<select name="presentation" style="width:20%;margin-left:5px;">
        		     		<c:forEach items="${presentations}" var="p">
        		     			<option value="${p}">${p}</option>
        		     		</c:forEach>
        		     	</select>
        		     </td>
        		     <td style="background-color:#E6F2FE">来源: </td>
        		     <td>
        		     	<input name="origin" class="input_box" type="text" style="vertical-align: middle;color: #555555;width:25%;margin-left:5px;float:left"/>
        		     </td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">一级内容:</td>
        		     <td style="text-align:left">
						<select style="float:left;margin-left:5px;width:20%;" name="firConId">
							<c:forEach items="${contentMap}" var="v">
								<option value="${v.key}" >${v.value }</option>
							</c:forEach>							    
						</select>
        		     </td>
        		     <td style="background-color:#E6F2FE">二级内容：</td>
        		   	 <td style="text-align:left">
        		   	 	<select style="float:left;margin-left:5px;width:20%;" name="secConId">
							<c:forEach items="${contentMap}" var="v">
								<option value="${v.key}" >${v.value }</option>
							</c:forEach>							    
						</select>
        		   	 </td>
        		     
        		   </tr>
        		   <tr>        		     
        		     <td width="13%" style="background-color:#E6F2FE">描述：</td>
	        		     <td style="text-align:left" width="37%">
	        		     	<input class="input_box" name="comment" maxlength="100" type="text" size="40" style="vertical-align: middle;color: #555555;width:95%;margin-left:5px">
	        		     </td>
        		     <td width="13%" style="background-color:#E6F2FE">名家名篇:</td>
        		     <td style="text-align:left" width="37%">
        		     	<select name="famousMasterpiece" style="float:left;margin-left:5px;width:20%;">
        		     		<c:forEach items="${famousMasterPieces}" var="fmp">
        		     			<option value="${fmp}">${fmp}</option>
        		     		</c:forEach>
        		     	</select>
        		     </td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">时代:</td>
        		     <td style="text-align:left">
        		     	<select name="times" style="float:left;margin-left:5px;width:20%;">
        		     		<c:forEach items="${times}" var="t">
        		     			<option value="${t}">${t}</option>
        		     		</c:forEach>
        		     	</select>
        		     </td>
        		     <td style="background-color:#E6F2FE">版本:</td>
        		     <td style="text-align:left"><input name="version" type="text" class="input_box" style="width:25%;float:left;margin-left:5px;"></td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">文件上传:</td>
        		     <td style="text-align:left" colspan="3">
        		     <span id="mfc" style="position:relative;display:block;width:300px;*width:300px;">   
        		      	 <input name="fileName" id="mediaFileText" class="input_box" style="margin-left:5px;margin-right:5px;width:50%" type="text">
        		      	 <input class="browse" type="button" value="浏览">
        		      	 <input id="mediaFile" type="file" style="height:24px;opacity:0;filter:alpha(opacity=0);position:absolute;right:79px;top:2px;" name="file" size="25" onchange="$('#mediaFileText').val(this.value)"> 
        		      	 <div style="color:green">(文件大小不要超过10M)</div>  	     	
        		     	<!-- <input name="file" type="file" class="input_box" style="width: 460px; height: 25px;" size="54">  -->
        		     </span>
        		     </td>        		     
        		   </tr>        		   
        		 </table>      		 
        		 <div style="text-align:center;margin-top:10px;"><input style="cursor: pointer;" type="button" class="submit" value="提交" id="doAction"/>
        		 	&nbsp;&nbsp;<input type="reset" class="reset" value="重置" style="cursor: pointer;"></div>
        		 </form>
    </div>
</body>
</html>