<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>bookcontent-left</title>
    <link rel="stylesheet" type="text/css" href="/css/admin.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery.treeview.css" />  
	<script type="text/javascript" src="/js/node/jq-treeview-async.js"></script>
	<script type="text/javascript">
		$(function (){
			$("#browser").treeview({
				url: "${pageContext.request.contextPath}/book/b_tree.do"
			});
		}); 
	</script>
  </head>
  
  <body class="lbody">
 	<div class="left">
 		<div class="date">
			 <span>现在时间： 
			 	<script language="javascript">
			       var day="";
			       var month="";
			       var ampm="";
			       var ampmhour="";
			       var myweekday="";
			       var year="";
			       mydate=new Date();
			       myweekday=mydate.getDay();
			       mymonth=mydate.getMonth()+1;
			       myday= mydate.getDate();
			       year= mydate.getFullYear();
			       if(myweekday == 0)
			       weekday=" 星期日 ";
			       else if(myweekday == 1)
			       weekday=" 星期一 ";
			       else if(myweekday == 2)
			       weekday=" 星期二 ";
			       else if(myweekday == 3)
			       weekday=" 星期三 ";
			       else if(myweekday == 4)
			       weekday=" 星期四 ";
			       else if(myweekday == 5)
			       weekday=" 星期五 ";
			       else if(myweekday == 6)
			       weekday=" 星期六 ";
			       document.write(year+"年"+mymonth+"月"+myday+"日 "+weekday);
			      </script>
			 </span>
		</div>
		<div class="fresh">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="35" align="center">
						<img src="/images/admin/refresh-icon.png">
						<a href="javascript:location.href=location.href">刷新</a>
					</td>
					<td width="2" valign="middle" height="35">
						<img src="/images/admin/left-line.png">
					</td>
					<td height="35" align="center">
						<img src="/images/admin/model-icon.png">
						<a target="rightFrame" href="../model/v_list.do">模型管理</a>
					</td>
				</tr>
			</table>
		</div> 
 	</div>
    <ul id="browser" class="filetree treeview">
    </ul>
  </body>
</html>
