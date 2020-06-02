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
<title>质检录入回复</title>
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="add.css">
</head>
<body>
<div class="purchase_entry_reply quality_test_reply">	
	<div class="top">
		<div class="top1">
			<h3>项目号：<span>${projectComplaint.projectNo}</span></h3>
			<h3 class="mt10">客户名：<span>${projectComplaint.customerName}</span></h3>
			<h3 class="mt10">
				电子质量跟踪单——质检回复
				<div class="btns top2">
					<a href="javascript:void(0);" class="btn select_blank" onclick="window.history.back()">返回</a>			
				</div>
			</h3>
		</div>		
	</div>
	<form id="form" onsubmit="return false;" method="post" enctype="multipart/form-data">
	<input type="hidden" name="complaintId" id="complaintId" value="${projectComplaint.id}">
	<input type="hidden" name="replyList" id="replyList">
	<input type="hidden" name="replyType" value="2">
	<c:forEach var="obj" items="${issueList}" varStatus="i">
		<div class="form-group mt5 reply_div">
			<p><em>问题${i.index+1}:</em><span>${obj.issue}</span></p>
			<select class="form-control mt5" filed="${obj.id}">
				<option  <c:forEach begin="1" end="${obj.replyList.size()}" varStatus="j" step="1"> 
							<c:if test="${obj.replyList.get(j.count-1).replyType == 2 && obj.replyList.get(j.count-1).replyContent eq '没发现'}">selected</c:if>
							</c:forEach>>没发现</option>
				<option <c:forEach begin="1" end="${obj.replyList.size()}" varStatus="j" step="1"> 
							<c:if test="${obj.replyList.get(j.count-1).replyType == 2 && obj.replyList.get(j.count-1).replyContent eq '有发现，质量评估会后放'}">selected</c:if>
							</c:forEach>>有发现，质量评估会后放</option>
				<option <c:forEach begin="1" end="${obj.replyList.size()}" varStatus="j" step="1"> 
							<c:if test="${obj.replyList.get(j.count-1).replyType == 2 && obj.replyList.get(j.count-1).replyContent eq '有发现，没按流程放行'}">selected</c:if>
							</c:forEach>>有发现，没按流程放行</option>
				<option <c:forEach begin="1" end="${obj.replyList.size()}" varStatus="j" step="1"> 
							<c:if test="${obj.replyList.get(j.count-1).replyType == 2 && obj.replyList.get(j.count-1).replyContent eq '有发现，整改后，没复检'}">selected</c:if>
							</c:forEach>>有发现，整改后，没复检</option>
			</select>
		</div>
	</c:forEach>
		<div class="form-group mt5">
			<p>其他解释和分析</p>
			<textarea class="form-control mt5 other_explain" name="inspectionContent">${projectComplaint.inspectionContent}</textarea>
		</div>		
	</form>
	<button class="btn btn-default display_block submit_type" <c:if test="${user.roleNo == 9 || user.roleNo == 69 || user.roleNo == 100 || user.roleNo == 99}">onclick="addReply('${projectComplaint.id}')"</c:if>>提交所有内容并返回</button>
</div>	
</body>
</html>
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
<script src="cookie.js"></script>
<script src="layer.js" type="text/javascript"></script>
<script type="text/javascript" src="jquery-form.js"></script>
<script>
	$('.btns_3 .btn').click(function(){
		$(this).css({'background-color':'#101010','color':'#ffffff'}).siblings().css({'background-color':'#027CFF','color':'#fff'});
		var key = $(this).index();
		$('.replys .reply').eq(key).show().siblings().hide();
	});
</script>
<script>

// $(function() {		
   
// })
//退出功能
function exitlogin() {
	window.location.href = "${ctx}/index.jsp";
}

</script>
<script>
	
</script>
<script type="text/javascript">

//质检回复录入
function addReply(id){
	   
	   var replyList=[];
	   $('.reply_div').find('select').each(function(){
		   var issueId = $(this).attr('filed');
		   var replyContent = $(this).val();
		   var name = $.cookie('name');
		   var replyTime = new Date();
		   var replyType = 2;
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

   
</script>





