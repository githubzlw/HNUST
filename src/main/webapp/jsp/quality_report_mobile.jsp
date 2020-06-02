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
		<title>任务系统，质检报告123</title>
		<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="../css/add.css">	
	</head>
<body>
	<div class="quality quality_report">
		<div class="title">
			<span class="glyphicon glyphicon-menu-left" onclick="javaScript:history.go(-1)"></span>质检情况
			<input type="hidden" id="id" value="${qualityReport.id}">
		
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
						<!-- <li>
							<a href="###"><img src="../img/0.png" alt=""></a>
						</li>
						<li>
							<a href="###"><img src="../img/0.png" alt=""></a>
						</li>
						<li>
							<a href="###"><img src="../img/0.png" alt=""></a>
						</li>
						<li>
							<a href="###"><img src="../img/0.png" alt=""></a>
						</li>
						<li>
							<a href="###"><img src="../img/0.png" alt=""></a>
						</li>
						<li>
							<a href="###"><img src="../img/0.png" alt=""></a>
						</li> -->
						
						
						
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
				<p></p>
			</div>
			<div class="a0 div_a"><a href="javaScript:void(0);" onclick="download_file(${qualityReport.id})">压缩包下载链接</a>
			<a href="javaScript:void(0);" style="float: right;" onclick="exportWord(${qualityReport.id})">导出报告</a>
			</div>
			<div class="a0">
				<span>本报告是：</span>
				<span>${qualityReport.typeView}</span>
			</div>
			<div class="a0">
				<span>本样品：</span>
				<span>${qualityReport.statusView}</span>
			</div>
			<div class="s_m">
				<p>${qualityReport.explainCause}</p>
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
        
</script>
<script>
	//  动态轮播图效果
	var li_width = $('.quality_report .imgs li').width();
	var li_length = $('.quality_report .imgs li').length;
	var remove_length = li_width * li_length;
	var key = 0;
	$('.quality_report .imgs ul').css({'width':remove_length + 'px'});
	
	$('#picPage').text('1'+'/'+li_length)
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
	}
	
	function deletePic(){
	    
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
				         id:id,
				         url:url
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
</script>
<!-- <script type="text/javascript">
if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
    window.location.href ="${ctx}/jsp/quality_report_mobile.jsp"; // 如果是苹果机，就打开苹果的页面
} else if (/(Android)/i.test(navigator.userAgent)) {//如果是安卓机，就打开安卓页面
    window.location.href ="${ctx}/jsp/quality_report_mobile.jsp";
} else {
    window.location.href ="${ctx}/jsp/quality_report_pc.jsp";
};
</script> -->





