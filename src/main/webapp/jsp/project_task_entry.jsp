<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>验货预约，任务录入</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/mobiscroll_date.css" />
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/test_report.css">
<style type="text/css">
body {
	font: 14px/28px "微软雅黑";
	background-color: #FAFAFA;
}

body>h2 {
	text-align: center;
	font-size: 24px;
	color: #222;
	font-weight: 100;
}

.contact *:focus {
	outline: none;
}

.contact {
	width: 1200px;
	/* height: 800px; */
	background: #fff;
	margin: 10px auto 40px;
	padding: 10px;
	-webkit-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-moz-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-ms-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-o-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
}

.contact ul {
	/* width: 650px;
            margin: 0 auto; */
	
}

.contact ul li {
	/* border-bottom: 1px solid #dfdfdf; */
	list-style: none;
	padding: 12px;
	padding-top: 0;
}

.contact ul li label {
	width: 155px;
	display: inline-block;
	float: left;
	text-align: right;
	margin-right: 20px;
	color: #555555;
	line-height: 34px;
}

.contact ul li input[type=text], .contact ul li input[type=password] {
	width: 402px;
	height: 30px;
	border: 1px solid #ddd;
	padding: 3px 8px;
	padding-top: 0;
}

.contact ul li input:focus {
	/* border-color: #c00; */
	
}

.contact ul li input[type=text] {
	transition: padding .25s;
	-o-transition: padding .25s;
	-moz-transition: padding .25s;
	-webkit-transition: padding .25s;
}

.contact ul li input[type=password] {
	transition: padding .25s;
	-o-transition: padding .25s;
	-moz-transition: padding .25s;
	-webkit-transition: padding .25s;
}

:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
	color: #C2C2C2;
	opacity: 1;
}

::-moz-placeholder { /* Mozilla Firefox 19+ */
	color: #C2C2C2;
	opacity: 1;
}

input:-ms-input-placeholder, textarea:-ms-input-placeholder {
	color: #C2C2C2;
	opacity: 1;
}

input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
	color: #C2C2C2;
	opacity: 1;
}

input::-o-input-placeholder, textarea::-o-input-placeholder {
	color: #C2C2C2;
	opacity: 1;
}

.task-receiver {
	width: 420px;
	height: 30px;
	border: 1px solid #ddd;
	padding: 3px 8px;
}

.task-tell {
	width: 622px;
	height: 107px;
	border: 1px solid #ddd;
	padding: 3px 8px;
	resize: none;
}

.date-time {
	background: url(../img/data-calendar.png) right center no-repeat;
	background-position-x: 98%;
}

.btn {
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

.btn input[type=submit]:hover {
	background-color: #4362C5;
}

.tips {
	color: rgba(0, 0, 0, 0.5);
	padding-left: 10px;
}

.tips_true, .tips_false {
	padding-left: 10px;
}

.tips_true {
	color: green;
}

.tips_false {
	color: red;
}

#finishTime {
	cursor: pointer;
}

.updown-list {
	display: none;
	width: 432px;
	/* height: 190px; */
	overflow: hidden;
	/* -webkit-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-moz-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-ms-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-o-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1); */
	box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
}

.updown-list>div {
	font-size: 20px;
	width: 432px;
	height: 40px;
	line-height: 40px;
	background-color: #FFF;
	margin-top: 150px;
	text-align: right;
	padding: 3px 12px;
	border-top: 1px solid #ECECEC;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-o-box-sizing: border-box;
	-ms-box-sizing: border-box;
	cursor: pointer;
	position: relative;
	z-index: 30;
}

.box-executive ul {
	width: 220px;
	/* height: 150px; */
	position: absolute;
	top: 34px;
	left: 0;
	z-index: 10;
	padding: 0;
	background-color: #FFF;
	border:1px solid #ececec;
	box-shadow:0 3px 3px rgba(0,0,0,.2);
	border-right: 1px solid #ECECEC;
	box-sizing: border-box;
	
}

.box-executive .list>li, .box-executive .list>li ol li {
	/* width: 300px;
                    height: 30px;
                    line-height: 30px; */
	font-size: 13px;
	color: #666;
	padding: 1px 12px;
	cursor: pointer;
	/* position: relative; */
}

.box-executive .list>li:before {
	content: "";
	display: inline-block;
	width: 10px;
	height: 15px;
	background: url(../img/arrow-right.png) center no-repeat;
	background-size: cover;
	position: relative;
	left: 186px;
	top: 3px;
}

.box-executive .list>li:hover, .box-executive .list>li ol li:hover {
	color: #FFF;
	background-color: #027CFF;
}

.list>li.active ol {
	display: block;
}

.list>li:hover ol {
	display: block;
}

.list>li ol {
	display: none;
	width: 216px;
	height: 150px;
	position: absolute;
	top: 0;
	left: 216px;
	z-index: 20;
	padding: 0;
	background-color: #FFF;
	box-shadow:0 3px 3px rgba(0,0,0,.2);
	overflow-y: scroll;
	overflow-x: hidden;
	border:1px solid #ccc;
}

.select_blank {
	background-color: #027CFF;
	padding: 7px 12px;
	text-decoration: none;
	color: #fff
}

.select_blank:hover, .select_blank:hover {
	text-decoration: none;
	background-color: #4362C5;
	color: #fff;
}

.f16 {
	font-size: 16px;
}
.ml20{margin-left:20px;}
.position_relative ul {
	width: 100%;
	box-shadow: -2px 2px 6px rgba(0, 0, 0, .5), 2px -2px 6px
		rgba(0, 0, 0, .5);
	max-height: 300px;
	overflow-y: scroll;
	padding: 5px;
	width: 400px;
	margin-left: 133px;
}

.position_relative ul li {
	float: unset;
	width: 100%;
	height: 25px;
	line-height: 25px;
	text-align: left;
}
.red{color:red;}
.w622{width:622px;}
.w1000{width:1000px;position: relative;left: 90px;}
.stages td{padding-right:25px;}
.stages td input{width:16px;height:16px;position:relative;top:2px;}
</style>
</head>
<body>
	<div class="task_entry">
		<a class="select_blank" target="_blank"
			href="/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}">返回功能选择页</a>

		<h2 style="text-align: center;">
			验货预约 <small>任务录入</small>
		</h2>
		<input type="hidden" name="userName" id="userName" value="${userName}">
		<input type="hidden" name="roleNo" id="roleNo" value="${roleNo}">
		<input type="hidden" name="userId" id="userId" value="${userId}">
		<div class="contact">
			<div>
				<ul>
					<li><label>任务发起人</label> <input type="text" name="initiator"
						id="initiator" value="${userName}" readonly="readonly" /></li>
					
					<li><label>相关项目号</label> <span class="add-on">SHS</span> <input
						type="text" name="projectNo" id="projectNo" placeholder="请输入项目号"
						value="${project.projectNo}" style="width: 370px;" onblur="selectByProjectNo(this)"/> <span
						class="tips" id="projectNoHtml"></span></li>
						<li class="row"><label>检验任务编号</label><span id="check-number"></span>
						</li>
						<li class="row"><label>任务接受人</label>
						<div class="col-sm-3 box-executive pl0">
							<input type="text" class="form-control" name="accepter"
								id="accepter" placeholder="请选择公司职员"><span
								id="accepterHtml"></span>
							<div class="updown-list" style="">
								<ul class="list">
									<li><span class="add_span">质检部</span>
										<ol id="user">

										</ol></li>
								</ul>
								<!-- <div>
									<span class="close-ck" onclick="foc()">X</span>
								</div> -->
							</div>


						</div></li>
						
					<li><label>任务简述</label> <textarea class="task-tell"
							name="description" id="description" placeholder="请输入任务简述" /></textarea> <span
						class="tips" id="descriptionHtml"></span></li>

					<li><label>检验类别</label> <span class="f16"> <input
							type="radio" name="testType" checked style="margin-left: 0;"
							value="样品" />样品
					</span> <span class="f16"><input type="radio" name="testType"
							value="过程" style="margin-left: 20px;" />过程</span> <span class="f16"><input
							type="radio" name="testType" value="出货"
							style="margin-left: 20px;" />出货</span>
						<span class="f16"><input
							type="radio" name="testType" value="帮忙看"
							style="margin-left: 20px;" />帮忙看</span>
						<span class="f16"><input
								type="radio" name="testType" value="验厂"
								style="margin-left: 20px;" />验厂</span>

					</li>
							
					<!--  -->		
					<li>
					<label>检验范围</label>
						<p class="f16 red"><b>请选择本次检验，如果质检OK 可以归结为结束的合同生产阶段:(影响到项目完成情况,请认真选择)</b></p>
						<table class="stages f16">
							<tbody id="factory_constract">
					
							</tbody>
						</table>
				    </li>		

		<!-- 			<li class="row"><label></label> <input type="checkbox"
						name="isAll"><span id="stage_content">本次检验后<span
							id="stage">样品</span>阶段的货品就全部检完
					</span></li> -->

					<li class="row"><label>当前生产状态</label> <input type="text"
						class="form-control col-xs-4 w200" id="produceStatus"
						value="${project.status}"> <span class="tips"
						id="produceStatusHtml"></span> <span>至少2字,必填</span></li>

					<li class="address clearfix">
					<label>检验地址</label> 
					<div class="w622 pull-left address_div">
						<span class="f16"><input type="radio" name="place" value="公司" />公司</span><br/>							
						<span class="f16 "> <input type="radio" name="place"   value="仓库" />仓库	</span><br/> 															
						<span class="f16 "> <input type="radio" name="place" checked  value="工厂" />工厂	</span><br/>
						<span class="f16 "> <input type="radio" name="place" checked  value="新工厂" />新工厂	</span><br/>
					</div>
						
						
						<!-- <span class="f16"><input type="radio" name="place" value="工厂" style="margin-left: 20px;" />工厂 </span>							
						<div class="form-group position_relative" style="display: none;">
							<input class="form-control" style="width: 300px;" name="factory"
								placeholder="请输入工厂名">
							<ul>

							</ul>
						</div> -->
						
						</li>
				<!-- 		<li class="clearfix">
							<label>检验类别</label> 
							<div class="w622 pull-left">
								<span class="f16"><input type="radio" name="place" value="样品" />样品</span>							
								<span class="f16  ml20"> <input type="radio" name="place" checked  value="过程" />过程	</span>	
								<span class="f16  ml20"> <input type="radio" name="place"  value="出货" />出货</span>														
							</div>
						</li> -->
				


					<!-- 				<li class="row"><label>工厂</label> <input type="text"
						class="form-control col-xs-4 w200" id="inspectionAddress">
						<span class="tips" id="inspectionAddressHtml"></span><span>必填</span></li> -->

					<%-- 	<li class="row"><label>预计交期</label> <input type="text"
						name="expectedDelivery" id="expectedDelivery"
						value="<fmt:formatDate value="${project.deliveryDate}" pattern="yyyy-MM-dd"/>"
						class="input-text form-control date-time col-xs-4 w200"
						placeholder="请输入预计交期" /> <span class="tips"
						id="expectedDeliveryHtml"></span><span>必填</span></li> --%>
					<li class="row">
						<label>预计交期</label> <span class="f16" id="delivery_date"></span>

					</li>


					<li class="row"><label>船期</label> <input type="text"
						name="shippingDate" id="shippingDate" value=""
						class="input-text form-control date-time col-xs-4 w200"
						placeholder="请输入船期" /> <span class="tips" id="shippingDateHtml"></span>
						<!-- <span>必填</span> --></li>

					<li><label>要求任务完成时间</label> <input type="text"
						name="finishTime" id="finishTime" value=""
						class="input-text form-control date-time w200"
						placeholder="请输入完成时间" /><span >如果检验时间超过1天，请注意再给检验发布任务</span> <span class="tips" id="finishTimeHtml"></span></li>

					<li style="margin-top: -16px;"><label>如果紧急,请给出理由</label> <textarea
							class="task-tell" name="urgentReason" id="urgentReason"
							placeholder="请输入紧急理由" /></textarea> <span class="tips"
						id="urgentReasonHtml"></span></li>
				</ul>

			</div>
		</div>
		<b class="btn"><input type="submit" class="subm-but"
			onclick="addProjectTask()" value="提交" /><span class="tips"
			id="subHtml"></span>
	</div>
</body>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script src="../js/jquery-1.10.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../js/mobiscroll_date.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../layer/2.1/layer.js" type="text/javascript"
charset="utf-8"></script>
<script type="text/javascript">
$('.add_span').click(function(){	
	$('#accepter').val($(this).text());
	$('.updown-list').toggle();
})


    $(function () {
        var currYear = (new Date()).getFullYear();
        var opt = {};
        opt.date = {preset: 'date'};
        opt.datetime = {preset: 'datetime'};
        opt.time = {preset: 'time'};
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
        
        
        
        
            //获取质检人员
      		$.ajax({
				type : 'post',
				url : '${ctx}/syncUser/queryByJob',
				data : {
					job : "质检"
				},
				success : function(json) {
// 					var json = eval("(" + data + ")");
					var users = json.data;
					$('#user').empty();
					for(var j=0;j<users.length;j++){
						$('#user').append('	<li onclick="selectUser(this)">'+users[j].userName+'</li>');
					}
				}
		      })
		      
		      
		      //如果项目号初始不为空，调用selectByProjectNo查询合同
		      var projectNo = '${project.projectNo}';
		      if(projectNo){
		    	  var This = $('#projectNo'); 
		    	  selectByProjectNo(This);
		      }
		      
		      
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
    function goBack() {
        var roleNo = $("#roleNo").val();
        var userId = $("#userId").val();
        var userName = $("#userName").val();
        var purchaseNameId = "";
        window.location.href = "${ctx}/user/toIndex?userId=" + userId + "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId + "&userName=" + encodeURI(encodeURI(userName));
    }

    $(function () {
        var d = new Date();
        d.setDate(d.getDate() + 3);
        var day = d.getDay();
        if (day == 6 || day == 0) {
            d.setDate(d.getDate() + 3);
        }
        $("#finishTime").val(new Date(d.toString()).Format("yyyy-MM-dd"));
    });

    function selectAccepter(obj) {
        var accepter = $(obj).find("option:selected").val();
        $("#accepter").val(accepter);
    }

    //初始项目号是否存在
    var project_flag = true;
    
    //保存检验任务
    function addProjectTask() {
    	
    	if(!project_flag){
    		layer.msg('未查询到项目号，请先系统录入',{time:3000}); 
    		return false;
    	}
    	
    	
        var checkNumber = $("#check-number").html();
        var roleNo = $("#roleNo").val();
        var userId = $("#userId").val();
        var userName = $("#userName").val();
        var initiator = $("#initiator").val();
        var accepter = $("#accepter").val();
        var projectNo = $("#projectNo").val();
        var description = $("#description").val();
        var finishTime = $("#finishTime").val();
        var urgentReason = $("#urgentReason").val();
        var expectedDelivery = $("#delivery_date").text();
        var shippingDate = $("#shippingDate").val();
        var produceStatus=$("#produceStatus").val();
        var inspectionAddress=$("#inspectionAddress").val();
        var testType=$("input[name='testType']:checked").val();
        if (accepter == null || accepter == "" || accepter == undefined) {
            $("#accepterHtml").html('<font class="tips_false">请输入任务接受人</font>');
            return false;
        }else{
        	 $("#accepterHtml").empty();
        }
        if (projectNo == null || projectNo == "" || projectNo == undefined) {
            $("#projectNoHtml").html('<font class="tips_false">请输入相关项目号</font>');
            return false;
        }else{
        	 $("#projectNoHtml").empty();
        }
        if (description == null || description == "" || description == undefined) {
            $("#descriptionHtml").html('<font class="tips_false">请输入任务简述</font>');
            return false;
        }else{
        	 $("#descriptionHtml").empty();
        }
        
        //如果选择了本次检验是最后一次检验，则加载到任务描述
        if($('input[name="isAll"]').is(":checked")){
        	var stageContent = $('#stage_content').text();
        	description = description + '(' + stageContent + ')';
        }
        
        
        
/*         if (expectedDelivery == null || expectedDelivery == "" || expectedDelivery == undefined) {
            $("#expectedDeliveryHtml").html('<font class="tips_false">请输入预计交期</font>');
            return false;
        }else{
        	 $("#expectedDeliveryHtml").empty();
        } */
/*         if (inspectionAddress == null || inspectionAddress == "" || inspectionAddress == undefined) {
            $("#inspectionAddressHtml").html('<font class="tips_false">请输入验货地址</font>');
            return false;
        }else{
        	 $("#inspectionAddressHtml").empty();
        } */
        
        //获取公司和仓库
        var factoryFlag = false;
  	    var inspectionAddress = '';
     	  $('input[name="place"]').each(function(){
     		  if($(this).is(":checked")){
     			  var placeName = $(this).val();
     			  if(placeName != '工厂'){
     				 inspectionAddress += placeName+",";
     			  }else{
     				 factoryFlag = true;
     			  }
     		  }
     	  })		  
     	 //获取工厂
  	  if(factoryFlag){
  		  $('input[name="factory"]').each(function(){
  	   		   var factoryName = $(this).val();  
  	   		   if(factoryName){
  	   			 inspectionAddress += factoryName+",";
  	   		   }		   		   
  	   	   })
  	  }
   	  if(inspectionAddress.length>0){
   		  inspectionAddress = inspectionAddress.substring(0, inspectionAddress.length-1); 
   	  }
        
        
        
        if(produceStatus==null ||produceStatus=="" || produceStatus==undefined){
        	$("#produceStatusHtml").html('<font class="tips_false">请输入生产状态</font>');
            return false;
        }else{
        	 $("#produceStatusHtml").empty();
        }
        if (finishTime == null || finishTime == "" || finishTime == undefined) {
            $("#finishTimeHtml").html('<font class="tips_false">请输入任务完成时间</font>');
            return false;
        }else{
        	 $("#finishTimeHtml").empty();
        }
        
        if(!projectNo){
 		   layer.msg("项目号不能为空",{time:2000});  
 		   return false;
	      }else{
	 	   //自动加上SHS，防止自动录入shs
	 	   projectNo = projectNo.toUpperCase().replace("SHS","");
	 	   projectNo = "SHS"+projectNo;
	     }
        var projectNoStr = projectNo;
        var ds = new Date();
        ds.setDate(ds.getDate() + 2);
        var days = ds.getDay();
        if (days == 6 || days == 0) {
            ds.setDate(ds.getDate() + 2);
        }
        var finishTimeStr = new Date(ds.toString()).Format("yyyy-MM-dd");
        if(!(roleNo==100 || roleNo==99 || roleNo==98)){
        	if (new Date(Date.parse(finishTime)) < new Date(Date.parse(finishTimeStr))) {
//                $("#finishTimeHtml").html('<font class="tips_false">验货日期必须设置在3天之后，3天内的项目只有质检部长有权利建立安排任务，请当面说明</font>');
//                return false; 
                  if(!urgentReason){
                	  $("#finishTimeHtml").html('<font class="tips_false">3天内的验货任务，请输入理由</font>'); 
                	  return false;
                  }                 
            }	 
       } 
        
        
        var checkWeekDay = new Date(Date.parse(finishTime))
        if (checkWeekDay.getDay() == 6 || checkWeekDay.getDay() == 0) {
            $("#finishTimeHtml").html('<font class="tips_false">请选择工作日时间</font>');
            return false;
        }else{
        	 $("#finishTimeHtml").empty();
        }

        
        //工厂检验信息
        var factoryList=[];
        $('#factory_constract').find('tr').each(function(){
        	
        	var This = $(this);
        	var id = This.attr('filed');        	       	
        	var projectStage = '';
        	if(This.find('input[name="stage"]').is(":checked")){
        		projectStage = This.find('input[name="stage"]:checked').val();
        	}
        	if(!projectStage){
        		return;
        	}
        	var factory = {"id":id,"projectStage":projectStage};
        	factoryList.push(factory);
        })
        
        
        
        
        $(".subm-but").attr("disabled", true).css("background-color", "#999");
        $.ajax({
            type: "post",
            url: "${ctx}/inspection/addInspection",
            data: {
                projectNo: projectNoStr,
                accepter: accepter,
                initiator: initiator,
                description: description,
                finishTime: finishTime,
                urgentReason: urgentReason,
                expectedDelivery:expectedDelivery,
                shippingDate:shippingDate,
                produceStatus:produceStatus,
                inspectionAddress:inspectionAddress,
                testType:testType,
                checkNumber:checkNumber,
                factoryList:JSON.stringify(factoryList)
            },
            success: function (json) {
//                 var json = eval("(" + data + ")");
                if (json.ok) {
                    window.location.href = "${ctx}/projectTask/projectTaskList?userName=" + userName + "&roleNo=" + roleNo + "&userId=" + userId;
                } else {
                    $("#subHtml").html('<font class="tips_false">录入失败</font>');
                    $(".subm-but").css("background-color", "#027CFF").removeAttr('disabled');
                }
            },
            error: function () {
                $("#subHtml").html('<font class="tips_false">录入失败</font>');
                $(".subm-but").css("background-color", "#027CFF").removeAttr('disabled');
            }
        });
    }

    	

    $(".box-executive input,.box-executive em").click(
        function (event) {
            $(this).parents(".project-task-list").find(".updown-list")
                .slideUp(30).end().end().siblings(".updown-list")
                .slideDown();
            $(this).parent(".box-executive").find("em").addClass(
                "arrow-icon");
            
            //不显示错误信息
           	$("#accepterHtml").empty();
            
    		$(document).on("click", function(){
				$(".updown-list").hide();
			});
			
    		event.stopPropagation();
            
            return false;
        });
    
	$(".updown-list").on("click", function(e){
		e.stopPropagation();
	});
    
	
	 //当光标选中时，移除 错误信息
	 $(".contact input,textarea").focus(function(){
		 if($(this).attr('name') != 'isAll'){
			 $(this).next().empty();
		 }		 
	 })
	
	 //当选择样品和大货时
	 $('input[name="testType"]').change(function(){
		 var stage = $('input[name="testType"]:checked').val();
		 if(stage == '出货'){
			 stage = '大货';
			 $('#stage').parents('li').show();
		 }else if(stage == '过程'){
			 $('#stage').parents('li').hide();
			 return;
		 }else{
			 $('#stage').parents('li').show();
		 }		 
		 $('#stage').text(stage);
	 })
	 

//     $("#user li").click(function (event) {
//         var game1 = $(this).text()
//         console.log($(this).parents(".updown-list").siblings("input").length);
//         $(this).parents(".updown-list").siblings("input").val(game1);
//         foc();
//     });
	 
	function selectUser(obj){
	    var game1 = $(obj).text()
//         console.log($(obj).parents(".updown-list").siblings("input").length);
        $(obj).parents(".updown-list").siblings("input").val(game1);
        // foc();
        $('#accepter').val(game1);
        $('.updown-list').toggle();
	} 


    function addClick() {
        $(".box-executive input,.box-executive em").click(
            function (event) {
                $(this).parents(".project-task-list").find(".updown-list")
                    .slideUp(30).end().end().siblings(".updown-list")
                    .slideDown();
                $(this).parent(".box-executive").find("em").addClass(
                    "arrow-icon");
                return false;
            });
        $(".list ol li").click(
            function (event) {
                var game1 = $(this).text()
                console.log($(this).parents(".updown-list").siblings(
                    "input").length);
                $(this).parents(".updown-list").siblings("input")
                    .val(game1);
                foc();
            });

    }
    function foc() {
        $(".close-ck").parents(".updown-list").slideUp();
        $(".box-executive em").removeClass("arrow-icon");
    }
    
    function goBack(){
		var roleNo=$("#roleNo").val();
		var userId=$("#userId").val();
		var userName=$("#userName").val();
		window.location.href="${ctx}/user/toIndex?userId="+userId+"&roleNo="+roleNo+"&userName="+encodeURI(encodeURI(userName));	
    }
    
    
    
    
      var names = []; 
	  $(function(){
		    var factoryNameList = '${factoryNameList}';
		    factoryNameList = factoryNameList.substring(1, factoryNameList.length - 1);
		    names=factoryNameList.split(",");	
		    
		    $('input[name="place"]').change(function(){
		    	if($(this).val() == '工厂'){
		    	    if($(this).is(":checked")){	    		
		    			$('.position_relative').show();
		    			$('.add_detail .facotry_td ul').hide();
		    		}else{
		    			$('.position_relative').hide();
		    		}
		    	}
		    })
	  })
	  
	  
	     $('input[name="factory"]').keyup(function(){
	    	
	    	var key = $(this).val();
	    	$(this).next().empty();
	    	for(var i=0;i<names.length;i++){
	    		if(key){
	        		if(names[i].indexOf(key) != -1){
	        			$(this).next().append('<li>'+names[i]+'</li>');
	        		}  
	    		}  		 
	    	}
	    	$(this).next('ul').show();
	    	$(this).next().find('li').show();   	
	    	
	    	
	    	  //选中关键词  		 
	  		$('.position_relative li').click(function(e){
	  			var li_val = $(this).text();
	  			
	  			$('.position_relative ul li,.position_relative ul').hide();
	  			$(this).parent().parent().find('input').val(li_val).css({'padding-left':'0'});
	  			
	  			 $(document).on("click", function(){
	  			     $('.position_relative li,.position_relative ul').hide();
				 });								
				 e.stopPropagation(); 
	  		});
	    })
	    
	    
	   //根据项目号查询
	   function selectByProjectNo(obj){
		   var projectNo = $(obj).val();
		   if(!projectNo){
			 return false;
		   }else{
			   //自动加上SHS，防止自动录入shs
			   projectNo = projectNo.toUpperCase().replace("SHS","");
			   projectNo = "SHS"+projectNo;
		   }	   
		   
			  $.ajax({
				    type:"post",                   
				    url:"${ctx}/project/queryProject",           
				    data:{
				    	  projectNo:projectNo
				    	 },              
				    success:function(json){  
// 				    	var json = eval("(" + data + ")");
						if (json.ok) {
							var project = json.data.project;
							var factoryList = json.data.factoryList;
							var reserList = json.data.reserList;
							if(!project){
								layer.msg('未查询到项目号',{time:2000});  
								project_flag = false;
							}else{
								project_flag = true;
								if(project.sampleScheduledDate){
								    $('#delivery_date').text(formatDateTime(project.sampleScheduledDate));
								}
								if(project.deliveryDate){
									$('#delivery_date').text(formatDateTime(project.deliveryDate));
                                }
                                if(project.masterQualityInspection){
									$('#accepter').val(project.masterQualityInspection);
								}else{
									$("#accepter").val("质检部");
								}
								//任务编号
								$("#check-number").html(project.projectNo+"-JY"+json.data.projectCheckNumber);
								
							}
							//最近录入要求质检检验报表
							if(reserList){
								var html="";
								for(var i=0;i<reserList.length;i++){
									var inspectionReservation="<div style='color:red'> 日期:"+formatDateTime(reserList[i].finishTime)+",质检:"+reserList[i].accepter+",去"+reserList[i].inspectionAddress+"检验</div>";
									
									
									
									html+=	inspectionReservation;	
								}
								document.getElementById("projectNoHtml").innerHTML =html;
								
							}
							//查询到的合同工厂数据
							if(factoryList){
								$('.address_div').find('.f16:gt(3)').remove();
								$('.address_div').find('br:gt(3)').remove();
								$('#factory_constract').empty();
								for(var i=0;i<factoryList.length;i++){
									
									var factoryNames = $('.address_div').text();
									if(factoryNames.indexOf(factoryList[i].factoryName) != -1){
										
									}else{
										$('.address_div').append('<span class="f16"> <input type="radio" name="place" value="'+factoryList[i].factoryName+'">'+factoryList[i].factoryName+'</span><br>');
									}							
									
									//根据合同性质判断显示
									var td = '';
									if(factoryList[i].orderNature == 2){
										td = '<td>返修/补货阶段 <input type="checkbox" name="stage" value="2"/></td>';
									}else if(factoryList[i].orderNature == 1 && factoryList[i].deliveryDate != null){
										td = '<td>大货 <input type="checkbox" name="stage" value="1"/></td>';
									}else if(factoryList[i].orderNature == 1 && factoryList[i].sampleDate != null){
										td = '<td>样品 <input type="checkbox" name="stage" value="0"/></td>';
									}
									
									var tr = '<tr filed='+factoryList[i].id+'>'+
												'<td>'+(factoryList[i].contractNo?factoryList[i].contractNo:'')+'</td>'+
												'<td>'+factoryList[i].factoryName+'</td>'+td+ 
											'</tr>';
									$('#factory_constract').append(tr)
								}
								
								
								  $('input[name="stage"]').change(function(){
									  $(this).parent().siblings().find('input[name="stage"]').attr('checked',false);									 
								  })
							}
							
						}else{
							layer.msg(data.message,{time:2000});  
						}
				    }
			})
	   }
	
	  

	  
	  
	  
	  function formatDateTime(inputTime) {  
	  		    var date = new Date(inputTime);
	  		    var y = date.getFullYear();  
	  		    var m = date.getMonth() + 1;  
	  		    m = m < 10 ? ('0' + m) : m;  
	  		    var d = date.getDate();  
	  		    d = d < 10 ? ('0' + d) : d;  
	  		    var h = date.getHours();
	  		    h = h < 10 ? ('0' + h) : h;
	  		    var minute = date.getMinutes();
	  		    var second = date.getSeconds();
	  		    minute = minute < 10 ? ('0' + minute) : minute;  
	  		    second = second < 10 ? ('0' + second) : second; 
	  		    return y + '-' + m + '-' + d;  
	  		};

	    
</script>
</html>

