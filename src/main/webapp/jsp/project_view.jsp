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
<title>进行中项目列表</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../layui/css/layui.css" />
<link rel="stylesheet" href="../css/add.css">
<style>
body{position:relative;}
button.layui-laypage-btn{position: relative;width: 80px;}
.tc{width:700px;height:auto;position:fixed;top:50%;left:50%;transform:translate(-50%,-50%);z-index:10;
background-color:#fff;box-shadow:0 2px 18px 0 rgba(0,0,0,.2);
}
.tc1{font-size:16px;display:none;}
.tc1 .date{padding-left:15px;padding-right:15px;}
.tc1 input[type="file"]{position:absolute;top:0;left:15px;width:70px;overflow:hidden;opacity:0;}
.tc1 .sc,.tc1 .bottom button{background-color: #027CFF;color:#fff;border:0 none;}
.tc1 .bottom button{padding:5px 16px;font-size:16px;}
.tc1 input[type="number"]{width:89%;}
.tc1 .add_day{margin-top:5px;margin-left:5px;}
.tc1 .file_name{margin-left:8px;color:#027CFF;}
.table>tbody>tr>td, .table>thead>tr>th{padding:3px;}
table button.btn{border-radius: 0;}
.th1{width:60px;}
.th2{width:140px;}
.th3{width:150px;}
.th4{width:100px;}
.th5{width:100px;}
.th6{}
.th7{width:120px;}
.th8{width:215px;}
.th9{width:175px;}
.th10{}
.th11{}
.th12{}
.th13{width:65px;}
.customer_complaint_entry button{font-size:14px;margin-top:8px;}
.add_zt{display:inline-block;width:180px;}
.add_name{width:160px;display:inline-block;}
.add_custome_name{display:inline-block;width:100px;}

@media screen and (min-width: 768px){
 .customer_complaint_entry .add_btn_delay{word-break:break-all;width:90px;border-radius:0;border:0 none;display:block;}
}


</style>
</head>
<body>
<div class="tc tc1 panel">

</div>

	<div class="customer_complaint_entry purchase_track_view" style="width:100%;">
		<h1 class="customer_complaint_h1">
			项目时间管理
			<div class="btns">
				<a class="select_blank btn" target="_blank" href="/user/toIndex">返回功能选择页</a>
			</div>
		</h1>
		
		<div class="wrap1 mt10">
		<form id="form" class="roleform form-horizontal" action="/project/selectProjectView"  role="form" >
    <input type="hidden" value="${page}" name="page">
    <input type="hidden" value="${pageSize}" name="pageSize">
			<div class="dates container" style="width:100%;">
				<div class="row">
					<div class="col-xs-10">
					    <span class="pull-left and">采购</span>
						<div class="add_date pull-left">
							 <select id="purchase_name" name="purchaseName" onchange="select()">

							 </select>
						</div>
						<span class="pull-left and">跟单</span>
						<div class="add_date pull-left">
							 <select id="documentary_name" name="sellName" onchange="select()">
	
							 </select>
						</div>
					</div>
				</div>
				</form>
				<div class="row" style="margin-top: 10px;">
					<table class="table table-bordered" >
						<thead>
							<tr>
								<th class="th1">序号</th>
								<th class="th2">项目号</th>
								<th class="th3">项目名</th>
								<th class="th4">项目等级</th>
								<th class="th5">客户名</th>
								<th class="th6">工厂名</th>
								<th class="th7">客户付款日期</th>
								<th class="th8">收到生产已经开始的证据</th>
								<th class="th9">目标完成日期</th>
								<th class="th10">客户延期</th>								
								<th class="th11">目前客户态度</th>
								<th class="th12">项目状态</th>
								<th class="th13"></th>
							</tr>
						</thead>
						<tbody>
						   <c:forEach  var="obj" items="${projectList}" varStatus="i">
								<tr>
									<td>${i.index+1}</td>
									<td>${obj.projectNo}</td>
									<td>${obj.projectName}</td>
									<td>${obj.plantAnalysis == 1 ? 'A' : (obj.plantAnalysis == 2 ? 'B': (obj.plantAnalysis == 3 ? 'C' : '暂无'))}</td>
									<td ><span class="add_custome_name">${obj.customerName}</span></td>
									<td><span class="add_name">${obj.companyName}</span></td>
									<td><fmt:formatDate value="${obj.moneyDate}" pattern="yyyy-MM-dd"/></td>
									<td field="${obj.projectNo}">
                                          <div class="input-group date form_date col-sm-6 content" data-date="" data-date-format="yyyy-mm-dd">
												<input class="form-control brt brt_7" size="16" type="text" style="width: 157px;" placeholder="选择日期" readonly  value="<fmt:formatDate value="${obj.prevSampleDate}" pattern="yyyy-MM-dd"/>"> <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										  </div>
									</td>
									<td>
									   <c:if test="${obj.deliveryDate!=null}">
									     <p>大货：<fmt:formatDate value="${obj.deliveryDate}" pattern="yyyy-MM-dd"/></p>
									   </c:if>
									   <c:if test="${obj.sampleScheduledDate!=null}">
									     <p>样品：<fmt:formatDate value="${obj.sampleScheduledDate}" pattern="yyyy-MM-dd"/></p>
									   </c:if>
									   延后交期:<c:if test="${obj.delayDay==0 }">暂无</c:if>
									   <c:if test="${obj.delayDay!=0 }">
									   <c:if test="${obj.cargoDelayedDeliveryDate!=null}">
									     <p>大货：<fmt:formatDate value="${obj.cargoDelayedDeliveryDate}" pattern="yyyy-MM-dd"/></p>
									   </c:if>
									   <c:if test="${obj.delayedDeliveryDate!=null}">
									     <p>样品：<fmt:formatDate value="${obj.delayedDeliveryDate}" pattern="yyyy-MM-dd"/></p>
									   </c:if>
									   
									   </c:if>
									</td>
									<td>
										<span><fmt:formatDate value="${obj.dateDelivery}" pattern="yyyy-MM-dd"/><c:if test="${obj.dateDelivery==null}">暂无</c:if></span>
										<c:if test="${obj.deliveryReminder==1 }"><span style="color:red;">交期过松</span></c:if>
										<button type="button" class="btn btn-default add_btn_delay" onclick="tc1Show('${user.userName}','${obj.projectNo}','<fmt:formatDate value="${obj.dateDelivery}" pattern="yyyy-MM-dd"/>','${obj.delayDay}'<%-- ,'${obj.explain}' --%>,'${obj.interpretationDocument}')">录入延期</button>
									</td>
									<td><textarea class="form-control">${obj.customerAttitude}</textarea><button class="btn btn-default" type="button" onclick="updateAttitude('${obj.projectNo}',this)">确认更新</button></td>
									<td><span class="add_zt">${obj.report}</span></td>
									<td><a onclick='window.location="/project/showDetails?projectNo=${obj.projectNo}"' target="_blank">详情</a></td>
								</tr>
						   </c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<center id="page" class="page"></center>
		</div>
	</div>

</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/jquery-form.js"></script>
<script src="../layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>

<script>
function tc1Show(userName,projectNo,dateDelivery,delayDays,interpretationDocument){
	$('#projectNo').val(projectNo);
	$('#dateDelivery').val(dateDelivery);
	$('#delayDays').val(delayDays);
	$('#interpretationDocument').val(interpretationDocument);
	
	
	var div1 ='';
	$.ajax({
	    type:"post",                   
	    url:"${ctx}/project/delayMessage",           
	    data:{
	    	  projectNo:projectNo
	    	 }, 
	    dataType : "text",
	    async : false,
	    success:function(str){  
	     var result = eval('(' + str + ')');
	     if(result.ok){
			  //var dateDelivery = result.data.dateDelivery;
			  var delayDay = result.data.delayDay;
			  var explain = result.data.explain;
			  var interpretationDocument = result.data.interpretationDocument;
	div1 += '<div class="form-horizon panel-body">'
		
		if("ninazhao"==userName){div1 +='<div class="form-group row">'
			 div1 +='<label class="col-xs-4">公司同意延后交期日</label>'
				 div1 += '<input type="hidden" name="userName" id="userName" value="'+userName+'">'
				div1 += '<div class="input-group date form_date col-xs-3 content" data-date="" data-date-format="yyyy-mm-dd">'
					div1 += '<input class="form-control brt brt_7" size="16" type="text" style="width: 157px;" placeholder="选择日期" id="lateDeliveryDate" name="lateDeliveryDate" readonly="" value="">' 
						div1 +='<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>';
							div1 +='</div></div>'
		}
								div1 +='<div class="form-group row"><label class="col-xs-4">客户总延误天数</label>'	  		
									div1 +='<div class="col-xs-4 clearfix">'
										div1 +='<input type="number" class="form-control pull-left" id="delayDays" name="delayDays" value="'+delayDay+'">'
											div1 +='<span class="pull-left add_day">天</span></div></div>'
	  			  			  		
	  	
			div1 +='<div class="form-group row"><label class="col-xs-4">解释:</label><div class="col-xs-8">'	  			  		
			div1 +='<textarea class="form-control" id="explain" name="explain">'+explain+'</textarea></div></div>'	  			  		
			div1 +='<div class="form-group row"><div class="col-xs-6 col-xs-offset-4">'
			div1 +='<p><b>格式</b></p><p>7-31 到 8-10,客户要求暂停</p><p>8-15 到 8-20,等待客户确认图纸</p><p>8-15 到 8-20,送样到收到反馈</p>'
			div1 +='<p>注意：</p><p>天数必须能对上邮件日期(会有审核).</p><p>请不要计入再合同签订前的客户原因延迟</p></div></div>'	
			div1 +='<form id="form1" class="roleform form-horizontal" action="/project/selectProjectView">'	
			div1 +='<input type="hidden" id="projectNo" name="projectNo" value="'+projectNo+'">'
			div1 +='<input type="hidden" id="newFileName" name="newFileName"><input type="hidden" id="fileName" name="fileName">'
			div1 +='<div class="form-group row"><label class="col-xs-4">对应证据上传及更新:</label>'
			div1 +='<div class="col-xs-8">'
			
			div1 +='<input type="file" name="file" class="pull-left zj_upload" onchange="upload(this)" id="file_input" >'		  		
			div1 +='<button class="btn btn-default sc">点击上传</button>'
			if(interpretationDocument!=''){
			div1 +='<a target="_blank" href="/product_img/'+projectNo+'/'+interpretationDocument+'" class="file_name">证据下载</a>'
			}
			
			div1 +='<font id="newfile"></font></div></div>'
			div1 +='<div class="form-group row"><label class="col-xs-4">(多个请合并压缩)</label></div></form>'
			div1 +='<div class="bottom"><button class="btn btn-default pull-left add_btn_close">关闭</button>'
			div1 +='<button class="btn btn-default pull-right " onclick="updateExtensionDocument();">提交</button></div></div>'
		    
			$('.tc1').show();
			$('.tc1').append(div1);
			
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
			$('.add_btn_close').click(function(){
				$('.tc1 .panel-body').remove();
				$('.tc1').hide();
			})
	     }else{	
			 easyDialog.open({
					container : {
						content : json.message
					},
					overlay : false,
					autoClose : 2000
				});
		  }   
	   }
	 }); 
};






   


$(function(){
    	ajaxSelectOption(); 
    	
    	

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
    		
    		
    		$('.brt_7,.brt').on('change',function(){
    			var projectNo = $(this).parents('td').attr("field");
    			var prevSampleDate = $(this).val();
    			update(projectNo,prevSampleDate,'');
    		})
    		
    })


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
		window.location.href = "${ctx}/user/toIndex?userId=" + userId
				+ "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId
				+ "&userName=" + encodeURI(encodeURI(userName));
	}
	
	
	var purchaseName = '${purchaseName}';	
	var documentaryName = '${documentaryName}';	
	function ajaxSelectOption() {

		$.ajax({
			type : "post",
			url : "${ctx}/project/queryStaff.do ",
			success : function(json) {
				if (json.ok) {
					var purchaseList = json.data.purchase
					var saleList = json.data.sale
					var qualityList = json.data.quality
					var purchaseHtml = '<option value="">全部采购</option>'
					var saleHtml = '<option value="">全部跟单</option>'

					for (var i = 0; i < purchaseList.length; i++) {
						purchaseHtml += '<option value="'+purchaseList[i].userName+'">'+ purchaseList[i].userName+ '</option>';
		         	}
					for (var i = 0; i < saleList.length; i++) {
						saleHtml += '<option value="'+saleList[i].userName+'">'+ saleList[i].userName + '</option>'
					}
					$('#purchase_name').html(purchaseHtml);
					$('#documentary_name').html(saleHtml);
				   //显示选择的采购、跟单、质检信息
					if(purchaseName){
					   $('#purchase_name').find("option[value="+purchaseName+"]").attr("selected",true);
					}
				    if(documentaryName){
				       $('#documentary_name').find("option[value="+documentaryName+"]").attr("selected",true);
				    }	   
				}
			}
		 })
		}
	
	
	
	function updateAttitude(projectNo,obj){
		var customerAttitude = $(obj).prev().val();
		if(customerAttitude){
			update(projectNo,'',customerAttitude);
		}
	}
	
	 /**
	  * 更新送样时间  客户态度
	  */
	 function update(projectNo,prevSampleDate,customerAttitude){
		   		 
	       $.ajax({ 
			   url : "/project/updateProjectView", 
			   type: "POST", 
			   data : { 
				   projectNo:projectNo,
				   prevSampleDate:prevSampleDate,
				   customerAttitude:customerAttitude
			   }, 
			   dataType:"json", 
			   success : function(json) { 
				   if(json.ok) 
				    { 
					   layer.msg('添加成功',{time:2000}); 
				    }else{
				      layer.msg(json.message,{time:2000});
				    }
			   }
			})
	 }
	 
	 
	 //选择人员事件
	 function select(){
		 $('#form').submit();      
	 }
	
	
</script>

<script type="text/javascript">

		var total = ${total};
		var pageSize = ${pageSize};
		var page = ${page};
		layui.use(['form', 'layedit', 'laydate', 'carousel', 'element', 'laypage'], function() {
			var layer = layui.layer,
				laypage = layui.laypage,
				element = layui.element;

			laypage.render({
				elem: 'page',
				count: total,
				layout: ['page', 'limit', 'skip', 'count', ],
				theme: '#3e88f5',
				curr: page ,   //获取起始页					
				limit: pageSize,
				jump: function(obj, first) {
					//obj包含了当前分页的所有参数，比如：
					//		    console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
					//		    console.log(obj.limit); //得到每页显示的条数
					//首次不执行
					if(!first) {
						//					var load = layer.load(2, {shade: [0.1,'#000']});
//						locationHref("page=" + obj.curr + "&limit=" + obj.limit, "#usteel-condition", "page");
                        $('input[name="page"]').val(obj.curr);
                        $('input[name="pageSize"]').val(obj.limit);
                        $('#form').submit();                       
					}
				}
			});
		});
		
		//上传方法
		function upload(obj) {
			var n = $(obj).get(0).files[0].name;
			$('.file_name').text(n);
			
			var fileNames = $(obj).val();

			if (fileNames == null || fileNames == '' || fileNames == undefined) {
				return false;
			} else {
				
				
			}

			// 先上传后获取上传文件路径
			$('#form1').ajaxSubmit({
				type : "post",
				url : "${ctx}/project/uploadInterpretationDocument",
				dataType : "text",
				async : false,
				success : function(str) {
					var result = eval('(' + str + ')');
					if (result.ok) {
						var newFileName = result.data.newFileName;
						var fileName = result.data.originalFilename;
						$('#newFileName').val(newFileName);
						$('#fileName').val(fileName);
						document.getElementById("newfile").innerHTML=fileName;
					} else {
						layer.msg('上传失败', {
							time : 2000
						});
					}
				},
				error : function() {
					layer.msg('上传失败', {
						time : 2000
					});
					
				}
			});
		}
		
		/**
		  * 修改延期内容
		  */
		 function updateExtensionDocument(){
			   	var fileName=$("#newFileName").val();	 
			   	var projectNo=$("#projectNo").val();	 
			   	var dateDelivery=$("#dateDelivery").val();	 
			   	var delayDays=$("#delayDays").val();	 
			   	var explain=$("#explain").val();
				var lateDeliveryDate=$("#lateDeliveryDate").val();
			   	explain = explain.replace(/\%/g,"%25").replace(/\#/g,"%23").replace(/\&/g,"%26").replace(/\+/g,"%2B");
			   	var userName=$("#userName").val();
			   	if(userName=="ninazhao"){
			   		$.ajax({ 
			 		   url : "/project/updateExtensionDocument", 
			 		   type: "POST", 
			 		   data : { 
			 			   projectNo:projectNo,
			 			  lateDeliveryDate:lateDeliveryDate
			 			  }, 
			 		   dataType:"json", 
			 		   success : function(json) { 
			 			   if(json.ok) 
			 			    { 
			 				  $('.tc1').hide(); 
							   $('#form').submit();
			 			
			 			    }else{
			 			      layer.msg(json.message,{time:2000});
			 			    }
			 		   }
			 		})
			   	}else{
			   	if(delayDays!=0){
			   	if(fileName==null || fileName.replace(/^\s\s*/, '').replace(/\s\s*$/, '').length==0){
					alert("存在客户延期天数，要求必须上传相应证据");
					return false;
				}
			   	}
			   	
			   	explain = explain.replace(/\%/g,"%25").replace(/\#/g,"%23").replace(/\&/g,"%26").replace(/\+/g,"%2B");
		       $.ajax({ 
				   url : "/project/updateExtensionDocument", 
				   type: "POST", 
				   data : { 
					   projectNo:projectNo,
					   fileName:fileName,
					   dateDelivery:dateDelivery,
					   delayDays:delayDays,
					   explain:explain
					   
				   }, 
				   dataType:"json", 
				   success : function(json) { 
					   if(json.ok) 
					    { 
						   $('.tc1').hide(); 
						   $('#form').submit();
					
					    }else{
					      layer.msg(json.message,{time:2000});
					    }
				   }
				})
			   	}
		}
	</script>






