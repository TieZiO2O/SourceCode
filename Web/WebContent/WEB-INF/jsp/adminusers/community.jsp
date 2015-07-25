<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/place_mt.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.js"></script>
	<script type="text/javascript">
	
	/* function lxSearch() {
		// $("#search").click(function(){
		//	alert("");
		//}); 
		
		var city = $('#city').val();
		city = $.trim(city);
		$.ajax({ 
			 url : '${pageContext.request.contextPath}/adminusers/community_query.do', 
			 data: "city="+city,
			 type : 'POST',
			 success : function(msg) {  
                 alert(msg);  
             } 
	 	});
	} */
	function lxSearch(){
		var city = $('#city').val();
		city = $.trim(city);
		if(city==null || city==''||city == '不限'){
			alert("请选择城市...");
			return;
		}
		window.location.href="community_query.do?city="+city;
	}
	function lxDel(id){
		
		if (confirm('确定将此该小区删除?')){
			window.location.href="community_delete.do?id="+id;
        }
	}
	function lxAdd(){
		window.location.href="community_add.do?city="+city;
	}
	/* function lxUpd(id){
		
		window.location.href="community_update.do?id="+id;
	} */
	
	/////0625
	function lxOpen(id) {
		window.location.href="community_Open.do?id="+id;
	}
	function lxClose(id) {
		window.location.href="community_Close.do?id="+id;
	}
	
	
	
	</script>
	
  </head>
  
  <body onload="javascript:initialize();">
	<center>
		<h1>小区管理</h1>
		<p>
			 <select id="province"onchange="javascript:selectchange(province,city);" style="width: 70px"></select>省
			 <select id="city" style="width: 100px"></select>市&nbsp;||&nbsp;
			 <!-- <input type="button" id="search" value="查询" /> -->
			 <a href="javascript:lxSearch()" id="search">查询</a>
			 <a href="javascript:lxAdd()" id="add">添加</a>
			 
		</p>
			 
	</center>
	<hr />
	<div>
	<c:if test="${communityList!=null}">
		<table border="1" width="880px">
			<tr>
				<th>序号</th>
				<th>小区名</th>
				<th>备注</th>
				<th>开通状态</th>
				<th colspan="3">操作</th>
			</tr>
			<c:forEach items="${communityList}" var="me" varStatus="vs">
				<tr>
					<td>${vs.count }</td>
					<td>${me.communityName }</td>
					<td>${me.remark }</td>
					<td>${me.opened==1?'已开通':'未开通' }</td>
					<td><a href="javascript:lxDel(${me.id })">删除</a></td>
					<%-- <td><a href="javascript:lxUpd(${me.id })">修改</a></td> --%>
					<td><a href="javascript:lxOpen(${me.id })">开通</a></td>
					<td><a href="javascript:lxClose(${me.id })">关闭</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${communityList==null}">
		对不起，该市没有小区加入...
	</c:if>
	${result }
	</div>

</body>
</html>
