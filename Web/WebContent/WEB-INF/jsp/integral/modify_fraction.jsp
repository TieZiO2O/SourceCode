<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="modify_fraction.do" method="post">
		用户Id：<input type="text" name="userId" value="1"><br/>
		操作类型：<input type="text" name="operatetype" value="1"><br/>
		商品Id：<input type="text" name="commodityId" value="1"><br/>
		<input type="submit" value="提交">
	</form>
	
	<form action="getallcommodity.do" method="post">
		<input type="submit" value="获取所有">
	</form>
	<br/>
	<form action="getcomdetail.do" method="post">
		<input type="text" value="c54bb6fb-37c5-43f7-b463-4cbf7d14a3d1" name="guid">
		<input type="submit" value="获取详细">
	</form>
</body>
</html>