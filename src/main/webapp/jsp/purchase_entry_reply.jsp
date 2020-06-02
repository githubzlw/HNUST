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
<title>采购录入回复</title>
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="add.css">

</head>
<body>
<div class="purchase_entry_reply">	
	<div class="top">
		<div class="top1">
			<h3>项目号：<span>${projectComplaint.projectNo}</span></h3>
			<h3 class="mt10">客户名：<span>${projectComplaint.customerName}</span></h3>
			<h3 class="mt10">
				电子质量跟踪单——采购回复
				<div class="btns top2">
					<a href="javascript:void(0);" class="btn select_blank" onclick="window.history.back()">返回</a>			
				</div>
			</h3>
		</div>		
	</div>
	<form id="form" onsubmit="return false;" method="post" enctype="multipart/form-data">
	<input type="hidden" name="complaintId" id="complaintId" value="${projectComplaint.id}">
	<input type="hidden" name="replyList" id="replyList">
	<input type="hidden" name="replyType" value="1">
		<c:forEach var="obj" items="${issueList}" varStatus="i">
		<div class="form-group mt5 reply_div">
			<p><em>问题${i.index+1}:</em><span>${obj.issue}</span></p>
			<textarea class="form-control mt5" filed="${obj.id}" placeholder="请输入问题${i.index+1}的整改方案"><c:forEach begin="1" end="${obj.replyList.size()}" varStatus="j" step="1"> <c:if test="${obj.replyList.get(j.count-1).replyType == 1}">${fn:trim(obj.replyList.get(j.count-1).replyContent)}</c:if></c:forEach></textarea>
		</div>
		</c:forEach>		
		<div class="form-group mt5 clearfix ">
			<label class="pull-left date_label">预计整改完成日期：</label>
			<div class="date_select">
				<div class="input-group date form_date"
					data-date="" data-date-format="yyyy-mm-dd">
					<input id="predict_complete_time" name="predictCompleteTime"
						class="form-control brt brt_7" size="16" type="text"
						place="选择日期" placeholder="选择日期" value="<fmt:formatDate value="${projectComplaint.predictCompleteTime}" pattern="yyyy-MM-dd"/>">
						<span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar"></i>
						</span>
						
				</div>
				<span></span>
			</div>
		</div>
		<div class="form-group mt10">
			<p>预计整改成本：（如果部分成本需要我司出，请注明）</p>
			<textarea class="form-control mt5" id="cost_analysis" name="costAnalysis">${projectComplaint.costAnalysis}</textarea>
		</div>
		<div class="form-group mt5">
			<p>根原因分析：</p>
			<textarea class="form-control mt5" id="purchase_content" name="purchaseContent">${projectComplaint.purchaseContent}</textarea>
		</div>
	</form>
	<button class="btn btn-default display_block submit_type" <c:if test="${user.roleNo == 1020 || user.roleNo == 5 || user.roleNo == 6 || user.roleNo == 69 || user.roleNo == 100 || user.roleNo == 99 || user.roleNo == 7}">onclick="addReply('${projectComplaint.id}')"</c:if>>提交所有内容并返回</button>
</div>	
</body>
</html>
<script src="jquery.min.js"></script>
<script src="cookie.js"></script>
<script src="bootstrap.min.js"></script>
<script src="layer.js" type="text/javascript"></script>
<script type="text/javascript" src="jquery-form.js"></script>
<script src="datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
	$('.btns_3 .btn').click(function(){
		$(this).css({'background-color':'#101010','color':'#ffffff'}).siblings().css({'background-color':'#027CFF','color':'#fff'});
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
   function addReply(id){
	   
	   var predictCompleteTime = $('input[name="predictCompleteTime"]').val();
	   if(!predictCompleteTime){
		   layer.msg('请输入预计整改完成日期',{time:2000});  
		   return false;
	   }
	   var costAnalysis = $('#cost_analysis').val();
	   if(!costAnalysis){
		   layer.msg('请输入预计整改成本',{time:2000});  
		   return false;
	   }
	   
	   var replyList=[];
	   $('.reply_div').find('textarea').each(function(){
		   var issueId = $(this).attr('filed');
		   var replyContent = $(this).val();
		   var name = $.cookie('name');
		   var replyTime = new Date();
		   var replyType = 1;
		   var issueReply = {'issueId':issueId,'replyContent':replyContent,'replyUser':name,'replyTime':replyTime,'replyType':replyType};
		   replyList.push(issueReply);		   
	   })
	   $('#replyList').val(JSON.stringify(replyList));	   
	   
	   $("#form").ajaxSubmit({
			type : "post",
			url : "${ctx}/complaint/addReply",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
		           window.location='/complaint/queryComplaint?id='+id;
				}else{
				  layer.msg(result.message,{time:2000});  
				}
			},
			error : function() {
				
			}
		});
   }

 
   //上传方法
   function upload(obj,type){
	   
	    var fileNames = $(obj).val();
		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		}
		// 先上传后获取上传文件路径
		$(obj).parents('form').ajaxSubmit({
			type : "post",
			url : "${ctx}/upload/uploadComplaint",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
					var filePath = result.data.path;
					var fileName = result.data.originalFilename;
				    $(obj).next().text(fileName);
				    //1：新增上传
				    //2：修改上传
				    if(type == 1){
				    	$('#filePath').val(filePath);
						$('#fileName').val(fileName);
				    }else if(type == 2){
				    	$('#filePath_edit').val(filePath);
						$('#fileName_edit').val(fileName);
				    }
					
				}
			},
			error : function() {
				
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
	$('.table-condensed tbody,.table-condensed tfoot').on('click',function(){
		$('.datetimepicker').hide();
	})
</script>




