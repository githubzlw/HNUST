<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String projectNo=request.getParameter("projectNo");
	String userId=request.getParameter("userId");
	String roleNo=request.getParameter("roleNo");	
%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=yes" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />	
		<meta name="Description" content="${qualityReport.conclusion}"/>			
		<title>${qualityReport.projectNo} ${qualityReport.projectName} (${qualityReport.type == 0 ? '样品检验' : (qualityReport.type == 1 ? '大货样品' : (qualityReport.type == 2 ? '中期检验' : (qualityReport.type == 3 ? '终期检验' : '')))})  ${qualityReport.place} ${qualityReport.user}  <fmt:formatDate value="${qualityReport.checkDate}" pattern="yyyyMMdd"/></title>
		<link rel="stylesheet" href="bootstrap.min.css">
		<link rel="stylesheet" href="add.css?v1208">		
		<link rel="stylesheet" href="progressBar.css" />
	    <link rel="stylesheet" href="ui.css" />
	    <link rel="stylesheet" href="ui.progress-bar.css">		
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
	.share_report .dh a{display:block;width:100%;border:1px solid #ccc;background-color:#027CFF;
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
	.zj_upload{opacity: 0;position: absolute;width: 84px;margin-top: 12px;}
    .z-span{font-size: 14px;float: left;width: 129px;margin-top: 5px;color: red;}
    .share_report_v11_19  .inspection-ul li{border-bottom:1px solid #ccc;font-size:18px;}
    .share_report_v11_19  .inspection-ul .check_btn{height: auto;}
   	.share_report_v11_19  .inspection-ul{padding:5px 10px;}
   	.share_report_v11_19 .inspection-ul li{margin:0;padding:10px 0;}
   	.share_report_v11_19  .inspection-ul li span{font-size:18px;}
   	.add_question .th4,.add_question .th5{width:55px;}
  	.add_question .th3{width:55px;}
  	.add_question .th1{width:60px;}
  	.table>thead>tr>th{vertical-align:middle;}
  	.add_plan img{width:40px;height:40px;border:1px solid red;}
  	.add_plan .more_word_wrap{height:75px;overflow:hidden;}
  	.add_plan .more{cursor:pointer;color:#027cff;text-align:right;}
  	.add_plan .name{color:#027cff;}
    @media screen and (max-width:768px){
    	.share_report_v11_19  .inspection-ul li span{font-size:18px;}
   	
    }
    @media screen and (min-width:768px){
    	.add_question .th3,.add_question .th4,.add_question .th5{width:110px;}
    }
    
    .customer_complaint_B_detail .big_pic{width: 100%;
    height: 100%;
    left: 0;
    margin-left: 0;}
   .big_pic img{    position: absolute;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    margin: auto!important;}
</style>	
	</head>
<body>

	<a href="#top" class="back_top"><img src="back_top.jpg"></a>
	<div class="bigpic">
		<div class="pinch-zoom">
			<img src="">			
		</div>
	</div>
	<div class="confirm_list customer_complaint_B_detail quality share_report share_report_v11_19 ">		
<div class="big_pic_transparent"></div>
<div class="big_pic">
	<img src="" class="img-responsive mt20">
</div>
				
   <div class="top clearfix">
		<div class="pull-left top1">
			<h1 class="customer_complaint_h1">电子质检报告</h1>
		</div>
		<div class="btns pull-right top2">
			<a class="select_blank btn"  href="http://117.144.21.74:10010/user/toIndex">返回功能选择页</a>
			<a onclick="goIndex('https://www.kuaizhizao.cn/quality/qualityList')" class="btn select_blank">返回质检报告列表</a>
		</div>
	</div>
<!-- 		<div class="btns pull-right top2"> -->
<!-- 			<a onclick="goIndex('https://www.kuaizhizao.cn/quality/qualityList')" class="btn select_blank">返回质检报告列表</a> -->
<!-- 		</div>					 -->
		<div>
		<c:if test="${(qualityReport.type==3||qualityReport.type==2)&& qualityReport.status==2}"><c:if test="${qualityReport.processInstanceId==null }"><button class="btn btn-default" 
									onclick="sendDataToDingTalk('${qualityReport.dingTalkId }',${qualityReport.id},'${productionPlan.planNode}');">发送到钉钉审批(未发送)</button>
								</c:if>
								<c:if test="${qualityReport.processInstanceId!=null }">
								<c:if test="${qualityReport.dingdingStatus=='TERMINATED' }">
								<button class="btn btn-default" 
									onclick="sendDataToDingTalk('${qualityReport.dingTalkId }',${qualityReport.id},'${productionPlan.planNode}');">钉钉审批已取消(可再次发送)</button>
								</c:if>
								<c:if test="${qualityReport.dingdingStatus=='COMPLETED' && qualityReport.dingdingResult=='agree' }">
								钉钉审批(已通过)
								</c:if>
								<c:if test="${qualityReport.dingdingStatus=='COMPLETED' && qualityReport.dingdingResult=='refuse' }">
							          钉钉审批(未通过)
								</c:if>
								<c:if test="${qualityReport.dingdingStatus!='COMPLETED' && qualityReport.dingdingStatus!='TERMINATED' }">
								钉钉审批(进行中)
								</c:if>
								</c:if></c:if>
		<a class="btn qq_a share_wechat">可点击本按钮分享到微信群(需使用QQ浏览器)</a>
		  <input type="hidden" value="<fmt:formatDate value="${qualityReport.checkDate}" pattern="MM/dd/yyyy"/>" id="checkDate">
		 	
		 	<div class="clearfix mt10">
		<div class="pull-left list_left">
			<p >项目号：<a href="http://117.144.21.74:10010/project/showDetails?projectNo=${qualityReport.projectNo}"><span>${qualityReport.projectNo}</span></a></p>
			<p >项目名：<span>${project.projectName}</span></p>
			<p >项目等级：<span>
			<c:if test="${project.plantAnalysis==1 }">A</c:if>
			<c:if test="${project.plantAnalysis==2 }">B</c:if>
			<c:if test="${project.plantAnalysis==3 }">C</c:if>
			<c:if test="${project.plantAnalysis==4 }">D</c:if>
			</span></p>
			<p >客户名：<span>${project.customerName}</span></p>
			<p>人员列表：
				<span>${project.sellName.concat('/')}${project.purchaseName!=null?project.purchaseName.concat('/'):''}
				${(project.masterQualityInspection!=null&&project.masterQualityInspection!='')?project.masterQualityInspection.concat('/'):''}
				${(project.zhijian1!=null&&project.zhijian1!='')?project.zhijian1.concat('/'):''}
				${(project.zhijian2!=null&&project.zhijian2!='')?project.zhijian2.concat('/'):''}
				${(project.zhijian3!=null&&project.zhijian3!='')?project.zhijian3.concat('/'):''}
				${(project.qualityInspector1!=null&&project.qualityInspector1!='')?project.qualityInspector1.concat('/'):''}
				${(project.qualityInspector2!=null&&project.qualityInspector2!='')?project.qualityInspector2.concat('/'):''}
				${(project.qualityInspector3!=null&&project.qualityInspector3!='')?project.qualityInspector3.concat('/'):''}
				${(project.qualityInspector4!=null&&project.qualityInspector4!='')?project.qualityInspector4.concat('/'):''}
				${(project.qualityInspector5!=null&&project.qualityInspector5!='')?project.qualityInspector5.concat('/'):''}
				${(project.qualityInspector6!=null&&project.qualityInspector6!='')?project.qualityInspector6.concat('/'):''}
				${(project.qualityInspector7!=null&&project.qualityInspector7!='')?project.qualityInspector7.concat('/'):''}
				</span>
			</p>
			<p><em>工厂列表：</em>
				<span>
				  <c:forEach var="obj" items="${projectFactoryList}" varStatus="i">
				      <c:choose>
					      <c:when test="${i.index == projectFactoryList.size()-1}">
					         ${obj.factoryName}
					      </c:when>
					      <c:otherwise>
					         ${obj.factoryName.concat('/')}
					      </c:otherwise>
				      </c:choose>				      
				    </c:forEach>
				</span>
			</p>
			<p>
					船期时间： <span>${project.sailingDate!=null?project.sailingDate:'出运单系统暂无'}</span>
				</p>
		<c:if test="${materialList!=null }">
		<c:forEach items="${materialList}" var="list">
			     <p>
					
					[材料类型:<c:if test="${list.materialSelection==1 }">不锈钢</c:if>
					<c:if test="${list.materialSelection==2 }">普通碳钢</c:if>
					<c:if test="${list.materialSelection==3 }">特种钢材</c:if>
					<c:if test="${list.materialSelection==4 }">塑料</c:if>
					<c:if test="${list.materialSelection==5 }">铝</c:if>
					<c:if test="${list.materialSelection==6 }">铜</c:if>
					<c:if test="${list.materialSelection==7 }">铸铁铸钢</c:if>
					<c:if test="${list.materialSelection==8 }">其他</c:if>]
					[具体牌号:${list.specificLicensePlate }]
					[材料判定:<c:if test="${list.materialJudgement==1 }">合格</c:if>
					<c:if test="${list.materialJudgement==2 }">不合格</c:if>
					<c:if test="${list.materialJudgement==3 }">不知道</c:if>]
					
					
				</p>
		</c:forEach>	
		<c:if test="${materialCertificate!=null }">
		<p>
		[材料证明：
		<c:forEach items="${materialCertificate}" var="list" varStatus="i">
		<a href="${list.uploadurl }" target="_blank">材料证明${i.count }</a>
		</c:forEach>]
		</p>
		</c:if>
		
				
		</c:if>
		<c:if test="${qualityReport.surfaceResultEntry!=null }">
		<p>
		表面处理选择:<span><c:if test="${qualityReport.surfaceTreatment==1 }">预镀锌的管材或者板材</c:if>
					<c:if test="${qualityReport.surfaceTreatment==2 }">镀锌</c:if>
					<c:if test="${qualityReport.surfaceTreatment==3 }">电镀</c:if>
					<c:if test="${qualityReport.surfaceTreatment==4 }">阳极氧化</c:if>
					<c:if test="${qualityReport.surfaceTreatment==5 }">喷粉</c:if>
					<c:if test="${qualityReport.surfaceTreatment==6 }">油漆</c:if>
					<c:if test="${qualityReport.surfaceTreatment==7 }">无</c:if>
					</span>
		</p>
		
		<c:if test="${qualityReport.filmThickness!=null }"><p>工厂的原料规格和你的进料检验结果:<span>${qualityReport.filmThickness }</span></p></c:if>
		
		<c:if test="${qualityReport.surfaceResultEntry!=null }"><p>测膜厚及观察结果:<span>${qualityReport.surfaceResultEntry }</span></p></c:if>
		
		</c:if>		
		</div>
		<div class="pull-right small_imgs text-center">
			<c:if test="${not empty project.productImg}">
				<img src="https://www.kuaizhizao.cn/product_img/${project.productImg}" class="img-responsive">
			</c:if>
		</div>	
	</div>	
	
	<div class="line mt10 mb10"></div>	
		 	
 		 	<c:if test="${productionPlan.uploader!=null && productionPlan.uploader!=''}">
			<div class="total">
					<div class=""><span>需求汇总</span></div>
<%-- 					onclick="showExcel('${productionPlan.planNode}','${qualityReport.id}')" --%>
					<div><em class="mr10">${productionPlan.uploader}</em><em><fmt:formatDate value="${productionPlan.createDate}" pattern="yyyy-MM-dd"/></em> <a class="btn bgcolor_ff0 pull-right" onclick="showExcel('${productionPlan.planNode}')">预览</a>
					<span>${productionPlan.planDate2String }</span><a href="http://117.144.21.74:33168/upload/zhongwentuzhi/${productionPlan.planNode }" target="_blank">下载</a>
					</div>
					<div class="preview" style="display:none;">
					    <iframe id="view" style="height: 500px;" src='' width='100%' height='100%' frameborder='1'>
						</iframe>
					</div>
			</div> 
			<div class="line mt10 mb10"></div>	
			</c:if>
			
			
								 	
		  <p>基础信息:<!-- <span style="color:red;padding-left: 50px;">可点击浏览器菜单分享本质检报告</span> --></p>
		 <%--  <span>项目号：${qualityReport.projectNo}</span>
		  <span style="padding-left: 20px;">项目名：${project.projectName}</span><br> --%>
	
		  <span>检验地点：${qualityReport.place}</span><br>
		  <span>检验费时：${qualityReport.spendTime}</span>
		  <span>检验日期：<fmt:formatDate value="${qualityReport.checkDate}" pattern="yyyy-MM-dd"/></span><br>
		  <span>订单量：${qualityReport.orders}</span><br>
		  <c:if test="${qualityReport.picNum != null}">
		  <span>图号：${qualityReport.picNum}</span>
		  </c:if>
		  <span class="add_plan">
		  	<br/>检验计划：
		  	<c:if test="${inspectionReportList !=null }">
		  		<a  href="${inspectionReportList.reportName }" target="_blank">文件下载</a>
		  		日期:<span><c:if test="${inspectionReportList.createDate!=null }"><fmt:formatDate value="${inspectionReportList.createDate }" pattern="yyyy-MM-dd"/></c:if></span>
		  	
		  	<p>文字介绍:</p>
		  	<c:if test="${inspectionReportList.emailContent!=null && inspectionReportList.emailContent!=''}"><div class="more_word_wrap">
			  	<p class="more_word">
			  		
			  		${inspectionReportList.emailContent}
			  		
			  	</p>
		  	</div>
		  	<p class="more">点击查看更多</p></c:if></c:if>
		  </span>
		  <div class="s_m">
				<span><b>本样品:</b></span>
				<span class="sample_val z-color color_blue">${qualityReport.statusView}
				<c:if test="${ qualityReport.statusView != '没问题'}">
				<a href="http://117.144.21.74:10010/meetingRecord/inputMeetingRecord?roleNo=100&userId=180&userName=${userName }&projectNo=${qualityReport.projectNo}" target="_blank"><span style="color:red;">(如有异议,请点击我录入验货会结论来推翻)</span></a></c:if></span>
			</div>
			    <c:if test="${qualityReport.statusView != '没问题'}">
					<div class="s_m disnon">
					    <span><b>对采购要求:</b></span>
						<span class="z-color color_blue">${qualityReport.explainCause}</span>
					</div>
				</c:if>
				<c:if test="${qualityReport.statusView eq '有问题，需要采购解决'}">
				<div class="s_m disnon">
				    <span><b>由于问题需要采购解决，相应任务完成情况:</b></span>
					<c:if test="${purchaseTask!=''}"><span class="z-color color_blue">${purchaseTask}</span></c:if>
					<c:if test="${purchaseTask==''}"><span class="red">未完成</span></c:if>
				</div></c:if>
				<span>
					<b>检验结论：</b>
					<span class="z-color color_blue" id="conclusion">${qualityReport.conclusion}</span>
				</span>
				
		 <%--  <div>
		   <span>本次图号对应检验计划完成情况：</span><br>
		        <c:if test="${plans!=null&&plans.size()>0}">
			       <ul class="inspection-ul">
			        <c:forEach var="obj" items="${plans}" varStatus="i">
			           <c:if test="${fn:containsIgnoreCase(qualityReport.picNum, obj.productComponent)}">
			            <li class="c-standard">图号：${obj.productComponent} <span>(${obj.completeCount}/${obj.completeCount+obj.noCompleteCount})</span><button class="btn check_btn" type="button" onclick="openInspection(this,${i.index})">展开</button></li>
			                   <c:forEach var="plan" items="${planList}" varStatus="j">
				                 <c:if test="${obj.productComponent == plan.productComponent}">
				                   <li class="check_index_${i.index}" style="display:none;">
				                   <span class="c-type">${plan.type}:${plan.productStandards}</span> <span class="c-span" <c:if test="${plan.isWork == 0}">style="color: #ec4949;"</c:if>>${plan.isWork == 1 ? '做了': '未做'}</span>
				                  <c:if test="${plan.content!=null&&plan.content!=''}">
				                   <p>说明:</p>
				                   <span>${plan.content}</span>
				                   </c:if>
				                   <c:if test="${plan.inspectionPic!=null&&plan.inspectionPic!=''}">
				                      <c:forEach items="${fn:split(plan.inspectionPic,',')}" var="image">
				                         <p><img src="${image}" alt=""></p>
				                      </c:forEach>
				                   </c:if> 
				                   </li>
				                 </c:if>
					           </c:forEach>
					    </c:if>       
			        </c:forEach>
			       </ul> 
		        </c:if>
		        <c:if test="${plans==null||plans.size()==0}">
		         <span class="color_blue">本报告上传时，系统无相应检验计划/检验计划格式较旧无法解析。</span><br>
		        </c:if>
		  </div> --%>
		  <div class="line mt10 mb10"></div>	
		</div>
		<div class="wrap">
			<div class="wrap_top">

				<div class="imgs" style="overflow: visible;">
					<ul  class="clearfix add_imgs"  style="width: auto;">
					
						<c:forEach var="obj" items="${picList}">
						   <c:if test="${obj.picType != 5}">
							    <li class="">
							       <div>
								    <a><img src="${obj.picName}" alt=""></a>
								    <span>${obj.picExplain == null ? '' : obj.picExplain}</span>
							      </div>	    
							    </li>
						    </c:if>
						</c:forEach>
					</ul>					
				</div>				
				<c:if test="${qualityReport.boxNumber != null && qualityReport.boxNumber != ''}">
					<p >装箱信息：</p>
					<span>箱数：${qualityReport.boxNumber}</span>
					<span>每箱数量：${qualityReport.perQty}</span>
					<span>数量清点：${qualityReport.inventoryQty}</span>
					<span>开箱数量：${qualityReport.openQty}</span>
					<span>开箱比例：${qualityReport.openRate==null?'暂无':qualityReport.openRate}${qualityReport.openRate!=null?'%':''}</span>
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


			<div class="s_m" style="padding-top: 29px;">
				<span><b>本报告是:</b></span>
				<span class="z-color color_blue">${qualityReport.typeView}</span>
			</div>
			<div class="add_question">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th class="th1">合同号</th>
							<th>供应商名</th>
							<th class="th3">合同交期</th>
							<th class="th4">产品是否完成</th>
							<th class="th5">是否需要返工</th>
						</tr>
					</thead>
					<tbody>
					 <c:forEach var="obj" items="${projectFactoryList}" varStatus="i">
						<tr>
							<td><c:if test="${obj.contractNo!=null }">${obj.contractNo }</c:if>
                                <c:if test="${obj.contractNo==null }">合同${i.count }</c:if></td>
							<td>${obj.factoryName }</td>
							<td><c:if test="${obj.sampleDate!=null }"><fmt:formatDate value="${obj.sampleDate }" pattern="yyyy-MM-dd"/></c:if>
                                <c:if test="${obj.deliveryDate!=null }"><fmt:formatDate value="${obj.deliveryDate }" pattern="yyyy-MM-dd"/></c:if>
                            </td>
							<td>
								<label><c:if test="${obj.sampleFinishTime!=null||obj.productFinishTime!=null }">是</c:if> </label>
								<label> <c:if test="${obj.sampleFinishTime==null&&obj.productFinishTime==null }">否</c:if> </label>
							</td>
							<td>
								<label><c:if test="${obj.rework==1 }">是</c:if> </label>
								<label><c:if test="${obj.rework==0 }">否</c:if></label>
							</td>
						</tr></c:forEach>
					</tbody>
				</table>
			</div>
			
			<c:if test="${qualityReport.statusView eq '有问题，需要采购解决'}">
				<div>
				   <c:if test="${qualityReport.operateExplain!=null}">
				      <span><b>整改结论：</b></span><span class="z-color color_blue">${qualityReport.operateExplain}</span>  
				   </c:if>
				   <c:if test="${qualityReport.operateExplain==null}">
				      <span><b>整改结论：</b></span><span class="red">暂无</span>  
				   </c:if>
				   <c:if test="${qualityReport.productFileName!=null}">
				       <span><b>整改完成的图纸、照片、视频：</b></span><a href="/project_img/${qualityReport.projectNo}/product/${qualityReport.productFileName}" download="/project_img/${qualityReport.projectNo}/product/${qualityReport.productFileName}" id="product_file_download">${qualityReport.productFileName}</a>
				   </c:if>
				   <c:if test="${qualityReport.productFileName==null}">
				       <span><b>整改完成的图纸、照片、视频：</b></span><span class="red">暂无</span>
				   </c:if>
				</div>
				
				</c:if>
			
			<%-- <c:if test="${qualityReport.noCheck != null}"> 
			 <div class="s_m">
				<span><b>因为没检具而没法测量尺寸的数量:</b></span>
				<span class="sample_val z-color color_blue">${qualityReport.noCheck}</span>
			</div>
			</c:if>
			<c:if test="${qualityReport.keySizeExceed != null}">
			<div class="s_m">
				<span><b>关键尺寸超差数量:</b></span>
				<span class="sample_val z-color color_blue">${qualityReport.keySizeExceed}</span>
			</div>
			</c:if>
			<c:if test="${qualityReport.factoryInspection != null}">
				<div class="s_m">
					<span><b>工厂有做过哪些检验:</b></span>
					<span class="sample_val z-color color_blue">${qualityReport.factoryInspection}</span>
				</div>
			</c:if> --%>
			<c:if test="${qualityReport.isAllRight == 1}">
				<div class="s_m">
					<span class="sample_val z-color color_blue">含本报告在内如果所有产品已经完全没有问题</span>
				</div>
			</c:if>
			<div class="jy_result">
				
<%-- 				<div><b>项目相关人员:</b><span class="z-color color_blue">${project.sellName.concat('/')}${project.purchaseName.concat('/')}${project.zhijian1.concat('/')}${project.zhijian2.concat('/')}${project.zhijian3.concat('/')}</span></div> --%>
				<div class="contract_date">
					<b>项目相关日期：</b>
					<div><b>合同签订:</b><em class="z-color color_blue f18"><fmt:formatDate value="${project.actualStartDate}" pattern="yyyy-MM-dd"/></em></div>
					<div><b>首次到账:</b><em class="z-color color_blue f18"><fmt:formatDate value="${project.moneyDate}" pattern="yyyy-MM-dd"/></em></div>
					<div><b>样品交期:</b><em class="z-color color_blue f18"><fmt:formatDate value="${project.sampleScheduledDate}" pattern="yyyy-MM-dd"/></em></div>
					<div><b>大货交期:</b><em class="z-color color_blue f18"><fmt:formatDate value="${project.deliveryDate}" pattern="yyyy-MM-dd"/></em></div>
				</div>
			</div>
			<div class="panel dp_panel">
				<div class="panel-heading"><b>大众点评</b></div>
				<div class="panel-body">
					<div class="dp">
						<div class="pl"><textarea class="form-control pl_textarea" <c:if test="${userName==null || userName ==''}">placeholder="您还未登录，请先点击“点评”按钮登录"</c:if>></textarea></div>
						<div class="text-right">
						<button class="btn dp_btn" style="float: left;">						 
						附件上传
						</button>
						<span class="z-span" style="font-size: 14px"></span>
						<form onsubmit="return false;" method="post" enctype="multipart/form-data">
						   <input type="hidden" name="projectNo" value="${qualityReport.projectNo}">
						   <input type="hidden" name="newFileName" id="newFileName">
			               <input type="hidden" name="fileName" id="fileName">
						   <input type="file" name="file" class="pull-left zj_upload" onchange="upload(this)">
						</form>
						<button class="btn dp_btn" onclick="sendComment('${qualityReport.projectNo}','${qualityReport.id}','${param.userName}')">点评</button>
						</div>
					</div>

					<table class="table table-bordered dp_tabel">
						<tbody class="dp_tabel_body">
							
						  <c:forEach var="obj" items="${comments}">
							<tr>
								<td>								
									<div>
										<div>
											<span>${obj.reviewer}</span>
											<em><fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></em>
										</div>
										<div>${obj.comment}</div>
										<c:if test="${obj.newFileName != null && obj.newFileName != ''}">
										   <div>附件：<a href="/project_img/${obj.projectNo}/comment/${obj.newFileName}">${obj.fileName}</a></div>
									    </c:if>
									</div>
									<c:if test="${obj.reviewer eq userName}">
									<button class="btn del" onclick="del(this,'${obj.id}')">删除</button>								
									</c:if>							 
								</td>	
								</tr>
							</c:forEach>															
														
						</tbody>
					</table>
					<a class="btn qq_a share_wechat">
					可点击本按钮分享到微信群(需使用QQ浏览器)
					</a>
				</div>
			</div>
			<div class="s_m">
			   <a href="#" onclick="exportWord('${qualityReport.id}')" class="text-left f14 btn qq_a">以EXCEL文档形式导出</a><br>
			   <a id="share_link" href="#" class="a-line qq_a">使用邮件客户端发送本报告给相关人员</a>
			  <!--  <a class="a-line share_wechat" style="margin-top: 5px;color: #337ab7;">微信分享</a> -->
			</div>
	 		<div class="dh">
				<h3 class="mt10 fw700 f14 mb10 f18">点击查看工厂的大货进度  </h3>
				<c:if test="${projectFactoryList==null || projectFactoryList==''}">
				<span class="text-center display_block f18">尚未有生产合同给到工厂</span>
				</c:if>
				<c:forEach var="obj" items="${projectFactoryList}">
				    <a onclick="jump_quickpart('${obj.projectNo}','${obj.factoryId}')"  target="_blank" class="btn">${obj.factoryName}</a>
				</c:forEach>
			</div> 
			<div class="s_m mb30">
			  	<h3 class="mt10 fw700 f14 mb10 f18">点击观看视频</h3>
			  	<c:forEach var="obj" items="${videos}">
			  	<div class="video_div text-center">
				  	<video controls>
				  	 <c:set value="${ fn:split(obj.qualityVideoName,'.') }" var="names" />
				  	    <c:forEach items="${names}" var="name" varStatus="i">
					  	       <c:if test="${i.index == 1 and name != 'mp4'}">
					  		   <source src="/project_img/${project.projectNo}/video/convert/${obj.videoFile}" type="video/mp4"/>
					  		   </c:if>
					  		   <c:if test="${i.index == 1 and name eq 'mp4'}">
					  		   <source src="/project_img/${project.projectNo}/video/${obj.videoFile}" type="video/mp4"/>
					  		   </c:if>
	                    </c:forEach>
				  	</video>
			  	</div>
			  	<div class="bottom clearfix">
			  		<span class="pull-left f18">${obj.remark}</span>
			  		<span class="pull-right f18">上传日期:<fmt:formatDate value="${obj.uploadTime}" pattern="yyyy-MM-dd"/></span>
			  	</div>
			  	</c:forEach>
			  	<c:if test="${videos == null || videos.size() == 0}">
			  	   <div>暂无</div>
			  	</c:if>
			</div>
		</div>	
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
</body>
</html>
<script src="jquery.min.js"></script>
<script type="text/javascript" src="cookie.js"></script>	
<script src="bootstrap.min.js"></script>
<script src="layer.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="pinchzoom.min.js"></script>
<script type="text/javascript" src="upload-base.js"></script>
<script type="text/javascript" src="jquery-form.js"></script>
<script type="text/javascript" src="shareWechat.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
var mobile=/Android|webOS|iPhone|iPad|BlackBerry/i.test(navigator.userAgent);  //判断是否移动端

    $(function () {
        $('div.pinch-zoom').each(function () {
            new RTP.PinchZoom($(this), {});
        });
    })

      window.onload = window.onresize = function(){

          var clientWidth =document.documentElement.clientWidth;

          document.getElementsByTagName("html")[0].style.fontSize =

                  clientWidth / 768* 40 + "px";          
      }  
      
      
      var productImg = '${project.productImg}';
      var checkImg = $('.add_imgs').find('img:first').attr('src');
      $(function(){    	  
          var projectNo = "${qualityReport.projectNo}";
          var projectName = "${project.projectName}";
          var type = "${qualityReport.type}";
          var place = "${qualityReport.place}";
          var user = "${qualityReport.user}";
          var conclusion = $('#conclusion').text();
          var status = "${qualityReport.status}";
         
          var subject = "";
          if(conclusion){
        	  subject = conclusion;
          }else{
        	  subject = (status == 0 ? '没问题' : (status == 1 ? '有问题' : (status == 2 ? '有问题但需要采购解决：'+projectName : '')));
          }
          
          var checkDate = "<fmt:formatDate value="${qualityReport.checkDate}" pattern="yyyyMMdd"/>";
          
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
				          a = a.replace("http://117.144.21.74:10010","https://www.kuaizhizao.cn");
// 				          a = encodeURIComponent(a);
				          var link = "";
				          var mailSubject = projectNo + " " + projectName + " "+ (type == 0 ? '样品检验' : (type == 1 ? '大货样品' : (type == 2 ? '中期检验' : (type == 3 ? '终期检验' : '')))) + " " + place + " " + user + " " + checkDate;
				          $('title').html(mailSubject);
				          $('meta[name="description"]').attr('content',mailSubject);
				          if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
				        	  link = 'mailto:'+sendMail+'?'+cc_mail+'&subject='+mailSubject+'&body=' + encodeURIComponent(conclusion) + a; 
			        	  } else if (/(Android)/i.test(navigator.userAgent)) {
			        		  link = 'mailto:'+sendMail+'?'+cc_mail+'&subject='+mailSubject+'&body=' + encodeURIComponent(conclusion) + a; 
			        	  }else{
			        		  link = 'mailto:'+sendMail_pc+'?'+cc_mail_pc+'&subject='+mailSubject+'&body='+ encodeURIComponent(conclusion) + a; 
			        	  }				          
				          $('#share_link').attr('href',link);	
				          
				          var link = location.href.replace("http://117.144.21.74:10010","www.kuaizhizao.cn");
				          //微信分享
				          var img = productImg?'https://www.kuaizhizao.cn/product_img/'+productImg:'https://www.kuaizhizao.cn/product_img/'+checkImg;
				          var shareInfo = {'url':a,'title':mailSubject,'desc':subject,'type':2,'img':img};
				          $('.share_wechat').attr('onclick','shareWechat('+JSON.stringify(shareInfo).replace(/\"/g,"'")+')');
				          
				          //title和description赋值
				          $('title').text(mailSubject);
				          $('meta[name="Description"]').attr('content',subject);
					  }
			     }			
			})    	  
      })

	function exportWord(reportId){
		window.location = "${ctx}/exportReportExcel?reportId="+reportId;
	}	
	function back(){     
		var projectNo = '${qualityReport.projectNo}';
		var roleNo = '${user.roleNo}';
		var userName = '${user.userName}';
		var userId = '${user.id}';
        if($.cookie('name')){
			window.location = '${ctx}/project/showDetails?projectNo='+projectNo+'&roleNo='+roleNo+'&userId='+userId+'&userName='+userName;	
		}else{
			window.location = '${ctx}/index.jsp';
		}
	}	
	// 小图放大
	$('.add_imgs img').click(function(){
		if(mobile){  //移动端
			$('.bigpic,.bigpic span').show();
			var src = $(this).attr('src');
			$('.bigpic img').attr('src',src);
		  }else{  //PC端
			  var windowHeight = $(window).height();
			  var windowWidth = $(window).width();
			  var imgHeight = $(this).height();
			  var imgWidth = $(this).width();
			  var imgFlag = 0;
			  var imgSize = 0;
			  var imgSize1 = 0;
			  if(imgHeight>imgWidth){  //图片高度比宽度大
				  imgFlag = 1;
				  imgSize = windowHeight * imgFlag;
				  imgSize1=imgSize*imgWidth/imgHeight;
				  
			  }else{  //图片宽度比高度大
				  imgFlag = imgWidth/imgHeight;
				  imgSize = windowWidth/imgFlag;
				  imgSize1=imgSize*imgHeight/imgWidth;
				  if(imgSize1>windowHeight){
					  imgSize1=windowHeight;
					  imgSize= imgFlag*imgSize1;
				  }
				 
			  }
			  $('.big_pic').show();
			  var src = $(this).attr('src');
			  $('.big_pic img').attr('src',src);
			  if(imgHeight>imgWidth){
			  $('.big_pic img').css("width",imgSize1);
			  $('.big_pic img').css("height",imgSize);
			  }else{
			  
			  $('.big_pic img').css("width",imgSize);
			  $('.big_pic img').css("height",imgSize1);
			  }
		  }  
				
	});
	$('.big_pic').click(function(){  //PC端关闭大图弹窗
		 $('.big_pic').hide();
	});

	$('.bigpic span').click(function(){    //移动端关闭大图弹窗
		$('.bigpic,.bigpic span').hide();	
	});	
	$('.bigpic').click(function(){
		$('.bigpic').hide();
	});
   //删除评论
	 function del(obj,id){		
	   
		 layer.open({
				type:1,
				skin:'finish-btn',
				title:'是否确认删除',
				anim:4,
				shade:0.6,
				shadeClose:true,
				btn:['确定','取消'],
			})		
			
		$('.finish-btn .layui-layer-btn0').click(function(){
			 $.ajax({
			     type:"post",                   
			     url:"${ctx}/project/delComment",           
			     data:{
			    	 id:id 
			     },              
			     success:function(json){
// 			    	 var json = eval("(" + data +")");
					 if(json.ok){
						 $(obj).parent().parent().remove();
					 }else{
						 layer.msg("删除失败",{time:2000}); 
					 }
			     }
			 })	
		})
	}	   
   //微信分享
   function shareWechat(shareInfo){
	   qqShare(shareInfo);
   }  

   function sendComment(projectNo,reportId, userName){
	  if(!$.cookie('name')){
		  var a = location.href;
		  a = encodeURIComponent(a);
		  window.location = 'https://www.kuaizhizao.cn/index.jsp?purchase_history='+a;
	  }	 
	 
	  var comment = $('.pl_textarea').val();
	  if(!comment){
		  layer.msg("留言不能为空",{time:2000}); 
		  return false;
	  }
	  var newFileName = $('#newFileName').val();
	  var fileName = $('#fileName').val();
	  
	  $.ajax({
		     type:"post",                   
		     url:"${ctx}/project/projectComment",           
		     data:{
		    	 projectNo:projectNo,
		    	 reportId:reportId,
		    	 comment:comment,
		    	 fileName:fileName,
		    	 newFileName:newFileName,
		    	 userName:userName
		     },              
		     success:function(json){
// 		    	 var json = eval("(" + data +")");
				 if(json.ok){
					 var fileDiv = '';
					 //添加附件显示
					 if(newFileName){
						 fileDiv = '<div>附件：<a href="/project_img/'+projectNo+'/comment/'+newFileName+'">'+fileName+'</a></div>';
					 }					 
					 $('.dp_tabel_body').before('<tr><td><div><div><span>'+json.data.userName+' </span><em>'+json.data.createDate+'</em></div><div>'+comment+'</div>'+fileDiv+'</div><button class="btn del" onclick="del(this,\''+json.data.id+'\')">删除</button></td></tr>');			     
				 
				 }else{
					 if(json.message == '请登录'){
						 var a = location.href;
						 a = encodeURIComponent(a);
						 window.location = 'https://www.kuaizhizao.cn/index.jsp?purchase_history='+a;
					 }
				 } 
		     }
		})	 
 }
 // 样品没问题新需求
 var sample_val = $('.sample_val').val();
 if(sample_val == '没问题'){
	 $('.disnon').hide();
 }
 //
 $('.sure').click(function(){
	 $('.add_supplier').hide();
 })
 //
 $('.add_btn').click(function(){
	 $('.add_supplier').show();
 })
 
 //跳转快制造工厂
 function jump_quickpart(projectNo,supplierId){	 
	 if(!supplierId){
		 layer.msg("该工厂还未在快制造注册",{time:2000});  
		 return false;
	 }
	 var a = location.href;
     a = a.replace("http://117.144.21.74:10010","https://www.kuaizhizao.cn");
     a = encodeURIComponent(a);
	 var name = $.cookie('name');
	 if(!name){
		 window.location = 'https://www.kuaizhizao.cn/index.jsp?purchase_history='+a;
	 }else{
		 window.location = 'https://www.kuaizhizao.cn/report/reportListPurchase?csgOrderId='+projectNo+'&supplierId='+supplierId+'&factoryId=0&realName='+name;
	 }
 }
 
 
    //跳转质检报告列表页
	function goIndex(link){
		 var a = encodeURIComponent(link);
		 if(!$.cookie('name')){
			 window.location = 'https://www.kuaizhizao.cn/index.jsp?purchase_history='+a;
		 }else{
			 window.location = link;
		 }
	}
 
 
 
    
	//上传文件
	function upload(obj){	
			var path = $(obj).val();
		    sppath = path.split("\\");
		    var fileName = sppath[sppath.length-1];	  	   
		    if(fileName == null || fileName == '' || fileName == undefined){
		    	return false;
		    }else{
			   $('#fileName').val(fileName);
	    	   autTime(); 
			   $('#upload_title').children().text("上传进度");
		    }	 		    	
	     		
			  
			  //先上传后获取上传文件路径
			 $(obj).parents('form').ajaxSubmit({
				type: "post",
				url: "/quality/commentUpload",
		     	dataType: "text",
		     	success: function(str){
		     	var result = eval('(' + str + ')');		
		     	var newFileName = result.data;
			     	if(result.ok){  
			     		$(obj).parents('form').find('input[name="newFileName"]').val(newFileName); 		
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
    
   
    
    function showExcel(planNode){
    	if($('.preview').css("display")=="none"){
    		$('#view').attr('src','https://view.officeapps.live.com/op/view.aspx?src=http://www.kuaizhizao.cn/upload/zhongwentuzhi/'+planNode+'');
    		$('.preview').show();
    	}else{
    		$('.preview').hide();
    	}   	
    }
 
 
 $(function(){

	    //微信分享链接
	    var appId = "";
	    var timestamp = 0;
	    var nonceStr = "";
	    var signature = "";
        var id = '${qualityReport.id}';
        var title = 'Inspection Report'+'${project.projectNameEn == null ? project.projectName : project.projectNameEn}';   // 分享标题
        var desc = $('#checkDate').val()+"\n China Synergy Group";    // 分享描述
        var a = 'https://www.kuaizhizao.cn/quality/shareQualityEn?id='+id;       // 分享链接
        var img = productImg?'https://www.kuaizhizao.cn/product_img/'+productImg:'https://www.kuaizhizao.cn/product_img/'+checkImg;
        
        
	    $.ajax({
	        async : false,
	        type : "GET",// 请求方式
	        url : "https://www.kuaizhizao.cn/wimpl/signature.do",// 地址，就是action请求路径
	        data : {
	            'pageUrl':window.location.href.split('#')[0]
	        },
	        xhrFields:{withCredentials:true},
	        dataType : "json",// 数据类型text xml json script jsonp
	        success : function(msg) {
	            appId = msg.appid;
	            timestamp = msg.timestamp;
	            nonceStr = msg.noncestr;
	            signature = msg.signature;
	        },
	        error : function() {
	            setTimeout(function(){
	                //window.location.href = "/fastermake-wechat/m-zh/error.html";
	            }, 0);

	        }
	    })


	    
	    wx.config({

	        debug: false, // true开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。

	        appId: appId, // 公众号的唯一标识

	        timestamp: timestamp, // 时间戳

	        nonceStr: nonceStr, // 随机串

	        signature: signature,// 签名

	        jsApiList: ['onMenuShareAppMessage','onMenuShareTimeline','hideMenuItems','showOptionMenu','showMenuItems'] // 需要使用的JS接口列表

	    });

	    wx.ready(function(){

	        //不隐藏菜单
	        wx.showOptionMenu();

	        //隐藏分享到QQ、QQ空间，微博和脸书功能
	        wx.hideMenuItems({

	            menuList: ['menuItem:share:qq','menuItem:share:QZone','menuItem:share:weiboApp','menuItem:share:facebook']

	        });
	        //开放分享给朋友、分享到朋友圈功能
	        wx.showMenuItems({

	            menuList: ['menuItem:share:appMessage','menuItem:share:timeline']

	        });
	        //分享给朋友
	        wx.onMenuShareAppMessage({

	            title: title, // 分享标题

	            desc: desc, // 分享描述

	            link : a, // 分享链接

	            imgUrl: img, // 分享图标

	            type: 'link', // 分享类型,music、video或link，不填默认为link

	            /* dataUrl: '',*/ // 如果type是music或video，则要提供数据链接，默认为空

	            success: function () {
	                // 用户确认分享后执行的回调函数

	                setTimeout(function(){
	                	 layer.msg("分享成功",{time:1000}); 
	                }, 0);

	            },

	            /*cancel: function () {
	                // 用户取消分享后执行的回调函数

	            }*/

	        });
	    })
	    })
	    
	    
 // 小图点击放大
$('.small_imgs').click(function(){
	var src = $('.small_imgs img').attr('src');
	$('.big_pic_transparent,.big_pic').show();
	$('.big_pic img').attr('src',src);  
});
 $('.big_pic_transparent').click(function(){
  $('.big_pic_transparent,.big_pic').hide();
 })
 // 顶部返回箭头显示隐藏   
  $(document).scroll(function(){	 
	 var h =  $(document).scrollTop();
	 if(h > 0){
		 $('.back_top').show();
	 }else{
		 $('.back_top').hide();
	 }
	});
 
 
    //展开
	function openInspection(obj,index){
		if($(obj).text() == '收起'){
			$(obj).text('展开');
			$(obj).parents('ul').find('.check_index_'+index+'').hide();		
		}else{
			$(obj).text('收起');
			$(obj).parents('ul').find('.check_index_'+index+'').show();		
		}		
	}
    
 // 点击显示更多
 
 $('.add_plan .more').click(function(){
	 var wrap_h = $('.more_word_wrap').height();
	 var word_h = $('.more_word_wrap .more_word').height();
	 if(wrap_h == '75'){
		 $('.more_word_wrap').css({'height':word_h + 'px'});
	 }else if(wrap_h == word_h){
		 $('.more_word_wrap').css({'height':'75px'});
	 }
	 
	 
	 
 })
    
  //上传钉钉审批
		 function sendDataToDingTalk(dingTalkId,id,planNode){
			 
			 $.ajax({
        	 	    url: "https://www.kuaizhizao.cn/Ding-Talk/qualityInspectionReport2",
        	 	    type: "post",
        	 	   dataType:"text",
        	 	    data: {
        	 	    	dingTalkId:dingTalkId,
        	 	    	qualityId:id,
        	 	    	url:planNode,
        	 	    	selectButton:1
        	 	    },
        	 	    success: function (data) {
        	 	    	 $.ajax({
        	        	 	    url: "${ctx}/quality/updateProcess",
        	        	 	    type: "post",
        	        	 	   dataType:"text",
        	        	 	    data: {
        	        	 	    	id:id,
        	        	 	    	process_instance_id:data
        	        	 	    },
        	        	 	    success: function (data1) {
        	        	 	    window.location.reload();	
        	        	 	    	
        	        	 	    },
        	        	 	   
        	        	 	   
        	        	 	    
        	    	  		});	
        	 	    	
        	 	    },
        	 	   error:function(data){
        	 		  
        	 		  alert("数据未传钉钉审批");    
        	 	    }
        	 	   
        	 	    
    	  		});
			 
			
	    }	  
</script>



























