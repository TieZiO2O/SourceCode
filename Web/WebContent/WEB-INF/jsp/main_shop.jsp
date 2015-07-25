<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商户界面</title>
</head>

	<frameset rows="48,54%" border="1">
		<frame src="${pageContext.request.contextPath}/user/shop_top.do" />
		<frameset cols="250,*">
			<frame src="${pageContext.request.contextPath}/user/shop_left.do"/>
			<frame src="${pageContext.request.contextPath}/user/shop_main.do" name="right"/>
		</frameset>
	</frameset>
</html>