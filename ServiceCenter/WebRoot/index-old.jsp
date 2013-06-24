<%@page contentType="text/html;charset=utf-8"%>
<%
	String rel = null;

	rel = request.getParameter("rel");
	rel = rel == null ? "/main.jsp" : rel.trim();
	if (rel.indexOf("/") != 0)
		rel = "/main.jsp";
%>
<html>
<head>
<title>管理中心</title>
<meta http-equiv=Content-Type content="text/html;charset=utf-8"/>
</head>
<frameset rows="64,*"  frameborder="NO" border="0" framespacing="0">
	<frame src="top.jsp" noresize="noresize" frameborder="NO" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" target="main" />
  <frameset cols="165,*"  rows="*" id="frame">
	<frame src="menu.jsp" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
	<frame src="<%=rel %>" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
  </frameset>
<noframes>
  <body></body>
    </noframes>
</html>
