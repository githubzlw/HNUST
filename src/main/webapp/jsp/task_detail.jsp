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
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<title>任务详情-手机</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
		<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="../css/add.css">
		<link rel="stylesheet" href="../css/progressBar.css" />
	    <link rel="stylesheet" href="../css/ui.css" />
	    <link rel="stylesheet" href="../css/ui.progress-bar.css">	
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
		
		<style type="text/css">
		  .tc{
		        position: fixed;
			    left: 4%;
			    width: 89%;
			    border: 1px solid #ccc;
			    background-color: #fff;
			    z-index: 100;
			    height: 21%;
			    padding: 15px;
			    top: 198px;
			    box-shadow: 3px -2px 6px rgba(43,43,43,0.5), -3px 2px 6px rgba(43,43,43,0.5);
			}
			
		.tc	textarea{
			height: 49px;
            width: 80%;
			}
		.add_tc textarea{
			height: 70px;
	        width: 102%;
	        }	
		.tc h3{margin-bottom:10px;}
		.btn{margin-top: 7px;}
		.btn{margin-top: 7px;}
		</style>
	</head>
	<body>
	<div class="task_detail_mb">
		<div class="tc2" style="display:none;">
	    	<ul>
	    		<c:forEach var="obj" items="${factoryList}" varStatus="i">
		    		<li class="clearfix">
		    			<span class="pull-left">${obj.factoryName}</span>
		    			<select class="pull-right">
		    				<option></option>
		    				<option value="0">未去该工厂</option>
		    				<option value="1">1分</option>
		    				<option value="2">2分</option>
		    				<option value="3">3分</option>
		    				<option value="4">4分</option>
		    				<option value="5">5分</option>
		    			</select>
		    		</li>
		    	</c:forEach>		    		
	    	</ul>
    	<div class="btns clearfix mt10">
    		<button class="btn pull-left bgcolor_ff0" onclick="cancel_score()">取消</button>
    		<button class="btn pull-right bgcolor_ff0" onclick="save()">保存</button>
    	</div>
      </div>
	
		<div id="mask"></div>
		 <div class="tc process" style="display:none;">
	    	<h3>进度更新</h3>
	    	<textarea class="form-control" id="content"></textarea>
	    	<div>
	    		<button class="btn btn-primary" onclick="closeProgress()">关闭</button>
	    		<button class="btn btn-primary" onclick="updateProgress()">提交</button>
	    	</div>
        </div>
        <div class="tc z-date" style="display: none;">
			       <div class="col-sm-4 ">
						<div class="input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd"> 
							<input id="expectFinishTime" value="<fmt:formatDate value="${task.expectFinishTime}" pattern="yyyy-MM-dd"/>" class="form-control brt brt_7" size="16" type="text" placeholder="选择日期" requiredate> <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<span></span>
					</div>
			    	<div>
			    		<button class="btn btn-primary" onclick="closeProgress()">关闭</button>
			    		<button class="btn btn-primary" onclick="subExpectFinishTime()">提交</button>
			    	</div>
		 </div>
		 <div class="tc add_tc" style="display: none;">
		   <textarea id="operateExplain" class="form-control" name="operateExplain" placeholder="请输入操作说明..."></textarea>
		   <input type="hidden" id="operateType" name="operateType">
	       <div class="btns2">
		       <button class="btn btn-primary">取消</button>
		       <button class="btn btn-primary" onclick="addOperateExplain()">保存</button>
	       </div>
		</div>
        <input type="hidden" name="taskType" id="taskType" value="${task.taskType}">
		<input type="hidden" id="projectNo" value="${task.projectNo}">
		<input type="hidden" id="id" value="${task.id}">
		<input type="hidden" id="userName" value="${userName}">
		<input type="hidden" id="roleNo" value="<%=roleNo%>">
		<input type="hidden" id="userId" value="<%=userId%>">
		<input type="hidden" name="projectTaskId" id="projectTaskId" value="${task.id}">
		<div class="detail">
		    <div class="go-back" onclick="goBack()"></div>
			<div class="detail-tit">任务详情页</div>
			<div class="detail-middle">
			

	
		    
				<!--任务基本信息-->
				<div class="detail-one">
					<p class="detail-top">任务基本信息</p>
				       <dl class="index-list">
					     <dd>
								<p><span class="span-left">项&nbsp; 目 号： </span><span class="span-right porject-number"><a href="/project/showDetails?projectNo=${task.projectNo}" class="a_project_num">${task.projectNo}</a></span></p>
								<c:if test="${roleNo==100 or roleNo==99 or roleNo==98}">
									<p><span class="span-left">跟单销售： </span><span class="span-right project-person">${task.sellName}</span></p>
									<p><span class="span-left">采 &nbsp; &nbsp; &nbsp;购： </span><span class="span-right project-person">${task.purchaseName}</span></p>
								</c:if>
								<c:if test="${roleNo==5 || roleNo == 1020}">
									<p><span class="span-left">&nbsp;采  &nbsp; 购： </span><span class="span-right project-person">${task.purchaseName}</span></p>
								</c:if>
								<c:if test="${roleNo==6 || roleNo==69 || roleNo == 1020}">
									<p><span class="span-left">跟单销售： </span><span class="span-right project-person">${task.sellName}</span></p>
								</c:if>
								<p><span class="span-left">截止日期： </span><span class="span-right project-date">
								  <fmt:formatDate value="${task.finishTime}" pattern="yyyy-MM-dd"/>
								  </span>
								 </p>
								<p><span class="span-left">任务节点： </span><span class="span-right project-person">
<%-- 								    ${task.node==0?'无':'' }${task.node==1?'样品交货':''}${task.node==2?'大货交货':''} --%>
								   </span>
								</p>
								<p><span class="span-left">任务状态： </span><span class="span-right project-person">
								    ${task.taskStatus==0?'未完成':'' }${task.taskStatus==1?'已完成':''} ${task.taskStatus==2?'已暂停':''} 
								   </span>
								</p>
								<p>
									<span class="span-left">开箱比例： </span>
									<span class="span-right project-person">${projectTask.openRate==null?(projectTask.checkType != '出货' ? '非大货': ''):(projectTask.openRate)}${projectTask.openRate!=null?'%':''}</span>
								</p>
								
								<p class="p-row">
								<span class="span-left">任务需求：</span><span class="span-right project-name">${task.description}</span>
									<c:if test="${task.returnUrl != null && task.returnUrl != ''}">
									   <a href="${task.returnUrl}" target="_blank">点击跳转</a>
									</c:if>
									<c:if test="${task.taskType eq '9'}">
								        <br><span style="color:red;">同时请打包上传整改完成的图纸、图片、视频并在任务完成时填写整改结论 </span>     	
						         		   <form onsubmit="return false;" method="post" enctype="multipart/form-data">
						         		      <input type="hidden" name="projectNo" value="${task.projectNo}">
							                  <input type="hidden" name="fileName" id="productFileName" value="${task.productFileName}">
						         		      <input type="file" name="file" class="pull-left" onchange="upload(this)"> 
						         		   </form>
						         		  <a href="/static_img/project_img/${task.projectNo}/product/${task.productFileName}" download="/static_img/project_img/${task.projectNo}/product/${task.productFileName}" id="product_file_download" <c:if test="${task.productFileName==null}">style="display:none;"</c:if>>${task.productFileName}</a>				
								    </c:if>
								</p>
								<!-- 判断是否需要上传视频 -->
								<c:if test="${task.isVideo == 1}">
									<p>	<span style="color:red;">
										跟单觉得本项目有问题，需要提供视频和预计交期：
										<c:if test="${task.isVideoUpload == 1}">
										       视频已上传 <a target="_blank" href="/project/showDetails?projectNo=${task.projectNo}">点我新窗口查看视频</a> 
										</c:if>
										<c:if test="${task.isVideoUpload == 0}">
										      <a target="_blank" href="/uploadVideo/toUploadVideo?projectNo=${task.projectNo}&userName=${userName}&taskId=${task.id}">未上传，点我上传</a> 
										</c:if>
									</span>
									</p>
								</c:if>
								 <p><a href="http://117.144.21.74:10010/project/showDetails?projectNo=${task.projectNo}" style="color:red;">检验计划要求速览</a></p>
							</dd>
					 </dl>
				</div>
				<!--任务汇报情况-->
<%-- 				<div class="detail-one">
					<p class="detail-top">进展汇报</p>
					<c:forEach var="taskReport" items="${task.taskReportList}">
						<div class="date-reason">
							<p><span class="span-left">汇报人：</span><span class="span-right ">${taskReport.reportName}</span></p>
							<p><span class="span-left">汇报进展：</span><span >${taskReport.taskReport}</span></p>
							<div class="pic-show">
								<span class="span-left">文件链接：</span>
								  <span><a href="#" onclick="openFile('http://117.144.21.74:10010/uploadimg/${taskReport.picUrl}')">${taskReport.picUrl}</a></span>
							</div>
						</div>
					</c:forEach>
				</div> --%>
				<div class="key-search btn-search">
					<div class="btns">
					 <c:if test="${task.taskStatus eq '0'}">
							<a class="btn bgcolor_ff0" onclick="expectFinishTime()">预计完成时间</a>
							<a class="btn bgcolor_ff0 new_add" onclick="showModelFrame(2)">任务暂停</a>
							<a class="btn bgcolor_ff0 new_add" onclick="showModelFrame(3)">任务取消</a>
							<a class="btn bgcolor_ff0" onclick="showTc()">超一周任务更新进度</a>
							<a class="btn bgcolor_ff0 new_add" onclick="showModelFrame(1)">任务完成</a>	
							<c:if test="${task.isVideo == 1}">
							  <a class="btn bgcolor_ff0" target="_blank" href="/uploadVideo/toUploadVideo?projectNo=${task.projectNo}&userName=${userName}&taskId=${task.id}">视频上传</a>					
						    </c:if>
					  </c:if>  
					  <c:if test="${task.taskStatus eq '1' || task.taskStatus eq '3'}">
							<a class="btn bgcolor_ff0 new_add" onclick="showModelFrame(0)">重启任务</a>
							<a class="btn bgcolor_ff0 new_add" onclick="showModelFrame(4)">新的任务</a>							
							<c:if test="${task.isVideo == 1}">
							  <a class="btn bgcolor_ff0" target="_blank" href="/uploadVideo/toUploadVideo?projectNo=${task.projectNo}&userName=${userName}&taskId=${task.id}">视频上传</a>					
						    </c:if>
					  </c:if>  
					  <c:if test="${task.taskStatus eq '2'}">
					        <a class="btn bgcolor_ff0 new_add" onclick="showModelFrame(3)">任务取消</a>	
							<a class="btn bgcolor_ff0 new_add" onclick="showModelFrame(0)">重启任务</a>													
							<c:if test="${task.isVideo == 1}">
							  <a class="btn bgcolor_ff0" target="_blank" href="/uploadVideo/toUploadVideo?projectNo=${task.projectNo}&userName=${userName}&taskId=${task.id}">视频上传</a>					
						    </c:if>
					  </c:if>  
					</div>
				
				
			            <%-- <% if(roleNo.equals("6")){%>
							<button class="btn btn-primary" id="taskReport">任务汇报</button>
						    <button class="btn btn-primary" id="task">任务完成</button>
						<%}%>
						<% if(roleNo.equals("5")){%>
							<button class="btn btn-primary" id="taskReport">任务汇报</button>
						    <button class="btn btn-primary" id="task">任务完成</button>
						    <button class="btn btn-primary" id="restartTask">重启任务</button>						  
						<%}%>
						<% if(roleNo.equals("100") || roleNo.equals("99")  || roleNo.equals("98")){%>
						    <button class="btn btn-primary" id="taskReport">任务汇报</button>
						    <button class="btn btn-primary" id="task">任务完成</button>
							<button class="btn btn-primary" id="delTask">删除任务</button>
							<button class="btn btn-primary" id="restartTask">重启任务</button>
						<%}%>
						<button class="btn btn-primary"style="width: 50%;margin-top: 21px;" onclick="showProgress()">超一周任务进度更新</button> --%>
			   </div>
			</div>
		</div>
</div>
    
</body>	      
		<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
		<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
		<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
		<script src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
		<script type="text/javascript" src="${ctx}/js/upload-base.js"></script>
        <script type="text/javascript" src="${ctx}/js/jquery-form.js"></script>
		<script type="text/javascript">
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
				$(".date-time").mobiscroll($.extend(opt['date'], opt['default']));
				
				
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
				
                var expectFinishTime = $('#expectFinishTime').val();
				if(!expectFinishTime){
					var date = formatDateTime(new Date());
					$('#expectFinishTime').val(date);
				}
			});
		</script>
		<script type="text/javascript">
			//底部导航弹出处理
			$('.footer-list-one').click(function(){
				$('#mask').show(100);
				$('.index-mask-one').show(300);
			});
			$('#mask').click(function(){
				$('#mask').hide(200);
				$('.index-mask-one').hide(200);
			})
			$('.footer-cancel').click(function(){
				$('#mask').hide(200);
				$('.index-mask-one').hide(200);
			})
			$('.footer-list-two').click(function(){
				$('#mask').show(100);
				$('.index-mask-two').show(300);
			});
			$('#mask').click(function(){
				$('#mask').hide(200);
				$('.index-mask-two').hide(200);
			})
			$('.footer-cancel').click(function(){
				$('#mask').hide(200);
				$('.index-mask-two').hide(200);
			})
			//处理图片点击放大
			$(document).on('click','.pic-show img',function(){
				$('#click-big').show(100);
				var this_src=$(this).attr('src');
				$('#click-big img').attr('src',this_src);
				layer.open({
					type:1,
					skin:'img-big',
					title:'图片预览',
					area:['100%','50%'],
					anim:4,
					shadeClose:true,
					content:$("#click-big")
				})
			})
			//任务汇报
			$("#taskReport").click(function(){
				 var projectNo=$("#projectNo").val();
				 var id=$("#id").val();
				 var roleNo=$("#roleNo").val();
				 var userId=$("#userId").val();
				 var userName=$("#userName").val();
				 window.location.href="${ctx}/jsp/task_report.jsp?projectNo="+projectNo+"&id="+id+"&userId="+userId+"&roleNo="+roleNo+"&userName="+userName+"&flag=1";
			})
			$("#task").click(function(){
				 var taskId=$("#id").val();
				 var userId=$("#userId").val();
				 var roleNo=$("#roleNo").val();
				 var userName=$("#userName").val();
				 var flag=1;
				  $.ajax({
					    type:"post",                   
					    url:"${ctx}/task/updateTask",           
					    data:{
					    	 taskId:taskId,
					    	 flag:flag
					    	 },              
					    success:function(json){  
// 					      var json = eval("(" + data +")");
						  if(json.ok){
							  layer.msg(json.message,{time:2000});
							  window.location.href="${ctx}/task/taskDetails?id="+taskId+"&userId="+userId+"&roleNo="+roleNo+"&userName="+encodeURI(encodeURI(userName))+"&flag=1";	
						  }else{
							  layer.msg(json.message,{time:2000});
						  }   
					    }
				 });  
			})
			//重启任务
			$("#restartTask").click(function(){
				 var projectNo=$("#projectNo").val();
				 var taskId=$("#id").val();
				 var flag=0;
				  $.ajax({
					    type:"post",                   
					    url:"${ctx}/task/updateTask",           
					    data:{
					    	 taskId:taskId,
					    	 flag:flag
					    	 },              
					    success:function(json){  
// 					      var json = eval("(" + data +")");
						  if(json.ok){
							  layer.msg(json.message,{time:2000});
						  }else{
							  layer.msg(json.message,{time:2000});
						  }   
					    }
				 });  
			})
			
			//删除任务
			$("#delTask").click(function(){
				 var projectNo=$("#projectNo").val();
				 var taskId=$("#id").val();
				 var userId=$("#userId").val();
				 var roleNo=$("#roleNo").val();
				 var userName=$("#userName").val();
				 $.ajax({
					    type:"post",                   
					    url:"${ctx}/task/delTask",           
					    data:{
					    	 taskId:taskId
					    	 },              
					    success:function(json){  
// 					      var json = eval("(" + data +")");
						  if(json.ok){
							  window.location.href="${ctx}/jsp/project_list.jsp?userId="+userId+"&roleNo="+roleNo+"&userName="+userName+"&flag=1";
						  }else{
							  layer.msg(json.message,{time:2000});
						  }   
					   }
				 });  
			})
			//一键返回列表页 
		 function goBack(){
		    var projectNo=$("#projectNo").val();
			var roleNo=$("#roleNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			if(userName!=null && userName!=''){
				userName=$("#userName").val();
			}else{
				userName=$("userName").val();
			}
			window.location.href="${ctx}/jsp/project_list_task.jsp";	
		 }
			
		 function openFile(url){
	    	 window.location.href=encodeURI(url); 
	     }
		 
		 
		 
		 
		 
		 
		 //关闭更新进度弹框
		 function closeProgress(){
			 $('.process').hide();
		 }
		 function showProgress(){
			 $('.process').show();
		 }
		//更新进度
		 function updateProgress(){
		 	var id=$("#id").val();
		 	var progress = $('#content').val();
		 	if(!progress){
		 		layer.msg('请输入内容',{time:2000});
		 		return false;
		 	}
		 	$.ajax({
		 	     type:"post",                   
		 	     url:"${ctx}/projectTask/updateProgress",           
		 	     data:{
		 	    	 progress:progress,
		 	    	 id:id
		 	     },              
		 	     success:function(json){  
// 		 		      var json = eval("(" + data +")");
		 			  if(json.ok){
		 				 layer.msg('更新成功',{time:2000});
		 				  $('.tc').hide();	
		 			  }
		 	     }
		 	 });		
		 }
		
		
		
		
		
		 
	</script>	
	
	
	<!-- 任务详情修改后JS  2018.12.27 -->
	<script type="text/javascript">
	var search = "";
	search = getCookie('search');	

	function getCookie(objName){//获取指定名称的cookie的值

	    var arrStr = document.cookie.split("; ");

	    for(var i = 0;i < arrStr.length;i ++){

	        var temp = arrStr[i].split("=");

	        if(temp[0] == objName) return unescape(temp[1]);

	   } 

	}
	
	
	
		//打开更新进度
		function showTc(){
			$('.process').show();	
		}
		function closeProgress(){
			$('.tc').hide();	
		}
		//更新进度
		function updateProgress(){
			var id=$("#id").val();
			var progress = $('#content').val();
			if(!progress){
				layer.msg('请输入内容',{time:2000});
				return false;
			}
			$.ajax({
			     type:"post",                   
			     url:"${ctx}/projectTask/updateProgress",           
			     data:{
			    	 progress:progress,
			    	 id:id
			     },              
			     success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  layer.msg('更新成功',{time:2000});
						  $('.tc').hide();	
					  }
			     }
			 });		
		}
		
		
		//打开预计完成时间弹框
		function expectFinishTime(){
			 $(".z-date").toggle();
		}
		
		/**
		 * 提交预计完成时间
		 */
		function subExpectFinishTime(){
			var roleNo=$("#roleNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
			var id=$("#id").val();
			var expectFinishTime=$("#expectFinishTime").val();
			if(!expectFinishTime){
				layer.msg('请输入时间',{time:2000});
				return fasle;
			}
			
			$.ajax({
			     type:"post",                   
			     url:"${ctx}/projectTask/updateExpectFinishTime",           
			     data:{
			    	 expectFinishTime:expectFinishTime,
			    	 id:id
			     },              
			     success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  $(".z-date").hide();
		                  layer.msg('更新成功',{time:2000});
					  }else{
						  layer.msg(json.message,{time:2000});
					  }   
			     }
			 });	
		}
		
		
		//新增弹窗
// 		$('.new_add').click(function(){
// 			$('.add_tc').show();
// 		})
		$('.add_tc .btns2 button:first-child').click(function(){
			$('.add_tc').hide();
		})
		//弹出框显示
		function showModelFrame(obj){
			//如果是项目完成任务，打开评分弹框
			 var taskType = $('#taskType').val();
			 if(taskType == 6 && obj == 1){
				 $('.tc2').show();
				 return false;
			 }
			 $('.add_tc').show();
			 $("#operateType").val(obj);
		}
		
		/**
		 * 提交事件
		 */
		function addOperateExplain(){
			var operateType=$("#operateType").val();
			if(operateType==0){//重启任务
				restartProjectTask();
			}
			if(operateType==1){//任务完成
				finishProjectTask();
			}
		    if(operateType==2){//任务暂停
		    	stopProjectTask();
			}
		    if(operateType==3){//任务取消
		    	cancelProjectTask();
			}
		    if(operateType==4){//放弃任务更新为
		    	updateOtherProjectTask();
		    }
		}
		
	
		
		//任务重启 0
		function restartProjectTask(){
			var roleNo=$("#roleNo").val();
			var userId=$("#userId").val();
			var userName=$("#userName").val();
		    var projectTaskId=$("#projectTaskId").val();
		    var operateExplain=$("#operateExplain").val();
		    if(operateExplain=="" ||operateExplain==undefined ||operateExplain==null){
		    	layer.msg("请输入操作说明",{time:2000});
		    	return false;
		    }
		    var taskStatus=0;
			 $.ajax({
			     type:"post",                   
			     url:"${ctx}/projectTask/updateProjectTask",           
			     data:{
			    	 projectTaskId:projectTaskId,
			    	 taskStatus:taskStatus,
			    	 userName:userName,
			    	 operateExplain:operateExplain
			     },              
			     success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  window.location.reload();
					  }else{
						  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
					  }   
			     }
			 });	
		}
		
		var isVideo = '${task.isVideo}';
		var isVideoUpload = '${task.isVideoUpload}';
		//完成任务按钮 任务完成  1
		function finishProjectTask(){
		     var projectTaskId=$("#projectTaskId").val();
		 	 var roleNo=$("#roleNo").val();
			 var userId=$("#userId").val();
			 var userName=$("#userName").val();
			 var taskType=$("#taskType").val();
		     var taskStatus=1;
		     var operateExplain=$("#operateExplain").val();
		     //查询视频是否上传
		     findVideoUpload(projectTaskId);
		     if(isVideo == 1 && isVideoUpload == 0){
		    	 layer.msg('您还未上传视频，请点击上传',{time:2000});
		    	 return false;
		     }
		     var productFileName = $('#productFileName').val();
		     if(taskType == 9){   	
		    	 if(!productFileName){
		    		 layer.msg('您还未上传产品改进视频图片',{time:2000});
		    		 return false;
		    	 }
		    	 if(!operateExplain){
		    		 layer.msg('您还未填写整改结论',{time:2000});
		    		 return false;
		    	 }
		     }  
		     
			 $.ajax({
			     type:"post",                   
			     url:"${ctx}/projectTask/updateProjectTask",           
			     data:{
			    	 projectTaskId:projectTaskId,
			    	 taskStatus:taskStatus,
			    	 userName:userName,
			    	 operateExplain:operateExplain,
			    	 productFileName:productFileName
			     },              
			     success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  window.location.reload();
					  }else{
						  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
					  }   
			     }
			 });
		}		
		
		//暂停任务按钮 暂停任务2
		function stopProjectTask(){
		     var projectTaskId=$("#projectTaskId").val();
		     var taskStatus=2;
		 	 var roleNo=$("#roleNo").val();
			 var userId=$("#userId").val();
			 var userName=$("#userName").val();
			 var operateExplain=$("#operateExplain").val();
			 if(operateExplain=="" ||operateExplain==undefined ||operateExplain==null){
			    	layer.msg("请输入操作说明",{time:2000});
			    	return false;
			 }
			 $.ajax({
			     type:"post",                   
			     url:"${ctx}/projectTask/updateProjectTask",           
			     data:{
			    	 projectTaskId:projectTaskId,
			    	 taskStatus:taskStatus,
			    	 userName:userName,
			    	 operateExplain:operateExplain
			     },              
			     success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  window.location.reload();
					  
					  }else{
						  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
					  }   
			     }
			 });  
		}
		//将放弃的任务更新为其他任务(4)
		function updateOtherProjectTask(){
		     var projectTaskId=$("#projectTaskId").val();
		 	 var roleNo=$("#roleNo").val();
			 var userId=$("#userId").val();
			 var userName=$("#userName").val();
			 var taskStatus=4;
			 var operateExplain=$("#operateExplain").val();
			 if(operateExplain=="" ||operateExplain==undefined ||operateExplain==null){
			    	layer.msg("请输入操作说明",{time:2000});
			    	return false;
			 }
			 $.ajax({
			     type:"post",                   
			     url:"${ctx}/projectTask/updateProjectTask",           
			     data:{
			    	 projectTaskId:projectTaskId,
			    	 taskStatus:taskStatus,
			    	 userName:userName,
			    	 operateExplain:operateExplain
			     },              
			     success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  window.location.reload();
					  }else{
						  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
					  }   
			     }
			 });  
		}
		
		//取消任务按钮 取消任务  3
		function cancelProjectTask(){
		     var projectTaskId=$("#projectTaskId").val();
		     var taskStatus=3;
		 	 var roleNo=$("#roleNo").val();
			 var userId=$("#userId").val();
			 var userName=$("#userName").val();
			 var operateExplain=$("#operateExplain").val();
			 if(operateExplain=="" ||operateExplain==undefined ||operateExplain==null){
			    layer.msg("请输入操作说明",{time:2000});
			    return false;
		     }
			 $.ajax({
			     type:"post",                   
			     url:"${ctx}/projectTask/updateProjectTask",           
			     data:{
			    	 projectTaskId:projectTaskId,
			    	 taskStatus:taskStatus,
			    	 userName:userName,
			    	 operateExplain:operateExplain
			     },              
			     success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						 window.location.reload();
					  }else{
						  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
					  }   
			     }
			 });  
		}
		
		 //取消评分弹框
		  function cancel_score(){
			 $('.tc2').hide();	
		  }

		//完成任务按钮 任务完成  1
		function save(){
		     var projectTaskId=$("#projectTaskId").val();
		 	 var roleNo=$("#roleNo").val();
			 var userId=$("#userId").val();
			 var userName=$("#userName").val();
			 var projectNo = $('#projectNo').val();
		     var taskStatus=1;
		     
		     //判断是否给工厂打分，没打分提示
		     var scoreFlag = true;
		     var time = new Date();
			 var scoreList=[];
			  $('.tc2 ul').find('li').each(function(){
				  var factoryName = $(this).find('span:eq(0)').text();
				  var score = $(this).find('select').val();				  
				  if(score==''||score==null){
					  layer.msg("请给工厂打分（1-5分）",{time:2000});
					  scoreFlag = false;
					  return false;
				  }
				  
				  if(!(factoryName&&score) || score == 0){
					  return;
				  }
				  var factoryScore = {"factoryName":factoryName,"score":score,"scorer":userName,"projectNo":projectNo};
				  scoreList.push(factoryScore);
			  })
		     
			  if(!scoreFlag){
					 return false;
			  } 
			  
		     
			 $.ajax({
			     type:"post",                   
			     url:"${ctx}/projectTask/updateProjectTask",           
			     data:{
			    	 projectTaskId:projectTaskId,
			    	 taskStatus:taskStatus,
			    	 userName:userName,
			    	 scoreList:JSON.stringify(scoreList)
			     },              
			     success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						    if(search){
							  window.location.href=search;
							}
					  }else{
						  $("#subHtml").html('<font class="tips_false">'+json.message+'</font>');
					  }   
			     }
			 });
		}
		
		
		
		//查询视频是否上传
		function findVideoUpload(taskId){
			 $.ajax({
			     type:"post",                   
			     url:"${ctx}/projectTask/selectByTaskId",           
			     data:{
			    	 id:taskId
			     },  
			     async:false, 
			     success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  var task = json.data;
						  isVideo = task.isVideo;
						  isVideoUpload = task.isVideoUpload;
					  }
			     }
			 })
		}
		
		
		
		//上传视频
		function upload(obj){	
			    var projectNo = $('#projectNo').val();
				var path = $(obj).val();
			    sppath = path.split("\\");
			    var fileName = sppath[sppath.length-1];	  	   
			    if(fileName == null || fileName == '' || fileName == undefined){
			    	return false;
			    }else{
				   $(obj).parents('form').find('input[name="fileName"]').val(fileName);
		    	   autTime(); 
				   $('#upload_title').children().text("上传进度");
			    }	 		    	
		     		
				  
				  //先上传后获取上传文件路径
				 $(obj).parents('form').ajaxSubmit({
					type: "post",
					url: "/upload/uploadProductFile",
			     	dataType: "text",
			     	success: function(str){
			     	var result = eval('(' + str + ')');		
			     	var fileNewName = result.data.fileName;
				     	if(result.ok){  
				     		$(obj).parents('form').find('input[name="fileName"]').val(fileNewName); 		
				     		$('#product_file_download').attr('href','/static_img/project_img/'+projectNo+'/product/'+fileNewName+'').attr('download','/static_img/project_img/'+projectNo+'/product/'+fileNewName+'').text(fileNewName).show();
				     	}else{
				     		layer.msg('上传失败',{time:2000});
				     	}
			     	},
					error: function(){
						layer.msg('上传失败',{time:2000});
			     		$('#show_upload_dialog').hide();
					}       	     	
			 	}); 	 		    

		 }
	</script>
	
    <script type="text/javascript">
	    function formatDateTime(inputTime) {  
	  		    var date = new Date(inputTime);
	  		    var y = date.getFullYear();  
	  		    var m = date.getMonth() + 1;  
	  		    m = m < 10 ? ('0' + m) : m;  
	  		    var d = date.getDate();  
	  		    d = d < 10 ? ('0' + d) : d;  
	  		    var h = date.getHours();
	  		    h = h < 10 ? ('0' + h) : h;
	  		    var minute = date.getMinutes();
	  		    var second = date.getSeconds();
	  		    minute = minute < 10 ? ('0' + minute) : minute;  
	  		    second = second < 10 ? ('0' + second) : second; 
	  		    return y + '-' + m + '-' + d;  
	  		};
    </script>
</html>
