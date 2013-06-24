<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head> 
    <title>短信群发</title>
    
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script language="javascript" type="text/javascript" src="/js/core.js"></script>
	<script language="javascript" type="text/javascript" src="/js/dialog.js"></script>	
	<script type="text/javascript" src="/js/module.js"></script>
	
	<script type="text/javascript">
		function $(id) { return document.getElementById(id); }
			
		function dataReq(){
			new Module().Load((function(args, el, text){
				$('nums').value = text;
			}),null ,null ,'/servlet/ImportMobileData?unit=' + encodeURI($('unitz').value == null ? '' : $('unitz').value) + "&isStay=" + $('isStayz').value , AJAX_TYPE_TEXT
			);
			// new Module().Load(func, args, el, url, type);
		}
	</script>	       		
  </head> 
  <body>
  <br>
    <form id="_form" action="/servlet/RecordSaver" method="post">
    <table width="95%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#555555">	
  	<tr background="../images/bg.gif">
            <td height="26">
                &nbsp;&nbsp;<strong style="color: #98CAEF">&nbsp;&nbsp;短信群发</strong>
            </td>                         
    </tr>
    <tr>
            <td bgcolor="#FFFFFF" id="_chl_0" colspan="2">
            <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
            <tr><td align="right" bgcolor="#F9FBFD" width="18%"><strong>手机号码：</strong></td>
            <td bgcolor="#ffffff"><textarea id="nums" class="input_box" rows="5" style="width:300px;font-size: 12px;" name="phoneNumber"></textarea>(多个号码之间请换行分隔)
            					 </td></tr>
            <tr><td align="right" bgcolor="#F9FBFD"><strong>手机号码导入:</strong></td><td bgcolor="#ffffff">
                                单位名称:<input id="unitz" type="text" class="input_box" name="unit">
                                是否安排住宿: <select id="isStayz" name="isStay"><option value="0">请选择</option><option value="1">是</option><option value="2">否</option></select>                    
            <input type="button" class="blue_btn" value="数据导入" onclick="javascript:dataReq()"></td></tr>					 
  			<tr><td align="right" bgcolor="#F9FBFD" width="18%"><strong>短信内容：</strong></td><td  bgcolor="#ffffff"><textarea id="content" class="input_box" rows="10" style="width:800px;font-size: 12px;" name="content"></textarea></td></tr>
  			</table>
  			</td>
  	</tr>
  	<tr><td bgcolor="#ffffff" colspan="2" align="right"><input type="submit" class="gray_btn" value="发送" ></tr>		
  	</table>
  	</form>
	<div class="dialog" id="a_dialog" style="display:none;">
	    <h2><a href="javascript:;" onclick="CloseDialog()">关闭</a><b id="a_dialog_title"></b></h2>
	    <div class="dialog_cont" id="a_dialog_cont">&nbsp;</div>
    </div>
    <div id="a_mask"></div>
	<br>
	<br>
  </body>
</html>
