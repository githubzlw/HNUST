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
<title>采购行踪查看</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/add.css">
</head>
<body>
	<div class="customer_complaint_entry purchase_track_view">
		<h1 class="customer_complaint_h1">
			采购行踪查看
			<div class="btns">
				<a class="select_blank btn" target="_blank"
					href="/user/toIndex?userId=<%=userId%>&roleNo=<%=roleNo%>&userName=<%=userName%>">返回功能选择页
				</a> <a class="btn" href="">采购每行程录入/修改 </a>
			</div>
		</h1>
		<div class="wrap1 mt10">
			<p>行踪查看：</p>
			<div class="dates container">
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
						<button class="btn bgcolor_ff0 ">查询</button>
					</div>
				</div>
				<div class="row" style="margin-top: 10px;">
					<table class="table table-bordered" contenteditable="true">
						<thead>
							<tr>
								<th>采购名</th>
								<th>日期</th>
								<th>原计划</th>
								<th>行踪</th>
								<th>修改</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>2019/04/28</td>
								<td>TB - Monthly</td>
								<td>Default</td>
								<td><button>修改</button></td>
							</tr>
							<tr class="success">
								<td>1</td>
								<td>2019/04/28</td>
								<td>TB - Monthly</td>
								<td>Approved</td>
								<td><button>修改</button></td>
							</tr>
							<tr class="error">
								<td>2</td>
								<td>2019/04/28</td>
								<td>TB - Monthly</td>
								<td>Declined</td>
								<td><button>修改</button></td>
							</tr>
							<tr class="warning">
								<td>3</td>
								<td>2019/04/28</td>
								<td>TB - Monthly</td>
								<td>Pending</td>
								<td><button>修改</button></td>
							</tr>
							<tr class="info">
								<td>4</td>
								<td>2019/04/28</td>
								<td>TB - Monthly</td>
								<td>Call in to confirm</td>
								<td><button>修改</button></td>
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








