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
<title>电子准予出货确认单-销售采购</title>
<link rel="stylesheet" href="../bootstrap.min.css">
<link rel="stylesheet" href="../add.css">
<style>
.change label:first-child{width:170px;}
.relative_top{position:relative;top:5px;}
@media screen and (max-width: 415px) and (min-width: 376px){
   .confirm_list .customer_complaint_h1 {
      font-size: 24px;
	}
}

</style>
</head>
<body>
<form action="/complaint/addFirst" method="post">
<input type="hidden" value="first" name="step">
<input type="hidden" value="${shippingConfirmation.id}" name="id">
<div class="confirm_list confirm_list_first">
	<div class="div_transparent"></div>
	<h1 class="customer_complaint_h1">
	<span>${shippingConfirmation.projectNo}</span>电子出货确认单
	<div class="btns">
		<a class="select_blank btn" href="/complaint/detail?id=${shippingConfirmation.id}">返回确认单</a>			
	</div>	
	</h1>	
	<h3 class="mt10 mb10"><b class="mr10">第一步：基本的出货信息   </b>提供人：(跟单/采购)</h3>	
	
	<div class="form-group clearfix ">
		<label class="pull-left relative_top">项目号：</label>
		<span class="pull-left">${shippingConfirmation.projectNo}</span>
	</div>
	<div class="form-group clearfix">
		<label class="pull-left relative_top">客户名称：</label>
		<input type="text" filed="客户名称" class="form-control pull-left" name="customerName" value="${shippingConfirmation.customerName == null ? shippingConfirmation.customerNameProject : shippingConfirmation.customerName}"/>
	</div>
	<div class="form-group clearfix">
			<label class="pull-left relative_top">货物名称：</label>
			<input type="text" filed="货物名称" class="form-control pull-left" name="productName" value="${shippingConfirmation.productName == null ? project.projectName : shippingConfirmation.productName}"/>
	</div>
	<div class="form-group clearfix">
			<label class="pull-left relative_top">项目金额：</label>
			<input type="text" filed="项目金额" placeholder="注意单位" style="width:200px;" class="form-control pull-left" name="projectAmount" value="${project.projectAmount == null ? project.projectAmount : project.projectAmount}"/>
			<em class="relative_top">万美元</em>
	</div>
	<div class="form-group clearfix">
			<label class="pull-left relative_top">实发数量：</label>
			<input type="text" filed="实发数量" class="form-control pull-left" name="productNumber" value="${shippingConfirmation.productNumber }"/>
	</div>
	<div class="form-group clearfix">
			<label class="pull-left relative_top">本产品单件(套)重量：</label>
			<input type="text" filed="本产品单件(套)重量" class="form-control pull-left" name="weight" value="${shippingConfirmation.weight }"/>kg
	</div>
	<div class="form-group clearfix">
			<label class="pull-left">出货类型：</label>
			
			<label><input type="radio" name="sampleOrProduct" value="1" <c:if test="${shippingConfirmation.sampleOrProduct ==1}">checked</c:if>>大货</label>
			<label><input type="radio" name="sampleOrProduct" value="0" <c:if test="${shippingConfirmation.sampleOrProduct ==0}">checked</c:if>>样品</label>
		    
	</div>
	<div class="form-group clearfix">
			<label class="pull-left">材质类型：</label>
			<%-- <select style="width: 83px;height: 26px;" name="isPlastic">
			    <option value="0" <c:if test="${shippingConfirmation.isPlastic == 0}">selected</c:if>>其他</option>
				<option value="1" <c:if test="${shippingConfirmation.isPlastic == 1}">selected</c:if>>塑料件</option>				
			</select> --%>
			<label><input type="radio" name="isPlastic" value="0" <c:if test="${shippingConfirmation.isPlastic ==0}">checked</c:if>>其他</label>
			<label><input type="radio" name="isPlastic" value="1" <c:if test="${shippingConfirmation.isPlastic ==1}">checked</c:if>>塑料件</label>
		    
	</div>
	<div class="form-group clearfix transport">
			<label class="pull-left">运输方式：</label>
			<label><input type="radio" name="shippingWay" value="海运" <c:if test="${shippingConfirmation.shippingWay eq '海运'}">checked</c:if><c:if test="${shippingConfirmation.shippingWay == null || shippingConfirmation.shippingWay == ''}">checked</c:if>>海运</label>
			<label><input type="radio" name="shippingWay" value="空运" <c:if test="${shippingConfirmation.shippingWay eq '空运'}">checked</c:if>>空运</label>
		    <label><input type="radio" name="shippingWay" value="快递" <c:if test="${shippingConfirmation.shippingWay eq '快递'}">checked</c:if>>快递</label>
	</div>

    <div class="form-group clearfix sure">
			<label class="pull-left"> 本次产品是否有改动:</label>
			<c:if test="${shippingConfirmation.productChanges == 0}">
			<label><input type="radio" name="productChanges" <c:if test="${shippingConfirmation.productChanges==0}">checked</c:if> value="0" >否</label>			
			<label><input type="radio" name="productChanges" value="1" >是</label></c:if>			
	        <c:if test="${shippingConfirmation.productChanges == 1}"><label>是</label></c:if>
	</div>

	<div class="form-group clearfix transport">
			<label class="pull-left">发货前有没有发小批量给客户：</label><br/>
			<label><input type="radio" name="shippingInformation" value="有发" <c:if test="${shippingConfirmation.shippingInformation eq '有发'}">checked</c:if>>有发</label>
			<label><input type="radio" name="shippingInformation" value="没发" <c:if test="${shippingConfirmation.shippingInformation eq '没发'}">checked</c:if>>没发</label>
		   <br/>
		    <label style="width:140px;"><input type="radio" name="shippingInformation" value="本批就是小批量" <c:if test="${shippingConfirmation.shippingInformation eq '本批就是小批量'}">checked</c:if>>本批就是小批量</label>
	</div>
<%-- 	<div class="form-group clearfix sure">
			<label class="pull-left"> 是否需要ED最终确认:</label>
			<label><input type="radio" name="isBossConfirm" value="0" <c:if test="${shippingConfirmation.isBossConfirm == 0}">checked</c:if>>否</label>			
			<label><input type="radio" name="isBossConfirm" value="1" <c:if test="${shippingConfirmation.isBossConfirm == 1}">checked</c:if>>是</label>			
	</div> --%>

	<button class="btn btn-default bgcolor_ff0 tj" type="button" onclick="save('${shippingConfirmation.id}',this,'${param.userName}')">提交</button>
</div>	
</form>
</body>
</html>
<script src="../jquery.min.js"></script>
<script src="../bootstrap.min.js"></script>
<script type="text/javascript" src="../jquery-form.js"></script>
<script src="../layer.js" type="text/javascript" charset="utf-8"></script>



<script type="text/javascript">
  function save(id,obj, userName){
	    if(!id){
		   layer.msg("id不能为空",{time:2000});  
		   return false;
	    }
	    //判断不能为空
	    var flag = true;
	    $("input[name='customerName'],input[name='productName'],input[name='projectAmount'],input[name='productNumber'],input[name='weight']").each(function(){	    	 
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
	    $("input[name='shippingInformation']").each(function(){	    	 
	    	var shippingInformation = $("input[name='shippingInformation']:checked").val();
	    	 if(!shippingInformation){
	    		 layer.msg("发货前有没有发小批量给客户:不能为空",{time:2000}); 
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
						  window.location = '/complaint/detail?id='+id + '&userName=' + userName;
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

					







