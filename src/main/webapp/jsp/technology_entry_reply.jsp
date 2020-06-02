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
<title>技术录入回复</title>
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="add.css">
<link rel="stylesheet" href="progressBar.css" />
<link rel="stylesheet" href="ui.css" />
<link rel="stylesheet" href="ui.progress-bar.css">

<style type="text/css">
		.zj_upload {
		    opacity: 0;
		    position: absolute;
		    width: 84px;
		    margin-top: 3px;
		}
		.quality_test_reply .del {
		    position: absolute;
		    top: 0;
		    right: 0;
		    background-color: #e54040;
		}
		.quality_test_reply td{
		    padding: 8px;
		}
		.quality_test_reply .dp {
		    border: 1px solid #ddd;
		    margin-bottom: 15px;
		    padding: 8px;
		    font-size: 18px;
		}
</style>

</head>
<body>
<div class="purchase_entry_reply quality_test_reply">	
	<div class="top">
		<div class="top1">
			<h3>项目号：<span>${projectComplaint.projectNo}</span></h3>
			<h3 class="mt10">客户名：<span>${projectComplaint.customerName}</span></h3>
			<h3 class="mt10">
				电子质量跟踪单——技术部回复
				<div class="btns top2">
					<a href="javascript:void(0);" class="btn select_blank" onclick="window.history.back()">返回</a>			
				</div>
			</h3>
		</div>		
	</div>
<%-- 	<form id="form" class="form-horizontal" onsubmit="return false;" method="post" enctype="multipart/form-data">
	<input type="hidden" name="complaintId" id="complaintId" value="${projectComplaint.id}">
	<input type="hidden" name="replyList" id="replyList">
	<input type="hidden" name="replyType" value="3">
	<input type="hidden" name="projectNo" value="${projectComplaint.projectNo}"> --%>
		<div class="form-group mt5">
			<p class="mt5">上传 详细 整改方案，工艺流程控制计划，工装检具设计</p>
			<!-- <input type="hidden" name="filePath" id="filePath">
			<input type="hidden" name="fileName" id="fileName">
			<input type="file" class="mt5" name="file" onchange="upload(this)"> -->
		</div>	
		
		
		
		<div class="panel dp_panel">
		<div class="panel-body">
			<div class="dp">
				<div class="pl"><textarea class="form-control pl_textarea" placeholder="输入回复内容"></textarea></div>
				<div class="text-right">
				<button class="btn dp_btn" style="float: left;">						 
				附件上传
				</button>
				<span class="z-span" style="font-size: 14px"></span>
				<form onsubmit="return false;" method="post" enctype="multipart/form-data">
				   <input type="hidden" name="projectNo" value="${projectComplaint.projectNo}">
				   <input type="hidden" name="newFileName" id="newFileName">
	               <input type="hidden" name="fileName" id="fileName">
				   <input type="file" name="file" class="pull-left zj_upload" onchange="upload(this)">
				</form>
				<button class="btn dp_btn" onclick="sendComment('${projectComplaint.projectNo}','${projectComplaint.id}')">点评</button>
				</div>
			</div>

			<table class="table table-bordered dp_tabel">
				<tbody class="dp_tabel_body">
					
				  <c:forEach var="obj" items="${comments}">
					<tr>
						<td>								
							<div>
								<div>
									<span>${obj.reviewer}</span>
									<em><fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></em>
								</div>
								<div>${obj.comment}</div>
								<c:if test="${obj.newFileName != null && obj.newFileName != ''}">
								   <div>附件：<a href="/project_img/${obj.projectNo}/comment/${obj.newFileName}">${obj.fileName}</a></div>
							    </c:if>
							</div>
							<c:if test="${fn:containsIgnoreCase(userName, obj.reviewer)}">
							<button class="btn del" onclick="del(this,'${obj.id}')">删除</button>								
							</c:if>							 
						</td>	
						</tr>
					</c:forEach>															
												
				</tbody>
			</table>		
		</div>
	</div>	
<!-- 	</form> -->
	<button class="btn btn-default display_block submit_type mt10" <c:if test="${user.roleNo == 7 || user.roleNo == 100 || user.roleNo == 99}">onclick="addReply('${projectComplaint.id}')"</c:if>>提交所有内容并返回</button>
</div>	


			<div class="w-out-box" id="show_upload_dialog" style="display:none;">
			<div class="weui_mask"></div>
			<div class="w-weui_dialog">
			  <div id="container">
			
				<div class="content">
					<h1>上传进度</h1>
				</div>
				
				<!-- Progress bar -->
				<div id="progress_bar" class="ui-progress-bar ui-container">
				<div class="ui-progress" style="width: 0%;" id="ui-progress-upload">
				<span class="ui-label" style="display:none;">正在加载...<b class="value">0%</b></span>
				</div>
				</div>
				<!-- /Progress bar -->
			    <a class="close-reveal-modal" style="color: #fff; font-size: 30px;position: absolute;right: 10px;top: 10px;" href="javascript:void(0);" onclick="cancel_upload()">×</a>
				<div class="content" id="main_content" style="display: none;">
					<p>加载完成。</p>
				</div>
			   </div>
			   </div>
          </div>
</body>
</html>
<script src="jquery.min.js"></script>
<script src="cookie.js"></script>
<script src="layer.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="upload-base.js"></script>
<script src="bootstrap.min.js"></script>
<script type="text/javascript" src="jquery-form.js"></script>
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

//技术回复录入
function addReply(id){
	   
	  /*  var replyList=[];
       var issueId = ${issueId};
	   var name = $.cookie('name');
	   var replyTime = new Date();
	   var replyType = 3;
	   var fileName = $('#fileName').val();
	   var filePath = $('#filePath').val();
	   var issueReply = {'issueId':issueId,'replyUser':name,'replyTime':replyTime,'replyType':replyType,'fileName':fileName,'filePath':filePath};
	   replyList.push(issueReply);		
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
		}); */
	 window.location='/complaint/queryComplaint?id='+id;
}
   
 
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
		$(obj).parents('form').ajaxSubmit({
			type : "post",
			url : "${ctx}/upload/uploadComplaint",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
					var newFileName = result.data.newFileName;
					var fileName = result.data.originalFilename;
					$('#newFileName').val(newFileName); 					
				    $('#fileName').val(fileName);					
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
 
   //发送消息
   function sendComment(projectNo,complaintId){	
	   
		  var comment = $('.pl_textarea').val();
		  var newFileName = $('#newFileName').val();
		  var fileName = $('#fileName').val();
		  if(!comment&&!newFileName){
			  layer.msg("留言和附件不能同时为空",{time:2000}); 
			  return false;
		  }
		  
		  $.ajax({
			     type:"post",                   
			     url:"${ctx}/project/projectComment",           
			     data:{
			    	 projectNo:projectNo,
			    	 complaintId:complaintId,
			    	 comment:comment,
			    	 fileName:fileName,
			    	 newFileName:newFileName
			     },              
			     success:function(json){
//	 		    	 var json = eval("(" + data +")");
					 if(json.ok){
						 var fileDiv = '';
						 //添加附件显示
						 if(newFileName){
							 fileDiv = '<div>附件：<a href="/static_img/project_complaint/'+projectNo+'/'+newFileName+'">'+fileName+'</a></div>';
						 }					 
						 $('.dp_tabel_body').before('<tr><td><div><div><span>'+json.data.userName+' </span><em>'+json.data.createDate+'</em></div><div>'+comment+'</div>'+fileDiv+'</div><button class="btn del" onclick="del(this,\''+json.data.id+'\')">删除</button></td></tr>');			     
					 
					 }else{
						 layer.msg(json.message,{time:2000}); 
					 } 
			     }
			})	 
	 }
   
   
     //删除评论
	 function del(obj,id){			   
		 layer.open({
				type:1,
				skin:'finish-btn',
				title:'是否确认删除',
				anim:4,
				shade:0.6,
				shadeClose:true,
				btn:['确定','取消'],
			})		
			
		$('.finish-btn .layui-layer-btn0').click(function(){
			 $.ajax({
			     type:"post",                   
			     url:"${ctx}/project/delComment",           
			     data:{
			    	 id:id 
			     },              
			     success:function(json){
					 if(json.ok){
						 $(obj).parent().parent().remove();
					 }else{
						 layer.msg("删除失败",{time:2000}); 
					 }
			     }
			 })	
		})
	}	   
</script>





