<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head lang="en">
<meta charset="UTF-8">
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="${ctx}/css/test.css">
<link rel="stylesheet" href="${ctx}/css/add.css">
<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<title>项目修改</title>
<style>
.addInput {
	position: relative;
}

.updown-list {
	display: none;
	width: 432px;
	height: 215px;
	overflow: hidden;
	-webkit-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-moz-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-ms-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-o-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	position: absolute;
}

.updown-list>div {
	font-size: 20px;
	width: 432px;
	height: 40px;
	line-height: 40px;
	background-color: #FFF;
	margin-top: 165px;
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
	height: 175px;
	position: absolute;
	top: 0;
	left: 0;
	z-index: 999;
	padding: 0;
	background-color: #FFF;
	border-right: 1px solid #ECECEC;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-o-box-sizing: border-box;
	-ms-box-sizing: border-box;
	margin-top: 0;
}

.box-executive .list>li, .box-executive .list>li ol li {
	font-size: 13px;
	color: #666;
	padding: 1px 12px;
	cursor: pointer;
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

.mt {
	margin-top: -5px;
}

.mt_c {
	margin-top: 0;
}

.exit_enter {
	position: absolute;
	top: 18px;
	right: 15px;
}

.exit_enter button {
	background-color: #027CFF;
	color: #FFF;
	font-size: 18px;
	padding: 1px 16px;
	border: 0 none;
	border-radius: 2px;
}

.main-table {
	position: relative;
	padding: 24px 15px;
}

.meeting-list-table .color-blocks-btn {
	margin-top: 10px;
}

.meeting-list-table .mt_5.color-blocks-btn {
	margin-top: -5px;
}

.tab-link {
	font-size: 20px;
	line-height: unset;
}

.grid tr td {
	font-size: 17px;
	padding: 5px;
}

.meeting-list-table input {
	font-size: 16px;
}

.inputs_two {
	border: 1px solid transparent;
}

.select_blank {
	background-color: #027CFF;
	padding: 7px 12px;
	text-decoration: none;
	color: #fff;
	border-radius: 2px;
	font-size: 15px;
}

.select_blank:hover, .select_blank:hover {
	text-decoration: none;
	background-color: #4362C5;
	color: #fff;
}
.new_project_entry .s1{width:130px;}
.bottom>div{margin-top:20px}
.bottom button{border-radius:4px;margin-right:10px}
.add_div{margin:0 15px;padding:0 0 30px 0}
table .add_span_state{padding:2px 12px;}
.table-bordered>tbody>tr>td{text-align:center;}
.new_project_entry .btns{top:-10px;}
</style>
</head>
<body class="list-bgcolor">
	<div class="main-container new_project_entry">
		<form id="form" action="/project/editProject" method="post"
			onsubmit="return false;">
			<c:if test="${userName eq 'ninazhao' ||roleNo==9|| userName eq 'edward'|| userName eq 'jerrylong'|| userName eq 'qcdirector'}">
			<input type="hidden" value="${project.projectNo}" name="projectNo">
			<input type="hidden" id="factoryList" name="factoryList">
			<div class="main-table meeting-list-table form-horizontal">
				<a class="select_blank" target="_blank"
					href="/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}">返回功能选择页</a>
				<h3>项目修改：</h3>
				<div class="form-group">
					<label for="">相关项目号</label> <span>${project.projectNo}</span>
					<c:if test="${userName eq 'ninazhao'}">
					    <a href="/project/sysnProjectByErp?projectNo=${project.projectNo}"
						target="_blank">同步合同信息</a>
					</c:if> 
				</div>
				<div class="form-group">
					<label>项目状态</label> <select class="form-control"
						id="project_status" name="projectStatus">
						<option value="0"
							<c:if test="${project.projectStatus == 0}">selected</c:if>>新立项项目</option>
						<option value="1"
							<c:if test="${project.projectStatus == 1}">selected</c:if>>进行中项目</option>
						<option value="2"
							<c:if test="${project.projectStatus == 2}">selected</c:if>>大货完结项目</option>
						<option value="4"
							<c:if test="${project.projectStatus == 4}">selected</c:if>>已取消项目</option>
						<option value="5"
							<c:if test="${project.projectStatus == 5}">selected</c:if>>已暂停项目</option>
						<option value="6"
							<c:if test="${project.projectStatus == 6}">selected</c:if>>样品完结项目</option>
					</select>
				</div>
				<c:if test="${userName eq 'ninazhao'}">
				<div class="form-group mt10">
					<label for="">金额</label> <input type="text" class="form-control"
						value="${project.projectAmount}" id="amount" name="projectAmount"
						onkeyup="keyUp(this)" onKeyPress="keyPress(this)"
						onblur="onBlur(this)" /> <span>万美元</span>
				</div>
				<c:if test="${project.sampleScheduledDate!=null}"><div class="form-group mt10">
					<div class="clearfix">
						<label class="label_date add_label_date">样品交期</label>
						<div class="add_date">
							<div class="input-group date form_date" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="sampleScheduledDate" name="sampleScheduledDate"
									class="form-control brt brt_7 add_input" size="16" type="text"
									value="<fmt:formatDate value="${project.sampleScheduledDate}" pattern="yyyy-MM-dd"/>"
									place="选择日期" placeholder="选择日期" requiredate
									style="width: 161px;"><span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span class="add_span"></span>
						</div>
					</div>
				</div></c:if>
				<c:if test="${project.deliveryDate!=null}"><div class="form-group">
					<div class="clearfix">
						<label class="label_date add_label_date">大货交期</label>
						<div class="add_date">
							<div class="input-group date form_date" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="deliveryDate" name="deliveryDate"
									class="form-control brt brt_7 add_input" size="16" type="text"
									value="<fmt:formatDate value="${project.deliveryDate}" pattern="yyyy-MM-dd"/>"
									place="选择日期" field="报价截止日期" placeholder="选择日期" requiredate
									style="width: 161px;"><span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span class="add_span"></span>
						</div>
						<span></span>
					</div>
				</div></c:if>
				</c:if>
				<c:if test="${project.sampleFinishTime!=null }"><div class="form-group">
					<div class="clearfix">
						<label class="label_date add_label_date">项目样品完结时间</label>
						<div class="add_date">
							<div class="input-group date form_date" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="sampleFinishTime" name="sampleFinishTime"
									class="form-control brt brt_7 add_input" size="16" type="text"
									value="<fmt:formatDate value="${project.sampleFinishTime}" pattern="yyyy-MM-dd"/>"
									place="选择日期" placeholder="选择日期" requiredate
									style="width: 161px;"><span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span class="add_span"></span>
						</div>
						<span></span>
					</div>
				</div></c:if>
				<c:if test="${project.finishTime!=null}"><div class="form-group">
					<div class="clearfix">
						<label class="label_date add_label_date">项目大货完结时间</label>
						<div class="add_date">
							<div class="input-group date form_date" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="finishTime" name="finishTime"
									class="form-control brt brt_7 add_input" size="16" type="text"
									value="<fmt:formatDate value="${project.finishTime}" pattern="yyyy-MM-dd"/>"
									place="选择日期" placeholder="选择日期" requiredate
									style="width: 161px;"><span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span class="add_span"></span>
						</div>
						<span></span>
					</div>
				</div></c:if>
				<h4>
					<b>各个项目完结时间:</b>
				</h4>
				<div class="factorys mt10">
					<c:forEach var="obj" items="${factoryList}" varStatus="i">
						<div class="facotry_list clearfix" filed="${obj.id}">
							<p>
								<b>${obj.contractNo} ${obj.factoryName}</b>
							</p>
							<div class="clearfix mt10">
							<div class="s1">
								<c:if test="${obj.sampleDate != null && obj.orderNature == 1}">
									<span class="mr5 w55">样品</span>
									<input type="checkbox" name="select" value="0" <c:if test="${obj.sampleFinishTime != null && obj.orderNature == 1}">checked</c:if>>
								</c:if>
								<c:if test="${obj.deliveryDate != null && obj.orderNature == 1}">
									<span class="mr5 w55">大货</span>
									<input type="checkbox" name="select" value="1" <c:if test="${obj.productFinishTime != null && obj.orderNature == 1}">checked</c:if>>
								</c:if>
								<c:if test="${obj.orderNature == 2}">
									<span class="mr5 w55">返修补货</span>
									<input type="checkbox" name="select" value="2" <c:if test="${obj.repairReplenishmentFinishTime != null && obj.orderNature == 2}">checked</c:if>>
								</c:if>
							</div>
							<div class="s2">
								<span class="mr5 ml15">完成日期</span>
								<div class="display_inline_block">
									<div class="input-group date form_date"
										data-date-format="yyyy-mm-dd">
										<input class="form-control brt brt_7" size="16" type="text"
											place="选择日期" placeholder="选择日期" readonly
											<c:if test="${obj.sampleDate != null && obj.orderNature == 1}">value="${obj.sampleFinishTime}"</c:if>
											<c:if test="${obj.deliveryDate != null && obj.orderNature == 1}">value="${obj.productFinishTime}"</c:if>
											<c:if test="${obj.orderNature == 2}">value="${obj.repairReplenishmentFinishTime}"</c:if>>
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
							</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

			<div class="btns">
				<button class="btn" onclick="editProject()">修改</button>
			</div>
			</c:if>
			<c:if test="${roleNo==5}">
			<input type="hidden" value="${project.projectNo}" name="projectNo" id="projectNo">
			<input type="hidden" id="factoryList" name="factoryList">
			<div class="main-table meeting-list-table form-horizontal">
				<a class="select_blank" target="_blank"
					href="/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}">返回功能选择页</a>
				<h3>项目修改：</h3>
				<div class="form-group">
					<label for="">相关项目号</label> <span>${project.projectNo}</span>
				</div>
				<div class="form-group">
					<label>现在项目状态</label> 
						<c:if test="${project.projectStatus == 2}">大货完结项目</c:if>
						<c:if test="${project.projectStatus == 6}">样品完结项目</c:if>
				</div>
				</div>
           <div class="row add_div">
           <div class="col-xs-12">
             <table class="table table-bordered">
             	<thead>
		             <tr>
			             <th></th>
			             <th>工厂名称</th>
			             <th>合同交期</th>
			             <th>产品是否完成</th>	             
		             </tr>
	             </thead>
	             <tbody>
		            <c:forEach var="obj" items="${factoryList}" varStatus="i">
		             <tr>
		             <td><c:if test="${obj.contractNo!=null }">${obj.contractNo }</c:if>
		             <c:if test="${obj.contractNo==null }">合同${i.count }</c:if>
		             </td>
		             <td>${obj.factoryName }</td>
		             <td>
		             <c:if test="${obj.sampleDate!=null }"><fmt:formatDate value="${obj.sampleDate }" pattern="yyyy-MM-dd"/></c:if>
		             <c:if test="${obj.deliveryDate!=null }"><fmt:formatDate value="${obj.deliveryDate }" pattern="yyyy-MM-dd"/></c:if>
		             </td>
		             <td>
		             YES &nbsp; / &nbsp; <a class="add_span_state btn" href="##" onclick="updateBargain(${obj.id},0);">NO</a>
		             </td>
		            
		             </tr>
		             </c:forEach>
	             </tbody>
             </table>
				<!-- <button class="btn" onclick="updateStatus()">将本项目修改为进行中</button> -->
				<div class="bottom">
					<div><button class="btn select_blank" onclick="updateBargain(0,1);">重启本项目</button><span class="red">注意本操作不可逆, 项目状态将变为进行中</span></div>
					<div><button class="btn select_blank" onclick="updateBargain(0,2);">暂停本项目</button><span class="red">本项目状态将变为项目暂停</span></div>
					<div><button class="btn select_blank" onclick="updateBargain(0,3);">取消本项目</button><span class="red">本项目状态将变为项目取消</span></div>
				</div>
			</div>
			</div>		
			</c:if>
		</form>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-form.js"></script>
<script type="text/javascript">
	//上页、下一页查询
	function seachPage(num) {
		num = Number(num);
		var countPage = Number($("#countPage").val());
		var totalPage = Number($('#totalPage').val());
		if (countPage == 1 && num == 1) {
			return false;
		}
		if (num == 2 && countPage == totalPage) {
			return false;
		}
		if (num == 1) {
			$("#countPage").val(countPage - 1);
			searchProjectData(countPage - 1)
		} else if (num == 2) {
			$("#countPage").val(countPage + 1);
			searchProjectData(countPage + 1)
		}

	}

	function goBack() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var purchaseNameId = "";
		window.location.href = "${ctx}/jsp/project_list.jsp?userId=" + userId
				+ "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId
				+ "&userName=" + encodeURI(encodeURI(userName));
	}

	function foc() {
		$(".close-ck").parents(".updown-list").slideUp();
		$(".box-executive em").removeClass("arrow-icon");
	}

	function exitlogin() {
		$.cookie('name', '', {
			path : '/'
		});
		$.cookie('pass', '', {
			path : '/'
		});
		window.location.href = "${ctx}/index.jsp";
	}
	function goBack() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var purchaseNameId = "";
		window.location.href = "${ctx}/user/toIndex?userId=" + userId
				+ "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId
				+ "&userName=" + encodeURI(encodeURI(userName));
	}

	//项目修改
	function editProject() {

		//工厂完成对应合同信息
		var flag = true;
		var factoryList = [];
		$('.facotry_list').each(function() {
							var This = $(this);
							var id = This.attr('filed');
							var orderState = '';
							var endDate = '';
							var sampleFinishTime = '';
							var productFinishTime = '';
							var repairReplenishmentFinishTime = '';
							if (This.find('input[name="select"]')
									.is(":checked")) {
								orderState = This.find(
										'input[name="select"]:checked').val();
								endDate = This.find(
										'input[name="select"]:checked')
										.parent().siblings('.s2').find('input')
										.val();
								if (!endDate) {
									layer.msg("请选择结束日期", {
										time : 2000
									});
									flag = false;
									return false;
								}
								if (orderState == 0) {
									sampleFinishTime = endDate;
								}
								if (orderState == 1) {
									productFinishTime = endDate;
								}
								if (orderState == 2) {
									repairReplenishmentFinishTime = endDate;
								}
							}
// 							if (!orderState) {
// 								return;
// 							}
							//如果选择样品完结，需要给定完结日期
							if($('#sampleFinishTime').val()){
								//样品合同，未填写完成日期，给予提醒
								if (This.find('input[name="select"]').val() == 0 && !This.find('input[name="select"]').is(":checked")){
									layer.msg("样品已完结，还有样品合同未选择完结日期", {
										time : 3000
									});
									flag = false;
									return false;
								}				
							}
							//如果选择大货完结，需要给定完结日期
							if($('#deliveryDate').val()){
								//大货合同，未填写完成日期，给予提醒
								if (This.find('input[name="select"]').val() == 1 && !This.find('input[name="select"]').is(":checked")){
									layer.msg("大货已完结，还有大货合同未选择完结日期", {
										time : 3000
									});
									flag = false;
									return false;
								}
							}
							
							
							var factory = {
								"id" : id,								
								"productFinishTime" : productFinishTime,
								"sampleFinishTime" : sampleFinishTime,
								"repairReplenishmentFinishTime" : repairReplenishmentFinishTime
							};
							factoryList.push(factory);
						})

		if (!flag) {
			return false;
		}

		$('#factoryList').val(JSON.stringify(factoryList));

		$("#form").ajaxSubmit({
			type : "post",
			url : "${ctx}/project/editProject",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
					layer.msg("修改成功", {
						time : 2000
					});					
					setTimeout(function(){window.close();},2000);
				}
			},
			error : function() {

			}
		});
	}
	//项目修改
	function updateBargain(id,num) {
		var projectNo = $('#projectNo').val();        
		$.ajax({ 
			   url : "/project/updateBargain", 
			   type: "POST", 
			   data : { 
				   "projectNo":projectNo,
				   "id":id,
				   "num":num
			   }, 
			   dataType:"json", 
			   success : function(json) { 
				   if(json.ok) 
				    { 
					   window.close(); 
				    }else{
					   easyDialog.open({
							container : {
								content : json.message
							},
							overlay : false,
							autoClose : 2000
						}); 
				    }
			   }
			})
	}
	
	
	

	function keyPress(ob) {
		if (!ob.value.match(/^[\+\-]?\d*?\.?\d*?$/))
			ob.value = ob.t_value;
		else
			ob.t_value = ob.value;
		if (ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))
			ob.o_value = ob.value;
	}
	function keyUp(ob) {
		if (!ob.value.match(/^[\+\-]?\d*?\.?\d*?$/))
			ob.value = ob.t_value;
		else
			ob.t_value = ob.value;
		if (ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))
			ob.o_value = ob.value;
	}
	function onBlur(ob) {
		if (!ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))
			ob.value = ob.o_value;
		else {
			if (ob.value.match(/^\.\d+$/))
				ob.value = 0 + ob.value;
			if (ob.value.match(/^\.$/))
				ob.value = 0;
			ob.o_value = ob.value
		}
		;
	}
</script>
<script>
	//选中选日期
// 	$('.facotry_list .s2').hide();
	$('.facotry_list input[type="checkbox"]').click(function() {
		var check = $(this).prop('checked');
		if (check == true) {
			$(this).parent().siblings('.s2').show();
		} else if (check == false) {
			$(this).parent().siblings('.s2').hide();
		}
	})

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
</html>