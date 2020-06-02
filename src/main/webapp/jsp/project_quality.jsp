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
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>任务系统，注塑项目质量分析</title>
		<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="../css/add.css">		
	</head>
<body class="project_quality_bg">
	<div class="project_quality">
		<h3>质量分析表录入-塑料类</h3>
		<div class="input_in">
			<!-- <div class="title clearfix">
				<div class="col-xs-3">
					<label class="pull-left">项目号:</label>
					<input type="text" class="form-control pull-left">
				</div>
				<div class="col-xs-3">
					<label class="pull-left">产品名：</label>
					<input type="text" class="form-control pull-left">
				</div>
				<div class="col-xs-3">
					<label class="pull-left">订单等级：</label>
					<input type="text" class="form-control pull-left">
				</div>				
			</div> -->
			<div class="wrap wrap1">
				<p class="p_title">图纸分析: </p>
				<div class="form-inline">
					<div class="form-group">
						<label>图纸数量：</label>
						<input type="text" class="form-control">
					</div>
					<div class="form-group group2">
						<label>是否需要做BOM表：</label>
						<label class="radio-inline">
							<input type="radio" name="require">是
						</label>
						<label class="radio-inline">
							<input type="radio" name="require">否
						</label>
					</div>
				</div>
				<div class="form-inline">
					<div class="form-group">
						<label class="w240">图纸尺寸最高精度要求(写出数值/等级):</label>
						<input type="text" class="form-control">
					</div>
					<div class="form-group">
						<label>零件颜色及表面要求:</label>
						<input type="text" class="form-control">
					</div>
				</div>
				<div class="form-inline">
					<div class="form-group">
						<label>产品材料:</label>
						<input type="text" class="form-control">
					</div>
					<div class="form-group">
						<label>建议模仁材料牌号及原因：</label>
						<input type="text" class="form-control">
					</div>
				</div>
				<div class="form-inline">
					<div class="form-group">
						<label class="w240">客户原图上哪些要求,标准 还不清楚：</label>
						<input type="text" class="form-control">
					</div>
					<div class="form-group group2">
						<label> 是否有没查到的标准：</label>
						<label class="radio-inline">
							<input type="radio" name="require">有
						</label>
						<label class="radio-inline">
							<input type="text" class="inline-input">
						</label>
						<label class="radio-inline ml0">
							<input type="radio" name="require">无
						</label>
					</div>
				</div>
				<div class="form-inline special">
					<div class="form-group group3">
						<label class="w100">有无3D图: </label>
						<label class="radio-inline">
							<input type="radio" name="require">有
						</label>
						<label class="radio-inline no">
							<input type="radio" name="require">无
						</label>
					</div>
					<div class="form-group group3 group3_2">
						<label class="w100"> 有无2D图: </label>
						<label class="radio-inline">
							<input type="radio" name="require">有
						</label>
						<label class="radio-inline no">
							<input type="radio" name="require">无
						</label>
					</div>
					<div class="form-group qs">
						<label> 未注公差标注是否有缺失：</label>
						<input type="text" class="form-control">
					</div>
				</div>
				<div class="form-inline">
					<div class="form-group">
						<label>是否有装配关系： </label>
						<input type="text" class="form-control">
					</div>
					<div class="form-group group2">
						<label> 验证装配合理性: </label>
						<label class="radio-inline">
							<input type="radio" name="require">合理
						</label>
						<label class="radio-inline ml0">
							<input type="radio" name="require">不合理
						</label>
						<label class="radio-inline">
							<input type="radio" name="require">N/A
						</label>
					</div>
					
				</div>
				<div class="form-inline">
					<div class="form-group">
						<label>产品有无后续处理要求:   </label>
						<input type="text" class="form-control">
					</div>
					<div class="form-group">
						<label> (如果没要求,是否该体醒客户) </label>
						<input type="text" class="form-control">
					</div>
					
				</div>
				<div class="form-inline">
					<div class="form-group">
						<label>产品功能： </label>
						<textarea class="form-control"></textarea>
					</div>					
				</div>
				<div class="form-inline">
					<div class="form-group">
						<label>产品的特殊要求: </label>
						<textarea class="form-control"></textarea>
					</div>					
				</div>
				<div class="form-inline">
					<div class="form-group">
						<label>如果是新产品,你认为存在什么设计缺陷:</label>
						<textarea class="form-control"></textarea>
					</div>					
				</div>
				<div class="form-inline product_require">
					<div class="form-group">
						<label class="spe_label">客户对产品外观的 要求是:</label>
						<label class="radio-inline">
							<input type="radio" name="require">精致漂亮
						</label>
						<label class="radio-inline">
							<input type="radio" name="require">干净无毛刺
						</label>
						<label class="radio-inline">
							<input type="radio" name="require"> 一般
						</label>
					</div>					
				</div>
			</div>
			<div class="wrap wrap2">
				<p class="p_title">工艺分析:(特殊工艺或要求达到需注明)</p>
				<div class="form-inline">
					<div class="form-group">
						<label>产品的特殊要求: </label>
						<textarea class="form-control"></textarea>
					</div>					
				</div>
				<div class="form-inline">
					<div class="form-group">
						<label>技术协议： </label>
						<textarea class="form-control"></textarea>
					</div>					
				</div>
				<div class="form-inline">
					<div class="form-group">
						<label>材料性能表(如客户有特殊要求需明确凯融和客户的对比）:</label>
						<textarea class="form-control"></textarea>
					</div>					
				</div>
			</div>
			<div class="btns"><button class="btns">提交</button></div>
		</div>

	</div>
</body>
</html>
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script>
        window.onload = window.onresize = function(){

            var clientWidth =document.documentElement.clientWidth;

            document.getElementsByTagName("html")[0].style.fontSize =

                    clientWidth / 768* 40 + "px";
        }
</script>
