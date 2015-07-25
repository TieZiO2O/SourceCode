<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	function chkPhone(){
		var phone=document.getElementById("phone");
		var div=document.getElementById("divphone");
		var str=phone.value;
		var flag=true;
		if(str.length==11){
			var i=0;
			for(i=0;i<13;i++){
				var ch=str.charAt(i);
				if(ch>=0&&ch<=9){
					continue;
				}
				else {
					flag=false;
				}
			}
			if(flag==false){
				div.innerHTML="请输入数字";
			}
			else{
				div.innerHTML="";
			}
		}
		else{
			div.innerHTML="手机号的长度 错误";
			flag=false;
		}
		return flag;
	} 
	function chkRealName(){
		var realname=document.getElementById("realname");
		var div=document.getElementById("divrealname");
		var str=realname.value;
		var flag=true;
		if(str.length>0){
			var i=0;
			for(i=0;i<str.length;i++){
				var ch=str.charAt(i);
				if(ch!=' '){
					continue;
				}
				else {
					flag=false;
					break;
				}
			}
			if(flag==false){
				div.innerHTML="不能含有空格";
			}
			else{
				div.innerHTML="";
			}
		}
		else{
			div.innerHTML="不能为空...";
			flag=false;
		}
		return flag;
	}
	function chkLinkMan(){
		var linkman=document.getElementById("linkman");
		var div=document.getElementById("divlinkman");
		var str=linkman.value;
		var flag=true;
		if(str.length>0){
			var i=0;
			for(i=0;i<str.length;i++){
				var ch=str.charAt(i);
				if(ch!=' '){
					continue;
				}
				else {
					flag=false;
					break;
				}
			}
			if(flag==false){
				div.innerHTML="不能含有空格";
			}
			else{
				div.innerHTML="";
			}
		}
		else{
			div.innerHTML="不能为空...";
			flag=false;
		}
		return flag;
	}
	function chkAddress(){
		var address=document.getElementById("address");
		var div=document.getElementById("divaddress");
		var str=address.value;
		var flag=true;
		if(str.length>0){
			var i=0;
			for(i=0;i<str.length;i++){
				var ch=str.charAt(i);
				if(ch!=' '){
					continue;
				}
				else {
					flag=false;
					break;
				}
			}
			if(flag==false){
				div.innerHTML="不能含有空格";
			}
			else{
				div.innerHTML="";
			}
		}
		else{
			div.innerHTML="不能为空...";
			flag=false;
		}
		return flag;
	}
	</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#ADEAEA">
<center>
<form action="${sessionScope.basePath }property/updatenow.do" method="post">
<table >
<tr align="center">
<td colspan="2" align="center">商户信息修改</td>
</tr>
<tr>
<td>用户名：</td><td><input type="text" name="username" value="${sessionScope.property.userName}" disabled="true" /></td>
</tr>
<tr>
<td>店铺名称：</td><td><input type="text" name="realname" id="realname" value="${sessionScope.property.realName}" onblur="chkRealName()"/><font color="red"><div id="divrealname"></div></font></td>
</tr>
<tr>
<td>性别：</td><td><label name="sex" >${sessionScope.shopuser.sex}</label></td>
</tr>
<tr>
<td>联系人：</td><td><input type="text" name="linkman" id="linkman" value="${sessionScope.property.linkman}" onblur="chkLinkMan()" /><font color="red"><div id="divlinkman"></div></font></td>
</tr>
<tr>
<td>联系方式：</td><td><input type="text" name="phone" id="phone" value="${sessionScope.property.phone}" onblur="chkPhone()" /><font color="red"><div id="divphone"></div></font></td>
</tr>
<tr>
<td>邮箱：</td><td><input type="text" name="email" disabled="true" value="${sessionScope.property.email}" /></td>
</tr>
<tr>
<td>邮编：</td><td><input type="text"name="postcode" value="${sessionScope.property.postCode}" disabled="true"/></td>
</tr>
<tr>
<td>所在省：</td><td><input type="text"name="province" value="${sessionScope.property.province}" disabled="true"/></td>
</tr>
<tr>
<td>所在城市：</td><td><input type="text"name="city" value="${sessionScope.property.city}" disabled="true"/></td>
</tr>
<tr>
<td>所在区域</td><td><input type="text"name="district" value="${sessionScope.property.district}" disabled="true"/></td>
</tr>
<tr>
<td>所在小区</td><td><input type="text"name="community" value="${sessionScope.property.community}" disabled="true"/></td>
</tr>
<tr>
<td>具体地址：</td><td><input type="text" name="address" id="address" value="${sessionScope.property.address}" onblur="chkAddress()"/><font color="red"><div id="divaddress"></div></font></td>
</tr>
<tr>
<td>商户照片：</td><td><img src="${pageContext.request.contextPath}${sessionScope.photopath}" width="200" height="200"><a href="${pageContext.request.contextPath}/property/gochangephoto.do">修改照片</a></td>
</tr>
<tr>
<td>关于我们：</td>
<td>
<textarea id="aboutus" name="aboutus" style="height:90%;" width="100%">${sessionScope.property.aboutUs}</textarea>
</td>
</tr>
<tr>
<td>类别：</td><td><input type="text" value="${sessionScope.property.contentName}"  name="contentname" disabled="true"/></td>
</tr>
<tr>
<td><input type="submit" value="修改"/></td><td><input type="reset" value="重置"></td>
</tr>
</table>
</form>
<div>
<font color="green">
${result}
</font>
</div>
</center>
</body>
</html>