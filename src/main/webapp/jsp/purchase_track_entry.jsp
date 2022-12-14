<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String userName = request.getParameter("userName");
	String userId = request.getParameter("userId");
	String roleNo = request.getParameter("roleNo");
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
<title>采购单/多日行踪录入</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/add.css">
</head>
<body>
	<div
		class="customer_complaint_entry purchase_track_view purchase_track_entry">
		<h1 class="customer_complaint_h1">
			采购单/多日行踪录入
			<div class="btns">
				<a class="select_blank btn" target="_blank"
					href="/user/toIndex?userId=<%=userId%>&roleNo=<%=roleNo%>&userName=<%=userName%>">返回功能选择页
				</a> <a class="btn" href="">返回行踪目查看页 </a>
			</div>
		</h1>
		<div class="wrap1 mt10">
			<a href="http://117.144.21.74:10010">您还未登录，点击登录。</a>
			<div class="dates container mt10">
				<div class="row">
					<div class="col-xs-10">
						<div class="add_date pull-left">
							<div class="input-group date form_date" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="startDate" name="startDate"
									class="form-control brt brt_7 add_input" size="16" type="text"
									value="" place="选择日期" field="报价截止日期" placeholder="选择日期"
									readonly="" requiredate=""><span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span class="add_span"></span>
						</div>
						<span class="pull-left and">到</span>
						<div class="add_date pull-left">
							<div class="input-group date form_date" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="startDate" name="startDate"
									class="form-control brt brt_7 add_input" size="16" type="text"
									value="" place="选择日期" field="报价截止日期" placeholder="选择日期"
									readonly="" requiredate=""><span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span class="add_span"></span>
						</div>
					</div>
					<div class="col-xs-2">
						<button class="btn bgcolor_ff0 add_entry">开始录入</button>
					</div>
				</div>
			</div>
			<p class="mt10">说明：如果是节假日，请正确选择录入日期区间，最大不可超过10天。</p>

		</div>
		<div class="container">
			<div class="clearfix row">
				<div class="col-xs-4">录入人选择:</div>
				<div class="col-xs-8">
					 <select class="form-control entry_select">					
						<option>录入人1</option>
						<option>录入2</option>
						<option>录入3</option>
					</select>
				</div>				
			</div>
			<div class="row row_border">
				<div class="col-xs-12">
					<p>日期1:</p>
					<label>
						<input type="radio" name="single">
						<span>正常上班</span>
					</label>
					<label>
						<input type="radio" name="single">
						<span>非工作日/其他</span>
					</label>
				</div>
				<div class="col-xs-12">
					<p>去处:</p>
					<label>
						<input type="radio" name="single">
						<span>公司</span>
					</label>
					<label>
						<input type="radio" name="single">
						<span>仓库</span>
					</label>
					<label>
						<input type="radio" name="single">
						<span>工厂名</span>
					</label>
				</div>
				<div class="col-xs-12">
					<textarea class="form-control"></textarea>										
				</div>
			</div>
			<div class="row row_border">
				<div class="col-xs-12">
					<p>日期2:</p>
					<label>
						<input type="radio" name="single">
						<span>正常上班</span>
					</label>
					<label>
						<input type="radio" name="single">
						<span>非工作日/其他</span>
					</label>
				</div>
				<div class="col-xs-12">
					<p>去处:</p>
					<label>
						<input type="radio" name="single">
						<span>公司</span>
					</label>
					<label>
						<input type="radio" name="single">
						<span>仓库</span>
					</label>
					<label>
						<input type="radio" name="single">
						<span>工厂名</span>
					</label>
				</div>
				<div class="col-xs-12">
					<textarea class="form-control"></textarea>										
				</div>
			</div>
			<div class="row mt20">
				<button class="col-xs-12 bg_ff2 btn">提交</button>
			</div>
		</div>
		
	</div>
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/jquery-form.js"></script>
<script src="/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
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
	$('.table-condensed tbody,.table-condensed tfoot').on('click', function() {
		$('.datetimepicker').hide();
	});
	var w = window.screen.width;
	if (w < 768) {
		$('.glyphicon-calendar').click(function() {
			$('.dropdown-menu').css({
				'left' : 'unset',
				'right' : '0px'
			});
		});
	};
</script>
<script>
	//退出功能
	function exitlogin() {
		// 	$.cookie('name', '', {
		// 		path : '/'
		// 	});
		// 	$.cookie('pass', '', {
		// 		path : '/'
		// 	});
		window.location.href = "${ctx}/index.jsp";
	}

	//返回主页
	function goBack() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var purchaseNameId = "";
		window.location.href = "${ctx}/user/toIndex?userId=" + userId
				+ "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId
				+ "&userName=" + encodeURI(encodeURI(userName));
	}
</script>








