<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
  String roleNo=request.getParameter("roleNo");
  String projectNo=request.getParameter("projectNo");
  String userId=request.getParameter("userId");
  String userName=request.getParameter("userName");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<title>任务分配</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
	</head>
	<body>
		<header class="task-head">
	     	<div class="go-back" onclick="window.history.back(-1);"></div>
			<div class="task-tit">任务分配</div>
		</header>
		<main class="task-main">
		   <input type="hidden" id="roleNo" value="<%=roleNo%>">
		   <input type="hidden" id="userId" value="<%=userId%>">
			<div class="detail-one">
				<p class="detail-top">任务分配信息</p>
				<p class="p-row">
					<span class="span-left">部&nbsp; 署  人：</span>
					<span class="span-right">
						<input class="form-control username input-text" id="userName" readonly="readonly" value="<%=userName%>"></input>
					</span>
				</p>
				<p class="p-row">
					<span class="span-left">项&nbsp; 目 号：</span>
					<span class="span-right">
						<input class="form-control username input-text" id="projectNo" readonly="readonly" value="<%=projectNo%>"></input>
					</span>
				</p>
				<p class="p-row">
					<span class="span-left">节点选择：</span>
					<span class="span-right">
						<select class="task-select" id="selectNode">
							<option value="0" selected>无</option>
							<option value="1">样品交货</option>
							<option value="2">大货交货</option>
						</select>
					</span>
				</p>
				<p class="p-row">
					<span class="span-left">截止日期：</span>
					<span class="span-right">
						<input type="text" class="input-text form-control date-time" placeholder="请选择截止时间"/>
					</span>
				</p>
				<p class="p-row">
					<span class="span-left">任务需求：</span>
					<span class="span-right">
						<textarea class="textarea task-textarea" id="taskDemand" placeholder="请输入任务需求"></textarea>
					</span>
				</p>
			</div>
			<div class="task-btn">
				<button class="btn btn-primary">确定</button>
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
				
				$('.btn').click(function(){
					var projectNo=$("#projectNo").val();
					var node=$("#selectNode").find('option:selected').attr('value');
					var endDate=$(".date-time").val();
					var taskDemand=$("#taskDemand").val();
					var roleNo=$("#roleNo").val();
					var userId=$("#userId").val();
					var userName=$("#userName").val();
					if(endDate==''){
						layer.msg('请输入截止日期',{time:2000});
						return false;
					}
					if(taskDemand==''){
						layer.msg('请输入任务需求',{time:2000});
						return false;
					}
					 $.ajax({
						    type:"post",                   
						    url:"${ctx}/task/addTask",           
						    data:{
						        	projectNo:projectNo,
						        	node:node,
						        	endDate:endDate,
						        	taskDemand:taskDemand,
						        	roleNo:roleNo,
						        	userName:userName
						    	 },              
						    success:function(json){  
// 							      var json = eval("(" + data +")");
								  if(json.ok){
									  window.location.href="${ctx}/project/showDetails?projectNo="+projectNo+"&userId="+userId+"&roleNo="+roleNo+"&userName="+encodeURI(encodeURI(userName));	
								  }else{
									  layer.msg(json.message,{time:2000});
								  }   
						    }
					 });  
				})
			
			});
		</script>
	</body>
</html>
