<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>类别管理-顶级分类</title>
    <link rel="stylesheet" type="text/css" href="/css/admin.css" />
    <link rel="stylesheet" type="text/css" href="/css/theme.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" /> 
    <link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		//添加类别窗口
		function openCategoryWindow(){
			$('#w').window({
				title: '添加类别',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 250,
                resizable:false
			});
		}
		
		function closeCategoryWindow(){
			$('#w').window('close');
		}
		
		function addcategory(){
			$('#w').window('open');
		}
		
		function postCategoryForm(){
			if($('#name').val() == ''){
				$.messager.alert('系统提示', '请输入类别名称！', 'warning');
                return false;
			}
			
			$.ajax({
				url :"${pageContext.request.contextPath}/category/add.do",
				type :"Get",
				data :encodeURI(encodeURI(decodeURIComponent($("#_form").serialize(),true))),
				beforeSend : function(XMLHttpRequest){
					$.messager.progress({
						title: 'Please waiting',
						msg: 'loading data ....'
					});
				},
				
				success: function(data){
					$.messager.progress('close');
					location.href = location.href;
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
                        //alert(XMLHttpRequest);
                        //alert(textStatus);
                        //alert(errorThrown);
                        closeCategoryWindow();	
  						$.messager.alert("提示信息","数据添加失败"+ errorThrown,"error");
                    }  
			});
		}
		
		function optDelete(){
			var ids = $('[name=ids]');
			var count = 0;
			for(var i = 0;i<ids.length;i++ ){
				if(ids[i].checked) count ++;
			}
			if(count == 0){
				$.messager.alert('提示信息','请选择要删除的类别,此操作请谨慎!','warning');
				return;
			} 
			$.messager.confirm('提示信息','确定要删除这' + count + '个类别吗?请谨慎操作,类别删除后无法恢复.',function(flag){
				if(flag){
					$('#form1').submit();
				}
			});
		}
		
		function checkJunk(obj){
			var ids = $('[name=ids]');
			if(obj.checked){
				for(var i = 0;i < ids.length;i++){
					ids[i].checked = 'checked';
				}
			}else{
				for(var i = 0;i < ids.length;i++){
					ids[i].checked = '';
				}
			}
		}
		
		$(function(){			
			openCategoryWindow();
			
			$('#btnEp').click(function(){
				postCategoryForm();
			});
			
			$('#btnCancel').click(function(){
				closeCategoryWindow();
			});
		});
	</script>
  </head> 
  <body>
    <div class="box-positon">
		<div class="rpos">当前位置: 类别管理-顶级分类</div>
		<div class="clear"></div>
	</div>
	<form id="form1" method="post" action="${pageContext.request.contextPath }/category/delCategory.do">
	<div class="body-box" style="margin-top:10px;">		
		<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0" style="">
		<thead class="pn-lthead">
			<tr>
				<th width="30"><input name="selectAll" type="checkbox" onclick="checkJunk(this)"></th>
				<th>ID</th>
				<th>类别名称</th>
				<th>类别描述</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody class="pn-ltbody">	
			<c:forEach items="${categoryList }" var="category">
				<tr bgcolor="#FFFFFF" onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
					<td align="center"><input type="checkbox" value="${category.id}" name="ids"></td>
					<td align="center">${category.id }</td>
					<td align="center">${category.name }</td>
					<td align="center">${category.description }</td>
					<td align="center"><fmt:formatDate value="${category.createDateTime }" type="date" /></td>
					<td align="center"><a href="${pageContext.request.contextPath }/category/find/${category.id}.do">查看</a> 
						| 
						<a href="${pageContext.request.contextPath }/category/edit/${category.id}.do">编辑</a></td>
				</tr>
			</c:forEach>
		</tbody>	
		</table>		    
	</div>
	<div style="margin:10px;">
		<input class="del-button" type="button" onclick="optDelete();" value="删除">
		&nbsp;&nbsp;<input id="add-category" class="save-order" type="button" onclick="addcategory();" value="添加类别">
	</div>
	</form>
	<!-- 添加类别窗口 -->
	<div id="w" class="easyui-window" title="添加类别" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            	<form id="_form" action="">
            	<input type="hidden" name="parentId" value="${parentId }"/>
                <table cellpadding=3>
                    <tr>
                    	<td>上级类别：</td>
                    	<td><c:choose><c:when test="${parentId == 0 }">顶级类别</c:when><c:otherwise>${nodeMap[parentId]}</c:otherwise></c:choose></td>
                    </tr>
                    <tr>
                        <td>类别名称：</td>
                        <td><input id="name" name="name" type="text" /></td>
                    </tr>
                    <tr>
	    				<td>类别描述:</td>
	    				<td><textarea id="description" name="description" style="height:60px;"></textarea></td>
	    			</tr>
                </table>
                </form>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" href="javascript:void(0)" >确定</a>
                <a id="btnCancel" class="easyui-linkbutton" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>
	
	</body>
</html>
