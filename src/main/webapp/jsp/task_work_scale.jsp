<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
<title>检验工作量表</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<style>
.mt20{margin-top:20px;}
.select_blank,.select_blank:hover{background-color: #027cff; color: #fff;}
h3{margin-top:0;}
body{font-size:15px;}
th{font-weight:700;}
th,td{text-align:center;}
.container{padding:0;}
</style>
</head>
<body>
	<div class="task_work_scale mt20">
		<div class="container ">
			<div class="row">
				<div class="col-xs-6"><h3>检验工作量表</h3></div>
				<div class="col-xs-6 text-right">
					<a class="select_blank btn btn-default" target="_blank" href="/user/toIndex">返回功能选择页</a>
				</div>
			</div>
			<div class="row mt20">
				<div class="col-xs-12">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>质检名</th>
								<th>名下正在生产的合同金额</th>
								<th>名下正在打样的项目总数</th>
								<th>名下正在生产的项目总数</th>
								<th>上月工作时数</th>
								<th>本月至今工作时数</th>
								<th>上月上传报告数量</th>
								<th>本月上传报告数量</th>
							</tr>
						</thead>
						<tboyd>
							<tr>
								<td>AAA</td>
								<td>99999</td>
								<td>999</td>
								<td>999</td>
								<td>200</td>
								<td>120</td>
								<td>100</td>
								<td>100</td>
							</tr>
							<tr>
								<td>BBB</td>
								<td>99999</td>
								<td>999</td>
								<td>999</td>
								<td>200</td>
								<td>120</td>
								<td>100</td>
								<td>100</td>
							</tr>
						</tboyd>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>	
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>