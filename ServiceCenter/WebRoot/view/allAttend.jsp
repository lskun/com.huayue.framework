<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>每月日常考勤统计</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 	<script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>	
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.1.2.2.js"></script>
	<style type="text/css">
    	*{margin:0;padding:0;list-style-type:none;}
		a,img{border:0;}
		.demo{margin:100px auto 0 auto;width:400px;text-align:center;font-size:18px;}
		.demo .action{color:#3366cc;text-decoration:none;font-family:"微软雅黑","宋体";}
		
		.overlay{position:fixed;top:0;right:0;bottom:0;left:0;z-index:998;width:100%;height:100%;_padding:0 20px 0 0;background:#f6f4f5;display:none;}
		.showbox{position:fixed;top:0;left:50%;z-index:9999;opacity:0;filter:alpha(opacity=0);margin-left:-80px;}
		*html,*html body{background-image:url(about:blank);background-attachment:fixed;}
		*html .showbox,*html .overlay{position:absolute;top:expression(eval(document.documentElement.scrollTop));}
		#AjaxLoading{border:1px solid #8CBEDA;color:#37a;font-size:12px;font-weight:bold;}
		#AjaxLoading div.loadingWord{width:180px;height:50px;line-height:50px;border:2px solid #D6E7F2;background:#fff;}
		#AjaxLoading img{margin:10px 15px;float:left;display:inline;}
    </style>
  </head>  
  <body>
    <div class="box-positon">
		<div class="rpos">当前位置: 每月日常考勤 统计</div>
		<div class="clear"></div>
	</div>	
	<div style="margin:10px;">	
	<form id="form1" action="${pageContext.request.contextPath}/attend/calculateAll.do" method="post" onsubmit="return isValidateTime();">
		<table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
				<tr>
        		     <td width="15%" style="background-color:#E6F2FE">请选择员工：</td>
        		     <td style="text-align:left">
        		     	<select id="userId" name="userId">
						<option value="">--请选择--</option>
						<c:forEach items="${userMap}" var="u">
							<option value="${u.key }">${u.value }</option>
						</c:forEach>
						</select><span style="color:green">(默认统计全体员工)</span>
        		     </td>
        		   	 <td width="15%" style="background-color:#E6F2FE">请选择统计考勤的月份: </td>
        		     <td width="35%">
        		     <input id="monthDate" name="monthDate"  value="${monthDate }" type="text" class="input_box" size="30" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM'})"/>
        		</span>     
        		     </td>
        		</tr>
        		<tr>
        			<td colspan="4">
        				<input id="datasubmit" type="submit" class="sblue_btn" value="考勤计算">
        			</td>
        		</tr>									
		</table>
	</form>	
	</div>
	<div class="overlay"></div>

			<div id="AjaxLoading" class="showbox">
				<div class="loadingWord"><img src="/images/waiting.gif">统计中，请稍候...</div>
			</div>
			
			<div style="height:1200px;"></div>	
  </body>
  <script type="text/javascript">
  var h = $(document).height();
		$(".overlay").css({"height": h });	
		function isValidateTime(){
			var $time = $('#monthDate').val();

			if($time == ''){
				$.messager.alert("提示信息","请选择截止时间","warning");
				return false;
			}
			return true;
		}
		
			$('#datasubmit').click(function(){
				var flag = isValidateTime();
				
				if(flag){
						$("#form1").submit(function(){
								$(".overlay").css({'display':'block','opacity':'0.8'});						
								$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},200);
							});			
					}			
			});
		
  </script>
</html>
