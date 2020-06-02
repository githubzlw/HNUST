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
<title>任务系统,检验计划,pc</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/test_report.css">
</head>
<style>
.select_blank{background-color:#027CFF;padding: 7px 12px;text-decoration:none;
 color:#fff;border-radius: 2px;font-size: 15px;
}
.select_blank:hover,.select_blank:hover{text-decoration:none;background-color:#4362C5;color:#fff;}
</style>
<body>
<div class="plan_list">
	<div class="title clearfix">
		<div class="div_h3 pull-left">检验计划列表</div>
		<div class="logo pull-right"><img src="../img/logo.png"></div>
	</div>
	<div class="btns_search clearfix">
		<div class="pull-left">
			<input class="form-control" placeholder="请输入项目号" id="projectNo">
			<button class="form-control" onclick="searchProjectData(1)">搜索</button>
		</div>
		<div class="pull-right">
			<button class="form-control">清除所有搜索条件</button>
			<a class="select_blank" target="_blank" href="/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}">返回功能选择页</a>
			<a href="###" ><button class="form-control last_btn" onclick="exitlogin()">退出系统</button></a>
		</div>
		<input type="hidden" value="${userName}" name="userName" id="userName">
		<input type="hidden" value="${userId}" name="userId" id="userId"> 
		<input type="hidden" value="${roleNo}" name="roleNo" id="roleNo">
	</div>
	<table class="table-bordered">
		<thead>
			<tr>
				<th rowspan="2">项目号<br/>点本列编辑检验计划</th>
				<th colspan="4">初次检验计划创立流程</th>
				<th>后续检验计划更新</th>
			</tr>
			<tr>
				<th>新建检验计划</th>
				<th>王工审核状况</th>
				<th>质检是否补充过</th>
				<th>阳工审核</th>
				<th>检验计划更新次数/质检报告上传份数</th>
			</tr>
		</thead>
		<tbody id="planHtml">
			
		</tbody>
	</table>
	<div class="page-box">
		总数:&nbsp;<span id="totalCount"></span>&nbsp;每页:
		<select id="selectPage" name="selectPage" onchange="selectPageFunc()">
			<option value="5">5</option>
			<option value="10">10</option>
			<option value="20">20</option>
			<option value="50">50</option>
		</select>
		条 当前页/总页:<span style="color: red" id="pageNumberSpan"></span>/<span id="countPageSpan" style="color: red"></span>&nbsp; <a href="#" class="emanagergetpagea first-padding" onclick="searchProjectData(1)" title="第一页">首页</a> <a href="#" class="emanagergetpagea first-padding" onclick="searchProjectData(2)" title="上一页(第1页)">上页</a>
		<a href="#" class="emanagergetpagea" onclick="searchProjectData(3)" title="下一页(第3页)">下页</a> <a href="#" class="emanagergetpagea last_page" onclick="searchProjectData(4)" title="最后一页">尾页</a>
		<!-- 跳转到第<input type="text" class="n" value="n">页 -->
	</div>
	<input type="hidden" id="pageNumber" name="pageNumber" value=""> 
	<input type="hidden" id="countPage" name="countPage" value="">
	<input type="hidden" id="pageSize" name="pageSize" value="50">
</div>
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script>
$(function() {		
	searchProjectData(1);
})
//退出功能
function exitlogin() {
	$.cookie('name', '', {
		path : '/'
	});
	$.cookie('pass', '', {
		path : '/'
	});
	window.location.href = "${ctx}/index.jsp";
}

//返回主页
function goBack() {
    var roleNo = $("#roleNo").val();
    var userId = $("#userId").val();
    var userName = $("#userName").val();
    var purchaseNameId = "";
    window.location.href = "${ctx}/user/toIndex?userId=" + userId + "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId + "&userName=" + encodeURI(encodeURI(userName));
}
//清空所有条件
function cleanSelect(){	
	$("#projectNo").val("")
   	var totalCount = Number($("#totalCount").val());
	if (totalCount == 0) {
		$("#totalCount").val(1)
	}
	searchProjectData(1)	
}
//查询
function searchProjectData(obj){
	var pageNumber = $("#pageNumber").val();
	var countPage = $("#countPage").val();
	var roleNo = $("#roleNo").val();
	var userId = $("#userId").val();
	var userName = $("#userName").val();
	var pageSize=$("#pageSize").val();
    var projectNo=$("#projectNo").val();
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
	var inputKey = $("#inputKey").val();//关键字查询
	$('#planHtml').html('')
	$('#pageNumber').val("")
	$('#pageNumberSpan').text("")
    $('#countPage').val("")
    $('#countPageSpan').text("")
	$.ajax({
			type : 'post',
			url : '${ctx}/plan/selectInspectionPlan',
			data : {
				pageNumber : pageNumber,
				roleNo : roleNo,
				userId : userId,
				userName : userName,
				pageSize:pageSize,
				projectNo:projectNo
			},
			success : function(json) {
// 				var json = eval("(" + data + ")");
				var planList = json.data.planList;//项目数据
				var pageNumber = json.data.pageNumber
				var totalCount = json.data.count
				var pageSize=json.data.pageSize
				var userId=json.data.userId;
				var roleNo=json.data.roleNo;
				var userName=json.data.userName;
				$('#pageNumber').val(pageNumber)
				$('#pageNumberSpan').text(pageNumber)
				if(totalCount){
					var countPage = Math.ceil(totalCount/pageSize)
					$('#countPage').val(countPage);
					$('#countPageSpan').text(countPage);
					$("#totalCount").text(totalCount);
				}
				var planHtml="";
				for (var i = 0; i < planList.length; i++) {
					var plan=planList[i];
					var addHtml='';
					var qHtml='';
					var wHtml=''
					var yHtml=''
					if(plan.addPlan=='0'){
						addHtml='<a href="/projectTask/selectProjectTaskById?id='+plan.id+'&userId='+userId+'&userName='+userName+'&roleNo='+roleNo+'">'+'未建立</a>'
					}else{
						addHtml='已建立'
					}
					if(plan.wAudit=='0'){
						wHtml='未审核'
					}else{
						wHtml='已审核'
					}
					if(plan.isQuality=='0'){
						qHtml='未补充'
					}else{
						qHtml='已补充'
					}
					if(plan.yAudit=='0'){
						yHtml='未审核'
					}else{
						yHtml='已审核'
					}
					planHtml+='<tr><td><a target="_blank" href="http://117.144.21.74:33168/po/POupload.aspx?id='+plan.projectNo+'">'+plan.projectNo+'</a></td>'
					+'<td>'+addHtml+'</td>'
					+'<td>'+wHtml+'</td>'
					+'<td>'+qHtml+'</td>'
					+'<td>'+yHtml+'</td>'
					+'<td>'+plan.updateNum+'/'+(plan.reportCount == null ? 0 : plan.reportCount)+'</td>'
				}
				$("#planHtml").append(planHtml);
			}
      })	
}
</script>




