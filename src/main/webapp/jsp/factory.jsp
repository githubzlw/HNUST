<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>工厂库</title>
    <link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/add.css">
    <link rel="stylesheet" href="../css/progressBar.css" />
    <link rel="stylesheet" href="../css/ui.css" />
    <link rel="stylesheet" href="../css/ui.progress-bar.css">
</head>

<body>
    <div class="factory">
        <h1 class="customer_complaint_h1">
            	工厂库
      <!--       <span>现有合作过工厂总数
                <em>m</em> 个 快制造工厂
                <em> n </em>合作过的
                <em>o</em>
            </span> -->                           
            <div class="btns">
                <a class="select_blank btn" target="_blank" href="/user/toIndex">返回功能选择页</a>
            </div>
        </h1>
        <form id="form4" action="/project/queryFactoryList" method="post">
            <input type="hidden" value="${sortNum}" name="sortNum" id="sortNum">
            <input type="hidden" value="${upOrDown}" name="upOrDown" id="upOrDown">
            <div class="form-group check clearfix mt10 time">
                <span>时间段</span>
                <%-- <select name="queryDate">
									    <option value="">所有</option>
									    <option value="1" <c:if test="${queryDate == 1}">selected</c:if>>半年</option>
									    <option value="2" <c:if test="${queryDate == 2}">selected</c:if>>3个月</option>
									    <option value="3" <c:if test="${queryDate == 3}">selected</c:if>>1个月</option>
										</select> 
								--%>
                    <label>
                        <input type="radio" name="queryDate" value="" checked>全部</label>
                    <label>
                        <input type="radio" name="queryDate" value="1" <c:if test="${queryDate == 1}">checked</c:if>>半年</label>
                    <label>
                        <input type="radio" name="queryDate" value="2" <c:if test="${queryDate == 2}">checked</c:if>>三个月</label>
                    <label>
                        <input type="radio" name="queryDate" value="3" <c:if test="${queryDate == 3}">checked</c:if>>一个月</label>
            </div>
            <div class="form-group clearfix mt10">
                <input type="text" class="form-control pull-left" placeholder="请输入工厂名" name="inputKey" id="input_key" value="${inputKey}">
                <button type="button" class="btn bgcolor_ff0 pull-right" onclick="searchProjectData(1)">查询</button>
            </div>
            <div class="tables clearfix">
                <table class="table1">
                    <thead>
                        <tr>
                            <th><div class="d1">工厂</div></th>
                            <th><div class="d2">城市</div></th>
                            <th>
                                <div class="d d3">
                                   	 交货率 (全项目)                                  
                                    <div class="arrow" filed="1" <c:if test="${sortNum == 1}">style="color: red;"</c:if>>
                                        <span class="s1 glyphicon glyphicon-triangle-top" <c:if test="${sortNum == 1 && upOrDown == 1}">style="display:inline-block;"</c:if>></span>
                                        <span class="s2 glyphicon glyphicon-triangle-bottom display_none" <c:if test="${sortNum == 1 && upOrDown == 1}">style="display:none;"</c:if>></span>
                                    </div>
                                </div>
                            </th>
               
                            <th>
                                <div class="d d4">
                                    	质量投诉率
                                    <br />(全项目)
                                    <div class="arrow" filed="2" <c:if test="${sortNum == 2}">style="color: red;"</c:if>>
                                        <span class="s1 glyphicon glyphicon-triangle-top" <c:if test="${sortNum == 2 && upOrDown == 1}">style="display:inline-block;"</c:if>></span>
                                        <span class="s2 glyphicon glyphicon-triangle-bottom display_none" <c:if test="${sortNum == 2 && upOrDown == 1}">style="display:none;"</c:if>></span>
                                    </div>
                                </div>
                            </th>                    
                            <th>  
                            <div class="d d5">                          
                            	交货率(单一工厂项目)
                            	<div class="arrow" filed="3" <c:if test="${sortNum == 3}">style="color: red;"</c:if>>
                                  <span class="s1 glyphicon glyphicon-triangle-top" <c:if test="${sortNum == 3 && upOrDown == 1}">style="display:inline-block;"</c:if>></span>
                                  <span class="s2 glyphicon glyphicon-triangle-bottom display_none" <c:if test="${sortNum == 3 && upOrDown == 1}">style="display:none;"</c:if>></span>
                              </div>
                           	 </div>
                           	</th>
                            <th>
                            	<div class="d d6">
	                            	质量投诉率<br />(单一工厂项目)
	                            	<div class="arrow" filed="4" <c:if test="${sortNum == 4}">style="color: red;"</c:if>>
	                                  <span class="s1 glyphicon glyphicon-triangle-top" <c:if test="${sortNum == 4 && upOrDown == 1}">style="display:inline-block;"</c:if>></span>
	                                  <span class="s2 glyphicon glyphicon-triangle-bottom display_none" <c:if test="${sortNum == 4 && upOrDown == 1}">style="display:none;"</c:if>></span>
	                              </div>
                           	 </div>
                           	</th>
                            <th>
                            	<div class="d d7">
                           		 	工厂在做的合同数量
                           		 <div class="arrow" filed="0" <c:if test="${sortNum == 0}">style="color: red;"</c:if>>
	                                  <span class="s1 glyphicon glyphicon-triangle-top" <c:if test="${sortNum == 0 && upOrDown == 1}">style="display:inline-block;"</c:if>></span>
	                                  <span class="s2 glyphicon glyphicon-triangle-bottom display_none" <c:if test="${sortNum == 0 && upOrDown == 1}">style="display:none;"</c:if>></span>
	                              </div>
                           	 </div>
                            </th>
                            <th>
                            	<div class="d d8">
                            		工厂评分<br/>(1-5分)/(评分数量)
                            	<div class="arrow" filed="5" <c:if test="${sortNum == 5}">style="color: red;"</c:if>>
	                                  <span class="s1 glyphicon glyphicon-triangle-top" <c:if test="${sortNum == 5 && upOrDown == 1}">style="display:inline-block;"</c:if>></span>
	                                  <span class="s2 glyphicon glyphicon-triangle-bottom display_none" <c:if test="${sortNum == 5 && upOrDown == 1}">style="display:none;"</c:if>></span>
	                              </div>
                           	 </div>
                           	</th>
                            <th>
                            		<div class="d d9">
                                <select onchange="searchProjectData()" name="qualityName">
                                    <option value="">全部质检</option>
                                    <option value="wangjingjun" <c:if test="${qualityName eq 'wangjingjun'}">selected</c:if>>wangjingjun</option>
                                    <option value="maxiaolei" <c:if test="${qualityName eq 'maxiaolei'}">selected</c:if>>maxiaolei</option>
                                    <option value="litie" <c:if test="${qualityName eq 'litie'}">selected</c:if>>litie</option>
                                    <option value="zoumin" <c:if test="${qualityName eq 'zoumin'}">selected</c:if>>zoumin</option>
                                    <option value="wangzhuo" <c:if test="${qualityName eq 'wangzhuo'}">selected</c:if>>wangzhuo</option>
                                    <option value="zhangyouqing" <c:if test="${qualityName eq 'zhangyouqing'}">selected</c:if>>zhangyouqing</option>
                                    <option value="zhaochun" <c:if test="${qualityName eq 'zhaochun'}">selected</c:if>>zhaochun</option>
                                    <option value="zhuxiaojing" <c:if test="${qualityName eq 'zhuxiaojing'}">selected</c:if>>zhuxiaojing</option>
                                </select>
                                <span>去的次数</span>
                                <div class="arrow" filed="6" <c:if test="${sortNum == 6}">style="color: red;"</c:if>>
	                                  <span class="s1 glyphicon glyphicon-triangle-top" <c:if test="${sortNum == 6 && upOrDown == 1}">style="display:inline-block;"</c:if>></span>
	                                  <span class="s2 glyphicon glyphicon-triangle-bottom display_none" <c:if test="${sortNum == 6 && upOrDown == 1}">style="display:none;"</c:if>></span>
	                              </div>
                           	 </div>
                            </th>
                            <th>
                            		<div class="d d10">
                                <select onchange="searchProjectData()" name="purchaseName">
                                    <option value="">全部采购</option>
                                    <option value="zhaoqiang" <c:if test="${purchaseName eq 'zhaoqiang'}">selected</c:if>>zhaoqiang</option>
                                    <option value="xuping" <c:if test="${purchaseName eq 'xuping'}">selected</c:if>>xuping</option>
                                    <option value="xuwei" <c:if test="${purchaseName eq 'xuwei'}">selected</c:if>>xuwei</option>
                                    <option value="ThomasChen" <c:if test="${purchaseName eq 'ThomasChen'}">selected</c:if>>ThomasChen</option>
                                    <option value="Bensonzhang" <c:if test="${purchaseName eq 'Bensonzhang'}">selected</c:if>>Bensonzhang</option>
                                    <option value="RogerQiu" <c:if test="${purchaseName eq 'RogerQiu'}">selected</c:if>>RogerQiu</option>
                                    <option value="chengmingkun" <c:if test="${purchaseName eq 'chengmingkun'}">selected</c:if>>chengmingkun</option>
                                    <option value="shiqinhui" <c:if test="${purchaseName eq 'shiqinhui'}">selected</c:if>>shiqinhui</option>
                                </select>
                                <span>去的次数</span>
                                <div class="arrow" filed="7" <c:if test="${sortNum == 7}">style="color: red;"</c:if>>
	                                  <span class="s1 glyphicon glyphicon-triangle-top" <c:if test="${sortNum == 7 && upOrDown == 1}">style="display:inline-block;"</c:if>></span>
	                                  <span class="s2 glyphicon glyphicon-triangle-bottom display_none" <c:if test="${sortNum == 7 && upOrDown == 1}">style="display:none;"</c:if>></span>
	                              </div>
                           	 </div>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="obj" items="${factoryList}" varStatus="i">
                            <tr>
                                <td>
                                    <a href="/project/queryFactoryProject?factoryName=${obj.factoryName}">${obj.factoryName}</a>
                                </td>
                                <td>${obj.city!= null?obj.city:''}</td>
                                <td>
                                    <c:if test="${obj.onTimeRate == null && queryDate == 1}">无半年内完成项目(全部)</c:if>
                                    <c:if test="${obj.onTimeRate == null && queryDate == 2}">无三个月内完成项目(全部)</c:if>
                                    <c:if test="${obj.onTimeRate == null && queryDate == 3}">无一个月内完成项目(全部)</c:if>
                                    <c:if test="${obj.onTimeRate != null}"><fmt:formatNumber type="number" value="${obj.onTimeRate*100}" pattern="0.00" maxFractionDigits="2"/>%</c:if>
                                </td>
                                <td>
                                <c:if test="${obj.complaintRate == null}">
                                   0
                                </c:if>
                                <c:if test="${obj.complaintRate != null}">
                                <fmt:formatNumber type="number" value="${obj.complaintRate*100}" pattern="0.00" maxFractionDigits="2"/>%
                                </c:if>
                                </td>
                                <td>
                                    <c:if test="${obj.onTimeRateOnly == null && queryDate == 1}">无半年内完成项目(单一工厂)</c:if>
                                    <c:if test="${obj.onTimeRateOnly == null && queryDate == 2}">无三个月内完成项目(单一工厂)</c:if>
                                    <c:if test="${obj.onTimeRateOnly == null && queryDate == 3}">无一个月内完成项目(单一工厂)</c:if>
                                    <c:if test="${obj.onTimeRateOnly != null}"><fmt:formatNumber type="number" value="${obj.onTimeRateOnly*100}" pattern="0.00" maxFractionDigits="2"/>%</c:if>
                                </td>
                                <td>
                                    <c:if test="${obj.complaintRateOnly == null}">
                                     0
	                                </c:if>
	                                <c:if test="${obj.complaintRateOnly != null}">
	                                <fmt:formatNumber type="number" value="${obj.complaintRateOnly*100}" pattern="0.00" maxFractionDigits="2"/>%
	                                </c:if>
                                </td>
                                <td>${obj.projectCount == null ? '0' :obj.projectCount}</td>
                                <td style="color: red;">
                                <c:if test="${obj.avgScore != null}">
                                <fmt:formatNumber type="number" value="${obj.avgScore}" pattern="0.0" maxFractionDigits="1"/>
                                 </c:if>
                                <c:if test="${obj.avgScore == null}">
                                0
                                 </c:if>
                                    <c:if test="${obj.scoreCount != null}">
                                        <span style="font-size: 13px; color: black;">(${obj.scoreCount})</span>
                                    </c:if>
                                </td>
                                <td>${obj.trackQualityCount}</td>
                                <td>${obj.trackPurchaseCount}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>
                                <a href="#">仓库</a>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>${qualityStoreCount}</td>
                            <td>${purchaseStoreCount}</td>
                        </tr>
                        <tr>
                            <td>
                                <a href="#">公司</a>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>${qualityCompanyCount}</td>
                            <td>${purchaseCompanyCount}</td>
                        </tr>
                    </tbody>
                </table>
<!--                 <table class="table2"> -->
<!-- 								    <thead> -->
<!-- 								        <tr> -->
<!-- 								            <th class="th1">工厂名</th> -->
<!-- 								            <th><div class="d2_2">项目号</div></th> -->
<!-- 								            <th><div class="d2_3">项目名</div></th> -->
<!-- 								            <th><div class="d2_4">合同号</div></th> -->
<!-- 								            <th><div class="d2_5">性质</div></th> -->
<!-- 								            <th><div class="d2_6">合同交期</div></th> -->
<!-- 								            <th><div class="d2_7">完成日期</div></th> -->
<!-- 								            <th><div class="d2_8">采购手选状态</div></th> -->
<!-- 								            <th><div class="d2_9">合同总金额</div></th> -->
<!-- 								            <th><div class="d2_10">是否有开工照片</div></th> -->
<!-- 								            <th><div class="d2_11">是否有检验报告</div></th> -->
<!-- 								            <th><div class="d2_12">采购解释</div></th> -->
<!-- 								            <th><div class="d2_13">该项目上传地址</div></th> -->
<!-- 								            <th><div class="d2_14">工厂看的所有项目列表地址</div></th> -->
<!-- 								            <th><div class="d2_15">内部观看地址</div></th> -->
<!-- 								        </tr> -->
<!-- 								    </thead> -->
<!-- 								    <tbody> -->
<%-- 								        <c:forEach var="obj" items="${factoryList}" varStatus="i"> --%>
<!-- 								            <tr> -->
<%-- 								                <td>
<%-- 								                    <a <c:if test="${obj.factoryId != null}">target="_blank" href="https://www.kuaizhizao.cn/report/reportListPurchase?csgOrderId=${obj.projectNo}&supplierId=${obj.factoryId}&factoryId=0&realName=${userName}"</c:if>>${obj.factoryName}</a> --%>
<%-- 								                </td> --%> 
<%-- 								                <td>${obj.projectNo}</td> --%>
<%-- 								                <td>${obj.projectName}</td> --%>
<%-- 								                <td>${obj.contractNo}</td> --%>
<%-- 								                <td>${obj.orderNature == 2? '返修/补货':(obj.deliveryDate!=null?'大货':(obj.sampleDate!=null?'样品':''))}</td> --%>
<!-- 								                <td> -->
<%-- 								                    <c:if test="${obj.deliveryDate!=null}"> --%>
<%-- 								                        <fmt:formatDate value="${obj.deliveryDate}" pattern="yyyy-MM-dd" /> --%>
<%-- 								                    </c:if> --%>
<%-- 								                    <c:if test="${obj.sampleDate!=null}"> --%>
<%-- 								                        <fmt:formatDate value="${obj.sampleDate}" pattern="yyyy-MM-dd" /> --%>
<%-- 								                    </c:if> --%>
<!-- 								                </td> -->
<%-- 								                <td>${obj.productFinishTime!=null?obj.productFinishTime:(obj.sampleFinishTime!=null?obj.sampleFinishTime:'')}</td> --%>
<%-- 								                <td>${obj.orderState == 1 ? '在做模具' : (obj.orderState == 2 ? '在打样或小批量' : (obj.orderState == 3 ? '在大批量生产' :(obj.orderState --%>
<%-- 								                    == 4 ? '因为工厂原因还未做' :(obj.orderState == 5 ? '因为我司或者客户原因暂停' : (obj.orderState == 6 ? '已经部分交货，正在继续生产' : --%>
<%-- 								                    (obj.orderState == 7 ? '已经部分交货，目前暂停' : '未更新状态'))))))}</td> --%>
<%-- 								                <td>${obj.projectAmount}</td> --%>
<%-- 								                <td>${obj.isUploadPic == true ? '有' : '无'}</td> --%>
<%-- 								                <td>${obj.isUploadQualityReport == true ? '有' : '无'}</td> --%>
<!-- 								                <td></td> -->
<!-- 								                <td> -->
<%-- 								                    <a class="bgcolor_ff0" <c:if test="${obj.factoryId!=null && obj.factoryId != ''}">onclick="copyBtn(this,'${obj.projectNo}','${obj.factoryId}',1)"</c:if>>复制</a> --%>
<!-- 								                </td> -->
<!-- 								                <td> -->
<%-- 								                    <a class="bgcolor_ff0" <c:if test="${obj.factoryId!=null && obj.factoryId != ''}">onclick="copyBtn(this,'${obj.projectNo}','${obj.factoryId}',2)"</c:if>>复制</a> --%>
<!-- 								                </td> -->
<!-- 								                <td> -->
<%-- 								                    <a class="bgcolor_ff0" <c:if test="${obj.factoryId!=null && obj.factoryId != ''}">onclick="copyBtn(this,'${obj.projectNo}','${obj.factoryId}',3)"</c:if>>复制</a> --%>
<!-- 								                </td> -->
<!-- 								            </tr> -->
<%-- 								        </c:forEach> --%>
<!-- 								    </tbody> -->
<!-- 								</table> -->
<!--                 <button class="btn bgcolor_ff0 look" type="button">查看所有</button> -->
            </div>
            <input type="hidden" id="pageStr" name="pageStr" value="${page == null ? 1 : page}">
            <input type="hidden" id="countPage" name="countPage" value="${lastNum}">
            <input type="hidden" id="pageSize" name="pageSize" value="${pageSize == null ? 100 : pageSize}">
        </form>
        <div class="page-box">
            当前页/总页:
            <span style="color: red" id="pageNumberSpan">${page}</span>/
            <span id="countPageSpan" style="color: red">${lastNum}</span>&nbsp;
            <a class="emanagergetpagea first-padding" onclick="searchProjectData(1)" title="第一页">首页</a>
            <a class="emanagergetpagea first-padding" <c:if test="${page > 1}">onclick="searchProjectData(2)"</c:if>
                title="上一页(第1页)">上页</a>
            <a class="emanagergetpagea" <c:if test="${page < lastNum}">onclick="searchProjectData(3)"</c:if>
                title="下一页(第3页)">下页</a>
            <a class="emanagergetpagea last_page" onclick="searchProjectData(4)" title="最后一页">尾页</a>
            <!-- 跳转到第<input type="text" class="n" value="n">页 -->
        </div>
    </div>
</body>

</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-form.js"></script>
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>	
<!-- <script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script> -->



<script>
    //查询
    function searchProjectData(obj) {
        var pageNumber = $("#pageStr").val();
        var countPage = $("#countPage").val();
        var pageSize = $("#pageSize").val();
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

    // 排序：
    //根据查询次数判断
    //初始点击倒序排序
    var count = $.cookie('queryCount');
    $('.arrow').click(function () {
    	
       if(!count){
    	   count = 0;
       }
   	   var date = new Date();
   	   date.setTime(date.getTime()+300*1000);//5分钟后过期	   
    	//上次查询
    	var sortNumPrev = $('#sortNum').val();   
    	if(sortNumPrev == $(this).attr('filed')){
	    	count++;
	    }else{
	    	count = 0;
	    }
    	if(count>0){
            $(this).find('.s1,.s2').toggle();
            if($(this).find('.s1').css('display') == 'none'){
            	 $('#upOrDown').val(0);
            }else{
            	 $('#upOrDown').val(1);
            }
            
            $('#sortNum').val($(this).attr('filed'));     
            $.cookie('queryCount',count,{ expires: date, path: '/'});
            searchProjectData(1);
    	}else{
    		$('#upOrDown').val(0);
    		$('#sortNum').val($(this).attr('filed'));
    		count++;
    		$.cookie('queryCount',count,{ expires: date, path: '/'});
    		searchProjectData(1);
    	}    	   	
    });
    // 表格全部显示或隐藏
    $('.factory .look').click(function(){
    	$('.table2').toggle();
    	var display_val = $('.table2').css('display');
    	if(display_val == 'table'){
    		$('.factory .look').text('收起')
    	}else if(display_val == 'none'){
    		$('.factory .look').text('查看更多')
    	}    	
    });
</script>
<script>

// 新增  sisi js  代码
/**
 *复制工厂上传链接
 */
function copyBtn(obj, projectNo, factoryId, type) {

	//工厂id base64加密 
	var base64 = new Base64();
	var factoryIdEncrypt = base64.encode(factoryId);
	if (type == 1) {
		$('#copy').text(
				"http://www.kuaizhizao.cn/report/reportList?csgOrderId="
						+ projectNo + "&supplierId=" + factoryIdEncrypt);
	}
	if (type == 2) {
		$('#copy').text(
				"https://www.kuaizhizao.cn/report/reportList?quoteStatus=2&supplierId="
						+ factoryIdEncrypt);
	}
	if (type == 3) {
		$('#copy').text(
				"https://www.kuaizhizao.cn/report/reportListPurchase?csgOrderId="
						+ projectNo + "&supplierId=" + factoryId
						+ "&factoryId=0&realName=ninazhao");
	}
	var $parm = $('#copy');
	var parm = $parm.get(0);
	parm.select();
	document.execCommand("copy", false, null);
	easyDialog.open({
		container : {
			content : '复制成功'
		},
		overlay : false,
		autoClose : 2000
	});
}
</script>










