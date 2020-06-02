<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String projectNo=request.getParameter("projectNo");
	String roleNo=request.getParameter("roleNo");
	String userName=request.getParameter("userName");
	String userId=request.getParameter("userId");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<title>项目留言</title>
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
		<style>
			#qidong input{
				width:50%;
			}
				#qidong textarea{
				height:1.2rem;
				width:50%;
			}
		
		</style>
	</head>
<body>
		<header class="task-head">
	     	<div class="go-back" onclick="window.history.back(-1);"></div>
			<div class="task-tit">项目留言</div>
		</header>
		<main class="task-main">
		   <input type="hidden" id="roleNo" value="<%=roleNo%>">
		   <input type="hidden" id="userId" value="<%=userId%>">
		   <input type="hidden" id="userName" value="<%=userName%>">
		   <input type="hidden" id="projectNo" value="<%=projectNo%>">
			<div class="detail-one" id="qidong">
				<p class="detail-top">项目留言</p>
			<form action="" method="post" class="form">
				<div class="modal-body">
					<div class="form-row margin-top" >
					<label class="control-label"><span class="font-red">*</span>留言人：</label>
					<input type="text" class="input-text form-control-comment" readonly="readonly" id="reviewer" value="<%=userName%>">
				</div>
				<div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>项目号：</label>
					<input type="text" class="input-text form-control-comment" readonly="readonly" value="<%=projectNo%>">
				</div>
				<div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>留言内容：</label>
					<textarea  class="input-text project-reason date-time" placeholder="请输入留言内容 " id="comment"></textarea>
				</div>
			</form>
			</div>
			<div class="task-btn">
				<button class="btn btn-primary" id="submitButton">确定</button>
			</div>
		</main>
		<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
		<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function () {
				var currYear = (new Date()).getFullYear();	
				var opt={};
				opt.date = {preset : 'date'};
				opt.datetime = {preset : 'datetime'};
				opt.time = {preset : 'time'};
				opt.default = {
					theme: 'android-ics light', //皮肤样式
					display: 'modal', //显示方式 
					mode: 'scroller', //日期选择模式
					dateFormat: 'yyyy-mm-dd',
					lang: 'zh',
					showNow: true,
					nowText: "今天",
					startYear: currYear - 1, //开始年份
					endYear: currYear + 50 //结束年份
				};
				$(".date-time").mobiscroll($.extend(opt['date'], opt['default']));
			});
		</script>
		<script type="text/javascript">
		 $("#submitButton").click(function(){
			    var projectNo=$("#projectNo").val();
				var userName=$("#userName").val();
				var comment=$("#comment").val();
				var roleNo=$("#roleNo").val();
				var project=$("#projectNo").val();
				var userId=$("#userId").val();
				if(comment == null ||comment==""){
					layer.msg("留言内容不能为空",{time:2000});
					return false;
				}
				$.ajax({
				    type:"post",                   
				    url:"${ctx}/project/projectComment",           
				    data:{
				    	  projectNo:projectNo,
				    	  userName:userName,
				    	  comment:comment
				    },              
				    success:function(data){  
			    	  var json = eval("(" + data +")");
			    	  if(json.ok){
			             window.location.href="${ctx}/project/showDetails?projectNo="+projectNo+"&roleNo="+roleNo+"&userName="+userName+"&userId="+userId;
					  }else{
						 layer.msg(json.message,{time:2000});
					  }   
				   }
			 	}); 
	    }) 
		</script>
</body>
</html>