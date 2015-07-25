<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	function chkPassword2()
	{
		var password=document.getElementById("password2");
    	var div=document.getElementById("divPassword2");
    	var str=password.value;
		var flag=true;
		if(str.length>=6 && str.length<=20)
		{
			var i=0;
			for(i=0;i<str.length;i++)
			{
				var ch=str.charAt(i);
				if(ch>='0'&&ch<='9')
				{
					continue;
				}
				else if(ch>='a'&&ch<='z'||ch>='A'&&ch<='Z')
				{
					continue;
				}
				else 
				{
					flag=false;
				}
			}
			if(flag==false)
			{
				div.innerHTML="密码为数字,字母或字母和数字的组合";
			}
			else
			{
				div.innerHTML="";
			}
		}
		else
		{
			div.innerHTML="密码长度为 6-20 位";
			flag=false;
		}
		return flag;
	}
	function chkPassword()
	{
    	var password=document.getElementById("password");
    	var div=document.getElementById("divPassword");
    	var str=password.value;
		var flag=true;
		if(str.length>=6 && str.length<=20)
		{
			var i=0;
			for(i=0;i<str.length;i++)
			{
				var ch=str.charAt(i);
				if(ch>='0'&&ch<='9')
				{
					continue;
				}
				else if(ch>='a'&&ch<='z'||ch>='A'&&ch<='Z')
				{
					continue;
				}
				else 
				{
					flag=false;
				}
			}
			if(flag==false)
			{
				div.innerHTML="密码为数字,字母或字母和数字的组合";
			}
			else
			{
				div.innerHTML="";
			}
		}
		else
		{
			div.innerHTML="密码长度为 6-20 位";
			flag=false;
		}
		return flag;
    }
	function chkPassword1()
	{
    	var password1=document.getElementById("password1");
    	var password=document.getElementById("password");
    	var div=document.getElementById("divPassword1");
    	var str1=password1.value;
    	var str=password.value;
		var flag=true;
		if(str==str1)
		{
			flag=true;
		}
		else
		{
			flag=false;
		}
		if(flag==false)
		{
			div.innerHTML="俩次密码不一致";
		}
		else
		{
			div.innerHTML="";
		}
		return flag;
    }
	 function chkOk(){
	    	if(chkPassword2()&&chkPassword()&&chkPassword1()){
	    		return true;
	    	}
	    	return false;
	    }
	</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#3299CC">
	<center>
		<form action="${sessionScope.basePath }user/changepwdnow.do" method="post" onsubmit="return chkOk();">
			<table width="100%">
				<tr>
					<td align="center"><h1>
							<strong>修改密码</strong>
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
								<td><input type="text" id="username" name="username" value="${sessionScope.username}" disabled="true"/></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">旧密码:</td>
								<td><input type="password" id="password2" name="password2" onblur="chkPassword2();" /></td>
								<td><font color='red'><div id="divPassword2"></div></font></td>
							</tr>
							<tr>
								<td align="right">新密码:</td>
								<td><input type="password" id="password" name="password" onblur="chkPassword();" /></td>
								<td><font color='red'><div id="divPassword"></div></font></td>
							</tr>
							<tr>
								<td align="right">确认密码:</td>
								<td><input type="password" name="password1" id="password1" onblur="chkPassword1();" /></td>
								<td><font color='red'><div id="divPassword1"></div></font></td>
							</tr>
							<tr>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td align="center"><input type="submit" value="修改" style="width: 80px;" />  <input
						type="reset" value="重置"  style="width: 80px;" /></td>
				</tr>
				<tr>
					<td align="center"><font color="red" >${result1}</font></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>