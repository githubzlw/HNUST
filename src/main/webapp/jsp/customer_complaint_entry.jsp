<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String userName = request.getParameter("userName");
	String userId = request.getParameter("userId");
	String roleNo = request.getParameter("roleNo");
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
<title>客户投诉录入</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="../css/add.css">
<link rel="stylesheet" href="../css/progressBar.css" />
<link rel="stylesheet" href="../css/ui.css" />
<link rel="stylesheet" href="../css/ui.progress-bar.css">
<link rel="stylesheet" href="../css/easydialog.css" />

<style type="text/css">
.z-ul {
	position: absolute;
	margin-top: 34px;
	background-color: #fff;
	width: 240px;
	height: auto;
	display: none;
	margin-left: 16px;
}

.z-ul li {
	height: 22px;
	font-size: 13px;
	border: 1px solid #ccc;
	padding-left: 10px;
}

input[type=file].add_sc {
	display: inline-block;
}

.add_table th {
	font-weight: normal;
}

.add_table .th4, .add_table .th5 {
	width: 40px;
}

.add_table label {
	width: 40px;
	padding-right: 0;
}

.add_table label input {
	height: auto;
	line-height: normal;
}

.add_table>tbody>tr>td, .add_table>thead>tr>th {
	padding: 0;
}

.add_table .th1 {
	width: 60px;
}

.add_complete_list p {
	font-size: 16px;
}

.pic_up_wrap textarea {
	width: 80%;
}

.pic_up label{cursor:pointer;}

.customer_complaint_entry .label_pic {
	width: 175px;
}

.pic_up input {
	height: auto;
	line-height: auto;
	margin-right: 10px;
	width: 14px;
	height: 15px;
}

.up  input[type=file] {
	display: inline-block;
}

.pic_up_wrap {
	display: none;
}

@media screen and (min-width:768px) {
	.customer_complaint_entry {
		width: 1000px;
	}
	.customer_complaint_entry .grade {
		width: 84%;
	}
	
}
@media screen and (max-width:768px){
	.pic_up label{width:70px;}
	.up input[type=file]{display:block;}
}
@media screen and (max-width: 375px){
	.pic_up span{font-size:15px;}
	
}
</style>

</head>
<body>
	<div class="customer_complaint_entry">
		<div class="tc_bq">
			<div class="top clearfix">
				<p class="pull-left">已有问题标签</p>
				<button class="btn bgcolor_ff0 pull-right clo">关闭</button>
			</div>
			<div class="lis">
				<ul id="processList">

				</ul>
			</div>
			<button class="display_block btn bgcolor_ff0 clo clo_add"
				onclick="add_issue_batch()">添加并关闭窗口</button>
		</div>
		<div class="div_transparent"></div>
		<h1 class="customer_complaint_h1">
			客户投诉录入
			<div class="btns">
				<a class="select_blank btn" target="_blank"
					href="/user/toIndex?userId=<%=userId%>&roleNo=<%=roleNo%>&userName=<%=userName%>">返回功能选择页</a>
				<a class="btn"
					href="${ctx}/complaint/queryList?roleNo=<%=roleNo%>&userId=<%=userId%>&userName=<%=userName%>">返回客户投诉列表</a>
			</div>
		</h1>
		<div class="form-group mt10 entry_name">
			<label class="pull-left">录入人</label> <input type="text"
				class="display_inline_block w70_" value="<%=userName%>"
				name="userName" readonly="readonly">
		</div>
		<div class="form-group mt10 positon_relative">
			<label class="pull-left">相关项目号</label> SHS<input type="text"
				class="form-control display_inline_block w70_ project_no"
				name="projectNo" id="project_no" onblur="selectByProjectNo(this)">
			<!-- 			<ul class="project_no_list">
				<li>shs1</li>
				<li>shs12</li>
				<li>shs123</li>
			</ul> -->
		</div>
		<div class="form-group mt10 positon_relative clearfix">
			<label class="pull-left">项目阶段</label>
			<div class="pull-left grade">
				<label><input type="radio" name="projectStage" value="0"><span>样品</span></label>
				<label><input type="radio" name="projectStage" value="2"><span>大货</span></label>
			</div>
		</div>
		<div class="form-group mt10 positon_relative clearfix">
			<label class="pull-left">严重等级</label>
			<div class="pull-left grade">
				<label><input type="radio" name="seriousLevel" value="4"><span>客户索赔</span></label>
				<label><input type="radio" name="seriousLevel" value="3"><span>退货重做</span></label>
				<label><input type="radio" name="seriousLevel" value="2"><span>严重抱怨</span></label>
				<label><input type="radio" name="seriousLevel" value="1"><span>一般性抱怨</span></label>
				<label><input type="radio" name="seriousLevel" value="5"><span>改进</span></label>
			</div>
		</div>
		<div class="form-group mt10 positon_relative clearfix">
			<label class="pull-left label_pic">是否需要图纸更新</label>
			<div class="pull-left pic_up">
				<label><input type="radio" name="pic_up" value="否" checked><span>否</span></label>
				<label><input type="radio" name="pic_up" value="是"><span>是</span></label>
			</div>
		</div>
		<form onsubmit="return false;" method="post"
								enctype="multipart/form-data">
	   <input type="hidden" name="projectNo">
		<div class="pic_up_wrap">
			<div class="form-group mt10 positon_relative description clearfix">
				<label class="pull-left">任务简述 </label>
				<textarea class="form-control pull-left" id="taskBrief" name="taskBrief"></textarea>
			</div>
			<div class="form-group mt10 positon_relative up clearfix mt10">
				<label class="pull-left">附件上传 </label>
				<div class="pull-left">
					<input type="file" id="taskAttachment" name="taskAttachment" onchange="uploadAttachments(this)"> <span class="red">(如有多个文件请压缩后打包上传)</span>
				</div>
			</div>
			<div class="imgs1 clearfix"></div>
		</div>
		</form>
		<div class="form-group">
			<label class="pull-left">项目名</label> <span class="f18"
				id="project_name">选定后展示</span>
		</div>
		<div class="form-group">
			<label class="pull-left">人员列表</label> <span class="f18"
				id="project_member">选定后展示</span>
		</div>
		<div class="form-group">
			<div class="line"></div>
			<div class="wrap">
				<p class="f18">客户主要问题投诉</p>
				<div class="wrap1 mt10">
					1.
					<button class="btn bgcolor_ff0 sel_btn" type="button">可从已有问题标签中选择添加</button>
				</div>
				<div class="wrap2 ">
					<div class="wrap2_in1 mt10">
						2.<span>可以新输入一个【工艺：问题】标签，例如：【焊接：虚焊】</span> <span>请先选择工艺（黄色字体为项目技术部预选的工艺）</span>
						<select class="form-control display_inline_block"
							id="process_list">
							<option value="">选择对应工艺</option>
						</select>
					</div>
					<div class="wrap2_in2 mt10 clearfix">
						<span>请输入工艺所对应的问题标签，请尽量简短</span> <input type="text"
							class="form-control pull-left" placeholder="5个字以内" id="issue"
							oninput="selectByIssue(this)">
						<ul class="z-ul">

						</ul>
						<button class="btn bgcolor_ff0 pull-right" onclick="add_issue()">添加</button>
					</div>

				</div>
				<div class="wrap3">
					<h4 class="mt10">
						已归纳【工艺：问题】标签：<span id="s_node">暂无</span>
					</h4>
					<ul class="list">

					</ul>
				</div>
				<!-- <div class="text-center mt10">
						<button class="btn bgcolor_ff0 tj">提交并关闭</button>
					</div> -->
			</div>


		</div>

		<div class="form-group add_customer">
			<div class="line"></div>
			<label class="display_block describe">客户(原文和翻译)</label>
			<textarea class="form-control" id="complaintContent"
				name="complaintContent"></textarea>
		</div>
		<div class="form-group mt10">
			<div class="line"></div>
			<label class="display_block describe">质量问题</label> <input
				type="hidden" name="issueList" id="issueList">
			<table class="table quality_table">
				<tbody id="tbody">
					<tr class="fist_li">
						<td>
							<form onsubmit="return false;" method="post"
								enctype="multipart/form-data">
								<input type="hidden" name="projectNo">
								<textarea class="form-control mb10" placeholder="请输入问题1"></textarea>
								<div class="clearfix pic_upload f14 mt10 mb15">
									<span class="pull-left mr5">问题1图上传（可多图）:</span> <input
										type="file" multiple="multiple" class="pull-left" name="file2"
										onchange="uploadImg(this)">
								</div>
								<div class="imgs clearfix"></div>
								<button class="btn btn-default del" onclick="del(this)">删除</button>
							</form>

						</td>
						<!-- <td><button class="btn btn-default del" onclick="del(this)">删除</button></td>	 -->
					</tr>

					<tr>
						<td>
							<form onsubmit="return false;" method="post"
								enctype="multipart/form-data">
								<input type="hidden" name="projectNo">
								<textarea class="form-control" placeholder="请输入问题2"></textarea>
								<div class="clearfix pic_upload f14 mt10 15">
									<span class="pull-left mr5">问题2图上传（可多图）:</span> <input
										type="file" multiple="multiple" class="pull-left" name="file2"
										onchange="uploadImg(this)">
								</div>
								<div class="imgs clearfix"></div>
								<button class="btn btn-default del" onclick="del(this)">删除</button>
							</form>

						</td>
						<!-- <td><button class="btn btn-default del" onclick="del(this)">删除</button></td> -->
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td class="text-center"><a class="btn btn-default add"><span
								class="glyphicon glyphicon-plus"></span></a></td>
						<!-- <td></td> -->
					</tr>
				</tfoot>
			</table>
		</div>
		<form id="form" class="form-horizontal" onsubmit="return false;"
			method="post" enctype="multipart/form-data">

			<div class="form-group mt10">
				<div class="line"></div>
				<label class="display_block describe">其他非图片附件打包这里上传</label>
				<div class="clearfix">
					<span class="mr5">上传附件:</span> <input type="hidden"
						name="projectNo"> <input type="hidden" name="filePath"
						id="filePath"> <input type="hidden" name="fileName"
						id="fileName"> <input type="file" class="f14 add_sc"
						name="file" onchange="upload(this)">
				</div>
				<div class="line"></div>
				<div class="complete_list">
					<p>该项目完成情况列表：</p>
					<p>（请视投诉情况判断是否将合同交期清空），</p>
					<p>项目整体完成情况将由系统自动调整.</p>

					<table class="table table-bordered">
						<thead>
							<tr>
								<th>合同号</th>
								<th>供应商名</th>
								<th>合同交期</th>
								<th>产品是否完成</th>
								<th>是否需要返工</th>
							</tr>
						</thead>
						<tbody class="item_list">

						</tbody>
					</table>
				</div>


				<div class="line"></div>

				<span class="f18 mt10 display_block tips ">质量分析会请上传开会所用的质量分析表</span>
			</div>

		</form>
		<button class="btn f18 mt10 creat" onclick="addComplaint(this)">生成电子质量跟踪单</button>
		<div class="links f16 mt10">
			<span>信息链接：</span> <a href="">需求汇总</a> <a href="">质量会议记录</a> <a
				href="">图纸</a> <a href="">质检报告</a> <a href="">检验计划</a> <a href="">生产进度报告</a>
		</div>
		<div class="w-out-box" id="show_upload_dialog" style="display: none;">
			<div class="weui_mask"></div>
			<div class="w-weui_dialog">
				<div id="container">

					<div class="content">
						<h1>上传进度</h1>
					</div>

					<!-- Progress bar -->
					<div id="progress_bar" class="ui-progress-bar ui-container">
						<div class="ui-progress" style="width: 0%;"
							id="ui-progress-upload">
							<span class="ui-label" style="display: none;">正在加载...<b
								class="value">0%</b></span>
						</div>
					</div>
					<!-- /Progress bar -->
					<a class="close-reveal-modal"
						style="color: #fff; font-size: 30px; position: absolute; right: 10px; top: 10px;"
						href="javascript:void(0);" onclick="cancel_upload()">×</a>
					<div class="content" id="main_content" style="display: none;">
						<p>加载完成。</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/upload-base.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-form.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript" src="../js/easydialog.min.js"></script>
<script type="text/javascript" src="../js/process_zh.js"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">

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
	
	/* $('.table-condensed tbody,.table-condensed tfoot').on('click',function(){
		$('.datetimepicker').hide();
	}) */
	
	$('.pic_up label').click(function(){
		var v = $(this).find('span').text();
		if(v == '是'){
			$('.pic_up_wrap').show();
		}else{
			$('.pic_up_wrap').hide();
		}
	})

	
	

// 相关项目号搜索筛选功能
 $.expr[':'].contains = function(a, i, m){
        return $(a).text().toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
};
$('.project_no').keyup(function(){
	$('.div_transparent').show();
	$(this).siblings('ul').show();
	var key = $(this).val();	
	$('.project_no_list li').each(function(){
		$(".project_no_list li:not(:contains("+ key +"))").css({'display':'none'});
	});
	if(key == ""){
		$('.project_no_list li').show();
	}				
});
$('.project_no_list li').click(function(){	
	$('.project_no_list,.div_transparent').hide();
	var li_val = $(this).text();
	$('.project_no').val(li_val);
});
$('.div_transparent').click(function(){
	$('.project_no_list,.div_transparent').hide();
});
</script>

<script>

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



</script>
<script>
	$('.add_btn').on('click',function(){
		$('.entry1').show();
	})
	$('.btn_close').on('click',function(){
		$(this).parents('.complain_entry').hide();
	})
		
</script>
<script type="text/javascript">

  $(function(){
		//工艺列表
		var options = addProcess();
		$("#process_list").append(options);
  })


   //新增客户投诉
   function addComplaint(obj){
	   var projectNo = $('#project_no').val();
	   var complaintContent = $('#complaintContent').val();
	   if(!projectNo){
		   easyDialog.open({
	    		  container : {
	    		    content : '项目号不能为空'
	    		  },
	    		  autoClose : 2000
	    	});
		   return false;
	   }else{
		   //自动加上SHS，防止自动录入shs
		   projectNo = projectNo.toUpperCase().replace("SHS","");
		   projectNo = "SHS"+projectNo;
	   }
	   if(!complaintContent){
		   easyDialog.open({
	    		  container : {
	    		    content : '投诉内容不能为空'
	    		  },
	    		  autoClose : 2000
	    	});
		   return false;
	   }
	   //客户投诉列表
	   var issueList = [];
	   $('#tbody').find('tr').each(function(){
			    if($(this).find('textarea').val()){		    	
			    	//获取图片列表
			    	var imgList = '';
			    	$(this).find('img').each(function(){
			    		var img = $(this).attr('img-data');
			    		imgList +=img+",";
			    	})
			    	if(imgList!=''){
			    		imgList = imgList.substring(0, imgList.length-1);
			    	}
			         	
			    	var issue = {"issue":$(this).find('textarea').val(),"imgList":imgList};
			    	issueList.push(issue);
			     }		    
	   })
	   
	   
	     //客户投诉列表
	   var issueList1 = [];
	   var pic_up=$('input:radio[name="pic_up"]:checked').val();
	   var taskBrief="";
	   var taskAttachment="";
	   if(pic_up=="是"){
	   taskBrief=$('#taskBrief').val();
	    taskAttachment=$('#taskAttachment1').val();;
	   
	   }
	   //问题标签
	   var issues = [];
	   $('.list li').each(function(){
		   var id = $(this).attr('filed');
		   if(id){
			   var analysisIssue = {"id":id };
			   issues.push(analysisIssue);
		   }
	   })
	   
	$('.lis').find('li').each(function(){
		var This = $(this);
		if(This.find('input[type="checkbox"]').is(':checked')){
			var process = This.find('span:eq(0)').text();
			var issue = This.find('span:eq(1)').text();
			var analysisIssue = {"projectNo":projectNo,"issue":issue,"process":process,"isComplaint":1};
			issueList.push(analysisIssue);
		}				
	}) 
	   
	   
       $('#issueList').val(JSON.stringify(issueList));
	   
	   
	    //获取项目阶段
	    var projectStage = $('input[name="projectStage"]:checked').val();
	    //严重等级
	    var seriousLevel = $('input[name="seriousLevel"]:checked').val();
	    if(!seriousLevel){
		    easyDialog.open({
	    		  container : {
	    		    content : '严重等级不能为空'
	    		  },
	    		  autoClose : 2000
	    	 });
		     return false;
	   }
	    var userName = $('input[name="userName"]').val();
	   
	    $(obj).css({'background-color':'#ddd'}).attr("disabled",true);
		$.ajax({
			    type:"post",                   
			    url:"${ctx}/complaint/addComplaint",         
			    data:{
			    	  projectNo:projectNo,
			    	  complaintContent:complaintContent,
			    	  issueList:JSON.stringify(issueList),
			    	  pic_up:pic_up,
			    	  taskBrief1:taskBrief,
			    	  taskAttachment1:taskAttachment,
			    	  issues:JSON.stringify(issues),
			    	  projectStage:projectStage,
			    	  seriousLevel:seriousLevel,
			    	  userName:userName,
			    	  fileName:$('#fileName').val(),
			    	  filePath:$('#filePath').val()
			    	 },              
			    success:function(result){  
			    	$(obj).css({'background-color':'#027CFF'}).removeAttr('disabled');   
// 					var result = eval('(' + str + ')');
					if (result.ok) {
						 easyDialog.open({
				    		  container : {
				    		    content : '录入成功'
				    		  },
				    		  autoClose : 2000
				    	});
						window.location = "https://www.kuaizhizao.cn/complaint/queryComplaint?id="+result.data;
					}else{
						  easyDialog.open({
				    		  container : {
				    		    content : result.message
				    		  },
				    		  autoClose : 2000
				    	});
					}
			    }
		})
	   
   }
   

 
   //上传方法
   function upload(obj){
	   
	   var projectNo = $('#project_no').val();
	   if(!projectNo){
		   easyDialog.open({
	    		  container : {
	    		    content : '项目号不能为空'
	    		  },
	    		  autoClose : 2000
	    	});
		   return false;
	   }else{
		   //自动加上SHS，防止自动录入shs
		   projectNo = projectNo.toUpperCase().replace("SHS","");
		   projectNo = "SHS"+projectNo;
		   $(obj).parents('form').find('input[name="projectNo"]').val(projectNo);
	   }
	   
	    var fileNames = $(obj).val();
		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		}else{
			 autTime(); 
			 $('#upload_title').children().text("上传进度");
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
				    $('#filePath').val(filePath);
					$('#fileName').val(fileName);					
				}else{
					easyDialog.open({
			    		  container : {
			    		    content : '上传失败'
			    		  },
			    		  autoClose : 2000
			    	}); 
				}
			},
			error : function() {
				 easyDialog.open({
		    		  container : {
		    		    content : '上传失败'
		    		  },
		    		  autoClose : 2000
		    	});
				 $('#show_upload_dialog').hide();
			}
		});
   }
 
   
   //根据项目号查询
   function selectByProjectNo(obj){
	   var projectNo = $(obj).val();
	   if(!projectNo){
		    easyDialog.open({
	    		  container : {
	    		    content : '项目号不能为空'
	    		  },
	    		  autoClose : 2000
	    	 });
		     return false;
	   }else{
		   //自动加上SHS，防止自动录入shs
		   projectNo = projectNo.toUpperCase().replace("SHS","");
		   projectNo = "SHS"+projectNo;
	   }	   
	   
		  $.ajax({
			    type:"post",                   
			    url:"${ctx}/project/queryProject",           
			    data:{
			    	  projectNo:projectNo
			    	 },              
			    success:function(json){  
// 			    	var json = eval("(" + data + ")");
					if (json.ok) {
						var project = json.data.project;
						var issues = json.data.analysisIssueList;
						
						
						if(!project){
							easyDialog.open({
					    		  container : {
					    		    content : '未查询到项目号'
					    		  },
					    		  autoClose : 2000
					    	});
						}else{
							$('#project_name').text(project.projectName?project.projectName:"");
							$('#project_member').text((project.sellName?project.sellName+"/":"")+(project.purchaseName?project.purchaseName+"/":"")+(project.zhijian1?project.zhijian1+"/":"")+(project.zhijian2?project.zhijian2+"/":"")+(project.zhijian3?project.zhijian3+"/":""));
							var obj=eval(json.data.projectFactoryList);
							var text="";
	    	            	   $(obj).each(function (index){
	    	            		var val=obj[index];
	    	            		var date="";
	    	            		if(val.sampleDate!=null){
	    	            			 date = new Date(val.sampleDate).format("yyyy-MM-dd");
	    	            		}else if(val.deliveryDate!=null){
	    	            			date = new Date(val.deliveryDate).format("yyyy-MM-dd");
	 	    	            	}
	    	            		var finish="";
	    	            		if(val.sampleFinishTime!=null||val.productFinishTime!=null){
	    	            			finish="<label><input type='radio' checked name='finish"+index+"' id='finish"+index+"' onclick='modifyState(0,1,"+val.id+",this);' value='是'>是</label>"
	    	            			+"<label><input type='radio'  name='finish"+index+"' id='finish"+index+"' onclick='modifyState(0,0,"+val.id+",this);' value='否'>否</label>";
	    	            		}else if(val.sampleFinishTime==null&&val.productFinishTime==null){
	    	            			finish="<label><input type='radio'  name='finish"+index+"' id='finish"+index+"' onclick='modifyState(0,1,"+val.id+",this);' value='是'>是</label>"
	    	            			+"<label><input type='radio' checked  name='finish"+index+"' id='finish"+index+"' onclick='modifyState(0,0,"+val.id+",this);' value='否'>否</label>";	
	    	            		}
							   var rework="";
							   if(val.rework==1){
								   rework="<label><input type='radio' checked name='rework"+index+"' id='rework"+index+"' onclick='modifyState(1,1,"+val.id+",this);' value='是'>是</label>"
	    	            			+"<label><input type='radio'  name='rework"+index+"' id='rework"+index+"' onclick='modifyState(1,0,"+val.id+",this);' value='否'>否</label>";
	    	            		}else if(val.rework==0){
	    	            			rework="<label><input type='radio'  name='rework"+index+"' id='rework"+index+"' onclick='modifyState(1,1,"+val.id+",this);' value='是'>是</label>"
	    	            			+"<label><input type='radio' checked  name='rework"+index+"' id='rework"+index+"' onclick='modifyState(1,0,"+val.id+",this);' value='否'>否</label>";	
	    	            		}
							   var contractNo="";
							  if(val.contractNo==null){
								  contractNo="合同"+ index+1;
							  }
	    	            		
	    	            		text+="<tr><td>"+contractNo+"</td>"
	    						+"<td>"+val.factoryName+"</td>"
	    						+"<td>"+date+"</td>"
	    						+"<td>"+finish+"</td>"
	    						+"<td>"+rework+"</td>"
	    						
	    						+"</tr>";	
	    						});
	    	            	   $('.item_list').html(text);
							 //质量问题标签
				/* 			  $('.list').empty();
							  for(var i=0;i<issues.length;i++){
								  var li = '<li class="mt10"><span>'+issues[i].process+'：</span> <span>'+issues[i].issue+'</span> <span>出现过(<a href="/project/selectIssueList?issue='+issues[i].issue+'" class="blue"><i>'+issues[i].occurrenceNum+'</i></a>)次</span><button class="btn del" type="button" onclick="del_AnalysisIssue('+issues[i].id+',this)">删除</button></li>';
								  $('.list').append(li);
			                  } */
						}						
					}else{
						layer.msg(data.message,{time:2000});  
					}
			    }
		})
   }
   Date.prototype.format = function(format) {
	    var o = {
	        "M+" : this.getMonth() + 1, // month
	        "d+" : this.getDate(), // day
	        "h+" : this.getHours(), // hour
	        "m+" : this.getMinutes(), // minute
	        "s+" : this.getSeconds(), // second
	        "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
	        "S" : this.getMilliseconds()
	    // millisecond
	    };
	    if (/(y+)/.test(format)) {
	        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    }
	    for ( var k in o) {
	        if (new RegExp("(" + k + ")").test(format)) {
	            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	        }
	    }
	    return format;
	};

   
   
   
   
   //上传方法
   function uploadImg(obj){
	   
	   var projectNo = $('#project_no').val();
	   if(!projectNo){
		   easyDialog.open({
	    		  container : {
	    		    content : '项目号不能为空'
	    		  },
	    		  autoClose : 2000
	    	});
		   return false;
	   }else{
		   //自动加上SHS，防止自动录入shs
		   projectNo = projectNo.toUpperCase().replace("SHS","");
		   projectNo = "SHS"+projectNo;
		   $(obj).parents('form').find('input[name="projectNo"]').val(projectNo);
	   }
	   
	    var fileNames = $(obj).val();
		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		}else{
			 autTime(); 
			 $('#upload_title').children().text("上传进度");
		}
		// 先上传后获取上传文件路径
		$(obj).parents('form').ajaxSubmit({
			type : "post",
			url : "${ctx}/complaint/uploadIssuePic",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
		          	var imgs = result.data;
		          	var split = imgs.split(",");
		          	for(var i=0;i<split.length;i++){
			        	var div = '<div class="clearfix pic_name f14 pull-left text-center">'+									
				                          '<img src="/static_img/project_complaint/'+projectNo+"/"+split[i]+'" img-data="'+split[i]+'">'+				   
										'<button class="btn btn-default f18" onclick="del_img(this)">x</button>'+
									'</div>';
						$(obj).parents('form').find('.imgs').append(div);			
		          	}
					
				}else{
					 easyDialog.open({
			    		  container : {
			    		    content : '上传失败'
			    		  },
			    		  autoClose : 2000
			    	});
				}
			},
			error : function() { 
				   easyDialog.open({
			    		  container : {
			    		    content : '上传失败'
			    		  },
			    		  autoClose : 2000
			    	});
				 $('#show_upload_dialog').hide();
			}
		});
   }
   //上传方法
   function uploadAttachments(obj){
	   
	   var projectNo = $('#project_no').val();
	   if(!projectNo){
		   easyDialog.open({
	    		  container : {
	    		    content : '项目号不能为空'
	    		  },
	    		  autoClose : 2000
	    	});
		   return false;
	   }else{
		   //自动加上SHS，防止自动录入shs
		   projectNo = projectNo.toUpperCase().replace("SHS","");
		   projectNo = "SHS"+projectNo;
		   $(obj).parents('form').find('input[name="projectNo"]').val(projectNo);
	   }
	   
	    var fileNames = $(obj).val();
		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		}else{
			 autTime(); 
			 $('#upload_title').children().text("上传进度");
		}
		// 先上传后获取上传文件路径
		$(obj).parents('form').ajaxSubmit({
			type : "post",
			url : "${ctx}/complaint/uploadIssuePic",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
		          	var imgs = result.data;
		          	var split = imgs.split(",");
		          	for(var i=0;i<split.length;i++){
			        	var div = '<div class="clearfix pic_name f14 pull-left text-center">'+									
				                          '<input type="hidden"  id="taskAttachment1" name="taskAttachment1"  value="'+split[i]+'">'+				   
										
									'</div>';
						$(obj).parents('form').find('.imgs1').append(div);			
		          	}
					
				}else{
					 easyDialog.open({
			    		  container : {
			    		    content : '上传失败'
			    		  },
			    		  autoClose : 2000
			    	});
				}
			},
			error : function() { 
				   easyDialog.open({
			    		  container : {
			    		    content : '上传失败'
			    		  },
			    		  autoClose : 2000
			    	});
				 $('#show_upload_dialog').hide();
			}
		});
   }
   
</script>
<script>

//质量问题添加
var addtr = $('.first_li').clone();
$('.add').click(function(){	
	var index = $('#tbody').find('tr').length+1;
	$('#tbody').append(
			'<tr><td><form onsubmit="return false;" method="post" enctype="multipart/form-data"><input type="hidden" name="projectNo"><textarea class="form-control textarea_index" placeholder="请输入问题'+index+'"></textarea><div class="clearfix pic_upload f14 mt10 mb15"><span class="pull-left mr5 span_index">问题图'+index+'上传（可多图）</span><input type="file" multiple="multiple" class="pull-left" name="file2" onchange="uploadImg(this)"></div><div class="imgs clearfix"></div><button class="btn btn-default del" onclick="del(this)">删除</button></form></td></tr>');		

	$('#tbody tr').each(function(){
		console.log($(this).index());
		var key = $(this).index() + 1;
		$(this).find('textarea').attr('placeholder','请输入问题'+ key);
		$(this).find('span').text('问题'+ key +'图上传（可多图）:');
		
	})
}); 

// 删除问题
		
function del(obj){
	$(obj).parent().parent().parent().remove();	
	$('#tbody tr').each(function(){
		console.log($(this).index());
		var key = $(this).index() + 1;
		$(this).find('textarea').attr('placeholder','请输入问题'+ key);
		$(this).find('span').text('问题'+ key +'图上传（可多图）:');
		
	})
}	
	

$('#tbody tr').each(function(){
	console.log($(this).index());
	var key = $(this).index() + 1;
	$(this).find('textarea').attr('placeholder','请输入问题'+ key);
	$(this).find('span').text('问题'+ key +'图上传（可多图）:');
	
})

// 问题标签弹窗
$('.sel_btn').click(function(){
	
	  $.ajax({
		    type:"post",                   
		    url:"${ctx}/project/selectByIssue",           
		    data:{
		    	  issue:null
		    	 },              
		    success:function(json){  
// 		    	var json = eval("(" + data + ")");
				if (json.ok) {
					var issueList = json.data;
					$('#processList').empty();
					 if(issueList){
						 for(var i=0;i<issueList.length;i++){	
							 $('#processList').append('<li><input type="checkbox"><span>'+(issueList[i].process?issueList[i].process:'')+'</span>：<span>'+issueList[i].issue+'</span></li>');
						 }
					 }	 
				}else{
					   easyDialog.open({
				    		  container : {
				    		    content : data.message
				    		  },
				    		  autoClose : 2000
				    	});
				}
		    }
	})
	
	 $('.tc_bq').show();
});
$('.clo').click(function(){
	$('.tc_bq').hide();
});



/*
 * 添加质量、工艺问题
 */
function add_issue(){
	
	 var projectNo = $('#project_no').val();	   
     if(!projectNo){
  	   easyDialog.open({
 		  container : {
 		    content : '项目号不能为空'
 		  },
 		  autoClose : 2000
 		});
	   return false;
     }else{
	   //自动加上SHS，防止自动录入shs
	   projectNo = projectNo.toUpperCase().replace("SHS","");
	   projectNo = "SHS"+projectNo;
     }
	 var issue = $('#issue').val();	 
	 var qualityAnalysisId = $('#qualityAnalysisId').val();
	 var process = $('#process_list').val();
	 if(!issue){
	    easyDialog.open({
    		  container : {
    		    content : '问题不能为空'
    		  },
    		  autoClose : 2000
    		});	
		 return false;
	 }
	 if(issue.length>5){
	    easyDialog.open({
    		  container : {
    		    content : '问题在5个字以内'
    		  },
    		  autoClose : 2000
    		});	
		 return false;
	 }
	 if(!process){
	   easyDialog.open({
    		  container : {
    		    content : '工艺不能为空'
    		  },
    		  autoClose : 2000
    		});
		 return false;
	 }
	//投诉问题
	var isComplaint = 1;
	 
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/project/addAnalysisIssue",           
	     data:{
	    	 issue:issue,
	    	 projectNo:projectNo,
	    	 process : process,
	    	 qualityAnalysisId:qualityAnalysisId,
	    	 isComplaint:isComplaint
	     },              
	     success:function(json){
// 	    	 var json = eval("(" + data +")");
			 if(json.ok){
			     easyDialog.open({
		    		  container : {
		    		    content : '保存成功'
		    		  },
		    		  autoClose : 2000
		    		});
			     $('#s_node').text('');
				 var issues = json.data;
// 				  $('.list').empty();
// 				  for(var i=0;i<issues.length;i++){
					  var li = '<li class="mt10" filed="'+issues.id+'"><span>'+issues.process+'：</span> <span>'+issues.issue+'</span> <span>出现过(<a href="/project/selectIssueList?issue='+issues.issue+'" class="blue"><i>'+issues.occurrenceNum+'</i></a>)次</span><button class="btn del" type="button" onclick="del_AnalysisIssue('+issues.id+',this)">删除</button></li>';
					  $('.list').append(li);
//                   }
			  }else{
				   easyDialog.open({
			    		  container : {
			    		    content : json.message
			    		  },
			    		  autoClose : 2000
			    	});
			  }
	     },
	     error:function(){
			   easyDialog.open({
		    		  container : {
		    		    content : json.message
		    		  },
		    		  autoClose : 2000
		    	});			    	
	     }
		})		 
}


/*
 * 批量添加质量、工艺问题
 */
function add_issue_batch(){

	   var projectNo = $('#project_no').val();	   
	   if(!projectNo){
		   easyDialog.open({
	    		  container : {
	    		    content : '项目号不能为空'
	    		  },
	    		  autoClose : 2000
	    		});
		   return false;
	   }else{
		   //自动加上SHS，防止自动录入shs
		   projectNo = projectNo.toUpperCase().replace("SHS","");
		   projectNo = "SHS"+projectNo;
	   }
	
	var issueList=[];
	$('.lis').find('li').each(function(){
		var This = $(this);
		if(This.find('input[type="checkbox"]').is(':checked')){
			var process = This.find('span:eq(0)').text();
			var issue = This.find('span:eq(1)').text();
			var analysisIssue = {"projectNo":projectNo,"issue":issue,"process":process,"isComplaint":1};
			issueList.push(analysisIssue);
		}				
	}) 
	 if(!issueList){
		 $('.tc_bq').hide();
		 return false;
	 }
	 
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/project/addAnalysisIssueBatch",           
	     data:{
	       projectNo:projectNo,
           issueList:JSON.stringify(issueList)
	     },              
	     success:function(json){
// 	    	 var json = eval("(" + data +")");
			 if(json.ok){
				  easyDialog.open({
		    		  container : {
		    		    content : '保存成功'
		    		  },
		    		  autoClose : 2000
		    		});
				  $('#s_node').text('');
				  var issues = json.data;
// 				  $('.list').empty();
				  for(var i=0;i<issues.length;i++){
					  var li = '<li class="mt10"><span>'+issues[i].process+'：</span> <span>'+issues[i].issue+'</span> <span>出现过(<a href="/project/selectIssueList?issue='+issues[i].issue+'" class="blue"><i>'+issues[i].occurrenceNum+'</i></a>)次</span><button class="btn del" type="button" onclick="del_AnalysisIssue('+issues[i].id+',this)">删除</button></li>';
					  $('.list').append(li);
                  }
				  
			  }else{
				  easyDialog.open({
		    		  container : {
		    		    content : json.message 
		    		  },
		    		  autoClose : 2000
		    		});
			  }
	     },
	     error:function(){
	    	 easyDialog.open({
	    		  container : {
	    		    content : json.message 
	    		  },
	    		  autoClose : 2000
	    		});			    	
	     }
		})		 
}


/**
 * 删除质量、工艺问题
 */
function del_AnalysisIssue(id,obj){				
		if(!id){
			return false
		}
		
		var btnFn = function(){
			$.ajax({
			     type:"post",                   
			     url:"${ctx}/project/deleteAnalysisIssue",           
			     data:{
			         id:id
			     },              
			     success:function(json){
// 			    	 var json = eval("(" + data +")");
					 if(json.ok){
						$(obj).parents('li').remove();
					  }else{
						  easyDialog.open({
				    		  container : {
				    		    content : json.message 
				    		  },
				    		  autoClose : 2000
				    		});
					  }
			     },
			     error:function(){
			    	 easyDialog.open({
			    		  container : {
			    		    content : json.message 
			    		  },
			    		  autoClose : 2000
			    		});
			     }
				});
			  };

			easyDialog.open({
			  container : {
			    header : '提示信息',
			    content : '确认删除？',
			    yesFn : btnFn,
			    noFn : true
			  }
		});
	}
	
	
	
//根据问题查询
function selectByIssue(obj){
	  
	 var process = $('#process_list').val();
	 if(!process){
		 return false;
	 }
	
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/project/selectByIssue",           
	     data:{
	    	 issue : $(obj).val(),
	    	 process:process
	     },              
	     success:function(json){
// 	    	  var json = eval("(" + data +")");
			  if(json.ok){
				 var issueList = json.data;
				 $('.z-ul').empty();
				 if(issueList){
					 for(var i=0;i<issueList.length;i++){
						 $('.z-ul').append('<li>'+issueList[i].issue+'</li>');
					 }
					 $('.z-ul').show();
					 //选中关键词
			   		 $('.z-ul li').click(function(e){
			   			 $('#issue').val($(this).text()); 
			   			 $('.z-ul').hide();
			   			 
			   			 $(document).on("click", function(){
			   			     $('.z-ul').hide();
						 });								
						 e.stopPropagation();
			   		 })
				 }
				 						 
			  }else{
				  easyDialog.open({
		    		  container : {
		    		    content : json.message 
		    		  },
		    		  autoClose : 2000
		    		});
			  }
	     },
	     error:function(){
	    	 easyDialog.open({
	    		  container : {
	    		    content : json.message 
	    		  },
	    		  autoClose : 2000
	    		});	    	
	     }
		})		 
}
/*修改项目状态及完成时间  */
function modifyState(finished,remark,id,obj){
	if(finished==0){
		var voide="";
	if(remark==0){
		voide="确认点否？ 将清空该合同完成日期";
	}else if(remark==1){
		voide="确认已完成？完成日期将自动设置为今天";
	}
	var sure=confirm(voide);
		if(sure){
			var projectNo = $('#project_no').val();	   
			  
				   //自动加上SHS，防止自动录入shs
				   projectNo = projectNo.toUpperCase().replace("SHS","");
				   projectNo = "SHS"+projectNo;
			   
			$.ajax({
				url : "/project/modifyState",
				type : "POST",
				data : {
					"finished":finished,
					"rework" : remark,
					"projectNo" : projectNo,
					"id":id
				},
				dataType : "json",
				success : function(json) {
					if(json.ok){
						
					}else{
						alert(json.message);
					}
				}
			})	
		}else{
			// 点击弹窗 取消按钮								
			var sibling_check = $(obj).parent().siblings('label').find('input').val();
			if(sibling_check == '是'){
				
				$(obj).removeAttr('checked');
				$(obj).parent().siblings('label').find('input').prop('checked', true);
				
				
			}else if(sibling_check == '否'){
				$(obj).removeAttr('checked');
				$(obj).parent().siblings('label').find('input').prop('checked', true);
				
			}					
		}
	}else{
		var projectNo = $('#project_no').val();	   
		  
		   //自动加上SHS，防止自动录入shs
		   projectNo = projectNo.toUpperCase().replace("SHS","");
		   projectNo = "SHS"+projectNo;
	   
	$.ajax({
		url : "/project/modifyState",
		type : "POST",
		data : {
			"finished":finished,
			"rework" : remark,
			"projectNo" : projectNo,
			"id":id
		},
		dataType : "json",
		success : function(json) {
			if(json.ok){
				
			}else{
				alert(json.message);
			}
		}
	})	
	}
}


</script>





