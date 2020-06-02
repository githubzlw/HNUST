<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
  String projectNo=request.getParameter("projectNo");
  String id=request.getParameter("id");
  String roleNo=request.getParameter("roleNo");
  String userId=request.getParameter("userId");
  String userName=request.getParameter("userName");
%>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<title>任务汇报</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
	</head>
	<body>
		<div class="progress-report">
		<div class="go-back" onclick="window.history.back(-1);"></div>
			<div class="report-tit">任务汇报</div>
			<form action="${ctx}/taskReport/addReport" method="post" class="form" enctype="multipart/form-data" onsubmit="return updateUserPassword()">
			    <input type="hidden" name="projectNo" value="<%=projectNo%>">
			    <input type="hidden" name="id" value="<%=id%>">
			    <input type="hidden" name="roleNo" value="<%=roleNo%>">
			    <input type="hidden" name="userId" value="<%=userId%>">
			    <input type="hidden" name="userName" value="<%=userName%>">
				<div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>汇报人：</label>
					<input type="text" class="input-text form-control" readonly="readonly" name="reportName" placeholder="请填写汇报人" value="<%=userName%>">
				</div>
				<div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>汇报内容：</label>
					<textarea type="text" class="textarea project-reason" id="taskReport" name="taskReport"placeholder="请填写汇报内容..."></textarea>
				</div>
				<div class="form-row margin-top">
					<span class="btn-upload form-group">
						<a href="javascript:void();" class="btn btn-primary">图片上传</a>
						<input type="file" multiple name="file" class="input-file">
					  	<input class="input-text upload-url" type="text" name="uploadfile-1" id="uploadfile-1" readonly>
					</span>
				</div>
				<div class="form-row margin-top">
					<input type="submit" class="btn btn-primary" value="提交" />
				</div>
			</form>
		</div>
		<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(document).on("change",".input-file",function(){
				var uploadVal=$(this).val();
				$(this).parent().find(".upload-url").val(uploadVal).focus().blur();
			});
			
			function check(){
			   var report=$("#taskReport").val();
			   if(report ==  null || report == ''){
			         layer.msg('汇报内容不能为空',{time:2000});
			         return false;
			   }
			   return true;
			 }
		</script>
	</body>
</html>
