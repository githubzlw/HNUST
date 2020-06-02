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
		<title>任务系统，${qualityReport.projectNo}<fmt:formatDate value="${qualityReport.checkDate}" pattern="yyyy-MM-dd"/>人员设置</title>
		<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="../css/add.css">		
	</head>
<body>
	<div class="person_set">
		<h3><b>人员设置</b><button class="btn">新增人员</button></h3>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>人员</th>					
					<th>注册时间</th>					
					<th>状态</th>					
					<th>操作</th>					
				</tr>				
			</thead>
			<tbody>
				<tr>
					<td><span>A</span></td>
					<td><span>20180606</span><em>11:04</em></td>
					<td>在职</td>
					<td><button class="btn">修改信息</button></td>
				</tr>
				<tr>
					<td><span>B</span></td>
					<td><span>20180606</span><em>11:04</em></td>
					<td>非在职</td>
					<td><button class="btn">修改信息</button></td>
				</tr>
				<tr>
					<td><span>A</span></td>
					<td><span>20180606</span><em>11:04</em></td>
					<td>在职</td>
					<td><button class="btn">修改信息</button></td>
				</tr>
				<tr>
					<td><span>B</span></td>
					<td><span>20180606</span><em>11:04</em></td>
					<td>非在职</td>
					<td><button class="btn">修改信息</button></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>	
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>







