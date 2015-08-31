<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.validate.extension.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function(){
		$("#belong").change(function(){
			var data=$(this).val();
			alert(data);
			$("#type").val(data);
		});
		
		$("#type").val($("#belong").val());
		$("#frmSub").validate({
			rules:{
				style:{
					required:true
				},
				image:{
					required:true,
					extension:"gif|jpg|png|bmp"
				}
			},
			messages:{
				style:{
					required:"服务类型不能为空"
				},
				image:{
					required:"服务Logo不能为空",
					extension:"文件的后缀名必须为gif|png|jpg|bmp"
				}
			}
		});
	});
</script>
<title>添加新服务类型</title>
</head>
<body>
	<div style="margin-left:30px">
		<form id="frmSub" action="addservicetype.do" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>服务类型：</td>
					<td><input type="text" name="style"></td>
				</tr>
				<tr>
					<td>所属服务：</td>
					<td>
						<select id="belong">
							<option value="周边服务">周边服务</option>
							<option value="全城服务">全城服务</option>
						</select>
						<input type="hidden" name="servicetype" id="type">
					</td>
				</tr>
				<tr>
					<td>服务Logo：</td>
					<td>
						<input type="file" name="image"/>
					</td>
				</tr>
			</table>
			<br>
			<input type="submit" value="提交">
		</form>
	</div>
	<br>
	
</body>
</html>