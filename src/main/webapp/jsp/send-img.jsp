<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
  String projectNo=request.getParameter("projectNo");
  String roleNo=request.getParameter("roleNo");
  String userName=request.getParameter("userName");
  String userId=request.getParameter("userId");
%>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<title>项目汇报</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
		<link rel="stylesheet" href="${ctx}/lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/css/add.css">		
		<link rel="stylesheet" href="${ctx}/css/progressBar.css" />
	    <link rel="stylesheet" href="${ctx}/css/ui.css" />
	    <link rel="stylesheet" href="${ctx}/css/ui.progress-bar.css">
	</head>
	<body>
		<div class="progress-report progress_report_add">
		<div class="go-back" onclick="window.history.back(-1);"></div>
			<div class="report-tit">项目汇报</div>
			<form action="${ctx}/report/addReport" method="post" class="form" enctype="multipart/form-data" onsubmit="return check()">
			    <input type="hidden" name="projectNo" value="<%= projectNo%>">
			    <input type="hidden" name="roleNo" value="<%= roleNo%>">
			    <input type="hidden" name="userId" value="<%= userId%>">
				<div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>汇报人：</label>
					<input type="text" class="input-text form-control" readonly="readonly" name="reportName" placeholder="请填写汇报人" value="<%=userName%>">
				</div>
				<div class="form-row margin-top">
					<label class="control-label">项目阶段：</label>
					<select name="projectStage">
					    <option value="">请选择项目阶段</option>
					    <option value="0">样品</option>
					    <option value="1">小批量</option>
					    <option value="2">大货</option>
					</select>
				</div>
				<div class="add_pics">
					<label for="">上传图片</label>
					<div class="uls">
						<ul class="clearfix">
<!-- 							<li> -->
<!-- 								<img src="" alt="" /> -->
<!-- 								<span class="glyphicon glyphicon-remove"></span> -->
<!-- 							</li> -->
							<li class="text-center add">
								<em class="glyphicon glyphicon-plus"></em>
								<input type="file"  name="files">
							</li>
						</ul>
					</div>
				</div>
				<div class="form-row margin-top">
					<label class="control-label add_control-label"><span class="font-red">*</span>汇报内容：</label>
					<textarea type="text" class="textarea project-reason" id="report" name="report"placeholder="请填写汇报内容..."></textarea>
				</div>
				<!-- <div class="form-row margin-top">
					<span class="btn-upload form-group">
						<a href="javascript:void();" class="btn btn-primary">图片上传</a>
						<input type="file" multiple name="file" class="input-file">
					  	<input class="input-text upload-url" type="text" name="uploadfile-1" id="uploadfile-1" readonly>
					</span>
				</div> -->
				<div class="form-row margin-top">
					<input type="submit" class="btn btn-primary add_btn" value="提交" />
				</div>
			</form>
		</div>
		<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="../js/upload-base.js"></script>
        <script type="text/javascript" src="../js/jquery-form.js"></script>
		<script type="text/javascript">
			$(document).on("change",".input-file",function(){
				var uploadVal=$(this).val();
				$(this).parent().find(".upload-url").val(uploadVal).focus().blur();
			});
			
			function check(){
			   var report=$("#report").val();
			   if(report ==  null || report == ''){
			         layer.msg('汇报内容不能为空',{time:2000});
			         return false;
			   }
			   return true;
			 }
		</script>
	</body>
</html>
<script>
	$('.progress_report_add .add_pics span').on('click',function(){
		$(this).parent().remove();
	})
	
	
	
	
function show_drawingNames(obj) {
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
		url : "${ctx}/upload/uploadProductPicAndChangeName.do",
		dataType : "text",
		async : false,
		success : function(str) {
			var result = eval('(' + str + ')');
			if (result.ok) {
				var data = result.data;
				var addhtml=''
				for(var i=0;i<data.length;i++){

					addhtml+='<li><div class="img_in">'
					+'<a href="'+data[i].replace('/compressImg','')+'" target="_blank">'
					+'<img src="'+data[i]+'" alt=""></a>'
					+'</div><span class="glyphicon glyphicon-remove"></span>'
					+'</li>';
				}
				$(obj).parents('form').find('.clearfix').prepend(addhtml);
				/*删除当前图片*/
				$('.quality .wrap2 li span').on('click',function(){
					$(this).parent().remove();
				})
			}
		},
		error : function() {
			
		}
	});
}
</script>
