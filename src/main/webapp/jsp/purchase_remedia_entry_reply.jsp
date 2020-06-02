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
<title>采购整改结果录入回复</title>
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet"
	href="datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="add.css">
<link rel="stylesheet" href="progressBar.css" />
<link rel="stylesheet" href="ui.css" />
<link rel="stylesheet" href="ui.progress-bar.css">
<style>
.mt15{margin-top:15px;}
.purchase_entry_reply .top2 {
	right: 0;
	bottom: 45px;
}

form input[type="file"] {
	position: absolute;
	top: 0;
	left: 0;
	width:85px;
	height:30px;
	opacity:0;
}
.remedia_entry_reply form{position:relative;}
.remedia_entry_reply .dp{margin-bottom:15px;}
.file_name{position: relative;left:10px;height: 33px;width: 195px;display: block;
	    white-space: nowrap;overflow: hidden;text-overflow: ellipsis;line-height:33px;font-size:16px;
}
#file_input{top:-29px;}
</style>
</head>
<body>
	<div class="purchase_entry_reply remedia_entry_reply">
		<div class="top">
			<div class="top1">
				<h3>
					项目名：<span>${projectComplaint.projectNo}</span>
				</h3>
				<h3 class="mt10">
					客户名：<span>${projectComplaint.customerName}</span>
				</h3>
				<h3 class="mt10">
					电子质量跟踪单——采购/跟单整改结果录入回复
					<div class="btns top2">
						<a class="btn select_blank" onclick="window.history.back()">返回</a>
					</div>
				</h3>
			</div>
		</div>
		<!-- <form id="form" onsubmit="return false;" method="post" enctype="multipart/form-data"> -->
		<input type="hidden" name="complaintId" id="complaintId"
			value="${projectComplaint.id}"> <input type="hidden"
			name="replyList" id="replyList"> <input type="hidden"
			name="replyType" value="4"> <input type="hidden"
			name="roleNo" value="${user.roleNo}">

		<div class="time_name mb10">
			<c:if
				test="${projectComplaint.rectificationSellPurchaseReply != null}">
				<p class="clearfix">
					<em class="pull-left">采购/跟单整改结果录入时间:</em><span class="pull-left">
						<fmt:formatDate
							value="${projectComplaint.rectificationSellPurchaseTime}"
							pattern="yyyy-MM-dd" />
					</span><span class="pull-right">${projectComplaint.rectificationSellPurchaseReply}</span>
				</p>
			</c:if>
			<c:if test="${projectComplaint.rectificationZhijianReply != null}">
				<p class="clearfix">
					<em class="pull-left">质检整改结果录入时间:</em><span class="pull-left">
						<fmt:formatDate
							value="${projectComplaint.rectificationZhijianTime}"
							pattern="yyyy-MM-dd" />
					</span> <span class="pull-right">${projectComplaint.rectificationZhijianReply}</span>
				</p>
			</c:if>
		</div>
		<c:forEach var="obj" items="${issueList}" varStatus="i">
			<div class="form-group mt5 reply_div">
				<p>
					<em>问题${i.index+1}:</em><span class="reply" filed="${obj.id}">${obj.issue}</span>
				</p>


			</div>
		</c:forEach>



		<c:if
			test="${user.roleNo == 5 || user.roleNo == 6 ||user.roleNo==1020 ||user.roleNo==100}">
			<div class="panel dp_panel">
				<div class="panel-body">
					<div class="dp">
						<div class="pl">
							<textarea class="form-control pl_textarea" placeholder="输入回复内容"></textarea>
						</div>
						<div class="clearfix mt15">
							<div class="pull-left">
								<button class="btn dp_btn" style="float: left;">附件上传</button>
								<span class="file_name"></span>
								<form onsubmit="return false;" method="post"
								enctype="multipart/form-data">
								<input type="hidden" name="projectNo"
									value="${projectComplaint.projectNo}"> <input
									type="hidden" name="newFileName" id="newFileName"> <input
									type="hidden" name="fileName" id="fileName"> <input
									type="file" name="file" class="pull-left zj_upload"
									onchange="upload(this)" id="file_input">
							</form>
							</div>
							
							<span class="z-span" style="font-size: 14px"></span>
							<div class="pull-right">
								<button class="btn dp_btn"
								onclick="insertReplyInformation('${projectComplaint.projectNo}','${projectComplaint.id}')">上传</button>
							</div>
							
						</div>
					</div>

					<table class="table table-bordered dp_tabel">
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
														href="/project_img/${obj.projectNo}/comment/${obj.newFileName}">${obj.fileName}</a>
												</div>
											</c:if>
										</div> <c:if test="${fn:containsIgnoreCase(userName, obj.reviewer)}">
											<button class="btn del" onclick="del(this,'${obj.id}')">删除</button>
										</c:if>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</c:if>
	</div>


</body>
</html>
<script src="jquery.min.js"></script>
<script src="cookie.js"></script>
<script src="bootstrap.min.js"></script>
<script src="layer.js" type="text/javascript"></script>
<script type="text/javascript" src="jquery-form.js"></script>
<script src="datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="upload-base.js"></script>

<script>
	$('.btns_3 .btn').click(function() {
		$(this).css({
			'background-color' : '#101010',
			'color' : '#ffffff'
		}).siblings().css({
			'background-color' : '#027CFF',
			'color' : '#fff'
		});
		var key = $(this).index();
		$('.replys .reply').eq(key).show().siblings().hide();
	});
</script>
<script>
	//退出功能
	function exitlogin() {
		window.location.href = "${ctx}/index.jsp";
	}
</script>
<script type="text/javascript">	
	//采购回复录入
	function insertReplyInformation(projectNo, complaintId) {
		var comment = $('.pl_textarea').val();
		var newFileName = $('#newFileName').val();
		var fileName = $('#fileName').val();
		if (!comment && !newFileName) {
			layer.msg("留言和附件不能同时为空", {
				time : 2000
			});
			return false;
		}
		
				$.ajax({
					type : "post",
					url : "${ctx}/project/projectComment",
					data : {
						projectNo : projectNo,
						complaintId : complaintId,
						comment : comment,
						fileName : fileName,
						newFileName : newFileName
					},
					success : function(json) {

						if (json.ok) {
							var fileDiv = '';
							//添加附件显示
							if (newFileName) {
								fileDiv = '<div>附件：<a href="/static_img/project_complaint/'+projectNo+'/'+newFileName+'">'
										+ fileName + '</a></div>';
							}
							$('.dp_tabel_body')
									.before(
											'<tr><td><div><div><span>'
													+ json.data.userName
													+ ' </span><em>'
													+ json.data.createDate
													+ '</em></div><div>'
													+ comment
													+ '</div>'
													+ fileDiv
													+ '</div><button class="btn del" onclick="del(this,\''
													+ json.data.id
													+ '\')">删除</button></td></tr>');

						} else {
							layer.msg(json.message, {
								time : 2000
							});
						}
					}
				})
	}
	//上传方法
	function upload(obj) {
		var n = $(obj).get(0).files[0].name;
		$('.file_name').text(n);
		
		var fileNames = $(obj).val();

		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		} else {
			autTime();
			$('#upload_title').children().text("上传进度");
		}

		// 先上传后获取上传文件路径
		$('form').ajaxSubmit({
			type : "post",
			url : "${ctx}/complaint/uploadComplaint",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
					var newFileName = result.data.newFileName;
					var fileName = result.data.originalFilename;
					$('#newFileName').val(newFileName);
					$('#fileName').val(fileName);
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
</script>
<script>
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
	$('.table-condensed tbody,.table-condensed tfoot').on('click', function() {
		$('.datetimepicker').hide();
	});
		
</script>




