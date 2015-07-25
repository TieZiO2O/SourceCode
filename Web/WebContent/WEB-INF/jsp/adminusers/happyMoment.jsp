<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
function lxSearch(){
	window.location.href="happyMoment_search.do";
}
	function lxDel(id){
		
		if (confirm('确定将此该条开心一刻删除?')){
			window.location.href="happyMoment_del.do?id="+id;
        }
	}
	function lxAdd(){
		window.location.href="happyMoment_add.do";
	}
	
</script>
</head>
<body>
	<center>
		<h1>开心一刻</h1>
		<p>
			 <a href="javascript:lxSearch()" id="search">查询</a>
			 <a href="javascript:lxAdd()" id="add">添加</a>
			 
		</p>
			 
	</center>
	<hr />
	<div>
	<c:if test="${happyMoments!=null}">
		<table border="1" width="880px">
			<tr>
				<th>序号</th>
				<th>主题</th>
				<th>内容</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${happyMoments}" var="me" varStatus="vs">
				<tr>
					<td>${vs.count }</td>
					<td>${me.title }</td>
					<td>${me.content }</td>
					<td>
						<a href="javascript:lxDel(${me.id })">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${happyMoments==null}">
		对不起，没有开心一刻...
	</c:if>
	${result }
	</div>

</body>
</html>