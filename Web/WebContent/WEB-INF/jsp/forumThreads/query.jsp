<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="isthreadbelong.do" method="POST">
		<input type="text" value="94c9dfd6-258d-4805-8e97-c65db418fdeb" name="guid">
		<input type="text" value="1" name="uid">
		<input type="submit" value="查一个">
	</form>
	
	<form action="deletebyguid.do" method="POST">
		<input type="text" value="94c9dfd6-258d-4805-8e97-c65db418fdeb" name="guid">
		<input type="submit" value="删除">
	</form>
	
	<form action="getAllByUid.do" method="POST">
		<input type="text" value="1" name="uid">
		<input type="submit" value="查多个">
	</form>
</body>
</html>