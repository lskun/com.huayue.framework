<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	int unit_id = Integer.parseInt(request.getParameter("unit_id"));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>添加成员</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
  </head>
  
  <body>
  <div class="Content">
   	 	<div class="Right">
        	<div class="Devicon Midbox"> 添加成员信息</div>
        	<form name="_form" id="_form" method="post" action="/servlet/AddMember">
        		 <input type="hidden" name="unit_id" value="<%=unit_id %>">
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
	        		    <td><input name="name" type="text"></td>
	        		    <td><select name="gender" style="width:60px;"><option value="1">男</option><option value="0">女</option></select></td>
	        		    <td><input name="nation" type="text"></td>
	        		    <td><input name="position" type="text"></td>
	        		    <td><input name="phone_number" type="text" ></td>
	        		    <td><input name="mobile" type="text" ></td>
	        		    <td><input name="email" type="text" ></td>       		    
	        		 </tr>        		 
	        	</table>
	        	<div style="float:left;width:100%">
				   <input type="submit" value="提交" class="blue_btn fl" style="float:right">
				 </div>
	        </form>
	        </div>
	     </div>
  </body>
</html>
