<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
function chkTitle(){
	var title=document.getElementById("title");
	var div=document.getElementById("divtitle");
	var str=title.value;
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
function chkBulletinInfo(){
	var bulletininfo=document.getElementById("bulletininfo");
	var div=document.getElementById("divbulletininfo");
	var str=bulletininfo.value;
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
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加公告</title>
</head>
<body>
<center>
<h1>
<strong>添加公告</strong>
</h1>
<form action="${sessionScope.basePath }property/addbulletinnow.do" method="post">
<table border="1">
<tr>
<td>标题：</td><td><input type="text" id="title" name="title" onblur="chkTitle()"/><font color="red"><div id="divtitle"></div></font></td>
</tr>
<tr>
<td>公告内容：</td><td><textarea rows="10" cols="20" name="bulletininfo" id="bulletininfo" onblur="chkBulletinInfo()"></textarea><font color="red"><div id="divbulletininfo"></div></font></td>
</tr>
<tr>
<td><input type="submit" value="添加"/></td><td><input type="reset" value="重置"></td>
</tr>
</table>
${sessionScope.result1}
</form>
</center>
</body>
</html>