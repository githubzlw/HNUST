<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

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
	position:absolute;
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
	z-index: 10;
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
.fl{
	float:left;
	margin-right:5px;
}
.mt{
	margin-top:-5px;
}
.mt_c{
	margin-top:0;
}
.exit_enter{
	position:absolute;
    top: 18px;
    right: 15px;
}
.exit_enter button{
	background-color: #027CFF;
    color: #FFF;
    font-size: 18px;
    padding: 1px 16px;
    border:0 none;
    border-radius:2px;
}
.main-table{
	position:relative;
	
}
.meeting-list-table .color-blocks-btn{
	margin-top:10px;
}

.meeting-list-table .mt_5.color-blocks-btn{
	margin-top:-5px;
}
.grid tr th{white-space: unset;text-overflow: unset;}
    
    
</style>
<html>
<head lang="en">
<meta charset="UTF-8">
<link rel="stylesheet" href="${ctx}/css/test.css">
<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<title>??????????????????</title>
<style>
.tab-link{
	font-size:20px;
	line-height:unset;
}
.grid tr td{
	font-size:17px;
	padding:5px;
}
.meeting-list-table input{
	font-size:16px;
}
.inputs_two{
	border:1px solid transparent;
}
.grid tr th{ white-space: unset;text-overflow: unset;padding:6px 0;vertical-align:middle;text-align:center;}   
.grid tr th .tab-link{font-size:17px;}
.select_blank{background-color:#027CFF;padding: 3px 12px;text-decoration:none;color:#fff;border-radius: 2px;}		
.select_blank:hover,.select_blank:hover{text-decoration:none;background-color:#4362C5;color:#fff;}
</style>
</head>
<body class="list-bgcolor">
	<div class="main-container">
		<div>
			<h3>
				??????????????????<i style="margin-left: 15px;">(??????????????????<fmt:formatNumber
						value="${averageHours}" pattern="0" />(??????),???????????????${finishRatio},??????
					????????????????????? ??????<fmt:formatNumber value="${maxDate}" pattern="0" />??????)
				</i>
			</h3>
		</div>
		<div class="main-table meeting-list-table">
			<div class="exit_enter">
				<a class="select_blank" target="_blank" href="/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}">?????????????????????</a>
				<button class="btn" onclick="exitlogin()">??????</button>
			</div>
			<div class="fl">
				<input type="text" name="searchProjectNo"id="searchProjectNo" placeholder="?????????" value="${searchProjectNo}"/>
			</div> 
			<%-- <span><input type="text" name="searchName" id="searchName" placeholder="??????" value="${searchName}" /></span>  --%>
			<div class="fl"><div class="box-executive">
					<input type="text" class="form-control" name="searchName" id="searchName" value="${searchName}" placeholder="?????????????????????">
					<div class="updown-list"> <!-- style="margin-left: 150px;" -->
						<ul class="list" ><!-- style="top: 200px; margin-left: 680px;" -->
							<li class="active"><span>?????????</span>
								<ol>
								<c:forEach items="${technologyList}" var="user">
									<li>${user.userName }</li>
 								    
								</c:forEach>
								</ol></li>
							<li><span>?????????</span>
								<ol>
								<c:forEach items="${saleNameList}" var="user">
									<li>${user.userName }</li>
 								    
								</c:forEach>
									<!-- <li>jerrylong</li>		
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
									<li>annazhu</li>
									<li>ShirleyYu</li>
									<li>jennyguo</li>
									<li>AndsXue</li>
									<li>crystalyao</li>
									<li>LindaWu</li> -->
									
								</ol></li>
							<li><span>?????????</span>
								<ol>
								<c:forEach items="${purchaseList}" var="user">
									<li>${user.userName }</li>
 								    
								</c:forEach>
									<!-- <li>Jiangwenlong</li>
									<li>Bensonzhang</li>
									<li>shiqinhui</li>
									<li>zhaoqiang</li>
									<li>xuping</li>
									<li>ThomasChen</li>
									<li>xuwei</li>
									<li>chengmingkun</li>
									<li>kevinwu</li>
									<li>jayxu</li> -->
								</ol></li>
							<li><span>?????????</span>
								<ol>
								<c:forEach items="${qualitytestingList}" var="user">
									<li>${user.userName }</li>
 								    
								</c:forEach>
									<!-- <li>yanggong</li>
									<li>wangjingjun</li>
									<li>maxiaolei</li>
									<li>litie</li>
									<li>zoumin</li>
									<li>wangzhuo</li>
									<li>zhangyouqing</li>
									<li>zhaochun</li>
									<li>zhuxiaojing</li>
									<li>chenhui</li>
									<li>zhoubin</li>
									<li>xucunshan</li> -->
								</ol></li>
							
						</ul>
						<div>
							<span class="close-ck" onclick="foc()">X</span>
						</div>
					</div>
				</div>
			</div>
			
			<div class="fl mt">???????????????<select id="sendOrReceive" name="sendOrReceive" class="project-status" value="${sendOrReceive}">
					<option value="0" <c:if test="${sendOrReceive eq '0'}"> selected="selected"</c:if>>??????</option>
					<option value="1" <c:if test="${sendOrReceive eq '1'}"> selected="selected"</c:if>>??????????????????</option>
					<option value="2" <c:if test="${sendOrReceive eq '2'}"> selected="selected"</c:if>>???????????????</option>
			</select>
			</div> 
			<div class="fl mt"> ?????????
				<select class="project-status"
					onchange="selectOnchange(this)">	
						<option value="-1"
							<c:if test="${taskStatus eq '-1'}"> selected="selected"</c:if>>??????</option>
						<option value="0"
							<c:if test="${taskStatus eq '0'}"> selected="selected"</c:if>>?????????</option>
						<option value="1"
							<c:if test="${taskStatus eq '1'}"> selected="selected"</c:if>>??????</option>
						<option value="2"
							<c:if test="${taskStatus eq '2'}"> selected="selected"</c:if>>??????</option>
						<option value="3"
							<c:if test="${taskStatus eq '3'}"> selected="selected"</c:if>>??????</option>
				</select>
			</div> 
			
			<input type="hidden" value="${taskStatus}" name="taskStatus"
				id="taskStatus"> <input type="hidden" value="${userName}"
				name="userName" id="userName"> <input type="hidden"
				value="${userId}" name="userId" id="userId"> <input
				type="hidden" value="${roleNo}" name="roleNo" id="roleNo">
				 <input
				type="submit" class="color-blocks-btn mt mt_5" value="??????"
				onClick="searchProjectData(1)" /><br/>
			<br/>
				<div class="inputs_two">
				 <input type="submit" class="color-blocks-btn addInput" style="position: relative;left: 0px;top: 0px;" value="????????????????????????" onClick="toInputInspection('${userName}',${userId},${roleNo})"/> 
				<input type="submit" class="color-blocks-btn addInput" style="position: relative;left: 0px;top: 0px;" value="????????????"
				id="projectSummary" /> <input type="submit" style="position: relative;left: 0px;top: 0px;" class="color-blocks-btn addInput"
				value="????????????" onClick="addProjectTask()" id="goBackHtml" /> <input
				type="submit" class="color-blocks-btn addInput" value="????????????"
				onClick="goBack()" id="goBackHtml" /> <input type="submit"
				class="color-blocks-btn" style="position: relative;left: 0px;top: 0px;" value="????????????" onClick="projectMeetingList()"
				id="goBackHtml" />
				<input type="submit"
				class="color-blocks-btn" style="position: relative;left: 0px;top: 0px;" value="60????????????????????????" onClick="qualityAnalysisTable()"
				id="goBackHtml" /></div>

			<p>
			<div class="table-wrap">
				<div class="table-head">
					<div class="table-head-wrap">
						<table class="grid">
							<colgroup>
								<col>
								<col>
								<col>
								<col>
								<col>
								<col>
								<col>
								<col>
							</colgroup>
							<thead>
								<tr>
									<th width="90"><span class="tab-link">?????????</span></th>
									<th width="65"><span class="tab-link">?????????</span></th>
									<th width="65"><span class="tab-link">?????????</span></th>
									<th width="115"><span class="tab-link">??????</span></th>
									<th width="190"><span class="tab-link">??????</span></th>
									<th width="95"><span class="tab-link">???????????????</span></th>
									<th width="95"><span class="tab-link">????????????</span></th>
<!-- 									<th style="width:90px;"><span class="tab-link">????????????1????????????3???????????????</span></th> -->
									<th width="81"><span class="tab-link">????????????</span></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${projectTaskList}" var="projectTask">
								<!-- ?????????????????? -->
								<c:if test="${roleNo!=100 and  roleNo!=99 and roleNo!=98 and roleNo!=97 }">
									<tr class="project-flag">
										<input type="hidden" name="taskFlag"
											value="${projectTask.taskFlag}">
										<td width="50">
											<div>${projectTask.projectNo}</div>
										</td>
										<td width="51">
											<div>${projectTask.initiator}</div>
										</td>
										<td width="50">
											<div>${projectTask.accepter}</div>
										</td>
										<td width="91">
											<div>
												??????:
												<fmt:formatDate value="${projectTask.startTime}"
													pattern="yyyy-MM-dd" />
												<br> ??????:
												<fmt:formatDate value="${projectTask.finishTime}"
													pattern="yyyy-MM-dd" />
												<br> ??????:
												<fmt:formatDate value="${projectTask.expectFinishTime}"
													pattern="yyyy-MM-dd" />
												<br> ??????:
												<fmt:formatDate value="${projectTask.operatorTime}"
													pattern="yyyy-MM-dd" />
												<br>
												<c:if test="${projectTask.taskType eq '2'}">
												??????:<fmt:formatDate value="${projectTask.inspectionReservation.expectedDelivery}" pattern="yyyy-MM-dd"/><br> 
												??????:<fmt:formatDate value="${projectTask.inspectionReservation.shippingDate}" pattern="yyyy-MM-dd" /><br>
												</c:if>
												<c:if test="${projectTask.taskType eq '2'}">
												<c:if test="${projectTask.inspectionReservation.finishTime != null}">
												????????????:<fmt:formatDate value="${projectTask.inspectionReservation.finishTime}" pattern="yyyy-MM-dd" /></c:if>
												</c:if>
											</div>
										</td>
										<td width="190">
											<div class="mark" title ="${projectTask.description}">${projectTask.description}
											<c:if test="${projectTask.taskUrl !=null}">
											<a href="http://117.144.21.74:10010/project_task_img/${projectTask.taskUrl}">${projectTask.taskUrl}</a></c:if>
											<c:if test="${projectTask.taskType eq '2'}"><br>??????????????????:${projectTask.inspectionReservation.produceStatus}<br>????????????:${projectTask.inspectionReservation.testType}<br>
											<c:if test="${projectTask.openRate != null}">???????????????${projectTask.openRate==null?(projectTask.checkType != '??????' ? '?????????': ''):(projectTask.openRate+'%')}</c:if>
											</c:if></div>
										</td>
										<td width="95">
											<c:choose>
											      <c:when test="${projectTask.taskType eq '2' and (roleNo==100 or roleNo==99 or roleNo==98 or roleNo==97)}">
											        ${projectTask.urgentReason}
											      </c:when>
											      <c:otherwise>
											        ${projectTask.urgentReason}
											      </c:otherwise>
											  </c:choose>
										</td>
										<td width="95">
											<div>${projectTask.operateExplain}</div>
										</td>
								<%-- 		<td>
										<c:if test="${projectTask.progressStatus == null || projectTask.progressStatus == ''}">
										 ?????????
										</c:if>
										<c:if test="${projectTask.progressStatus != null && projectTask.progressStatus != ''}">
										    <c:if test="${projectTask.progress != null && projectTask.progress != ''}">
										        ${projectTask.progress} <fmt:formatDate value="${projectTask.progressDate}" pattern="yyyy-MM-dd"/>
										    </c:if>
										    <c:if test="${projectTask.progress == null || projectTask.progress == ''}">
										        <span style="color:red;">?????????</span>
										    </c:if>
										</c:if>
										</td> --%>
										<td width="81" class="mession-status">
											<div>
												<c:if test="${projectTask.taskStatus eq '0'}">?????????</c:if>
												<c:if test="${projectTask.taskStatus eq '1'}">?????????</c:if>
												<c:if test="${projectTask.taskStatus eq '2'}">??????</c:if>
												<c:if test="${projectTask.taskStatus eq '3'}">??????</c:if>
												<c:if
													test="${projectTask.operator!= '' and projectTask.operator!= null}">
		                                     (${projectTask.operator})
		                                   </c:if><br>
												<a
													href="${ctx}/projectTask/selectProjectTaskById?id=${projectTask.id}&userId=${userId}&userName=${userName}&roleNo=${roleNo}">????????????</a>
													
											</div>
										</td>
									</tr>
								</c:if>


								<!-- ????????????????????????????????? -->
								<c:if test="${roleNo==100 or roleNo==99 or roleNo==98 or roleNo==97  }">
									<tr class="project-flag">
										<input type="hidden" name="taskFlag"
											value="${projectTask.taskFlag}">
										<td width="50">
											<div>${projectTask.projectNo}</div>
										</td>
										<td width="51">
											<div>${projectTask.initiator}</div>
										</td>
										<td width="50">
											<div>${projectTask.accepter}</div>
										</td>
										<td width="91">
											<div>
												??????:<fmt:formatDate value="${projectTask.startTime}" pattern="yyyy-MM-dd"/><br> 
												??????:<fmt:formatDate value="${projectTask.finishTime}" pattern="yyyy-MM-dd"/><br> 
												??????:<fmt:formatDate value="${projectTask.expectFinishTime}" pattern="yyyy-MM-dd"/><br> 
												??????:<fmt:formatDate value="${projectTask.operatorTime}" pattern="yyyy-MM-dd" /><br>
												<c:if test="${projectTask.taskType eq '2'}">
												??????:<fmt:formatDate value="${projectTask.inspectionReservation.expectedDelivery}" pattern="yyyy-MM-dd"/><br> 
												??????:<fmt:formatDate value="${projectTask.inspectionReservation.shippingDate}" pattern="yyyy-MM-dd" /><br>
												</c:if>
											<c:if test="${projectTask.taskType eq '2'}"><c:if test="${projectTask.inspectionReservation.finishTime != null}">????????????:<fmt:formatDate value="${projectTask.inspectionReservation.finishTime}" pattern="yyyy-MM-dd" /></c:if>
											</c:if>
											</div>
										</td>
										<td width="190">
											<div class="mark" title="${projectTask.description}">${projectTask.description}<c:if test="${projectTask.taskUrl !=null}"><a href="http://117.144.21.74:10010/project_task_img/${projectTask.taskUrl}">${projectTask.taskUrl}</a></c:if>
											<c:if test="${projectTask.taskType eq '2'}"><br>??????????????????:${projectTask.inspectionReservation.produceStatus}<br>????????????:${projectTask.inspectionReservation.testType}<br>
											</c:if></div>
										</td>
										<td width="95">
											<div>
											   <c:choose>
											      <c:when test="${projectTask.taskType eq '2' and (roleNo==100 or roleNo==99 or roleNo==98 or roleNo==97)}">
											        ${projectTask.urgentReason}
											      </c:when>
											      <c:otherwise>
											        ${projectTask.urgentReason}
											      </c:otherwise>
											   </c:choose>
											</div>
										</td>
										<td width="95">
											<div>${projectTask.operateExplain}</div>
										</td>
<!-- 										<td> -->
<%-- 										<c:if test="${projectTask.progressStatus == null || projectTask.progressStatus == ''}"> --%>
<!-- 										 ????????? -->
<%-- 										</c:if> --%>
<%-- 										<c:if test="${projectTask.progressStatus != null && projectTask.progressStatus != ''}"> --%>
<%-- 										    <c:if test="${projectTask.progress != null && projectTask.progress != ''}"> --%>
<%-- 										        ${projectTask.progress} <fmt:formatDate value="${projectTask.progressDate}" pattern="yyyy-MM-dd"/> --%>
<%-- 										    </c:if> --%>
<%-- 										    <c:if test="${projectTask.progress == null || projectTask.progress == ''}"> --%>
<!-- 										        <span style="color:red;">?????????</span> -->
<%-- 										    </c:if> --%>
<%-- 										</c:if> --%>
<!-- 										</td> -->
										<td width="81" class="mession-status">
											<div>
												<c:if test="${projectTask.taskStatus eq '0'}">?????????</c:if>
												<c:if test="${projectTask.taskStatus eq '1'}">?????????</c:if>
												<c:if test="${projectTask.taskStatus eq '2'}">??????</c:if>
												<c:if test="${projectTask.taskStatus eq '3'}">??????</c:if>
												<c:if
													test="${projectTask.operator!= '' and projectTask.operator!= null}">
		                                     (${projectTask.operator})
		                                   </c:if><br>
												<a
													href="${ctx}/projectTask/selectProjectTaskById?id=${projectTask.id}&userId=${userId}&userName=${userName}&roleNo=${roleNo}">????????????</a>
											
											</div>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="page-box">
				??????:${totalCount}??????:10??? ?????????/??????:<span style='color: red'>${pageNumber}</span>/<span
					style='color: red'>${countPage}</span>&nbsp; <a href="#"
					class='emanagergetpagea first-padding'
					onclick="searchProjectData(1)" title='?????????' class='a02'>??????</a> <a
					href="#" class='emanagergetpagea first-padding'
					onclick="searchProjectData(2)" title='?????????(???1???)' class='a02'>??????</a>
				<a href="#" class='emanagergetpagea' onclick="searchProjectData(3)"
					title='?????????(???3???)' class='a02'>??????</a> <a href="#"
					class='emanagergetpagea' onclick="searchProjectData(4)"
					title='????????????' class='a02'>??????</a>
			</div>
			<input type="hidden" id="pageNumber" name="pageNumber"
				value="${pageNumber}"> <input type="hidden" id="countPage"
				name="countPage" value="${countPage}">
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
<script type="text/javascript">
	$(function() {
		$('.project-flag').each(
				function(i) {
					var project_flag = $(this).find('input[name="taskFlag"]')
							.val();
					if (project_flag == 1) {
						//$(this).css("border","1px solid red");
						$(this).css("border", "1px solid red").find("td").css(
								"border-bottom", "none").end().find("td:first")
								.css("border-left", "none");
						$(this).prev("tr").find("td").css("border-bottom",
								"none");
					}
				})
				
				
		//?????????????????????cookie
		var search = location.href;
		$.cookie('search', search, {
			path : '/'
		});		
	})
	$(function() {
		var userName = $("#userName").val();
		if (userName == null || userName == '' || userName == undefined
				|| userName == "null") {
			$("#goBackHtml").hide();
		}
	})

	//????????????????????????
	$("#projectSummary").click(
			function() {
				var roleNo = $("#roleNo").val();
				var userId = $("#userId").val();
				var userName = $("#userName").val();
				window.location.href = "${ctx}/jsp/project-summary.jsp?roleNo="
						+ roleNo + "&userId=" + userId + "&userName="
						+ userName;
			})

	//??????????????????
	function projectMeetingList() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		window.open("${ctx}/meetingRecord/selectMeetingRecordList?roleNo="
				+ roleNo + "&userId=" + userId + "&userName=" + userName);
	}
	//????????????????????????
	function qualityAnalysisTable() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		window.open("${ctx}/qualityAnalysisTable/listItems?roleNo="
				+ roleNo + "&userId=" + userId + "&userName=" + userName);
	}
	//????????????????????????
	function addProjectTask() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		window.location.href = "${ctx}/projectTask/addTask?roleNo="
				+ roleNo + "&userId=" + userId + "&userName=" + userName;
	}
	function statisticsProjectTask() {
		window.location.href = "${ctx}/projectTask/statisticsProjectTask";
	}
	function toInputInspection(userName,userId,roleNo) {
		window.open ("http://117.144.21.74:10010/inspection/toInputInspection?roleNo="+roleNo+"&userId="+userId+"&userName="+userName);
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

	function selectOnchange(obj) {
		var taskStatus = $(obj).find('option:selected').val();
		$("#taskStatus").val(taskStatus);
	}

	//????????????
	function searchProjectData(obj) {
		var searchName = $("#searchName").val();
		var projectNo = $("#searchProjectNo").val();
		var pageNumber = $("#pageNumber").val();
		var countPage = $("#countPage").val();
		var taskStatus = $("#taskStatus").val();
		var type = obj;
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var sendOrReceive = $('#sendOrReceive').val()

		// 1 ?????????  2.?????????  3.????????? 4.??????
		if (type == 1) {
			pageNumber = 1;
		}
		if (type == 2) {//?????????
			if (pageNumber == 1) {
				pageNumber = 1
			} else {
				pageNumber = Number(pageNumber) - 1;
			}
		}
		if (type == 3) {//?????????
			if (pageNumber == countPage) {
				pageNumber = countPage;
			} else {
				pageNumber = Number(pageNumber) + 1;
			}
		}
		if (type == 4) {//??????
			pageNumber = countPage;
		}
		window.location.href = "${ctx}/projectTask/projectTaskList?projectNo="
				+ projectNo + "&searchName=" + searchName + "&pageNumber="
				+ pageNumber + "&taskStatus=" + taskStatus + "&userName="
				+ userName + "&roleNo=" + roleNo + "&userId=" + userId
				+ "&sendOrReceive=" + sendOrReceive;
	}
	
	
	
	$(".box-executive input,.box-executive em").click(
		function(event) {
			$(this).parents(".project-task-list").find(".updown-list") .slideUp(30).end().end().siblings(".updown-list").slideDown();
			$(this).parent(".box-executive").find("em").addClass("arrow-icon");
			return false;
		}
	);

	$(".list ol li").click(function(event) {
		var game1 = $(this).text()
		console.log($(this).parents(".updown-list").siblings("input").length);
		$(this).parents(".updown-list").siblings("input").val(game1);
		foc();
	});

	function addClick() {
	  $(".box-executive input,.box-executive em").click(
		function(event) {
			$(this).parents(".project-task-list").find(".updown-list").slideUp(30).end().end().siblings(".updown-list").slideDown();
			$(this).parent(".box-executive").find("em").addClass("arrow-icon");
			return false;
		}
	  );

	 $(".list ol li").click(
		function(event) {
			var game1 = $(this).text()
			console.log($(this).parents(".updown-list").siblings("input").length);
			$(this).parents(".updown-list").siblings("input").val(game1);
			foc();
		}
	 );

	}
	// ????????????4??????????????????
	// var markLength = $('.mark').length();
	$('.mark').each(function(){
		var markText = $(this).text();
		var markLength = $(this).text().length;
		var str = '';
		if(markLength > 60){			
			str = $(this).text().substring(0,55) + '.....';
			$(this).text(str)
			
		}
				
	});
	
	
	
	
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
		window.location.href = "${ctx}/user/toIndex?userId=" + userId + "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId + "&userName=" + encodeURI(encodeURI(userName));
	}
</script>
</html>