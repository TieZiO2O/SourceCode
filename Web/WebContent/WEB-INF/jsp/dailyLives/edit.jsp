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
	<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/css/fileinput.css" rel="stylesheet" media="all">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
	function btnDeleteImg(value){
		$(value).attr("disabled","true");
		var sid=$(value).attr("name");
		$.ajax({
			url:"${pageContext.request.contextPath}/dailyLives/delete_imgs.do",
			data:{guid:sid},
			success:function(data){
				var json=JSON.parse(data);
				if(json.result=="success"){
					
					alert("删除成功");
					$("#tdImgs").remove();
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
		<form action="edit.do" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td colspan="2">标　　题：<input type="text" name="title" value="${daily.title}">&nbsp;&nbsp;</td>
					<td colspan="2">电话：<input type="text" name="phone" value="${daily.phone}">&nbsp;&nbsp;</td>
					<td colspan="2">地址：<input type="text" name="address" value="${daily.address}">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="6"><br></td>
				</tr>
				<tr>
					<td colspan="3">顾客须知：<textarea type="text" name="customerNotice" cols="40" rows="10">${daily.customerNotice}</textarea>&nbsp;</td>
					<td colspan="3">内容：<textarea name="content" cols="40" rows="10">${daily.content}</textarea>&nbsp;</td>
				</tr>
				<tr>
					<td id="tdImgs" colspan="6">
						<div style="text-align:right">
							<c:if test="${imgcount>1}">
								<c:forEach items="${daily.images }" var="img">
									<img src="${img }" width="100px" height="150px">
								</c:forEach>
							</c:if>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="5">       
						<br>请选择上传的图片：<input id="file-0a" class="file" name="img" type="file" multiple data-min-file-count="1">
					</td>
				</tr>
				<tr>
					<input type="hidden" name="guid" value="${daily.guid}">
					
					<td colspan="6" style="text-align: right">
						<br>
						<input type="button" onclick="javascript:btnDeleteImg(this)" name="${daily.guid}" class="btn btn-danger" value="删除图片">
						<input type="submit" class="btn btn-primary" value="提交">
					</td>
				</tr>
			</table>
			
			
		</form>
	</div>
	<br>
	
</body>
</html>