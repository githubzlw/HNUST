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
.page2{
	padding-bottom:30px;
}

</style>
<title>角色绑定</title>
</head>
<body class="list-bgcolor">
	<div class="main-container page1" style="display: none">
		<div>
			<h3>角色绑定列表</h3>
		</div>
		<div class="main-table meeting-list-table">
			<span> <!--<input type="text" name="searchProjectNo"
				id="searchProjectNo" placeholder="项目号" />  -->
			</span> <input type="hidden" value="${userName}" name="userName"
				id="userName"> <input type="hidden" value="${userId}"
				name="userId" id="userId"> <input type="hidden"
				value="${roleNo}" name="roleNo" id="roleNo">
			<!--<input
				name="" type="submit" value="查询" class="color-blocks-btn"
				onClick="searchProjectData(1)" />  -->
			<input name="" type="submit" value="返回主页" class="color-blocks-btn"
				onClick="goBack()" id="goBackHtml" /> <input name="" type="submit"
				value="录入角色绑定" class="color-blocks-btn" onClick="addRoleBind()"
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
									<!--<th style="width: 25%"><span class="tab-link">项目号</span></th>  -->
									<th style="width: 25%"><span class="tab-link">角色</span></th>

									<th style="width: 25%"><span class="tab-link">职员</span></th>
									<th style="width: 25%"><span class="tab-link">操作</span></th>
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

						</colgroup>
						<tbody>

							<c:forEach items="${roleList}" var="roleBindList">
								<tr>
									<!--  <td style="width: 25%">
										<div>${roleBindList.projectNo}</div>
									</td>-->


									<td style="width: 25%">
										<div>${roleBindList.createName}</div>
									</td>

									<td style="width: 25%">
										<div>
											<fmt:formatDate value="${roleBindList.updateTime}"
												pattern="yyyy-MM-dd" />
										</div>
									</td>


									<td style="width: 25%">
										<div>
											<a href="javascript:void(0);" style="padding-left: 10px"
												onclick="getDetail('${roleBindList.id}')">编辑</a> <a
												href="javascript:void(0);" style="padding-left: 10px"
												onclick="deleteDetail('${roleBindList.id}')">删除</a>
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

	<div class="page2 main-container">
		<div>
			<h3>角色绑定列表</h3>
		</div>

		<form id="roleform" class="roleform form-horizontal" role="form"
			onsubmit="return false;">
			<!--<input id="roleBindId" name="roleBindId" type="hidden" value=""></input>  -->
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button class="btn btn-default"
						style="background-color: #027CFF; color: #fff;" onClick="goBack()"
						id="goBackHtml">返回首页</button>

				</div>
			</div>


			<!-- <div class="form-group">
				<label for="firstname" class="col-sm-2 control-label"><i
					class="ffhh">*</i>项目号</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="projectNo"
						name="projectNo" placeholder="请输入项目号"><span></span>
				</div>
			</div> -->


			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label"><i
					class="ffhh">*</i>角色绑定</label>
				<div class="col-sm-3">
					<select name="roleType" class="form-control"
						onchange="showWorker(this)">
						<option value="0">--请选择角色类别--</option>
						<option value="1">绘图</option>
						<option value="2">技术分析(金属类)</option>
						<option value="3">包装分析</option>
						<option value="8">销售部助理</option>
						<option value="9">总裁助理</option>
						<option value="10">采购部助理</option>
						<option value="11">报价员</option>
						<option value="12">技术分析(塑料类)</option>
						<option value="13">质检</option>
					</select><span></span>
				</div>

				<div class="col-sm-3 box-executive" style="display: none">
					<input type="text" class="form-control" name="staffName"
						placeholder="请选择公司职员"><span></span>

					<div class="updown-list">
						<ul class="list">
							<li class="active"><span>技术部</span>
								<ol>
									<li>LiuZhongYi</li>
									<li>zhangqun</li>
									<li>OwenCui</li>
									<li>wangweiping</li>
									<li>DeanZhang</li>
								</ol></li>
							<li><span>销售部</span>
								<ol>
									<li>jerrylong</li>		
									<li>amyzhao</li>
									<li>tina</li>
									<li>paul</li>
									<li>FionaYan</li>
									<li>Susiehuang</li>
									<li>minniewu</li>
									<li>SherryZhou</li>
									<li>LynnYuan</li>
									<li>elainesheng</li>
									<li>ivywu</li>
									<li>kathypan</li>
									<li>chloema</li>
									<li>annazhu</li>
									<li>KateGong</li>
									<li>ShirleyYu</li>
									<li>NataliaLi</li>
									<li>jennyguo</li>
									<li>Kristinemei</li>
									<li>pandage</li>
									<li>MinnXu</li>
									<li>AndsXue</li>
									<li>Kevin</li>
									<li>Janezhou</li>
									<li>crystalyao</li>
								</ol></li>
							<li><span>采购部</span>
								<ol>
									<li>Jiangwenlong</li>
									<li>Bensonzhang</li>
									<li>ninazhao</li>
									<li>zhaoshuhao</li>
									<li>zhaoqiang</li>
									<li>YanRongchao</li>
									<li>xuping</li>
									<li>ThomasChen</li>
									<li>xuwei</li>
									<li>RogerQiu</li>
									<li>SISI</li>
									<li>chengmingkun</li>
								</ol></li>
							<li><span>质检部</span>
								<ol>
									<li>yanggong</li>
									<li>wangjingjun</li>
									<li>maxiaolei</li>
									<li>litie</li>
									<li>zoumin</li>
									<li>wangzhuo</li>
									<li>zhangyouqing</li>
									<li>liuzikai</li>
									<li>zuoliang</li>
									<li>zhaochun</li>
									<li>wuyijun</li>
								</ol></li>
						</ul>
						<div>
							<span class="close-ck" onclick="foc()">X</span>
						</div>
					</div>


				</div>
				<div style="font-size: 21px; color: blue; cursor: pointer;">
					<span style="margin-left: 30px;" onclick="addRole()"> + 新增</span>
				</div>


			</div>

		

		</form>
		
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button id="submitButton" class="btn btn-default"
						style="background-color: #027CFF; color: #fff;"
						onclick="saveDetail()">保存</button>
				</div>
			</div>

		<div></div>
	</div>





	<div id="addRole" style="display: none">
		<div class="form-group addTempRole">
			<label for="firstname" class="col-sm-2 control-label"></label>
			<div class="col-sm-3">
				<select name="roleType" class="form-control"
					onchange="showWorker(this)">
					<option value="0">--请选择角色类别--</option>
					<option value="1">绘图</option>
					<option value="2">技术分析(金属类)</option>
					<option value="3">包装分析</option>
					<option value="8">销售部助理</option>
					<option value="9">总裁助理</option>
					<option value="10">采购部助理</option>
					<option value="11">报价员</option>
					<option value="12">技术分析(塑料类)</option>
					<option value="13">质检</option>
				</select><span></span>
			</div>

			<div class="col-sm-3 box-executive" style="display: none">
				<input type="text" class="form-control" name="staffName"
					placeholder="请选择公司职员"><span></span>
				<div class="updown-list">
					<ul class="list">
						<li class="active"><span>技术部</span>
							<ol>
								<li>LiuZhongYi</li>
								<li>zhangqun</li>
								<li>OwenCui</li>
								<li>wangweiping</li>
								<li>DeanZhang</li>
							</ol></li>
						<li><span>销售部</span>
							<ol>
								<li>jerrylong</li>
								<li>amyzhao</li>
								<li>tina</li>
								<li>paul</li>
								<li>FionaYan</li>
								<li>Susiehuang</li>
								<li>minniewu</li>
								<li>SherryZhou</li>
								<li>LynnYuan</li>
								<li>elainesheng</li>
								<li>ivywu</li>
								<li>kathypan</li>
								<li>chloema</li>
								<li>annazhu</li>
								<li>KateGong</li>
								<li>ShirleyYu</li>
								<li>NataliaLi</li>
								<li>jennyguo</li>
								<li>Kristinemei</li>
								<li>pandage</li>
								<li>chengmingkun</li>
								<li>MinnXu</li>
								<li>AndsXue</li>
								<li>Kevin</li>
								<li>Janezhou</li>
								<li>crystalyao</li>
							</ol></li>
						<li><span>采购部</span>
							<ol>
								<li>Jiangwenlong</li>
								<li>Bensonzhang</li>
								<li>ninazhao</li>
								<li>zhaoshuhao</li>
								<li>zhaoqiang</li>
								<li>xuping</li>
								<li>ThomasChen</li>
								<li>xuwei</li>
								<li>RogerQiu</li>
								<li>SISI</li>
								<li>chengmingkun</li>
							</ol></li>
						<li><span>质检部</span>
							<ol>
								<li>yanggong</li>
								<li>wangjingjun</li>
								<li>maxiaolei</li>
								<li>litie</li>
								<li>zoumin</li>
								<li>wangzhuo</li>
								<li>zhangyouqing</li>
								<li>liuzikai</li>
								<li>zuoliang</li>
								<li>zhaochun</li>
								<li>wuyijun</li>
							</ol></li>
					</ul>
					<div>
						<span class="close-ck" onclick="foc()">X</span>
					</div>
				</div>


			</div>

			<div style="font-size: 21px; color: blue; cursor: pointer;">
				<span style="margin-left: 30px;" onclick="deleteRole(this)">
					- 删除</span>
			</div>



		</div>


	</div>







</body>
<script type="text/javascript" src="../js/jquery-form.js"></script>
<script type="text/javascript">
	$(function() {

		var userName = $("#userName").val();
		if (userName == null || userName == '' || userName == undefined) {
			$("#goBackHtml").hide();
		}
		getDetail()

	})

	function getDetail() {

		$.ajax({
			url : "${ctx}/roleBind/selectRecordDetails.do",
			type : "post",
			async : false,
			traditional : true,
			datatype : "json",
			success : function(json) {
// 				var json = eval("(" + result + ")");
				if (json.ok) {
					//addRoleBind()
					//$('#roleBindId').val(id)
					var roleBindDetails = json.data

					//$('#projectNo').val(roleBindList.projectNo).attr(
					//		'readonly', true)

					$('#roleform').find('.updown-list').hide()

					if (roleBindDetails && roleBindDetails.length > 0) {
						for (var j = 0; j < roleBindDetails.length; j++) {
							var roleBindDetail = roleBindDetails[j]

							if (j != 0) {
								addRole()
							}

							var temp = $('#roleform').find(
									'select[name=roleType]').eq(j)
							temp.val(roleBindDetail.roleType)
							showWorker(temp)
							temp.parent().next().find('input[name=staffName]')
									.val(roleBindDetail.userName)

						}
					}

				} else {

				}

			}

		})

	}
	/*
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
					url : "${ctx}/roleBind/deleteRecordDetails.do",
					type : "post",
					async : false,
					traditional : true,
					data : {
						'roleBindId' : id
					},
					datatype : "json",
					success : function(result) {
						var json = eval("(" + result + ")");

						if (json.ok) {

							window.location.href = "${ctx}/roleBind/queryList?userName="
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
	 */

	function saveDetail() {

		$('.page2').find('.ff4').removeClass('ff4').text('')

		/*if (!$('#projectNo').val()) {
			$('#projectNo').next().addClass('ff4').text('请输入项目号')
		}*/

		$('.page2').find('select[name=roleType]').each(function() {
			if ($(this).val() == 0) {
				$(this).next().addClass('ff4').text('请选择角色类型')
			}

		})

		$('.page2').find('input[name=staffName]').each(function() {
			if ($(this).is(':visible')) {
				if (!$(this).val()) {
					$(this).next().addClass('ff4').text('请选择公司职员')
				}

			}

		})

		if ($('.ff4').length > 0) {
			return false;
		}

		var map =""
		
		$('.page2').find('select[name=roleType]').each(
				function() {

					var value = $(this).val()
					var name = $(this).parent().next().find(
							'input[name=staffName]').val()

					map += '&'+value + ',' + name + ';'

				})

		$('.page2').find('select[name=roleType]').each(
				function() {

					var value = $(this).val()
					var name = $(this).parent().next().find(
							'input[name=staffName]').val()
					var temp = '&'+value + ',' + name

					if (map.indexOf(temp) != map.lastIndexOf(temp)) {
						$(this).next().addClass('ff4').text('数据重复，请删除一条')
					}

				})

		if ($('.ff4').length > 0) {
			return false;
		}

		$("#submitButton").attr("disabled", true).css("background-color",
				"#999");

		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var roleNo = $("#roleNo").val();
		
		

		$("#roleform")
				.ajaxSubmit(
						{
							type : "post",
							url : "${ctx}/roleBind/addRecord",
							dataType : "text",
							data : {
								userName : userName,
								userId : userId
							},
							success : function(json) {
// 								var json = eval("(" + result + ")");
								if (json.ok) {
									
									easyDialog.open({
										container : {
											header : 'Prompt message',
											content : json.message
										},
										overlay : false,
										autoClose : 3000
									});
								
									$("#submitButton").css({'background-color':'#006dcc'}).removeAttr("disabled");
                                
								//	window.location.href = "${ctx}/roleBind/queryList?userName="
								//			+ userName
								//			+ "&roleNo="
								//			+ roleNo
								//			+ "&userId=" + userId;
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

	/*
	 function addRoleBind() {

	 $('.page1').hide();
	 $('.page2').show();
	 $('.roleform')[0].reset()
	 $('.page2 .addTempRole').remove()
	 $('.page2').find('input[name=staffName]').parent().hide()
	 $('.page2').find('.ff4').removeClass('ff4').text('')
	 $('#roleBindId').val('')
	 $('#projectNo').removeAttr('readonly')

	 $("#submitButton").css("background-color", "#027CFF").removeAttr(
	 'disabled');

	 }

	 function showPage1() {
	 $('.page1').show()
	 $('.page2').hide()
	 }
	 */

	function deleteRole(obj) {

		$(obj).parents('.form-group').remove()

	}

	function addRole() {
		$('#roleform').append($('#addRole .form-group').clone())
		addClick()

	}
	/*
	 * 
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
	/*
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
		window.location.href = "${ctx}/roleBind/queryList?" + "pageNumber="
				+ pageNumber + "&userName=" + userName + "&userId=" + userId
				+ "&roleNo=" + roleNo;
	}
	 */

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

	function showWorker(obj) {
		var value = $(obj).val()
		if (value && value != 0) {
			$(obj).parent().next().show()
		} else {
			$(obj).parent().next().hide()
		}
		$(obj).parent().next().find('input[name=staffName]').val('')

	}
</script>
</html>