<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String projectNo=request.getParameter("projectNo");
	String userId=request.getParameter("userId");
	String roleNo=request.getParameter("roleNo");	
%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>任务系统，${qualityReport.projectNo}<fmt:formatDate value="${qualityReport.checkDate}" pattern="yyyy-MM-dd"/>修改信息
</title>
		<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
		<link rel="stylesheet" href="../css/add.css">		
	</head>
<body>
	<div class="person_new_add">
		<h3><b>修改信息</b></h3>
		<section>
		<form action="" class="form-inline clearfix">
			<div class="pull-left div_l w350">
				<h4>基本信息：</h4>
				<div class="form-group mb_35">
					<label for="">用户名</label>
					<input type="text" class="form-control">
					<span class="tip">用户名要求2-12位数字和字母组成</span>
				</div><br/>
				<div class="form-group mb_35">
					<label for="">登录密码</label>
					<input type="password" class="form-control">
					<span class="tip">密码长度必须是 6-20 位</span>
				</div><br/>
				<div class="form-group">
					<label for="">邮箱</label>
					<input type="email" class="form-control">					
				</div><br/>
				<div class="form-group">
					<label for="">邮箱密码</label>
					<input type="password" class="form-control">					
				</div>
				<div class="form-group">
					<label>状态：</label>
					<label class="state_label"><input type="radio" name="state">在职</label>
					<label class="state_label"><input type="radio" name="state">非在职</label>				
				</div>
				<h4>辅助信息：</h4>
				<div class="form-group">
					<label for="">中文名</label>
					<input type="text" class="form-control">
				</div><br/>
				<div class="form-group">
					<label for="">电话号码</label>
					<input type="number" class="form-control">					
				</div><br/>
				<div class="form-group ">
					<label for="" class="pull-left">生日</label>
					<div class="pull-left">
						<div class="input-group date form_date"
							data-date="" data-date-format="yyyy-mm-dd">
							<input id="quoteEndDate" name="quoteEndDate"
								class="form-control brt brt_7" size="16" type="text" value=""
								place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
								requiredate onchange="checkDeadline()">  <span class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<span></span>
					</div>
				</div>
			</div>
			<div class="pull-right div_r w850">
				<div class="form-group group_top">
					<label>角色赋予模板选择:</label>
					<label><input type="checkbox">销售</label>
					<label><input type="checkbox">报价</label>
					<label><input type="checkbox">采购</label>
					<label><input type="checkbox">跟单</label>					
					<label><input type="checkbox">助理</label>
					<label><input type="checkbox">技术</label>
					<label><input type="checkbox">质检</label>
					<select class="form-control">
						<option value="">领导</option>
						<option value="">普通人</option>
					</select>
				</div>
				<table class="table table-bordered group-bottom">
					<thead>
						<tr>
							<th>系统列表</th>
							<th colspan="8">权限内容</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td> MYIT#0 ERP</td>
							<td><label><input type="checkbox">销售</label></td>
							<td><label><input type="checkbox">报价</label></td>
							<td><label><input type="checkbox">采购</label></td>
							<td><label><input type="checkbox">跟单</label></td>
							<td><label><input type="checkbox">助理</label></td>
							<td><label><input type="checkbox">技术</label></td>
							<td><label><input type="checkbox">质检</label></td>
							<td><select class="form-control">
									<option value="">领导</option>
									<option value="">普通人</option>
								</select>
							</td>
						</tr>
						<tr>
							<td> MYIT#1 任务管理系统 </td>
							<td><label><input type="checkbox">销售</label></td>
							<td><label><input type="checkbox">报价</label></td>
							<td><label><input type="checkbox">采购</label></td>
							<td><label><input type="checkbox">跟单</label></td>
							<td><label><input type="checkbox">助理</label></td>
							<td><label><input type="checkbox">技术</label></td>
							<td><label><input type="checkbox">质检</label></td>
							<td><select class="form-control">
									<option value="">领导</option>
									<option value="">普通人</option>
								</select>
							</td>
						</tr>
						<tr>
							<td> MYIT#2 CRM </td>
							<td><label><input type="checkbox">销售</label></td>
							<td><label><input type="checkbox">报价</label></td>
							<td><label><input type="checkbox">采购</label></td>
							<td><label><input type="checkbox">跟单</label></td>
							<td><label><input type="checkbox">助理</label></td>
							<td><label><input type="checkbox">技术</label></td>
							<td><label><input type="checkbox">质检</label></td>
							<td><select class="form-control">
									<option value="">领导</option>
									<option value="">普通人</option>
								</select>
							</td>
						</tr>
						<tr>
							<td> MYIT#3 内部报价系统 </td>
							<td><label><input type="checkbox">销售</label></td>
							<td><label><input type="checkbox">报价</label></td>
							<td><label><input type="checkbox">采购</label></td>
							<td><label><input type="checkbox">跟单</label></td>
							<td><label><input type="checkbox">助理</label></td>
							<td><label><input type="checkbox">技术</label></td>
							<td><label><input type="checkbox">质检</label></td>
							<td><select class="form-control">
									<option value="">领导</option>
									<option value="">普通人</option>
								</select>
							</td>
						</tr>
						<tr>
							<td> MYIT#4 对外报价系统 </td>
							<td><label><input type="checkbox">销售</label></td>
							<td><label><input type="checkbox">报价</label></td>
							<td><label><input type="checkbox">采购</label></td>
							<td><label><input type="checkbox">跟单</label></td>
							<td><label><input type="checkbox">助理</label></td>
							<td><label><input type="checkbox">技术</label></td>
							<td><label><input type="checkbox">质检</label></td>
							<td><select class="form-control">
									<option value="">领导</option>
									<option value="">普通人</option>
								</select>
							</td>
						</tr>
						<tr>
							<td> NBEMAIL  MYIT#5 </td>
							<td><label><input type="checkbox">销售</label></td>
							<td><label><input type="checkbox">报价</label></td>
							<td><label><input type="checkbox">采购</label></td>
							<td><label><input type="checkbox">跟单</label></td>
							<td><label><input type="checkbox">助理</label></td>
							<td><label><input type="checkbox">技术</label></td>
							<td><label><input type="checkbox">质检</label></td>
							<td><select class="form-control">
									<option value="">领导</option>
									<option value="">普通人</option>
								</select>
							</td>
						</tr>
						<tr>
							<td> 分红系统  MYIT#6 </td>
							<td><label><input type="checkbox">销售</label></td>
							<td><label><input type="checkbox">报价</label></td>
							<td><label><input type="checkbox">采购</label></td>
							<td><label><input type="checkbox">跟单</label></td>
							<td><label><input type="checkbox">助理</label></td>
							<td><label><input type="checkbox">技术</label></td>
							<td><label><input type="checkbox">质检</label></td>
							<td><select class="form-control">
									<option value="">领导</option>
									<option value="">普通人</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div class="btns clearfix">
			<button class="btn pull-left">关闭</button>
			<button class="btn pull-right">提交</button>
		</div>
		</section>
		
	</div>
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>	
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
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






