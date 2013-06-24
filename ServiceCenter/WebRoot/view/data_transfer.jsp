<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>数据迁移</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
		<div class="rpos">当前位置: 客户档案管理 - 数据导入</div>
		<div class="clear"></div>
	</div>
	<div class="Content">
   	 	<div class="Right">
        	<div class="Devicon Midbox">上传导入文件
        		<div style="float:right">此处下载规范模板xls文件：<input type="button" class="sblue_btn" value="模版下载" onclick="javascript:location.href='/data/modeldownload.do'"></div>
        	</div>
        	<br>
        	<div style="background:#fff8d8;border:1px solid #ffefc8;color:#333;padding:5px 10px;font-size: 12px;">
				<strong>上传导入文件注意事项：</strong>
				<br>
				<ul class="notice">
                   <li>1.导入文件必须为Excel95/97/2000/xp格式的xls文件或者Excel2007格式的xlsx文件；</li>
                   <li>2.导入的Excel文件工作表第一行必须为列名,数据从第二行开始；</li>
                   <li>3.如果Excel文件有密码保护，请在导入前先清除密码；</li>
                   <li>4.导入前请仔细检查Excel文件内容,尽量保证导入数据正确有效;</li>
                   <li>5.上传文件过程请不要立即关闭浏览器,以免出现数据传输中断；</li>
                   <li><B>6.特别注意：导入文件必须和文件模板格式一致,请先下载数据文件模板xls文件。</B></li>
                </ul>	
			</div>
			<div style="margin:15px;">
				<form id="form1" enctype="multipart/form-data" name="form1" method="post" action="/data/uploadfile.do">
					<input type="file" name="myfile" id="myfile" style="width: 460px; height: 25px;" size="54" />
					<input id="uploadsubmit" type="submit" class="sblue_btn" value="上传">
				</form>
			</div>
			<div class="overlay"></div>

			<div id="AjaxLoading" class="showbox">
				<div class="loadingWord"><img src="/images/waiting.gif">导入中，请稍候...</div>
			</div>
			
			<div style="height:1200px;"></div>
		</div>
	</div>
  </body>
  <script type="text/javascript">
		var h = $(document).height();
		$(".overlay").css({"height": h });	
		function isvalidatefile(obj)
		{
	        var extend = form1.myfile.value.substring(form1.myfile.value.lastIndexOf(".")+1);
	        if(extend==""){
		        $.messager.alert("提示信息","请选择要导入的文件!","warning");
		        return false;
	        }else{
	    		if(!(extend=="xls"||extend=="xlsx")){
	       		$.messager.alert("提示信息","请上传后缀名为xls或xlsx的文件!","warning");
	       		var nf = document.getElementById(obj).cloneNode(true);
	                nf.value='';
	                document.getElementById(obj).parentNode.replaceChild(nf, document.getElementById(obj));
	       		return false;
	    		}
	        }
	        return true;
	    }

	    $("#uploadsubmit").click(function(){
				var flag = isvalidatefile('myfile');
				if(flag){
					$("#form1").submit(function(){
							$(".overlay").css({'display':'block','opacity':'0.8'});						
							$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},200);
						});			
				}
		    }); 
	</script>
</html>
