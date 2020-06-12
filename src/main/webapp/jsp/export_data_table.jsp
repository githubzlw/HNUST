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
	<title>导出数据列表页</title>
	<script type="text/javascript"> 
    </script>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
	<link rel="stylesheet" href="${ctx}/css/add.css">   
	</head>
   <body class="bg">
		<div class="login-main select_fun">			
			<h2 class="login-tit">
			<img src="../img/logo.png" class="logo">
			<span>任务系统</span>
			<button class="ext" onclick="exitlogin()">登出</button>
			</h2>
			<div class="btns">

				
				<h3>导出数据列表</h3>

				<div class="btn-list">

					<a target="blank" href="${ctx}/project/exportMonthProject"><button>最近一个月完成的项目导出</button></a>
					<a href="${ctx}/project/exportDelayProject"><button>延期项目导出</button></a>

                  <br>
						<a target="blank" href="${ctx}/project/proofingPhaseProject"><button>A/B级打样阶段项目导出</button></a>
						<a href="${ctx}/project/completeTasks"><button>按时完成项目导出</button></a>
						<a href="${ctx}/user/qualityInspectionComplaints"><button>质检项目投诉表导出</button></a>
                        <a href="${ctx}/project/ongoingProjects"><button>进行中项目导出</button></a>
						<br>
					<div class="userselediv_nor btn-list">

						<input type="text" id="startTime" name="endTime" class="userselein form-control pull-left" style="border:1px solid #ddd;font-size: 0.1rem !important;">
						<input type="text" id="endTime" name="endTime" class="userselein form-control pull-left" style="border:1px solid #ddd;font-size: 0.1rem !important;">
                        <a ><button onclick="searchProjectExportProgress();">查询工厂付款导出</button></a>
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
//查看项目详情
function searchProjectExportProgress(){
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	window.open("${ctx}/project/projectExportProgress?startTime="+startTime+"&endTime="+endTime);
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
	
	//同步cookie到快制造
	  if($.cookie('name')){
           var username = $.cookie('name');
           $.ajax({
  		     type:"POST",
  		     url:"https://www.kuaizhizao.cn/account/addPurchaseName",
  		     data:{PURCHASE_USER_NAME:username},
  		     xhrFields:{withCredentials:true},
  		     dataType:"json",
  		     async:false,      //同步
  		     success:function(result){		   
  		     }
         })	
	  }	 
		
})

</script>
