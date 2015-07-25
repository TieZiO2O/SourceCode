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
	<!-- <meta name="renderer" content="webkit|ie-comp|ie-stand"> -->
	<!-- <meta name="renderer" content="webkit"> -->
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/place_mt.js"></script>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/category_mt.js"></script> --%>
	<script type="text/javascript">
	
	function chkTitle(){
		
		var name=document.getElementById("adTitle").value;
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
		
		var name=document.getElementById("adContent").value;
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
	
	function chkAdUrl(){
		var name=document.getElementById("adUrl").value;
		var div=document.getElementById("divAdUrl");
		if(name.length>0){
			if(name.length>500){
				div.innerHTML="<font color='red'>网址太长 </font>";
				return false;
			}
			else{
				div.innerHTML="<font color='green'> ✔ </font>";
				return true;
			}
		}
		div.innerHTML="<font color='red'> 请输入网址 </font>";
		return false;
	}
    function chkAll() {
		return chkTitle() && chkContent() && chkAdShopId();
	} 
    
    ///////////////////////
	function lxSearchCommunity()
    {
    	//$("#community").empty();
    	var city = $('#city').val();
		city = $.trim(city);
		
		if(city=="不限"){
			alert("请选择城市...");
			return;
		}
		
		$.ajax({ 
			url : '${pageContext.request.contextPath}/adminusers/ad_getMyCommunity.do',
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
	        		$("#community").append("<option value='"+val[i].id+"'>"+val[i].communityName+"</option>");
	        	}
	            /* $.each(result, function (key,value) {
	            	$("#community").append("<option value='"+value.id+"'>"+value.communityName+"</option>");
	            }); */
	        }
	       }).fail(function() { alert("查询失败"); }) ;
    }
    
function chkPhone(){
		
		var phone=document.getElementById("phone");
		var div=document.getElementById("spanPhone");
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
    
    function lxSearchShopper(){
    	
    	var phone = $('#phone').val();
    	phone = $.trim(phone);
    	
		if(phone==null || phone==""){
			$("#spanPhone").html("手机号不能为空");
			return;
		}
		
		 $.ajax({ 
			 url : '${pageContext.request.contextPath}/adminusers/ad_getShopperInfo.do',
			 data: {"phone":phone},
			 type : 'post'
		})
        .done(function (result,a,b) {//ajax的done解析result
        if(b==null || b==''){
           	 $("#basicshow").html("<font color='red'>该商户尚未注册</font>");
        }else{
        	$("#basicshow").html("");//清空info内容
        	document.getElementById("shopperId").value="";
			document.getElementById("shopType").value="";
			
			var val = b.responseText;
        	//alert("b.responseText="+result.length);
        	var result=eval("("+val+")");
        	//alert(result);
        	//alert(result[0].length);
        	//alert(result[0].city);
        	var shopType="";
        	if(result[0].shopType=='0'){
				shopType="周边商家";
			}else{
				shopType="全城商家";
			}
        	if(result[0].role=="shop"){
        		$("#basicshow").append(
                        "<div>省:" + result[0].province + "</div>" + 
                        "<div>市:" + result[0].city + "</div>" + 
                        "<div>商户名称:" + result[0].realName    + "</div>" +
                        "<div>小区:" + result[0].community    + "</div>" +
                        "<div>地址:" + result[0].address    + "</div>" +
                        "<div>商家类别:" + shopType    + "</div>" +
                        "<div>服务类型:" + result[0].contentName + "</div>"
                 );
        	}else{
				$("#basicshow").append(
                        "<div>省:" + result[0].province + "</div>" + 
                        "<div>市:" + result[0].city + "</div>" + 
                        "<div>物业名称:" + result[0].realName    + "</div>" +
                        "<div>小区:" + result[0].community    + "</div>" +
                        "<div>地址:" + result[0].address    + "</div>" +
                        "<div>服务角色:" + "小区物业"    + "</div>"
                 );
        	}
        	
        	document.getElementById("shopperId").value=result[0].id;
        	document.getElementById("shopType").value=result[0].shopType;
        	document.getElementById("role").value=result[0].role;
        }
       }).fail(function() { alert("查询失败"); }) ;
    }
	</script>

  </head>
  
  <body onload="javascript:initialize();">
	<center>
		<h1>添加广告</h1>
	</center>
	<hr />
	<div>
	<!--
	action="${pageContext.request.contextPath}/adminusers/shopper_addOpt.do"onsubmit="return chkAll();"
	  ${pageContext.request.contextPath}/adminusers/happyMoment_addOpt.do"onsubmit="return chkAll();
	  -->
		
		<form id="form1" method="post" action="" >
		<div>
			 <div id="tishi_message"><font color="red">*小区可多选；要查询小区并输入手机号查询商户之后，才能添加广告</font></div>
			 <select id="province"onchange="javascript:selectchange(province,city);" style="width: 70px"></select>省
			 <select id="city" style="width: 100px"></select>市&nbsp;||&nbsp;
			 <a href="javascript:lxSearchCommunity()" id="searchCommunity">查询小区</a><br/>
			 <select id="community" name="community" style="width: 120px;height: 150px" multiple="multiple"></select>
			 <br/>
			 输入商户手机号：<input id="phone" name="phone" type="text" onblur="chkPhone()" /><font color="red"><span id="spanPhone"></span></font>
			 <a href="javascript:lxSearchShopper()" id="searchShopper">查询商户</a>
			 <div id="basicshow">显示一下基本信息</div>
		</div>
		<hr />
		<table width="60%">
			<tr>
				<th width="40%" align="right">标题</th>
				<td>
					<input id="adTitle" name="adTitle" type="text" onblur="chkTitle()"  />
				</td>
				<td width="37%"><font color="red"><div id="divTitle"></div></font></td>
			</tr>
			<tr>
				<th width="40%" align="right">内容</th>
				<td>
					<!-- <input id="adContent" name="adContent" type="text" onblur="chkContent()"  /> -->
					<textarea style="margin: 2px; height: 155px; width: 297px;" name="adContent" id="adContent" onblur="chkContent()"></textarea>
				</td>
				<td width="37%"><font color="red"><div id="divContent"></div></font></td>
			</tr>
			<!-- <tr>
				<th width="40%" align="right">跳转网址</th>
				<td>
					<input id="adUrl" name="adUrl" type="text" onblur="chkAdUrl()"  />
				</td>
				<td width="37%"><font color="red"><div id="divAdUrl"></div></font></td>
			</tr> -->
			<tr>
				<td>
					<input id="shopperId" name="shopperId" type="hidden"  />
					<input id="shopType" name="shopType" type="hidden"  />
					<input id="communityList" name="communityList" type="hidden"  />
					<input id="role" name="role" type="hidden"  />
				</td>
			</tr>
			<tr>
				<td colspan="2" width="40%">
					 <!-- <input type="submit" value="添加" /> -->
					 <input id="adAdd" type="button" name="submit" onclick="onClik();" value="添加"/> 
				</td>
			</tr>
		</table>
		</form>
	${result }
	</div>
</body>
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
	 	//alert($("#shopperId").val()+$("#shopType").val());
	 	document.getElementById("communityList").value="";
	 	var selCommunity=$("#community").val();
	 	var communityList="";
	 	//alert(selCommunity);
	 	if(($("#shopType").val()=='0'&& $("#role").val()=='shop') || $("#role").val()=='property'){
	 		if(selCommunity==null || selCommunity==''){
		 		alert("请选择发布广告的小区...");
		 		return;
		 	}else{
		 		for (var i = 0; i < selCommunity.length; i++) {
		 			communityList+=selCommunity[i];
		 			if(i<selCommunity.length-1){
		 				communityList+=",";
		 			}
				}
		 		document.getElementById("communityList").value=communityList;
		 	}
	 	} 
	 	
        var jsonuserinfo = $('#form1').serializeObject();  
        //alert(JSON.stringify(jsonuserinfo));  //ad_addOpt
        $.ajax({
            //url:"${pageContext.request.contextPath}/adminusers/ad_addOpt1.do",
            url:"${pageContext.request.contextPath}/adminusers/ad_addOpt.do",
            type:"post",
            dataType:"json",
            contentType: "application/json; charset=utf-8",  
            data:JSON.stringify(jsonuserinfo),
            success:function(responseText){
            	var data = eval(responseText);
            	if(data['success']){
            		alert("添加成功");
            		$("#adAdd").attr({"disabled":"disabled"});
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
</html>
