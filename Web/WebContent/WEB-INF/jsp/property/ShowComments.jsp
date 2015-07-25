<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>评论管理</title>
<script type="text/javascript">
function lxDel(id){
	
	if (confirm('确定将该反馈删除?')){
		window.location.href="deletecommentnow.do?commentsid="+id;
    }
}
</script>
</head>
<body>
<center>
<h1>
<strong>评论管理</strong>
</h1>
<form>
<table border="1">
<tr bgcolor="#ADEAEA">
<td>ID</td>
<td>评论内容</td>
<td>提交时间</td>
<td>评论用户</td>
</tr>
   <c:forEach items="${sessionScope.commentslist}" var="comments"  >
   <tr>
  <td>${comments.id}</td>
  <td>${comments.msg}</td>
  <td>${comments.commitTime}</td>
  <td>${comments.memName}</td> 
  <%-- <td>
  	<a href="${pageContext.request.contextPath}/property/deletecommentnow.do?commentsid=${comments.id}">删除</a>
  </td> --%>
  <td><a href="javascript:lxDel(${comments.id })">删除</a></td>
  </tr>
  </c:forEach>
</table>
</form>
</center>
</body>
</html>