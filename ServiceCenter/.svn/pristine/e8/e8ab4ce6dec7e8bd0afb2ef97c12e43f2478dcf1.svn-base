<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.huayue.apply.TokenGen"%>
<%
	TokenGen.getInstance().saveToken(request);
	String s = (String)session.getAttribute(TokenGen.TOKEN_FOR_SUBMIT);
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>报名申请表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<script type="text/javascript" src="/js/Address.js"></script>	
	<script type="text/javascript">
	    var currRow = "1";
 	    //对电子邮件的验证
	    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	    function $(idx) { return document.getElementById(idx); }
	    function insertRow(obj) {
	        var row = obj.insertRow(-1);
	        row.id = "row" + currRow;
	        var cell1 = row.insertCell(-1);
	        var cell2 = row.insertCell(-1);
	        var cell3 = row.insertCell(-1);
	        var cell4 = row.insertCell(-1);
	        var cell5 = row.insertCell(-1);
	        var cell6 = row.insertCell(-1);
	        var cell7 = row.insertCell(-1);
	        cell1.innerHTML = " <input maxlength='15' type='text'>";
	        cell2.innerHTML = " <select style='width:96%;'><option value='1'>男</option><option value='0'>女</option></select>";
	        cell3.innerHTML = " <input maxlength='10' type='text'>";
	        cell4.innerHTML = " <input maxlength='10' type='text'>";
	        cell5.innerHTML = " <input maxlength='30' type='text'>";
	        cell6.innerHTML = " <input maxlength='30' type='text'>";
	        cell7.innerHTML = " <input maxlength='80' type='text'>";

	        chnBgcolor(obj, currRow, "ffffff");
	    }

	    function chnBgcolor(obj, index, color) {
	        for (var i = 0; i < obj.rows.length; i++) {
	            if (i == index) {
	                obj.rows[i].bgColor = color;
	            }
	            else {
	                obj.rows[i].bgColor = "ffffff";
	            }
	        }
	    }
	    function del(num) {
	        var row = $("row" + num);
	        var tab = $("tb");
	        tab.deleteRow(row.rowIndex);

	    }
		function overReq(){
			if(document._form.isStay.value == '1'){
				document._form.isTogether.value = '';
				document._form.roomNum.focus();
			}else if(document._form.isStay.value == '0'){
				document._form.isTogether.value = '0';
				document._form.invoiceAddr.focus();
			}
		}
	    function isNumber(name)  //数值检测
	    {
	        if (name.length == 0) return false;
	        for (i = 0; i < name.length; i++) {
	            if (name.charAt(i) < "0" || name.charAt(i) > "9")
	                return false;
	        }
	        return true;
	    }

	    function SubForm(obj) {
	        var x = window.confirm('您确认要提交报名信息吗?');

	        if (!x)
	            return false;
	        //表单值验证
	        if (document._form.unit.value.length == 0) {
	            $('unit_prompt').innerHTML = "<span style='color:red;'>请输入单位名称.</span>";
	            document._form.unit.focus();
	            return false;
	        }
	        if (document._form.unitAddress.value.length == 0) {
	            $('unitAddress_prompt').innerHTML = "<span style='color:red;'>请输入单位地址.</span>";
	            document._form.unitAddress.focus();
	            return false;
	        }
	        if(document._form.isStay.value == ''){
		        alert('请选择是否安排住宿!');
				return false;
		    }		
	        if(document._form.invoiceAddr.value.length == 0){
				$('invoiceAddr_prompt').innerHTML = "<span style='color:red;'>请输入发票填写的单位名称.</span>";
				document._form.invoiceAddr.focus();
				return false;
		    }
		
		if(document._form.contactWay.value == ''){
		    	alert('请填写报名联系人手机号，方便在线修改以及查询报名信息');
		    	return false;
		    }
		    if(!/^\d{11}$/.test(document._form.contactWay.value)){
		    	alert('您输入的手机号码格式错误，请重新输入!');
		    	return false;
		    }
		    if(document._form.isStay.value == 1){
		    	if(document._form.isTogether.value == ''){
					alert('请选择是否服从调剂!');
					document._form.isTogether.focus();
					return false;
				}
		    }
	        //  if(document._form.post_code.value.length == 0 || !isNumber(document._form.post_code.value)){
	        //	$('post_code_prompt').innerHTML="<span style='color:red;'>请输入邮编.</span>";
	        //	document._form.post_code.focus();
	        //	return false;
	        //}


	        var strs = '';
	        for (var i = 1; i < obj.rows.length; i++) {
	            var str = '';
	            if (obj.rows[1].getElementsByTagName('input')[0].value == '') {
	                alert('请填写报名人员信息!');
	                return false;
	            }
	            if (obj.rows[1].getElementsByTagName('input')[4].value == '' ){
		            alert('请填写手机号码,方便会务组人员与您联系!');
		            return false;
	            }
	            if(obj.rows[1].getElementsByTagName('input')[5].value == '' || !myreg.test(obj.rows[1].getElementsByTagName('input')[5].value)){
		            alert('请输入您的有效邮箱!');
		            return false;
	            }
	            var inputs = obj.rows[i].getElementsByTagName('input');

	            var sec = obj.rows[i].getElementsByTagName('select')
	            for (var j = 0; j < inputs.length; j++) {
	                if (inputs[0].value == null || inputs[0].value == '') break;
	                if (j == inputs.length - 1) {
	                    str += inputs[j].value + ',' + sec[0].value + '|';
	                } else {
	                    str += inputs[j].value + ',';
	                }
	            }
	            strs += str;
	        }
	        $('columnList').value = strs;
	        return true;
	    }	
		

		
	</script>
  </head>  
  <body>
   	 <div class="Content">
   	 	<div class="Right">
        	<div class="Devicon Midbox">网上报名</div>
        	<div style="background:#39c;border:1px solid #ffefc8;color:#333;padding:5px 10px;font-size: 12px;margin-bottom:20px">
				<strong style="color:#fff">报名申请单填写须知：</strong>
				<br/>
				<ul class="notice">
                   <li style="color:#ff0"><B>本表单提交成功后,如果需要修改报名信息,可致电刘老师:15011250775,阮老师:15120095299,董老师:15210847953，由会务组统一修改。</B></li>
                   <li style="color:#ff0">在报名过程中，如有需要可致电董老师：（010）58803960转631，15210847953；苗老师（010）58803960转605，13811964724.</li>
                   <li style="color:#ff0">请慎重填写,(*)号为必填项。</li>
                </ul>	
			</div>
		<form name="_form" id="_form" method="post" onsubmit="return SubForm(document.all.tabModel);" action="/servlet/FormExcutor">
        	  <div class="Solution clearfix" style="padding: 20px 40px;">       	
        		 <input type="hidden" name="category_id" value="2"/>	
        		 <input type="hidden" name="token" value="<%=s %>"/>	
        		 <input id="columnList" type="hidden" name="columnList" value=""/>
        		 <table class="dwmc">
        		   <tr class="e7">
        		     <td width="13%" style="background-color:#E6F2FE">单位名称:</td>
        		     <td ><input name="unit" maxlength="50" id="unit" class="input_box" type="text" size="40" style="vertical-align: middle;color: #555555;width:50%;float:left;margin-left:5px;"/><span style="color:red;float:left;">&nbsp;*</span><div id="unit_prompt" style="float:left;"></div></td>
        		   	 <td width="13%" style="background-color:#E6F2FE">邮编:</td>
        		     <td width="37%"><input name="post_code" class="input_box" maxlength="10" id="post_code" type="text" style="width:25%;float:left;margin-left:5px;"/><div id="post_code_prompt" style="float:left;"></div></td>
        		   </tr>
        		   <tr>
        		     <td width="13%" style="background-color:#E6F2FE">所在地:</td>
        		     <td>  
        		     <div style="float:left;margin-left:5px;">
        		     	<select id="deliverprovince" name="deliverprovince"></select>
					 	<select id="delivercity" name="delivercity"></select>
						<select id="deliverarea" name="deliverarea"></select>
						<script src="/js/Address.js"></script>
						<script>
							window.onload=function(){new PCAS("deliverprovince","delivercity","deliverarea", "","","");}
						</script>
					</div>	
						</td><td width="13%" style="background-color:#E6F2FE">单位地址:</td><td>			
							<input class="input_box" maxlength="50" name="unitAddress" id="unitAddress" type="text" size="40" style="vertical-align: middle;color: #555555;width:50%;margin-left:5px;"/><span style="color:red;float:left;">&nbsp;*</span><div id="unitAddress_prompt" style="float:left;"></div>       		    
        		   		</td>
        		   </tr>
        		   <tr class="e7">
        		     <td style="background-color:#E6F2FE">是否需安排住宿:</td>
        		     <td style="text-align:left">
        		     	<select name="isStay" onchange="overReq()" style="width:70px;float:left;"><option value="">请选择</option><option value="1">是</option><option value="0">否</option></select><span style="color:red;">&nbsp;*</span>
        		     	<!-- <input type="radio" checked="checked" value="1" id="isStay" name="isStay" style="width:3%"><span class="spa">是</span> <input type="radio" value="0" name="isStay"  style="width:3%"><span class="spa">否</span> -->
        		     </td>
        		      <td style="background-color:#E6F2FE">住宿要求: </td>
        		     <td style="text-align:left"><input type="text" class="input_box" maxlength="2" name="roomNum" style="width:10%;margin-left:5px">标间<span style="color:green">(如果不安排住宿，此项无需填写/请输入阿拉伯数字)</span></td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">是否同意调剂（与他人合住）</td>
        		     <td colspan="3" style="text-align:left">
        		     	<select id="isTogether" name="isTogether" style="width:70px;float:left;">
        		     		<option value="">请选择</option>
        		     		<option value="1">是</option>
        		     		<option value="0">否</option>
        		     	</select><span style="color:red;">&nbsp;*</span>
        		     </td>
        		   </tr>
        		   <tr class="e7">        		     
        		     <td width="13%" style="background-color:#E6F2FE">发票上准确的单位名称：</td>
        		     <td style="text-align:left" width="37%"><input name="invoiceAddr" maxlength="100" class="input_box" type="text" size="40" style="vertical-align: middle;color: #555555;width:50%;margin-left:5px"><span style="color:red;float:left;">&nbsp;*</span><div id="invoiceAddr_prompt" style="float:left;"></div></td>
        		     <td width="13%" style="background-color:#E6F2FE">报名人联系方式(手机号码):</td>
        		     <td style="text-align:left" width="37%"><input name="contactWay" class="input_box" maxlength="20" type="text" style="width:25%;float:left;margin-left:5px;"/><span style="color:red;float:left;">&nbsp;*</span><span style="color:green;">(请务实填写,方便报名信息修改与核实.)</span></td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">发票开票备注:</td>
        		     <td style="text-align:left" colspan="3"><textarea name="invoiceRemark" style="width:30%;margin-left:5px"></textarea></td>
        		   </tr>        		   
        		 </table>
        		 <table id="tabModel" class="bmbd" style="font-size: 13px;border-spacing: 1px;border-collapse:inherit;margin-top:20px;" width="100%" align="center" cellspacing="1" cellpadding="2" border="0" bgcolor="#cccccc">       		      	        		
        		 <tr align="center">
        		 	<td width="20%" class="bdt">姓名(<span style="color:red">*</span>)</td>
        		 	<td width="5%" class="bdt">性别</td>
        		 	<td width="5%" class="bdt">民族</td>
        		 	<td width="10%" class="bdt">职务</td>
        		 	<td width="20%" class="bdt">办公电话</td>
        		 	<td width="15%" class="bdt">手机(<span style="color:red">*</span>)</td>
        		 	<td width="25%" class="bdt">邮箱(<span style="color:red">*</span>)</td>
        		 	
        		 </tr>   
        		 <tr id="row0" bgcolor="#FFFFFF">
        		    <td><input name="name" type="text"></td>
        		    <td><select><option value="1">男</option><option value="0">女</option></select></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		 </tr>
        		 <tr id="row0" bgcolor="#FFFFFF">
        		    <td><input name="name" type="text"></td>
        		    <td><select><option value="1">男</option><option value="0">女</option></select></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		 </tr><tr id="row0" bgcolor="#FFFFFF">
        		    <td><input name="name" type="text"></td>
        		    <td><select><option value="1">男</option><option value="0">女</option></select></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		 </tr><tr id="row0" bgcolor="#FFFFFF">
        		    <td><input name="name" type="text"></td>
        		    <td><select><option value="1">男</option><option value="0">女</option></select></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		 </tr><tr id="row0" bgcolor="#FFFFFF">
        		    <td><input name="name" type="text"></td>
        		    <td><select><option value="1">男</option><option value="0">女</option></select></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		 </tr><tr id="row0" bgcolor="#FFFFFF">
        		    <td><input name="name" type="text"></td>
        		    <td><select><option value="1">男</option><option value="0">女</option></select></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		    <td><input type="text"></td>
        		 </tr>
        		</table>
			    <div style="float:left;width:100%;margin-top:10px">
			      <input id="ok" type="button" class="sblue_btn tianjia" onclick="insertRow(document.all.tabModel);" value="添加成员" style="float:left"/>
			    </div>
                <div style="float:left;width:100%;margin:20px 0px 20px;padding-left:45%;height:40px;" class="clearfix"/>
			      <input type="submit" value="提 交" class="blue_btn fl">
			    </div>

        	</form>
			<div class="clear"></div>
          </div>
       </div>
       </div>
     </div>
		<script type="text/javascript">
			var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
			document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F8726ccdc3b3f1133285b2b37ed57a28b' type='text/javascript'%3E%3C/script%3E"));
		</script>

  </body>
</html>
