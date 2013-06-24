<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.huayue.apply.domain.RegisterUnit"%>
<%
	String result = (String)request.getAttribute("result");	
	if (!"OK".equals(result))
	{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", result, null));
		return;
	}
	com.huayue.framework.util.PageInfo pInfo = (com.huayue.framework.util.PageInfo)request.getAttribute("UnitList");
%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>单位信息列表</title>
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
                if(document.editForm.selectAll.checked) {
                    for(var i=0; i<ids.length; i++) {
                        ids[i].checked="checked";
                    }
                } else {
                    for(var i=0; i<ids.length; i++) {
                        ids[i].checked="";
                    }
                }
          }
          function checkSelect(){
          	  var tmp = 0;
          	  var ids = document.getElementsByName("ids");
          	  for(var i=0; i<ids.length; i++) {
                  if(ids[i].checked) tmp += 1;
              }
              if(tmp == 0) {
              	alert('请选择单位!');
              	return false;
              }
              var standard = $('standard').value; 
              if(standard == '' || stand == null){
              	alert('请填写收费标准!');
              	return false;
              }
          }
	</script>
  </head>
  
  <body>
   	<br/>
   	<div style="margin: 5px 0px;">
  	<form id="_form" name="form" action="/servlet/ListUnitMessages" method="post">
  		<input id="pageIndex" type="hidden" name="pageIndex" value="1">
  		<table width="95%" border="0" align="center" cellpadding="3" cellspacing="1">
  		<tr><td>
  		  		
  		 <strong>单位名称：</strong><input name="unit" type="text" class="input_box" style="width: 200px;" />&nbsp;
  		 <strong>收费标准: </strong><select name="charge_standard"><option value="">请选择</option><option value="380">380</option><option value="480">480</option></select>&nbsp; 		
  		 <strong>是否安排住宿：</strong><select name="is_stay"><option value="">请选择</option><option value="0">否</option><option value="1">是</option></select>&nbsp;
  		 <strong>是否同意调剂：</strong><select name="is_together"><option value="">请选择</option><option value="0">否</option><option value="1">是</option></select>&nbsp;
  		 <strong>会议类别:</strong><select name="category_id"><option value="">请选择</option><option value="1">第三届新学校论坛</option></select>&nbsp;  		 
  		 <strong>报名起始时间：</strong><input name="start_time" type="text" class="input_box" size="20" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />&nbsp;
  		 <strong>报名截止时间：</strong><input name="end_time" type="text" class="input_box" size="20" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />  		 
  		 <input value="查询" class="blue_btn" type="submit">&nbsp;&nbsp;</td></tr>
  		</table>
  	</form></div>
  	<form id="formJunk" name="editForm" action="/servlet/MultiFormEditor" method="post" onsubmit="return checkSelect();">
    <table width="95%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#555555">
        <tr background="../images/bg.gif">
            <td height="26" colspan="8">
                &nbsp;&nbsp;<strong style="color: #98CAEF">单位信息详情列表</strong>
            </td>                         
        </tr>     
        <tr>
            <td bgcolor="#FFFFFF" id="_chl_0" colspan="2">
				<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
				    <tr bgcolor="#FFFFFF" style="font-weight: bold" background="../images/search.gif">
				    	<td width="1%" align="center"><input name="selectAll" type="checkbox" onclick="checkJunk()"></td> 
                        		<td width="2%" align="center">ID</td>                       
                        		<td width="5%" align="center">单位名称</td>
                        		<td width="5%" align="center">发票地址</td>                     
                        		<td width="3%" align="center">安排住宿</td>
                        		<td width="3%" align="center">同意调剂</td>
                        		<td width="1%" align="center">收费标准</td>                       
                        		<td width="5%" align="center">报名时间</td>                       
                        		<td width="3%" align="center">操作</td>
                    </tr>
                    <%
                    	if(pInfo.size() != 0){
                                        	for(int i = 0,l = pInfo.size() ;i < l ; i++)
                                        	{
                                        	    RegisterUnit unit = (RegisterUnit)pInfo.get(i);
                                        		String sendTime = com.huayue.framework.util.DateUtil.dateToString(new Date(unit.getApplyTime()),com.huayue.framework.util.DateUtil.FORMAT_ONE);
                                        		int id = unit.getId();
                    %>
                     <tr bgcolor="#FFFFFF" onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
                     	<td align="center"><input type="checkbox" value="<%=id %>" name="ids"></td>
                     	<td align="center"><%
                     		if(unit.getCharge_status() == 1){
                     			out.print("<span style=\"color: red\">" + id + "</span>");
                     		}else{
                     			out.print(id);
                     		}
                     	%><br></td>
                     	<td><%=unit.getName() %><br></td>
                     	<td align="center"><%=unit.getInvoiceAddr() %><br></td>
                     	<td align="center"><%=unit.getIsStay() == 1 ? "<span style=\"color: green\">是</span>": "<span style=\"color: gray\">否</span>"%><br></td>
                     	<td align="center"><%=unit.getIsTogether()== 1 ? "<span style=\"color: green\">是</span>": "<span style=\"color: gray\">否</span>"%><br></td>
                     	<td style="<%if(unit.getCharge_standard() == 480.0 ){ out.println("background: #E2EDFB");}else if(unit.getCharge_standard() == 380.0) out.println("background: #FDF2EF");%>" align="center"><%=unit.getCharge_standard()%><br></td>
                        <td align="center"><%=sendTime %><br></td>                       
                     	<td align="center"><a href="/apply/add_member.jsp?unit_id=<%=id %>">添加成员</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="/servlet/DeleteUnit?id=<%=id %>" onclick="javascript:return confirm('你确定删除该单位记录吗?')">删除</a></td>
                     </tr>	
                     <%
                     	}
                     }else
                     	out.println("<td bgcolor=\"#FFFFFF\" align=\"center\" colspan=\"9\"><span style=\"color:red;\">无对应的搜索结果</span></td>");
                     %>
     			</table> 
     		</td>
     	</tr>
     	<tr>
     	<td height="25" bgcolor="#FFFFFF" width="300px" align="left">  		 
     		<strong>收费标准: </strong><input id="standard" name="standard" type="text">
			<span style="float:right;"><input type="submit" class="blue_btn" value="编辑收费标准"></span></td>  
            <td height="25" bgcolor="#FFFFFF"  align="right">       
                            当前共<%=pInfo.getRecordCount() %>条 当前第<%=pInfo.getPageIndex()%>/<%=pInfo.getPageCount() %> 页 <a href="javascript:GO(1)">首页</a> <a href="javascript:GO(<%=pInfo.prevPage()%>)">上一页</a> <a href="javascript:GO(<%=pInfo.nextPage()%>)">下一页</a>
                             转到第&nbsp;<input id="index" style="width:30px;" type="text">&nbsp;页  <input type="button" value="转" onclick="javascript:GoIndex()">&nbsp;&nbsp;&nbsp;        
            </td>
        </tr>    	                
  </table></form>
  </body>
</html>