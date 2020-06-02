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
<title>大货完结交货</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">	
<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/mobiscroll_date.css" />
<link rel="stylesheet" href="${ctx}/css/add.css">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
</head>
<body class="add_project_finish">
	<header class="task-head">
	<div class="go-back" onclick="window.history.back(-1);"></div>
	<div class="task-tit">大货完结交货</div>
	</header>
	<main class="task-main"> <input type="hidden" id="roleNo"
		value="<%=roleNo%>"> <input type="hidden" id="userId"
		value="<%=userId%>"> <input type="hidden" id="userName"
		value="<%=userName%>"> <input type="hidden" id="projectNo"
		value="<%=projectNo%>">
	<div class="detail-one" id="qidong">
		<p class="detail-top">大货完结交货</p>
		<form action="" method="post" class="form" onclick="return false;">
			<div class="">
				<label for="">项目号：</label> <span>${projectNo}</span>
			</div>
			<div class="">
				<label for="">原始交期：</label> <span><fmt:formatDate
						value="${project.deliveryDate}" pattern="yyyy-MM-dd" /></span>
			</div>
			<c:forEach var="obj" items="${projectSchedules}" varStatus="i">
				<div class="">
					<label for="">第${i.index+1}批交期：</label> <span><fmt:formatDate
							value="${obj.predictDate}" pattern="yyyy-MM-dd" /></span>
				</div>
			</c:forEach>

			<c:forEach var="obj" items="${delayList}" varStatus="i">
				<div>
					第<em>${obj.delayCount}</em>次交期<br>
					<c:if test="${obj.isAgree == 1}">
						<fmt:formatDate value="${obj.agreeTime}" pattern="yyyy-MM-dd" />${obj.agreePerson} 确认通过</c:if>
				</div>
				<div class="">
					<label for="">第${i.index+1}批交期：</label> <span><fmt:formatDate
							value="${obj.delayDate}" pattern="yyyy-MM-dd" /></span>
				</div>
			</c:forEach>

			<c:forEach var="obj" items="${projectSchedules}" varStatus="i">
				<div>
					<c:if test="${obj.isFinish == 1}">
						<label>第${i.index+1}已设置</label>
						<span><fmt:formatDate value="${obj.actualDate}" pattern="yyyy-MM-dd" /></span>
						<button>已设置</button>
					</c:if>
					<c:if test="${obj.isFinish == 0}">
						<label>第${i.index+1}阶段未完结</label>
						<div class="form-group add_form-group">
							<label for="" class="add_label"><span
								class="left20">*</span>完结日期</label>
							<div class="add_date">
								<div class="input-group date form_date"
									data-date="" data-date-format="yyyy-mm-dd">
									<input name="finishDate"  
										class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value="${nowDate}" pattern="yyyy-MM-dd" />"
										place="选择日期" field="完结日期" placeholder="选择日期" readonly
										requiredate> <span class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<span></span>
								<button class="fc81d0 add_btn" onclick="finish_product(this,'${obj.id}')">确认完结</button>
							</div>
							
						</div>
						
					</c:if>
				</div>
			</c:forEach>

		</form>
	</div>
	<div class="task-btn">
		<button class="btn btn-primary"
			onclick="window.location='${ctx}/project/showDetails?projectNo=${projectNo}&roleNo=${roleNo}&userId=${userId}&userName=${userName}'">返回详情页</button>
	</div>
	</main>
	<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
		charset="utf-8"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>		
	<script src="../layer/2.1/layer.js" type="text/javascript"
		charset="utf-8"></script>
	<script type="text/javascript">
		// 		 $("#submitButton").click(function(){
		// 			  var checked=$('.checkbox-inline input:checked').attr('value');
		// 			  var projectNo=$("#projectNo").val();
		// 			  var pauseReason=$("#pauseReason").val();
		// 			  var userId=$("#userId").val();
		// 			  var userName=$("#userName").val();
		// 			  var roleNo=$("#roleNo").val();

		// 			  if(checked ==undefined){
		// 				  layer.msg("请选择操作",{time:2000});  
		// 				  return false; 
		// 			  }
		// 			  if(checked!=0){//如果暂停项目请填写原因
		// 				  if(pauseReason=="" || pauseReason==null){
		// 					  layer.msg("请填写原因",{time:2000});  
		// 					  return false;
		// 				  } 
		// 			  }

		// 			  $.ajax({
		// 			    type:"post",                   
		// 			    url:"${ctx}/project/pauseProject",           
		// 			    data:{
		// 			    	  projectNo:projectNo,
		// 			    	  checked:checked,
		// 			    	  pauseReason:pauseReason,
		// 			    	 },              
		// 			    success:function(data){  
		// 			      var json = eval("(" + data +")");
		// 				  if(json.ok){
		// 		             window.location.href="${ctx}/project/showDetails?projectNo="+projectNo+"&roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
		// 				  }else{
		// 					  layer.msg(json.message,{time:2000});
		// 				  }   
		// 			    }
		// 			 });
		// 		 })

		//大货完成
		function finish_product(obj, id) {
			
			var finishDate = $(obj).parent().find('input[name="finishDate"]').val();
			if(!finishDate){
				layer.msg('结束时间不能为空',{time:2000});
				return false;
			}
			
			layer.open({
				type : 1,
				skin : 'finish-btn',
				title : '大货完成交货',
				anim : 4,
				shade : 0.6,
				shadeClose : true,
				btn : [ '确定', '取消' ],
			})

			$('.finish-btn .layui-layer-btn0').click(function() {
				$.ajax({
					type : "post",
					url : "${ctx}/project/updateProjectSchedule",
					data : {
						id : id,
						finishDate : finishDate
					},
					success : function(data) {
						var json = eval("(" + data + ")");
						if (json.ok) {
							window.location.reload();
						} else {
							layer.msg(json.message, {
								time : 2000
							});
						}
					}
				})

			})

		}
	</script>
</body>
</html>
<script>
	/* 日历插件*/
    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 4,
        defaultDate : new Date(),
        forceParse: 0
    });

</script>