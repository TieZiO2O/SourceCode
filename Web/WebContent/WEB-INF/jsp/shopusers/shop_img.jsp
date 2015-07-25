<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>店面信息图片管理</title>
</head>
<body>
<center>
<h1>
<strong>店面信息图片管理</strong>
</h1>
<form>
<table>
 <tr>
 <c:set var="line" value="0"/>  <%-- 控制每行的输出量 --%>
  <c:forEach items="${sessionScope.shopimglist}" var="shopimg">
  <c:if test="${line==4}">
  <c:set var="line" value="0"/> 
  </tr>
  </c:if>
  <c:set var="line" value="${line+1}"/>
  <td>
  <img src="${pageContext.request.contextPath}${shopimg.imgUrl}" alt="商品图片" width="160" height="160"/>
  <br/>
 <a href="${pageContext.request.contextPath}/user/deleteshopimg.do?photoid=${shopimg.id}">删除</a>
 <br/>
 </td>&nbsp;&nbsp;
  </c:forEach>
  </tr>
</table>
<a href="${pageContext.request.contextPath}/user/goaddshopimg.do">添加新图片</a>
</form>
</center>
</body>
</html>