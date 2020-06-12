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
<title>任务系统,本周质检地图</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/add.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css" />
<link rel="stylesheet" href="../css/test_report.css">
<link rel="stylesheet" href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="../layer/2.1/skin/layer.css" >
<style>

.table>thead>tr>th{vertical-align:middle;}
table{font-size:16px;}
.table-bordered>thead>tr>th{border-bottom-width:0;}
.select_blank{position:relative;top:5px;}
.table1_tc,.productionCompletion{position:fixed;top:20px;left:50%;transform:translate(-50%,0);
box-shadow: 0 3px 9px rgba(0,0,0,.45);background-color: #fff;z-index: 100;display:none;
padding:20px;height:750px;overflow-y: auto;overflow-x:hidden;}
.th18,.th19,.th20,.th21{width:55px;}
.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th
{padding:3px;}
th{text-align:center;}
.th1{width:130px;}
.th2{width:110px;}
.th3{width:65px;}
.th4{width:65px;}
.th5{width:30px;}
.th6{width:100px;}
.th7{width:70px;}
.th8{width:100px;}
.th_name{width:100px;}
.th18{width:60px;text-align:center;}
.th19{width:75px;text-align:center;}
.th20{width:75px;text-align:center;}
.th21{width:90px;}
.th22{width:75px;}
.table1_tc .th1{width:120px;}
.table1_tc .th2{width:140px;}
.table1_tc .th3{width:200px;}
.table1_tc .close{position: absolute;top: 10px;right: 30px; padding:6px 12px;font-size:16px;font-weight:normal;
   border-radius: 4px;text-align: center;background-color: #027CFF;z-index: 1000000;opacity: 1;color:#fff;
}

table .s1,table .s2,table .s3,table .s4,table .s5,table .s6,table .s7,table .s8,
table .s18,table .s19,table .s20,table .s21
{display:inline-block;text-overflow: ellipsis;overflow:hidden;white-space:nowrap;}
table .form-control{padding:3px;width:83px;}
.add_date{width:125px;}
.container-fluid{padding-left:0;padding-right:0;}
.table1_tc{width:1240px;margin:0 auto;}
.table1_tc table{width:1200px;margin:0 auto;}
table{font-size:14px;}

.mr30{font-size:22px;font-weight: 700}
.h3_title{font-size:25px;font-weight: 700}

.table1_tc th:first-child{width:90px;}
/* 2020.4.29.新增 */
.person_in{width:125px;height:34px;}
th{position: relative;}
.toggle{position: absolute;top:-25px;right:30px;padding:6px 12px;background-color:#027cff;color:#fff;
border-radius: 4px;width:70px;}
span.add_span{display:block;width:115px;}
span.aad_date_span{display:none;}
span.aad_date_span.display_table{display:table;}
.productionCompletion{max-height: 750px;overflow-y: scroll;width:1000px;}
.productionCompletion .date_d{width:136px;}
.productionCompletion .d1{display: none;width:50px;position:relative;top:6px;}
.table-bordered>thead>tr>th{border-bottom: 0 none;;}
.productionCompletion  td{position: relative;}
.tc2_close{position: absolute;right:0;top:0;}
h3{position: relative;}
.productionCompletion .s .d{display:inline-block;width:190px;float:left;position:relative;top:6px;}
.productionCompletion .s .d_btn{float:left;}
.productionCompletion .th13{width:335px;}
.productionCompletion .s1{width:100px;}
.productionCompletion .s2{width:200px;}
.productionCompletion .s3{width:100px;}
.productionCompletion .s4{width:100px;}
.productionCompletion .s5{width:30px;}
.productionCompletion .s13{width:310px;}
.productionCompletion_tc table{width:750px;}
.checked_more{font-weight: bold;color: green;}
.goodsEntry_tc table{width:1110px;margin-top: 15px;}
.goodsEntry  td{position: relative;}
.tc3_close{position: absolute;right:0;top:0;}
.goodsEntry .d1{display: none;width:50px;position:relative;top:6px;}
.goodsEntry{max-height: 750px;overflow-y: scroll;width:1187px;}
.goodsEntry .date_d{width:136px;}
.goodsEntry{position:fixed;top:20px;left:50%;transform:translate(-50%,0);box-shadow: 0 3px 9px rgba(0,0,0,.45);background-color: #fff;z-index: 100;display:none;padding:20px;height:750px;overflow-y: auto;overflow-x:hidden;}
</style>
</head>
<body>
<div class="quality_map container-fluid">
	<div class="row mt30">
		<div class="col-xs-6">
			<h3 class="h3_title">本周质检地图</h3>
		</div>
		<div class="col-xs-6 text-right">
			<img src="../img/logo.png">
		</div>
	</div>
	<input type="hidden" id="userId" name="userId" value="${userId}">
	<input type="hidden" id="userName" name="userName" value="${userName}">
	<input type="hidden" id="roleNo" name="roleNo" value="${roleNo}">
	<input type="hidden" id="start" name="start" value="${start}">
	<input type="hidden" id="end" name="end" value="${end}">

	<!-- 第一个表格开始 -->
	<div class="row">
		<div class="col-xs-6">
			<span class="f16 mr30">以前未完成检验的生产项目</span>

		</div>
		<div class="col-xs-6 text-right">
			<a class="select_blank" target="_blank" href="/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}">返回功能选择页</a>
			<a class="select_blank" onclick="exitlogin()"> 退出系统 </a>
		</div>
	</div>

	<div class="row" style="margin-top:30px;">
		<div class="col-xs-12">
			<table class="table table-bordered mt10">
				<thead>
					<tr>
						<th class="th1">项目号</th>
						<th class="th2">项目名</th>
						<th class="th3">检验阶段</th>
						<th class="th4">合同金额</th>
						<th class="th5">等级</th>
						<th class="th6">工厂名</th>
						<th class="th7">发起人</th>
						<th class="th8" style="border-right: 5px solid rgb(255, 128, 0);">是否紧急</th>
						<th class="th9 th_name" >wangjingjun</th>
						<th class="th10 th_name" >maxiaolei</th>
						<th class="th10 th_name" >zhangyouqing</th>
						<th class="th12 th_name" >zhoubin</th>

						<th class="th14 th_name" >zoumin</th>
						<th class="th15 th_name" >zhuxiaojing</th>
						<th class="th16 th_name" >litie</th>
						<th class="th17 th_name" >dingxiang</th>
						<th class="th18" style="border-left: 5px solid rgb(255, 128, 0);">进料检验 <div class="toggle">隐藏</div></th>
						<th class="th19">检验计划上传日期</th>
						<th class="th20">最近质量投诉日期</th>
						<th class="th21">目前状态</th>
						<th class="th22">以前质检</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="obj" items="${projectTasks4}" varStatus="i">

				<tr>
						<td><span class="s1 th1"><a href="http://117.144.21.74:10010/inspection/toSelectInspection?userId=${userId}&roleNo=${roleNo}&userName=${userName}&projectNo=${obj.projectNo }" target="_blank">

						<c:if test="${obj.checkNumber && obj.checkNumber!='' }">${obj.checkNumber }</c:if>
						<c:if test="${!obj.checkNumber || obj.checkNumber=='' }">${obj.projectNo }</c:if>

						</a></span></td>
						<td><span class="s2 th2" title="${obj.projectName }">${obj.projectName }</span></td>
						<td><span class="s3 th3" title="${obj.description }">${obj.description }</span></td>
						<td><span class="s4 th4" title="${obj.projectAmount }">${obj.projectAmount }</span></td>
						<td><span class="s5 th5" title="">${obj.plantAnalysis == 0 ? '暂无' :(obj.plantAnalysis == 1 ? 'A' : (obj.plantAnalysis == 2 ? 'B' : obj.plantAnalysis == 3 ? 'C' : '暂无'))}</span></td>
						<td><span class="s6 th6" title="${obj.inspectionAddress }">${obj.inspectionAddress }</span></td>
						<td><span class="s7 th7" title="${obj.initiator }">${obj.initiator }</span></td>
						<td style="border-right: 5px solid rgb(255, 128, 0);">
							<span class="s8 th8" title="${obj.urgentReason }">${obj.urgentReason }</span>
						</td>


						<c:if test="${obj.finishTime<date2 &&obj.accepter=='wangjingjun' }">
							<td  style="background-color:yellow;" class="person_wrap" name="wangjingjun">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='wangjingjun'}">
							<td  style="background-color:#38ec06;" class="person_wrap" name="wangjingjun">
						</c:if>
						<c:if test="${obj.accepter!='wangjingjun'}">
							<td  class="person_wrap" name="wangjingjun">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='wangjingjun' }"><span class="aad_date_span display_table first">
						<%-- <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
							<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
							<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
							<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						</span></c:if></div>
						</td>


						<c:if test="${obj.finishTime<date2 &&obj.accepter=='maxiaolei'}">
							<td  style="background-color:yellow;" class="person_wrap" name="maxiaolei">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='maxiaolei' }">
							<td  style="background-color:#38ec06;" class="person_wrap" name="maxiaolei">
						</c:if>
						<c:if test="${obj.accepter!='maxiaolei'}">
							<td  class="person_wrap" name="maxiaolei">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='maxiaolei' }"><span class="aad_date_span display_table first">
						<%-- <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
							<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
							<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
							<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						</span></c:if></div>
						</td>

						<c:if test="${obj.finishTime<date2 &&obj.accepter=='zhangyouqing' }">
							<td  style="background-color:yellow;" class="person_wrap" name="zhangyouqing">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='zhangyouqing'  }">
							<td  style="background-color:#38ec06;" class="person_wrap" name="zhangyouqing">
						</c:if>
						<c:if test="${obj.accepter!='zhangyouqing'}">
							<td  class="person_wrap" name="zhangyouqing">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='zhangyouqing' }"><span class="aad_date_span display_table first">
<%-- 						<input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>

						<c:if test="${obj.finishTime<date2 &&obj.accepter=='zhoubin'}">
							<td  style="background-color:yellow;" class="person_wrap" name="zhoubin">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='zhoubin' }">
							<td  style="background-color:#38ec06;" class="person_wrap" name="zhoubin">
						</c:if>
						<c:if test="${obj.accepter!='zhoubin'}">
							<td  class="person_wrap" name="zhoubin">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='zhoubin' }"><span class="aad_date_span display_table first">
<%-- 						<input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
 --%>						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>

						</span></c:if></div>

						</td>

						<c:if test="${obj.finishTime<date2 &&obj.accepter=='zoumin'}">
							<td  style="background-color:yellow;" class="person_wrap" name="zoumin">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='zoumin'}">
							<td  style="background-color:#38ec06;" class="person_wrap" name="zoumin">
						</c:if>
						<c:if test="${obj.accepter!='zoumin'}">
							<td  class="person_wrap" name="zoumin">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='zoumin' }"><span class="aad_date_span display_table first">
						<%-- <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>

						<c:if test="${obj.finishTime<date2 &&obj.accepter=='zhuxiaojing' }">
							<td  style="background-color:yellow;" class="person_wrap" name="zhuxiaojing">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='zhuxiaojing'}">
							<td  style="background-color:#38ec06;" class="person_wrap" name="zhuxiaojing">
						</c:if>
						<c:if test="${obj.accepter!='zhuxiaojing'}">
							<td  class="person_wrap" name="zhuxiaojing">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='zhuxiaojing' }"><span class="aad_date_span display_table first">
<%-- 						<input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
 --%>						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>

								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>

						<c:if test="${obj.finishTime<date2 &&obj.accepter=='litie'}">
							<td  style="background-color:yellow;" class="person_wrap" name="litie">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='litie'}">
							<td  style="background-color:#38ec06;" class="person_wrap" name="litie">
						</c:if>
						<c:if test="${obj.accepter!='litie'}">
							<td  class="person_wrap" name="litie">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='litie' }"><span class="aad_date_span display_table first">
<%-- 						<input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
 --%>
 							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>

								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>

						<c:if test="${obj.finishTime<date2 &&obj.accepter=='dingxiang'}">
							<td  style="background-color:yellow;" class="person_wrap" name="dingxiang">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='dingxiang'}">
							<td  style="background-color:#38ec06;" class="person_wrap" name="dingxiang">
						</c:if>
						<c:if test="${obj.accepter!='dingxiang'}">
							<td  class="person_wrap" name="dingxiang">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='dingxiang' }"><span class="aad_date_span display_table first">

<%--  <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
 --%>
 							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>

								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>




						<td style="border-left: 5px solid rgb(255, 128, 0);"><span class="s18 th18" >${obj.incomingInspection }</span></td>
						<td><span class="s19 th19" >${obj.inspectionPlan }</span></td>
						<td><span class="s20 th20" >${obj.qualityComplaint }</span></td>
						<td><span class="s21 th21" title="${obj.produceStatus }">${obj.produceStatus }</span></td>
						<td><span class="s21 th21">${obj.zhijian1 }</span></td>

					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 第一个表格结束 -->

	<!-- 第2个表格开始 -->
	<div class="row">
		<div class="col-xs-12">
			<span class="f16 mr30">${start }至${end }检验的生产项目</span>
			<button class="btn btn-default select_blank"  onclick="searchAll(${roleNo},'${userName}',1);">显示按工作日和成员安排的表</button>
			<button class="btn btn-default select_blank btn_tc2"  onclick="searchProductionCompletion(${roleNo},'${userName}','${start}');">本周下周生产完成，未安排质检的项目(${noInspectionTask }) 第一次大货，但尚未安排中期检验项目(${noInterimInspection })</button>
			<button class="btn btn-default select_blank btn_tc3"  onclick="searchGoodsEntry();">到货表</button>
		 </div>
	</div>
	<div class="row tc_row">
		<div class="col-xs-12 table1_tc">
			<span class="close">关闭 </span>
		<div>
	<button class="btn btn-default"  onclick="searchAll(${roleNo},'${userName}',1);">本周成员安排表</button>
	<button class="btn btn-default"  onclick="searchAll(${roleNo},'${userName}',2);">下周成员安排表</button>
	<button class="btn btn-default"  onclick="searchAll(${roleNo},'${userName}',3);">上周成员安排表</button>
	</div>
	<table class="table table-bordered mt10">
        <thead>
			<tr>
				<th class="th19" style="background:#4eddff">日期</th>
				<th class="" style="background:#4eddff">dingxiang</th>
				<th class="" style="background:#4eddff">litie</th>
				<th class="" style="background:#4eddff">maxiaolei</th>
				<th class="" style="background:#4eddff">wangjingjun</th>
				<th class="" style="background:#4eddff">zhangyouqing</th>
				<th class="" style="background:#4eddff">zhoubin</th>
				<th class="" style="background:#4eddff">zhuxiaojing</th>
				<th class="" style="background:#4eddff">zoumin</th>
				<th class="" style="background:#4eddff">质检部</th>

			</tr>
		</thead>
		<tbody class="dp_tabel_body">

		</tbody>
	</table>
	</div>
	</div>
	<!-- 第2个弹窗开始 -->
	<div class="container productionCompletion productionCompletion_tc">
		<div class="row">
			<div class="col-xs-12">
				<h3 class="f16 mr30">本周下周生产完成,但未安排质检的项目 <button class="btn btn-default tc2_close">关闭</button></h3>
				<p>本表为本周及下周预计生产完成表，如果看到有项目还没有完成：</p>
				<p>1.马上预约项目检验</p>
				<p>2.或者不能马上布置任务可对预计完成时间进行后移调整.</p>
				<p>调整后项目将被移出来。本调整将只调整任务系统中的项目预计完成时间.</p>
			</div>
			<div class="col-xs-12">
				<table class="table table-bordered table1">
					<thead>
						<tr>
							<th class="th1">项目号</th>
							<th class="th2">项目名</th>
							<th class="th3">跟单</th>
							<th class="th4">采购</th>
							<th class="th5">等级</th>
							<th class="th13">预计交期调整或预约检验任务</th>
						</tr>
					</thead>

					<tbody class="no_inspection_task_list">

		            </tbody>
					<!-- <tbody>
						<tr>
							<td><a class="s s1" href="###">SHS22744</span></td>
							<td> <span class="s s2">装饰框</span> </td>
							<td> <span class="s s3">检验阶段</span> </td>
							<td> <span class="s s4">30000</span> </td>
							<td> <span class="s s5">A</span> </td>
							<td> <span class="s s6">公司</span> </td>
							<td> <span class="s s7">bensonzhi</span> </td>
							<td> <span class="s s8">不需要</span> </td>
							<td> <span class="s s9">2020-03-18</span> </td>
							<td> <span class="s s10">2020-03-20</span> </td>
							<td><span class="s s11">样品</span></td>
							<td><span class="s s12">无</span></td>
							<td>
								<div class="s s13">
									<div class="form-group clearfix">
										<span class="pull-left d1">样品:</span>
										<div class="pull-left date_d">
											<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
												<input id="quoteEndDate" name="quoteEndDate" class="form-control brt brt_7" size="16" type="text" value="" place="选择日期" field="报价截止日期" placeholder="选择日期" readonly requiredate >onchange="checkDeadline()"
												<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
											</div>
										</div>
									</div>
									<div class="form-group clearfix">
										<span class="pull-left d1">大货:</span>
										<div class="pull-left date_d">
											<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
												<input id="quoteEndDate" name="quoteEndDate" class="form-control brt brt_7" size="16" type="text" value="" place="选择日期" field="报价截止日期" placeholder="选择日期" readonly requiredate >onchange="checkDeadline()"
												<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
											</div>
										</div>
									</div>
									<button class="btn btn-default select_blank">预约检验任务</button>
								</div>
							</td>
						</tr>
					</tbody> -->
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<h3 class="f16 mr30">第一次大货，但是尚未安排中期检验的项目</h3>
				<p>本表为第一次大货，但尚未安排中期检验的项目,</p>
				<p>1.马上预约项目检验</p>
				<p>2.或者不能马上布置任务可对预计完成时间进行后移调整,来对应延后中期检验系统提示时间.</p>
				<p>调整后项目将被移本表。本调整将只调整任务系统中的项目预计完成时间.</p>
			</div>
			<div class="col-xs-12">
				<table class="table table-bordered table1">
					<thead>
						<tr>
							<th class="th1">项目号</th>
							<th class="th2">项目名</th>
							<th class="th3">跟单</th>
							<th class="th4">采购</th>
							<th class="th5">等级</th>
							<th class="th13">预计交期调整或预约检验任务</th>
						</tr>
					</thead>
					<tbody class="no_interim_inspection_list">

		            </tbody>
					<!-- <tbody>
						<tr>
							<td><a class="s s1" href="###">SHS22744</span></td>
							<td> <span class="s s2">装饰框</span> </td>
							<td> <span class="s s3">检验阶段</span> </td>
							<td> <span class="s s4">30000</span> </td>
							<td> <span class="s s5">A</span> </td>
							<td> <span class="s s6">公司</span> </td>
							<td> <span class="s s7">bensonzhi</span> </td>
							<td> <span class="s s8">不需要</span> </td>
							<td> <span class="s s9">2020-03-18</span> </td>
							<td> <span class="s s10">2020-03-20</span> </td>
							<td><span class="s s11">样品</span></td>
							<td><span class="s s12">无</span></td>
							<td>
								<div class="s s13">
									<div class="form-group clearfix">
										<span class="pull-left d1">样品:</span>
										<div class="pull-left date_d">
											<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
												<input id="quoteEndDate" name="quoteEndDate" class="form-control brt brt_7" size="16" type="text" value="" place="选择日期" field="报价截止日期" placeholder="选择日期" readonly requiredate >onchange="checkDeadline()"
												<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
											</div>
										</div>
									</div>
									<div class="form-group clearfix">
										<span class="pull-left d1">大货:</span>
										<div class="pull-left date_d">
											<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
												<input id="quoteEndDate" name="quoteEndDate" class="form-control brt brt_7" size="16" type="text" value="" place="选择日期" field="报价截止日期" placeholder="选择日期" readonly requiredate >onchange="checkDeadline()"
												<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
											</div>
										</div>
									</div>
									<button class="btn btn-default select_blank">预约检验任务</button>
								</div>
							</td>
						</tr>
					</tbody> -->
				</table>
			</div>
		</div>
	</div>
	<!-- 第2个弹窗结束 -->
	<!-- 第3个弹窗开始 -->
	<div class="container goodsEntry goodsEntry_tc">
		<div class="row">
			<div class="col-xs-12">
				<h3 class="f16 mr30">到货情况表 <button class="btn btn-default tc3_close">关闭</button></h3>
			</div>
			<div class="col-xs-12">
				<table class="table table-bordered table1">
					<thead>
						<tr>
							<th class="th1">图片</th>
							<th class="th1">入库日期</th>
							<th class="th2">入库单号</th>
							<th class="th3">项目号</th>
							<th class="th4">货物名称</th>
							<th class="th5">数量</th>
							<th class="th13">箱数/托板数</th>
							<th class="th13">包装单位</th>
							<th class="th13">项目负责人</th>
							<th class="th13">发件方</th>
						</tr>
					</thead>

					<tbody class="goods_entry_task_list">

		            </tbody>
				</table>
			</div>
		</div>
	</div>
	 <!-- 第3个弹窗结束 -->


	<!-- 第2个表格开始  -->
	<div class="row">
		<div class="col-xs-12">
			<table class="table table-bordered mt10">
				<thead>
					<tr>
						<th class="th1">项目号</th>
						<th class="th2">项目名</th>
						<th class="th3">检验阶段</th>
						<th class="th4">合同金额</th>
						<th class="th5">等级</th>
						<th class="th6">工厂名</th>
						<th class="th7">发起人</th>
						<th class="th8" style="border-right: 5px solid rgb(255, 128, 0);">是否紧急</th>
						<th class="th9 th_name" >质检部</th>
						<th class="th9 th_name" >wangjingjun</th>
						<th class="th10 th_name" >maxiaolei</th>
						<th class="th10 th_name" >zhangyouqing</th>
						<th class="th12 th_name" >zhoubin</th>

						<th class="th14 th_name" >zoumin</th>
						<th class="th15 th_name" >zhuxiaojing</th>
						<th class="th16 th_name" >litie</th>
						<th class="th17 th_name" >dingxiang</th>
						<th class="th18" style="border-left: 5px solid rgb(255, 128, 0);">进料检验 <div class="toggle" style="right:unset;left:-35px;">隐藏</div></th>
						<th class="th19">检验计划上传日期</th>
						<th class="th20">最近质量投诉日期</th>
						<th class="th21">目前状态</th>
						<th class="th22">以前质检</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="obj" items="${projectTasks1}" varStatus="i">

				<tr>
						<td><span class="s1 th1"><a href="http://117.144.21.74:10010/inspection/toSelectInspection?userId=${userId}&roleNo=${roleNo}&userName=${userName}&projectNo=${obj.projectNo }" target="_blank">
						<c:if test="${obj.checkNumber!=null && obj.checkNumber!='' }">${obj.checkNumber }</c:if>
						<c:if test="${obj.checkNumber == null || obj.checkNumber=='' }">${obj.projectNo }</c:if>
						</a></span></td>
						<td><span class="s2 th2" title="${obj.projectName }">${obj.projectName }</span></td>
						<td><span class="s3 th3" title="${obj.description }">${obj.description }</span></td>
						<td><span class="s4 th4" title="${obj.projectAmount }">${obj.projectAmount }</span></td>
						<td><span class="s5 th5" title="">${obj.plantAnalysis == 0 ? '暂无' :(obj.plantAnalysis == 1 ? 'A' : (obj.plantAnalysis == 2 ? 'B' : obj.plantAnalysis == 3 ? 'C' : '暂无'))}</span></td>
						<td><span class="s6 th6" title="${obj.inspectionAddress }">${obj.inspectionAddress } <c:if test="${obj.dTalkIds>0 }"><span style="color:red;margin-left:10px;">已入库</span></c:if></span></td>
						<td><span class="s7 th7" title="${obj.initiator }">${obj.initiator }</span></td>
						<td style="border-right: 5px solid rgb(255, 128, 0);">
							<span class="s8 th8" title="${obj.urgentReason }">${obj.urgentReason }</span>
						</td>



						<c:if test="${obj.finishTime<date2 &&obj.accepter=='质检部' }">
							<td  style="background-color:yellow;" class="person_wrap" name="质检部">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='质检部'}">
							<td  style="background-color:#38ec06;" class="person_wrap" name="质检部">
						</c:if>
						<c:if test="${obj.accepter!='质检部'}">
							<td  class="person_wrap" name="质检部">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='质检部' }"><span class="aad_date_span display_table first">
						<%-- <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
							<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
							<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
							<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						</span></c:if></div>
						</td>




						<c:if test="${obj.finishTime<date2 &&obj.accepter=='wangjingjun' }">
							<td  style="background-color:yellow;" class="person_wrap" name="wangjingjun">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='wangjingjun'}">
							<td  style="background-color:#38ec06;" class="person_wrap" name="wangjingjun">
						</c:if>
						<c:if test="${obj.accepter!='wangjingjun'}">
							<td  class="person_wrap" name="wangjingjun">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='wangjingjun' }"><span class="aad_date_span display_table first">
						<%-- <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
							<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
							<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
							<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						</span></c:if></div></td>




						<c:if test="${obj.finishTime<date2 &&obj.accepter=='maxiaolei'}">
							<td  style="background-color:yellow;" class="person_wrap" name="maxiaolei">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='maxiaolei' }">
							<td  style="background-color:#38ec06;" class="person_wrap" name="maxiaolei">
						</c:if>
						<c:if test="${obj.accepter!='maxiaolei'}">
							<td  class="person_wrap" name="maxiaolei">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='maxiaolei' }"><span class="aad_date_span display_table first">
						<%-- <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
							<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
							<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
							<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						</span></c:if></div>
						</td>

						<c:if test="${obj.finishTime<date2 &&obj.accepter=='zhangyouqing' }">
							<td  style="background-color:yellow;" class="person_wrap" name="zhangyouqing">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='zhangyouqing'  }">
							<td  style="background-color:#38ec06;" class="person_wrap" name="zhangyouqing">
						</c:if>
						<c:if test="${obj.accepter!='zhangyouqing'}">
							<td  class="person_wrap" name="zhangyouqing">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='zhangyouqing' }"><span class="aad_date_span display_table first">
<%-- 						<input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>

						<c:if test="${obj.finishTime<date2 &&obj.accepter=='zhoubin'}">
							<td  style="background-color:yellow;" class="person_wrap" name="zhoubin">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='zhoubin' }">
							<td  style="background-color:#38ec06;" class="person_wrap" name="zhoubin">
						</c:if>
						<c:if test="${obj.accepter!='zhoubin'}">
							<td  class="person_wrap" name="zhoubin">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='zhoubin' }"><span class="aad_date_span display_table first">
<%-- 						<input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
 --%>						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>

						</span></c:if></div>
						</td>

						<c:if test="${obj.finishTime<date2 &&obj.accepter=='zoumin'}">
							<td  style="background-color:yellow;" class="person_wrap" name="zoumin">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='zoumin'}">
							<td  style="background-color:#38ec06;" class="person_wrap" name="zoumin">
						</c:if>
						<c:if test="${obj.accepter!='zoumin'}">
							<td  class="person_wrap" name="zoumin">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='zoumin' }"><span class="aad_date_span display_table first">
						<%-- <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>

						<c:if test="${obj.finishTime<date2 &&obj.accepter=='zhuxiaojing' }">
							<td  style="background-color:yellow;" class="person_wrap" name="zhuxiaojing">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='zhuxiaojing'}">
							<td  style="background-color:#38ec06;" class="person_wrap" name="zhuxiaojing">
						</c:if>
						<c:if test="${obj.accepter!='zhuxiaojing'}">
							<td  class="person_wrap" name="zhuxiaojing">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='zhuxiaojing' }"><span class="aad_date_span display_table first">
<%-- 						<input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
 --%>						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>

								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>

						<c:if test="${obj.finishTime<date2 &&obj.accepter=='litie'}">
							<td  style="background-color:yellow;" class="person_wrap" name="litie">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='litie'}">
							<td  style="background-color:#38ec06;" class="person_wrap" name="litie">
						</c:if>
						<c:if test="${obj.accepter!='litie'}">
							<td  class="person_wrap" name="litie">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='litie' }"><span class="aad_date_span display_table first">
<%-- 						<input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
 --%>
 							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>

								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>

						<c:if test="${obj.finishTime<date2 &&obj.accepter=='dingxiang'}">
							<td  style="background-color:yellow;" class="person_wrap" name="dingxiang">
						</c:if>
						<c:if test="${obj.finishTime>=date2 &&obj.accepter=='dingxiang'}">
							<td  style="background-color:#38ec06;" class="person_wrap" name="dingxiang">
						</c:if>
						<c:if test="${obj.accepter!='dingxiang'}">
							<td  class="person_wrap" name="dingxiang">
						</c:if>
						<div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='dingxiang' }"><span class="aad_date_span display_table first">

<%--  <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
 --%>
 							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>

								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>




						<td style="border-left: 5px solid rgb(255, 128, 0);"><span class="s18 th18" >${obj.incomingInspection }</span></td>
						<td><span class="s19 th19" >${obj.inspectionPlan }</span></td>
						<td><span class="s20 th20" >${obj.qualityComplaint }</span></td>
						<td><span class="s21 th21" title="${obj.produceStatus }">${obj.produceStatus }</span></td>
						<td><span class="s21 th21">${obj.zhijian1 }</span></td>

					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 第2个表格结束  -->
	</div>
	<!-- 第3个表格开始  -->
	<div class="row">
		<div class="col-xs-12">
		<span class="f16 mr30">检验完成的，但还没结束的项目  （如果 需要复检，请 重新布置检验任务）</span>
		</div>
		<div class="col-xs-12">
			<table class="table table-bordered mt10">
				<thead>
					<tr>
						<th class="th1">项目号</th>
						<th class="th2">项目名</th>
						<th class="th3">检验阶段</th>
						<th class="th4">合同金额</th>
						<th class="th5">等级</th>
						<th class="th6">工厂名</th>
						<th class="th7">发起人</th>
						<th class="th8" style="border-right: 5px solid rgb(255, 128, 0);">是否紧急</th>
						<th class="th9 th_name" >wangjingjun</th>
						<th class="th10 th_name" >maxiaolei</th>
						<th class="th10 th_name" >zhangyouqing</th>
						<th class="th12 th_name" >zhoubin</th>
						<!-- <th class="th13 th_name" >zhaochun</th> -->
						<th class="th14 th_name" >zoumin</th>
						<th class="th15 th_name" >zhuxiaojing</th>
						<th class="th16 th_name" >litie</th>
						<th class="th17 th_name" >dingxiang</th>

						<th class="th18" style="border-left: 5px solid rgb(255, 128, 0);">进料检验<div class="toggle">隐藏</div></th>
						<th class="th19">检验计划上传日期</th>
						<th class="th20">最近质量投诉日期</th>
						<th class="th21">质检报告</th>
						<th class="th21">目前状态</th>
						<th class="th21">以前质检</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="obj" items="${projectTasks3}" varStatus="i">
					<tr>
						<td><a class="s1 th1" href="http://117.144.21.74:10010/inspection/toSelectInspection?userId=${userId}&roleNo=${roleNo}&userName=${userName}&projectNo=${obj.projectNo }" target="_blank">${obj.projectNo }</a></td>
						<td><span class="s2 th2" title="${obj.projectName }">${obj.projectName }</span></td>
						<td><span class="s3 th3" title="${obj.description }">${obj.description }</span></td>
						<td><span class="s4 th4" title="${obj.projectAmount }">${obj.projectAmount }</span></td>
						<td><span class="s5 th5" title="">${obj.plantAnalysis == 0 ? '暂无' :(obj.plantAnalysis == 1 ? 'A' : (obj.plantAnalysis == 2 ? 'B' : obj.plantAnalysis == 3 ? 'C' : '暂无'))}</span></td>
						<td><span class="s6 th6" title="${obj.inspectionAddress }">${obj.inspectionAddress }</span></td>
						<td><span class="s7 th7" title="${obj.initiator }">${obj.initiator }</span></td>
						<td style="border-right: 5px solid rgb(255, 128, 0);">
							<span class="s8 th8" title="${obj.urgentReason }">${obj.urgentReason }</span>
						</td>

						<td class="person_wrap"><div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='wangjingjun' }"><span class="aad_date_span display_table first">
						<%-- <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
							<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
							<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
							<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						</span></c:if></div>
						</td>
						<td class="person_wrap"><div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='maxiaolei' }"><span class="aad_date_span display_table first">
						<%-- <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
							<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
							<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
							<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
						</div>
						</span></c:if></div>
						</td>
						<td class="person_wrap"><div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='zhangyouqing' }"><span class="aad_date_span display_table first">
<%-- 						<input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>
						<td class="person_wrap"><div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='zhoubin' }"><span class="aad_date_span display_table first">
<%-- 						<input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
 --%>						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>

						</span></c:if></div>
						</td>

						<td class="person_wrap"><div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='zoumin' }"><span class="aad_date_span display_table first">
						<%-- <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/> --%>
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>
						<td class="person_wrap"><div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='zhuxiaojing' }"><span class="aad_date_span display_table first">
<%-- 						<input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
 --%>						<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>

								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>
						<td class="person_wrap"><div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='litie' }"><span class="aad_date_span display_table first">
<%-- 						<input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
 --%>
 							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>

								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>
						<td class="person_wrap"><div class="person_in">
						<%-- <span class="aad_date_span ">
							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
								<!-- <span class="input-group-addon blt posirela"><i class="line1 posiabso"></i></span>  -->
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span> --%>
						<c:if test="${obj.accepter=='dingxiang' }"><span class="aad_date_span display_table first">

<%--  <input type="text" name="finishTime" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>
 --%>
 							<div class="add_date input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input  name="finishTime" class="form-control brt brt_7" size="16" type="text" value="<fmt:formatDate value='${obj.finishTime }' pattern='yyyy-MM-dd'/>" place="出检日期" field="报价截止日期" placeholder="出检日期" readonly requiredate onchange="selectOnchangeUpdate(this,'${obj.projectNoId}','${obj.accepter}')"/>

								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</span></c:if></div>
						</td>


						<td style="border-left: 5px solid rgb(255, 128, 0);"><span class="s18 th18" >${obj.incomingInspection }</span></td>
						<td><span class="s19 th19" >${obj.inspectionPlan }</span></td>
						<td><span class="s20 th20" >${obj.qualityComplaint }</span></td>
						<td><span class="s20 th20" ><a  href="https://www.kuaizhizao.cn/quality/shareQuality?id=${obj.id }" target="_blank">质检报告</a></span></td>
						<td><span class="s21 th21" title="${obj.produceStatus }">${obj.produceStatus }</span></td>
						<td><span class="s21 th21">${obj.zhijian1 }</span></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 第3个表格结束  -->
</div>

<!-- <script src="../lib/jquery/jquery.min.js"></script> -->
<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>

<!-- <script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script> -->
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
/* 第一个弹窗开始 */
$('thead .th8').css('border-right','5px solid #ff8000');
$('thead .th18').css('border-left','5px solid #ff8000');
$('tbody .th8').parent('td').css('border-right','5px solid #ff8000');
$('tbody .th18').parent('td').css('border-left','5px solid #ff8000');
// 日期表格切换
var dataClone = $('.first_data_add').clone(true);

//显示隐藏切换
	$('.toggle').click(function(){
		$(this).closest('thead').find('.th_name').toggle();
		$(this).closest('table').find('tr').find('.person_wrap').toggle();
		var display = $(this).closest('table').find('tr').find('.person_wrap').css('display');
		if(display == 'none'){
			$(this).closest('table').find('.s1').css('width','90px');
			$(this).closest('table').find('.s2').css('width','220px');
			$(this).closest('table').find('.s3').css('width','140px');
			$(this).closest('table').find('.s4').css('width','100px');
			$(this).closest('table').find('.s5').css('width','30px');
			$(this).closest('table').find('.s6').css('width','210px');
			$(this).closest('table').find('.s7').css('width','100px');
			$(this).closest('table').find('.s8').css('width','210px');

			$(this).closest('table').find('.s13').css('width','310px');
			$(this).closest('table').find('.s18').css('width','100px');
			$(this).closest('table').find('.s19').css('width','75px');
			$(this).closest('table').find('.s20').css('width','75px');
			$(this).closest('table').find('.s21').css('width','180px');
			$(this).closest('table').find('.s22').css('width','180px');


		}else{
			$(this).closest('table').find('.s1').css('width','90px');
			$(this).closest('table').find('.s2').css('width','110px');
			$(this).closest('table').find('.s3').css('width','65px');
			$(this).closest('table').find('.s4').css('width','65px');
			$(this).closest('table').find('.s5').css('width','30px');
			$(this).closest('table').find('.s6').css('width','100px');
			$(this).closest('table').find('.s7').css('width','70px');
			$(this).closest('table').find('.s8').css('width','100px');

			$(this).closest('table').find('.person_in').css('width','125px');

			/* $(this).closest('table').find('.s13').css('width','310px'); */

			$(this).closest('table').find('.s18').css('width','60px');
			$(this).closest('table').find('.s19').css('width','75px');
			$(this).closest('table').find('.s20').css('width','75px');
			$(this).closest('table').find('.s21').css('width','90px');
			$(this).closest('table').find('.s22').css('width','90px');
		}
	});
/* 第一个弹窗结束 */
/* 第二个弹窗开始 */

$('.productionCompletion .th8').css('border-right','0 none');
$('.btn_tc2').click(function(){
	$('.productionCompletion').show();
});
$('.btn_tc3').click(function(){
	$('.goodsEntry').show();
});
$('.tc2_close').click(function(){
	$('.productionCompletion').hide();
});
$('.tc3_close').click(function(){
    $('.goods_entry_task_list').html('');
	$('.goodsEntry').hide();
});
/* 查询数据 */
function searchGoodsEntry(){
	 $.ajax({
		type : "post",
		url : "${ctx}/inspection/searchGoodsEntry",
		data : {
		},
		 success : function(json) {
			 if (json.ok) {
				 var obj=eval(json.data.listEntry);
				 var text="";
				 $(obj).each(function (index){
					 var val=obj[index];
					 text +='<tr><td><img width="200px" height="200px" src="'+val.goodsImg+'"></td>'
					 +'<td width="200px">'+val.createTime+'</td>'+
					 '<td width="200px">'+val.businessId+'</td>'+
					 '<td width="200px">'+val.projectNumber+'</td>'+
					 '<td width="200px">'+val.textCount+'</td>'+
					 '<td width="100px">'+val.goodsCount+'</td>'+
					 '<td width="100px">'+val.bagCount+'</td>'+
					 '<td width="100px">'+val.goodsUnit+'</td>'+
					 '<td width="200px">'+val.claimUser+'</td>'+
					 '<td width="200px">'+val.recipient+'</td>';
				 })
				 $('.goods_entry_task_list').html(text);

			 }


		 }

	})
}
/* 查询数据 */
 function searchProductionCompletion(roleNo,userName,start){

	 $.ajax({
		type : "post",
		url : "${ctx}/inspection/searchProductionCompletion",
		data : {
			"roleNo":roleNo,
			"userName":userName

		},
		 success : function(json) {
           if (json.ok) {
        	   var obj=eval(json.data.noInspectionTaskList);
        	   var obj1=eval(json.data.noInterimInspectionList);
        	   var text="";
        	   var text1="";

        	   $(obj).each(function (index){
        		 var val=obj[index];
        		 var plantAnalysis="";
         		  if(val.plantAnalysis == 0){
         			plantAnalysis="暂无";
         		  }else if(val.plantAnalysis == 1){
         			plantAnalysis="A";
         		  }else if(val.plantAnalysis == 2){
         			plantAnalysis="B";
         		  }else if(val.plantAnalysis == 3){
         			plantAnalysis="C";
         		  }
         		 var sampleDeliveryDate='';
				 if(val.sampleDeliveryDate!= '' && val.sampleDeliveryDate!= null &&new Date(val.sampleDeliveryDate)>new Date(start)){
					 sampleDeliveryDate+="&nbsp;"+val.sampleDeliveryDate;
				 }
				 var deliveryTime='';
				 if(val.deliveryTime!= '' && val.deliveryTime!= null &&new Date(val.deliveryTime)>new Date(start)){
					 deliveryTime+="&nbsp;"+val.deliveryTime;
				 }
                 text+="<tr><td><span class='s1'>"+val.projectNo+"</span></td>"
                 +"<td><span class='s2'>"+val.projectName+"</span></td>"
                 +"<td><span class='s3'>"+val.sellName+"</span></td>"
                 +"<td><span class='s4'>"+val.purchaseName+"</span></td>"
                 +"<td><span class='s5'>"+plantAnalysis+"</span></td>";
                 text+="<td>"
				 +"<div class='s s13 clearfix'>"	;
				if(sampleDeliveryDate!=""&&sampleDeliveryDate!=null){
					text+="<div class='form-group clearfix d pull-left'>"

				 +"<span class='pull-left d1'>样品:</span>"
				 +"			<div class='pull-left date_d'>"
				 +"				<div class='input-group date form_date' data-date='' data-date-format='yyyy-mm-dd'>"
				 +'					<input  name="sampleDeliveryDate" class="form-control brt brt_7" size="16" type="text" value=\''+sampleDeliveryDate+'\' place="选择日期" field="报价截止日期" placeholder="选择日期" readonly requiredate onchange="checkDeadline(this,\''+val.projectNo+'\',1)">	'
				 +"					<span class='input-group-addon'><i class='glyphicon glyphicon-calendar'></i></span>"
				 +"				</div>"
				 +"			</div>"
				 +"		</div>";
				}
				 if(deliveryTime!=""&&deliveryTime!=null){
					 text+="		<div class='form-group clearfix d pull-left'>"
				 +"			<span class='pull-left d1'>大货:</span>"
				 +"			<div class='pull-left date_d'>"
				 +"				<div class='input-group date form_date' data-date='' data-date-format='yyyy-mm-dd'>"
				 +'					<input  name="deliveryTime" class="form-control brt brt_7" size="16" type="text" value=\''+deliveryTime+'\' place="选择日期" field="报价截止日期" placeholder="选择日期" readonly requiredate onchange="checkDeadline(this,\''+val.projectNo+'\',2)" >'
 				 +"					<span class='input-group-addon'><i class='glyphicon glyphicon-calendar'></i></span>"
				 +"				</div>"
				 +"			</div>"
				 +"		</div>";
				 }
				 text+='		<button class="btn btn-default select_blank d_btn pull-left" onclick="appointmentInspectionTask(\''+val.projectNo+'\');">预约检验任务</button>'
 				+"	</div>"
				 +"</td>"


                		 +"</tr>";
				});
        	   $(obj1).each(function (index){
          		 var val=obj1[index];
          		var plantAnalysis="";
       		  if(val.plantAnalysis == 0){
       			plantAnalysis="暂无";
       		  }else if(val.plantAnalysis == 1){
       			plantAnalysis="A";
       		  }else if(val.plantAnalysis == 2){
       			plantAnalysis="B";
       		  }else if(val.plantAnalysis == 3){
       			plantAnalysis="C";
       		  }

			 var deliveryTime='';
			 if(val.deliveryTime!= '' && val.deliveryTime!= null){
				 deliveryTime+="&nbsp;"+val.deliveryTime;
			 }
          		 text1+="<tr><td><span class='s1'>"+val.projectNo+"</span></td>"
                 +"<td><span class='s2'>"+val.projectName+"</span></td>"
                 +"<td><span class='s3'>"+val.sellName+"</span></td>"
                 +"<td><span class='s4'>"+val.purchaseName+"</span></td>"
                 +"<td><span class='s5'>"+plantAnalysis+"</span></td>"
                		+ "<td>"
        				 +"<div class='s s13 clearfix'>"
        				/*  +"<div class='form-group clearfix d'>"
        				 +"<span class='pull-left d1'>样品:</span>"
        				 +"			<div class='pull-left date_d'>"
        				 +"				<div class='input-group date form_date' data-date='' data-date-format='yyyy-mm-dd'>"
        				 +'					<input  name="sampleDeliveryDate" class="form-control brt brt_7" size="16" type="text" value=\''+sampleDeliveryDate+'\' place="选择日期" field="报价截止日期" placeholder="选择日期" readonly requiredate onchange="checkDeadline(this,\''+val.projectNo+'\',1)">	'
        				  +"					<span class='input-group-addon'><i class='glyphicon glyphicon-calendar'></i></span>"
        				 +"				</div>"
        				 +"			</div>"
        				 +"		</div>" */
        				 +"		<div class='form-group clearfix d pull-left'>"
        				 +"			<span class='pull-left d1'>大货:</span>"
        				 +"			<div class='pull-left date_d'>"
        				 +"				<div class='input-group date form_date' data-date='' data-date-format='yyyy-mm-dd'>"
        				 +'					<input  name="deliveryTime" class="form-control brt brt_7" size="16" type="text" value=\''+deliveryTime+'\' place="选择日期" field="报价截止日期" placeholder="选择日期" readonly requiredate onchange="checkDeadline(this,\''+val.projectNo+'\',2)" >'
        				 +"					<span class='input-group-addon'><i class='glyphicon glyphicon-calendar'></i></span>"
        				 +"				</div>"
        				 +"			</div>"
        				 +"		</div>"
        				 +'		<button class="btn btn-default select_blank d_btn pull-left" onclick="appointmentInspectionTask(\''+val.projectNo+'\');">预约检验任务</button>'
        				 +"	</div>"
        				 +"</td>"
                		 +"</tr>";
  				});



        	  $('.no_inspection_task_list').html(text);
        	  $('.no_interim_inspection_list').html(text1);
        	 // $('.no_inspection_task_list').html(text);
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
	       	   });


			} else {
				layer.msg(json.message, {
					time : 2000
				});
			}
		}
	})
}

 function checkDeadline(obj,projectNo,num){
	 var time="";
	 if(num==1){
	  time=$(obj).parents('tr').find("input[name='sampleDeliveryDate']").val();
	 }else if(num==2){
		 time=$(obj).parents('tr').find("input[name='deliveryTime']").val();
	 }
	 var roleNo = $("#roleNo").val();
		var userName = $("#userName").val();
	 $.ajax({
	     type:"post",
	     url:"${ctx}/project/updateDeliveryDateModification",
	     data:{
	    	    projectNo:projectNo,
	    	    num:num,
	    	    time:time
	     },
	     success:function(json){
//		       var json = eval("(" + data +")");
		   if(json.ok){
			   $('.productionCompletion').show();
			   searchProductionCompletion(roleNo,userName);
		   }else{
			   layer.msg(json.message,{time:2000});
		   }
	     }
	 });
}


 function appointmentInspectionTask(projectNo){
	 var roleNo = $("#roleNo").val();
	var userName = $("#userName").val();
	var userId = $("#userId").val();
	 window.open("http://117.144.21.74:10010/inspection/toInputInspection?roleNo="+roleNo+"&userId="+userId+"&userName="+userName+"&projectNo="+projectNo);
 }



/* 客户系列统计 */
function searchAll(roleNo,userName,num){

	 $.ajax({
		type : "post",
		url : "${ctx}/inspection/searchAll",
		data : {
			"roleNo":roleNo,
			"userName":userName,
			"num":num

		},
		 success : function(json) {
           if (json.ok) {
        	   var obj=eval(json.data.projectTasks0);
        	   var obj1=eval(json.data.projectTasks1);
        	   var obj2=eval(json.data.projectTasks2);
        	   var obj3=eval(json.data.projectTasks3);
        	   var obj4=eval(json.data.projectTasks4);
        	   var obj5=eval(json.data.projectTasks5);
        	   var obj6=eval(json.data.projectTasks6);

        	   var Monday=json.data.Monday;
        	   var Tuesday=json.data.Tuesday;
        	   var Wednesday=json.data.Wednesday;
        	   var Thursday=json.data.Thursday;
        	   var Friday=json.data.Friday;
        	   var Saturday=json.data.Saturday;
        	   var Sunday=json.data.Sunday;
        	   var name="";
        	   if(num==1){
        	  name="本周";
        	   }else if(num==2){
        	  name="下周";
        	   }else if(num==3){
        	  name="上周";
        	   }
        	   var text="";
        	   text+="<tr><td style='background:#4eddff'>"+name+"星期一"+Monday+"</td>";
        	   $(obj).each(function (index){
        		var val=obj[index];
        		if(val.inspectionAddress== "" || val.inspectionAddress == null || val.inspectionAddress == undefined){
        			text+="<td><span class='add_span'>无安排</span></td>";
        		}else{
        		var value=val.inspectionAddress;
				text+="<td><span class='add_span'>"+value+"</span></td>";
        		}
				});
        	   text+="</tr>";
        	   text+="<tr><td style='background:#4eddff'>"+name+"星期二 "+Tuesday+"</td>";
        	   $(obj1).each(function (index){
        		var val=obj1[index];
        		if(val.inspectionAddress== "" || val.inspectionAddress == null || val.inspectionAddress == undefined){
        			text+="<td><span class='add_span'>无安排</span></td>";
        		}else{
        		var value=val.inspectionAddress;
				text+="<td><span class='add_span'>"+value+"</span></td>";
        		}
				});
        	   text+="</tr>";
        	   text+="<tr><td style='background:#4eddff'>"+name+"星期三 "+Wednesday+"</td>";
        	   $(obj2).each(function (index){
        		var val=obj2[index];
        		if(val.inspectionAddress== "" || val.inspectionAddress == null || val.inspectionAddress == undefined){
        			text+="<td><span class='add_span'>无安排</span></td>";
        		}else{
        		var value=val.inspectionAddress;
				text+="<td><span class='add_span'>"+value+"</span></td>";
        		}
				});

        	   text+="</tr>";
        	   text+="<tr><td style='background:#4eddff'>"+name+"星期四 "+Thursday+"</td>";
        	   $(obj3).each(function (index){
        		var val=obj3[index];
        		if(val.inspectionAddress== "" || val.inspectionAddress == null || val.inspectionAddress == undefined){
        			text+="<td><span class='add_span'>无安排</span></td>";
        		}else{
        		var value=val.inspectionAddress;
				text+="<td><span class='add_span'>"+value+"</span></td>";
        		}
				});

        	   text+="</tr>";
        	   text+="<tr><td style='background:#4eddff'>"+name+"星期五 "+Friday+"</td>";
        	   $(obj4).each(function (index){
        		var val=obj4[index];
        		if(val.inspectionAddress== "" || val.inspectionAddress == null || val.inspectionAddress == undefined){
        			text+="<td><span class='add_span'>无安排</span></td>";
        		}else{
        		var value=val.inspectionAddress;
				text+="<td><span class='add_span'>"+value+"</span></td>";
        		}
				});
        	   text+="</tr>";
        	   text+="<tr><td style='background:#4eddff'>"+name+"星期六 "+Saturday+"</td>";
        	   $(obj5).each(function (index){
        		var val=obj5[index];
        		if(val.inspectionAddress== "" || val.inspectionAddress == null || val.inspectionAddress == undefined){
        			text+="<td><span class='add_span'>无安排</span></td>";
        		}else{
        		var value=val.inspectionAddress;
				text+="<td><span class='add_span'>"+value+"</span></td>";
        		}
				});
        	   text+="</tr>";
        	   text+="<tr><td style='background:#4eddff'>"+name+"星期日 "+Sunday+"</td>";
        	   $(obj6).each(function (index){
        		var val=obj6[index];
        		if(val.inspectionAddress== "" || val.inspectionAddress == null || val.inspectionAddress == undefined){
        			text+="<td><span class='add_span'>无安排</span></td>";
        		}else{
        		var value=val.inspectionAddress;
				text+="<td><span class='add_span' >"+value+"</span></td>";
        		}

				});
        	   text+="</tr>";


        	   // alert(text);
        	   $('.dp_tabel_body').html(text);
        	   $('.table1_tc').show();

        	   if(num === 3){
        	       $(".table1_tc").find(".mt10").find(".checked_ad").each(function(){
        	           if($(this).html()=='仓库'){
        	               $(this).addClass("checked_more");
                           $(this).attr('color','green');

					   }
				   });
			   }

			} else {
				layer.msg(json.message, {
					time : 2000
				});
			}
		}
	})
}

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


$('.table1_tc .close').click(function(){
	$('.table1_tc').hide();
});


</script>
<script type="text/javascript">


function selectOnchangeUpdate(obj,projectNoId,acceptor1){
	var accepter = $(obj).closest('td').attr('name');
	var roleNo = $("#roleNo").val();
	if(roleNo==9||roleNo==100){
	 var finishTime=$(obj).context.value;
	 var userName = $("#userName").val();
	 var start = $("#start").val();
	 var end = $("#end").val();
	 $.ajax({
	     type:"post",
	     url:"${ctx}/inspection/updateInspectionReservation",
	     data:{
	        	projectNoId:projectNoId,
	        	finishTime:finishTime,
	        	userName:userName,
	        	start:start,
	        	end:end,
	        	acceptor1:acceptor1,
	        	accepter:accepter
	     },
	     success:function(json){
//	       var json = eval("(" + data +")");
		   if(json.ok){
			   layer.msg(json.message,{time:4000});
		   }else{
			   layer.msg(json.message,{time:4000});
		   }
	     }
	 });
	}else{
		layer.msg("抱歉，你无权限修改时间",{time:4000});
	}
}







     Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
</script>
<script>
//退出功能
function exitlogin() {
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

/*  function formatDateTime(inputTime) {  
		    var date = new Date(inputTime);
		    var y = date.getFullYear();  
		    var m = date.getMonth() + 1;  
		    m = m < 10 ? ('0' + m) : m;  
		    var d = date.getDate();  
		    d = d < 10 ? ('0' + d) : d;  
		    var h = date.getHours();
		    h = h < 10 ? ('0' + h) : h;
		    var minute = date.getMinutes();
		    var second = date.getSeconds();
		    minute = minute < 10 ? ('0' + minute) : minute;  
		    second = second < 10 ? ('0' + second) : second; 
		    return y + '-' + m + '-' + d;  
		}; */

	/* 	Date.prototype.pattern = function(fmt) {
			var o = {
				"M+" : this.getMonth() + 1, //月份
				"d+" : this.getDate(), //日
				"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
				"H+" : this.getHours(), //小时
				"m+" : this.getMinutes(), //分
				"s+" : this.getSeconds(), //秒
				"q+" : Math.floor((this.getMonth() + 3) / 3), //季度
				"S" : this.getMilliseconds()
			//毫秒
			};
			var week = {
				"0" : "/u65e5",
				"1" : "/u4e00",
				"2" : "/u4e8c",
				"3" : "/u4e09",
				"4" : "/u56db",
				"5" : "/u4e94",
				"6" : "/u516d"
			};
			if (/(y+)/.test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			}
			if (/(E+)/.test(fmt)) {
				fmt = fmt
						.replace(
								RegExp.$1,
								((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
										: "/u5468")
										: "")
										+ week[this.getDay() + ""]);
			}
			for ( var k in o) {
				if (new RegExp("(" + k + ")").test(fmt)) {
					fmt = fmt.replace(RegExp.$1,
							(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
									.substr(("" + o[k]).length)));
				}
			}
			return fmt;
		}  */
</script>
</body>
</html>




