<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<title>功能选择</title>
	<script type="text/javascript"> 
    </script>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
	<link rel="stylesheet" href="${ctx}/css/add.css">
	<style>
		.show_red{color:red;}
		.omit_mark{display:inline-block;height:1em;line-height:1;text-align:left;vertical-align:-.25em;overflow:hidden;}
		.omit_mark::before{display:block;content:'...\A..\A.';white-space:pre-wrap;animation:dot 1s infinite step-start both;-webkit-animation:dot 1s infinite step-start both;-ms-animation:dot 1s infinite step-start both;-moz-animation:dot 1s infinite step-start both;-o-animation:dot 1s infinite step-start both;}
		@keyframes dot{
			33%{transform:translateY(-2em);}
			66%{transform:translateY(-1em);}
		}
		@-webkit-keyframes dot{
			33%{transform:translateY(-2em);}
			66%{transform:translateY(-1em);}
		}
		@-moz-keyframes dot{
			33%{transform:translateY(-2em);}
			66%{transform:translateY(-1em);}
		}
		@-o-keyframes dot{
			33%{transform:translateY(-2em);}
			66%{transform:translateY(-1em);}
		}
		@-ms-keyframes dot{
			33%{transform:translateY(-2em);}
			66%{transform:translateY(-1em);}
		}
	</style>
	</head>
   <body class="bg">
		<div class="login-main select_fun">			
			<h2 class="login-tit">
			<img src="../img/logo.png" class="logo">
			<span>任务系统</span>
			<button class="ext" onclick="exitlogin()">登出</button>
			</h2>

			<div class="btns">
				<c:if test="${roleNo==5 && user.job=='跟单'}"><div class="docmentary">
					<h3>跟单功能模块</h3>
					<p>1、正在跟单项目数<span>${documentaryItemNumber }</span>，当前延期项目数<span>${numberOfDeferredItems }</span>，超过3个月的跟单项目数<span>${numberOfDocumentaryItemsOver3Months }</span>，一周内未更新项目数量<span>${numberOfItemsNotUpdated }</span>，请点击PC项目列表按钮进行更新</p>
					<p>2、<!-- 项目完成后未跟进邮件的项目数量<span>a</span>， -->一个月内完成的项目数量<span>${numberOfProjectsCompletedInOneMonth }</span>，一个月内完成但没跟进的项目数量<span>${noFollowUpItems }</span></p>
					<p class="mt5"><button class="btn btn-default" onclick="loginNBEmail('${userName}');">ERP查看跟单项目</button></p>
					<p>3.<button class="btn btn-default" onclick="difficultProject('${userName}',${userId},${roleNo });">疑难项目点我去重点关照(<span>${numberOfDifficultProjects }</span>)</button></p>
					<p>4.<button class="btn btn-default" onclick="TaskSystemView('${userName}');">我的报出去但没有成功的项目列表</button></p>
					<p>5.<button class="btn btn-default" onclick="nonPaymentCustomers('${userName}')">催款项目列表</button></p>
					<p>6.<button class="btn btn-default" onclick="UpdateProjectStatus('${userInfo}');">${num }个项目不跟新会在本周内死去</button></p>
					<p>7.<button class="btn btn-default" onclick="unquotedItems('${userInfo}');">内部报价系统 超过3天未报价${unquotedItem1 } 其中老客户${unquotedItem2 }</button></p>
					<div class="line mb10"></div>
				</div></c:if>
				<c:if test="${roleNo==5 && user.job=='销售'}"><div class="docmentary">
					<h3>销售功能模块</h3>
					<p>1.<button class="btn btn-default" onclick="UpdateProjectStatus('${userInfo}');">${num }个项目不跟新会在本周内死去</button></p>
					<p>2.<button class="btn btn-default" onclick="unquotedItems('${userInfo}');">内部报价系统 超过3天未报价${unquotedItem1 } 其中老客户${unquotedItem2 }</button></p>
					<div class="line mb10"></div>
				</div></c:if>
				<c:if test="${userName=='jerrylong' }"><div class="docmentary">
					<h3>Jerry的功能模块</h3>
					<p>1、正在跟单项目数<span>${documentaryItemNumber }</span>，当前延期项目数<span>${numberOfDeferredItems }</span>，超过3个月的跟单项目数<span>${numberOfDocumentaryItemsOver3Months }</span>，一周内未更新项目数量<span>${numberOfItemsNotUpdated }</span>，请点击PC项目列表按钮进行更新</p>
					<p>2、<!-- 项目完成后未跟进邮件的项目数量<span>a</span>， -->一个月内完成的项目数量<span>${numberOfProjectsCompletedInOneMonth }</span>，一个月内完成但没跟进的项目数量<span>${noFollowUpItems }</span></p>
					<p class="mt5"><button class="btn btn-default" onclick="adminLoginNBEmail('${userName}');">ERP查看项目</button></p>
					<p>3.<button class="btn btn-default" onclick="difficultProject('${userName}',${userId},${roleNo });">点我查看总的疑难项目列表(<span>${numberOfDifficultProjects }</span>)</button></p>
					<p>4.<button class="btn btn-default" onclick="TaskSystemView('${userName}');">我的报出去但没有成功的项目列表</button></p>
					<p>5.<button class="btn btn-default" onclick="nonPaymentCustomers('${userName}')">催款项目列表</button></p>
					<%-- <p>1、跟单的项目总数<span>a</span> 状态更新比例<span>b</span> <a href="###">点我更新(链接到PC项目列表)</a></p>
					<p>2、完成的项目数量 本月跟进邮件的数量 <a href="###">点我跟进</a></p>
					<p><a href="###">点我查看总的疑难项目列表(<span>n</span>)</a></p>
					<p><a href="http://192.168.1.91:8080/New-Quotation/EmailUserController/TaskSystemView?userName=${userName}">4、报出去但没有成功的项目，所有的清单，要可以标注哪些不适合我们做。</a></p>
					<p><a href="###">5、催款项目列表</a></p> --%>
					<div class="line mb10 mt10"></div>
				</div></c:if>
				
				<h3>功能列表</h3>
				<div class="userselediv_nor btn-list">
				<input type="text" id="projectNo" name="projectNo" class="userselein form-control pull-left" style="border:1px solid #ddd;">
				<a ><button onclick="searchProjectNo(${roleNo},${userId},'${userName}');">查询PC项目列表</button></a>
				<%-- <input type="button" onclick="searchProjectNo(${roleNo},${userId},'${userName}');" value="查询PC项目列表"> --%>
				</div>
				<div class="btn-list">
					<a href="${ctx}/project/showListNew?pageNumber=1"><button>PC项目列表</button></a>
					<a href="${ctx}/jsp/project_list_m.jsp?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}"><button>手机项目列表</button></a>

					<%--a target="blank" href="${ctx}/project/exportMonthProject"><button>最近一个月完成的项目导出</button></a>--%><br>
					<c:if test="${userName=='ninazhao'}">
					 <a target="blank" href="http://117.144.21.74:43900/NBEmail/helpServlet?action=Base64login&className=EmailUserServlet&userInfo=${userInfo}"><button>不开心客户邮件列表</button></a> 
					</c:if>
					<a href="${ctx}/statistics/selectAllByDate?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>统计列表</button></a>
					<a href="${ctx}/jsp/project-summary.jsp?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>项目汇总</button></a>
					<%--<c:if test="${roleNo=='100'}">
						<a href="${ctx}/project/exportDelayProject"><button>延期项目导出</button></a><br>
					</c:if>--%>
					<a href="${ctx}/project/queryFactoryList"><button>工厂库</button></a>
					<c:if test="${userName=='ninazhao'||userName=='emily'}">					
						<a target="blank" href="${ctx}/project/queryFactoryProject"><button>工厂状态管理</button></a>
					</c:if>	
					<a href="${ctx}/project/productingProjectDetail"><button>在生产工厂及项目列表</button></a> 
					<br>
						<%--<a target="blank" href="${ctx}/project/proofingPhaseProject"><button>A/B级打样阶段项目导出</button></a><br>--%>
							
					<c:if test="${roleNo=='100'}">					
						<a target="blank" href="${ctx}/projectTask/searchAll"><button>任务统计列表</button></a>
										
						<a target="blank" href="${ctx}/salesAndMerchandisingScoreController/salesAndMerchandisingScore"><button>销售、跟单评分</button></a>
					
						
					</c:if>
					<c:if test="${userName=='ninazhao' || userName=='edward'}">
					<a target="blank" href="http://117.144.21.74:43900/NBEmail/CustomerLossStatistics/customerLoss?userInfo=${userInfo}"><button>电话联系表</button></a>
					</c:if>
					<a href="${ctx}/project/selectProjectView"><button>项目时间管理</button></a><br>
					<%--<c:if test="${roleNo=='100'}">
						<a href="${ctx}/project/completeTasks"><button>按时完成项目导出</button></a>
						<a href="${ctx}/user/qualityInspectionComplaints"><button>质检项目投诉表导出</button></a>
						<br>
					</c:if>--%>
					<c:if test="${roleNo=='100'}">
						<a target="_blank" href="${ctx}/quotation/baojStatistics"><button>内部报价及NBEmail数据统计</button></a>
						<%--<a target="_blank" href="${ctx}/project/projectExportProgress"><button>进行中项目导出</button></a>--%>
						<a target="_blank" href="${ctx}/jsp/export_data_table.jsp"><button>导出数据列表页</button></a>
						</c:if>
					
					<div class="line mb10"></div>
					<a href="${ctx}/projectTask/projectTaskList?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>PC任务列表 <span class="show_red"><span id="noFinishCount"></span></span></button></a>
					<a href="/jsp/project_list_task.jsp?userId=180&roleNo=100&purchaseNameId=&userName=ninazhao"><button>手机任务列表 </button></a>
					<a href="${ctx}/projectTask/addTask?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button class="btn1">普通任务录入</button></a>
					<a href="${ctx}/inspection/toInputInspection?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>验货预约任务录入</button></a>
					<a href="${ctx}/inspection/toSelectInspection?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>检验任务列表</button></a>
					<a href="${ctx}/plan/toSelectPlan?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>检验计划</button></a>
					<a href="${ctx}/quality/qualityList"><button>检验报告列表  <span class="show_red"><span id="qualityCount"></span></span>  </button></a>
					<a href="${ctx}/project/selectIssueList"><button>质量问题检索</button></a>
					<c:if test="${roleNo=='100'||roleNo=='9' ||roleNo=='5'||roleNo=='6'||roleNo=='10' }">					
					<a target="_blank" href="${ctx}/inspection/qualityInspectionMap?roleNo=${roleNo}&userId=${userId}&userName=${userName}" ><button>本周质检地图</button></a><br>
						</c:if>
					<div class="line mb10"></div>
					<a href="${ctx}/complaint/queryList?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>客户投诉列表<span class="show_red"><span id="unFinishComplaintCount"></span></span></button></a>
					<a href="${ctx}/jsp/customer_complaint_entry.jsp?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>客户投诉录入</button></a>
					<a href="${ctx}/qualityAnalysisTable/listItems?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>60天质量分析表列表</button></a>
                    <a href="${ctx}/complaint/queryShippingList"><button>电子准予出货确认单<span class="show_red"><span id="unFinishShippingCount"></span></span></button></a>
					<a href="http://117.144.21.74:33169/shipping/Login" target="_blank"><button>出运联系单系统</button></a>				
					<div class="line mb10"></div>
					<a href="${ctx}/meetingRecord/selectMeetingRecordList?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button class="btn1">会议列表</button></a>
					<a href="${ctx}/meetingRecord/inputMeetingRecord?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>PC会议录入</button></a>
					<a href="${ctx}/project/queryNewProject?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>项目启动录入</button></a>
					<div class="line mb10"></div>
					
					<%--<c:if test="${userName =='system' || userName=='ninazhao'}">
						<div class="task add_task">
							<div class="title">任务流系统</div>
							<div class="pic"><img src="../img/kh.png"></div>
							<div class="btns_in">
								<a href="${ctx}/roleBind/queryList?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>角色绑定</button></a><br>
								<a href="${ctx}/trigger/queryList?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button>触发动作</button></a><br>
								<a href="${ctx}/taskflow/queryList?roleNo=${roleNo}&userId=${userId}&userName=${userName}"><button class="btn-last">任务流</button></a>
							</div>
						</div>
					</c:if>--%>
					<div class="mt10">					
						<a href="http://117.144.21.74:43888/New-Quotation/quotation/login.jsp"><button>内部报价系统</button></a>
						<c:if test="${userName=='ninazhao' ||userName=='rsLynn'}"><a href="${ctx}/syncUser/showAllUser"><button>新员工录入</button></a></c:if>
						<c:if test="${userName eq 'ninazhao'||userName eq 'emily'}"><a href="/track/toAddTrack"><button>采购行踪录入</button></a></c:if>
						<a href="http://117.144.21.74:33169/ERP-NBEmail/jsp/login.jsp"><button>客户管理系统</button></a>
					</div>
				</div>
			</div>
		</div>		
	</body>
</html>
<script type="text/javascript" src="${ctx}/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>	
<script type="text/javascript" src="${ctx}/js/common.js"></script>	
<script type="text/javascript">
/*报出去但没有成功的项目  */
function TaskSystemView(userName) {
window.open("http://117.144.21.74:43888/New-Quotation/EmailUserController/TaskSystemView?userName="+userName);
}
function unquotedItems(userName) {
window.open("http://117.144.21.74:43888/New-Quotation/helpServlet?action=lookQuotation&className=QuotationNewQuotesServlet");
}
/*疑难项目列表  */
function difficultProject(userName,userId,roleNo) {
	window.open("${ctx}/project/difficultItemListPage?userName="+userName+"&roleNo="+roleNo);
	}
/* 催款页面 */
function nonPaymentCustomers(userName) {
window.open("http://117.144.21.74:33169/ERP-NBEmail/helpServlet?action=nonPaymentCustomers&className=ItCaseIdServlet");
}
/*登录NBEmail  */
function loginNBEmail(userName) {
window.open("http://117.144.21.74:33168/caselist.aspx?state=0");
}
/*登录NBEmail  */
function adminLoginNBEmail(userName) {
window.open("http://117.144.21.74:33168/main.aspx?prosort=9999");
}
//跳转到内部报价看本周不处理项目
function UpdateProjectStatus(userInfo){
	window.open("http://117.144.21.74:43888/New-Quotation/quote/queryListBySaleName");
}
//查看项目详情
function searchProjectNo(roleNo,userId,userName){
	 var projectNo = $("#projectNo").val();
	 projectNo = projectNo.toUpperCase().replace("SHS","");
	   projectNo = "SHS"+projectNo;
	window.open("http://117.144.21.74:10010/project/showListNew?pageNumber=1&projectTypeS=&projectStageS=&plantAnalysisS=&detailStatusS=0,1,2,4,&delayStatusS=&inputKey="+projectNo+"&purchaseName=&documentaryName=&qualityName=&technician=&qualityReportSelect=&importantSelect=&expectedShipmentSelect=");
}
//退出功能
function exitlogin() {
	$.cookie('name', '', {
		path : '/'
	});
	$.cookie('pass', '', {
		path : '/'
	});
	window.location.href = "${ctx}/index.jsp";
}

$(function(){
	/*页面加载完开始获取所有的列表数据*/

	selectAllCount();

	//同步cookie到快制造
	  if($.cookie('name')){
           var username = $.cookie('name');
           $.ajax({
  		     type:"POST",
  		     url:"https://www.kuaizhizao.cn/account/addPurchaseName",
  		     data:{PURCHASE_USER_NAME:username},
  		     xhrFields:{withCredentials:true},
  		     dataType:"json",
  		     async:true,      //异步
  		     success:function(result){		   
  		     }
         })	
	  }	 

});

/*获取所有的列表数据*/
function selectAllCount() {
	$.ajax({
		type: 'POST',
		url:'../project/selectAll',
		dataType:'json',
		success: function(data){
			if (data.ok === true){
				var Data = data.data;
				if (Data){

					$('#noFinishCount').text("("+Data.noFinishCount+")");
					$('#qualityCount').text("("+Data.qualityCount+")");
					$('#unFinishShippingCount').text("("+Data.unFinishShippingCount+")");
					$('#unFinishComplaintCount').text("("+Data.unFinishComplaintCount+")");
				}else{ //无数据,隐藏
					$('.show_red').hide();
				}
			}else{ //无数据,隐藏
				$('.show_red').hide();
			}
		},
		error: function(err){
			$('.show_red').hide(); //无数据,隐藏
			console.log(err);
		}
	})
}
</script>
