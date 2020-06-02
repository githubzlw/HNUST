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
<link rel="stylesheet" href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="${ctx}/css/test.css">
<link rel="stylesheet" href="${ctx}/css/add.css">
<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>	
<title>新项目启动录入</title>
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

</style>
</head>
<body class="list-bgcolor">
	<div class="main-container new_project_entry">
		
		<div>
			
		</div>
		<div class="main-table meeting-list-table form-horizontal">
			<h3>人员录入：</h3>
			<div class="form-group">
				<label for="">登录名</label>
				<input type="text" class="form-control" id="user_name">
				<span>必填</span>
			</div>
			<div class="form-group">
				<label for="">密码</label>
				<input type="text" class="form-control" id="pwd">
				<span></span>
			</div>
			<div class="form-group">
				<label for="">权限</label>
				<input type="text" class="form-control" id="pwd">
				<span></span>
			</div>			
			<div class="form-group">
				<div class="clearfix">
					<label class="label_date">启动日期</label>
					<div class="add_date">
						<div class="input-group date form_date"
							data-date="" data-date-format="yyyy-mm-dd">
							<input id="startDate" name="startDate"
								class="form-control brt brt_7" size="16" type="text" value=""
								place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
								requiredate><span class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<span></span>
					</div>					
				</div>				
			</div>
			<div class="form-group">
				<label for="">跟单</label>
				<select class="form-control" id="sell_id">
					<c:forEach var="obj" items="${userList}" varStatus="i">
						<option value="${obj.id}">${obj.userName}</option>
	                 </c:forEach>
				</select>
			</div>
			<!-- 保留 -->
			<div class="fl">
			<div class="box-executive form-group">			
					<label>采购</label>
					<input type="text" class="form-control" name="purchaseName" id="purchaseName" placeholder="请选择公司职员">
					<div class="updown-list"> <!-- style="margin-left: 150px;" -->
						<ul class="list" ><!-- style="top: 200px; margin-left: 680px;" -->
							<li class="active"><span>技术部</span>
								<ol>
									<li>LiuZhongYi</li>
									<li>zhangqun</li>
									<li>OwenCui</li>
									<li>YanRongchao</li>
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
									<li>xuping</li>
									<li>ThomasChen</li>
									<li>xuwei</li>
									<li>RogerQiu</li>
									<li>SISI</li>
									<li>ArnoZhou</li>
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
			</div>
			<!-- 保留 -->			
			<div class="btns"><button class="btn" onclick="addProject()">录入</button></div>
      
     <form id="form" class="roleform form-horizontal"  method="get" action="/project/queryNewProject" onclick="return false;"> 
			<h3>新项目启动历史：</h3>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>销售名</th>
						<th>项目号</th>
						<th>启动时间</th>
						<th>金额（万美元）</th>
					</tr>
				</thead>
				<tbody>
				    <c:forEach var="project" items="${projectList}" varStatus="i">
						<tr>
							<td>${project.sellName}</td>
							<td>${project.projectNo}</td>
							<td><fmt:formatDate value="${project.dateSampleUploading}" pattern="yyyy-MM-dd"/></td>
							<td>${project.projectAmount}</td>
						</tr>
					</c:forEach>
				</tbody>						
			</table>
			 <input type="hidden" id="pageNumber" name="page"value="${pageNumber}">
			 <input type="hidden" id="countPage" name="countPage" value="${countPage}">
			 <input type="hidden" id="totalPage" name="totalPage" value="${totalPage}">
			 <input type="hidden" value="${userId}" name="userId" id="userId">
	</form>		

			<div class="page-box">
				总数:${count}每页:${pageSize}条 当前页${countPage} 总页:<span style='color: red'>${totalPage}</span><span
					style='color: red'></span>&nbsp; <a href="#"
					class='emanagergetpagea first-padding'
					onclick="searchProjectData(1)" title='第一页' class='a02'>首页</a> <a
					href="#" class='emanagergetpagea first-padding'
					onclick="seachPage(1)" title='上一页(第1页)' class='a02'>上页</a>
				<a href="#" class='emanagergetpagea' onclick="seachPage(2)"
					title='下一页(第3页)' class='a02'>下页</a> <a href="#"
					class='emanagergetpagea' onclick="searchProjectData(${totalPage})"
					title='最后一页' class='a02'>尾页</a>
			</div>
			
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">




//上页、下一页查询
function seachPage(num){
	num = Number(num);
	var countPage = Number($("#countPage").val());
	var totalPage = Number($('#totalPage').val());
	if(countPage == 1 && num == 1){
		return false;
	}
	if(num == 2 && countPage == totalPage){
		return false;
	}
	if(num == 1){
		$("#countPage").val(countPage-1);
		searchProjectData(countPage-1)
	}else if(num == 2){
		$("#countPage").val(countPage+1);
		searchProjectData(countPage+1)
	}
	
	
}











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
	})
	$(function() {
		var userName = $("#userName").val();
		if (userName == null || userName == '' || userName == undefined
				|| userName == "null") {
			$("#goBackHtml").hide();
		}
	})

	//进入项目统计界面
	$("#projectSummary").click(
			function() {
				var roleNo = $("#roleNo").val();
				var userId = $("#userId").val();
				var userName = $("#userName").val();
				window.location.href = "${ctx}/jsp/project-summary.jsp?roleNo="
						+ roleNo + "&userId=" + userId + "&userName="
						+ userName;
			})

	//进入会议列表
	function projectMeetingList() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		window.open("${ctx}/meetingRecord/selectMeetingRecordList?roleNo="
				+ roleNo + "&userId=" + userId + "&userName=" + userName);
	}
	//进入质量会议列表
	function qualityAnalysisTable() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		window.open("${ctx}/qualityAnalysisTable/listItems?roleNo="
				+ roleNo + "&userId=" + userId + "&userName=" + userName);
	}
	//进入录入任务页面
	function addProjectTask() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		window.location.href = "${ctx}/jsp/input_project_task.jsp?roleNo="
				+ roleNo + "&userId=" + userId + "&userName=" + userName;
	}
	function statisticsProjectTask() {
		window.location.href = "${ctx}/projectTask/statisticsProjectTask";
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

	//筛选列表
	function searchProjectData(pageNumber) {
		$("#pageNumber").val(pageNumber);
		
		//提交表单
		$('#form').submit();
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
	
	
	
	//新项目录入
	function addProject(){
		
		 var projectNo = $('#projectNo').val();
		 var amount = $('#amount').val();
		 var startDate = $('#startDate').val();
		 var purchaseName = $('#purchaseName').val();
		 var userId = $('#userId').val();
		
		
		 if(projectNo=="" || projectNo==null){
			  layer.msg("项目号不能为空",{time:2000});  
			  return false;
		  }
		 if(startDate=="" || startDate==null){
			  layer.msg("启动时间不能为空",{time:2000});  
			  return false;
		  }
		 
		 
		  $.ajax({
			    type:"post",                   
			    url:"${ctx}/project/addProject",           
			    data:{
			    	  projectNo:projectNo,
			    	  amount:amount,
			    	  startDate:startDate,
			    	  purchaseName : purchaseName,
			    	  userId:userId,
			    	  sellId:$('#sell_id').val()
			    	 },              
			    success:function(json){  
// 			      var json = eval("(" + data +")");
				  if(json.ok){
					  window.location.reload();
				  }else{
					  layer.msg(json.message,{time:2000});
				  }   
			    }
		 });  
		
		
	}
	
	
</script>
</html>
<script>
	/* 日历插件*/
    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 4,
        forceParse: 0
    });

</script>