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
		<title>新增人员</title>
		<link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
		<link rel="stylesheet" href="css/add.css">		
	</head>
<body>
	<div class="person_new_add">
		<h3><b>新增人员</b></h3>
		<section>
		<form id="form" action="${ctx}/syncUser/addUser" class="form-inline clearfix" method="post">
		<input type="hidden" name="jsonStr" id="userInfo">
			<div class="pull-left div_l w350">
				<h4>基本信息：</h4>
				<div class="form-group mb_35">
					<label for="">用户名</label>
					<input type="text" class="form-control" name="userName" id="userName" onblur="check(this)">
					<span class="tip">用户名要求2-12位数字和字母组成</span>
				</div><br/>
				<div class="form-group mb_35">
					<label for="">登录密码</label>
					<input type="password" class="form-control" name="password" id="pwd">
					<span class="tip">密码长度必须是 6-15 位</span>
				</div><br/>
				<div class="form-group">
					<label for="">工号</label>
					<input  class="form-control" name="jobNumber" id="jobnumber">					
				</div><br/>
				<div class="form-group">
					<label for="">邮箱</label>
					<input type="email" class="form-control" name="emailAddress" id="email">					
				</div><br/>
				<div class="form-group">
					<label for="">邮箱密码</label>
					<input class="form-control" name="emailPwd" id="email_pwd">					
				</div>
				<div class="form-group">
					<label>状态：</label>
					<label class="state_label"><input type="radio" name="flag" value="1" checked>在职</label>
					<label class="state_label"><input type="radio" name="flag" value="0">非在职</label>				
				</div>
				<h4>辅助信息：</h4>
				<div class="form-group">
					<label for="">中文名</label>
					<input type="text" class="form-control" id="real_name" name="trueName">
				</div><br/>
				<div class="form-group">
					<label for="">电话号码</label>
					<input type="number" class="form-control" id="phone" name="phone">					
				</div><br/>
				<div class="form-group ">
					<label for="" class="pull-left">生日</label>
					<div class="pull-left">
						<div class="input-group date form_date"
							data-date="" data-date-format="yyyy-mm-dd">
							<input id="birthday"  name="birthday"  
								class="form-control brt brt_7" size="16" type="text" value=""
								place="选择日期" field="报价截止日期" placeholder="选择日期" readonly
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
							<td><label><input type="checkbox" value='3'>销售</label></td>
							<td><label><input type="checkbox" value='4'>报价</label></td>
							<td><label><input type="checkbox" value='4'>采购</label></td>
							<td><label><input type="checkbox" value='3'>跟单</label></td>
							<td><label><input type="checkbox" value='3'>助理</label></td>
							<td><label><input type="checkbox" value='22'>技术</label></td>
							<td><label><input type="checkbox" value='4'>质检</label></td>
							<td><select class="form-control">
							        <option value="1">普通人</option>
									<option value="11">领导</option>									
								</select>
							</td>
						</tr>
						<tr filed="taskRole">
							<td> MYIT#1 任务管理系统 </td>
						    <td><label><input type="checkbox" value='5'>销售</label></td>
							<td><label><input type="checkbox" value='10'>报价</label></td>
							<td><label><input type="checkbox" value='6'>采购</label></td>
							<td><label><input type="checkbox" value='5'>跟单</label></td>
							<td><label><input type="checkbox" value='5'>助理</label></td>
							<td><label><input type="checkbox" value='7'>技术</label></td>
							<td><label><input type="checkbox" value='9'>质检</label></td>
							<td><select class="form-control">
						        	<option value="1">普通人</option>
									<option value="100">领导</option>									
								</select>
							</td>
						</tr>
						<tr filed="crmRole">
							<td> MYIT#2 CRM </td>
						    <td><label><input type="checkbox" value='5'>销售</label></td>
							<td><label><input type="checkbox" value='10'>报价</label></td>
							<td><label><input type="checkbox" value='6'>采购</label></td>
							<td><label><input type="checkbox" value='5'>跟单</label></td>
							<td><label><input type="checkbox" value='5'>助理</label></td>
							<td><label><input type="checkbox" value='7'>技术</label></td>
							<td><label><input type="checkbox" value='9'>质检</label></td>
							<td><select class="form-control">
									<option value="1">普通人</option>
									<option value="100">领导</option>
								</select>
							</td>
						</tr>
						<tr filed="quotationRole">
							<td> MYIT#3 内部报价系统 </td>
						    <td><label><input type="checkbox" value='5'>销售</label></td>
							<td><label><input type="checkbox" value='10'>报价</label></td>
							<td><label><input type="checkbox" value='10'>采购</label></td>
							<td><label><input type="checkbox" value='5'>跟单</label></td>
							<td><label><input type="checkbox" value='9'>助理</label></td>
							<td><label><input type="checkbox" value='7'>技术</label></td>
							<td><label><input type="checkbox" value='9'>质检</label></td>
							<td><select class="form-control">
									<option value="1">普通人</option>
									<option value="99">领导</option>
								</select>
							</td>
						</tr>
						<tr filed="kuaiRole">
							<td> MYIT#4 快制造 </td>
						    <td><label><input type="checkbox" value='5'>销售</label></td>
							<td><label><input type="checkbox" value='10'>报价</label></td>
							<td><label><input type="checkbox" value='6'>采购</label></td>
							<td><label><input type="checkbox" value='5'>跟单</label></td>
							<td><label><input type="checkbox" value='5'>助理</label></td>
							<td><label><input type="checkbox" value='7'>技术</label></td>
							<td><label><input type="checkbox" value='9'>质检</label></td>
							<td><select class="form-control">
									<option value="1">普通人</option>
									<option value="100">领导</option>
								</select>
							</td>
						</tr>
						<tr filed="nbRole">
							<td> NBEMAIL  MYIT#5 </td>
						    <td><label><input type="checkbox" value='5'>销售</label></td>
							<td><label><input type="checkbox" value='10'>报价</label></td>
							<td><label><input type="checkbox" value='6'>采购</label></td>
							<td><label><input type="checkbox" value='5'>跟单</label></td>
							<td><label><input type="checkbox" value='9'>助理</label></td>
							<td><label><input type="checkbox" value='7'>技术</label></td>
							<td><label><input type="checkbox" value='9'>质检</label></td>
							<td><select class="form-control">
									<option value="1">普通人</option>
									<option value="99">领导</option>
								</select>
							</td>
						</tr>
						<tr filed="bonusRole">
							<td> 分红系统  MYIT#6 </td>
						    <td><label><input type="checkbox" value='5'>销售</label></td>
							<td><label><input type="checkbox" value='10'>报价</label></td>
							<td><label><input type="checkbox" value='6'>采购</label></td>
							<td><label><input type="checkbox" value='4'>跟单</label></td>
							<td><label><input type="checkbox" value='9'>助理</label></td>
							<td><label><input type="checkbox" value='10'>技术</label></td>
							<td><label><input type="checkbox" value='7'>质检</label></td>
							<td><select class="form-control">
									<option value="1">普通人</option>
									<option value="100">领导</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div class="btns clearfix">
			<button class="btn pull-left" onclick="window.location='${ctx}/syncUser/showAllUser'">关闭</button>
			<button class="btn pull-right" onclick="add()">提交</button>
		</div>
		</section>
		
	</div>
</body>
</html>
<script src="lib/jquery/jquery.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
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
	
	//验证用户名是否存在
	var flag = true;
	var p_flag = true;
	var p_jobnumber = false;
	function check(obj){
		 
		  if(!obj.val()){
			  layer.msg("用户名不能为空",{time:2000}); 	
			  return false;
		  }
		  
	      $.ajax({
			     type:"post",                   
			     url:"${ctx}/syncUser/checkUser",           
			     data:{
			    	 projectNo:projectNo
			     },              
			     success:function(data){
			    	 var json = eval("(" + data +")");
					 if(json.ok){
						 flag = true;
					 }else{
						 flag = false;
						 layer.msg(json.message,{time:2000}); 
					 }
			     }
			   })	
	}
	
	
	    
	
	//密码失焦事件   
	$('#pwd').blur(function(){
		var pwd = $('#pwd').val();
		var psdReg = /^[0-9a-zA-Z_]{6,15}$/;//密码正则
		if (!psdReg.test(pwd)) {
			p_flag = false;
			layer.msg("密码长度必须是 6-15 位",{time:2000}); 
			return;
		}
		p_flag = true;
	})
	
	$('#jobnumber').blur(function(){
		var jobnumber = $('#jobnumber').val();
		if(!jobnumber){
			p_jobnumber = false;
			layer.msg("工号不为空",{time:2000}); 
			return;	
		}
		p_jobnumber = true;
	})
	
	
	
	//添加用户
	function add(){
		
		if(!flag){
			layer.msg("用户名已存在",{time:2000}); 	
			return false;
		}	
		
		if(!p_flag){
			layer.msg("密码长度必须是 6-15 位",{time:2000}); 
			return false;
		}
		
		if(!p_jobnumber){
			layer.msg("工号不能为空",{time:2000});
			return false;
		}
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






