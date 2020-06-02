<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>人员列表</title>
		<link rel="stylesheet" href="${ctx}/user/lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/user/css/add.css">		
		<style type="text/css">
		
		   .z-a{
		       font-size: 14px;
			    border: 1px solid #ccc;
			    color: #333;
			    background: #FFFFFF;
			    text-align: center;
			    margin-left: 30px;		   
		   }
		</style>
	</head>
<body>
<form action="${ctx}/syncUser/showAllUser" method="post" id="form">
	<div class="person_set">
		<h3><b>人员设置</b><a class="btn z-a" href="${ctx}/user/person_new_add.jsp">新增人员</a>
			<input class="form-control" style="width: 195px;display: inline-block;" type="text" id="userName" name="userName" placeholder="姓名">
		    <a class="btn z-a" style="background-color: #5abffb;color: #fff;" onclick="formSubmit()">搜索</a>
		</h3>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>人员</th>	
					<th>工号</th>				
					<th>注册时间</th>					
					<th>职位</th>					
					<th>状态</th>					
					<th>操作</th>					
				</tr>				
			</thead>
			<tbody>
			<c:forEach var="obj" items="${userList}" varStatus="i">
				<tr>
					<td><span>${obj.userName}</span></td>
					<td>${obj.jobNumber }</td>	
					<td><span><fmt:formatDate value="${obj.registerDate}" pattern="yyyy-MM-dd"/></span></td>
					<td><span>${obj.job}</span></td>
					<td><c:if test="${obj.flag == 0}">离职</c:if><c:if test="${obj.flag == 1}">在职</c:if></td>
					<td><button class="btn" onclick="window.location='${ctx}/syncUser/edit?id=${obj.id}';return false;">修改信息</button></td>
				</tr>
             </c:forEach>
			</tbody>
		</table>
	</div>
</form>	
</body>
</html>
<script src="${ctx}/user/lib/jquery/jquery.min.js"></script>
<script src="${ctx}/user/lib/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
 function formSubmit(){
	 $('#form').submit();	 
 } 

</script>





