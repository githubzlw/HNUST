<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String projectNo = request.getParameter("projectNo");
	String roleNo = request.getParameter("roleNo");
	String userName = request.getParameter("userName");
	String userId = request.getParameter("userId");
%>
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
<title>任务系统，质检情况填写</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/add.css">
<link rel="stylesheet" href="../css/progressBar.css" />
<link rel="stylesheet" href="../css/ui.css" />
<link rel="stylesheet" href="../css/ui.progress-bar.css">
<link rel="stylesheet" href="${ctx}/css/easydialog.css" />
<script type="text/javascript" src="../js/easydialog.min.js"></script>
<style type="text/css">
.position_relative ul {
	background-color: #fff;
	position: absolute;
	top: 40px;
	left: 0;
	z-index: 10;
}

#easyDialogBox {
	margin-top: -392px;
}

.add_tc {
	width: 320px;
	height: 270px;
	box-shadow: 3px -2px 6px rgba(43, 43, 43, 0.5), -3px 2px 6px
		rgba(43, 43, 43, 0.5);
	position: fixed;
	top: 40px;
	left: 50%;
	margin-left: -160px;
	z-index: 12;
	background-color: #fff;
	text-align: center;
	padding-top: 10px;
	display: none;
	margin-top: 80px;
	padding: 13px 11px 17px 13px;
}

.add_tc_zb {
	height: auto;
	width: 544px;
}

.add_tc .s_c {
	position: relative;
	text-align: left;
	padding-left: 10px;
	margin-bottom: 10px;
}

.add_tc button {
	width: 100px;
	height: 30px;
	line-height: 30px;
	background-color: #fff;
	border: 1px solid #ddd;
}

.add_tc_zb .s_c input {
	position: absolute;
	width: 120px;
	height: 90px;
	opacity: 0;
	top: 0;
	left: 0;
}

.add_tc_zb .imgs li {
	float: left;
	width: 120px;
	height: 90px;
	border: 1px solid #999;
	text-align: center;
	line-height: 90px;
	position: relative;
	margin-right: 12px;
	margin-bottom: 10px;
	color: #666;
	font-size: 14px;
}

.pic-checkbox {
	text-align: left;
}

.li_img {
	width: 146px;
	height: 93px;
	overflow: hidden;
	line-height: 131px;
	text-align: center;
	margin-right: 16px;
}

.add_tc input {
	height: auto;
}

.add_tc .btns2 {
	margin-top: 10px;
}

.add_question {
	margin-top: 15px;
}

.add_question th {
	font-size: 16px;
}

.material_type {
	border: 1px solid #ccc;
	padding: 5px 20px;
}

.material_type .col-xs-3, .material_type .col-xs-9 {
	padding: 0;
}

.material_type label {
	font-weight: normal;
	margin-right: 8px;
}

.material_type label input[type="radio"] {
	height: unset;
}

.add_material {
	width: 100%;
	margin: 10px 0;
}

.material_type {
	margin-bottom: 10px;
}

.btn-success1 {
	color: #fff;
	background-color: #0db1ff;
	border-color: #0db1ff;
}

.add_question input[type="radio"] {
	width: 16px;
	height: 16px;
}

.inspect-time {
	width: 100px;
	display: inline-block;
	margin-right: 5px;
}

.add_table2 {
	border: 1px solid #ccc;
	padding: 5px;
	margin-bottom: 20px;
}

.add_table2 label {
	font-weight: normal;
}

@media screen and (min-width:769px) {
	.add_tc_zb {
		width: 435px;
	}
	.add_wrap {
		width: 100%;
		font-size: 20px;
	}
	.material_wrap .container {
		width: 100%;
		font-size: 20px;
	}
	.material_wrap label span {
		font-size: 16px;
	}
	.surfaceTreatment span {
		font-size: 16px;
	}
	.surfaceTreatment input {
		height: unset;
	}
}

@media screen and (max-width:768px) {
	.add_tc_zb {
		width: 100%;
		left: 0;
		margin-left: 0;
	}
	.surfaceTreatment input[name="surfaceTreatment"] {
		height: auto;
	}
	.add_table2 tr td:first-child span {
		display: inline-block;
		width: 100px;
	}
}

.div_checkbox {
	width: 300px;
	height: 150px;
	overflow: auto;
	border: 1px solid #999;
	margin-left: 50px;
	padding: 0 10px;
}
.add_packing i{font-size:20px;top:6px;margin-right:5px;color:#0db1ff;}
.add_pack_detail em{margin-right:5px;color:#666;}
.add_pack_detail{margin-bottom:10px;}
.blue{color:#0db1ff;cursor: pointer;}
.packing_tc{position: fixed;top:50%;left:50%;transform: translate(-50%,-50%);width:90%;padding:20px 15px;
background-color:#fff;box-shadow: 0 0 9px 0 rgba(0,0,0,.2);z-index: 10;font-size:20px;text-align: center;
	max-width:768px;display: none;}
@media screen and (max-width:768px){
	.packing_tc{font-size:16px;}
}

</style>
</head>
<body>
	<div class="quality add_detail">
		<%--<div class="packing_tc"> 包装开箱内容不全 </div>--%>
		<input type="hidden" id="projectNoId" value="${projectNoId}">
		<input type="hidden" id="roleNo" value="${roleNo}"> <input
			type="hidden" id="userId" value="${userId}"> <input
			type="hidden" id="userName" value="${userName}"> <input
			type="hidden" id="projectNo" value="${projectNo}"> <input
			type="hidden" id="inspectionForm"> <input type="hidden"
			id="inspectionPath">

		<div class="title">
			<span class="glyphicon glyphicon-menu-left"
				onclick="window.location='${ctx}/project/showDetails?projectNo=${projectNo}&userName=${userName}'"></span>质检情况
		</div>

		<div class="add_tc add_tc1 add_tc_zb">
			<input type="hidden" id="inspectionPlanId"> <input
				type="hidden" id="inspectionPlan_li_index">
			<p></p>
			<p class="pic-checkbox">证明图片（最多3张）</p>
			<form id="file_upload_id" onsubmit="return false;" method="post"
				enctype="multipart/form-data">
				<div class="s_c">
					<input type="hidden" name="fileNames" id="fileNames" value="">
					<ul class="imgs clearfix" id="inspectionPic">
						<li class="i-upload"><span class="glyphicon glyphicon-plus"></span><input
							type="file" id="uploadFile" name="files"
							onchange="fileChange(this,0)" multiple></li>
					</ul>
				</div>
			</form>
			<p class="pic-checkbox">
				<input type="checkbox" id="p_check">加入总体的包装图
			</p>
			<p class="pic-checkbox">
				<input type="checkbox" id="m_check">加入总体的材料图
			</p>
			<textarea id="content" name="content" class="form-control"
				placeholder="请输入改点的文字说明"></textarea>
			<div class="btns2 clearfix">
				<button class="pull-left" onclick="cancelInspection(this)">取消</button>
				<button class="pull-right" onclick="addInspection(this)">保存</button>
			</div>
		</div>

		<div class="wrap wrap1">
			<p>
				项目号&nbsp;&nbsp;&nbsp;&nbsp;<span id="projectNo">${projectNo}</span>
			</p>
		</div>

		<div class="wrap wrap2">
			<p>检验报告</p>
			<div class="sele_">
				<label>这次上传的是：</label> <select id="type" name="type"
											   onchange="change_stage(this)">
					<option value="0" <c:if test="${checkType eq '样品'}">selected</c:if>>样品检验</option>
					<option value="2" <c:if test="${checkType eq '过程'}">selected</c:if>>中期检验</option>
					<option value="3" <c:if test="${checkType eq '出货'}">selected</c:if>>终期检验</option>
				</select> <span>报告</span>
			</div>
			<table class="table table-bordered table1 mt10">
				<tbody>
					<tr>
						<td>产品名</td>
						<td>${projectName}</td>
					</tr>
					<tr>
						<td>图号</td>
						<td><c:forEach var="obj" items="${distinctPlan}"
								varStatus="i">
								<p>
									<input type="checkbox" class="pic_num"
										value="${obj.productComponent}" onchange="select_img(this)"><span>${obj.productComponent}</span>
								</p>
							</c:forEach>
							<p>
								<input type="checkbox" class="pic_num" value="其他"><span>其他</span><input
									class="form-control">
							</p></td>
					</tr>
					<tr>
						<td>检验地点</td>
						<td class="facotry_td"><label class="mr10"><input
								type="checkbox" name="place" value="公司"> 公司</label> <label
							class="mr10"><input type="checkbox" name="place"
								value="仓库"> 仓库</label> <label><input type="checkbox"
								name="place" value="工厂"> 工厂</label>
							<div class="form-group position_relative" style="display: none;">
								<input class="form-control" name="factory" placeholder="请输入工厂名">
								<ul>

								</ul>
							</div>
							<div class="form-group position_relative" style="display: none;">
								<input class="form-control mt10" name="factory"
									placeholder="请输入工厂名">
								<ul>

								</ul>
							</div>
							<div class="form-group position_relative" style="display: none;">
								<input class="form-control mt10" name="factory"
									placeholder="请输入工厂名">
								<ul>

								</ul>
							</div></td>
					</tr>
					<tr>
						<td>检验费时</td>
						<td><input class="form-control" field="spendTime"
							id="spendTime" placeholder="">天，<span>只允许填写0-1之间的数，如果检验时间超过1天，请注意再给检验发布任务</span></td>
					</tr>
					<tr>
						<td>检验人员</td>
						<td>
							<div class="div_checkbox">
								<c:forEach items="${users}" var="user">
									<div class="checkbox">
										<label> 
										<input <c:if test="${userName==user.userName }">checked ='checked'</c:if> type="checkbox" name="quality_testing"
											value="${user.userName }">${user.userName }
											
										</label>
									</div>
								</c:forEach>
							</div>
						</td>
					</tr>
					<tr>
						<td>检验日期</td>
						<td>
							<div class="input-group date form_date" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="checkDate" field="checkDate" name="checkDate"
									class="form-control brt brt_7" size="16" type="text" value=""
									place="选择日期" placeholder="选择日期" readonly requiredate> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>订单量</td>
						<td><input class="form-control" field="orders" placeholder=""></td>
					</tr>
				</tbody>
			</table>
			<form id="detail_form" onsubmit="return false;">
				<input type="hidden" name="projectNo" value="${projectNo}" />
				<div class="imgs">
					<span>细节</span>
					<ul class="clearfix">
						<li><em class="glyphicon glyphicon-plus"></em><input
							name="files" onchange="fileChange(this,0)" type="file" multiple /></li>
					</ul>
				</div>
			</form>
			<form id="bad_form" onsubmit="return false;">
				<input type="hidden" name="projectNo" value="${projectNo}" />
				<div class="imgs">
					<span>不良品</span>
					<ul class="clearfix">
						<li><em class="glyphicon glyphicon-plus"></em><input
							name="files" onchange="fileChange(this,0)" type="file" multiple /></li>
					</ul>
				</div>
			</form>
			<form id="material_form" onsubmit="return false;">
				<input type="hidden" name="projectNo" value="${projectNo}" />
				<div class="imgs">
					<span>材质证明</span>
					<ul class="clearfix">
						<li><em class="glyphicon glyphicon-plus"></em><input
							name="files" onchange="fileChange(this,0)" type="file" multiple /></li>
					</ul>
				</div>
			</form>
			<form id="package_form" onsubmit="return false;">
				<input type="hidden" name="projectNo" value="${projectNo}" />
				<div class="imgs">
					<%--<div class="old">
						<span>包装图(开箱比例${openRate==null?'暂无':openRate}${openRate!=null?'%':''}，质检检验要标好箱号和生产日期并拍照)</span><br>
						<span>实际开箱比例系统会自动计算</span>
					</div>--%>
					<div class="add_packing">
						<p >包装图，实际开箱比例系统会自动计算 ,<span class="blue"><i class="glyphicon glyphicon-menu-up">  </i>点击收起详细要求</span></p>
						<div class="add_pack_detail">
							<p><em class="glyphicon glyphicon-star"></em>多个开箱的情况下，质检必须提供开箱照片，并在箱子上 写上  序号 </p>
							<p><em class="glyphicon glyphicon-star"></em>所有抽检的产品，聚在一起，拍一张全家福。照片里面需要 摆一张纸，写上抽样数量。 如果不得不分批拍，请注明 每一批的出处和数量。</p>
							<p><em class="glyphicon glyphicon-star"></em>质检总监需要对 抽样数量 是否合理 进行 负责 </p>
						</div>
					</div>



					<ul class="clearfix">
						<li><em class="glyphicon glyphicon-plus"></em><input
							name="files" onchange="fileChange(this,0)" type="file" multiple /></li>
					</ul>
				</div>
			</form>
			<table class="table table-bordered table2">
				<tbody>
					<tr>
						<td>总箱数</td>

						<td><input class="form-control" field="boxNumber" id="boxNumber"
							placeholder="数字"></td>
					</tr>
					<tr>
						<td>每箱数量</td>

						<td><input class="form-control" field="perQty" placeholder="" id="perQty"></td>
					</tr>
					<tr>
						<td>数量清点</td>

						<td><input class="form-control" field="inventoryQty" id="inventoryQty"
							placeholder=""></td>
					</tr>
					<tr>
						<td>开箱数量</td>

						<td><input class="form-control" field="openQty" id="openQty"
							placeholder=""></td>
					</tr>
				</tbody>
			</table>
			<div class="material_wrap">
				<div class="material_type container">
					<div class="row row1">
						<div class="col-xs-3">材料类型:</div>
						<div class="col-xs-9">
							<label data-text="1"><input type="radio"
								name="materialSelection0" value="1"> <span>不锈钢</span></label> <label
								data-text="2"><input type="radio"
								name="materialSelection0" value="2"> <span>普通碳钢</span></label>
							<label data-text="3"><input type="radio"
								name="materialSelection0" value="3"> <span>特种钢材</span></label>
							<label data-text="4"><input type="radio"
								name="materialSelection0" value="4"> <span>塑料</span></label> <label
								data-text="5"><input type="radio"
								name="materialSelection0" value="5"> <span>铝</span></label> <label
								data-text="6"><input type="radio"
								name="materialSelection0" value="6"> <span>铜</span></label> <label
								data-text="7"><input type="radio"
								name="materialSelection0" value="7"> <span>铸铁铸钢</span></label>
							<label data-text="8"><input type="radio"
								name="materialSelection0" value="8"> <span>其他</span></label>
						</div>
					</div>
					<div class="row row2">
						<div class="col-xs-3">具体牌号:</div>
						<div class="col-xs-9">
							<input type="text" name="specificLicensePlate"
								id="specificLicensePlate" class="form-control">
						</div>
					</div>
					<div class="row row3">
						<div class="col-xs-3">材料判定:</div>
						<div class="col-xs-9">
							<label><input type="radio" name="materialJudgement0"
								value="1"> <span>合格</span></label> <label><input
								type="radio" name="materialJudgement0" value="2"> <span>不合格</span></label>
							<label><input type="radio" name="materialJudgement0"
								value="3"> <span>不知道</span></label>
						</div>
					</div>
					<div class="row row4">
						<div class="col-xs-3">提示</div>
						<div class="col-xs-9">
							<p>提示文字提示文字提示文字提示文字提示文字</p>
						</div>
					</div>
					<div class="row ">
						<div class="col-xs-12 text-right">
							<button class="btn btn-danger material_del">删除</button>
						</div>
					</div>
				</div>
			</div>
			<div class="container add_wrap">
				<div class="row">
					<div class="col-xs-12">
						<button class="btn btn-success1 add_material">添加</button>
					</div>
				</div>
			</div>
			<!-- <table class="table table-bordered table2">
				<tbody>
					<tr>
						<td>材料选择</td>
						<td><input type="radio" name="materialSelection" value="1" > <span>不锈钢</span>
						<input type="radio" name="materialSelection" value="2" > <span>普通碳钢</span>
						<input type="radio" name="materialSelection" value="3" > <span>特种钢材</span>
						<input type="radio" name="materialSelection" value="4" > <span>塑料</span>
						<input type="radio" name="materialSelection" value="5" > <span>铝</span>
						<input type="radio" name="materialSelection" value="6" > <span>铜</span>
						<input type="radio" name="materialSelection" value="7" > <span>铸铁铸钢</span>
						<input type="radio" name="materialSelection" value="8" > <span>其他</span></td>
					</tr>
					<tr>
						<td>具体号牌</td>

						<td><input class="form-control" name="specificLicensePlate" id="specificLicensePlate" placeholder=""></td>
					</tr>
					<tr>
						<td>材料判定</td>

						<td><input type="radio" name="materialJudgement" value="1" > <span>合格</span>
						<input type="radio" name="materialJudgement" value="2" > <span>不合格</span>
						<input type="radio" name="materialJudgement" value="3" > <span>不知道</span></td>
					</tr>
					
				</tbody>
			</table> -->

			<div class="table2 add_table2">
				<div class="row surfaceTreatment">
					<div class="col-xs-3">表面处理选择</div>
					<div class="col-xs-9">
						<label><input type="radio" name="surfaceTreatment"
							value="1"> <span>预镀锌的管材或者板材</span></label> <label><input
							type="radio" name="surfaceTreatment" value="2"> <span>镀锌</span></label>
						<label><input type="radio" name="surfaceTreatment"
							value="3"> <span>电镀</span></label> <label><input
							type="radio" name="surfaceTreatment" value="4"> <span>阳极氧化</span></label>
						<label><input type="radio" name=surfaceTreatment value="5">
							<span>喷粉</span></label> <label><input type="radio"
							name="surfaceTreatment" value="6"> <span>油漆</span></label> <label><input
							type="radio" name="surfaceTreatment" value="7"> <span>无</span></label>
					</div>
				</div>
				<div class="row" id="result_entry1" style="display: none;">
					<div class="col-xs-12">请输入工厂的原料规格和你的进料检验结果</div>
					<div class="col-xs-12">
						<textarea class=" form-control" id="filmThickness"
							name="filmThickness"></textarea>
					</div>
				</div>
				<div class="row" id="result_entry2">
					<div class="col-xs-12">请测膜厚并录入观察结果</div>
					<div class="col-xs-12">
						<textarea class=" form-control" id="surfaceResultEntry"
							name="surfaceResultEntry"></textarea>
					</div>
				</div>
			</div>
			<!-- <table class="table table-bordered table2 add_table2">
				<tbody>
					<tr class="surfaceTreatment">
						<td><span>表面处理选择</span></td>
						<td>
						<label><input type="radio" name="surfaceTreatment" value="1" > <span>预镀锌的管材或者板材</span></label>
						<label><input type="radio" name="surfaceTreatment" value="2" > <span>镀锌</span></label>
						<label><input type="radio" name="surfaceTreatment" value="3" > <span>电镀</span></label>
						<label><input type="radio" name="surfaceTreatment" value="4" > <span>阳极氧化</span></label>
						<label><input type="radio" name=surfaceTreatment value="5" > <span>喷粉</span></label>
						<label><input type="radio" name="surfaceTreatment" value="6" > <span>油漆</span></label>
						<label><input type="radio" name="surfaceTreatment" value="7" > <span>无</span></label>
						</td>
					</tr>
					<tr id="result_entry1">
						<td><span>请输入工厂的原料规格和你的进料检验结果及膜厚</span></td>
                       <td><textarea style=" border:solid 1px #e9e9e9;  " rows="5" cols="70" id="surfaceResultEntry" name="surfaceResultEntry"></textarea></td>
					</tr>
					<tr id="result_entry2" style="display:none;">
						<td><span>请测膜厚并录入观察结果</span></td>
                        <td><textarea style=" border:solid 1px #e9e9e9; " rows="5" cols="70" id="surfaceResultEntry" name="surfaceResultEntry"></textarea></td>
					</tr>
					
				</tbody>
			</table> -->
			<form id="check_form" onsubmit="return false;">
				<input type="hidden" name="projectNo" value="" />
				<div class="imgs add_jy">
					<span>检验表格</span>
					<ul class="clearfix">
						<li><em class="glyphicon glyphicon-plus"></em><input
							name="files" onchange="fileChange(this,1)" type="file" multiple /></li>
					</ul>
				</div>
			</form>
			<!-- <div class="add_inputs"> -->
			<!-- <div class="mb10 add_checkbox" id="stage_div">
					<label>
						<input type="checkbox" name="isAllRight"/>
						<span>含本报告在内如果所有<span class="project-stage">样品</span>阶段产品已经完全没有问题请勾选我（将会导致任务系统内的<span class="project-stage">样品生产阶段</span>结束）</span>
					</label>
				</div> -->
			<!-- <div class="mb10">
					<div>有几个尺寸 因为没检具而没法测量？</div>
					<input type="text" id="no_check"
						onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
						onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
				</div>
				<div class="mb10">
					<div>有几个关键尺寸超差？</div>
					<input type="text" id="key_size_exceed"
						onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
						onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
				</div>
				<div class="factory_history">
					<div>工厂有做过：</div>
					<label><input type="checkbox" name="sample" value="首样检验">首样检验</label>
					<label><input type="checkbox" name="process" value="过程检验">过程检验</label>
					<label><input type="checkbox" name="feed" value="进料检验">进料检验</label>
					<label><input type="checkbox" name="none" value="没有">没有</label>
				</div> -->
			<%-- <div>
				    <div>检验计划相关要求：<span style="color:red">请注意，有图的地方务必点击+号插图！！！</span></div>
				    <table class="add_tabel table">
				        <tbody id="check_body">
				        <c:forEach var="obj" items="${distinctPlan}" varStatus="i">
				           <tr class="check check_${i.index}" style="display:none;" filed_date = "<fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd" />">
				              <td>
                                <span>${obj.productComponent}</span>	
                                <p>要求完成点：<button class="check_btn" type="button" onclick="openInspection(this,${i.index})">请展开选择</button></p>			              
				              </td>
				              <td><input type="checkbox" onchange="selectWork(this,${i.index})" class="all-check" value="1"><span>做了</span></td>
				              <td><input type="checkbox" onchange="selectWork(this,${i.index})" class="all-check" value="0"><span>没做</span></td>				        
				              <td><span>说明图/文字</span></td>				        			        
				           </tr>
				            <c:forEach var="plan" items="${plans}" varStatus="j">
				                 <c:if test="${obj.productComponent == plan.productComponent}">
				                  <tr class="check_s check_index_${i.index}" style="display:none;" filed="${plan.id}">
					                 <td>
					                   <span>${plan.type}:${plan.productStandards}</span>	
					                 </td>
					                 <td><input type="checkbox" value="1" name="work" onchange="selectWorkOnly(this,${i.index})" <c:if test="${plan.isWork == 1}">checked</c:if>><span>做了</span></td>
				                     <td><input type="checkbox" value="0" name="no_work" onchange="selectWorkOnly(this,${i.index})" <c:if test="${plan.isWork == 0}">checked</c:if>><span>没做</span></td>
				                     <td><button class="t-btn" onclick="openInspectionImg('${plan.id}',${j.index})"
				                     <c:if test="${(plan.content!=null&&plan.content!='') || (plan.inspectionPic!=null&&plan.inspectionPic!='')}">
				                     style="color:green;"
				                     </c:if>
				                     >+</button></td>
				                  </tr>
				                 </c:if>				                 
				            </c:forEach>
			            </c:forEach>	           				        
				        </tbody>
				    </table>
				</div> --%>
			<!-- </div> -->
			<div class="report">
				<div class="radios">
					<div class="radio_0 clearfix">
						<input type="radio" name="state" value="0" checked> <span>没问题</span>
					</div>
					<%-- 			<div class="question1 noIssue">
						<p>注意,可选择将下列工厂的下列生产阶段结束:(已经默认勾选任务发布者觉得可结束的阶段)</p>
				    <c:forEach var="obj" items="${factoryList}" varStatus="i">
				       <input type="hidden" id="deliveryDate" name="deliveryDate" value="<fmt:formatDate value="${obj.deliveryDate }" pattern="yyyy-MM-dd" />">
				       <input type="hidden" id="sampleDate" name="sampleDate" value="<fmt:formatDate value="${obj.sampleDate }" pattern="yyyy-MM-dd" />" >
								<div class="factorys" filed="${obj.id}">
								<p>${obj.contractNo}  ${obj.factoryName}</p>
									<c:if test="${obj.sampleDate != null && obj.orderNature == 1}">
											<div class="facotry_list clearfix">								
												<div class="s1">
													<span class="mr5 w55">样品</span><input type="checkbox"
														name="select" <c:if test="${obj.projectStage == 0}">checked</c:if> value="0">
												</div>
												<div class="s2">
													<span class="mr5 ml15">完成日期</span>
													<div class="input-group date form_date" data-date=""
														data-date-format="yyyy-mm-dd">
														<input class="form-control brt brt_7" size="16" type="text" value="${obj.sampleFinishTime}"
															place="选择日期" placeholder="选择日期" readonly="" requiredate="">
														<span class="input-group-addon"><span
															class="glyphicon glyphicon-calendar"></span></span>
													</div>
												</div>
											</div>
									</c:if>
									<c:if test="${obj.deliveryDate != null && obj.orderNature == 1}">
										<div class="facotry_list clearfix">
											<div class="s1">								
												<span class="mr5 w55">大货</span>
												<input type="checkbox" name="select" <c:if test="${obj.projectStage == 1}">checked</c:if> value="1"> 
											</div>
											<div class="s2">									
												<span class="mr5 ml15">完成日期</span>
												<div class="input-group date form_date" data-date=""
													data-date-format="yyyy-mm-dd">
													<input class="form-control brt brt_7" size="16" type="text" value="${obj.productFinishTime}"
														place="选择日期" placeholder="选择日期" readonly="" requiredate="">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-calendar"></span></span>
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${obj.orderNature == 2}">
										<div class="facotry_list clearfix">
											<div class="s1">
											<span class="mr5 w55">返修补货</span><input type="checkbox"
												name="select" value="2" <c:if test="${obj.projectStage == 2}">checked</c:if>> 
											</div>	
											<div class="s2">								
												<span class="mr5 ml15">完成日期</span>
												<div class="input-group date form_date" data-date=""
													data-date-format="yyyy-mm-dd">
													<input class="form-control brt brt_7" size="16" type="text" value="${obj.repairReplenishmentFinishTime}"
														place="选择日期" placeholder="选择日期" readonly="" requiredate="">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-calendar"></span></span>
												</div>
											</div>
										</div>	
									</c:if>							
								</div>
						</c:forEach>
					</div> --%>
					<div class="radio_0 clearfix second_radio">
						<input type="radio" name="state" value="1"> <span>有问题，但已经处理/让步接受</span>
					</div>
					<div class="explain" style="display: none">
						<%-- <div class="select_more">
								<span><em id="purchase">${project.purchaseName==null?project.sellName:project.purchaseName}</em></span>
							</div> --%>
						<textarea id="explain" class="form-control" placeholder="原因说明"></textarea>
					</div>
					<div class="question1 deal" style="display: none;">
						<p>
							<i class="red">注意</i>,可选择将下列工厂的下列生产阶段结束:(已经默认勾选任务发布者觉得可结束的阶段)
						</p>
						<c:forEach var="obj" items="${factoryList}" varStatus="i">
							<div class="factorys" filed="${obj.id}">
								<p>${obj.contractNo}${obj.factoryName}</p>
								<c:if test="${obj.sampleDate != null && obj.orderNature == 1}">
									<div class="facotry_list clearfix">
										<div class="s1">
											<span class="mr5 w55">样品</span><input type="checkbox"
												name="select" value="0"
												<c:if test="${obj.projectStage == 0}">checked</c:if>>
										</div>
										<div class="s2">
											<span class="mr5 ml15">完成日期</span>
											<div class="input-group date form_date" data-date=""
												data-date-format="yyyy-mm-dd">
												<input class="form-control brt brt_7" size="16" type="text"
													value="${obj.sampleFinishTime}" place="选择日期"
													placeholder="选择日期" readonly="" requiredate=""> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${obj.deliveryDate != null && obj.orderNature == 1}">
									<div class="facotry_list clearfix">
										<div class="s1">
											<span class="mr5 w55">大货</span> <input type="checkbox"
												name="select" value="1"
												<c:if test="${obj.projectStage == 1}">checked</c:if>>
										</div>
										<div class="s2">
											<span class="mr5 ml15">完成日期</span>
											<div class="input-group date form_date" data-date=""
												data-date-format="yyyy-mm-dd">
												<input class="form-control brt brt_7" size="16" type="text"
													value="${obj.productFinishTime}" place="选择日期"
													placeholder="选择日期" readonly="" requiredate=""> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${obj.orderNature == 2}">
									<div class="facotry_list clearfix">
										<div class="s1">
											<span class="mr5 w55">返修补货</span><input type="checkbox"
												name="select" value="2"
												<c:if test="${obj.projectStage == 2}">checked</c:if>>
										</div>
										<div class="s2">
											<span class="mr5 ml15">完成日期</span>
											<div class="input-group date form_date" data-date=""
												data-date-format="yyyy-mm-dd">
												<input class="form-control brt brt_7" size="16" type="text"
													value="${obj.repairReplenishmentFinishTime}" place="选择日期"
													placeholder="选择日期" readonly="" requiredate=""> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span>
											</div>
										</div>
									</div>
								</c:if>
							</div>
						</c:forEach>

					</div>
					<div class="radio_0 clearfix last_radio">
						<input type="radio" name="state" value="2"> <span>有问题，需要采购解决</span>
						<div class="task">
							<div class="select_more">
								<span><em id="purchase">${project.purchaseName==null?project.sellName:project.purchaseName}</em></span>
							</div>
							<textarea id="taskDetail" class="form-control" placeholder="任务说明"></textarea>
						</div>

						<%-- 	<div class="explain" style="display: none">
							<div class="select_more">
								<span><em id="purchase">${project.purchaseName==null?project.sellName:project.purchaseName}</em></span>
							</div>
							<textarea id="explain" class="form-control" placeholder="原因说明"></textarea>
						</div> --%>
					</div>
				</div>
			</div>
			<div class="line mt10"></div>
			<c:if test="${task!=null }">
				<c:if test="${projectDrawingList!=null }">
			请质检选择下列图纸更新是否有对应的过程检或样品：
			
			<c:forEach var="obj" items="${projectDrawingList}" varStatus="i">

						<p class="p-row">
							<span class="span-left">图纸文件:</span> 
							<span class="span-right"><a
								href="http://117.144.21.74:33168/upload/tuzhi/${obj.drawingName}">${obj.drawingName}</a>
								<c:if test="${task.drawingId!=obj.id}">
									<button class="btn btn-default" id="selectTask" 
										onclick="updateAll(${task.id},'${obj.id }');">确认</button>										
									<i class="updataall" id="updateall"></i>	
								</c:if> 								
								<c:if test="${task.drawingId==obj.id}">已确认,${task.verificationTime },${task.verifier } </c:if>
							</span>
						</p>

					</c:forEach>
				</c:if>
			</c:if>
			<div class="line mt10"></div>
			<div class="add_question">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>合同号</th>
							<th>供应商名</th>
							<th>合同交期</th>
							<th>产品是否完成</th>
							<th>是否需要返工</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="obj" items="${factoryList}" varStatus="i">
							<tr>
								<td><c:if test="${obj.contractNo!=null }">${obj.contractNo }</c:if>
									<c:if test="${obj.contractNo==null }">合同${i.count }</c:if></td>
								<td>${obj.factoryName }</td>
								<td><c:if test="${obj.sampleDate!=null }">
										<fmt:formatDate value="${obj.sampleDate }"
											pattern="yyyy-MM-dd" />
									</c:if> <c:if test="${obj.deliveryDate!=null }">
										<fmt:formatDate value="${obj.deliveryDate }"
											pattern="yyyy-MM-dd" />
									</c:if></td>
								<td><label><input type="radio"
										<c:if test="${obj.sampleFinishTime!=null||obj.productFinishTime!=null }">checked</c:if>
										name="finish${i.count }" id="finish${i.count }"
										onclick="modifyState(0,1,'${obj.projectNo}',${obj.id },this);"
										value="是">是</label> <label><input type="radio"
										<c:if test="${obj.sampleFinishTime==null&&obj.productFinishTime==null }">checked</c:if>
										name="finish${i.count }" id="finish${i.count }"
										onclick="modifyState(0,0,'${obj.projectNo}',${obj.id },this);"
										value="否">否</label></td>
								<td><label><input type="radio"
										<c:if test="${obj.rework==1 }">checked</c:if>
										name="rework${i.count }" id="rework${i.count }"
										onclick="modifyState(1,1,'${obj.projectNo}',${obj.id },this);"
										value="是">是</label> <label><input type="radio"
										<c:if test="${obj.rework==0 }">checked</c:if>
										name="rework${i.count }" id="rework${i.count }"
										onclick="modifyState(1,0,'${obj.projectNo}',${obj.id },this);"
										value="否">否</label></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="line mt10"></div>
			<div class="jy_result">
				<p>检验结论:(会随检验报告的连接抄送在邮件正文中)</p>
				<textarea class="form-control" id="conclusion"></textarea>
			</div>
		</div>
		<%-- <div class="btns text-right">
			<label>本质检报告针对${projectNo}项目下的</label>
			<select name="task_sel" id="task_sel">
			    <option></option>
			    <c:forEach var="obj" items="${tasks}" varStatus="i">
			         <option value="${obj.id}">${obj.description}</option>
			    </c:forEach>
			</select>
			<label style="color:red;">如果任务未显示，可能是你已经点击了完成，直接上传即可</label>
		</div>  --%>
		<div class="btns text-right">
			<button class="btn" onclick="savaAll()">提交所有上传图片和问题</button>
			<span class="tips" style="color: red;" id="subHtml"></span>
		</div>


		<div class="w-out-box" id="show_upload_dialog" style="display: none;">
			<div class="weui_mask"></div>
			<div class="w-weui_dialog">
				<div id="container">

					<div class="content">
						<h1>上传进度</h1>
					</div>

					<!-- Progress bar -->
					<div id="progress_bar" class="ui-progress-bar ui-container">
						<div class="ui-progress" style="width: 0%;"
							id="ui-progress-upload">
							<span class="ui-label" style="display: none;">正在加载...<b
								class="value">0%</b></span>
						</div>
					</div>
					<!-- /Progress bar -->
					<a class="close-reveal-modal"
						style="color: #fff; font-size: 30px; position: absolute; right: 10px; top: 10px;"
						href="javascript:void(0);" onclick="cancel_upload()">×</a>
					<div class="content" id="main_content" style="display: none;">
						<p>加载完成。</p>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
<script src="/lib/jquery/jquery.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/upload-base.js"></script>
<script type="text/javascript" src="/js/jquery-form.js"></script>
<script src="/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/layer/2.1/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script src="/js/dist/lrz.all.bundle.js"></script>
<script>

//添加材料
	delShow();
	var key= 0;
$('.add_material').click(function(){
	var material_type = $('.material_wrap .material_type:first-child').clone(true);
	$('.material_wrap').append(material_type);
	console.log('1');
	delShow();
	
	$('.material_wrap .material_type').each(function(index,item){
		// var index_val = $('.material_wrap .material_type').index();
		$('.material_wrap .material_type').eq(index).find('.row1').find('input[type="radio"]').attr('name','materialSelection'+index);
		$('.material_wrap .material_type').eq(index).find('.row3').find('input[type="radio"]').attr('name','materialJudgement'+index);
	});
	
});

// 删除材料
$('.material_del').click(function(){
	$(this).parent().parent().parent().remove();
	$('.material_type .material_del').show();
	$('.material_wrap .material_type:first-child').find('.material_del').hide();
	delShow();
	
	$('.material_wrap .material_type').each(function(index,item){
		// var index_val = $('.material_wrap .material_type').index();
		$('.material_wrap .material_type').eq(index).find('.row1').find('input[type="radio"]').attr('name','materialSelection'+index);
		$('.material_wrap .material_type').eq(index).find('.row3').find('input[type="radio"]').attr('name','materialJudgement'+index);
	});
	
});
// 删除按钮显示
function delShow(){
	$('.material_type .material_del').show();
	$('.material_wrap .material_type:first-child').find('.material_del').hide();
};

// 
$('.surfaceTreatment input[type="radio"]').change(function(){
	var this_val = $(this).val();
	if(this_val == '1'){
		$('#result_entry1').show();
		
		
	}else if(this_val == '2'){
		
		$('#result_entry1').hide();
		
	}
});

$('.row4 ').hide();
$('.row1 label').click(function(){
	var data_text = $(this).attr('data-text');
	$(this).parent().parent().siblings('.row4').show();
	if(data_text == '1'){
		$(this).parent().parent().siblings('.row4').find('p').text('请提供相应 药水检验结果');
	}else if(data_text == '3'){
		$(this).parent().parent().siblings('.row4').find('p').text('请提供第3方元素测试结果，如果 有力学性能要求，也请测试');
	}else if(data_text == '4'){
		$(this).parent().parent().siblings('.row4').find('p').text('请调查 原料中的添加剂 是否有回料，玻纤，UV, 阻燃材料 的添加是否合理。 如果 图纸要求ROHS,REACH,是否有第3方测试');
	}else if(data_text == '6'){
		$(this).parent().parent().siblings('.row4').find('p').text('请提供第3方元素测试结果 （除非订单很小）');
	}else if(data_text == '7'){
		$(this).parent().parent().siblings('.row4').find('p').text('请拿到工厂熔炼和工厂自身的化验记录');
	}else{
		$(this).parent().parent().siblings('.row4').hide();
	}
})
$('.add_packing .blue').click(function(){
    $('.add_pack_detail').toggle();
    if($('.add_pack_detail').css('display') == 'none'){
        $('.add_packing i').css('transform','rotate(180deg)');
	}else{
        $('.add_packing i').css('transform','rotate(0deg)');
	};
});

</script>
<script>
	/* //阳工质检工厂检验确认修改
	$('.factory_history label')
			.click(
					function() {
						var name = $(this).find('input').attr('name');
						if ($('input[name="none"]').is(":checked")) {
							$(
									'input[name="sample"],input[name="process"],input[name="feed"]')
									.prop('disabled', true);
						} else {
							$(
									'input[name="sample"],input[name="process"],input[name="feed"]')
									.prop('disabled', false);
						}
						//选了前3个后面一个就不能选了
						if ($('input[name="sample"]').is(":checked")
								|| $('input[name="process"]').is(":checked")
								|| $('input[name="feed"]').is(":checked")) {
							$('input[name="none"]').prop('disabled', true);
						} else {
							$('input[name="none"]').prop('disabled', false);
						}
					}); */
</script>
<script>
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
	$('.table-condensed tbody,.table-condensed tfoot').on('click', function() {
		$('.datetimepicker').hide();
	});
</script>
<script>
	window.onload = window.onresize = function() {

		var clientWidth = document.documentElement.clientWidth;

		document.getElementsByTagName("html")[0].style.fontSize =

		clientWidth / 768 * 40 + "px";
	}
</script>
<script>
	/*添加图片*/
	var $li = '<li><div class="img_in">' + '<img src="" alt="">'
			+ '</div><span class="glyphicon glyphicon-remove"></span>'
			+ '</li>';
	$('.quality .wrap2 .report input').click(
			function() {
				if ($('.quality .last_radio input').is(':checked')
						|| $('.quality .second_radio input').is(':checked')) {
					if ($('.quality .last_radio input').is(':checked')) {
						if ($('#purchase').text()) {
							$('.quality .wrap2 .report .task').show();
						} else {
							alert('该项目没有分配采购和跟单')
							$('#create_form')
									.find('input[name=state][value=0]').prop(
											'checked', true)
						}
						$('.quality .wrap2 .report .explain').hide();
					}
					//有问题,已解决问题说明
					if ($('.quality .second_radio input').is(':checked')) {
						if ($('#purchase').text()) {
							$('.quality .wrap2 .report .explain').show();
						} else {
							alert('该项目没有分配采购和跟单')
							$('#create_form')
									.find('input[name=state][value=0]').prop(
											'checked', true)
						}
						$('.quality .wrap2 .report .task').hide();
					}

				} else {
					$('.quality .wrap2 .report .task').hide();
					$('.quality .wrap2 .report .explain').hide();
				}
			})
</script>
<script>
	function show_drawingNames(obj) {
		var fileNames = $(obj).val();
		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		} else {
			autTime();
			$('#upload_title').children().text("上传进度");
		}

		var projectNo = $('#projectNo').val();
		$(obj).parents('form').find('input[name=projectNo]').val(projectNo);
		// 先上传后获取上传文件路径
		$(obj)
				.parents('form')
				.ajaxSubmit(
						{
							type : "post",
							url : "${ctx}/upload/uploadProductPicAndChangeName.do",
							dataType : "text",
							async : false,
							success : function(str) {
// 								var result = eval('(' + str + ')');
								var result = str;
								if (result.ok) {
									var data = result.data;
									var addhtml = ''
									for (var i = 0; i < data.length; i++) {

										addhtml += '<li class="li_img"><div class="img_in">'
												+ '<a href="'
												+ data[i].replace(
														'/compressImg', '')
												+ '" target="_blank">'
												+ '<img src="'+data[i]+'" alt=""></a>'
												+ '</div><span class="glyphicon glyphicon-remove"></span>'
												+ '</li>';
									}
									$(obj).parents('form').find('.clearfix')
											.prepend(addhtml);
									/*删除当前图片*/
									$('.quality .wrap2 li span').on('click',
											function() {
												$(this).parent().remove();
											})
								}
							},
							error : function() {

							}
						});
	}

	//获取上传质检报告
	function upload_form(obj) {
		var fileNames = $(obj).val();
		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		} else {
			autTime();
			$('#upload_title').children().text("上传进度");
		}
		var projectNo = $('#projectNo').val()
		$(obj).parents('form').find('input[name=projectNo]').val(projectNo)
		// 先上传后获取上传文件路径
		$(obj).parents('form').ajaxSubmit({
			type : "post",
			url : "${ctx}/upload/uploadQualityForm",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
					var inspectionPath = result.data.path;
					var originalFilename = result.data.originalFilename;
					$(obj).next().text(originalFilename);
					$('#inspectionPath').val(inspectionPath);
					$('#inspectionForm').val(originalFilename);
				}
			},
			error : function() {

			}
		});
	}

	function savaAll() {
		var userName = $('#userName').val()
		var roleNo = $('#roleNo').val()
		var userId = $('#userId').val()
		var projectNo = $('#projectNo').val()
		var taskId = $('#task_sel').val();
		var spendTime = $('#spendTime').val();
		/* var materialSelection=$('input:radio[name="materialSelection"]:checked').val();
		
		 //报关单需要客户公司名称
    	if(!materialSelection){
    		alert("材料选择未选");
			return false;
    	}
		 var specificLicensePlate = $('#specificLicensePlate').val();
		if(!specificLicensePlate){
			alert("请填材料具体号牌?");
			return false
		}	
		var materialJudgement=$('input:radio[name="materialJudgement"]:checked').val();
		 //报关单需要客户公司名称
    	if(!materialJudgement){
    		alert("材料判定未选");
			return false;
    	} */
    	if(spendTime>1){
    		alert("检验时间允许范围是0-1天");
			return false;
    	}
		
    	
    	
    	var surfaceTreatment=$('input:radio[name="surfaceTreatment"]:checked').val();
		 //报关单需要客户公司名称
    	if(!surfaceTreatment){
    		alert("表面处理未选");
			return false;
    	}
		 
		 
    	 var filmThickness = $('#filmThickness').val();
    	 var surfaceResultEntry = $('#surfaceResultEntry').val();
 		if(!surfaceResultEntry){
 			alert("请填写观察结果");
 			return false
 		}	
		
		
 		var flag = true;
        var milestoneList = [];
        var tl = $('.material_type').length;
        var projectNo = projectNo;
        var num=0;
		$('.material_type').each(function(){
			var name1="materialSelection"+num;
			var name2="materialJudgement"+num;
			var materialSelection = $(this).find('input:radio[name="'+name1+'"]:checked').val();
			var specificLicensePlate = $(this).find('input[name="specificLicensePlate"]').val();
			var materialJudgement = $(this).find('input:radio[name="'+name2+'"]:checked').val();
			if(!materialSelection || !specificLicensePlate||!materialJudgement){
				alert('材料选择、具体号牌和材料判定不能为空') ; 
				flag = false;
				return fasle;
			}
			var milestone = {"materialSelection":materialSelection,"specificLicensePlate":specificLicensePlate,"materialJudgement":materialJudgement};
			milestoneList.push(milestone);
			num++;
		})
 		
 		
 		
 		
        var boxnumber = '';
        var perQty = '';
        var inventoryQty = '';
        var openQty = '';
       
		var detailUrl = '';
		var badUrl = '';
		var materialUrl = '';
		var packageUrl = '';
		var checkUrl = '';
		var purchaseName = '';
		$('#detail_form').find('a').each(function() {
			if (!detailUrl) {
				detailUrl = $(this).attr('href')
			} else {
				detailUrl = detailUrl + ';' + $(this).attr('href')
			}
		})
		$('#bad_form').find('a').each(function() {
			if (!badUrl) {
				badUrl = $(this).attr('href')
			} else {
				badUrl = badUrl + ';' + $(this).attr('href')
			}
		})
		$('#material_form').find('a').each(function() {
			if (!materialUrl) {
				materialUrl = $(this).attr('href')
			} else {
				materialUrl = materialUrl + ';' + $(this).attr('href')
			}
		})
		$('#package_form').find('a').each(function() {
			if (!packageUrl) {
				packageUrl = $(this).attr('href')
			} else {
				packageUrl = packageUrl + ';' + $(this).attr('href')
			}
		})
		$('#check_form').find('a').each(function() {
			if (!checkUrl) {
				checkUrl = $(this).attr('href')
			} else {
				checkUrl = checkUrl + ';' + $(this).attr('href')
			}
		})

		if (!detailUrl) {
			alert("请上传细节图片")
			return false
		}

		//各项保存的数据
		var param = {};
		$('.wrap2').find('input').each(function() {
			var field = $(this).attr('field');
			if (field) {
				param[field] = $(this).val();
				if(field=='boxNumber'){
					boxnumber= $(this).val();
				}
				if(field=="perQty"){
					perQty=$(this).val();
				}
				if(field=="inventoryQty"){
					inventoryQty=$(this).val();
				}
				if(field=="openQty"){
					openQty=$(this).val();
				}
			}
		})
		//遍历获取图号
		var picNum = '';
		$('.pic_num').each(function(){
			if ($(this).is(":checked")) {
				var pic = $(this).val();
				if(pic == '其他'){
					pic == $(this).next().next().val();
				}
				if(pic){
					picNum +=pic+","; 
				}				
			}
		})
		if(picNum){
			picNum = picNum.substring(0, picNum.length - 1);
			param['picNum'] = picNum;
		}else{
			alert("请选择检验图号");
			return false;
		}
		

		//获取公司和仓库
		var checkPlace = '';
		var factoryFlag = false;
		$('input[name="place"]').each(function() {
			if ($(this).is(":checked")) {
				var placeName = $(this).val();
				if (placeName != '工厂') {
					checkPlace += placeName + ",";
				} else {
					factoryFlag = true;
				}
			}
		})
		//获取工厂
		if (factoryFlag) {
			$('input[name="factory"]').each(function() {
				var factoryName = $(this).val();
				if (factoryName) {
					checkPlace += factoryName + ",";
				}
			})
		}
		if (checkPlace.length > 0) {
			checkPlace = checkPlace.substring(0, checkPlace.length - 1);
		}
		if(!checkPlace){
			alert("请填写检验地点");
			return false;
		}
		 
		/*if(!boxnumber||!perQty||!inventoryQty||!openQty){
			alert("请填写总箱数、每箱数量、数量清点、开箱数量");
		}*/
		// 大货类的检验，保存时如果这里内容没填全，弹窗提醒”包装开箱内容不全”，并不允许保存
        var select_option = $('#type').val();
        if(select_option == '3'){
            var boxNumber  = $('#boxNumber').val();
            var perQty  = $('#perQty').val();
            var inventoryQty = $('#inventoryQty').val();
            var openQty = $('#openQty').val();
            if(boxNumber == '' || inventoryQty =='' || inventoryQty == '' || openQty == ''){
                // $('#add_tc').show();
                alert('包装开箱内容不全')
                return false;
			}
			// else{
             //    $('#add_tc').hide();
			// }
        }

		param["place"] = checkPlace;
		var factoryInspection = '';
		/* //获取工厂检验
		
		$('.factory_history').find('input[type="checkbox"]:checked').each(function() {
			factoryInspection += $(this).val() + ",";
		}) */
		/* if (factoryInspection) {
			factoryInspection = factoryInspection.substring(0,
					factoryInspection.length - 1);
			param['factoryInspection'] = factoryInspection;
		}else{
			alert("请勾选工厂有做过检验情况！");
			return false;
		} */
		//是否所有产品已经完全没有问题
/* 		if ($('input[type="checkbox"][name="isAllRight"]').is(':checked')) {
			param['isAllRight'] = 1;
		} */
	
		var quality_testing = document.getElementsByName("quality_testing");
		var quality_testing1 = "";
		
		var flag1 = true;
		for(var i = 0; i < quality_testing.length;i++){
			if(quality_testing[i].checked){
				flag1 = false;
				quality_testing1 += ","+quality_testing[i].value;
				
			}
		}
		var state = ''
		$('.report').find('input[name=state]').each(function() {
			if ($(this).prop('checked')) {
				state = $(this).val()
			}
		})
		if (state == '2') {
			if ($('#taskDetail').val() == '') {
				alert('请输入任务内容')
				return false
			}
			purchaseName = $('#purchase').text()
		}
		if (state == '1') {
			if ($('#explain').val() == '') {
				alert('请输入任务内容')
				return false
			}
			purchaseName = $('#purchase').text()
		}
		
		
		//工厂完成对应合同信息
		var factoryListStr = '';
		var flag = true;
        var factoryList=[];
        if(state == 0){
        	$('.noIssue').find('.factorys').each(function(){       	
            	var This = $(this);
            	var id = This.attr('filed');        	       	
            	var orderState = '';
            	var endDate = '';
            	var sampleDate = $('#sampleDate').val();
            	var deliveryDate =  $('#deliveryDate').val();
            	var sampleFinishTime = null;
            	var productFinishTime = null;
            	var repairReplenishmentFinishTime = null;
            	if(This.find('input[name="select"]').is(":checked")){
            		orderState = This.find('input[name="select"]:checked').val();
            		endDate = This.find('input[name="select"]:checked').parent().siblings('.s2').find('input').val();
            		if(!endDate){
            			alert('请选择结束日期');
            			flag = false;
            			return false;
            		}
            		if(orderState == 0){
            			sampleFinishTime = endDate;
            		}
            		if(orderState == 1){
            			productFinishTime = endDate;
            		}
            		if(orderState == 2){
            			
            			repairReplenishmentFinishTime = endDate;
            		}
            	}
            	if(!orderState){
            		return;
            	}
            	var factory = {"id":id,"productFinishTime":productFinishTime,
            			"sampleFinishTime":sampleFinishTime,"repairReplenishmentFinishTime":repairReplenishmentFinishTime
            			,"sampleDate":sampleDate,"deliveryDate":deliveryDate,"orderNature":orderState};
            	factoryList.push(factory);
            })
            factoryListStr = JSON.stringify(factoryList);
            
        }else if(state == 1){
        	$('.deal').find('.factorys').each(function(){       	
            	var This = $(this);
            	var id = This.attr('filed');        	       	
            	var orderState = '';
            	var endDate = '';
            	var sampleFinishTime = null;
            	var productFinishTime = null;
            	var repairReplenishmentFinishTime = null;
            	if(This.find('input[name="select"]').is(":checked")){
            		orderState = This.find('input[name="select"]:checked').val();
            		endDate = This.find('input[name="select"]:checked').parent().siblings('.s2').find('input').val();
            		if(!endDate){
            			alert('请选择结束日期');
            			flag = false;
            			return false;
            		}
            		if(orderState == 0){
            			sampleFinishTime = endDate;
            		}
            		if(orderState == 1){
            			productFinishTime = endDate;
            		}
            		if(orderState == 2){
            			repairReplenishmentFinishTime = endDate;
            		}
            	}
            	if(!orderState){
            		return;
            	}
            	var factory = {"id":id,"productFinishTime":productFinishTime,
            			"sampleFinishTime":sampleFinishTime,"repairReplenishmentFinishTime":repairReplenishmentFinishTime};
            	factoryList.push(factory);
            })
            
            factoryListStr = JSON.stringify(factoryList);
        }
        
		
		if(!flag){
			return false;
		}
		
		//获取检验计划完成情况
        var planList = [];
		$('.check_s').each(function(){
			var id = $(this).attr('filed');
			This = $(this);
			if(This.find('input[type="checkbox"]').is(':checked')){
				var isWork = This.find('input[type="checkbox"]:checked').val();
				var plan = {"id":id,"isWork":isWork};
				planList.push(plan);
			}
		})
		//获取检验计划时间
		var inspectionCreateDate = $('#check_body').find('.check:first').attr('filed_date');
		
		
		var taskDetail = $('#taskDetail').val()
		var explain = $("#explain").val();
		var checkExplain = $("#checkExplain").val();
		var packageExplain = $("#packageExplain").val();
		$(".btn").attr("disabled", true).css("background-color", "#999");
		var type = $('#type').val();
		$
				.ajax({
					type : "post",
					url : "${ctx}/quality/saveQuality",
					data : {
						projectNoId:$('#projectNoId').val(),
						projectNo : projectNo,
						userName : userName,
						detailUrl : detailUrl,
						badUrl : badUrl,
						materialUrl : materialUrl,
						packageUrl : packageUrl,
						checkUrl : checkUrl,
						type : type,
						state : state,
						taskDetail : taskDetail,
						explain : explain,
						purchaseName : purchaseName,
						checkExplain : checkExplain,
						packageExplain : packageExplain,
						param : JSON.stringify(param),
						inspectionPath : $('#inspectionPath').val(),
						inspectionForm : $('#inspectionForm').val(),
						taskId : taskId,
						conclusion : $('#conclusion').val(),
						keySizeExceed : 0,
						noCheck : 0,
						//materialSelection: materialSelection, 
						//specificLicensePlate : specificLicensePlate , 
						//materialJudgement:materialJudgement, 
						filmThickness: filmThickness, 
						surfaceTreatment: surfaceTreatment, 
						surfaceResultEntry : surfaceResultEntry , 
						factoryList: factoryListStr, 
						inspectionCreateDate:inspectionCreateDate,
						planList:JSON.stringify(planList) , 
						quality_testing1:quality_testing1,
						milestoneList:JSON.stringify(milestoneList)  
					},
					success : function(json) {
// 						var json = eval("(" + data + ")");
						if (json.ok) {
							window.location.href = '${ctx}/project/showDetails?projectNo='
									+ projectNo
									+ '&roleNo='
									+ roleNo
									+ '&userId='
									+ userId
									+ '&userName='
									+ userName;
						} else {
							$("#subHtml")
									.html(
											'<font class="tips_false">录入失败'
													+ (json.message ? json.message
															: "") + '</font>');
							$(".btn").css("background-color", "#027CFF")
									.removeAttr('disabled');
						}
					},
					error : function() {
						$("#subHtml").html(
								'<font class="tips_false">录入失败'
										+ (json.message ? json.message : "")
										+ '</font>');
						$(".btn").css("background-color", "#027CFF")
								.removeAttr('disabled');
					}

				})
		return false

	}

	function fileChange(that,num) {
		var filepath = $(that).val();
		var projectNo = $('#projectNo').val();
		if (filepath == "") {
			return;
		}
		var extStart = filepath.lastIndexOf(".");
		var ext = filepath.substring(extStart, filepath.length).toUpperCase();
		if (".jpg|.png|.bmp|.jpeg".toUpperCase().indexOf(ext.toUpperCase()) == -1) {
			alert("只允许上传jpg、png、bmp、jpeg格式的图片");
			return false;
		}

		var tl = that.files.length;
		//以图片宽度为800进行压缩 
		var fileName;
		var file;
		for (var i = 0; i < tl; i++) {
			var fileName = that.files[i].name;
			var fileForm = that.files[i];
			var width = fileForm.width;
			var height = fileForm.height;
			synPic(that, fileForm, projectNo, fileName,num);
		}
	}

	function synPic(obj, fileForm, projectNo, fileName,num) {

		var width = 800;//800
		if(num==1){
			width=1600;
		}
		var formId = $(obj).parents('form').attr('id');
		if (formId == 'check_form') {
			width = 1024;//1024
			if(num==1){
				width=2048;
			}
		}

		lrz(fileForm, {
			width : width,
			quality:0.8


		})
				.then(
						function(rst) {

							//压缩后异步上传 
							$
									.ajax({
										url : "/upload/fileUploadPicture",
										type : "POST",
										data : {
											projectNo : projectNo,
											fileName : fileName,
											imgdata : rst.base64
										//压缩后的base值 
										},
										dataType : "json",
										cache : false,
										async : true,
										success : function(json) {
																						
												if (json.ok) {
													var data = json.data;

													if(formId == 'file_upload_id'){
														$('#inspectionPic').append('<li class="li_img"><img src="'+data+'"></li>');													
													}else{
														addhtml = '<li><div class="img_in">'
															+ '<a href="'+data+'" target="_blank">'
															+ '<img src="'+data+'" alt=""></a>'
															+ '</div><span class="glyphicon glyphicon-remove"></span><span class="glyphicon glyphicon-repeat rotate" onclick="rotate(\''
															+ data
															+ '\',this,0)"></span>'
															+ '</li>';
														$(obj).parents('form').find(
																'.clearfix').prepend(
																addhtml);
														/*删除当前图片*/
														$('.quality .wrap2 li .glyphicon-remove')
																.on(
																		'click',
																		function() {
																			$(this)
																					.parent()
																					.remove();
																		})
		
													}
													
												} else {
													alert(json.message);///data.message为上传失败原因 
												}

										},
										error : function() {
											alert("上传失败");
										}
									});
						});
	}

	//旋转当前图片
	function rotate(filePath, obj, num) {
		var deg = num + 90;
		$.ajax({
			url : "/upload/updateQualityImg",
			type : "POST",
			data : {
				filePath : filePath,
				degree : 90
			},
			dataType : "json",
			cache : false,
			async : true,
			success : function(json) {
				$(obj).attr('onclick',
						'rotate(\'' + filePath + '\',this,' + deg + ')');
				$(obj).siblings().find('img').css({
					"transform" : "rotate(" + deg + "deg)"
				});
			}
		})
	}

	//改变检验阶段
	function change_stage(obj) {
		if ($(obj).val() == 0) {
			$('.project-stage').text('样品生产阶段');
			$('#stage_div').show();
		} else if ($(obj).val() == 3) {
			$('.project-stage').text('大货生产阶段');
			$('#stage_div').show();
		} else {
			$('#stage_div').hide();
		}
	}

	var names = [];
	$(function() {
		var factoryNameList = '${factoryNameList}';
		factoryNameList = factoryNameList.substring(1,
				factoryNameList.length - 1);
		names = factoryNameList.split(",");

		$('input[name="place"]').change(function() {
			if ($(this).val() == '工厂') {
				if ($(this).is(":checked")) {
					$('.position_relative').show();
					$('.add_detail .facotry_td ul').hide();
				} else {
					$('.position_relative').hide();
				}
			}
		})
	})
	

	
	$('input[name="factory"]').bind("focus keyup",function() {

		var key = $(this).val();
		if(!key){
			key = "厂";
		}
		$(this).next().empty();
		for (var i = 0; i < names.length; i++) {
			if (key) {
				if (names[i].indexOf(key) != -1) {
					$(this).next().append('<li>' + names[i] + '</li>');
				}
			}
		}
		$(this).next('ul').show();
		$(this).next().find('li').show();

		//选中关键词  		 
		$('.facotry_td li').click(function(e) {
			var li_val = $(this).text();

			$('.facotry_td ul li,.facotry_td ul').hide();
			$(this).parent().parent().find('input').val(li_val).css({
				'padding-left' : '0'
			});

			$(document).on("click", function() {
				$('.facotry_td li,.facotry_td ul').hide();
			});
			e.stopPropagation();
		});
	})
	// 选中显示日期，否则不显示选日期
	$('.add_detail .facotry_list .s2').hide();
	$('.facotry_list .s1 input[type="checkbox"]').each(function(){
		if($(this).is(":checked")){
			$(this).parent().siblings('.s2').show();
		}
	})
	
	
	
	$('.facotry_list .s1 input[type="checkbox"]').click(function(){
		var check = $(this).prop('checked');
		if(check == true){
			$(this).parent().siblings('.s2').show();
			$(this).parent().parent().siblings().find('input[name="select"]').attr('checked',false);
			$(this).parent().parent().siblings().find('.s2').hide();
		}else if(check == false){
			$(this).parent().siblings('.s2').hide();
		}		
	})
	
	
	
	
	//显示对应工厂完结勾选
	$('input[name="state"]').change(function(){
		if($(this).val() == 0){
			$('.noIssue').show();
			$('.deal').hide();
		}else if($(this).val() == 1){
			$('.deal').show();
			$('.noIssue').hide();
		}else{
			$('.deal').hide();
			$('.noIssue').hide();
		}
	})
	
	
	//选择计划是否完成，批量
	function selectWorkOnly(obj,index){
		if($(obj).is(":checked")){
			$(obj).parent().siblings().find('input[type="checkbox"]').removeAttr('checked');
		}else{
			if($(obj).val() == 0){
				$('#check_body').find('.check_'+index+' input[type="checkbox"][value="0"]').removeAttr('checked');
			}
			if($(obj).val() == 1){
				$('#check_body').find('.check_'+index+' input[type="checkbox"][value="1"]').removeAttr('checked');
			}			
		}
		checkboxSelect(index);
	}
	
	
	//选择计划是否完成，批量
	function selectWork(obj,index){
		if($(obj).is(":checked")){
			$(obj).parent().siblings().find('input[type="checkbox"]').removeAttr('checked');
			if($(obj).val()==0){
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="no_work"]').prop('checked','true'); 
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="work"]').removeAttr('checked'); 
			}
			if($(obj).val()==1){
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="work"]').prop('checked','true'); 
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="no_work"]').removeAttr('checked'); 
			}
		}else{
			if($(obj).val()==0){
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="no_work"]').removeAttr('checked');
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="work"]').removeAttr('checked'); 
			}
			if($(obj).val()==1){
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="work"]').removeAttr('checked'); 
				$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="no_work"]').removeAttr('checked'); 
			}
		}		
	}
	
	
	function checkboxSelect(index){
		//用于判断是否全选
		var c_work_flag = true;
		var c_no_work_flag = true;
		$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="work"]').each(function(){
			if(!$(this).is(":checked")){
				c_work_flag = false;
			}
		})
		$('#check_body').find('.check_index_'+index+' input[type="checkbox"][name="no_work"]').each(function(){
			if(!$(this).is(":checked")){
				c_no_work_flag = false;
			}
		})
		//判断是否全选
		if(c_work_flag){
			$('#check_body').find('.check_'+index+' input[type="checkbox"][value="1"]').prop('checked','true'); 
		}
		if(c_no_work_flag){
			$('#check_body').find('.check_'+index+' input[type="checkbox"][value="0"]').prop('checked','true'); 
		}
	}
	/*修改项目状态及完成时间  */
	function updateAll(taskId,drawingId){
		var userName = $('#userName').val();
		  $.ajax({
				url : "/projectTask/updateAll",
				type : "POST",
				data : {
					"taskId":taskId,
					"drawingId" : drawingId,
					"userName":userName
				},
				dataType : "json",
				success : function(json) {
					if(json.ok){
						$('#updateall').text(json.message);
						$('#selectTask').hide();
						
					}else{
						alert(json.message);
					}
				}
			})	
		
	}
	/*修改项目状态及完成时间  */
	function modifyState(finished,remark,projectNo,id,obj){
		var sure="";
		if(finished==0){
			var voide="";
			if(remark==0){
				voide="确认点否？ 将清空该合同完成日期";
			}else if(remark==1){
				voide="确认已完成？完成日期将自动设置为今天";
			}
			var sure=confirm(voide);
		} else{
			sure=true;
		}
		if(sure){
			$.ajax({
				url : "/project/modifyState",
				type : "POST",
				data : {
					"finished":finished,
					"rework" : remark,
					"projectNo" : projectNo,
					"id":id
				},
				dataType : "json",
				success : function(json) {
					if(json.ok){
						
					}else{
						alert(json.message);
					}
				}
			})	
		}else{
			// 点击弹窗 取消按钮								
			var sibling_check = $(obj).parent().siblings('label').find('input').val();
			if(sibling_check == '是'){
				
				$(obj).removeAttr('checked');
				$(obj).parent().siblings('label').find('input').prop('checked', true);
				
				
			}else if(sibling_check == '否'){
				$(obj).removeAttr('checked');
				$(obj).parent().siblings('label').find('input').prop('checked', true);
				
			}			
		}
	}
	
	//展开
	function openInspection(obj,index){
		if($(obj).text() == '收起'){
			$(obj).text('请展开选择');
			$(obj).parents('tbody').find('.check_index_'+index+'').hide();		
		}else{
			$(obj).text('收起');
			$(obj).parents('tbody').find('.check_index_'+index+'').show();		
		}		
	}
	
	
	
	$(function(){
		var tl = $('#check_body').find('.check').length;
		for(var i=0;i<tl;i++){
			checkboxSelect(i);
		}
	})
	
	//选择图号事件
	function select_img(obj){			
	    $('.check').each(function(){
   		     if($(obj).val() == $(this).find('span:first').text()){
   		    	 if($(obj).is(":checked")){
   		    		 $(this).show();
   		    	 }else{
   		    		 $(this).hide();
   		    	 }
   		     }
   	    })	    
	}
	
	//打开上传质检证明框
	function openInspectionImg(id,index){
		$('#inspectionPlanId').val(id);
		$('#inspectionPlan_li_index').val(index);
		$('.add_tc').show();
	}
	
	
	//关闭录入质检证明
	function cancelInspection(obj){
		$(obj).parents('.add_tc').find("ul li:not(.i-upload)").remove();
		$('#content').val('');
		$(obj).parents('.add_tc').hide();		
	}
	
	//保存检验说明
	function addInspection(obj){
		var id = $('#inspectionPlanId').val();
		var index = $('#inspectionPlan_li_index').val();
		if(!id){
			alert("未获取到id");
			return false;
		}
		//获取图片
		var images='';
		var tl = $('.add_tc ul li:not(.i-upload)').length;
		if(tl>3){
			alert("最多上传3张");
			return false;
		}
		$('.add_tc ul li:not(.i-upload)').each(function(){
			var img = $(this).find('img').attr('src');
			if(img){
				images+=img+",";
			}
		})
		//查询是否选中包装图
		if($('.p_check').is(":checked")){
			$('#package_form').find('a').each(function() {
				if (packageUrl) {
					images+= $(this).attr('href')+",";
				} 
			})
		}
		//查询是否选中材料图
		if($('.m_check').is(":checked")){
			$('#material_form').find('a').each(function() {
				if (packageUrl) {
					images+= $(this).attr('href')+",";
				} 
			})
		}		
		
		if(images){
			images = images.substring(0,images.length - 1);
		}
		//获取说明文字
		var content = $('#content').val();
        if(!images && !content){
        	alert("请提供证明");
        	return false;
        }		
		
		$.ajax({
			url : "/quality/updateInspectionPlan",
			type : "POST",
			data : {
				id:id,
				images : images,
				content : content
			},
			dataType : "json",
			success : function(json) {
				if(json.ok){
					cancelInspection(obj);
					$('.check_s').eq(index).find('button').css('color','green');
				}else{
					alert(json.message);
				}
			}
		})	
	}
	
	
	
</script>



















