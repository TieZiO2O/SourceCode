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
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.js"></script>
	<script type="text/javascript">
	
	function chkTitle(){
		
		var name=document.getElementById("title").value;
		var div=document.getElementById("divTitle");
		if(name.length>0){
			if(name.length>50){
				div.innerHTML="<font color='red'>标题太长 </font>";
				return false;
			}
			else{
				div.innerHTML="<font color='green'> ✔ </font>";
				return true;
			}
		}
		div.innerHTML="<font color='red'> 请输入标题 </font>";
		return false;
	}
	
	function chkContent(){
		
		var name=document.getElementById("content").value;
		var div=document.getElementById("divContent");
		if(name.length>0){
			if(name.length>200){
				div.innerHTML="<font color='red'>内容太长 </font>";
				return false;
			}
			else{
				div.innerHTML="<font color='green'> ✔ </font>";
				return true;
			}
		}
		div.innerHTML="<font color='red'> 请输入内容 </font>";
		return false;
	}
	
    function chkAll() {
		return chkTitle() && chkContent();
	} 
	</script>
<script type="application/javascript">    
$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
};  
  
 function onClik(){  
        var jsonuserinfo = $('#form1').serializeObject();  
        //alert(JSON.stringify(jsonuserinfo));  
        $.ajax({
            url:"${pageContext.request.contextPath}/adminusers/happyMoment_addOpt1.do",
            type:"post",
            dataType:"json",
            contentType: "application/json; charset=utf-8",  
            data:JSON.stringify(jsonuserinfo),
            success:function(responseText){
            	var data = eval(responseText);
            	if(data['success']){
            		alert("添加成功");
            		$("#hAdd").attr({"disabled":"disabled"});
            	}else{
            		alert("添加失败");
            	}
            },
            error:function(){
              alert("添加失败");
            }
          });
}
 
</script>
  </head>
  
  <body>
	<center>
		<h1>添加开心一刻</h1>
		<p>
			 <!-- <a href="javascript:lxAdd()" id="add">添加</a> -->
		</p>
			 
	</center>
	<hr />
	<div>
	<!--
	action="${pageContext.request.contextPath}/adminusers/shopper_addOpt.do"onsubmit="return chkAll();"
	  ${pageContext.request.contextPath}/adminusers/happyMoment_addOpt.do"onsubmit="return chkAll();
	  -->
		
		<form id="form1" method="post" action="" >
		<table width="60%">
			<tr>
				<th width="40%" align="right">标题</th>
				<td>
					<input id="title" name="title" type="text" onblur="chkTitle()"  />
				</td>
				<td width="37%"><font color="red"><div id="divTitle"></div></font></td>
			</tr>
			<tr>
				<th width="40%" align="right">内容</th>
				<td>
					<!-- <input id="content" name="content" type="text" onblur="chkContent()"  /> -->
					<textarea style="margin: 2px; height: 155px; width: 297px;" name="content" id="content" onblur="chkContent()"></textarea>
				</td>
				<td width="37%"><font color="red"><div id="divContent"></div></font></td>
			</tr>
			<tr>
				<td colspan="2" width="40%">
					 <!-- <input type="submit" value="添加" /> -->
					 <input id="hAdd" type="button" name="submit" onclick="onClik();" value="添加"/> 
				</td>
			</tr>
		</table>
		</form>
	${result }
	</div>
</body>
</html>
