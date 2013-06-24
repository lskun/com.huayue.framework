<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>报名信息修改</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript">
		function validateCode(){
			var code = $("#code").val();
			if(code == ''){
				$.messager.alert("提示信息","验证码不能为空!","warning");
				return false;
			}
			if(!/^\w{8}$/.test(code)){
				$.messager.alert("提示信息","您输入的验证码格式错误!","warning");
				return false;
			}
			return true;
		}
		
		$("#codesubmit").click(function(){
			var flag = validateCode();
			if(flag){
				$("#form1").submit();
			}
		});
	</script>
  </head>
  
  <body>
  <div class="Content">
   	 	<div class="Right">
        	<div class="Devicon Midbox">会议报名信息在线查询、修改</div>
        	<div style="background:#39c;border:1px solid #ffefc8;color:#333;padding:5px 10px;font-size: 12px;margin-bottom:20px">
				<strong style="color:#fff"><span style="font-size:15px;">报名信息修改须知：</span></strong>
				<br/>
				<ul class="notice">
                   <li style="color:#ff0"><b style="font-size:14px;">① 在报名信息查询、修改过程中，如有问题可致电董老师：（010）58803960转631，15210847953；苗老师（010）58803960转605，13811964724.</b></li>
                   <li style="color:#ff0"><b style="font-size:14px;">② 注意验证码字母大小写格式.</b></li>
                   <li style="color:#ff0"><b style="font-size:14px;">③ 提示:验证码为报名提交成功后以短信形式发送至报名联系人手机上,请保管好此凭证。</b></li>
                </ul>	
			</div>
  <form id="form1" method="post" action="/servlet/VerificationExecuter" onsubmit="return validateCode();" style="width:100%;text-align:center;padding-top:20px">
  		<span style="font-size:18;font-weight:bold;">请输入验证码：</span><input name="code" class="input_box" id="code" type="text" style="vertical-align: middle;color: #555555;width:10%;height:24px;margin-left:5px;">
		<input id="codesubmit" type="submit" class="sblue_btn" value="提交">
  </form>
  	</div>
  	</div>
  </body>
</html>
