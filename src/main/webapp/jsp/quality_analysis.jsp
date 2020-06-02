<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
  String projectNo=request.getParameter("projectNo");
  String userId=request.getParameter("userId");
  String userName=request.getParameter("userName");
%>
<html>
<head>
  <title>项目质量分析录入</title>
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="keywords" content="HTML,CSS,XML,JavaScript">
 <link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
  <script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
  <script src="../js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript" src="../js/jquery-form.js"></script>
  <style type="text/css">
  .usermatd1{height:28px;padding-right:15px;}
  .usermatd2{height:28px;weith:200px;}
 
  .userselediv_nor{display:inline-block;width:129px;height:24px;border:1px solid #cbd1df;position:relative;}
		body{
			font:14px/28px "微软雅黑";
			background-color: #FAFAFA;
		}
		body>h2{
			text-align: center;
			font-size: 24px;
			color: #222;
    		font-weight: 100;
		}
		.contact *:focus{outline :none;}
		.contact{
			width: 1200px;
		    /* height: 800px; */
		    background: #fff;
		    margin: 10px auto 40px;
		    padding: 10px;
		    -webkit-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    -moz-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    -ms-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    -o-box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		    box-shadow: 1px 1px 10px rgba(0,0,0,0.1);
		}
		.contact ul {
			/* width: 650px;
			margin: 0 auto; */
		}
		.contact ul li{
			/* border-bottom: 1px solid #dfdfdf; */
			list-style: none;
			padding: 12px;
		}
		.contact ul li label {
			width: 135px;
		    display: inline-block;
		    float: left;
		    text-align: right;
		    margin-right: 20px;
		    color: #555555;
		    line-height: 34px;
		}
		.contact ul li input[type=text],.contact ul li input[type=password]{
			width: 402px;
    		height: 26px;
			border :1px solid #ddd;
			padding: 3px 8px;
		}
		.contact ul li input:focus{
			/* border-color: #c00; */
			
		}
		.contact ul li input[type=text]{
			transition: padding .25s;
			-o-transition: padding  .25s;
			-moz-transition: padding  .25s;
			-webkit-transition: padding  .25s;
		}
		.contact ul li input[type=password]{
			transition: padding  .25s;
			-o-transition: padding  .25s;
			-moz-transition: padding  .25s;
			-webkit-transition: padding  .25s;
		}
		:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		    color: #C2C2C2; opacity:1; 
		}
		
		::-moz-placeholder { /* Mozilla Firefox 19+ */
		    color: #C2C2C2;opacity:1;
		}
		
		input:-ms-input-placeholder,textarea:-ms-input-placeholder{
		    color: #C2C2C2;opacity:1;
		}
		
		input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{
		    color: #C2C2C2;opacity:1;
		}
		input::-o-input-placeholder,textarea::-o-input-placeholder{
		    color: #C2C2C2;opacity:1;
		}
		.task-receiver{
			width: 420px;
		    height: 30px;
		    border: 1px solid #ddd;
		    padding: 3px 8px;
		}
		.task-tell{
			width: 622px;
    		height: 107px;
		    border: 1px solid #ddd;
		    padding: 3px 8px;
		    resize: none;
		}
		.date-time{
			background: url(../img/data-calendar.png) right center no-repeat;
    		background-position-x: 98%;
    	}
		.btn{
			position: relative;
		    width: 100%;
		    display: inline-block;
		    text-align: center;
		    margin-bottom: 40px;
		}
		.btn input[type=submit] {
		    padding: 7px 46px;
		    background-color: #4B67E7;
		    border: none;
		    font-size: 14px;
		    color: #fff;
		}
		.btn input[type=submit]:hover{background-color: #4362C5;}
		.tips{
			color: rgba(0, 0, 0, 0.5);
			padding-left: 10px;
		}
		.tips_true,.tips_false{
			padding-left: 10px;
		}
		.tips_true{
			color: green;
		}
		.tips_false{
			color: red;
		}
		#finishTime{cursor: pointer;}
		.userselediv_nor.input{
	width:240px;
}
.custdin{height: 25px;padding: 3px;border: 1px solid #CBD1DF;}
.userselein{width:128px;border:0 none;padding:3px;margin:1px 0 0 0px;background:transparent;}
.usectable_re{margin-left:50px;margin-top:8px;}
  </style>
</head>
<body>
    <h2 style="text-align:center;">项目质量分析录入</h2>
    <input type="hidden" name="userName" id="userName" value="<%=userName%>">
    <input type="hidden" name="projectNo" id="projectNo" value="<%=projectNo%>">
    <input type="hidden" name="userId" id="userId" value="<%=userId%>">
	<div class="contact">
		<div>
	
		<table class="usectable_re">      
			      <tr>
			        <td class="usermatd1">质量分析录入人:</td>
			        <td>
                        <div class="userselediv_nor">
                        <input type="text"  size="30" value="<%=userName%>" class="userselein"/>
                        </div>
                        </td>   
			      </tr>
			      <tr>
			    	<td class="usermatd1">图纸数量:</td>
			        <td style="padding-right:100px;">
                        <div class="userselediv_nor">
                        <input type="text" id="quantityDrawings" name="quantityDrawings" size="30"  class="userselein"/>
                        </div>
                        </td>
			      <td class="usermatd2">是否需要做BOM表:</td>
			          <td> <div class="userselediv_nora">
				            <select name="bomTable" id="bomTable" class="userseleina">
								<option value="0"></option>
								<option value="1">是</option>
								<option value="2">否</option>
							</select> 
						</div></td>   
			       
			       </tr>
			      <tr>
			    	<td class="usermatd1">图纸尺寸最高精度要求:</td>
			         <td style="padding-right:100px;">
                        <div class="userselediv_nor">
                        <input type="text" id="maxPrecisionRequiremen" name="maxPrecisionRequiremen" size="30"  class="userselein"/>
                        </div>
                        </td> 
			       
			      <td class="usermatd2">对应的 精度等级:</td>
			         <td>
                        <div class="userselediv_nor">
                        <input type="text" id="precisionGrade" name="precisionGrade" size="30"  class="userselein"/>
                        </div>
                        </td> 
			      </tr>
			      <tr>
			    	<td class="usermatd1">表面处理有什么要求:</td>
			         <td style="padding-right:100px;">
                        <div class="userselediv_nor">
                        <input type="text" id="maxRoughnessRequirement" name="maxRoughnessRequirement" size="30"  class="userselein"/>
                        </div>
                        </td> 
			      <td class="usermatd2">零件表面粗糙度最高要求:</td>
			        <td>
                        <div class="userselediv_nor">
                        <input type="text" id="surfaceTreatment" name="surfaceTreatment" size="30"  class="userselein"/>
                        </div>
                        </td> 
			      </tr>
			      <tr>
			    	<td class="usermatd1">材料国外牌号:</td>
			         <td style="padding-right:100px;">
                        <div class="userselediv_nor">
                        <input type="text" id="materialsAbroad" name="materialsAbroad" size="30" class="userselein"/>
                        </div>
                        </td> 
			      <td class="usermatd2">对应国标:</td>
			         <td>
                        <div class="userselediv_nor">
                        <input type="text" id="correspondingNationalStandard" name="correspondingNationalStandard" size="30" class="userselein"/>
                        </div>
                        </td> 
			      </tr>
			      <tr>
			    	<td class="usermatd1">客户原图上, 还列出了哪些标准:</td>
			         <td style="padding-right:100px;">
                        <div class="userselediv_nor">
                        <input type="text" id="listStandards" name="listStandards" size="30"  class="userselein"/>
                        </div>
                        </td> 
			      <td class="usermatd2">是否有没查到的标准:</td>
			         <td>
                        <div class="userselediv_nor">
                        <input type="text" id="standardNotFound" name="standardNotFound" size="30"  class="userselein"/>
                        </div>
                        </td> 
			      </tr>
			      <tr>
			    	<td class="usermatd1">尺寸标注/公差标注是否有缺失:</td>
			        <td  style="padding-right:100px;">
                        <select name="annotation" id="annotation" class="userseleina">
								<option value="0"></option>
								<option value="1">是</option>
								<option value="2">否</option>
							</select> 
                        </td> 
			      <td class="usermatd2">是否有装配关系:</td>
			        <td>
                        <select name="assemblyRelationship" id="assemblyRelationship" class="userseleina">
								<option value="0"></option>
								<option value="1">是</option>
								<option value="2">否</option>
							</select> 
                        </td> 
			      </tr>
			      <tr>
			    	<td class="usermatd1">热处理的要求:</td>
			        <td style="padding-right:100px;">
                        <div class="userselediv_nor">
                        <input type="text" id="heatTreatmentRequirements" name="heatTreatmentRequirements" size="30"  class="userselein"/>
                        </div>
                        </td> 
			      <td class="usermatd2">客户要求的力学测试和强度要求:</td>
			         <td>
                        <div class="userselediv_nor">
                        <input type="text" id="customerRequirements" name="customerRequirements" size="30"  class="userselein"/>
                        </div>
                        </td> 
			      </tr>
			      <tr>
			    	<td  >有什么设计缺陷:</td>
			         <td colspan="2">
                     <textarea class="task-tell" name="designDefects" id="designDefects" placeholder="请输入有什么设计缺陷"/></textarea>
                       </td> 
			      </tr>
			      <tr><td >建议的生产流程，设备，以及工艺:</td>
			         <td colspan="2">
                        <textarea class="task-tell" name="recommendedProcess" id="recommendedProcess" placeholder="请输入建议流程"/></textarea>
                      </td> 
			      </tr>
			      <tr>
			    	<td  >建议和客户沟通的问题:</td>
			        <td colspan="2">
                   
                       <textarea class="task-tell" name="suggestCommunicationProblems" id="suggestCommunicationProblems" placeholder="请输入建议和客户沟通的问题"/></textarea>
                        
                        </td> 
			      </tr>
			     
			      
			      
			      
			      <tr>
			      <td><input type="submit" class="subm-but" onclick="addProjectTask()"value="提交"/><span class="tips" id="subHtml"></span></td>
			      
			      </tr>
			      </table>
			
				
			
			
		</div>
	</div>
	
</body>

<script type="text/javascript">
	$(function () {
		var currYear = (new Date()).getFullYear();	
		var opt={};
		opt.date = {preset : 'date'};
		opt.datetime = {preset : 'datetime'};
		opt.time = {preset : 'time'};
		opt.default = {
			theme: 'android-ics light', //皮肤样式
			display: 'modal', //显示方式 
			mode: 'scroller', //日期选择模式
			dateFormat: 'yyyy-mm-dd',
			lang: 'zh',
			showNow: true,
			nowText: "今天",
			startYear: currYear, //开始年份
			endYear: currYear + 50 //结束年份
		};
		$(".date-time").mobiscroll($.extend(opt['date'], opt['default']));
	})
	Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
</script>
<script type="text/javascript">
function goBack(){
	var projectNo=$("#projectNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var purchaseNameId="";
	window.location.href="${ctx}/jsp/project_list.jsp?userId="+userId+"&roleNo="+roleNo+"&purchaseNameId="+purchaseNameId+"&userName="+encodeURI(encodeURI(userName));	
}

$(function(){
	var d = new Date();
	d.setDate(d.getDate() + 2);
	$("#finishTime").val(new Date(d.toString()).Format("yyyy-MM-dd"));
});

function selectAccepter(obj){
	var accepter=$(obj).find("option:selected").val();
	$("#accepter").val(accepter);
}

function addProjectTask(){
	var projectNo=$("#projectNo").val();
	
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var quantityDrawings=$("#quantityDrawings").val();
	var bomTable=$("#bomTable").val();
	var maxPrecisionRequiremen=$("#maxPrecisionRequiremen").val();
	var precisionGrade=$("#precisionGrade").val();
	var maxRoughnessRequirement=$("#maxRoughnessRequirement").val();
	var surfaceTreatment=$("#surfaceTreatment").val();
	var materialsAbroad=$("#materialsAbroad").val();
	var correspondingNationalStandard=$("#correspondingNationalStandard").val();
	var listStandards=$("#listStandards").val();
	var standardNotFound=$("#standardNotFound").val();
	var annotation=$("#annotation").val();
	var assemblyRelationship=$("#assemblyRelationship").val();
	var heatTreatmentRequirements=$("#heatTreatmentRequirements").val();
	var customerRequirements=$("#customerRequirements").val();
	var designDefects=$("#designDefects").val();
	var recommendedProcess=$("#recommendedProcess").val();
	var suggestCommunicationProblems=$("#suggestCommunicationProblems").val();
	
	 $.ajax({
	     type:"post",                   
	     url:"${ctx}/qualityAnalysisTable/addQualityAnalysis",           
	     data:{
	    	 userName:userName,
	        	projectNo:projectNo,
	        	quantityDrawings:quantityDrawings,
	        	bomTable:bomTable,
	        	maxPrecisionRequiremen:maxPrecisionRequiremen,
	        	precisionGrade:precisionGrade,
	        	maxRoughnessRequirement:maxRoughnessRequirement,
	        	surfaceTreatment:surfaceTreatment,
	        	materialsAbroad:materialsAbroad,
	        	correspondingNationalStandard:correspondingNationalStandard,
	        	listStandards:listStandards,
	        	standardNotFound:standardNotFound,
	        	annotation:annotation,
	        	assemblyRelationship:assemblyRelationship,
	        	heatTreatmentRequirements:heatTreatmentRequirements,
	        	customerRequirements:customerRequirements,
	        	designDefects:designDefects,
	        	recommendedProcess:recommendedProcess,
	        	suggestCommunicationProblems:suggestCommunicationProblems
	     },              
	     success:function(json){  
// 	    	 var json = eval("(" + data +")");
			  if(json.ok){  	 
				  window.location.href="${ctx}/qualityAnalysisTable/listItems?userName="+userName+"&userId="+userId;
			  }else{
				  
				 $("#subHtml").html('<font class="tips_false">录入失败</font>');
				 $(".subm-but").css("background-color", "#027CFF").removeAttr('disabled');
			  }
			  
	     },
	     error : function() {
				$("#subHtml").html('<font class="tips_false">录入失败</font>');
				$(".subm-but").css("background-color", "#027CFF").removeAttr('disabled');
			}
	 });  
}
</script>
</html>

