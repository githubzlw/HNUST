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
		<title>项目状态信息录入</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
		<style type="text/css">
			* {
				-webkit-box-sizing: border-box;
				-moz-box-sizing: border-box;
				box-sizing: border-box;
				margin: 0;
				padding: 0;
				outline: none;
			}
			
			html {
				font-size: 100px;;
			}
			.form-element{width:90%;overflow:auto;}
			.form-element .con:after {
				clear: both;
				content: "";
				display: block;
			}
			
			.form-element .tit {
				color: #333;
				display: block;
				font-weight: bold;
				margin-bottom: .5em;
				-webkit-user-select: none;
				-moz-user-select: none;
				user-select: none;
			}
			
			.form-element .con {
				margin: .4em 0;
			}
			
			.form-element input,
			.form-element button {
				-webkit-box-sizing: border-box;
				-moz-box-sizing: border-box;
				box-sizing: border-box;
				-moz-padding-start: 0;
				-moz-padding-end: 0;
			}
			
			.form-element .form-c+.txt,
			.form-element .form-select {
				background-image: -webkit-linear-gradient(white, #eee);
				background-image: -moz-linear-gradient(white, #eee);
				background-image: -ms-linear-gradient(white, #eee);
				background-image: -o-linear-gradient(white, #eee);
				background-image: linear-gradient(white, #eee);
				text-shadow: 1px 1px 0 #fff;
				-webkit-user-select: none;
				-moz-user-select: none;
				user-select: none;
			}
			
			.form-element .form-c+.txt {
				border: 1px solid #ccc;
				box-shadow: 0 0 0 1px #fff inset;
				display: block;
				padding: .6em 1.2em;
				position: relative;
			}
			
			.form-element label .form-c+.txt:before {
				background-color: #fff;
				box-shadow: 0 0 0 1px #aaa inset, 0 0 0 2px #fff inset, 1px 1px 3px 4px #ddd inset;
				content: "";
				float: left;
				height: 1.2em;
				margin-right: .4em;
				margin-top: .2em;
				width: 1.2em;
				-webkit-transition: background-color .3s;
				-moz-transition: background-color .3s;
				-ms-transition: background-color .3s;
				-o-transition: background-color .3s;
				transition: background-color .3s;
			}
			
			.form-element .form-c:checked+.txt {
				text-shadow: -1px -1px 0 #fff;
			}
			
			.form-element label [type="checkbox"].form-c+.txt:before {
				border-radius: .4em;
			}
			
			.form-element .form-c {
				display: none;
			}
			
			.form-element .form-c+.txt,
			.form-element .form-select {
				background-image: -webkit-linear-gradient(white, #eee);
				background-image: -moz-linear-gradient(white, #eee);
				background-image: -ms-linear-gradient(white, #eee);
				background-image: -o-linear-gradient(white, #eee);
				background-image: linear-gradient(white, #eee);
				text-shadow: 1px 1px 0 #fff;
				-webkit-user-select: none;
				-moz-user-select: none;
				user-select: none;
			}
			
			.form-element .form-c+.txt {
				border: 1px solid #ccc;
				box-shadow: 0 0 0 1px #fff inset;
				display: block;
				padding: .6em 1.2em;
				position: relative;
			}
			
			.form-element .form-c+.txt:hover {
				background-image: -webkit-linear-gradient(white, #ddd);
				background-image: -moz-linear-gradient(white, #ddd);
				background-image: -ms-linear-gradient(white, #ddd);
				background-image: -o-linear-gradient(white, #ddd);
				background-image: linear-gradient(white, #ddd);
			}
			
			.form-element label:first-child .form-c+.txt {
				border-radius: .4em .4em 0 0;
				border-width: 1px 1px 0 1px;
			}
			
			.form-element label:last-child .form-c+.txt {
				border-radius: 0 0 .4em .4em;
				border-width: 0 1px 1px 1px;
			}
			
			.form-element label [type="radio"].form-c+.txt:before {
				border-radius: 50%;
			}
			
			.form-element label [type="checkbox"].form-c+.txt:before {
				border-radius: .4em;
			}
			
			.form-element .inline label .form-c+.txt:before,
			.form-element .inline label .form-c+.txt:after {
				display: none;
			}
			
			.form-element .inline label:first-child .form-c+.txt {
				border-radius: .4em 0 0 .4em;
				border-width: 1px 0 1px 1px;
			}
			
			.form-element .inline label:last-child .form-c+.txt {
				border-radius: 0 .4em .4em 0;
				border-width: 1px 1px 1px 0;
			}
			
			.form-element .form-c:checked+.txt {
				text-shadow: -1px -1px 0 #fff;
			}
			
			.form-element .form-c:checked+.txt:before {
				background-color: #666;
				box-shadow: 0 0 0 1px #aaa inset, 0 0 0 3px #fff inset;
			}
			
			.form-element .inline .form-c:checked+.txt {
				background-image: -webkit-linear-gradient(#ddd, white);
				background-image: -moz-linear-gradient(#ddd, white);
				background-image: -ms-linear-gradient(#ddd, white);
				background-image: -o-linear-gradient(#ddd, white);
				background-image: linear-gradient(#ddd, white);
				color: #666;
			}
			
			
		</style>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
	</head>
	<body>
		<div class="progress-report">
		<div class="go-back" onclick="window.history.back(-1);"></div>
			<div class="report-tit">项目状态信息录入</div>
			<form action="${ctx}/statusEnter/addStatusEnter" method="post" class="form" enctype="multipart/form-data" onsubmit="return check()">
			    <input type="hidden" name="projectNo" value="<%= projectNo%>">
			    <input type="hidden" name="roleNo" value="<%= roleNo%>">
			    <input type="hidden" name="userId" value="<%= userId%>">
			    <input type="hidden" name="userName" value="<%= userName%>">
			    <input type="hidden" name="statusType" id="statusType" value="">
			    <input type="hidden" name="feedback" id="feedback" value="">
				<div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>项&nbsp;目&nbsp;号：</label>
					<input type="text" class="input-text form-control" readonly="readonly" name="projectNo" placeholder="请填写汇报人" value="<%= projectNo%>">
				</div>
								<div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>消息日期：</label>
					<span class="span-right">
						<input type="text" class="input-text form-control date-time" placeholder="请选择消息时间"/>
					</span>
				</div>
				<div class="form-row margin-top">
					<div class="form-element">
						<dl class="row">
							<dt class="tit">前期生产中的沟通？</dt>
							<dd class="con">
								<label>
									<input type="checkbox" class="form-c" name="statusType" value="1">
									<span class="txt">需求变更或细化 – 和图纸相关</span>
								</label>
								<label>
									<input type="checkbox" class="form-c" name="statusType"  value="2" >
									<span class="txt">需求变更或细化 – 其他 </span>
								</label>
								<label>
									<input type="checkbox" class="form-c"  name="statusType" value="3">
									<span class="txt">交期或者价格相关</span>
								</label>
							</dd>
						</dl>
					</div>
					<div class="form-element">
					<dl class="row">
						<dt class="tit"> 收到货后反馈？</dt>
						<dd class="con">
							<label>
								<input type="radio" class="form-c" value="1" name="feedbackRadio" >
								<span class="txt">有问题</span>
							</label>
							<label>
								<input type="radio" class="form-c" value="2" name="feedbackRadio" >
								<span class="txt">客户开心 </span>
							</label>
						</dd>
					</dl>
				    </div>
				</div>
				<div class="form-row margin-top">
					<label class="control-label"><span class="font-red">*</span>细&nbsp;&nbsp;&nbsp;节：</label>
					<textarea type="text" class="textarea project-reason" id="details" name="details" placeholder="细节..."></textarea>
				</div>
				<div class="form-row margin-top">
					<span class="btn-upload form-group">
						<a href="javascript:void();" class="btn btn-primary">文件上传</a>
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
		<script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
		<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function () {
				var currYear = (new Date()).getFullYear();	
				var opt={};
				opt.date = {preset : 'date'};
				opt.datetime = {preset : 'datetime'};
				opt.time = {preset : 'time'};
				opt.default = {
					theme: 'android-ics light', //皮肤样式
					display: 'modal', //显示方式 
					mode: 'scroller', //日期选择模式
					dateFormat: 'yyyy-mm-dd',
					lang: 'zh',
					showNow: true,
					nowText: "今天",
					startYear: currYear - 1, //开始年份
					endYear: currYear + 50 //结束年份
				};
				$(".date-time").mobiscroll($.extend(opt['date'], opt['default']));
			});
			
			$(document).on("change",".input-file",function(){
				var uploadVal=$(this).val();
				$(this).parent().find(".upload-url").val(uploadVal).focus().blur();
			});
			
			function check(){
			  var chk_value =[]; 
			  $('input[name="statusType"]:checked').each(function(){ 
			    chk_value.push($(this).val()); 
			  }); 
			  $("#statusType").val(chk_value);
			  var feedbackRadio=$('input[name="feedbackRadio"]:checked').val();
			  $("#feedback").val(feedbackRadio);
			  var messageDate=$(".date-time").val();
			  if(messageDate==''){
					layer.msg('请输入消息日期',{time:2000});
					return false;
			  } 
			  var statusType=$("#statusType").val();
			  if(statusType=="" ||statusType==null){
				  layer.msg('请选择前期以及生产之中的沟通',{time:2000});
			      return false;
			  }
			  if(feedbackRadio=="" ||feedbackRadio==null){
				  layer.msg('请选择收货反馈意见',{time:2000});
			      return false;
			  }
			  var details=$("#details").val();
			  if(details==""||details==null){
				  layer.msg('请填写细节内容',{time:2000});
			      return false;
			  }
			  return true;  
			 }
		</script>
	</body>
</html>
