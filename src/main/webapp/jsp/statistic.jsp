<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head lang="en">
<meta charset="UTF-8">
<title>统计页</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="../css/add.css">

<style>.wrap7 table{width:80%;}</style>

</head>
<body>
	<div class="statistic w1250">
		<div class="top clearfix">
			<div class="pull-left">
				<h3 class="pull-left">统计页</h3>
				
				<button class="blue1 btn" onclick="selectWeek(1)">查询一周</button>
				<button class="blue1 btn" onclick="selectWeek(2)">查询一个月</button>
			</div>
			<div class="pull-right mt-18">
				<button class="blue2 btn"
					onclick="window.location='${ctx}/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}'">返回主页</button>
				<button class="blue2 btn"
					onclick="window.location = '${ctx}/index.jsp'">退出系统</button>
				<img src="../img/logo.png" alt="">
			</div>
		</div>
		<form id="form" class="roleform form-horizontal" method="get"
			action="/statistics/selectAllByDate" onclick="return false;">
			<input type="hidden" name="dateType" id="dateType">
			<div class="wrap wrap1">
				<h4>
					一、启动项目数量<span>${totalCount}</span>，总金额<span><fmt:formatNumber value="${totalAmount}" type="number"  groupingUsed="false"  maxFractionDigits="2"/>
						（万美元）</span>
				</h4>
				<p>
					启动最多的销售是<span>${saleName}</span>(${saleNewNum}新客户${saleOldNum}老客户)，
				</p>
				<p>
					启动最多的跟单是<span>${followName}</span>(${followNewNum}新客户${followOldNum}老客户)，
				</p>
				<p>
					启动最多的采购是<span>${purcharName}</span>(${purchaseNewNum}新客户${purchaseOldNum}老客户)。
				</p>
			</div>
			<div class="wrap wrap2">
				<h4>
					二、报价发出<span>${num}</span>个项目，预计总金额是<span><fmt:formatNumber value="${total}" type="number"  groupingUsed="false"  maxFractionDigits="2"/></span>
					(万美元)，报价发出平均时间是<span>${time}</span>小时
				</h4>
				<p>
					报价最多的销售是<span>${sale}</span>
				</p>
				<p>
					报价最多的报价工程师是<span>${quoter}</span>
				</p>
				<p>
					报价最多的助理是<span>${quotationAssistant}</span>.
				</p>
			</div>
			<div class="wrap wrap3">
				<h4>三、采购统计</h4>
				<table class="table table-bordered table1">
					<thead>
						<tr>
							<th>采购人员</th>
							<th class="w100">生产项目总数</th>
							<th class="w100">返单项目数</th>
							<th class="w100">返单总金额（万美元）</th>
							<th class="w100">新项目数量</th>
							<th class="w100">新项目总金额（万美元）</th>
							<th class="w100">到账一个月的项目数量</th>
							<th class="w100">到账二个月的项目数量</th>
							<th class="w100">到账三个月的项目数量</th>
							<th class="w100">到账三个月以上的项目数量</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="obj" items="${emailUsers}" varStatus="i">
							<c:if test="${obj.roleNo == 6}">
								<tr>
									<td>${obj.userName}</td>
									<td>${obj.personData.processNum}</td>
									<td>${obj.personData.reorderNum}</td>
									<td>${obj.personData.totalAmount}</td>
									<td>${obj.personData.newNum}</td>
									<td>${obj.personData.totalNewAmount}</td>
									<td>${obj.personData.oneMonthCount}</td>
									<td>${obj.personData.twoMonthCount}</td>
									<td>${obj.personData.threeMonthCount}</td>
									<td>${obj.personData.upThreeMounthCount}</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="wrap wrap4">
				<h4>四、跟单统计</h4>
				<table class="table table-bordered table1">
					<thead>
						<tr>
							<th>跟单人员</th>
							<th class="w100">生产项目总数</th>
							<th class="w100">返单项目数</th>
							<th class="w100">返单总金额（万美元）</th>
							<th class="w100">新项目数量</th>
							<th class="w100">新项目总金额（万美元）</th>
							<th class="w100">到账一个月的项目数量</th>
							<th class="w100">到账二个月的项目数量</th>
							<th class="w100">到账三个月的项目数量</th>
							<th class="w100">到账三个月以上的项目数量</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="obj" items="${emailUsers}" varStatus="i">
							<c:if test="${obj.roleNo == 5 && obj.job == '跟单'}">
								<tr>
									<td>${obj.userName}</td>
									<td>${obj.personData.processNum}</td>
									<td>${obj.personData.reorderNum}</td>
									<td>${obj.personData.totalAmount}</td>
									<td>${obj.personData.newNum}</td>
									<td><fmt:formatNumber value="${obj.personData.totalNewAmount}" type="number"  groupingUsed="false"  maxFractionDigits="2"/></td>
									<td>${obj.personData.oneMonthCount}</td>
									<td>${obj.personData.twoMonthCount}</td>
									<td>${obj.personData.threeMonthCount}</td>
									<td>${obj.personData.upThreeMounthCount}</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</form>
			<div class="wrap wrap10" id="wrap10">
				<h4>五、质检上传报告统计</h4>
				<div class="search">
					<div>
						<button class="blue2  btn btn-default wrap10_search" type="button" onclick="customerStatistics(6);">搜索</button>
					</div>
					
				</div>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>质检人员</th>
							<th>名下在生产的合同金额</th>
							<th>名下在打样的项目总数</th>
							<th>名下在量产的项目总数</th>
							<th>上月工作天数</th>
							<th>本月至今工作天数</th>
							<th>上月上传报告数</th>
							<th>本月上传报告数量</th>
							
						</tr>
					</thead>
					<tbody class="inspection_tabel_body">
					
					</tbody>
				</table>
			</div>
			
			
			<div class="wrap wrap5">
				<h4>六、客户统计</h4>
				<div class="search">
					<div class="form-group ">						
						<div class="col-sm-3 ">
							<div class="input-group date form_date col-sm-6" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="customerStartTime" name="customerStartTime"
									class="form-control brt brt_7" size="16" type="text"  value=""
									place="起始日期" field="起始日期" placeholder="起始日期" readonly
									requiredate > <span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span></span>
						</div>
						<div class="col-sm-3 ">
							<div class="input-group date form_date col-sm-6" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="customerEndTime" name="customerEndTime"
									class="form-control brt brt_7" size="16" type="text"  value=""
									place="截止日期" field="截止日期" placeholder="截止日期" readonly
									requiredate > <span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span></span>
						</div>
						<button class="blue2  btn btn-default" type="button" onclick="customerStatistics(1);">搜索</button>
					</div>
					
				</div>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>全部</th>
							<th>新客户打样数目</th>
							<th>预计金额</th>
							<th>新客户大货单数目</th>
							<th>金额</th>
							<th>客户打样数目</th>
							<th>预计金额</th>
							<th>客户大货单数目</th>
							<th>金额</th>
							<th>翻单项目数</th>
							<th>金额</th>
						</tr>
					</thead>
					<tbody class="dp_tabel_body">
					
					</tbody>
				</table>
			</div>
			<div class="wrap wrap6">
				<h4>七、    最近半年还没有转到出货阶段的项目</h4>
				<div class="search row">
					<div class="col-xs-2">
						<select class="form-control col-xs-4" name="purchaseUser" id="purchaseUser">
						<option>所有采购</option>
						<c:forEach var="obj" items="${purchaseUsers}" varStatus="i">
							<option>${obj.userName }</option>
							
						</c:forEach>	
						</select>
					</div>
					<div class="col-xs-2">
						<select class="form-control" name="saleUser" id="saleUser">
							<option>所有销售/跟单</option>
							<c:forEach var="obj" items="${saleUsers}" varStatus="i">
							<option>${obj.userName }</option>
							</c:forEach>
						</select>
					</div>
					
					<button class="blue2 btn btn-default" onclick="customerStatistics(2);">查询</button>
				</div>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th></th>
							<th>项目号</th>
							<th>采购</th>
							<th>跟单</th>
							<th>到款日期</th>
							<th>出货状态</th>
							<th>已进行天数</th>
						</tr>
					</thead>					
					<tbody class="dp_transfer_items">					
					</tbody>
				</table>
			</div>
			<div class="wrap wrap7">
				<h4>八、 项目的平均完成时间</h4>
				<div class="search">
					<div class="form-group ">						
						<div class="col-sm-3 ">
							<div class="input-group date form_date col-sm-6" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="quoteStartDate" name="quoteStartDate"
									class="form-control brt brt_7" size="16" type="text" value=""
									place="选择日期" field="开始日期" placeholder="开始日期" readonly
									requiredate > <span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span></span>
						</div>
						<div class="col-sm-3 ">
							<div class="input-group date form_date col-sm-6" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="quoteEndDate" name="quoteEndDate"
									class="form-control brt brt_7" size="16" type="text" value=""
									place="选择日期" field="截止日期" placeholder="截止日期" readonly
									requiredate > <span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span></span>
						</div>
						<button class="blue2  btn btn-default" type="button" onclick="customerStatistics(3);">搜索</button>
					</div>					
				</div>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>采购/销售</th>
							<th>平均完成时间</th>
							<th>项目数</th>
						</tr>
					</thead>
					<tbody class="wrap7_con">
					
					</tbody>
				</table>				
			</div>
			<div class="wrap wrap8">
				<h4>九 、 最近3月质量问题比例</h4>
				<div class="search">
					<button class="blue2  btn btn-default" type="button" onclick="customerStatistics(4);">查询</button>
				</div>
				<table class="table table-bordered">	
				<thead>
						<tr>
							<th>采购/跟单</th>
							<th>当月的投诉数</th>
							<th>正在进行的项目数</th>
							<th>上月的投诉数</th>
							<th>上月的完成项目数</th>
							<th>上上月的投诉数</th>
							<th>上上月的完成项目数</th>
							<th>3个月平均质量投诉率</th>
							
						</tr>
			  </thead>
			  <tbody class="wrap8_con">
				</tbody>				
				</table>
			</div>
			<div class="wrap wrap9">
				<h4>十、所有打样了而没有大货单的项目的成功率</h4>
				<div class="search">
					<div class="form-group ">						
						<div class="col-sm-3 ">
							<div class="input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input id="sampleStartTime" name="sampleStartTime" class="form-control brt brt_7" size="16" type="text" value="" place="选择日期" field="报价截止日期" placeholder="起始日期" readonly="" requiredate="" onchange="checkDeadline()"> <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span></span>
						</div>
						<div class="col-sm-3 ">
							<div class="input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd">
								<input id="sampleEndTime" name="sampleEndTime" class="form-control brt brt_7" size="16" type="text" value="" place="选择日期" field="报价截止日期" placeholder="截止日期" readonly="" requiredate="" onchange="checkDeadline()"> <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span></span>
						</div>
						<button class="blue2  btn btn-default" type="button" onclick="customerStatistics(5);">搜索</button>
					</div>					
				</div>
				<table class="table table-bordered">	
				<thead>
						<tr>
							<th>采购/跟单</th>
							<th>打样有大货/打样全部项目</th>
							<th>成功率</th>
							<th>打样无大货项目号</th>
							
						</tr>
			  </thead>
			  <tbody class="wrap9_con">
				</tbody>				
				</table>
				
			</div>
			
		
	</div>
</body>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
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
	$('.table-condensed tbody,.table-condensed tfoot').on('click', function() {
		$('.datetimepicker').hide();
	})

	function selectWeek(dateType) {
		$('#dateType').val(dateType);
		$('#form').submit();
	}
	
	/* 客户系列统计 */
	function customerStatistics(num){
		var url="";
		var customerStartTime1="";
		var customerEndTime1="";
		if(num==1){
			var customerStartTime1 = $("#customerStartTime").val();//起始时间
			var customerEndTime1 = $("#customerEndTime").val();//结束时间
		  }else if(num==2){
			var customerStartTime1 = $("#purchaseUser").val();//采购
			var customerEndTime1 = $("#saleUser").val();//跟单 
		  }else if(num==3){
			  var customerStartTime1 = $("#quoteStartDate").val();//项目平均完成时间起始时间
				var customerEndTime1 = $("#quoteEndDate").val();//项目平均完成时间截止时间 
		  }else if(num==4){
			  var customerStartTime1 = "";//项目平均完成时间起始时间
				var customerEndTime1 = "";//项目平均完成时间截止时间  
		  }else if(num==5){
			  var customerStartTime1 =$("#sampleStartTime").val();//打样无大货起始时间
				var customerEndTime1 =$("#sampleEndTime").val();//打样无大货截至时间
		  }else if(num==6){
			  
		  }
		 $.ajax({
			type : "post",
			url : "${ctx}/statistics/customerStatistics",
			data : {
				customerStartTime : customerStartTime1,
				customerEndTime : customerEndTime1,
				num:num
			},
			 success : function(json) {
               if (json.ok) {
            	   if(num==1){
            	   var obj=eval(json.data.userList);
            	   var text="";
            	   $(obj).each(function (index){
            		var val=obj[index];
					text+="<tr><td>"+val.userName+"</td>"
					+"<td>"+val.newCustomerProofingNumber+"</td><td>"+val.newCustomerProofingMoney+"</td>"
					+"<td>"+val.newCustomerBulkNumber+"</td><td>"+val.newCustomerBulkMoney+"</td>"
					+"<td>"+val.customerProofingNumber+"</td><td>"+val.customerProofingMoney+"</td>"
					+"<td>"+val.customerBulkNumber+"</td><td>"+val.customerBulkMoney+"</td>"
					+"<td>"+val.returnItemNumber+"</td><td>"+val.returnItemMoney+"</td>"
					+"</tr>";	
					});
            	   $('.dp_tabel_body').html(text);
					}else if(num==2){
            		   var text="";
            		   var projectNo1="";
            		   var moneyDate1="";
            		   var finishTime1="";
            		   var days1="";
            		   var projectNo2="";
            		   var moneyDate2="";
            		   var finishTime2="";
            		   var days2="";
            		   var projectNo3="";
            		   var moneyDate3="";
            		   var finishTime3="";
            		   var days3="";
            		   var purchaseName1="";
            		   var purchaseName2="";
            		   var purchaseName3="";
            		   var sellName1="";
            		   var sellName2="";
            		   var sellName3="";
            		   var obj1=eval(json.data.newAdditions);
            		   var obj2=eval(json.data.noChangeProject);
            		   var obj3=eval(json.data.reductionProjects);
            		   if(obj1!=''&&obj1!=null){
            		   $(obj1).each(function (index){
            			   var val=obj1[index];
            			   projectNo1+="<p>"+val.projectNo+"</p>";
            			   purchaseName1+="<p>"+val.purchaseName+"</p>";
            			   sellName1+="<p>"+val.sellName+"</p>";
                			if(val.moneyDate!=null){
            			   var date =toDate(val.moneyDate);
            			   moneyDate1+="<p>"+date+"</p>";
            			   }else{
            			   moneyDate1+="<p>暂无</p>";   
            			   }
            			   if(val.finishTime!=null){
            				   finishTime1+="<p>已出货</p>";
                			   }else{
                			 finishTime1+="<p>未出货</p>";   
                			   } 
            			   if(val.scheduledDate!=null){
            				   days1+="<p>"+val.scheduledDate+"</p>";
                 			   }else{
                 			var date =toDate(val.createDate);
             				  var date1=new Date(date);  //开始时间
                 			  var date2=new Date();    //结束时间
                              var date3=date2.getTime()-date1.getTime();  //时间差的毫秒数
                               //计算出相差天数
                 			  var day=Math.floor(date3/(24*3600*1000));   
                 				   
                 			  days1+="<p>"+day+"</p>";   
                 			   }
            			});
            		   }
            		   text+="<tr><td>最近半年新增的项目</td><td>"
            		   text+=projectNo1;
            		   text+="</td><td>";
            		    text+=purchaseName1;
            		   text+="</td><td>";
            		   text+=sellName1;
            		   text+="</td><td>"; 
            		   text+=moneyDate1;
            		   text+="</td><td>";
            		   text+=finishTime1;
            		   text+="</td><td>";
            		   text+=days1;
            		   text+="</td></tr>";
            		   if(obj2!=''&&obj2!=null){
            		   $(obj2).each(function (index){
            			   var val=obj2[index];
            			   projectNo2+="<p>"+val.projectNo+"</p>";
            			   purchaseName2+="<p>"+val.purchaseName+"</p>";
            			   sellName2+="<p>"+val.sellName+"</p>";
            			   if(val.moneyDate!=null){
            			   var date =toDate(val.moneyDate);
            			   moneyDate2+="<p>"+date+"</p>";
            			   }else{
            			   moneyDate2+="<p>暂无</p>";   
            			   }
            			   if(val.finishTime!=null){
            				   finishTime2+="<p>已出货</p>";
                			   }else{
                			 finishTime2+="<p>未出货</p>";   
                			   } 
            			   if(val.scheduledDate!=null){
            				   days2+="<p>"+val.scheduledDate+"</p>";
                 			   }else{
                 			var date =toDate(val.createDate);
             				  var date1=new Date(date);  //开始时间
                 			  var date2=new Date();    //结束时间
                              var date3=date2.getTime()-date1.getTime();  //时间差的毫秒数
                               //计算出相差天数
                 			  var day=Math.floor(date3/(24*3600*1000));   
                 				   
                 			  days2+="<p>"+day+"</p>";   
                 			   }
            			});
            		   }
            		   text+="<tr><td>没有变化的项目</td><td>"
            		   text+=projectNo2;
            		   text+="</td><td>";
           		       text+=purchaseName2;
           		       text+="</td><td>";
           		       text+=sellName2;
            		   text+="</td><td>"; 
            		   text+=moneyDate2;
            		   text+="</td><td>";
            		   text+=finishTime2;
            		   text+="</td><td>";
            		   text+=days2;
            		   text+="</td></tr>";
            		   if(obj3!=''&&obj3!=null){
            		   $(obj3).each(function (index){
            			   var val=obj3[index];
            			   projectNo3+="<p>"+val.projectNo+"</p>";
            			   purchaseName3+="<p>"+val.purchaseName+"</p>";
            			   sellName3+="<p>"+val.sellName+"</p>";
            			   if(val.moneyDate!=null){
            			   var date =toDate(val.moneyDate);
            			   moneyDate3+="<p>"+date+"</p>";
            			   }else{
            			   moneyDate3+="<p>暂无</p>";   
            			   }
            			   if(val.finishTime!=null){
            				   finishTime3+="<p>已出货</p>";
                			   }else{
                			 finishTime3+="<p>未出货</p>";   
                			   } 
            			   if(val.scheduledDate!=null){
            				   days3+="<p>"+val.scheduledDate+"</p>";
                 			   }else{
                 			var date =toDate(val.createDate);
             				  var date1=new Date(date);  //开始时间
                 			  var date2=new Date();    //结束时间
                              var date3=date2.getTime()-date1.getTime();  //时间差的毫秒数
                               //计算出相差天数
                 			  var day=Math.floor(date3/(24*3600*1000));   
                 			  days3+="<p>"+day+"</p>";   
                 			   }
            			});
            		   }
            		   text+="<tr><td>最近半年减少的项目</td><td>"
            		   text+=projectNo3;
            		   text+="</td><td>";
           		       text+=purchaseName3;
           		       text+="</td><td>";
           		       text+=sellName3;  
            		   text+="</td><td>";  
            		   text+=moneyDate3;
            		   text+="</td><td>";
            		   text+=finishTime3;
            		   text+="</td><td>";
            		   text+=days3;
            		   text+="</td></tr>";
            		  
            		    $('.dp_transfer_items').html(text);
   					}else if(num==3){
   					  var obj=eval(json.data.averageCompletionTime);
   	            	   var text="";
   	            	   $(obj).each(function (index){
   	            		var val=obj[index];
   	            		if(val.scheduledDate!=0){
   	            		text+="<tr><td>"+val.userName+"</td>"
   						+"<td>"+val.scheduledDate+"</td>"
   						+"<td>"+val.item+"</td>"
   						+"</tr>";	
   	            		}
   						});
   	            	   $('.wrap7_con').html(text);
   						
   					}else if(num==4){
   						var obj=eval(json.data.QualityProblem);
   						var text="";
    	            	   $(obj).each(function (index){
    	            		var val=obj[index];
    	            		var allComplaints=(val.allComplaints)*100;
    	            		text+="<tr><td>"+val.userName+"</td>"
    						+"<td>"+val.monthlyComplaints+"</td>"
    						+"<td>"+val.projectsUnderWay+"</td>"
    						+"<td>"+val.complaintVolume+"</td>"
    						+"<td>"+val.completedLastMonth+"</td>"
    						+"<td>"+val.lastMonthTwoComplaints+"</td>"
    						+"<td>"+val.completedAboveMonth+"</td>"
    						+"<td>"+allComplaints.toFixed(2)+"%</td>"
    						
    						+"</tr>";	
    						});
    	            	   $('.wrap8_con').html(text);
   					}else if(num==5){
   						var obj=eval(json.data.SuccessRateProject);
   						var text="";
    	            	   $(obj).each(function (index){
    	            		var val=obj[index];
    	            		var successRate=(val.successRate)*100;
    	            		
    	            		
    	            		text+="<tr><td>"+val.userName+"</td>"
    						+"<td>"+val.sampleContracts+"/"+val.allContracts+"</td>"
    						+"<td>"+successRate.toFixed(2)+"%</td>"
    						+"<td>"+val.allProject+"</td>"
    						+"</tr>";	
    						});
    	            	   $('.wrap9_con').html(text);
   					}else if(num==6){
   						var obj=eval(json.data.TestReport);
   						var text="";
    	            	   $(obj).each(function (index){
    	            		var val=obj[index];
    	            		text+="<tr><td>"+val.userName+"</td>"
    						+"<td>"+val.contractAmount+"</td>"
    						+"<td>"+val.proofingProject+"</td>"
    						+"<td>"+val.massProductionProject+"</td>"
    						+"<td>"+val.lastWorkHour+"</td>"
    						+"<td>"+val.workingHours+"</td>"
    						+"<td>"+val.reportNumber+"</td>"
    						+"<td>"+val.monthReportNumber+"</td>"
    						+"</tr>";	
    						});
    	            	   $('.inspection_tabel_body').html(text);
   					}
				} else {
					layer.msg(json.message, {
						time : 2000
					});
				}
			}
		})
	}
	function toDate(obj){  
	     var date = new Date();  
	     date.setTime(obj.time);  
	     date.setHours(obj.hours);  
	     date.setMinutes(obj.minutes);  
	     date.setSeconds(obj.seconds);  
	     return date.format("yyyy-MM-dd hh:mm:ss");
	}
	Date.prototype.format = function(format) {  
		 
		 var o = {  
		  "M+" : this.getMonth() + 1,  
		  "d+" : this.getDate(),  
		  "h+" : this.getHours(),  
		  "m+" : this.getMinutes(),  
		  "s+" : this.getSeconds(),  
		  "q+" : Math.floor((this.getMonth() + 3) / 3),  
		  "S" : this.getMilliseconds()  
		 }  
		 if (/(y+)/.test(format)) {  
		  format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4  
		      - RegExp.$1.length));  
		 }  
		 for (var k in o) {  
		  if (new RegExp("(" + k + ")").test(format)) {  
		   format = format.replace(RegExp.$1, RegExp.$1.length == 1  
		       ? o[k]  
		       : ("00" + o[k]).substr(("" + o[k]).length));  
		  }  
		 }  
		 return format;  
		}   
	
	var url = window.location.href;
	if(url.indexOf('#wrap10') != -1){
		$('.wrap10_search').click();
	}
</script>




















