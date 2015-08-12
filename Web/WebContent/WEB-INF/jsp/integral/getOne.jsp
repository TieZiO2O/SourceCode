<%@page import="com.nciae.community.domain.Integral"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String pnt="";
	if(request.getAttribute("integral")!=null){
		Integral inte=(Integral)request.getAttribute("integral");
		if(inte.isUsed()){
			pnt="在用";
		}else{
			pnt="不用";
		}
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="getByUid.do" method="post">
		用户名：<input type="text" name="uid"/>
		<input type="submit" value="查询"/>
	</form>
	
	<table>
		<thead>
			<th>id</th>
			<th>用户名</th>
			<th>积分</th>
			<th>是否在用</th>
		</thead>
		<tbody>
			<tr>
				<td>${integral.id }</td>
				<td>${integral.userId }</td>
				<td>${integral.fraction }</td>
				<td>
					<%=pnt %>
				 </td>
			</tr>
		</tbody>
	</table>
</body>
</html>