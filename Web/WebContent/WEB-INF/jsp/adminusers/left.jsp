<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	    
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/fileinput.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/fileinput_locale_zh.js"></script>
	<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/css/fileinput.css" rel="stylesheet" media="all">
	 --%>
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
  
  <body>
      <ul>
      	 <li><a href="${pageContext.request.contextPath}/adminusers/community_page.do" target="right">小区管理</a></li>
      	 <li><a href="${pageContext.request.contextPath}/adminusers/shopper_page.do" target="right">商户管理</a></li>
      	 <li><a href="${pageContext.request.contextPath}/adminusers/property_page.do" target="right">物业管理</a></li>
      	 <li><a href="${pageContext.request.contextPath}/integral/integral_page.do" target="right">积分管理</a></li>
      	 <%-- <li><a href="${pageContext.request.contextPath}/integral/getByUid.do" target="right">查询积分</a></li> --%>
      	 <li><a href="${pageContext.request.contextPath}/dailyLives/servicemanage.do" target="right">服务管理</a></li>
      	 <%-- <li><a href="${pageContext.request.contextPath}/adminusers/upload_page.do" target="right">上传图片</a></li> --%>
      	 <%-- <li><a href="${pageContext.request.contextPath}/user/admin_logout.do" target="right">注销</a></li>
      	 <li><a href="${pageContext.request.contextPath}/adminusers/upload_page3.do" target="right">上传图片</a></li> --%>
      	 <li><a href="${pageContext.request.contextPath}/adminusers/soft_reply.do" target="right">悠然反馈</a></li>
      	 <li><a href="${pageContext.request.contextPath}/adminusers/dailyAbc_page.do" target="right">每日常识</a></li>
      	 <li><a href="${pageContext.request.contextPath}/adminusers/happyMoment_page.do" target="right">开心一刻</a></li>
      	 <li><a href="${pageContext.request.contextPath}/adminusers/ad_page.do" target="right">广告管理</a></li>
      	 <li><a href="${pageContext.request.contextPath}/adminusers/gochangepwd.do" target="right">修改密码</a></li>
      	 <li><a href="${pageContext.request.contextPath}/adminusers/admin_logout.do" target="_parent">注销</a></li>
      </ul>
  </body>
</html>
