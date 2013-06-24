<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>book information</title>   
	<link rel="stylesheet" type="text/css" href="/css/dev.css">
	<link rel="stylesheet" type="text/css" href="/css/admin.css" />
    <link rel="stylesheet" type="text/css" href="/css/theme.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" /> 
    <link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
    	var pageIndex = 1;
    	var pageSize = 10;
    	var countries = [];
    	var contentMap = [];   	
	    <%    String[] x = (String[])request.getSession().getAttribute("countries");
	        for(int i = 0; i <x.length;i++ ){ %>
	      countries[<%=i%>] =  '<%=x[i]%>';
	     <% } %>  
		<%  Map<Integer,String> map = (Map<Integer,String>)request.getSession().getAttribute("contentMap");
			for(Map.Entry<Integer,String> entry : map.entrySet()){
		%>
			contentMap[<%=entry.getKey()%>] = '<%=entry.getValue()%>';
		<% } %>		
    	function openbookWindow(){
    		$('#w').window({
    			title: '添加文章',
    			width: 800,
    			modal: true,
                shadow: true,
                closed: true,
                height: 530,
                resizable:false
    		});
    	}
    	
    	 // 判断数组中包含element元素
  		Array.prototype.contains = function (element) {
  
    		for (var i = 0; i < this.length; i++) {
        		if (this[i] == element) {
            		return true;
        		}
    		}
    		return false;
		}
    	
    	//得到行对象
		function getRowObj(obj)
		{
			var i = 0;
			while(obj.tagName.toLowerCase() != "tr"){
				obj = obj.parentNode;
				if(obj.tagName.toLowerCase() == "table")return null;
			}
			return obj;
		}

		//根据得到的行对象得到所在的行数
		function getRowNo(obj){
			var trObj = getRowObj(obj);
			var trArr = trObj.parentNode.children;
			for(var trNo= 0; trNo < trArr.length; trNo++){
				if(trObj == trObj.parentNode.children[trNo]){
					alert(trNo+1);
				}
			}
		}

		//删除行
		function delRow(obj){
			var tr = this.getRowObj(obj);
			if(tr != null){
				tr.parentNode.removeChild(tr);
			}else{
				throw new Error("the given object is not contained by the table");
			}
		}
		
		//批量删除
		function batchDel(){
			var idss = $('[name=idss]');
			var x = 0;
			for(var i=0;i<idss.length;i++){
				if(idss[i].checked){
					delRow(idss[i]);
					x++;
				}
			}
			if(x == 0){
				$.messager.alert('提示信息','请选择要删除的记录!','warning');
			}
		}				
		 
    	function selectData(){
    		var ids = $('[name=ids]');
    		var idss = $('[name=idss]');
 			var html = "";
 			var isExist = true;
 				for(var i=0; i < ids.length; i++ ){
 					if(ids[i].checked ){
 						for(var j=0;j<idss.length;j++){
 							if(ids[i].value == idss[j].value){
 								$.messager.alert('提示信息',ids[i].parentNode.parentNode.cells[2].innerHTML + ' 已经存在于此书中',"error");
 								isExist = false;
 								break;
 							}
 						}
 						if(isExist){
 							insertRow(ids[i]);				
 						}
 					}	
 				} 				  	
    	}
    	
    	function insertRow(obj) {
	        var row = document.all.tabmodel.insertRow(-1);
	        //row.id = "row" + currRow;
	        var cell1 = row.insertCell(-1);
	        var cell2 = row.insertCell(-1);
	        var cell3 = row.insertCell(-1);
	        var cell4 = row.insertCell(-1);
	        var cell5 = row.insertCell(-1);
	        var cell6 = row.insertCell(-1);
	        var cell7 = row.insertCell(-1);
	        var cell8 = row.insertCell(-1);
	        var cell9 = row.insertCell(-1);
	        var cell10 = row.insertCell(-1);
	        cell1.innerHTML = " <input type='checkbox' value='" +obj.parentNode.parentNode.cells[0].innerHTML + "' name='idss'/>";
	        cell2.innerHTML = obj.parentNode.parentNode.cells[1].innerHTML + "<input type='hidden' value='" + obj.parentNode.parentNode.cells[1].innerHTML + "' name='idss'/>";
	        cell3.innerHTML = obj.parentNode.parentNode.cells[2].innerHTML;
	        cell4.innerHTML = obj.parentNode.parentNode.cells[3].innerHTML;
	        cell5.innerHTML = obj.parentNode.parentNode.cells[4].innerHTML;
	        cell6.innerHTML = obj.parentNode.parentNode.cells[5].innerHTML;
	        cell7.innerHTML = obj.parentNode.parentNode.cells[6].innerHTML;
    		cell8.innerHTML = obj.parentNode.parentNode.cells[7].innerHTML;
    		cell9.innerHTML = obj.parentNode.parentNode.cells[8].innerHTML;
    		cell10.innerHTML = "<input class=\"del-button\" type=\"button\" onclick=\"delRow(this);\" value=\"删除\">";
    	}	
    	
    	function addBook(){
    		$('#w').window('open');
    		AjaxGetData(1,10);
    	}
    	
    	function checkJunk(obj) {
                var ids = document.getElementsByName("ids");
                if(obj.checked) {
                    for(var i=0; i<ids.length; i++) {
                        ids[i].checked="checked";
                    }
                } else {
                    for(var i=0; i<ids.length; i++) {
                    	ids[i].checked="";
                    }
                }
           }
    	
    	function checkJunks(obj) {
                var ids = document.getElementsByName("idss");
                if(obj.checked) {
                    for(var i=0; i<ids.length; i++) {
                        ids[i].checked="checked";
                    }
                } else {
                    for(var i=0; i<ids.length; i++) {
                    	ids[i].checked="";
                    }
                }
           }
    	
    	function closebookWindow(){
    		$('#w').window('close');
    	}
    	
    	function AjaxGetData(index,size){
    		$.ajax({
    			url: '${pageContext.request.contextPath}/acticle/searchData.do',
    			type: 'Get',
    			data: 'pageIndex='+ index + '&pageSize=' + size + '&' +  encodeURI(encodeURI(decodeURIComponent($("#form1").serialize(),true))),
    			dataType: 'json',
    			beforeSend: function(XMLHttpRequest){
    				$.messager.progress({
    					title: 'please waiting...',
    					msg: 'Loading data'
    				});
    			},
    			success: function(data){
    				var json = data.items;
    				var html = "";
    				html += "<table style=\"font-size:13px;\" width=100% border=1 align=center cellpadding=\"3\" cellspacing=\"1\" bgcolor=\"#555555\">";
    				html += "<tr><td height=28 colspan=9><strong style=\"color: #98CAEF\">&nbsp;&nbsp;文章信息列表</strong></td></tr>";;
    				html += "<tr><td bgcolor=#FFFFFF colspan=9><div style=\"margin-top:5px;\" >";
    				html += "<table style=\"font-size:13px;\" width=100% border=1 cellpadding=3 cellspacing=1 bgcolor=\"#EFEFEF\">";
    				html += "<tr height=26 bgcolor=\"#FFFFFF\" style=\"font-weight: bold;text-align:center;\" background=\"../images/search.gif\"><td width=\"3%\" align=\"center\"><input id=\"selectAll\" name=\"selectAll\" type=\"checkbox\" onclick=\"checkJunk(this);\"></td><td width=\"4%\">ID</td><td>篇名</td><td>作者</td><td>国别</td><td>体裁</td><td>表达方式</td><td>一级内容</td><td>二级内容</td></tr>";
    				html += "<tbody>";
    				
    				for(var i=0;i<json.length;i++){
    					var obj = json[i];
    					html += "<tr height=26 style=\"text-align:center;\" bgcolor=#FFFFFF onMouseOver=\"style.backgroundColor='#fff8d8'\" onMouseOut=\"style.backgroundColor='#ffffff';\">";
    					html += "<td align=\"center\"><input type=\"checkbox\" value="+obj.id+" name=\"ids\"></td>";
    					html += "<td>"+obj.id+"</td>";
    					html += "<td>"+obj.name+"</td>";
    					html += "<td>"+obj.author+"</td>";
    					html += "<td>"+countries[obj.countryId]+"</td>";
    					html += "<td>"+obj.genre+"</td>";
    					html += "<td>"+obj.presentation+"</td>";
    					html += "<td>"+contentMap[obj.firConId]+"</td>";
    					html += "<td>"+contentMap[obj.secConId]+"</td>";
    					html += "</tr>";					
    				}
    				html += "</tbody>";
                    html += "</table></td></tr>";
                    html += "<tr height=30 bgcolor=#FFFFFF>";
                    html += "<td height=30 align=right colspan='9'>";
                    html += "<span>当前共" + data.recordCount + "条    当前第" + data.pageIndex + "</span>" + "/<span id='count'>" + data.pageCount + "</span>页&nbsp;&nbsp;";
                    html += "<a href='javascript:void' onclick='GoToFirstPage()' id='aFirstPage' ><span style=\"color:blue;\">首页</span></a>&nbsp;&nbsp;";
                    html += "<a href='javascript:void' onclick='GoToPrePage()' id='aPrePage' ><span style=\"color:blue;\">上一页</a></span>&nbsp;&nbsp;";
                    html += "<a href='javascript:void' onclick='GoToNextPage()' id='aNextPage'><span style=\"color:blue;\">下一页</a></span>&nbsp;&nbsp;";
                    html += "<a href='javascript:void' onclick='GoToEndPage()' id='aEndPage' ><span style=\"color:blue;\">尾页</a></span>&nbsp;&nbsp;";
                    html += "<input type='text' size='4' />&nbsp;<input type='button' value='跳转' onclick='GoToAppointPage(this)' />&nbsp;";
                    html += "</td>";
                    html += "</tr>";
                    html += "</table>";  
                    $.messager.progress('close');
                    $('#divResult').html("");
                    $('#divResult').html(html);   
    			},
    			error: function (XMLHttpRequest, textStatus, errorThrown) {                     	
  						$.messager.alert("提示信息","数据加载失败"+ errorThrown,"error");
                    }  
    		});
    	}
    	
    	function GoToFirstPage() {
                pageIndex = 1;
                AjaxGetData( pageIndex, pageSize);
            }
            
            function GoToPrePage() {
                pageIndex -= 1;
                pageIndex = pageIndex >= 1 ? pageIndex : 1;
                AjaxGetData( pageIndex, pageSize);
            }
           
            function GoToNextPage() {
                if (pageIndex < parseInt($("#count").text())) {
                    pageIndex += 1;
                }
                    AjaxGetData( pageIndex, pageSize);
            }
           
            function GoToEndPage() {
                pageIndex = parseInt($("#count").text()) ;
                AjaxGetData( pageIndex, pageSize);
            }
           
            function GoToAppointPage(e) {
                var page = $(e).prev().val();
                if (isNaN(page)) {
                    alert("Page should be a valid number");
                }
                else {
                    var tempPageIndex = pageIndex;
                    pageIndex = parseInt($(e).prev().val());
                    if (pageIndex <= 0 || pageIndex > parseInt($("#count").text())) {
                        pageIndex = tempPageIndex;
                        alert("Please input valid page scope!");
                    }
                    else {
                        AjaxGetData(pageIndex, pageSize);
                    }
                }
            }
    	
    	function submitForm(){
    		$.messager.confirm('系统提示','您确定要提交以上修改信息吗?',function(r){
    			if(r){
    			 $('#_form').submit();
    			}
    		});
    	}
    	
    	$(function(){			
			openbookWindow();
			
			$('#btnEp').click(function(){
				closebookWindow();
				selectData();
			});
			
			$('#btnCancel').click(function(){
				closebookWindow();
			});
			
			$('#doAction').click(function(){
				submitForm();
			})
		});
    </script>
  </head>  
  <body>
     <div class="box-positon">
		<div class="rpos">当前位置: 书籍内容 - 编辑</div>
			<input class="ropt return-button" style="margin:5px 0 0 5px" type="submit" onclick="javascript:history.back(-1);" value="返回列表"/>
		<div class="clear"></div>
	</div>
	<div class="body-box" style="margin-top:10px;">
	<form id="_form" action="${pageContext.request.contextPath }/book/complexUpdate.do" method="post">
	<input type="hidden" name="id" value="${book.id }"/>
	<table style="font-size:13px;" width=100% border=1 align=center cellpadding="3" cellspacing="1" bgcolor="#555555">
		<tr><td height=28 colspan=10><strong style="color: #98CAEF">&nbsp;&nbsp;书籍详细信息</strong></td></tr>
		<tr><td bgcolor=#FFFFFF colspan=10><div style="margin:5px;" >
		<table class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
			<tr>
        		     <td width="13%" style="background-color:#E6F2FE">书名:</td>
        		     <td style="text-align:left"><input name="name" value="${book.name }" class="input_box" /></td>
        		   	 <td width="13%" style="background-color:#E6F2FE">书籍ID:</td>
        		     <td width="37%" style="text-align:left">${book.id }</td>
        	</tr>
        	<tr>
        			<td width="13%" style="background-color:#E6F2FE">作者:</td>
        			<td style="text-align:left"><input value="${book.author }" class="input_box" name="author"/></td>
        			<td width="13%" style="background-color:#E6F2FE">出版社:</td>
        			<td width="37%" style="text-align:left"><input value="${book.publisher }" class="input_box" name="publisher"/></td>
        	</tr>
        	<tr>
        		<td width="13%" style="background-color:#E6F2FE">所属类别:</td>
        		<td style="text-align:left">${categoryStr }</td>
        		<td width="13%" style="background-color:#E6F2FE">出版日期:</td>
        		<td style="text-align:left">
        			<c:if test="${book.publishDate != 0 }"><fmt:formatDate value="${book.publishDateText }" type="date"/></c:if></td>
        	</tr>
			<tr>
				<td width="13%" style="background-color:#E6F2FE">创建时间:</td>
				<td style="text-align:left"><fmt:formatDate value="${book.createTimeText }" type="date"/></td>
				<td width="13%" style="background-color:#E6F2FE">书籍描述:</td>
				<td style="text-align:left"><input value="${bokk.bookDescription }" class="input_box" name="bookDescription" /></td>
			</tr>
		</table>
		</div>
		</td>
		</tr>
		<tr><td bgcolor=#FFFFFF colspan=10>
			<div style="margin:5px;" >
				<table id="tabmodel" class="dwmc" width="100%" cellspacing="1" cellpadding="2" border="0">
					<tbody>
					<tr style="font-weight: bold;text-align:center;" background="../images/search.gif">
						<td width="1%"><input name="selectAll" type="checkbox" onclick="checkJunks(this)"></td>
						<td width="2%">ID</td>
						<td>文章篇名</td>
						<td>作者</td>
						<td>国别</td>
						<td>体裁</td>
						<td>表达方式</td>
						<td>一级内容</td>
						<td>二级内容</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${book.acticles }" var="acticle">
					<tr height=26 style="text-align:center;" bgcolor=#FFFFFF onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
						<td><input type="checkbox" value="${acticle.id}" name="idss"></td>
						<td>${acticle.id }<input type="hidden" value="${acticle.id }" name="idss" ></td>
						<td>${acticle.name }</td>
						<td>${acticle.author }</td>
						<td>${countries[acticle.countryId] }</td>
						<td>${acticle.genre }</td>
						<td>${acticle.presentation }</td>
						<td>${contentMap[acticle.firConId] }</td>
						<td>${contentMap[acticle.secConId]}</td>
						<td><input class="del-button" type="button" onclick="delRow(this);" value="删除"></td>
					</tr>
					</c:forEach> 
					</tbody>
				</table>
			</div>
			</td>
		</tr>
		</table>
		</form>
	</div>
	<div style="margin:10px;">
		<input id="add-book" class="save-order" type="button" value="添加文章" onclick="addBook();">
		&nbsp;&nbsp;<input class="del-button" type="button" onclick="batchDel();" value="删除">
		<input id="doAction" style="cursor: pointer;float:right" type="button" class="submit" value="提交" ">		
	</div>
	<!--acticle search -->
	<div id="w" class="easyui-window" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 800px; height: 500px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form id="form1" name="form1" action="">
            	<div style="margin-top:10px;">
            		<strong>篇名:</strong><input class="input_box" type="text" name="acticleName" style="vertical-align: middle;color: #555555;width:20%;margin-left:5px;"/>
            		&nbsp;&nbsp;<strong>作者:</strong><input class="input_box" type="text" name="author" style="vertical-align: middle;color: #555555;width:15%;margin-left:5px;">
					&nbsp;&nbsp;<strong>国别:</strong>
						<select name="countryId" style="margin-left:5px;width:15%;">
        		     		<option value="">--请选择--</option>
        		     		<c:forEach items="${countries}" var="item" varStatus="status">
        		     			<option value="${status.count - 1}">${item}</option>
        		     		</c:forEach>
        		     	</select>
      				&nbsp;&nbsp;<strong>体裁:</strong>
        				<select name="genre" style="margin-left:5px;width:15%;">
							<option value="">--请选择--</option>
							<c:forEach items="${genres}" var="v">
								<option value="${v}">${v}</option>
							</c:forEach>
						</select>
            	</div>
            	<div style="padding:5px 0 5px 0;"><strong>表达方式:</strong>
						<select name="presentation" style="width:15%;margin-left:5px;">
							<option value="">--请选择--</option>
        		     		<c:forEach items="${presentations}" var="p">
        		     			<option value="${p}">${p}</option>
        		     		</c:forEach>
        		     	</select>				
		&nbsp;&nbsp;<strong>一级内容:</strong>
						<select style="margin-left:5px;width:15%;" name="firConId">
							<option value="">--请选择--</option>
							<c:forEach items="${contentMap}" var="v">
								<option value="${v.key}" >${v.value }</option>
							</c:forEach>							    
						</select>
		&nbsp;&nbsp;<strong>二级内容:</strong>
						<select style="margin-left:5px;width:15%;" name="secConId">
							<option value="">--请选择--</option>
							<c:forEach items="${contentMap}" var="v">
								<option value="${v.key}" >${v.value }</option>
							</c:forEach>							    
						</select>
		&nbsp;&nbsp;<input class="query" type="button" value="查询" onclick="AjaxGetData(1,10);"/></div>	
		</form>
		<div id="divResult" style="margin-top:5px;font-family: 宋体,Arial Narrow,arial,serif;font-size: 13px;"></div>              
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton"  href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton"  href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>
	
	</body>	