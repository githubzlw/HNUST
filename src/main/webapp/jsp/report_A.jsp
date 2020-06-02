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
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>任务系统,检验报告,pc</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/test_report.css">
</head>
<body>
	<div class="test_report">
		<div class="row row1">
			<a href="###" class="a_btn"><button>返回检验计划列表</button></a> 
			<a href="###" class="a_btn"><button>去到项目详情手机版</button></a>
			<div class="pull-right">
				<div class="pull-left clearfix div1">
					<span class="s1">可选历史版本</span> <select class="form-control select1">
						<option>20180517 12:30 xxx 修改版本</option>
						<option>20180517 12:30 xxx 修改版本</option>
						<option>20180517 12:30 xxx 修改版本3</option>
					</select>
				</div>
				<a href="###"><button>在当前页打开</button></a> 
				<a href="###" class="last_a"><button>下载EXCEL版本</button></a>
			</div>
		</div>
		<div class="row">
			<h3 class="h1">检验计划</h3>
			<div class="div_line">
				<div class="form-inline form-inline1">
					<div class="form-group">
						<label>项目号:</label> <input type="text" class="form-control" id="projectNo" placeholder="">
					</div>
					<div class="form-group">
						<label>项目名称:</label> <input type="text" class="form-control" id="projectName" placeholder="">
					</div>
				</div>
				<div class="form-inline form-inline2">
					<div class="form-group">
						<label>零件号:</label> <input type="text" class="form-control" id="partsNo" placeholder="">
					</div>
					<div class="form-group">
						<label class="b_b">版本:</label> <input type="text" class="form-control" id="version" placeholder="">	
					</div>
					<div class="form-group">
						<label>名称:</label> <input type="text" class="form-control" id="fileName" placeholder="">
					</div>
				</div>
			</div>
			<p class="p1 text-left">重要（关键)尺寸要求:</p>
			<table class="table table-bordered table1">
				<thead>
					<tr>
						<th class="th1">气泡图编号</th>
						<th class="th2">项目</th>
						<th class="th3">标准</th>
						<th class="th2">抽样数量</th>
						<th class="th5">量检具(精度)</th>
						<th class="th6">过程检验</th>
						<th class="th6">大货检验</th>
						<th class="th8"></th>
					</tr>
				</thead>
					<tbody id="sizeDemandSelect">
					<tr>
						<td><input type="text" class="form-control" name="numberNo" value="1"></td>
						<td><input type="text" class="form-control" name="project"></td>
						<td><input type="text" class="form-control" name="standard"></td>
						<td><input type="text" class="form-control" name="samlpingNum"></td>
						<td><input type="text" class="form-control" name="accuracy"></td>
						<td><input type="checkbox" checked="checked" name="processTest" class="form-control"></td>
						<td><input type="checkbox" checked="checked" name="bigGoodsTest" class="form-control"></td>
						<td><button>删除</button></td>
					</tr>
				</tbody>
			</table >
			<button class="form-control addtr addtr1">新增</button>
		</div>
		<div class="material">
			<p class="p1 text-left">材料要求:</p>
			<table class="table table-bordered table2">
				<tbody id="materialDemandSelect">
					<tr>
						<td class="th1"><input type="text" class="form-control" value="1"></td>
						<td ><input type="text" class="form-control" name="materialDemandExplain"></td>
						<td class="th8"><button>删除</button></td>
					</tr>
				</tbody>
			</table>
			<button class="form-control addtr addtr2">新增</button>
		</div>
		<div class="material">
			<p class="p1 text-left">包装要求:</p>
			<table class="table table-bordered table2 table3">
				<tbody id="packageDemandSelect">
					<tr>
						<td class="th1"><input type="text" class="form-control" value="1"></td>
						<td><input type="text" class="form-control" name="packageDemandExplain"></td>
						<td class="th8"><button>删除</button></td>
					</tr>
				</tbody>
			</table>
			<button class="form-control addtr addtr3">新增</button>
		</div>
		<div class="material">
			<p class="p1 text-left">其他要求(质检有责任监管采购新增加的要求的实施):</p>
			<table class="table table-bordered table2 table4">
				<tbody id="otherDemandSelect">
					<tr>
						<td class="th1"><input type="text" class="form-control" value="1"></td>
						<td ><input type="text" class="form-control" name="otherDemandExplain"></td>
						<td class="th8"><button>删除</button></td>
					</tr>
				</tbody>
			</table>
			<button class="form-control addtr addtr4">新增</button>
		</div>
		<div class="material">
			<p class="p1 text-left">总部质检要求:</p>
			<table class="table table-bordered table2 table5">
				<tbody >
					<tr>
						<td class="text-left w290">说明：（需要送的工艺点和送的时间）</td>
						<td><input type="text" class="form-control" id="describes"></td>
						<td class="w245">
							<input type="radio" name="testType" value="1">送一箱
							<input type="radio" name="testType" value="2">送几个
							<input type="radio" name="testType" value="3">没法送
						</td>
					</tr>
					
				</tbody>
			</table>
			<table class="table table-bordered table2">
				<tbody>
					<tr>
						<td>编制</td>
						<td><input type="text" class="form-control" id="compactor"></td>
						<td>审核</td>
						<td><input type="text" class="form-control" id="auditor"></td>
						<td>编制日期</td>
						<td><input type="text" class="form-control" id="compactorDate"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="tips">
			<p>备注:</p>
			<p>(1) 关健特性用★表示，重要特性用▲表示。</p>
			<p>(2) 常规量具不特别注明。</p>
			<p>(3) 外观和包装抽样比例至少20%。</p>
			<p>(4) 样品至少2-3件全尺寸检验。(如模具成型件，每穴2-3件)</p>
			<p>(5) 复杂的专用检具要根据检具指导书进行操作。</p>
		</div>
		<div class="pull-right last_div" ><span>不管有没有改动请都</span><button class="t_j" onclick="addInspectionPlan()">提交，并保存</button>
	</div>



</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script>
var len = 1;

	 $('.addtr1').click(function(){
		
		var tr_add = $(this).siblings('table').find('tbody tr:first-child').clone(true);
		$(this).siblings('table').append(tr_add);
		len ++ ;
		$('.addtr1').siblings('table').find('tr').each(function(){
			var index_key  = $(this).index();
			$(this).find('td:first-child').find('input').val(index_key + 1 );
		})
		
	})
	$('.addtr2').click(function(){
		var tr_add = $(this).siblings('table').find('tbody tr:first-child').clone(true);
		$(this).siblings('table').append(tr_add);
		len ++ ;
		$('.addtr2').siblings('table').find('tr').each(function(){
			var index_key  = $(this).index();
			console.log(index_key)
			$(this).find('td:first-child').find('input').val(index_key + 1 );
		})
	}) 
	$('.addtr3').click(function(){
		var tr_add = $(this).siblings('table').find('tbody tr:first-child').clone(true);
		$(this).siblings('table').append(tr_add);
		len ++ ;
		$('.addtr3').siblings('table').find('tr').each(function(){
			var index_key  = $(this).index();
			console.log(index_key)
			$(this).find('td:first-child').find('input').val(index_key + 1 );
		})
	}) 
	$('.addtr4').click(function(){
		var tr_add = $(this).siblings('table').find('tbody tr:first-child').clone(true);
		$(this).siblings('table').append(tr_add);
		len ++ ;
		$('.addtr4').siblings('table').find('tr').each(function(){
			var index_key  = $(this).index();
			console.log(index_key)
			$(this).find('td:first-child').find('input').val(index_key + 1 );
		})
	}) 

	$('table button').click(function(){
		$(this).parent().parent().remove();
		$('.table1 tr').each(function(){
			 var index_key  = $(this).index();
			 $(this).find('td:first-child').find('input').val(index_key + 1 );
		})
		$('.table2 tr').each(function(){
			 var index_key  = $(this).index();
			 $(this).find('td:first-child').find('input').val(index_key + 1 );
		})
		$('.table3 tr').each(function(){
			 var index_key  = $(this).index();
			 $(this).find('td:first-child').find('input').val(index_key + 1 );
		})
		$('.table4 tr').each(function(){
			 var index_key  = $(this).index();
			 $(this).find('td:first-child').find('input').val(index_key + 1 );
		})
	})
	
	//添加检验计划
	function addInspectionPlan(){
	  var projectNo=$("#projectNo").val();
	  var projectName=$("#projectName").val();
	  var partsNo=$("#partsNo").val();
	  var version=$("#version").val();
	  var fileName=$("#fileName").val();
	  var describes=$("#describes").val();
	  var compactor=$("#compactor").val();
	  var auditor=$("#auditor").val();
	  var compactorDate=$("#compactorDate").val();
	  var testType=$("input[name='testType']:checked").val(); 
	  var sizeDemandS="";
	  $("#sizeDemandSelect").find('tr').each(function () {
		  var project= $(this).find("input[name='project']").val();
		  var standard= $(this).find("input[name='standard']").val();
		  var samlpingNum= $(this).find("input[name='samlpingNum']").val();
		  var accuracy=$(this).find("input[name='accuracy']").val();
		  var processTest=0;
		  if($('input[name="processTest"]:checked')){
			  processTest=1;
		  }
		  
		  var bigGoodsTest=0;
		  if($('input[name="bigGoodsTest"]:checked')){
			  bigGoodsTest=1;
		  }
		  infoStr=project+'&'+standard+'&'+samlpingNum+'&'+accuracy+'&'+processTest+'&'+bigGoodsTest+',';
		  sizeDemandS+=infoStr;
	  })
	  
	  var materialDemandS=""
	  $("#materialDemandSelect").find('tr').each(function(){
		  var materialDemandExplain= $(this).find("input[name='materialDemandExplain']").val();
		  materialDemandS+=materialDemandExplain+',';
	  })
	  
	  var packageDemandS=""
	  $("#packageDemandSelect").find('tr').each(function(){
		  var packageDemandExplain= $(this).find("input[name='packageDemandExplain']").val();
		  packageDemandS+=packageDemandExplain+',';
	  })
	  
	  var otherDemandS=""
	  $("#otherDemandSelect").find('tr').each(function(){
		  var otherDemandExplain= $(this).find("input[name='otherDemandExplain']").val();
		  otherDemandS+=otherDemandExplain+',';
	  })
	  $.ajax({
		type : "post",
		data:{
			   projectNo:projectNo,
			   projectName:projectName,
			   partsNo:partsNo,
			   version:version,
			   fileName:fileName,
			   describes:describes,
			   compactor:compactor,
			   auditor:auditor,
			   compactorDate:compactorDate,
			   testType:compactorDate,
			   sizeDemandS:sizeDemandS,
			   materialDemandS:materialDemandS,
			   otherDemandS:otherDemandS,
			   packageDemandS:packageDemandS
	    },  
		url : "${ctx}/plan/addInspectionPlan",
		dataType : "json",
		success : function(result) {
			var json = eval("(" + result + ")");
		},	
	  });	
	}
</script>







