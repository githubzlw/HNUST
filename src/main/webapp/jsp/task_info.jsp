<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<title>任务信息</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
	</head>
	<body>
		<div class="go-back" onclick="goBack()"></div>
		<input type="hidden" id="roleNo" value="${roleNo}">
	    <input type="hidden" id="userId" value="${userId}">
	    <input type="hidden" id="userName" value="${userName}">
	    <input type="hidden" id="purchaseNameId" value="${purchaseNameId}">
		<div class="index-main">	
		    <div class="detail-tit">任务信息</div>
			<div class="index-middle">
				<!-- 任务列表显示 -->
				 <c:forEach  var="task" items="${taskList}">
					<a href="${ctx}/task/taskDetails?projectNo=${task.projectNo}&id=${task.id}&roleNo=${roleNo}&userId=${userId}&userName=${userName}" class="index-link">
						<dl class="index-list">
							<dd>
								<p><span class="span-left">项&nbsp;&nbsp;目 号：</span><span class="span-right porject-number">${task.projectNo}</span></p>
								<c:if test="${roleNo==100 or roleNo==99 or roleNo==98}">
									<p><span class="span-left">跟单销售：</span><span class="span-right project-person">${task.roleName}</span></p>
									<p><span class="span-left">采&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 购：</span><span class="span-right project-person">${task.purchaseName}</span></p>
								</c:if>
								<c:if test="${roleNo==5 || roleNo == 1020}">
									<p><span class="span-left">采&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;购：</span><span class="span-right project-person">${task.purchaseName}</span></p>
								</c:if>
								<c:if test="${roleNo==6 || roleNo==69 || roleNo == 1020}">
									<p><span class="span-left">跟单销售：</span><span class="span-right project-person">${task.sellName}</span></p>
								</c:if>
								<p><span class="span-left">截止日期：</span><span class="span-right project-date">
								  <fmt:formatDate value="${task.endDate}" pattern="yyyy-MM-dd"/>
								  </span>
								 </p>
								<p><span class="span-left">任务节点：</span><span class="span-right project-person">
								    ${task.node==0?'无':'' }${task.node==1?'样品交货':''}${task.node==2?'大货交货':''}
								   </span>
								</p>
								<p><span class="span-left">任务状态：</span><span class="span-right project-person">
								    ${task.status==0?'未完成':'' }${task.status==1?'已完成':''} 
								   </span>
								</p>
								<p class="p-row"><span class="span-left">任务需求：</span><span class="span-right project-name">${task.taskDemand}</span></p>
							</dd>
						</dl>
					  </a>
					</c:forEach>	
				</div>
			</div>
		</div>
		<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			
			jQuery.Huitab =function(tabBar,tabCon,class_name,tabEvent,i){
				var $tab_menu=$(tabBar);
				  // 初始化操作
				$tab_menu.removeClass(class_name);
				$(tabBar).eq(i).addClass(class_name);
				$(tabCon).hide();
				$(tabCon).eq(i).show();
				  
				$tab_menu.bind(tabEvent,function(){
					$tab_menu.removeClass(class_name);
				    $(this).addClass(class_name);
				    var index=$tab_menu.index(this);
				    $(tabCon).hide();
				    $(tabCon).eq(index).show();
				})
			}
			$.Huitab(".index-top span",".index-middle .index-task","index-active","click","0");
			$('.footer-list-two').click(function(){
				$('#mask').show(100);
				$('.index-mask').show(300);
			});
			 //一键返回列表页 
			 function goBack(){
				var roleNo=$("#roleNo").val();
				var userId=$("#userId").val();
				var userName=$("#userName").val();
				var purchaseNameId=$("#purchaseNameId").val();
				window.location.href="${ctx}/jsp/project_list.jsp?userId="+userId+"&roleNo="+roleNo+"&purchaseNameId="+purchaseNameId+"&userName="+encodeURI(encodeURI(userName));	
			 }
		</script>
	</body>
</html>
