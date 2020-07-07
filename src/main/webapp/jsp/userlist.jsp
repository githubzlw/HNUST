<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head lang="en">
<meta charset="UTF-8">
<title>统计页</title>
</head><link type="text/css" rel="stylesheet" href="${ctx}/css/emana.css" />

<style type="text/css">
.right_out {
	position: absolute;
	top: 12px;
	left: 60%;
	z-index: 2;
}

.right_out img {
	vertical-align: middle;
}

.right_out a {
	color: #fff;
}
.emanagergettable{
	width:800px;
}

.usechange1{margin:20px;}
.td1{width:150px;}
.td2{width:280px;}
.td4,.td5{width:100px;}
.add_table td{padding:5px;}
.userselediv_nor, .userselein{width:auto;}
</style>

<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script>
//查询指定月份数据
function querySalesStatistics(timeCreening,num) {
window.open("/quotation/baojStatistics?timeCreening="+timeCreening+"&num="+num);
		
	
}
</script>
</head>
<body>
<div class="usechange1">


			<div>
				<c:if test="${fyfz==1 }"><h2>1.报价工程师当前的报价数量统计表</h2></c:if>
				<c:if test="${fyfz==9 }"><h2>9.销售工作量统计</h2></c:if>
			</div>
			<br /> 
			<form
				action="/quotation/baojStatistics"
				method="post">
				<table class="usectable">
					<tr>
                     <c:if test="${fyfz==9 }">
                     <input type="button" onclick="querySalesStatistics(1,9);" value="最近2周数据">
                     <input type="button" onclick="querySalesStatistics(2,9);" value="最近1个月数据">
                     <input type="button" onclick="querySalesStatistics(3,9);" value="最近1季度数据">
                      <input type="button" onclick="querySalesStatistics(4,9);" value="最近2季度数据">
                      <br/>
                     </c:if>
						

						<td class="usermatd3" style="padding-left:0;">统计数据:</td>
                             <c:if test="${transtime!=null }">翻译平均时间:${transtime }</c:if>
						<td style="padding-right:15px;">
							<div class="userselediv_nor" style="border:0 none;height: auto;">
								<select name="num" id="audit" class="userselein"
								style="background-color:#fff;color:#333;padding:6px 12px;border:1px solid #ccc;border-radius: 4px;width:auto;">
									
									<option value="1"
										<c:if test="${fyfz==1 }">selected="selected"</c:if>>报价工程师当前的报价数量</option>
									<option value="9"
										<c:if test="${fyfz==9 }">selected="selected"</c:if>>销售工作量统计</option>
									<option value="2"
										<c:if test="${fyfz==2 }">selected="selected"</c:if>>2个月未及时回复客户信息</option>
									<%--<option value="3"
										<c:if test="${fyfz==3 }">selected="selected"</c:if>>2个月放弃的项目客户信息</option>--%>
									<%--<option value="4"
										<c:if test="${fyfz==4 }">selected="selected"</c:if>>2个月内立项未回复客户列表</option>--%>
								
									<%--<option value="5"
										<c:if test="${fyfz==5 }">selected="selected"</c:if>>2翻译最近100个项目及平均翻译时间</option>--%>
								
									<%--<option value="6"
										<c:if test="${fyfz==6 }">selected="selected"</c:if>>2个月内立项至今无报价员</option>--%>
								
									<%--<option value="7"
										<c:if test="${fyfz==7 }">selected="selected"</c:if>>2个月内立项有报价员，5天未报价</option>--%>
									<option value="8"
										<c:if test="${fyfz==8 }">selected="selected"</c:if>>每个月的 AB级客户</option>
								
								</select>
							</div>
						</td>
						


						<td class="usermatd">
							<input type="submit" value="查询客户联系信息"
						   style="background-color:#fff;color:#333;padding:6px 12px;border:1px solid #ccc;border-radius: 4px;">
						</td>
					</tr>
				</table>
			</form>
			<div style="margin:10px 0;">
				<a href="http://117.144.21.74:43900/NBEmail/jsp/customer_statistics_page.html" target="_blank">贸易公司控制面板</a>
			</div>
			<c:if test="${fyfz==1 }">
				<table class="emanagergettable add_table">
				<tr class="emanagergettr">
					<!-- <td>选择</td> -->
                      <td>报价员</td>
					<td class="w100">7天内完成报价数</td>
					<td class="w100">A级未完成报价数量</td>
					<td class="w100">B级未完成报价数量</td>
					<td class="w100">C级未完成报价数量</td>
					<td class="w100">D级未完成报价数量</td>
					
				</tr>
				<c:forEach var="obj" items="${userList}" varStatus="i">
							
								<tr>
									<td>${obj.name}</td>
									<td>${obj.quoteNumber}</td>
									<td>${obj.aLevelProject}</td>
									<td>${obj.bLevelProject}</td>
									<td>${obj.cLevelProject}</td>
									<td>${obj.dLevelProject}</td>
									
								</tr>
							
						</c:forEach>

			</table></c:if>
          <c:if test="${fyfz==9 }"><table class="emanagergettable">
				<tr class="emanagergettr">
					<!-- <td>选择</td> -->
                      <td>销售名</td>
					<td class="w100">等待报价项目(AB/全部)</td>
					<td class="w100">客户抱怨贵了,正在挽救(AB/全部)</td>
					<td class="w100">客户没反馈(AB/全部)</td>
					<td class="w100">和客户积极沟通中(AB/全部)</td>
					<td class="w100">放弃(AB/全部)</td>
					<td class="w100">下单(AB/全部)</td>
				</tr>
				<c:forEach var="obj" items="${userList}" varStatus="i">
							   <tr>
									<td>${obj.name}</td>
									<td><a href="http://117.144.21.74:43888/New-Quotation/quote/quotationStatusStatistics?quotationStatus=1&projectStatus=1&&userName=${obj.name}&timeCreening1=${timeCreening}" target="_blank">${obj.waitingQuotation}</a>/<a href="http://117.144.21.74:43888/New-Quotation/quote/quotationStatusStatistics?quotationStatus=1&projectStatus=2&&userName=${obj.name}&timeCreening1=${timeCreening}" target="_blank">${obj.allWaitingQuotation}</a></td>
									<td><a href="http://117.144.21.74:43888/New-Quotation/quote/quotationStatusStatistics?quotationStatus=4&projectStatus=1&&userName=${obj.name}&timeCreening1=${timeCreening}" target="_blank">${obj.complainingExpensive}</a>/<a href="http://117.144.21.74:43888/New-Quotation/quote/quotationStatusStatistics?quotationStatus=4&projectStatus=2&&userName=${obj.name}&timeCreening1=${timeCreening}" target="_blank">${obj.allComplainingExpensive}</a></td>
									<td><a href="http://117.144.21.74:43888/New-Quotation/quote/quotationStatusStatistics?quotationStatus=6&projectStatus=1&&userName=${obj.name}&timeCreening1=${timeCreening}" target="_blank">${obj.noFeedback}</a>/<a href="http://117.144.21.74:43888/New-Quotation/quote/quotationStatusStatistics?quotationStatus=6&projectStatus=2&&userName=${obj.name}&timeCreening1=${timeCreening}" target="_blank">${obj.allNoFeedback}</a></td>
									<td><a href="http://117.144.21.74:43888/New-Quotation/quote/quotationStatusStatistics?quotationStatus=2&projectStatus=1&&userName=${obj.name}&timeCreening1=${timeCreening}" target="_blank">${obj.activeCommunication}</a>/<a href="http://117.144.21.74:43888/New-Quotation/quote/quotationStatusStatistics?quotationStatus=2&projectStatus=2&&userName=${obj.name}&timeCreening1=${timeCreening}" target="_blank">${obj.allActiveCommunication}</a></td>
									<td><a href="http://117.144.21.74:43888/New-Quotation/quote/quotationStatusStatistics?quotationStatus=5&projectStatus=1&&userName=${obj.name}&timeCreening1=${timeCreening}" target="_blank">${obj.giveUp}</a>/<a href="http://117.144.21.74:43888/New-Quotation/quote/quotationStatusStatistics?quotationStatus=5&projectStatus=2&&userName=${obj.name}&timeCreening1=${timeCreening}" target="_blank">${obj.allGiveUp}</a></td>
									<td><a href="http://117.144.21.74:43888/New-Quotation/quote/quotationStatusStatistics?quotationStatus=3&projectStatus=1&&userName=${obj.name}&timeCreening1=${timeCreening}" target="_blank">${obj.placeOrder}</a>/<a href="http://117.144.21.74:43888/New-Quotation/quote/quotationStatusStatistics?quotationStatus=3&projectStatus=2&&userName=${obj.name}&timeCreening1=${timeCreening}" target="_blank">${obj.allPlaceOrder}</a></td>
								</tr>
						</c:forEach>
              </table></c:if>
				<h4></h4>
			</div>
</body>
</html>