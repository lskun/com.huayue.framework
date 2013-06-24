<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.huayue.framework.util.PageInfo,com.huayue.platform.entity.Message" %>
<%
	String result = String.valueOf(request.getAttribute("result"));
	if (!"OK".equals(result))
	{
		out.println(com.huayue.framework.util.Utils.getTipHtml("", result, null));
		return;
	}
	Message msg = (Message)request.getAttribute("InboundMessage");
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
	</td>
    <td width="16" valign="top" background="../images/mail_rightbg.gif"><img src="../images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
  	<td background="../images/mail_leftbg.gif" valign="middle">&nbsp;</td>
  	<td>
		<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#555555">
        <tr background="../images/bg.gif">
            <td colspan="2">
                &nbsp;&nbsp;<strong style="color: #98CAEF">接收记录详情</strong>
            </td>                         
        </tr>     
		<tr>
			<td bgcolor="#FFFFFF" id="_chl_0" colspan="2">
				<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
				<tr><td align="right" bgcolor="#F9FBFD" width="18%"><strong>短信记录ID：</strong></td><td  bgcolor="#ffffff"><%=msg.getId() %></td></tr>
		    <tr><td align="right" bgcolor="#F9FBFD" width="18%"><strong>短信来源号码：</strong></td><td  bgcolor="#ffffff"><%=msg.getPhoneNumber() %></td></tr>
		    <tr><td align="right" bgcolor="#F9FBFD" width="18%"><strong>短信内容：</strong></td><td bgcolor="#ffffff"><%=msg.getContent()%></td></tr>	
		    <tr bgcolor="#ffffff">
    		<td colspan="2" align="right">      	
        	<input type="button" name="Submit" class="gray_btn" value="返回上一层" onclick="history.back();">  
        </td></tr>	
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
