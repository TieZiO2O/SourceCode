<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
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
	
function chkName(){
		
		var name=document.getElementById("communityName").value;
		var div=document.getElementById("divName");
		
		if(name.length>0){
			if(name.length>50){
				div.innerHTML="<font color='red'>小区名太长 </font>";
				return false;
			}
			else{
				div.innerHTML="<font color='green'> ✔ </font>";
				return true;
			}
		}
		div.innerHTML="<font color='red'> 请输入小区名 </font>";
		return false;
	}
	function chkCity(){
		
		var city = $('#city').val();
		city = $.trim(city);
		if(city==null || city==''|| city == '不限'){
			alert("请选择...");
			return false;
		}
		return true;
	}
	function chkAll(){
		return chkName()&&chkCity();
	}
	</script>
	
  </head>
  
  <body onload="javascript:initialize();">
	<center>
		<h1>添加小区</h1>
		<p>
			 <!-- <a href="javascript:lxAdd()" id="add">添加</a> -->
		</p>
			 
	</center>
	<hr />
	<div>
		<form method="post" action="${pageContext.request.contextPath}/adminusers/community_addOpt.do"onsubmit="return chkAll();">
		<table width="60%">
			<tr>
				<th width="40%" align="right">小区名</th>
				<td>
					<input id="communityName" name="communityName" type="text" onblur="chkName()"  />
				</td>
				<td width="37%"><font color="red"><div id="divName"></div></font></td>
			</tr>
			<tr>
				<th width="40%" align="right">备注</th>
				<td>
					<input id="remark" name="remark" type="text"  />
				</td>
				<td width="37%"><font color="red"><div id="divRemark"></div></font></td>
			</tr>
			<tr>
				<th width="40%" align="right">所在市</th>
				<td>
					<select id="province"onchange="javascript:selectchange(province,city);" style="width: 70px"></select>
			 		<select id="city" name="city" style="width: 100px"></select>
				</td>
			</tr>
			<tr>
				<td colspan="2" width="40%">
					<input type="submit" value="添加" />
				</td>
			</tr>
		</table>
		</form>
	${result }
	</div>

</body>
</html>
