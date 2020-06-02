<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<title>采购信息</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css"/>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
	</head>
	<body>
		<div class="go-back" onclick="window.history.back(-1);"></div>
		<input type="hidden" id="roleNo" value="<%= roleNo%>">
		<input type="hidden" id="userId" value="<%= userId%>">
		<input type="hidden" id="userName" value="<%= userName%>">
		<input type="hidden" id="pageSize" value="10">
		<input type="hidden" id="pageNumber" value="1">
		<input type="hidden" id="totalCount" value="10">
		<div class="index-main">	
		    <div class="detail-tit">消息中心</div>
			<div class="index-middle">
				<div id="messageList"></div>
			</div>
		</div>
		<script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		 var queryObj ={"preNum":1,"isPre":false,"nextNum":2,"isNext":false,"curNum":0};
		 var isDoQuery = false;
		 $(function(){
		     //一进来加载默认数据
		     showload();
		     
	         $(".index-middle").scroll(function() {
	        	var txHeight=$('#messageList').height();
	        	var projectNum=$("#totalCount").val();
	        	var hh=$(this).scrollTop();
			        if (hh+620>txHeight && $('#messageList').find('dl').length<=projectNum) {
			        	txHeight=$('#messageList').height();
			        	if(isDoQuery){
			        		return false;
			        	}else{		
			        		isDoQuery = true;
			        		showload();
			        	}	       	
			        }else{
			        	return false;
			        }
			  });
		 })

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
			function openFile(url){
		    	 window.location.href=encodeURI(url); 
		    }
			
		   /**分页加载数据*/
		   function showload(){
			  var totalCount = Number($("#totalCount").val());
			  pageNumber = queryObj.curNum +1;
			  if(!isDoQuery || (queryObj.preNum == pageNumber && !queryObj.isPre) || (queryObj.nextNum == pageNumber && !queryObj.isNext) ){
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
		   
		   function projectDataLoad(pageNumber){
		      var roleNo=$("#roleNo").val();
			  var userId=$("#userId").val();        
			  var userName=$("#userName").val();
			  var pageSize=$("#pageSize").val();
			  isDoQuery = true;
			  if((queryObj.preNum == pageNumber && !queryObj.isPre) || (queryObj.nextNum == pageNumber && !queryObj.isNext) ){
				  doQuery(pageNumber,roleNo,userName,userId);
			  }else{
				  isDoQuery = false;
				  return false;
			  }
		    }
		   function doQuery(pageNumber,roleNo,userName,userId){
		    $.ajax({
				type:'post',
				url:'${ctx}/project/message',
				sync : true,
				data:{
					pageNumber:pageNumber,roleNo:roleNo,userId:userId,userName:userName
				},
				beforeSend:function(){
					index=layer.load();
				},
				success:function(json){
// 				var json = eval("(" + data +")");
				var dataHtml = '';
				   layer.close(index);
// 				   var json = eval("(" + data +")");
				   var messageList=json.data.messageList;//消息数据
				   var roleNo=json.data.roleNo;//角色代码
				   var userId=json.data.userId;//用户Id
				   var userName=json.data.userName;//用户名
				   var pageSize=json.data.pageSize;
				   var pageNumber=json.data.pageNumber;
				   var totalCount=json.data.totalCount;
				   if(json.ok){	   
					   for(var i=0;i<messageList.length;i++){
						   dataHtml+='<dl class="index-list"><dd><p class="p-row"><span class="span-left">项目名称：</span><span class="span-right project-name">'+messageList[i].projectName+'</span></p>'
						   dataHtml+='<p><span class="span-left">项目号：</span><span class="span-right porject-number">'+messageList[i].projectNo+'</span></p>'
						   if(roleNo==5 || roleNo == 1020){
							   dataHtml+='<p><span class="span-left">采购：</span><span class="span-right project-person">'+messageList[i].purchaseName+'</span></p>'
						   }else if(roleNo==6 || roleNo==69 || roleNo == 1020){
							   dataHtml+='	<p><span class="span-left">跟单销售：</span><span class="span-right project-person">'+messageList[i].sellName+'</span></p>'	
						   }else{
							   dataHtml+='<p><span class="span-left">跟单销售：</span><span class="span-right project-person">'+messageList[i].roleName+'</span></p>'
							   dataHtml+='<p><span class="span-left">采购：</span><span class="span-right project-person">'+messageList[i].purchaseName+'</span></p>'  	
						   }
						   dataHtml+='<p><span class="span-left">汇报人：</span><span class="span-right project-name">'+messageList[i].reportName+'</span></p>'
						   dataHtml+='<p class="p-row"><span class="span-left">采购进展：</span><span>'+messageList[i].report+'</span></p>'
						   dataHtml+='<div class="pic-show"><span class="span-left">文件链接：</span> <span><a href="#" onclick="openFile(\'http://117.144.21.74:10010/uploadimg/'+messageList[i].picUrl+'\')">'+messageList[i].picUrl+'</a></span></div></dd></dl>'
					   }
					   $("#messageList").append(dataHtml);
					   $("#totalCount").val(totalCount);
					   //分页使用 
					   queryObj.isPre = true;
					   queryObj.preNum = pageNumber;
					   queryObj.isNext= false;
					   queryObj.nextNum= pageNumber + 1;
					   queryObj.curNum = pageNumber;
				   }
				   isDoQuery = false;
				},
				error : function(){
					isDoQuery = false;
				}
	    	});
		   }
		</script>
		
	</body>
</html>
