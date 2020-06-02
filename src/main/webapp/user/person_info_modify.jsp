<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>修改信息</title>
		<link rel="stylesheet" href="${ctx}/user/lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/user/lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
		<link rel="stylesheet" href="${ctx}/user/css/add.css">	
		<style>
			.state input[type="radio"]{position:relative;top:8px;}
		</style>	
	</head>
<body>
	<div class="person_new_add">
		<h3><b>修改信息</b></h3>
		<section>
		<form id="form" action="${ctx}/syncUser/updateUser" class="form-inline clearfix" method="post">
		<input type="hidden" name="jsonStr" id="userInfo">
		<input type="hidden" name="id" value="${user.id}">
			<div class="pull-left div_l w350">
				<h4>基本信息：</h4>
				<div class="form-group mb_35">
					<label for="">用户名</label>
					<input type="text" class="form-control" name="userName" id="userName" value="${user.userName}"> 
					<span class="tip">用户名要求2-12位数字和字母组成</span>
				</div><br/>
				<div class="form-group mb_35">
					<label for="">登录密码</label>
					<input class="form-control" name="password" id="pwd" value="${user.password}">
					<span class="tip">密码长度必须是 6-15 位</span>
				</div><br/>
				<div class="form-group">
					<label for="">工号</label>
					<input type="text" class="form-control" name="jobNumber" id="jobnumber" value="${user.jobNumber }">					
				</div>
				<div class="form-group">
					<label for="">邮箱</label>
					<input type="email" class="form-control" name="emailAddress" id="email" value="${user.emailAddress}">					
				</div><br/>
				<div class="form-group">
					<label for="">邮箱密码</label>
					<input class="form-control" name="emailPwd" id="email_pwd" value="${user.emailPwd}">					
				</div>
				<div class="form-group state">
					<label>状态：</label>
					<label class="state_label"><input type="radio" name="flag" value="1" <c:if test="${user.flag == 1}">checked</c:if>>在职</label>
					<label class="state_label"><input type="radio" name="flag" value="0" <c:if test="${user.flag == 0}">checked</c:if>>非在职</label>				
				</div>
				<h4>辅助信息：</h4>
				<div class="form-group">
					<label for="">中文名</label>
					<input type="text" class="form-control" id="real_name" name="trueName"  value="${user.trueName}">
				</div><br/>
				<div class="form-group">
					<label for="">电话号码</label>
					<input type="number" class="form-control" id="phone" name="phone" value="${user.phone}">					
				</div><br/>
				<div class="form-group ">
					<label for="" class="pull-left">生日</label>
					<div class="pull-left">
						<div class="input-group date form_date"
							data-date="" data-date-format="yyyy-mm-dd">
							<input id="birthday"  name="birthday"  
								class="form-control brt brt_7" size="16" type="text"  value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>"
								place="选择日期" field="报价截止日期" placeholder="选择日期"
								requiredate>  <span class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<span></span>
					</div>
				</div>
			</div>
			<div class="pull-right div_r w850">
				<div class="form-group group_top">
					<label>角色赋予模板选择:</label>
					<label><input type="checkbox" value="5">销售</label>
					<label><input type="checkbox" value="10">报价</label>
					<label><input type="checkbox" value="6">采购</label>
					<label><input type="checkbox" value="5">跟单</label>					
					<label><input type="checkbox" value="5">助理</label>
					<label><input type="checkbox" value="7">技术</label>
					<label><input type="checkbox" value="9">质检</label>
					<select class="form-control">
						<option value="1">普通人</option>
						<option value="100">领导</option>
					</select>
				</div>
				<table class="table table-bordered group-bottom">
					<thead>
						<tr>
							<th>系统列表</th>
							<th colspan="8">权限内容</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<tr filed="erpRole">
							<td> MYIT#0 ERP</td>
							<td><label><input type="checkbox" value='3' <c:if test="${user.erpRole == 3 && user.job == '销售'}">checked</c:if>>销售</label></td>
							<td><label><input type="checkbox" value='4' <c:if test="${user.erpRole == 4 && user.job == '报价'}">checked</c:if>>报价</label></td>
							<td><label><input type="checkbox" value='4' <c:if test="${user.erpRole == 4 && user.job == '采购'}">checked</c:if>>采购</label></td>
							<td><label><input type="checkbox" value='3' <c:if test="${user.erpRole == 3 && user.job == '跟单'}">checked</c:if>>跟单</label></td>
							<td><label><input type="checkbox" value='3' <c:if test="${user.erpRole == 3 && user.job == '助理'}">checked</c:if>>助理</label></td>
							<td><label><input type="checkbox" value='22' <c:if test="${user.erpRole == 22 && user.job == '技术'}">checked</c:if>>技术</label></td>
							<td><label><input type="checkbox" value='4' <c:if test="${user.erpRole == 4 && user.job == '质检'}">checked</c:if>>质检</label></td>
							<td><select class="form-control">
							        <option value="1">普通人</option>
									<option value="11" <c:if test="${user.erpRole == 11}">selected</c:if>>领导</option>									
								</select>
							</td>
						</tr>
						<tr filed="taskRole">
							<td> MYIT#1 任务管理系统 </td>
						    <td><label><input type="checkbox" value='5' <c:if test="${user.taskRole == 5 && user.job == '销售'}">checked</c:if>>销售</label></td>
							<td><label><input type="checkbox" value='10' <c:if test="${user.taskRole == 10 && user.job == '报价'}">checked</c:if>>报价</label></td>
							<td><label><input type="checkbox" value='6' <c:if test="${user.taskRole == 6 && user.job == '采购'}">checked</c:if>>采购</label></td>
							<td><label><input type="checkbox" value='5' <c:if test="${user.taskRole == 5 && user.job == '跟单'}">checked</c:if>>跟单</label></td>
							<td><label><input type="checkbox" value='5' <c:if test="${user.taskRole == 5 && user.job == '助理'}">checked</c:if>>助理</label></td>
							<td><label><input type="checkbox" value='7' <c:if test="${user.taskRole == 7 && user.job == '技术'}">checked</c:if>>技术</label></td>
							<td><label><input type="checkbox" value='9' <c:if test="${user.taskRole == 9 && user.job == '采购'}">checked</c:if>>质检</label></td>
							<td><select class="form-control">
							        <option value="1">普通人</option>
									<option value="100" <c:if test="${user.erpRole == 100}">selected</c:if>>领导</option>									
								</select>
							</td>
						</tr>
						<tr filed="crmRole">
							<td> MYIT#2 CRM </td>
						    <td><label><input type="checkbox" value='5' <c:if test="${user.crmRole == 5 && user.job == '销售'}">checked</c:if>>销售</label></td>
							<td><label><input type="checkbox" value='10' <c:if test="${user.crmRole == 10 && user.job == '报价'}">checked</c:if>>报价</label></td>
							<td><label><input type="checkbox" value='6' <c:if test="${user.crmRole == 6 && user.job == '采购'}">checked</c:if>>采购</label></td>
							<td><label><input type="checkbox" value='5' <c:if test="${user.crmRole == 5 && user.job == '跟单'}">checked</c:if>>跟单</label></td>
							<td><label><input type="checkbox" value='5' <c:if test="${user.crmRole == 5 && user.job == '助理'}">checked</c:if>>助理</label></td>
							<td><label><input type="checkbox" value='7' <c:if test="${user.crmRole == 7 && user.job == '技术'}">checked</c:if>>技术</label></td>
							<td><label><input type="checkbox" value='9' <c:if test="${user.crmRole == 9 && user.job == '质检'}">checked</c:if>>质检</label></td>
							<td><select class="form-control">
							        <option value="1">普通人</option>
									<option value="100" <c:if test="${user.crmRole == 100}">selected</c:if>>领导</option>									
								</select>
							</td>
						</tr>
						<tr filed="quotationRole">
							<td> MYIT#3 内部报价系统 </td>
						    <td><label><input type="checkbox" value='5' <c:if test="${user.quotationRole == 5 && user.job == '销售'}">checked</c:if>>销售</label></td>
							<td><label><input type="checkbox" value='10' <c:if test="${user.quotationRole == 10 && user.job == '报价'}">checked</c:if>>报价</label></td>
							<td><label><input type="checkbox" value='10' <c:if test="${user.quotationRole == 10 && user.job == '采购'}">checked</c:if>>采购</label></td>
							<td><label><input type="checkbox" value='5' <c:if test="${user.quotationRole == 5 && user.job == '跟单'}">checked</c:if>>跟单</label></td>
							<td><label><input type="checkbox" value='9' <c:if test="${user.quotationRole == 9 && user.job == '助理'}">checked</c:if>>助理</label></td>
							<td><label><input type="checkbox" value='7' <c:if test="${user.quotationRole == 7 && user.job == '技术'}">checked</c:if>>技术</label></td>
							<td><label><input type="checkbox" value='9' <c:if test="${user.quotationRole == 9 && user.job == '质检'}">checked</c:if>>质检</label></td>
							<td><select class="form-control">
							        <option value="1">普通人</option>
									<option value="99" <c:if test="${user.quotationRole == 99}">selected</c:if>>领导</option>									
								</select>
							</td>
						</tr>
						<tr filed="kuaiRole">
							<td> MYIT#4 快制造 </td>
						    <td><label><input type="checkbox" value='5' <c:if test="${user.kuaiRole == 5 && user.job == '销售'}">checked</c:if>>销售</label></td>
							<td><label><input type="checkbox" value='10' <c:if test="${user.kuaiRole == 10 && user.job == '报价'}">checked</c:if>>报价</label></td>
							<td><label><input type="checkbox" value='6' <c:if test="${user.kuaiRole == 6 && user.job == '采购'}">checked</c:if>>采购</label></td>
							<td><label><input type="checkbox" value='5' <c:if test="${user.kuaiRole == 5 && user.job == '跟单'}">checked</c:if>>跟单</label></td>
							<td><label><input type="checkbox" value='5' <c:if test="${user.kuaiRole == 5 && user.job == '助理'}">checked</c:if>>助理</label></td>
							<td><label><input type="checkbox" value='7' <c:if test="${user.kuaiRole == 7 && user.job == '技术'}">checked</c:if>>技术</label></td>
							<td><label><input type="checkbox" value='9' <c:if test="${user.kuaiRole == 9 && user.job == '质检'}">checked</c:if>>质检</label></td>
							<td><select class="form-control">
							        <option value="1">普通人</option>
									<option value="100" <c:if test="${user.kuaiRole == 100}">selected</c:if>>领导</option>									
								</select>
							</td>
						</tr>
						<tr filed="nbRole">
							<td> NBEMAIL  MYIT#5 </td>
						    <td><label><input type="checkbox" value='5' <c:if test="${user.nbRole == 5 && user.job == '销售'}">checked</c:if>>销售</label></td>
							<td><label><input type="checkbox" value='10' <c:if test="${user.nbRole == 10 && user.job == '报价'}">checked</c:if>>报价</label></td>
							<td><label><input type="checkbox" value='6' <c:if test="${user.nbRole == 6 && user.job == '采购'}">checked</c:if>>采购</label></td>
							<td><label><input type="checkbox" value='5' <c:if test="${user.nbRole == 5 && user.job == '跟单'}">checked</c:if>>跟单</label></td>
							<td><label><input type="checkbox" value='9' <c:if test="${user.nbRole == 9 && user.job == '助理'}">checked</c:if>>助理</label></td>
							<td><label><input type="checkbox" value='7' <c:if test="${user.nbRole == 7 && user.job == '技术'}">checked</c:if>>技术</label></td>
							<td><label><input type="checkbox" value='9' <c:if test="${user.nbRole == 9 && user.job == '质检'}">checked</c:if>>质检</label></td>
							<td><select class="form-control">
							        <option value="1">普通人</option>
									<option value="99" <c:if test="${user.nbRole == 99}">selected</c:if>>领导</option>									
								</select>
							</td>
						</tr>
						<tr filed="bonusRole">
							<td> 分红系统  MYIT#6 </td>
						   <td><label><input type="checkbox" value='5' <c:if test="${user.taskRole == 5 && user.job == '销售'}">checked</c:if>>销售</label></td>
							<td><label><input type="checkbox" value='10' <c:if test="${user.taskRole == 10 && user.job == '报价'}">checked</c:if>>报价</label></td>
							<td><label><input type="checkbox" value='6' <c:if test="${user.taskRole == 6 && user.job == '采购'}">checked</c:if>>采购</label></td>
							<td><label><input type="checkbox" value='4' <c:if test="${user.taskRole == 4 && user.job == '跟单'}">checked</c:if>>跟单</label></td>
							<td><label><input type="checkbox" value='9' <c:if test="${user.taskRole == 9 && user.job == '助理'}">checked</c:if>>助理</label></td>
							<td><label><input type="checkbox" value='10' <c:if test="${user.taskRole == 10 && user.job == '技术'}">checked</c:if>>技术</label></td>
							<td><label><input type="checkbox" value='7' <c:if test="${user.taskRole == 7 && user.job == '质检'}">checked</c:if>>质检</label></td>
							<td><select class="form-control">
							        <option value="1">普通人</option>
									<option value="100" <c:if test="${user.taskRole == 100}">selected</c:if>>领导</option>									
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div class="btns clearfix">
			<button class="btn pull-left" onclick="window.location='${ctx}/syncUser/showAllUser'">关闭</button>
			<button class="btn pull-right" onclick="update('${user.id}')">提交</button>
		</div>
		</section>
		
	</div>
</body>
</html>
<script src="${ctx}/user/lib/jquery/jquery.min.js"></script>
<script src="${ctx}/user/lib/bootstrap/js/bootstrap.min.js"></script>
<!-- <script src="layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script> -->
<script src="${ctx}/user/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/user/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
	/* 日历插件*/
	$('.form_date').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 4,
		forceParse : 0
	});
	$('.table-condensed tbody,.table-condensed tfoot').on('click',function(){
		$('.datetimepicker').hide();
	})
	
	
	
	
	function update(){		
		//信息数据
        var data = {};		
		$('#form').find('input[type!=hidden][type!=checkbox]').each(function() {	
			    if($(this).attr('name') == 'flag'){
			    	if($(this).is(":checked")){
			    		data[$(this).attr('name')] = $(this).val();
			    	}
			    }else{
			    	data[$(this).attr('name')] = $(this).val();
			    }									
		})
		
		//各系统权限
		$('#tbody').find('tr').each(function(){
			 This = $(this);
			 This.find(':checkbox').each(function(i){
				 if($(this).is(":checked")){
					 data[This.attr('filed')] = $(this).val();
					 data['job'] = $(this).parent().text();
					 //如果是领导，统一最高权限
					 if(This.find('select').val() == 100){
						 data[This.attr('filed')] = 100;
					 }
				 }
			 })

		})
		$('#userInfo').val(JSON.stringify(data));
		$('#form').submit();
	}
	
	
	
	
	
	//标题checkbox选中事件
	$('div .group_top').find(':checkbox').change(function(){
		var i = $('div .group_top').find(':checkbox').index($(this));
		if ($(this).is(":checked")) {
		    //操作
		    $('#tbody').find('tr').each(function(){
		    	 $(this).find(':checkbox').each(function(j){
		    		  if(i == j){
		    			  $(this).prop('checked',true);
		    		  }
		    	 })
		    })
		}else{
			//操作
		    $('#tbody').find('tr').each(function(){
		    	 $(this).find(':checkbox').each(function(j){
		    		  if(i == j){
		    			  $(this).removeAttr('checked');
		    		  }
		    	 })
		    })
		}
	})
	
	//选择事件
	$('div .group_top').find('select').change(function(){
		var val = $(this).val();	
		$('#tbody').find('tr').each(function(){
			 $(this).find('option').each(function(){
				 if(val == $(this).val()){
					 $(this).prop('selected',true);
				 } 
			 })
		})
	})
</script>






