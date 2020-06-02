<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String roleNo = request.getParameter("roleNo");
	String userId = request.getParameter("userId");
	String userName = request.getParameter("userName");
	String purchaseNameId = request.getParameter("purchaseNameId");
%>
<html style="height:auto;">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>任务列表-手机</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/add.css">
</head>
<body>
<div class="add_project_detail">
<div class="tc_bq">
		<div class="top clearfix">
			<p class="pull-left" style="font-weight: 600;font-size: 20px;">历史问题标签 </p>
			<button class="btn bgcolor_ff0 pull-right clo" >关闭</button>
		</div>
		<div class="lis" style="padding-top: 10px;">
			<ul id="processList">
				
			</ul>
		</div>
</div>
</div>
<form id="form4" action="/project/selectIssueList" method="post">	
<%--     <input type="hidden" value="${issue}" name="issue"> --%>
	<div class="confirm_list quality_problem">
		<h1 class="customer_complaint_h1">质量问题检索
			<div class="btns">
				<a class="select_blank btn" href="/user/toIndex">返回功能选择页</a>
				<a class="btn mt5 sel_btn" href="###">历史问题标签</a>
			</div>
		</h1>
		<div class="form-group clearfix mt40">
			<label class="pull-left label-control">显示范围</label>
			<select class="form-control pull-left" name="type">
				<option value="0">全部</option>
				<option value="3" <c:if test="${type == 3}">selected</c:if>>质量、技术分析关键词</option>
				<option value="1" <c:if test="${type == 1}">selected</c:if>>检验报告</option>
				<option value="2" <c:if test="${type == 2}">selected</c:if>>客户投诉</option>
			</select>
		</div>
		<div class="form-group clearfix mt10">
			<label class="pull-left label-control">项目号</label>
			<input type="text" class="form-control pull-left" name="issue" value="${issue}" placeholder="可输入质量/工艺的关键词">
			<button class="btn bgcolor_ff0 pull-left" type="submit">查询</button>
		</div>
		<ul id="issue_ul">
			<c:forEach var="obj" items="${showList}" varStatus="i">
				<li>
					<div class="top">
						<span class="mr5">${obj.projectNo}</span>
						<span class="mr5">${obj.projectName}</span>
						<c:if test="${obj.type == 3}">
						<span><fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd"/></span>
						</c:if>
					</div>
					<div>
				        <c:if test="${obj.type == 3}">
							<em>质量、技术分析标签匹配：</em>
							<span class="blue" onclick="window.location='/project/showDetails?projectNo=${obj.projectNo}'">${(issue == null || issue == '')? obj.issue : issue}</span>
						</c:if>
				        <c:if test="${obj.type == 2}">
							<em>客户投诉标签匹配：</em>
							<span class="blue" onclick="window.location='/complaint/queryComplaint?id=${obj.id}'">${obj.issue}</span>
						</c:if>
				        <c:if test="${obj.type == 1}">
							<em>检验报告标签匹配：</em>
							<span class="blue" onclick="window.location='/quality/shareQuality?id=${obj.id}'">${obj.issue}</span>
						</c:if>
					</div>
				</li>
			</c:forEach>				
		</ul>
		<input type="hidden" id="pageStr" name="pageStr" value="${page == null ? 1 : page}"> 
		<input type="hidden" id="countPage" name="countPage" value="${lastNum}">
		<input type="hidden" id="pageSize" name="pageSize" value="${pageSize == null ? 20 : pageSize}">
 		<div class="page-box mt10" style="display:block;">
			当前页/总页:
			<span style="color: red" id="pageNumberSpan">${page}</span>/
			<span id="countPageSpan" style="color: red">${lastNum}</span>&nbsp;
			<a class="emanagergetpagea first-padding" onclick="searchProjectData(1)" title="第一页">首页</a> 
			<a class="emanagergetpagea first-padding" title="上一页(第1页)" <c:if test="${page > 1}">onclick="searchProjectData(2)"</c:if>>上页</a> 
			<a class="emanagergetpagea" title="下一页(第3页)" <c:if test="${page < lastNum}">onclick="searchProjectData(3)"</c:if>>下页</a> 
			<a class="emanagergetpagea last_page" onclick="searchProjectData(4)" title="最后一页">尾页</a>
		</div>				
	</div>	
	</form>		
</body>

<script src="../lib/jquery/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-form.js"></script>

<script type="text/javascript">
//查询
function searchProjectData(obj){
	var pageNumber = $("#pageStr").val();
	var countPage = $("#countPage").val();
	var pageSize=$("#pageSize").val();
    var projectNo=$("#projectNo").val();
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

	$('#pageStr').val(pageNumber)
	
	$('#form4').submit();   
}

$(function(){
	var issue = $('input[name="issue"]').val();
	if($.trim(issue) != null && $.trim(issue) != ''){
		keyLight(issue);
	}
})


function keyLight(key, bgColor){
    var oDiv = document.getElementById('issue_ul'),
    sText = oDiv.innerHTML,
    bgColor = bgColor || "orange",    
    sKey = "<span name='addSpan' style='color: "+bgColor+";'>"+key+"</span>",
    num = -1,
    rStr = new RegExp(key, "g"),
    rHtml = new RegExp("\<.*?\>","ig"), //匹配html元素
    aHtml = sText.match(rHtml); //存放html元素的数组
    sText = sText.replace(rHtml, '{~}');  //替换html标签
    sText = sText.replace(rStr,sKey); //替换key
    sText = sText.replace(/{~}/g,function(){  //恢复html标签
          num++;
          return aHtml[num];
    });
    oDiv.innerHTML = sText;
}



//问题标签弹窗
$('.sel_btn').click(function(){
	
	  $.ajax({
		    type:"post",                   
		    url:"${ctx}/project/selectComplaintIssue",           
		    data:{
		    	  issue:null
		    	 },              
		    success:function(json){  
// 		    	var json = eval("(" + data + ")");
				if (json.ok) {
					var issueList = json.data;
					$('#processList').empty();
					 if(issueList){
						 for(var i=0;i<issueList.length;i++){	
							 $('#processList').append('<li><span>'+(issueList[i].process?issueList[i].process:'')+'</span>：<span>'+issueList[i].issue+'</span><span>总被投诉次数'+issueList[i].occurrenceNum+'</span></li>');
						 }
					 }	 
				}else{
					   easyDialog.open({
				    		  container : {
				    		    content : data.message
				    		  },
				    		  autoClose : 2000
				    	});
				}
		    }
	})
	
	 $('.tc_bq').show();
});
$('.clo').click(function(){
	$('.tc_bq').hide();
});

</script>

</html>












