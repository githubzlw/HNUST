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
<meta name="twitter:card" content="summary">
<meta name="twitter:site" content="">
<meta name="twitter:title" content="准予出货确认单_${shippingConfirmation.serialNumber}_${shippingConfirmation.productName}">
<meta name="twitter:description" content="${shippingConfirmation.sampleOrProduct == 0 ? '样品_' : '大货_'}${shippingConfirmation.purchaseConfirm != null ? '' : (shippingConfirmation.purchaseName == null?'':shippingConfirmation.purchaseName)}/${shippingConfirmation.qualityLeaderConfirm != null ? '' : '阳工'}${(shippingConfirmation.purchaseLeaderConfirm == null && shippingConfirmation.isQualityLeaderConfirm == 1) ? '/姜工' : ''}${bossConfirm == true ? '/Ed': ''}_未签字">
<meta name="twitter:creator" content="${user.userName }">

<meta name="twitter:image" content="https://www.kuaizhizao.cn/product_img/${project.productImg}">
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
b{font-weight:normal;}
.confirm_list_share p{display:inline-block;}
.confirm_list h3{display: inline-block;}
.table>tbody>tr>td{padding:0;}
.table{margin-bottom: 0;}
.table>tbody>tr>td{border:0 none;}
.add_print_img a{display:block;float:left;width:500px;height:500px;margin-right:20px;}


@media screen and (min-width: 768px){
.confirm_list{font-size:13px;}
.confirm_list h3{font-size:14px;}
}
.confirm_list_share span,.confirm_list_share em{font-size:13px;}
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
			<div class="btns pull-right top2 no-print">
				
					
			</div>
        </div>
        <%--  <div class="d1 no-print">
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
			
		</div>
	
		<span class="mt10">
			<b>第一步：基本的出货信息 </b>
			
		</span>
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
			</c:otherwise>
		</c:choose>

		
		<div class="mt10">
			<b>第二步：检验结果复述</b>
			<%-- <c:choose>
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
			</c:choose> --%>
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

		<div class="mt10">
			<b>第三步：出货质量分析会的决议如下</b>
			
		</h3>
		<c:choose>
			<c:when test="${shippingConfirmation.meetingConclusion == null}">
				<span class="red">未录入</span>
			</c:when>
			<c:otherwise>
				<p class="blue">${shippingConfirmation.meetingConclusion}</p>
			</c:otherwise>
		</c:choose>


		<div class="mt10">
			<b>第四步：跟单销售确认</b>
			
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
		
		
			


		
		<ul class="sign_ul">
			<c:if test="${bossConfirm == true}">
				<span >注：本项目应涉及以下原因，需Ed签字批准</span>
					<span>(请带上样品及检验报告)</span>
					<div style="display:inline-block;">
						<c:if test="${shippingConfirmation.amountMoney != 0}">
								1.清关金额超过15000美金的货物出货，需Eddie签字
							</c:if>
							<c:if test="${shippingConfirmation.firstShipment != 0}">
								2.第一次出大货，需Eddie签字
							</c:if>
							<c:if test="${shippingConfirmation.complaint != 0}">
								3.上批货物出过问题或被投诉，本次出货需Eddie签字
							</c:if>
							<c:if test="${shippingConfirmation.productChanges != 0}">
								4.产品有改动，本次出货需Eddie签字
							</c:if>
							<c:if test="${shippingConfirmation.nonPlastic != 0}">
								5.非塑料类样品，出货需Eddie签字
							</c:if>
							<c:if test="${shippingConfirmation.grade != 0}">
								6.项目等级A/B级或客户等级A/B，出货需Eddie签字
							</c:if>
						
					</div></li>
			</c:if>
			
				<div class="no-print" style="display:inline-block;">
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
			
		</ul>
		
		<c:if test="${qualityReports1!=null }">
		
		<div class="mt10">
			<span>
				<b>项目下有问题的检验报告上的总结+沟通记录</b>
			</h3>
		</div>
        <table class="table  dp_tabel">
			<tbody class="dp_tabel_body">
				<c:forEach var="qualityReport" items="${qualityReports1}">
					 <tr>
						<td>
							<span>${qualityReport.detailView}</span>
				      <div>
					<b>检验结论：</b>
					<span class="z-color color_blue" id="conclusion">${qualityReport.conclusion}</span>
				    </div>
				  <c:if test="${qualityReport.comment!=null}"> <table class="table dp_tabel">
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
		
		<div class="add_print_img">
			<c:forEach var="picReport" items="${picList}">					
					<a><img src="${picReport.picName }" alt="" class="img-responsive"></a>						
			</c:forEach>
		</div>
		
		
		<%--  <table class="table  dp_tabel">
			<tbody class="dp_tabel_body">
				<c:forEach var="picReport" items="${picList}">
					 <tr>
						<td>
							<img src="${picReport.picName }" alt="">
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table> --%>
		</div>

		
		
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
		img = productImg ? 'https://www.kuaizhizao.cn/product_img/'
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








