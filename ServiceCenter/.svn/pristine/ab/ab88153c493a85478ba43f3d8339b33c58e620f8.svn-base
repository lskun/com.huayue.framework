<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.huayue.apply.domain.ComprehensData"%>
<%@page import="com.huayue.apply.ExportController"%>
<%
	String result = String.valueOf(request.getAttribute("result"));
	if(!"OK".equals(result))
	{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", result, null));   	
	}
	ComprehensData cpre = (ComprehensData)request.getAttribute("MemberMessage");
	int id = Integer.parseInt(String.valueOf(request.getAttribute("id")));
	int unit_id = Integer.parseInt(String.valueOf(request.getAttribute("unit_id")));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>编辑成员信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	
  </head>  
  <body>
   	 <div class="Content">
   	 	<div class="Right">
        	<div class="Devicon Midbox"> 编辑成员信息</div>
        	<br>
        	
        	<div class="Solution" style="padding: 10px 40px;">
        	<form name="_form" id="_form" method="post" action="/servlet/SaveEditedMsg">
        		 <input type="hidden" name="id" value="<%=id %>">
        		 <input type="hidden" name="unit_id" value="<%=unit_id %>">        		 
        		 <table class="dwmc">
        		   <tr>
        		     <td class="graybg" width="10%">单位名称:</td>
        		     <td colspan="3"><input name="unit" id="unit" value="<%=cpre.getUnit_name() %>" maxlength="30" class="input_box" type="text" size="40" style="vertical-align: middle;color: #555555;width:30%;float:left;margin-left:5px;"/></td>
        		   </tr>
        		   <tr>
        		     <td class="graybg" width="10%">单位地址:</td>
        		     <td width="40%"><input class="input_box" value="<%=cpre.getUnitAddr() %>" maxlength="30" name="unitAddress" id="unitAddress" type="text" size="40" style="vertical-align: middle;color: #555555;width:50%;float:left;margin-left:5px;"/><div id="unitAddress_prompt" style="float:left;"></div></td>
        		     <td class="graybg" width="10%">邮编:</td>
        		     <td width="40%"><input name="post_code" maxlength="20" value="<%=cpre.getPost_code() %>" id="post_code" type="text" style="width:50%;float:left;margin-left:5px;"/><div id="post_code_prompt" style="float:left;"></div></td>
        		   </tr>
        		   <tr>
        		     <td class="graybg">是否需安排住宿:</td>
        		     <td style="text-align:left"><input type="text" size="20" style="vertical-align: middle;color: #555555;width:30%;float:left;margin-left:5px;" value="<%=cpre.getIsStay()  %>" name="isStay" style="width:3%"/><span style="color:green">(1表示是,0表示否)</span></td>
        		      <td class="graybg">住宿要求: </td>
        		    <td><input type="text" value="<%=cpre.getRonnNum() %>" name="roomNum" style="vertical-align: middle;color: #555555;width:30%;float:left;margin-left:5px;"><span style="float:left;">标间</span>  </td>
        		   </tr>
        		   <tr>        		     
        		     <td class="graybg">是否同意调剂（与他人合住）</td>
        		     <td colspan="3" style="text-align:left"><input type="text" style="vertical-align: middle;color: #555555;width:30%;float:left;margin-left:5px;" size="20" value="<%=cpre.getIsTogether()  %>" name="isTogether" style="width:2%"><span style="color:green">(1表示是,0表示否)</span></td>
        		   </tr>
        		   <tr>        		     
        		     <td class="graybg">发票上准确的单位名称：</td>
        		     <td style="text-align:left"><input name="invoiceAddr" value="<%=cpre.getInvoiceAddr() %>" maxlength="60" type="text" size="40" style="vertical-align: middle;color: #555555;margin:17px 0px 0px 0px;width:50%"></td>
        		     <td class="graybg">发票开票内容:</td>
        		     <td style="text-align:left"><input type="text" name="invoiceContent" style="vertical-align: middle;color: #555555;width:30%;float:left;margin-left:5px;" value="<%=cpre.getInvoiceContent() %>" style="width:3%"/><span style="color:red;float:left">格式说明:培训费、会务费、资料费对应的格式修改[1,2,3]</span>.</td>
        		   </tr>
        		   <tr>        		     
        		     <td class="graybg">发票开票备注:</td>
        		     <td style="text-align:left" colspan="3"><textarea name="invoiceRemark" style="width:30%"><%=cpre.getInvoiceRemark() %></textarea></td>
        		   </tr>
        		   <tr>        		     
        		     <td class="graybg">您通过何种方式知悉活动</td>
        		     <td style="text-align:left" colspan="3"><%=ExportController.changeArr(cpre.getUnitSurvey(),ExportController.DEFAULT_UNIT_SURVEY) %></td>
        		   </tr>
        		 </table>
        		 <table id="tabModel" style="font-size: 13px;border-spacing: 1px;border-collapse:inherit" width="70%" align="center" cellspacing="1" cellpadding="2" border="0" bgcolor="#ccc">       		      	        		
        		 <tr bgcolor="#FFFFFF" align="center" style="font-weight: bold;height: 50px;">
        		 	<td class="graybg" width="">姓名</td>
        		 	<td class="graybg">性别<span style="color:green">(1表示男,0表示女)</span></td>
        		 	<td class="graybg"width="">民族</td>
        		 	<td class="graybg" width="">职务</td>
        		 	<td class="graybg" width="">办公电话</td>
        		 	<td class="graybg" width="">手机</td>
        		 	<td class="graybg" width="">邮箱</td>
        		 	
        		 </tr>   
        		 <tr id="row0" bgcolor="#FFFFFF">
        		    <td><input name="name" value="<%=cpre.getName() %>" type="text"></td>
        		    <td><input name="gender" value="<%=cpre.getGender() %>" type="text"></td>
        		    <td><input name="nation" type="text" value=<%=cpre.getNation() %>></td>
        		    <td><input name="position" type="text" value="<%=cpre.getPosition() %>"></td>
        		    <td><input name="phone_number" type="text" value="<%=cpre.getPhoneNumber() %>"></td>
        		    <td><input name="mobile" type="text" value="<%=cpre.getMobile() == 0 ? "" : cpre.getMobile()%>"></td>
        		    <td><input name="email" type="text" value="<%=cpre.getEmail() %>"></td>
        		 </tr>       	
        		
        		</table>
				<div style="float:left;width:100%">
				   <input type="submit" value="提交" class="blue_btn fl" style="float:right">
				 </div>
        	</form>
			<div class="clear"></div>
        	</div>
        </div>	
   	 </div>
  </body>
</html>
