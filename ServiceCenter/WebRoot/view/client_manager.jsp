<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>客户关系档案管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" type="text/css" href="/css/dev.css" />
	<link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
    <script type="text/javascript" src="/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.js"></script>	
	<script type="text/javascript" src="/js/Address.js"></script>
	    <script type="text/javascript">        
            var pageIndex = 0;
            var pageSize = 20;
            $(function () {             
                    pageIndex = 1;
                    //progress();
                    AjaxGetData(pageIndex, pageSize);              
            });

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

			function optDelete(){
				var ids = $('[name=ids]');
			    var tmp = 0;
			    var val = "";
				for(var i=0; i<ids.length; i++){
						if(ids[i].checked) {
							tmp+=1;
							val += ids[i].value;
							val +=",";
						}
					}				
				if(tmp == 0 ) {
					$.messager.alert("提示信息","请选择要删除的记录!","warning");
					return;
				}
				//alert(val);
				$.messager.confirm("提示信息","确定要删除这" + tmp + "条记录吗?",function(flag){
					if(flag){
						$.ajax({
							url: "${pageContext.request.contextPath}/crm/delete.do",
							type: "Get",
							data: "ids=" + val,
							dataType:"json",
							beforeSend:function(){
								$.messager.progress({  
                					title:'Please waiting',  
                					msg:'Loading data...'  
            					}); 
							},
							success:function(data){
								if(data.del=="true"){
									$.messager.alert("提示信息","数据删除成功!","info");
									$.messager.progress('close');
									AjaxGetData(pageIndex, pageSize);
								}else{
									$.messager.alert("提示信息","数据删除失败!","error");
									$.messager.progress('close');
								}
							}
						});	
					}
				});	
				
			}
			
            function AjaxGetData(index, size) 
            {
                $.ajax({
                    url: "${pageContext.request.contextPath}/crm/clientListAjax.do",
                    type: "Get",
                    data: "pageindex=" + index + "&pagesize=" + size + "&" + encodeURI(encodeURI(decodeURIComponent($("#form1").serialize(),true))),
                    dataType: "json",
                    beforeSend : function(XMLHttpRequest) {                    	  
						$.messager.progress({  
                			title:'Please waiting',  
                			msg:'Loading data...'  
            			}); 
                    },  
  
                    success: function (data) {
                         var json = data.items;
                         var html = "";
                     
                         html += "<table style=\"font-size:13px;\" width=100% border=1 align=center cellpadding=\"3\" cellspacing=\"1\" bgcolor=\"#555555\">";
                         html += "<tr><td height=28 colspan=11><strong style=\"color: #98CAEF\">&nbsp;&nbsp;客户信息列表</strong></td></tr>";
                         html += "<tr><td bgcolor=#FFFFFF colspan=12><div style=\"margin:5px;\" >";
                         html += "<table style=\"font-size:13px;\" width=100% border=1 cellpadding=3 cellspacing=1 bgcolor=\"#CCCCCC\">";
                         html += "<tr height=26 bgcolor=\"#FFFFFF\" style=\"font-weight: bold;text-align:center;\" background=\"../images/search.gif\"><td width=\"2%\" align=\"center\"><input id=\"selectAll\" name=\"selectAll\" type=\"checkbox\" onclick=\"checkJunk(this);\"></td><td>ID</td><td>单位名称</td><td>姓名</td><td>性别</td><td>职务</td><td>办公电话</td><td>手机</td><td>邮箱</td><td>传真</td><td>添加时间</td><td>相关操作</td></tr>";            
                         html += "<tbody>";     
                      for(var i=0;i<json.length;i++)
                      {               
                          var obj = json[i];
                       	   html += "<tr height=26 style=\"text-align:center;\" bgcolor=#FFFFFF onMouseOver=\"style.backgroundColor='#fff8d8'\" onMouseOut=\"style.backgroundColor='#ffffff';\">";
						   html += "<td align=\"center\"><input type=\"checkbox\" value="+obj.id+" name=\"ids\"></td>";	
                       	   html += "<td>"+obj.id+"</td>";
                       	   html += "<td>"+obj.unit+"</td>";
                           html += "<td>"+obj.name+"</td>";
                           html += "<td>"+obj.sex+"</td>";
                           html += "<td>"+obj.duty+"</td>";
                           html += "<td>"+obj.phone+"</td>";
                           html += "<td>"+obj.mobile+"</td>";
                           html += "<td>"+obj.email+"</td>";
                           html += "<td>"+obj.fax+"</td>";
                           html += "<td>"+new Date(obj.registerTime).toLocaleDateString()+"</td>";
                           html += "<td><a href='/crm/editClient.do?id="+obj.id+"'><span style=\"color:blue;\">编辑</span></a>&nbsp;|&nbsp;<a href='/crm/showInfo.do?id="+obj.id+"'><span style=\"color:blue;\">查看</span></a></td>";
                           html += "</tr>";
                       }
                       html += "</tbody>";
                       html += "</table></td></tr>";
                       html += "<tr height=30 bgcolor=#FFFFFF>";
                       html += "<td height=30 align=right colspan='12'><div style=\"float:left;margin-left:5px;\"><input type=\"button\" class=\"del-button\" value=\"删除\" onclick=\"optDelete();\" />&nbsp;&nbsp;<input type=\"button\" value=\"短信发送\" class=\"sblue_btn\" onclick=\"multiTaken();\"></div>";
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
                        //alert(XMLHttpRequest);
                        //alert(textStatus);
                        //alert(errorThrown);	
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
            
            function multiTaken(){
 				var ids = $('[name=ids]');
 				var val = "";
 				var tmp = 0;
 				for(var i=0; i < ids.length; i++ ){
 					if(ids[i].checked){
 						tmp += 1;
 						val += ids[i].parentNode.parentNode.cells[7].innerHTML;
						val += "<br/>"
 					}
 				}
 				
 				if(tmp == 0){
 					$.messager.alert("提示信息","请选择要发送短信的记录ID!","warning");
 					return ;
 				}
 				//alert(val);
				location.href='/crm/numberCollection.do?ids=' + val;
            }
            
            function progress(){  
            	$.messager.progress({  
                	title:'Please waiting',  
                	msg:'Loading data...'  
            	});  
            	setTimeout(function(){  
                	$.messager.progress('close');  
            	},50000)  
        	}  
        </script>
  </head>
  
  <body bgcolor="#eef2fb" style="width:100%;height:100%;">
    <div class="box-positon">
		<div class="rpos">当前位置: 客户关系档案管理 - 列表</div>
		<div style="float:right;margin-right:5px;margin-top:3px;"><input class="add" type="button" onclick="location.href='${pageContext.request.contextPath}/view/client_add.jsp'" value="添加"></div>
		<div class="clear"></div>
	</div>
	<form id="form1" name="form1">
		<div style="margin:10px;">单位名称：<input name="unit" style="height: 20px;line-height: 24px;" type="text" value="" class="input_box">			 	
	     	            姓名：<input name="name" type="text" style="height: 20px;line-height: 24px;" value="" class="input_box"> 
	     	            所在地：   	<select id="deliverprovince" name="deliverprovince"></select>
						 	<select id="delivercity" name="delivercity"></select>
							<select id="deliverarea" name="deliverarea"></select>
							<script src="/js/Address.js"></script>
							<script>
								window.onload=function(){new PCAS("deliverprovince","delivercity","deliverarea");}
							</script><br/><br/>
						<span style="float:left;">类别：</span>	
							<c:forEach items="${categories}" var="v">
	        		     		<input type="checkbox" name="categoryId" id="${v.key}" value="${v.key }" style="width:2%;float:left" /><span class="spa"><label for="${v.key}">${v.value}</label></span>
	        		     	</c:forEach>
						<input class="query" type="button" value="查询" onclick="AjaxGetData(1,20);">	
	     </div>
     </form>
	 <div id="divResult" style="margin:10px;font-family: 宋体,Arial Narrow,arial,serif;font-size: 13px;"></div>	
						
  </body>
</html>
