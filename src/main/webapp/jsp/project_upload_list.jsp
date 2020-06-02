<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>工厂上传项目列表</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/add.css">
<link rel="stylesheet" href="../css/progressBar.css" />
<link rel="stylesheet" href="../css/ui.css" />
<link rel="stylesheet" href="../css/ui.progress-bar.css">	
</head>
<body>
<div class="facotry_project_list" style="max-width: 1024px;">
	<div class="div_transparent"></div>
	<h2 class="customer_complaint_h1">
	<p style="width: 63%;">工厂项目上传列表</p>
	<div class="btns">
		<a class="select_blank btn" target="_blank" href="/user/toIndex">返回功能选择页</a>
		<a  class="btn" href="/project/showListNew">返回项目列表页</a>				
	</div>	
	</h2>	
	<div class="share">
<!-- <a class="btn btn-default">可点击右上角分享到微信群（如使用手机微信查看）</a> -->
<!-- <a class="btn btn-default">可点击本按钮分享到微信群（需使用QQ浏览器）</a> -->
	</div>
	<form id="form4" action="/statistics/selectFactoryUpload" method="post">
	<div class="form-group check clearfix">
		<input type="text" class="form-control pull-left" name="selectStr" value="${selectStr}" placeholder="项目号">
		<button type="button" class="btn bgcolor_ff0" onclick="searchProjectData(1)">查询</button>
	</div>
	<ul>
		<c:forEach var="obj" items="${reportList}" varStatus="i">
		<li <c:if test="${obj.factoryId != null}">onclick="window.location='https://www.kuaizhizao.cn/report/reportListPurchase?csgOrderId=${obj.csgOrderId}&supplierId=${obj.factoryId}&factoryId=0&realName=${userName}'"</c:if>>
			<span>${i.index+1}、</span>
			<span style="text-decoration: underline;color: blue;cursor: pointer;">${obj.csgOrderId}</span>
			<span>${obj.projectName}</span>
			<span>${obj.factoryName}</span>
			<span>合同日期：<em><fmt:formatDate value="${obj.contractDate}" pattern="yyyy-MM-dd"/></em></span>
			<span>上次更新动态的日期：<em>${obj.uploadDate == '' || obj.uploadDate == null ?'暂无': fn:substringBefore(obj.uploadDate, ' ')}</em></span>
		<li>
		</c:forEach>
	</ul>
	<input type="hidden" id="pageStr" name="pageStr" value="${page == null ? 1 : page}"> 
	<input type="hidden" id="countPage" name="countPage" value="${lastNum}">
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize == null ? 20 : pageSize}">
	</form>
	<div class="page-box mt10">
		总数:<span id="totalCount">${count}</span> 
		当前页/总页:<span style="color: red" id="pageNumberSpan">${page}</span>/
		<span id="countPageSpan" style="color: red">${lastNum}</span>&nbsp; 		
		<a class="emanagergetpagea first-padding" onclick="searchProjectData(1)" title="第一页">首页</a> 
		<a class="emanagergetpagea first-padding" <c:if test="${page > 1}">onclick="searchProjectData(2)"</c:if> title="上一页(第1页)">上页</a>
		<a class="emanagergetpagea" <c:if test="${page < lastNum}">onclick="searchProjectData(3)"</c:if> title="下一页(第3页)">下页</a> 
		<a class="emanagergetpagea last_page" onclick="searchProjectData(4)" title="最后一页">尾页</a>
		<!-- 跳转到第<input type="text" class="n" value="n">页 -->
	</div>
	
</div>	
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script src="../js/base64.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>


<script type="text/javascript">

//查询
function searchProjectData(obj){
	var pageNumber = $("#pageStr").val();
	var countPage = $("#countPage").val();
	var pageSize=$("#pageSize").val();
	var type = obj;
	
	// 1 第一页  2.上一页  3.下一页 4.尾页
	if (type == 1) {
		pageNumber = 1;
	}
	if (type == 2) {//上一页
		if (pageNumber == 1) {
			pageNumber = 1
		} else {
			pageNumber = Number(pageNumber) - 1;
		}
	}
	if (type == 3) {//下一页
		if (pageNumber == countPage) {
			pageNumber = countPage;
		} else {
			pageNumber = Number(pageNumber) + 1;
		}
	}
	if (type == 4) {//尾页
		pageNumber = countPage;
	}
	var inputKey = $("#input_key").val();//关键字查询
	$('#pageStr').val(pageNumber)
	$('#pageNumberSpan').text("")
    $('#countPage').val("")
    $('#countPageSpan').text("")

	$('#form4').submit();   
}

</script>
					







