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
<title>项目首页</title>
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
	<!--遮罩层-->
	<div id="mask"></div>
	<div class="index-main home">
		<div class="index-top">
			<div>
				<span class="index-active">项目列表</span><span id="tsak-list">采购任务列表</span>
				<span onclick="pcUrl()"
					style="background: #7FFFD4">电脑浏览</span>
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
				<!-- <div class="key-search">
					<button class="left btn btn-primary" id="exitlogin"
						style="margin-left: 10px;">退出系统</button>
					<button id="cleanSelect" class="left btn btn-primary"
						style="margin-left: 10px; width: 150px;" onclick="cleanSelect()">清空所有关键词和条件</button>
					<span class="span-left">项目总数：</span><span class="span-left"
						id="projectNum"></span> 
						<span class="span-left">延期总数：</span><span class="span-left animation-scale" id="delayNum" style="color: red"></span>

				</div> -->

				<!-- <div class="key-search">
					<div style="overflow:hidden;">
					<label for="input-key" class="left control-label">关键字搜索：</label> <input
						type="text" class="left input-text form-control" id="inputKey"
						value="" placeholder="项目号/项目名/姓名" />
					<button class="left btn btn-primary" id="searchBut">搜索</button><br>
					</div >
					<div class="red" style="color:red;position:relative;top:0;left:0;">无该项目，请联系项目跟单到ERP上该项目页添加您的姓名，并务必按"保存"键。</div>
				</div>
				 -->
				 <!-- <div class="index-link">
					<p style="line-height: 0.5rem; height: 0.60rem; margin: 0.20rem;">
						<span class="span-right form-inline"> <label class="pull-left">人员筛选</label>
						 <select id="purchase_name" style="width: 1.8rem; height: 0.50rem;"
							onchange="selectOnchange()" class="form-control">
								<option value="">全部采购</option>
							    <option value="无采购">无采购</option>
						</select> 
						<select id="documentary_name"
							style="width: 1.8rem; height: 0.50rem;"
							onchange="selectOnchange()" class="form-control">
								<option value="">全部跟单</option>
						</select> 
						<select id="quality_name" style="width: 1.8rem; height: 0.50rem;"
							onchange="selectOnchange()" class="form-control">
								<option value="">全部质检</option>
						</select>

						</span>

					</p>
				</div> -->
				<!-- <div class="index-link">
					<p style="line-height: 0.5rem; height: 0.60rem; margin: 0.20rem;">

						<label>项目状态</label> <select name="projectType" id="projectType"
							value="" style="width: 2.0rem; height: 0.50rem;"
							onchange="selectOnchange()">
							<option value="-1">所有项目</option>
							<option value='0'>新立项项目</option>
							<option value='1'>进行中项目</option>
							<option value='6'>样品完结项目</option>  
							<option value='2'>大货完结项目</option>
							<option value='4'>暂停项目</option>
							<option value='5'>取消项目</option>
						</select> <label style="margin-left: 20px;">项目阶段</label> 
						<select
							name="projectStage" id="projectStage" value=""
							style="width: 2.0rem; height: 0.50rem;"
							onchange="selectOnchange()">
							<option value="">全部</option>>
							<option value='0'>样品</option>
							<option value='1'>模具</option>
							<option value='2'>大货</option>
						</select>
					</p>
				</div> -->
					
				<!-- 新加和PC搜索菜单保持一致 开始-->
				<form id="form" class="roleform form-horizontal" onsubmit="return false;">
					<div class="form-group form-group1">
					<button id="cleanSelect" class="left btn btn-primary"  style="margin-left:10px;margin-bottom:10px;width: 90%;" onclick="cleanSel()">清空所有关键词和条件</button><br>
					</div>
				<label for="input-key" class="left control-label">关键字搜索：</label> 
					<div style="overflow:hidden;display: flex;">
					<input type="text" class="left input-text form-control" id="inputKey" placeholder="项目号/项目名/姓名" />
					<button class="left btn btn-primary" id="searchBut" style="margin-bottom: 4px;">搜索</button><br>
					</div >
	
					<div class="form-group form-group1">
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
							<option value="进行中有设置过延期的项目">进行中有设置过延期的项目</option>
							<option value="质检报告更新倒序排序">质检报告更新倒序排序</option>
							<option value="今后7天预计出货">今后7天预计出货</option>
							<option value="最近两周没更新周报的A/B级项目">最近两周没更新周报的A/B级项目</option>
						</select>
					</div>
<!-- 					<div class="form-group form-group3 mt10">
						<label class="col-xs-3 pl0 pr0 label_state">项目状态</label> <span></span>
						改变 开始
						<div class="dropdown dropdown3 col-xs-9 pl0 pr0">
							<button id="dLabel" type="button" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false" class="btn btn-default col-xs-12 text-left">
								<em>全部状态</em> 
								<span class="caret pull-right"></span>
							</button>
							<div class="div_transparent"></div>
							<ul class="ul3">
								<li class="first-li"><label><input type="checkbox"
										  value="-1"><span>全部状态</span></label>
								<li><label><input type="checkbox" name="projectType"
										 value='0'><span>新立项</span></label></li>
								<li><label><input type="checkbox" name="projectType"
										 value='1'><span>进行中</span></label></li>
								<li><label><input type="checkbox" name="projectType"
										 value='6'><span>样品完结</span></label></li>							
								<li><label><input type="checkbox" name="projectType"
										 value='2'><span>大货完结</span></label></li>
								<li><label><input type="checkbox" name="projectType"
										 value='5'><span>项目取消</span></label></li>
								<li><label><input type="checkbox" name="projectType"
										 value='4'><span>项目暂停</span></label></li>		
							</ul>
						</div>
						改变 结束
					</div>
					<div class="form-group form-group4 mt10">
						<label class="col-xs-3 pl0 pr0 label_state">细节状态</label> <span></span>
						改变 开始
						<div class="dropdown dropdown4 col-xs-9 pl0 pr0">
							<button id="dLabel" type="button" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"  class="btn btn-default col-xs-12 text-left">
								<em>全部细节状态</em> 
								<span class="caret pull-right"></span>
							</button>
							<div class="div_transparent"></div>
							<ul class="ul4">
								<li class="first-li"><label><input type="checkbox"
										  value="-1"><span>全部 细节状态</span>
								<li><label><input type="checkbox" name="detailStatus"  value="0"><span>未选择</span></label></li>
								<li><label><input type="checkbox" name="detailStatus"  
										 value="1"><span>正常生产中</span></label></li>		
								<li><label><input type="checkbox" name="detailStatus"
										 value='2'><span>返工重做中</span></label></li>
								<li><label><input type="checkbox" name="detailStatus"
										 value='4'><span>悬而未决中</span></label></li>
	
							</ul>
						</div>
						改变 结束
					</div> 
					<div class="form-group form-group5 mt10">
						<label class="col-xs-3 pl0 pr0 label_state">延期</label> <span></span>
						改变 开始
						<div class="dropdown dropdown5 col-xs-9 pl0 pr0">
							<button id="dLabel" type="button" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false" class="btn btn-default col-xs-12 text-left">
								<em>全部 </em>
								<span class="caret pull-right"></span>																
							</button>
							<div class="div_transparent"></div>
							<ul class="ul5">
								<li class="first-li"><label><input type="checkbox"
										  value="-1"><span>全部</span></label></li>
								<li><label><input type="checkbox"
										 name="delayStatus" value="0"><span>正常</span></label></li>
								<li><label><input type="checkbox" name="delayStatus" 
										 value='1'><span>有延期</span></label></li>
								<li><label><input type="checkbox" name="delayStatus" 
										onchange="searchProjectData(1)" value='2'><span>延期后超期</span></label></li>
							</ul>
						</div>
						改变 结束
					</div> 
					<div class="form-group form-group6 mt10">
						<label class="col-xs-3 pl0 pr0 label_state">项目阶段</label> <span></span>
						改变 开始
						<div class="dropdown dropdown6 col-xs-9 pl0 pr0">
							<button id="dLabel" type="button" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false" class="btn btn-default col-xs-12 text-left">
								<em>全部阶段</em> <span class="caret pull-right"></span>
							</button>
							<div class="div_transparent"></div>
							<ul class="ul6">
								<li class="first-li"><label><input type="checkbox"
										value=""><span>全部阶段</span></label></li>
								<li><label><input type="checkbox"
									    name="projectStage" value="3"><span>未更新</span></label></li>	
								<li><label><input type="checkbox"
										name="projectStage" value='0'><span>样品</span></label></li>
								<li><label><input type="checkbox"
										name="projectStage" value='1'><span>小批量</span></label></li>
								<li><label><input type="checkbox"
										name="projectStage" value='2'><span>大货</span></label></li>
							</ul>
						</div>
						改变 结束
					</div> -->
<!-- 					<div class="form-group form-group6 mt10">
					<label class="col-xs-3 pl0 pr0 label_state">项目等级</label> <span></span>
					改变 开始
					<div class="dropdown dropdown7 col-xs-9 pl0 pr0">
						<button id="dLabel" type="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false" class="btn btn-default col-xs-12 text-left">
							<em>全部等级</em> <span class="caret pull-right"></span>
						</button>
						<div class="div_transparent"></div>
						<ul class="ul7">
							<li class="first-li"><label><input type="checkbox"  value=""><span>全部等级</span></label></li>
							<li><label><input type="checkbox" name="plantAnalysis"  value="0"><span>未定</span></label></li>
							<li><label><input type="checkbox" name="plantAnalysis"  value='1'><span>A</span></label></li>
							<li><label><input type="checkbox" name="plantAnalysis"  value='2'><span>B</span></label></li>
							<li><label><input type="checkbox" name="plantAnalysis"  value='3'><span>C</span></label></li>
						</ul>
					</div>
					改变 结束
				</div> -->
				</form>
				<!-- 新加和PC搜索菜单保持一致结束 -->
				<div class="red mt10 f14" style="display:none;">
				无该项目，请联系项目跟单到ERP上该项目页添加您的姓名，并务必按"保存"键。</div>
			</div>
			<!-- 项目列表 -->
			<div class="index-task" id="projectDataList"></div>				
			<!-- 任务列表显示 -->
			<div class="index-task" id="projectTaskList"></div>
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
	</script>
	<script type="text/javascript">
// 		var queryObj = {
// 			"preNum" : 1,
// 			"isPre" : false,
// 			"nextNum" : 2,
// 			"isNext" : false,
// 			"curNum" : 0,
// 			"operatorType" : 0
// 		};
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
			
// 			var um = $("#userName").val();

// 			if (um == 'system' || um=='ninazhao') {
// 				$('#taskFlow').show()
// 			}
			//projectDataLoad(1);
// 			var indexHeight;
			//判断PC端还是移动端
// 			var sUserAgent = navigator.userAgent.toLowerCase();
// 			var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
// 			var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
// 			var bIsMidp = sUserAgent.match(/midp/i) == "midp";
// 			var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
// 			var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
// 			var bIsAndroid = sUserAgent.match(/android/i) == "android";
// 			var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
// 			var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
// 			if (!(bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc
// 					|| bIsAndroid || bIsCE || bIsWM)) {
// 				indexHeight = 1042;
// 			} else {
// 				indexHeight = 500;
// 			}
			showload();
/* 			$(".index-middle")
					.scroll(
							function() {
								var txHeight = $('#projectDataList').height();
								var projectNum = $("#projectNum").text();
								var hh = $(this).scrollTop();
								if (hh + indexHeight > txHeight
										&& $('#projectDataList').find('dl').length <= projectNum) {
									if (isDoQuery) {
										return false;
									} else {
										isDoQuery = true;
										queryObj.operatorType = 0;
										showload();
									}
									txHeight = $('#projectDataList').height();
								} else {
									return false;
								}
		
							}); */
							
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
		                  showload();
		                }
		            }
		        })												
		});

/* 		jQuery.Huitab = function(tabBar, tabCon, class_name, tabEvent, i) {
			var $tab_menu = $(tabBar);
			// 初始化操作
			$tab_menu.removeClass(class_name);
			$(tabBar).eq(i).addClass(class_name);
			$(tabCon).hide();
			$(tabCon).eq(i).show();

			$tab_menu.bind(tabEvent, function() {
				$tab_menu.removeClass(class_name);
				$(this).addClass(class_name);
				var index = $tab_menu.index(this);
				$(tabCon).hide();
				$(tabCon).eq(index).show();
			})
		}
		$.Huitab(".index-top span", ".index-middle .index-task",
				"index-active", "click", "0");
		$('.footer-list-two').click(function() {
			$('#mask').show(100);
			$('.index-mask').show(300);
		});

		$('#taskFlow').click(function() {
			$('#mask').show(100);
			$('.index-mask-task').show(300);
		});

		$('#mask').click(function() {
			$('#mask').hide(200);
			$('.index-mask').hide(200);
			$('.index-mask-task').hide(200);
		})
		$('.footer-cancel').click(function() {
			$('#mask').hide(200);
			$('.index-mask').hide(200);
			$('.index-mask-task').hide(200);

		}) */
		
					
		/**分页加载数据*/
		function showload() {
			scrollPage++;
			console.log("begin query,pageNumber:" + scrollPage);
			doQuery(scrollPage);				
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
			queryObj.operatorType = 1;
			queryObj.preNum = 1;
			queryObj.isPre = false;
			queryObj.nextNum = 1;
			queryObj.isNext = false;
			queryObj.curNum = 0;
			showload();
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
			showload();
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
		//进入录入任务页面
		$("#addProjectTask")
				.click(
						function() {
							var roleNo = $("#roleNo").val();
							var userId = $("#userId").val();
							var userName = $("#userName").val();
							window.location.href = "${ctx}/jsp/input_project_task.jsp?roleNo="
									+ roleNo
									+ "&userId="
									+ userId
									+ "&userName=" + userName;
						});
		//任务列表页
		$("#projectTaskTechnologyList")
				.click(
						function() {
							var roleNo = $("#roleNo").val();
							var userId = $("#userId").val();
							var userName = $("#userName").val();
							window.location.href = "${ctx}/projectTask/projectTaskList?roleNo="
									+ roleNo
									+ "&userId="
									+ userId
									+ "&userName=" + userName;
						})
		//会议录入界面
		$("#inputMeetingRecord")
				.click(
						function() {
							var roleNo = $("#roleNo").val();
							var userId = $("#userId").val();
							var userName = $("#userName").val();
							window.location.href = "${ctx}/jsp/input_meeting_record.jsp?roleNo="
									+ roleNo
									+ "&userId="
									+ userId
									+ "&userName=" + userName;
						});
		//会议列表页面
		$("#meetingRecordList")
				.click(
						function() {
							var roleNo = $("#roleNo").val();
							var userId = $("#userId").val();
							var userName = $("#userName").val();
							window.location.href = "${ctx}/meetingRecord/selectMeetingRecordList?roleNo="
									+ roleNo
									+ "&userId="
									+ userId
									+ "&userName=" + userName;
						});

		//角色绑定
		$("#roleBind").click(
				function() {
					var roleNo = $("#roleNo").val();
					var userId = $("#userId").val();
					var userName = $("#userName").val();
					window.location.href = "${ctx}/roleBind/queryList?roleNo="
							+ roleNo + "&userId=" + userId + "&userName="
							+ userName;
				});

		//触发动作
		$('#trigger').click(
				function() {
					var roleNo = $("#roleNo").val();
					var userId = $("#userId").val();
					var userName = $("#userName").val();
					window.location.href = "${ctx}/trigger/queryList?roleNo="
							+ roleNo + "&userId=" + userId + "&userName="
							+ userName;
				});

		//任务流
		$('#taskFlowdetail').click(
				function() {
					var roleNo = $("#roleNo").val();
					var userId = $("#userId").val();
					var userName = $("#userName").val();
					window.location.href = "${ctx}/taskflow/queryList?roleNo="
							+ roleNo + "&userId=" + userId + "&userName="
							+ userName;
				})

		function projectDataLoad(pageNumber) {
			var operatorType = queryObj.operatorType;
			isDoQuery = true;
			var pageSize = $("#pageSize").val();
			if ((queryObj.preNum == pageNumber && !queryObj.isPre)
					|| (queryObj.nextNum == pageNumber && !queryObj.isNext)) {
				doQuery(pageNumber,operatorType);
			} else {
				isDoQuery = false;
				return false;
			}

		}

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
			}else if($('#sort_select').val() == '进行中有设置过延期的项目'){
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
							
							projectType:projectType,
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
						beforeSend : function() {
							index = layer.load();
						},
						success : function(data) {
							layer.close(index);
							var json = eval("(" + data + ")");
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
							//var totalCountFinish=json.data.totalCountFinish;//大货完成数量
							var purchaseNameId = json.data.purchaseNameId;//采购姓名查询
							$("#purchaseSelect").empty();//采购下拉列表

							if (operatorType == 1) { // 代表是查询操作要清空
								$("#projectDataList").empty();
								$("#projectTaskList").empty();
								$("#selectProjectType").empty();
							} else {

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
										dataHtml += '<p class="project-status pc_p"><span class="span-left">项目状态：</span><span class="span-right project-state">'
										//报关日期
										if(projectDataList[i].exportDate != null){
											dataHtml += '<p class="pc_p"><span class="span-left">报关日期：</span><span class="span-right project-date">'
												+ (projectDataList[i].exportDate)+ '</span></p>'
										}
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
										dataHtml +='<p class="detail-link">' /* '<a class="left btn btn-primary" href="${ctx}/project/showDetails?projectNo='
												+ projectDataList[i].projectNo
												+ '&roleNo='
												+ roleNo
												+ '&userId='
												+ userId
												+ '&userName='
												+ userName
												+ '">详情</a>' */
										if (projectDataList[i].projectDrawingList.length > 0) {
											// dataHtml += '<span><a class="left btn btn-primary" href="#" style="margin-left:5px;font-size:0.25rem;">图纸</a></span>'
										}
										if (projectDataList[i].inspectionReportList.length > 0) {
											// dataHtml += '<a class="left btn btn-primary" style="width:27%;margin-left:5px;" href="#">QC REPORT</a>'
										}
// 									    if (projectDataList[i].delay!=null && (userName =='Jiangwenlong' ||  userName =='jiangwenlong' || roleNo==5 ) && projectDataList[i].delay.isAgree==0) {
// 											dataHtml +='<span  class="left btn btn-primary" style="width:5%;margin-left:5px;color:#fff;" onclick="applayDelaySub(1,\''+projectDataList[i].projectNo+'\')">同意</span>'
// 												+'<span  class="left btn btn-primary" style="width:5%;margin-left:5px;color:#fff;" onclick="applayDelaySub(2,\''+projectDataList[i].projectNo+'\')">不同意</span>'
// 										        +'<br>'
// 										     if(projectDataList[i].delay.internalCause){
// 										    	 dataHtml +='<div><span class="span-left" style="color:red;">申请延期时间:'+ (new Date(projectDataList[i].delay.delayDate).pattern("yyyy-MM-dd"))+'&nbsp;&nbsp;&nbsp;&nbsp;原因:'
// 											        +projectDataList[i].delay.internalCause+'</span></div>' 
// 										     }
// 											 if(projectDataList[i].delay.externalCause){
// 										    	 dataHtml +='<div><span class="span-left" style="color:red;">申请延期时间:'+ (new Date(projectDataList[i].delay.delayDate).pattern("yyyy-MM-dd"))+'&nbsp;&nbsp;&nbsp;&nbsp;原因:'
// 											        +projectDataList[i].delay.externalCause+'</span></div>' 
// 										     }	
// 									    }
										if (roleNo == 5) {
											dataHtml += '<a class="left btn btn-primary" style="width:20%;margin-left:5px;" href="${ctx}/jsp/status_enter.jsp?projectNo='
													+ projectDataList[i].projectNo
													+ '&roleNo='
													+ roleNo
													+ '&userId='
													+ userId
													+ '&userName='
													+ userName
													+ '">录入状态</a>'
										}
										dataHtml += /* '</p> */'</dd></dl></a>'
									}
									for (var i = 0; i < taskList.length; i++) {//任务列表
										taskHtml += '<a href="${ctx}/task/taskDetails?projectNo='
												+ taskList[i].projectNo
												+ '&id='
												+ taskList[i].id
												+ '&roleNo='
												+ roleNo
												+ '&userId='
												+ userId
												+ '&userName='
												+ userName
												+ '" class="index-link">'
										taskHtml += '<dl class="index-list task-list" style="margin-top: 45px;"><dd><p><span class="span-left">项目号：</span><span class="span-right porject-number">'
												+ taskList[i].projectNo
												+ '</span></p>'
										taskHtml += '<p><span class="span-left">截止日期：</span><span class="span-right project-date">'
												+ (new Date(taskList[i].finishTime)
														.pattern("yyyy-MM-dd"))
												+ '</span></p>'
										if (roleNo == 5 || roleNo == 1020) {
											taskHtml += '<p><span class="span-left">采购：</span><span class="span-right">'
													+ (taskList[i].purchaseName ? taskList[i].purchaseName
															: "")
													+ '</span></p>'
										} else if (roleNo == 6 || roleNo == 69 || roleNo == 1020) {
											taskHtml += '<p><span class="span-left">跟单销售：</span><span class="span-right">'
													+ (taskList[i].sellName?taskList[i].sellName:"")
													+ '</span></p>'
										} else {
											taskHtml += '<p><span class="span-left">跟单销售：</span><span class="span-right">'
													+ (taskList[i].sellName?taskList[i].sellName:"")
													+ '</span></p>'
											taskHtml += '<p><span class="span-left">采购：</span><span class="span-right">'
													+ (taskList[i].purchaseName ? taskList[i].purchaseName: "")
													+ '</span></p>'
										}
										taskHtml += '<p><span class="span-left">任务描述：</span><span class="span-right">'						
										taskHtml += '</span></p>';

										taskHtml += '<input type="hidden" class="task-status" value="'+taskList[i].taskStatus+'"/>'
										taskHtml += '<p><span class="span-left">任务状态：</span><span class="span-right project-person">'
										if (taskList[i].taskStatus == 0) {
											taskHtml += '未完成'+'</span></p>';
										} else if(taskList[i].taskStatus == 1){
											taskHtml += '已完成' + '</span></p>';
										}else if(taskList[i].taskStatus == 2){
											taskHtml += '已暂停' + '</span></p>';
										}
										taskHtml += '<p class="p-row"><span class="span-left">任务需求：</span><span class="span-right project-name">'
												+ taskList[i].description
												+ '</span></p></dd></dl></a>';
									}
									$("#projectDataList").append(dataHtml);//项目列表
									$("#projectTaskList").append(taskHtml);//任务列表	
								/* 	if(projectType==""){//如果查询所有的去掉完成的项目
										$("#projectNum").text(projectNum-totalCountFinish);//项目总数
									}else{ */
										$("#projectNum").text(projectNum);//项目总数
									/* } */
									$("#delayNum").text(delayNum);//延期数量

								} else {
									queryObj.operatorType = 1;
									queryObj.preNum = 1;
									queryObj.isPre = false;
									queryObj.nextNum = 1;
									queryObj.isNext = false;
									queryObj.curNum = 0;
									$("#projectNum").text(0);//项目总数
									$("#delayNum").text(0);//延期数量
									$("#projectType").val(projectType);
									$('.f14').show();
								}

								//$("#selectProjectType").append(typeHtml);

								$("#selectProjectType").find(
										'input[type=radio]').each(function() {

									if ($(this).val() == projectType) {
										$(this).prop('checked', true)
									}
								})

								
							
								$("#totalCount").val(totalCount);

								//分页使用 
								queryObj.isPre = true;
								queryObj.preNum = pageNumber;
								queryObj.isNext = false;
								queryObj.nextNum = pageNumber + 1;
								queryObj.curNum = pageNumber;
								$("#pageNumber").val(queryObj.curNum);

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
								//点击任务列表判断任务完成与未完成标记
								$('#tsak-list')
										.click(
												function() {
													$('.task-list')
															.each(
																	function(i) {
																		var task_status = $(
																				'.task-status')
																				.eq(
																						i)
																				.val();
																		if (task_status == 0) {
																			$(
																					this)
																					.find(
																							'.project-person')
																					.css(
																							"color",
																							"red")
																		}
																	})
												})
						
								//判断是否是任务返回，跳转任务列表
							    var flag = $('#flag').val();
								if(flag){
									$('#tsak-list').click();
								}
												
								
						
		                    } else if(!json.ok && json.message == '请先登录'){
								 window.location = '/index.jsp';
							}else {
								//点击项目状态查询
								$("#projectType").val(projectType);
								queryObj.operatorType = 1;
								queryObj.preNum = 1;
								queryObj.isPre = false;
								queryObj.nextNum = 1;
								queryObj.isNext = false;
								queryObj.curNum = 0;
								layer.msg(json.message, {
									time : 2000
								});
							}
							isDoQuery = false;
						},
						error : function() {
							isDoQuery = false;
						}
					});
		}

		/**分页加载数据*/
/* 		function showload() {
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
						success : function(data) {
							var json = eval("(" + data + ")");
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
			queryObj.operatorType = 1;
			queryObj.preNum = 1;
			queryObj.isPre = false;
			queryObj.nextNum = 1;
			queryObj.isNext = false;
			queryObj.curNum = 0;
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
				success : function(data) {
					var json = eval("(" + data + ")");
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

















