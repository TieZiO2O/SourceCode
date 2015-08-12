<%@page import="java.util.Iterator"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.nciae.community.domain.DailyLivesType"%>
<%@page import="com.nciae.community.domain.DailyLives"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/fileinput.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/fileinput_locale_zh.js"></script>
	<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/css/fileinput.css" rel="stylesheet" media="all">
<script type="text/javascript">
	$(document).ready(function(){
		$("#wholeCityStyle").show();
		$("#arroundStyle").hide();
		$("#serviceSelect").change
		(function(){
			if($("#serviceSelect").val()=="wholeCity_Service"){
				$("#wholeCityStyle").show();
				$("#arroundStyle").hide();
			}else{
				$("#wholeCityStyle").hide();
				$("#arroundStyle").show();
			}
		});
		
		$("select[name='style1']").change(function(){
			$("input[name='style']").val($(this).val());
		});
		
		$("select[name='style2']").change(function(){
			$("input[name='style']").val($(this).val());
		});
		
		$("input[name='style']").val($("select[name='style1']").val());
	})
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加新的服务</title>
</head>
<body>
	<br>
	<form enctype="multipart/form-data" method="post" action="add.do">
		<table style="margin-left:20px;">
			<tr>
				<td>请选择上传的图片：</td>
				<td colspan="3">       
					<input id="file-0a" class="file" name="img" type="file" multiple data-min-file-count="1">
				</td>
			</tr>
			<tr>
				<td>所属服务：</td>
				<td>
				    <select id="serviceSelect" name="serviceType">
               			<option value="wholeCity_Service" selected="selected">全城服务</option>
               			<option value="arround_Service">周边服务</option>
               		</select>
				</td>
				<td>服务类型：</td>
				<td>
					<select name="style1" id="wholeCityStyle" >
               			<c:forEach items="${dailys}" var="dlv">
               				<c:if test="${dlv.getServiceType()=='全城服务'}">
               					<option value="${dlv.getId()}">${dlv.getStyle()}</option>
               				</c:if>
               			</c:forEach>
               		</select>
               		<select name="style2" id="arroundStyle">
               			<c:forEach items="${dailys}" var="dlv">
               				<c:if test="${dlv.getServiceType()=='周边服务'}">
               					<option value="${dlv.getId()}">${dlv.getStyle()}</option>
               				</c:if>
               			</c:forEach>
               		</select>
               		<input type="hidden" name="style">
				</td>
			</tr>
			<tr>
				<td>店面地址：</td>
				<td><input type="text" name="customerNotice"></td>
				<td>联系方式:</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td>题目：</td>
				<td colspan="3"><input type="text" name="title"/></td>
			</tr>
			<tr>
				<td>顾客须知：</td>
				<td colspan="3"><textarea rows="3" cols="60" name="customerNotice"></textarea></td>
			</tr>
			<tr>
				<td>内容：</td>
				<td colspan="3"><textarea rows="10" cols="60" name="content"></textarea></td>
			</tr>
		</table>
		
        <button type="submit" class="btn btn-primary">Submit</button>
        <button type="reset" class="btn btn-default">Reset</button>
    </form>
    <hr>
</body>
</html>