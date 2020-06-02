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
<title>电子准予出货确认单-跟单销售确认</title>
<link rel="stylesheet" href="../bootstrap.min.css">
<link rel="stylesheet" href="../add.css">

</head>
<body>
<form action="/complaint/addFirst" method="post">
<input type="hidden" value="fourth" name="step">
<input type="hidden" value="${shippingConfirmation.id}" name="id">
<div class="confirm_list confirm_list_four">
	<div class="div_transparent"></div>
	<h1 class="customer_complaint_h1">
	<span>${shippingConfirmation.projectNo}</span>-电子出货确认单
	<div class="btns">
		<a class="btn btn-default"  href="/complaint/detail?id=${shippingConfirmation.id}">返回确认单</a>				
	</div>	
	</h1>	
	<h3 class="mt10 mb10"><b class="mr10">第四步：跟单销售确认</b>提供人：<span>跟单/采购</span></h3>
	<div class="form-group clearfix">
		<label class="pull-left">客户欠我们多少钱：</label>		
		<input type="text" class="form-control pull-left mr10" name="notPaid" value="${shippingConfirmation.notPaid}">		
		<span>$美元</span>
	</div>
	<div class="form-group clearfix">
		<label class="pull-left">占总销售额比例：</label>
		<input type="text" class="form-control pull-left mr10" name="scale" value="${shippingConfirmation.scale}">		
		<span>%</span>
	</div>	
	<div class="form-group pass">
		<label>大货已经通过客户初步确认：</label>
		<label class="mr20"><input type="radio" name="customerConfirmWay" class="mr10" <c:if test="${shippingConfirmation.customerConfirmWay eq '通过照片'}">checked</c:if><c:if test="${shippingConfirmation.customerConfirmWay == null}">checked</c:if> value="通过照片">通过照片</label>
		<label><input type="radio" name="customerConfirmWay" class="mr10" <c:if test="${shippingConfirmation.customerConfirmWay eq '通过快递大货样'}">checked</c:if> value="通过快递大货样">通过快递大货样</label>
	</div>	
	<div class="form-group pass">
		<label>英文版检验报告：</label>
		<label class="mr20"><input type="radio" name="isQualityReportEn" class="mr10" <c:if test="${shippingConfirmation.isQualityReportEn == 0}">checked</c:if><c:if test="${shippingConfirmation.isQualityReportEn == null}">checked</c:if> value="0">没提供</label>
		<label class="mr20"><input type="radio" name="isQualityReportEn" class="mr10" <c:if test="${shippingConfirmation.isQualityReportEn == 1}">checked</c:if> value="1">已经提供</label>
		<label class="mr20"><input type="radio" name="isQualityReportEn" class="mr10" <c:if test="${shippingConfirmation.isQualityReportEn == 2}">checked</c:if> value="2">一天内来补</label>		
		<label><input type="radio" name="isQualityReportEn" class="mr10" <c:if test="${shippingConfirmation.isQualityReportEn == 3}">checked</c:if> value="3">包装材料符合客户要求并保护得当 . 标签与内容相符合，唛头
符合要求 .</label>		
	</div>
	<div class="form-group clearfix">
		<label class="pull-left">报关退税品名：</label>
		<input type="text" class="form-control pull-left name" name="drawbackProduct" value="${shippingConfirmation.drawbackProduct}">		
	</div>	
	<div class="form-group clearfix">
		<label class="pull-left">退税率：</label>
		<input type="text" class="form-control pull-left mr10" name="drawbackRate" value="${shippingConfirmation.drawbackRate}">		
		<span>%</span>
	</div>		
	<div class="form-group clearfix">
		<label class="pull-left">海运或空运费金额：</label>
		<input type="text" class="form-control pull-left mr10" name="shippingFee" value="${shippingConfirmation.shippingFee}">		
		<span>$美元</span>
	</div>
		
	<div class="form-group textarea_value">
		<label class="">销售对质量的担忧：</label>
		<textarea class="form-control mt10" name="salesWorry">${shippingConfirmation.salesWorry}</textarea>
	</div>
	<button class="btn btn-default bgcolor_ff0 tj"  type="button"  onclick="save('${shippingConfirmation.id}',this)">提交</button>
<!-- 	<button class="btn btn-default bgcolor_ff0 tj">已提交，点我修改</button> -->
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
</script>
					







