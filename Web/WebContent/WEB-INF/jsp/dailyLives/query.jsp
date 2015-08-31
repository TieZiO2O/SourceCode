<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
function deleteOne(value){
	$(value).attr("disabled","true");
	var sid=$(value).attr("name");
	$.ajax({
		url:"${pageContext.request.contextPath}/dailyLives/deleteonedailytype.do",
		data:{sid:sid},
		success:function(data){
			var json=JSON.parse(data);
			if(json.result=="success"){
				alert("删除成功");
				$("#"+sid).remove();
			}else{
				alert("删除失败");
			}
			$(value).removeAttr("disabled");
		},
		error:function(data){
			alert("删除失败");
			$(value).removeAttr("disabled");
		}
	});
}
</script>
</head>
<body>
	<!-- <form action="getAllServiceTypeBySid.do" method="post">
		<input type="text" value="1" name="sid">
		<input type="submit" value="提交">
	</form> -->
	<jsp:include page="addservicetype.jsp"></jsp:include>
	<h3 style="text-align:center">服务类型</h3>
	<div style="text-align:center" >
		<table class="table table-condensed">
			<thead>
				<tr>
					<th>序列号</th>
					<th>服务名称</th>
					<th>服务所属类型</th>
					<th>logo地址</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dailyLives}" var="data">
					<tr id="${data.id }">
						<td>${data.id}</td>
						<td>${data.style}</td>
						<td>${data.serviceType}</td>
						<td>${data.logoPath}</td>
						<td>
							<input type="button" value="删除" name="${data.id}" onclick="javascript:deleteOne(this)">
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>