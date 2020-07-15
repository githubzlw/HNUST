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
<meta name="Description"   
	content="${shippingConfirmation.sampleOrProduct == 0 ? '样品_' : '大货_'}${shippingConfirmation.purchaseConfirm != null ? '' : (shippingConfirmation.purchaseName == null?'':shippingConfirmation.purchaseName)}/${shippingConfirmation.qualityLeaderConfirm != null ? '' : '阳工'}${(shippingConfirmation.purchaseLeaderConfirm == null && shippingConfirmation.isQualityLeaderConfirm == 1) ? '/姜工' : ''}${bossConfirm == true ? '/Ed': ''}_未签字" />


<title>准予出货确认单_${shippingConfirmation.serialNumber}_${shippingConfirmation.productName}</title>
<%-- <meta property="og:title" content="准予出货确认单_${shippingConfirmation.serialNumber}_${shippingConfirmation.productName}" />
<meta property="og:description" content="${shippingConfirmation.sampleOrProduct == 0 ? '样品_' : '大货_'}${shippingConfirmation.purchaseConfirm != null ? '' : (shippingConfirmation.purchaseName == null?'':shippingConfirmation.purchaseName)}/${shippingConfirmation.qualityLeaderConfirm != null ? '' : '阳工'}${(shippingConfirmation.purchaseLeaderConfirm == null && shippingConfirmation.isQualityLeaderConfirm == 1) ? '/姜工' : ''}${bossConfirm == true ? '/Ed': ''}_未签字">
<meta property="og:url" content="https://www.kuaizhizao.cn/complaint/detail?id=${shippingConfirmation.id}" />
<meta property="og:site_name" content="准予出货确认单" /> --%>
<meta name="twitter:card" content="summary">
<meta name="twitter:site" content="">
<meta name="twitter:title" content="准予出货确认单_${shippingConfirmation.serialNumber}_${shippingConfirmation.productName}">
<meta name="twitter:description" content="${shippingConfirmation.sampleOrProduct == 0 ? '样品_' : '大货_'}${shippingConfirmation.purchaseConfirm != null ? '' : (shippingConfirmation.purchaseName == null?'':shippingConfirmation.purchaseName)}/${shippingConfirmation.qualityLeaderConfirm != null ? '' : '阳工'}${(shippingConfirmation.purchaseLeaderConfirm == null && shippingConfirmation.isQualityLeaderConfirm == 1) ? '/姜工' : ''}${bossConfirm == true ? '/Ed': ''}_未签字">
<meta name="twitter:creator" content="${user.userName }">

<meta name="twitter:image" content="http://117.144.21.74:10010/product_img/${project.productImg}"> 
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="jsmodern-1.1.1.min.css">
<script type="text/javascript" src="easydialog.min.js"></script>
<link rel="stylesheet" href="add.css">
<link rel="stylesheet" href="progressBar.css" />
<link rel="stylesheet" href="ui.css" />
<link rel="stylesheet" href="ui.progress-bar.css">
<link rel="stylesheet" href="easydialog.css" />
<style type="text/css">
.zj_upload {
	opacity: 0;
	position: absolute;
	width: 65px;
}

.bgcolor_ff0 {
	background-color: #027CFF;
	color: #fff;
}

.del {
	float: right;
}


</style>

<style media="print" type="text/css">
	a[href]:after {
	    content: none !important;
	  } 
    .noprint {
    display:none
    }
 /*    .print {
        margin-left:25%
    }
     @("@")page 
    {
        size:  auto;   /* auto is the initial value */
        margin: 0mm;  /* this affects the margin in the printer settings */
    }   */
    
    
    
    
</style>


</head>

<body>
	<div
		class="customer_complaint_B_detail confirm_list confirm_list_share">
		<div id="print_div">
		<a href="#top" class="back_top"><img src="back_top.jpg"></a>
		<div class="big_pic_transparent"></div>
		<div class="big_pic">
			<img src="" class="img-responsive mt20">
		</div>
		<div class="div_transparent"></div>
		<div class="top">
			<h1 class="customer_complaint_h1 top1"></h1>
			电子准予出货确认单
			<div class="btns pull-right top2 no-print">
				<a class="select_blank btn"
					href="http://117.144.21.74:10010/user/toIndex">返回功能选择页</a> <a
					class="btn btn-default"
					href="http://117.144.21.74:10010/complaint/queryShippingList">返回确认单列表</a>
					<c:if test="${isSign == true}">
						<a class="btn btn-default"
						href="https://www.kuaizhizao.cn/complaint/detail?id=${id }&work=print" target="_blank" style="background-color: #8c8cff;" >打印专用页面</a>
					</c:if>
					<c:if test="${isSign == false}">
						<a class="btn btn-default"
						href="#" style="background-color: #ccc;">打印</a>
					</c:if>
			</div>


		</div>

		<%-- <c:if test="${userName==null||userName==''}">
			<div class="share mt10">
				<a onclick="sendReply()"
					style="color: red; background-color: #fff; text-decoration: underline;">您还未登录，如需回复，请先点击登录。</a>
			</div>
		</c:if> --%>
		<div class="d1 no-print">
			<span>确认单编码</span> - <span>${shippingConfirmation.serialNumber}</span>
		</div>
		<div class="no-print">
			<span>当前状态</span> :
			<c:if test="${shippingConfirmation.isComplete == 0 && isSign == false}">
				<span class="red">未填全不能签字</span>
			</c:if>
			<c:if test="${shippingConfirmation.isComplete == 0 && isSign == true}">
				<span class="red">可签字未签完</span>
			</c:if>
			<c:if test="${shippingConfirmation.isComplete == 1}">
				<span class="green">准予出货</span>
			</c:if>
		</div>

		<!-- 	<button class="btn btn-default bgcolor_ff0 share_btn">可点击本按钮分享到微信群(需使用QQ浏览器)</button> -->
		<a class="share_wechat btn share no-print" id="share-qrcode"
			style="color: #fff; background-color: #027CFF; width: 100%; white-space: normal;">可点击本按钮分享到微信群(需使用QQ浏览器)</a>
		<%-- <div	class="project_pic mt10">
		<img src="http://117.144.21.74:10010/product_img/${project.productImg}" class="img-responsive">
	</div> --%>
		<div class="clearfix mt10">
			<div class="pull-left list_left">
				<p>
					项目号：<span>${shippingConfirmation.projectNo}</span>
				</p>
				<p>
					项目名：<span>${project.projectName}</span>
				</p>
				<p>
					客户名：<span>${shippingConfirmation.customerName}</span>
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
				<p>
					船期时间： <span>${project.sailingDate!=null?project.sailingDate:'出运单系统暂无'}</span>
				</p>
			</div>
			<div class="pull-right small_imgs text-center no-print">
				<img
					src="http://117.144.21.74:10010/product_img/${project.productImg}"
					class="img-responsive">
			</div>
		</div>
		<div class="line mt10"></div>
		<h3 class="mt10">
			<b>第一步：基本的出货信息 </b>
			<c:choose>
				<c:when
					test="${(fn:containsIgnoreCase(userName, shippingConfirmation.purchaseName) && userName != '' && userName != null) || (fn:containsIgnoreCase(userName, shippingConfirmation.sellName) && userName != '' && userName != null) || userName eq 'ninazhao'}">
					<button class="btn btn-default bgcolor_ff0 no-print"
						style="background-color: #027CFF; color: #fff;"
						onclick="window.location='/complaint/toAdd/1?id=${shippingConfirmation.id}'">跟单/采购录入</button>
				</c:when>
				<c:otherwise>
					<button class="btn btn-default bgcolor_ff0"
						style="background-color: #ccc">跟单/采购录入</button>
				</c:otherwise>
			</c:choose>
		</h3>
		<c:choose>
			<c:when test="${shippingConfirmation.productName == null}">
				<span class="red">未录入</span>
			</c:when>
			<c:otherwise>
				<%-- <p><em>项目号：</em><span>${shippingConfirmation.projectNo}</span></p> --%>
				<%--  <p><em>客户名称：</em><span>${shippingConfirmation.customerName}</span></p> --%>
				<p>
					<em>货物名称：</em><span>${shippingConfirmation.productName}</span>
				</p>
				<p>
					<em>出货类型：</em><span>${shippingConfirmation.sampleOrProduct == 0 ? '样品' : '大货'}</span>
				</p>
				<p>
					<em>种类：</em><span>${shippingConfirmation.isPlastic == 1 ? '塑料件' : '其他'}</span>
				</p>
				<p>
					<em>运输方式：</em><span>${shippingConfirmation.shippingWay}</span>
				</p>
				<p>
					<em>实发数量：</em><span>${shippingConfirmation.productNumber} </span>
				</p>
				<p>
					<em>项目金额：</em><span>${project.projectAmount}万美元</span>
				</p>
				<p>
					<em>本产品单件(套)重量：</em><span>${shippingConfirmation.weight}kg</span>
				</p>
				<p>
					<em>发货前有没有发小批量给客户：</em><span>${shippingConfirmation.shippingInformation}</span>
				</p>
			</c:otherwise>
		</c:choose>


		<h3 class="mt10">
			<b>第二步：检验结果复述</b>
			<c:choose>
				<c:when
					test="${(userName != '' && userName != null) && (fn:containsIgnoreCase(userName, shippingConfirmation.zhijian1) || fn:containsIgnoreCase(userName, shippingConfirmation.zhijian2) || fn:containsIgnoreCase(userName, shippingConfirmation.zhijian3) || fn:containsIgnoreCase(userName, 'yanggong')|| userName eq 'ninazhao' || fn:containsIgnoreCase(userName, shippingConfirmation.sellName))}">
					<button class="btn btn-default bgcolor_ff0 no-print"
						style="background-color: #027CFF; color: #fff;"
						onclick="window.location='/complaint/toAdd/2?id=${shippingConfirmation.id}'">检验录入</button>
				</c:when>
				<c:otherwise>
					<button class="btn btn-default bgcolor_ff0"  
						style="background-color: #ccc">检验录入</button>
				</c:otherwise>
			</c:choose>
		</h3>
		<c:choose>
			<c:when test="${shippingConfirmation.checkQty == null}">
				<span class="red">未录入</span>
			</c:when>
			<c:otherwise>
				<p>
					<em>抽检产品数量：</em><span>${shippingConfirmation.checkQty}</span>
				</p>
				<p>
					<em>产品总数：</em><span>${shippingConfirmation.orders}</span>
				</p>
				<p>
					<em>包装数量：</em><span>${shippingConfirmation.boxNumber}</span>
				</p>
				<p>
					<em>开箱数量：</em><span>${shippingConfirmation.openQty}</span>
				</p>
				<p>
					<em>检验费时：</em><span>${fn:replace(fn:toUpperCase(shippingConfirmation.spendTime), 'H', '')}
						H</span>
				</p>
				<p>
					<em>发现的问题和比例：</em><span>${shippingConfirmation.checkConclusion}</span>
				</p>
				<p>
					<em>哪些预定的检验没能执行：</em><span>${shippingConfirmation.noExecuted}</span>
				</p>
				<p>
					<em>哪些问题是让步接受的：</em><span>${shippingConfirmation.concessiveAccept == null?'无':shippingConfirmation.concessiveAccept}</span>
				</p>
			</c:otherwise>
		</c:choose>

		<h3 class="mt10">
			<b>第三步：出货质量分析会的决议如下</b>
			<c:choose>
				<c:when
					test="${(userName != '' && userName != null) && (fn:containsIgnoreCase(userName, shippingConfirmation.zhijian1) || fn:containsIgnoreCase(userName, shippingConfirmation.zhijian2) || fn:containsIgnoreCase(userName, shippingConfirmation.zhijian3) || fn:containsIgnoreCase(userName, 'yanggong') || userName eq 'ninazhao'|| (fn:containsIgnoreCase(userName, shippingConfirmation.sellName) && userName != '' && userName != null))}">
					<button class="btn btn-default bgcolor_ff0 no-print"
						style="background-color: #027CFF; color: #fff;"
						onclick="window.location='/complaint/toAdd/3?id=${shippingConfirmation.id}'">检验/跟单录入</button>
				</c:when>
				<c:otherwise>
					<button class="btn btn-default bgcolor_ff0"
						style="background-color: #ccc">检验/跟单录入</button>
				</c:otherwise>
			</c:choose>
		</h3>
		<c:choose>
			<c:when test="${shippingConfirmation.meetingConclusion == null}">
				<span class="red">未录入</span>
			</c:when>
			<c:otherwise>
				<p class="blue">${shippingConfirmation.meetingConclusion}</p>
			</c:otherwise>
		</c:choose>


		<h3 class="mt10">
			<b>第四步：跟单销售确认</b>
			<c:choose>
				<c:when
					test="${(fn:containsIgnoreCase(userName, shippingConfirmation.purchaseName) && userName != '' && userName != null) || (fn:containsIgnoreCase(userName, shippingConfirmation.sellName) && userName != '' && userName != null) || userName eq 'ninazhao'}">
					<button class="btn btn-default bgcolor_ff0 no-print"
						style="background-color: #027CFF; color: #fff;"
						onclick="window.location='/complaint/toAdd/4?id=${shippingConfirmation.id}'">跟单/采购录入</button>
				</c:when>
				<c:otherwise>
					<button class="btn btn-default bgcolor_ff0"
						style="background-color: #ccc">跟单/采购录入</button>
				</c:otherwise>
			</c:choose>
		</h3>
		<c:choose>
			<c:when
				test="${shippingConfirmation.fourthPerson == null || shippingConfirmation.fourthPerson == ''}">
				<span class="red">未录入</span>
			</c:when>
			<c:otherwise>
				<p>
					<em>客户欠我们多少钱：</em><span>${shippingConfirmation.notPaid}</span>
					<c:if
						test="${shippingConfirmation.notPaid !=null && shippingConfirmation.notPaid !=''}">
						<span>美元</span>
					</c:if>
				</p>
				<p>
					<em>占总销售额比例：</em><span>${shippingConfirmation.scale}</span>
					<c:if
						test="${shippingConfirmation.scale !=null && shippingConfirmation.scale !=''}">
						<span>%</span>
					</c:if>
				</p>
				<p>
					<em>大货已经通过客户初步确认：</em><span>${shippingConfirmation.customerConfirmWay}</span>
				</p>
				<p>
					英文版检验报告：
					<c:if test="${shippingConfirmation.isQualityReportEn == 0}">
						<span>没提供</span>
					</c:if>
					<c:if test="${shippingConfirmation.isQualityReportEn == 1}">
						<span>已经提供</span>
					</c:if>
					<c:if test="${shippingConfirmation.isQualityReportEn == 2}">
						<span>一天内来补</span>
					</c:if>
					<c:if test="${shippingConfirmation.isQualityReportEn == 3}">
						<span>包装材料符合客户要求并保护得当 . 标签与内容相符合，唛头符合要求 </span>
					</c:if>
				</p>
				<p>
					<em>报关退税品名：</em><span>${shippingConfirmation.drawbackProduct}</span>
				</p>
				<p>
					<em>退税率：</em><span>${shippingConfirmation.drawbackRate}</span><span></span>
				</p>
				<p>
					<em>海运或空运费金额：</em><span>${shippingConfirmation.shippingFee}</span>
					<c:if
						test="${shippingConfirmation.shippingFee !=null && shippingConfirmation.shippingFee !=''}">
						<span>美元</span>
					</c:if>
				</p>
				<p>销售对质量的担忧：</p>
				<p>
					<span>${shippingConfirmation.salesWorry}</span>
				</p>
			</c:otherwise>
		</c:choose>

		<h3 class="mt10" style="margin-bottom: 10px;">
			<b>第五步：本次出运的投诉整改确认</b>
		</h3>
		<c:forEach var="obj" items="${complaintList}" varStatus="i">
			<c:if test="${obj.completeTime !=null}">
				<a
					href="https://www.kuaizhizao.cn/complaint/queryComplaint?id=${obj.id}">
					<em>#<fmt:formatNumber value="${obj.id}" pattern="0000" />投诉单
						${obj.projectNo}
				</em><em style="color: #83e016;">已完成</em>
				</a>
			</c:if>
			<c:if test="${obj.completeTime == null}">
				<a
					href="https://www.kuaizhizao.cn/complaint/queryComplaint?id=${obj.id}"><em>#<fmt:formatNumber
							value="${obj.id}" pattern="0000" />投诉单 ${obj.projectNo}
				</em> <em style="color: red;">未完成，不能进行后续签名流程</em></a>
			</c:if>
		</c:forEach>
		<c:if test="${complaintList == null || complaintList.size()==0}">
			<p class="blue">无投诉</p>
		</c:if>
		<p class="mt10">
			<%-- 	   <c:choose>
		    <c:when test="${shippingConfirmation.firstPerson == null || shippingConfirmation.secondPerson == null || shippingConfirmation.thirdPerson == null || shippingConfirmation.fourthPerson == null}">
			  <em class="red">未完成，不能进行后续签名流程</em>
			</c:when>
			<c:otherwise>
			  <em style="color:#83e016;">已完成</em>
			</c:otherwise>
	   </c:choose>	 --%>
		<p class="mt10">
			<b class="f16">其他签名流程前置确认：</b>
			<c:if
				test="${shippingConfirmation.sampleOrProduct == 0 && (shippingConfirmation.sampleFileName == null || shippingConfirmation.sampleFileName == '') && isSampleNoProblem == false}">
				<em class="red display_block">a、未检测到样品检验合格报告，且采购未上传样品检验报告，不能执行后续签字流程</em>
				<form onsubmit="return false;" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="projectNo"
						value="${shippingConfirmation.projectNo}"> <input
						type="hidden" name="id" value="${shippingConfirmation.id}">
					<input type="hidden" name="fileName" id="fileName"> <input
						type="file" name="file" class="pull-left zj_upload"
						onchange="upload(this)"><a href="###" class="red up display_block"><b>点我上传</b></a>
				</form>
			</c:if>
			<c:if test="${shippingConfirmation.sampleFileName != null}">
				<div>
					a、样品检验报告：<a
						href="http://117.144.21.74:10010/project_img/${shippingConfirmation.projectNo}/sample/${shippingConfirmation.sampleFileName}">${shippingConfirmation.sampleFileOriginalName}</a>
				</div>
			</c:if>
		</p>
		<c:if
			test="${(shippingConfirmation.sampleOrProduct == 1 && isProductNoProblem == false && productFileName == null && (shippingConfirmation.shipmentAgreement == null || shippingConfirmation.shipmentAgreement == ''))}">
			<form onsubmit="return false;" method="post"
				enctype="multipart/form-data">
				<div class="mt10">
					<p class="red">a、无大货终检合格的报告</p>
					<p class="position_relative sc mt5">
						<input type="hidden" name="projectNo"
							value="${shippingConfirmation.projectNo}"> <input
							type="hidden" name="id" value="${shippingConfirmation.id}">
						<input type="hidden" name="fileName"> <input type="file"
							class="position_absolute" name="file"
							onchange="uploadShipment(this)">点击上上传允许出货的证明
					</p>
				</div>
			</form>
		</c:if>
		<c:if
			test="${isProductNoProblem == false  && shippingConfirmation.shipmentAgreement != null && shippingConfirmation.shipmentAgreement != '' && isProductNoDingDing==true}">
			<div class="mt10">
				<p class="red">a、无大货终检合格的报告</p>
				<p class="mt5">但已上传允许出货的证明</p>
				<a
					href="http://117.144.21.74:10010/project_img/${shippingConfirmation.projectNo}/shipment/${shippingConfirmation.shipmentAgreement}">
					点击下载 <em>${shippingConfirmation.shipmentAgreement}</em>
				</a>
			</div>
		</c:if>
		<c:if
			test="${shippingConfirmation.sampleOrProduct == 1 && isProductNoProblem == true && isProductNoDingDing==true}">
			<div class="mt10">
				<p class="blue">a、有终检合格的报告</p>
			</div>
		</c:if>
		<c:if
			test="${productFileName != null && isProductNoProblem == false && isProductNoDingDing==true}">
			<div class="mt10">
				<p class="blue">a、终检有问题，但有上传整改完成的图纸、照片及视频</p>
				<p>整改结论：${operateExplain}</p>
				<a href="/static_img/project_img/${shippingConfirmation.projectNo}/product/${productFileName}" download="/static_img/project_img/${shippingConfirmation.projectNo}/product/${productFileName}">下载链接</a>
			</div>
		</c:if>
	   <c:if
			test="${isProductNoProblem == false  && shippingConfirmation.shipmentAgreement != null && shippingConfirmation.shipmentAgreement != '' && isProductNoDingDing==false}">
			<div class="mt10">
				<p class="red">a、无大货终检合格的报告</p>
				
				<p class="mt5">但已上传允许出货的证明</p>
				<a
					href="http://117.144.21.74:10010/project_img/${shippingConfirmation.projectNo}/shipment/${shippingConfirmation.shipmentAgreement}">
					点击下载 <em>${shippingConfirmation.shipmentAgreement}</em>
				</a>
				<p class="red">b、大货终检报告已自动传钉钉审批，但没有全部走完审批，需要催下 <button onclick="downloadData();" >实时查询审批结果</button></p>
				<c:forEach var="obj" items="${qualityReports2}"><a
					href="https://www.kuaizhizao.cn/quality/shareQuality?id=${obj.id}" target="_blank">
					查看质检报告 
				</a></c:forEach>
			</div>
		</c:if>
		<c:if
			test="${shippingConfirmation.sampleOrProduct == 1 && isProductNoProblem == true && isProductNoDingDing==false}">
			<div class="mt10">
				<p class="blue">a、有终检合格的报告</p>
				<p class="red">b、大货终检报告已自动传钉钉审批，但没有全部走完审批，需要催下 <button onclick="downloadData();">实时查询审批结果</button></p>
				<c:forEach var="obj" items="${qualityReports2}"><a
					href="https://www.kuaizhizao.cn/quality/shareQuality?id=${obj.id}" target="_blank">
					查看质检报告 
				</a></c:forEach>
			</div>
		</c:if>
		<c:if
			test="${productFileName != null && isProductNoProblem == false && isProductNoDingDing==false}">
			<div class="mt10">
				<p class="blue">a、终检有问题，但有上传整改完成的图纸、照片及视频</p>
				
				<p>整改结论：${operateExplain}</p>
				<a href="/static_img/project_img/${shippingConfirmation.projectNo}/product/${productFileName}" download="/static_img/project_img/${shippingConfirmation.projectNo}/product/${productFileName}">下载链接</a>
			<p class="red">b、大货终检报告已自动传钉钉审批，但没有全部走完审批，需要催下  <button onclick="downloadData();" >实时查询审批结果</button></p>
			<c:forEach var="obj" items="${qualityReports2}"><a
					href="https://www.kuaizhizao.cn/quality/shareQuality?id=${obj.id}" target="_blank">
					查看质检报告 
				</a></c:forEach>
			</div>
		</c:if>
	
	 <c:if test="${shippingConfirmation.sampleOrProduct == 0}">
			<div class="list mt10 no-print">
				<div class="">
					b、产品360度视频：
				</div>
				<c:forEach var="obj" items="${videoList}">
					<div class="video_div text-center">
						<video controls style="max-width: 100%;">
							<c:set value="${ fn:split(obj.qualityVideoName,'.') }" var="names" />
							<c:forEach items="${names}" var="name" varStatus="i">
								<c:if test="${i.index == 1 and name != 'mp4'}">
									<source
										src="/project_img/${obj.projectNo}/video/convert/${obj.videoFile}"
										type="video/mp4" />
								</c:if>
								<c:if test="${i.index == 1 and name eq 'mp4'}">
									<source
										src="/project_img/${obj.projectNo}/video/${obj.videoFile}"
										type="video/mp4" />
								</c:if>
							</c:forEach>
						</video>
					</div>
				</c:forEach>
				<c:if test="${videoList==null||videoList.size()==0}">
					<p>
						<a class="red" target="_blank"
							href="http://117.144.21.74:10010/uploadVideo/toUploadVideo?projectNo=${shippingConfirmation.projectNo}&userName=${userName}&videoType=4">暂无，点我上传</a>
					</p>
				</c:if>
			</div>
      </c:if>
<c:if test="${userName==null||userName==''}">
			<div class="share mt10">
				<a onclick="sendReply()"
					style="color: red; background-color: #fff; text-decoration: underline;">您还未登录，如需回复，请先点击登录。</a>
			</div>
		</c:if>

		<h3 class="mt10">
			<b>签名流程</b>
		</h3>
		<ul class="sign_ul">


			<c:if test="${shippingConfirmation.isComplete == 0 && isSign == true}">
				<c:if test="${shippingConfirmation.deliveryConfirmation==0}"><button  class="btn btn-default bgcolor_ff0 no-print"
						 style="background-color: #027CFF; color: #fff;"  onclick="deliveryConfirmation(${shippingConfirmation.id})" >点我开始签名</button></c:if>
				<c:if test="${shippingConfirmation.deliveryConfirmation==1}"><button  class="btn btn-default bgcolor_ff0 no-print" style="background-color: #027CFF; color: #fff;" >
					已生成钉钉签名流程</button></c:if>
			</c:if>
			<c:if test="${shippingConfirmation.isComplete == 1}">
				<button  class="btn btn-default bgcolor_ff0 no-print"
						 style="background-color: #027CFF; color: #fff;" >签名已完成</button>
			</c:if>
		<%--<li class="no-print"><em>跟单签名：</em>
				<div class="sure">
					<c:choose>
						<c:when test="${shippingConfirmation.salesConfirm != null}">
							<button class="btn btn-default">已签名确认</button>
							<span>日期:</span>
							<span><fmt:formatDate
									value="${shippingConfirmation.salesConfirmTime}"
									pattern="yyyy/MM/dd" /></span>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when
									test="${((fn:containsIgnoreCase(userName, shippingConfirmation.sellName) && userName != '' && userName != null) || userName eq 'ninazhao') && isSign == true }">
									<button class="btn btn-default bgcolor_ff0"
										style="background-color: #027CFF; color: #fff;"
										onclick="confirm('${shippingConfirmation.id}',0,0)">确认签名</button>
								</c:when>
								<c:otherwise>
									<button class="btn btn-default">不可签名</button>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div></li>
			<c:if
				test="${shippingConfirmation.purchaseName!=null && shippingConfirmation.purchaseName !=''}">
				<li class="no-print"><em>采购签名同意出货：</em>
					<div class="sure">
						<c:choose>
							<c:when test="${shippingConfirmation.purchaseConfirm != null}">
								<button class="btn btn-default">已签名确认</button>
								<span>日期:</span>
								<span><fmt:formatDate
										value="${shippingConfirmation.purchaseConfirmTime}"
										pattern="yyyy/MM/dd" /></span>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when
										test="${((fn:containsIgnoreCase(userName, shippingConfirmation.purchaseName) && userName != '' && userName != null) || userName eq 'ninazhao') && isSign == true}">
										<button class="btn btn-default bgcolor_ff0"
											style="background-color: #027CFF; color: #fff;"
											onclick="confirm('${shippingConfirmation.id}',1,0)">确认签名</button>
									</c:when>
									<c:otherwise>
										<button class="btn btn-default">不可签名</button>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div></li>
			</c:if>
			<li class="mt10">
				<div class="sure">
				<b>质量总监签名同意出货：</b>
					<c:choose>
						<c:when
							test="${shippingConfirmation.qualityLeaderConfirm != null}">
							<span><c:if test="${shippingConfirmation.qualityLeaderConfirmId==1 }">
							<button class="btn btn-default">已签名确认</button>
							</c:if>
							<c:if test="${shippingConfirmation.qualityLeaderConfirmId==2 }">
							<button class="btn btn-default bgcolor_ff0"
										style="background-color: #027CFF; color: #fff;"
										onclick="confirm('${shippingConfirmation.id}',2,1)">确认签名</button>
							<button class="btn btn-default">不同意出货</button>
							</c:if>
							<span>日期:</span>
							<span><fmt:formatDate
									value="${shippingConfirmation.qualityLeaderConfirmTime}"
									pattern="yyyy/MM/dd" /></span>
								</span>	
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when
									test="${(fn:containsIgnoreCase(userName, 'Tonyliao') || userName eq 'ninazhao' || userName eq 'yanggong') && isSign == true}">
									<button class="btn btn-default bgcolor_ff0"
										style="background-color: #027CFF; color: #fff;"
										onclick="confirm('${shippingConfirmation.id}',2,1)">确认签名</button>
									<button class="btn btn-default bgcolor_ff0"
										style="background-color: #027CFF; color: #fff;"
										onclick="confirm('${shippingConfirmation.id}',2,2)">不同意出货</button>
								</c:when>
								<c:otherwise>
									<button class="btn btn-default">未签名</button>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div></li>
			<c:if test="${shippingConfirmation.isQualityLeaderConfirm == 1}">
				<li class="mt10">
					<div class="sure">
					<b>采购副总裁(姜工)批准：</b>
						<c:choose>
							<c:when
								test="${shippingConfirmation.purchaseLeaderConfirm != null}">
								<span><c:if test="${shippingConfirmation.purchaseLeaderConfirmId==1 }">
								<button class="btn btn-default">已签名确认</button></c:if>
								<c:if test="${shippingConfirmation.purchaseLeaderConfirmId==2 }">
								<button class="btn btn-default bgcolor_ff0"
											style="background-color: #027CFF; color: #fff;"
											onclick="confirm('${shippingConfirmation.id}',3,1)">确认签名</button>
								<button class="btn btn-default">不同意出货</button></c:if>
								<span>日期:</span>
								<span><fmt:formatDate
										value="${shippingConfirmation.purchaseLeaderConfirmTime}"
										pattern="yyyy/MM/dd" /></span>
								</span>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when
										test="${(fn:containsIgnoreCase(userName, 'Jiangwenlong') || userName eq 'ninazhao') && isSign == true}">
										<button class="btn btn-default bgcolor_ff0"
											style="background-color: #027CFF; color: #fff;"
											onclick="confirm('${shippingConfirmation.id}',3,1)">确认签名</button>
											<button class="btn btn-default bgcolor_ff0"
											style="background-color: #027CFF; color: #fff;"
											onclick="confirm('${shippingConfirmation.id}',3,2)">不同意出货</button>
									</c:when>
									<c:otherwise>
										<button class="btn btn-default">未签名</button>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div></li>
			</c:if>
			<c:if test="${bossConfirm == true}">
				<li class="mt10">
					<div class="sure">
					<b>总经理批准：</b>
						<c:choose>
							<c:when test="${shippingConfirmation.bossConfirm != null }">
							<span><c:if test="${shippingConfirmation.bossConfirmId==1 }">
								<button class="btn btn-default">已签名确认</button>
								</c:if>
								<c:if test="${shippingConfirmation.bossConfirmId==2 }">
								<button class="btn btn-default bgcolor_ff0"
											style="background-color: #027CFF; color: #fff;"
											onclick="confirm('${shippingConfirmation.id}',4,1)">确认签名</button>
								<button class="btn btn-default">不同意出货</button></c:if>
								<span>日期:</span>
								<span><fmt:formatDate
										value="${shippingConfirmation.bossConfirmTime}"
										pattern="yyyy/MM/dd" /></span>
							</span></c:when>
							<c:otherwise>
								<c:choose>
									<c:when
										test="${(fn:containsIgnoreCase(userName, 'edward') || userName eq 'ninazhao') && isSign == true}">
										<button class="btn btn-default bgcolor_ff0"
											style="background-color: #027CFF; color: #fff;"
											onclick="confirm('${shippingConfirmation.id}',4,1)">确认签名</button>
											<button class="btn btn-default bgcolor_ff0"
											style="background-color: #027CFF; color: #fff;"
											onclick="confirm('${shippingConfirmation.id}',4,2)">不同意出货</button>
									</c:when>
									<c:otherwise>
										<button class="btn btn-default">未签名</button>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div></li>
					<li class="mt10">
					<span >注：本项目应涉及以下原因，需Ed签字批准</span><br/>
					<span>(请带上样品及检验报告)</span>
					<div >
						<c:if test="${shippingConfirmation.amountMoney != 0}">
								1.清关金额超过15000美金的货物出货，需Eddie签字
							</c:if>
							<c:if test="${shippingConfirmation.firstShipment != 0}">
								<br/>2.第一次出大货，且大于10000美元，需Eddie签字
							</c:if>
							<c:if test="${shippingConfirmation.complaint != 0}">
								<br/>3.上批货物出过问题或被投诉，本次出货需Eddie签字
							</c:if>
							<c:if test="${shippingConfirmation.productChanges != 0}">
								<br/>4.产品有改动，且大于10000美元，本次出货需Eddie签字
							</c:if>
							<c:if test="${shippingConfirmation.nonPlastic != 0}">
								<br/>5.非塑料类样品，且大于10000美元，出货需Eddie签字
							</c:if>
							<c:if test="${shippingConfirmation.grade != 0}">
								<br/>6.项目等级A/B级或客户等级A/B，且大于10000美元，出货需Eddie签字
							</c:if>
						
					</div></li>
			</c:if>--%>
		</ul>
		<div class="no-print">
			<span>当前状态：</span>
				<c:if test="${shippingConfirmation.isComplete == 1}">
					<span class="green">准予出货</span>
				</c:if>
				<c:if test="${shippingConfirmation.isComplete == 0 && isSign == false}">
				    <span class="red">未填全不能签字</span>
				</c:if>
				<c:if test="${shippingConfirmation.isComplete == 0 && isSign == true}">
					<span class="red">可签字未签完</span>
				</c:if>	
		</div>
		<c:if test="${qualityReports1!=null }"><div class="line mt10 no-print"></div>
		
		<div class="mt10">
			<h3>
				<b>项目下有问题的检验报告上的总结+沟通记录</b>
			</h3>
		</div>
        <table class="table table-bordered dp_tabel">
			<tbody class="dp_tabel_body">
				<c:forEach var="qualityReport" items="${qualityReports1}">
					 <tr>
						<td>
							<span>${qualityReport.detailView}</span>
				      <div>
					<b>检验结论：</b>
					<span class="z-color color_blue" id="conclusion">${qualityReport.conclusion}</span>
				    </div>
				  <c:if test="${qualityReport.comment!=null}"> <table class="table table-bordered dp_tabel">
						<tbody class="dp_tabel_body">
						<c:forEach var="obj" items="${qualityReport.comment}">
							<tr>
								<td>								
									<div>
										<div>
											<span>${obj.reviewer}</span>
											<em><fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></em>
										${obj.comment}
										</div>
										<!-- <div></div> -->
										<c:if test="${obj.newFileName != null && obj.newFileName != ''}">
										   <div>附件：<a href="/project_img/${obj.projectNo}/comment/${obj.newFileName}">${obj.fileName}</a></div>
									    </c:if>
									</div>
									<c:if test="${obj.reviewer eq userName}">
									<button class="btn del" onclick="del(this,'${obj.id}')">删除</button>								
									</c:if>							 
								</td>	
								</tr>
							</c:forEach>															
						</tbody>
					</table></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table></c:if>
		<div class="line mt10 no-print"></div>
		
		<div class="mt10 no-print">
			<h3>
				<b>大众点评</b>
			</h3>
			<div class="clearfix no-print">
				<textarea class="form-control mt10" id="comment"></textarea>
				<button class="btn pull-right display_block mt10" type="button"
					onclick="sendComment('${shippingConfirmation.projectNo}','${shippingConfirmation.id}')">点评</button>
			</div>
		</div>

		<table class="table table-bordered dp_tabel no-print">
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
							</div> <c:if test="${obj.reviewer eq userName}">
								<button class="btn del" onclick="del(this,'${obj.id}')">删除</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<div class="line mt10"></div>


		<div class="list mt10 no-print">
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
							<td class="w70"><a
								onclick="window.location='https://www.kuaizhizao.cn/quality/shareQuality?id=${report.id}'"
								class="btn btn-defbgcolor_ff0r_ff0">查看</a></td>
						</tr>
					</c:forEach>
					<c:if
						test="${qualityReports == null || qualityReports.size() == 0}">
					   暂无
					</c:if>
				</tbody>
			</table>
		</div>
		
		</div>

		<div class="task">
			<h3 class="mt10">
				<b>本项目相关任务：</b>
			</h3>
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
				</tbody>

			</table>
		</div>
		<div class="line mt10"></div>
		<div class="mt10">
			<div>
				<em>质量协议中的项目问题点关键词：</em> <span class="blue"> <c:choose>
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
				</span>
			</div>
			<div>
				<c:if test="${qualityAnalysis != null && qualityAnalysis != ''}">
					<em>采购分析：</em>
					<span class="blue">${qualityAnalysis.purchaseReply == null ? '暂无回复' : qualityAnalysis.purchaseReply}</span>
				</c:if>
			</div>
		</div>
		<!-- 	<a class="share_wechat btn share" id="share-qrcode">可点击本按钮分享到微信群(需使用QQ浏览器)</a> -->
	</div>
</body>
</html>
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
<script type="text/javascript" src="jquery-form.js"></script>
<script src="layer.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="cookie.js"></script>
<script type="text/javascript" src="shareWechat.js"></script>
<script type="text/javascript" src="upload-base.js"></script>
<script type="text/javascript" src="jquery-form.js"></script>
<script type="text/javascript" src="easydialog.min.js"></script>
<script type="text/javascript"
	src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="jsmodern-1.1.1.min.js"></script>

<script src="jQuery.print.js"></script>

<script type="text/javascript">
	//确认签名  0:销售  1：采购 2：质检总监   3：采购总经理  4：总裁
	function confirm(id, type,num) {

		var btnFn = function() {
			if (!id) {
				layer.msg("id不能为空", {
					time : 2000
				});
				return false;
			}
			$.ajax({
				type : "post",
				url : "/complaint/addFirst",
				data : {
					id : id,
					type : type,
					num : num
				},
				success : function(json) {
// 					var json = eval("(" + result + ")");
					if (json.ok) {
						layer.msg("确认成功", {
							time : 2000
						});
						window.location = '/complaint/detail?id=' + id;
					} else if (json.message == '您还未登录') {
						var a = location.href;
						a = encodeURIComponent(a);
						window.location = '/index.jsp?purchase_history=' + a;
					} else {
						layer.msg(json.message, {
							time : 2000
						});
					}
				}
			})
			return false
		};

		easyDialog.open({
			container : {
				header : '提示信息',
				content : '确认签名？',
				yesFn : btnFn,
				noFn : true
			}
		});
	}
</script>
<script type="text/javascript">
	var projectNo = '${shippingConfirmation.projectNo}';
	var productName = '${shippingConfirmation.productName}';
	var purchaseName = '${shippingConfirmation.purchaseName}';
	var sellName = '${shippingConfirmation.sellName}';
	var zhijian1 = '${shippingConfirmation.zhijian1}';
	var zhijian2 = '${shippingConfirmation.zhijian2}';
	var zhijian3 = '${shippingConfirmation.zhijian3}';
	var productImg = '${project.productImg}';
	var title = '';
	var desc = '';
	var img = '';
	var a = '';
	$(function() {
		a = location.href;
		a = a
				.replace("http://117.144.21.74:10010",
						"https://www.kuaizhizao.cn");
		img = productImg ? 'http://117.144.21.74:10010/product_img/'
				+ productImg : '';
		title = projectNo + "_出货确认单_" + productName;
		var inspection = zhijian1 + " " + zhijian2 + " " + zhijian3 + "/";
		desc = purchaseName + "/" + sellName + "/" + inspection;
		//微信分享
		var shareInfo = {
			'url' : a,
			'title' : title,
			'desc' : desc,
			'type' : 2,
			'img' : img
		};

		if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
			$('.share_wechat').attr(
					'onclick',
					'shareChat('
							+ JSON.stringify(shareInfo).replace(/\"/g, "'")
							+ ')');
		} else if (/(Android)/i.test(navigator.userAgent)) {
			$('.share_wechat').attr(
					'onclick',
					'shareChat('
							+ JSON.stringify(shareInfo).replace(/\"/g, "'")
							+ ')');
		} else {
			$('#share-qrcode').text('请点击微信扫码分享');
			//微信分享
			jsModern.share({
				qrcode : "#share-qrcode"
			});
		}

	})

	//微信分享
	function shareChat(shareInfo) {
		qqShare(shareInfo);
	}

	//控制跳转回复逻辑
	function sendReply() {
		var a = location.href;
		a = encodeURIComponent(a);
		window.location = '/index.jsp?purchase_history=' + a;
	}
    //控制跳转回复逻辑
    function downloadData() {
		$.ajax({
			type : "post",
			url : "https://www.kuaizhizao.cn/Ding-Talk/getMessage3",
			data : {

			},
			success : function(json) {

				layer.msg("数据拉取成功", {
					time: 2000
				});

			}
		})

    }
	//控制跳转回复逻辑
	function deliveryConfirmation(id) {
		$.ajax({
					type : "post",
					url : "/complaint/updateDeliveryConfirmation",
					data : {
						id:id

					},
					success : function(json) {

						window.location.reload();

					}
				})



	}

	//上传文件
	function upload(obj) {
		var path = $(obj).val();
		sppath = path.split("\\");
		var fileName = sppath[sppath.length - 1];
		if (fileName == null || fileName == '' || fileName == undefined) {
			return false;
		} else {
			$('#fileName').val(fileName);
			autTime();
			$('#upload_title').children().text("上传进度");
		}

		//先上传后获取上传文件路径
		$(obj).parents('form').ajaxSubmit({
			type : "post",
			url : "/complaint/sampleUpload",
			dataType : "text",
			success : function(str) {
				var result = eval('(' + str + ')');
				var newFileName = result.data;
				if (result.ok) {
					layer.msg('上传成功', {
						time : 2000
					});
				} else {
					layer.msg('上传失败', {
						time : 2000
					});
				}
			},
			error : function() {
				layer.msg('上传失败', {
					time : 2000
				});
				$('#show_upload_dialog').hide();
			}
		});

	}

	//上传出货证明
	function uploadShipment(obj) {
		var path = $(obj).val();
		sppath = path.split("\\");
		var fileName = sppath[sppath.length - 1];
		if (fileName == null || fileName == '' || fileName == undefined) {
			return false;
		} else {
			$(obj).parents('form').find('input[name="fileName"]').val(fileName);
			autTime();
			$('#upload_title').children().text("上传进度");
		}

		//先上传后获取上传文件路径
		$(obj).parents('form').ajaxSubmit({
			type : "post",
			url : "/complaint/shipmentAgreementUpload",
			dataType : "text",
			success : function(str) {
				var result = eval('(' + str + ')');
				var newFileName = result.data;
				if (result.ok) {
					layer.msg('上传成功', {
						time : 2000
					});
					setTimeout(function() {
						window.location.reload();
					}, 2000);
				} else {
					layer.msg('上传失败', {
						time : 2000
					});
				}
			},
			error : function() {
				layer.msg('上传失败', {
					time : 2000
				});
				$('#show_upload_dialog').hide();
			}
		});

	}

	//发送评论
	function sendComment(projectNo, shippingId) {
		if (!$.cookie('name')) {
			var a = location.href;
			a = encodeURIComponent(a);
			window.location = 'https://www.kuaizhizao.cn/index.jsp?purchase_history='
					+ a;
		}

		var comment = $('#comment').val();
		if (!comment) {
			layer.msg("留言不能为空", {
				time : 2000
			});
			return false;
		}
		var newFileName = $('#newFileName').val();
		var fileName = $('#fileName').val();

		$
				.ajax({
					type : "post",
					url : "${ctx}/project/projectComment",
					data : {
						projectNo : projectNo,
						shippingId : shippingId,
						comment : comment,
						fileName : fileName,
						newFileName : newFileName
					},
					success : function(json) {
// 						var json = eval("(" + data + ")");
						if (json.ok) {
							$('.dp_tabel_body')
									.before(
											'<tr><td><div><div><span>'
													+ json.data.userName
													+ ' </span><em>'
													+ json.data.createDate
													+ '</em></div><div>'
													+ comment
													+ '</div></div><button class="btn del" onclick="del(this,\''
													+ json.data.id
													+ '\')">删除</button></td></tr>');

						} else {
							if (json.message == '请登录') {
								var a = location.href;
								a = encodeURIComponent(a);
								window.location = 'https://www.kuaizhizao.cn/index.jsp?purchase_history='
										+ a;
							}
						}
					}
				})
	}

	//删除评论
	function del(obj, id) {

		layer.open({
			type : 1,
			skin : 'finish-btn',
			title : '是否确认删除',
			anim : 4,
			shade : 0.6,
			shadeClose : true,
			btn : [ '确定', '取消' ],
		})

		$('.finish-btn .layui-layer-btn0').click(function() {
			$.ajax({
				type : "post",
				url : "${ctx}/project/delComment",
				data : {
					id : id
				},
				success : function(json) {
// 					var json = eval("(" + data + ")");
					if (json.ok) {
						$(obj).parent().parent().remove();
					} else {
						layer.msg("删除失败", {
							time : 2000
						});
					}
				}
			})
		})
	}

	$(function() {

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
				'pageUrl' : window.location.href.split('#')[0]
			},
			xhrFields : {
				withCredentials : true
			},
			dataType : "json",// 数据类型text xml json script jsonp
			success : function(msg) {
				appId = msg.appid;
				timestamp = msg.timestamp;
				nonceStr = msg.noncestr;
				signature = msg.signature;
			},
			error : function() {
				setTimeout(function() {
					//window.location.href = "/fastermake-wechat/m-zh/error.html";
				}, 0);

			}
		})

		wx.config({

			debug : false, // true开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。

			appId : appId, // 公众号的唯一标识

			timestamp : timestamp, // 时间戳

			nonceStr : nonceStr, // 随机串

			signature : signature,// 签名

			jsApiList : [ 'onMenuShareAppMessage', 'onMenuShareTimeline',
					'hideMenuItems', 'showOptionMenu', 'showMenuItems' ]
		// 需要使用的JS接口列表

		});

		wx.ready(function() {

			//不隐藏菜单
			wx.showOptionMenu();

			//隐藏分享到QQ、QQ空间，微博和脸书功能
			wx.hideMenuItems({

				menuList : [ 'menuItem:share:qq', 'menuItem:share:QZone',
						'menuItem:share:weiboApp', 'menuItem:share:facebook' ]

			});
			//开放分享给朋友、分享到朋友圈功能
			wx.showMenuItems({

				menuList : [ 'menuItem:share:appMessage',
						'menuItem:share:timeline' ]

			});
			//分享给朋友
			wx.onMenuShareAppMessage({

				title : title, // 分享标题

				desc : desc, // 分享描述

				link : a, // 分享链接

				imgUrl : img, // 分享图标

				type : 'link', // 分享类型,music、video或link，不填默认为link

				/* dataUrl: '',*/// 如果type是music或video，则要提供数据链接，默认为空
				success : function() {
					// 用户确认分享后执行的回调函数

					setTimeout(function() {
						layer.msg("分享成功", {
							time : 1000
						});
					}, 0);

				},

			/*cancel: function () {
			    // 用户取消分享后执行的回调函数
			
			}*/

			});
		})
		

	// 小图点击放大
	$('.small_imgs').click(function() {
		var src = $('.small_imgs img').attr('src');
		$('.big_pic_transparent,.big_pic').show();
		$('.big_pic img').attr('src', src);
	});
	$('.big_pic_transparent').click(function() {
		$('.big_pic_transparent,.big_pic').hide();
	})
	// 顶部返回箭头显示隐藏   
	$(document).scroll(function() {
		var h = $(document).scrollTop();
		if (h > 0) {
			$('.back_top').show();
		} else {
			$('.back_top').hide();
		}
	});


	 
 });
 
 
//打印
function print(){
	   //$("#print_div").jqprint();

	   $("#print_div").print({
        //Use Global styles
        globalStyles : true,
        //Add link with attrbute media=print
        mediaPrint : false,
        //Print in a hidden iframe
        iframe : false,
        //Don't print this
        noPrintSelector : ".no-print",
        //Log to console when printing is done via a deffered callback
        deferred: $.Deferred().done(function() { console.log('Printing done', arguments); })
    });
 }
</script>








