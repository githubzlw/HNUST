<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String userName=request.getParameter("userName");
	String userId=request.getParameter("userId");
	String roleNo=request.getParameter("roleNo");	
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>客户投诉录入</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<style type="text/css">
.project_modify{font-size:16px;}
.bgcolorcff{background-color:#027cff;color: #fff;}
.project_modify{margin-top:20px;}
.red{color:red;}
.project_modify input[type="radio"]{width:16px;height:16px;margin-right:5px;}
.project_modify label{font-weight:normal;margin-right:20px;}
@media screen and (max-width:768px){
	.project_modify,th,td{font-size:14px;}
	.project_modify label{margin-right:0;}
}
</style>
</head>
<body>
	<div class="project_modify">
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
				<a target="_blank" href="/user/toIndex?userId=180&roleNo=100&userName=ninazhao">
					<button class="btn btn-default bgcolorcff">返回功能选择页</button>
				</a>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<h3>项目修改</h3>
				    <div><b>相关项目号: </b><span>SHS20999</span></div>
				    <div>现在项目状态：<span>已完成项目</span></div>
				    <p class="red">注意以下操作不可逆</p>	
			    </div>		    
			</div>
			<div class="row">
				<div class="col-xs-12">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>合同号</th>
								<th>供应商名</th>
								<th>合同交期</th>
								<th>产品是否完成</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>SHS09757-3</td>
								<td>宁海县跃龙街道坤超模塑厂</td>
								<td>2019/09/03</td>
								<td>
									<label><input type="radio" name="complete1">是</label>
									<label><input type="radio" name="complete1">否</label>
								</td>
							</tr>
							<tr>
								<td>SHS09757-3</td>
								<td>宁海县跃龙街道坤超模塑厂</td>
								<td>2019/09/03</td>
								<td>
									<label><input type="radio" name="complete2">是</label>
									<label><input type="radio" name="complete2">否</label>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
		</div>
	</div>
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">

/* 日历插件*/ 
  
$(function(){
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
	
	/* $('.table-condensed tbody,.table-condensed tfoot').on('click',function(){
		$('.datetimepicker').hide();
	}) */
})

</script>






