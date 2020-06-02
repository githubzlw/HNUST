<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>客户投诉列表-新版</title>
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="add.css">
<style>

.pic_up label{font-weight:normal;margin-right:10px;}
.pic_up input{margin-right:5px;}
.pic_up span,.company_type span{margin-right:10px;font-size:16px;}
.company_type{margin-right:10px;}
.add_graed label{font-size:16px;font-weight:normal;margin-right:10px;}
ul{position:relative;}
.spe_li{position:absolute;top:0;right:0;}

 @media screen and (min-width:768px){
	.customer_complaint_B  .scope  .form-control1{
		width:350px;
	}
	.customer_complaint_h1 .btns{right:20px;}		
}
@media screen and (max-width: 768px){
	.customer_complaint_h1 .btns{width: 165px;text-align:right;}
	.customer_complaint_h1 .btns a:first-child{margin-bottom:5px;}
	.customer_complaint_B .person_select{margin-top:10px;}
	.customer_complaint_B .person_select .form-control{margin-bottom:10px;}
	.customer_complaint_B .scope .form-control{width:100%;}
	.customer_complaint_B .search input{width:80%;}
	.complaint_list li,.complaint_list span,.complaint_list b{word-break:break-all;}	
	.customer_complaint_B .br{display:block;word-break:keep-all;width:100%;line-height: 5px;}
} 

</style>
</head>
<body>
<div class="customer_complaint_B">	
	<h1 class="customer_complaint_h1">
		质量投诉列表
		<div class="btns">
			<a class="select_blank btn mt5" target="_blank" href="http://117.144.21.74:10010/user/toIndex">返回功能选择页</a>	
			<a href="http://117.144.21.74:10010/jsp/customer_complaint_entry.jsp?roleNo=${roleNo}&userId=${userId}&userName=${userName}" class="btn last">电子质量投诉跟踪单录入</a>				
		</div>
	</h1>
	<div class="form-group scope">
		<label class="f16 mr10">展示范围</label>
		<select class="form-control display_inline_block" id="solve">
			<option value="1" <c:if test="${solve eq '1'}">selected</c:if>>全部</option>
			<option value="0" <c:if test="${solve eq '0'}">selected</c:if>>未解决</option>
			<option value="2" <c:if test="${solve eq '2'}">selected</c:if>>已解决,未验证</option>
			<option value="3" <c:if test="${solve eq '3'}">selected</c:if>>已解决，已验证</option>
		</select>
	</div>
	<div class="form-group scope person_select clearfix">
		<label class="f16 mr10 pull-left">人员筛选</label>
		
			<select class="form-control form-control1 display_inline_block" id="documentaryScreening">
				<option value="-1" <c:if test="${documentaryScreening eq '-1'}">selected</c:if>>全部跟单</option>
				<c:forEach var="cus" items="${documentaryUser}" varStatus="i">
				<option value="${cus.userName }" <c:if test="${cus.userName==documentaryScreening}">selected</c:if>>${cus.userName }</option>
				
				</c:forEach>
			</select>
		
		
			<select class="form-control form-control1 display_inline_block" id="purchasingScreening">
				<option value="-1" <c:if test="${purchasingScreening eq '-1'}">selected</c:if>>全部采购</option>
				<c:forEach var="cus" items="${saleUser}" varStatus="i">
				<option value="${cus.userName }" <c:if test="${cus.userName==purchasingScreening}">selected</c:if>>${cus.userName }</option>
				</c:forEach>
			</select>
		
		
	</div>
	<div class="form-group search mt10">
		<input type="text" class="form-control pull-left" placeholder="请输入项目号或项目名" id="input_key" value="${inputKey}">
		
		<button class="btn btn-default" onclick="searchProjectData(1)">查询</button>
	</div>
	<div class="row">
		<div class="col-sm-6 col-xs-12 form-group mt10 positon_relative clearfix">
				<span class="pull-left company_type"> 投诉类型  </span>
				<div class="pull-left grade add_graed">
					<label><input type="radio" <c:if test="${seriousLevelStr==-1 }">checked</c:if> name="seriousLevel"  value="-1"><span>全部</span></label>
					<label><input type="radio" <c:if test="${seriousLevelStr==0 }">checked</c:if> name="seriousLevel" value="0"><span>其他</span></label>
					<label><input type="radio" <c:if test="${seriousLevelStr==5 }">checked</c:if> name="seriousLevel" value="5"><span>改进</span></label>
				</div>
		</div>
		<div class="col-sm-6 col-xs-12 pic_up clearfix">
			<span class="pull-left"> 是否需求更新图纸 </span>
			<div class="pull-left">
				<label><input type="radio" name="pic_up" value="全部" checked><span>全部</span></label>
				<label><input type="radio" name="pic_up" value="否" ><span>否</span></label>
				<label><input type="radio" name="pic_up" value="是"><span>是</span></label>
			</div>
		</div>
	</div>
<!-- 	<p class="num_p">投诉订单编号：<span>XXXXXX</span></p> -->
<form id="form4" action="/complaint/queryList" method="post" onsubmit="return false;">	
	<table class="table table-bordered complaint_list">
		<tbody>
		<c:forEach var="obj" items="${complaintList}" varStatus="i">
			<tr onclick="window.location='https://www.kuaizhizao.cn/complaint/queryComplaint?id=${obj.id}'">
				<td>
					<ul>
						<li>
							<span><b>#<fmt:formatNumber value="${obj.id}" pattern="0000"/></b></span>
						</li>
						<li>
							<span><b>${obj.customerName}</b></span>
						</li>
						<li>
							<span><b>${obj.projectNo}</b></span>
						</li>
						<li>
							<span><b>${obj.projectName}</b></span>
						</li>								
						<li>
							<span><b><fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd"/></b></span>
						</li>
						<li>
							<span><b>${obj.process==null?'':obj.process}</b></span>
						</li>
						<li class="spe_li">
							<b><c:if test="${obj.picUp!=0 }">
							<c:if test="${obj.verification!=0 }"><span class="blue">图纸要更新,已验证</span></c:if>
							<c:if test="${obj.verification==0 }"><span class="red">图纸要更新,未验证</span></c:if>
							</c:if></b>
						</li>
						<li class="br"><br/></li>													
						<li>
						  <c:choose>
								<c:when test="${obj.purchaseConfirmDate != null && obj.purchaseConfirmDate != ''}">
									采购回复完成日期：<span class="blue"><fmt:formatDate value="${obj.purchaseConfirmDate}" pattern="yyyy-MM-dd"/></span>
								</c:when>
							    <c:otherwise>
							        ${obj.purchaseName} <span class="red"> 未回复</span>
							    </c:otherwise>
						  </c:choose>	
						</li>
						<c:if test="${obj.seriousLevel!=5 }"><li>
						   <c:choose>
								<c:when test="${obj.zhijianReplyTime != null && obj.zhijianReplyTime != ''}">
							                  质检回复完成日期：<span class="blue"><fmt:formatDate value="${obj.zhijianReplyTime}" pattern="yyyy-MM-dd"/></span>
							    </c:when>
							    <c:otherwise>
							    ${obj.masterQualityInspection==null?'':obj.masterQualityInspection}
							     ${obj.zhijian1==null?'':obj.zhijian1} ${obj.zhijian2} ${obj.zhijian3}
							        ${obj.qualityInspector1} ${obj.qualityInspector2} ${obj.qualityInspector3}
							        ${obj.qualityInspector4} ${obj.qualityInspector5} ${obj.qualityInspector6}
							        ${obj.qualityInspector7}
							        <span class="red"> 未回复</span>
							    </c:otherwise>
							</c:choose>	
						</li></c:if>
						<li>
						   <c:choose>
								<c:when test="${obj.technicianReplyTime != null && obj.technicianReplyTime != ''}">
							         技术回复完成日期：<span class="blue"><fmt:formatDate value="${obj.technicianReplyTime}" pattern="yyyy-MM-dd"/></span>
							     </c:when>
							     <c:otherwise>
							             技术回复完成日期：<span class="red">还没有</span>
							     </c:otherwise>
							</c:choose>
						</li>
						<li>
							 <c:choose>
								 <c:when test="${obj.completeTime != null && obj.completeTime != ''}">
							             最终整改完成日期：<span class="blue"><fmt:formatDate value="${obj.completeTime}" pattern="yyyy-MM-dd"/></span>
							     </c:when>
							     <c:otherwise>
							             最终整改完成日期：<span class="red">还没有</span>
							     </c:otherwise>
							</c:choose>
						</li>
						<c:if test="${obj.seriousLevel!=5 }"><li>
							 <c:if test="${obj.inspectionLeaderConfirmDate != null && obj.inspectionLeaderConfirmDate != ''&&obj.inspectionLeaderConfirm == 1}">
								  质检总监确认：<span class="blue">确认签名,已验证<fmt:formatDate value="${obj.inspectionLeaderConfirmDate}" pattern="yyyy-MM-dd"/></span>
							     </c:if>
							     <c:if test="${obj.inspectionLeaderConfirmDate != null && obj.inspectionLeaderConfirmDate != ''&&obj.inspectionLeaderConfirm == 0}">
								 质检总监确认：<span class="yellow">确认签名,未验证<fmt:formatDate value="${obj.inspectionLeaderConfirmDate}" pattern="yyyy-MM-dd"/></span>
							    </c:if>
							     <c:if test="${obj.inspectionLeaderConfirmDate == null &&obj.inspectionLeaderConfirm == 0}">
								 质检总监确认：<span class="red">还没有</span>
							     </c:if>
						</li></c:if>
					</ul>
				</td>
			</tr>		
	  </c:forEach>	
		</tbody>
	</table>
	<input type="hidden" id="documentaryScreeningStr" name="documentaryScreening"> 
	<input type="hidden" id="seriousLevelStr" name="seriousLevelStr"> 
	<input type="hidden" id="purchasingScreeningStr" name="purchasingScreening"> 
	<input type="hidden" id="solveStr" name="solve"> 
	<input type="hidden" id="inputKey" name="inputKey" value=""> 
	<input type="hidden" id="pageStr" name="pageStr" value="${page == null ? 1 : page}"> 
	<input type="hidden" id="countPage" name="countPage" value="${lastNum}">
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize == null ? 100 : pageSize}">
	<input type="hidden" value="${userName}" name="userName" id="userName">
    <input type="hidden" value="${userId}" name="userId" id="userId"> 
    <input type="hidden" value="${roleNo}" name="roleNo" id="roleNo">	
    </form>
	<div class="page-box">
	          总数:<span style="color: red">${count}</span>
		当前页/总页:<span style="color: red" id="pageNumberSpan">${page}</span>/
		<span id="countPageSpan" style="color: red">${lastNum}</span>&nbsp; 		
		<a class="emanagergetpagea first-padding" onclick="searchProjectData(1)" title="第一页">首页</a> 
		<a class="emanagergetpagea first-padding" <c:if test="${page > 1}">onclick="searchProjectData(2)"</c:if> title="上一页(第1页)">上页</a>
		<a class="emanagergetpagea" <c:if test="${page < lastNum}">onclick="searchProjectData(3)"</c:if> title="下一页(第3页)">下页</a> 
		<a class="emanagergetpagea last_page" onclick="searchProjectData(4)" title="最后一页">尾页</a>
		<!-- 跳转到第<input type="text" class="n" value="n">页 -->
	</div>
</div>	
</body>
</html>
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
<script>
// 相关项目号搜索筛选功能
/*  $.expr[':'].contains = function(a, i, m){
        return $(a).text().toUpperCase().indexOf(m[3].toUpperCase()) >= 0;

};
$('.project_no').keyup(function(){
	$('.div_transparent').show();
	$(this).siblings('ul').show();
	var key = $(this).val();	
	$('.project_no_list li').each(function(){
		$(".project_no_list li:not(:contains("+ key +"))").css({'display':'none'});
	});
	if(key == ""){
		$('.project_no_list li').show();
	}				
});
$('.project_no_list li').click(function(){	
	$('.project_no_list,.div_transparent').hide();
	var li_val = $(this).text();
	$('.project_no').val(li_val);
});
$('.div_transparent').click(function(){
	$('.project_no_list,.div_transparent').hide();
}) */
</script>
<script>

//退出功能
function exitlogin() {
// 	$.cookie('name', '', {
// 		path : '/'
// 	});
// 	$.cookie('pass', '', {
// 		path : '/'
// 	});
	window.location.href = "${ctx}/index.jsp";
}



//查询
function searchProjectData(obj){
	var pageNumber = $("#pageStr").val();
	var countPage = $("#countPage").val();
	var roleNo = $("#roleNo").val();
	var userId = $("#userId").val();
	var userName = $("#userName").val();
	var pageSize=$("#pageSize").val();
    var projectNo=$("#projectNo").val();
    var solve = $('#solve').val();
    var documentaryScreening = $('#documentaryScreening').val();
    var purchasingScreening = $('#purchasingScreening').val();
    var seriousLevel = $("input[name='seriousLevel']:checked").val();
    
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
	var inputKey = $("#input_key").val();//关键字查询
	$('#planHtml').html('')
	$('#pageStr').val(pageNumber)
	$('#pageNumberSpan').text("")
    $('#countPage').val("")
    $('#countPageSpan').text("")
	$('#inputKey').val(inputKey);
	$('#solveStr').val(solve);
	$('#documentaryScreeningStr').val(documentaryScreening);
	$('#purchasingScreeningStr').val(purchasingScreening);
	$('#seriousLevelStr').val(seriousLevel);
	
	
	$('#form4').removeAttr('onsubmit');
	$('#form4').submit();   
}

//enter事件
document.onkeydown = function (e) {
    if (!e) e = window.event;
    if ((e.keyCode || e.which) == 13) {
    	searchProjectData(1);
    }
}
</script>







