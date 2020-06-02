
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head lang="en">
<meta charset="UTF-8">
<link rel="stylesheet" href="${ctx}/css/test.css">
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/css/easydialog.css" />
<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript" src="../js/easydialog.min.js"></script>
<style type="text/css">
.updown-list {
	display: none;
	width: 432px;
	height: 190px;
	overflow: hidden;
	-webkit-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-moz-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-ms-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-o-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
}

.updown-list>div {
	font-size: 20px;
	width: 432px;
	height: 40px;
	line-height: 40px;
	background-color: #FFF;
	margin-top: 150px;
	text-align: right;
	padding: 3px 12px;
	border-top: 1px solid #ECECEC;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-o-box-sizing: border-box;
	-ms-box-sizing: border-box;
	cursor: pointer;
	position: relative;
	z-index: 30;
}

.box-executive ul {
	width: 100%;
	height: 150px;
	position: absolute;
	top: 34px;
	left: 0;
	z-index: 10;
	padding: 0;
	background-color: #FFF;
	border-right: 1px solid #ECECEC;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-o-box-sizing: border-box;
	-ms-box-sizing: border-box;
}

.box-executive .list>li, .box-executive .list>li ol li {
	/* width: 300px;
		    height: 30px;
    		line-height: 30px; */
	font-size: 13px;
	color: #666;
	padding: 1px 12px;
	cursor: pointer;
	/* position: relative; */
}

.box-executive .list>li:before {
	content: "";
	display: inline-block;
	width: 10px;
	height: 15px;
	background: url(../img/arrow-right.png) center no-repeat;
	background-size: cover;
	position: relative;
	left: 186px;
	top: 3px;
}

.box-executive .list>li:hover, .box-executive .list>li ol li:hover {
	color: #FFF;
	background-color: #027CFF;
}

.list>li.active ol {
	display: block;
}

.list>li:hover ol {
	display: block;
}

.list>li ol {
	display: none;
	width: 216px;
	height: 150px;
	position: absolute;
	top: 0;
	left: 216px;
	z-index: 20;
	padding: 0;
	background-color: #FFF;
	overflow-y: scroll;
	overflow-x: hidden;
}

#easyDialogBox {
	margin-top: -392px;
}
</style>
<title>任务流</title>
</head>
<body class="list-bgcolor">
	<div class="main-container page1">
		<div>
			<h3>任务流</h3>
		</div>
		<div class="main-table meeting-list-table">
			<span> <!--<input type="text" name="searchProjectNo"
				id="searchProjectNo" placeholder="项目号" />  -->
			</span> <input type="hidden" value="${userName}" name="userName"
				id="userName"> <input type="hidden" value="${userId}"
				name="userId" id="userId"> <input type="hidden"
				value="${roleNo}" name="roleNo" id="roleNo">
			<!-- <input
				name="" type="submit" value="查询" class="color-blocks-btn"
				onClick="searchProjectData(1)" /> -->
			<input name="" type="submit" value="返回主页" class="color-blocks-btn"
				onClick="goBack()" id="goBackHtml" /> <input name="" type="submit"
				value="添加任务流" class="color-blocks-btn" onClick="addtaskflow()"
				id="goBackHtml" />

			<div class="table-wrap" style="margin-top: 10px;">
				<div class="table-head">
					<div class="table-head-wrap">
						<table class="grid">
							<colgroup>
								<col>
								<col>
								<col>
								<col>

							</colgroup>
							<thead>
								<tr>
									<th style="width: 15%"><span class="tab-link">项目等级</span></th>
									<th style="width: 15%"><span class="tab-link">项目类型</span></th>
									<th style="width: 20%"><span class="tab-link">触发条件</span></th>
									<th style="width: 30%"><span class="tab-link">触发动作</span></th>
									<th style="width: 20%"><span class="tab-link">日期</span></th>
									<th style="width: 20%"><span class="tab-link">操作</span></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div class="table-content">
					<table class="grid">
						<colgroup>
							<col>
							<col>
							<col>
							<col>
							<col>
							<col>
						</colgroup>
						<tbody>

							<c:forEach items="${taskFlowList}" var="taskflow">
								<tr>
									<td style="width: 15%">
										<c:choose>
											<c:when test="${taskflow.plantAnalysis == 1 }">
												<div>A</div>
											</c:when>
											<c:when test="${taskflow.plantAnalysis == 2 }">
												<div>B</div>
											</c:when>
											<c:when test="${taskflow.plantAnalysis == 3 }">
												<div>C</div>
											</c:when>
											<c:otherwise><div></div></c:otherwise>
								    </c:choose>
									</td>
									<td style="width: 15%">
									  <c:choose>
											<c:when test="${taskflow.projectMaterialProperties == 1 }">
												<div>金属</div>
											</c:when>
											<c:when test="${taskflow.projectMaterialProperties == 2 }">
												<div>塑料</div>
											</c:when>
											<c:when test="${taskflow.projectMaterialProperties == 3 }">
												<div>其他</div>
											</c:when>
											<c:otherwise><div></div></c:otherwise>
								    </c:choose>
									</td>

									<td style="width: 20%">
										<div>${taskflow.conditionName}</div>
									</td>
									<td style="width: 30%">
										<div>${taskflow.taskTitle}(${taskflow.roleName})</div>
									</td>
									<td style="width: 20%">
										<div>
											<fmt:formatDate value="${taskflow.updateTime}"
												pattern="yyyy-MM-dd" />
										</div>
									</td>


									<td style="width: 20%">
										<div>
											<a href="javascript:void(0);" style="padding-left: 10px"
												onclick="getDetail('${taskflow.id}')">编辑</a> <a
												href="javascript:void(0);" style="padding-left: 10px"
												onclick="deleteDetail('${taskflow.id}')">删除</a>
										</div>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
			<div class="page-box">
				总数:${totalCount}每页:10条 当前页/总页:<span style='color: red'>${pageNumber}</span>/<span
					style='color: red'>${countPage}</span>&nbsp; <a href="#"
					class='emanagergetpagea first-padding'
					onclick="searchProjectData(1)" title='第一页' class='a02'>首页</a> <a
					href="#" class='emanagergetpagea first-padding'
					onclick="searchProjectData(2)" title='上一页(第1页)' class='a02'>上页</a>
				<!-- 		    <a href="#" class='emanagergetpagea' title='1' class='a02'>1</a>
		    <a href="#" class='emanagergetpagea' title='2' class='a02'>2</a>
		    <a href="#" class='emanagergetpagea' title='3' class='a02'>3</a>
		    <a href="#" class='emanagergetpagea' title='4' class='a02'>4</a> -->
				<a href="#" class='emanagergetpagea' onclick="searchProjectData(3)"
					title='下一页(第3页)' class='a02'>下页</a> <a href="#"
					class='emanagergetpagea' onclick="searchProjectData(4)"
					title='最后一页' class='a02'>尾页</a>
			</div>
			<input type="hidden" id="pageNumber" name="pageNumber"
				value="${pageNumber}"> <input type="hidden" id="countPage"
				name="countPage" value="${countPage}">
		</div>
	</div>

	<div class="page2 main-container" style="display: none">

		<form id="taskform" class="roleform form-horizontal" role="form"
			onsubmit="return false;">
			<input id="taskflowId" name="taskflowId" type="hidden" value=""></input>
			<input id="conditionName" name="conditionName" type="hidden" value=""></input>
			<!--  	<input id="taskTitle" name="taskTitle" type="hidden" value=""></input>-->

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button onclick="showPage1()" class="btn btn-default"
						style="background-color: #027CFF; color: #fff;">返回</button>
					<button id="submitButton" class="btn btn-default"
						style="background-color: #027CFF; color: #fff;"
						onclick="saveDetail()">保存</button>
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label"><i
					class="ffhh">*</i>触发条件</label>
				<div class="col-sm-3">
					<select id="conditionType" name="conditionType"
						class="form-control" onchange="getConditionName(this)">
						<option value="0">--请选择触发条件--</option>
						<option value="4">新报价项目创建</option>
						<option value="1">项目进入跟单阶段</option>
						<option value="2">项目录入了样品质量会议</option>
						<option value="3">项目录入了启动会议</option>
						<option value="5">项目跟单销售发出质量投诉</option>
						<option value="6">项目原定交期 超期一周后 ，给采购布置一个解释情况的任务</option>
					</select><span></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label"><i
					class="ffhh"></i>项目等级</label>
				<div class="col-sm-3">
					<select id="plantAnalysis" name="plantAnalysis"
						class="form-control">
						<option value="0">--请选择项目优先级--</option>
						<option value="1">A</option>
						<option value="2">B</option>
						<option value="3">C</option>
					</select><span></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label"><i
					class="ffhh"></i>项目类型</label>
				<div class="col-sm-3">
					<select id="projectMaterialProperties"
						name="projectMaterialProperties" class="form-control">
						<option value="0">--请选择项目类型--</option>
						<option value="1">金属</option>
						<option value="2">塑料</option>
						<option value="3">其他</option>
					</select><span></span>
				</div>
			</div>

			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label"> <i
					class="ffhh">*</i>第一个任务
				</label>
				<div class="col-sm-3">
					<select id="triggerId" name="triggerId"
						class="form-control triggerId" onchange="getTriggerName(this)">
					</select><span></span>
				</div>

				<label for="firstname" class="col-sm-2 control-label"> <i
					class="ffhh"></i>第二个任务:
				</label>
				<div class="col-sm-3">
					<select name="triggerId" class="form-control triggerId"
						onchange="getTriggerName(this)">
					</select><span></span>
				</div>

			</div>


			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label"> <i
					class="ffhh"></i>第三个任务:
				</label>
				<div class="col-sm-3">
					<select name="triggerId" class="form-control triggerId"
						onchange="getTriggerName(this)">
					</select><span></span>
				</div>
				<label for="firstname" class="col-sm-2 control-label"> <i
					class="ffhh"></i>第四个任务:
				</label>
				<div class="col-sm-3">
					<select name="triggerId" class="form-control triggerId"
						onchange="getTriggerName(this)">
					</select><span></span>
				</div>
			</div>

			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label"> <i
					class="ffhh"></i>第五个任务:
				</label>
				<div class="col-sm-3">
					<select name="triggerId" class="form-control triggerId"
						onchange="getTriggerName(this)">
					</select><span></span>
				</div>
				<label for="firstname" class="col-sm-2 control-label"> <i
					class="ffhh"></i>第六个任务:
				</label>
				<div class="col-sm-3">
					<select name="triggerId" class="form-control triggerId"
						onchange="getTriggerName(this)">
					</select><span></span>
				</div>
			</div>

			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label"> <i
					class="ffhh"></i>第七个任务:
				</label>
				<div class="col-sm-3">
					<select name="triggerId" class="form-control triggerId"
						onchange="getTriggerName(this)">
					</select><span></span>
				</div>
				<label for="firstname" class="col-sm-2 control-label"> <i
					class="ffhh"></i>第八个任务:
				</label>
				<div class="col-sm-3">
					<select name="triggerId" class="form-control triggerId"
						onchange="getTriggerName(this)">
					</select><span></span>
				</div>
			</div>


			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label"> <i
					class="ffhh"></i>第九个任务:
				</label>
				<div class="col-sm-3">
					<select name="triggerId" class="form-control triggerId"
						onchange="getTriggerName(this)">
					</select><span></span>
				</div>
				<label for="firstname" class="col-sm-2 control-label"> <i
					class="ffhh"></i>第十个任务:
				</label>
				<div class="col-sm-3">
					<select name="triggerId" class="form-control triggerId"
						onchange="getTriggerName(this)">
					</select><span></span>
				</div>
			</div>


		</form>

		<div></div>
	</div>
</body>
<script type="text/javascript" src="../js/jquery-form.js"></script>
<script type="text/javascript">
	$(function() {

		var userName = $("#userName").val();
		if (userName == null || userName == '' || userName == undefined) {
			$("#goBackHtml").hide();
		}
		ajaxList()

	})

	function ajaxList() {

		$
				.ajax({
					url : "${ctx}/taskflow/getTaskList.do",
					type : "post",
					async : false,
					traditional : true,
					datatype : "json",
					success : function(json) {
// 						var json = eval("(" + result + ")");
						if (json.ok) {
							var triggerList = json.data
							if (triggerList && triggerList.length > 0) {
								var addOption = '<option value="0">--请选择触发动作--</option>';

								for (var k = 0; k < triggerList.length; k++) {
									var triggerTask = triggerList[k]
									addOption += '<option value="'+triggerTask.id+'">'
											+ triggerTask.taskTitle + '(' + triggerTask.roleName + ')'
											+ '</option>'

								}
								$('.triggerId').html(addOption)

							}
						}
					}
				})

	}

	function getConditionName(obj) {

		$("#submitButton").css("background-color", "#027CFF").removeAttr(
				'disabled');

		if ($(obj).val() != 0) {
			var tempValue = $(obj).find("option:selected").text();
			$('#conditionName').val(tempValue)
		} else {
			$('#conditionName').val('')

		}

	}

	function getTriggerName(obj) {

		$("#submitButton").css("background-color", "#027CFF").removeAttr(
				'disabled');

		if ($(obj).val() != 0) {
			var tempValue = $(obj).find("option:selected").text();
			$('#taskTitle').val(tempValue)
		} else {
			$('#taskTitle').val('')

		}

	}

	function getDetail(id) {
		if (!id) {
			return false;
		}

		$.ajax({
			url : "${ctx}/taskflow/selectRecordDetails.do",
			type : "post",
			async : false,
			traditional : true,
			data : {
				'taskflowId' : id
			},
			datatype : "json",
			success : function(json) {
// 				var json = eval("(" + result + ")");
				if (json.ok) {
					addtaskflow()
					$('#taskflowId').val(id)
					var taskflow = json.data
					$('#conditionType').val(taskflow.conditionType)
					$('#triggerId').val(taskflow.triggerId)
					$('#conditionName').val(taskflow.conditionName)
					$('#taskTitle').val(taskflow.taskTitle)
					$('#plantAnalysis').val(taskflow.plantAnalysis)
					$('#projectMaterialProperties').val(
							taskflow.projectMaterialProperties)
					projectMaterialProperties

					var tfDetails = taskflow.tfDetails
					if (tfDetails) {
						for (var j = 1; j < tfDetails.length; j++) {
							var taskFlowDetail = tfDetails[j]
							$('.page2 select[name=triggerId]').eq(j).val(
									taskFlowDetail.triggerId)
						}
					}

				} else {

				}

			}

		})

	}

	function deleteDetail(id) {
		if (!id) {
			return false;
		}

		if (window.confirm('你确定要删除吗？')) {

		} else {
			return false;
		}

		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var roleNo = $("#roleNo").val();

		$
				.ajax({
					url : "${ctx}/taskflow/deleteRecordDetails.do",
					type : "post",
					async : false,
					traditional : true,
					data : {
						'taskflowId' : id
					},
					datatype : "json",
					success : function(json) {
// 						var json = eval("(" + result + ")");

						if (json.ok) {

							window.location.href = "${ctx}/taskflow/queryList?userName="
									+ userName
									+ "&roleNo="
									+ roleNo
									+ "&userId=" + userId;

						} else {
							easyDialog.open({
								container : {
									header : 'Prompt message',
									content : json.message
								},
								overlay : false,
								autoClose : 12000
							});
						}

					}

				})
	}

	function saveDetail() {

		$('.page2').find('.ff4').removeClass('ff4').text('')

		$('.page2').find('select[name=conditionType]').each(function() {
			if ($(this).val() == 0) {
				$(this).next().addClass('ff4').text('请选择角色类型')
			}

		})

		$('.page2').find('select[id=triggerId]').each(function() {

			if ($(this).val() == 0) {
				$(this).next().addClass('ff4').text('请选择触发动作')
			}

		})

		if ($('.ff4').length > 0) {
			return false;
		}

		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var roleNo = $("#roleNo").val();

		$("#submitButton").attr("disabled", true).css("background-color",
				"#999");

		$("#taskform")
				.ajaxSubmit(
						{
							type : "post",
							url : "${ctx}/taskflow/addRecord",
							dataType : "text",
							success : function(json) {
// 								var json = eval("(" + result + ")");
								if (json.ok) {

									window.location.href = "${ctx}/taskflow/queryList?userName="
											+ userName
											+ "&roleNo="
											+ roleNo
											+ "&userId=" + userId;
								} else {

									easyDialog.open({
										container : {
											header : 'Prompt message',
											content : json.message
										},
										overlay : false,
										autoClose : 3000
									});

								}

							}
						})

	}

	function addtaskflow() {

		$('.page1').hide();
		$('.page2').show();
		$('#taskform')[0].reset()

		$('.page2').find('.ff4').removeClass('ff4').text('')

		$("#submitButton").css("background-color", "#027CFF").removeAttr(
				'disabled');

		$('#taskflowId').val('')

	}

	function showPage1() {
		$('.page1').show()
		$('.page2').hide()
	}

	function deleteRole(obj) {

		$(obj).parents('.form-group').remove()

	}
	/*
	 * 
	function addRole() {

		$('#taskform').append($('#addRole .form-group').clone())
		addClick()

	}
	
	 function addMeetingRecord() {
	 var roleNo = $("#roleNo").val();
	 var userId = $("#userId").val();
	 var userName = $("#userName").val();
	 window.location.href = "${ctx}/meetingRecord/inputMeetingRecord?roleNo="
	 + roleNo + "&userId=" + userId + "&userName=" + userName;
	 };
	
	 */

	function goBack() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var purchaseNameId = "";
		window.location.href = "${ctx}/jsp/project_list.jsp?userId=" + userId
				+ "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId
				+ "&userName=" + encodeURI(encodeURI(userName));
	}
	//筛选列表
	function searchProjectData(obj) {
		var projectNo = $("#searchProjectNo").val();
		var pageNumber = $("#pageNumber").val();
		var countPage = $("#countPage").val();
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
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
		window.location.href = "${ctx}/taskflow/queryList?" + "pageNumber="
				+ pageNumber + "&userName=" + userName + "&userId=" + userId
				+ "&roleNo=" + roleNo;
	}

	$(".box-executive input,.box-executive em").click(
			function(event) {
				$(this).parents(".project-task-list").find(".updown-list")
						.slideUp(30).end().end().siblings(".updown-list")
						.slideDown();
				$(this).parent(".box-executive").find("em").addClass(
						"arrow-icon");
				return false;
			});

	$(".list ol li").click(function(event) {
		var game1 = $(this).text()
		console.log($(this).parents(".updown-list").siblings("input").length);
		$(this).parents(".updown-list").siblings("input").val(game1);
		foc();
	});

	function addClick() {
		$(".box-executive input,.box-executive em").click(
				function(event) {
					$(this).parents(".project-task-list").find(".updown-list")
							.slideUp(30).end().end().siblings(".updown-list")
							.slideDown();
					$(this).parent(".box-executive").find("em").addClass(
							"arrow-icon");
					return false;
				});

		$(".list ol li").click(
				function(event) {
					var game1 = $(this).text()
					console.log($(this).parents(".updown-list").siblings(
							"input").length);
					$(this).parents(".updown-list").siblings("input")
							.val(game1);
					foc();
				});

	}

	function foc() {
		$(".close-ck").parents(".updown-list").slideUp();
		$(".box-executive em").removeClass("arrow-icon");
	}

	/*
	function showWorker(obj) {
		var value = $(obj).val()
		if (value && value != 0) {
			$(obj).parent().next().show()
		} else {
			$(obj).parent().next().hide()
		}
		$(obj).parent().next().find('input[name=staffName]').val('')

	}
	 */
</script>
</html>