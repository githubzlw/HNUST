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
<title>电子准予出货确认单-检验</title>
<link rel="stylesheet" href="../bootstrap.min.css">
<link rel="stylesheet" href="../add.css">
</head>
<body>
<form action="/complaint/addFirst" method="post">
<input type="hidden" value="second" name="step">
<input type="hidden" value="${shippingConfirmation.id}" name="id">
<div class="confirm_list confirm_list_second">
	<div class="div_transparent"></div>
	<h1 class="customer_complaint_h1">
	<span>${shippingConfirmation.projectNo}</span>-电子出货确认单
	<div class="btns">
		<a class="select_blank btn" href="/complaint/detail?id=${shippingConfirmation.id}">返回确认单</a>				
	</div>	
	</h1>	
	<h3 class="mt10 mb10"><b class="mr10">第二步：检验结果复述</b>提供人：<span>检验</span></h3>	
	<div class="form-group clearfix report">
		<label class="pull-left">已录质检报告：</label>
		<select class="form-control pull-left mr10" id="report">
		  <option></option>
		  <c:forEach var="obj" items="${reports}" varStatus="i">
			<option value="${obj.id}">${obj.type == 0 ? '样品' : (obj.type == 1 ? '大货' : (obj.type == 2 ? '中期' : (obj.type == 3 ? '终期' : '')))}_<fmt:formatDate value="${obj.checkDate}" pattern="yyyy/MM/dd"/>的报告</option> 
		  </c:forEach>
		</select>
		<button type="button" class="btn btn-default bgcolor_ff0" onclick="importReport()">导入输入框</button>
	</div>
	<div class="form-group clearfix ">
		<label class="pull-left">抽检产品数量：</label>
		<input type="text" filed="抽检产品数量" class="form-control pull-left" name="checkQty" value="${shippingConfirmation.checkQty}"/>
	</div>
	<div class="form-group clearfix">
		<label class="pull-left">产品总数：</label>
		<input type="text" filed="产品总数" class="form-control pull-left" name="orders" value="${shippingConfirmation.orders}"/>
	</div>
	<div class="form-group clearfix">
			<label class="pull-left">包装数量：</label>
			<input type="text" filed="包装数量" class="form-control pull-left" name="boxNumber" value="${shippingConfirmation.boxNumber}"/>
	</div>
	<div class="form-group clearfix">
			<label class="pull-left">开箱数量：</label>
			<input type="text" filed="开箱数量" class="form-control pull-left" name="openQty" value="${shippingConfirmation.openQty}"/>
	</div>
	<div class="form-group clearfix">
			<label class="pull-left">检验费时：</label>
			<input type="text" filed="检验费时" class="form-control pull-left" name="spendTime" value="${shippingConfirmation.spendTime}"/>
	</div>
	<div class="form-group clearfix last">
			<label class="pull-left ">发现的问题和比例：</label>
			<input type="text" class="form-control pull-left" name="checkConclusion" value="${shippingConfirmation.checkConclusion}"/>
	</div>
	<div class="form-group clearfix textarea_value">
			<label>哪些预定的检验没能执行：</label>
			<textarea class="form-control mt10" name="noExecuted">${shippingConfirmation.noExecuted}</textarea>
	</div>
	<div class="form-group clearfix textarea_value">
			<label>哪些问题是让步接受的：</label>
			<textarea class="form-control mt10" name="concessiveAccept">${shippingConfirmation.concessiveAccept}</textarea>
	</div>
	<button class="btn btn-default bgcolor_ff0 tj" type="button"  onclick="save('${shippingConfirmation.id}',this)">提交</button>
</div>	
</form>
</body>
</html>
<script src="../jquery.min.js"></script>
<script src="../bootstrap.min.js"></script>
<script type="text/javascript" src="../jquery-form.js"></script>
<script src="../layer.js" type="text/javascript" charset="utf-8"></script>



<script type="text/javascript">
  function save(id,obj){	   
	    if(!id){
		   layer.msg("id不能为空",{time:2000});  
		   return false;
	    }
	    
	    
	    //判断不能为空
	    var flag = true;
	    $("input[name='checkQty'],input[name='orders'],input[name='boxNumber'],input[name='openQty'],input[name='spendTime']").each(function(){	    	 
	    	 var name = $(this).attr('filed');
	    	 if(!$(this).val()){
	    		 layer.msg(name+"不能为空",{time:2000});  
	    		 flag = false;
	    		 return false;
	    	 }
	    })
	    if(!flag){
	    	return false;
	    }
	    
	    
	   
		$(obj).parents('form').ajaxSubmit({
				type : "post",
				url : "/complaint/addFirst",
				dataType : "text",
				success : function(result) {
					var json = eval("(" + result + ")");
					  if(json.ok){
						  layer.msg("录入成功",{time:2000});  
						  window.location = '/complaint/detail?id='+id;
					  }else if(json.message == '您还未登录'){
						  var a = location.href;
						  a = encodeURIComponent(a);
						  window.location = '/index.jsp?purchase_history='+a;
					  }else{
						  layer.msg(json.message,{time:2000});  
					  }    
				},
				error : function() {
					layer.msg("录入失败",{time:2000});
				}
								
			});
  }
  
  
  
  
  //根据id导入质检报告数据
  function importReport(){
	 var reportId = $('#report').val(); 
     if(!reportId){
		   layer.msg("质检报告ID不能为空",{time:2000});  
		   return false;
	 }
	 
	 $.ajax({
		    type:"post",                   
		    url:"${ctx}/quality/queryByReportId",           
		    data:{
		    	  reportId:reportId
		    	 },              
		    success:function(json){  
// 		    	var json = eval("(" + data + ")");
				if (json.ok) {
					var report = json.data;
					if(report){
						$('input[name="checkQty"]').val(report.inventoryQty);
						$('input[name="orders"]').val(report.orders);
						$('input[name="boxNumber"]').val(report.boxNumber);
						$('input[name="openQty"]').val(report.openQty);
						$('input[name="spendTime"]').val(report.spendTime);
						$('input[name="checkConclusion"]').val(report.conclusion);
					}				
				}else{
					layer.msg(data.message,{time:2000});  
				}
		    }
	})
	 
  }
  
</script>


					







