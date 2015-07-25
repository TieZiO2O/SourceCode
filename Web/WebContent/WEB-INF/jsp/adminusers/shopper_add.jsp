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
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/category_mt.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.js"></script>
	<script type="text/javascript">
	
	function chkUserName(){
		
		var name=document.getElementById("userName").value;
		var div=document.getElementById("divUserName");
		if(name.length>0){
			if(name.length>50){
				div.innerHTML="<font color='red'>用户名太长 </font>";
				return false;
			}
			else{
				div.innerHTML="<font color='green'> ✔ </font>";
				return true;
			}
		}
		div.innerHTML="<font color='red'> 请输入用户名 </font>";
		return false;
	}
	
	function chkName(){
		
		var name=document.getElementById("realName").value;
		var div=document.getElementById("divName");
		if(name.length>0){
			if(name.length>50){
				div.innerHTML="<font color='red'>商户名太长 </font>";
				return false;
			}
			else{
				div.innerHTML="<font color='green'> ✔ </font>";
				return true;
			}
		}
		div.innerHTML="<font color='red'> 请输入商户名 </font>";
		return false;
	}
	
	function chkLinkman(){
		
		var name=document.getElementById("linkman").value;
		var div=document.getElementById("divLinkman");
		if(name.length>0){
			if(name.length>50){
				div.innerHTML="<font color='red'>联系人名太长 </font>";
				return false;
			}
			else{
				div.innerHTML="<font color='green'> ✔ </font>";
				return true;
			}
		}
		div.innerHTML="<font color='red'> 请输入联系人名 </font>";
		return false;
	}
	function chkPhone(){
		
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
			div.innerHTML="手机号的长度错误";
			flag=false;
		}
		return flag;
	}
	//验证邮箱格式
	function isEmail(str) {
	    if (str.charAt(0) == "." || str.charAt(0) == "@" || str.indexOf('@', 0) == -1 ||
	        str.indexOf('.', 0) == -1 || str.lastIndexOf("@") == str.length - 1 ||
	        str.lastIndexOf(".") == str.length - 1 ||
	        str.indexOf('@.') > -1)
	        return false;
	    else
	        return true;
	}
	function chkEmail(){
		
		var name=document.getElementById("email").value;
		var div=document.getElementById("divEmail");
		if(name.length>0){
			if(!isEmail(name)){
				div.innerHTML="<font color='red'>非法电子邮箱 </font>";
				return false;
			}
			else{
				div.innerHTML="<font color='green'> ✔ </font>";
				return true;
			}
		}
		div.innerHTML="<font color='red'> 请输入电子邮箱  </font>";
		return false;
	}
		//验证邮编
		String.prototype.CheckPostCode = function() {
			//var MyNumber = this.search(/^[1-9]{1}[0-9]{5}$/);
			//var MyNumber = this.search(/^[1-9][0-9]{5}$/);
			var MyNumber = this.search(/^[0-9]{6}$/);
			if (MyNumber == -1)
				return false;
			return true;
		};
		function chkPostCode() {

			var name = document.getElementById("postCode").value;
			var div = document.getElementById("divPostCode");
			
			if (name.length > 0) {
				if (!name.CheckPostCode()) {
					div.innerHTML = "<font color='red'>非法邮编 </font>";
					return false;
				} else {
					div.innerHTML = "<font color='green'> ✔ </font>";
					return true;
				}
			}
			div.innerHTML = "<font color='red'> 请输入邮编  </font>";
			return false;
		}

		function chkCity() {

			var city = $('#city').val();
			city = $.trim(city);
			if (city == null || city == '' || city == '不限') {
				alert("请选择...");
				return false;
			}
			return true;
		}
		/* function chkAll() {
			return chkName() && chkCity();
		} */
		/* function onSub(){
			return chkUserName()&&chkName()&&chkLinkman()&&chkPhone()&&chkEmail()&&chkPostCode()&&chkCity();
		} */
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
	 
/////////////////////0628
		 if(!boo){
			 return;
		 }
/////////////////////0703
		 if(!uname){
			 return;
		 }
	 
	 
        var jsonuserinfo = $('#form1').serializeObject();  
        //alert(JSON.stringify(jsonuserinfo));  
        $.ajax({
            url:"${pageContext.request.contextPath}/adminusers/shopper_addOpt2.do",
            type:"post",
            dataType:"json",
            contentType: "application/json; charset=utf-8",  
            data:JSON.stringify(jsonuserinfo),
            success:function(responseText){
            	var data = eval(responseText);
            	if(data['success']){
            		alert("添加成功");
            		$("#sAdd").attr({"disabled":"disabled"});
            	}else{
            		alert("添加失败");
            	}
            },
            error:function(){
              alert("添加失败");
            }
          });
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
			url : '${pageContext.request.contextPath}/adminusers/shopper_add_getCommunity.do',
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
/* function lxSearchCommunity()
 {
	$("#community").empty();
 	var city = $('#city').val();
		city = $.trim(city);
		//var city = '不限';
		if(city=="不限"){
			alert("请选择城市...");
			return;
		}
		$.ajax({ //shopper_getCommunity    //shopper_add_getCommunity
			 url : '${pageContext.request.contextPath}/adminusers/shopper_add_getCommunity.do',
			 data: {"city":city},
			 type : 'post',
			 success : function(msg) {  
              var comList = eval(msg);
              if(msg==null || msg==''){
             	 alert("该城市尚无小区加入...");
              }else{
             	 for(var i = 0;i < comList.length; i++){
             		 $("#community").append("<option value='"+comList[i]+"'>"+comList[i]+"</option>");
             	 }
             	 
              }
          },
          error:function(){
              alert("查询失败");
          } 
	 	});
 } */
 
 var boo;
 function lxChkPhone()
 {
	 	var phone = $('#phone').val();
 		phone = $.trim(phone);
 	
		if(phone==null || phone==""){
			$("#divPhone").html("手机号不能为空");
			boo=false;
			return boo;
		}
		
		$.ajax({ 
			url : '${pageContext.request.contextPath}/adminusers/shopper_chkPhone.do',
			data: {"phone":phone},
			type : 'post'
		})
		.done(function (result,a,b) {//ajax的done解析result
			
			var val = b.responseText;
        	//var result=eval("("+val+")");
        	var div=document.getElementById("divPhone");
        	
	        if(val==null || val==''){
	        	//alert("手机号码不能为空");
	        	div.innerHTML="手机号码不能为空";
	        	boo=false;
				return boo;
	        }else if(val=='exist'){
	        	//alert("手机号码已存在");
	        	div.innerHTML="手机号码已存在";
	        	boo=false;
				return boo;
	        }else if(val=='none'){
	        	boo=true;
				return boo;
	        }
	       }).fail(function() { alert("查询失败");boo=false;return boo; });
 }
 
 //////////////0703
 var uname;
 function lxChkLoginName()
 {
	 	var userName = $('#userName').val();
	 	userName = $.trim(userName);
 	
		if(userName==null || userName==""){
			$("#divUserName").html("用户名不能为空");
			uname=false;
			return uname;
		}
		
		$.ajax({ 
			url : '${pageContext.request.contextPath}/adminusers/shopper_chkLoginName.do',
			data: {"userName":userName},
			type : 'post'
		})
		.done(function (result,a,b) {//ajax的done解析result
			
			var val = b.responseText;
        	//var result=eval("("+val+")");
        	var div=document.getElementById("divUserName");
        	
	        if(val==null || val==''){
	        	//alert("用户名不能为空");
	        	div.innerHTML="用户名不能为空";
	        	uname=false;
				return uname;
	        }else if(val=='exist'){
	        	//alert("用户名已存在");
	        	div.innerHTML="用户名已存在";
	        	uname=false;
				return uname;
	        }else if(val=='none'){
	        	uname=true;
				return uname;
	        }
	       }).fail(function() { alert("查询失败");uname=false;return uname; });
 }
 
 /*
 function lxChkPhone()
 {
	 	var phone = $('#phone').val();
 		phone = $.trim(phone);
 	
		if(phone==null || phone==""){
			$("#divPhone").html("手机号不能为空");
			return false;
		}
		
		$.ajax({ 
			url : '${pageContext.request.contextPath}/adminusers/shopper_chkPhone.do',
			data: {"phone":phone},
			type : 'post'
		})
		.done(function (result,a,b) {//ajax的done解析result
	        if(b==null || b==''){
	        	alert("手机号码不能为空");
	        	return false;
	        }else if(b=='exist'){
	        	alert("手机号码已存在");
	        	return false;
	        }else if(b=='none'){
	        	return true;
	        }
	       }).fail(function() { alert("查询失败");return false; }) ;
 }
 */
 
</script>
	
  </head>
  
  <body onload="javascript:initialize();javascript:initialized();">
	<center>
		<h1>添加商户</h1>
		<p>
			 <!-- <a href="javascript:lxAdd()" id="add">添加</a> -->
		</p>
			 
	</center>
	<hr />
	<div>
	<!--
	action="${pageContext.request.contextPath}/adminusers/shopper_addOpt.do"onsubmit="return chkAll();"
	  -->
		
		<form id="form1" method="post" action="">
		<table width="60%">
			<tr>
				<th width="40%" align="right">用户名</th>
				<td>
					<input id=userName name="userName" type="text" onblur="chkUserName();lxChkLoginName()"  />
				</td>
				<td width="37%"><font color="red"><div id="divUserName"></div></font></td>
			</tr>
			<!-- <tr>
				<th width="40%" align="right">密码</th>
				<td>
					<input id=userPwd name="userPwd" type="text" onblur="chkPwd()"  />
				</td>
				<td width="37%"><font color="red"><div id="divPwd"></div></font></td>
			</tr> -->
			<tr>
				<th width="40%" align="right">商户</th>
				<td>
					<input id="realName" name="realName" type="text" onblur="chkName()"  />
				</td>
				<td width="37%"><font color="red"><div id="divName"></div></font></td>
			</tr>
			<tr>
				<th width="40%" align="right">性别</th>
				<td>
					<!-- <input id="sex" name="sex" type="text"  /> -->
					<input id="sexM" name="sex" type="radio" value="男" checked="checked"  />男<input id="sexW" name="sex" type="radio" value="女"  />女
				</td>
				<td width="37%"><font color="red"><div id="divSex"></div></font></td>
			</tr>
			<tr>
				<th width="40%" align="right">联系人</th>
				<td>
					<input id="linkman" name="linkman" type="text" onblur="chkLinkman()" />
				</td>
				<td width="37%"><font color="red"><div id="divLinkman"></div></font></td>
			</tr>
			<tr>
				<th width="40%" align="right">手机号</th>
				<td>
					<input id="phone" name="phone" type="text" onblur="chkPhone();lxChkPhone()" />
				</td>
				<td width="37%"><font color="red"><div id="divPhone"></div></font></td>
			</tr>
			<tr>
				<th width="40%" align="right">邮箱</th>
				<td>
					<input id="email" name="email" type="text" onblur="chkEmail()"  />
				</td>
				<td width="37%"><font color="red"><div id="divEmail"></div></font></td>
			</tr>
			<tr>
				<th width="40%" align="right">邮编</th>
				<td>
					<input id="postCode" name="postCode" type="text"  onblur="chkPostCode()" />
				</td>
				<td width="37%"><font color="red"><div id="divPostCode"></div></font></td>
			</tr>
			<tr>
				<th width="60%" align="right">所在省市</th>
				<td>
					<select id="province" name="province" onchange="javascript:selectchange(province,city);" style="width: 70px"></select>
			 		<select id="city" name="city" style="width: 100px"></select>
				</td>
			</tr>
			<!-- <tr>
				<th width="40%" align="right">所在区</th>
				<td>
					<input id="district" name="district" type="text"  />
				</td>
				<td width="37%"><font color="red"><div id="divDistrict"></div></font></td>
			</tr> -->
			<!-- <tr>
				<th width="40%" align="right">小区</th>
				<td>
					<input id="community" name="community" type="text"  />
				</td>
				<td width="37%"><font color="red"><div id="divCommunity"></div></font></td>
			</tr> -->
			<tr>
				<th width="70%" align="right">小区</th>
				<td>
					<select id="community" name="community"  style="width: 50%"></select>
					<a href="javascript:lxSearchCommunity()" id="searchCommunity"  style="width: 10%">选择小区</a>
				</td>
				<td width="10%"><font color="red"><div id="divCommunity"></div></font></td>
			</tr>
			<tr>
				<th width="40%" align="right">地址</th>
				<td>
					<input id="address" name="address" type="text"  />
				</td>
				<td width="37%"><font color="red"><div id="divAddress"></div></font></td>
			</tr>
			<tr>
				<th width="40%" align="right">商家类型</th>
				<td>
					<!-- <input id="contentName" name="contentName" type="text"  /> -->
					<select id="shopType" name="shopType" onchange="javascript:selectedchange(shopType,contentName);" style="width: 80px"></select>
			 		<select id="contentName" name="contentName" style="width: 120px"></select>
				</td>
				<td width="37%"><font color="red"><div id="divContentName"></div></font></td>
			</tr>
			<!-- <tr>
				<th width="40%" align="right">选择图标</th>
				<td>
					<input type="file" name="shopLogo">
				</td>
				<td width="37%"><font color="red"><div id="divshopLogo"></div></font></td>
			</tr> -->
			<!-- <tr>
				<th width="40%" align="right">用户角色</th>
				<td>
					<select id="role" name="role" style="width: 120px">
						<option value="shop" selected="selected">商户</option>
						<option value="property">物业</option>
					</select>
				</td>
				<td width="37%"><font color="red"><div id="divRole"></div></font></td>
			</tr> -->
			<tr>
				<td colspan="2" width="40%">
					<!-- <input type="submit" value="添加" /> -->
					<input id="sAdd" type="button" name="submit" onclick="onClik();" value="添加"/>
				</td>
			</tr>
		</table>
		</form>
	${result }
	</div>
</body>
</html>
