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
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>分批交货设置</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css" />
<link rel="stylesheet" href="${ctx}/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/lib/datetimepicker/css/bootstrap-datetimepicker.min.css" />
<!-- <link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/> -->
<link rel="stylesheet" href="${ctx}/css/add.css">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<style>
#qidong textarea {
	height: 1.2rem;
	width: 50%;
}
</style>
</head>
<body class="add_project_plan">
	<header class="task-head">
	<div class="go-back" onclick="window.history.back(-1);"></div>
	<div class="task-tit">分批交货设置</div>
	</header>
	<main class="task-main"> <input type="hidden" id="roleNo"
		value="<%=roleNo%>"> <input type="hidden" id="userId"
		value="<%=userId%>"> <input type="hidden" id="userName"
		value="<%=userName%>"> <input type="hidden" id="projectNo"
		value="<%=projectNo%>">
	<div class="" id="qidong">
		<p class="detail-top">分批交货设置</p>
		<!-- 旧版本启动节点交货日期开始 -->
		<!-- 	 <form action="" method="post" class="form">
							<div class="form-row margin-top">
								<label class="control-label"><span class="font-red">*</span>开工日期：</label>
								<input type="text" class="input-text form-control date-time" id="actualStartDate" placeholder="请选择实际开工交期" value="${actualStartDate}">
							</div>
							<div class="form-row margin-top">
								<label class="control-label"><span class="font-red"></span>生产计划节点：</label>
								<textarea  class="input-text project-reason date-time" placeholder="请选择生产计划节点内容" id="node" >${project.planList[0].planNode}</textarea>
							</div>
							<div class="form-row margin-top">
								<label class="control-label"><span class="font-red"></span>生产计划时间：</label>
								<input type="text" class="input-text form-control date-time" placeholder="请选择生产计划时间" id="nodeDate" value="${project.planList[0].planDate2String}">
							</div>
							<div class="form-row margin-top">
								<label class="control-label"><span class="font-red"></span>生产计划节点：</label>
								<textarea  class="input-text project-reason date-time" placeholder="请选择生产计划节点内容" id="node1" >${project.planList[1].planNode}</textarea>
							</div>
							<div class="form-row margin-top">
								<label class="control-label"><span class="font-red"></span>生产计划时间：</label>
								<input type="text" class="input-text form-control date-time" placeholder="请选择生产计划时间" id="nodeDate1" value="${project.planList[1].planDate2String}">
							</div>
							<div class="form-row margin-top">
								<label class="control-label"><span class="font-red">*</span>样品交期：</label>
								<input type="text" class="input-text form-control date-time" id="sampleScheduledDate" placeholder="请选择样品交货日期" value="${sampleScheduledDate}">
							</div>
							<div class="form-row margin-top">
								<label class="control-label"><span class="font-red">*</span>大货交期：</label>
								<input type="text" class="input-text form-control date-time" id="scheduledDate" placeholder="请选择原定交货日期"  value="${scheduledDate}">
							</div>
						</form> -->
		<!-- 旧版本启动节点交货日期结束 -->

		<!-- 新版本分批交货设置开始 -->
		<p>
			大货合同交期：<span><fmt:formatDate value="${project.deliveryDate}" pattern="yyyy-MM-dd"/></span>
		</p>
		<div class="row">
			<label for="" class="col-sm-2 control-label">第一批交期</label>
			<div class="date_input">
				<div class="input-group date form_date" data-date=""
					data-date-format="yyyy-mm-dd">
					<c:if test="${projectSchedules1 == null}">
						<input id="projectSchedules1" name="projectSchedules1"
						class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value="${project.deliveryDate}" pattern="yyyy-MM-dd"/>"
						place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
						requiredate>
					</c:if>
					<c:if test="${projectSchedules1 != null}">
					<input id="projectSchedules1" name="projectSchedules1"
						class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value="${projectSchedules1}" pattern="yyyy-MM-dd"/>"
						place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
						requiredate>
					</c:if>	
					<!-- <span
										class="input-group-addon blt posirela"><i
										class="line1 posiabso"></i></span>  -->
					<span class="input-group-addon"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
				<span></span>

			</div>
		</div>
		<div class="row">
			<label for="" class="col-sm-2 control-label">第二批交期</label>
			<div class="date_input">
				<div class="input-group date form_date" data-date=""
					data-date-format="yyyy-mm-dd">
					<input id="projectSchedules2" name="projectSchedules2"
						class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value="${projectSchedules2}" pattern="yyyy-MM-dd"/>"
						place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
						requiredate>
					<!-- <span
										class="input-group-addon blt posirela"><i
										class="line1 posiabso"></i></span>  -->
					<span class="input-group-addon"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
				<span></span>

			</div>
		</div>
		<div class="row">
			<label for="" class="col-sm-2 control-label">第三批交期</label>
			<div class="date_input">
				<div class="input-group date form_date" data-date=""
					data-date-format="yyyy-mm-dd">
					<input id="projectSchedules3" name="projectSchedules3"
						class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value="${projectSchedules3}" pattern="yyyy-MM-dd"/>"
						place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
						requiredate>
					<!-- <span
										class="input-group-addon blt posirela"><i
										class="line1 posiabso"></i></span>  -->
					<span class="input-group-addon"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
				<span></span>

			</div>
		</div>
		<p>说明：您最多可以设置分3批交货</p>
		<!-- 新版本分批交货设置结束 -->
	</div>
	<div class="task-btn">
		<button class="btn btn-primary" id="submitButton">确定</button>
	</div>
	</main>
	<!-- <script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script> -->
	<script src="../lib/jquery/jquery.min.js"></script>
	<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
	<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script
		src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<!-- <script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script> -->
	<script src="../layer/2.1/layer.js" type="text/javascript"
		charset="utf-8"></script>

	<!-- 日期插件 -->
	<script>
	/* 日历插件*/
	$('.form_date').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 4,
		forceParse : 0
	});
	$('.table-condensed tbody,.table-condensed tfoot').on('click',function(){
		$('.datetimepicker').hide();
	})
</script>
<!--  	<script type="text/javascript">  
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
		</script>-->

<script type="text/javascript">

$("#submitButton").click(function(){
	  var projectSchedules1=$("#projectSchedules1").val();
	  var projectSchedules2=$("#projectSchedules2").val();
	  var projectSchedules3=$("#projectSchedules3").val();
	  var projectNo=$("#projectNo").val();
	  var roleNo=$("#roleNo").val();
	  var userName=$("#userName").val();
	  var userId=$("#userId").val();
	  if(!projectSchedules1){
		  layer.msg("请至少填写一个交期",{time:1000});
	      return false;
	  }
	  $.ajax({
		    type:"post",                   
		    url:"${ctx}/project/updateProject",           
		    data:{projectSchedules1:projectSchedules1,
		          projectSchedules2:projectSchedules2,
		          projectSchedules3:projectSchedules3,
		          projectNo:projectNo,
		          userName:userName
		    	 },              
		    success:function(json){  
// 		      var json = eval("(" + data +")");
			  if(json.ok){
	              window.location.href="${ctx}/project/showDetails?projectNo="+projectNo +"&roleNo="+roleNo+"&userName="+userName+"&userId="+userId;
			  }else{
				  layer.msg(json.message,{time:2000});
			  }   
		    }
	 });  
	    }) 

</script>



<!-- old -->
	<!-- <script type="text/javascript">
		 $("#submitButton").click(function(){
			  var scheduledDate=$("#scheduledDate").val();
		              var actualStartDate=$("#actualStartDate").val();
			  var projectNo=$("#projectNo").val();
			  var roleNo=$("#roleNo").val();
			  var userName=$("#userName").val();
			  var node=$("#node").val();
			  var nodeDate=$("#nodeDate").val();
			  var node1=$("#node1").val();
			  var nodeDate1=$("#nodeDate1").val();
			  var userId=$("#userId").val();
			  var sampleScheduledDate=$("#sampleScheduledDate").val();
			  if(actualStartDate=='' || actualStartDate==null){
				  layer.msg("请填写项目开工日期",{time:2000});
			      return false;
			  }
			  if((scheduledDate=='' || scheduledDate==null) &&(sampleScheduledDate==''||sampleScheduledDate==null)){
				  layer.msg("请填写一个交期",{time:2000});
			      return false;
			  }
			  $.ajax({
				    type:"post",                   
				    url:"${ctx}/project/updateProject",           
				    data:{node:node,nodeDate:nodeDate,
				    	  node1:node1,nodeDate1:nodeDate1,
				    	  scheduledDate:scheduledDate,
				    	  actualStartDate:actualStartDate,
				    	  roleNo:roleNo,
				    	  projectNo:projectNo,
				    	  userName:userName,
				    	  sampleScheduledDate:sampleScheduledDate
				    	 },              
				    success:function(data){  
				      var json = eval("(" + data +")");
					  if(json.ok){
			              window.location.href="${ctx}/project/showDetails?projectNo="+projectNo +"&roleNo="+roleNo+"&userName="+userName+"&userId="+userId;
					  }else{
						  layer.msg(json.message,{time:2000});
					  }   
				    }
			 });  
			    }) 
		</script> -->
</body>
</html>