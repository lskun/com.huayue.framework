<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>服务器进程信息</title>
  </head>
  <body>
    <pre>
    	<%
    		Map<Thread,StackTraceElement[]> map = Thread.getAllStackTraces();
    		for(Map.Entry<Thread,StackTraceElement[]> entry : map.entrySet()){
    			out.println(entry.getKey());
    			for(StackTraceElement ste : entry.getValue()){
    				out.println(ste.toString());
    			}
    		}
    	 %>
    </pre>
  </body>
</html>
