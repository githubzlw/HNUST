<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String roleNo = request.getParameter("roleNo");
	String userId = request.getParameter("userId");
	String userName = request.getParameter("userName");
	String purchaseNameId = request.getParameter("purchaseNameId");
%>
<html style="height:auto;">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>任务列表-手机</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/add.css">
</head>
<body>
<form id="form4" action="/project/selectIssueList" method="post">	
<%--     <input type="hidden" value="${issue}" name="issue"> --%>
	<div class="confirm_list quality_problem history_problem">
		<h1 class="customer_complaint_h1">历史问题标签
			<div class="btns">
				<a class="select_blank btn" href="/user/toIndex">返回功能选择页</a>
				<a class="btn mt5" href="###x">返回质量问题检索</a>
			</div>
		</h1>
		<div class="wrap mt40">															
					<div class="wrap3">						
						<ul class="list">
							<li class="mt10"><span>工艺：</span> <span>具体问题</span> <span>共被投诉
									(<i>n</i>)次
							</span>
								<button class="btn del" type="button">删除</button></li>
							<li class="mt10"><span>焊接：</span> <span>具体问题</span> <span>共被投诉
									(<i>n</i>)次
							</span>
								<button class="btn del" type="button">删除</button></li>
						</ul>
					</div>
					<!-- <div class="text-center mt10">
						<button class="btn bgcolor_ff0 tj">提交并关闭</button>
					</div> -->
				</div>			
	</div>	
	</form>		
</body>
<script src="../lib/jquery/jquery.min.js" type="text/javascript"></script>
</html>












