<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String userName=request.getParameter("userName");
	String userId=request.getParameter("userId");
	String roleNo=request.getParameter("roleNo");	
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
<title>疑难项目列表</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/add.css">
<link rel="stylesheet" href="../css/progressBar.css" />
<link rel="stylesheet" href="../css/ui.css" />
<link rel="stylesheet" href="../css/ui.progress-bar.css">	
</head>
<body>
<div class="facotry_project_list difficult_project_list">
	<div class="div_transparent"></div>
	<h1 class="customer_complaint_h1">
	疑难项目列表
	<div class="btns">
		<a class="select_blank btn" target="_blank" href="${ctx}/user/toIndex?userId=<%=userId%>&roleNo=<%=roleNo%>&userName=<%=userName%>&purchaseNameId=">返回功能选择页</a>
	</div>	
	</h1>
	<input type="hidden" id="userName" value="${userName }">		
	<input type="hidden" id="roleNo" value="${roleNo }">		
	<div class="form-group check clearfix share">
		<span class="pull-left">SHS</span><input type="text" id="projectNo" class="form-control pull-left" placeholder="请输入项目号" >
		<button class="btn bgcolor_ff0" onclick="updateDifficultProject()">点击新增</button>
	</div>
	<c:if test="${userName=='jerrylong' }">
	<select id="userId" onchange="selectDifficultProject()" class="form-control">
	<option value="0">-全部-</option>
	<c:forEach var="user" items="${userList}" >
	<option value="${user.id }">${user.userName }</option>
      </c:forEach>
	</select>
	</c:if>
	<p>点击项目号可以直接到项目详情</p>
	<ul>
	<c:forEach var="difficultItemListPage" items="${list}">
		<li>
			<a class="btn btn-default del" style="background:#027cff;color:#ffffff;" onclick="del('${difficultItemListPage.projectNo}')">移除本表</a>
			<a href="${ctx}/project/showDetails?projectNo=${difficultItemListPage.projectNo}&roleNo=<%=roleNo%>&userId=<%=userId%>&userName=<%=userName%>" target="_blank">${difficultItemListPage.projectNo }</a>
			<span>${difficultItemListPage.projectName }</span>
			<span>已到账<em>${difficultItemListPage.dateBefore }</em>天</span>
		</li>
	</c:forEach>
		
	</ul>		
</div>	
</body>
</html>
<script src="/lib/jquery/jquery.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/upload-base.js"></script>
<script type="text/javascript" src="/js/jquery-form.js"></script>
<script src="/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/dist/lrz.all.bundle.js"></script>
<script>
/* function del(obj){
	$(obj).parent().remove();
}; */
function del(projectNo){
	$.ajax({
		type : "post",
		data : {
			
			projectNo : projectNo,
			difficultProject : 0
		},
		url : "${ctx}/project/updateDifficultProject",
		success : function(json) {
// 			var json = eval("(" + data + ")");
			if (json.ok) {
				window.location.reload();
			}
		}
	})
}


function updateDifficultProject(){
	var projectNo=$("#projectNo").val();
	$.ajax({
		type : "post",
		data : {
			
			projectNo : projectNo,
			difficultProject : 1
		},
		url : "${ctx}/project/updateDifficultProject",
		success : function(json) {
// 			var json = eval("(" + data + ")");
			if (json.ok) {
				window.location.reload();
			}
		}
	})
}
function selectDifficultProject(){
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var roleNo=$("#roleNo").val();
	window.open( "${ctx}/project/difficultItemListPage?userName="+userName+"&userId="+userId+"&roleNo="+roleNo);
		
}
</script>




					







