<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.huayue.apply.domain.ComprehensData"%>
<%
	String result = (String)request.getAttribute("result");	
	if (!"OK".equals(result))
	{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", result, null));
		return;
	}
	com.huayue.framework.util.PageInfo pInfo = (com.huayue.framework.util.PageInfo)request.getAttribute("DataCollection");
%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>报名信息列表</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <style>
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}
</style>
	<script type="text/javascript">
		function $(id) { return document.getElementById(id); }
		function GO(page) {
			$("pageIndex").value = page;
			$("_form").submit();
		}  
		function GoIndex(){
			var maxIndex = <%=pInfo.getPageCount()%>;
			var idx = $('index').value;
			if(idx > maxIndex || idx <= 0)
			{
				idx = maxIndex;
			} 
			$("pageIndex").value = idx;
			$("_form").submit();
		}
		  function checkJunk() {
                var ids = document.getElementsByName("ids");
                if(document.formJunk.selectAll.checked) {
                    for(var i=0; i<ids.length; i++) {
                        ids[i].checked="checked";
                    }
                } else {
                    for(var i=0; i<ids.length; i++) {
                        ids[i].checked="";
                    }
                }
           }
           
           function delMember(){
           		var tmp = 0;
           		var ids = document.getElementsByName("ids");
           		for(var i=0; i<ids.length; i++) {
                        if(ids[i].checked) tmp += 1;
                    }
                if(tmp == 0) { alert('请选择要删除的记录'); return false; }  
           		if(!confirm('您确认删除选中信息记录吗?')) return false;
           }
	</script>
  </head>
  <body>
   	<br/>
   	<div style="margin: 5px 0px;">
  	<form id="_form" name="form" action="/servlet/ListMemberMsgs" method="post">
  		<input id="pageIndex" type="hidden" name="pageIndex" value="1">
  		<table width="95%" border="0" align="center" cellpadding="3" cellspacing="1">
  		<tr>
  			<td>		  		
		  		 <strong>单位名称：</strong><input name="unit" value="<c:if test="${unit !=null }">${unit }</c:if>" type="text" class="input_box" style="width: 200px;" />&nbsp; 		
		  		 <strong>报名人姓名：</strong><input name="member" value="<c:if test="${member!=null }">${member }</c:if>"  type="text" maxlength="10" class="input_box" style="width: 80px;"/>&nbsp;
		  		 <strong>是否安排住宿：</strong>
		  		 	<select name="is_stay">
		  		 		<option value="">请选择</option>
		  		 		<option <c:if test="${is_stay == 0}">selected</c:if> value="0">否</option>
		  		 		<option <c:if test="${is_stay == 1}">selected</c:if> value="1">是</option>
		  		 	</select>&nbsp;
		  		 <strong>是否同意调剂：</strong><select name="is_together"><option value="">请选择</option>
		  		 	<option value="0" <c:if test="${is_together == 0}">selected</c:if>>否</option>
		  		 	<option value="1" <c:if test="${is_together == 1}">selected</c:if>>是</option></select>&nbsp;
		  		 <strong>会议类别:</strong>
			  		 <select name="category_id">
			  		 	<option value="">请选择</option>
			  		 	<option value="1" <c:if test="${category_id == 1}">selected</c:if>>第三届新学校论坛</option>
			  		 	<option value="2" <c:if test="${category_id == 2}">selected</c:if>>2013深圳会议</option>
			  		 </select>&nbsp;
		  		 <strong>报名起始时间：</strong><input name="start_time" type="text" value="<c:if test="${start_time != null && start_time != 0}">${start_time }</c:if>" class="input_box" size="20" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />&nbsp;
		  		 <strong>报名截止时间：</strong><input name="end_time" type="text" value="<c:if test="${end_time != null && end_time != 0}">${end_time }</c:if>" class="input_box" size="20" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />  		 
		  		 <input value="查询" class="blue_btn" type="submit">&nbsp;&nbsp;
  		 	</td>
  		 </tr>
  		</table>
  	</form></div>
  	<form id="formJunk" name="formJunk" action="/servlet/DeleteMember" method="post" onsubmit="return delMember();">
    <table width="95%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#555555">
        <tr background="../images/bg.gif">
            <td height="26" colspan="8">
                &nbsp;&nbsp;<strong style="color: #98CAEF">报名信息详情列表</strong>
            </td>                         
        </tr>     
        <tr>
            <td bgcolor="#FFFFFF" id="_chl_0" colspan="2">
				<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
				    <tr style="font-weight: bold" background="../images/search.gif" bgcolor="#FFFFFF">
				    	<td width="2%" align="center"><input name="selectAll" type="checkbox" onclick="checkJunk()"></td> 
                        <td width="2%" align="center">ID</td>                       
                        <td width="3%" align="center">姓名</td>
                        <td width="2%" align="center">性别</td>
                        <td width="3%" align="center">民族</td>
                        <td width="4%"align="center">办公电话</td>
                        <td width="2%"align="center">手机</td>
                        <td width="4%"align="center">邮箱</td>
                        <td width="6%"align="center">单位名称</td>
                        <td width="2%"align="center">安排住宿</td>
                        <td width="2%"align="center">同意调剂</td>
                        <td width="4%"align="center">报名时间</td>                       
                        <td width="5%"align="center">操作</td>
                    </tr>
                    <%
                    	if(pInfo.size() != 0 ){
                                        	for(int i = 0,l = pInfo.size() ;i < l ; i++)
                                        	{
                                        	    ComprehensData per = (ComprehensData)pInfo.get(i);
                                        		String sendTime = com.huayue.framework.util.DateUtil.dateToString(new Date(per.getApplyTime()),com.huayue.framework.util.DateUtil.FORMAT_ONE);
                                        		long id = per.getId();
                    %>
                     <tr bgcolor="#FFFFFF" onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
                     	<td align="center"><input type="checkbox" value="<%=id %>" name="ids"></td>
                     	<td align="center"><%=id %></td>
                     	<td><%=per.getName() %></td>
                     	<td align="center"><%=per.getGenderText() %></td>
                     	<td align="center"><%=per.getNation() %></td>
                     	<td align="center"><%=per.getPhoneNumber()%></td>
                     	<td align="center"><%=per.getMobile() == 0 ? "-" :per.getMobile()%></td>
                        <td><%=per.getEmail() %></td>
                        <td><%=per.getUnit_name() %></td>                         
                     	<td align="center"><%=per.getIsStay() == 1 ? "<span style=\"color: green\">是</span>": "<span style=\"color: gray\">否</span>" %></td>
                     	<td align="center"><%=per.getIsTogether() == 1? "<span style=\"color: green\">是</span>": "<span style=\"color: gray\">否</span>" %></td>
                     	<td align="center"><%=sendTime%></td>
                     	<td align="center"><a href="/servlet/GetMemberMsgById?id=<%=id %>">查看</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="/servlet/EditMemberMsgById?id=<%=id %>&unit_id=<%=per.getUnit_id() %>">编辑</a></td>
                     </tr>	
                     <%
                     	}
                     }else out.println("<td bgcolor=\"#FFFFFF\" align=\"center\" colspan=\"13\"><span style=\"color:red;\">无对应的搜索结果</span></td>");
                     	
                     %>
     			</table> 
     		</td>
     	</tr>
     	<tr>
     	<td height="25" bgcolor="#FFFFFF" width="80px" align="left"><input type="submit" class="blue_btn" value="删除信息记录" ></td>  
            <td height="25" bgcolor="#FFFFFF"  align="right">
                            当前共<%=pInfo.getRecordCount() %>条 当前第<%=pInfo.getPageIndex()%>/<%=pInfo.getPageCount() %> 页 <a href="javascript:GO(1)">首页</a> <a href="javascript:GO(<%=pInfo.prevPage()%>)">上一页</a> <a href="javascript:GO(<%=pInfo.nextPage()%>)">下一页</a>
                             转到第&nbsp;<input id="index" style="width:30px;" type="text">&nbsp;页  <input type="button" value="转" onclick="javascript:GoIndex()">&nbsp;&nbsp;&nbsp;        
            </td>
        </tr>    	                
  </table></form>
  </body>
</html>
