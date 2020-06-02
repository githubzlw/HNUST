
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String projectNo=request.getParameter("projectNo");
	String userName=request.getParameter("userName");
	String userId=request.getParameter("userId");
	String roleNo=request.getParameter("roleNo");
	String taskId=request.getParameter("taskId");
	String videoType=request.getParameter("videoType");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>任务系统，手机详情，视频上传</title>		
		<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="../css/add.css">	
		<link rel="stylesheet" href="../css/progressBar.css" />
	    <link rel="stylesheet" href="../css/ui.css" />
	    <link rel="stylesheet" href="../css/ui.progress-bar.css">		
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
	<div class="quality detail_video_upload">
		<div class="add_title_div">
			<div class="title_div">
				<div class="title">
					<span class="glyphicon glyphicon-menu-left" onclick="javaScript:history.go(-1)"></span>手机项目详情页——视频上传
				</div>
			</div>
			<p class="mt10" style="color:red;">为了减少视频体积，节约网络流量，请用微信先上传到“文件传输助手”进行压缩，然后保存到手机，发送压缩过的视频。上传的视频会根据项目，出现在微信质检报告的末尾</p>
			选择上传工厂：
			<select id="select_factory">
			<c:forEach var="obj" items="${factoryList}">
			   <option value="${obj.factoryId}">${obj.factoryName}</option>
		    </c:forEach>
			</select>	
			
			<div class="divs mt10">
				<label>本视频是：</label>
				<div class="form-group">
					<label><input type="checkbox" name="videoType" value="1"/> 验厂/拍工厂环境和展品的</label>					
				</div>
				<div class="form-group">					
					<label><input type="checkbox" name="videoType" value="2"/> 对应质检报告的</label>
				</div>
				<div class="form-group">					
					<label><input type="checkbox" name="videoType" value="4"/> 产品360度视频 </label>
				</div>
				<div class="form-group">					
					<label><input type="checkbox" name="videoType" value="3"/> 任务视频 </label>
				</div>				
			</div>
					
			<div class="uploads">
			   <form onsubmit="return false;" method="post" enctype="multipart/form-data">
			   <input type="hidden" name="projectNo" value="<%=projectNo%>">
			   <input type="hidden" name="filePath">
			   <input type="hidden" name="fileName">
					<div class="border_top mt10">
						<div class="top clearfix mt10">
							<input type="text" class="pull-left form-control inp1" name="remark"> 
							<button class="btn btn-default pull-right" onclick="del(this);">删除</button>
						</div>
						<div class="top clearfix mt10">
							<input type="file" name="file" class="pull-left" onchange="upload(this)"> 
							<span class="pull-right">大小：<em></em></span>
						</div>
					</div>
				</form>
			</div>
			<div class="btn btn-default display_block mt10 add_vido"><span class="glyphicon glyphicon-plus"></span></div>
			<div class="btn btn-default display_block mt10" onclick="save_video('<%=projectNo%>','<%=userName%>','<%=taskId%>')">提交所有已经上传视频</div>
		</div>
		
		
		
		<div class="w-out-box" id="show_upload_dialog" style="display:none;">
			<div class="weui_mask"></div>
			<div class="w-weui_dialog">
			  <div id="container">
			
				<div class="content">
					<h1>上传进度</h1>
				</div>
				
				<!-- Progress bar -->
				<div id="progress_bar" class="ui-progress-bar ui-container">
				<div class="ui-progress" style="width: 0%;" id="ui-progress-upload">
				<span class="ui-label" style="display:none;">正在加载...<b class="value">0%</b></span>
				</div>
				</div>
				<!-- /Progress bar -->
			    <a class="close-reveal-modal" style="color: #fff; font-size: 30px;position: absolute;right: 10px;top: 10px;" href="javascript:void(0);" onclick="cancel_upload()">×</a>
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
<script type="text/javascript" src="../js/jquery-form.js"></script>
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
     
       var taskId = <%=taskId%>;
       var type = <%=videoType%>; //视频类型
       //checkbox单选
	   $(function(){
	      
	       
	       //如果是任务跳转，则选择任务视频，不允许修改
	       if(taskId){
	    	  $('input[name="videoType"]:last').attr("checked",true);
	    	  $('input[name="videoType"]').attr('disabled',true);  
	       }else{	
	    	   if(type == 4){
	    		   $('input[name="videoType"]:eq(2)').attr('checked',true); 
	    	   }else{
	    		   $('input[name="videoType"]:first').attr('checked',true); 
	    	   }	    	   
	    	   $('input[name="videoType"]:last').attr('disabled',true);  
	    	   $(":checkbox").click(function(){
	    	       //设置当前选中checkbox的状态为checked
	    	       $(this).attr("checked",true);
	    	       $(this).parent().parent().siblings().find('input[type="checkbox"]').attr("checked",false); //设置当前选中的checkbox同级(兄弟级)其他checkbox状态为未选中
	    	   });
	       }
	   })
 
      
      
      
  // 添加视频
  $('.add_vido').click(function(){
	  var border_top = '<form onsubmit="return false;" method="post" enctype="multipart/form-data">'
					+'<input type="hidden" name="projectNo" value="<%=projectNo%>">'
					+'<input type="hidden" name="filePath">'
					+'<input type="hidden" name="fileName">'
		            +'<div class="border_top mt10">'
					+'<div class="top clearfix mt10">'
					+'<input type="text" class="pull-left form-control inp1" name="remark"name="remark"> '
					+'<button class="btn btn-default pull-right" onclick="del(this);">删除</button>'
					+'</div>'
					+'<div class="top clearfix mt10">'
					+'<input type="file" name="file" class="pull-left"  onchange="upload(this)"> '
					+'<span class="pull-right">大小：<em></em></span>'
					+'</div>'
					+'</div></form>';
	 $('.uploads').append(border_top);
	 
  });
  function del(obj){
	  $(obj).parent().parent().remove();
  };
 
  
  
  

//上传视频
function upload(obj){	
		var path = $(obj).val();
	    sppath = path.split("\\");
	    var fileName = sppath[sppath.length-1];	  	   
	    if(fileName == null || fileName == '' || fileName == undefined){
	    	return false;
	    }else{
		    //获取文件大小
		    var fileSize =getFilesize(obj);
			$(obj).parents('form').find('input[name="fileName"]').val(fileName);
			$(obj).parents('form').find('em').text(fileSize+"Mb");
    	   autTime(); 
		   $('#upload_title').children().text("上传进度");
	    }	 		    	
     		
		  
		  //先上传后获取上传文件路径
		 $(obj).parents('form').ajaxSubmit({
			type: "post",
			url: "/uploadVideo/upload",
	     	dataType: "text",
	     	success: function(str){
	     	var result = eval('(' + str + ')');		
	     	var filePath = result.data;
		     	if(result.ok){  
		     		$(obj).parents('form').find('input[name="filePath"]').val(filePath); 		
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
 
 
 
 //保存所有视频
 function save_video(projectNo,userName,tId){
	  var factoryName = $('#select_factory option:selected').text();
	  var factoryId = $('#select_factory').val();
	  var videoList=[];
	  $('form').each(function(){
		  var fileName = $(this).find('input[name="fileName"]').val();
		  var filePath = $(this).find('input[name="filePath"]').val();
		  if(!(fileName&&filePath)){
			  return ture;
		  }
		  var remark = $(this).find('input[name="remark"]').val();
		  var taskId = null;
		  if(tId){
			  taskId = tId;
		  }

		  //获取视频类型
		  var videoType = $('input[name="videoType"]:checked').val();
		  if(!videoType){
			  videoType = 0;
		  }
		  
		  var factoryVideo = {"factoryName":factoryName,"factoryId":factoryId,"qualityVideoName":fileName,
				  "videoFile":filePath,"projectNo":projectNo,"uploader":userName,"remark":remark,"taskId":taskId,"videoType":videoType}
		  videoList.push(factoryVideo);
	  })
	 
	 
	  $.ajax({
		    type:"post",                   
		    url:"${ctx}/uploadVideo/saveVideo",           
		    data:{
			    	videoList:JSON.stringify(videoList)
		    	 },              
		    success:function(json){  
// 		    	var json = eval("(" + data + ")");
				if (json.ok) {
					window.location='/project/showDetails?projectNo='+projectNo;
				}else{
					layer.msg(data.message,{time:2000});  
				}
		    }
	  })
	 
 }
 
 
 
 /*
  * 检测文件大小
  */
 function getFilesize(file) {
    fileSize = file.files[0].size / 1024 / 1024;
    fileSize = Number(fileSize).toFixed(1);
    return fileSize;
 }
</script>







