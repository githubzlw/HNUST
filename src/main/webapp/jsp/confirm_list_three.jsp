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
<title>电子准予出货确认单-出货质量分析会</title>
<link rel="stylesheet" href="../bootstrap.min.css">
<link rel="stylesheet" href="../add.css">
</head>
<body>
<form action="/complaint/addFirst" method="post">
<input type="hidden" value="third" name="step">
<input type="hidden" value="${shippingConfirmation.id}" name="id">
<div class="confirm_list confirm_list_second confirm_list_three">
	<div class="div_transparent"></div>
	<h1 class="customer_complaint_h1">
	<span>${shippingConfirmation.projectNo}</span>-电子出货确认单
	<div class="btns">
		<a class="select_blank btn" href="/complaint/detail?id=${shippingConfirmation.id}">返回确认单</a>			
	</div>	
	</h1>	
	<h3 class="mt10 mb10"><b class="mr10">第三步：出货质量分析会的决议如下</b>提供人：<span>检验/跟单</span></h3>	
	<div class="form-group clearfix report">
		<label class="pull-left">已录会议信息：</label>
		<select class="form-control pull-left mr10" id="meeting">
		  <option></option>
		  <c:forEach var="obj" items="${meetings}" varStatus="i">
		    <option></option>
			<option filed="${obj.meetingDescribe}">会议编号：<span>${obj.id}</span>_<span>${obj.meetingDescribe}</span></option> 
		  </c:forEach>
		</select>		
		<button type="button" class="btn btn-default bgcolor_ff0" onclick="importMeeting()">导入输入框</button>
	</div>		
	<div class="form-group clearfix textarea_value">
			<textarea class="form-control" name="meetingConclusion"></textarea>
	</div>
	<button class="btn btn-default bgcolor_ff0 tj" type="button" onclick="save('${shippingConfirmation.id}',this)">提交</button>
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
    	 if(!$('textarea[name="meetingConclusion"]').val()){
    		 layer.msg("会议内容不能为空",{time:2000});  
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
  function importMeeting(){
	 var content = $('#meeting').find("option:selected").attr('filed');
	 if(content){
		 $('textarea[name="meetingConclusion"]').val(content);
	 }	
  }
  
</script>

					







