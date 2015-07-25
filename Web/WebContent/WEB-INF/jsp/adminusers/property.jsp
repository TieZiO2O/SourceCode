<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/place_mt.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
	
	function lxSearch(){
		var city = $('#city').val();
		city = $.trim(city);
		var community = $('#community').val();
		community = $.trim(community);
		
		/* if(city==null || city==''||city == '不限'){
			alert("请选择...");
			return;
		}
		if(community==null || community==''){
			alert("请先查询小区...");
			return;
		} */
		window.location.href="property_query.do?city="+city+"&community="+community;
	}
	function lxDel(id){
		
		if (confirm('确定将此该物业删除?')){
			window.location.href="property_delete.do?id="+id;
        }
	}
	function lxUpload(id){
		window.location.href="upload_page.do?id="+id;
	}
	function lxAdd(){
		window.location.href="property_add.do";
	}
	
    function lxSearchCommunity()
    {
   	 var city = $('#city').val();
   		city = $.trim(city);
   		
   		if(city=="不限"){
   			alert("请选择城市...");
   			return;
   		}
   		
   		$.ajax({ 
   			url : '${pageContext.request.contextPath}/adminusers/property_add_getCommunity.do',
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
   	        		$("#community").append("<option value='"+val[i]+"'>"+val[i]+"</option>");
   	        	}
   	        }
   	       }).fail(function() { alert("查询失败"); }) ;
    }
    
    
	///////0628
	function lxSetPwd(id){
		window.location.href="shopper_property_setPwd.do?id="+id;
	}
</script>
</head>
<body onload="javascript:initialize();">
	<center>
		<h1>物业管理</h1>
		<p>
			 <select id="province"onchange="javascript:selectchange(province,city);" style="width: 70px"></select>省
			 <select id="city" style="width: 100px"></select>市&nbsp;||&nbsp;
			 <a href="javascript:lxSearchCommunity()" id="searchCommunity">查询小区</a>
			 <select id="community" name="community" style="width: 100px"></select>&nbsp;||&nbsp;
			 <a href="javascript:lxSearch()" id="search">查询</a>
			 <a href="javascript:lxAdd()" id="add">添加</a>
			 
		</p>
			 
	</center>
	<hr />
	<div>
	<c:if test="${properties!=null}">
		<!-- <table border="1" width="880px"> -->
		<table border="1" width="100%">
			<tr>
				<th>序号</th>
				<th>登录名</th>
				<th>商户名</th>
				<th>性别</th>
				<th>联系人</th>
				<th>手机号</th>
				<th>邮编</th>
				<th>小区</th>
				<th>地址</th>
				<th colspan="2">操作</th>
			</tr>
			<c:forEach items="${properties}" var="me" varStatus="vs">
				<tr>
					<td>${vs.count }</td>
					<td>${me.userName }</td>
					<td>${me.realName }</td>
					<td>${me.sex }</td>
					<td>${me.linkman }</td>
					<td>${me.phone }</td>
					<td>${me.postCode }</td>
					<td>${me.community }</td>
					<td>${me.address }</td>
					<td>
						<a href="javascript:lxDel(${me.id })">删除</a>
					</td>
					<%-- <td>
						<a href="javascript:lxUpload(${me.id })">上传Logo</a>
					</td> --%>
					<td>
						<a href="javascript:lxSetPwd(${me.id })">恢复初始密码</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${properties==null}">
		对不起，没有物业加入...
	</c:if>
	${result }
	</div>

</body>
</html>