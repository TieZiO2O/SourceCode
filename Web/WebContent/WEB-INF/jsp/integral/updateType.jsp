<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/bootstrap.min.js"></script>
	<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
	
	function operateFract(value){
		$(value).attr("disabled","disabled");
		var fenshu=$("input[name='fraction']").val();
		var isAddText=$("#sltOption").val();
		var isAdd=true;
		if(isAddText=="0"){
			isAdd=false;
		}else{
			isAdd=true;
		}
		
		$.ajax({
			url:"${pageContext.request.contextPath}/integral/modifyoperate.do",
			type:"POST",
			data:{fraction:fenshu,addOrdecrease:isAdd,operatetype:"phonecall"},
			success:function(data){
				alert("操作成功");
				$(value).removeAttr("disabled");
			},
			error:function(){
				alert("操作失败");
				$(value).removeAttr("disabled");
			}
		});
	}
</script>
</head>
<body>
	
	<div style="margin-left:30px;margin-top:30px">
			<table class="table" style="width:30%">
				<tr>
					<td>更新类型：</td>
					<td>拨打电话</td>
				</tr>
				<tr>
					<td>更新分数：</td>
					<td><input type="text" name="fraction" value="${type.fraction }"></td>
				</tr>
				<tr>
					<td>更新类型：</td>
					<td>
						<select id="sltOption">
							<option value="1">增加</option>
							<option value="0">减少</option>
						</select>
					</td>
					<!-- <input type="text" name="addOrdecrease" value="1"> -->
				</tr>
			</table>
			<input type="button" class="btn" id="btnUpdate" onclick="javascript:operateFract(this)" value="更新"/>
	</div>
	
	<br/>
	
</body>
</html>