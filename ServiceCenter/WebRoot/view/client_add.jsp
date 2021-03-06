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
    
    <title>客户信息添加</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<script type="text/javascript" src="/js/Address.js"></script>
	<script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
  </head>
  
  <body>
    <div class="box-positon">
		<div class="rpos">当前位置: 客户档案管理 - 添加</div>
		<div class="clear"></div>
	</div>
    <div class="body-box">
        	<form name="_form" id="_form" method="post" action="/crm/save.do">
        		 <table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
        		   <tr>
        		     <td width="13%" style="background-color:#E6F2FE">单位名称:</td>
        		     <td ><input name="unit" maxlength="50" id="unit" class="input_box" type="text" size="40" style="vertical-align: middle;color: #555555;width:50%;float:left;margin-left:5px;"/><span style="color:red;float:left;">&nbsp;*</span><div id="unit_prompt" style="float:left;"></div></td>
        		   	 <td width="13%" style="background-color:#E6F2FE">邮编:</td>
        		     <td width="37%"><input name="postCode" maxlength="10" class="input_box" id="post_code" type="text" style="width:25%;float:left;margin-left:5px;"/><div id="post_code_prompt" style="float:left;"></div></td>
        		   </tr>
        		   <tr>
        		     <td width="13%" style="background-color:#E6F2FE">所在地:</td>
        		     <td>  
        		     <div style="float:left;margin-left:5px;">
        		     	<select id="deliverprovince" name="deliverprovince"></select>
					 	<select id="delivercity" name="delivercity"></select>
						<select id="deliverarea" name="deliverarea"></select>
						<script>
							window.onload=function(){new PCAS("deliverprovince","delivercity","deliverarea", "","","");}
						</script>
					</div>	
						</td><td width="13%" style="background-color:#E6F2FE">单位地址:</td><td>			
							<input class="input_box" maxlength="50" name="address" type="text" size="40" style="vertical-align: middle;color: #555555;width:50%;margin-left:5px;"/><span style="color:red;float:left;">&nbsp;*</span><div id="unitAddress_prompt" style="float:left;"></div>       		    
        		   		</td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE;">姓名: </td>
        		     <td style="text-align:left"><input class="input_box" type="text" name="name" style="width:15%;margin-left:5px;"></td>
        		     <td style="background-color:#E6F2FE">性别: </td>
        		     <td><select name="sex" style="float:left;margin-left:5px;"><option value="男">&nbsp;男&nbsp;</option><option value="女">&nbsp;女&nbsp;</option></select></td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">民族:</td>
        		     <td style="text-align:left">
						<select style="float:left;margin-left:5px;" name="nation">
						<c:forEach items="${nations}" var="v">
							<option value="${v.key}" >${v.value }</option>
						</c:forEach>							    
						   </select>
        		     </td>
        		     <td style="background-color:#E6F2FE">参加过的活动</td>
        		   	 <td style="text-align:left"></td>
        		     
        		   </tr>
        		   <tr>        		     
        		     <td width="13%" style="background-color:#E6F2FE">称呼：</td>
        		     <td style="text-align:left" width="37%"><input class="input_box" name="callName" maxlength="100" type="text" size="40" style="vertical-align: middle;color: #555555;width:25%;margin-left:5px"><span style="color:red;float:left;">&nbsp;*</span><div id="invoiceAddr_prompt" style="float:left;"></div></td>
        		     <td width="13%" style="background-color:#E6F2FE">职务:</td>
        		     <td style="text-align:left" width="37%"><input class="input_box" name="duty" maxlength="20" type="text" style="width:25%;float:left;margin-left:5px;"/></td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">办公电话:</td>
        		     <td style="text-align:left"><input name="phone" type="text" class="input_box" style="width:25%;float:left;margin-left:5px;"></td>
        		     <td style="background-color:#E6F2FE">手机号码:</td>
        		     <td style="text-align:left"><input name="mobile" type="text" class="input_box" style="width:25%;float:left;margin-left:5px;"></td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">电子邮箱:</td>
        		     <td style="text-align:left"><input name="email" type="text" class="input_box" style="width:25%;float:left;margin-left:5px;"></td>
        		     <td style="background-color:#E6F2FE">传真:</td>
        		     <td style="text-align:left"><input name="fax" type="text" class="input_box" style="width:25%;float:left;margin-left:5px;"></td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">信息录入时间</td>
        		     <td style="text-align:left"><input name="time" type="text" class="input_box" style="width:25%;float:left;margin-left:5px;" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM'})" /></td>
        		     <td style="background-color:#E6F2FE">补充说明</td>
        		     <td style="text-align:left"><textarea name="remark" style="width:50%;margin-left:5px"></textarea></td>
        		   </tr>
        		   <tr>
        		   	<td style="background-color:#E6F2FE">类别:</td>
        		     <td style="text-align:left"  colspan="3">
        		     	
        		     	<c:forEach items="${categories}" var="v">
        		     		<input type="checkbox" name="categoryId" id="${v.key}" value="${v.key }" style="width:3%;float:left" /><span class="spa"><label for="${v.key}">${v.value}</label></span>
        		     	</c:forEach>

        		     </td>
        		   </tr>			
        		 </table>      		 
        		 <div style="text-align:center;"><input style="cursor: pointer;" type="submit" class="sblue_btn" value="提交" onclick="javascript:if(!confirm('确定提交该客户信息吗?')) return false;">
        		 	&nbsp;&nbsp;<input type="reset" class="gray_btn" value="重置" style="cursor: pointer;"></div>
        		 </form>
        		 </div>
  </body>
</html>
