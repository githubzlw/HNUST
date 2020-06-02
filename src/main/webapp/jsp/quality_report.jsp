<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String projectNo=request.getParameter("projectNo");
	String roleNo=request.getParameter("roleNo");
	String userName=request.getParameter("userName");
	String userId=request.getParameter("userId");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>任务系统，质检报告编辑</title>		
		<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="../css/add.css">			
			<style type="text/css">
			.a-line{
			    border: 1px solid #ccc;
			    color: #333;
			    padding: 3px 3px 3px 3px;
			    background: #FFFFFF;
			    height: 30px;
			    text-decoration: none;
			    display: block;
			    text-align: center;
			 }
			
			
	
	</style>	
	</head>
<body>
	<div class="quality quality_report quality_report_mobile add_quality_report">
		<div class="add_title_div">
			<div class="title_div">
				<div class="title">
					<span class="glyphicon glyphicon-menu-left" onclick="javaScript:history.go(-1)"></span>质检报告编辑

					<input type="hidden" id="id" value="${qualityReport.id}">	
					<input type="hidden" name="picName" id="picName" value="">	
					<input type="hidden" name="qualityReportId" id="qualityReportId" value="${qualityReport.id}">
					<input type="hidden" name="picProjectNo" id="picProjectNo" value="${qualityReport.projectNo}">	
				</div>
			</div>
		</div>
		<div class="wrap">
			<div class="wrap_top">

                <div class="btn btn1 " onclick = "btn1()"><span class="glyphicon glyphicon-chevron-left"></span></div>
				<div class="btn btn2 " onclick = "btn2()"><span class="glyphicon glyphicon-chevron-right"></span></div>
				<div class="imgs">
					<ul  class="clearfix" >
					
						<c:forEach var="obj" items="${picList}">
						    <li>
							    <a href="###"><img src="${obj.picName}" alt=""></a>
							    <input type="hidden" value="${obj.id}">
						    </li>
						</c:forEach>
					</ul>					
				</div>
         				<div class="bottom clearfix">
					<button class="pull-left" onclick="deletePic()">删除</button>
					 <div class="pull-right">
						<span>${qualityReport.createtimeView}</span>
						<span id="picPage"></span>
					</div>
				</div>
			</div>
			<div class="s_m s_m_1">
				<div class="d1">图片描述:</div>
				<div class="pic_intro clearfix">
					<textarea class="form-control pull-left" id="picExplain" name="picExplain" value=""></textarea>
					<button class="pull-right" onclick="addQualityPicExplain()">更新</button>
				</div>
			</div>
			<div class="a0 div_a">
				<a href="javaScript:void(0);"  onclick="download_file(${qualityReport.id})">以图片压缩包形式导出</a>
				<a href="javaScript:void(0);"  onclick="exportWord(${qualityReport.id})">以EXCEL文档形式导出</a>
				<a href="###">保存所有图片和质检报告EXCEL文档到ERP</a>
				<a href="###">发送给相关成员</a>
			</div>
			<div class="a0">
				<span><b>本报告是：</b></span>
				<span>${qualityReport.typeView}</span>
			</div>
			<%-- <div class="a0">
				<span><b>因为没检具而没法测量尺寸的数量：</b></span>
				<span>${qualityReport.noCheck}</span>
			</div>
			<div class="a0">
				<span><b>关键尺寸超差数量：</b></span>
				<span>${qualityReport.keySizeExceed}</span>
			</div> --%>
			<div class="a0">
				<span><b>本样品：</b></span>
				<span>${qualityReport.statusView}<c:if test="${qualityReport.status=='2' }"><a href="../meetingRecord/inputMeetingRecord?roleNo=${roleNo }&userId=${userId }&userName=${userName}&&projectNo=${projectNo}" target="_blank">(如有异议，点击我录入验货会结论推翻)</a></c:if></span>
			</div>
			<div class="s_m">
			    <span><b>对采购要求:</b></span>
				<p>${qualityReport.explainCause}</p>
			</div>
			<div class="btn_send  text-center">
				<a id="share_link" href="#" class="a-line">发送邮件给相关人员</a>
			</div>
		</div>	
	</div>
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>

<script>
      window.onload = window.onresize = function(){

          var clientWidth =document.documentElement.clientWidth;

          document.getElementsByTagName("html")[0].style.fontSize =

                  clientWidth / 768* 40 + "px";
      }
 
      function download_file(obj){
          var dataid = obj
          if(dataid){
          	window.location = "${ctx}/quality/download?id="+dataid;
          } 
      }
     
      
      
      
     $(function(){
    	  
          var projectNo = "${qualityReport.projectNo}";
          
	      $.ajax({
			     type:"post",                   
			     url:"${ctx}/project/queryMail",           
			     data:{
			    	 projectNo:projectNo
			     },              
			     success:function(json){
// 			    	 var json = eval("(" + data +")");
					 if(json.ok){
						  var sellMail = json.data.sellEmail;
						  var purchaseEmail = json.data.purchaseEmail;
						  var bossMail = json.data.boss;
						  var cc_mail = "cc=jieguangyang@sourcing-cn.com&cc=wangweiping@sourcing-cn.com&cc=jiangwenlong@sourcing-cn.com&cc=jerrylong@sourcing-cn.com&cc=nina@sourcing-cn.com"; 
						  var cc_mail_pc = "cc=jieguangyang@sourcing-cn.com,wangweiping@sourcing-cn.com,jiangwenlong@sourcing-cn.com,jerrylong@sourcing-cn.com,nina@sourcing-cn.com"; 
						  var sendMail = sellMail;
						  var sendMail_pc = sellMail;
						  if(purchaseEmail){
							  sendMail = purchaseEmail;
							  sendMail_pc = sellMail + "," +purchaseEmail;
							  cc_mail = cc_mail + "&cc=" + sellMail;
						  }
						  //A、B级项目添加老板抄送
						  if(bossMail){
							  cc_mail = cc_mail + "&cc=" +bossMail;
							  cc_mail_pc = cc_mail + "," +bossMail;
						  }
				          var a = location.href;
				          var link = "";
				          if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
				        	  link = 'mailto:'+sendMail+'?'+cc_mail+'&subject="${qualityReport.projectNo}"质检报告&body='+ a; 
			        	  } else if (/(Android)/i.test(navigator.userAgent)) {
			        		  link = 'mailto:'+sendMail+'?'+cc_mail+'&subject="${qualityReport.projectNo}"质检报告&body='+ a; 
			        	  }else{
			        		  link = 'mailto:'+sendMail_pc+'?'+cc_mail_pc+'&subject="${qualityReport.projectNo}"质检报告&body='+ a; 
			        	  }				          
				          $('#share_link').attr('href',link);
						 
					  }
			     }
			
			})
    	  
      })
</script>
<script>
	//  动态轮播图效果
	var li_width = $('.quality_report .imgs li').width();
	var li_length = $('.quality_report .imgs li').length;
	var remove_length = li_width * li_length;
	var key = 0;
	$('.quality_report .imgs ul').css({'width':remove_length + 'px'});
	
	$('#picPage').text('1'+'/'+li_length)
	// 获取img  路径
	var src = $('.wrap .imgs ul li').eq(key).find('img').attr('src');
	selectPicExplainByName(src);
	$("#picName").val(src);
	// 右按钮事件
	function btn2(){
		key++ ;
		console.log(key)
		var move1 = - (li_width * key);
		if(key > (li_length-1)){
			key = 0;
			$('.quality_report .imgs ul').css('left','0');
		}else{
			$('.quality_report .imgs ul').stop().animate({'left':move1 + 'px'}, 300);
		}
		
		$('#picPage').text((key+1)+'/'+li_length)
		// 获取img  路径
		var src = $('.wrap .imgs ul li').eq(key).find('img').attr('src');
		selectPicExplainByName(src);
		$("#picName").val(src);
	}
	// 左按钮事件
	function btn1(){
		key--;
		var move2 = - (li_width * key);
		if(key < 0){
			key = 0;
			$('.quality_report .imgs ul').css('left','0');
		}else{
			$('.quality_report .imgs ul').stop().animate({'left':move2 + 'px'}, 300);
		}
		$('#picPage').text((key+1)+'/'+li_length)
		// 获取img  路径
		var src = $('.wrap .imgs ul li').eq(key).find('img').attr('src');
		selectPicExplainByName(src);
		$("#picName").val(src);
	}
	
	function deletePic(){
	    
// 		var deleteurl =  $('.quality_report .imgs li a img').eq(key).attr('src')
		
// 	    var url=''
// 	    var index =0
// 		$('.quality_report .imgs li a').find('img').each(function(){
			
			
// 			if(index!=key){
// 			 if(!url){
// 		        	url=$(this).attr('src')
// 		        }else{
// 		        	url=url+';'+$(this).attr('src')
// 		        }}
// 			index=index+1
			
// 		})

//         var id = $('#id').val()
       var id = $('.quality_report .imgs li a img').eq(key).parent().next().val();
 
		
		var index = layer.open({
			type:1,
			skin:'finish-btn',
			title:'确认删除？',
			anim:4,
			shade:0.6,
			shadeClose:true,
			btn:['确定','取消'],
			yes:function(){
				
				$.ajax({
				     type:"post",                   
				     url:"${ctx}/quality/deletePic",           
				     data:{
				         id:id
				     },              
				     success:function(json){
// 				    	 var json = eval("(" + data +")");
						 if(json.ok){
							  window.location.href="${ctx}/quality/viewQuality?id="+$('#id').val();
						  }else{
							  layer.msg(json.message)
						  }
						 layer.close(index)
				     },
				     error:function(){
				    	 layer.msg(json.message);
				    	 layer.close(index)
				     }
				
					})
			}
		})	
	}

	function exportWord(reportId){
		window.location = "${ctx}/exportReportExcel?reportId="+reportId;
	}
	
	function selectPicExplainByName(obj){
    	var picName=obj;
    	$.ajax({
			url : "${ctx}/qualityPic/selectQualityPicExplain",
			async : false,
			traditional : true,
			data : {
				picName:picName
			},
			datatype : "json",
			success : function(json) {
// 				var json = eval("(" + result + ")");
				$("#picExplain").val('');
				if (json.ok) {
				  if(json.data!=null && json.data!=''){
					  $("#picExplain").val(json.data.picExplain);    
				  }
				}
			}
		})
    }
	
	function addQualityPicExplain(){
    	var qualityReportId=$("#qualityReportId").val(); 
    	var picProjectNo=$("#picProjectNo").val();
    	var picExplain=$("#picExplain").val();
    	var picName=$("#picName").val();
    	$.ajax({
			url : "${ctx}/qualityPic/addQualityPicExplain",
			type : "post",
			async : false,
			traditional : true,
			data : {
				qualityReportId :qualityReportId,
				picProjectNo:picProjectNo,
				picExplain:picExplain,
				picName:picName
			},
			datatype : "json",
			success : function(json) {
// 				var json = eval("(" + result + ")");
				if (json.ok) {
			
				} else {
					easyDialog.open({
						container : {
							header : 'Prompt message',
							content : json.message
						},
						overlay : false,
						autoClose : 1000
					});
				}

			},
			error : function() {
				easyDialog.open({
					container : {
						content : 'Update failed'
					},
					autoClose : 1000
			  });
			}
		})
    }
</script>





