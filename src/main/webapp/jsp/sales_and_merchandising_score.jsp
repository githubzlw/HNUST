<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head lang="en">
<meta charset="UTF-8">
<title>销售、跟单评分</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="../css/add.css">
<style>

.button_style{
width:30px;
}
span.ba input {
    background-color: #fff;
    border: 1px solid #999;
}
.w80{
width:80px;}
.w60{
width:60px;}
.w1250{width:1460px !important;}
.w70{
width:70px;}
</style>
	
</head>

<body>
	<div class="statistic w1250">
		<div class="top clearfix">
			<div class="pull-left">
				<h3 class="pull-left">销售、跟单评分</h3>

				
			</div>
			<div class="pull-right mt-18">
				<button class="blue2 btn" onclick="window.location='${ctx}/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}'">返回主页</button>
				<button class="blue2 btn" onclick="window.location = '${ctx}/index.jsp'">退出系统</button>
				<img src="../img/logo.png" alt="">
			</div>			
		</div>
		
		<input type="text" readonly class="Wdate" id="time1"
								name="time1" value="${date }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
		<button class="blue1 btn" onclick="selectTime()">查询</button>
 <form id="form" class="roleform form-horizontal"  method="get" action="/statistics/selectAllByDate" onclick="return false;"> 
		<input type="hidden" name="dateType" id="dateType">
		
		
		<div class="wrap">
			<h4>销售统计</h4>
			<table class="table table-bordered table1">
				<thead>
					<tr>
						<th class="w80">销售人员</th>
						<th class="w60">总分(100分)</th>
					    <th class="w100">老客户报价成功率(20分)</th>
						<th class="w100">新客户大项目报价成功率(20分)</th>
						<th class="w100">老客户大项目报价成功率(20分)</th>
						<th class="w60">排名(20分)</th>
						<th class="w60">增长(20分)</th>
						
					</tr>
				</thead>
				<tbody>
				<c:forEach var="obj" items="${allScoreList}" varStatus="i">
				   <c:if test="${obj.roleName == '销售'}">
						<tr>
							<td>${obj.saleName}</td>
							<td><fmt:formatNumber value="${obj.totalScore}" pattern="0"/></td>
							<td><fmt:formatNumber value="${obj.oldCustomerOff}" pattern="0"/></td>
							<td><fmt:formatNumber value="${obj.newCustomerBigProjectOffer}" pattern="0"/></td>
							<td><fmt:formatNumber value="${obj.oldCustomerBigProjectOffer}" pattern="0"/></td>
							<td><fmt:formatNumber value="${obj.ranking}" pattern="0"/></td>
							<td><fmt:formatNumber value="${obj.increase}" pattern="0"/></td>
							
						</tr>
					</c:if>
				</c:forEach>	
				</tbody>
			</table>
		</div>
		<div class="wrap">
			<h4>跟单统计</h4>
			<table class="table table-bordered table1">
				<thead>
					<tr>
						<th class="w70">跟单人员</th>
						<th class="w60">总分(100分)</th>
						
						<th class="w60">流程(5分)</th>
						<th class="w80">质量打分(25分)</th>
						<th class="w80">交期打分(25分)</th>
						<th class="w80">客户跟进(25分)</th>
						<th class="w80">利润(20分)</th>
						<c:if test="${roleNo==100 }"><th class="w80">流程扣分次数调整</th></c:if>
						
					</tr>
				</thead>
				<tbody>
				<c:forEach var="obj" items="${allScoreList}" varStatus="i">
				   <c:if test="${obj.roleName == '跟单'}">
					<tr>
						<td>${obj.saleName}</td>
						<td><fmt:formatNumber value="${obj.totalScore}" pattern="0"/></td>
						<td><fmt:formatNumber value="${obj.technologicalProcess}" pattern="0"/></td>
						<td><fmt:formatNumber value="${obj.quality}" pattern="0"/></td>
						<td><fmt:formatNumber value="${obj.deliveryTime}" pattern="0"/></td>
						<td><fmt:formatNumber value="${obj.customerFollowUp}" pattern="0"/></td>
						<td><fmt:formatNumber value="${obj.ranking+obj.increase}" pattern="0"/></td>
						<c:if test="${roleNo==100 }">
						<td style="text-align: center;"><c:if test="${obj.number>0}"><span class="ba"><input type="button" value="-" onclick="updateDeDuction('${obj.saleName}','${obj.date }',-1,${obj.number});" class="button_style"></span></c:if>
						<span class="ba">${obj.number}</span>
						<span class="ba">
						<input  type="button" value="+" onclick="updateDeDuction('${obj.saleName}','${obj.date }',1,${obj.number});" class="button_style"></span>
						</td></c:if>
					</tr>
					</c:if>
				  </c:forEach>	
				</tbody>
			</table>
		</div>
	  </form>
	</div>
</body>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script  type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jquery-form.js"></script>	
</html>
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
	$('.table-condensed tbody,.table-condensed tfoot').on('click',function(){
		$('.datetimepicker').hide();
	})
	
	
	
	
	function selectTime(){
		var time1 = $("#time1").val();
		 window.location='${ctx}/salesAndMerchandisingScoreController/salesAndMerchandisingScore?date='+time1;
	}
	function updateDeDuction(saleName,date,num,number){
		$.ajax({
			type : "post",
			data : {
				saleName : saleName,
				date : date,
				num : num,
				number : number
			},
			url : "${ctx}/deduction/updateDeDuction",
			success : function(data) {
				
					window.location.href=window.location.href;
				
			}
		})
	}
	
	
	
</script>




















