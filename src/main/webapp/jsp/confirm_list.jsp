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
<meta http-equiv="refresh" content="600">
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>电子准予出货确认单</title>
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="add.css">

</head>
<body>
	<div class="confirm_list">
		<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">						
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p>确认删除该出货单？</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"  id="delete">确定</button>
						<button type="button" class="btn-primary close1 btn" data-dismiss="modal" aria-label="Close1">取消</button>
					</div>
				</div>
			</div>
		</div>
		<h1 class="customer_complaint_h1">
			电子准予出货确认单
			<div class="btns">
				<a class="select_blank btn"
					href="http://117.144.21.74:10010/user/toIndex">返回功能选择页</a>
			</div>
		</h1>
		<form action="/complaint/addShippingConfirmation" method="post">
			<div class="form-group add clearfix mt10">
				<span class="pull-left mr10">SHS</span> <input type="text"
					class="form-control pull-left mr10" id="project_no"
					name="projectNo">
				<button class="btn bgcolor_ff0 pull-left" type="button"
					onclick="add_shipping(this)">新建电子出货确认单</button>
			</div>
		</form>
		<p>1、如果是大货，你需要先做“出货联系单”（安排运输和报关）， 再做“准予出货确认单”（偏重质量）</p>
		<p>2、如果是样品，只要做“准予出货确认单”</p>
		<form id="form4" action="/complaint/queryShippingList" method="post">
		   <div class="form-group add clearfix mt10">
				<span class="pull-left mr10">出货类型</span>
				<select style="height:29px;" name="sampleOrProduct">
				    <option <c:if test="${sampleOrProduct == -1}">selected</c:if> value="">全部</option>
				    <option value="0" <c:if test="${sampleOrProduct == 0}">selected</c:if>>样品出货</option>
				    <option value="1" <c:if test="${sampleOrProduct == 1}">selected</c:if>>大货出货</option>
				</select>
				<span class="mr10" style="margin-left: 35px;">状态</span>
				<select style="height:29px;" name="isComplete">
				    <option <c:if test="${isComplete == -1}">selected</c:if> value="-1">全部</option>
				    <option value="1" <c:if test="${isComplete == 1}">selected</c:if>>准予出货</option>
				    <option value="0" <c:if test="${isComplete == 0 || isCompleteStr == null || isCompleteStr == ''}">selected</c:if>>未准予出货</option>
				</select>
			</div>
			<div class="form-group add clearfix mt10">
				<span class="pull-left mr10">SHS</span> <input type="text"
					class="form-control pull-left mr10" name="inputKey" placeholder="项目号"
					value="${inputKey}">
				<button type="button" class="btn bgcolor_ff0 pull-left"
					onclick="searchProjectData(1)">查询</button>
			</div>
			<ul id="shipping_ul">
				<c:forEach var="obj" items="${shippings}" varStatus="i">
					<li class="mt10 clearfix ">
							<c:choose>
									<c:when test="${fn:contains(obj.serialNumber,'TQ') || (userName ne 'ninazhao' && fn:containsIgnoreCase(userName, obj.createPerson) == false)}">
										<button type="button" class="btn btn-default del pull-left mr5">删除</button>
									</c:when>
									<c:otherwise>
										<button type="button" class="btn  del pull-left mr5" data-toggle="modal"
											data-target=".bs-example-modal-sm" onclick="showNotice(${obj.id},${i.index})" style="background-color:red;color: #fff;">删除</button>								
									</c:otherwise>
							</c:choose>
							<div class="pull-left ship_info">
							<div class="display_iline_block list_cont pull-left ">								
								<a href="https://www.kuaizhizao.cn/complaint/detail?id=${obj.id}&userName=${userName}" target="_blank"><em>${obj.serialNumber}</em><span>_</span><span><fmt:formatDate
										value="${obj.createTime}" pattern="yyyy/MM/dd" />_</span></a>
								<c:if test="${obj.isComplete == 0 && obj.isSign == false}">
									<span class="red">未填全不能签字</span>
								</c:if>
								<c:if test="${obj.isComplete == 0 && obj.isSign == true}">
									<span class="red">可签字未签完</span>
								</c:if>
								<c:if test="${obj.isComplete == 1}">
									<span class="blue" style="color:#33b73e;">准予出货</span>
								</c:if>
	<%-- 							<span>${obj.createPerson}</span>建立的 --%>
							</div>	
							<br>
							<div class="display_iline_block list_cont pull-left ">基本信息录入:
								  <c:choose>
									 <c:when test="${obj.firstPerson != null && obj.firstPerson != ''}">
									     <span style="color:#33b73e;">1、${obj.sellName}</span>
									 </c:when>
									 <c:otherwise>
								     	 <span class="red">1、${obj.sellName}</span>
									 </c:otherwise>
								  </c:choose>
								  <c:choose>
									 <c:when test="${obj.secondPerson != null && obj.secondPerson != ''}">
									     <span style="color:#33b73e;">2、
									     <c:if test="${obj.masterQualityInspection != null}">${obj.masterQualityInspection}</c:if>
									      <c:if test="${obj.zhijian1 != null}">${obj.zhijian1}</c:if>
									      <c:if test="${obj.zhijian2 != null && obj.zhijian2 != ''}">/${obj.zhijian2}</c:if>
									      <c:if test="${obj.zhijian3 != null && obj.zhijian3 != ''}">/${obj.zhijian3}</c:if>
									      <c:if test="${obj.qualityInspector1 != null && obj.qualityInspector2 != ''}">/${obj.qualityInspector1}</c:if>
									      <c:if test="${obj.qualityInspector2 != null && obj.qualityInspector2 != ''}">/${obj.qualityInspector2}</c:if>
									      <c:if test="${obj.qualityInspector3 != null && obj.qualityInspector3 != ''}">/${obj.qualityInspector3}</c:if>
									      <c:if test="${obj.qualityInspector4 != null && obj.qualityInspector4 != ''}">/${obj.qualityInspector4}</c:if>
									      <c:if test="${obj.qualityInspector5 != null && obj.qualityInspector5 != ''}">/${obj.qualityInspector5}</c:if>
									      <c:if test="${obj.qualityInspector6 != null && obj.qualityInspector6 != ''}">/${obj.qualityInspector6}</c:if>
									      <c:if test="${obj.qualityInspector7 != null && obj.qualityInspector7 != ''}">/${obj.qualityInspector7}</c:if>
                                          </span>
									 </c:when>
									 <c:otherwise>
								     	 <span class="red">2、
									     <c:if test="${obj.masterQualityInspection != null}">${obj.masterQualityInspection}</c:if>
									      <c:if test="${obj.zhijian1 != null}">${obj.zhijian1}</c:if>
									      <c:if test="${obj.zhijian2 != null && obj.zhijian2 != ''}">/${obj.zhijian2}</c:if>
									      <c:if test="${obj.zhijian3 != null && obj.zhijian3 != ''}">/${obj.zhijian3}</c:if>
									      <c:if test="${obj.qualityInspector1 != null && obj.qualityInspector2 != ''}">/${obj.qualityInspector1}</c:if>
									      <c:if test="${obj.qualityInspector2 != null && obj.qualityInspector2 != ''}">/${obj.qualityInspector2}</c:if>
									      <c:if test="${obj.qualityInspector3 != null && obj.qualityInspector3 != ''}">/${obj.qualityInspector3}</c:if>
									      <c:if test="${obj.qualityInspector4 != null && obj.qualityInspector4 != ''}">/${obj.qualityInspector4}</c:if>
									      <c:if test="${obj.qualityInspector5 != null && obj.qualityInspector5 != ''}">/${obj.qualityInspector5}</c:if>
									      <c:if test="${obj.qualityInspector6 != null && obj.qualityInspector6 != ''}">/${obj.qualityInspector6}</c:if>
									      <c:if test="${obj.qualityInspector7 != null && obj.qualityInspector7 != ''}">/${obj.qualityInspector7}</c:if>
                                           </span>
									 </c:otherwise>
								  </c:choose>
								  <c:choose>
									 <c:when test="${obj.thirdPerson != null && obj.thirdPerson != ''}">
									     <span style="color:#33b73e;">3、
									      <c:if test="${obj.masterQualityInspection != null}">${obj.masterQualityInspection}</c:if>
									      <c:if test="${obj.zhijian1 != null}">${obj.zhijian1}</c:if>
									      <c:if test="${obj.zhijian2 != null && obj.zhijian2 != ''}">/${obj.zhijian2}</c:if>
									      <c:if test="${obj.zhijian3 != null && obj.zhijian3 != ''}">/${obj.zhijian3}</c:if>
									      <c:if test="${obj.qualityInspector1 != null && obj.qualityInspector2 != ''}">/${obj.qualityInspector1}</c:if>
									      <c:if test="${obj.qualityInspector2 != null && obj.qualityInspector2 != ''}">/${obj.qualityInspector2}</c:if>
									      <c:if test="${obj.qualityInspector3 != null && obj.qualityInspector3 != ''}">/${obj.qualityInspector3}</c:if>
									      <c:if test="${obj.qualityInspector4 != null && obj.qualityInspector4 != ''}">/${obj.qualityInspector4}</c:if>
									      <c:if test="${obj.qualityInspector5 != null && obj.qualityInspector5 != ''}">/${obj.qualityInspector5}</c:if>
									      <c:if test="${obj.qualityInspector6 != null && obj.qualityInspector6 != ''}">/${obj.qualityInspector6}</c:if>
									      <c:if test="${obj.qualityInspector7 != null && obj.qualityInspector7 != ''}">/${obj.qualityInspector7}</c:if>
                                           </span>
									 </c:when>
									 <c:otherwise>
								     	 <span class="red">3、
									      <c:if test="${obj.masterQualityInspection != null}">${obj.masterQualityInspection}</c:if>
									      <c:if test="${obj.zhijian1 != null}">${obj.zhijian1}</c:if>
									      <c:if test="${obj.zhijian2 != null && obj.zhijian2 != ''}">/${obj.zhijian2}</c:if>
									      <c:if test="${obj.zhijian3 != null && obj.zhijian3 != ''}">/${obj.zhijian3}</c:if>
									      <c:if test="${obj.qualityInspector1 != null && obj.qualityInspector2 != ''}">/${obj.qualityInspector1}</c:if>
									      <c:if test="${obj.qualityInspector2 != null && obj.qualityInspector2 != ''}">/${obj.qualityInspector2}</c:if>
									      <c:if test="${obj.qualityInspector3 != null && obj.qualityInspector3 != ''}">/${obj.qualityInspector3}</c:if>
									      <c:if test="${obj.qualityInspector4 != null && obj.qualityInspector4 != ''}">/${obj.qualityInspector4}</c:if>
									      <c:if test="${obj.qualityInspector5 != null && obj.qualityInspector5 != ''}">/${obj.qualityInspector5}</c:if>
									      <c:if test="${obj.qualityInspector6 != null && obj.qualityInspector6 != ''}">/${obj.qualityInspector6}</c:if>
									      <c:if test="${obj.qualityInspector7 != null && obj.qualityInspector7 != ''}">/${obj.qualityInspector7}</c:if>
                                           </span>
									 </c:otherwise>
								  </c:choose>
								  <c:choose>
									 <c:when test="${obj.fourthPerson != null && obj.fourthPerson != ''}">
									     <span style="color:#33b73e;">4、${obj.sellName}</span>
									 </c:when>
									 <c:otherwise>
								     	 <span class="red">4、${obj.sellName}</span>
									 </c:otherwise>
								  </c:choose>
							</div>	
							<br>	
							<div class="display_iline_block list_cont pull-left ">签名:
								  <c:choose>
									 <c:when test="${obj.salesConfirm != null && obj.salesConfirm != ''}">
									     <span style="color:#33b73e;">${obj.sellName}</span>
									 </c:when>
									 <c:otherwise>
								     	 <span class="red">${obj.sellName}</span>
									 </c:otherwise>
								  </c:choose>
								  <c:if test="${obj.purchaseName!=null&&obj.purchaseName!=''}">
									  <c:choose>
										 <c:when test="${obj.purchaseConfirm != null && obj.purchaseConfirm != ''}">
										     <span style="color:#33b73e;">、${obj.purchaseName}</span>
										 </c:when>
										 <c:otherwise>
									     	 <span class="red">、${obj.purchaseName}</span>
										 </c:otherwise>
									  </c:choose>
								  </c:if> 
									  <c:choose>
										 <c:when test="${obj.qualityLeaderConfirm != null && obj.qualityLeaderConfirm != ''}">
										     <span style="color:#33b73e;">、质检总监</span>
										 </c:when>
										 <c:otherwise>
									     	 <span class="red">、质检总监</span>
										 </c:otherwise>
									  </c:choose>
							
							     <c:if test="${obj.isQualityLeaderConfirm == 1}">
									  <c:choose>
										 <c:when test="${obj.purchaseLeaderConfirm != null && obj.purchaseLeaderConfirm != ''}">
										     <span style="color:#33b73e;">、姜工</span>
										 </c:when>
										 <c:otherwise>
									     	 <span class="red">、姜工</span>
										 </c:otherwise>
									  </c:choose>
								 </c:if> 
								 
							     <c:if test="${obj.isBossConfirm == 1}">
									  <c:choose>
										 <c:when test="${obj.bossConfirm != null && obj.bossConfirm != ''}">
										     <span style="color:#33b73e;">、Ed</span>
										 </c:when>
										 <c:otherwise>
									     	 <span class="red">、Ed</span>
										 </c:otherwise>
									  </c:choose>
								 </c:if> 
							</div>
							<br>							
							<div class="display_iline_block list_cont pull-left">
							    <c:forEach var="c" items="${obj.comments}" varStatus="i">
							        ${c.reviewer}:<span class="blue">${c.comment}</span>
							    </c:forEach>     
							</div>	
							</div>				
						</li>
				</c:forEach>
				
			</ul>
			<div class="page-box mt10">
				当前页/总页:<span style="color: red" id="pageNumberSpan">${page}</span>/
				<span id="countPageSpan" style="color: red">${lastNum}</span>&nbsp;
				<a class="emanagergetpagea first-padding"
					onclick="searchProjectData(1)" title="第一页">首页</a> <a
					class="emanagergetpagea first-padding"
					<c:if test="${page > 1}">onclick="searchProjectData(2)"</c:if>
					title="上一页(第1页)">上页</a> <a class="emanagergetpagea"
					<c:if test="${page < lastNum}">onclick="searchProjectData(3)"</c:if>
					title="下一页(第3页)">下页</a> <a class="emanagergetpagea last_page"
					onclick="searchProjectData(4)" title="最后一页">尾页</a>
			</div>
			<input type="hidden" id="pageStr" name="pageStr"
				value="${page == null ? 1 : page}"> <input type="hidden"
				id="countPage" name="countPage" value="${lastNum}"> <input
				type="hidden" id="pageSize" name="pageSize"
				value="${pageSize == null ? 100 : pageSize}">
		</form>
	</div>
</body>
</html>
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
<script type="text/javascript" src="jquery-form.js"></script>
<script src="layer.js" type="text/javascript" charset="utf-8"></script>


<script type="text/javascript">
	function add_shipping(obj) {
		var projectNo = $('#project_no').val();
		if (!projectNo) {
			layer.msg("项目号不能为空", {
				time : 2000
			});
			return false;
		} else {
			//自动加上SHS，防止自动录入shs
			projectNo = projectNo.toUpperCase().replace("SHS", "");
			projectNo = "SHS" + projectNo;
			$('#project_no').val(projectNo);
		}

		$(obj).parents('form').ajaxSubmit({
			type : "post",
			url : "/complaint/addShippingConfirmation",
			dataType : "text",
			success : function(result) {
				var json = eval("(" + result + ")");
				if (json.ok) {
					layer.msg("录入成功", {
						time : 2000
					});
					window.location.reload();
				} else if (json.message == '您还未登录') {
					var a = location.href;
					a = encodeURIComponent(a);
					window.location = '/index.jsp?purchase_history=' + a;
				} else {
					layer.msg(json.message, {
						time : 2000
					});
				}
			},
			error : function() {
				layer.msg("录入失败", {
					time : 2000
				});
			}

		});
	}

	/**
	 *删除
	 */
	 
	// 消息弹窗 
	function showNotice(id,index){
		$('#delete').attr('onclick','del(\''+id+'\',\''+index+'\')');
	};
	// 取消弹窗确认
	$('.modal-footer button:last-child').click(function(){
		/* $('.modal').hide();
		$('.modal,.modal-backdrop').removeClass('in');
		$('body').removeClass('modal-open'); */
	})
	// 删除本条
	function del(id, index) {
		 if (!id) {
			return false;
		} 
		/* layer.open({
		type:1,
		skin:'finish-btn',
		title:'删除该出货单？',
		anim:4,
		shade:0.6,
		shadeClose:true,
		btn:['确定','取消'],
		})	  */

		 /* $('.finish-btn .layui-layer-btn0').click(function() { */
			$.ajax({
				url : "/complaint/deleteShipping",
				type : "POST",
				data : {
					id : id
				},
				dataType : "json",
				success : function(json) {
					if (json.ok) {
						layer.msg("删除成功", {
							time : 2000
						}); 
						$('#shipping_ul li').eq(index).remove();
					} else {
						layer.msg(json.message, {
							time : 2000
						});
					}
				}
			})
	}

	//查询
	function searchProjectData(obj) {
		var pageNumber = $("#pageStr").val();
		var countPage = $("#countPage").val();
		var pageSize = $("#pageSize").val();
		var projectNo = $("#projectNo").val();
		var type = obj;

		// 1 第一页  2.上一页  3.下一页 4.尾页
		if (type == 1) {
			pageNumber = 1;
		}
		if (type == 2) {//上一页
			if (pageNumber == 1) {
				pageNumber = 1
			} else {
				pageNumber = Number(pageNumber) - 1;
			}
		}
		if (type == 3) {//下一页
			if (pageNumber == countPage) {
				pageNumber = countPage;
			} else {
				pageNumber = Number(pageNumber) + 1;
			}
		}
		if (type == 4) {//尾页
			pageNumber = countPage;
		}
		var inputKey = $("#input_key").val();//关键字查询
		$('#pageStr').val(pageNumber)
		$('#pageNumberSpan').text("")
		$('#countPage').val("")
		$('#countPageSpan').text("")

		$('#form4').submit();
	}
	
	
	//enter事件
	document.onkeydown = function (e) {
	    if (!e) e = window.event;
	    if ((e.keyCode || e.which) == 13) {
	    	searchProjectData(1);
	    }
	}
</script>










