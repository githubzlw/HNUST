<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta name="Description" content='
			<c:if test="${videoList!=null && videoList.size()>0}">
			    <c:if test="${videoList.get(0).videoType == 1}">
			      ${videoList.get(0).factoryName}_<fmt:formatDate value="${videoList.get(0).uploadTime}" pattern="yyyy-MM-dd"/>_${videoList.get(0).uploader}
			    </c:if>
			    <c:if test="${videoList.get(0).videoType == 2}">
			      <fmt:formatDate value="${videoList.get(0).uploadTime}" pattern="yyyy-MM-dd"/>_${videoList.get(0).uploader}
			    </c:if>
			    <c:if test="${videoList.get(0).videoType == 3}">
			      ${projectTask.description}
			    </c:if>
			</c:if>
		'/>		
		<title>
		  <c:if test="${videoList!=null && videoList.size()>0}">
		     <c:if test="${videoList.get(0).videoType == 1}">
		        ${videoList.get(0).projectNo}_验厂视频
		     </c:if>
		     <c:if test="${videoList.get(0).videoType == 2}">
		        ${videoList.get(0).projectNo}_质检视频
		     </c:if>
		     <c:if test="${videoList.get(0).videoType == 3}">
		        ${videoList.get(0).projectNo}_任务视频
		     </c:if>
		  </c:if>
		</title>
		<link rel="stylesheet" href="bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="index.css"/>
		<link rel="stylesheet" type="text/css" href="add.css"/>
		<script type="text/javascript" src="common.js"></script>
		<style>
		   .special{display:block;}
		   .pic_file{display:block;position:relative;height:30px;line-height: 30px;border: 1px solid transparent;}
		   .pic_file input{width:105px;display:block;position:absolute;z-index:1000;overflow:hidden;opacity:0;}
		   .pic_file button{position:absolute;top:0;left:0;width:105px;height:30px;line-height:30px;background-color:#81d0fc;color: #fff;
    font-size: 16px;}
    .add_p p{width:auto;}
    .add_p .w195{width:50%;}
    .go_right{left: unset;right: 0.1rem;background: url(../img/right.png) no-repeat;background-size: contain;}		
    .index-mask-two{background-color:#fff;}
    .index-mask-two a {background-color:#fff;}
    .add_project_detail .add_delay_date {margin-bottom: 0;}	
    </style>
	</head>
	<body class="add_project_detail">
		
		<div class="go-back" onclick="goBack()"></div>
		<div class="go-back go_right" onclick="pcUrl()"></div>
		<div id="mask"></div>
		<div class="detail">
			<div class="detail-tit">项目详情页</div>
			<div class="detail-middle">
				<div class="detail-one add_video" style="display:block;">
						<p class="detail-top">
							<a class="pull-left">视频观看</a>						
						</p>
						<c:forEach var="obj" items="${videoList}">
						   <div class="video_div text-center">
								<video controls>
								<c:set value="${ fn:split(obj.qualityVideoName,'.') }" var="names" />
							  	    <c:forEach items="${names}" var="name" varStatus="i">
								  	       <c:if test="${i.index == 1 and name != 'mp4'}">
								  		   <source src="/project_img/${obj.projectNo}/video/convert/${obj.videoFile}" type="video/mp4"/>
								  		   </c:if>
								  		   <c:if test="${i.index == 1 and name eq 'mp4'}">
								  		   <source src="/project_img/${obj.projectNo}/video/${obj.videoFile}" type="video/mp4"/>
								  		   </c:if>
				                    </c:forEach>
								</video>
							</div>
							<div >
								
								<span class="pull-left">${obj.remark}</span>
	<%-- 							<button class="pull-right btn del" onclick="del('${obj.id}',this)">删除</button> --%>
								<span class="pull-right mr10">上传日期：<fmt:formatDate value="${obj.uploadTime}" pattern="yyyy-MM-dd"/></span>						
							</div>			
						</c:forEach>	
						<p>项目号：${videoList!=null && videoList.size()>0 ? videoList.get(0).projectNo : ''}</p>		
						<c:if test="${projectTask.projectNo != null && projectTask.projectNo != ''}">								
						<p>任务发起人：${projectTask.initiator}</p>				
						<p>任务接收人：${projectTask.accepter}</p>				
						<p>任务发起时间：<fmt:formatDate value="${projectTask.startTime}" pattern="yyyy-MM-dd"/></p>	
						<p style="width:100%;">要求任务完成时间：<fmt:formatDate value="${projectTask.finishTime}" pattern="yyyy-MM-dd"/></p>			
						<p style="width:100%;">任务描述：${projectTask.description} </p>	
						</c:if>		
				</div>
			</div>
		</div>	
		</body>		
		<script src="jquery.min.js" type="text/javascript" charset="utf-8"></script>		
		<script type="text/javascript" src="bootstrap.min.js"></script>
		<script src="layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="shareWechat.js"></script>
		<script type="text/javascript">


         
		var projectNo = '${project.projectNo}';
		var productImg = '${project.productImg}';
		var title ='';
		var desc='';
		var img = '';
		var a = '';

      //QQ浏览器分享
      function toShare(id,videoType,factoryName,uploadDate,uploader){
        var shareInfo = '';
        a = location.href;
		a = a.replace("http://117.144.21.74:10010","https://www.kuaizhizao.cn");     

        if(videoType == 3){
				$.ajax({
					    type:"post",                   
					    url:"${ctx}/projectTask/selectProjectTaskById",           
					    data:{
					    	  id:id
					    	 },              
					    success:function(json){  
// 					      var json = eval("(" + data +")");
						  if(json.ok){
							  var task = json.data;
	                          if(task){
	                             title = projectNo + "任务视频";
	                             desc = task.description;
	                             shareInfo = {'url':a,'title':title,'desc':desc,'type':2,'img':img};
	                          }	                      
	 
						  }else{
							 layer.msg(json.message,{time:2000});
						  }   
				     }
			     });  
        }else if(videoType == 2){
               title = projectNo + "质检视频";    
               desc =  uploadDate+"_"+ uploader;         
               shareInfo = {'url':a,'title':title,'desc':desc,'type':2,'img':img};
        }else if(videoType == 1){
               title = projectNo + "验厂视频";    
               desc = factoryName + " " + uploadDate+"_"+ uploader;         
               shareInfo = {'url':a,'title':title,'desc':desc,'type':2,'img':img};
        }
          shareChat(shareInfo);
      }



	    //微信分享
	   function shareChat(shareInfo){
		   qqShare(shareInfo);
	   }  


		</script>
	

</html>
