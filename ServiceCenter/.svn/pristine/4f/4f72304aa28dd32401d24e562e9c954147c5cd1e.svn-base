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
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>报名申请表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
  </head>  
  <body>
   	 <div class="Content">
   	 	<div class="Right">
        	<div class="Devicon Midbox"> 报名人员详细信息</div>
        	<br>
        	
        	<div class="Solution" style="padding: 10px 40px;">
        		 <table class="dwmc">
        		   <tr>
        		     <td class="graybg" width="13%">单位名称:</td>
        		     <td colspan="3"><span style="float:left"><%=cpre.getUnit_name() %></span></td>
        		   </tr>
        		   <tr>
        		     <td class="graybg" width="13%">单位地址:</td>
        		     <td width="37%"><span style="float:left"><%=cpre.getUnitAddr() %></span></td>
        		     <td class="graybg" width="13%">邮编:</td>
        		     <td width="37%"><span style="float:left"><%=cpre.getPost_code() %></span></td>
        		   </tr>
        		   <tr>
        		     <td class="graybg">是否需安排住宿:</td>
        		     <td style="text-align:left"><span style="float:left"><%=cpre.getIsStay() == 0 ? "否" : "是"%></span></td>
        		      <td class="graybg">住宿要求标间: </td>
        		     <td style="text-align:left"><span style="float:left"><%=cpre.getRonnNum() %></span></td>
        		   </tr>
        		   <tr>        		     
        		     <td class="graybg">是否同意调剂（与他人合住）</td>
        		     <td colspan="3" style="text-align:left"><span style="float:left"><%=cpre.getIsTogether() == 0 ? "否" : "是" %></span></td>
        		   </tr>
        		   <tr>        		     
        		     <td class="graybg">发票上准确的单位名称：</td>
        		     <td style="text-align:left"><span style="float:left"><%=cpre.getInvoiceAddr() %></span></td>
        		     <td class="graybg">发票开票内容:</td>
        		     <td style="text-align:left"><span style="float:left"><%=ExportController.changeArr(cpre.getInvoiceContent(),ExportController.DEFAULT_INVOICE_CONTENT) %></span></td>
        		   </tr>
        		   <tr>        		     
        		     <td class="graybg">发票开票备注:</td>
        		     <td style="text-align:left" colspan="3"><span style="float:left"><%=cpre.getInvoiceRemark() %></span></td>
        		   </tr>
        		   <tr>        		     
        		     <td class="graybg">您通过何种方式知悉活动</td>
        		     <td style="text-align:left" colspan="3"><span style="float:left"><%=ExportController.changeArr(cpre.getUnitSurvey(),ExportController.DEFAULT_UNIT_SURVEY) %></span></td>
        		   </tr>
        		 </table>
        		 <table id="tabModel" style="font-size: 13px;border-spacing: 1px;border-collapse:inherit;margin-top:20px" width="100%" align="center" cellspacing="1" cellpadding="2" border="0" bgcolor="#ccc">       		      	        		
        		 <tr bgcolor="#FFFFFF" align="center" style="font-weight: bold;height: 50px;">
        		 	<td class="graybg" width="">姓名(<span style="color:red">*</span>)</td>
        		 	<td class="graybg">性别</td>
        		 	<td class="graybg" width="">民族</td>
        		 	<td class="graybg" width="">职务</td>
        		 	<td class="graybg" width="">办公电话</td>
        		 	<td class="graybg" width="">手机</td>
        		 	<td class="graybg" width="">邮箱</td>
        		 	
        		 </tr>   
        		 <tr id="row0" bgcolor="#FFFFFF">
        		    <td align="center"><%=cpre.getName() %></td>
        		    <td align="center"><%=cpre.getGenderText() %></td>
        		    <td align="center"><%=cpre.getNation() %></td>
        		    <td align="center"><%=cpre.getPosition() %></td>
        		    <td align="center"><%=cpre.getPhoneNumber() %></td>
        		    <td align="center"><%=cpre.getMobile() == 0 ? "" : cpre.getMobile() %></td>
        		    <td align="center"><%=cpre.getEmail() %></td>        		    
        		 </tr>        		 
        		</table>
			<div class="clear"></div>
        	</div>
        </div>	
   	 </div>
  </body>
</html>
