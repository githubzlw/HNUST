<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
<title>质检报告列表</title>
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="add.css">
</head>
<body>
<div class="customer_complaint_B">	
	<h1 class="customer_complaint_h1">
		质检报告列表
	</h1>
	<div class="form-group search mt10" style="margin-top: 26px;">
		<input type="text" class="form-control pull-left" placeholder="请输入项目号或项目名" id="input_key" value="${inputKey}" style="margin-right: 0;">
		<button class="btn btn-default" onclick="searchProjectData(1)">查询</button>
	</div>

<form id="form4" action="/quality/qualityList" method="post" onsubmit="return false;">	
	<table class="table tablered complaint_list">
		<tbody>
		<c:forEach var="obj" items="${reports}" varStatus="i">
			<tr>
				<td>
					<ul>
						<li class="display_inline_block mr20" >
							<a href="https://www.kuaizhizao.cn/quality/shareQuality?id=${obj.id}"><span><b>${obj.projectNo}</b></span></a>
						</li>
						<li class="display_inline_block mr20">
							<a href="https://www.kuaizhizao.cn/quality/shareQuality?id=${obj.id}"><span><b>${obj.projectName}</b></span></a>
						</li>
						<li class="display_inline_block mr20">
							<span><b><fmt:formatDate value="${obj.createtime}" pattern="yyyy-MM-dd"/></b></span>
						</li>          
						<li>
							<span>
							   ${obj.user}：${obj.status == 0 ? '<span class="blue">没问题</span>' : (obj.status == 1 ? '<span class="blue">有问题已解决</span>' : (obj.status == 2 ? ('<span class="blue">有问题需要采购解决_</span>') : ''))}
							   <c:if test="${obj.taskList!=null && obj.taskList.size()>0}">
							     <span class="blue">${obj.taskList.get(0).description}</span>  
							   </c:if>
							</span>
						</li>							  					
						<li>
							<span>${obj.sellName == null ? '暂无跟单': obj.sellName} ： <span ${obj.sellReplyContent != null ? 'class="blue"': 'class="red"'}>${obj.sellReplyContent != null ? obj.sellReplyContent : '暂无'}</span></span>							
						</li>						  
						<li>
							<span>${obj.purchaseName == null ? '暂无采购': obj.purchaseName} ： <span ${obj.purchaseReply == true ? 'class="blue"': 'class="red"'}>${obj.purchaseReply == true? obj.purchaseReplyComment.comment : '暂无'}</span></span>							
						</li>	
						<c:if test="${obj.status == 2}">				  
    				    <li>
                               <c:if test="${obj.operateExplain!=null}">
							     <span>整改结论：</span><span class="blue">${obj.operateExplain}</span>
							    </c:if>			
						       <c:if test="${obj.operateExplain==null}">
							     <span>整改结论：</span><span class="red">暂无</span>
							    </c:if>									      
						</li>	
						<li>
						       <c:if test="${obj.productFileName!=null}">
							     <span>整改完成的图纸、照片、视频：</span><a href="/project_img/${obj.projectNo}/product/${obj.productFileName}" download="/project_img/${obj.projectNo}/product/${obj.productFileName}" id="product_file_download">${obj.productFileName}</a>
							    </c:if>			
						       <c:if test="${obj.productFileName==null}">
							     <span>整改完成的图纸、照片、视频：</span><span class="red">暂无</span>
							    </c:if>			
						</li>
						</c:if>						  											
						<li>
							<span>yangjieguang ： <span ${obj.yangReply == true ? 'class="blue"': 'class="red"'}>${obj.yangReply == true?obj.yangReplyContent:'暂无'}</span></span>							
						</li>																										
					</ul>
				</td>
			</tr>		
	  </c:forEach>	
		</tbody>
	</table>
	<input type="hidden" id="inputKey" name="inputKey" value=""> 
	<input type="hidden" id="pageStr" name="pageStr" value="${page == null ? 1 : page}"> 
	<input type="hidden" id="countPage" name="countPage" value="${lastNum}">
	<input type="hidden" id="pageSize" name="pageSize" value="100">
    </form>
	<div class="page-box">
		当前页/总页:<span style="color: red" id="pageNumberSpan">${page}</span>/
		<span id="countPageSpan" style="color: red">${lastNum}</span>&nbsp; 		
		<a class="emanagergetpagea first-padding" onclick="searchProjectData(1)" title="第一页">首页</a> 
		<a class="emanagergetpagea first-padding" <c:if test="${page > 1}">onclick="searchProjectData(2)"</c:if> title="上一页(第1页)">上页</a>
		<a class="emanagergetpagea" <c:if test="${page < lastNum}">onclick="searchProjectData(3)"</c:if> title="下一页(第3页)">下页</a> 
		<a class="emanagergetpagea last_page" onclick="searchProjectData(4)" title="最后一页">尾页</a>
		<!-- 跳转到第<input type="text" class="n" value="n">页 -->
	</div>
</div>	
</body>
</html>
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
<script>



//查询
function searchProjectData(obj){
	var pageNumber = $("#pageStr").val();
	var countPage = $("#countPage").val();
	var roleNo = $("#roleNo").val();
	var userName = $("#userName").val();
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
	var inputKey = $("#input_key").val();//关键字查询
	$('#planHtml').html('')
	$('#pageStr').val(pageNumber)
	$('#pageNumberSpan').text("")
    $('#countPage').val("")
    $('#countPageSpan').text("")
	$('#inputKey').val(inputKey);
	
	
	$('#form4').removeAttr('onsubmit');
	$('#form4').submit();   
}



//enter事件
document.onkeydown = function (e) {
    if (!e) e = window.event;
    if ((e.keyCode || e.which) == 13) {
    	searchProjectData(1);
    }
}
</script>







