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
<title>公告管理</title>
</head>
<body>
<center>
<h1>
<strong>公告管理</strong>
</h1>
<form>
<table border="1">
<tr bgcolor="#ADEAEA">
<td>标题</td>
<td>公告内容</td>
<td>提交时间</td>
</tr>
   <c:forEach items="${sessionScope.bulletinlist}" var="bulletin"  >
   <tr>
  <td>${bulletin.title}</td>
  <td>${bulletin.bulletinInfo}</td>
  <td>${bulletin.commitTime}</td>
  <td>
  <a href="${pageContext.request.contextPath}/property/deletebulletinnow.do?bulletinid=${bulletin.id}">删除</a>
  </td>
  </tr>
  </c:forEach>
  <tr><td><a href="${pageContext.request.contextPath}/property/gotoaddbulletin.do">添加新公告</a></td></tr>
</table>
${sessionScope.result}
</form>
</center>
</body>
</html>