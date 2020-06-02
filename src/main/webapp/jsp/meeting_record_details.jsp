<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
  <title>会议详情</title>
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="keywords" content="HTML,CSS,XML,JavaScript">
  <link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
  <script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
  <script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript" src="../js/jquery-form.js"></script>
  <style type="text/css">
		body{
			font:14px/28px "微软雅黑";
		}
		.contact *:focus{outline :none;}
		.contact{
			width: 1200px;
		    background: #fff;
		    margin: 10px auto 40px;
		    padding: 10px;
		    -webkit-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    -moz-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    -ms-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    -o-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    box-shadow: 1px 1px 10px rgba(0,0,0,0.1);0.1);
		}
		.contact ul {
			/* width: 650px;
			margin: 0 auto; */
			margin: 0;
		}
		.contact ul li{
			/* border-bottom: 1px solid #dfdfdf; */
			list-style: none;
			padding: 12px;
			min-height: 28px;
		}
		.contact ul li label {
			width: 100px;
			height: 28px;
			display: inline-block;
		    float: left;
		    text-align: right;
		    margin-right: 110px;
		    color: #555555;
		}
		.contact ul li input[type=text],.contact ul li input[type=password]{
			width: 220px;
			height: 25px;
			border :1px solid #aaa;
			padding: 3px 8px;
			border-radius: 5px;
		}
		.contact ul li input:focus{
			border-color: #c00;
			
		}
		.btn{
			position: relative;
			left: 300px;
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
		.task-name{padding-bottom: 40px;}
		.task-name li{overflow: hidden;}
		.task-name-content{
	        color: #4b67e7;
   			text-decoration: none;
   			float: left;
		}
		.task-name-content:hover{
			color: #2c479c;
		}
		.contact ul.task-name li a{display: inline-block;min-width: 400px;}
		.contact ul.task-name li a span{display: inline-block;width: 400px;word-wrap:break-word }
		.contact ul.task-name li div{display: inline-block;margin-left: 30px;}
  </style>
</head>
<body>
    <h2 style="text-align:center;">会议详情</h2>
    <form id="addMeetingProjectTask" onsubmit="return false;" method="post">
	<div class="contact" style="margin-top:10px;">
		<div>
			<ul>
				<li>
					<label>录入人：</label>
					<span>${meetingRecord.meetingInputer}</span>
				</li>
				<li>
					<label>相关项目号:</label>
					<span>${meetingRecord.projectNo}</span>
				</li>
				<li>
					<label>会议名称:</label>
					<span>${meetingRecord.meetingName}</span>
				</li>
				<li style="height: 330px;">
					<label>会议纪要:</label>
					<span style="display: inline-block;max-width: 900px;  float: left;"/>${meetingRecord.meetingDescribe}</span>
				</li>
				<li>
					<label>会议录入时间:</label>
					<span><fmt:formatDate value="${meetingRecord.createDate}" pattern="yyyy-MM-dd"/></span>
				</li>
			</ul>
			<ul class="task-name">
			   <c:if test="${fn:length(meetingRecord.projectTaskList) > 1 }">
			     <c:forEach items="${meetingRecord.projectTaskList}" var="projectTask" varStatus="i">
			        <c:if test="${i.index==0}">
			       	<li>
			       		<label>会议任务:</label>
				        <a href="${ctx}/projectTask/selectProjectTaskById?id=${projectTask.id}&userId=${userId}&userName=${userName}&roleNo=${roleNo}"> 
						     <span>${projectTask.description}</span>
						</a>
						<div> 执行人：<span>${projectTask.accepter}</span>
						
					     <c:if test="${projectTask.taskStatus eq '1'}"> 任务完成时间：<span>${projectTask.operatorTimeView}</span></c:if></div>
					</li>
			        </c:if>
			        
			        <c:if test="${i.index >0}">
			        <li>
			        	<label></label>
				        <a href="${ctx}/projectTask/selectProjectTaskById?id=${projectTask.id}&userId=${userId}&userName=${userName}&roleNo=${roleNo}">
							<span>${projectTask.description}</span>
						</a>
						<div>执行人：<span>${projectTask.accepter}</span>
						<c:if test="${projectTask.taskStatus eq '1'}"> 任务完成时间：<span>${projectTask.operatorTimeView}</span></c:if></div>
					</li>
			        </c:if>
			     </c:forEach> 
			  </c:if>
			  <c:if test="${fn:length(meetingRecord.projectTaskList) ==1 }">
			     <c:forEach items="${meetingRecord.projectTaskList}" var="projectTask" varStatus="i">
			     	<li>
			     		<label>会议任务:</label>
			        	<a href="${ctx}/projectTask/selectProjectTaskById?id=${projectTask.id}&userId=${userId}&userName=${userName}&roleNo=${roleNo}" class="task-name-content">
				        	<span>${projectTask.description}</span>
				    	</a>
				    	<div>执行人：<span>${projectTask.accepter}</span>
				    	<c:if test="${projectTask.taskStatus eq '1'}"> 任务完成时间：<span>${projectTask.operatorTimeView}</span></c:if></div>
				    </li>
			     </c:forEach> 
			  </c:if>
		  </ul>
		</div>
	</div>
	</form>
</body>

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
	})
	Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
</script>
<script type="text/javascript">
 function submitMeetingProjectTask(){
	 $("#addMeetingProjectTask").ajaxSubmit({
		type : "post",
		url : "${ctx}/meetingRecord/addMeetingRecord",
		dataType : "text",
		success : function(result) {
			var json = eval("(" + result + ")");
			  if(json.ok){
				  window.location.href="${ctx}/meetingRecord/selectMeetingRecordList";
			  }else{
				 $("#subHtml").html('<font class="tips_false">录入失败</font>');
			  }    
		}
	});
} 
</script>
</html>

