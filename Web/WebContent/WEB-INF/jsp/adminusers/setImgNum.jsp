<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
	
	function chkNum(){
		
		//var name=document.getElementById("imgNumber").value;
		var name = $('#imgNumber').val();
		name = $.trim(name);
		var div=document.getElementById("divImgNumber");
		if(name.length>0){
			if(!isNaN(name)){
				if(name<4 || name>16){
					div.innerHTML="<font color='red'>数字应在4-16之间 </font>";
					return false;
				}
				else{
					div.innerHTML="<font color='green'> ✔ </font>";
					return true;
				}
			}else{
				div.innerHTML="<font color='red'> 请输入数字 </font>";
				return false;
			}
		}
		
		div.innerHTML="<font color='red'> 请输入标题 </font>";
		return false;
	}
	
    function chkAll() {
		return chkNum();
	} 
    
</script>
</head>
<body>
	<h4>设置上传图片数量(Logo+展示图片总数)</h4>
	<form name="userForm"  onsubmit="return chkAll();" id="userForm" action="${pageContext.request.contextPath}/adminusers/shopper_dealImgNum.do" method="post">
		总数：<input type="text" name="imgNumber" id="imgNumber" onblur="chkNum()"><div id="divImgNumber"></div><br/>
		<div>请输入整数（4-16）</div>
		<input type="hidden" id="id" name="id" value="<%=request.getParameter("id") %>"  />
		<input type="submit" value="提交"> 
	</form>
</body>
</html>