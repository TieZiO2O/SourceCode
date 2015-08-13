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
		var tr=$("tr[name='"+$(value).attr("id")+"']");
		var userName=$(tr).find("td[id='userName']").text();
		var fenshu=$(tr).find("input[name='fraction']").val();
		var isAddText=$(tr).find("select[name='iSAdd']").val();
		var nowfenshu=parseInt($(tr).find("td[id='fraction']").text());
		var isAdd=true;
		if(isAddText=="false"){
			isAdd=false;
		}else{
			isAdd=true;
		}
		
		$.ajax({
			url:"${pageContext.request.contextPath}/integral/modify.do",
			type:"POST",
			data:{fraction:fenshu,name:userName,addOrFract:isAdd},
			success:function(data){
				var zuihou=0;
				if(isAdd){
					zuihou=nowfenshu+parseInt(fenshu);
				}else{
					zuihou=nowfenshu-parseInt(fenshu);
				}
				$(tr).find("td[id='fraction']").text(zuihou);
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
	<!-- <form action="modify.do" method="post">
		用户名：<input type="text" name="userId"/><br/>
		分　 数：<input type="text" name="fraction"/><br/>
		选　择：
		<select name="addOrFract">
			<option value="true">增加</option>
			<option value="false">减少</option>
		</select><br/>
		<input type="submit" value="提交"/>
	</form> -->
	
	<div style="text-align:center">
		<form action="query.do" method="post">
			用户名：<input type="text" name="name"/>
			<input type="submit" value="查询"/>
		</form>
	</div>
	<br/>
	
	<table class="table table-condensed">
		<thead>
			<th>序列号</th>
			<th>用户名</th>
			<th>积分</th>
			<th>是否在用</th>
			<th>操作</th>
			<th>操作分数</th>
			<th>提交</th>
		</thead>
		<tbody>
			<c:forEach items="${integrals}" var="integral">
				<tr name="${integral.userId}">
					<td>${integral.id }</td>
					<td id="userName">${integral.userName }</td>
					<td id="fraction">${integral.fraction }</td>
					<td>
						<c:if test="${integral.isUsed()==true}">
							在用
						</c:if>
						<c:if test="${integral.isUsed()==false}">
							不用
						</c:if>
					</td>
					<td>
						<select name="iSAdd">
							<option value="true">增加</option>
							<option value="false">减少</option>
						</select>
					</td>
					<td>
						<input type="text" name="fraction" >
					</td>
					<td>
						<input class="btn" type="button" id="${integral.userId}" value="提交" onclick="javascript:operateFract(this)">
						<input type="hidden" value="${integral.userId}" name="uid">
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>