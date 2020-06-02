<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<title>登录</title>
	<script type="text/javascript"> 
    </script>
	<link rel="stylesheet" type="text/css" href="${ctx}/quality/index.css"/>
    <script type="text/javascript" src="${ctx}/quality/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/quality/cookie.js"></script>	
    <script type="text/javascript" src="${ctx}/quality/common.js"></script>	   
	</head>
   <body class="bg">
   <input type="hidden" value="<%=request.getParameter("purchase_history") == null ? "" : request.getParameter("purchase_history")%>" id="purchase_history">
		<div class="login-main login1">
			<img src="${ctx}/quality/logo.png" class="logo">
			<h2 class="login-tit">任务系统</h2>
			<form action="" method="post" class="form">
				<div class="form-row">
					<label class="control-label"><span class="font-red">*</span>账号：</label>
					<input type="text" class="form-control username input-text" placeholder="请输入账号">
					<p class="validate-username">用户名输入有误，用户名不能为空</p>
				</div>
				<div class="form-row">
					<label class="control-label"><span class="font-red">*</span>密码：</label>
					<input type="password" class="form-control input-text password" placeholder="请输入密码">
					<p class="validate-password">用户名输入有误，用户名不能为空</p>
				</div>
				<div class="form-row form-row3">
					<input type="checkbox"  class="login-check" id="check"/>
					<label for="check" class="control-label label-psw">记住密码并保留登录状态</label>					
				</div>
				<div class="form-row">					
					<input type="button" value="登录" class="login-btn" >
					<!-- <input type="button" value="任务列表" class="project-task-btn"> -->
				</div>
			</form>
		</div>
		<script type="text/javascript">
			// $(function(){
				var user_name=$('.username');
				var pass_word=$('.password');
				var label_checked=$('.login-check');
				if($.cookie('name')){
					user_name.val($.cookie('name'));
					pass_word.val($.cookie('pass'));
					label_checked.prop('checked','true');
				};
				label_checked.click(function(){
					if(!$(this).prop('checked')){
						$.cookie('name', '',{ path: '/' });
						$.cookie('pass', '',{ path: '/' });
					}
				});
				user_name.blur(function(){
					if(user_name.val()==""){
						$('.validate-username').show().text('账号不能为空');
					}else{
						$('.validate-username').hide();
					}
				});
				pass_word.blur(function(){
					if(pass_word.val()==""){
						$('.validate-password').show().text('密码不能为空');
						
					}else{
						$('.validate-password').hide();
					}
				});
				 $('.login-btn').mousedown(function(){
					login();
				}) 
			// })
			
			
			function login(){
				var username=$('.username').val();
				var password=$('.password').val();
				var checked=$('.login-check');
				var purchaseHistory = $('#purchase_history').val();
				if(username==""){
					$('.validate-username').show().text('账号不能为空');
				}else if(password==""){
					$('.validate-password').show().text('密码不能为空');
				}else{
				    $.ajax({
					    type:"post",                   
					    url:"${ctx}/user/showUser",           
					    data:{userName:username,password:password						    	
					         },
					    success:function(data){  
// 					      var json = eval('(' + data +')'); 
                          var json = data;
						  if(json.ok){
							  window.location.href="${ctx}/user/toIndex"; 
							  
							  
							 /*  $('.login-btn').css({border:0,background:'#ccc'});
// 							  $.cookie('name',username,{ expires: 700, path: '/' });
							  if(checked.prop('checked')){
								  $.cookie('pass',password,{ expires: 700, path: '/' });
							  }
							  var userId=json.data.id;
							  var roleNo=json.data.roleNo;
							  var userName=json.data.userName;
							  var purchaseNameId="";	 */							  								  
							  
						/*   $.ajax({
								     type:"POST",
								     url:"/account/addPurchaseName",
								     data:{PURCHASE_USER_NAME:username},
								     xhrFields:{withCredentials:true},
								     dataType:"json",
								     async:false,      //同步
								     success:function(result){
								   
								     }
					          }) */
							  
							 /*  if(purchaseHistory){
								  window.location = purchaseHistory;
							  }else{
								  window.location.href="${ctx}/user/toIndex"; 
							  }			 */					 
							  //window.location.href="${ctx}/projectTask/projectTaskList?userId="+userId+"&roleNo="+roleNo+"&purchaseNameId="+encodeURI(encodeURI(purchaseNameId))+"&userName="+encodeURI(encodeURI(userName));   
						  }else{
							  $('.validate-password').show().text(json.message);
						  }   
					    }
				    });  
			     }
			}
			
			//enter事件
			 document.onkeydown = function (e) {
	            if (!e) e = window.event;
	            if ((e.keyCode || e.which) == 13) {
	            	$('.username').blur();
	            	$('.password').blur();
	            	login();
	            }
	        } 
		</script>
	</body>
</html>
