<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>统计页面</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

</head><link type="text/css" rel="stylesheet" href="/css/emana.css" />
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="../css/add.css">
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
width:1400px;}

.usechange1{padding: 20px;}

.td1{width:150px;}
.td2{width:185px;}
.td3{width:500px;}
.td4{width:85px;}
.td5{width:100px;}
.td6{width:90px;}
.td7{width:60px;}
.td8{width:100px;}
.table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th{border:0 none;}
.usechange_statistics .btn{padding:6px 12px;}
.usechange_statistics .userselediv_nor{width: auto;height:auto;border-color: #fff;}
.add_sale_table .s{display: inline-block;}
h2{font-weight: 700;font-size:20px;}
.add_tabel {width: 535px;margin-bottom: 0;}
.add_tabel>tbody>tr>td{pading:0;}
</style>

<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript">
</script>
<script type="text/javascript">


function updateOutLookCustomers(cid,num){

	  $.ajax({
		     type:"post",
		     url:"${ctx}/quotation/updateOutLookCustomers",
		     data:{
		    	 cid:cid,
		    	 num:num
		    },
		     success:function(json){

				 if(json.ok){
					 window.location.reload();
				 }else{
					 window.location.reload();
				 }
		     }
		})
}

</script>

<body>
	<div class="cusalldiv">


		<div class="usechange1 usechange_statistics">
			<div style="font-size:20px;font-weight:700;">
				<h2>${title }</h2>
			</div>
			<form
				action="/quotation/baojStatistics"
				method="post">
				<table  class="table add_tabel">
					<tr>


<%--
						<td class="usermatd3">统计数据:
                             <c:if test="${transtime!=null }">Sarah翻译最近100封邮件平均时间:${transtime }天</c:if><br/>
                             <c:if test="${transtime1!=null }">Nicole翻译最近100封邮件平均时间:${transtime1 }天</c:if>
                             </td>--%>
                             <br/>
						<td>
							<div class="userselediv_nor">
								<select name="num" id="audit" class="userselein form-control">

									<option value="1"
										<c:if test="${fyfz==1 }">selected="selected"</c:if>>报价工程师当前的报价数量</option>
									<option value="9"
										<c:if test="${fyfz==9 }">selected="selected"</c:if>>销售工作量统计</option>
									<option value="2"
										<c:if test="${fyfz==2 }">selected="selected"</c:if>>2个月未及时回复客户信息</option>
									<%--<option value="3"
										<c:if test="${fyfz==3 }">selected="selected"</c:if>>2个月放弃的A/B级客户信息</option>--%>
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
						<td>
						<div class="userselediv_nor">
						<select name="saleName" id="saleName" class="userselein form-control">
						<option value="${saleName }">${saleName }</option>
						<option value="FionaYan">FionaYan</option>
						<option value="AmyZhao">AmyZhao</option>
						<option value="tina">tina</option>
						<option value="Paul">Paul</option>
						<option value="Susiehuang">Susiehuang</option>
						</select></div>
						</td>
                      <c:if test="${fyfz==8 }">  <td>
                        <div class="col-sm-3 ">
							<div class="input-group date form_date col-sm-6" data-date=""
								data-date-format="yyyy-mm">
								<input id="createTime" name="createTime"
									class="form-control brt brt_7" size="16" type="text"  <%-- value="${createTime }" --%>
								value="${createTime }"	place="时间筛选" field="时间筛选" placeholder="时间筛选" readonly
									requiredate  style="width:140px;"> <span class="input-group-addon" ><span
									class="glyphicon glyphicon-calendar" ></span></span>
							</div>
							<span></span>
						</div>
						</td></c:if>

						<td class="usermatd">
						<button class="btn btn-default">查询客户联系信息</button>
						<!-- <input type="submit" value="查询客户联系信息"> -->
						</td>
					</tr>
				</table>
			</form>
			<div style="margin-:10px 0;font-size: 14px;">
<a href="http://117.144.21.74:43900/NBEmail/jsp/customer_statistics_page.html" target="_blank" >贸易公司控制面板</a>
			</div>
				<table class="emanagergettable add_sale_table" >
				<tr class="emanagergettr">
					<!-- <td>选择</td> -->

					<td class="td1"><span class="s s1">客户ID-客户名</span></td>
					<td class="td2"><span class="s s2">客户网址</span></td>
					<td class="td3"><span class="s s3">邮件信息</span></td>
					<td class="td4"><span class="s s4">最近报价项目</span></td>
					<td class="td5"><span class="s s5">项目状态</span></td>
					<td class="td5 td6"><span class="s s6">销售</span></td>
					<td class="td5 td7"><span class="s s7">客户状态</span></td>
					<td class="td5 td8"><span class="s s8">是否outlook客户</span></td>
           <c:if test="${fyfz==8 }"><td>创建时间</td></c:if>
           <c:if test="${fyfz==5 }"><td>收到邮件时间</td></c:if>
           <c:if test="${fyfz==5 }"><td>翻译时间</td></c:if>
           <c:if test="${fyfz==5 }"><td>翻译人</td></c:if>
				</tr>
				<c:forEach items="${cusList }" var="u" varStatus="i">
					<tr>
						<td> <span class="s s1"><a
							href="http://117.144.21.74:43900/NBEmail/helpServlet?action=getCustus&className=CustomerServlet&cid=${u.cid }">${u.cid }-${u.firstName}</a></span></td>

						<td><span class="s s2">${u.siteUrl }</span></td>
						<td><span class="s s3">${u.ybcontent }${u.content }</span></td>
						<td><span class="s s4">${u.projectId }</span></td>

						<td>
						<span class="s s5">
						<c:choose>
						<c:when test="${u.projectstatus==1 }">等待报价</c:when>
						<c:when test="${u.projectstatus==2 }">和客户积极沟通中</c:when>
								<c:when test="${u.projectstatus==3 }">已下单</c:when>
								<c:when test="${u.projectstatus==4 }">客户抱怨贵了，正在挽救</c:when>
								<c:when test="${u.projectstatus==8 }">放弃</c:when>
								<c:when test="${u.projectstatus==12 }">客户没反馈</c:when>
							</c:choose></span></td>
                        <td><span class="s s6">${u.saleName }</span></td>
                        <td><span class="s s7"><c:choose>
						<c:when test="${u.isTranslate==1 }">翻译完成</c:when>
						<c:when test="${u.isTranslate==7 }">翻译完成</c:when>
						<c:when test="${u.isTranslate==8 }">下单项目</c:when>
						<c:when test="${u.isTranslate==9 }">完成项目</c:when>
						</c:choose></span></td>
                        <td><span class="s s8"><c:choose>
						<c:when test="${u.outlookCustomers==0 }"><input type="button" onclick="updateOutLookCustomers(${u.cid },1);" value="不是"></c:when>
						<c:when test="${u.outlookCustomers==1 }"><span style="color:blue">是</span></c:when>

							</c:choose></span></td>

						<c:if test="${fyfz==8 }"><td>${u.createTime }</td></c:if>
                  <c:if test="${fyfz==5 }"><td>${u.createTime != null ?fn:substring(u.createTime,0,fn:indexOf(u.createTime," ")):""}</td></c:if>
                  <c:if test="${fyfz==5 }"><td><fmt:formatDate value="${u.translaterDate }" pattern="yyyy-MM-dd" /></td></c:if>
                  <c:if test="${fyfz==5 }"><td>${u.transName }</td></c:if>
					</tr>
				</c:forEach>
			</table>
			<br />
			<div>${pager }</div>
		</div>
	</div>
</body>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</html>
<script>
	/* 日历插件*/
	$('.form_date').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 4,
		forceParse : 0
	});
	$('.table-condensed tbody,.table-condensed tfoot').on('click', function() {
		$('.datetimepicker').hide();
	})

	function selectWeek(dateType) {
		$('#dateType').val(dateType);
		$('#form').submit();
	}


	function toDate(obj){
	     var date = new Date();
	     date.setTime(obj.time);
	     date.setHours(obj.hours);
	     date.setMinutes(obj.minutes);
	     date.setSeconds(obj.seconds);
	     return date.format("yyyy-MM-dd hh:mm:ss");
	}
	Date.prototype.format = function(format) {

		 var o = {
		  "M+" : this.getMonth() + 1,
		  "d+" : this.getDate(),
		  "h+" : this.getHours(),
		  "m+" : this.getMinutes(),
		  "s+" : this.getSeconds(),
		  "q+" : Math.floor((this.getMonth() + 3) / 3),
		  "S" : this.getMilliseconds()
		 }
		 if (/(y+)/.test(format)) {
		  format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
		      - RegExp.$1.length));
		 }
		 for (var k in o) {
		  if (new RegExp("(" + k + ")").test(format)) {
		   format = format.replace(RegExp.$1, RegExp.$1.length == 1
		       ? o[k]
		       : ("00" + o[k]).substr(("" + o[k]).length));
		  }
		 }
		 return format;
		}
</script>
