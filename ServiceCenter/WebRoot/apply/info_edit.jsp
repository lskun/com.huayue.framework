<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@page import="com.huayue.apply.domain.RegisterUnit"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String result = (String)request.getAttribute("result");
	
	if(!"OK".equals(result)){
		out.println(com.huayue.framework.util.Utils.getTipHtml("", result, null));
		return;
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>      
    <title>报名申请表</title>
	<meta http-equiv="Expires" content="0">
     <meta http-equiv="kiben" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<script type="text/javascript" src="/js/jquery.js"></script>	
	<style type="text/css">
	*{margin:0;padding:0;list-style-type:none;}
	a,img{border:0;}
	.form label, .form input, .form select, .form textarea, .form button, .form .label{float:left;font-size:12px;}
	.fl{float:left;}.fr{float:right;}.fn{float:none;}
	.hide{display:none;}.invisible{visibility:hidden;}.overflow{overflow:hidden;}
	.clear{display:block;clear:both;height:0;overflow:hidden;}
	body{font:12px/180% Arial, Helvetica, sans-serif,"宋体";}
	/* yellow_button */
	.yellow_button{background:url(/images/yellow_button.png);border:none;cursor:pointer;height:36px;line-height:36px; overflow:hidden; display:inline-block; text-align:center; font-size:14px; font-weight:800; color:#fff;background-position:0 0;width:139px;}
	#registsubmit.disabled{background-position:0 -36px;cursor:default!important;}
	.red{color:#ff0000;font-family:"宋体";font-weight:normal;}
	/* formbox */
	#formbox{padding:20px;margin:20px auto;}
	#formbox h3{font-size:16px;height:32px;color:#3366cc;font-weight:800;border-bottom:solid 1px #D1D1D1;margin:0 0 20px 0;padding:0 10px;}
	#formbox .item{padding-top:5px;height:50px;overflow:hidden;line-height:26px;}
	#formbox .item a:link,#formbox .item a:visited{text-decoration:underline;}
	#formbox .label{width:300px;text-align:right;font-size:14px;}
	#formbox .span-150{width:150px;}
	#formbox .text{width:240px;height:16px;padding:4px 3px;border:1px solid #bbb;font-size:14px;font-family:arial,"宋体";}
	#formbox .text-1{width:100px;}
	#formbox .blank{width:16px;height:16px;margin:2px 5px 0;}
	#formbox .img img{height:26px;margin:0 5px;}
	#formbox .succeed{background:url(/images/pwdstrength.gif) no-repeat -105px 0;}
	#formbox .yellow_button{font-size:14px;font-weight:bold;color:#fff;border:none;cursor:pointer;}
	#formbox .highlight1{border:1px solid #EFA100;outline:2px solid #FFDC97;*border:2px solid #ffcc66;*padding:3px 2px;}
	#formbox .highlight2{border:1px solid #f00;outline:1px solid #FFC1C1;color:#f00;}
	#formbox .pwdbg{background:#FFF8EB;}
	#formbox .focus{color:#999;line-height:22px;*line-height:20px;width:40%;height:24px;line-height:24px;float:left;display:block}
	#formbox .null,#formbox .error{color:red;line-height:22px;*line-height:20px;width:40%;height:24px;line-height:24px;float:left;display:block}
	#formbox .checkbox{margin-top:6px;*margin-top:2px;}
	#formbox #referrer{color:#999;font-size:12px;}
	#formbox #protocol{margin:0px 5px 0 0;display:inline;}
	#pwdstrength{color:#999;line-height:22px;padding-right:10px;}
	#pwdstrength b{float:left;width:104px;height:13px;overflow:hidden;margin-top:5px;*margin-top:3px;}
	.strengthA b{background:url(/images/pwdstrength.gif) no-repeat 0 0;}
	.strengthB b{background:url(/images/pwdstrength.gif) no-repeat 0 -13px;}
	.strengthC b{background:url(/images/pwdstrength.gif) no-repeat 0 -26px;}
	.dslabel{width:20%;height:24px;line-height:24px;float:left;background:#000;display:block}
</style>

	<script type="text/javascript" src="/js/Address.js"></script>	
	<script type="text/javascript">
	    function $(id) {return document.getElementById(id);}	
	    var currRow = "${fn:length(RegisterUnit.members)}" ;
		currRow = parseInt(currRow) + 1;
 	    //对电子邮件的验证
	    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
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
	        var cell8 = row.insertCell(-1);
	        cell1.innerHTML = " <input maxlength='15' type='text'>";
	        cell2.innerHTML = " <select style='width:60px;'><option value='1'>男</option><option value='0'>女</option></select>";
	        cell3.innerHTML = " <input maxlength='10' type='text'>";
	        cell4.innerHTML = " <input maxlength='10' type='text'>";
	        cell5.innerHTML = " <input maxlength='30' type='text'>";
	        cell6.innerHTML = " <input maxlength='30' type='text'>";
	        cell7.innerHTML = " <input maxlength='80' type='text'>";
		cell8.innerHTML = " <a href='#' onclick='delRow(" + currRow +")'>删除</a>";
	        chnBgcolor(obj, currRow, "ffffff");
	        currRow++;
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
	    function delRow(num) {
	        var row = $("row" + num);
	        var tab = $("tabModel");
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
	        if(obj.rows.length == 1) {
	        	alert("报名人员信息不能为空!");
	        	return false;
	        }	
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
	            for (var j = 0; j < inputs.length ; j++) {
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
        	<div class="Devicon Midbox">报名信息-在线修改</div>
        	<div style="background:#39c;border:1px solid #ffefc8;color:#333;padding:5px 10px;font-size: 12px;margin-bottom:20px">
				<strong style="color:#fff">报名申请单填写须知：</strong>
				<br/>
				<ul class="notice">
                   <li style="color:#ff0">在报名过程中，如有需要可致电董老师：（010）58803960转631，15210847953；苗老师（010）58803960转605，13811964724.</li>
                   <li style="color:#ff0">请慎重填写,(*)号为必填项。</li>
                </ul>	
			</div>      				
			<form name="_form" id="_form" method="post" onsubmit="return SubForm(document.all.tabModel);" action="/servlet/FormUpdater">
		<div class="Solution clearfix" style="padding: 20px 40px;">
        	      	<input type="hidden" name="unitId" value="${RegisterUnit.id }">
        		 <input id="columnList" type="hidden" name="columnList" value="">
        		 <table class="dwmc">
        		   <tr>
        		     <td width="13%" style="background-color:#E6F2FE">单位名称:</td>
        		     <td ><input name="unit" maxlength="50" value="${RegisterUnit.name }" id="unit" class="input_box" type="text" size="40" style="vertical-align: middle;color: #555555;width:50%;float:left;margin-left:5px;"/><span style="color:red;float:left;">&nbsp;*</span><div id="unit_prompt" style="float:left;"></div></td>
        		   	 <td width="13%" style="background-color:#E6F2FE">邮编:</td>
        		     <td width="37%"><input name="post_code" maxlength="10" value="${RegisterUnit.post_code }" id="post_code" type="text" style="width:25%;float:left;margin-left:5px;"/><div id="post_code_prompt" style="float:left;"></div></td>
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
							window.onload=function(){new PCAS("deliverprovince","delivercity","deliverarea");}
						</script>
					</div>	
						</td><td width="13%" style="background-color:#E6F2FE">单位地址:</td><td>			
							<input class="input_box" maxlength="50" value="${RegisterUnit.unitAddr }" name="unitAddress" id="unitAddress" type="text" size="40" style="vertical-align: middle;color: #555555;width:50%;margin-left:5px;"/><span style="color:red;float:left;">&nbsp;*</span><div id="unitAddress_prompt" style="float:left;"></div>       		    
        		   		</td>
        		   </tr>
        		   <tr>
        		     <td style="background-color:#E6F2FE">是否需安排住宿:</td>
        		     <td style="text-align:left">
        		     	<select name="isStay" onchange="overReq()" style="width:70px;float:left;"><option value="1" <c:if test="${RegisterUnit.isStay==1}">selected</c:if> >是</option><option value="0" <c:if test="${RegisterUnit.isStay==0 }">selected</c:if> >否</option></select><span style="color:red;">&nbsp;*</span>
        		     	<!-- <input type="radio" checked="checked" value="1" id="isStay" name="isStay" style="width:3%"><span class="spa">是</span> <input type="radio" value="0" name="isStay"  style="width:3%"><span class="spa">否</span> -->
        		     </td>
        		      <td style="background-color:#E6F2FE">住宿要求: </td>
        		     <td style="text-align:left"><input type="text" maxlength="2" value="<c:if test="${RegisterUnit.ronnNum != 0}">${RegisterUnit.ronnNum}</c:if>" name="roomNum" style="width:10%;margin-left:5px">标间</td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">是否同意调剂（与他人合住）</td>
        		     <td colspan="3" style="text-align:left"><input type="radio" <c:if test="${RegisterUnit.isTogether == 1}">checked</c:if> value="1" name="isTogether" style="width:2%"><span class="spa">是</span><input type="radio" <c:if test="${RegisterUnit.isTogether == 0}">checked</c:if> value="0" name="isTogether" style="width:2%"><span class="spa">否</span></td>
        		   </tr>
        		   <tr>        		     
        		     <td width="13%" style="background-color:#E6F2FE">发票上准确的单位名称：</td>
        		     <td style="text-align:left" width="37%"><input name="invoiceAddr" value="${RegisterUnit.invoiceAddr }" maxlength="100" type="text" size="40" style="vertical-align: middle;color: #555555;width:50%;margin-left:5px"><span style="color:red;float:left;">&nbsp;*</span><div id="invoiceAddr_prompt" style="float:left;"></div></td>
        		     <td width="13%" style="background-color:#E6F2FE">报名人联系方式(手机号码):</td>
        		     <td style="text-align:left" width="37%"><input name="contactWay" value="${RegisterUnit.contactWay}" maxlength="20" type="text" style="width:25%;float:left;margin-left:5px;"/><span style="color:red;float:left;">&nbsp;*</span><span style="color:green;">(请务实填写,方便报名信息修改与核实.)</span></td>
        		   </tr>
        		   <tr>        		     
        		     <td style="background-color:#E6F2FE">发票开票备注:</td>
        		     <td style="text-align:left" colspan="3"><textarea name="invoiceRemark" style="width:30%;margin-left:5px">${RegisterUnit.invoiceRemark }</textarea></td>
        		   </tr>
        		   
        		 </table>
        		<table id="tabModel" class="bmbd" style="font-size: 13px;border-spacing: 1px;border-collapse:inherit;margin-top:20px;" width="100%" align="center" cellspacing="1" cellpadding="2" border="0" bgcolor="#cccccc">     		      	        		
	        		 <tr align="center">
	        		 	<td width="20%" class="bdt">姓名(<span style="color:red">*</span>)</td>
        		 	<td width="5%" class="bdt">性别</td>
        		 	<td width="5%" class="bdt">民族</td>
        		 	<td width="5%" class="bdt">职务</td>
        		 	<td width="20%" class="bdt">办公电话</td>
        		 	<td width="15%" class="bdt">手机(<span style="color:red">*</span>)</td>
        		 	<td width="20%" class="bdt">邮箱(<span style="color:red">*</span>)</td>
	        		<td class="bdt">操作选项</td>
	        		 </tr>   
        		 <c:forEach items="${RegisterUnit.members }" var="member" varStatus="status">
	        		 <tr id="row${status.count }" bgcolor="#FFFFFF">	        		 	
	        		    <td><input name="name" maxlength="4" value="${member.name}" type="text"></td>
	        		    <td><select style="width:60px;" name="gender"><option value="1" <c:if test="${member.gender == 1}">selected</c:if> >男</option><option value="0" <c:if test="${member.gender == 0}">selected</c:if> >女</option></select></td>
	        		    <td><input name="nation" maxlength='15' type="text" value="${member.nation }"></td>
	        		    <td><input name="position" maxlength='15' type="text" value="${member.position}"></td>
	        		    <td><input name="phoneNumber" maxlength='30' type="text" value="${member.phoneNumber}"></td>
	        		    <td><input name="mobile" maxlength='30' type="text" value="${member.mobile}"></td>
	        		    <td><input name="email" maxlength='80' type="text" value="${member.email}"></td>
	        		    <td><a href="#" onclick="delRow(${status.count})">删除</a></td>
	        		 </tr>
        		 </c:forEach>
        		 </table>
        		 <div style="float:left;width:100%;margin-top:20px">
			  		<input id="ok" type="button" class="sblue_btn" onclick="insertRow(document.all.tabModel);" value="添加成员" style="float:left">
				</div>
				<div style="float:left;width:30%;margin-top:20px;padding-left:50%;height:40px;">
					<input class="blue_btn fl" type="submit" value="保存修改"><input class="blue_btn fl" style="margin-left:20px"type="reset" name="button" id="reset" value="重置" /> 
				</div>  
				</div>      		 
        		 </form>
        		
			
			<div class="clear"></div>
        	</div>
        </div>	
   	 
  </body>
</html>
