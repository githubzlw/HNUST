<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
  <title>任务详情-PC</title>
  <meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  
  <meta name="keywords" content="HTML,CSS,XML,JavaScript">
  <link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
  <link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="../css/add.css">
  <link rel="stylesheet" href="../css/progressBar.css" />
  <link rel="stylesheet" href="../css/ui.css" />
  <link rel="stylesheet" href="../css/ui.progress-bar.css">	
  <script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
  <script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
  <script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
  
  <style type="text/css">
		body{
			font:14px/28px "微软雅黑";
			background-color: #FAFAFA;
		}
		h2{
			text-align: center;
			font-size: 24px;
			color: #222;
    		font-weight: 100;
		}
		.contact *:focus{outline :none;}		
		.contact ul {
			/* width: 650px;
			margin: 0 auto; */
		}
		.contact ul li{
		    width: 46%;
    		float: left;
			list-style: none;
		}
		.contact ul li.wid-all{width: 100%;}
		.contact ul li label {
			width:170px;
			display: inline-block;
			float: left;
			color: #555;
		}
		.contact ul li span{
			color: #555;
		}
		.contact ul li input[type=text],.contact ul li input[type=password]{
			width: 220px;
			height: 25px;
			border :1px solid #aaa;
			padding: 3px 8px;
			border-radius: 5px;
		}	
		.tips{
			color: rgba(0, 0, 0, 0.5);
			padding-left: 10px;
		}
		.tips_true,.tips_false{
			padding-left: 10px;
		}
		.tips_true{
			color: green;
		}
		.tips_false{
			color: red;
		}
		.task-icon-left{
			width: 30%;
			float: left;
			min-height: 250px;
			position: relative;
		}
		.task-icon-left img{
			display: inline-block;
			position: absolute;
			left: -9px;
		    top: 26px;
			/* background: url(../img/task-detail-list1.png) center no-repeat; */
		}
		.task-icon-left:after{
			content: "";
			width: 30px;
			height: 100%;
			position: absolute;
			right: 0;
			background: url(../img/ta-right.png) center no-repeat;
		}
		.task-li-right{
			width: 68%;
			float: left;
			margin-top: 40px;
			padding-right: 20px;
    		padding-bottom: 30px;
		}
		.mission-status{
			width: 1200px;
		    margin: 0 auto;
		    padding-bottom: 100px;
		    overflow: hidden;
		}
		
		.mission-status input:hover{
			color: #fff;
			background-color: #4B67E7;
			border-color: #4B67E7;
		}
		/* .mission-status input.complete-mission{
			color: #fff;
			background-color: #4B67E7;
			border-color: #4B67E7;
		} */
		/* .mission-status input.complete-mission:hover{
			background-color: #4362C5;
		} */
		.expected-finish-time{
			display: inline-block;
		    position: relative;
		    height: 33px;
		    min-width: 154px;
		    float: right;
		}
		.expectFinishTimeHtml{
		display: none;
			width: 300px;
			height: 200px;
			border: 1px solid #ccc;
		    position: absolute;
		    top: 50%;
		    left: 50%;
		    margin-top: -25px;
		    margin-left: -150px;
		    background-color: #fff;
		}
		.expectFinishTimeHtml input[type="text"]{
		    color: #777777;
		    padding: 8px 12px;
		    background-color: #fff;
		    border: 1px solid #DDDDDD;
		    vertical-align: bottom;
		}
		.new_add{
    padding: 1px 2px;
    font-size: 12px;
    background-color: #fff;
    outline: none;
    border:0 none;
    border: 1px solid #999;
    position: absolute;
    bottom: 0;
    right: 0;
    z-index:1;
    font-size: 14px;
    color: #333;
}
.add_tc{
	width:320px;
	height:170px;
	box-shadow:3px -2px 6px rgba(43,43,43,0.5),-3px 2px 6px rgba(43,43,43,0.5);
	position:fixed;
	top:310px;
	left:50%;
	margin-left:-160px;
	z-index:2;
	background-color:#fff;
	text-align:center;
    padding-top: 10px;
    display:none;
}
.add_tc textarea{
	width:300px;
	height:110px;
	border:1px solid #ddd;
    resize: none;
    overflow-y:auto;
}
.add_tc .btns2{
	overflow:hidden;
	padding: 10px;
}
.pull-left{
float:left;
}
.pull-right{
float:right;
}
.contact ul .li_time label{
	width:43px;
}
.li_time span{
	float:left;
}
.contact ul .li_time label:last-child{
	margin-left:60px;
}
.task-li-right{position:relative;}
.add_btns{position:absolute;right:25px;top:-25px;}
.add_btns a{padding:6px 12px;border:1px solid #4B67E7;border-radius:4px;margin-left:5px;text-decoration:none;
    color: #fff;
    background-color: #4B67E7;
}	
.tc{position:fixed;left:50%;margin-left:-200px;width:400px;border:1px solid #ccc;
	background-color:#fff;z-index:100;padding:15px;top: 300px;
	box-shadow: 3px -2px 6px rgba(43,43,43,0.5),
 	-3px 2px 6px rgba(43,43,43,0.5);
}
.tc h3{margin-top:0;}
.tc textarea{resize: none;margin-bottom:20px;}     
  </style>
</head>
<body>
<div class="project_task_detail">
    <h2 class="text-center">任务详情<c:if test="${projectTask.taskType eq '2'}">(预约验货)</c:if></h2>
    <div class="tc" style="display:none;">
    	<h3>进度更新</h3>
    	<textarea class="form-control" id="content"></textarea>
    	<div>
    		<button class="pull-left btn1">关闭</button>
    		<button class="pull-right btn2" onclick="updateProgress()">提交</button>
    	</div>
    </div>
    <div class="tc2" style="display:none;">
    	<ul>
	    	<c:forEach var="obj" items="${factoryList}" varStatus="i">
	    		<li class="clearfix">
	    			<span class="pull-left">${obj.factoryName}</span>
	    			<select class="pull-right">
	    				<option></option>
	    				<option value="0">未去该工厂</option>
	    				<option value="1">1分</option>
	    				<option value="2">2分</option>
	    				<option value="3">3分</option>
	    				<option value="4">4分</option>
	    				<option value="5">5分</option>
	    			</select>
	    		</li>
	    	</c:forEach>	
    	</ul>
    	<div class="btns clearfix mt10">
    		<button class="btn pull-left bgcolor_ff0" onclick="cancel_score()">取消</button>
    		<button class="btn pull-right bgcolor_ff0" onclick="save()">保存</button>
    	</div>
    </div>
    <input type="hidden" name="projectNo" id="projectNo" value="${projectTask.projectNo}">
    <input type="hidden" name="taskType" id="taskType" value="${projectTask.taskType}">
    <input type="hidden" name="userName" id="userName" value="${userName}">
    <input type="hidden" name="roleNo" id="roleNo" value="${roleNo}">
    <input type="hidden" name="userId" id="userId" value="${userId}">
    <input type="hidden" name="id" id="id" value="${projectTask.id}">
	<div class="contact clearfix">
		<div class="task-icon-left">
			<c:if test="${projectTask.taskStatus eq '0'}"><img src="../img/task-detail-list1.png" /></c:if>
			<c:if test="${projectTask.taskStatus eq '1'}"><img src="../img/task-detail-list2.png" /></c:if>
			<c:if test="${projectTask.taskStatus eq '2'}"><img src="../img/task-detail-list4.png" /></c:if>
			<c:if test="${projectTask.taskStatus eq '3'}"><img src="../img/task-detail-list3.png" /></c:if>
		</div>
		<div class="add_tc">
		   <textarea id="operateExplain" name="operateExplain" placeholder="请输入操作说明......" value=""></textarea>
		   <input type="hidden" id="operateType"  name="operateType" value="">
	       <div class="btns2">
		       <button class="pull-left">取消</button>
		       <button class="pull-right" onclick="addOperateExplain()">保存</button>
	       </div>
		</div>
		<div class="task-li-right">
			
			<ul class="project_detail_ul">
				<li>
					<label>任务发起人：</label>
					<span>${projectTask.initiator}</span>
					<input type="hidden" name="projectTaskId" id="projectTaskId" value="${projectTask.id}">
				</li>
				<li>
					<label>任务发起时间：</label>
					<span><fmt:formatDate value="${projectTask.startTime}"/></span>
				</li>
				<li>
					<label>任务接受人：</label>
					<span>${projectTask.accepter}</span>
				</li>
				<li>
					<label>要求任务完成时间：</label>
					<span><fmt:formatDate value="${projectTask.finishTime}"/></span>
				</li>
				<li>
					<label>相关项目号：</label>
					<c:choose>
						<c:when test="${fn:containsIgnoreCase(projectTask.description, '编制检验计划')}">
						   <span> <a target="_blank" href="http://117.144.21.74:33168/po/POupload.aspx?id=${projectTask.projectNo}">${projectTask.projectNo} </a></span>
					    </c:when>
					    <c:otherwise>
					        <span> <a target="_blank" href="/project/showListNew?inputKey=${projectTask.projectNo}">${projectTask.projectNo} </a></span>
					    </c:otherwise>
				    </c:choose>
				</li>
				<li>
					<label>开箱比例：</label>
					<span>${projectTask.openRate==null?(projectTask.checkType != '出货' ? '非大货': ''):(projectTask.openRate)}${projectTask.openRate!=null?'%':''}</span>
				</li>
				
				<c:if test="${projectTask.isVideo == 1}">
					<li style="width:100%;">
						<span style="color:red;">
							跟单觉得本项目有问题，需要提供视频和预计交期（点击任务完成时，在弹窗中填写）：
							<c:if test="${projectTask.isVideoUpload == 1}">
							       视频已上传 <a target="_blank" href="/project/showDetails?projectNo=${projectTask.projectNo}">点我新窗口查看视频</a> 
							</c:if>
							<c:if test="${projectTask.isVideoUpload == 0}">
							      <a target="_blank" href="/uploadVideo/toUploadVideo?projectNo=${projectTask.projectNo}&userName=${userName}&taskId=${projectTask.id}">未上传，点我上传</a> 
							</c:if>
						</span>
					</li>
				</c:if>
				
				<li>
				   <c:if test="${projectTask.taskType eq '2'}">
					<label>交期：</label>
					<span><fmt:formatDate value="${projectTask.inspectionReservation.expectedDelivery}"/></span>
					
				  </c:if>
				  <c:if test="${projectTask.taskType ne '2'}">
					<label>&nbsp;</label>
					<span readonly="readonly"></span>
				  </c:if>
				</li>
				<li>
				   <c:if test="${projectTask.taskType eq '2'}">
				
					<label>船期：</label>
					<span><fmt:formatDate value="${projectTask.inspectionReservation.shippingDate}"/></span>
				 
				  </c:if>
				  <c:if test="${projectTask.taskType ne '2'}">
					<label>&nbsp;</label>
					<span readonly="readonly"></span>
					
				  </c:if>
				</li>
				<li>
				   <c:if test="${projectTask.taskType eq '2'}">
				
					<label>出检日期:</label> 
				 <span><fmt:formatDate value="${projectTask.inspectionReservation.finishTime}" pattern="yyyy-MM-dd" /></span>
				 
				  </c:if>
				  <c:if test="${projectTask.taskType ne '2'}">
					<label>&nbsp;</label>
					<span readonly="readonly"></span>
					
				  </c:if>
				</li>
				<li class="wid-all">
					<label>任务简述：</label>
					<span readonly="readonly">${projectTask.description}<c:if test="${projectTask.taskType eq '2'}">&nbsp;&nbsp;当前生产状态:${projectTask.inspectionReservation.produceStatus}</c:if></span>
					<c:if test="${projectTask.returnUrl != null && projectTask.returnUrl != ''}">
					   <a href="${projectTask.returnUrl}" target="_blank">点击跳转</a>
					</c:if>
					<c:if test="${projectTask.taskType eq '9'}">
				        <br><span style="color:red;">同时请打包上传整改完成的图纸、图片、视频并在任务完成时填写整改结论 </span>     	
		         		   <form onsubmit="return false;" method="post" enctype="multipart/form-data">
		         		      <input type="hidden" name="projectNo" value="${projectTask.projectNo}">
			                  <input type="hidden" name="fileName" id="productFileName" value="${projectTask.productFileName}">
		         		      <input type="file" style="margin-left: 170px;" name="file" class="pull-left" onchange="upload(this)"> 
		         		   </form>
		         		  <a href="/static_img/project_img/${projectTask.projectNo}/product/${projectTask.productFileName}" download="/static_img/project_img/${projectTask.projectNo}/product/${projectTask.productFileName}" id="product_file_download" <c:if test="${projectTask.productFileName==null}">style="display:none;"</c:if>>${projectTask.productFileName}</a>				
				    </c:if>
				</li>
				
				
				<c:if test="${projectTask.taskUrl != null}">
				<li class="wid-all">
					<label>附件下载：</label>
					<a href="/static_img/project_task_img/${projectTask.taskUrl}" download="/static_img/project_task_img/${projectTask.taskUrl}">${projectTask.taskUrl}</a>
				</li>
				</c:if>
				<c:if test="${projectTask.urgentReason!='' and projectTask.urgentReason!=null}">
					<li class="wid-all">
						<label>如果紧急,请给出理由：</label>
						<span>${projectTask.urgentReason}</span>
					</li>
				</c:if>
				<%-- <li class="wid-all">
					<a href="http://117.144.21.74:10010/project/showDetails?projectNo=${projectTask.projectNo}" style="color:red;">检验计划要求速览</a>
				</li> --%>

			</ul>
			<span class="tips" id="urgentReasonHtml"></span>
		</div>
	</div>
	<div class="mission-status">			
			<a class="select_blank btn" href="http://117.144.21.74:10010/user/toIndex" target="_blank">返回功能选择页</a>					
			<a class="select_blank btn" href="http://117.144.21.74:33168/po/CaseInPo.aspx?id=${projectTask.projectNo}" target="_blank">跳转ERP</a>
			<a class="select_blank btn" href="http://117.144.21.74:43888/New-Quotation/quotation/login.jsp" target="_blank">跳转内部报价系统</a>								
	    <c:if test="${projectTask.taskStatus eq '0'}"><!--任务未完成 -->
		    <input type="submit" class="complete-mission select_blank" onclick="showModelFrame(1)" value="任务完成"/>	    	
		    <input type="submit" onclick="showTc()" <c:if test="${projectTask.progressStatus != null && projectTask.progressStatus != ''}">style="background-color:#71e7f6;"</c:if> value="超一周任务更新进度" class="input4"/>
			<input type="submit" onclick="showModelFrame(3)" value="任务取消" />
			<input type="submit" onclick="showModelFrame(2)" value="任务暂停"/>
	    </c:if>
	    
	    <c:if test="${projectTask.taskStatus eq '1' || projectTask.taskStatus eq '3'}"><!--任务已完成或者取消 -->
		  <input type="submit" onclick="showModelFrame(0)"value="重启任务"/>
		  <input type="submit" onclick="showModelFrame(4)"value="新的任务"/>
	    </c:if>
	    
	    <c:if test="${projectTask.taskStatus eq '2'}"><!-- 任务暂停 -->
	      <input type="submit" onclick="showModelFrame(3)"value="任务取消"/>
		  <input type="submit" onclick="showModelFrame(0)"value="重启任务"/>
	    </c:if>
	    
	    <c:if test="${projectTask.taskStatus eq '0'}"><!--任务未完成 -->
	    <div class="expected-finish-time">
	    	<input type="submit" onclick="expectFinishTime()" value="预计完成时间"/>
	    	<!-- <div class="expectFinishTimeHtml" id="expectFinishTimeHtml">
			   <label>预计完成时间:</label>
			   <input type="text" name="expectFinishTime" id="expectFinishTime" value="" class="input-text form-control date-time" placeholder="请点击选择完成时间"/><span class="tips" id="finishTimeHtml"></span>
			   <input type="submit" value="确认" onclick="subExpectFinishTime()"/>
			</div> -->
	    </div>
	    </c:if>
	    
	    
	    <c:if test="${not empty projectTask.meetingNo}">
	      <input name="" style="height:28px;line-height:28px;background-color: #027cff;border: none;color: #fff;padding: 1px 16px; margin-left: 6px;border-radius: 2px;"type="submit" value="关联会议" onClick="relatedMeeting('${projectTask.meetingNo}')" id="goBackHtml"/>
	    </c:if>
	 </div>
	 <div class="expectFinishTimeHtml" id="expectFinishTimeHtml">
	   <!-- <label>预计完成时间:</label> -->
	   <input type="text" name="expectFinishTime" id="expectFinishTime" value="" class="input-text form-control date-time" placeholder="请点击选择完成时间"/><span class="tips" id="finishTimeHtml"></span>
	   <input type="submit" value="确认" onclick="subExpectFinishTime()"/>
	   <input type="submit" value="关闭" onclick="expectFinishTimeClose()"/>
	</div>
	 
</body>
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/js/upload-base.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-form.js"></script>
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
		startYear: currYear, //开始年份
		endYear: currYear + 50 //结束年份
	};
	$(".date-time").mobiscroll($.extend(opt['date'], opt['default']));
})
</script>
<script type="text/javascript">
var search = "";
search = getCookie('search');	

function getCookie(objName){//获取指定名称的cookie的值

    var arrStr = document.cookie.split("; ");

    for(var i = 0;i < arrStr.length;i ++){

        var temp = arrStr[i].split("=");

        if(temp[0] == objName) return unescape(temp[1]);

   } 

}


//新增弹窗
$('.new_add').click(function(){
	$('.add_tc').show();
})
$('.add_tc .btns2 button:first-child').click(function(){
	$('.add_tc').hide();
})

function relatedMeeting(meetingNo){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var meetingNo=meetingNo;
	window.location.href="${ctx}/meetingRecord/selectMeetingRecordDetails?meetingNo="+meetingNo+"&roleNo="+roleNo+"&userName="+userName+"&userId="+userId;	
}
function expectFinishTime(){
	$("#expectFinishTimeHtml").toggle();
}
function expectFinishTimeClose(){
	$("#expectFinishTimeHtml").toggle();
}

//打开更新进度
function showTc(){
	$('.tc').show();	
}
//更新进度
function updateProgress(){
	var id=$("#id").val();
	var progress = $('#content').val();
	if(!progress){
		layer.msg('请输入内容',{time:2000});
		return false;
	}
	$.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateProgress",           
	     data:{
	    	 progress:progress,
	    	 id:id
	     },              
	     success:function(json){  
// 		      var json = eval("(" + data +")");
			  if(json.ok){
				  layer.msg('更新成功',{time:2000});
				  $('.tc').hide();	
			  }
	     }
	 });		
}



/**
 * 提交预计完成时间
 */
function subExpectFinishTime(){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var id=$("#id").val();
	var expectFinishTime=$("#expectFinishTime").val();
	if(!expectFinishTime){
		layer.msg('请输入时间',{time:2000});
		return fasle;
	}
	
	$.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateExpectFinishTime",           
	     data:{
	    	 expectFinishTime:expectFinishTime,
	    	 id:id
	     },              
	     success:function(json){  
// 		      var json = eval("(" + data +")");
			  if(json.ok){
				  $("#expectFinishTimeHtml").hide();
                  layer.msg('更新成功',{time:2000});
				 // window.location.href="${ctx}/projectTask/projectTaskList?userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
			  }else{
				  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
			  }   
	     }
	 });	
}
//返回主页
function goBack(){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var purchaseNameId="";
	window.location.href="${ctx}/jsp/project_list.jsp?userId="+userId+"&roleNo="+roleNo+"&purchaseNameId="+purchaseNameId+"&userName="+encodeURI(encodeURI(userName));	
}
//任务重启 0
function restartProjectTask(){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
    var projectTaskId=$("#projectTaskId").val();
    var operateExplain=$("#operateExplain").val();
    if(operateExplain=="" ||operateExplain==undefined ||operateExplain==null){
    	layer.msg("请输入操作说明",{time:2000});
    	return false;
    }
    var taskStatus=0;
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateProjectTask",           
	     data:{
	    	 projectTaskId:projectTaskId,
	    	 taskStatus:taskStatus,
	    	 userName:userName,
	    	 operateExplain:operateExplain
	     },              
	     success:function(json){  
// 		      var json = eval("(" + data +")");
			  if(json.ok){
				  //window.location.href="${ctx}/projectTask/projectTaskList?userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
			      if(search){
			    	  window.location.href=search;
			      }
			  }else{
				  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
			  }   
	     }
	 });	
}


var isVideo = '${projectTask.isVideo}';
var isVideoUpload = '${projectTask.isVideoUpload}';
//完成任务按钮 任务完成  1
function finishProjectTask(){
     var projectTaskId=$("#projectTaskId").val();
 	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 var taskType = $('#taskType').val();
	
     var taskStatus=1;
     var operateExplain=$("#operateExplain").val();
     //查询视频是否上传
     findVideoUpload(projectTaskId);
     if(isVideo == 1 && isVideoUpload == 0){
    	 layer.msg('您还未上传视频，请点击上传',{time:2000});
    	 return false;
     }
     var productFileName = $('#productFileName').val();
     if(taskType == 9){   	
    	 if(!productFileName){
    		 layer.msg('您还未上传产品改进视频图片',{time:2000});
    		 return false;
    	 }
    	 if(!operateExplain){
    		 layer.msg('您还未填写整改结论',{time:2000});
    		 return false;
    	 }
     }
         
     
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateProjectTask",           
	     data:{
	    	 projectTaskId:projectTaskId,
	    	 taskStatus:taskStatus,
	    	 userName:userName,
	    	 operateExplain:operateExplain,
	    	 productFileName:productFileName
	     },              
	     success:function(json){  
// 		      var json = eval("(" + data +")");
			  if(json.ok){
				  window.location.reload(); 
				  //window.location.href="${ctx}/projectTask/projectTaskList?userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
				  if(search){
			    	  window.location.href=search;
			      }
			  }else{
				  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
			  }   
	     }
	 });
}
//取消任务按钮 取消任务  3
function cancelProjectTask(){
     var projectTaskId=$("#projectTaskId").val();
     var taskStatus=3;
 	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 var operateExplain=$("#operateExplain").val();
	 if(operateExplain=="" ||operateExplain==undefined ||operateExplain==null){
	    layer.msg("请输入操作说明",{time:2000});
	    return false;
     }
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateProjectTask",           
	     data:{
	    	 projectTaskId:projectTaskId,
	    	 taskStatus:taskStatus,
	    	 userName:userName,
	    	 operateExplain:operateExplain
	     },              
	     success:function(json){  
// 		      var json = eval("(" + data +")");
			  if(json.ok){
				  //window.location.href="${ctx}/projectTask/projectTaskList?userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
				  if(search){
			    	  window.location.href=search;
			      }
			  }else{
				  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
			  }   
	     }
	 });  
}
//暂停任务按钮 暂停任务2
function stopProjectTask(){
     var projectTaskId=$("#projectTaskId").val();
     var taskStatus=2;
 	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 var operateExplain=$("#operateExplain").val();
	 if(operateExplain=="" ||operateExplain==undefined ||operateExplain==null){
	    	layer.msg("请输入操作说明",{time:2000});
	    	return false;
	 }
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateProjectTask",           
	     data:{
	    	 projectTaskId:projectTaskId,
	    	 taskStatus:taskStatus,
	    	 userName:userName,
	    	 operateExplain:operateExplain
	     },              
	     success:function(json){  
// 		      var json = eval("(" + data +")");
			  if(json.ok){
				  //window.location.href="${ctx}/projectTask/projectTaskList?userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
				  if(search){
			    	  window.location.href=search;
			      }
			  
			  }else{
				  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
			  }   
	     }
	 });  
}
//将放弃的任务更新为其他任务(4)
function updateOtherProjectTask(){
     var projectTaskId=$("#projectTaskId").val();
 	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 var taskStatus=4;
	 var operateExplain=$("#operateExplain").val();
	 if(operateExplain=="" ||operateExplain==undefined ||operateExplain==null){
	    	layer.msg("请输入操作说明",{time:2000});
	    	return false;
	 }
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateProjectTask",           
	     data:{
	    	 projectTaskId:projectTaskId,
	    	 taskStatus:taskStatus,
	    	 userName:userName,
	    	 operateExplain:operateExplain
	     },              
	     success:function(json){  
// 		      var json = eval("(" + data +")");
			  if(json.ok){
				 // window.location.href="${ctx}/projectTask/projectTaskList?userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
				  if(search){
			    	  window.location.href=search;
			      }
			  }else{
				  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
			  }   
	     }
	 });  
}
//弹出框显示
function showModelFrame(obj){
		 //如果是项目完成任务，打开评分弹框
		 var taskType = $('#taskType').val();
		 if(taskType == 6 && obj == 1){
			 $('.tc2').show();
			 return false;
			 
		 }
		 if(taskType == 13 && obj == 1){
		 var projectTaskId=$("#projectTaskId").val();
		 $.ajax({
		     type:"post",                   
		     url:"${ctx}/project/uploadInspectionPlan",           
		     data:{
		    	 projectTaskId:projectTaskId
		    	},              
		     success:function(json){  
               if(json.ok){
            	   finishProjectTask(); 
            	  
				  }else{
					  alert("ERP检验计划未跟新");
					  return false;
				  }   
		     }
		 }); 
		 }else{
		 $('.add_tc').show();
		 $("#operateType").val(obj);
		 }
		 
		 
		 
}
/**
 * 提交事件
 */
function addOperateExplain(){
	var operateType=$("#operateType").val();
	if(operateType==0){//重启任务
		restartProjectTask();
	}
	if(operateType==1){//任务完成
		finishProjectTask();
	}
    if(operateType==2){//任务暂停
    	stopProjectTask();
	}
    if(operateType==3){//任务取消
    	cancelProjectTask();
	}
    if(operateType==4){//放弃任务更新为
    	updateOtherProjectTask();
    }
}
// 进度更新弹窗
$('.input4').click(function(){
	$('.tc').show();
})
$('.btn1,.btn2').click(function(){
	$('.tc').hide();
})



  //取消评分弹框
  function cancel_score(){
	 $('.tc2').hide();	
  }

//完成任务按钮 任务完成  1
function save(){
     var projectTaskId=$("#projectTaskId").val();
 	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 var projectNo = $('#projectNo').val();
     var taskStatus=1;
     
     //判断是否给工厂打分，没打分提示
     var scoreFlag = true;
     var time = new Date();
	 var scoreList=[];
	  $('.tc2 ul').find('li').each(function(){
		  var factoryName = $(this).find('span:eq(0)').text();
		  var score = $(this).find('select').val();
		  if(score==''||score==null){
			  layer.msg("请给工厂打分（1-5分）",{time:2000});
			  scoreFlag = false;
			  return false;
		  }
		  if(!(factoryName&&score) || score == 0){
			  return;
		  }
		  var factoryScore = {"factoryName":factoryName,"score":score,"scorer":userName,"projectNo":projectNo};
		  scoreList.push(factoryScore);
	  })
     
	 if(!scoreFlag){
		 return false;
	 } 
	  
     
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/updateProjectTask",           
	     data:{
	    	 projectTaskId:projectTaskId,
	    	 taskStatus:taskStatus,
	    	 userName:userName,
	    	 scoreList:JSON.stringify(scoreList)
	     },              
	     success:function(json){  
// 		      var json = eval("(" + data +")");
			  if(json.ok){
				    if(search){
					  window.location.href=search;
					}
			  }else{
				  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
			  }   
	     }
	 });
}


//查询视频是否上传
function findVideoUpload(taskId){
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/projectTask/selectByTaskId",           
	     data:{
	    	 id:taskId
	     },  
	     async:false, 
	     success:function(json){  
// 		      var json = eval("(" + data +")");
			  if(json.ok){
				  var task = json.data;
				  isVideo = task.isVideo;
				  isVideoUpload = task.isVideoUpload;
			  }
	     }
	 })
}


//上传视频
function upload(obj){	
	    var projectNo = $('#projectNo').val();
		var path = $(obj).val();
	    sppath = path.split("\\");
	    var fileName = sppath[sppath.length-1];	  	   
	    if(fileName == null || fileName == '' || fileName == undefined){
	    	return false;
	    }else{
		   $(obj).parents('form').find('input[name="fileName"]').val(fileName);
    	   autTime(); 
		   $('#upload_title').children().text("上传进度");
	    }	 		    	
     		
		  
		  //先上传后获取上传文件路径
		 $(obj).parents('form').ajaxSubmit({
			type: "post",
			url: "/upload/uploadProductFile",
	     	dataType: "text",
	     	success: function(str){
	     	var result = eval('(' + str + ')');		
	     	var fileNewName = result.data.fileName;
		     	if(result.ok){  
		     		$(obj).parents('form').find('input[name="fileName"]').val(fileNewName); 		
		     		$('#product_file_download').attr('href','/static_img/project_img/'+projectNo+'/product/'+fileNewName+'').attr('download','/static_img/project_img/'+projectNo+'/product/'+fileNewName+'').text(fileNewName).show();
		     	}else{
		     		layer.msg('上传失败',{time:2000});
		     	}
	     	},
			error: function(){
				layer.msg('上传失败',{time:2000});
	     		$('#show_upload_dialog').hide();
			}       	     	
	 	}); 	 		    

 }

</script>
</html>

