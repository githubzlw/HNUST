<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
    String projectNo = request.getParameter("projectNo");
	String roleNo = request.getParameter("roleNo");
	String userId = request.getParameter("userId");
	String userName = request.getParameter("userName");
	String purchaseNameId = request.getParameter("purchaseNameId");
%>
<html style="height:auto;">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>任务列表-手机</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css" />
<link rel="stylesheet" href="../css/add.css">
<style>
.span-right {
	display: initial;
	float: none;
}
html {
    width: 100%;
    height:auto;
}

</style>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
</head>
<body style="height:auto;">
	<div class="project_list_task">

		<!--遮罩层-->
		<div id="mask"></div>
		<div class="index-main home">
			<div class="index-top">
				<div>
					<!-- <span class="index-active">项目列表</span> -->
					<span id="tsak-list">手机任务列表</span>
					<!-- <span onclick="pcUrl()"
					style="background: #7FFFD4">电脑浏览</span> -->
					<a class="bgcolor_ff0  btn pull-right" href="/user/toIndex" >返回功能选择页</a>
			</div>

		</div>
		<input type="hidden" id="roleNo" value="<%=roleNo%>"> 
		<input type="hidden" id="projectNo" value="<%=projectNo%>"> 
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
			
			</div>
			<input type="hidden" id="roleNo" value="<%=roleNo%>"> <input
				type="hidden" id="userId" value="<%=userId%>"> <input
				type="hidden" id="userName" value="<%=userName%>"> <input
				type="hidden" id="purchaseNameId" value="<%=purchaseNameId%>">
			<input type="hidden" id="flag"
				value="<%=request.getParameter("flag") == null ? "" : request
					.getParameter("flag")%>">
			<input type="hidden" id="pageSize" value="8"> <input
				type="hidden" id="pageNumber" value="1"> <input
				type="hidden" id="totalCount" value="10"> <input
				type="hidden" id="operatorType" value="0">
			<div class="index-middle">
				<div class="div-bg">
					<!-- 新加和PC搜索菜单保持一致 开始-->
					<form id="form" class="roleform form-horizontal"
						onsubmit="return false;">
						<div class="form-group form-group1">
							<button id="cleanSelect"
								class="left btn btn-primary bgcolor_ff0 form-control"
								onclick="cleanSel()">清空所有关键词和条件</button>
						</div>
						<div class="search clearfix mt10">
							<label for="input-key" class="pull-left control-label">关键字搜索：</label>
							<input type="text" class="pull-left input-text form-control"
								id="inputKey" placeholder="项目号" />
							<button class="pull-right btn btn-primary bgcolor_ff0"
								id="searchBut" style="margin-bottom: 4px;">搜索</button>
							<br>
						</div>  
					</form>
				</div>
				<!-- 项目列表 -->
				<!-- <div class="index-task" id="projectDataList"></div>			 -->
				<!-- 任务列表显示 -->
				<div class="index-task" id="projectTaskList"></div>
			</div>
		</div>
	</div>	
		<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
			charset="utf-8"></script>
		<script src="../layer/2.1/layer.js" type="text/javascript"
			charset="utf-8"></script>
		<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
		<script type="text/javascript">
			Date.prototype.pattern = function(fmt) {
				var o = {
					"M+" : this.getMonth() + 1, //月份         
					"d+" : this.getDate(), //日         
					"h+" : this.getHours() % 12 == 0 ? 12
							: this.getHours() % 12, //小时         
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
								(RegExp.$1.length == 1) ? (o[k])
										: (("00" + o[k])
												.substr(("" + o[k]).length)));
					}
				}
				return fmt;
			}
			
			
			
			
			var dd;
			$(function(){
				if(dd){
					dd.getAuthCode({
					    success:(res)=>{
					        dd.alert({content: res.authCode})
					          $.ajax({
									     type:"POST",
									     url:"/user/dingtalkLogin",
									     data:{code:res.authCode},
									     dataType:"json",
									     async:false,      //同步
									     success:function(result){
									    	
									     }
						          })
					    },
					    fail: (err)=>{
					        dd.alert({content: JSON.stringify(err)})
					    }
					})
				}				
			})
			
			
		</script>
		<script type="text/javascript">
		
			var isDoQuery = true;
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

				if (roleNo == 6 || roleNo == 69 || roleNo == 1020) {
					$('#purchase_name').hide()
				}
				if (roleNo == 5 || roleNo == 1020) {
					$('#documentary_name').hide()
				}
				if (roleNo == 9 || roleNo == 69 || roleNo == 1020) {
					$('#quality_name').hide()
				}

				ajaxSelectOption();

				showload();
/* 				$(".index-middle")
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
				
				//查询初始化
				scrollPage = 0;
				windowHeight = 0;
				
				showload();
			})
			//根据选择的采购查询列表
			function selectOnchange() {

				var totalCount = Number($("#totalCount").val());
				if (totalCount == 0) {
					$("#totalCount").val(1)
				}
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


			function doQuery(pageNumber) {

				var inputKey = $("#inputKey").val();//关键字查询
				var projectStage = $('#projectStage').val()
				var purchase_name = $('#purchase_name').val()
				var documentary_name = $('#documentary_name').val()
				var quality_name = $('#quality_name').val()
				var projectNo = $('#projectNo').val()
				if(projectNo == null || projectNo == '' || projectNo == undefined||projectNo == "null"){
					projectNo="";
				}
         		//不允许查询
				isDoQuery = false;
				
				$.ajax({
							type : 'post',
							url : '${ctx}/projectTask/taskList',
							dateType:'json',
							sysn:true,
							data : {
								pageNumber : pageNumber,
								inputKey : inputKey,							
								purchase_name : purchase_name,
								documentary_name : documentary_name,
								quality_name : quality_name,
								projectNo:projectNo
							},
							success : function(json) {
// 								var json = eval("(" + data + ")");

			 					if (scrollPage == 1) { // 代表是查询操作要清空
									$("#projectTaskList").empty();
									$("#selectProjectType").empty();
								}  
								if (json.ok) {
     								var roleNo = json.data.roleNo;//角色代码
									var taskList = json.data.taskList;  //任务列表
									var userId = json.data.userId;//用户Id
									var userName = json.data.userName;//用户名
									var pageSize = json.data.pageSize;
									var pageNumber = json.data.pageNumber;
									var totalCount = json.data.totalCount;
									maxPage = parseInt(totalCount/10);
									$("#totalCount").val(totalCount);
									var dataHtml = '';
									var taskHtml = '';

									if(taskList && taskList.length >0){

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
												taskHtml += '<dl class="index-list task-list"><dd><p><span class="span-left">项目号：</span><span class="span-right porject-number">'
														+ taskList[i].projectNo
														+ '</span></p>'
												taskHtml += '<p><span class="span-left">截止日期：</span><span class="span-right project-date">'
														+ (new Date(
																taskList[i].finishTime)
																.pattern("yyyy-MM-dd"))
														+ '</span></p>'
												if (roleNo == 5) {
													taskHtml += '<p><span class="span-left">采购：</span><span class="span-right">'
															+ (taskList[i].purchaseName ? taskList[i].purchaseName
																	: "")
															+ '</span></p>'
												} else if (roleNo == 6 || roleNo == 69 || roleNo == 1020) {
													taskHtml += '<p><span class="span-left">跟单销售：</span><span class="span-right">'
															+ (taskList[i].sellName ? taskList[i].sellName
																	: "")
															+ '</span></p>'
												} else {
													taskHtml += '<p><span class="span-left">跟单销售：</span><span class="span-right">'
															+ (taskList[i].sellName ? taskList[i].sellName : "")
															+ '</span></p>';
													taskHtml += '<p><span class="span-left">采购：</span><span class="span-right">'
															+ (taskList[i].purchaseName ? taskList[i].purchaseName
																	: "")
															+ '</span></p>';
												}
												taskHtml += '<p><span class="span-left">任务接收人：</span><span class="span-right">'+taskList[i].accepter+'</span></p>';
												/* taskHtml += '<p><span class="span-left">任务描述：</span><span class="span-right">'
												taskHtml += '</span></p>'; */
	
												taskHtml += '<input type="hidden" class="task-status" value="'+taskList[i].taskStatus+'"/>'
												taskHtml += '<p><span class="span-left">任务状态：</span><span class="span-right project-person">'
											
												if (taskList[i].taskStatus == 0) {
													taskHtml += '未完成'
															+ '</span></p>';
												} else if (taskList[i].taskStatus == 1) {
													taskHtml += '已完成'
															+ '</span></p>';
												} else if (taskList[i].taskStatus == 2) {
													taskHtml += '已暂停'
															+ '</span></p>';
												}
												taskHtml += '<p class="p-row"><span class="span-left">任务需求：</span><span class="span-right project-name">'
														+ taskList[i].description
														+ '</span></p></dd></dl></a>';
											}
			
											$("#projectTaskList").append(taskHtml);//任务列表	
									}				

									if (roleNo == 6 || roleNo == 69 || roleNo == 1020) {//显示采购下拉框(销售,管理员显示)
										$("#purchaseSelect").css("display",
												"none");
									} else {
										$("#purchaseSelect").css("display",
												"block");
									}
									//下拉框重新赋值
									if (!(purchaseNameId == null
											|| purchaseNameId == "" || purchaseNameId == "0")) {
										$("#purchase_id").val(purchaseNameId);
									}
									
									//获取当前页面高度
									 scrollHeight = $('.index-task').height();
									 isDoQuery = true;

								}else if(!json.ok && json.message == '请先登录'){
									 window.location = '/index.jsp';
								}
							},
							error : function() {
								isDoQuery = false;
							}
						});
			}



			function ajaxSelectOption() {

				$
						.ajax({
							type : "post",
							url : "${ctx}/project/queryStaff.do ",
							success : function(json) {
// 								var json = eval("(" + data + ")");
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

									$('#purchase_name').html(purchaseHtml);
									$('#documentary_name').html(saleHtml);
									$('#quality_name').html(qualityHtml);

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
			}

			function pcUrl() {
				var roleNo = $('#roleNo').val()
				var um = $("#userName").val();
				var ui = $("#userId").val();
				window.location.href = "${ctx}/project/showListNew?userName="
						+ um + "&userId=" + ui + "&roleNo=" + roleNo
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
// 						var json = eval("(" + data + ")");
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
							};

						};
					});

			//细节状态
			$('.dropdown4 input')
					.click(
							function() {
								if ($(this).parent().parent().hasClass(
										'first-li')) {
									$('.dropdown4 button em').html('全部细节状态');
									//$('.dropdown5 input').prop('checked',true);		
									if ($(this).is(':checked')) {
										$('.dropdown4 input').prop('checked',
												true);
									} else {
										$('.dropdown4 input').prop('checked',
												false);
									}
									;
								} else {
									// 多选反选
									var input_len = $('.dropdown4 li').not(
											'.first-li').find('input:checked').length;
									if (input_len > 3) {
										$(this).parent().parent().siblings(
												'.first-li').find('input')
												.prop('checked', true);
										$('.dropdown4 button em')
												.html('全部细节状态');
									} else {
										$(this).parent().parent().siblings(
												'.first-li').find('input')
												.prop('checked', false);
										// 获取赋值
										var s = '';
										$('.dropdown4 input[name="detailStatus"]')
												.each(
														function() {
															if ($(this).is(
																	':checked')) {
																s += $(this)
																		.siblings(
																				'span')
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
			$('.dropdown6 input')
					.click(
							function() {
								if ($(this).parent().parent().hasClass(
										'first-li')) {
									$('.dropdown6 button em').html('全部阶段');
									// $('.dropdown2 input').prop('checked',true);	
									if ($(this).is(':checked')) {
										$('.dropdown6 input').prop('checked',
												true);
									} else {
										$('.dropdown6 input').prop('checked',
												false);
									}
									;
								} else {
									// 多选反选
									var input_len = $('.dropdown6 li').not(
											'.first-li').find('input:checked').length;
									if (input_len > 3) {
										$(this).parent().parent().siblings(
												'.first-li').find('input')
												.prop('checked', true);
										$('.dropdown6 button em').html('全部阶段');
									} else {
										$(this).parent().parent().siblings(
												'.first-li').find('input')
												.prop('checked', false);
										// 获取赋值
										var s = '';
										$(
												'.dropdown6 input[name="projectStage"]')
												.each(
														function() {
															if ($(this).is(
																	':checked')) {
																s += $(this)
																		.siblings(
																				'span')
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
			$('.dropdown7 input')
					.click(
							function() {
								if ($(this).parent().parent().hasClass(
										'first-li')) {
									$('.dropdown7 button em').html('全部等级');
									// $('.dropdown3 input').prop('checked',true);		
									if ($(this).is(':checked')) {
										$('.dropdown7 input').prop('checked',
												true);
									} else {
										$('.dropdown7 input').prop('checked',
												false);
									}
									;
								} else {
									// 多选反选
									var input_len = $('.dropdown7 li').not(
											'.first-li').find('input:checked').length;
									if (input_len > 3) {
										$(this).parent().parent().siblings(
												'.first-li').find('input')
												.prop('checked', true);
										$('.dropdown7 button em').html('全部等级');
									} else {
										$(this).parent().parent().siblings(
												'.first-li').find('input')
												.prop('checked', false);
										// 获取赋值
										var s = '';
										$(
												'.dropdown7 input[name="plantAnalysis"]')
												.each(
														function() {
															if ($(this).is(
																	':checked')) {
																s += $(this)
																		.siblings(
																				'span')
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

















