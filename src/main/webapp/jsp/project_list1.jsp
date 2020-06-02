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
		<title>项目首页</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<style>.span-right {display: initial;float: none;}</style>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
	</head>
	<body>
		<!--遮罩层-->
		<div id="mask"></div>
		<div class="index-main">
			<div class="index-top">
				<div><span class="index-active">项目列表</span><span id="tsak-list">采购任务列表</span></div>
				 
			</div>
		    <input type="hidden" id="roleNo" value="${roleNo }">
		    <input type="hidden" id="userId" value="${userId }">
		    <input type="hidden" id="userName" value="${userName }">
		    <input type="hidden" id="purchaseNameId" value="${purchaseNameId }">
		    <input type="hidden" id="pageSize" value="10">
		    <input type="hidden" id="pageNumber" value="1">
		    <input type="hidden" id="totalCount" value="10">
		    <input type="hidden" id="operatorType" value="0">
			<div class="index-middle">
				 <div class="div-bg">	
				  <div class="key-search">
						<label for="input-key" class="left control-label">关键字搜索：</label>
						<input type="text" class="left input-text form-control" id="inputKey" value="" placeholder="项目号/项目名/姓名"/>
						<button class="left btn btn-primary" id="searchBut">搜索</button>
			    	</div>
			    	<div class="index-link">
						<p class="index-height">
							<span class="span-left">项目总数：</span><span class="span-left" id="projectNum"></span>
							<span class="span-left">延期总数：</span><span class="span-left animation-scale" id="delayNum"></span>
						</p>
					</div>
					<div class="index-link">
					  <p style="line-height:0.5rem ;height: 0.60rem;margin: 0.20rem;">
					      <span class="span-left" id="selectProjectType">
						   
                          </span>
                          
                          <span class="span-right" style="float:right;" id="purchaseSelect">
                          </span>
                            <input type="hidden" id="purchaseSelectValue" value="">
                       </p>
					</div>
				</div>
				<!-- 项目列表 -->
				<div class="index-task" id="projectDataList">
				
				</div>
				<!-- 任务列表显示 -->
				<div class="index-task" id="projectTaskList">
				
				</div>
			</div>
			<div class="index-footer">
				<!-- <span class="footer-list-one" id="messageCenter">消息中心</span> -->
				<span class="footer-list-one" id="addProjectTask">录入任务</span>
				<span style="background:blue;" id="projectTaskTechnologyList">极简任务列表</span>
				<span class="footer-list-one" id="inputMeetingRecord">录入会议</span>
				<span style="background:blue;" id="meetingRecordList">会议列表</span>
				<span class="footer-list-two">项目统计</span>	
			</div>	
		</div>
		<div class="index-mask">
			<a href="javascript:;" id="projectSummary" class="footer-margin">项目汇总</a>
			<a href="javascript:;" class="footer-cancel">取消</a>
		</div>
		<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
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
	var queryObj ={"preNum":1,"isPre":false,"nextNum":2,"isNext":false,"curNum":0,"operatorType":0};
	var isDoQuery = false;
     //一进来加载默认数据
     $(function(){
        //projectDataLoad(1);
        var indexHeight;
        //判断PC端还是移动端
        var sUserAgent = navigator.userAgent.toLowerCase();
    	var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
    	var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
    	var bIsMidp = sUserAgent.match(/midp/i) == "midp";
    	var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
    	var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
    	var bIsAndroid = sUserAgent.match(/android/i) == "android";
    	var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
    	var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
    	if(!(bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM)) {
    		indexHeight=1042;
    	} else {
    		indexHeight=500;
    	}
        showload();
        $(".index-middle").scroll(function() {
        	var txHeight=$('#projectDataList').height();
        	var projectNum=$("#projectNum").text();
        	var hh=$(this).scrollTop();
		        if (hh+indexHeight>txHeight&&$('#projectDataList').find('dl').length<=projectNum) {
		        	if(isDoQuery){
		        		return false;
		        	}else{		
		        		isDoQuery = true;
		        		queryObj.operatorType =0;
		        		showload();
		        	}		        	
		        	txHeight=$('#projectDataList').height();
		        }else{
		        	return false;
		        }
		     console.log(txHeight+'=e==='+hh);
		     console.log("dl count : " + $("#projectDataList").find('dl').length)
		  });
     });
     
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
		$('#mask').click(function(){
			$('#mask').hide(200);
			$('.index-mask').hide(200);
		})
		$('.footer-cancel').click(function(){
			$('#mask').hide(200);
			$('.index-mask').hide(200);
		})
		//页面查询按钮
		$("#projectSummary").click(function(){
			 var roleNo=$("#roleNo").val();
			 var userId=$("#userId").val();
			 var userName=$("#userName").val();
			 window.location.href="${ctx}/jsp/project-summary.jsp?roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
		})
		
        //页面查询按钮
		$("#searchBut").click(function(){
			 var totalCount = Number($("#totalCount").val());
			 if(totalCount == 0){
				$("#totalCount").val(1)
			 }
			 queryObj.operatorType =1;
			 queryObj.preNum =1;
			 queryObj.isPre =false;
			 queryObj.nextNum =1;
			 queryObj.isNext =false;
			 queryObj.curNum =0;
			 showload();
		})
		//根据选择的采购查询列表
		function selectOnchange(obj){
			var purchaseSelect=$(obj).find('option:selected').val();
		    $("#purchaseSelectValue").val(purchaseSelect);
		    $("#purchaseNameId").val('');
		    var totalCount = Number($("#totalCount").val());
			if(totalCount == 0){
				$("#totalCount").val(1)
			}
		    queryObj.operatorType =1;
			queryObj.preNum =1;
			queryObj.isPre =false;
			queryObj.nextNum =1;
			queryObj.isNext =false;
			queryObj.curNum =0;
			showload();
	    }

		//消息中心
		$("#messageCenter").click(function(){
			 var roleNo=$("#roleNo").val();
			 var userId=$("#userId").val();
			 var userName=$("#userName").val();
			 $("#messageNum").val('0');
			 window.location.href="${ctx}/jsp/message.jsp?roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
		})
		//进入录入任务页面
		$("#addProjectTask").click(function(){
			 var roleNo=$("#roleNo").val();
			 var userId=$("#userId").val();
			 var userName=$("#userName").val();
			 window.location.href="${ctx}/jsp/input_project_task.jsp?roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
		});
		//任务列表页
		$("#projectTaskTechnologyList").click(function(){
			var roleNo=$("#roleNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			window.location.href="${ctx}/projectTask/projectTaskList?roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
		})
		//会议录入界面
		$("#inputMeetingRecord").click(function(){
			 var roleNo=$("#roleNo").val();
			 var userId=$("#userId").val();
			 var userName=$("#userName").val();
			 window.location.href="${ctx}/jsp/input_meeting_record.jsp?roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
		});
		//会议列表页面
		$("#meetingRecordList").click(function(){
			 var roleNo=$("#roleNo").val();
			 var userId=$("#userId").val();
			 var userName=$("#userName").val();
			 window.location.href="${ctx}/meetingRecord/selectMeetingRecordList?roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
		});
		function projectDataLoad(pageNumber){
			  var operatorType = queryObj.operatorType;
			  isDoQuery = true;
			  var roleNo=$("#roleNo").val();
			  var userId=$("#userId").val();        
			  var userName=$("#userName").val();
			  var projectType=$("#projectType").val();	
			  var pageSize=$("#pageSize").val();
			  var totalCount=$("#totalCount").val();
			  var inputKey=$("#inputKey").val();//关键字查询
			  var purchaseSelectValue=$("#purchaseSelectValue").val();//采购列表查询
			  var purchaseNameId;
			  if(inputKey!=null && inputKey!='' && inputKey!=undefined){
				  inputKey=$("#inputKey").val(); 
			  }
			  if(purchaseSelectValue!=null && purchaseSelectValue!='' && purchaseSelectValue!=undefined && purchaseSelectValue!='全部采购'){
				  purchaseNameId=$("#purchaseSelectValue").val();
			  }else{
				  purchaseNameId=$("#purchaseNameId").val();
			  }
			  if((queryObj.preNum == pageNumber && !queryObj.isPre) || (queryObj.nextNum == pageNumber && !queryObj.isNext) ){
				  doQuery(pageNumber,roleNo,userId,inputKey,purchaseNameId,userName,projectType,operatorType);
			  }else{
				  isDoQuery = false;
				  return false;
			  }
			  
	     	}
		
		
		function doQuery(pageNumber,roleNo,userId,inputKey,purchaseNameId,userName,projectType,operatorType){
			  $.ajax({
					type:'post',
					url:'${ctx}/project/showList',
					sync : true,
					data:{
						pageNumber:pageNumber,roleNo:roleNo,userId:userId,inputKey:inputKey,
						purchaseNameId:purchaseNameId,userName:userName,projectType:projectType,operatorType:operatorType
					},
					beforeSend:function(){
						index=layer.load();
					},
					success:function(data){
					   layer.close(index);
					   var json = eval("(" + data +")");
					   var projectDataList=json.data.list;//项目数据
					   var delayNum=json.data.delayNum; //延期数目
					   var messageNum=json.data.messageNum;//消息数目
					   var projectNum=json.data.projectNum;//项目数量
					   var projectType=json.data.projectType; //状态类型(完成,未完成,暂停)
					   var purchaseList=json.data.purchaseList; //采购筛选列表
					   var roleNo=json.data.roleNo;//角色代码
					   var taskList=json.data.taskList;//任务列表
					   var userId=json.data.userId;//用户Id
					   var userName=json.data.userName;//用户名
					   var pageSize=json.data.pageSize;
					   var pageNumber=json.data.pageNumber;
					   var totalCount=json.data.totalCount;
					   var purchaseNameId=json.data.purchaseNameId;//采购姓名查询
					   $("#purchaseSelect").empty();//采购下拉列表
					   $("#selectProjectType").empty();
					   if(operatorType==1){ // 代表是查询操作要清空
						   $("#projectDataList").empty();
						   $("#projectTaskList").empty();
						   $("#selectProjectType").empty();
					   }else{
						   
					   }
					   if(json.ok){	  
						   var dataHtml = '';
						   var taskHtml='';
						   if(projectDataList.length > 0){
							   for(var i=0;i<projectDataList.length;i++){//项目列表
								   dataHtml +='<dl class="index-list"><dd><p class="pc_p"><span class="span-left"></span><span class="span-right porject-number">'+projectDataList[i].projectNo+'</span></p>'
								   dataHtml +='<p class="pc_p"><span class="span-left"></span><span class="span-right project-name">'+projectDataList[i].projectName+'</span></p>'
								   dataHtml +='<p class="pc_p"><span class="span-left"></span><span class="span-right project-name">'+projectDataList[i].companyName+'</span></p>'
								   if(roleNo==5){
									   dataHtml+='<p class="pc_p"><span class="span-left">采购：</span><span class="span-right project-person">'+projectDataList[i].purchaseName+'</span></p>'
								   }else if(roleNo==6){
									   dataHtml+='<p class="pc_p"><span class="span-left">跟单销售：</span><span class="span-right project-person">'+projectDataList[i].sellName+'</span></p>'	
								   }else{
									   dataHtml+='<p class="pc_p"><span class="span-left">跟单销售：</span><span class="span-right project-person">'+projectDataList[i].trueName+'</span></p>'
									   dataHtml+='<p class="pc_p"><span class="span-left">采购：</span><span class="span-right project-person">'+projectDataList[i].purchaseName+'</span></p>'  	
								   }
								   if(projectDataList[i].deliveryDate!=null &&projectDataList[i].deliveryDate!=''){
									   dataHtml+='<p class="pc_p"><span class="span-left">交期：</span><span class="span-right project-date">'+(new Date(projectDataList[i].deliveryDate).pattern("yyyy-MM-dd")) +'</span></p>'
								   }else{
									   dataHtml+='<p class="pc_p"><span class="span-left">交期：</span><span class="span-right project-date"></span></p>'
								   }
								  
								   dataHtml+='<p class="project-status pc_p"><span class="span-left">状态：</span><span class="span-right project-state">'
									    if(projectDataList[i].deliveryStatus==1){
											dataHtml += "延期,";
										} else if(projectDataList[i].deliveryStatus==2){
											dataHtml += "暂停,";
										}
										if(projectDataList[i].warningStatus==1){
											dataHtml += "质量投诉,";
										} 
										if(projectDataList[i].importance==1){
											dataHtml += "重要,";
										}
										if(projectDataList[i].stage==0){
											dataHtml += "样品,";
										} else if(projectDataList[i].stage==1){
											dataHtml += "大货,";
										} else if(projectDataList[i].stage==2){
											dataHtml += "成熟,";
										}
										if(projectDataList[i].finish==1){
											dataHtml += "完成";
										}
										if(projectDataList[i].isPause!=null){
											if(projectDataList[i].isPause==1){
												dataHtml += "项目暂停:&nbsp;"+projectDataList[i].pauseReason+'</span></p></a>';
											}
											if(projectDataList[i].isPause==2){
												dataHtml += "项目取消:&nbsp;"+projectDataList[i].pauseReason+'</span></p></a>';
											}
										}
										dataHtml+='<input type="hidden" value="'+projectDataList[i].flag+'" class="project-flag">'
										if(projectDataList[i].flag==1){
											dataHtml += '<p class="pc_p"><span class="span-left warning-project">项目快到期了</span></p>'
										}
										if(projectDataList[i].flag==2){
											dataHtml += '<p class="pc_p"><span class="span-left delay-project">项目已延期</span></p>'
										}
										if(projectDataList[i].projectReportDate !=null){
											dataHtml +='<p class="pc_p"><span class="span-left delay-project">周报更新时间:'+(new Date(projectDataList[i].projectReportDate).pattern("yyyy-MM-dd")) +'</span></p>'
										}
										if(projectDataList[i].projectReportList.length>0){
											dataHtml +='<p class="pc_p"><span class="span-left delay-project" style="color:green">已更新周报</span></p>';
										}else{
											dataHtml +='<p class="pc_p"><span class="span-left delay-project" style="color:red">未更新周报</span></p>';
										}
										dataHtml+='<p class="detail-link"><a class="left btn btn-primary" href="${ctx}/project/showDetails?projectNo='+projectDataList[i].projectNo+'&roleNo='+roleNo+'&userId='+userId+'&userName='+userName+'&purchaseNameId='+purchaseNameId+'">详情</a>'
										if(projectDataList[i].taskList.length>0){
											dataHtml+='<a class="left btn btn-primary" style="width:27%;margin-left:5px;" href="${ctx}/project/projectTask?projectNo='+projectDataList[i].projectNo+'&roleNo='+roleNo+'&userId='+userId+'&userName='+userName+'&purchaseNameId='+purchaseNameId+'">有'+projectDataList[i].taskList.length+'条新任务</a>'
										}
										if(projectDataList[i].projectDrawingList.length>0){
											dataHtml+='<span><a class="left btn btn-primary" href="#" style="margin-left:5px;font-size:0.25rem;">图纸</a></span>'
										}
										if(projectDataList[i].inspectionReportList.length>0){
											dataHtml+='<a class="left btn btn-primary" style="width:27%;margin-left:5px;" href="#">QC REPORT</a>'
										}
										if(roleNo==5){
											dataHtml+='<a class="left btn btn-primary" style="width:20%;margin-left:5px;" href="${ctx}/jsp/status_enter.jsp?projectNo='+projectDataList[i].projectNo+'&roleNo='+roleNo+'&userId='+userId+'&userName='+userName+'">录入状态</a>'
										}
										dataHtml+='</p></dd></dl>'
							   }
							   for(var i=0;i<taskList.length;i++){//任务列表
								   taskHtml+='<a href="${ctx}/task/taskDetails?projectNo='+taskList[i].projectNo+'&id='+taskList[i].id+'&roleNo='+roleNo+'&userId='+userId+'&userName='+userName+'" class="index-link">'
								   taskHtml+='<dl class="index-list task-list"><dd><p><span class="span-left">项目号：</span><span class="span-right porject-number">'+taskList[i].projectNo+'</span></p>'
								   taskHtml+='<p><span class="span-left">截止日期：</span><span class="span-right project-date">'+(new Date(taskList[i].endDate).pattern("yyyy-MM-dd"))+'</span></p>'
								   if(roleNo==5){
									   taskHtml+='<p><span class="span-left">采购：</span><span class="span-right">'+taskList[i].purchaseName+'</span></p>'
								   }else if(roleNo==6){
									   taskHtml+='<p><span class="span-left">跟单销售：</span><span class="span-right">'+taskList[i].sellName+'</span></p>'	
								   }else{
									   taskHtml+='<p><span class="span-left">跟单销售：</span><span class="span-right">'+taskList[i].roleName+'</span></p>'
									   taskHtml+='<p><span class="span-left">采购：</span><span class="span-right">'+taskList[i].purchaseName+'</span></p>'  	
								   }
								   taskHtml+='<p><span class="span-left">任务节点：</span><span class="span-right">'
								    if(taskList[i].node==0){
								    	taskHtml += "无";
									} else if(taskList[i].node==1){
										taskHtml += "样品交货";
									}
									if(taskList[i].node==2){
										taskHtml += "大货交货"+ '</span></p>';
									} 
									taskHtml+='<input type="hidden" class="task-status" value="'+taskList[i].status+'"/>'
									taskHtml+='<p><span class="span-left">任务状态：</span><span class="span-right project-person">'
									if(taskList[i].status==0){
										taskHtml+='未完成';
									}else{
										taskHtml+='已完成'+'</span></p>';
									}
									taskHtml+='<p class="p-row"><span class="span-left">任务需求：</span><span class="span-right project-name">'+taskList[i].taskDemand+'</span></p></dd></dl></a>';
							   }
							   $("#projectDataList").append(dataHtml);//项目列表
							   $("#projectTaskList").append(taskHtml);//任务列表							   
							   $("#projectNum").text(projectNum);//项目总数
							   $("#delayNum").text(delayNum);//延期数量
							   
						   }else{
							   queryObj.operatorType =1;
								queryObj.preNum =1;
								queryObj.isPre =false;
								queryObj.nextNum =1;
								queryObj.isNext =false;
								queryObj.curNum =0;
							   $("#projectNum").text(0);//项目总数
							   $("#delayNum").text(0);//延期数量
							   $("#projectType").val(projectType);     
						   }
   
						   var typeHtml='';
						   if(projectType==0){
							  typeHtml+='<input class="projectType" type="radio"  style="margin-left:10px;" value="0" checked >未完成 '
							  typeHtml+='<input class="projectType" type="radio"  style="margin-left:10px;" value="1">完成 ' 
							  typeHtml+='<input class="projectType" type="radio"  style="margin-left:10px;" value="2">暂停 ' 
						   }else if(projectType==1){
							   typeHtml+='<input class="projectType" type="radio"  style="margin-left:10px;" value="0">未完成 '
							   typeHtml+='<input class="projectType" type="radio"  style="margin-left:10px;" value="1" checked>完成 ' 
							   typeHtml+='<input class="projectType" type="radio"  style="margin-left:10px;" value="2">暂停 ' 
						   }else if(projectType==2){
							   typeHtml+='<input class="projectType" type="radio"  style="margin-left:10px;" value="0">未完成 '
							   typeHtml+='<input class="projectType" type="radio"  style="margin-left:10px;" value="1">完成 ' 
							   typeHtml+='<input class="projectType" type="radio"  style="margin-left:10px;" value="2" checked>暂停 ' 
						   }
						   typeHtml+='<input type="hidden" name="projectType" id="projectType" value="'+projectType+'">'
						   $("#selectProjectType").append(typeHtml);
						   var purchaseHtml='采购名：<select id="purchase_id" style="width:2.0rem;height:0.60rem;" onchange="selectOnchange(this)"><option>全部采购</option>';
						   for(var i=0;i<purchaseList.length;i++){
							   purchaseHtml+='<option value="'+purchaseList[i].purchaseName+'">'+purchaseList[i].purchaseName+'</option>'
						   }
						   purchaseHtml+='</select>';
						   $("#purchaseSelect").append(purchaseHtml);//采购下拉列表
						   $("#totalCount").val(totalCount);
						   
						 //分页使用 
						   queryObj.isPre = true;
						   queryObj.preNum = pageNumber;
						   queryObj.isNext= false;
						   queryObj.nextNum= pageNumber + 1;
						   queryObj.curNum = pageNumber;
						   
						   console.log("query success ,cur pageNumber:" + queryObj.curNum);
						   $("#pageNumber").val(queryObj.curNum);
						   
						   
						   
						   if(roleNo==6){//显示采购下拉框(销售,管理员显示)
							   $("#purchaseSelect").css("display","none");
						   }else{
							   $("#purchaseSelect").css("display","block");
						   }
						   //下拉框重新赋值
						   if(!(purchaseNameId == null || purchaseNameId==""  || purchaseNameId=="0")){
							   $("#purchase_id").val(purchaseNameId); 
						   }
						  //项目列表延期与快到期标记
							$('.index-list').each(function(i){
								var project_flag=$('.project-flag').eq(i).val();
								if(project_flag==1){
									$(this).css("border","1px solid deepskyblue");
								}else if(project_flag==2){
									$(this).css("border","1px solid red");
								}
							})
							//点击任务列表判断任务完成与未完成标记
							$('#tsak-list').click(function(){
								$('.task-list').each(function(i){
									var task_status=$('.task-status').eq(i).val();
									if(task_status==0){
										$(this).find('.project-person').css("color","red")
									}
								})
							})
							//点击项目状态查询
							$(".projectType").change(function(){
								var projectType=$(this).val();
					            $("#projectType").val(projectType);
					            queryObj.operatorType =1;
								queryObj.preNum =1;
								queryObj.isPre =false;
								queryObj.nextNum =1;
								queryObj.isNext =false;
								queryObj.curNum =0;
								var totalCount = Number($("#totalCount").val());
								if(totalCount == 0){
									$("#totalCount").val(1)
								}
					            showload();
							});
					   }else{
						 //点击项目状态查询
				            $("#projectType").val(projectType);
				            queryObj.operatorType =1;
							queryObj.preNum =1;
							queryObj.isPre =false;
							queryObj.nextNum =1;
							queryObj.isNext =false;
							queryObj.curNum =0;
						  layer.msg(json.message,{time:2000});
					   }  
					   isDoQuery = false;
					},
					error : function(){
						isDoQuery = false;
					}
		    	});
		  }
		
	
		   /**分页加载数据*/
		   function showload(){
			  var totalCount = Number($("#totalCount").val());
			  pageNumber = queryObj.curNum +1;
			  console.log("begin query,pageNumber:" + pageNumber);
			  if(!isDoQuery || (queryObj.preNum == pageNumber && !queryObj.isPre) || (queryObj.nextNum == pageNumber && !queryObj.isNext) ){
				  console.log("preNum:" + queryObj.preNum + "," + "isQuery:" + queryObj.isPre + ",curNum:" + queryObj.curNum + ",nextNum:" + queryObj.nextNum + "," + "isQuery:" + queryObj.isNext);
				  
				  var residue=totalCount % 10;
				  var divider=parseInt(totalCount/10);
				  if(residue>0){
					  divider=divider+1;
				  }
				  if(pageNumber<=divider){
					  projectDataLoad(pageNumber);
				  }else{
					return true;
				  }
			  }else{
				  return false;
			  }	  
		   }
	  
	</script>
</body>
</html>
