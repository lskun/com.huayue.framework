<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>   
    <title>文章管理-顶级分类</title>
    	<link rel="stylesheet" type="text/css" href="/css/admin.css" />
    	<link rel="stylesheet" type="text/css" href="/css/theme.css" />
    	<link rel="stylesheet" type="text/css" href="/css/style.css" /> 
    	<link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    	<link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
    	<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    	<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
    	<script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
    	<script type="text/javascript">
	    	function Go(page){
				document.getElementById("pageIndex").value = page;
				$("#_form").submit();
			}
			function GoIndex(){
				var max_index = "${acticlePagination.pageCount}";
				var idx = $('#index').value;
				if(!isNaN(idx)) idx = 1;
				else(idx > max_index || idx <= 0)
				{
					idx = max_index;
				} 
				$("#pageIndex").value = idx;
				$("#_form").submit();
			}
			
			function delBook(bookId){
				$.messager.confirm('系统提示','确定要删除该条记录吗?',function(r){
           			if(r){
           				$.post('${pageContext.request.contextPath}/book/delBook.do?id='+ bookId ,function(data){
           					if(data == 'OK'){
           						$.messager.alert('提示信息','记录删除成功!','info');
           						location.href = '/book/b_list.do';
           					}
           				});
           			}
           		});
			}
			
    	</script>
    </head>
	<body>
		<div class="box-positon">
		<div class="rpos">当前位置: 书籍管理-顶级分类</div>
		<form class="ropt" style="margin-right:10px;">
			<input class="add" type="submit" onclick="this.form.action='add_view.do';" value="添加">
			<input type="hidden" value="${nodeId }" name="nodeId">
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box" style="margin-top:10px;">
		<form id="_form" style="padding:5px;" method="post" action="${pageContext.request.contextPath }/book/b_list.do">
			<input type="hidden" value="${nodeId }" name="root">
			<input type="hidden" id="pageIndex" name="pageIndex"/>
			<div>
				书名:
				<input type="text" class="input_box" style="width:100px" value="" name="bookName">
				&nbsp;&nbsp;作者:
				<input type="text" class="input_box" style="width:70px" value="" name="author">
				&nbsp;&nbsp;出版社:
				<input type="text" class="input_box" style="width:120px" value="" name="publisher">						
				&nbsp;&nbsp;创建时间段:
				<input id="stime" name="stime" style="margin-left:5px;" type="text" class="input_box" size="30" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})"/>
				&nbsp;至&nbsp;
				<input id="etime" name="etime" style="margin-left:5px;" type="text" class="input_box" size="30" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})"/>
				<input class="query" type="submit" value="查询">	
			</div>
		</form>
		<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0" style="">
			<thead class="pn-lthead">
				<tr>
					<th width="30"><input name="selectAll" type="checkbox" onclick="checkJunk()"></th>
					<th>ID</th>
					<th>书名</th>
					<th>作者</th>
					<th>描述</th>
					<th>出版社</th>
					<th>文章总数</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="pn-ltbody">	
			<c:forEach items="${bookCollection.items}" var="book">
				<tr bgcolor="#FFFFFF" onMouseOver="style.backgroundColor='#fff8d8'" onMouseOut="style.backgroundColor='#ffffff';">
					<td align="center"><input type="checkbox" value="${book.id}" name="ids"></td>
					<td align="center">${book.id }</td>
					<td align="left"><strong>[${nodeMap[book.categoryId] }]</strong>&nbsp;&nbsp;${book.name }</td>
					<td align="center">${book.author }</td>
					<td align="left">${book.bookDescription }</td>
					<td align="center">${book.publisher }</td>
					<td align="center">${book.acticleCount }</td>
					<td align="center"><fmt:formatDate value="${book.createTimeText }" type="date" /></td>
					<td align="center">
						<a class="pn-opt" href="${pageContext.request.contextPath }/book/showDetail.do?id=${book.id}">查看</a>&nbsp;&nbsp;|&nbsp;&nbsp;
						<a class="pn-opt" href="${pageContext.request.contextPath }/book/showDetail.do?id=${book.id}&type=edit">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;
						<a class="pn-opt" onclick="delBook(${book.id})" href="#">删除</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tbody>
				<tr>
					<td class="pn-sp" align="center">
						 当前共${bookCollection.recordCount }条 当前第${bookCollection.pageIndex }/${bookCollection.pageCount } 页 <input type="button" class="first-page" value="首页" onclick="javascript:GO(1);"/>
                             <input class="pre-page" type="button" value="上一页" onclick="javascript:Go(${bookCollection.prevPage})"> 
                             <input class="next-page" type="button" value="下一页" onclick="javascript:Go(${bookCollection.nextPage })">
                             <input class="last-page" type="button" value="尾页" onclick="javascript:Go(${bookCollection.pageCount})">
                           	  转到第&nbsp;<input id="index" style="width:30px;" type="text">&nbsp;页  <input type="button" class="go" value="转" onclick="javascript:GoIndex()">&nbsp;&nbsp;&nbsp;        
					</td>
				</tr>
			</tbody>
		</table>			
	</div>
	</body>
</html>