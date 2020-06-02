<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />	
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/css/add.css">
<title>编辑投诉单流程</title>
</head>
<body>
	<div class="confirm_list customer_complaint_entry  edit_complaint">
	<div class="tc_bq">
		<div class="top clearfix">
			<p class="pull-left">已有问题标签 </p>
			<button class="btn bgcolor_ff0 pull-right clo" >关闭</button>
		</div>
		<div class="lis">
			<ul>
				<li>
					<input type="checkbox"><span>工艺：</span><span>具体问题</span>
				</li>
				<li>
					<input type="checkbox"><span>工艺：</span><span>具体问题</span>
				</li>
				<li>
					<input type="checkbox"><span>工艺：</span><span>具体问题</span>
				</li>
			</ul>
		</div>
		<button class="display_block btn bgcolor_ff0 clo clo_add"> 添加并关闭窗口</button>
</div>
		<h1 class="customer_complaint_h1">
			客户主要投诉问题：
			<div class="btns">
				<a class="select_blank btn">返回</a>
			</div>
		</h1>
		<div class="wrap">
			<div class="wrap1 mt10">1.
			<button class="btn bgcolor_ff0 sel_btn" type="button">请从已有问题标签中选择</button>
			</div>
			<div class="wrap2 ">
				<div class="wrap2_in1 mt10" >
					2.
					<select class="form-control display_inline_block">
						<option>选择对应工艺</option>
						<option>选择对应工艺</option>
					</select>
				</div>
				<div class="wrap2_in2 mt10 clearfix">
					<input type="text" class="form-control pull-left" value="请输入工艺所对应的问题标签">
					<button class="btn bgcolor_ff0 pull-right">添加</button>
				</div>
			
			</div>
			<div class="wrap3">
				<h4 class="mt10">本投诉已添加归类：</h4>
				<ul class="list">
					<li class="mt10">
						<span>工艺：</span>
						<span>具体问题</span>
						<span>共出现过 (<i>n</i>)次</span>
						<button class="btn del" type="button">删除</button>
					</li>
					<li class="mt10">
						<span>焊接：</span>
						<span>具体问题</span>
						<span>共出现过 (<i>n</i>)次</span>
						<button class="btn del" type="button">删除</button>
					</li>				
				</ul>
			</div>
			<div class="text-center mt10"><button class="btn bgcolor_ff0 tj">提交并关闭</button></div>
		</div>
	</div>
</body>
</html>
<script src="../js/jquery.min.js"></script>
<script>
//问题标签弹窗
$('.sel_btn').click(function(){
	 $('.tc_bq').show();
});
$('.clo').click(function(){
	$('.tc_bq').hide();
});
</script>



