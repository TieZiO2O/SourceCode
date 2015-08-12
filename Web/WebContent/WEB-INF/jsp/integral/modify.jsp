<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="modify.do" method="post">
		用户名：<input type="text" name="userId"/><br/>
		分　 数：<input type="text" name="fraction"/><br/>
		选　择：
		<select name="addOrFract">
			<option value="true">增加</option>
			<option value="false">减少</option>
		</select><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>