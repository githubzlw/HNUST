<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>项目详情-手机</title>
<link rel="stylesheet" href="${ctx}/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">	
<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/mobiscroll_date.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/progressBar.css" />
<link rel="stylesheet" href="${ctx}/css/ui.css" />
<link rel="stylesheet" href="${ctx}/css/ui.progress-bar.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/add.css" />
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<style>
.special {
	display: block;
}

.pic_file {
	display: block;
	position: relative;
	height: 30px;
	line-height: 30px;
	border: 1px solid transparent;
}

.pic_file input {
	width: 105px;
	display: block;
	position: absolute;
	z-index: 1000;
	overflow: hidden;
	opacity: 0;
}

.pic_file button {
	position: absolute;
	top: 0;
	left: 0;
	width: 105px;
	height: 30px;
	line-height: 30px;
	background-color: #027CFF;
	color: #fff;
	font-size: 16px;
}

.add_p p {
	width: auto;
}

.add_p .w195 {
	width: 50%;
}

.go_right {
	left: unset;
	right: 0.1rem;
	background: url(../img/right.png) no-repeat;
	background-size: contain;
}

.index-mask-two {
	background-color: #fff;
}

.index-mask-two a {
	background-color: #fff;
}

.add_project_detail .add_delay_date {
	margin-bottom: 0;
}

.z-ul {
	position: absolute;
	margin-top: 34px;
	background-color: #fff;
	width: 271px;
	height: auto;
	display: none;
	margin-left: 16px;
}

.z-ul li {
	height: 22px;
	font-size: 13px;
	border: 1px solid #ccc;
	padding-left: 5px;
}

.posirela {
    position: relative;
}
.purchase_detail .tab_bj .selectarror {
    top: 12px;
    right: -24px;
}
.selectarror {
    width: 6px;
    height: 6px;
    background: url(../images/products/selectarror.png) no-repeat;
    position: absolute;
    right: 0;
    top: 0;
}

#processNames {
    display: block;
    white-space: nowrap;
    /* width: 221px; */
    overflow: hidden;
    text-overflow: ellipsis;
    padding-right: 25px;
    background-color: #fff;
}
.sel220 {
    width: 220px;
    height: 28px;
    border-radius: 0;
    font-size: 13px;
    line-height: 28px;
    padding: 0;
    padding-left: 10px;
    color: #666;
    border-color: #ccc;
}

.dx {
    width: 220px;
    overflow-y: scroll;
    height: 150px;
    border: 1px solid #ccc;
    border-top-color: transparent;
    padding-left: 10px;
    position: absolute;
    top: 28px;
    left: 15px;
    background-color: #fff;
    display: none;
    z-index: 200000;
}

.dx input[tyoe=checkbox]{
   position: absolute;
}

.z-over-hide{
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    width: 100%;
}
.pull-right{float:right;}
.add__project_sc .sc{display:inline-block;position:relative;}
.add__project_sc input[type="file"]{position:absolute;top:0;left:0;width: 115px;opacity: 0;}
.add__project_sc .sc_result{position:relative;top:0px;left:-8px;}
.add__project_sc .sc button{width:115px;border-radius:0px;color:#027cff;border-color:#027cff;}
.add__project_sc li{margin-bottom:8px;}
@media screen and (max-width: 768px){
	.add_date {width:auto;}
}

.add_sc .add_date{margin-right:10px;}
.add_date .add_sc td .input-group-addon{margin-right:0;}
.input-group-addon span{line-height:1;}
.add_modigy{margin-top:4px;}
</style>
</head>
<body class="add_project_detail">
<div class="tc_bq" style="display: none">
		<div class="top clearfix">
			<p class="pull-left">已有问题标签 </p>
			<button class="btn bgcolor_ff0 pull-right clo" >关闭</button>
		</div>
		<div class="lis">
			<ul>
			    <c:forEach var="obj" items="${issueList}">
					<li>
						<input type="checkbox"><span>${obj.process}</span>：<span>${obj.issue}</span>
					</li>
				</c:forEach>
			</ul>
		</div>
		<button class="display_block btn bgcolor_ff0 clo clo_add" onclick="add_issue_batch()">添加并关闭窗口</button>
</div>



	<form onsubmit="return false;" method="post"
		enctype="multipart/form-data">
		<div class="modal fade bs-example-modal-sm" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">
							<b class="f16">上传质量分析表/技术协议</b>
						</h4>
						<button type="button" class="btn bgcolor_ff0 pull-right back"
							data-dismiss="modal" aria-label="Close">关闭</button>
					</div>
					<div class="modal-body">
						<input type="hidden" name="projectNo" value="${project.projectNo}">
						<input type="hidden" name="id"
							value="${project.qualityAnalysis.id}"> <input
							type="hidden" name="fileNewName" id="fileNewName"> <input
							type="hidden" name="fileName" id="fileName"> <input
							type="hidden" name="fileType" id="fileType">
						<div style="margin-bottom: 10px;">
						    <span>第一步  更新工艺（已经尝试从报价系统获取数据）</span>
						    <div style="position:relative;" class="add_check">
						    			<input type="text" class="form-control" name="process"/>
						    			<span class="glyphicon glyphicon-triangle-bottom"></span>
						    			<ul id="processList" style="display:none;">
						    			
						    			</ul>					   
							 </div>
							 
				
						</div>	
						<span>第二步  上传</span>
						<p>
							<span>文件名</span> <input type="file" class="display_inline_block pull-right"
								name="file1" onchange="upload_analysis(this)">
						</p>
						<div class="name">
							<label class="display_block"><input type="radio"
								name="analysisType" class="mr5 pull-left" value="1" />质量分析表</label> <label
								class="display_block"><input type="radio"
								name="analysisType" class="mr5 pull-left" value="2" />技术协议</label>
						</div>
						<p>技术分析表请将内部用的和外部用的分开标识清楚，方便采购助理注明在合同上</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default bgcolor_ff0"
							data-dismiss="modal" onclick="update_analysis(this)">上传</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div class="go-back" onclick="goBack()"></div>
	<div class="go-back go_right" onclick="pcUrl()"></div>
	<div id="mask"></div>
	<input type="hidden" id="projectNo" value="${project.projectNo}">
	<input type="hidden" id="roleNo" value="${roleNo}">
	<input type="hidden" id="userId" value="${userId}">
	<input type="hidden" id="userName" value="${userName}">
	<input type="hidden" id="purchaseNameId" value="${purchaseNameId}">
	<input type="hidden" id="pageNumber" value="${pageNumber}">
	<input type="hidden" id="qualityAnalysisId"
		value="${project.qualityAnalysis.id}">
	<div class="detail">
		<div class="detail-tit">项目详情页</div>				
		<div class="detail-middle">
			<!--项目基本信息-->
			<div class="detail-one">
				<div class="detail-top">
					<span>项目基本信息</span>
				</div>
				<p class="p-row">
					<span class="span-left">项目名称：</span><span class="span-right">${project.projectName}</span><span
						class="span-left" style="margin-left: 10px;">项目号：</span><span
						class="span-right">${project.projectNo}</span>
				</p>
				<p>
					<span class="span-right">${project.companyName}</span>			
				</p>
				<c:if test="${project.exportDate != null}">	
					<p>
						<span class="span-left">报关日期：</span><span class="span-right">${project.exportDate}</span>
					</p>
				</c:if>
			</div>
			<!-- 需求汇总 -->
			<div class="detail-one total">
				<div class="detail-top">
					<span>历史项目号</span>
				</div>
				<div>
				    <c:if test="${projectList != null && projectList.size()>1}">
						<c:forEach var="obj" items="${projectList}" varStatus="i">
						   <c:if test="${i.index == projectList.size() - 1}">
							    <c:if test="${obj.projectNo ne project.projectNo}">
							       <a href="/project/showDetails?projectNo=${obj.projectNo}" target="_href">${obj.projectNo}</a>
							    </c:if>
							</c:if>    
						   <c:if test="${i.index != projectList.size() - 1}">
							    <c:if test="${obj.projectNo ne project.projectNo}">
							       <a href="/project/showDetails?projectNo=${obj.projectNo}" target="_href">${obj.projectNo}</a>/
							    </c:if>
							</c:if>    
						</c:forEach>
					</c:if>
				</div>
				<div class="preview"></div>
			</div>
			<c:if test="${productionPlan.uploader!=null && productionPlan.uploader!=''}">
				<div class="detail-one total">
					<div class="detail-top">
						<span>需求汇总</span>
					</div>
					<div>
						<span class="mr10">${productionPlan.uploader}</span><em><fmt:formatDate value="${productionPlan.createDate}" pattern="yyyy-MM-dd"/></em>
						<span>${productionPlan.planDate2String }</span><a href="http://117.144.21.74:33168/upload/zhongwentuzhi/${productionPlan.planNode }" target="_blank">下载</a>
						<button class="btn bgcolor_ff0 pull-right" onclick="showExcel('${productionPlan.planNode}')">预览</button>
					</div>
					<div class="preview" style="display:none;">
					    <iframe id="view" style="height: 500px;" src='' width='100%' height='100%' frameborder='1'>
						</iframe>
					</div>
				</div>
			</c:if>
			
			<!-- 项目列表大图上传 -->
			<form id="file_upload_id" onsubmit="return false;" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="productImg" id="productImg" value="">
				<input type="hidden" name="projectImgNo" id="projectImgNo"
					value="${project.projectNo}">
				<div class="detail-one">
					<div class="detail-top">
						<span class="special">项目列表大图上传<br />(周一开会项目列表会显示,请传高清晰图)
						</span>

						<!-- <span class="special">(周一开会项目列表会显示,请传高清晰图)</span> -->
					</div>
					<div class="pic_file">
						<input type="file" id="file_upload" name="file_upload"
							onchange="doUploadFile(this)">
						<button>上传文件</button>
						<a
							href="http://117.144.21.74:10010/product_img/${project.productImg}"
							id="productImgHtml" style="margin-left: 200px;">${project.productImg}</a>
					</div>
				</div>
			</form>

			<!--项目图纸信息-->
			<div class="detail-one">
				<div class="detail-top">
					<span>图纸信息</span>
				</div>
				<c:forEach var="projectDrawing"
					items="${project.projectDrawingList}">
					<c:if test="${projectDrawing.category eq '1'}">
						<p class="p-row">
							<span class="span-left">图纸文件:</span> <span class="span-right"><a
								href="http://117.144.21.74:33168/upload/tuzhi/${projectDrawing.drawingName}">${projectDrawing.drawingName}</a></span>
						</p>
					</c:if>
					<c:if test="${projectDrawing.category eq '2'}">
						<p class="p-row">
							<span class="span-left">图纸文件:</span> <span class="span-right"><a
								href="http://117.144.21.74:33168/upload/neibubaojia/${projectDrawing.drawingName}">${projectDrawing.drawingName}</a></span>
						</p>
					</c:if>
					<c:if test="${projectDrawing.category eq '3'}">
						<p class="p-row">
							<span class="span-left">图纸文件:</span> <span class="span-right"><a
								href="http://117.144.21.74:33168/upload/duiwaibaojia/${projectDrawing.drawingName}">${projectDrawing.drawingName}</a></span>
						</p>
					</c:if>
					<c:if
						test="${projectDrawing.category eq '4' || projectDrawing.category eq '5'}">
						<p class="p-row">
							<span class="span-left">图纸文件:</span> <span class="span-right"><a
								href="http://117.144.21.74:33168/upload/zhongwentuzhi/${projectDrawing.drawingName}">${projectDrawing.drawingName}</a> &nbsp;&nbsp;${projectDrawing.name }
								<c:if test="${projectDrawing.complaintId!=0 }">
								需验证-
								<c:if test="${projectDrawing.verification!=0 }">
								已验证
								</c:if>
								<c:if test="${projectDrawing.verification==0 }">
								未验证
								</c:if>
								</c:if>
								</span>
						</p>
					</c:if>
				</c:forEach>
			</div>
			<!-- 质量技术协议列表 -->
			<div class="detail-one">

				<div class="detail-top clearfix">
					<span class="display_block pull-left">质量技术协议列表</span>
					<button type="button"
						class="btn btn-default bgcolor_ff0 pull-right" data-toggle="modal"
						data-target=".bs-example-modal-sm">上传质量/技术分析表</button>
				</div>
				<ul>

					<c:if
						test="${project.qualityAnalysis.qualityAnalysisName != null && project.qualityAnalysis.qualityAnalysisName != ''}">
						<li class="clearfix mt10">
							<button class="btn btn-danger pull-left mr5"
								onclick="del_Analysis('${project.qualityAnalysis.id}','1',this)">删除</button>
							<div class="pull-left analysis_li">
								<span class="mr5">质量分析表:</span> <a href="/project_img/${project.projectNo}/analysis/${project.qualityAnalysis.qualityAnalysisNewName}" download="/project_img/${project.projectNo}/analysis/${project.qualityAnalysis.qualityAnalysisNewName}" class="mr5">${project.qualityAnalysis.qualityAnalysisName}</a>
								<span><fmt:formatDate value="${obj.qualityUploadTime}"
										pattern="yyyy-MM-dd" /></span>
							</div>
						</li>
					</c:if>
					<c:if
						test="${project.qualityAnalysis.technologyAnalysisName != null && project.qualityAnalysis.technologyAnalysisName != ''}">
						<li class="clearfix mt10">
							<button class="btn btn-danger pull-left mr5"
								onclick="del_Analysis('${project.qualityAnalysis.id}','2',this)">删除</button>
							<div class="pull-left analysis_li">
								<span class="mr5">技术分析表:</span> <a href="/project_img/${project.projectNo}/analysis/${project.qualityAnalysis.technologyAnalysisNewName}" download="/project_img/${project.projectNo}/analysis/${project.qualityAnalysis.technologyAnalysisNewName}" class="mr5">${project.qualityAnalysis.technologyAnalysisName}</a>
								<span><fmt:formatDate
										value="${project.qualityAnalysis.technologyUploadTime}"
										pattern="yyyy-MM-dd" /></span>
							</div>
						</li>
					</c:if>
				</ul>
<!-- 				<div class="red mt10">传完质量/技术分析表请添加文档中提到可能的问题点标签：</div> -->
	<%-- 			<div class="clearfix row add_label mt10">
					<input type="text" class="form-control pull-left col-xs-5 "
						id="issue" oninput="selectByIssue(this)">
					<ul class="z-ul">

					</ul>
					<button class="btn btn-info pull-right" type="button"
						onclick="add_issue()">添加质量、工艺问题标签</button>
				</div>
				<span class="display_block mt10">请尽量用联想功能输入同样的问题关键词</span> <span
					class="display_block mt10">质量、工艺问题标签：</span>
				<ul>
					<c:forEach var="obj" items="${project.analysisIssueList}"
						varStatus="i">
						<li class="clearfix mt10">
							<div class="pull-left analysis_li  mr5">
								<span class="mr5">${obj.issue}</span> <span>出现过 (<a
									href="/project/selectIssueList?issue=${obj.issue}" class="blue">${obj.occurrenceNum}</a>)次
								</span>
							</div>
							<button class="btn btn-danger pull-right"
								onclick="del_AnalysisIssue('${obj.id}',this)">删除</button>
						</li>
					</c:forEach>
				</ul> --%>
				
			<!-- 项目存在问题点 -->
			<%-- <div class="detail-one project_question ">
				<div class="detail-top clearfix">
					<span class="display_block pull-left">项目可能存在的问题<em
						class="red ml10">请在传完质量/技术 分析表后添加</em></span>
				</div>
				<div class="wrap">
					<div class="wrap1 mt10">
						1.
						<button class="btn bgcolor_ff0 sel_btn" type="button">可从已有问题标签中选择添加</button>
					</div>
					<div class="wrap2 ">
						<div class="wrap2_in1 mt10">
							2. <span>可以新输入一个【工艺：问题】标签，例如：【焊接：虚焊】</span>
							   <span>请先选择工艺（黄色字体为项目技术部预选的工艺）</span>
							<select class="form-control display_inline_block" id="process_list">
								   <option value="">选择工艺</option>
							 </select>
						</div>
						<div class="wrap2_in2 mt10 clearfix">
						    <span>请输入工艺所对应的问题标签，请尽量简短</span>
							<input type="text" class="form-control pull-left" placeholder="5个字以内" id="issue" oninput="selectByIssue(this)">
							<ul class="z-ul">

					        </ul>
							<button class="btn bgcolor_ff0 pull-right" onclick="add_issue()">添加</button>
						</div>

					</div>
					<div class="wrap3">
						<h4 class="mt10">本项目可能出现的【工艺：问题】标签：(如有选择工艺,系统会自动添加)</h4>
						<ul class="list">
						   <c:forEach var="obj" items="${project.analysisIssueList}" varStatus="i">
								<li class="mt10"><span>${obj.process}：</span> <span>${obj.issue}</span> <span>出现过
										(<a href="/project/selectIssueList?issue=${obj.issue}" class="blue"><i>${obj.occurrenceNum}</i></a>)次
								</span>
									<button class="btn del" type="button" onclick="del_AnalysisIssue('${obj.id}',this)">删除</button></li>
							</c:forEach>
						</ul>
					</div>
					<!-- <div class="text-center mt10">
						<button class="btn bgcolor_ff0 tj">提交并关闭</button>
					</div> -->
				</div>
			</div> --%>

           <%--  <div class="line mt10"></div>
				<div class="mt10">1、请技术部审核分析表内容</div>
				<c:if test="${(project.qualityAnalysis.qualityAnalysisName == null || project.qualityAnalysis.qualityAnalysisName == '') && (project.qualityAnalysis.technologyAnalysisName == null || project.qualityAnalysis.technologyAnalysisName == '')}"><div class="mt10" style="color: red;">暂无工艺/问题</div></c:if>
			 --%>
			  <c:if test="${(project.qualityAnalysis.qualityAnalysisName != null && project.qualityAnalysis.qualityAnalysisName != '')}">
				<div class="mt10">2、采购需要对每个【工艺：问题】标签进行分析</div>
				<div class="mt10 clearfix">
					采购回复：
					<c:choose>
						<c:when
							test="${project.qualityAnalysis.purchaseReply != null && project.qualityAnalysis.purchaseReply != ''}">
							<span class="blue" id="purchaseReply">${project.qualityAnalysis.purchaseReply}</span>
						</c:when>
						<c:otherwise>
							<span class="blue" id="purchaseReply">暂无</span>
						</c:otherwise>
					</c:choose>

				</div>
				<div class="mt10 clearfix">
					王工：
					<c:choose>
						<c:when
							test="${project.qualityAnalysis.technicianReply1 != null && project.qualityAnalysis.technicianReply1 != ''}">
							<span class="blue" id="technicianReply1">${project.qualityAnalysis.technicianReply1}</span>
						</c:when>
						<c:otherwise>
							<span class="blue" id="technicianReply1">暂无</span>
						</c:otherwise>
					</c:choose>

				</div>
				<div class="mt10 clearfix">
					技术总工：
					<c:choose>
						<c:when
							test="${project.qualityAnalysis.technicianReply2 != null && project.qualityAnalysis.technicianReply2 != ''}">
							<span class="blue">${project.qualityAnalysis.technicianReply2}</span>
						</c:when>
						<c:otherwise>
							<span class="blue">暂无</span>
						</c:otherwise>
					</c:choose>
				</div>
				<textarea class="form-control" id="issueReply" <c:if test="${userName==null || userName ==''}">placeholder="您还未登录，请先点击“回复”按钮登录"</c:if>></textarea>
				<button class="btn bgcolor_ff0 reply_btn pull-right mt10" onclick="reply('${project.qualityAnalysis.id}','${userName}','${roleNo}')">回复</button>
			</c:if>
			</div>
          
			<!--投诉历史-->
			<div class="detail-one complaint">
				<c:if test="${complaints.size()>0}">
					<div class="detail-top">
						<span>客户投诉历史</span>
					</div>
					<c:forEach var="obj" items="${complaints}" varStatus="i">
					    <em class="z-over-hide">${obj.complaintContent}</em>
						<em>投诉日期:<i><fmt:formatDate value="${obj.createTime}"
									pattern="yyyy-MM-dd" /></i></em>
						<em>最终整改完成日期<c:choose>
								<c:when
									test="${obj.completeTime != null && obj.completeTime != ''}">
									<em>已完成</em>
								</c:when>
								<c:otherwise>
									<em style="color:red;">还没有</em>
								</c:otherwise>
							</c:choose>
						</em>
				<%-- 		<em class="ml10 mr10"> <c:choose>
								<c:when
									test="${obj.purchaseConfirmDate != null && obj.purchaseConfirmDate != ''}">
									<em>采购已确认</em>
								</c:when>
								<c:otherwise>
									<em>采购未确认</em>
								</c:otherwise>
							</c:choose>
						</em>	 --%>			
						<br>
						<a onclick="window.location='https://www.kuaizhizao.cn/complaint/queryComplaint?id=${obj.id}'">点我到投诉详情页</a>
					    <br>
					</c:forEach>

				</c:if>
			</div>
			
			<!-- 质检报告 -->
			<div class="detail-one quality_test clearfix">
				<div class="detail-top clearfix">
					<span class="pull-left">质检报告</span>
					<!-- <button id="sendButton" class="sc_bt btn bgcolor_ff0 pull-right">质检报告上传</button> -->
				</div>
				<div>
					<div>请先选择质检任务:(注意如果零件很多，没传完前请不要点完成)</div>
					<table class="add_sc">
						<tbody>
							<c:forEach var="task" items="${project.projectTaskList}">
								<c:if
									test="${task.taskType eq '2' && (task.accepter == userName || userName == 'ninazhao') && task.taskStatus eq '0'}">
									<tr>
										<td><span><fmt:formatDate
													value="${task.startTime}" pattern="yyyy-MM-dd" /><span
												class="ml10">${task.checkType}</span></span><span>${task.description}</span><span></span>
											<div>检验地址应该是：${task.checkPlace}</div>
											<div>开箱比例：${task.openRate==null?(task.checkType != '出货' ? '非大货': ''):(task.openRate)}${task.openRate!=null?'%':''}</div>
											<div>工厂实际完成时间选择:<c:if test="${task.newDate==null }">
											<div class="clearfix">
											<div class="input-group date form_date col-sm-6 pull-left" data-date="" data-date-format="yyyy-mm-dd">
											
												<div class="add_date">
													<div class="input-group date form_date" data-date=""
														data-date-format="yyyy-mm-dd">
														<input id="sampleScheduledDate" name="sampleScheduledDate"
															class="form-control brt brt_7 add_input" size="16" type="text"
															value="<fmt:formatDate value="${project.sampleScheduledDate}" pattern="yyyy-MM-dd"/>"
															place="选择日期" placeholder="选择日期" requiredate
															style="width: 161px;"><span class="input-group-addon"><span
															class="glyphicon glyphicon-calendar"></span></span>
													</div>
													<span class="add_span"></span>
												</div>
												
												
												
											</div>
											<input type="button" class="btn bgcolor_ff0 pull-left add_btn add_modigy" value="修改" onclick="updateNewDate(${task.id});">
											
										</div>	
											<span>(工厂生产晚了不能及时检验时候请点选)</span>
											
											</c:if>
											<c:if test="${task.newDate!=null }"><fmt:formatDate value="${task.newDate}" pattern="yyyy-MM-dd"/></c:if>
											</div>
											</td>

										<td><button class="btn bgcolor_ff0 mr10 sendButton"
												id="sendButton"
												onclick="window.location='${ctx}/quality/addQuality?projectNo=${task.projectNo}&userName=${userName}&checkType=${task.checkType}&openRate=${task.openRate}&projectNoId=${task.projectNoId}'">上传</button>
											<button class="btn bgcolor_ff0"
												onclick="complete_task('${task.id}',1)">完成</button></td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>

				</div>
				<table class="mt10">
					<c:forEach var="qualityReport" items="${project.qrList}">
						<tr>
							<td class="td1"><div class="s_name">${qualityReport.detailView}</div></td>
							<td class="td3"><button
									onclick="deleteQuality(${qualityReport.id})"
									class="btn btn-default "
									style="background-color: #d9534f; color: #fff;">删除</button></td>
						</tr>
						<tr>
							<td colspan="2">
								<button onclick="viewDetailQuality(${qualityReport.id})"
									class="btn btn-default">编辑</button>
								<button class="btn btn-default"
									onclick="window.location='https://www.kuaizhizao.cn/quality/shareQuality?id=${qualityReport.eid}'">内部分享</button>
								<button class="btn btn-default"
									onclick="window.location='https://www.kuaizhizao.cn/quality/shareQualityEn?id=${qualityReport.eid}'">外部分享</button>
								<c:if test="${(qualityReport.type==3||qualityReport.type==2)&& qualityReport.status==2}"><c:if test="${qualityReport.processInstanceId==null }"><button class="btn btn-default" 
									onclick="sendDataToDingTalk('${qualityReport.dingTalkId }',${qualityReport.id},'${productionPlan.planNode}');">发送到钉钉审批(未发送)</button>
								</c:if>
								<c:if test="${qualityReport.processInstanceId!=null }">
								<c:if test="${qualityReport.dingdingStatus=='CANCELED' }">
								<button class="btn btn-default" 
									onclick="sendDataToDingTalk('${qualityReport.dingTalkId }',${qualityReport.id},'${productionPlan.planNode}');">钉钉审批已取消(可再次发送)</button>
								</c:if>
								<c:if test="${qualityReport.dingdingStatus=='COMPLETED' && qualityReport.dingdingResult=='agree' }">
								钉钉审批(已通过)
								</c:if>
								<c:if test="${qualityReport.dingdingStatus=='COMPLETED' && qualityReport.dingdingResult=='refuse' }">
							          钉钉审批(未通过)
								</c:if>
								<c:if test="${qualityReport.dingdingStatus!='COMPLETED' && qualityReport.dingdingStatus!='CANCELED' }">
								钉钉审批(进行中)
								</c:if>
								</c:if></c:if>
								<c:if
									test="${qualityReport.type == 0 && (qualityReport.meetingNo == null || qualityReport.meetingNo == '')}">
									<button
										onclick="window.location='/meetingRecord/inputMeetingRecord?roleNo=${roleNo}&userId=${userId}&userName=${userName}&projectNo=${qualityReport.projectNo}&type=0&reportId=${qualityReport.id}'"
										class="btn btn-default red" style="color: red;">上传样品分析会</button>
								</c:if> <c:if
									test="${qualityReport.type == 0 && qualityReport.meetingNo != null && qualityReport.meetingNo != ''}">
									<span>已上传样品分析会</span>
								</c:if> <c:if
									test="${qualityReport.type == 2 && (qualityReport.meetingNo == null || qualityReport.meetingNo == '') && (qualityReport.status == 1 || qualityReport.status == 2 || project.projectAmount > 0.5)}">
									<button
										onclick="window.location='/meetingRecord/inputMeetingRecord?roleNo=${roleNo}&userId=${userId}&userName=${userName}&projectNo=${qualityReport.projectNo}&type=23&reportId=${qualityReport.id}'"
										class="btn btn-default red" style="color: red;">上传大货分析会</button>
								</c:if> <c:if
									test="${qualityReport.type == 2 && qualityReport.meetingNo != null && qualityReport.meetingNo != ''}">
									<span>已上传大货分析会</span>
								</c:if> <c:if
									test="${qualityReport.type == 3 && (qualityReport.meetingNo == null || qualityReport.meetingNo == '') && (qualityReport.status == 1 || qualityReport.status == 2 || project.projectAmount > 0.5)}">
									<button
										onclick="window.location='/meetingRecord/inputMeetingRecord?roleNo=${roleNo}&userId=${userId}&userName=${userName}&projectNo=${qualityReport.projectNo}&type=23&reportId=${qualityReport.id}'"
										class="btn btn-default red" style="color: red;">上传大货分析会</button>
								</c:if> <c:if
									test="${qualityReport.type == 3 && (qualityReport.meetingNo == null || qualityReport.meetingNo == '') && isNewProject == true && qualityReport.status == 0}">
									<button
										onclick="window.location='/meetingRecord/inputMeetingRecord?roleNo=${roleNo}&userId=${userId}&userName=${userName}&projectNo=${qualityReport.projectNo}&type=23&reportId=${qualityReport.id}'"
										class="btn btn-default red" style="color: red;">上传大货分析会</button>
								</c:if> <c:if 
									test="${qualityReport.type == 3 && qualityReport.meetingNo != null && qualityReport.meetingNo != ''}">
									<span>已上传大货分析会</span>
								</c:if>
							</td>
						</tr>

					</c:forEach>
				</table>
			</div>
			<!-- 		<div class="detail-one upload">
					<p class="detail-top"><span>会议上传</span></p>
					<div class="clearfix words ">
						<span class="pull-left mt10">本项目是新项目，还未上传大货会    本项目是新项目，还未上传大货会</span>
						<button class="pull-right btn btn-default mt10">点我上传大货会
							<input type="file"/>
						</button>
					</div>
				</div> -->
			<div class="detail-one add_video" style="display: block;">
				<p class="detail-top">
					<span class="pull-left">视频观看</span> <a
						class="pull-right position_relative video_sc btn" target="_blank"
						href="<c:url value='/uploadVideo/toUploadVideo'>
						<c:param name='projectNo' value='${project.projectNo}'></c:param>
						<c:param name='userName' value='${userName}'></c:param>
						<c:param name='userId' value='${userId}'></c:param>
						<c:param name='roleNo' value='${roleNo}'></c:param>
						</c:url>">视频上传</a>
				</p>
				<c:forEach var="obj" items="${videos}">
					<div class="video_div text-center">
						<video controls> <c:set
							value="${ fn:split(obj.qualityVideoName,'.') }" var="names" /> <c:forEach
							items="${names}" var="name" varStatus="i">
							<c:if test="${i.index == 1 and name != 'mp4'}">
								<source
									src="/project_img/${project.projectNo}/video/convert/${obj.videoFile}"
									type="video/mp4" />
							</c:if>
							<c:if test="${i.index == 1 and name eq 'mp4'}">
								<source
									src="/project_img/${project.projectNo}/video/${obj.videoFile}"
									type="video/mp4" />
							</c:if>
						</c:forEach> </video>
					</div>
					<div>

						<span class="pull-left">${obj.remark}</span>
						<button class="pull-right btn del" onclick="del('${obj.id}',this)">删除</button>
						<span class="pull-right mr10">上传日期：<fmt:formatDate
								value="${obj.uploadTime}" pattern="yyyy-MM-dd" /></span>
					</div>
					<a class="btn btn-default bgcolor_ff0"
						href="http://www.kuaizhizao.cn/quality/queryByVideoId?id=${obj.id}&taskId=${obj.taskId}">查看视频</a>
					<a class="btn btn-default share_wechat bgcolor_ff0"
						onclick="toShare('${obj.id}','${obj.videoType}','${obj.factoryName}','<fmt:formatDate value="${obj.uploadTime}" pattern="yyyy-MM-dd"/>','${obj.uploader}','${obj.taskId}')">可点击本按钮分享到微信群(需使用QQ浏览器)</a>
				</c:forEach>

			</div>
			<div class="detail-one big_good">
				<div class="detail-top">
					<span class="pull-left">工厂大货进度</span>
				</div>
				<c:if test="${factoryList==null||factoryList==''}">
					<span class="text-center display_block">尚未有生产合同给到工厂</span>
				</c:if>
				<c:forEach var="obj" items="${factoryList}">
					<a class="btn btn-default " target="_blank"
						href="https://www.kuaizhizao.cn/report/reportListPurchase?csgOrderId=${obj.projectNo}&supplierId=${obj.factoryId}&factoryId=0&realName=${userName}"
						target="_blank"> ${obj.factoryName}<i></i></a>
				</c:forEach>
			</div>
			<div class="detail-one">
				<div class="detail-top">
					<span class="pull-left">检验计划</span>
				</div>
				<c:forEach var="inspectionPlan"
					items="${project.inspectionPlanList}">
					<p class="p-row">
						<span class="span-left"></span> <span class="span-right"><a
							href="http://117.144.21.74:33168/upload/po/upload/JIANYANJIHUAZJ/${inspectionPlan.reportName}">${inspectionPlan.reportName}</a></span>
					</p>
				</c:forEach>
			</div>
			<div class="detail-one">
				<div class="detail-top">
					<span class="pull-left">最新版检验计划内容速览</span>
				</div>
					 <table style="width: 100%;">
				        <tbody id="check_body">
					        <c:forEach var="obj" items="${plans}" varStatus="i">
					           <tr class="check check_${i.index}" filed_date = "<fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd" />">
					              <td>
	                                <span>${obj.productComponent}</span>	
	                                <p style="float: none;width: auto;display: block;">要求完成点：<button class="check_btn" type="button" onclick="openInspection(this,${i.index})">请展开选择</button></p>			              
					              </td>				        			        
					           </tr>
					            <c:forEach var="plan" items="${planList}" varStatus="j">
					                 <c:if test="${obj.productComponent == plan.productComponent}">
					                  <tr class="check_s check_index_${i.index}" style="display:none;" filed="${plan.id}">
						                 <td>
						                   <span>${plan.type}:${plan.productStandards}</span>	
						                 </td>					                
					                  </tr>
					                 </c:if>				                 
					            </c:forEach>
				            </c:forEach>	           				        
				        </tbody>
				    </table>
			</div>

			<!--项目交期-->
			<div class="detail-one">
				<div class="detail-top">
					<span class="pull-left">项目交期</span>
				</div>
				<div class="date-reason original-date add_p">
					<c:if test="${project.poDate!=null}"><p class="w195">
					<span class="span-left">PO日期：</span><span class="span-right"><fmt:formatDate
								value="${project.poDate}" pattern="yyyy-MM-dd" /></span>
					</p></c:if>
					<c:if test="${project.actualStartDate!=null}"><p>
						<span class="span-left">开工日期：</span><span class="span-right"><fmt:formatDate
								value="${project.actualStartDate}" pattern="yyyy-MM-dd" /></span>
					</p></c:if>
					<c:if test="${project.sampleScheduledDate!=null}"><p class="w195">
						<span class="span-left">样品交期：</span><span class="span-right"><fmt:formatDate
								value="${project.sampleScheduledDate}" pattern="yyyy-MM-dd" /></span>
					</p></c:if>
					<c:if test="${project.deliveryDate!=null && project.sampleScheduledDate==null}"><p class="w195">
						<span class="span-left">大货交期：</span><span class="span-right"><fmt:formatDate
								value="${project.deliveryDate}" pattern="yyyy-MM-dd" /></span>
					</p></c:if>
					<c:if test="${project.deliveryDate!=null && project.sampleScheduledDate!=null}"><p >
						<span class="span-left">大货交期：</span><span class="span-right"><fmt:formatDate
								value="${project.deliveryDate}" pattern="yyyy-MM-dd" /></span>
					</p></c:if>
				</div>
				<c:forEach var="delay" items="${project.delayList}" varStatus="i">
					<div class="date-reason">
						<p>
							<span class="span-left">第${delay.num}批大货预计交期：</span><span
								class="span-right "><fmt:formatDate
									value="${delay.delayDate}" pattern="yyyy-MM-dd" /></span>
						</p>
						<p class="p-row">
							<span>延期原因：</span><span>${delay.internalCause}${delay.externalCause}</span>
						</p>
					</div>
				</c:forEach>
			</div>
			<!-- 大货时间 -->
			<div class="detail-one">
				<div class="detail-top">
					<span class="pull-left">大货交期</span>
				</div>
				<c:forEach var="obj" items="${projectSchedules}" varStatus="i">
					<div class="date-reason">
						<p style="width: 60%">
							<span class="span-left">第${i.index+1}批大货预计交期：</span><span
								class="span-right "><fmt:formatDate
									value="${obj.predictDate}" pattern="yyyy-MM-dd" /></span>
						</p>
						<c:if test="${obj.isFinish == 1}">
							<p style="width: 60%; color: #03bd00">
								<span class="span-left">第${i.index+1}批大货实际时间：</span><span
									class="span-right "><fmt:formatDate
										value="${obj.actualDate}" pattern="yyyy-MM-dd" /></span>
							</p>
						</c:if>
					</div>
				</c:forEach>
			</div>

			<!--生产计划-->
			<%-- <div class="detail-one"> 
 					<p class="detail-top">生产计划</p> 
 					<div class="table-container"> 
 						<table class="table"> 
 							<tr> 
 								<th>节点</th> 
								<th>日期</th> 
 							</tr> 
 						    <c:forEach var="plan" items="${project.planList}"> 
							   <tr> 
 								 <td>${plan.planNode}</td> 
 								 <td><fmt:formatDate value="${plan.planDate}" pattern="yyyy-MM-dd"/></td> 
 							  </tr> 
 							</c:forEach> 
						</table> 
 					</div> 
 				</div>  --%>
			<!--项目汇报-->
			<div class="detail-one">
				<div class="detail-top">
					<span class="pull-left">项目汇报</span>
				</div>
				<c:forEach var="report" items="${project.reportList}">
					<div class="date-reason">
						<p class="p-row">
							<span class="span-left">汇&nbsp;报 &nbsp;人：</span><span
								class="span-right ">${report.reportName}</span><span
								class="span-left" style="margin-left: 30px;">汇&nbsp;报时间：</span><span
								class="span-right "><fmt:formatDate
									value="${report.createDate}" pattern="yyyy-MM-dd" /></span>
						</p>
						<p class="p-row">
							<span class="span-left">项目进展：</span><span>${report.report}</span>
						</p>
						<p class="p-row">
							<span class="span-left">项目阶段：</span><span>${report.projectStageView}</span>
						</p>
						<p class="p-row">
							<span class="span-left">汇报文件：</span> <a href="#"
								onclick="openFile('http://117.144.21.74:10010/uploadimg/${report.picUrl}')"><span>${report.picUrl}</span></a>
						</p>
						<!--<a href="#" onclick="openFile('http://localhost:8080/uploadimg/${report.picUrl}')"><span>${report.picUrl}</span></a></p>-->
					</div>
				</c:forEach>
			</div>
			<!--任务汇报-->
			<div class="detail-one">
				<div class="detail-top">
					<span class="pull-left">任务汇报</span>
				</div>
				<c:forEach var="task" items="${project.taskList}">
					<div class="date-reason sbb">
						<a
							href="${ctx}/task/taskDetails?projectNo=${task.projectNo}&id=${task.id}&roleNo=${roleNo}&userId=${userId}&userName=${userName}">
							<div class="div-top">
								<p class="p-row">
									<span class="span-left">部&nbsp;署 &nbsp;人：</span><span
										class="span-right ">${task.operator}</span><span
										class="span-left" style="margin-left: 30px;">部署时间：</span><span
										class="span-right "><fmt:formatDate
											value="${task.createDate}" pattern="yyyy-MM-dd" /></span>
								</p>
								<p class="p-row">
									<span class="span-left">任务状态段：</span><span class="span-right ">
										${task.status==0?'未完成':'' }${task.status==1?'已完成':''} </span><span
										class="span-left" style="margin-left: 30px;">截至时间：</span><span
										class="span-right "><fmt:formatDate
											value="${task.endDate}" pattern="yyyy-MM-dd" /></span>
								</p>
								<p class="p-row">
									<span class="span-left">任务节点：</span><span class="span-right ">${task.node==0?'无':'' }${task.node==1?'样品交货':''}${task.node==2?'大货交货':''}</span>
								</p>
								<p class="p-row">
									<span class="span-left">任务需求：</span><span>${task.taskDemand}</span>
								</p>
							</div>
						</a>
						<c:forEach var="taskReport" items="${task.taskReportList}">
							<div class="div-list">
								<p class="p-row">
									<span class="span-left">汇报人：</span><span class="span-right">${taskReport.reportName}</span>
									<span class="span-left" style="margin-left: 30px;">汇报时间：</span><span
										class="span-right"><fmt:formatDate
											value="${taskReport.createDate}" pattern="yyyy-MM-dd" /></span>
								</p>
								<p class="p-row">
									<span class="span-left">汇报文件：</span> <a href="#"
										onclick="openFile('http://117.144.21.74:10010/uploadimg/${taskReport.picUrl}')"><span>${taskReport.picUrl}</span></a>
								</p>
								<p class="p-row">
									<span class="span-left">任务进展：</span><span>${taskReport.taskReport}</span>
								</p>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>

			<!--销售录入状态信息-->
			<div class="detail-one">
				<div class="detail-top">
					<span class="pull-left">销售录入状态信息</span>
				</div>
				<c:forEach var="statusEnter" items="${project.statusEnterList}">
					<div class="date-reason"
						style="border: 1px solid deepskyblue; box-sizing: border-box; border-radius: 4px;">
						<p class="p-row">
							<span class="span-left">项&nbsp;目 &nbsp;号：</span><span
								class="span-right ">${statusEnter.projectNo}</span><span
								class="span-left" style="margin-left: 30px;">消息时间：</span><span
								class="span-right "><fmt:formatDate
									value="${statusEnter.createDate}" pattern="yyyy-MM-dd" /></span>
						</p>
						<p class="p-row">
							<span class="span-left">前期生产中的沟通：</span>
							<c:forEach var="statusType" items="${statusEnter.statusTypeList}">
								<c:if test="${statusType eq '1'}">
									<span class="span-right ">需求变更或细化–和图纸相关</span>
								</c:if>
								<c:if test="${statusType eq '2'}">
									<span class="span-right ">需求变更或细化–其他</span>
								</c:if>
								<c:if test="${statusType eq '3'}">
									<span class="span-right ">交期或者价格相关</span>
								</c:if>
							</c:forEach>
						</p>
						<p class="p-row">
							<span class="span-left">收货后的反馈：</span> <span class="span-right ">
								<c:if test="${statusEnter.feedback eq '1'}">有问题</c:if> <c:if
									test="${statusEnter.feedback eq '2'}">客户开心</c:if>
							</span>
						</p>
						<c:if test="${statusEnter.type eq '0'}">
							<p class="p-row">
								<span class="span-left">上传文件：</span><a href="#"
									onclick="openFile('http://117.144.21.74:10010/uploadimg/${statusEnter.fileUrl}')"><span>${statusEnter.fileUrl}</span></a>
							</p>
						</c:if>
						<c:if test="${statusEnter.type eq '1'}">
							<p class="p-row">
								<span class="span-left">上传文件：</span><a href="#"
									onclick="openFile('http://117.144.21.74:43900/NBEmail/download3?filename=${statusEnter.fileUrl}')"><span>${statusEnter.fileUrl}</span></a>
							</p>
						</c:if>
						<p class="p-row">
							<span class="span-left">细节：</span><span class="span-right">${statusEnter.details}</span>
						</p>
					</div>
				</c:forEach>
			</div>

			<!-- 项目金额 -->
			<div class="detail-one price_">
				<lable class="col-xs-1">项目金额</lable>
				<input id='projectAmout' type="text" placeholder="请尽快填写项目金额"
					value="${project.projectAmount}" />万美元
				<button id="editProjectAmout">修改</button>
			</div>

			<!--项目留言-->
			<%-- <div class="detail-one">
				<div class="detail-top">
					<span class="pull-left">项目留言</span>
				</div>
				<c:forEach var="comment" items="${project.commentList}">
					<div class="date-reason">
						<p class="p-row">
							<span class="span-left">留&nbsp;言 &nbsp;人：</span><span
								class="span-right ">${comment.reviewer}</span><span
								class="span-left" style="margin-left: 30px;">留言时间：</span><span
								class="span-right "><fmt:formatDate
									value="${comment.createDate}" pattern="yyyy-MM-dd" /></span>
						</p>
						<p class="p-row">
							<span class="span-left">留言内容：</span><span class="span-right ">${comment.comment}</span>
						</p>
					</div>
				</c:forEach>
			</div> --%>
		</div>
		<div class="index-footer">
			<!-- <span class="footer-list-one">项目交期</span> --> <span
				class="footer-list-two">项目状态</span> <!-- <span class="footer-list-three"
				id="taskAllocation">任务分配</span> -->
		</div>
	</div>
	<!--项目交期弹出-->
	<div class="index-mask-one">
		<a
			href="${ctx}/jsp/project_date_set.jsp?projectNo=${project.projectNo}&contractDays=${project.contractDays}&userName=${userName}&roleNo=${roleNo}&userId=${userId}&scheduledDays=${project.scheduledDays}"
			class="fact-date">日期设置</a>
		<%-- <a href="${ctx}/project/editDetails?projectNo=${project.projectNo}&roleNo=${roleNo}&userName=${userName}&userId=${userId}" class="fact-date">分批交货设置</a>
			<a href="${ctx}/project/getDelay?projectNo=${project.projectNo}&roleNo=${roleNo}&userName=${userName}&userId=${userId}" class="footer-margin delay-date add_delay_date">延长交期</a> --%>
		<a href="javascript:;" class="footer-cancel">取消</a>
	</div>
	<!--项目状态-->
	<div class="index-mask-two">
		<c:if
			test="${roleNo==5 || roleNo == 1020 || roleNo==100 || roleNo==99 && project.projectStatus != 5}">
			<a
				href="${ctx}/jsp/project_pause.jsp?projectNo=${project.projectNo}&roleNo=${roleNo}&userName=${userName}&userId=${userId}&projectStatus=${project.projectStatus}">状态设置</a>
		</c:if>
		<a
			href="${ctx}/jsp/send-img.jsp?projectNo=${project.projectNo}&roleNo=${roleNo}&userName=${userName}&userId=${userId}">项目汇报</a>

		<%-- <a
			href="${ctx}/jsp/comment.jsp?projectNo=${project.projectNo}&roleNo=${roleNo}&userName=${userName}&userId=${userId}"
			class="finish-say">项目留言</a> --%> <a href="javascript:;"
			class="footer-cancel">取消</a>
	</div>
	<div id="click-big">
		<img src="../img/2.jpg" />
	</div>
	<!-- 进度上传条 -->
	<div class="w-out-box" id="show_upload_dialog" style="display: none;">
		<div class="weui_mask"></div>
		<div class="w-weui_dialog">
			<div id="container">

				<div class="content">
					<h1>上传进度</h1>
				</div>

				<!-- Progress bar -->
				<div id="progress_bar" class="ui-progress-bar ui-container">
					<div class="ui-progress" style="width: 0%;" id="ui-progress-upload">
						<span class="ui-label" style="display: none;">正在加载...<b
							class="value">0%</b></span>
					</div>
				</div>
				<!-- /Progress bar -->
				<a class="close-reveal-modal"
					style="color: #fff; font-size: 30px; position: absolute; right: 10px; top: 10px;"
					href="javascript:void(0);" onclick="cancel_upload()">×</a>
				<div class="content" id="main_content" style="display: none;">
					<p>加载完成。</p>
				</div>
			</div>
		</div>
	</div>
	<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
		charset="utf-8"></script>

	<script src="../js/mobiscroll_date.js" type="text/javascript"
		charset="utf-8"></script>
	<script type="text/javascript"
		src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="../layer/2.1/layer.js" type="text/javascript"
		charset="utf-8"></script>
	<script type="text/javascript" src="/js/upload-base.js"></script>
	<script type="text/javascript" src="../js/jquery-form.js"></script>
	<script type="text/javascript" src="../quality/shareWechat.js"></script>
	<script type="text/javascript" src="../js/easydialog.min.js"></script>
	<script type="text/javascript" src="../js/process_zh.js"></script>
	<script type="text/javascript">


        //项目号
		var projectNo = '${project.projectNo}';
		//产品照片
		var productImg = '${project.productImg}';
		var title ='';
		var desc='';
		var img = '';
		var a = '';

			$(function () {
				var currYear = (new Date()).getFullYear();	
				var opt={};
				opt.date = {preset : 'date'};
				opt.datetime = {preset : 'datetime'};
				opt.time = {preset : 'time'};
				opt.default = {
					theme: 'android-ics light', //皮肤样式
					display: 'modal', //显示方式 
					mode: 'scroller', //日期选择模式
					dateFormat: 'yyyy-mm-dd',
					lang: 'zh',
					showNow: true,
					nowText: "今天",
					startYear: currYear - 1, //开始年份
					endYear: currYear + 50 //结束年份
				};
				$(".date-time").mobiscroll($.extend(opt['date'], opt['default']));
				
				
				
				
				//工艺列表
				var options = addProcess();
				var options_input = addProcess_input();
				$("#process_list").append(options);
				$("#processList").append(options_input);
			});

      //QQ浏览器分享
      function toShare(id,videoType,factoryName,uploadDate,uploader,taskId){
        var shareInfo = '';
        if(taskId){
        	  a = 'http://www.kuaizhizao.cn/quality/queryByVideoId?id='+id+'&taskId='+taskId; 
        }else{
        	  a = 'http://www.kuaizhizao.cn/quality/queryByVideoId?id='+id; 
        }
         

        if(videoType == 3){
				$.ajax({
					    type:"post",                   
					    url:"${ctx}/projectTask/selectByTaskId",           
					    data:{
					    	 id:taskId
					    	 },              
					    success:function(json){  
// 					      var json = eval("(" + data +")");
						  if(json.ok){
							  var task = json.data;
	                          if(task){
	                             title = projectNo + "任务视频";
	                             desc = task.description;
	                             shareInfo = {'url':a,'title':title,'desc':desc,'type':2,'img':img};
	                             shareChat(shareInfo);
	                          }	                      	 
						  }else{
							 layer.msg(json.message,{time:2000});
						  }   
				     }
			     });  
        }else if(videoType == 2){
               title = projectNo + "质检视频";    
               desc =  uploadDate+"_"+ uploader;         
               shareInfo = {'url':a,'title':title,'desc':desc,'type':2,'img':img};
               shareChat(shareInfo);
        }else if(videoType == 1){
               title = projectNo + "验厂视频";    
               desc = factoryName + "/r/n" + uploadDate+"_"+ uploader;         
               shareInfo = {'url':a,'title':title,'desc':desc,'type':2,'img':img};
               shareChat(shareInfo);
        }
          
      }



	    //微信分享
	   function shareChat(shareInfo){
		   qqShare(shareInfo);
	   }  


		</script>
	<script type="text/javascript">
			//底部导航弹出处理
			$('.footer-list-one').click(function(){
				var roleNo=$("#roleNo").val();
				var userName=$("#userName").val();
// 				if(roleNo==6 || userName=='ArnoZhou'){
					$('#mask').show(100);
					$('.index-mask-one').show(300);
// 				}else{
// 					layer.msg("没有权限操作",{time:2000});
// 					return false;
// 				}
				
			});
			$('#mask').click(function(){
				$('#mask').hide(200);
				$('.index-mask-one').hide(200);
			})
			$('.footer-cancel').click(function(){
				$('#mask').hide(200);
				$('.index-mask-one').hide(200);
			})
			$('.footer-list-two').click(function(){
				var roleNo=$("#roleNo").val();
				$('#mask').show(100);
				$('.index-mask-two').show(300);
			});
			$('#mask').click(function(){
				$('#mask').hide(200);
				$('.index-mask-two').hide(200);
			})
			$('.footer-cancel').click(function(){
				$('#mask').hide(200);
				$('.index-mask-two').hide(200);
			})
		  //大货完成交货
		  $(document).on('click','.finish-task',function(){
				layer.open({
					type:1,
					skin:'finish-btn',
					title:'大货完成交货',
					anim:4,
					shade:0.6,
					shadeClose:true,
					btn:['确定','取消'],
				})	
				$('.finish-btn .layui-layer-btn0').click(function(){
					var projectNo=$("#projectNo").val();
					var type='1';
					$.ajax({
					    type:"post",                   
					    url:"${ctx}/project/updateProjectStatus",           
					    data:{
					    	  projectNo:projectNo,type:type
					    	 },              
					    success:function(json){  
// 					      var json = eval("(" + data +")");
						  if(json.ok){
							 layer.msg(json.message,{time:2000});
						  }else{
							 layer.msg(json.message,{time:2000});
						  }   
					   }
				 });  
				 $('#mask').hide();
				 $('.index-mask-two').hide();
		 		})
		  }) 
		  
		  
		  
		  //样品完成交货
// 		  $(document).on('click','.sample-finish-task',function(){
// 				layer.open({
// 					type:1,
// 					skin:'finish-btn',
// 					title:'样品完成交货',
// 					anim:4,
// 					shade:0.6,
// 					shadeClose:true,
// 					btn:['确定','取消'],
// 				})	
// 				$('.finish-btn .layui-layer-btn0').click(function(){
// 					var projectNo=$("#projectNo").val();
// 					var type="2";
// 					$.ajax({
// 					    type:"post",                   
// 					    url:"${ctx}/project/updateProjectStatus",           
// 					    data:{
// 					    	  projectNo:projectNo,type:type
// 					    	 },              
// 					    success:function(data){  
// 					      var json = eval("(" + data +")");
// 						  if(json.ok){
// 							 layer.msg(json.message,{time:2000});
// 						  }else{
// 							 layer.msg(json.message,{time:2000});
// 						  }   
// 					   }
// 				 });  
// 				 $('#mask').hide();
// 				 $('.index-mask-two').hide();
// 		 		})
// 		  }) 
		  
		  $("#taskAllocation").click(function(){
			var roleNo=$("#roleNo").val();
			var projectNo=$("#projectNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			window.location.href="${ctx}/jsp/task_allocation.jsp?roleNo="+roleNo+"&projectNo="+projectNo+"&userId="+userId+"&userName="+encodeURI(encodeURI(userName));
		  })
		 //一键返回列表页 
		 function goBack(){
		    var pageNumber=$("#pageNumber").val();
			var roleNo=$("#roleNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			var purchaseNameId=$("#purchaseNameId").val();
			window.location.href="${ctx}/jsp/project_list_m.jsp?userId="+userId+"&roleNo="+roleNo+"&purchaseNameId="+purchaseNameId+"&userName="+encodeURI(encodeURI(userName))+"&pageNumber="+pageNumber;	
		 }
		function pcUrl(){		
	        var roleNo = $('#roleNo').val()			
			var um = $("#userName").val();
	        var ui = $("#userId").val();
	        var pageNumber=$("#pageNumber").val();
	    	window.location.href = "${ctx}/project/showListNew?userName="+um + "&userId=" + ui+ "&roleNo=" + roleNo+"&pageNumber="+pageNumber;
		}
			
		function openFile(url){
		   window.location.href=encodeURI(url); 
		}
		
		$('#editProjectAmout').click(function(){
			var index = layer.open({
				type:1,
				skin:'finish-btn',
				title:'确认修改？',
				anim:4,
				shade:0.6,
				shadeClose:true,
				btn:['确定','取消'],
				yes:function(){
					var projectNo=$("#projectNo").val();
					var projectAmout = $('#projectAmout').val()
					
					$.ajax({
					    type:"post",                   
					    url:"${ctx}/project/editAmout",           
					    data:{
					    	  projectNo:projectNo,
					    	  projectAmout:projectAmout
					    	 },              
					    success:function(json){  
					    	
// 					      var json = eval("(" + data +")");
						  if(json.ok){
							 layer.msg(json.message,{time:2000});
						  }else{
							 layer.msg(json.message,{time:2000});
						  }
						  layer.close(index)
					   }
				 });  
					
				},
			})	
		
		})
		
/* 		$('.sendButton').click(function(){
		
			var roleNo=$("#roleNo").val();
			var projectNo=$("#projectNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			window.location.href="${ctx}/quality/addQuality?roleNo="+roleNo+"&projectNo="+projectNo+"&userId="+userId+"&userName="+encodeURI(encodeURI(userName));
			
		}) */
		
		function deleteQuality(obj){
			var id=obj;
			if(!id){
				return false
			}
			var index = layer.open({
				type:1,
				skin:'finish-btn',
				title:'确认删除？',
				anim:4,
				shade:0.6,
				shadeClose:true,
				btn:['确定','取消'],
				yes:function(){
					var roleNo=$("#roleNo").val();
					var projectNo=$("#projectNo").val();
					var userId=$("#userId").val();
					var userName=$("#userName").val();
						
					$.ajax({
				     type:"post",                   
				     url:"${ctx}/quality/deleteQuality",           
				     data:{
				         id:id,userName:userName
				     },              
				     success:function(json){
// 				    	 var json = eval("(" + data +")");
						 if(json.ok){
							  window.location.href="${ctx}/project/showDetails?projectNo="+projectNo+"&userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
						  }else{
							  layer.msg(json.message)
						  }
						 layer.close(index)
				     },
				     error:function(){
				    	 layer.msg(json.message);
				    	 layer.close(index)
				     }
					})

				}
			})
		}
		
		function viewDetailQuality(obj){
			var id=obj;
			if(!id){
				return false
			}
			var roleNo=$("#roleNo").val();
			var projectNo=$("#projectNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			window.location.href="${ctx}/quality/viewQuality?id="+id+"&roleNo="+roleNo+"&projectNo="+projectNo+"&userId="+userId+"&userName="+encodeURI(encodeURI(userName));	
		}
		
		 //上传文件
		 function doUploadFile(obj){
			  var path =  $("#file_upload").val();
		      sppath = path.split("\\");
			  var productImg = sppath[sppath.length-1];
			  $("#productImg").val(productImg);
	         //发送ajax请求,提交form表单    
			  $("#file_upload_id").ajaxSubmit({
				type : "post",
				url : "${ctx}/project/uploadProductFile",
				dataType : "text",
				success : function(result) {
				    var data = eval("(" + result +")");
					$('#productImgHtml').text(data);
					$('#productImgHtml').attr('href','http://117.144.21.74:10010/product_img/'+data+''); 
				},
				error : function() {
					easyDialog.open({
						container : {
							content : '  Upload failed'
						},
						autoClose : 1000
				  });
				}
			   });
		 }
		 
		
		 
		 
		 
		 
		 
		 /**
		  * 删除视频
		  */
		 function del(id,obj){				
				if(!id){
					return false
				}
				var index = layer.open({
					type:1,
					skin:'finish-btn',
					title:'确认删除？',
					anim:4,
					shade:0.6,
					shadeClose:true,
					btn:['确定','取消'],
					yes:function(){
							
						$.ajax({
					     type:"post",                   
					     url:"${ctx}/uploadVideo/deleteVideo",           
					     data:{
					         id:id
					     },              
					     success:function(json){
// 					    	 var json = eval("(" + data +")");
							 if(json.ok){
								$(obj).parent().prev().remove();
								$(obj).parent().remove();
							  }else{
								  layer.msg(json.message)
							  }
							 layer.close(index)
					     },
					     error:function(){
					    	 layer.msg(json.message);
					    	 layer.close(index)
					     }
						})

					}
				})
			}
		 
		 
		 
		 /**
		  * 删除质量分析表、技术分析表
		  */
		 function del_Analysis(id,fileType,obj){				
				if(!id){
					return false
				}
				var index = layer.open({
					type:1,
					skin:'finish-btn',
					title:'确认删除？',
					anim:4,
					shade:0.6,
					shadeClose:true,
					btn:['确定','取消'],
					yes:function(){
							
						$.ajax({
					     type:"post",                   
					     url:"${ctx}/project/deleteAnalysis",           
					     data:{
					         id:id,
					         fileType:fileType
					     },              
					     success:function(json){
// 					    	 var json = eval("(" + data +")");
							 if(json.ok){
								$(obj).parents('li').remove();
							  }else{
								  layer.msg(json.message)
							  }
							 layer.close(index)
					     },
					     error:function(){
					    	 layer.msg(json.message);
					    	 layer.close(index)
					     }
						})

					}
				})
			}
		 
		 
		 /**
		  * 删除质量、工艺问题
		  */
		 function del_AnalysisIssue(id,obj){				
				if(!id){
					return false
				}
				var index = layer.open({
					type:1,
					skin:'finish-btn',
					title:'确认删除？',
					anim:4,
					shade:0.6,
					shadeClose:true,
					btn:['确定','取消'],
					yes:function(){
							
						$.ajax({
					     type:"post",                   
					     url:"${ctx}/project/deleteAnalysisIssue",           
					     data:{
					         id:id
					     },              
					     success:function(json){
// 					    	 var json = eval("(" + data +")");
							 if(json.ok){
								$(obj).parents('li').remove();
							  }else{
								  layer.msg(json.message)
							  }
							 layer.close(index)
					     },
					     error:function(){
					    	 layer.msg(json.message);
					    	 layer.close(index)
					     }
						})

					}
				})
			}
		 
		 
		 
		//上传文件
		 function upload_analysis(obj){	
		 		var path = $(obj).val();
		 	    sppath = path.split("\\");
		 	    var fileName = sppath[sppath.length-1];	  	   
		 	    if(fileName == null || fileName == '' || fileName == undefined){
		 	    	return false;
		 	    }else{
		 		   $('#fileName').val(fileName);
		     	   autTime(); 
		 		   $('#upload_title').children().text("上传进度");
		 	    }	 		    	
		      		
		 		  
		 		  //先上传后获取上传文件路径
		 		 $(obj).parents('form').ajaxSubmit({
		 			type: "post",
		 			url: "/upload/analysisUpload",
		 	     	dataType: "text",
		 	     	success: function(str){
		 	     	var result = eval('(' + str + ')');		
		 	     	var newFileName = result.data;
		 		     	if(result.ok){  
		 		     		$('#fileNewName').val(newFileName);		
		 		     		$(obj).prev().text(fileName);
		 		     	}else if(result.message == '您还未登录'){
							  var a = location.href;
							  a = encodeURIComponent(a);
							  window.location = '/index.jsp?purchase_history='+a;
						}else{
		 		     		layer.msg('上传失败',{time:2000});
		 		     	}
		 	     	},
		 			error: function(){
		 				layer.msg('上传失败',{time:2000});
		 	     		$('#show_upload_dialog').hide();
		 			}       	     	
		 	 	}); 	 		    

		  }
		
		
		 //更新质量分析表、技术分析表
		 function update_analysis(obj){	
		    				
		      	var fileType = $('input[name="analysisType"]:checked').val();
		 		$('#fileType').val(fileType); 		      	
		 		  //先上传后获取上传文件路径
		 		 $(obj).parents('form').ajaxSubmit({
		 			type: "post",
		 			url: "/project/updateAnalysis",
		 	     	dataType: "text",
		 	     	success: function(str){
		 	     	   var result = eval('(' + str + ')');		
		 		     	if(result.ok){  
		 		     		layer.msg('保存成功',{time:2000}); 	
		 		     		window.location.reload();
		 		     	}else{
		 		     		layer.msg('保存失败',{time:2000});
		 		     	}
		 	     	},
		 			error: function(){
		 				layer.msg('保存失败',{time:2000});
		 	     		$('#show_upload_dialog').hide();
		 			}       	     	
		 	 	}); 	 		    

		  }
		 
		 
		 //质量分析回复
		 function reply(id,userName,roleNo){	
		    			
			    if(!userName){
			    	  var a = location.href;
					  a = encodeURIComponent(a);
					  window.location = 'http://117.144.21.74:10010/index.jsp?purchase_history='+a;
			    }
		      	var issueReply = $('#issueReply').val();
		      	$.ajax({
				     type:"post",                   
				     url:"${ctx}/project/updateAnalysisIssue",           
				     data:{
				    	 id:id,
				    	 issueReply:issueReply
				     },              
				     success:function(json){
// 				    	 var json = eval("(" + data +")");
						 if(json.ok){
							 layer.msg('保存成功',{time:2000}); 
							 if(roleNo == 6 || roleNo == 1020 || roleNo == 69){
								 $('#purchaseReply').text(issueReply);
							 }
							 if(userName.toLowerCase() == 'wangweiping'){
								 $('#technicianReply1').text(issueReply);
							 }
							 
						  }else{
							  layer.msg(json.message)
						  }
				     },
				     error:function(){
				    	 layer.msg(json.message);			    	
				     }
				 })		 
		  }
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 /*
		  * 添加质量、工艺问题
		  */
		 function add_issue(){
			 
			 var issue = $('#issue').val();
			 var projectNo = $('#projectNo').val();
			 var qualityAnalysisId = $('#qualityAnalysisId').val();
			 var process = $('#process_list').val();
			 if(!issue){
				 layer.msg('问题不能为空',{time:2000}); 	
				 return false;
			 }
			 if(!process){
				 layer.msg('工艺为空',{time:2000}); 	
				 return false;
			 }
			 if(issue.length>5){
			      layer.msg('问题在5个字以内',{time:2000}); 	
				  return false;
				 }
			 
			 $.ajax({
			     type:"post",                   
			     url:"${ctx}/project/addAnalysisIssue",           
			     data:{
			    	 issue:issue,
			    	 projectNo:projectNo,
			    	 process : process,
			    	 qualityAnalysisId:qualityAnalysisId
			     },              
			     success:function(json){
// 			    	 var json = eval("(" + data +")");
					 if(json.ok){
						 layer.msg('保存成功',{time:2000}); 
						 window.location.reload();
					  }else{
						  layer.msg(json.message)
					  }
			     },
			     error:function(){
			    	 layer.msg(json.message);			    	
			     }
				})		 
		 }
		 
		 
		 /*
		  * 批量添加质量、工艺问题
		  */
		 function add_issue_batch(){
			 
			var projectNo = $('#projectNo').val();			 
			var issueList=[];
			$('.lis').find('li').each(function(){
				var This = $(this);
				if(This.find('input[type="checkbox"]').is(':checked')){
					var process = This.find('span:eq(0)').text();
					var issue = This.find('span:eq(1)').text();
					var analysisIssue = {"projectNo":projectNo,"issue":issue,"process":process};
					issueList.push(analysisIssue);
				}				
			}) 
			 if(!issueList){
				 $('.tc_bq').hide();
				 return false;
			 }
			 
			 $.ajax({
			     type:"post",                   
			     url:"${ctx}/project/addAnalysisIssueBatch",           
			     data:{
			    	projectNo:projectNo,
                    issueList:JSON.stringify(issueList)
			     },              
			     success:function(json){
// 			    	 var json = eval("(" + data +")");
					 if(json.ok){
						 layer.msg('保存成功',{time:2000}); 
						 window.location.reload();
					  }else{
						  layer.msg(json.message)
					  }
			     },
			     error:function(){
			    	 layer.msg(json.message);			    	
			     }
				})		 
		 }
		 
		 
		 
		 
		 
		 
		 
		 
		 //根据问题查询
		 function selectByIssue(obj){
			 
			 var process = $('#process_list').val();
			 if(!process){
				 return false;
			 }
			 
			 $.ajax({
			     type:"post",                   
			     url:"${ctx}/project/selectByIssue",           
			     data:{
			    	 issue : $(obj).val(),
			    	 process:process
			     },              
			     success:function(json){
// 			    	  var json = eval("(" + data +")");
					  if(json.ok){
						 var issueList = json.data;
						 $('.z-ul').empty();
						 if(issueList){
							 for(var i=0;i<issueList.length;i++){
								 $('.z-ul').append('<li>'+issueList[i].issue+'</li>');
							 }
							 $('.z-ul').show();
							 //选中关键词
					   		 $('.z-ul li').click(function(e){
					   			 $('#issue').val($(this).text()); 
					   			 $('.z-ul').hide();
					   			 
					   			 $(document).on("click", function(){
					   			     $('.z-ul').hide();
								 });								
								 e.stopPropagation();
					   		 })
						 }
						 						 
					  }else{
						  layer.msg(json.message)
					  }
			     },
			     error:function(){
			    	 layer.msg(json.message);			    	
			     }
				})		 
		 }
		 
		
		 
		 //完成任务
		 function complete_task(id,taskStatus){
		   var userName = $('#userName').val();
			 layer.open({
					type:1,
					skin:'finish-btn',
					title:'确认完成该任务？',
					anim:4,
					shade:0.6,
					shadeClose:true,
					btn:['确定','取消'],
					})
					
				$('.finish-btn .layui-layer-btn0').click(function() { 		
					 $.ajax({
					     type:"post",                   
					     url:"${ctx}/projectTask/updateProjectTask",           
					     data:{
					    	 projectTaskId:id,
					    	 taskStatus:taskStatus,
					    	 userName:userName
					     },              
					     success:function(json){  
// 						      var json = eval("(" + data +")");
							  if(json.ok){
								  window.location.reload();
							  }else{
								  layer.msg('操作失败',{time:2000});
							  }   
					     }
					 });
		       }) 
	    }	
		 //修改工厂实际完成时间
		 function updateNewDate(id){
		   var newDate = $('#new_date').val();
		   $.ajax({
					     type:"post",                   
					     url:"${ctx}/projectTask/updateProjectTask",           
					     data:{
					    	 projectTaskId:id,
					    	 newDate:newDate
					    	 
					     },              
					     success:function(json){  
							  if(json.ok){
								  window.location.reload();
							  }else{
								  layer.msg('操作失败',{time:2000});
							  }   
					     }
					 });
		       
	    }	
		 //上传钉钉审批
		 function sendDataToDingTalk(dingTalkId,id,planNode){
			 
			 $.ajax({
        	 	    url: "https://www.kuaizhizao.cn/Ding-Talk/qualityInspectionReport2",
        	 	    type: "post",
        	 	   dataType:"text",
        	 	    data: {
        	 	    	dingTalkId:dingTalkId,
        	 	    	qualityId:id,
        	 	    	url:planNode,
        	 	    	selectButton:1
        	 	    },
        	 	    success: function (data) {
        	 	    	 $.ajax({
        	        	 	    url: "${ctx}/quality/updateProcess",
        	        	 	    type: "post",
        	        	 	   dataType:"text",
        	        	 	    data: {
        	        	 	    	id:id,
        	        	 	    	process_instance_id:data
        	        	 	    },
        	        	 	    success: function (data1) {
        	        	 	    window.location.reload();	
        	        	 	    	
        	        	 	    },
        	        	 	   
        	        	 	   
        	        	 	    
        	    	  		});	
        	 	    	
        	 	    },
        	 	   error:function(data){
        	 		  
        	 		  alert("数据未传钉钉审批");    
        	 	    }
        	 	   
        	 	    
    	  		});
			 
			
	    }	
		 //上传钉钉审批
		 function updateStatus(id,processInstanceId){
			 
			 $.ajax({
        	 	    url: "https://www.kuaizhizao.cn/Ding-Talk/updateStatus",
        	 	    type: "post",
        	 	   dataType:"text",
        	 	    data: {
        	 	    	processInstanceId:processInstanceId,
        	 	    	id:id
        	 	    },
        	 	    success: function (data) {
        	 	    	window.location.reload(); 
        	 	    },
        	 	   error:function(data){
        	 		  
        	 		  alert("数据未传钉钉审批");    
        	 	    }
        	 	   
        	 	    
    	  		});
			 
			
	    }	
		 
		//文档在线预览 
	    function showExcel(planNode){
	    	if($('.preview').css("display")=="none"){
	    		$('#view').attr('src','https://view.officeapps.live.com/op/view.aspx?src=http://www.kuaizhizao.cn/upload/zhongwentuzhi/'+planNode+'');
	    		$('.preview').show();
	    	}else{
	    		$('.preview').hide();
	    	}   	
	    }
		 
		 
		 //如果没有检验任务，提示暂无检验
		 $(function(){
			 var tl =  $('.add_sc').find('tr').length;
			 if(tl == 0){
				 $('.add_sc').after('<span style="color: #337ab7;">暂无</span>');
			 }
			 			 
			 $('#processList input[type="checkbox"]').click(function(){
					var str = '';
					$('#processList input[type="checkbox"]:checked').each(function(){
						var text = $(this).siblings('span').text();
						if(text){
							text = text.split('-')[1];
						}
						str += text + ',';				
					})
					var str = str.substring(0,str.length-1);
					$('.add_check input[name="process"]').val(str);
				})
		 })
		 // 问题标签弹窗
		 $('.sel_btn').click(function(){
			 $('.tc_bq').show();
		 });
		$('.clo').click(function(){
			$('.tc_bq').hide();
		});
		// 工艺多选
		$('.add_check .glyphicon-triangle-bottom').click(function(){
			$('#processList').toggle();								 
		})
	
		
    //检验计划展开
	function openInspection(obj,index){
		if($(obj).text() == '收起'){
			$(obj).text('请展开选择');
			$(obj).parents('tbody').find('.check_index_'+index+'').hide();		
		}else{
			$(obj).text('收起');
			$(obj).parents('tbody').find('.check_index_'+index+'').show();		
		}		
	}
	
		
		//上传文件
		 function insertFile(projectNo,num){
			 if(num==1){
			  var path =  $("#procurementAnalysisReport").val();
		      sppath = path.split("\\");
			  var allDocuments = sppath[sppath.length-1];
			  $("#allDocuments").val(allDocuments);
			  $("#category").val(num);
			 }else if(num==2){
			  var path =  $("#evaluationReport").val();
			  sppath = path.split("\\");
			  var allDocuments = sppath[sppath.length-1];
			  $("#allDocuments").val(allDocuments);
			  $("#category").val(num);	 
			 }else if(num==3){
			  var path =  $("#preparatoryReport").val();
			  sppath = path.split("\\");
			  var allDocuments = sppath[sppath.length-1];
			  $("#allDocuments").val(allDocuments);
			  $("#category").val(num);	 
			 }else if(num==4){
			  var path =  $("#packagingScheme").val();
			  sppath = path.split("\\");
			  var allDocuments = sppath[sppath.length-1];
			  $("#allDocuments").val(allDocuments);
			  $("#category").val(num);	 
			 }else if(num==5){
			  var path =  $("#qualityEfficiencyReport").val();
			  sppath = path.split("\\");
			  var allDocuments = sppath[sppath.length-1];
			  $("#allDocuments").val(allDocuments);
		      $("#category").val(num);	 
			 }else if(num==6){
			  var path =  $("#analysisConcentrator").val();
			  sppath = path.split("\\");
			  var allDocuments = sppath[sppath.length-1];
			  $("#allDocuments").val(allDocuments);
			  $("#category").val(num);	 
			 }
		     //发送ajax请求,提交form表单    
			  $("#uploadProductionReport").ajaxSubmit({
				type : "post",
				url : "${ctx}/qualityAndEfficiencyReport/uploadProductFile",
				dataType : "text",
				success : function(result) {
				  
				    if(num==1){
				    	$('#open1').hide();
				    	$('#close1').show();
				    }else if(num==2){
				    	$('#open2').hide();
				    	$('#close2').show();
				    }else if(num==3){
				    	$('#open3').hide();
				    	$('#close3').show();
				    }else if(num==4){
				    	$('#open4').hide();
				    	$('#close4').show();
				    }else if(num==5){
				    	$('#open5').hide();
				    	$('#close5').show();
				    }else if(num==6){
				    	$('#open6').hide();
				    	$('#close6').show();
				    }
				},
				error : function() {
					easyDialog.open({
						container : {
							content : '  Upload failed'
						},
						autoClose : 1000
				  });
				}
			   });
			
		 } 
		
	</script>
	<script>
	/* 日历插件*/ 
	  
	$(function(){
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
	})
	
	
	</script>

</body>
</html>
