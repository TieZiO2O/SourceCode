<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="add.do" method="POST" enctype="multipart/form-data">
		<input type="file" name="img"/>
		题目：<input type="text" name="title"/>
		内容：<input type="text" name="content"/>
		phone:<input type="text" name="phone">
		<input type="hidden" name="uid" value="1"/>
		<input type="submit" value="提交">
	</form>
</body>
</html>