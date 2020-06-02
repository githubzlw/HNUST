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
    <link rel="stylesheet" href="../css/add.css">
    <link rel="stylesheet" href="../css/progressBar.css" />
    <link rel="stylesheet" href="../css/ui.css" />
    <link rel="stylesheet" href="../css/ui.progress-bar.css">
<style>
 .producting_project{max-width:768px;margin:0 auto;}
 .back{background-color: #027CFF;padding: 3px 6px;color: #fff;border-radius:4px;top:10px;position:relative;}
 .back:hover{color:#fff;position:relative;}
  h3{font-size:20px;margin-top:10px;font-weight:700;}
  .mt20{margin-top:20px;}  
</style>	
<body>
	<div class="container">
	<div class="producting_project">
		<div class="row">
			<div class="col-xs-7"><h3 >在生产工厂及项目列表</h3></div>
			<div class="col-xs-5">
				<a class="back" target="_blank" href="/user/toIndex">返回功能选择页</a>
			</div>			
		</div>
		 <form id="form4" action="/project/productingProjectDetail" method="post">	
		 <input type="hidden" value="${sortNum}" name="sortNum" id="sortNum">
            <input type="hidden" value="${upOrDown}" name="upOrDown" id="upOrDown">
		<div class="row mt20">
			<div class="col-xs-12">
				<table class="table table-bordered" id="tableSort">
					<thead>
						<tr>
							<th class="th1">工厂</th>
							<th type="number" class="th2">
                            	<div class="d d7">
                           		 	工厂在做的合同数量
                           		 	<span class="s1 glyphicon glyphicon-triangle-top"></span>
                           		 	<span class="s2 glyphicon glyphicon-triangle-bottom display_none"></span>
                           		 <%-- <div class="arrow" filed="0" <c:if test="${sortNum == 0}">style="color: red;"</c:if>>
	                                  <span class="s1 glyphicon glyphicon-triangle-top" <c:if test="${sortNum == 0 && upOrDown == 1}">style="display:inline-block;"</c:if>></span>
	                                  <span class="s2 glyphicon glyphicon-triangle-bottom display_none" <c:if test="${sortNum == 0 && upOrDown == 1}">style="display:none;"</c:if>></span>
	                              </div> --%>
                           	 </div>
                            </th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="obj" items="${factoryList}" varStatus="i">
						<tr>
							 <td class="separator">
                                <a target="_blank" href="/project/productingProject?factoryName=${obj.factoryName}">${obj.factoryName}</a>
                             </td>
                             <td >${obj.projectCount == null ? '0' :obj.projectCount}</td>
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
	<div class="page-box text-center">
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
    
    
    
    /* 表格排序 */
    $('.s2').show();
    $('.s1').hide();
     var sortIndex = -1;
       $('#tableSort thead th').each(function () {//遍历thead的tr下的th
          var thisIndex = $('#tableSort thead th').index($(this)); //获取th所在的列号
          $(this).click(function () {//给当前表头th增加点击事件
            var dataType = $(this).attr("type");//点击时获取当前th的type属性值
            checkColumnValue(thisIndex, dataType);
            $('.s1,.s2').toggle();
          });
          $('.th1').off('click'); 
        });
     //对表格排序
       function checkColumnValue(index, type) {
         var trsValue = new Array();
         $('#tableSort tbody tr').each(function () {
           var tds = $(this).find('td');
           //获取行号为index列的某一行的单元格内容与该单元格所在行的行内容添加到数组trsValue中
           trsValue.push(type + ".separator" + $(tds[index]).html() + ".separator" + $(this).html());
           $(this).html("");
         });
         var len = trsValue.length;
         if (index == sortIndex) {
         //如果已经排序了则直接倒序
           trsValue.reverse();
           
         } else {
         	 
           for (var i = 0; i < len; i++) {
             //split() 方法用于把一个字符串分割成字符串数组
             //获取每行分割后数组的第一个值,即此列的数组类型,定义了字符串\数字\Ip
             type = trsValue[i].split(".separator")[0];
             for (var j = i + 1; j < len; j++) {
               //获取每行分割后数组的第二个值,即文本值
               value1 = trsValue[i].split(".separator")[1];
               //获取下一行分割后数组的第二个值,即文本值
               value2 = trsValue[j].split(".separator")[1];
               //接下来是数字\字符串等的比较
               if (type == "number") {
                 value1 = value1 == "" ? 0 : value1;
                 value2 = value2 == "" ? 0 : value2;
                 if (parseFloat(value1) > parseFloat(value2)) {
                   var temp = trsValue[j];
                   trsValue[j] = trsValue[i];
                   trsValue[i] = temp;
                 }
               } 
             }
           }
         }
         for (var i = 0; i < len; i++) {
           $("tbody tr:eq(" + i + ")").html(trsValue[i].split(".separator")[2]);
         }
         sortIndex = index;
       }
    
    
    
    
    
    
    
    
</script>