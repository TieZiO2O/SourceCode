<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	session.setAttribute("basePath", basePath);
%>
<script type="text/javascript">
	/* function chkPhone(){
		var phone=document.getElementById("phone");
		var div=document.getElementById("divPhone");
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
	} */
	function chkUserName(){
		var username=document.getElementById("username");
		var div=document.getElementById("divUserName");
		var str=username.value;
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
	function chkPassword(){
    	var password=document.getElementByName("password");
    	var div=document.getElementById("divPassword");
    	var str=password.value;
		var flag=true;
		if(str.length<6||str.length>20){
			var i=0;
			for(i=0;i<str.length;i++){
				var ch=str.charAt(i);
				if(ch>=0&&ch<=9){
					continue;
				}
				else if(ch>='a'&&ch<='z'||ch>='A'&&ch<='Z'){
					continue;
				}
				else {
					flag=false;
				}
			}
			if(flag==false){
				div.innerHTML="密码为数字,字母或字母和数字的组合";
			}
			else{
				div.innerHTML="";
			}
		}
		else{
			div.innerHTML="密码长度为 6-20 位";
			flag=false;
		}
		return flag;
    }
    function chkOk(){
    	if(chkUserName()&&chkPassword()){
    		return true;
    	}
    	return false;
    }
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>用户登录</title>
</head>
<body>
	<center>
		<form action="${sessionScope.basePath }user/login.do" method="post" onsubmit="return chkOk();">
			<table width="100%">
				<tr>
					<td align="center"><h1>
							<strong>用户登录</strong>
						</h1></td>
				</tr>
				<tr>
					<td align="center"><table>
							<!-- <tr>
								<td align="right">手机号:</td>
								<td><input type="text" id="phone" name="phone" onblur="chkPhone()"/></td>
								<td><font color='red'><div id="divPhone"></div></font></td>
							</tr> -->
							<tr>
								<td align="right">用户名:</td>
								<td><input type="text" id="username" name="username" onblur="chkUserName()"/></td>
								<td><font color='red'><div id="divUserName"></div></font></td>
							</tr>
							<tr>
								<td align="right">密码:</td>
								<td><input type="password" name="password" onblur="chkPassword()"/></td>
								<td><font color='red'><div id="divPassword"></div></font></td>
							</tr>
							<tr>
							</tr>
							<tr>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td align="center"><input type="submit" value="登陆" style="width: 80px;" />  <input
						type="reset" value="重置"  style="width: 80px;" /></td>
				</tr>
				<tr>
					<td align="center"><font color="red" >${error}</font></td>
				</tr>
			</table>
		</form>
		<form action="forumThreads/list.do" method="POST">
			<input type="submit" value="提交"/>
		</form>
	</center>
</body>