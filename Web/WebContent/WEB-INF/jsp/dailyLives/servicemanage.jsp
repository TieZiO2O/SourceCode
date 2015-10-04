<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/fileinput.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/fileinput_locale_zh.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/place_mt.js"></script>
	<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/css/fileinput.css" rel="stylesheet" media="all">
	
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
	function deleteOne(value){
		$(value).attr("disabled","true");
		var sid=$(value).attr("name");
		$.ajax({
			url:"${pageContext.request.contextPath}/dailyLives/delete_dailylives_byguid.do",
			data:{guid:sid},
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
<title>服务管理</title>
</head>	
<body>
	<br>
	<div>
		<form action="servicemanage.do" method="post">
			<table>
				<tr>
					<td>标题：<input type="text" name="title">&nbsp;</td>
					<td>电话：<input type="text" name="phone">&nbsp;</td>
					<td>服务类型：<select id="sltStyle">
									<c:forEach items="${dailyLivesType }" var="type">
										<option value="${type.id }">${type.style }</option>	
									</c:forEach>
								</select>&nbsp;
					</td>
					<td><input type="submit" value="查询" class="btn"></td>
				</tr>
			</table>
			
		</form>
	</div>
	<br>
	<c:forEach items="${dailyLives }" var="daily">
		<hr>
		<div id="${daily.guid }">
			<div>
				<div>
					</div>
				<table class="table">
					<tr>
						<td>
							<table class="table">
								<tr>
									<td colspan="4">
										<span>${daily.title }</span>&nbsp;&nbsp;&nbsp;&nbsp;
										<span><input type="button" class="btn btn-danger" id="btnDelete" onclick="javascript:deleteOne(this)" name="${daily.guid }" value="删除"></span>
										<a class="btn btn-large btn-primary" 
										href="${pageContext.request.contextPath}/dailyLives/jumptoedit.do?guid=${daily.guid}">修改</a>
									</td>
								</tr>
								<tr>
									<td>电　　话：</td>
									<td>${daily.phone }</td>
									<td>地　　址：</td>
									<td>${daily.address }</td>
								</tr>
								<tr>
									<td>所属类型：</td>
									<td>${daily.dailyLivesType.style}</td>
									<td>创建时间:</td>
									<td><fmt:formatDate value="${daily.createDate}" type="both" dateStyle="long"/> </td>
								</tr>
								<tr>
									<td>顾客须知：</td>
									<td>${daily.customerNotice }</td>
									<td>内容：</td>
									<td>${daily.content }</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<div style="text-align:right">
								<c:if test="${daily.images.size()>1 }">
									<c:forEach items="${daily.images }" var="img">
										<img src="${img }" width="100px" height="150px">
									</c:forEach>
								</c:if>
							</div>
						</td>
					</tr>
				</table>
			</div>
			
		</div>
	</c:forEach>
</body>
</html>