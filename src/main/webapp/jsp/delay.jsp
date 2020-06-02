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
<title>延长交期</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css" />
<link rel="stylesheet" href="${ctx}/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/lib/datetimepicker/css/bootstrap-datetimepicker.min.css" />
<!-- <link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/> -->
<link rel="stylesheet" href="${ctx}/css/add.css">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
</head>
<body class="add_delay">
	<header class="task-head">
	<div class="go-back" onclick="window.history.back(-1);"></div>
	<div class="task-tit">延长交期</div>
	</header>
	<main class="task-main"> <input type="hidden" id="roleNo"
		value="<%=roleNo%>"> <input type="hidden" id="userId"
		value="<%=userId%>"> <input type="hidden" id="userName"
		value="<%=userName%>"> <input type="hidden" id="projectNo"
		value="<%=projectNo%>">
	<div class="detail-one" id="qidong">
		<p class="detail-top">延长交期</p>
			<p>上次交期:</p>
			<c:forEach var="obj" items="${projectSchedules}" varStatus="i">
			<p>第${i.index+1}批交期: <span id="predictDate${i.index+1}"><fmt:formatDate value="${obj.predictDate}" pattern="yyyy-MM-dd"/></span> </p>
			</c:forEach>
		<form action="" method="post" class="form">
			

			<!-- <div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>延期日期：</label>
					<input type="text" class="input-text form-control date-time" id="delayDate" placeholder="请选择延期日期">
				</div> -->
			<div class="row">
				<label for="" class="col-sm-2 control-label">第一批延期日期：</label>
				<div class="date_input">
					<div class="input-group date form_date" data-date=""
						data-date-format="yyyy-mm-dd">
						<input id="projectSchedules1" class="form-control brt brt_7" size="16" type="text" value=""
							place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
							requiredate ><!-- onchange="checkDeadline()" -->
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
				<label for="" class="col-sm-2 control-label">第二批延期日期：</label>
				<div class="date_input">
					<div class="input-group date form_date" data-date=""
						data-date-format="yyyy-mm-dd">
						<input id="projectSchedules2" class="form-control brt brt_7" size="16" type="text" value=""
							place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
							requiredate ><!-- onchange="checkDeadline()" -->
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
				<label for="" class="col-sm-2 control-label">第三批延期日期：</label>
				<div class="date_input">
					<div class="input-group date form_date" data-date=""
						data-date-format="yyyy-mm-dd">
						<input id="projectSchedules3" class="form-control brt brt_7" size="16" type="text" value=""
							place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
							requiredate ><!-- onchange="checkDeadline()" -->
						<!-- <span
										class="input-group-addon blt posirela"><i
										class="line1 posiabso"></i></span>  -->
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<span></span>

				</div>
			</div>
			<p>说明：<br>第一次如果设置了多批次，以后也是多批次，不能减或增批次，完成后批次数量将不能再更改。</p>
			<div class="form-row margin-top">
				<label class="control-label"><span class="font-red">*</span>原因类型：</label>
				<div class="checkbox-inline">
					<span><input type="radio" name="radio-reason" id="aa1"
						value="1" checked /> <label for="aa1">采购原因</label></span> <span><input
						type="radio" name="radio-reason" id="aa2" value="0" /> <label
						for="aa2">客户原因</label></span>
				</div>
			</div>
			<div class="form-row margin-top">
				<label class="control-label"><span class="font-red">*</span>延期原因：</label>
				<textarea type="text" id="delayCause" class="textarea text-reason"
					placeholder="请输入项目延期原因..."></textarea>
			</div>
		</form>
	</div>
	<div class="task-btn">
		<button class="btn btn-primary" id="submitButton">确定</button>
	</div>
	</main>
	<!-- <script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script> -->
		<script src="../lib/jquery/jquery.min.js"></script>
	<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
	<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
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




	<!-- <script type="text/javascript">
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
		</script> -->
	<script type="text/javascript">
	
			function compareDate(d1,d2)
			{
			  return ((new Date(d1.replace(/-/g,"\/"))) >= (new Date(d2.replace(/-/g,"\/"))));
			}
	         //大货延期
			 $("#submitButton").click(function(){
		         var projectSchedules1=$("#projectSchedules1").val();
		         var projectSchedules2=$("#projectSchedules2").val();
		         var projectSchedules3=$("#projectSchedules3").val();
		         
		         var predictDate1 = "";
		         var predictDate2 = "";
		         var predictDate3 = "";
		         
		         //判断延期是否大于原交货期
		         if(projectSchedules1){
		        	 predictDate1 = $('#predictDate1').text();
		        	 if(predictDate1){
		        		 var flag = compareDate(projectSchedules1,predictDate1);
		        		 if(!flag){
	        			   layer.msg("第一批次大货延期时间不能小于原时间",{time:2000});  
						   return false; 
		        		 }
		        	 }else{
	        			   layer.msg("第一批次大货时间未录入",{time:2000});  
						   return false; 
		        	 }
		         }
		         
		         //判断延期是否大于原交货期
		         if(projectSchedules2){
		        	 predictDate2 = $('#predictDate2').text();
		        	 if(predictDate2){
		        		 var flag = compareDate(projectSchedules2,predictDate2);
		        		 if(!flag){
	        			   layer.msg("第二批次大货延期时间不能小于原时间",{time:2000});  
						   return false; 
		        		 }
		        	 }else{
	        			   layer.msg("第二批次大货时间未录入",{time:2000});  
						   return false; 
		        	 }
		         }
		       //判断延期是否大于原交货期
		         if(projectSchedules3){
		        	 predictDate3 = $('#predictDate2').text();
		        	 if(predictDate3){
		        		 var flag = compareDate(projectSchedules3,predictDate3);
		        		 if(!flag){
	        			   layer.msg("第三批次大货延期时间不能小于原时间",{time:2000});  
						   return false; 
		        		 }
		        	 }else{
	        			   layer.msg("第三批次大货时间未录入",{time:2000});  
						   return false; 
		        	 }
		         }
		         
		         
		         
		         
		          var delayCause=$("#delayCause").val();
				  var projectNo=$("#projectNo").val();
				  var checked=$('.checkbox-inline input:checked').attr('value');
				  var userId=$("#userId").val();
				  var userName=$("#userName").val();
				  var roleNo=$("#roleNo").val();
				  if(!(projectSchedules1 || projectSchedules2 || projectSchedules3)){
					  layer.msg("请至少输入延期的时间",{time:2000});  
					  return false;
				  }
				  if(delayCause=="" || delayCause==null){
					  layer.msg("请填写延期的原因",{time:2000});  
					  return false;
				  }
				  $.ajax({
					    type:"post",                   
					    url:"${ctx}/delay/addDelay",           
					    data:{
					    	  projectNo:projectNo,
					    	  checked:checked,
					    	  delayCause:delayCause,
					    	  userName : userName,
					    	  projectSchedules1:projectSchedules1,
					    	  projectSchedules2:projectSchedules2,
					    	  projectSchedules3:projectSchedules3
					    	 },              
					    success:function(data){  
					      var json = eval("(" + data +")");
						  if(json.ok){
				             window.location.href="${ctx}/project/showDetails?projectNo="+projectNo+"&roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
						  }else{
							  layer.msg(json.message,{time:2000});
						  }   
					    }
				 });  
		}) 
	
	
	
	

	
	
	
	
	
	
	
	
// 		 $("#submitButton").click(function(){
//                  var delayDate=$("#delayDate").val();
//                  var delayCause=$("#delayCause").val();
// 				  var projectNo=$("#projectNo").val();
// 				  var checked=$('.checkbox-inline input:checked').attr('value');
// 				  var userId=$("#userId").val();
// 				  var userName=$("#userName").val();
// 				  var roleNo=$("#roleNo").val();
// 				  if(delayDate=="" || delayDate==null){
// 					  layer.msg("请选择延期的时间",{time:2000});  
// 					  return false;
// 				  }
// 				  if(delayCause=="" || delayCause==null){
// 					  layer.msg("请填写延期的原因",{time:2000});  
// 					  return false;
// 				  }
// 				  $.ajax({
// 					    type:"post",                   
// 					    url:"${ctx}/delay/addDelay",           
// 					    data:{
// 					    	  projectNo:projectNo,
// 					    	  checked:checked,
// 					    	  delayCause:delayCause,
// 					    	  delayDate:delayDate
// 					    	 },              
// 					    success:function(data){  
// 					      var json = eval("(" + data +")");
// 						  if(json.ok){
// 				             window.location.href="${ctx}/project/showDetails?projectNo="+projectNo+"&roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
// 						  }else{
// 							  layer.msg(json.message,{time:2000});
// 						  }   
// 					    }
// 				 });  
// 	    }) 
		</script>
</body>
</html>