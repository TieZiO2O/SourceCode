<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body bgcolor="#38B0DE">
      <ul>
      	 <li><a href="${pageContext.request.contextPath}/user/shop_main.do" target="right">首页</a></li>
      	 <br>
      	 <li><a href="${pageContext.request.contextPath}/user/showshopimg.do" target="right">店面商品图片管理</a></li>
      	 <br>
      	 <li><a href="${pageContext.request.contextPath}/user/update.do" target="right">修改信息</a></li>
      	 <br>
      	 <li><a href="${pageContext.request.contextPath}/user/gochangepwd.do" target="right">修改密码</a></li>
      	 <br>
      	 <li><a href="${pageContext.request.contextPath}/user/end.do" target="_top">注销</a></li>
      </ul>
  </body>
</html>
