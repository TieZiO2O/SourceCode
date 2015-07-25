<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员界面</title>
</head>
	<frameset rows="48,54%" border="5">
		<frame src="${pageContext.request.contextPath}/user/admin_top.do" />
		<frameset cols="160,*">
			<frame src="${pageContext.request.contextPath}/user/admin_left.do"/>
			<frame src="${pageContext.request.contextPath}/user/admin_main.do" name="right"/>
		</frameset>
	</frameset>

</html>