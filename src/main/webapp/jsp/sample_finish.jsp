<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String projectNo = request.getParameter("projectNo");
	String roleNo = request.getParameter("roleNo");
	String userName = request.getParameter("userName");
	String userId = request.getParameter("userId");
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>样品完结交货</title>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/lib/datetimepicker/css/bootstrap-datetimepicker.min.css" />	
<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/mobiscroll_date.css" />
<link rel="stylesheet" href="${ctx}/css/add.css">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
</head>
<body class="add_project_finish sample_finish">
	<header class="task-head">
	<div class="go-back" onclick="window.history.back(-1);"></div>
	<div class="task-tit">样品完结交货</div>
	</header>
	<main class="task-main"> <input type="hidden" id="roleNo"
		value="<%=roleNo%>"> <input type="hidden" id="userId"
		value="<%=userId%>"> <input type="hidden" id="userName"
		value="<%=userName%>"> <input type="hidden" id="projectNo"
		value="<%=projectNo%>">
	<div class="detail-one" id="qidong">
		<p class="detail-top">样品完结交货</p>
		<form action="" method="post" class="form" onclick="return false;">
			<div class="">
				<label for="">项目名称：</label> <span>${project.projectName}</span>
			</div>
			<div class="">
				<label for="">项目号：</label> <span>${projectNo}</span>
			</div>
			<div class="">
				<label for="">工厂：</label> <span>${project.companyName}</span>
			</div>
			<div class="form-group">
					<label for="" class=="add_label">合同样品交期</label>
					<span><fmt:formatDate value="${project.sampleScheduledDate}" pattern="yyyy-MM-dd"/></span>
			</div>
			<div class="form-group ">
					<label for="" class="add_finish_label">完成时间</label>
					<div class="add_finish_date">
						<div class="input-group date form_date" data-date=""
							data-date-format="yyyy-mm-dd">
							<input id="sampleFinishTime" name="sampleFinishTime"
								class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value="${nowDate}" pattern="yyyy-MM-dd" />"
								place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
								requiredate onchange="checkDeadline()"> <span class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<span></span>
					</div>
				</div>			 
		</form>
	</div>
	<div class="task-btn">
		<button class="btn btn-primary"
			onclick="finish_sample('${projectNo}','${roleNo}','${userId}','${userName}')">确认完结</button>
	</div>
	</main>
	<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="../layer/2.1/layer.js" type="text/javascript"
		charset="utf-8"></script>
<script src="../lib/jquery/jquery.min.js"></script>
	<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
	<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
		
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
		
				
	<script type="text/javascript">

	  
	  //样品完成交货
	  function finish_sample(projectNo,roleNo,userId,userName){
		  
		   var finishTime = $('#sampleFinishTime').val();
		   if(!finishTime){
			   layer.msg("请填写样式结束日期",{time:2000});  
			   return false;
		   }
		  
		  
			layer.open({
				type:1,
				skin:'finish-btn',
				title:'样品完成交货',
				anim:4,
				shade:0.6,
				shadeClose:true,
				btn:['确定','取消'],
			})	
			$('.finish-btn .layui-layer-btn0').click(function(){
				var projectNo=$("#projectNo").val();
				var type="2";
				$.ajax({
				    type:"post",                   
				    url:"${ctx}/project/updateProjectStatus",           
				    data:{
				    	  projectNo:projectNo,type:type,finishTime:finishTime
				    	 },              
				    success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  window.location='${ctx}/project/showDetails?projectNo='+projectNo+'&roleNo='+roleNo+'&userId='+userId+'&userName='+userName;
					  }else{
						 layer.msg(json.message,{time:2000});
					  }   
				   }
			 });  
			 $('#mask').hide();
			 $('.index-mask-two').hide();
	 		})
	  } 
	  
	</script>
</body>
</html>