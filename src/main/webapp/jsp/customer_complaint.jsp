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
<title>客户投诉列表</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="../css/test_report.css">
<link rel="stylesheet" href="../css/add.css">
</head>
<body>
<div class="plan_list customer_complain">
	<div class="complain_entry entry1">
		<h3><b>新投诉录入：</b><button class="btn pull-right btn_close">关闭</button></h3>
		<form id="form1" action="/complaint/addComplaint" method="post" onsubmit="return false;">
		    <input type="hidden" value="<%=request.getParameter("userName")%>" name="userName">
		    <input type="hidden" id="fileName" name="fileName">	  
		    <input type="hidden" id="filePath" name="filePath">  
			<div class="form-group group1 clearfix">
				<div class="col-sm-4 clearfix w300">
					<label>项目号</label>
					<input type="text" class="form-control pull-left w65" name="projectNo" id="projectNo">
				</div>
				<div class="col-sm-4 clearfix w300 w300_2">
					<label>投诉日期</label>
					<div class="add_date pull-left w65">
						<div class="input-group date form_date"
							data-date="" data-date-format="yyyy-mm-dd">
							<input id="complaintDate" name="complaintDate"
								class="form-control brt brt_7" size="16" type="text" value=""
								place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
								requiredate><span class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<span></span>
					</div>					
				</div>
			</div>
			<div class="form-group ">
				<label>投诉内容</label>
				<textarea class="form-control" name="complaintContent" id="complaintContent"></textarea>
			</div>
			<div class="btns clearfix">
				<div class="pull-left add_up">
					<input type="file" name="files" onchange="upload(this,1)"/><span style="left: 120px;"></span>
					<button class="btn">上传</button>
				</div>
				<button class="btn pull-right" onclick="addComplaint(this)">提交</button>
			</div>
		</form>
	</div>
	<div class="complain_entry edit_entry" style="display:none;">
		<h3><b>编辑投诉：</b><button class="btn pull-right btn_close">关闭</button></h3>
		<form id="form2" action="/complaint/updateComplaint" method="post" onsubmit="return false;">	
		 <input type="hidden" id="complaint_id" name="id">	  
		 <input type="hidden" value="1" name="type">	
		 <input type="hidden" id="fileName_edit" name="fileName">	  
		 <input type="hidden" id="filePath_edit" name="filePath">  
			<div class="form-group group1 clearfix">
				<div class="col-sm-4 clearfix w300">
					<label>项目号</label>
					<span class="form-control pull-left w65" id="projectNo_edit">项目号</span>					
				</div>
				<div class="col-sm-4 clearfix w300 w300_2">
					<label>投诉日期</label>
					<div class="add_date pull-left w65">
						<div class="input-group date form_date"
							data-date="" data-date-format="yyyy-mm-dd">
							<input id="complaintDate_edit" name="complaintDate"
								class="form-control brt brt_7" size="16" type="text" value=""
								place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
								requiredate><span class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<span></span>
					</div>					
				</div>
			</div>
			<div class="form-group clearfix">
				<div class="pull-left mr_65">
					<label>投诉内容</label>
					<textarea class="form-control" name="complaintContent" id="complaintContent_edit"></textarea>
				</div>
			</div>
			<div class="btns clearfix">
				<div class="pull-left add_up">
					<input type="file" name="files" onchange="upload(this,2)"/><span style="left: 120px;"></span>
					<button class="btn">上传</button>
				</div>
				<button class="btn pull-right" onclick="updateComplaint(this,1)">提交</button>
			</div>
		</form>
	</div>
	
	<div class="complain_entry edit_entry" style="display:none;">
		<h3><button class="btn pull-right btn_close">关闭</button></h3>
		<form id="form3" action="/complaint/updateComplaint" method="post" onclick="return false;">	
		 <input type="hidden" id="complaint_id2" name="id">	  
		 <input type="hidden" value="2" name="type">	  	  
			<div class="form-group group1 clearfix">
				<div class="col-sm-4 clearfix w300">
					<label>项目号</label>
					<span class="form-control pull-left w65" id="projectNo_edit2">项目号</span>					
				</div>
				<div class="col-sm-4 clearfix w300 w300_2">
					<label>投诉日期</label>
				    <span id="complaintDate_edit2"></span>				
				</div>
			</div>
			<div class="form-group clearfix">
				<div class="pull-left mr_65">
					<label>成本分析</label>
					<textarea class="form-control" name="costAnalysis" id="costAnalysis_edit"></textarea>
				</div>
			</div>
			<div class="btns clearfix">
				<button class="btn pull-right" onclick="updateComplaint(this,2)">提交</button>
			</div>
		</form>
	</div>
		<div class="title clearfix">
			<div class="div_h3 pull-left">客户投诉列表 <!-- <button class="add_btn btn">新投诉录入</button> --></div> 
 			<div class="logo pull-right"><img src="../img/logo.png"></div> 
 		</div>


<%-- 	<c:if test="${roleNo == 5 || roleNo == 100}"> --%>
<!-- 		<div class="title clearfix"> -->
<!-- 			<div class="div_h3 pull-left">客户投诉列表 <button class="add_btn btn">新投诉录入</button></div> -->
<!-- 			<div class="logo pull-right"><img src="../img/logo.png"></div> -->
<!-- 		</div> -->
<%-- 	</c:if> --%>
	<div class="btns_search clearfix">
		<div class="pull-left">
			<input class="form-control" placeholder="请输入项目号/人员/工厂" id="input_key" value="${inputKey}">
			<button class="form-control" onclick="searchProjectData(1)">查询</button>
		</div>
		<div class="pull-right">
			<button class="form-control" onclick="cleanSelect()">清除所有搜索条件</button>
			<a class="select_blank" target="_blank" href="/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}">返回功能选择页</a>
			<a href="###" ><button class="form-control last_btn" onclick="exitlogin()">退出系统</button></a>
		</div>

	</div>
	
	<form id="form4" action="/complaint/queryList" method="get" onsubmit="return false;">	
	<table class="table-bordered">
		<thead>
			<tr>
				<th class="th1">项目号</th>
				<th class="th2">投诉日期</th>
				<th class="th3">投诉内容</th>
				<th class="th4">成本分析</th>
				<th class="th5">处理完毕采购确认</th>
				<th class="th6">处理完毕跟单确认</th>
				<th class="th6">任务状况</th>
			</tr>
		</thead>
		<tbody id="tbody">
		<c:forEach var="obj" items="${complaintList}" varStatus="i">
			<tr>
				<td>${obj.projectNo}</td>
				<td><fmt:formatDate value="${obj.complaintDate}" pattern="yyyy-MM-dd"/></td>
				<td class="m_bot">
					<em title="${obj.complaintContent}">${obj.complaintContent}</em>
					<c:if test="${obj.sellId == userId || roleNo == 100}">
					<button class="pull-left w40" onclick="openEdit(this,'${obj.projectNo}','${obj.id}','<fmt:formatDate value="${obj.complaintDate}" pattern="yyyy-MM-dd"/>')">编辑</button>
					</c:if>					
					<c:if test="${obj.filePath != null}">
					  <a href="${ctx}/complaint/download?id=${obj.id}">下载链接</a>
					</c:if>
				</td>
				<td class="m_bot cost_analysis">
				    <input type="hidden" value="${obj.id}">
					<span class="pull-left w140" >${obj.costAnalysis}</span>
					<textarea class="pull-left w140 form-control">${obj.costAnalysis}</textarea>		
					<c:if test="${obj.purchaseId == userId || roleNo == 100}">			
					<%-- <button class="pull-left w40" onclick="openEditCost(this,'${obj.projectNo}','${obj.id}','<fmt:formatDate value="${obj.complaintDate}" pattern="yyyy-MM-dd"/>')">编辑</button> --%>
				    <button class="pull-left w40">编辑</button>
				    </c:if>
				</td>
				<td>
				<c:choose>
				    <c:when test="${obj.isPurchase == 1}">				
						<span class="pull-left  w155" title="${obj.purchaseContent}">${obj.purchaseContent}</span>
						<div class="center pull-left w84">
							<span style="display: block;">${obj.purchaseName}</span>
							<span>已确认</span>
							<span><fmt:formatDate value="${obj.purchaseConfirmDate}" pattern="yyyy-MM-dd"/></span>
						</div>
						<c:if test="${obj.purchaseId == userId}">
						   <button class="pull-left w40" onclick="purchase_cancel(this,'${obj.id}',0,3)">取消确认</button>
						</c:if>
					</c:when>
					<c:otherwise>
					   <c:if test="${obj.purchaseId == userId}">
							<textarea class="pull-left form-control w200"></textarea>					
							<div class="center pull-left w84">
								<span style="display: block;">${obj.purchaseName}</span>
								<span>未确认</span>
								<span></span>
							</div>
							   <button class="pull-left w40" onclick="purchase_cancel(this,'${obj.id}',1,3)">确认</button>	
						</c:if>				
					</c:otherwise>
				</c:choose>	
				</td>
				<td>
				<c:choose>
				    <c:when test="${obj.isSell == 1}">
						<span class="pull-left w155" title="${obj.sellContent}">${obj.sellContent}</span>
						<div class="center pull-left w84">
							<span style="display: block;">${obj.sellName}</span>
							<span>已确认</span>
							<span><fmt:formatDate value="${obj.sellConfirmDate}" pattern="yyyy-MM-dd"/></span>
						</div>
						<c:if test="${obj.sellId == userId}">
						   <button class="pull-left w40" onclick="sell_cancel(this,'${obj.id}',0,4)">取消确认</button>
						</c:if>
				    </c:when>
				    <c:otherwise>
					   <c:if test="${obj.sellId == userId}">
							<textarea class="pull-left form-control w200"></textarea>					
							<div class="center pull-left w84">
								<span style="display: block;">${obj.sellName}</span>
								<span>未确认</span>
								<span></span>
							</div>
							   <button class="pull-left w40" onclick="sell_cancel(this,'${obj.id}',1,4)">确认</button>	
						</c:if>				
					</c:otherwise>
				</c:choose>	
				</td>
				<td>
					<div class="contain_last">
				   <c:forEach begin="1" end="${obj.tasks.size()}" varStatus="j" step="1"> 				  
				      <a href="${ctx}/projectTask/selectProjectTaskById?id=${obj.tasks.get(j.count-1).id}&userId=${userId}&userName=${userName}&roleNo=${roleNo}">${obj.tasks.get(j.count-1).description} </a>  
				       <c:if test="${obj.tasks.get(j.count-1).taskStatus eq '0'}">
				             <span>(未完成)</span>
				       </c:if>
				       <c:if test="${obj.tasks.get(j.count-1).taskStatus eq '1'}">
				             <span>(已完成)</span>
				       </c:if>
				       <c:if test=" ${obj.tasks.get(j.count-1).taskStatus eq '2'}">
				             <span>(已暂停)</span>
				       </c:if>
				       <c:if test="${obj.tasks.get(j.count-1).taskStatus eq '3'}">
				             <span>(已取消)</span>
				       </c:if>
				   </c:forEach>	
				   </div>			  
				</td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
	<input type="hidden" id="inputKey" name="inputKey" value=""> 
	<input type="hidden" id="pageStr" name="pageStr" value="${page == null ? 1 : page}"> 
	<input type="hidden" id="countPage" name="countPage" value="${lastNum}">
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize == null ? 20 : pageSize}">
	<input type="hidden" value="${userName}" name="userName" id="userName">
    <input type="hidden" value="${userId}" name="userId" id="userId"> 
    <input type="hidden" value="${roleNo}" name="roleNo" id="roleNo">
    <input type="hidden" id="userName" value="${userName}">
	</form>
	<div class="page-box">
		总数:<span id="totalCount">${count}</span>每页:
		<select id="selectPage" name="selectPage" onchange="selectPageFunc()">
			<option value="5" <c:if test="${pageSize == 5}">selected</c:if>>5</option>
			<option value="10" <c:if test="${pageSize == 10}">selected</c:if>>10</option>
			<option value="20" <c:if test="${pageSize == 20}">selected</c:if>>20</option>
			<option value="50" <c:if test="${pageSize == 50}">selected</c:if>>50</option>
		</select>
		条 当前页/总页:<span style="color: red" id="pageNumberSpan">${page}</span>/<span id="countPageSpan" style="color: red">${lastNum}</span>&nbsp; <a href="#" class="emanagergetpagea first-padding" onclick="searchProjectData(1)" title="第一页">首页</a> <a href="#" class="emanagergetpagea first-padding" onclick="searchProjectData(2)" title="上一页(第1页)">上页</a>
		<a href="#" class="emanagergetpagea" onclick="searchProjectData(3)" title="下一页(第3页)">下页</a> <a href="#" class="emanagergetpagea last_page" onclick="searchProjectData(4)" title="最后一页">尾页</a>
		<!-- 跳转到第<input type="text" class="n" value="n">页 -->
	</div>

</div>
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-form.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
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
<script>

// $(function() {		
   
// })
//退出功能
function exitlogin() {
// 	$.cookie('name', '', {
// 		path : '/'
// 	});
// 	$.cookie('pass', '', {
// 		path : '/'
// 	});
	window.location.href = "${ctx}/index.jsp";
}

//返回主页
function goBack() {
    var roleNo = $("#roleNo").val();
    var userId = $("#userId").val();
    var userName = $("#userName").val();
    var purchaseNameId = "";
    window.location.href = "${ctx}/user/toIndex?userId=" + userId + "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId + "&userName=" + encodeURI(encodeURI(userName));
}
//清空所有条件
function cleanSelect(){	
	$("#input_key").val("");
   	$("#inputKey").val("");
	searchProjectData(1)	
}

//选择分页
function selectPageFunc() {
	var selectPage = $("#selectPage").val();
	$("#pageSize").val(selectPage);
	searchProjectData(1);
}

//查询
function searchProjectData(obj){
	var pageNumber = $("#pageStr").val();
	var countPage = $("#countPage").val();
	var roleNo = $("#roleNo").val();
	var userId = $("#userId").val();
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
</script>
<script>
	$('.add_btn').on('click',function(){
		$('.entry1').show();
	})
	$('.btn_close').on('click',function(){
		$(this).parents('.complain_entry').hide();
	})
	
	
	//打开投诉编辑窗口
	function openEdit(obj,projectNo,id,date){
		$('#complaint_id').val(id);
		$('#complaintDate_edit').val(date);
		$('#projectNo_edit').text(projectNo);
		$('#complaintContent_edit').text($(obj).prev().text());
		$('.edit_entry').eq(0).show();
	}
	
	//打开成本分析窗口
	/* function openEditCost(obj,projectNo,id,date){
		$('#complaint_id2').val(id);
		$('#complaintDate_edit2').text(date);
		$('#projectNo_edit2').text(projectNo);
		$('#costAnalysis_edit').text($(obj).prev().text());
		$('.edit_entry').eq(1).show();
	} */
	// 点击成本分析编辑和确认
	$('.cost_analysis button').siblings('textarea').hide()
	$('.cost_analysis button').click(function(){
		var btn_val = $(this).text();
		if(btn_val == '编辑'){
			$(this).text('确认');
			$(this).siblings('span').hide();
			$(this).siblings('textarea').show();
			$(this).attr('onclick','updateComplaint(this,2)');
		};
		
	});
</script>
<script type="text/javascript">

   //新增客户投诉（技术部杨工录入）
   function addComplaint(obj){
	   var projectNo = $('#projectNo').val();
	   var complaintDate = $('#complaintDate').val();
	   var complaintContent = $('#complaintContent').val();
	   if(!projectNo){
		   layer.msg("项目号不能为空",{time:2000});  
		   return false;
	   }
	   if(!complaintDate){
		   layer.msg("投诉日期不能为空",{time:2000});  
		   return false;
	   }
	   if(!complaintContent){
		   layer.msg("投诉内容不能为空",{time:2000});  
		   return false;
	   }

	   
	   $("#form1").ajaxSubmit({
			type : "post",
			url : "${ctx}/complaint/addComplaint",
			dataType : "text",
			async : false,
			success : function(result) {
// 				var result = eval('(' + str + ')');
				if (result.ok) {
		           window.location.reload();
				}else{
				  layer.msg(result.message,{time:2000});  
				}
			},
			error : function() {
				
			}
		});
   }
   
   
   
   
   
   //修改客户投诉
   function updateComplaint(obj,type){
	   
	   if(type == 1){
			   var id = $('#complaint_id').val();
			   var complaintDate = $('#complaintDate_edit').val();
			   var complaintContent = $('#complaintContent_edit').val();
			   if(id == null || id == ''){
				   layer.msg("修改id为空",{time:2000});  
				   return false;
			   }
			   if(!complaintDate){
				   layer.msg("投诉日期不能为空",{time:2000});  
				   return false;
			   }
			   if(!complaintContent){
				   layer.msg("投诉内容不能为空",{time:2000});  
				   return false;
			   }
	
			   
			   $("#form2").ajaxSubmit({
					type : "post",
					url : "${ctx}/complaint/updateComplaint",
					dataType : "text",
					async : false,
					success : function(result) {
// 						var result = eval('(' + str + ')');
						if (result.ok) {
				           window.location.reload();
						}else{
						  layer.msg(result.message,{time:2000});  
						}
					},
					error : function() {
						
					}
				});
	   }else if(type == 2){
		     var id = $(obj).parent().find('input:first').val();
		     var costAnalysis = $(obj).prev().val();
		     if(id == null || id == ''){
			    layer.msg("修改id为空",{time:2000});  
			    return false;
		     }
		     if(!costAnalysis){
			    layer.msg("成本分析不能为空",{time:2000});  
			    return false;
		     }
		     	
		     
			  $.ajax({
				    type:"post",                   
				    url:"${ctx}/complaint/updateComplaint",           
				    data:{
				    	id : id,
				    	costAnalysis : costAnalysis,
						type : type
				    	 },              
				    success:function(json){  
// 				    	var json = eval("(" + data + ")");
						if (json.ok) {
							$(obj).text('编辑');
							$(obj).siblings('span').text(costAnalysis).show();
							$(obj).siblings('textarea').hide();
							$(obj).removeAttr('onclick');
							window.location.reload();
						}else{
							layer.msg(data.message,{time:2000});  
						}
				    }
			  })
		     
		     
// 		     $("#form3").ajaxSubmit({
// 					type : "post",
// 					url : "${ctx}/complaint/updateComplaint",
// 					dataType : "text",
// 					async : false,
// 					success : function(str) {
// 						var result = eval('(' + str + ')');
// 						if (result.ok) {
							
// 							$(obj).text('编辑');
// 							$(obj).siblings('span').text(costAnalysis).show();
// 							$(obj).siblings('textarea').hide();
// 							$(obj).removeAttr('onclick');
							
// 				           window.location.reload();
// 						}else{
// 						  layer.msg(result.message,{time:2000});  
// 						}
// 					},
// 					error : function() {
						
// 					}
// 			 });
	   }
	
   }
   
   
   
   //采购确认与取消确认
   function purchase_cancel(obj,id,isPurchase,type){
	   
	   var userId = $('#userId').val();
	   //采购确认说明
	   var content = $(obj).prev().prev().val();
	   if(isPurchase == 1 && !content){
		   layer.msg("请写确认说明",{time:2000});  
		   return false;
	   }
		layer.open({
				type:1,
				skin:'finish-btn',
				title:'是否确认',
				anim:4,
				shade:0.6,
				shadeClose:true,
				btn:['确定','取消'],
			})		
			
		$('.finish-btn .layui-layer-btn0').click(function(){
			  $.ajax({
				    type:"post",                   
				    url:"${ctx}/complaint/updateComplaint",           
				    data:{
				    	userId : userId,
				    	purchaseContent : content,
						id : id,
						isPurchase : isPurchase,
						type : type
				    	 },              
				    success:function(json){  
// 				    	var json = eval("(" + data + ")");
						if (json.ok) {
							window.location.reload();
						}else{
							layer.msg(data.message,{time:2000});  
						}
				    }
			  })
				    	 
		})
   }
   
   
 //跟单确认与取消确认
   function sell_cancel(obj,id,isSell,type){
	   
	   var userId = $('#userId').val();
	   //跟单确认说明
	   var content = $(obj).prev().prev().val();
	   if(isSell == 1 && !content){
		   layer.msg("请写确认说明",{time:2000});  
		   return false;
	   }
		layer.open({
				type:1,
				skin:'finish-btn',
				title:'是否确认',
				anim:4,
				shade:0.6,
				shadeClose:true,
				btn:['确定','取消'],
			})		
			
		$('.finish-btn .layui-layer-btn0').click(function(){
			  $.ajax({
				    type:"post",                   
				    url:"${ctx}/complaint/updateComplaint",           
				    data:{
				    	userId : userId,
				    	sellContent : content,
						id : id,
						isSell : isSell,
						type : type
				    	 },              
				    success:function(json){  
// 				    	var json = eval("(" + data + ")");
						if (json.ok) {
							window.location.reload();
						}else{
							layer.msg(data.message,{time:2000});  
						}
				    }
			  })
				    	 
		})
   }
   
 
 
   //上传方法
   function upload(obj,type){
	   
	    var fileNames = $(obj).val();
		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		}
		// 先上传后获取上传文件路径
		$(obj).parents('form').ajaxSubmit({
			type : "post",
			url : "${ctx}/upload/uploadComplaint",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
					var filePath = result.data.path;
					var fileName = result.data.originalFilename;
				    $(obj).next().text(fileName);
				    //1：新增上传
				    //2：修改上传
				    if(type == 1){
				    	$('#filePath').val(filePath);
						$('#fileName').val(fileName);
				    }else if(type == 2){
				    	$('#filePath_edit').val(filePath);
						$('#fileName_edit').val(fileName);
				    }
					
				}
			},
			error : function() {
				
			}
		});
   }
 
   
</script>





