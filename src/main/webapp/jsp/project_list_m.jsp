                                        <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String roleNo = request.getParameter("roleNo");
	String userId = request.getParameter("userId");
	String userName = request.getParameter("userName");
	String purchaseNameId = request.getParameter("purchaseNameId");
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>项目列表-手机</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">		
<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css" />
<link rel="stylesheet" href="../css/add.css">
<style>
.span-right {
	display: initial;
	float: none;
}
</style>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
</head>
<body>
<div class="project_list_m">
	<!--遮罩层-->
	<div id="mask"></div>
	<div class="index-main home">
		<div class="index-top">
			<div class="clearfix">
				<span class="index-active">项目列表</span>
				<!-- <span id="tsak-list">采购任务列表</span>
				<span onclick="pcUrl()"
					style="background: #7FFFD4">电脑浏览</span> -->
					<a class="bgcolor_ff0  btn pull-right" href="/user/toIndex" >返回功能选择页</a><br>
<!-- 					<a class="bgcolor_ff0  btn pull-right" href="/jsp/project_list_task.jsp" >返回任务列表</a> -->
			</div>

		</div>
		<input type="hidden" id="roleNo" value="<%=roleNo%>"> 
		<input type="hidden" id="userId" value="<%=userId%>"> 
		<input type="hidden" id="userName" value="<%=userName%>"> 
		<input type="hidden" id="purchaseNameId" value="<%=purchaseNameId%>">
		<input type="hidden" id="flag" value="<%=request.getParameter("flag") == null ? "" : request.getParameter("flag")%>">
		<input type="hidden" id="pageSize" value="8"> 
		<input type="hidden" id="pageNumber" value="1"> 
		<input type="hidden" id="totalCount" value="10"> 
		<input type="hidden" id="operatorType" value="0">
		<div class="index-middle">
			<div class="div-bg">				
				<!-- 新加和PC搜索菜单保持一致 开始-->
				<form id="form" class="roleform form-horizontal" onsubmit="return false;">
					<div class="form-group form-group1">
					<button id="cleanSelect" type="button" class="left btn btn-primary bgcolor_ff0 form-control" onclick="cleanSel()">清空所有关键词和条件</button>
					</div>				
					<div class="search clearfix mt10">
						<label for="input-key" class="pull-left control-label">关键字搜索：</label> 
						<input type="text" class="pull-left input-text form-control" id="inputKey" placeholder="项目号/项目名/姓名" />
						<button class="pull-right btn btn-primary bgcolor_ff0" id="searchBut" style="margin-bottom: 4px;">搜索</button><br>
					</div >
	
					<div class="form-group form-group1 mt10 screen">
						<label for="firstname" class="col-xs-3 control-label pl0">人员筛选</label>
						<div class="col-xs-3 showHtml">
							<select id="purchase_name" name="purchase_name"
								class="form-control">
								<option value="">全部采购</option>
							</select><span></span>
						</div>
						<div class="col-xs-3 showHtml">
							<select id="documentary_name" name="documentary_name"
								class="form-control">
								<option value="">全部跟单</option>
							</select><span></span>
						</div>
						<div class="col-xs-3 showHtml">
							<select id="quality_name" name="quality_name" class="form-control">
								<option value="">全部质检</option>
							</select><span></span>
						</div>
					</div>
					<div class="form-group form-group2 other_gn mt10">
						<label class="control-label col-xs-12 pl0 ">其他独立排列功能</label>
						<select class="form-control col-xs-12" id="sort_select">
						    <option value=""></option>
						    <option value="处于暂停状态 超过2周的项目">处于暂停状态 超过2周的项目</option>
							<option value="过去2周取消的项目">过去2周取消的项目</option>
							<option value="有PO 合同未签的项目，而且 PO时间距离今天超过5天">有PO 合同未签的项目，而且 PO时间距离今天超过5天</option>
							<option value="处于样品完结状态 超过一个月的项目">处于样品完结状态 超过一个月的项目</option>
							<option value="进行中有紧急交货日的项目">进行中有紧急交货日的项目</option>
							<option value="质检报告更新倒序排序">质检报告更新倒序排序</option>
							<option value="今后7天预计出货">今后7天预计出货</option>
							<option value="最近两周没更新周报的A/B级项目">最近两周没更新周报的A/B级项目</option>
						</select>
					</div>
				</form>
				<!-- 新加和PC搜索菜单保持一致结束 -->
				<div class="red mt10 f14" style="display:none;">
				无该项目，请联系项目跟单到ERP上该项目页添加您的姓名，并务必按"保存"键。</div>
			</div>
			<!-- 项目列表 -->
			<div class="index-task" id="projectDataList"></div>				
			<!-- 任务列表显示 -->
			<!-- <div class="index-task" id="projectTaskList"></div> -->
		</div>
</div>
	<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
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
		
		
		
		//enter事件
		document.onkeydown = function (e) {
	        if (!e) e = window.event;
	        if ((e.keyCode || e.which) == 13) {
	        	$("#searchBut").click();
	        }
	    }
	</script>
	<script type="text/javascript">
		var isDoQuery = false;
		//最大页数
		var maxPage = 1;
		//滚动页
		var scrollPage = 0;
		//当前页面高度
		var cHeight = $('.index-middle').height();
		var windowHeight = 0;
		var scrollHeight = 0;
		//一进来加载默认数据
		$(function() {

			var roleNo = $('#roleNo').val()
			
			if(roleNo==6 || roleNo==69 || roleNo == 1020){
				$('#purchase_name').hide()
			}
			if(roleNo==5 || roleNo == 1020){
				$('#documentary_name').hide()
			}
			if(roleNo==9 || roleNo==69 || roleNo == 1020){
				$('#quality_name').hide()
			}
								
			ajaxSelectOption()			
			showload(1);

			//移动端滚动分页
	        //如果是完成项目禁止滚动事件		       
	        	$('.index-middle').scroll(function(){
		            var scrollTop = $(this).scrollTop();
		            windowHeight = cHeight + scrollTop;
		            scrollHeight = $('.index-task').height();
		            console.log("scrollTop:"+scrollTop);
		            console.log("scrollHeight:"+scrollHeight);
                    console.log("当前高度:"+(windowHeight));
		            if (windowHeight >= scrollHeight) {
		                if(scrollPage == maxPage){
		                    return false;
		                }
		                if(isDoQuery){
		                  showload(1);
		                }
		            }
		        })								
		});


		/**分页加载数据*/
		function showload(projectType) {
			scrollPage++;
			console.log("begin query,pageNumber:" + scrollPage);
			doQuery(scrollPage,projectType);				
		}

		
		//页面查询按钮
		$("#projectSummary")
				.click(
						function() {
							var roleNo = $("#roleNo").val();
							var userId = $("#userId").val();
							var userName = $("#userName").val();
							window.location.href = "${ctx}/jsp/project-summary.jsp?roleNo="
									+ roleNo
									+ "&userId="
									+ userId
									+ "&userName=" + userName;
						})
		//页面查询按钮
		$("#exitlogin").click(function() {
			$.cookie('name', '', {
				path : '/'
			});
			$.cookie('pass', '', {
				path : '/'
			});
			window.location.href = "${ctx}/index.jsp";
		})

		//页面查询按钮
		$("#searchBut").click(function() {
			var totalCount = Number($("#totalCount").val());
			if (totalCount == 0) {
				$("#totalCount").val(1)
			}
             //查询初始化
			scrollPage = 0;
			windowHeight = 0;
			showload(2);
		})
		//根据选择的采购查询列表
		function selectOnchange() {
			
		
			var totalCount = Number($("#totalCount").val());
			if (totalCount == 0) {
				$("#totalCount").val(1)
			}
			queryObj.operatorType = 1;
			queryObj.preNum = 1;
			queryObj.isPre = false;
			queryObj.nextNum = 1;
			queryObj.isNext = false;
			queryObj.curNum = 0;
			showload(2);
		}

		//消息中心
		$("#messageCenter").click(
				function() {
					var roleNo = $("#roleNo").val();
					var userId = $("#userId").val();
					var userName = $("#userName").val();
					$("#messageNum").val('0');
					window.location.href = "${ctx}/jsp/message.jsp?roleNo="
							+ roleNo + "&userId=" + userId + "&userName="
							+ userName;
				})
	


		function doQuery(pageNumber,operatorType) {
			
			var roleNo = $("#roleNo").val();
			var userId = $("#userId").val();
			var userName = $("#userName").val();
			var inputKey = $("#inputKey").val();//关键字查询
		
			var projectType = $("#projectType").val();
			if(projectType=="" ||projectType==null ||projectType==undefined){
				projectType="-1";
			}
			var projectStage = $('#projectStage').val()
			var purchase_name = $('#purchase_name').val()
			var documentary_name = $('#documentary_name').val()
			var quality_name = $('#quality_name').val()
			
			var qualityReportSelect = "";
			var importantSelect ="";
			var expectedShipmentSelect="";
			if($('#sort_select').val() == '质检报告更新倒序排序'){
				qualityReportSelect=1;
			}
			if($('#sort_select').val() == '最近两周没更新周报的A/B级项目'){
				importantSelect = 1;
			}else if($('#sort_select').val() == '处于暂停状态 超过2周的项目'){
				importantSelect = 2;		
			}else if($('#sort_select').val() == '过去2周取消的项目'){
				importantSelect = 3;			
			}else if($('#sort_select').val() == '有PO 合同未签的项目，而且 PO时间距离今天超过5天'){
				importantSelect = 4;			
			}else if($('#sort_select').val() == '处于样品完结状态 超过一个月的项目'){
				importantSelect = 5;				
			}else if($('#sort_select').val() == '进行中有紧急交货日的项目'){
				importantSelect = 6;				
			}
			
			if($('#sort_select').val() == '今后7天预计出货'){
				expectedShipmentSelect=1;
			}
			
			
			var projectTypeS = "";
			var projectStageS = "";
			var plantAnalysisS = "";
			var detailStatusS = "";      //细节状态  
			var delayStatusS = "";       //延期项目

			$('input[name="projectStage"]:checked').each(function() {
				projectStageS += $(this).val() + ",";
			});
			if($('.dropdown2').find('input:first').is(':checked')){
				projectStageS = "";
			}
			

			$('input[name="plantAnalysis"]:checked').each(function() {
				plantAnalysisS += $(this).val() + ",";
			});
			if($('.dropdown3').find('input:first').is(':checked')){
				plantAnalysisS = "";
			}
			
	        //状态
			$('input[name="projectType"]:checked').each(function() {
				projectTypeS += $(this).val() + ",";
			});
			if($('.dropdown1').find('input:first').is(':checked')){
				projectTypeS = "";
			}
	        
	        
	        //细节状态
			$('input[name="detailStatus"]:checked').each(function() {
				detailStatusS += $(this).val() + ",";
			});
	        
	        //延期状态
			$('input[name="delayStatus"]:checked').each(function() {
				delayStatusS += $(this).val() + ",";
			});
			if($('.dropdown6').find('input:first').is(':checked')){
				delayStatusS = "";
			}
			
			
			$.ajax({
						type : 'post',
						url : '${ctx}/project/showList',
						sync : true,
						data : {
							pageNumber : pageNumber,
							operatorType : operatorType,
							
							roleNo : roleNo,
							userId : userId,
							userName : userName,
							inputKey : inputKey,

							projectStage:projectStage,
							
							purchase_name : purchase_name,
							documentary_name :documentary_name,
							quality_name: quality_name,
							
							qualityReportSelect:qualityReportSelect,
							importantSelect:importantSelect,
							expectedShipmentSelect:expectedShipmentSelect,
							
							projectTypeS:projectTypeS,
							projectStageS : projectStageS,
							plantAnalysisS : plantAnalysisS,
							detailStatusS:detailStatusS,
							delayStatusS:delayStatusS
						},
						success : function(json) {							
// 							var json = eval("(" + data + ")");
							var projectDataList = json.data.list;//项目数据
							var delayNum = json.data.delayNum; //延期数目
							var messageNum = json.data.messageNum;//消息数目
							var projectNum = json.data.projectNum;//项目数量
							var projectType = json.data.projectType; //状态类型(完成,未完成,暂停)
							var roleNo = json.data.roleNo;//角色代码
							var taskList = json.data.taskList;//任务列表
							var userId = json.data.userId;//用户Id
							var userName = json.data.userName;//用户名
							var pageSize = json.data.pageSize;
							var pageNumber = json.data.pageNumber;
							var totalCount = json.data.totalCount;
							maxPage = parseInt(totalCount/10);
							$("#totalCount").val(totalCount);
							//var totalCountFinish=json.data.totalCountFinish;//大货完成数量
							var purchaseNameId = json.data.purchaseNameId;//采购姓名查询
							if(operatorType == 2){
// 								$("#purchaseSelect").empty();//采购下拉列表
								$("#projectDataList").empty();
							}
							
		
							if (json.ok) {
								var dataHtml = '';
								var taskHtml = '';
								
								
								if (projectDataList&& projectDataList.length > 0) {
									
									for (var i = 0; i < projectDataList.length; i++) {//项目列表
										dataHtml += '<a href="${ctx}/project/showDetails?projectNo='
											+ projectDataList[i].projectNo
											+ '&roleNo='
											+ roleNo
											+ '&userId='
											+ userId
											+ '&userName='
											+ userName
											+ '">'
											+'<dl class="index-list"><dd><p class="pc_p"><span class="span-left"></span><span class="span-right porject-number">'
												+ projectDataList[i].projectNo
												+ '</span></p>'
										        + '<p class="pc_p"><span class="span-left"></span><span class="span-right project-name">'
												+ (projectDataList[i].projectName?projectDataList[i].projectName:"")
												+ '</span></p>'
										        + '<p class="pc_p"><span class="span-left"></span><span class="span-right project-name">'
												+ (projectDataList[i].companyName?projectDataList[i].companyName:"")
												+ '</span></p>'
												
										if (roleNo == 5) {
											dataHtml += '<p class="pc_p"><span class="span-left">采购：</span><span class="span-right project-person">'
													+ (projectDataList[i].purchaseName ? projectDataList[i].purchaseName:"")
													+ '</span></p>'
													
										} else if (roleNo == 6) {
											dataHtml += '<p class="pc_p"><span class="span-left">跟单销售：</span><span class="span-right project-person">'
													+ (projectDataList[i].sellName ?projectDataList[i].sellName :"")
													+ '</span></p>'
										} else {
											dataHtml += '<p class="pc_p"><span class="span-left">跟单销售：</span><span class="span-right project-person">'
												+ (projectDataList[i].sellName ?projectDataList[i].sellName :"")
													+ '</span></p>'
											dataHtml += '<p class="pc_p"><span class="span-left">采购：</span><span class="span-right project-person">'
													+ (projectDataList[i].purchaseName ? projectDataList[i].purchaseName: "")
													+ '</span></p>'
										}
												
										if (projectDataList[i].deliveryDate != null&& projectDataList[i].deliveryDate != '') {
											dataHtml += '<p class="pc_p"><span class="span-left">大货交期：</span><span class="span-right project-date">'
													+ (new Date(projectDataList[i].deliveryDate).pattern("yyyy-MM-dd"))+ '</span></p>'
										} else {
											if(projectDataList[i].sampleScheduledDate!=null && projectDataList[i].sampleScheduledDate!=''){
												dataHtml += '<p class="pc_p"><span class="span-left">样品交期：</span><span class="span-right project-date">'
												+ (new Date(projectDataList[i].sampleScheduledDate).pattern("yyyy-MM-dd"))+ '</span></p>'
											}else{
												dataHtml += '<p class="pc_p"><span class="span-left">交期：</span><span class="span-right project-date"></span></p>'
											}
										}
										if(projectDataList[i].exportDate != null&& projectDataList[i].exportDate != ''){
											dataHtml += '<p class="pc_p"><span class="span-left">报关日期：</span><span class="span-right project-date">'
												+ (new Date(projectDataList[i].exportDate).pattern("yyyy-MM-dd"))+ '</span></p>'
										}
										
										dataHtml += '<p class="project-status pc_p"><span class="span-left">项目状态：</span><span class="span-right project-state">'
										if (projectDataList[i].warningStatus == 1) {
											dataHtml += " 质量投诉,";
										}
										if (projectDataList[i].importance == 1) {
											dataHtml += " 重要,";
										}
										dataHtml +=(projectDataList[i].status?projectDataList[i].status:"")						
										dataHtml += '<input type="hidden" value="'+projectDataList[i].flag+'" class="project-flag">'
										if (projectDataList[i].flag == 1) {
											dataHtml += '<p class="pc_p"><span class="span-left warning-project">项目快到期了</span></p>'
										}
										if (projectDataList[i].flag == 2) {
											dataHtml += '<p class="pc_p"><span class="span-left delay-project">项目已延期</span></p>'
										}
										if (projectDataList[i].projectReportDate != null) {
											dataHtml += '<p class="pc_p"><span class="span-left delay-project">周报更新时间:'
												+ (new Date(projectDataList[i].projectReportDate).pattern("yyyy-MM-dd"))+ '</span></p>'
										}
										if (projectDataList[i].projectReportList.length > 0) {
											dataHtml += '<p class="pc_p"><span class="span-left delay-project" style="color:green">已更新周报</span></p>';
										} else {
											dataHtml += '<p class="pc_p"><span class="span-left delay-project" style="color:red">未更新周报</span></p>';
										}
										dataHtml +='<p class="detail-link"></dd></dl></a>'
									}
								
									$("#projectDataList").append(dataHtml); //项目列表 															
									$("#projectNum").text(projectNum);      //项目总数
									$("#delayNum").text(delayNum);          //延期数量

								} 
								
								
								//获取当前页面高度
								 scrollHeight = $('.index-task').height();
								 isDoQuery = true;

								//$("#selectProjectType").append(typeHtml);

								$("#selectProjectType").find(
										'input[type=radio]').each(function() {

									if ($(this).val() == projectType) {
										$(this).prop('checked', true)
									}
								})

								if (roleNo == 6) {//显示采购下拉框(销售,管理员显示)
									$("#purchaseSelect").css("display", "none");
								} else {
									$("#purchaseSelect")
											.css("display", "block");
								}
								//下拉框重新赋值
								if (!(purchaseNameId == null
										|| purchaseNameId == "" || purchaseNameId == "0")) {
									$("#purchase_id").val(purchaseNameId);
								}
								
								//获取当前页面高度
								 scrollHeight = $('.index-task').height();
								 isDoQuery = true;
								
								//项目列表延期与快到期标记
								$('.index-list')
										.each(
												function(i) {
													var project_flag = $(
															'.project-flag')
															.eq(i).val();
													if (project_flag == 1) {
														$(this)
																.css("border",
																		"1px solid deepskyblue");
													} else if (project_flag == 2) {
														$(this)
																.css("border",
																		"1px solid red");
													}
												})
								
						} else if(!json.ok && json.message == '请先登录'){
								 window.location = '/index.jsp';
						}							
						},
						error : function() {
							isDoQuery = false;
						}
					});
		}

		/**分页加载数据*/
	/* 	function showload() {
			var totalCount = Number($("#totalCount").val());
			pageNumber = queryObj.curNum + 1;
			console.log("begin query,pageNumber:" + pageNumber);
			if (!isDoQuery
					|| (queryObj.preNum == pageNumber && !queryObj.isPre)
					|| (queryObj.nextNum == pageNumber && !queryObj.isNext)) {
				console.log("preNum:" + queryObj.preNum + "," + "isQuery:"
						+ queryObj.isPre + ",curNum:" + queryObj.curNum
						+ ",nextNum:" + queryObj.nextNum + "," + "isQuery:"
						+ queryObj.isNext);

				var residue = totalCount % 10;
				var divider = parseInt(totalCount / 10);
				if (residue > 0) {
					divider = divider + 1;
				}
				if (pageNumber <= divider) {
					projectDataLoad(pageNumber);
				} else {
					return true;
				}
			} else {
				return false;
			}
		} */

		function ajaxSelectOption() {

			$
					.ajax({
						type : "post",
						url : "${ctx}/project/queryStaff.do ",
						success : function(json) {
// 							var json = eval("(" + data + ")");
							if (json.ok) {

								var purchaseList = json.data.purchase
								var saleList = json.data.sale
								var qualityList = json.data.quality

								var purchaseHtml = '<option value="">全部采购</option><option value="无采购">无采购</option>'
								var saleHtml = '<option value="">全部跟单</option>'
								var qualityHtml = '<option value="">全部质检</option>'

								for (var i = 0; i < purchaseList.length; i++) {
									purchaseHtml += '<option value="'+purchaseList[i].userName+'">'
											+ purchaseList[i].userName
											+ '</option>'
								}
								for (var i = 0; i < saleList.length; i++) {
									saleHtml += '<option value="'+saleList[i].userName+'">'
											+ saleList[i].userName
											+ '</option>'
								}
								for (var i = 0; i < qualityList.length; i++) {
									qualityHtml += '<option value="'+qualityList[i].userName+'">'
											+ qualityList[i].userName
											+ '</option>'
								}

								$('#purchase_name').html(purchaseHtml)
								$('#documentary_name').html(saleHtml)
								$('#quality_name').html(qualityHtml)

							}
						}
					})

		}

		function cleanSel() {
			$("#inputKey").val("");
			$("#projectType").val("");
			$('#projectStage').val("");
			$('#purchase_name').val("");
			$('#documentary_name').val("");
			$('#quality_name').val("");

			$('#sort_select').find("option:first").attr("selected", true);
			$('.form-horizontal .dropdown3 button').html('全部状态');
			$('.form-horizontal .dropdown4 button').html('全部细节状态');
			$('.form-horizontal .dropdown5 button').html('全部');
			$('.form-horizontal .dropdown6 button').html('全部阶段');
			$('.form-horizontal .dropdown7 button').html('全部等级');
			$('input[name="projectStage"]:checked').attr("checked", false);
			$('input[name="plantAnalysis"]:checked').attr("checked", false);
			$('input[name="projectType"]:checked').attr("checked", false);
			$('input[name="detailStatus"]:checked').attr("checked", false);
			$('input[name="delayStatus"]:checked').attr("checked", false);

			var totalCount = Number($("#totalCount").val());
			if (totalCount == 0) {
				$("#totalCount").val(1)
			}
// 			queryObj.operatorType = 1;
// 			queryObj.preNum = 1;
// 			queryObj.isPre = false;
// 			queryObj.nextNum = 1;
// 			queryObj.isNext = false;
// 			queryObj.curNum = 0;
			showload();
		}

		function pcUrl() {
			var roleNo = $('#roleNo').val()
			var um = $("#userName").val();
			var ui = $("#userId").val();
			window.location.href = "${ctx}/project/showListNew?userName=" + um
					+ "&userId=" + ui + "&roleNo=" + roleNo
		}
		//同意 
		function applayDelaySub(obj, projectNo) {
			var type = obj;
			var projectNo = projectNo;
			var userName = $("#userName").val();
			$.ajax({
				type : "post",
				data : {
					type : type,
					projectNo : projectNo,
					userName : userName
				},
				url : "${ctx}/project/agreeDelayApply",
				success : function(json) {
// 					var json = eval("(" + data + ")");
					if (json.ok) {
						layer.msg(json.message)
					}
				}
			})
		}
		// 搜索筛选显示切换
		$('.dropdown button').click(function() {
			$(this).siblings('ul,.div_transparent').toggle();
		});
		$('.div_transparent').click(function() {
			$('.div_transparent,.home .dropdown ul').hide();
		})
		// 多选默认状态
		$('.dropdown input').prop('checked', true);
		// 项目状态				
		$('.dropdown3 input').click(
				function() {
					if ($(this).parent().parent().hasClass('first-li')) {
						$('.dropdown3 button em').html('全部状态');
						if ($(this).is(':checked')) {
							$('.dropdown3 input').prop('checked', true);
						} else {
							$('.dropdown3 input').prop('checked', false);
						}
						;
					} else {
						// 多选反选
						var input_len = $('.dropdown3 li').not('.first-li')
								.find('input:checked').length;
						if (input_len > 6) {
							$(this).parent().parent().siblings('.first-li')
									.find('input').prop('checked', true);
							$('.dropdown3 button em').html('全部状态');
						} else {
							$(this).parent().parent().siblings('.first-li')
									.find('input').prop('checked', false);
							// 获取赋值
							var s = '';
							$('.dropdown3 input[name="projectType"]').each(
									function() {
										if ($(this).is(':checked')) {
											s += $(this).siblings('span')
													.text()
													+ ",";
										}
									});
							if (s != '') {
								s = s.substring(0, s.length - 1);
								$('.dropdown3 button em').html(s);
							}
						}
						;

					}
					;
				});

		//细节状态
		$('.dropdown4 input').click(
				function() {
					if ($(this).parent().parent().hasClass('first-li')) {
						$('.dropdown4 button em').html('全部细节状态');
						//$('.dropdown5 input').prop('checked',true);		
						if ($(this).is(':checked')) {
							$('.dropdown4 input').prop('checked', true);
						} else {
							$('.dropdown4 input').prop('checked', false);
						}
						;
					} else {
						// 多选反选
						var input_len = $('.dropdown4 li').not('.first-li')
								.find('input:checked').length;
						if (input_len > 3) {
							$(this).parent().parent().siblings('.first-li')
									.find('input').prop('checked', true);
							$('.dropdown4 button em').html('全部细节状态');
						} else {
							$(this).parent().parent().siblings('.first-li')
									.find('input').prop('checked', false);
							// 获取赋值
							var s = '';
							$('.dropdown4 input[name="detailStatus"]').each(
									function() {
										if ($(this).is(':checked')) {
											s += $(this).siblings('span')
													.text()
													+ ",";
										}
									});
							if (s != '') {
								s = s.substring(0, s.length - 1);
								$('.dropdown4 button em').html(s);
							}
						}
						;

					}
					;

				});
		// 延期
		$('.dropdown5 input').click(
				function() {
					if ($(this).parent().parent().hasClass('first-li')) {
						$('.dropdown5 button em').html('全部');
						// $('.dropdown6 input').prop('checked',true);		
						if ($(this).is(':checked')) {
							$('.dropdown5 input').prop('checked', true);
						} else {
							$('.dropdown5 input').prop('checked', false);
						}
						;
					} else {
						// 多选反选
						var input_len = $('.dropdown5 li').not('.first-li')
								.find('input:checked').length;
						if (input_len > 1) {
							$(this).parent().parent().siblings('.first-li')
									.find('input').prop('checked', true);
							$('.dropdown5 button em').html('全部');
						} else {
							$(this).parent().parent().siblings('.first-li')
									.find('input').prop('checked', false);
							// 获取赋值
							var s = '';
							$('.dropdown5 input[name="delayStatus"]').each(
									function() {
										if ($(this).is(':checked')) {
											s += $(this).siblings('span')
													.text()
													+ ",";
										}
									});
							if (s != '') {
								s = s.substring(0, s.length - 1);
								$('.dropdown5 button em').html(s);
							}
						}
						;

					}
					;
				})
		// 项目阶段
		$('.dropdown6 input').click(
				function() {
					if ($(this).parent().parent().hasClass('first-li')) {
						$('.dropdown6 button em').html('全部阶段');
						// $('.dropdown2 input').prop('checked',true);	
						if ($(this).is(':checked')) {
							$('.dropdown6 input').prop('checked', true);
						} else {
							$('.dropdown6 input').prop('checked', false);
						}
						;
					} else {
						// 多选反选
						var input_len = $('.dropdown6 li').not('.first-li')
								.find('input:checked').length;
						if (input_len > 3) {
							$(this).parent().parent().siblings('.first-li')
									.find('input').prop('checked', true);
							$('.dropdown6 button em').html('全部阶段');
						} else {
							$(this).parent().parent().siblings('.first-li')
									.find('input').prop('checked', false);
							// 获取赋值
							var s = '';
							$('.dropdown6 input[name="projectStage"]').each(
									function() {
										if ($(this).is(':checked')) {
											s += $(this).siblings('span')
													.text()
													+ ",";
										}
									});
							if (s != '') {
								s = s.substring(0, s.length - 1);
								$('.dropdown6 button em').html(s);
							}
						}
						;

					}
					;

				});
		// 项目等级
		$('.dropdown7 input').click(
				function() {
					if ($(this).parent().parent().hasClass('first-li')) {
						$('.dropdown7 button em').html('全部等级');
						// $('.dropdown3 input').prop('checked',true);		
						if ($(this).is(':checked')) {
							$('.dropdown7 input').prop('checked', true);
						} else {
							$('.dropdown7 input').prop('checked', false);
						}
						;
					} else {
						// 多选反选
						var input_len = $('.dropdown7 li').not('.first-li')
								.find('input:checked').length;
						if (input_len > 3) {
							$(this).parent().parent().siblings('.first-li')
									.find('input').prop('checked', true);
							$('.dropdown7 button em').html('全部等级');
						} else {
							$(this).parent().parent().siblings('.first-li')
									.find('input').prop('checked', false);
							// 获取赋值
							var s = '';
							$('.dropdown7 input[name="plantAnalysis"]').each(
									function() {
										if ($(this).is(':checked')) {
											s += $(this).siblings('span')
													.text()
													+ ",";
										}
									});
							if (s != '') {
								s = s.substring(0, s.length - 1);
								$('.dropdown7 button em').html(s);
							}
						}
						;

					}
					;

				});
	</script>
	
</body>
</html>

















