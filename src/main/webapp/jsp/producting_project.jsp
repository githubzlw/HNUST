<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>在生产工厂及项目列表</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<style>
 .back{background-color: #027CFF;padding: 3px 6px;color: #fff;border-radius:4px;top:10px;position:relative;}
 .back:hover{color:#fff;position:relative;}
  h3{font-size:20px;margin-top:10px;font-weight:700;}
  label{cursor:pointer;margin-right:10px;}
  label input[type=radio]{margin-right:5px;}
  th,td{text-align:center;}
  table{font-size:15px;}
</style>	
<body>
	<div class="container">
	<div class="producting_project_details">
	
		<div class="row">
			<div class="col-xs-7"><h3 >${factoryName }</h3></div>
			<div class="col-xs-5">
				<a class="back" target="_blank" href="javascript:history.back(-1)">返回上一页</a>
				<a class="back" target="_blank" href="/user/toIndex">返回功能选择页</a>
			</div>			
		</div>
		<form id="form4" action="/project/productingProject" method="post">	
		<input type="hidden" name="factoryName" id="factoryName" value="${factoryName }">	
		<div class="row">
			<div class="col-xs-12">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>项目号</th>
							<th>项目名</th>
							<th>项目等级</th>
							<th>组员名字</th>
							<th>工厂名</th>
							<th>未完成检验任务</th>
							<th>刚签订合同</th>
							<th>最近出过检验报告</th>
							<th>延期</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="obj" items="${projectList}" varStatus="i">
						<tr>
							<td>${obj.projectNo}</td>
							<td>${obj.projectName}</td>
							<td>${obj.plantAnalysis == 0 ? '暂无' :(obj.plantAnalysis == 1 ? 'A' : (obj.plantAnalysis == 2 ? 'B' : obj.plantAnalysis == 3 ? 'C' : '暂无'))}</td>
							<td>${obj.projectMembers }</td>
							<td>${obj.factoryName}</td>							
							<td>${obj.incompleteInspectionTasks }</td>
							<td>
								<c:if test="${obj.contractNumber>0 }">是</c:if>
								<c:if test="${obj.contractNumber==0 }">否</c:if>
							</td>
							<td>
								<c:if test="${obj.recentInspectionReport>0 }">是</c:if>
								<c:if test="${obj.recentInspectionReport==0 }">否</c:if>
							</td>
							<td><c:if test="${obj.status!=null }">${obj.status }${obj.delayDays }</c:if>
							<c:if test="${obj.status==null }">无</c:if>
							</td>
						</tr>
					</c:forEach>	
					</tbody>
				</table>
			</div>		
		</div>
		<input type="hidden" id="pageStr" name="pageStr" value="${page == null ? 1 : page}">
            <input type="hidden" id="countPage" name="countPage" value="${lastNum}">
            <input type="hidden" id="pageSize" name="pageSize" value="${pageSize == null ? 100 : pageSize}">
            </form>
	</div>
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
<script>
    //查询
    function searchProjectData(obj) {
        var pageNumber = $("#pageStr").val();
        var countPage = $("#countPage").val();
        var pageSize = $("#pageSize").val();
        var factoryName = $("#factoryName").val();
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
