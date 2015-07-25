<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
function lxSearch(){
	window.location.href="reply_soft_search.do";
}
function lxDel(id){
	
	if (confirm('确定将此该反馈信息删除?')){
		window.location.href="reply_soft_del.do?id="+id;
    }
}
</script>
</head>
<body>
	<center>
		<h1>悠然反馈</h1>
		<p>
			 <a href="javascript:lxSearch()" id="search">查询</a>
		</p>
			 
	</center>
	<hr />
	<div>
	<c:if test="${softReplys!=null}">
		<table border="1" width="880px">
			<tr>
				<th>序号</th>
				<th>反馈信息</th>
				<th>反馈时间</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${softReplys}" var="me" varStatus="vs">
				<tr>
					<td>${vs.count }</td>
					<td>${me.msg }</td>
					<td>${me.commitTime }</td>
					<td>
						<a href="javascript:lxDel(${me.id })">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${softReplys==null}">
		对不起，没有用户对该软件反馈...
	</c:if>
	${result }
	</div>
</body>
</html>