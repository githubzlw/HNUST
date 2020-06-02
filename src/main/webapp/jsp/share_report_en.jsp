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
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=yes" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />		
		<title></title>
		<meta name="description" content="">
		<link rel="stylesheet" href="bootstrap.min.css">
		<link rel="stylesheet" href="add.css?v1119">			
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
	.bigpic{position:fixed;top:0;left:0;z-index:101;width:100%;height:100%;background-color:#000;display:none;padding-top:50px;}
	.bigpic img{width:100%;height:auto;}
	.bigpic span{position:absolute;right:10px;top:30px;font-size:36px;z-index:11;display:none;}
	.share_report .dh a{display:block;width:100%;border:1px solid #ccc;background-color:#006dcc;
	color:#fff;margin-top:5px;}
	</style>	
	<style type="text/css">
    div.pinch-zoom,
    div.pinch-zoom img{
        width: 100%;
        -webkit-user-drag: none;
    }
	.dp_panel .panel-heading,.dp_panel .panel-body{padding:0;}	
	.share_report .dh .div_title button{position:relative;top:-10px;}
	.share_report .dh{margin-bottom:10px;}
	.share_report .dh .div_title{font-size:16px;}
	.add_supplier{position:fixed;width:100%;min-height:200px;background-color:#fff;
		top:120px;left:0;padding:6px;
		box-shadow: 1px 1px 20px rgba(0,0,0,.3),-1px -1px 20px rgba(0,0,0,.3);				
	}
	.add_supplier h3{font-size:16px;}
	.add_supplier tr td:last-child{width:30px;}
/* 	.z-color{color: blue;} */
</style>	
	</head>
<body>
	<div class="bigpic">
		<div class="pinch-zoom">
			<img src="">			
		</div>
	</div>
	<div class="quality share_report share_report_v11_19">					
		<div class="top_title text-center">
			Inspection Report
		</div>					
		<div>	
		  <p><b>Basic Information:</b></p>
		  <span><b>Internal #：</b>${qualityReport.projectNo}</span><br>
		     <span><b>Project Name：</b>${project.projectNameEn == null ? project.projectName : project.projectNameEn}</span><br>
		  <c:if test="${qualityReport.picNum != null}">
		  <span><b>Drawing #：</b>${qualityReport.picNum}</span><br>
		  </c:if>
<%-- 		  <span><b>Location：</b>${qualityReport.place}</span><br> --%>
		  <span><b>Spend Time：</b>${qualityReport.spendTime}</span><br>
		  <span><b>Inspection Date：</b><fmt:formatDate value="${qualityReport.checkDate}" pattern="yyyy-MM-dd"/></span><br>
		  <span><b>Order Quantity：</b>${qualityReport.orders}</span><br>
		  <span><b>Result：</b>
		            <c:if test="${qualityReport.status == 0}">
						Pass
					</c:if>
					<c:if test="${qualityReport.status == 1}">
						Pass with minor Issues
					</c:if>
					 <c:if test="${qualityReport.status == 2}">
                        Reject
					</c:if></span><br>
		</div>
		<div class="wrap">
			<div class="wrap_top">

				<div class="imgs" style="overflow: visible;">
					<ul  class="clearfix add_imgs"  style="width: auto;">
					
						<c:forEach var="obj" items="${picList}">
						   <c:if test="${obj.picType != 5}">
							    <li class="">
							       <div >
								    <a><img src="${obj.picName}" alt=""></a>
								    <span>${obj.picExplain == null ? '' : obj.picExplain}</span>
							      </div>	    
							    </li>
						    </c:if>
						</c:forEach>
					</ul>					
				</div>
			   <c:if test="${qualityReport.boxNumber != null && qualityReport.boxNumber != ''}">
					<p><b>Shipping Information：</b></p>
					<span><b>Cartons/Count:</b>${qualityReport.boxNumber}</span>
					<span><b>Pieces/Cartons:${qualityReport.perQty}</b></span>
					<span><b>Total Count:${qualityReport.inventoryQty}</b></span>
					<span><b>Cartons Opened:${qualityReport.openQty}</b></span>
					<span><b>Open Rate:${qualityReport.openRate==null?'NA':qualityReport.openRate}${qualityReport.openRate!=null?'%':''}</b></span>
				</c:if>
				<div class="imgs" style="overflow: visible;">
					<ul  class="clearfix add_imgs"  style="width: auto;">					
						<c:forEach var="obj" items="${picList}">
						   <c:if test="${obj.picType == 5}">
							    <li>
							       <div>
								    <a><img src="${obj.picName}" alt=""></a>
								    <span>${obj.picExplain}</span>
							      </div>	    
							    </li>
						    </c:if>
						</c:forEach>
					</ul>					
				</div>
			</div>
		</div>	
	</div>
</body>
</html>
<script src="jquery.min.js"></script>
<script type="text/javascript" src="cookie.js"></script>	
<script src="bootstrap.min.js"></script>
<script type="text/javascript" src="pinchzoom.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('div.pinch-zoom').each(function () {
            new RTP.PinchZoom($(this), {});
        });
    })
</script>
<script>
      window.onload = window.onresize = function(){

          var clientWidth =document.documentElement.clientWidth;

          document.getElementsByTagName("html")[0].style.fontSize =

                  clientWidth / 768* 40 + "px";          
      }     

</script>
<script>	
	// 小图放大
	 $('.add_imgs img').click(function(){
		$('.bigpic,.bigpic span').show();
		var src = $(this).attr('src');
		$('.bigpic img').attr('src',src);		
	});
	$('.bigpic span').click(function(){
		$('.bigpic,.bigpic span').hide();	
	});	
	$('.bigpic').click(function(){
		$('.bigpic').hide();
	}); 
</script>



























