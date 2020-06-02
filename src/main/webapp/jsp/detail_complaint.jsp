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
		<title>任务系统，手机详情，客户投诉内容</title>		
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
	<div class="quality detail_complaint">
		<div class="add_title_div">
			<div class="title_div">
				<div class="title">
					<span class="glyphicon glyphicon-menu-left" onclick="javaScript:history.go(-1)"></span>手机项目详情页——客户投诉内容
					<input type="hidden" id="id" value="${qualityReport.id}">	
					<input type="hidden" name="picName" id="picName" value="">	
					<input type="hidden" name="qualityReportId" id="qualityReportId" value="${qualityReport.id}">
					<input type="hidden" name="picProjectNo" id="picProjectNo" value="${qualityReport.projectNo}">	
				</div>
			</div>
		</div>
		<h3 class="mt10 mb10">投诉内容 <a href="" download="" class="pull-right">投诉表文件名</a></h3>
		<p>字数字数字数字数字数字数字数字数字数字数字数字
			数字数字数字数字数字数字数字数字数字数字数字数
			字数字数字数字数字数字数字数字数字数</p>
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
			success : function(result) {
				var json = eval("(" + result + ")");
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
			success : function(result) {
				var json = eval("(" + result + ")");
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





