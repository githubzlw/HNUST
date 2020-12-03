<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
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
<meta name="Description"
	content="<c:if test="${projectComplaint.seriousLevel == 1}">一般抱怨</c:if><c:if test="${projectComplaint.seriousLevel == 2}">严重抱怨</c:if><c:if test="${projectComplaint.seriousLevel == 3}">退货</c:if><c:if test="${projectComplaint.seriousLevel == 4}">客户索赔</c:if><c:if test="${projectComplaint.seriousLevel == 5}">改进</c:if>_${projectComplaint.customerName}_${projectComplaint.process==null?'':projectComplaint.process}_<c:forEach var="obj" items="${factoryList}" varStatus="i">
				      <c:choose>
					      <c:when test="${i.index == factoryList.size()-1}">
					         ${obj.factoryName}
					      </c:when>
					      <c:otherwise>
					         ${obj.factoryName.concat('/')}
					      </c:otherwise>
				      </c:choose>				      
				    </c:forEach>_${project.sellName.concat('/')}${project.purchaseName!=null?project.purchaseName.concat('/'):''}${(project.zhijian1!=null&&project.zhijian1!='')?project.zhijian1.concat('/'):''}${(project.zhijian2!=null&&project.zhijian2!='')?project.zhijian2.concat('/'):''}${(project.zhijian3!=null&&project.zhijian3!='')?project.zhijian3.concat('/'):''}" />
<title>电子客户投诉单_${projectComplaint.projectNo}_${projectComplaint.projectName}</title>


<meta property="og:title" content="电子客户投诉单_${projectComplaint.projectNo}_${projectComplaint.projectName}" />
<meta property="og:url" content="https://www.kuaizhizao.cn/complaint/queryComplaint?id=${projectComplaint.id}" />
<meta property="og:type" content="website" />
<meta property="og:site_name" content="电子客户投诉单" />
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="add.css">
<link rel="stylesheet" href="jsmodern-1.1.1.min.css">
<link rel="stylesheet" href="easydialog.css" />
<script type="text/javascript" src="upload-base.js"></script>
<style type="text/css">
#layui-layer2 {
	top: 0px;
}

.tc_bq {
	width: 400px;
	height: 450px;
	background-color: #fff;
	box-shadow: -2px 0 6px rgba(0, 0, 0, .3), 2px 0 6px rgba(0, 0, 0, .3);
	position: fixed;
	top: 100px;
	left: 50%;
	margin-left: -260px;
	z-index: 100;
	padding: 10px;
	display: none;
}

.lis {
	height: 365px;
	overflow-y: auto;
	margin-bottom: 10px;
}

.redo form {
	position: relative;
}

.redo input[type="file"] {
	position: absolute;
	left: 0;
	top: 0;
	opacity: 0;
}
.sure_btn label{margin-right:5px;} 
.mr5{margin-right:5px}
.add_btns{position: relative;}
.add_btns .btns_1 {position: absolute; right: 0; top: -35px;}
.add_btns .btns_1 button,.add_btns2 .btns_1 button
{color: #027CFF;  background-color: #fff; text-decoration: underline;}  
.remed_reply {position:relative;}
.add_btns2{position: absolute; right: 0; top: -13px;}
@media screen and (min-width:768px){
	.customer_complaint_B_detail .transparent {}
}   
   .customer_complaint_B_detail .big_pic{width: 100%;
    height: 100%;
    left: 0;
    margin-left: 0;}
   .big_pic img{    position: absolute;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    margin: auto!important;}
    
    
   @media screen and (max-width:768px){
   	.confirm_list .add_btns2{top:-20px;}
   	.add_btns .btns_1{position: relative;top: 0;left: 0;right: unset; text-align: left;}
    .customer_complaint_B_detail .depart_reply .btn{margin-top:0;}
   
    
   } 
    
    
</style>

</head>
<body>
	<div
		class="confirm_list confirm_list_share customer_complaint_B_detail">
		<div class="tc_bq" style="display: none">
			<div class="top clearfix">
				<p class="pull-left">已有问题标签</p>
				<button class="btn bgcolor_ff0 pull-right clo">关闭</button>
			</div>
			<div class="lis">
				<ul>
					<c:forEach var="obj" items="${list}">
						<li><input type="checkbox"><span>${obj.process}</span>：<span>${obj.issue}</span>
						</li>
					</c:forEach>
				</ul>
			</div>
			<button class="display_block btn bgcolor_ff0 clo clo_add"
				onclick="add_issue_batch()">添加并关闭窗口</button>
		</div>


		<a href="#top" class="back_top"><img src="back_top.jpg"></a>
		<div class="big_pic_transparent"></div>
		<div class="big_pic">
			<img src="" class="img-responsive mt20">
		</div>
		<input type="hidden" value="${mailBody}" id="mailBody">
		<div class="transparent">
			<div class="pinch-zoom-container">
				<div class="pinch-zoom">
					<img src="">
				</div>
			</div>

		</div>
		<!-- 	<div class="tc position_absolute text-center alt-message"></div>			 -->
		<div class="top clearfix" id="top">
			<input id="project_no" value="${projectComplaint.projectNo}"
				type="hidden"> <input id="complaintId"
				value="${projectComplaint.id}" type="hidden">
			<div class="pull-left top1">
				<h1 class="customer_complaint_h1">电子质量投诉跟踪单</h1>
				<div class="d1">
					<span>跟踪单编码 — </span><span><fmt:formatNumber
							value="${projectComplaint.id}" pattern="0000" /></span>
				</div>
				<div class="">
					<span>投诉单流程：</span>
					<c:if test="${projectComplaint.seriousLevel==5 }"><c:if
						test="${projectComplaint.completeTime != null }">
						<em style="color: #08dc2d;font-size: 16px;">已完成</em>
					</c:if>
					<c:if test="${projectComplaint.completeTime==null }">
						<em style="color: red;font-size: 16px;">未完成</em>
					</c:if>
					</c:if>
					<c:if test="${projectComplaint.seriousLevel !=5 }"><c:if
						test="${projectComplaint.completeTime != null &&projectComplaint.inspectionLeaderConfirmDate!=null &&projectComplaint.inspectionLeaderConfirm==1}">
						<em style="color: #08dc2d;font-size: 16px;">完成,已验证</em>
					</c:if>
					<c:if test="${projectComplaint.completeTime != null &&projectComplaint.inspectionLeaderConfirmDate!=null &&projectComplaint.inspectionLeaderConfirm==0}">
						<em style="color: red;font-size: 16px;">完成,未验证</em>
					</c:if>
				   <c:if test="${projectComplaint.inspectionLeaderConfirmDate==null }">
				   <c:if test="${existRectificationReply==1 }">
				    <c:if test="${preliminaryVerification==1}">
			              <em style="color: red;font-size: 16px;">已经验证，待质检总监签字</em>	   
				   </c:if>
				   <c:if test="${preliminaryVerification==0}">
			              <em style="color: red;font-size: 16px;">未完成</em>	   
				   </c:if>
				   </c:if>
				    <c:if test="${existRectificationReply==0 }">
				   
				    <c:if test="${preliminaryVerification==1}">
			              <em style="color: red;font-size: 16px;">初步解决，还需验证</em>	   
				   </c:if>
						
				   <c:if test="${preliminaryVerification==0}">
			              <em style="color: red;font-size: 16px;">未完成</em>	   
				   </c:if>
				   </c:if>
				
					</c:if>
					</c:if>
					<c:if
						test="${projectComplaint.createPerson eq user.userName || user.userName eq 'ninazhao'}">
						<button type="button" class="btn" onclick="editProcess()">编辑</button>
					</c:if>
				</div>
			</div>
			<div class="btns pull-right top2">
				<%-- 		   <c:if test="${type==null || type==''}"> --%>
				<a class="select_blank btn"
					href="http://117.144.21.74:10010/user/toIndex">返回功能选择页</a> <a
					onclick="goIndex('https://www.kuaizhizao.cn/complaint/queryList')"
					class="btn select_blank">返回客户投诉列表</a>

				<%-- 		   </c:if> --%>
			</div>
		</div>

		<div class="form-group" id="process" style="display: none;">
			<div class="wrap">
				<p class="f18">客户主要问题投诉</p>
				<div class="wrap1 mt10">
					1.
					<button class="btn bgcolor_ff0 sel_btn" type="button">可从已有问题标签中选择添加</button>
				</div>
				<div class="wrap2 ">
					<div class="wrap2_in1 mt10">
						2.<span>可以新输入一个【工艺：问题】标签，例如：【焊接：虚焊】</span> <span>请先选择工艺（黄色字体为项目技术部预选的工艺）</span>
						<select class="form-control display_inline_block"
							id="process_list">
							<option value="">选择对应工艺</option>
						</select>
					</div>
					<div class="wrap2_in2 mt10 clearfix">
						<span>请输入工艺所对应的问题标签，请尽量简短</span> <input type="text"
							class="form-control pull-left" placeholder="5个字以内" id="issue"
							oninput="selectByIssue(this)">
						<ul class="z-ul">

						</ul>
						<button class="btn bgcolor_ff0 pull-right" onclick="add_issue()">添加</button>
					</div>

				</div>
				<div class="wrap3">
					<h4 class="mt10">
						已归纳【工艺：问题】标签：<span id="s_node"
							<c:if test="${analysisIssueList==null||analysisIssueList.size()==0}">style="display:none;"</c:if>>暂无</span>
					</h4>
					<ul class="list">
						<c:forEach var="obj" items="${analysisIssueList}" varStatus="i">
							<li class="mt10"><span>${obj.process}：</span> <span>${obj.issue}</span>
								<span>出现过 (<a
									href="/project/selectIssueList?issue=${obj.issue}" class="blue"><i>${obj.occurrenceNum}</i></a>)次
							</span>
								<button class="btn del" type="button"
									onclick="del_AnalysisIssue('${obj.id}',this)">删除</button></li>
						</c:forEach>
					</ul>
				</div>
				<!-- <div class="text-center mt10">
						<button class="btn bgcolor_ff0 tj">提交并关闭</button>
					</div> -->
			</div>
		</div>


		<c:if test="${user==null||user==''}">
			<div class="share mt10">
				<a onclick="sendReply(11)"
					style="color: red; background-color: #fff; text-decoration: underline;">您还未登录，如需回复，请先点击登录。</a>
			</div>
		</c:if>
		<div class="share mt10">
			<a class="share_wechat btn share" id="share-qrcode">可点击本按钮分享到微信群(需使用QQ浏览器)</a>
		</div>
		<div class="clearfix mt10">
			<div class="pull-left list_left">
				<p>
					项目号：<span>${projectComplaint.projectNo}</span>
				</p>
				<p>
					项目名：<span>${projectComplaint.projectName}</span>
				</p>
				<p>
					项目阶段：<span>${projectComplaint.projectStage == 0 ? '样品' : '大货'}</span>
				</p>
				<p>
					客户名：<span>${projectComplaint.customerName}</span>
				</p>
				<p>
					人员列表： <span>${project.sellName.concat('/')}${project.purchaseName!=null?project.purchaseName.concat('/'):''}${(project.zhijian1!=null&&project.zhijian1!='')?project.zhijian1.concat('/'):''}${(project.zhijian2!=null&&project.zhijian2!='')?project.zhijian2.concat('/'):''}${(project.zhijian3!=null&&project.zhijian3!='')?project.zhijian3.concat('/'):''}</span>
				</p>
				<p>
					<em>工厂列表：</em> <span> <c:forEach var="obj"
							items="${factoryList}" varStatus="i">
							<c:choose>
								<c:when test="${i.index == factoryList.size()-1}">
					         ${obj.factoryName}
					      </c:when>
								<c:otherwise>
					         ${obj.factoryName.concat('/')}
					      </c:otherwise>
							</c:choose>
						</c:forEach>
					</span>
				</p>
			</div>
			<div class="pull-right small_imgs text-center">
				<img
					src="https://www.kuaizhizao.cn/product_img/${project.productImg}"
					class="img-responsive">
			</div>
		</div>
		<div class="line mt10"></div>
		<div class="complaint_detail mt10">
			<h3>客户投诉情况：</h3>
			<p>
				<em>严重等级：</em> <span> <c:if
						test="${projectComplaint.seriousLevel == 1}">一般抱怨</c:if> <c:if
						test="${projectComplaint.seriousLevel == 2}">严重抱怨</c:if> <c:if
						test="${projectComplaint.seriousLevel == 3}">退货</c:if> <c:if
						test="${projectComplaint.seriousLevel == 4}">客户索赔</c:if>
						<c:if test="${projectComplaint.seriousLevel == 5}">改进</c:if>
				</span>
			</p>
			<%-- 	<p><em>项目名：</em><span>${projectComplaint.projectName}</span></p> --%>
			<%-- <p><em>人员列表：</em>
			<span>${project.sellName.concat('/')}${project.purchaseName.concat('/')}${project.zhijian1.concat('/')}${project.zhijian2.concat('/')}${project.zhijian3.concat('/')}</span>
		</p> --%>
			<p>${projectComplaint.complaintContent}</p>
			
			<p>是否需要跟新图纸：<c:if test="${projectComplaint.picUp!=0 }">
							是-<c:if test="${projectComplaint.verification!=0 }"><span class="blue">已验证</span></c:if>
							<c:if test="${projectComplaint.verification==0 }"><span class="red">未验证</span></c:if>
							</c:if>
							<c:if test="${projectComplaint.picUp==0 }">
							否
							</c:if>
							</p>
			
			<div class="question">
				<c:forEach var="obj" items="${issueList}" varStatus="i">
					<p class="mt10">
						<em> 问题${i.index+1}：</em><span>${obj.issue}</span>
					</p>
					<c:if test="${obj.imgList!=null&&obj.imgList!=''}">
						<c:set var="strs" value="${fn:split(obj.imgList, ',')}" />
						<c:forEach var="str" items="${strs}">
							<div class="imgs mt10" onclick=" bigPic(this)">
								<c:choose>
									<c:when test="${projectComplaint.id > 38}">
										<img
											src="https://www.kuaizhizao.cn/static_img/project_complaint/${projectComplaint.projectNo}/${str}"
											class="img-responsive">
									</c:when>
									<c:otherwise>
										<img
											src="https://www.kuaizhizao.cn/static_img/project_complaint/${project.productImg}"
											class="img-responsive">
									</c:otherwise>
								</c:choose>
							</div>
						</c:forEach>
					</c:if>
				</c:forEach>
			</div>
			<div class="text-right">
				<span>录入人：${projectComplaint.createPerson}</span><span class="ml20"><fmt:formatDate
						value="${projectComplaint.createTime}" pattern="yyyy-MM-dd" /></span>
			</div>
			<div class="clearfix mt10">
				<c:if
					test="${projectComplaint.filePath != null && projectComplaint.filePath != ''}">
					<span class="pull-left">附件名称</span>
					<span>${projectComplaint.fileName}</span>
					<a href="/complaint/download?id=${projectComplaint.id}"
						class="btn pull-right">下载</a>
				</c:if>
			</div>
		</div>
		<div class="line mb10"></div>
		<div class="depart_reply">
			<h3>各部门回复：</h3>
			<div class="btns add_btns">
				<div class="btns_1 text-right">
					<c:if
						test="${user.userName ne 'edward' && user.userName ne 'jerrylong' && user.userName ne 'Jiangwenlong' && user.userName ne 'qcdirector' && user.userName ne 'wangweiping'}">
						<c:choose>
							<c:when
								test="${(user.userName eq project.purchaseName && user.userName != '' && user.userName != null) || (user.userName eq project.sellName && user.userName != '' && user.userName != null)}">
								<a href="/complaint/purchaseReply?id=${projectComplaint.id}"
									style="display: inline-block; padding: 0;"><button
										class="btn"
										>采购/销售录入回复</button></a>
							</c:when>
							<c:otherwise>
								<a onclick="sendReply(1)"
									style="display: inline-block; padding: 0;"><button
										class="btn"
										>采购/销售录入回复</button></a>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${projectComplaint.seriousLevel!=5 }"><c:if
						test="${user.userName ne 'edward' && user.userName ne 'jerrylong' && user.userName ne 'Jiangwenlong' && user.userName ne 'wangweiping'}">
						<c:choose>
							<c:when
								test="${(user.userName != '' && user.userName != null) && (user.userName eq project.zhijian1 || user.userName eq project.zhijian2 || user.userName eq project.zhijian3 || user.userName eq 'qcdirector')}">
								<a href="/complaint/inspectionReply?id=${projectComplaint.id}"
									style="display: inline-block; padding: 0;"><button
										class="btn"
										>质检录入回复</button></a>
							</c:when>
							<c:otherwise>
								<a onclick="sendReply(2)"
									style="display: inline-block; padding: 0;"><button
										class="btn"
										>质检录入回复</button></a>
							</c:otherwise>
						</c:choose>
					</c:if></c:if>
					<c:if
						test="${user.userName ne 'edward' && user.userName ne 'jerrylong' && user.userName ne 'Jiangwenlong' && user.userName ne 'qcdirector' && user.userName ne 'Tonyliao'}">
						<c:choose>
							<c:when
								test="${user.roleNo == 7 || user.roleNo == 100 || user.roleNo == 99}">
								<a
									href="http://117.144.21.74:10010/complaint/technicianReply?id=${projectComplaint.id}"
									style="display: inline-block; padding: 0;"><button
										class="btn"
										>技术部录入回复</button></a>
							</c:when>
							<c:otherwise>
								<a onclick="sendReply(3)"
									style="display: inline-block; padding: 0;"><button
										class="btn"
										>技术部录入回复</button></a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</div>
				<div class="btns_3">
					<c:choose>
						<c:when test="${existPurchaseReply == 1}">
							<button class="btn" onclick="showReply(this)"
								filed="${existPurchaseReply}"
								style="background-color: rgb(56, 226, 219); color: rgb(255, 255, 255);">查看采购回复</button>
						</c:when>
						<c:otherwise>
							<button class="btn" filed="${existPurchaseReply}"
								style="color:#666;">查看采购回复</button>
						</c:otherwise>
					</c:choose>
					<c:if test="${projectComplaint.seriousLevel!=5 }"><c:choose>
						<c:when test="${existInspectionReply == 1}">
							<button class="btn" onclick="showReply(this)"
								filed="${existInspectionReply}"
								<c:choose><c:when test="${existPurchaseReply == 0}">style="background-color: rgb(56, 226, 219); color: rgb(255, 255, 255);"</c:when><c:otherwise>style="background-color: #027CFF; color: rgb(255, 255, 255);"</c:otherwise></c:choose>>查看质检回复</button>
						</c:when>
						<c:otherwise>
							<button class="btn" filed="${existInspectionReply}"
								style="background-color: #e5e5e5; color: rgb(255, 255, 255);">查看质检回复</button>
						</c:otherwise>
					</c:choose></c:if>
					<c:choose>
						<c:when test="${comments!=null || existTechnicianReply == 1}">
							<button class="btn" onclick="showReply(this)"
								filed="${existTechnicianReply}"
								<c:choose><c:when test="${existPurchaseReply == 0 && existInspectionReply == 0}">style="background-color: rgb(56, 226, 219); color: rgb(255, 255, 255);"</c:when> <c:otherwise>style="background-color: #027CFF; color: rgb(255, 255, 255);"</c:otherwise></c:choose>>查看技术部回复</button>
						</c:when>
						<c:otherwise>
							<button class="btn" filed="${existTechnicianReply }"
								style="background-color: #e5e5e5; color: rgb(255, 255, 255);">查看技术部回复</button>
						</c:otherwise>
					</c:choose>
				</div>
				
			</div>
			<div class="replys mt10">

				<div class="reply"
					<c:if test="${existPurchaseReply == 0}">style="display:none;"</c:if>>
					<h4>
						<b>采购回复：</b>
					</h4>
					<c:forEach var="obj" items="${issueList}" varStatus="i">
						<c:forEach begin="1" end="${obj.replyList.size()}" varStatus="j"
							step="1">
							<c:if test="${obj.replyList.get(j.count-1).replyType == 1}">
								<p>
									<b>问题${i.index+1}整改方案：</b><span>${obj.replyList.get(j.count-1).replyContent}</span>
								</p>
							</c:if>
						</c:forEach>
					</c:forEach>
					<p>
						<b>预计整改完成时间：</b><span><fmt:formatDate
								value="${projectComplaint.predictCompleteTime}"
								pattern="yyyy-MM-dd" /></span>
					</p>
					<p>
						<b>预计整改成本：</b><span>${projectComplaint.costAnalysis}</span>
					</p>
					<p>
						<b>根原因分析：</b><span>${projectComplaint.purchaseContent}</span>
					</p>
				</div>


				<c:if test="${projectComplaint.seriousLevel!=5 }"><div class="reply"
					<c:if test="${existInspectionReply == 0}">style="display:none;"</c:if>>
					<h4>
						<b>质检回复：</b>
					</h4>
					<c:forEach var="obj" items="${issueList}" varStatus="i">
						<c:forEach begin="1" end="${obj.replyList.size()}" varStatus="j"
							step="1">
							<c:if test="${obj.replyList.get(j.count-1).replyType == 2}">
								<p>
									<b>问题${i.index+1}：</b><span>${obj.replyList.get(j.count-1).replyContent}</span>
								</p>
							</c:if>
						</c:forEach>
					</c:forEach>
					<p>
						<b>其他解释和分析：</b><span>${projectComplaint.inspectionContent}</span>
					</p>
				</div></c:if>


				<div class="reply"
					<c:if test="${comments==null&&existTechnicianReply==0}">style="display:none;"</c:if>>
					<h4>
						<b>技术部回复：</b>
					</h4>
					<c:forEach var="obj" items="${issueList}" varStatus="i">
						<c:forEach begin="1" end="${obj.replyList.size()}" varStatus="j"
							step="1">
							<c:if test="${obj.replyList.get(j.count-1).replyType == 3}">
								<a
									href="/complaint/technicianFile?id=${obj.replyList.get(j.count-1).id}">${obj.replyList.get(j.count-1).fileName}</a>
								<span>点击可下载</span>
							</c:if>
						</c:forEach>
					</c:forEach>
					<table class="table table-bordered dp_tabel" style="width: 97%;">
						<tbody class="dp_tabel_body">

							<c:forEach var="obj" items="${comments}">
								<tr>
									<td>
										<div>
											<div>
												<span>${obj.reviewer}</span> <em><fmt:formatDate
														value="${obj.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></em>
											</div>
											<div>${obj.comment}</div>
											<c:if
												test="${obj.newFileName != null && obj.newFileName != ''}">
												<div>
													附件：<a
														href="https://www.kuaizhizao.cn/static_img/project_complaint/${obj.projectNo}/${obj.newFileName}">${obj.fileName}</a>
												</div>
											</c:if>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="line mt5"></div>
		<div class="remed_reply mt10">
			<h3>
				<b>整改结果回复：</b>
			</h3>
			<p class="red f18" style="color: red;">任何一个有过问题的项目，在出货前，整改结果都应该
				转发到 公司群里面。</p>
			<div class="btns text-right add_btns2">
				<div class="btns_1 text-right">
				<c:if
					test="${user.userName ne 'edward' && user.userName ne 'jerrylong' && user.userName ne 'Jiangwenlong' && user.userName ne 'wangweiping'}">
					<c:choose>
						<c:when
							test="${existPurchaseReply == 1 && existInspectionReply == 1 }">
							<a href="/complaint/remediaReply?id=${projectComplaint.id}&num=1"
								style="display: inline-block; padding: 0;"><button
									class="btn btn-default btn2">采购录入整改结果</button></a>
								<c:if test="${projectComplaint.seriousLevel!=5 }">	<a href="/complaint/remediaReply?id=${projectComplaint.id}&num=2"
								style="display: inline-block; padding: 0;"><button
									class="btn btn-default btn2">质检录入整改结果</button></a></c:if>
						</c:when>
						<c:otherwise>
							<a style="display: inline-block; padding: 0;"><button
									class="btn btn-default btn2 ">录入整改结果</button></a>
						</c:otherwise>
					</c:choose>
				</c:if>
				</div>
			</div>
			<div class="reply_detail mt10">
			
				<div class="reply1">
					<b>采购/跟单整改结果回复:</b>
					<table class="table table-bordered">
						<tbody>
						<c:if test="${comments1 !=null }">
							<c:forEach var="obj" items="${comments1}">
								<tr>
									<td>
										<div>
											<span>${obj.reviewer}</span> <em><fmt:formatDate
													value="${obj.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></em>
										</div>
										<div>${obj.comment}</div> <c:if
											test="${obj.newFileName != null && obj.newFileName != ''}">
											<div>
												附件：<a
													href="https://www.kuaizhizao.cn/static_img/project_complaint/${obj.projectNo}/${obj.newFileName}">${obj.fileName}</a>
											</div>
										</c:if>
									</td>
								</tr>
							</c:forEach></c:if>
							<c:if test="${comments1 ==null}">
							<span style="color:red;">暂无</span>
							</c:if>
						</tbody>
					</table>
				</div>
				<c:if
					test="${projectComplaint.rectificationSellPurchaseReply != null }">
					<div class="entry1">
						<p>
							<b>采购/跟单整改结果录入时间:</b>
						</p>
						<span>(${projectComplaint.rectificationSellPurchaseReply})</span>
						<span> <fmt:formatDate
								value="${projectComplaint.rectificationSellPurchaseTime}"
								pattern="yyyy-MM-dd" />
						</span>
					</div>
				</c:if>
				<c:if test="${projectComplaint.seriousLevel!=5 }">
					<div class="entry2">
						
							<b>质检整改结果:</b>
						
                <c:if test="${projectComplaint.rectificationZhijianReply != null}">
						<p>
							<b>录入人及录入时间:</b> <span class="mr10">(${projectComplaint.rectificationZhijianReply})</span>
							<span> <fmt:formatDate
									value="${projectComplaint.rectificationZhijianTime}"
									pattern="yyyy-MM-dd" />
							</span>
						</p></c:if>
						<c:if test="${projectComplaint.rectificationZhijianReply == null}">
						
							<span style="color:red;">暂无</span>
						</c:if>
					</div></c:if>
				
				<c:if test="${projectComplaint.seriousLevel!=5 }"><c:forEach var="obj" items="${issueList}" varStatus="i">
					<c:forEach begin="1" end="${obj.replyList.size()}" varStatus="j"
						step="1">
						<c:if test="${obj.replyList.get(j.count-1).replyType == 4}">
							<div class="question_result">
								<%-- <p>
									<b>问题${i.index+1}结果：</b>
									<span>${obj.replyList.get(j.count-1).replyContent}</span>
								</p> --%>
								<p>
									<b>质检回复问题${i.index+1}验证结果：</b> <span>${obj.replyList.get(j.count-1).qualification}</span>
								</p>
							</div>
						</c:if>
					</c:forEach>
				</c:forEach></c:if>
			</div>
		</div>
		<c:if test="${projectComplaint.seriousLevel==3 }">
			<div class="redo mt10">
				<button class="btn dp_btn" style="float: left;">
				本产品重做,点击上传重做的产品检验报告</button>
				<form onsubmit="return false;" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="projectNo"
						value="${projectComplaint.projectNo}">
						 <input
						type="hidden" name="projectComplaintId"
						value="${projectComplaint.id}">
						 <input type="file"
						name="file" class="pull-left zj_upload" onchange="upload(this)">
				</form>
			</div>
		</c:if>
		<table class="table table-bordered dp_tabel">
			<tbody class="complaintInspectionReport">

				<c:forEach var="obj" items="${complaintInspectionReport}">
					<tr>
						<td>
							<div>


								<c:if test="${obj.newFileName != null && obj.newFileName != ''}">
									<div>
										整改报告文档：<a
											href="/project_img/${obj.projectNo}/comment/${obj.newFileName}"
											target="_blank">${obj.fileName}</a>
									</div>
								</c:if>
							</div> <c:if test="${fn:containsIgnoreCase(userName, obj.reviewer)}">
								<button class="btn del"
									onclick="delComplaintInspectionReport('${obj.id}',${projectComplaint.id})">删除</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<div class="line mt5"></div>
		<div class="remed_reply mt10">
			<h3>
				<b>投诉单流程：</b>
			<c:if test="${projectComplaint.seriousLevel==5 }"><c:if
						test="${projectComplaint.completeTime != null }">
						<em style="color: #08dc2d;font-size: 16px;">已完成</em>
					</c:if>
					<c:if
						test="${projectComplaint.completeTime == null }">
						<em style="color: red;font-size: 16px;">未完成</em>
					</c:if>
					</c:if>
					<c:if test="${projectComplaint.seriousLevel!=5 }">
					<c:if
						test="${projectComplaint.completeTime != null &&projectComplaint.inspectionLeaderConfirmDate!=null &&projectComplaint.inspectionLeaderConfirm==1}">
						<em style="color: #08dc2d;">完成,已验证</em>
					</c:if>
					<c:if test="${projectComplaint.completeTime != null &&projectComplaint.inspectionLeaderConfirmDate!=null &&projectComplaint.inspectionLeaderConfirm==0}">
						<em style="color: red;">完成,未验证</em>
					</c:if>
				   <c:if test="${projectComplaint.inspectionLeaderConfirmDate==null }">
				   <c:if test="${existRectificationReply==1 }">
				    <c:if test="${preliminaryVerification==1}">
			              <em style="color: red;font-size: 16px;">已经验证，待质检总监签字</em>	   
				   </c:if>
				   <c:if test="${preliminaryVerification==0}">
			              <em style="color: red;font-size: 16px;">未完成</em>	   
				   </c:if>
				   </c:if>
				    <c:if test="${existRectificationReply==0 }">
				   
				    <c:if test="${preliminaryVerification==1}">
			              <em style="color: red;font-size: 16px;">初步解决，还需验证</em>	   
				   </c:if>
						
				   <c:if test="${preliminaryVerification==0}">
			              <em style="color: red;font-size: 16px;">未完成</em>	   
				   </c:if>
				   </c:if>
				
					</c:if>
					</c:if>
			</h3>
			<div class="reply_detail mt10">
				<c:choose>
					<c:when test="${projectComplaint.purchaseConfirmDate != null}">
						<p class="clearfix">
							<em class="pull-left"><b>采购/跟单回复完成日期:</b></em><span
								class="pull-left blue"> <fmt:formatDate
									value="${projectComplaint.purchaseConfirmDate}"
									pattern="yyyy-MM-dd" />
							</span>
						</p>
					</c:when>
					<c:otherwise>
						<p class="clearfix">
							<em class="pull-left"><b>采购/跟单回复完成日期:</b></em><span
								class="pull-left blue"> 未回复 </span>
						</p>
					</c:otherwise>
				</c:choose>
				<c:if test="${projectComplaint.seriousLevel!=5 }"><c:choose>
					<c:when test="${projectComplaint.zhijianReplyTime != null}">
						<p class="clearfix">
							<em class="pull-left"><b>质检回复完成日期:</b></em><span
								class="pull-left blue"> <fmt:formatDate
									value="${projectComplaint.zhijianReplyTime}"
									pattern="yyyy-MM-dd" /></span>
						</p>
					</c:when>
					<c:otherwise>
						<p class="clearfix">
							<em class="pull-left"><b>质检回复完成日期:</b></em><span
								class="pull-left blue"> 未回复</span>
						</p>
					</c:otherwise>
				</c:choose></c:if>
				<c:choose>
					<c:when
						test="${projectComplaint.technicianReplyTime != null || comments != null}">
						<p class="clearfix">
							<em class="pull-left"><b>技术回复完成日期:</b></em><span
								class="pull-left blue"> <fmt:formatDate
									value="${projectComplaint.technicianReplyTime}"
									pattern="yyyy-MM-dd" /></span>
							<c:if test="${comments != null}">
								<span class="pull-left blue"><fmt:formatDate
										value="${comments[0].createDate}" pattern="yyyy-MM-dd" /></span>
							</c:if>
						</p>
					</c:when>
					<c:otherwise>
						<p class="clearfix">
							<em class="pull-left"><b>技术回复完成日期:</b></em><span
								class="pull-left blue"> 未回复</span>
						</p>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${projectComplaint.completeTime != null}">
						<p class="clearfix">
							<em class="pull-left"><b>最终整改完成日期:</b></em><span
								class="pull-left blue"> <fmt:formatDate
									value="${projectComplaint.completeTime}" pattern="yyyy-MM-dd" /></span>
						</p>
					</c:when>
					<c:otherwise>
						<p class="clearfix">
							<em class="pull-left"><b>最终整改完成日期:</b></em><span
								class="pull-left blue"> 未回复</span>
						</p>
					</c:otherwise>
				</c:choose>

				<!-- 			 	<p class="clearfix"><em class="pull-left"><b>最终整改质检确认:</b></em>
					<span class="pull-left blue mr5">	2018-12-28</span>
					<span class="pull-left blue mr5">ABC</span>
					<em class="pull-left blue">未回复</em>		
			    </p> -->

			</div>
		</div>
<c:if test="${projectComplaint.seriousLevel!=5 }">
		<div class="sure f16">
			<div class="mt10">
				<div class="clearfix">
					<div class="pull-left">
						<b>质检总监签名同意出货:</b>
					</div>
					<span class="pull-left"> 
					
					<c:if test="${projectComplaint.inspectionLeaderConfirm == 1}">						
						<span class="blue"> 确认签名,且已经验证(<fmt:formatDate
								value="${projectComplaint.inspectionLeaderConfirmDate}"
								pattern="yyyy-MM-dd" />)
						</span>
					</c:if> 
					<c:if test="${projectComplaint.inspectionLeaderConfirmDate!=null && projectComplaint.inspectionLeaderConfirm == 0}">						
						<span class="yellow"> 确认签名,但未验证(<fmt:formatDate
								value="${projectComplaint.inspectionLeaderConfirmDate}"
								pattern="yyyy-MM-dd" />)
						</span>
					</c:if> 
					<c:if test="${projectComplaint.inspectionLeaderConfirmDate==null && projectComplaint.inspectionLeaderConfirm == 0}">
						未确认             
		             </c:if>
					</span>
				</div>
				
				<div class="sure_btn clearfix mt10">
				
				<c:if test="${projectComplaint.seriousLevel!=3 }">
					<c:if
						test="${projectComplaint.inspectionLeaderConfirm == 0 && (user.userName eq 'edward' ||  user.userName eq 'ninazhao' ||user.userName eq 'qcdirector'||user.userName eq 'Tonyliao' || user == null || user == '') && existRectificationReply == 1}">
						<label  ><input type="radio" name="sure">不能签名</label>
						<label><input type="radio" name="sure" value="0">确认签名</label>
					<input type="button" value="修改"  onclick="shipment('${projectComplaint.id}',6)" class="btn">
					</c:if>
					<c:if
						test="${projectComplaint.inspectionLeaderConfirm == 0 && (user.userName eq 'edward' ||user.userName eq 'ninazhao' ||user.userName eq 'qcdirector'||user.userName eq 'Tonyliao' || user == null || user == '') && existRectificationReply == 0}">
						<label style="color:#999;"><input type="radio" name="sure" disabled>不能签名</label>
						<label style="color:#999;"><input type="radio" name="sure" disabled>确认签名</label>
					
					<label style="color:#999;"><input type="button" value="修改" class="btn" disabled></label></c:if>
				</c:if>
				<c:if test="${projectComplaint.seriousLevel==3 }">
					<c:if
						test="${projectComplaint.inspectionLeaderConfirm == 0 && (user.userName eq 'edward' ||user.userName eq 'ninazhao' ||user.userName eq 'qcdirector'||user.userName eq 'Tonyliao' || user == null || user == '') && existRectificationReply == 1}">
						<label ><input type="radio" name="sure">不能签名</label>	
						<label><input type="radio" name="sure" value="1">确认签名,未验证</label>
					   <c:if test="${complaintInspectionReport!=null}">
						<label><input type="radio" name="sure" value="0"> 确认签名,且已经验证</label>
					</c:if>
					<c:if test="${complaintInspectionReport==null}">
						<label style="color:#999;"><input type="radio" name="sure" disabled> 确认签名,且已经验证</label>
					</c:if>
					<input type="button" value="修改"  onclick="shipment('${projectComplaint.id}',6)" class="btn">
					</c:if>
					
					<c:if
						test="${projectComplaint.inspectionLeaderConfirm == 0 && (user.userName eq 'edward' ||user.userName eq 'ninazhao' ||user.userName eq 'qcdirector'||user.userName eq 'Tonyliao' || user == null || user == '') && existRectificationReply == 0}">
						<label style="color:#999;"><input type="radio" name="sure" disabled>确认签名</label>	
						<label style="color:#999;"><input type="radio"  name="sure" disabled>确认签名,未验证</label>
						<label style="color:#999;"><input type="radio" name="sure" disabled> 确认签名,且已经验证</label>
					    <label style="color:#999;"><input type="button" value="修改"  class="btn" disabled></label>
					</c:if>
				</c:if>
				</div>
				
			</div>
		</div></c:if>

		<%-- <div class="line"></div>
				<div class="sure f16">					
						<div class="clearfix mt10">
							<div class="pull-left">					
								质检总监_<span>Yangjieguang</span>确认：
							</div>
							<span class="pull-right">
							    <c:choose>
							      <c:when test="${projectComplaint.inspectionLeaderConfirm == 1}">
							                    已确认(<fmt:formatDate value="${projectComplaint.inspectionLeaderConfirmDate}" pattern="yyyy-MM-dd"/>)					       
							      </c:when>
							      <c:otherwise>未确认</c:otherwise>
							   </c:choose>
							</span>
							<c:if test="${projectComplaint.inspectionLeaderConfirm == 0 && (user.userName eq 'yanggong' || user == null || user == '') && existRectificationReply == 1}">
							    <button class="btn pull-right mr10" onclick="shipment('${projectComplaint.id}',6)">可以出货</button>	
							</c:if>				
							<c:if test="${projectComplaint.inspectionLeaderConfirm == 0 && (user.userName eq 'yanggong' || user == null || user == '') && existRectificationReply == 0}">
							    <button class="btn pull-right mr10" style="background-color: #e5e5e5; color: rgb(255, 255, 255);">可以出货</button>	
							</c:if>				
						</div>
						<div class="clearfix mt10">
							<div class="pull-left">					
								采购副总裁_<span>Jiangwenlong</span>确认：
							</div>
							<span class="pull-right">
							   <c:choose>
							      <c:when test="${projectComplaint.purchaseLeaderConfirm == 1}">已确认(<fmt:formatDate value="${projectComplaint.purchaseLeaderConfirmDate}" pattern="yyyy-MM-dd"/>)</c:when>
							      <c:otherwise>未确认</c:otherwise>
							   </c:choose>
							</span>
							<c:if test="${projectComplaint.purchaseLeaderConfirm == 0 && (user.userName eq 'Jiangwenlong' || user == null || user == '') && existRectificationReply == 1}">
							  <button class="btn pull-right mr10" onclick="shipment('${projectComplaint.id}',5)">可以出货</button>			
							</c:if>		
							<c:if test="${projectComplaint.purchaseLeaderConfirm == 0 && (user.userName eq 'Jiangwenlong' || user == null || user == '') && existRectificationReply == 0}">
							  <button class="btn pull-right mr10" style="background-color: #e5e5e5; color: rgb(255, 255, 255);">可以出货</button>			
							</c:if>		
						</div>
				</div> --%>
		<div class="line mt10"></div>
		<div class="mt10 plan">
			<div class="f18">
				<b>检验计划：</b>
			</div>
			<table class="table tablered">
				<tbody>
					<c:forEach var="obj" items="${inspectionPlanList}">
						<tr>
							<td><a
								href="http://117.144.21.74:33168/upload/po/upload/JIANYANJIHUAZJ/${obj.reportName}"
								class="top"> <span>${obj.reportName}</span> <span><fmt:formatDate
											value="${obj.createDate}" pattern="yyyy-MM-dd" /></span>
							</a></td>
							<td><a
								href="http://117.144.21.74:33168/upload/po/upload/JIANYANJIHUAZJ/${obj.reportName}"
								class="btn btn-default">查看</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="line mt10"></div>
		<div class="list mt10">
			<div class="f18">
				<b>本项目质检报告列表：</b>
			</div>
			<table class="table tablered">
				<tbody>
					<c:forEach var="report" items="${qualityReports}">
						<tr>
							<td>
								<div class="top">
									<span>${report.detailView}</span>
								</div>
							</td>
							<td><a
								onclick="window.location='https://www.kuaizhizao.cn/quality/shareQuality?id=${report.id}'"
								class="btn btn-default">查看</a></td>
						</tr>
					</c:forEach>
					<c:if
						test="${qualityReports == null || qualityReports.size() == 0}">
					   暂无
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="list task mt10">
			<div class="f18">
				<b>本项目相关任务：</b>
			</div>
			<table class="tabele table-bordered mt10">
				<tbody>
					<c:forEach var="obj" items="${tasks}" varStatus="i">
						<tr>
							<td>
								<p>${obj.description}</p>
								<div>
									<span>${obj.initiator}</span> <span>${obj.accepter}</span> <span><fmt:formatDate
											value="${obj.startTime}" pattern="yyyy/MM/dd" /></span>
								</div>
							</td>
							<td><c:if test="${obj.taskStatus == 0}">
									<span class="red">未完成</span>
								</c:if> <c:if test="${obj.taskStatus == 1}">
									<span>已完成</span>
								</c:if> <c:if test="${obj.taskStatus == 2}">
									<span>已暂停</span>
								</c:if> <c:if test="${obj.taskStatus == 3}">
									<span>已取消</span>
								</c:if> <a
								href="http://117.144.21.74:10010/projectTask/selectProjectTaskById?id=${obj.id}"
								class="display_block">查看</a></td>
						</tr>
					</c:forEach>
					<c:if test="${tasks == null || tasks.size() == 0}">
					    暂无
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="line mt10"></div>
		<div class="mt10">
			<div>
				<em>质量协议中的项目问题点关键词：</em>
				<c:choose>
					<c:when
						test="${analysisIssueList != null && analysisIssueList.size() > 0}">
						<span class="blue"> <c:forEach var="obj"
								items="${analysisIssueList}" varStatus="i">
								<c:if test="${i.index == analysisIssueList.size() - 1}">		      
				             ${obj.issue}
				            </c:if>
								<c:if test="${i.index < analysisIssueList.size() - 1}">		      
				             ${obj.issue},
				            </c:if>
							</c:forEach>
						</span>
					</c:when>
					<c:otherwise>
				            暂无
				</c:otherwise>
				</c:choose>
			</div>
			<div>
				<em>采购分析：</em> <span class="blue">${qualityAnalysis.purchaseReply == null ? '暂无回复' : qualityAnalysis.purchaseReply}</span>
			</div>
		</div>
		<div class="share mt10">
			<a class="share_wechat btn share">可点击本按钮分享到微信群(需使用QQ浏览器)</a>
		</div>
		<div class="share mt10">
			<a id="share_link" href="#" class="btn share">使用邮件客户端发送本报告给相关人员</a>
		</div>
		<div class="share mt10" style="text-align: center;">
			<c:if
				test="${fn:containsIgnoreCase(user.userName, projectComplaint.createPerson) || user.userName eq 'ninazhao'}">
				<a class="btn" onclick="del(${projectComplaint.id})">删除</a>
			</c:if>
		</div>
	</div>
</body>
</html>
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
<script type="text/javascript" src="shareWechat.js"></script>
<script src="layer.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="pinchzoom.min.js"></script>
<%--<script src="http://www.jq22.com/jquery/jquery-2.1.1.js"></script>--%>
<script src="jquery.min.js"></script>
<script type="text/javascript"
	src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="jsmodern-1.1.1.min.js"></script>
<script type="text/javascript" src="cookie.js"></script>
<script type="text/javascript" src="easydialog.min.js"></script>
<script type="text/javascript" src="process_zh.js"></script>
<script src="cookie.js"></script>
<script type="text/javascript" src="jquery-form.js"></script>
<script src="datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="upload-base.js"></script>
<script type="text/javascript">
  var mobile=/Android|webOS|iPhone|iPad|BlackBerry/i.test(navigator.userAgent);  //判断是否移动端
  $(function () {
       $('div.pinch-zoom').each(function () {
           new RTP.PinchZoom($(this), {});
       });           
  }); 
  function bigPic(obj){
	  if(mobile){  //移动端
		  $('.transparent,.pinch-zoom-container').show();
		  var src = $(obj).find('img').attr('src');
		  $('.pinch-zoom img').attr('src',src);
	  }else{  //PC端
		  var windowHeight = $(window).height();
		  var windowWidth = $(window).width();
		  var imgHeight = $(obj).find('img').height();
		  var imgWidth = $(obj).find('img').width();
		  var imgFlag = 0;
		  var imgSize = 0;
		  var imgSize1 = 0;
		  if(imgHeight>imgWidth){  //图片高度比宽度大
			  imgFlag = 1;
			  imgSize = windowHeight * imgFlag;
			  imgSize1=imgSize*imgWidth/imgHeight;
		  }else{  //图片宽度比高度大
			  imgFlag = imgWidth/imgHeight;
			  imgSize = windowWidth / imgFlag;
			  imgSize1=imgSize*imgHeight/imgWidth;
			  if(imgSize1>windowHeight){
				  imgSize1=windowHeight;
				  imgSize= imgFlag*imgSize1;
			  }
		  }
		  
		  
		  $('.big_pic').show();
		  var src = $(obj).find('img').attr('src');
		  $('.big_pic img').attr('src',src);
		  if(imgHeight>imgWidth){
		  $('.big_pic img').css("width",imgSize1);
		  $('.big_pic img').css("height",imgSize);
		  }else{
		  $('.big_pic img').css("width",imgSize);
		  $('.big_pic img').css("height",imgSize1);
		  }
	  }  
  };
  $('.big_pic').click(function(){
	  $('.big_pic').hide();
  });
  
 $('.transparent').click(function(){
		 $('.transparent,.pinch-zoom-container').hide();
 });
</script>
<script>
	
	
//判断是否回复显示
function showReply(obj){
	$(obj).css({'background-color':'rgb(56, 226, 219)','color':'#ffffff'});
	$(obj).siblings().each(function(){
		var isReply = $(this).attr('filed');
		if(isReply == 1){
			$(this).css({'background-color':'#027CFF','color':'#fff'});
		}else{
			$(this).css({'background-color':'#e5e5e5','color':'#fff'});
		}
	})
	var key = $(obj).index();
	$('.replys .reply').eq(key).show().siblings().hide();
}
	
	
	
	function goIndex(link){
		 var a = encodeURIComponent(link);
		 if(!$.cookie('name')){
			 window.location = 'https://www.kuaizhizao.cn/index.jsp?purchase_history='+a;
		 }else{
			 window.location = link;
		 }
	}
	
	
	
</script>
<script>


//退出功能
function exitlogin() {
	window.location.href = "${ctx}/index.jsp";
}


var projectNo = '${projectComplaint.projectNo}';
var projectName = '${project.projectName}';
var purchaseName = '${project.purchaseName}';
var customerName = '${project.customerName}';
var sellName = '${project.sellName}';
var zhijian1 = '${project.zhijian1}';
var zhijian2 = '${project.zhijian2}';
var zhijian3 = '${project.zhijian3}';
var productImg = '${project.productImg}';
var mailBody = $('#mailBody').val();
var title ='';
var desc='';
var img = '';
var a = '';
$(function(){
	 a = location.href;
     a = a.replace("http://117.144.21.74:10010","https://www.kuaizhizao.cn");
     a = a + "&type=1";
     img = productImg?'https://www.kuaizhizao.cn/product_img/'+productImg:'';
     title = projectNo + "质量跟踪单";     
     var inspection = zhijian1+" "+zhijian2+" "+zhijian3+"/";
     desc = projectName+"_"+(customerName?customerName+"_":'')+purchaseName+"/"+sellName+"/"+inspection;
	 //微信分享
    var shareInfo = {'url':a,'title':title,'desc':desc,'type':2,'img':img};
   
       
    if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {        
    	 $('.share_wechat').attr('onclick','shareChat('+JSON.stringify(shareInfo).replace(/\"/g,"'")+')');
    } else if (/(Android)/i.test(navigator.userAgent)) {
    	 $('.share_wechat').attr('onclick','shareChat('+JSON.stringify(shareInfo).replace(/\"/g,"'")+')');
    }else{
    	$('#share-qrcode').text('请点击微信扫码分享');
        //微信分享
		jsModern.share({
		    qrcode: "#share-qrcode"
		}); 
    }       
    
    
    
    
    
    $.ajax({
	     type:"post",                   
	     url:"${ctx}/project/queryMail",           
	     data:{
	    	 projectNo:projectNo
	     },              
	     success:function(json){
// 	    	 var json = eval("(" + data +")");
			 if(json.ok){
				  var sellMail = json.data.sellEmail;
				  var purchaseEmail = json.data.purchaseEmail;
				  var bossMail = "edwardfan@sourcing-cn.com";
				  var cc_mail = "cc=jieguangyang@sourcing-cn.com&cc=wangweiping@sourcing-cn.com&cc=jiangwenlong@sourcing-cn.com&cc=jerrylong@sourcing-cn.com&cc=edwardfan@sourcing-cn.com&cc=nina@sourcing-cn.com"; 
				  var cc_mail_pc = "cc=jieguangyang@sourcing-cn.com,wangweiping@sourcing-cn.com,jiangwenlong@sourcing-cn.com,jerrylong@sourcing-cn.com,edwardfan@sourcing-cn.com,nina@sourcing-cn.com"; 
				  var sendMail = sellMail;
				  var sendMail_pc = sellMail;
				  if(purchaseEmail){
					  sendMail = purchaseEmail;
					  sendMail_pc = sellMail + "," +purchaseEmail;
					  cc_mail = cc_mail + "&cc=" + sellMail;
				  }
		          var link = "";
		          var mailSubject = projectNo + "_" + projectName + "_客户质量投诉";
		          $('title').html(mailSubject);
		          $('meta[name="description"]').attr('content',mailSubject);
		          if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
		        	  link = 'mailto:'+sendMail+'?'+cc_mail+'&subject='+mailSubject+'&body=' + a + mailBody; 
	        	  } else if (/(Android)/i.test(navigator.userAgent)) {
	        		  link = 'mailto:'+sendMail+'?'+cc_mail+'&subject='+mailSubject+'&body=' + a + mailBody; 
	        	  }else{
	        		  link = 'mailto:'+sendMail_pc+'?'+cc_mail_pc+'&subject='+mailSubject+'&body=' + a + mailBody; 
	        	  }				          
		          $('#share_link').attr('href',link);	
			  }
	     }			
	})   
	
	
	
	//工艺列表
	var options = addProcess();
	$("#process_list").append(options);
})


//控制跳转回复逻辑
function sendReply(type){	
	
	 if($.cookie('name')){
		  if(type == 1){
			  layer.msg("你不是该项目采购或销售",{time:200000});
		  }else if(type ==2){
			  layer.msg("你不是该项目质检",{time:2000});   
		  }else if(type ==3){
			  layer.msg("你还没有技术人员权限",{time:2000});   
		  }		 
		 return false;
	 }else{
		 var a = location.href;
		 a = encodeURIComponent(a);
		 window.location = '/index.jsp?purchase_history='+a;
	 }

}




//准许出货
function shipment(id,type){
	var radios = document.getElementsByName("sure");
	var value = 0; for(var i=0;i<radios.length;i++){
		if(radios[i].checked == true){ 
			//alert(radios[i].value);
			value = radios[i].value;
			} 
		}
	var num=value;
	//如果没登录跳转登录页
    if(!$.cookie('name')){
    	 var a = location.href;
		 a = encodeURIComponent(a);
		 window.location = '/index.jsp?purchase_history='+a; 
	}
	
    //如果未获取到投诉表id直接返回
	if(!id){
		layer.msg("未获取到投诉表id",{time:2000});  
		return false;
	}
	 layer.open({
		type:1,
		skin:'finish-btn',
		title:'确认可以出货？',
		anim:4,
		shade:0.6,
		shadeClose:true,
		btn:['确定','取消'],
	}) 	
		
	$('.finish-btn .layui-layer-btn0').click(function(){
		
		
		 $.ajax({ 
			   url : "${ctx}/complaint/updateComplaint", 
			   type: "POST", 
			   data : { 
				  id:id,
				  type:type,
				  num:num
			   }, 
			   dataType:"json", 
			   success : function(json) { 
				   if(json.ok) 
				    { 
					   layer.msg("确认成功",{time:2000});  
					   window.location.reload();
				    }
			   }
		 }) 
	})
		
}

function delComplaintInspectionReport(id,projectComplaintId){	
	if(!id){
		return false;
	}
	var flag = confirm("确定删除产品检验报告?");
	if(flag){
	    $.ajax({ 
			   url : "/complaintInspectionReport/delete", 
			   type: "POST", 
			   data : { 
				  id:id 
			   }, 
			   dataType:"json", 
			   success : function(json) { 
				   if(json.ok) 
				    { 
					   layer.msg("删除成功",{time:2000});  
					   window.location = '${ctx}/complaint/queryComplaint?id='+projectComplaintId;
				    }
			   }
		 }) 
	}
}



function del(id){	
	if(!id){
		return false;
	}
	layer.open({
		type:1,
		skin:'finish-btn',
		title:'删除该投诉',
		anim:4,
		shade:0.6,
		shadeClose:true,
		btn:['确定','取消'],
	})	
	
	
	$('.finish-btn .layui-layer-btn0').click(function(){	
		 $.ajax({ 
			   url : "/complaint/delete", 
			   type: "POST", 
			   data : { 
				  id:id 
			   }, 
			   dataType:"json", 
			   success : function(json) { 
				   if(json.ok) 
				    { 
					   layer.msg("删除成功",{time:2000});  
					   window.location = '${ctx}/complaint/queryList?roleNo=${user.roleNo}&userId=${user.id}&userName=${user.userName}';
				    }
			   }
		 }) 
	})
}






//问题标签弹窗
$('.sel_btn').click(function(){
	
	  $.ajax({
		    type:"post",                   
		    url:"${ctx}/project/selectByIssue",           
		    data:{
		    	  issue:null
		    	 },              
		    success:function(json){  
// 		    	var json = eval("(" + data + ")");
				if (json.ok) {
					var issueList = json.data;
					$('#processList').empty();
					 if(issueList){
						 for(var i=0;i<issueList.length;i++){	
							 $('#processList').append('<li><input type="checkbox"><span>'+(issueList[i].process?issueList[i].process:'')+'</span>：<span>'+issueList[i].issue+'</span></li>');
						 }
					 }	 
				}else{
					   easyDialog.open({
				    		  container : {
				    		    content : data.message
				    		  },
				    		  autoClose : 2000
				    	});
				}
		    }
	})
	
	 $('.tc_bq').show();
});
$('.clo').click(function(){
	$('.tc_bq').hide();
});



/*
 * 添加质量、工艺问题
 */
function add_issue(){
	
	 var projectNo = $('#project_no').val();	   
     if(!projectNo){
  	   easyDialog.open({
 		  container : {
 		    content : '项目号不能为空'
 		  },
 		  autoClose : 2000
 		});
	   return false;
     }else{
	   //自动加上SHS，防止自动录入shs
	   projectNo = projectNo.toUpperCase().replace("SHS","");
	   projectNo = "SHS"+projectNo;
     }
	 var issue = $('#issue').val();	 
	 var qualityAnalysisId = $('#qualityAnalysisId').val();
	 var process = $('#process_list').val();
	 if(!issue){
	    easyDialog.open({
    		  container : {
    		    content : '问题不能为空'
    		  },
    		  autoClose : 2000
    		});	
		 return false;
	 }
	 if(issue.length>5){
	    easyDialog.open({
    		  container : {
    		    content : '问题在5个字以内'
    		  },
    		  autoClose : 2000
    		});	
		 return false;
	 }
	 if(!process){
	   easyDialog.open({
    		  container : {
    		    content : '工艺不能为空'
    		  },
    		  autoClose : 2000
    		});
		 return false;
	 }
	//投诉问题
	var isComplaint = 1;
	 
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/project/addAnalysisIssue",           
	     data:{
	    	 issue:issue,
	    	 projectNo:projectNo,
	    	 process : process,
	    	 qualityAnalysisId:qualityAnalysisId,
	    	 isComplaint:isComplaint,
	    	 complaintId:$('#complaintId').val()
	     },              
	     success:function(json){
// 	    	 var json = eval("(" + data +")");
			 if(json.ok){
			     easyDialog.open({
		    		  container : {
		    		    content : '保存成功'
		    		  },
		    		  autoClose : 2000
		    		});
			     $('#s_node').hide();
				 var issues = json.data;
// 				  $('.list').empty();
// 				  for(var i=0;i<issues.length;i++){
					  var li = '<li class="mt10" filed="'+issues.id+'"><span>'+issues.process+'：</span> <span>'+issues.issue+'</span> <span>出现过(<a href="/project/selectIssueList?issue='+issues.issue+'" class="blue"><i>'+issues.occurrenceNum+'</i></a>)次</span><button class="btn del" type="button" onclick="del_AnalysisIssue('+issues.id+',this)">删除</button></li>';
					  $('.list').append(li);
//                   }
			  }else{
				   easyDialog.open({
			    		  container : {
			    		    content : json.message
			    		  },
			    		  autoClose : 2000
			    	});
			  }
	     },
	     error:function(){
			   easyDialog.open({
		    		  container : {
		    		    content : json.message
		    		  },
		    		  autoClose : 2000
		    	});			    	
	     }
		})		 
}


/*
 * 批量添加质量、工艺问题
 */
function add_issue_batch(){

	   var projectNo = $('#project_no').val();	   
	   if(!projectNo){
		   easyDialog.open({
	    		  container : {
	    		    content : '项目号不能为空'
	    		  },
	    		  autoClose : 2000
	    		});
		   return false;
	   }else{
		   //自动加上SHS，防止自动录入shs
		   projectNo = projectNo.toUpperCase().replace("SHS","");
		   projectNo = "SHS"+projectNo;
	   }
	
	   
	var complaintId = $('#complaintId').val();   
	var issueList=[];
	$('.lis').find('li').each(function(){
		var This = $(this);
		if(This.find('input[type="checkbox"]').is(':checked')){
			var process = This.find('span:eq(0)').text();
			var issue = This.find('span:eq(1)').text();
			var analysisIssue = {"projectNo":projectNo,"issue":issue,"process":process,"isComplaint":1,"complaintId":complaintId};
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
// 	    	 var json = eval("(" + data +")");
			 if(json.ok){
				  easyDialog.open({
		    		  container : {
		    		    content : '保存成功'
		    		  },
		    		  autoClose : 2000
		    		});
				  $('#s_node').hide();
				  var issues = json.data;
// 				  $('.list').empty();
				  for(var i=0;i<issues.length;i++){
					  var li = '<li class="mt10"><span>'+issues[i].process+'：</span> <span>'+issues[i].issue+'</span> <span>出现过(<a href="/project/selectIssueList?issue='+issues[i].issue+'" class="blue"><i>'+issues[i].occurrenceNum+'</i></a>)次</span><button class="btn del" type="button" onclick="del_AnalysisIssue('+issues[i].id+',this)">删除</button></li>';
					  $('.list').append(li);
                  }
				  
			  }else{
				  easyDialog.open({
		    		  container : {
		    		    content : json.message 
		    		  },
		    		  autoClose : 2000
		    		});
			  }
	     },
	     error:function(){
	    	 easyDialog.open({
	    		  container : {
	    		    content : json.message 
	    		  },
	    		  autoClose : 2000
	    		});			    	
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
		
		var btnFn = function(){
			$.ajax({
			     type:"post",                   
			     url:"${ctx}/project/deleteAnalysisIssue",           
			     data:{
			         id:id
			     },              
			     success:function(json){
// 			    	 var json = eval("(" + data +")");
					 if(json.ok){
						$(obj).parents('li').remove();
					  }else{
						  easyDialog.open({
				    		  container : {
				    		    content : json.message 
				    		  },
				    		  autoClose : 2000
				    		});
					  }
			     },
			     error:function(){
			    	 easyDialog.open({
			    		  container : {
			    		    content : json.message 
			    		  },
			    		  autoClose : 2000
			    		});
			     }
				});
			  };

			easyDialog.open({
			  container : {
			    header : '提示信息',
			    content : '确认删除？',
			    yesFn : btnFn,
			    noFn : true
			  }
		});
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
// 	    	  var json = eval("(" + data +")");
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
				  easyDialog.open({
		    		  container : {
		    		    content : json.message 
		    		  },
		    		  autoClose : 2000
		    		});
			  }
	     },
	     error:function(){
	    	 easyDialog.open({
	    		  container : {
	    		    content : json.message 
	    		  },
	    		  autoClose : 2000
	    		});	    	
	     }
		})		 
}


//编辑问题标签
function editProcess(){
	if($('#process').css('display') == 'none'){
		$('#process').show();
	}else{
		$('#process').hide();
	}
	
}










   //微信分享
   function shareChat(shareInfo){
	   qqShare(shareInfo);
   }  
     
  
   
   
   $(function(){

	    //微信分享链接
	    var appId = "";
	    var timestamp = 0;
	    var nonceStr = "";
	    var signature = "";
        var id = '${projectComplaint.id}';

	    $.ajax({
	        async : false,
	        type : "GET",// 请求方式
	        url : "https://www.kuaizhizao.cn/wimpl/signature.do",// 地址，就是action请求路径
	        data : {
	            'pageUrl':window.location.href.split('#')[0]
	        },
	        xhrFields:{withCredentials:true},
	        dataType : "json",// 数据类型text xml json script jsonp
	        success : function(msg) {
	            appId = msg.appid;
	            timestamp = msg.timestamp;
	            nonceStr = msg.noncestr;
	            signature = msg.signature;
	        },
	        error : function() {
	            setTimeout(function(){
	                //window.location.href = "/fastermake-wechat/m-zh/error.html";
	            }, 0);

	        }
	    })


	    
	    wx.config({

	        debug: false, // true开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。

	        appId: appId, // 公众号的唯一标识

	        timestamp: timestamp, // 时间戳

	        nonceStr: nonceStr, // 随机串

	        signature: signature,// 签名

	        jsApiList: ['onMenuShareAppMessage','onMenuShareTimeline','hideMenuItems','showOptionMenu','showMenuItems'] // 需要使用的JS接口列表

	    });

	    wx.ready(function(){

	        //不隐藏菜单
	        wx.showOptionMenu();

	        //隐藏分享到QQ、QQ空间，微博和脸书功能
	        wx.hideMenuItems({

	            menuList: ['menuItem:share:qq','menuItem:share:QZone','menuItem:share:weiboApp','menuItem:share:facebook']

	        });
	        //开放分享给朋友、分享到朋友圈功能
	        wx.showMenuItems({

	            menuList: ['menuItem:share:appMessage','menuItem:share:timeline']

	        });
	        //分享给朋友
	        wx.onMenuShareAppMessage({

	            title: title, // 分享标题

	            desc: desc, // 分享描述

	            link : a, // 分享链接

	            imgUrl: img, // 分享图标

	            type: 'link', // 分享类型,music、video或link，不填默认为link

	            /* dataUrl: '',*/ // 如果type是music或video，则要提供数据链接，默认为空

	            success: function () {
	                // 用户确认分享后执行的回调函数

	                setTimeout(function(){
	                	 layer.msg("分享成功",{time:1000}); 
	                }, 0);

	            },

	            /*cancel: function () {
	                // 用户取消分享后执行的回调函数

	            }*/

	        });
	    })
	    })
	    
	    
	// 小图点击放大
	$('.small_imgs').click(function(){
		var src = $('.small_imgs img').attr('src');
		$('.big_pic_transparent,.big_pic').show();
		$('.big_pic img').attr('src',src);  
	});
  $('.big_pic_transparent').click(function(){
	  $('.big_pic_transparent,.big_pic').hide();
  })
  // 顶部返回箭头显示隐藏   
  $(document).scroll(function(){	 
	 var h =  $(document).scrollTop();
	 if(h > 0){
		 $('.back_top').show();
	 }else{
		 $('.back_top').hide();
	 }
	});
	
//上传方法
  function upload(obj){
  	   var fileNames = $(obj).val();
  	   if (fileNames == null || fileNames == '' || fileNames == undefined) {
  			return false;
  		}else{
  			autTime(); 
  			 $('#upload_title').children().text("上传进度");
  		}
  		// 先上传后获取上传文件路径
  		$('form').ajaxSubmit({
  			type : "post",
  			url : "${ctx}/complaint/uploadComplaintInspectionReport",
  			dataType : "text",
  			async : false,
  			success : function(str) {
  				var result = eval('(' + str + ')');
  				if (result.ok) {
  					var newFileName = result.data.newFileName;
  					var projectNo = result.data.projectNo;
					var fileName = result.data.originalFilename;
					var id = result.data.id;
					var projectComplaintId = result.data.projectComplaintId;
  				  if(newFileName){
 					 fileDiv = '<div><a href="/static_img/project_complaint/'+projectNo+'/'+newFileName+'">'+fileName+'</a></div>';
 				 }					 
 				 $('.complaintInspectionReport').before('<tr><td><div><div><em>整改报告文档</em></div>'+fileDiv+'</div><button class="btn del" onclick="delComplaintInspectionReport('+id+','+projectComplaintId+')">删除</button></td></tr>');			     
 			 
  				}else{
  					 layer.msg('上传失败',{time:2000});  
  				}
  			},
  			error : function() {
  				 layer.msg('上传失败',{time:2000}); 
  				 $('#show_upload_dialog').hide();
  			}
  		});
  }
  
</script>




