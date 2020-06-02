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
<title><c:if test="${factoryName!=null && factoryName!=''}">
				${factoryName}-工厂状态管理
				</c:if> <c:if test="${factoryName==null || factoryName==''}">
				 工厂状态管理
				</c:if></title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/add.css">
<link rel="stylesheet" href="../css/progressBar.css" />
<link rel="stylesheet" href="../css/ui.css" />
<link rel="stylesheet" href="../css/ui.progress-bar.css">
<link rel="stylesheet" href="${ctx}/css/easydialog.css" />
<style>
.facotry_project_list {
	max-width: 100% !important;
}
.facotry_project_list   .top .check {margin-right:10px;;}
.facotry_project_list table .th1{}
.facotry_project_list table .th2{width: 135px;}
.facotry_project_list table .th3{width: 110px;}
.facotry_project_list table .th4{}
.facotry_project_list table .th5{width: 55px;}
.facotry_project_list table .th6{}
.facotry_project_list table .th7{width: 55px;}
.facotry_project_list table .th8{width: 110px;}
.facotry_project_list table .th9{width: 110px;}
.facotry_project_list table .th10{}
.facotry_project_list table .th11{width: 55px;}
.facotry_project_list table .th12{width: 100px;}
.facotry_project_list table .th12 span{display:inline-block;width:100px;}
.facotry_project_list table .th13{width:65px;}
.facotry_project_list table .th14{}
.facotry_project_list table .th15{width: 110px;}
.facotry_project_list table .th16{}
.facotry_project_list table .th17{}
.facotry_project_list table .th18{width: 50px;}
.facotry_project_list table .th19{}
.facotry_project_list table .th20{width: 110px;}

 @media screen and (min-width: 768px){
.top .check {border:0 none;margin:0;width: unset;}
} 

</style>
</head>
<body>
	<div
		class="customer_complaint_B_detail  confirm_list  facotry_project_list  ">
		<div class="div_transparent"></div>
		<div class="top position_relative">
			<h1 class="customer_complaint_h1 top1">
				<c:if test="${factoryName!=null && factoryName!=''}">
				${factoryName}-工厂状态管理
				</c:if>
				<c:if test="${factoryName==null || factoryName==''}">
				  工厂状态管理
				</c:if>
			</h1>
			<div class="btns pull-right top2 position_absolute">
				<a class="select_blank btn" target="_blank" href="/user/toIndex">返回功能选择页</a>
				<!-- <a  class="btn" href="/project/showListNew">返回项目列表页</a>		 -->
			</div>
		</div>
		<div class="share">
			
		</div>
		<form id="form4" action="/project/queryFactoryProject" method="post">
			<input type="hidden" value="${factoryName}" name="factoryName">
			<textarea style="opacity: 0; width: 1px; height: 1px;" readonly
				id="copy"></textarea>
			<div class="clearfix top">
				<div class="form-group check clearfix">
					<span>项目性质</span> <select
						class="form-control display_inline_block w150" name="orderStatus">
						<option value="-1">全部</option>
						<option value="0" <c:if test="${orderStatus == 0}">selected</c:if>>样品</option>
						<option value="1" <c:if test="${orderStatus == 1}">selected</c:if>>大货</option>
						<option value="2" <c:if test="${orderStatus == 2}">selected</c:if>>返修/补货</option>
					</select>
				</div>
				<div class="form-group check clearfix">
					<span>项目状态</span> <select
						class="form-control display_inline_block w255" name="orderState">
						<option value="-1">全部</option>
						<option value="1" <c:if test="${orderState == 1}">selected</c:if>>在做模具</option>
						<option value="2" <c:if test="${orderState == 2}">selected</c:if>>在打样或小批量</option>
						<option value="3" <c:if test="${orderState == 3}">selected</c:if>>在大批量生产</option>
						<option value="4" <c:if test="${orderState == 4}">selected</c:if>>因为工厂原因还未做</option>
						<option value="5" <c:if test="${orderState == 5}">selected</c:if>>因为我司或者客户原因暂停</option>
						<option value="6" <c:if test="${orderState == 6}">selected</c:if>>已经部分交货，正在继续生产</option>
						<option value="7" <c:if test="${orderState == 7}">selected</c:if>>已经部分交货，目前暂停</option>
					</select>
				</div>
				<div class="form-group check clearfix">
					<span>其他筛选</span> <select
						class="form-control display_inline_block w255" name="otherSelect">
						<option value="-1">全部</option>
						<option value="1" <c:if test="${otherSelect == 1}">selected</c:if>>上传合同10天后，无开工视频</option>
					</select>
				</div>
				<div class="form-group check clearfix">
					<input type="text" class="form-control pull-left w255"
						name="selectStr" value="${selectStr}" placeholder="项目号/工厂名">
					<button type="button" class="btn bgcolor_ff0"
						onclick="searchProjectData(1)">查询</button>
				</div>
			</div>
			<!-- 新加表格star -->
			<table class="table table-bordered ">
				<thead>
					<tr>
						<th class="th1">工厂名</th>
						<th class="th2">项目号</th>
						<th class="th3">该项目上传地址</th>
						<th class="th4">项目名</th>
						<th class="th5">项目等级</th>
						<th class="th6">合同号</th>
						<th class="th7">性质</th>
						<th class="th8">合同交期</th>
						<th class="th9">完成日期</th>
						<th class="th10">项目状态</th>
						<th class="th11">最新检验计划</th>
						<th class="th12"><span>最近一次检验报告</span></th>
						<th class="th13">客户投诉</th>
						<th class="th14">合同总金额</th>
						<th class="th15">合同上传日期</th>
						<th class="th16">工厂生产视频</th>
						<th class="th17">工厂生产照片</th>
						<!-- <th class="th11">是否有检验报告</th> -->
						<th class="th18">采购解释</th>						
						<th class="th19">工厂看的所有项目列表地址</th>
						<th class="th20">内部观看地址</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="obj" items="${factoryList}" varStatus="i">
						<tr>
							<td><a
								href="/project/queryFactoryProject?factoryName=${obj.factoryName}">${obj.factoryName}</a></td>
							<td><a target="_blank"
								href="http://117.144.21.74:10010/project/showListNew?pageNumber=1&projectTypeS=&projectStageS=&plantAnalysisS=&detailStatusS=0,1,2,4,&delayStatusS=&inputKey=${obj.projectNo}&purchaseName=&documentaryName=&qualityName=&technician=&qualityReportSelect=&importantSelect=&expectedShipmentSelect=">${obj.projectNo}</a></td>
							
							<td><a class="" target="_blank" onclick="openNewPage('${obj.projectNo}','${obj.factoryId}');"
								 >新窗口打开</a>
							</td>
							<td>${obj.projectName}</td>
							<td>${obj.plantAnalysis == 0 ? '暂无' :(obj.plantAnalysis == 1 ? 'A' : (obj.plantAnalysis == 2 ? 'B' : obj.plantAnalysis == 3 ? 'C' : '暂无'))}</td>
							<td>${obj.contractNo}</td>
							<td>${obj.orderNature == 2? '返修/补货':(obj.deliveryDate!=null?'大货':(obj.sampleDate!=null?'样品':''))}</td>
							<td><c:if test="${obj.deliveryDate!=null}">
									<fmt:formatDate value="${obj.deliveryDate}"
										pattern="yyyy-MM-dd" />
								</c:if> <c:if test="${obj.deliveryDate==null}">
									<c:if test="${obj.sampleDate!=null}">
										<fmt:formatDate value="${obj.sampleDate}" pattern="yyyy-MM-dd" />
									</c:if>
								</c:if></td>
							<td><c:if test="${obj.deliveryDate!=null}">
									${obj.productFinishTime!=null?obj.productFinishTime:''}
								</c:if> <c:if test="${obj.deliveryDate==null}">
									<c:if test="${obj.sampleDate!=null}">
									${obj.sampleFinishTime!=null?obj.sampleFinishTime:''}
								</c:if>
								</c:if></td>

							<td>${obj.orderState == 1 ? '在做模具' : (obj.orderState == 2 ? '在打样或小批量' : (obj.orderState == 3 ? '在大批量生产' :(obj.orderState == 4 ? '因为工厂原因还未做' :(obj.orderState == 5 ? '因为我司或者客户原因暂停' : (obj.orderState == 6 ? '已经部分交货，正在继续生产' :   (obj.orderState == 7 ? '已经部分交货，目前暂停' : '未更新状态'))))))}</td>
							<td><c:if test="${obj.inspectionPlanList!=null }">
									<c:forEach var="cus" items="${obj.inspectionPlanList}"
										varStatus="i">
										<a
											href="http://117.144.21.74:33168/upload/po/upload/JIANYANJIHUAZJ/${cus.reportName }">下载</a>
									</c:forEach>
								</c:if>
								<c:if test="${obj.inspectionPlanList==null }">无</c:if></td>
							<td><c:if test="${obj.qualityReportList!=null }">
									<c:forEach var="cus" items="${obj.qualityReportList}"
										varStatus="i">


										<p>
											<a target="_blank"
												href="https://www.kuaizhizao.cn/quality/shareQuality?id=${cus.id}">
												<c:if test="${cus.type == 0}">样品检验</c:if> <c:if
													test="${cus.type == 2}">中期检验</c:if> <c:if
													test="${cus.type == 3}">终期检验</c:if> <c:if
													test="${cus.status == 0}">没问题</c:if> <c:if
													test="${cus.status == 1}">有问题,但已经处理</c:if> <c:if
													test="${cus.status == 2}">有问题,需要采购解决</c:if>
											</a> [${cus.user}/
											<fmt:formatDate value="${cus.createtime}"
												pattern="yyyy-MM-dd" />
											]

										</p>

									</c:forEach>
								</c:if>
								<c:if test="${obj.qualityReportList==null }">无</c:if></td>
							<td><c:if test="${obj.complaintList!=null }">
									<c:forEach var="cus" items="${obj.complaintList}" varStatus="i">
										<p>
											<a target="_blank"
												href="https://www.kuaizhizao.cn/complaint/queryComplaint?id=${cus.id}">

												${cus.id}质量投诉跟踪单</a> [${cus.createPerson}/
											<fmt:formatDate value="${cus.createTime}"
												pattern="yyyy-MM-dd" />
											]

										</p>

									</c:forEach>
								</c:if>
								<c:if test="${obj.complaintList==null }">无</c:if></td>

							<td>${obj.projectAmount}</td>
							<td><fmt:formatDate value="${obj.contractDate}"
									pattern="yyyy-MM-dd" /></td>
							<td><c:if test="${obj.isUploadVideo == true}">
									<span style="background-color: #2ef92e;">有</span>
								</c:if> <c:if test="${obj.isUploadVideo == false}">
									<span style="background-color: #eaaa4bd6;">无</span>
								</c:if></td>
							<%-- <td>
							   <c:if test="${obj.isUploadPic == true}">
							     <span style="background-color:#2ef92e;">有</span>
							   </c:if>
							   <c:if test="${obj.isUploadPic == false}">
							     <span style="background-color: #eaaa4bd6;">无</span>
							   </c:if>
							</td> --%>
							<td><c:if test="${obj.isUploadQualityReport == true}">
									<span style="background-color: #2ef92e;">有</span>
								</c:if> <c:if test="${obj.isUploadQualityReport == false}">
									<span style="background-color: #eaaa4bd6;">无</span>
								</c:if></td>
							<td></td>
							<%-- <td><a class="bgcolor_ff0"
								<c:if test="${obj.factoryId!=null && obj.factoryId != ''}">onclick="copyBtn(this,'${obj.projectNo}','${obj.factoryId}',1)"</c:if>>复制</a>
							</td> --%>
							<td><a class="bgcolor_ff0"
								<c:if test="${obj.factoryId!=null && obj.factoryId != ''}">onclick="copyBtn(this,'${obj.projectNo}','${obj.factoryId}',2)"</c:if>>复制</a></td>
							<td><a
								href="https://www.kuaizhizao.cn/report/reportListPurchase?csgOrderId=${obj.projectNo}&supplierId=${obj.factoryId}&factoryId=0&realName=ninazhao"
								target="_blank">新窗口打开</a> <%-- <a class="bgcolor_ff0"
								<c:if test="${obj.factoryId!=null && obj.factoryId != ''}">onclick="copyBtn(this,'${obj.projectNo}','${obj.factoryId}',3)"</c:if>>复制</a> --%></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- 新加表格end -->
			<%-- 	<ul>
		<c:forEach var="obj" items="${factoryList}" varStatus="i">
		<li <c:if test="${obj.factoryId != null}">onclick="window.location='https://www.kuaizhizao.cn/report/reportListPurchase?csgOrderId=${obj.projectNo}&supplierId=${obj.factoryId}&factoryId=0&realName=${userName}'"</c:if>>
			<span>${i.index+1}、</span>
			<span style="text-decoration: underline;color: blue;cursor: pointer;">${obj.projectNo}</span>
			<span>${obj.projectName}</span>
			<span>交期：<em><fmt:formatDate value="${obj.deliveryDate}" pattern="yyyy-MM-dd"/></em></span>
			<span>上次更新动态的日期：<em>${obj.processUpdateDate == '' || obj.processUpdateDate == null ?'暂无':obj.processUpdateDate}</em></span>
		<li>
		</c:forEach>
	</ul> --%>
			<input type="hidden" id="pageStr" name="pageStr"
				value="${page == null ? 1 : page}"> <input type="hidden"
				id="countPage" name="countPage" value="${lastNum}"> <input
				type="hidden" id="pageSize" name="pageSize"
				value="${pageSize == null ? 100 : pageSize}">
		</form>
		<!-- 	<p>分享工厂用的项目列表地址:</p>
	<div class="copy">
		<button class="btn bgcolor_ff0 display_block mt10 mb10" type="button" id="btn">非手机微信点我复制</button>
		<p>手机微信请长按下面地址复制:</p>
		<textarea id="foo" style="width: 101%;border: 1px solid;" readonly></textarea>
	</div> -->

		<div class="page-box mt10">
			总数:<span id="totalCount">${count}</span> 当前页/总页:<span
				style="color: red" id="pageNumberSpan">${page}</span>/ <span
				id="countPageSpan" style="color: red">${lastNum}</span>&nbsp; <a
				class="emanagergetpagea first-padding"
				onclick="searchProjectData(1)" title="第一页">首页</a> <a
				class="emanagergetpagea first-padding"
				<c:if test="${page > 1}">onclick="searchProjectData(2)"</c:if>
				title="上一页(第1页)">上页</a> <a class="emanagergetpagea"
				<c:if test="${page < lastNum}">onclick="searchProjectData(3)"</c:if>
				title="下一页(第3页)">下页</a> <a class="emanagergetpagea last_page"
				onclick="searchProjectData(4)" title="最后一页">尾页</a>
			<!-- 跳转到第<input type="text" class="n" value="n">页 -->
		</div>

	</div>
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script src="../js/base64.js"></script>
<script type="text/javascript" src="../js/easydialog.min.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript"
	charset="utf-8"></script>


<script type="text/javascript">
	var factoryId = '${factoryId}';
	$(function() {
		var base64 = new Base64();
		factoryId = base64.encode(factoryId);
		$('#foo').text(
				'https://www.kuaizhizao.cn/report/reportList?quoteStatus=2&supplierId='
						+ factoryId);
	})

	//查询
	function searchProjectData(obj) {
		var pageNumber = $("#pageStr").val();
		var countPage = $("#countPage").val();
		var pageSize = $("#pageSize").val();
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

	/* window.onload=function(event){
	 var button=document.getElementById("btn");
	 button.onclick=function(event){
	 document.getElementById("foo").select();
	 document.execCommand("copy",false,null);
	 layer.msg("复制成功",{time:2000});
	 };
	 }; */

	/**
	 *复制工厂上传链接
	 */
	function copyBtn(obj, projectNo, factoryId, type) {

		//工厂id base64加密 
		var base64 = new Base64();
		var factoryIdEncrypt = base64.encode(factoryId);
		if (type == 1) {
			$('#copy').text(
					"http://www.kuaizhizao.cn/report/reportList?csgOrderId="
							+ projectNo + "&supplierId=" + factoryIdEncrypt);
		}
		if (type == 2) {
			$('#copy').text(
					"https://www.kuaizhizao.cn/report/reportList?quoteStatus=2&supplierId="
							+ factoryIdEncrypt);
		}
		if (type == 3) {
			$('#copy').text(
					"https://www.kuaizhizao.cn/report/reportListPurchase?csgOrderId="
							+ projectNo + "&supplierId=" + factoryId
							+ "&factoryId=0&realName=ninazhao");
		}
		var $parm = $('#copy');
		var parm = $parm.get(0);
		parm.select();
		document.execCommand("copy", false, null);
		easyDialog.open({
			container : {
				content : '复制成功'
			},
			overlay : false,
			autoClose : 2000
		});
	}
	 
	 
	 function openNewPage( projectNo, factoryId) {
		 var base64 = new Base64();
			var factoryIdEncrypt = base64.encode(factoryId);
			window.open("http://www.kuaizhizao.cn/report/reportList?csgOrderId="
					+ projectNo + "&supplierId=" + factoryIdEncrypt);
	 }
</script>








