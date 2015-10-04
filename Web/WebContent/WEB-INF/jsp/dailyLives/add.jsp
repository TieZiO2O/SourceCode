<%@page import="java.io.PrintWriter"%>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/place_mt.js"></script>
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
			$("#serviceSelect").change(function(){
				var index=$(this).val();
				if(index=="wholeCity_Service"){
					$("input[name='style']").val($("select[name='style1']").val());
				}else if(index=="arround_Service"){
					$("input[name='style']").val($("select[name='style2']").val());
				}
			})
			$("select[name='style1']").change(function(){
				$("input[name='style']").val($(this).val());
			});
			
			$("select[name='style2']").change(function(){
				$("input[name='style']").val($(this).val());
			});
			
			$("input[name='style']").val($("select[name='style1']").val());
			$("#frmSub").validate({
				rules:{
					title:{
						required:true,
					}
				},
				messages:{
					title:{
						required:"标题不能为空",
					}
				},
				submitHandler: function(form) { 
					form.submit();
				}
			});
			
		})
		
		function showMain(value){
			var val=$(value).val();
			if(val=="true"){
				$("#isShowMain").hide(1000);
			}else{
				$("#isShowMain").show(1000);
			}
		}
		function lxSearchCommunity()
	    {
	    	//$("#community").empty();
	    	var city = $('#city').val();
			city = $.trim(city);
			
			if(city=="不限"){
				alert("请选择城市...");
				return;
			}
			
			$.ajax({ 
				url : '${pageContext.request.contextPath}/adminusers/ad_getMyCommunity.do',
				data: {"city":city},
				type : 'post'
			})
			.done(function (result,a,b) {//ajax的done解析result
		        if(b==null || b==''){
		        	alert("该城市尚无小区加入...");
		        }else{
		        	$("#community").html("");//清空community内容
		        	var result = b.responseText;
		        	//alert("b.responseText="+result.length);
		        	var val=eval("("+result+")");
		        	for(var i=0;i<val.length;i++){
		        		$("#community").append("<option value='"+val[i].id+"'>"+val[i].communityName+"</option>");
		        	}
		        }
		       }).fail(function() { alert("查询失败"); }) ;
	    }
		function selectVal(value){
			var data=$(value).val();
			$("#hidCom").val(data);
		}
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加新的服务</title>
</head>
<body onload="javascript:initialize();">
	<br>
	<form id="frmSub" enctype="multipart/form-data" method="post" action="add.do">
		<table style="margin-left:20px;">
			<tr>
				<td>请选择上传的图片：</td>
				<td colspan="3">       
					<input id="file-0a" class="file" name="img" type="file" multiple data-min-file-count="1">
				</td>
			</tr>
			<tr>
				<td>是否在首页显示：</td>
				<td>
					<span>
						<input onclick="javascript:showMain(this)" type="radio" name="isMainList" value="false" checked>主列表不显示
						&nbsp;
						<input onclick="javascript:showMain(this)" type="radio" name="isMainList" value="true">主列表显示
					</span>
				</td>
				<td>选择商户：</td>
				<td>
					<span>
						<select name="shopper" id="shopper" >
	               			<c:forEach items="${users}" var="user">
               					<option value="${user.getId()}">${user.getRealName()}</option>
	               			</c:forEach>
               			</select>
					</span>
				</td>
			</tr>
			<tr id="isShowMain">
				<td>所属服务：</td>
				<td>
				    <select id="serviceSelect" name="serviceType">
               			<option value="wholeCity_Service" selected="selected">全城服务</option>
               			<option value="arround_Service">周边服务</option>
               		</select>
				</td>
				<td>服务类型：</td>
				<td>
					<span>
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
					</span>
					
				</td>
			</tr>
			<tr>
				<td>店面地址：</td>
				<td><input type="text" name="address"></td>
				<td>联系方式:</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td>题　　目：</td>
				<td colspan="3"><input type="text" name="title"/></td>
			</tr>
			<tr>
				<td>顾客须知：</td>
				<td colspan="3"><textarea rows="3" cols="60" name="customerNotice"></textarea></td>
			</tr>
			<tr>
				<td>内　　容：</td>
				<td colspan="3"><textarea rows="10" cols="60" name="content"></textarea></td>
			</tr>
			<tr>
				<td>获取所述小区：</td>
				<td>
					<div>
						 <div id="tishi_message"><font color="red">*小区可多选</font></div>
						 <select id="province"onchange="javascript:selectchange(province,city);" style="width: 70px"></select>省
						 <select id="city" style="width: 100px"></select>市&nbsp;||&nbsp;
						 <a href="javascript:lxSearchCommunity()" id="searchCommunity">查询小区</a><br/>
						 <select id="community" onchange="javascript:selectVal(this)" name="community" style="width: 120px;height: 150px" multiple="multiple"></select>
						 <input type="hidden" name="hidCom" id="hidCom">
					</div>
				</td>
			</tr>
		</table>
		
        <button id="btnSubmit" type="submit" class="btn btn-primary">Submit</button>
        <button type="reset" class="btn btn-default">Reset</button>
    </form>
    <hr>
</body>
</html>