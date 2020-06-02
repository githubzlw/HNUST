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
		<title>样品大货完结等各种状态设置
</title>
		<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
		<link rel="stylesheet" href="${ctx}/css/add.css">
		<script type="text/javascript" src="${ctx}/js/common.js"></script>	
		<link rel="stylesheet" href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">	
	</head>
  <body class="add_project_pause">
		<header class="task-head">
	     	<div class="go-back" onclick="window.history.back(-1);"></div>
			<div class="task-tit">样品大货完结等各种状态设置
</div>
		</header>
		<main class="task-main">
		   <input type="hidden" id="roleNo" value="<%=roleNo%>">
		   <input type="hidden" id="userId" value="<%=userId%>">
		   <input type="hidden" id="userName" value="<%=userName%>">
		   <input type="hidden" id="projectNo" value="<%=projectNo%>">
			<div class="detail-one" id="qidong">
				<p class="detail-top">样品大货完结等各种状态设置</p>
					<form action="" method="post" class="form">
						<div class="add_change_state">
							<div class="t1">
								<label>项目状态修改为：</label>
								<select class="" id="editProjectStatus">
								   <%if("1".equals(request.getParameter("projectStatus"))){%>
								        <option></option>
<!-- 										<option value="6">打样完结</option> -->
<!-- 										<option value="2">大货完结</option> -->
										<option value="4">项目暂停</option>
										<option value="5">项目取消</option>
								   <%}else if("2".equals(request.getParameter("projectStatus")) || "6".equals(request.getParameter("projectStatus"))){%>
								        <option></option>
								        <option value="1">重启项目</option>
	 						            <option value="4">项目暂停</option>
										<option value="5">项目取消</option>
								   <%}else if("4".equals(request.getParameter("projectStatus"))){%>
								        <option></option>
								        <option value="1">重启项目</option>
										<option value="5">项目取消</option>
								   <%}else if("0".equals(request.getParameter("projectStatus"))){%>
								        <option></option>
								        <option value="4">项目暂停</option>
										<option value="5">项目取消</option>
									<%}else if("6".equals(request.getParameter("projectStatus"))){%>
								        <option></option>
								        <option value="1">重启项目</option>
	 						            <option value="4">项目暂停</option>
										<option value="5">项目取消</option>
							        <%}else if("5".equals(request.getParameter("projectStatus"))){%>
								        <option></option>
								        <option value="1">重启项目</option>
	 						            <option value="4">项目暂停</option>
										<option value="5">项目取消</option>
								   <%}%>
								</select>
							</div>
							<!-- <div class="form-group add_date">
							<label for="" class="col-sm-2 control-label "><span ></span>日期：</label>
							<div class="col-sm-4 ">
								<div class="input-group date form_date col-sm-6"
									data-date="" data-date-format="yyyy-mm-dd"> 
									<input id="select_date" class="form-control brt brt_7" size="16" type="text" 
										place="选择日期" field="报价截止日期" placeholder="选择日期" readonly 
										requiredate> <span class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<span></span>
							</div>
						</div> -->
							<div>
							业务提醒：<br/>    
 								项目暂停 的 原因只能是：  客户原因 或者  公司层面的要求。   不能 是 因为工厂生产问题 买料问题等等而暂停							
							</div>
							<textarea class="form-control" id="reason" placeholder="理由"></textarea>
						</div>
						
					
				<%-- <div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>项目号：</label>
					<input type="text" id="projectNo" class="input-text" value="<%=projectNo%>">
				</div>
				<div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>选择操作：</label>
					<div class="checkbox-inline">
						<!-- <span><input type="radio" name="radio-reason" value="1"/> <label >暂停项目</label></span> -->
						<span><input type="radio" name="radio-reason"  value="1"/> <label >启动项目</label></span>
						<span><input type="radio" name="radio-reason"  value="5"/> <label >取消项目</label></span>
					</div>
				</div>
				<div class="form-row margin-top">
					<label class="control-label"><span class="font-red"></span>原因：</label>
					<textarea type="text" id="pauseReason" class="textarea text-reason" placeholder="请输入原因..."></textarea>
				</div> --%>
			</form>
			</div>
			<div class="task-btn">
				<button class="btn btn-primary" id="submitButton" onclick="updateProjectStatus('<%=request.getParameter("projectNo")%>')">确定</button>
			</div>
		</main>
		<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">

		
		     var myDate = new Date();
			//获取当前年
			 var year=myDate.getFullYear();
			 //获取当前月
			 var month=myDate.getMonth()+1;
			 //获取当前日
			 var date=myDate.getDate(); 
			 //$('#select_date').val(year+"-"+month+"-"+date);
		
		
		 //更改状态时筛选
		$('#editProjectStatus').change(function(){
			if($(this).val() == 6 || $(this).val() == 2){
				$('#reason').hide();
				$('#reason').prev().hide();
			}else{
				$('#reason').show();
				$('#reason').prev().show();
			}
		})
		 
		 
		 //更新项目状态
		function updateProjectStatus(projectNo){
    
			var userName = $('#userName').val(); 
	        var projectStatus = $('#editProjectStatus').val();
			var reason = $('#reason').val();
			//var time = $('#select_date').val();
			if(projectStatus == 1 || projectStatus == 4 || projectStatus == 5){
				if(!reason){
					layer.msg("请填写原因",{time:2000});  
					return false;
				}
			}
			/* if(projectStatus == 2 || projectStatus == 4 || projectStatus == 5 || projectStatus == 6){
				if(!time){

					layer.msg("请填写时间",{time:2000});  
					return false;
				}
			} */
			

				$.ajax({
				    type:"post",                   
				    url:"${ctx}/project/updateProjectNew",           
				    data:{
				    	  projectNo:projectNo,
				    	  projectStatus:projectStatus,
				    	  reason:reason,
				    	 // time:time,
				    	  userName:userName
				    	 },              
				    success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  layer.msg(json.message,{time:2000});  
						  window.location = '${ctx}/project/showDetails?projectNo=<%=projectNo%>&roleNo=<%=roleNo%>&userId=<%=userId%>&userName=<%=userName%>&pageNumber=1';
					  }else{
						  layer.msg(json.message,{time:2000});  
					  }   
				   }
			 });  		
			
		}
 
		 
		 
		</script>
</body>
</html>
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


















