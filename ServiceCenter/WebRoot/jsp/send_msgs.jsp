<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.huayue.framework.util.PageInfo,com.huayue.platform.entity.Message" %>
<%
	String result = String.valueOf(request.getAttribute("result"));
	if (!"OK".equals(result))
	{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", result, null));
		return;
	}
	PageInfo list = (PageInfo) request.getAttribute("PageInfo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="../images/skin.css" />
    <title>My JSP 'rev_msgs.jsp' starting page</title>
  </head>
    <body bgcolor="#eef2fb">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="../images/mail_leftbg.gif"><img src="../images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="../images/content-bg.gif">
	    <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">	     
	    </table>
	</td>
    <td width="16" valign="top" background="../images/mail_rightbg.gif"><img src="../images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
  	<td background="../images/mail_leftbg.gif" valign="middle">&nbsp;</td>
  	<td><br/>
		<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#555555">
        <tr background="../images/bg.gif">
            <td colspan="8">
                &nbsp;&nbsp;<strong style="color: #98CAEF">发送记录列表</strong>
            </td>                         
        </tr>     
		<tr>
			<td bgcolor="#FFFFFF" id="_chl_0" colspan="2">
				<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
				<tr style="font-weight: bold" background="../images/search.gif">
				    <td width="8%" align="center" >ID</td>
				    <td width="11%" >短信发送号码</td>
				    <td width="22%" >短信内容</td>
				    <td width="16%" align="center">短信发送时间</td>
				    <td width="12%" align="center">发送状态</td>
				    <td width="12%" align="center">操作</td>
  				</tr>
					  <%
					  	for (int i = 0; i < list.size(); i++)
					  			    	{    	
					  			    		Message msg = (Message)list.get(i) ;
					  %>
			    <tr bgcolor="#FFFFFF" onMouseOver="style.backgroundColor='#f1f1f1'" onMouseOut="style.backgroundColor='#ffffff';">
			       <td align="center"><%=msg.getId()%></td>
			       <td><%=msg.getPhoneNumber()%></td>
			       <td><%=com.huayue.framework.util.Format.subStr(msg.getContent())%>......</td>		     
			       <td align="center"><%=com.huayue.framework.util.DateUtil.dateToString(new java.util.Date(msg.getSend_time()),com.huayue.framework.util.DateUtil.FORMAT_ONE)%></td>
			       <td align="center"><%=com.huayue.sms.service.SmsSendService.getStateText(msg.getSend_state())%></td>
			       <td align="center"><a href="/servlet/ShowOutboundMessage?id=<%=msg.getId() %>" >查看详情</a></td>
			   </tr>
					  <%
					  		}
					  %>					 
			   <tr bgcolor="#ffffff" class="STYLE19" height="22" nowrap="nowrap">
			   <td colspan="7">
			   	   <table width="100%" cellspacing="0" cellpadding="0" border="0">
			   	   		<tr>
			   	   			<td width="25%">&nbsp;&nbsp;当前页：&nbsp;<%=list.getPageIndex() %>/<%=list.getPageCount() %>页 ,总<%=list.getRecordCount() %>条 ,每页<%=list.getPageSize() %>条</td>
			   	   			<td width="75%" align="right" valign="middle"><a href="/servlet/ListOutboundMsgs?pageIndex=1"><img src="/images/page_first_1.gif" /></a> <a href="/servlet/ListOutboundMsgs?pageIndex=<%=list.prevPage()%>"><img src="/images/page_back_1.gif" /></a>.<a href="/servlet/ListOutboundMsgs?pageIndex=<%=list.nextPage()%>"><img src="/images/page_next.gif" /></a> <a href="/servlet/ListOutboundMsgs?pageIndex=<%=list.getPageCount() %>"><img src="/images/page_last.gif" /></a></td>
			   	   		</tr>
			   	   </table>	
				</td> 				  
			   </tr>
			   </table>
		</td>
	</tr>

	</table>
	</td>
	<td background="../images/mail_rightbg.gif" >&nbsp;</td>
  </tr> 
  <tr>
    <td valign="bottom" background="../images/mail_leftbg.gif"><img src="../images/buttom_left2.gif" width="17" height="17" /></td>
    <td background="../images/buttom_bgs.gif"><img src="../images/buttom_bgs.gif" width="17" height="17"/></td>
    <td valign="bottom" background="../images/mail_rightbg.gif"><img src="../images/buttom_right2.gif" width="16" height="17" /></td>
  </tr>
</table>
</body>
</html>
