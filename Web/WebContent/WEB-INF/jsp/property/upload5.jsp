<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">    
var flag;
function lxValidate(userForm){
	 
	if(userForm.file.value=="")
	  {
		   alert("图片上传不能为空，请选择上传的图片！")
		   userForm.file.focus();
		   return false;
	  }
	
	
	 var strSrc = $("#file").val();  
	        img = new Image();  
	        img.src = getFullPath(strSrc);  
	        //验证上传文件格式是否正确  
	        var pos = strSrc.lastIndexOf(".");  
	        var lastname = strSrc.substring(pos, strSrc.length);
	        //var ext = lastname.toLowerCase();
	        var ext = lastname.toUpperCase();
	        //&&ext!=".BMP"&&ext!=".GIF"
	        if (ext!=".PNG"&&ext!=".JPG"&&ext!=".JPEG") {  
	            alert("您上传的文件类型为" + lastname + "，图片限于png,jpeg,jpg格式");  
	            return false;  //bmp,gif,
	        }  
	        //验证上传文件宽高比例  
	 
	        if (img.height / img.width > 1.5 || img.height / img.width < 1.25) {  
	            alert("您上传的图片比例超出范围，宽高比应介于1.25-1.5");  
	            return false;  
	        }  
	        //验证上传文件是否超出了大小  780K---150K
	        //img.fileSize / 1024 > 150
	        if (img.fileSize / 1024 > 150) {  
	            alert("您上传的文件大小超出了150K限制！");  
	            return false;  
	        } 
	        return (true&&flag); 
}


function getFullPath(obj) {    //得到图片的完整路径  
	    if (obj) {  
	        //ie  
	        if (window.navigator.userAgent.indexOf("MSIE") >= 1) {  
	            obj.select();  
	            return document.selection.createRange().text;  
	        }  
	        //firefox  
	        else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {  
	            if (obj.files) {  
	                return obj.files.item(0).getAsDataURL();  
	            }  
	            return obj.value;  
	        }  
	        return obj.value;  
	    }  
} 

function filesize(size){
	//var img = size.files[0];
	var imgSize = (size.files[0].size/1024).toFixed(2);
	
    //验证上传文件是否超出了大小  780K---150K
    //img.fileSize / 1024 > 150
    if (imgSize > 150) {  
        alert("您上传的文件大小超出了150K限制！");  
        flag=false;
    } else{
    	flag=true;
    }
    return flag;  
}
</script>
</head>
<body>
	<h4>上传文件</h4>
	<form name="userForm" onsubmit="return lxValidate(this);" id="userForm" action="${pageContext.request.contextPath}/user/upload1.do" method="post" enctype="multipart/form-data">
		选择文件：<input type="file" name="file" onchange="filesize(this)">
		<input type="submit" value="上传">
	</form>
	<div>*图片限于png,jpeg,jpg格式，大小不超过150K！</div>
	<div>*图片名尽量不要使用中文，不要使用特殊字符（如*，%，$，#等）。</div>
	<div>${result}</div>
</body>
</html>