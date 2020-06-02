<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
  String roleNo=request.getParameter("roleNo");
  String userId=request.getParameter("userId");
  String userName=request.getParameter("userName");
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>项目首页</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css" />
<script type="text/javascript" src="${ctx}/js/common.js"></script>
</head>
<body>
	<div class="project">
	<div class="go-back" onclick="window.history.back(-1);"></div>
		<div class="project-tit">项目汇总</div>
		<input type="hidden" id="roleNo" value="<%= roleNo%>">
		<input type="hidden" id="userId" value="<%= userId%>">
		<input type="hidden" id="userName" value="<%= userName%>">
		<div class="project-content">
			<div class="project-task">
				<div class="key-search toggle-time">
					<label for="input-key1" class="left control-label">开始时间：</label> <input
						type="text" class="left input-text form-control date-time" id="input-key1" placeholder="请选择开始时间">
				</div>
				<div class="key-search toggle-time">
					<label for="input-key2" class="left control-label">结束时间：</label> <input
						type="text" class="left input-text form-control date-time" id="input-key2" placeholder="请选择结束时间" onchange="selectOnchange()">
				</div>
				<div class="key-search">
					<label for="input-key3" class="left control-label">筛选条件：</label> <select
						onchange="selectOnchange()" class="left input-text form-control" id="input-key3">
						<option value="1">交期变更项目</option>
						<option value="2">采购情况汇报</option>
						<option value="3">未设定实际交期项目</option>
						<option value="4">出货延期项目</option>
						<option value="5">缺少开工图片项目</option>
						<option value="6">缺少任务汇报项目</option>
					</select>
					<button class="left btn btn-primary time-select" >筛选时间</button>
				</div>
				<div class="select-value">
					<p>
						<span class="select-tit left">交期变更项目</span><span
							class="project-number right">0(条)</span>
					</p>
				</div>
				<div class="project-main">
				  
				</div>
			</div>
		</div>
	</div>
	<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
	<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	Date.prototype.pattern=function(fmt) {         
	    var o = {         
	    "M+" : this.getMonth()+1, //月份         
	    "d+" : this.getDate(), //日         
	    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
	    "H+" : this.getHours(), //小时         
	    "m+" : this.getMinutes(), //分         
	    "s+" : this.getSeconds(), //秒         
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
	    "S" : this.getMilliseconds() //毫秒         
	    };         
	    var week = {         
	    "0" : "/u65e5",         
	    "1" : "/u4e00",         
	    "2" : "/u4e8c",         
	    "3" : "/u4e09",         
	    "4" : "/u56db",         
	    "5" : "/u4e94",         
	    "6" : "/u516d"        
	    };         
	    if(/(y+)/.test(fmt)){         
	        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
	    }         
	    if(/(E+)/.test(fmt)){         
	        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
	    }         
	    for(var k in o){         
	        if(new RegExp("("+ k +")").test(fmt)){         
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
	        }         
	    }         
	    return fmt;         
	} 
	</script>
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
			};
			
			$(function () {
				var currYear = (new Date()).getFullYear();	
				var opt={};
				opt.date = {preset : 'date'};
				opt.datetime = {preset : 'datetime'};
				opt.time = {preset : 'time'};
				opt.default = {
					theme: 'android-ics light', //皮肤样式
					display: 'modal', //显示方式 
					mode: 'scroller', //日期选择模式
					dateFormat: 'yyyy-mm-dd',
					lang: 'zh',
					showNow: true,
					nowText: "今天",
					startYear: currYear - 1, //开始年份
					endYear: currYear + 50 //结束年份
				};
				//日期时间调用
				$(".date-time").mobiscroll($.extend(opt['date'], opt['default']));
				//底部table切换
				$.Huitab(".project-bottom span",".project-content .project-task","project-active","click","0");
				//选择select的值对应到相应的列表标题
				$("#input-key3").val(1);
				selectOnchange();
				//时间筛选
				$('.time-select').click(function(){
					$('.toggle-time').toggle();
				});
				
			});
			
			function selectOnchange(){
				$('.select-tit').html($("#input-key3 option:selected").text());
				$('.project-main').empty();
				$(".project-number").empty();
				var roleNo=$("#roleNo").val();
				var userId=$("#userId").val();
				var startDate=$("#input-key1").val();
				var endDate=$("#input-key2").val();
				var value=$("#input-key3").val();
				var userName=$("#userName").val();
				var index;
				$.ajax({
					type:'post',
					url:'${ctx}/project/projectStatistics',
					data:{
						roleNo:roleNo,
						userId:userId,
						value:value,
						startDate:startDate,
						endDate:endDate
					},
					beforeSend:function(){
						index=layer.load();
					},
					success:function(json){
						layer.close(index);
// 						var json = eval("(" + data +")");
						  if(json.ok){	  
							  var dataList=json.data;
							  var roleNo=json.comment;
							  var count;
							  if(dataList==null){
								  count=0;
							  }else{
								  count=dataList.length;
							  }				
							  $(".project-number").text(count);
							  var str = '';
							  for(var i=0;i<dataList.length;i++){
								  str +='<a href="${ctx}/project/showDetails?projectNo='+dataList[i].projectNo+'&userName='+userName+'&userId='+userId+'&roleNo='+roleNo+'" class="project-link"><p class="p-row"><span class="span-left">项目名称：</span><span class="span-right">'
									+ dataList[i].projectName
									+'</span></p>'
								  +'<p><span class="span-left">项目号：</span><span class="span-right">'
								  + dataList[i].projectNo
										+'</span></p>'
										+'<p><span class="span-left">'
										if(roleNo==5 && dataList[i].purchaseName!=null){
											str+='采购人员：</span><span class="span-right">'+ dataList[i].purchaseName
										}else if((roleNo==6 || roleNo==69 || roleNo == 1020) && dataList[i].sellName!= null && dataList[i].sellName!=''){
											str+='销售人员：</span><span class="span-right">'+ dataList[i].sellName
										}else{
											if(dataList[i].purchaseName!=null && dataList[i].roleName!=null){
												str+='采购人员：</span><span class="span-right">'+ dataList[i].purchaseName+'</p><p><span class="span-left">销售人员：</span><span class="span-right">'+ dataList[i].roleName
											}else if(dataList[i].purchaseName!=null && dataList[i].roleName==null){
												str+='采购人员：</span><span class="span-right">'+ dataList[i].purchaseName+'</p><p><span class="span-left">销售人员：</span><span class="span-right">'
											}else if(dataList[i].purchaseName==null && dataList[i].roleName!=null){
												str+='采购人员：</span><span class="span-right">'+''+'</p><p><span class="span-left">销售人员：</span><span class="span-right">'+ dataList[i].roleName
											}else{
												str+='采购人员：</span><span class="span-right">'+''+'</p><p><span class="span-left">销售人员：</span><span class="span-right">'
											}
											
										}
										str+= '</span></p><p><span class="span-left">项目交期：</span><span class="span-right">'
										if(dataList[i].deliveryDate!=null &&dataList[i].deliveryDate!=''){
											str+= (new Date(dataList[i].deliveryDate).pattern("yyyy-MM-dd"))
										}
										str+= '</span></p><p class="p-row"><span class="span-left">负责状态：</span><span class="span-right"> '
										if(dataList[i].deliveryStatus==0){
											str += "准时,";
										} else if(dataList[i].deliveryStatus==1){
											str += "超期,";
										} else if(dataList[i].deliveryStatus==2){
											str += "暂停,";
										}
										if(dataList[i].warningStatus==0){
											str += "无,";
										} else if(dataList[i].warningStatus==1){
											str += "质量投诉,";
										} else if(dataList[i].warningStatus==2){
											str += "交期预警,";
										}
										
										if(dataList[i].importance==0){
											str += "一般,";
										} else if(dataList[i].importance==1){
											str += "重要,";
										}
										if(dataList[i].stage==0){
											str += "样品,";
										} else if(dataList[i].stage==1){
											str += "大货,";
										} else if(dataList[i].stage==2){
											str += "成熟,";
										}
										if(dataList[i].finish==0){
											str += "未完成,";
										} else if(dataList[i].finish==1){
											str += "已完成";
										}
										str +='</span></p></a>'
							  }
							  $('.project-main').append(str);
						  }else{
							  layer.msg(json.message,{time:2000});
						  }  
					}
		    	});
			}
	</script>
</body>
</html>