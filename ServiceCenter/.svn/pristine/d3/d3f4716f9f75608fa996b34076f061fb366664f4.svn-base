<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.huayue.platform.entity.SenderRecord"%>

<%
	String result = (String)request.getAttribute("result");	
	if (!"OK".equals(result))
	{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", result, null));
		return;
	}
	List<SenderRecord> list = (List<SenderRecord>)request.getAttribute("list");
%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'stopRecord.jsp' starting page</title>
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  </head> 
  <body>
     <br>
     <table width="95%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#555555">
        <tr background="../images/bg.gif">
            <td height="26" colspan="8">
                &nbsp;&nbsp;<strong style="color: #98CAEF">短信群发中断事件列表管理</strong>
            </td>                         
        </tr>     
        <tr>
            <td bgcolor="#FFFFFF" id="_chl_0" colspan="2">
				<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
				    <tr style="font-weight: bold" background="../images/search.gif">
						<td width="5%" align="center">ID</td>
						<td width="10%" align="center">发送起始时间</td>
						<td width="30%" align="center">短信内容</td>
						<td width="6%" align="center">已发送条数</td>
						<td width="6%" align="center">短信总条数</td>            
				    	<td width="10%" align="center">操作</td>  
				    </tr>
				    <%
				    	for(int i = 0 ,j = list.size();i < j ;i++ )
				    		    	{
				    		    		SenderRecord sender = list.get(i);
				    		    		long id = sender.getId();
				    %>
				    <tr bgcolor="#FFFFFF" onMouseOver="style.backgroundColor='#f1f1f1'" onMouseOut="style.backgroundColor='#ffffff';">
				    	<td align="center"><%=id%></td>
				    	<td align="center"><%=com.huayue.framework.util.DateUtil.dateToString(new java.util.Date(sender.getStartTime()),com.huayue.framework.util.DateUtil.FORMAT_ONE)%></td>
				    	<td align="left"><%=sender.getContent() %>......</td>
				    	<td align="center"><%=sender.getSendCount() %></td>
				    	<td align="center"><%=sender.getTotalCount() %></td>
				    	<td align="center"><a href="/servlet/ContinueSend?id=<%=id %>" >继续发送</a></td>
				    </tr>	
				    <% } %>
				</table>
			</td>
		</tr>
		</table>		    
  </body>
</html>
