<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>任务系统,检验任务列表</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/test_report.css">
<link rel="stylesheet" type="text/css" href="../css/mobiscroll_date.css" />
<link rel="stylesheet" href="../css/add.css">
<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../js/mobiscroll_date.js" type="text/javascript"
	charset="utf-8"></script>
<style>
.date-time {
	background: url(../img/data-calendar.png) right center no-repeat;
	background-position-x: 98%;
}
#qualityDate{width:120px;}
.th_last{width:120px;}
#quality_name_select{width:130px;}
.plan_list th, .plan_list td{padding-left:0;padding-right:0;}
.plan_list{width:1366px;}
.th1{width:115px;}
.th2{width:115px;}
.th3{width:100px;}
.th4{width:110px;}
.th5{width:125px;}
/* .th6{width:130px;} */
/* .th7{width:110px;} */
/* .th8{width:120px;} */
.th9{width:80px;}
.th10{width:50px;}
.th11{width:125px;}
.approval{color:blue;background-color:#fff;}
.select_blank{background-color:#027CFF;padding: 7px 12px;text-decoration:none;
 color:#fff;border-radius: 2px;font-size: 15px;
}
.select_blank:hover,.select_blank:hover{text-decoration:none;background-color:#4362C5;color:#fff;}
.tc {
    position: fixed;
    left: 50%;
    border: 1px solid #ccc;
    background-color: #fff;
    z-index: 100;
    padding: 15px;
    top: 198px;
    box-shadow: 3px -2px 6px rgba(43,43,43,0.5), -3px 2px 6px rgba(43,43,43,0.5);
}
.btns2 button{
    font-size: 14px;
    padding: 4px 8px;
    margin-right: 5px;
    margin-bottom: 10px;
}
.z-btn{
    width: 44px;
    font-size: 14px;
    padding: 3px 6px;
    background-color: #027CFF;
    color: #fff;
    float: left;
    margin-left: 5px;
    }
 
@media screen and (min-width: 768px)

.task_list .add_date { width: auto;} 
     
}   
.last_btn,.last_btn:hover{background-color: #027cff; color: #fff;}
.add_date .form_date{display:inline-block;width:200px;}
.input-group-addon{height:34px;}
.add_export{position:relative;top:-10px;}
.add_span{position:relative;top:-11px;}
#taskHtml .s{display:block;}  
#taskHtml .s4,#taskHtml .s5,#taskHtml .s8{width:90px;}
#taskHtml .s7{width:100px;}
#taskHtml .s9{width:50px;}
#taskHtml .s10{width:90px;}
#taskHtml .s12{width:50px;}
#taskHtml .s13{width:80px;}
#taskHtml .s14{width:35px;}
</style>
</head>
<body>
	<div class="plan_list task_list">
		<div class="title clearfix">
			<div class="div_h3 pull-left">检验任务列表
				<a href="/statistics/selectAllByDate?roleNo=100&userId=180&userName=ninazhao#wrap10" target="_blank" class="btn btn-default last_btn">检验工作量</a>
				<a href="${ctx}/inspection/qualityInspectionMap?roleNo=${roleNo}&userId=${userId}&userName=${userName}" target="_blank" class="btn btn-default">本周作战地图</a>
			</div>
			<div class="logo pull-right">
				<img src="../img/logo.png">
			</div>
		</div>
		<div class="tc add_tc" style="display: none;">
		  <span>开箱比例设为：</span>
		   <input id="openRate" class="form-control" name="openRate" style="width: 69px;display: inline-block;margin-bottom: 13px;">%
		   <input type="hidden" id="projectNoId" name="projectNoId">
		   <input type="hidden" id="index" name="index">
	       <div class="btns2">
		       <button class="btn btn-primary">取消</button>
		       <button class="btn btn-primary" style="float: right;" onclick="updateOpenRate(this)">保存</button>
	       </div>
		</div>
		<div class="btns_search clearfix">
			<div class="pull-left position_relative t12">
				<input class="form-control" placeholder="请输入项目号/人员/工厂" id="inputKey" name="inputKey" value=""> 
			<!-- 新增质检多选开始 -->
				<div class="dropdown">
					<button id="dLabel" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<em class="pull-left">全部质检</em>
						<span class="caret pull-left"></span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="dLabel" id="quality_ul">
						<li class="first-li">
							<input type="checkbox"> <span>全部质检</span>
						</li>												
					</ul>
				</div>
			<!-- 新增质检多选开始结束 -->


				<!-- <select class="form-control" id="quality_name" name="quality_name" onchange="searchProjectData(1)">
				  <option value="">全部质检</option>-->
<!-- 				</select>  -->
				<select  class="form-control" onchange="selectOnchange(this)">	
						<option value="-1" <c:if test="${taskStatus eq '-1'}"> selected="selected"</c:if>>所有</option>
						<option value="0" <c:if test="${taskStatus eq '0'}"> selected="selected"</c:if>>未完成</option>
						<option value="1" <c:if test="${taskStatus eq '1'}"> selected="selected"</c:if>>完成</option>
						<option value="2" <c:if test="${taskStatus eq '2'}"> selected="selected"</c:if>>暂停</option>
						<option value="3" <c:if test="${taskStatus eq '3'}"> selected="selected"</c:if>>取消</option>
				</select> 
				

				<input type="hidden" value="${projectNo}" name="projectNo" id="projectNo"> 
				
				<input type="hidden" value="${userName}" name="userName" id="userName"> 
				<input type="hidden" value="${userId}" name="userId" id="userId"> 
				<input type="hidden" value="${roleNo}" name="roleNo" id="roleNo">
				<button class="form-control" onclick="searchProjectData(1)">查询</button>
				<select class="form-control" id="approvalSelect" onchange="searchProjectData(1)">
					<option value="">无论有无出货单</option>
					<option value="1">有出货单</option>
					<option value="0">没出货单</option>
				</select>
				<c:if test="${userName == 'ninazhao' || userName=='qcdirector' || userName=='jerrylong' || userName=='Joeyqiao'}">
				    <form id="form" class="roleform form-horizontal" action="/inspection/exportInspection" style="float: right;padding: 12px 15px;">
				        <input type="hidden" name="inputKey">
				        <input type="hidden" name="userName">
				        <input type="hidden" name="quality_name">
				        <input type="hidden" name="taskStatus">
				        <input type="hidden" name="qualityNames">
						<a href="###"><button class="form-control last_btn"  onclick="exportExcel()">导出文档</button></a>
					</form>
				</c:if>	
				
				    
				
			</div>
			<div class="pull-right">
				<button class="form-control" onclick="cleanSelect()">清除所有搜索条件</button>
				<a class="select_blank" target="_blank" href="/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}">返回功能选择页</a>
				<a href="###"><button class="form-control last_btn"
						onclick="exitlogin()">退出系统</button></a>
			</div>
		</div>
		<div class="btns_search clearfix">
		<form id="form" class="roleform form-horizontal" action="/inspection/exportInspectionTask" >
		<div class="add_date" style="width:auto;">
		<div class="input-group date form_date " data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="time" name="time"
									class="form-control brt brt_7 add_input" size="16" type="text"
									value=""
									place="起始时间" placeholder="起始时间" requiredate
									style="width: 161px;"><span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
									</div>
									<span class="add_span">至</span>
		<div class="input-group date form_date" data-date=""
								data-date-format="yyyy-mm-dd">
									<input id="time1" name="time1"
									class="form-control brt brt_7 add_input" size="16" type="text"
									value=""
									place="结束时间" placeholder="结束时间" requiredate
									style="width: 161px;"><span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
								
						</div>
		<input type="hidden" name="inputKey">
				        <input type="hidden" name="userName">
				        <input type="hidden" name="quality_name">
				        <input type="hidden" name="taskStatus">
				        <input type="hidden" name="qualityNames">
						<a href="###" class="add_export"><button class="form-control last_btn"  onclick="exportExcel()">导出检验项目延期表</button></a>	
		
		
				        
	  </div>
	   </form>
		</div>
		<table class="table-bordered">
			<thead>
				<tr>
					<th class="th1">项目号</th>
					<th class="th1">项目名称</th>
					<th class="th2">当前状态</th>
					<th class="th3">预计交期</th>
					<th class="th4">船期</th>
					<th class="th5" style="width: 140px">出检日期</th>										
					<th class="th6">工厂</th>
					<th class="th6">任务发布时间</th>
					<th class="th6">类型</th>
					<th class="th7">发布人</th>
					<th class="th8">检验员</th>
					<th class="th8">开箱比例</th>
					<th class="th9">检验计划</th>
					<th class="th10">图纸</th>
					<th class="th11">详情</th>
					<th class="th11">紧急说明</th>
<!-- 					<th class="th9">出货单</th> -->
				</tr>
			</thead>
			<tbody id="taskHtml">
				<tr>
				</tr>
			</tbody>
		</table>
		<div class="page-box">
			总数:<span id="totalCount"></span>每页: <select id="selectPage"
				name="selectPage" onchange="selectPageFunc()">
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
			</select> 条 当前页/总页:<span style="color: red" id="pageNumberSpan"></span>/<span
				id="countPageSpan" style="color: red"></span>&nbsp; <a href="#"
				class="emanagergetpagea first-padding"
				onclick="searchProjectData(1)" title="第一页">首页</a> <a href="#"
				class="emanagergetpagea first-padding"
				onclick="searchProjectData(2)" title="上一页(第1页)">上页</a> <a href="#"
				class="emanagergetpagea" onclick="searchProjectData(3)"
				title="下一页(第3页)">下页</a> <a href="#"
				class="emanagergetpagea last_page" onclick="searchProjectData(4)"
				title="最后一页">尾页</a>
			<!-- 跳转到第<input type="text" class="n" value="" id="changeNumber" oninput="changePageNumber()">页 -->
		</div>
		<input type="hidden" id="pageNumber" name="pageNumber" value="">
		<input type="hidden" id="countPage" name="countPage" value="">
		<input type="hidden" id="pageSize" name="pageSize" value="20">
		<input type="hidden" id="taskStatus" name="taskStatus" value="-1">
	</div>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-form.js"></script>
</body>
</html>

<script type="text/javascript">
	Date.prototype.pattern = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, //月份         
			"d+" : this.getDate(), //日         
			"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时         
			"H+" : this.getHours(), //小时         
			"m+" : this.getMinutes(), //分         
			"s+" : this.getSeconds(), //秒         
			"q+" : Math.floor((this.getMonth() + 3) / 3), //季度         
			"S" : this.getMilliseconds()
		//毫秒         
		};
		var week = {
			"0" : "/u65e5",
			"1" : "/u4e00",
			"2" : "/u4e8c",
			"3" : "/u4e09",
			"4" : "/u56db",
			"5" : "/u4e94",
			"6" : "/u516d"
		};
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		if (/(E+)/.test(fmt)) {
			fmt = fmt
					.replace(
							RegExp.$1,
							((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
									: "/u5468")
									: "")
									+ week[this.getDay() + ""]);
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(fmt)) {
				fmt = fmt.replace(RegExp.$1,
						(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
								.substr(("" + o[k]).length)));
			}
		}
		return fmt;
	}
</script>
<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
<script>
	$(function() {		
		ajaxSelectOption();
		searchProjectData(1);
		
		
		$('#quality_ul').find('input[name="name"]').change(function(){
			searchProjectData(1);
		})
	})
	function selectOnchange(obj) {
			var taskStatus = $(obj).find('option:selected').val();
			$("#taskStatus").val(taskStatus);
		}
	function ajaxSelectOption() {
		$.ajax({
			type : "post",
			url : "${ctx}/project/queryStaff.do ",
			success : function(json) {
// 				var json = eval("(" + data +")");
				if (json.ok) {
					var qualityList = json.data.quality
					var qualityHtml ='<option value="">全部质检</option>'
					for(var i=0;i<qualityList.length;i++){
						qualityHtml+='<option value="'+qualityList[i].userName+'">'+qualityList[i].userName+'</option>'
					}
					$('#quality_name').html(qualityHtml);
				}
			}
		})
	}
	//退出功能
	function exitlogin() {
// 		$.cookie('name', '', {
// 			path : '/'
// 		});
// 		$.cookie('pass', '', {
// 			path : '/'
// 		});
		window.location.href = "${ctx}/index.jsp";
	}

	//返回主页
	function goBack() {
	    var roleNo = $("#roleNo").val();
	    var userId = $("#userId").val();
	    var userName = $("#userName").val();
	    var purchaseNameId = "";
	    window.location.href = "${ctx}/user/toIndex?userId=" + userId + "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId + "&userName=" + encodeURI(encodeURI(userName));
	}
	//清空所有条件
	function cleanSelect(){
		
		$("#inputKey").val("")
	    $('#quality_name').val("")
		$("#qualityReportSelect").val("");
	   	var totalCount = Number($("#totalCount").val());
		if (totalCount == 0) {
			$("#totalCount").val(1)
		}
		
		$('#quality_ul').find('input[name="name"]').attr("checked",false);
		$('.dropdown button em').html("全部质检");
		
		searchProjectData(1)	
	}
	//查询
	function searchProjectData(obj){
		
		
		var qualityNames = $('.dropdown button em').text();	
		if(qualityNames == "全部质检"){
			qualityNames = "";
		}
		var pageNumber = $("#pageNumber").val();
		var countPage = $("#countPage").val();
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var pageSize=$("#pageSize").val();
		var quality_name = $('#quality_name').val();
		var taskStatus = $("#taskStatus").val();
		var approvalSelect = $('#approvalSelect').val();
		var approvalSelect = $('#approvalSelect').val();
		var projectNo = $('#projectNo').val();
		
		var type = obj;
		
		// 1 第一页  2.上一页  3.下一页 4.尾页
		if (type == 1) {
			pageNumber = 1;
		}
		if (type == 2) {//上一页
			if (pageNumber == 1) {
				pageNumber = 1
			} else {
				pageNumber = Number(pageNumber) - 1;
			}
		}
		if (type == 3) {//下一页
			if (pageNumber == countPage) {
				pageNumber = countPage;
			} else {
				pageNumber = Number(pageNumber) + 1;
			}
		}
		if (type == 4) {//尾页
			pageNumber = countPage;
		}
		var inputKey = $("#inputKey").val();//关键字查询
        if(projectNo!= "" && projectNo != null && projectNo != undefined){
        	if(inputKey== "" || inputKey == null || inputKey == undefined){
        		inputKey=projectNo;
        	}
		}
		$('#taskHtml').html('')
		$('#pageNumber').val("")
		$('#pageNumberSpan').text("")
	    $('#countPage').val("")
	    $('#countPageSpan').text("")
		$.ajax({
				type : 'post',
				url : '${ctx}/inspection/selectInspection',
				data : {
					pageNumber : pageNumber,
					roleNo : roleNo,
					userId : userId,
					userName : userName,
					inputKey : inputKey,
					quality_name: quality_name,
					taskStatus:taskStatus,
					pageSize:pageSize,
					approvalSelect : approvalSelect,
					qualityNames : qualityNames,
					projectNo:projectNo
				},
				success : function(json) {
// 					var json = eval("(" + data + ")");
					var inspectionList = json.data.inspectionList;//项目数据
					var pageNumber = json.data.pageNumber
					var totalCount = json.data.count
					var pageSize=json.data.pageSize
					var userId=json.data.userId;
					var roleNo=json.data.roleNo;
					var userName=json.data.userName;
					var users=json.data.users;
					$('#pageNumber').val(pageNumber)
					$('#pageNumberSpan').text(pageNumber)
					if(totalCount){
						var countPage = Math.ceil(totalCount/pageSize)
						$('#countPage').val(countPage);
						$('#countPageSpan').text(countPage);
						$("#totalCount").text(totalCount);
					}
					
					
					//质检人员
					$('#quality_ul li').not(":first").remove();
					var options = "";
					for(var j=0;j<users.length;j++){
						options +='<option value="'+users[j].userName+'">'+users[j].userName+'</option>';
						$('#quality_ul').append('<li><input type="checkbox" onchange="selectQuality(this)" name="name"> <span>'+users[j].userName+'</span></li>');
					}
					
					
					
					
					var taskHtml="";
					if(inspectionList&&inspectionList.length>0){
						for(var i=0;i<inspectionList.length;i++){
							var inspection=inspectionList[i]; 
							 var zhijian='';
						     if(inspection.zhijian1){
						    	zhijian = inspection.zhijian1
						    	 if(inspection.zhijian2){
						    		 zhijian += '<br/>'+inspection.zhijian2
						    	 }
						     }else{ 
						    	 if(inspection.zhijian2){
						    		 zhijian = inspection.zhijian2
						    	 }
						     } 
						     var drawingHtml="";
						     if(inspection.projectDrawingList.length > 0){
						    	drawingHtml+='<a href=\'http://117.144.21.74:33168/upload/zhongwentuzhi/'+inspection.projectDrawingList[0].drawingName+'\'>图纸</a>' 
						     }
						     var planHtml="";
						     if(inspection.inspectionPlanList.length > 0){
						    	 planHtml+='<a href=\'http://117.144.21.74:33168/upload/po/upload/JIANYANJIHUAZJ/'+inspection.inspectionPlanList[0].reportName+'\'>检验计划</a>' 
						     }
						     var detailHtml='';
						     if(inspection.taskStatus=='0'){
						    	 detailHtml='<i>未完成</i>';
						     }
							 if(inspection.taskStatus=='1'){
								 detailHtml='<i>已完成</i>';				    	 
							 }
							 if(inspection.taskStatus=='2'){
								 detailHtml='<i>暂停</i>';
							 }
							 if(inspection.taskStatus=='3'){
								 detailHtml='<i>取消</i>'; 
							 }
							 if(inspection.operator!= '' && inspection.operator!= null){
								 detailHtml+='&nbsp;<i>('+inspection.operator+')</i><br>';
							 }
							 if(inspection.operatorTime!= '' && inspection.operatorTime!= null){
								 detailHtml+="&nbsp;"+(new Date(inspection.operatorTime).pattern("yyyy-MM-dd"));
							 }
							 var newDate='';
							 if(inspection.createDate!= '' && inspection.createDate!= null){
								 newDate+="&nbsp;"+(new Date(inspection.createDate).pattern("yyyy-MM-dd"));
							 }
							 //projectTask/projectTaskList?projectNo=SHS18928&searchName=&pageNumber=1&taskStatus=0&userName=ninazhao&roleNo=100&userId=180&sendOrReceive=0
							/* detailHtml+='&nbsp;<a href="${ctx}/projectTask/projectTaskList?projectNo='+inspection.projectNo+'&userId='+userId+'&userName='+userName+'&roleNo='+roleNo+'&taskStatus='+inspection.taskStatus+'&searchName='+""+'&pageNumber='+1+'&sendOrReceive='+0+'">详情操作</a>' */
							taskHtml+='<tr><td><span class="s s1">';
							if(inspection.checkNumber !=null && inspection.checkNumber!=''){
							   taskHtml+=inspection.checkNumber;
							}else{
							   taskHtml+=inspection.projectNo;
							}
							
							taskHtml+='</span></td>'
							+'<td><span class="s s2">'+(inspection.projectName == null ? "" : inspection.projectName)+'</span></td>'
							+'<td><span class="s s3">'+inspection.produceStatus+'</span></td>'
							+'<td><span class="s s4">'+(inspection.expectedDelivery == null ? '-' : new Date(inspection.expectedDelivery).pattern("yyyy-MM-dd"))+'</span></td>'
							+'<td><span class="s s5">'+(inspection.shippingDate == null ? '-' : new Date(inspection.shippingDate).pattern("yyyy-MM-dd"))+'</span></td>'
	                        +'<td style="width: 9%;"><span class="s s6"><input type="text" name="finishTime" value="'+(inspection.finishTime == null ? '-' :new Date(inspection.finishTime).pattern("yyyy-MM-dd"))+'" class="input-text form-control date-time col-xs-3 w200" placeholder="请输入出检日期" onchange="selectOnchangeUpdate(this)"/></span></td>'
	                        +'<td><span class="s s7">'+(inspection.inspectionAddress?inspection.inspectionAddress:"")+'</span>';
	                        if(inspection.dTalkIds>0){	                 
	                        	taskHtml = taskHtml + '<span style="color:red;margin-left:10px;">已入库</span>';
	                        }
	                        
	                        taskHtml = taskHtml +'</td>'
	                        +'<td><span class="s s8">'+newDate+'</span></td>'
	                        +'<td><span class="s s9">'+(inspection.testType?inspection.testType:"")+'</span></td>'
	                        +'<td><span class="s s10">'+inspection.initiator+'</span></td>'
	                        +'<td>'
		 				    +'<select class="form-control s s11" id="quality_name_select" name="quality_name" onchange="selectOnchangeAcceptor(this)">'
							+'<option value="'+inspection.accepter+'">'+inspection.accepter+'</option>'
							+ options
							+'</select>' 
	                        +'</td>'
							+'<td><div class="s s12"><button class="z-btn" type="button" onclick="openEditRate(\''+inspection.projectNoId+'\',\''+(inspection.openRate==null?'':inspection.openRate)+'\',this)">调整</button><span style="font-size: 13px;">'+(inspection.openRate==null?(inspection.testType != '出货' ? '非大货': ''):(inspection.openRate+'%'))+'</span></div></td>'
							+'<td><span class="s s13">'+planHtml+'</span></td>'
							+'<td><span class="s s14">'+drawingHtml+'</span></td>'
							+'<td><span class="s s15">'+detailHtml+'</span></td><input type="hidden" name="projectNoId" id="projectNoId" value="'+inspection.projectNoId+'"/>'
							+'<td><span class="s s16">'+inspection.urgentReason+'</span></td>'
// 							+'<td>'+((inspection.testType == '出货' || inspection.testType == '样品') ? (userName == 'ninazhao' || userName == 'AndsXue' ? (inspection.shippingApproval == 1 ? '<button class="approval add_button" onclick="change_approval(this,0,\''+inspection.projectNoId+'\')">已收到</button>' : '<button class="red add_button" onclick="change_approval(this,1,\''+inspection.projectNoId+'\')">未收到</button>') : inspection.shippingApproval == 1 ? '已收到' : '未收到') : '')+'</td>'
							+'</tr>'
						}
						$("#taskHtml").append(taskHtml);
						// 日期插件
						$(function () {
						    var currYear = (new Date()).getFullYear();
						    var opt = {};
						    opt.date = {preset: 'date'};
						    opt.datetime = {preset: 'datetime'};
						    opt.time = {preset: 'time'};
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
						// 日期结束
					}
					//根据返回的值选中
					$("#selectPage option[value='"+pageSize+"']").attr("selected","selected");
				}
	      })	
	}
	//选择分页
	function selectPageFunc(){
		var selectPage =$("#selectPage").val();
		$("#pageSize").val(selectPage);
		searchProjectData(1)
	}
	function changePageNumber(){
		var pageNumber =$("#changeNumber").val();
		$("#pageNumber").val(pageNumber);
		searchProjectData(1);
	}

	function selectOnchangeUpdate(obj){	
		 var projectNoId=$(obj).parents('tr').find("input[name='projectNoId']").val();
		 var finishTime=$(obj).parents('tr').find("input[name='finishTime']").val();
		 var userName = $("#userName").val();
		 $.ajax({
		     type:"post",                   
		     url:"${ctx}/inspection/updateInspectionReservation",           
		     data:{
		        	projectNoId:projectNoId,
		        	finishTime:finishTime,
		        	userName:userName
		     },              
		     success:function(json){  
// 		       var json = eval("(" + data +")");
			   if(json.ok){
				   layer.msg(json.message,{time:2000});
			   }else{
				   layer.msg(json.message,{time:2000});
			   }
		     }
		 });  
	}
	function selectOnchangeUpdate(obj){	
		 var projectNoId=$(obj).parents('tr').find("input[name='projectNoId']").val();
		 var finishTime=$(obj).parents('tr').find("input[name='finishTime']").val();
		 var userName = $("#userName").val();
		 $.ajax({
		     type:"post",                   
		     url:"${ctx}/inspection/updateInspectionReservation",           
		     data:{
		        	projectNoId:projectNoId,
		        	finishTime:finishTime,
		        	userName:userName
		     },              
		     success:function(json){  
// 		       var json = eval("(" + data +")");
			   if(json.ok){
				   layer.msg(json.message,{time:2000});
			   }else{
				   layer.msg(json.message,{time:2000});
			   }
		     }
		 });  
	}
	
	
	//打开修改开箱比例
	function openEditRate(projectNoId,rate,obj){
		$('#projectNoId').val(projectNoId);
		$('#openRate').val(rate);
		var index = $(obj).parents('tr').index();
		$('#index').val(index);
		$('.add_tc').show();
	}
	
	$('.add_tc .btns2 button:first-child').click(function(){
		$('.add_tc').hide();
	})
	
	//修改开箱比例
	function updateOpenRate(obj){	
		 var projectNoId = $('#projectNoId').val();
		 var openRate= $('#openRate').val();		
		 var index = $('#index').val();
		 
		 $.ajax({
		     type:"post",                   
		     url:"${ctx}/inspection/updateOpenRate",           
		     data:{
		        	projectNoId:projectNoId,
		        	openRate:openRate
		     },              
		     success:function(json){  
// 		       var json = eval("(" + data +")");
			   if(json.ok){
				   layer.msg(json.message,{time:2000});
				   $('#taskHtml').find('tr').eq(index).find('td:eq(10)').find('span').text(openRate+"%");
				   $('.add_tc').hide();
			   }else{
				   layer.msg(json.message,{time:2000});
			   }
		     }
		 });  
	}

	function selectOnchangeAcceptor(obj){	
		 var projectNoId=$(obj).parents('tr').find("input[name='projectNoId']").val();
		 var accepter=$(obj).val();
		 var userName = $("#userName").val();
		 $.ajax({
		     type:"post",                   
		     url:"${ctx}/inspection/updateInspectionReservation",           
		     data:{
		        	projectNoId:projectNoId,
		        	accepter:accepter,
		        	userName:userName
		     },              
		     success:function(json){  
// 		       var json = eval("(" + data +")");
			   if(json.ok){
				   layer.msg(json.message,{time:2000});
			   }else{
				   layer.msg(json.message,{time:2000});
			   }
		     }
		 });  
	}
	
	
	
	
	//改变准许出货确认单
	function change_approval(obj,type,projectNoId){
		
		var note = '';
		if(type == 1){
			note = '确认收到出货单？'
		}else if(type == 0){
			note = '是否更改为未收到出货单？'
		}
		
		 layer.open({
				type:1,
				skin:'finish-btn',
				title:note,
				anim:4,
				shade:0.6,
				shadeClose:true,
				btn:['确定','取消'],
			})	
		
			
		$('.finish-btn .layui-layer-btn0').click(function(){
			  $.ajax({
				    type:"post",                   
				    url:"${ctx}/inspection/updateApprovalShipping",           
				    data:{
				    	projectNoId:projectNoId,
				    	type:type
				    	 },              
				    success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  if(type == 1){
							  $(obj).removeClass('red').addClass('approval').text('已收到');
							  $(obj).attr('onclick','change_approval(this,0,\''+projectNoId+'\')');
						  }else if(type == 0){
							  $(obj).removeClass('approval').addClass('red').text('未收到');
							  $(obj).attr('onclick','change_approval(this,1,\''+projectNoId+'\')');
						  }

					  }else{
						  layer.msg(json.message,{time:2000});
					  }   
				    }
			  })
				    	 
		})
	}
	
	
	
</script>
<script>
	/*$('.task_list .dropdown-menu li input').click(function(){
		var str = '';
		$('.task_list input[name = "name"]').each(function(){
			if($(this).is(':checked')){
				str += $(this).siblings('span').text() + ',';
				alert(str)	;
				str = str.substring(0,str.length - 1);
				$('.task_list .dropdown-menu button em').text(str);
			}

			if(str !== ''){
				str = str.substring(0,str.length - 1);
				$('.task_list .dropdown-menu button em').text(str);
			}

		});
		
		
	})*/
	
		// 全选
		$('.dropdown .first-li input').click(function(){
			if ($(this).prop('checked')) {
					$(this).parent().parent().find('input').prop('checked', true);
							
				} else {
					$(this).parent().parent().find('input').prop('checked', false);
							
					$('.dropdown button em').html("");
				}
		})
		
		$('.dropdown input').click(function() {
		
		
		// 反选
		var input_len = $('.dropdown li').not('.first-li').find('input:checked').length;
		if (input_len > 11) {
			$(this).parent().siblings('.first-li').find('input').prop('checked', true);
					
		} else {
			$(this).parent().siblings('.first-li').find('input').prop('checked', false);
					
		}
		

	})
	

	//质检选择事件
	function selectQuality(obj){
		  var s = '';
			// 获取赋值
			$('.dropdown input[name="name"]').each(function() {
				if ($(this).is(':checked')) {
					s += $(this).siblings('span').text() + ",";
				}
			});
			if (s != '') {
				s = s.substring(0, s.length - 1);
				$('.dropdown button em').html(s);
			};
			$('.dropdown button em').attr('title',s);
	}
	
	
	
		//enter事件
		document.onkeydown = function (e) {
		    if (!e) e = window.event;
		    if ((e.keyCode || e.which) == 13) {
		    	searchProjectData(1);
		    }
		}
		
		
	/**
	 * 导出文档
	 */
	function exportExcel(){
		var qualityNames = $('.dropdown button em').text();	
		if(qualityNames == "全部质检"){
			qualityNames = "";
		}
		var userName = $("#userName").val();
		var quality_name = $('#quality_name').val();
		if(!quality_name){
			quality_name = '';
		}
		var taskStatus = $("#taskStatus").val();
		var inputKey = $("#inputKey").val();//关键字查询
		
		$('#form').find('input[name="userName"]').val(userName);
		$('#form').find('input[name="quality_name"]').val(quality_name);
		$('#form').find('input[name="taskStatus"]').val(taskStatus);
		$('#form').find('input[name="qualityNames"]').val(qualityNames);
		$('#form').find('input[name="inputKey"]').val(inputKey);
		
		$('#form').submit();
	}
	
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
</script>




