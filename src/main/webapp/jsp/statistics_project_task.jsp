<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${ctx}/css/test.css">
   <link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
    <script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
   
<script src="../js/mobiscroll_date.js" type="text/javascript"
	charset="utf-8"></script>
    <title>项目任务统计列表</title>
    <style>
.date-time {
	background: url(../img/data-calendar.png) right center no-repeat;
	background-position-x: 98%;
}
.btns_search{display:inline-block;}
.add_date .date{display:inline-block;top: 13px;}
.add_date .last_btn,.add_date .add_export{display:inline-block;}
.input-group-addon:last-child{height: 34px;}
.glyphicon glyphicon-calendar{height: 34px; }
 .form-horizontal  {display: inline-block;}
	</style>
	<script>
	function selectStatistic(sign){
		window.location.href="/projectTask/statisticsProjectTask?sign="+sign+"";	
	}
	</script>
</head>
<body class="complate-bgcolor">
<h1><c:if test="${time==null }">查询全部数据</c:if></h1>
<h1><c:if test="${time!=null }">查询${time }至${time1 }数据</c:if></h1>
<!-- <button class="blue1 btn btn-default" onclick="selectStatistic(1)">查询全部</button>
<button class="blue1 btn btn-default" onclick="selectStatistic(2)">查询最近一个月数据</button> -->
<div class="btns_search clearfix">
		
		<div class="add_date" style="width:auto;">
		<div class="input-group date form_date " data-date=""
								data-date-format="yyyy-mm-dd" style="width:200px;">
								<input id="time" name="time"
									class="form-control brt brt_7 add_input" size="16" type="text"
									value="${time }"
									place="起始时间" placeholder="起始时间" requiredate
									style="width: 161px;"><span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
									</div>
									<span class="add_span">至</span>
		               <div class="input-group date form_date" data-date=""
								data-date-format="yyyy-mm-dd" style="width:200px;">
									<input id="time1" name="time1"
									class="form-control brt brt_7 add_input" size="16" type="text"
									value="${time1 }"
									place="结束时间" placeholder="结束时间" requiredate
									style="width: 161px;"><span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
								
						</div>
						<form id="form" class="roleform form-horizontal" action="/projectTask/searchAll" >
						<input type="hidden" id="startTime" name="startTime">
						<input type="hidden" id="endTime" name="endTime">
		                <a href="###" class="add_export"><button class="form-control last_btn"  onclick="exportExcel()">按指定时间查询</button></a>	
					</form>
					<form id="form" class="roleform form-horizontal" action="/projectTask/derivedData" >	
						<input type="hidden" id="startTime1" name="startTime1">
						<input type="hidden" id="endTime1" name="endTime1">
						<a href="###" class="add_export"><button class="form-control last_btn"  onclick="exportExcel()">导出查询结果</button></a>	
		            </form>
		
				        
	  </div>
	   
		</div>
	<div class="complate-content">
		<div class="complate-detail">
	        <h3>每个人的未完成任务统计：</h3>
			<div class="table-wrap">
			    <div class="table-head">
			        <div class="table-head-wrap">
			            <table class="grid">
			                <colgroup>
			                    <col>
			                    <col>
			                </colgroup>
			                <thead>
			                <tr>
			                    <th>
			                        <span class="tab-link">姓名</span>
			                    </th>
			                    <th>
			                        <span class="tab-link">未完成数量</span>
			                    </th>
			                    <th>
			                        <span class="tab-link">超期5天未完成数量</span>
			                    </th>
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
			            </colgroup>
			            <tbody>
			             <c:forEach items="${noFinishList}" var="noFinish">
				              <tr <c:if test="${noFinish.roleNo == 6 ||noFinish.roleNo == 1020}">style="background-color:#9bd89b;font-weight: 800;"</c:if>>
				                <td>
				                    <div>${noFinish.accepter}</div>
				                </td>
				                <td>
				                    <div>${empty noFinish.noFinishCount?0:noFinish.noFinishCount}</div>
				                </td>
				                <td>
				                    <div>${empty noFinish.overCount?0:noFinish.overCount}</div>
				                </td>
				            </tr>
			            </c:forEach>
			          </tbody>
			        </table>
			    </div>
			</div>
		</div>

		<div class="complate-detail">
			<h3>查询完成任务数量：</h3>
			<div class="table-wrap">
			    <div class="table-head">
			        <div class="table-head-wrap">
			            <table class="grid">
			                <colgroup>
			                    <col>
			                    <col>
			                </colgroup>
			                <thead>
			                <tr>
			                    <th>
			                        <span class="tab-link">姓名</span>
			                    </th>
			                    <th>
			                        <span class="tab-link">完成数量</span>
			                    </th>		              
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
			            </colgroup>
			            <tbody>
			             <c:forEach items="${finishList}" var="finish">
				              <tr <c:if test="${finish.roleNo == 6 ||noFinish.roleNo == 1020}">style="background-color:#9bd89b;font-weight: 800;"</c:if>>
				                <td>
				                    <div>${finish.accepter}</div>
				                </td>
				                <td>
				                    <div>${empty finish.finishCount?0:finish.finishCount}</div>
				                </td>				               
				              </tr>
			            </c:forEach>
			          </tbody>
			        </table>
			    </div>
			</div>
		</div>

		<div class="complate-detail">
			<h3>每个人任务的及时完成率:</h3>
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
			                </colgroup>
			                <thead>
			                <tr>
			                    <th>
			                        <span class="tab-link">姓名</span>
			                    </th>
			                    <th>
			                        <span class="tab-link" title="及时完成数量">及时完成数量</span>
			                    </th>
			                     <th>
			                        <span class="tab-link" title="所有完成数量">所有完成数量</span>
			                    </th>
			                    <th>
			                        <span class="tab-link" title="及时完成率">及时完成率</span>
			                    </th>
			                    <th>
			                        <span class="tab-link" title="平均任务用时">平均任务用时</span>
			                    </th>
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
			            </colgroup>
			            <tbody>
			             <c:forEach items="${onTimeList}" var="onTime">
				              <tr <c:if test="${onTime.roleNo == 6 ||noFinish.roleNo == 1020}">style="background-color:#9bd89b;font-weight: 800;"</c:if>>
				                <td>
				                    <div>${onTime.accepter}</div>
				                </td>
				                <td>
				                    <div>${empty onTime.onTimeFinish?0:onTime.onTimeFinish}</div>
				                </td>
				                 <td>
				                    <div>${empty onTime.allFinish?0:onTime.allFinish}</div>
				                </td>
				                <td>
				                    <div>${onTime.finishRatio}</div>
				                </td>
				                <td>
				                    <div>
				                      <c:if test="${onTime.finishRatio ne '0.0%' }">
				                        <fmt:formatNumber value="${onTime.averageHours}" type="number" maxFractionDigits="1"/> /小时
				                      </c:if>
				                    </div>
				                </td>
				            </tr>
			            </c:forEach>
			          </tbody>
			        </table>
			    </div>
			</div>
		</div>

		<div class="complate-detail">
			<h3>会议相关未完成任务数量(7天)：</h3>
			<div class="table-wrap">
			    <div class="table-head">
			        <div class="table-head-wrap">
			            <table class="grid">
			                <colgroup>
			                    <col>
			                    <col>
			                </colgroup>
			                <thead>
			                <tr>
			                    <th>
			                        <span class="tab-link">姓名</span>
			                    </th>
			                    <th>
			                        <span class="tab-link">未完成数量</span>
			                    </th>
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
			            </colgroup>
			            <tbody>
			             <c:forEach items="${meetingRecordList}" var="meetingRecord">
				              <tr>
				                <td>
				                    <div>${meetingRecord.accepter}</div>
				                </td>
				                <td>
				                    <div>${empty meetingRecord.meetingTaskNum?0:meetingRecord.meetingTaskNum}</div>
				                </td>
				              </tr>
			            </c:forEach>
			          </tbody>
			        </table>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-form.js"></script>
<script type="text/javascript">
	Date.prototype.pattern = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, //月份         
			"d+" : this.getDate(), //日         
			"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时         
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
						(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
								.substr(("" + o[k]).length)));
			}
		}
		return fmt;
	}
</script>
<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
<script>
function exportExcel(){
	var time=$("#time").val();
	var time1=$("#time1").val();
	$('#startTime').val(time);
	$('#startTime1').val(time);
	$('#endTime').val(time1);
	$('#endTime1').val(time1);
	$('#form').submit();
}
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