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
<title>整改结果录入回复</title>
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="add.css">
</head>
<body>
<div class="purchase_entry_reply remedia_entry_reply">	
	<div class="top">
		<div class="top1">
			<h3>项目名：<span>${projectComplaint.projectNo}</span></h3>
			<h3 class="mt10">客户名：<span>${projectComplaint.customerName}</span></h3>
			<h3 class="mt10">
				电子质量跟踪单——质检整改结果录入回复
				<div class="btns top2">
					<a class="btn select_blank" onclick="window.history.back()">返回</a>			
				</div>
			</h3>
		</div>		
	</div>
	<form id="form" onsubmit="return false;" method="post" enctype="multipart/form-data">
	<input type="hidden" name="complaintId" id="complaintId" value="${projectComplaint.id}">
	<input type="hidden" name="replyList" id="replyList">
	<input type="hidden" name="replyType" value="4">		
	<input type="hidden" name="roleNo" value="${user.roleNo}">		
		 
		 <div class="time_name mb10">
		 <c:if test="${projectComplaint.rectificationSellPurchaseReply != null}">
		 	<p class="clearfix"><em class="pull-left">采购/跟单整改结果录入时间:</em><span class="pull-left">
               <fmt:formatDate value="${projectComplaint.rectificationSellPurchaseTime}" pattern="yyyy-MM-dd"/>
               </span><span class="pull-right">${projectComplaint.rectificationSellPurchaseReply}</span></p>
		 </c:if>
		 <c:if test="${projectComplaint.rectificationZhijianReply != null}">
		 	<p class="clearfix"><em class="pull-left">质检整改结果录入时间:</em><span class="pull-left">
		 	<fmt:formatDate value="${projectComplaint.rectificationZhijianTime}" pattern="yyyy-MM-dd"/></span>
		 	<span class="pull-right">${projectComplaint.rectificationZhijianReply}</span></p>
	     </c:if>
		 </div>
		<c:forEach var="obj" items="${issueList}" varStatus="i">
		<div class="form-group mt5 reply_div">
			<p><em>问题${i.index+1}:</em><span class="reply" filed="${obj.id}">${obj.issue}</span></p>
			<%-- <c:if test="${user.roleNo == 1020 || user.roleNo == 69 || user.roleNo == 5 || user.roleNo == 6 || user.roleNo == 100 || user.roleNo == 99 || user.roleNo == 7}">
				<textarea class="form-control mt5" placeholder="请输入问题${i.index+1} 是否解决问题如何解决问题 本栏采购/跟单填写"><c:forEach begin="1" end="${obj.replyList.size()}" varStatus="j" step="1"> <c:if test="${obj.replyList.get(j.count-1).replyType == 4}">${fn:trim(obj.replyList.get(j.count-1).replyContent)}</c:if></c:forEach></textarea>
			</c:if> --%>
			<c:if test="${user.roleNo == 9 || user.roleNo == 69 || user.roleNo == 100 || user.roleNo == 99}">
				<div class="mt10 clearfix">
					<label class="pull-left mr20">问题<span>${i.index+1}</span>验证结果</label>
					<div class="pull-left radios">
						<label>   
							<input type="radio" name="qualification${i.index}" value="合格"  
							 <c:forEach begin="1" end="${obj.replyList.size()}" varStatus="j" step="1"> 
							<c:if test="${obj.replyList.get(j.count-1).replyType == 4 && obj.replyList.get(j.count-1).qualification eq '合格'}">checked</c:if>
							</c:forEach>>合格
						</label>
						<label>
							<input type="radio" name="qualification${i.index}" value="不合格"
							 <c:forEach begin="1" end="${obj.replyList.size()}" varStatus="j" step="1"> 
							  <c:if test="${obj.replyList.get(j.count-1).replyType == 4 && obj.replyList.get(j.count-1).qualification eq '不合格'}">checked</c:if>
							 </c:forEach>>不合格
						</label>
					</div>
				  </div>
			 </c:if>
		</div>
		</c:forEach>		
	</form>
	<button class="btn btn-default display_block submit_type" onclick="addReply('${projectComplaint.id}')">提交所有内容并返回</button>
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


var roleNo = ${user.roleNo};
var userId = ${user.id};
//采购回复录入
function addReply(id){
	   
	   var replyList=[];
	   $('.reply_div').each(function(i){
		   var issueId = $(this).find('.reply').attr('filed');
		   var replyContent = $(this).find('textarea').val();
		   var name = $.cookie('name');
		   var replyTime = new Date();
		   var replyType = 4;
		   var qualification = $(this).find('input:radio[name="qualification'+i+'"]:checked').val();
		   
		   var issueReply = '';
		   if(roleNo == 9){
			  issueReply = {'issueId':issueId,'replyTime':replyTime,'replyType':replyType,'qualification':qualification,'inspectionName':name}; 
		   }else{
			  issueReply = {'issueId':issueId,'replyContent':replyContent,'replyUser':name,'replyTime':replyTime,'replyType':replyType}; 
		   }
		   
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




