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
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>采购行踪录入</title>
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="../css/add.css">

</head>
<body>
	<div class="confirm_list trank_entry">
		<h1 class="customer_complaint_h1">
			采购行踪录入
			<div class="btns">
				<a class="select_blank btn"
					href="/user/toIndex">返回功能选择页</a>
			</div>
		</h1>		
				<select class="form-control mt10" id="userName" name="userName">
					<option></option>						
					<option value="zhaoqiang">zhaoqiang</option>
					<option value="xuping">xuping</option>
					<option value="xuwei">xuwei</option>
					<option value="ThomasChen">ThomasChen</option>
					<option value="Bensonzhang">Bensonzhang</option>
					<option value="RogerQiu">RogerQiu</option>
					<option value="chengmingkun">chengmingkun</option>
					<option value="shiqinhui">shiqinhui</option>
				</select>	
				<div class="form-group mt10">										
						<div class="input-group date form_date col-sm-6"
							data-date="" data-date-format="yyyy-mm-dd">
							<input id="outDate" name="outDate"
								class="form-control brt brt_7" size="16" type="text" 
								place="选择日期" placeholder="选择日期" readonly
								requiredate><span class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<span></span>					
				</div>
				<label class="display_block"><input type="radio" class="mr5" checked="checked"><span>正常上班</span></label>	
				<div class="form-group mt10">	
					<em>去处:</em>
					<label class="ml10"><input type="checkbox" class="mr5" name="place" checked="checked" value="公司"><span>公司</span></label>	
					<label class="ml10"><input type="checkbox" class="mr5" name="place" value="仓库"><span>仓库</span></label>	
					<label class="ml10"><input type="checkbox" class="mr5" name="place" value="工厂"><span>工厂名</span></label>	
				</div>
				<div class="form-group position_relative" style="display:none;">
					<input type="text" class="form-control" name="factory" placeholder="工厂1">
					<ul>
				
					</ul>
				</div>
				<div class="form-group position_relative" style="display:none;">
					<input type="text" class="form-control" name="factory" placeholder="工厂2">
					<ul>
		    
					</ul>
				</div>	
				<div class="form-group position_relative" style="display:none;">
					<input type="text" class="form-control" name="factory" placeholder="工厂3">
					<ul>

					</ul>
				</div>	
				<button type="button" class="btn bgcolor_ff0 tj" onclick="addTrack()">确认并提交</button>	
	</div>
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript" charset="utf-8"></script>
<script>

//选择工厂
$.expr[':'].contains = function(a, i, m){
    return $(a).text().toUpperCase().indexOf(m[3].toUpperCase()) >= 0;

};

/* $('input[name="factory"]').keyup(function(){
	var key = $(this).val();
	$(this).siblings('ul').find('li').show();
	var a = $(this).siblings('ul').find('li').css('display');
	$(this).parent().find('li').each(function(){
		$(this).parent().find("li:not(:contains("+ key +"))").css({'display':'none'});
	})
}); */

/* $('li').click(function(){
	var li_val = $(this).text();
	$('ul li').hide();
	$(this).parent().parent().find('input').val(li_val);
}); */


  var names = []; 
  $(function(){
	    var factoryNameList = '${factoryNameList}';
	    factoryNameList = factoryNameList.substring(1, factoryNameList.length - 1);
	    names=factoryNameList.split(",");	
	    
	    $('input[name="place"]').change(function(){
	    	if($(this).val() == '工厂'){
	    	    if($(this).is(":checked")){	    		
	    			$('.position_relative').show();
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
    	$(this).next().find('li').show();   	
    	
    	
    	  //选中关键词  		 
  		$('li').click(function(e){
  			var li_val = $(this).text();
  			$('ul li').hide();
  			$(this).parent().parent().find('input').val(li_val);
  			
  			 $(document).on("click", function(){
  			     $('li').hide();
			 });								
			 e.stopPropagation(); 
  		});
    })


     

 
    //保存采购行踪
    function addTrack(){   	
	     //各项保存的数据
	   	 var param = {};
	   	 var userName = $('select[name="userName"]').val();
	   	 param["userName"] = userName;	
		 var outDate = $('input[name="outDate"]').val();
	   	 param["outDate"] = outDate; 
	   	 
	   	  var factoryFlag = false;
	   	  var placeList=[];
	   	  //获取公司和仓库
	   	  $('input[name="place"]').each(function(){
	   		  if($(this).is(":checked")){
	   			  var placeName = $(this).val();
	   			  if(placeName != '工厂'){
		   			  var place = {"place":placeName}
		   			  placeList.push(place);
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
		 			  var place = {"place":factoryName}
		   			  placeList.push(place);
		   		   }		   		   
		   	   })
		  }
	   	   
	   	  
			$.ajax({
				type : "post",
				data : {
					param : JSON.stringify(param),
					placeList : JSON.stringify(placeList)
				},
				url : "${ctx}/track/addTrack",
				success : function(json) {
// 					var json = eval("(" + data + ")");
					if (json.ok) {
						layer.msg(json.message,{time:2000});
						setTimeout(function(){              
							window.location.reload();//页面刷新2000毫秒
						},2000);
					}else{
						layer.msg(json.message,{time:2000});
					}
				}
			})
    	
    }













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
	$('.table-condensed tbody,.table-condensed tfoot').on('click',function(){
		$('.datetimepicker').hide();
	});
	
</script>












