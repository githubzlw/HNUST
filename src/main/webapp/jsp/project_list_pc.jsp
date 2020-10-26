<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.*"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head lang="en">
<meta charset="UTF-8">
<meta http-equiv="pragram" content="no-cache">
<meta http-equiv="cache-control" content="no-cache, must-revalidate">
<meta http-equiv="expires" content="0">
<title>项目列表-PC</title>
<link rel="stylesheet" href="${ctx}/css/test.css">
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="${ctx}/css/easydialog.css" />
<link rel="stylesheet" href="../css/add.css">
<style>
.table3 .up, .table3 .view {
	background-color: #027cff;
	padding: 4px 8px;
	color: #fff;
	border-radius: 4px;
	margin-top: 10px;
	display: inline-block;
}

.add_div_tc5 {
	width: 600px;
	height:auto;
	border: 2px solid #666;
	position: fixed;
	z-index: 5;
	top: 40px;
	left: 50%;
	margin-left: -160px;
	background-color: #999;
	display: none;	
} 
.add_tc4 .file_name{position:absolute;top:53px;left:15px;}

</style>
<script src="../lib/jquery/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
<script type="text/javascript" src="../js/easydialog.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script>
function autoSize(ImgD,h,w) {
     var image = new Image(); 
    image.src = ImgD.src; 
    if (image.width<w && image.height<h) {
        ImgD.width=image.width;
        ImgD.height=image.height;
    } else {
        if (w / h <= image.width / image.height) {
            ImgD.width=w;
            ImgD.height=w * (image.height / image.width);
        } else {
            ImgD.width=h * (image.width / image.height);
            ImgD.height=h;
        }
    }
} 
</script>

<style type="text/css">
.updown-list {
	display: none;
	width: 432px;
	height: 190px;
	overflow: hidden;
	-webkit-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-moz-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-ms-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
	-o-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
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
	width: 100%;
	height: 150px;
	position: absolute;
	top: 34px;
	left: 0;
	z-index: 10;
	padding: 0;
	background-color: #FFF;
	border-right: 1px solid #ECECEC;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-o-box-sizing: border-box;
	-ms-box-sizing: border-box;
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
	overflow-y: scroll;
	overflow-x: hidden;
}

#easyDialogBox {
	margin-top: -392px;
}

.big_pic {
	position: fixed;
	width: 100%;
	height: 100%;
	z-index: 10;
	text-align: center;
	display: none;
}

.big_pic .close {
	position: absolute;
	right: 10px;
	top: 10px;
	z-index: 1000;
	opacity: 1;
}

.big_pic .big_bg {
	position: fixed;
	width: 100%;
	height: 100%;
	background-color: #fff;
	z-index: 10;
	text-align: center;
	padding-top: 50px;
	top: 0;
}

.big_pic .big_in {
	width: 1200px;
	margin: 0 auto;
	position: relative;
	z-index: 100;
	text-align: center;
}

.tab-link {
	font-size: 18px;
	color: #666666;
	font-weight: bold;
	display: block;
	min-height: unset;
	line-height: unset;
	overflow: unset;
	text-overflow: unset;
	text-align: center;
}

.grid {
	overflow: hidden;
}

.grid tr td {
	font-size: 18px;
	color: #666666;
	border-bottom: 1px #dddddd solid;
	border-left: 1px #dddddd solid;
	padding: 4px;
	overflow: unset;
	line-height: unset;
	vertical-align: middle;
	text-overflow: unset;
	position: relative;
}

.grid tr td:first-child {
	text-align: center;
}

.grid tr th {
	padding: 4px;
	text-align: center;
	vertical-align: middle;
}

.form-horizontal .control-label {
	font-size: 16px;
}

.page-box {
	font-size: 16px;
	margin-top: 20px;
	margin-bottom: 15px;
}

.page-box a {
	font-size: 16px;
}

.meeting-list-table .color-blocks-btn {
	font-size: 16px;
	height: 30px;
}

.main-container {
	position: relative;
}

.main-container label {
	float: left;
	margin-top: 8px;
}

.form-group2 label {
	margin-right: 8px;
	font-size: 16;
}

.roleform select {
	float: left;
	width: 116px;
}

.form-horizontal .form-group2 {
	box-sizing: border-box;
	margin-left: 20px;
	margin-right: 8px;
}

.showHtml {
	margin-right: 25px;
}

.btns {
	position: absolute;
	right: 15px;
	top: -40px;
}

.main-table {
	position: relative;
	padding-bottom: 15px;
	padding-top: 5px;
}

.btn2 {
	margin-left: 335px;
}

.main-container {
	width: 1680px;
}

.text_ell {
	display: inline-block;
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	cursor: pointer;
}

.w125 {
	width: 125px;
	display: inline-block;
}

#tbhtml, #tbhtml * {
	box-sizing: border-box;
}

.new_add {
	padding: 1px 2px;
	font-size: 12px;
	background-color: #fff;
	outline: none;
	border: 0 none;
	border: 1px solid #999;
	position: absolute;
	bottom: 0;
	right: 0;
	z-index: 1;
	font-size: 14px;
	color: #333;
}

.add_tc {
	width: 320px;
	height: 270px;
	box-shadow: 3px -2px 6px rgba(43, 43, 43, 0.5), -3px 2px 6px
		rgba(43, 43, 43, 0.5);
	position: fixed;
	top: 40px;
	left: 50%;
	margin-left: -160px;
	z-index: 2;
	background-color: #fff;
	text-align: center;
	padding-top: 10px;
	display: none;
}

.add_tc2 {
	height: 215px;
}

.add_tc textarea {
	width: 300px;
	height: 110px;
	border: 1px solid #ddd;
	resize: none;
	overflow-y: auto;
}

.add_tc .btns2 {
	overflow: hidden;
	padding: 10px;
}

.roleform div {
	margin-bottom: 0;
}

.col-sm-4_2 {
	margin-left: 0px;
	margin-right: 0px;
}

.logo {
	position: absolute;
	top: 15px;
	right: 0;
}

.dropdown {
	position: relative;
}

.dropdown button {
	background-color: #fff;
	width: 98px;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	position: relative;
	text-align: left;
	margin-top: -8px;
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
}

.dropdown .caret {
	position: absolute;
	right: 2px;
	top: 15px;
}

.dropdown ul {
	list-style: none;
	width: 155px;
	height: 208px;
	background-color: #fff;
	box-shadow: 3px -2px 6px rgba(43, 43, 43, 0.5), -3px 2px 6px
		rgba(43, 43, 43, 0.5);
	padding-left: 10px;
	position: absolute;
	top: 40px;
	left: 90px;
	z-index: 100;
	display: none;
}

.dropdown .ul1 {
	height: 190px;
}

.dropdown .ul2 {
	height: 120px;
}

.dropdown .ul3 {
	height: 140px;
}

.dropdown .ul4 {
	height: 120px;
}

.dropdown .ul5 {
	height: 90px;
	width: 210px;
}

.dropdown ul li {
	display: block;
	height: 23px;
	float: left;
	width: 100%;
}

.dropdown ul li input {
	float: left;
	width: auto;
	margin-right: 8px;
}

.dropdown ul li label {
	margin-right: 0;
	display: block;
}

.div_transparent {
	position: fixed;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	z-index: 99;
	background-color: transparent;
	display: none;
}

.showHtml {
	width: 220px;
}

#tbhtml tr {
	min-height: 50px;
}

.table-wrap {
	width: 100%;
	outline: none;
	position: relative;
	font-size: 14px;
	color: #444;
	border: 1px #dddddd solid;
	margin-bottom: 12px;
}

.div_pur select {
	height: 31px;
	border: 1px solid #ddd;
	margin-left: 5px;
	margin-right: 5px;
}

.page-box a {
	padding: 6px 12px;
}

.page-box a.first-padding {
	padding: 6px 12px;
}

.page-box a:nth-last-child(2), .page-box a:last-child {
	padding: 6px 12px;
}

.big_pic .big_in .top {
	font-size: 28px;
	/* color:#81d0fc; */
	background-color: rgb(255, 255, 255);
}

.big_pic .top {
	overflow: hidden;
}

.big_pic .top button {
	color: #333;
	font-size: 32px;
	font-weight: 700;
}

.big_pic .center {
	overflow: hidden;
	background-color: rgb(255, 255, 255);
}

.big_pic .center .imgs1 {
	height: 500px;
	width: 560px;
	background-color: #fff;
	overflow: hidden;
	position: relative;
}

.big_pic .center .p_s p {
	font-size: 28px;
	text-align: left;
	width: 1140px;
	padding-left: 15px;
}

.big_pic .center .imgs1 ul {
	list-style: none;
	padding: 0;
	width: auto;
}

.big_pic .center .imgs1 li {
	width: 560px;
	height: 500px;
	text-align: center;
	line-height: 500px;
	float: left;
}

.big_pic .center .imgs1 li img {
	max-width: 560px;
	max-height: 500px;
	width: auto;
	height: auto;
}

.big_pic .center .btn span {
	font-weight: 700;
	color: #333;
	font-size: 16px;
}

.big_pic .btn-imgs {
	position: absolute;
	z-index: 100;
}

.big_pic .btn-imgs img {
	position: absolute;
	top: 230px;
	width: 50px;
	height: 50px;
	background-color: #fff;
	padding: 10px;
}

.big_pic .btn-imgs .img1 {
	left: 0;
	top: 210px;
}

.big_pic .btn-imgs .img2 {
	left: 510px;
	top: 210px
}

.big_pic .imgs2 .imgs {
	width: 100%;
	height: 500px;
	text-align: center;
	line-height: 500px;
}

.big_pic .imgs2 .imgs img {
	max-width: 100%;
	max-height: 500px;
	width: auto;
	height: auto;
}

.big_pic .bottom {
	font-size: 28px;
	/*  color: #81d0fc; */
	text-align: left;
	margin-top: 15px;
	background-color: rgb(255, 255, 255);
}

.big_pic .bottom em {
	font-style: normal;
}

.big_pic li {
	width: 100%;
	height: 500px;
	text-align: center;
	line-height: 500px;
}

.table-content {
	overflow-x: hidden;
}

.add_tc button {
	width: 100px;
	height: 30px;
	line-height: 30px;
	background-color: #fff;
	border: 1px solid #ddd;
}

.add_tc .s_c {
	position: relative;
	text-align: left;
	padding-left: 10px;
	margin-bottom: 10px;
}

.add_tc .s_c input {
	position: absolute;
	top: 0;
	left: 0;
	width: 100px;
	opacity: 0;
}

.add_tc p {
	text-align: left;
	padding-left: 10px;
	padding-right: 10px;
}

.grid tr td .imgs {
	width: 170px;
	min-height: 130px;
	text-align: center;
	line-height: 130px;
}

.grid tr td .imgs img {
	max-width: 170px;
	max-height: 130px;
	width: auto;
	height: auto;
}

#reportImgHtml {
	width: 185px;
	display: inline-block;
	flaot: left;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	margin-top: 5px;
}

.s_c button {
	float: left;
	margin-right: 5px;
}

.roleform div {
	padding-top: 0;
}

.form-group1 label, .form-group2 label {
	margin-top: 0;
}

#form {
	padding-top: 20px;
}

.form-group2 {
	margin-top: 20px;
}

.big_in2 .center .imgs1 {
	width: 800px;
	height: 600px;
	background-color: transparent;
	text-align: center;
	line-height: 600px;
}

.big_in2 .center .imgs1 img {
	max-width: 800px;
	max-height: 600px;
	width: auto;
	height: auto;
	vertical-align: middle;
}

.big_in2 .center .imgs2 {
	/* color:#81d0fc; */
	font-size: 28px;
	width: 350px;
	line-height: 30px;
	position: relative;
}

.big_in2 .center .imgs2 p {
	text-align: left;
}

.big_in2 .sc_p {
	position: absolute;
	right: 10px;
	bottom: 0;
}

.wrap {
	position: fixed;
	top: 35px;
	left: 50%;
	margin-left: -600px;
	z-index: 100;
	width: 1200px;
	background-color: #fff;
}

.bg_2 {
	width: 100%;
	height: 100%;
	position: fixed;
	top: 0;
	left: 0;
	background-color: #fff;
	z-index: 10;
	display: none;
}

@media only screen and (min-width: 1200px) {
	.quality_report .imgs {
		width: 800px;
		height: 600px;
	}
}

.wrap .pic_intro {
	width: 350px;
	min-height: 300px;
	padding-top: 10px;
}

.wrap .pic_intro textarea {
	height: 260px;
	font-size: 20px;
	width: 325px;
}

.wrap .div_clear {
	overflow: hidden;
}

.wrap .btn2 {
	right: 390px;
}

.wrap .imgs ul li a {
	height: 600px;
	width: 800px;
	display: block;
	padding-top: 12px;
}

.wrap .imgs ul li a img {
	max-width: 800px;
	max-height: 520px;
	width: auto;
	height: 520px;
}

.wrap .imgs ul li {
	height: 550px;
}

.wrap .div_clear button {
	right: 20px;
	font-size: 16px;
	color: #fff;
	background-color: #027cff;
}

.wrap .close {
	color: #333;
	font-size: 32px;
	font-weight: 700;
	background-color: #fff;
	position: absolute;
	top: -34px;
	right: 30px;
	opacity: unset;
	padding: 6px 12px;
	border-radius: 4px;
}

#btn2 {
	position: absolute;
	top: -100px;
	left: 285px;
}

.big_pic .center .z_b ul li img {
	width: 800px;
	height: 600px;
}

.add__tc_fk .form-control {
	width: auto;
}

.add__tc_fk .row {
	margin-left: 0;
	margin-right: 0;
	margin-bottom: 6px;
}

.add_tc_zb {
	height: auto;
	width: 544px;
}

.add_tc_zb .select_zb {
	width: auto;
}

.add_tc_zb .imgs li {
	float: left;
	width: 120px;
	height: 90px;
	border: 1px solid #999;
	text-align: center;
	line-height: 90px;
	position: relative;
	margin-right: 12px;
	margin-bottom: 10px;
	color: #666;
	font-size: 14px;
}

.add_tc_zb .imgs img {
	max-width: 120px;
	max-height: 90px;
	width: auto;
	height: auto;
}

.add_tc_zb .s_c input {
	position: absolute;
	width: 120px;
	height: 90px;
	opacity: 0;
}

.add_tc_zb .glyphicon-remove {
	position: absolute;
	right: 0;
	top: 0;
}

.add_tc_zb label {
	margin-left: 6px;
	font-size: 16px;
}

.add_tc_zb p {
	font-size: 16px;
}

.add_tc_zb textarea {
	width: 95%;
	margin-left: 10px;
}

.next_div {
	background-color: #eee;
}

.z-tb .content {
	float: right;
	margin: 0px 20px 1 1;
}

.z-tb {
	height: auto;
	/*     min-height:500px; */
	width: 390px;
}

.z-tb .z-t {
	margin: 5px 0px 0 62px;
	font-size: 16px;
}

.z-tb .del_btn {
	position: absolute;
	width: 47px;
	height: 86px;
	margin: 0 0 0px -185px;
	color: #fff;
	font-size: 16px;
	background-color: red;
	top: 0;
}

.z-tb .add_line {
	width: 91%;
	background-color: #7b7bfd;
	vertical-align: middle;
	text-align: center;
	height: 35px;
}

.milestone {
	margin: 10px 0 20px 0;
	position: relative;
}

#projectReportHtmlShow {
	position: absolute;
	height: 500px;
	top: 0;
	left: 0;
}

.big_pic .center .p_s .p1, .big_pic .center .p_s .p2 {
	width: 560px;
}

.select_blank {
	background-color: #027CFF;
	padding: 7px 12px;
	text-decoration: none;
	color: #fff;
	position: relative;
	top: 5px;
	margin-left: 380px;
}

.select_blank:hover, .select_blank:hover {
	text-decoration: none;
	background-color: #4362C5;
	color: #fff;
}

.project_list_pc .add_tc3 {
	height: auto;
	padding-top: 10px;
	padding-bottom: 10px;
}

#feedback:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
	color: #666;
}

#feedback::-moz-placeholder { /* Mozilla Firefox 19+ */
	color: #666;
}

input:-ms-input-placeholder, #feedback:-ms-input-placeholder {
	color: #666;
}

input::-webkit-input-placeholder, #feedback::-webkit-input-placeholder {
	color: #666;
}

.zj_delivery {
	padding-top: 34px;
	padding-bottom: 10px;
}

.add_tc .del {
	padding: 2px 3px;
	background-color: #e51c23;
	color: #fff;
	width: auto;
	height: auto;
	line-height: normal;
}

.meeting-list-table .add_entry_btn {
	float: right;
	font-size: 14px;
	padding: 3px;
	position: relative;
	top: -25px;
	right: -63px;
	height: auto;
	line-height: 20px;
}

.project_list_pc .add_tc4 {
	width: 600px;
	font-size: 16px;
	z-index:3;
	display:block;
}

.add_sc>div {
	position: relative;
	text-align: left;
}

.add_sc input[type="file"] {
	position: absolute;
	top: 0;
	left: 15px;
	z-index: 1;
	width: 100px;
	opacity: 0;
}

.add_tc4 button {
	background-color: #027CFF;
	color: #fff;
	border-radius: 4px;
	line-height:25px;
}

.project_list_pc .add_tc4 .row {
	margin-bottom: 10px;
}

.add_fileName {
	margin-top: 8px;
}

.add_tc4 textarea.form-control {
	width: 100%;
}
.project_list_pc .table1 .th6 .s6{width: 120px;}
.project_list_pc .table1>tbody>tr>td{padding-left:2px;}
#delayDays{width:80%;}

</style>
<script type="text/javascript">
  var purchaseName = "${project.purchaseName}";
  var documentaryName = "${project.sellName}";
  var qualityName = "${project.qualityName}";
  var technician = "${project.technicianStr}";
  var projectTypeStr = "${project.projectStatusS}";
  var projectStageStr = "${project.projectStageS}";
  var plantAnalysisStr = "${project.plantAnalysisS}";
  var detailStatusS = "${project.detailStatusS}";
  var delayStatusS = "${project.delayStatusS}";
  var inputKey = "${project.inputKey}";
  var importantSelect = "${project.importantSelect}";
  var qualityselect = "${project.qualityReportSelect}";
  var expectedShipmentSelect = "${project.expectedShipmentSelect}";
  


  
  
  $(function(){
	  
	  $('#inputKey').val(inputKey);
	  
	 // 默认情况下是全选，内容是全部状态
	$('.ul1 input,.ul2 input,.ul3 input,.ul4 input,.ul5 input').prop('checked',true);
	$('.dropdown1 button em').html('全部状态');
	$('.dropdown2 button em').html('全部阶段');
	$('.dropdown3 button em').html('全部等级');
	$('.dropdown5 button em').html('全部细节状态');
	$('.dropdown6 button em').html('全部');

	  //项目状态显示
	  if(projectTypeStr != '' && projectTypeStr != null){
		  var s = '';
		  $('.dropdown1').find('input[name="projectType"]').each(function(){
			  var num = $.inArray($(this).val(), projectTypeStr); 
			  if(num >= 0){
				 $(this).attr('checked','checked');
				 if(projectTypeStr.length < 7){
					 $('.dropdown1').find('input:first').removeAttr('checked');
				 }					 
				 s += $(this).siblings('span').text() + ",";
			  }else{
				 $(this).attr('checked',false);
				 $('.dropdown1').find('input:first').attr('checked',false);
			  }
		  })
		  
		  if (s != '') {
				s = s.substring(0, s.length - 1);
				$('.dropdown1 button em').html(s);
				if($('.dropdown1').find('input:first').is(':checked')){
					$('.dropdown1 button em').html('全部状态');
				}
		  }
		  
		  
		  
		  
	  }
	  
	  //项目细节状态显示
	  if(detailStatusS != '' && detailStatusS != null){
		  var s = '';
		  $('.dropdown5').find('input[name="detailStatus"]').each(function(){
			  var num = $.inArray($(this).val(), detailStatusS); 
			  if(num >= 0){
				 $(this).attr('checked','checked');
				 if(detailStatusS.length < 7){
					 $('.dropdown5').find('input:first').removeAttr('checked');
				 }					 
				 s += $(this).siblings('span').text() + ",";
			  }else{
				 $(this).attr('checked',false);
				 $('.dropdown5').find('input:first').attr('checked',false);
			  }
		  })
		  
		  if (s != '') {
				s = s.substring(0, s.length - 1);
				$('.dropdown5 button em').html(s);
				if($('.dropdown5').find('input:first').is(':checked')){
					$('.dropdown5 button em').html('全部细节状态');
				}
		  }
	  }
	  //项目阶段显示
	  if(projectStageStr != '' && projectStageStr != null){
		  var s = '';
		  $('.dropdown2').find('input[name="projectStage"]').each(function(){
			  var num = $.inArray($(this).val(), projectStageStr); 
			  if(num >= 0){
				 $(this).attr('checked','checked');
				 if(projectStageStr.length < 4){
					 $('.dropdown2').find('input:first').removeAttr('checked');
				 }					 
				 s += $(this).siblings('span').text() + ",";
			  }else{
				 $(this).attr('checked',false);
				 $('.dropdown2').find('input:first').attr('checked',false);
			  }
		  })
		  
		  if (s != '') {
				s = s.substring(0, s.length - 1);
				$('.dropdown2 button em').html(s);
				if($('.dropdown2').find('input:first').is(':checked')){
					$('.dropdown2 button em').html('全部阶段');
				}
		  }
	  }
	  
	  //项目等级显示
	  if(plantAnalysisStr != '' && plantAnalysisStr != null){
		  var s = '';
		  $('.dropdown3').find('input[name="plantAnalysis"]').each(function(){
			  var num = $.inArray($(this).val(), plantAnalysisStr); 
			  if(num >= 0){
				 $(this).attr('checked','checked');
				 if(projectStageStr.length < 4){
					 $('.dropdown3').find('input:first').removeAttr('checked');
				 }					 
				 s += $(this).siblings('span').text() + ",";
			  }else{
				 $(this).attr('checked',false);
				 $('.dropdown3').find('input:first').attr('checked',false);
			  }
		  })
		  
		  if (s != '') {
				s = s.substring(0, s.length - 1);
				$('.dropdown3 button em').html(s);
				if($('.dropdown3').find('input:first').is(':checked')){
					$('.dropdown3 button em').html('全部等级');
				}
		  }
	  }
	  //延期显示
	  if(delayStatusS != '' && delayStatusS != null){
		  var s = '';
		  $('.dropdown6').find('input[name="delayStatus"]').each(function(){
			  var num = $.inArray($(this).val(), delayStatusS); 
			  if(num >= 0){
				 $(this).attr('checked','checked');
				 if(projectStageStr.length < 2){
					 $('.dropdown6').find('input:first').removeAttr('checked');
				 }					 
				 s += $(this).siblings('span').text() + ",";
			  }else{
				 $(this).attr('checked',false);
				 $('.dropdown6').find('input:first').attr('checked',false);
			  }
		  })
		  
		  if (s != '') {
				s = s.substring(0, s.length - 1);
				$('.dropdown6 button').html(s);
				if($('.dropdown6').find('input:first').is(':checked')){
					$('.dropdown6 button').html('全部');
				}
		  }
	  }

	  //其他排序显示
	  if(importantSelect == '1'){
		  $('#sort_select').find("option[value='最近两周没更新周报的A/B级项目']").attr("selected",true); 
	  }else if(importantSelect == '2'){
		  $('#sort_select').find("option[value='处于暂停状态 超过2周的项目']").attr("selected",true); 
	  }else if(importantSelect == '3'){
		  $('#sort_select').find("option[value='过去2周取消的项目']").attr("selected",true); 
	  }else if(importantSelect == '4'){
		  $('#sort_select').find("option[value='有PO 合同未签的项目，而且 PO时间距离今天超过5天']").attr("selected",true); 
	  }else if(importantSelect == '5'){
		  $('#sort_select').find("option[value='处于样品完结状态 超过一个月的项目']").attr("selected",true); 
	  }else if(importantSelect == '6'){
		  $('#sort_select').find("option[value='进行中有设置过延期的项目']").attr("selected",true); 
	  }else if(importantSelect == '7'){
		  $('#sort_select').find("option[value='新合同上传倒序排列']").attr("selected",true); 
	  }else if(importantSelect == '8'){
		  $('#sort_select').find("option[value='项目结束超过 4个月客户无后续项目']").attr("selected",true); 
	  }
	  
	  
	  if(qualityselect != '' && qualityselect != null){
		  if(qualityselect=="1"){
		  $('#sort_select').find("option[value='质检报告更新倒序排序']").attr("selected",true); 
		  }else if(qualityselect=="3"){
		  $('#sort_select').find("option[value='有质检要求项目按任务发布时间排序']").attr("selected",true); 
		  }else if(qualityselect=="2"){
		  $('#sort_select').find("option[value='合同交期在最近半个月内项目']").attr("selected",true); 
		  }
	  }
	  if(expectedShipmentSelect != '' && expectedShipmentSelect!= null){
		  $('#sort_select').find("option[value='今后7天预计出货']").attr("selected",true); 
	  }
	  
  })
    
</script>



</head>
<body class="list-bgcolor project_list_pc">
	<div class="bg_2"></div>
	<!-- 质检报告pc版 -->
	<div class="quality quality_report quality_report_pc"
		id="qualityReportHtml"></div>

	<div class="big_pic">
		<div class="big_bg"></div>
		<div class="big_in big_in1" style="display: none;">
			<div class="top">
				<span class="pull-left" id="projectNoHtml"></span> <span
					class="pull-left" id="projectNameHtml"></span>
				<button class="pull-right">关闭 X</button>
			</div>
			<div class="center">
				<div class="p_s">
					<p class="pull-left p1">
						项目周报图 <span class="pull-right"><span>当前页数:</span><span
							id="currentImg">1</span><span>总页数:</span><span id="totalImgs">1</span></span>
					</p>
					<p class="pull-right p2">项目概图</p>
				</div>
				<div class="imgs1 pull-left z_b">
					<div class="btn-imgs">
						<img src="../img/left1.png" class="img1"> <img
							src="../img/right1.png" class="img2">
					</div>
					<ul id="projectReportHtmlShow">
						<!-- <li><img src="../img/logo.png" id="projectReportHtml"></li> -->
					</ul>
				</div>
				<div class="imgs1 imgs2 pull-right">

					<div class="imgs">
						<img src="" class="img_big"> <span class="add_img_span"></span>
					</div>
				</div>
			</div>
			<div class="bottom">
				<em>采购周报:</em> <span id="reportHtml"></span> <em>操作人:</em> <span
					id="operatorHtml"></span> <em>操作时间:</em> <span
					id="operatorDateHtml"></span>
			</div>
		</div>

		<div class="big_in big_in2">
			<div class="top">
				<span class="pull-left" id="projectNoHtml"></span> <span
					class="pull-left" id="projectNameHtml"></span>
				<button class="pull-right">关闭 X</button>
			</div>
			<div class="center">
				<div class="p_s" style="display: inline-block;">
					<p class="pull-left">
						<em class="project_introduce"></em>
					</p>
					<br>
					<p class="pull-left">
						项目概图
						<e style="color:red;" id="projectReportAlert"></e>
					</p>
				</div>
				<div class="imgs1 pull-left">
					<div class="imgs">
						<img src="" class="img_big">
					</div>
				</div>
				<div class="imgs1 imgs2 pull-right">
					<p>周报说明:</p>
					<p>
						<i id="reportHtmlNo"></i>
					</p>
					<div class="bottom_p">
						<p>
							操作者：<i id="operatorHtmlNo"></i>
						</p>
						<p>
							操作时间：<i id="operatorDateHtmlNo"></i>
						</p>
					</div>
				</div>
			</div>
		</div>

	</div>
	<input type="hidden" name="pcType" id="pcType" value="${pcType}">
	<div class="main-container page1">
		<img src="../img/logo.png" class="logo">
		<div>
			<h3></h3>
		</div>
		<form id="form" class="roleform form-horizontal" role="form"
			onsubmit="return false;">

			<div class="form-group form-group1" style="height: 40px;">
				<label for="firstname" class="col-sm-2 control-label"
					style="margin-top: 0; margin-left: -165px;"> <!-- <i
					class="ffhh">*</i> -->人员筛选
				</label>
				<div class="col-sm-2 showHtml ml-23">
					<select id="purchase_name" name="purchase_name"
						class="form-control">
						<option value="">全部采购</option>
					</select><span></span>
				</div>

				<div class="col-sm-2 showHtml">
					<select id="documentary_name" name="documentary_name"
						class="form-control">
						<option value="">全部跟单</option>
					</select><span></span>
				</div>
				<div class="col-sm-2 showHtml">
					<select id="quality_name" name="quality_name" class="form-control">
						<option value="">全部质检</option>
					</select><span></span>
				</div>
				<div class="col-sm-2 showHtml">
					<select id="technician" name="technician" class="form-control">
						<option
							<c:if test="${project.technicianStr eq '全部技术'}">selected</c:if>>全部技术</option>
						<option
							<c:if test="${project.technicianStr eq '无技术'}">selected</c:if>>无技术</option>
						<option
							<c:if test="${project.technicianStr eq 'wangweiping'}">selected</c:if>>wangweiping</option>
						<%-- 						<option <c:if test="${project.technicianStr eq 'yaoyu'}">selected</c:if>>yaoyu</option> --%>
					</select><span></span>
				</div>
				<div class="other_gn">
					<label>其他独立排列功能</label> <select class="form-control pull-left"
						id="sort_select" onchange="qualityReportSelect(this)">
						<option value=""></option>
						<option value="处于暂停状态 超过2周的项目">处于暂停状态 超过2周的项目</option>
						<option value="过去2周取消的项目">过去2周取消的项目</option>
						<option value="有PO 合同未签的项目，而且 PO时间距离今天超过5天">有PO
							合同未签的项目，而且 PO时间距离今天超过5天</option>
						<option value="处于样品完结状态 超过一个月的项目">处于样品完结状态 超过一个月的项目</option>
						<option value="进行中有设置过延期的项目">进行中有设置过延期的项目</option>
						<option value="今后7天预计出货">今后7天预计出货</option>
						<option value="最近两周没更新周报的A/B级项目">最近两周没更新周报的A/B级项目</option>
						<option value="新合同上传倒序排列">新合同上传倒序排列</option>
						<option value="项目结束超过 4个月客户无后续项目">项目结束超过 4个月客户无后续项目</option>
						<option value="合同交期在最近半个月内项目">合同交期在最近半个月内项目</option>
						<option value="有质检要求项目按任务发布时间排序">有质检要求项目按任务发布时间排序</option>
						<option value="质检报告更新倒序排序">质检报告更新倒序排序</option>
					</select>
				</div>
			</div>
			<!-- 替换开始 -->
			<div class="form-group form-group2"
				style="height: 40px; margin-top: 10px;">
				<div class="col-sm-2">
					<label class="">项目状态</label> <span></span>
					<!-- 改变 开始-->
					<div class="dropdown dropdown1">
						<button id="dLabel" type="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<em>全部状态</em> <span class="caret"></span>
						</button>
						<div class="div_transparent"></div>
						<ul class="ul1">
							<li class="first-li"><label><input type="checkbox"
									checked="checked" value="-1"><span>全部状态</span></label>
							<li><label><input type="checkbox" name="projectType"
									value='0'><span>新立项</span></label></li>
							<li><label><input type="checkbox" name="projectType"
									value='1'><span>进行中</span></label></li>
							<li><label><input type="checkbox" name="projectType"
									value='6'><span>样品完结</span></label></li>
							<li><label><input type="checkbox" name="projectType"
									value='2'><span>大货完结</span></label></li>
							<li><label><input type="checkbox" name="projectType"
									value='5'><span>项目取消</span></label></li>
							<li><label><input type="checkbox" name="projectType"
									value='4'><span>项目暂停</span></label></li>
							<!-- 							<li><label><input type="checkbox" name="projectType"
									 value='7'><span>返修/补货完结</span></label></li>		 -->
						</ul>
					</div>
					<!-- 改变 结束-->
				</div>
				<div class="col-sm-2">
					<label class="">细节状态</label> <span></span>
					<!-- 改变 开始 -->
					<div class="dropdown dropdown5">
						<button id="dLabel" type="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<em>全部细节状态</em> <span class="caret"></span>
						</button>
						<div class="div_transparent"></div>
						<ul class="ul4">
							<li class="first-li"><label><input type="checkbox"
									checked="checked" value="-1"><span>全部 细节状态</span></label>
							<li><label><input type="checkbox"
									name="detailStatus" value="0"><span>未选择</span></label></li>
							<li><label><input type="checkbox"
									name="detailStatus" value="1"><span>正常生产中</span></label></li>
							<li><label><input type="checkbox"
									name="detailStatus" value='2'><span>返工重做中</span></label></li>
							<li><label><input type="checkbox"
									name="detailStatus" value='4'><span>悬而未决中</span></label></li>

						</ul>
					</div>
					<!-- 改变 结束 -->
				</div>
				<div class="col-sm-2">
					<label class="">延期</label> <span></span>
					<!-- 改变 开始 -->
					<div class="dropdown dropdown6">
						<button id="dLabel" type="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<em>全部 </em> <span class="caret"> </span>
						</button>
						<div class="div_transparent"></div>
						<ul class="ul5">
							<li class="first-li"><label><input type="checkbox"
									checked="checked" value="-1"><span>全部</span></label></li>
							<li><label><input type="checkbox" name="delayStatus"
									value="0"><span>正常</span></label></li>
							<li><label><input type="checkbox" name="delayStatus"
									value='1'><span>超过合同交期和超过延期后交期（姜工修改的）</span></label></li>
							<!-- 							<li><label><input type="checkbox" name="delayStatus"  -->
							<!-- 									onchange="searchProjectData(1)" value='2'><span>延期后超期</span></label></li> -->
						</ul>
					</div>
					<!-- 改变 结束 -->
				</div>
				<div class="col-sm-2 col-sm-4_2">
					<label>项目阶段</label> <span></span>
					<!-- 改变 开始-->
					<div class="dropdown dropdown2">
						<button id="dLabel" type="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<em>全部阶段</em> <span class="caret"></span>
						</button>
						<div class="div_transparent"></div>
						<ul class="ul2">
							<li class="first-li"><label><input type="checkbox"
									checked="checked" value=""><span>全部阶段</span></label></li>
							<li><label><input type="checkbox"
									name="projectStage" value="3"><span>未更新</span></label></li>
							<li><label><input type="checkbox"
									name="projectStage" value='0'><span>样品</span></label></li>
							<li><label><input type="checkbox"
									name="projectStage" value='1'><span>小批量</span></label></li>
							<li><label><input type="checkbox"
									name="projectStage" value='2'><span>大货</span></label></li>
						</ul>
					</div>
					<!-- 改变 结束-->
				</div>
				<div class="col-sm-2">
					<label>项目等级</label> <span></span>
					<!-- 改变 开始-->
					<div class="dropdown dropdown3">
						<button id="dLabel" type="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<em>全部等级</em> <span class="caret"></span>
						</button>
						<div class="div_transparent"></div>
						<ul class="ul3">
							<li class="first-li"><label><input type="checkbox"
									checked="checked" value=""><span>全部等级</span></label></li>
							<li><label><input type="checkbox"
									name="plantAnalysis" value="0"><span>未定</span></label></li>
							<li><label><input type="checkbox"
									name="plantAnalysis" value='1'><span>A</span></label></li>
							<li><label><input type="checkbox"
									name="plantAnalysis" value='2'><span>B</span></label></li>
							<li><label><input type="checkbox"
									name="plantAnalysis" value='3'><span>C</span></label></li>
						</ul>
					</div>
					<!-- 改变 结束-->
				</div>
			</div>
			<!-- 替换结束 -->

			<div class="" style="height: 40px;">
				<label for="firstname" class="col-sm-2"
					style="margin-top: 5px; font-size: 16px; width: 90px; padding-left: 0; padding-right: 0; margin-left: 20px;">
					<!-- <i
					class="ffhh">*</i> -->关键词搜索
				</label>
				<div class="col-sm-3" style="padding-top: 0; margin-left: -18px;">
					<input class="form-control" style="width: 195px;" type="text"
						id="inputKey" name="inputKey" placeholder="项目号/项目名/姓名">
				</div>
			</div>
		</form>

		<!-- 新增头部开始-->



		<!-- 新增头部结束-->


		<div class="main-table meeting-list-table">
			<span> <!--<input type="text" name="searchProjectNo"
				id="searchProjectNo" placeholder="项目号" />  -->
			</span>
			<div class="btns">
				<input id="searchBut" type="submit" value="搜索"
					class="color-blocks-btn" onclick="searchProjectData(1)" />
				<!-- <input
					type="submit" value="质检报告更新倒序排序" class="color-blocks-btn btn2"
					id="btn2" onclick="qualityReportSelect()" /> -->
				<input type="submit" value="清空所有关键词和条件"
					class="color-blocks-btn btn2" onclick="cleanSelect()" />
				<!-- <input
					 id="searchBut" type="submit" value="今后预计7天出货"
					 class="color-blocks-btn" onclick="expectedShipmentSelect()" /> -->

				<a class="select_blank" target="_blank"
					href="/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}">返回功能选择页</a>
				<input name="" type="submit" value="退出系统" class="color-blocks-btn"
					onclick="exitlogin()" />
			</div>
			<div class="table-wrap" style="margin-top: 0;">
				<input type="hidden" name="reportImg" id="reportImg" value="">
				<input type="hidden" name="projectReportNo" id="projectReportNo"
					value="">
				<div class="add_tc add_tc1 add_tc_zb">
					<p>周报</p>
					<!-- 						<div class="row"> -->
					<!-- 							<label class=" col-sm-2">项目阶段</label>  -->
					<!-- 							<select class="form-control col-sm-6 select_zb" id="selectProjectStage"> -->
					<!-- 								<option value="0">样品</option> -->
					<!-- 								<option value="1">小批量</option> -->
					<!-- 								<option value="2">大货</option> -->
					<!-- 							</select> -->
					<!-- 						</div> -->
					<p>图片上传</p>
					<form id="file_upload_id" onsubmit="return false;" method="post"
						enctype="multipart/form-data">
						<div class="s_c">
							<input type="hidden" name="fileNames" id="fileNames" value="">
							<ul class="imgs clearfix" id="projectReportUrl">
								<li><span class="glyphicon glyphicon-plus"></span><input
									type="file" id="uploadFile" name="files"
									onchange="uploadWeekPicture(this)" multiple></li>
							</ul>
						</div>
					</form>
					<ul class="factory_state pl10 pr10" id="factory_ul">
						<%--  <c:forEach begin="1" end="${project.factoryList.size()}" varStatus="j" step="1">
									<li class="text-left mt5 clearfix"> 
										<span>${project.factoryList.get(j.count-1).factoryName} - 
										${project.factoryList.get(j.count-1).orderState == 1 ? '在做模具' : (project.factoryList.get(j.count-1).orderState == 2 ? '在打样或小批量' : 
										(project.factoryList.get(j.count-1).orderState == 3 ? '在大批量生产' :(project.factoryList.get(j.count-1).orderState == 4 ? '因为工厂原因还未做' : 
										(project.factoryList.get(j.count-1).orderState == 5 ? '因为我司或者客户原因暂停' : (project.factoryList.get(j.count-1).orderState == 6 ? '已经部分交货，正在继续生产' : 
										(project.factoryList.get(j.count-1).orderState == 7 ? '已经部分交货，目前暂停' : '未更新状态'))))))}
										</span>
										<select class="form-control display_inline_block">
											<option value="1">在做模具</option>
											<option value="2">在打样或小批量 </option>
											<option value="3">在大批量生产</option>
											<option value="4">因为工厂原因还未做</option>
											<option value="5">因为我司或者客户原因暂停</option>
											<option value="6">已经部分交货，正在继续生产</option>
											<option value="7">已经部分交货，目前暂停</option>									
										</select>
									</li>
							</c:forEach>						 --%>
					</ul>
					<p class="mt10">汇报内容</p>
					<textarea id="report" name="report" value="" class="form-control"></textarea>
					<div class="btns2">
						<button class="pull-left" onclick="cancelReport(this)">取消</button>
						<button class="pull-right" onclick="addProjectReport()">保存</button>
					</div>
				</div>

				<div class="add_tc add__tc_fk z-tb">
					<input type="hidden" name="projectNo"> <input type="hidden"
						name="index">
					<div class="clearfix">
						<div class="pull-left">
							<h1>添加/修改里程碑</h1>
							<div class="wrap_milestones">
								<div class="milestone">
									<div class="row">
										<label class="mr10 z-t z-t_num">里程碑1</label> <input
											type="text" class="form-control content"
											placeholder="请简要输入里程碑内容">
									</div>
									<div class="row">
										<label class="mr10 z-t z-t_num">里程碑1日期</label>
										<div class="input-group date form_date col-sm-6 content"
											data-date="" data-date-format="yyyy-mm-dd">
											<input class="form-control brt brt_7" size="16" type="text"
												style="width: 157px;" placeholder="选择日期" readonly> <span
												class="input-group-addon"><span
												class="glyphicon glyphicon-calendar"></span></span>
										</div>
									</div>
									<button class="btn btn-primary del_btn"
										onclick="del_milestone(this)">删除</button>
								</div>
							</div>
							<button class="btn btn-primary add_line">增加里程碑（最多添加10个，最少两个）</button>
						</div>
					</div>
					<div class="btns2">
						<button class="pull-left" onclick="closeMilestones(this)">关闭</button>
						<button class="pull-right" id="save_feedback"
							onclick="addMilestones()">提交</button>
					</div>
				</div>

				<div class="add_tc add_tc2 add__tc_fk">
					<div class="clearfix">
						<div class="pull-left">
							<p class="mb10 pl0">客户实际状态反馈：</p>
							<div class="row">
								<label class="col-sm-4 mr10">细节状态</label> <select
									class="form-control col-sm-5" id="selectProjectStatus">
									<option value="1">正常生产中</option>
									<option value="2">返工重做中</option>
									<option value="4">悬而未决中</option>
								</select>
							</div>
							<textarea id="feedback" name="feedback"></textarea>
							<input type="hidden" name="projectNoFack" id="projectNoFack"
								value="">
							<!-- <div class="btns2">
						<button class="pull-left" onclick="cancelAdd(this)">取消</button>
						<button class="pull-right" id="save_feedback" onclick="addFeedback()">保存</button>
					</div> -->
						</div>
						<div class="pull-right">
							<p class="mb10">计划分批交期设置：</p>
							<div class="addDate clearfix">
								<div class="form-group pull-left date_div">

									<div>
										<div class="input-group date form_date col-sm-6" data-date=""
											data-date-format="yyyy-mm-dd">
											<input class="form-control brt brt_7" size="16" type="text"
												id="new_date" place="选择日期" field="报价截止日期" placeholder="选择日期"
												readonly requiredate> <span
												class="input-group-addon"><span
												class="glyphicon glyphicon-calendar"></span></span>
										</div>
										<span></span>
									</div>
								</div>
								<button class="btn btn-default add" id="add_date">增加</button>
							</div>
							<ul id="date_log">

							</ul>
						</div>
					</div>
					<p class="red mt10">备注：如果选择了"返工重做中"，会将项目的状态改成进行中.</p>
					<div class="btns2">
						<button class="pull-left" onclick="cancelAdd(this)">取消</button>
						<button class="pull-right" id="save_feedback"
							onclick="addFeedback()">保存</button>
					</div>
				</div>
				<div class="add_tc add_tc3 add__tc_fk">

					<div class="row">
						<label class="col-sm-6" style="text-align: left;">项目状态修改为：</label>
						<select class="form-control col-sm-5" id="editProjectStatus">

						</select>
					</div>
					<div class="form-group add_date">
						<label for="" class="col-sm-2 control-label "><span></span>完结日期：</label>
						<div class="col-sm-4 ">
							<div class="input-group date form_date col-sm-6" data-date=""
								data-date-format="yyyy-mm-dd">
								<input id="select_date" class="form-control brt brt_7" size="16"
									type="text" value="" place="选择日期" field="报价截止日期"
									placeholder="选择日期" readonly requiredate><span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<span></span>
						</div>
					</div>
					<p>
						<b>业务提醒：</b><br /> 项目暂停 的 原因只能是： 客户原因 或者 公司层面的要求。 不能 是 因为工厂生产问题
						买料问题等等而暂停
					</p>
					<textarea id="reason" name="reason" placeholder="理由"></textarea>
					<div class="btns2">
						<button class="pull-left" onclick="add_tc3Hide(this)">关闭</button>
						<button class="pull-right">确定</button>
					</div>
				</div>

				<div class="add_div_tc5"></div>
				<div class="table-head">

					<form id="form2" class="roleform form-horizontal" method="post"
						action="/project/showListNew?pageNumber=${pageNumber}" role="form"
						onsubmit="return false;">
						<div class="table-head-wrap">

							<input type="hidden" value="${userName}" name="userName"
								id="userName"> <input type="hidden" value="${userId}"
								name="userId" id="userId"> <input type="hidden"
								value="${roleNo}" name="roleNo" id="roleNo"> <input
								type="hidden" name="projectTypeS" id="projectTypeS"> <input
								type="hidden" name="projectStageS" id="projectStageS"> <input
								type="hidden" name="plantAnalysisS" id="plantAnalysisS">
							<input type="hidden" name="screenType" id="screenType"> <input
								type="hidden" name="detailStatusS" id="detailStatusS"> <input
								type="hidden" name="delayStatusS" id="delayStatusS"> <input
								type="hidden" id="pageNumber" name="pageNumber"> <input
								type="hidden" id="countPage" name="countPage"
								value="${pageNumber}"> <input type="hidden"
								id="pageSize" name="pageSize" value="${pageSize}"> <input
								type="hidden" id="totalPage" name="totalPage" value="${lastNum}">
							<input type="hidden" name="qualityReportSelect"
								id="qualityReportSelect" value="${project.qualityReportSelect}" />
							<input type="hidden" id="expectedShipmentSelect"
								name="expectedShipmentSelect"
								value="${project.expectedShipmentSelect}"> <input
								type="hidden" name="picProjectNo" id="picProjectNo" value="">
							<input type="hidden" name="picName" id="picName" value="">
							<input type="hidden" name="qualityReportId" id="qualityReportId"
								value=""> <input type="hidden" name="inputKey"
								id="inputKey_par" value=""> <input type="hidden"
								name="purchase_name" id="purchase_name_par" value=""> <input
								type="hidden" name="documentary_name" id="documentary_name_par"
								value=""> <input type="hidden" name="quality_name"
								id="quality_name_par" value=""> <input type="hidden"
								name="technician" id="technician_par" value=""> <input
								type="hidden" name="importantSelect" id="importantSelect"
								value="${project.importantSelect}">
							<table class="table table-bordered table1 tabel_head1">
								<tbody>
									<tr>
										<td class="th1"><span class="s1">序号</span></td>
										<td class="th2"><span class="s2">项目号</span></td>
										<td class="th4"><span class="s4">项目/客户等级</span></td>
										<td class="th4"><span class="s4">项目金额<br />万/美元
										</span></td>
										<td class="th5"><span class="s5">产品图</span></td>
										<td class="th6"><span class="s6">日期</span></td>
										<td class="th7"><span class="s7">跟单类</span></td>
										<td class="th8"><span class="s8">采购类</span></td>
										<td class="th11"><span class="s8">采购计划的里程碑</span></td>
										<td class="th9"><span class="s9">质检类</span></td>
										<td class="th10"><span class="s10">问题汇总</span></td>
									</tr>
								</tbody>
							</table>
							<c:forEach var="project" items="${list}" varStatus="i">
								<table class="table table-bordered table1">
									<tbody id="table1_tbody">

										<tr>
											<td class="th1"
												<c:if test="${project.status != null && project.status != ''}">style="border-left-color:red;border-top-color:red;border-bottom-color:red;"</c:if>><div
													class="s1">${i.index+1}</div></td>
											<td class="th2"
												<c:if test="${project.status != null && project.status != ''}">style="border-top-color:red;border-bottom-color:red;"</c:if>>
												<div class="s2">
													<a target="_blank"
														href="${ctx}/project/showDetails?projectNo=${project.projectNo}&roleNo=${roleNo}&userId=${userId}&userName=${userName}&pageNumber=${pageNumber}">${project.projectNo}</a>

													<c:if test="${roleNo == 5}">
														
															<a class="add_span_state btn"
																href='/project/queryDetail?projectNo=${project.projectNo}'
																target="_blank">项目状态修改</a>
														
													</c:if>
													<c:if
														test="${userName eq 'ninazhao' || roleNo == 9 || roleNo==100}">
														<a class="add_span_state btn"
															href='/project/queryDetail?projectNo=${project.projectNo}'
															target="_blank">项目状态修改</a>
													</c:if>
													<span>${project.projectName}</span> <span>${project.customerName == null?'':project.customerName}</span>
													<c:if test="${roleNo != 5&&roleNo != 100 }">
														<c:if test="${project.projectStatus == 0}">新立项</c:if>
														<c:if test="${project.projectStatus == 1}">进行中</c:if>
														<c:if test="${project.projectStatus == 2}">大货完结</c:if>
														<c:if test="${project.projectStatus == 4}">项目暂停</c:if>
														<c:if test="${project.projectStatus == 5}">项目取消</c:if>
														<c:if test="${project.projectStatus == 6}">样品完结</c:if>
													</c:if>
													<c:if test="${ roleNo == 5||roleNo==100}">
														<%-- <button class="add_span_state btn" onclick="selectByProject('${project.projectNo}','${project.projectStatus}')"> --%>
														<c:if test="${project.projectStatus == 0}">
															<a style="color: black;" href='##'
																onclick="selectByProject('${project.projectNo}','${project.projectStatus}')">新立项</a>
														</c:if>
														<c:if test="${project.projectStatus == 1}">
															<a style="color: black;" href='##'
																onclick="selectByProject('${project.projectNo}','${project.projectStatus}')">进行中</a>
														</c:if>
														<c:if test="${project.projectStatus == 2}">
															<a style="color: black;" href='##'
																onclick="selectByProject('${project.projectNo}','${project.projectStatus}')">大货完结</a>
														</c:if>
														<c:if test="${project.projectStatus == 4}">
															<a style="color: black;" href='##'
																onclick="selectByProject('${project.projectNo}','${project.projectStatus}')">项目暂停</a>
														</c:if>
														<c:if test="${project.projectStatus == 5}">
															<a style="color: black;" href='##'
																onclick="selectByProject('${project.projectNo}','${project.projectStatus}')">项目取消</a>
														</c:if>
														<c:if test="${project.projectStatus == 6}">
															<a style="color: black;" href='##'
																onclick="selectByProject('${project.projectNo}','${project.projectStatus}')">样品完结</a>
														</c:if>
														<!-- </button> -->
													</c:if>
													<c:if test="${project.allTask > 0}">
														<a target="_blank"
															href="${ctx}/projectTask/projectTaskList?projectNo=${project.projectNo}&roleNo=${roleNo}&purchaseNameId=&userId=${userId}&userName=${userName}&taskStatus=-1&sendOrReceive=0&pageNumber=1"><span>任务完成(${project.finishTask}/${project.allTask})</span>
															<%-- <c:if test="${project.puaseTask > 0}">
									<span>任务暂停(${project.puaseTask}/${project.allTask})</span>
									</c:if> --%> </a>
													</c:if>
													<c:if
														test="${userName eq 'qcdirector' || userName eq 'ninazhao' }">
														<a target="_blank"
															href="${ctx}/projectTask/projectTaskList?projectNo=${project.projectNo}&roleNo=${roleNo}&purchaseNameId=&userId=${userId}&userName=${userName}&taskStatus=-1&sendOrReceive=0&pageNumber=1&tasktype=12">质量任务(${project.qualityFinishTask}/${project.qualityTask})</a>
														<button type="button"
															class="add_entry_btn color-blocks-btn"
															onclick="qualityTaskInput('${project.projectNo}',${userId},'${userName}',${roleNo});">录入</button>
													</c:if>
													<button class="table_toggle">扩展信息</button>
												</div>
											</td>
											<td class="th4"
												<c:if test="${project.status != null && project.status != ''}">style="border-top-color:red;border-bottom-color:red;"</c:if>><div
													class="s4">${project.plantAnalysisView != null ? project.plantAnalysisView: "暂无"}/${project.customerGrade == 0 ? '暂无' :(project.customerGrade == 1 ? 'A' : (project.customerGrade == 2 ? 'B' : project.customerGrade == 3 ? 'C' : '暂无'))}</div></td>
											<%-- 							<td class="th3" <c:if test="${project.status != null && project.status != ''}">style="border-top-color:red;border-bottom-color:red;"</c:if>><div class="s3">${project.customerGrade == 0 ? '暂无' :(project.customerGrade == 1 ? 'A' : (project.customerGrade == 2 ? 'B' : project.customerGrade == 3 ? 'C' : '暂无'))}</div></td> --%>
											<td class="th4"
												<c:if test="${project.status != null && project.status != ''}">style="border-top-color:red;border-bottom-color:red;"</c:if>><div
													class="s4">
													<span>${project.projectAmount}</span>
												</div></td>
											<td class="th5"
												<c:if test="${project.status != null && project.status != ''}">style="border-top-color:red;border-bottom-color:red;"</c:if>>
												<div class="s5" style="height: 100%;">
												
													<div class="imgs">
														<c:if test="${project.productImg != null}">
															<img
																src='http://117.144.21.74:10010/product_img/${project.productImg}'
																onload="autoSize(this,230,170)">
														</c:if>
														<span class="add_img_span"></span>
													</div>
												</div>
											</td>
											<td class="th6"
												<c:if test="${project.status != null && project.status != ''}">style="border-top-color:red;border-bottom-color:red;"</c:if>>
												<div class="s6">
													<c:if test="${project.actualStartDate != null }">
														<span>合同签订</span>
														<em><fmt:formatDate
																value="${project.actualStartDate}" pattern="yyyy-MM-dd" /></em>
													</c:if>
													<c:if test="${project.moneyDate != null }">
														<span>首次到账</span>
														<em><fmt:formatDate value="${project.moneyDate}"
																pattern="yyyy-MM-dd" /></em>
													</c:if>
													<c:if test="${project.sampleScheduledDate != null }">
								                                         样品交期：
								              <em><fmt:formatDate value="${project.sampleScheduledDate}"
																pattern="yyyy-MM-dd" /></em>
													</c:if>
													<c:if test="${project.deliveryDate != null }">
														<span>大货交期：</span>
														<em><fmt:formatDate value="${project.deliveryDate}"
																pattern="yyyy-MM-dd" /></em>
													</c:if>
													<%-- <c:if
														test="${project.scheduleList != null && project.scheduleList.size()>0}">
														<span>分批交货期：</span>
														<c:forEach begin="1" end="${project.scheduleList.size()}"
															varStatus="j" step="1">
															<c:if
																test="${project.scheduleList.get(j.count-1).predictDate != null}">
																<em><fmt:formatDate
																		value="${project.scheduleList.get(j.count-1).predictDate}"
																		pattern="yyyy-MM-dd" /></em>
															</c:if>
														</c:forEach>
													</c:if> --%>
													<%-- <c:if test="${project.exportDate != null}">
														<span>报关日期：</span>
														<em>${project.exportDate}</em>
													</c:if> --%>
													
													<c:if test="${project.delayDay != 0}">
														<span style="color: green;">客户延期天数:</span>
														<em style="color: green;">${project.delayDay}</em>
													</c:if>
													<c:if test="${project.lateDeliveryDate != null}">
														<span style="color: green;">公司延期后的交期:</span>
														<em style="color: green;"><fmt:formatDate value="${project.lateDeliveryDate}"
																pattern="yyyy-MM-dd" /></em>
													</c:if>
													<%-- <c:if test="${project.newPredictDate != null}">
														<span style="color: green;">我司预计交货日:</span>
														<em style="color: green;"><fmt:formatDate
																value="${project.newPredictDate}" pattern="yyyy-MM-dd" /></em>
													</c:if> --%>
													<%-- <c:if test="${project.urgentDeliveryDate != null}">
														<span style="color: green;">客户紧急交期:</span>
														<em style="color: green;"><fmt:formatDate
																value="${project.urgentDeliveryDate}"
																pattern="yyyy-MM-dd" /></em>
													</c:if> --%>
                                                    <c:if test="${project.sampleFinishTime!=null }">
                                                    <span style="color: green;">样品实际完成日期:</span>
														<em style="color: green;"><fmt:formatDate
																value="${project.sampleFinishTime}"
																pattern="yyyy-MM-dd" /></em>
                                                    </c:if>
                                                    <c:if test="${project.finishTime!=null }">
                                                    <span style="color: green;">大货实际完成日期:</span>
														<em style="color: green;"><fmt:formatDate
																value="${project.finishTime}"
																pattern="yyyy-MM-dd" /></em>
                                                    </c:if>
												</div>
											</td>
											<td class="th7"
												<c:if test="${project.status != null && project.status != ''}">style="border-top-color:red;border-bottom-color:red;"</c:if>>
												<div class="s7">
													<c:if
														test="${project.feedback != null && project.feedback != ''}">
														<span>${project.detailStr}</span>
														<span>${project.feedback.feedback == null ? '' :project.feedback.feedback}</span>
														<p>
															<fmt:formatDate value="${project.feedback.createDate}"
																pattern="yyyy-MM-dd" />
														</p>
														<p>${project.feedback.operator == null ? '' : project.feedback.operator}</p>
													</c:if>
													<%-- 								<c:if test="${project.projectStatus != 2}"> --%>
													<c:choose>
														<c:when test="${project.feedbackList.size() > 0}">
															<button class="btn btn_update new_add1"
																onclick="addFeeBackHtml('${project.projectNo}',${i.index})">已更新</button>
														</c:when>
														<c:otherwise>
															<button class="btn btn_update new_add1"
																style="background: #ff9800;"
																onclick="addFeeBackHtml('${project.projectNo}',${i.index})">未更新</button>
														</c:otherwise>
													</c:choose>
													<%-- 							    </c:if> --%>
												</div>
											</td>
											<td class="th8"
												<c:if test="${project.status != null && project.status != ''}">style="border-top-color:red;border-bottom-color:red;"</c:if>>
												<div class="s8">
													<c:if
														test="${project.projectReportList != null && project.projectReportList.size()>0}">
														<span> <c:if
																test="${project.projectReportList.get(0).projectStage == 0}">样品</c:if>
															<c:if
																test="${project.projectReportList.get(0).projectStage == 1}">小批量</c:if>
															<c:if
																test="${project.projectReportList.get(0).projectStage == 2}">大货</c:if>
														</span>
														<p>${project.projectReportList.get(0).report}</p>
														<p>${project.projectReportList.get(0).reportName == null ? '' : project.projectReportList.get(0).reportName}</p>
														<p>
															<fmt:formatDate
																value="${project.projectReportList.get(0).createDate}"
																pattern="yyyy-MM-dd" />
														</p>
														<button class="btn btn_update new_add2"
															onclick="addProjectReportHtml('${project.projectNo}')">已更新</button>
													</c:if>
													<c:if
														test="${project.projectReportList == null || project.projectReportList.size() == 0}">
														<span> <c:if test="${project.projectStage == 0}">样品</c:if>
															<c:if test="${project.projectStage == 1}">小批量</c:if> <c:if
																test="${project.projectStage == 2}">大货</c:if>
														</span>
														<p>${project.projectReport == null ? '' : project.projectReport.report}</p>
														<p>${project.purchaseName == null ? '无采购' : project.purchaseName}</p>
														<p>
															<fmt:formatDate value="${project.createDate}"
																pattern="yyyy-MM-dd" />
														</p>
														<button class="btn btn_update new_add2"
															style="background: #ff9800;"
															onclick="addProjectReportHtml('${project.projectNo}')">未更新</button>
													</c:if>
												</div>
											</td>
											<td class="th11"
												<c:if test="${project.status != null && project.status != ''}">style="border-top-color:red;border-bottom-color:red;"</c:if>>
												<c:if test="${project.milestones.size()>0}">
													<c:set var="now">
														<fmt:formatDate value="<%=new Date()%>"
															pattern="yyyy-MM-dd" />
													</c:set>
													<c:forEach begin="1" end="${project.milestones.size()}"
														varStatus="j" step="1">
														<c:set var="compareDate">
															<fmt:formatDate
																value="${project.milestones.get(j.count-1).milestoneDate}"
																pattern="yyyy-MM-dd" />
														</c:set>
														<c:if test="${j.count == 1}">
															<c:if test="${now < compareDate || now == compareDate}">
																<span style="color: #27cb38;"><fmt:formatDate
																		value="${project.milestones.get(j.count-1).milestoneDate}"
																		pattern="yyyy-MM-dd" /></span>
																<p style="color: #27cb38;">${project.milestones.get(j.count-1).milestoneName}</p>
															</c:if>
															<c:if test="${now > compareDate}">
																<span><fmt:formatDate
																		value="${project.milestones.get(j.count-1).milestoneDate}"
																		pattern="yyyy-MM-dd" /></span>
																<p>${project.milestones.get(j.count-1).milestoneName}</p>
															</c:if>
														</c:if>
														<c:if
															test="${j.count > 1 && j.count < project.milestones.size()}">
															<c:set var="compareDate1">
																<fmt:formatDate
																	value="${project.milestones.get(j.count-2).milestoneDate}"
																	pattern="yyyy-MM-dd" />
															</c:set>
															<c:choose>
																<c:when
																	test="${now > compareDate1 && now <= compareDate}">
																	<span style="color: #27cb38;"><fmt:formatDate
																			value="${project.milestones.get(j.count-1).milestoneDate}"
																			pattern="yyyy-MM-dd" /></span>
																	<p style="color: #27cb38;">${project.milestones.get(j.count-1).milestoneName}</p>
																</c:when>
																<c:otherwise>
																	<span><fmt:formatDate
																			value="${project.milestones.get(j.count-1).milestoneDate}"
																			pattern="yyyy-MM-dd" /></span>
																	<p>${project.milestones.get(j.count-1).milestoneName}</p>
																</c:otherwise>
															</c:choose>
														</c:if>
														<c:if
															test="${j.count > 1 && j.count == project.milestones.size()}">
															<c:set var="compareDate2">
																<fmt:formatDate
																	value="${project.milestones.get(j.count-2).milestoneDate}"
																	pattern="yyyy-MM-dd" />
															</c:set>
															<c:choose>
																<c:when
																	test="${now > compareDate2 && now <= compareDate}">
																	<span style="color: #27cb38;"><fmt:formatDate
																			value="${project.milestones.get(j.count-1).milestoneDate}"
																			pattern="yyyy-MM-dd" /></span>
																	<p style="color: #27cb38;">${project.milestones.get(j.count-1).milestoneName}</p>
																</c:when>
																<c:otherwise>
																	<span><fmt:formatDate
																			value="${project.milestones.get(j.count-1).milestoneDate}"
																			pattern="yyyy-MM-dd" /></span>
																	<p>${project.milestones.get(j.count-1).milestoneName}</p>
																</c:otherwise>
															</c:choose>
														</c:if>

													</c:forEach>
													<a
														onclick="openMilestone('${project.projectNo}','${i.index}')">修改里程碑</a>
												</c:if> <c:if
													test="${project.milestones == null || project.milestones.size() == 0}">
													<c:choose>
														<c:when
															test="${project.plantAnalysis == 1 || project.plantAnalysis == 2}">
															<p>
																<a
																	onclick="openMilestone('${project.projectNo}','${i.index}')">请点击上传</a>
																<!-- ，或请使用钉钉—审批—项目里程碑新建/更新来补充本A/B级项目里程碑 -->
															</p>
														</c:when>
														<c:otherwise>
															<p>非A\B级项目不需要</p>
														</c:otherwise>
													</c:choose>
												</c:if>
											</td>
											<td class="th9"
												<c:if test="${project.status != null && project.status != ''}">style="border-top-color:red;border-bottom-color:red;"</c:if>>
												<div class="s9">
													<c:forEach begin="1" end="${project.qrList.size()}"
														varStatus="j" step="1">
														<c:if test="${j.index < 3}">
															<p>
																<a target="_blank"
																	href="https://www.kuaizhizao.cn/quality/shareQuality?id=${project.qrList.get(j.count-1).id}">
																	<c:if test="${project.qrList.get(j.count-1).type == 0}">样品检验</c:if>
																	<c:if test="${project.qrList.get(j.count-1).type == 2}">中期检验</c:if>
																	<c:if test="${project.qrList.get(j.count-1).type == 3}">终期检验</c:if>
																	<c:if
																		test="${project.qrList.get(j.count-1).status == 0}">没问题</c:if>
																	<c:if
																		test="${project.qrList.get(j.count-1).status == 1}">有问题,但已经处理</c:if>
																	<c:if
																		test="${project.qrList.get(j.count-1).status == 2}">有问题,需要采购解决</c:if>
																</a> [${project.qrList.get(j.count-1).user}/
																<fmt:formatDate
																	value="${project.qrList.get(j.count-1).createtime}"
																	pattern="yyyy-MM-dd" />
																]
																<button type="button" style="padding: 0;"
																	class="btn_modify"
																	onclick="openQualityReport('${project.qrList.get(j.count-1).id}','${project.qrList.get(j.count-1).projectNo}')">编辑</button>
															</p>
														</c:if>
													</c:forEach>
													<c:if
														test="${project.erpReports != null && project.erpReports.size() > 0}">
														<c:forEach begin="1" end="${project.erpReports.size()}"
															varStatus="j" step="1">
															<c:if test="${j.index < 3}">
																<p>
																	<a
																		href="http://117.144.21.74:33168/upload/po/upload/JIANYANBAOGAO/${project.erpReports.get(j.count-1).picUrl}">
																		${project.erpReports.get(j.count-1).explainCause} </a>
																	[${project.erpReports.get(j.count-1).user}/
																	<fmt:formatDate
																		value="${project.erpReports.get(j.count-1).createtime}"
																		pattern="yyyy-MM-dd" />
																	]
																</p>
															</c:if>
														</c:forEach>
													</c:if>

													<p>${project.zhijian1 == null ? '' : project.zhijian1}/${project.zhijian2 == null ? '' : project.zhijian2}</p>
												</div>
											</td>
											<td class="th10"
												<c:if test="${project.status != null && project.status != ''}">style="border-right-color:red;border-top-color:red;border-bottom-color:red;"</c:if>>
												<div class="s10">
													<c:choose>
														<c:when
															test="${project.inspectionPlanList != null && project.inspectionPlanList.size() > 0}">
															<%-- 							         <a href="http://117.144.21.74:33168/upload/po/upload/JIANYANJIHUAZJ/${project.inspectionPlanList.get(0).reportName}">检验计划 </a> --%>
														</c:when>
														<c:otherwise>
															<c:if
																test="${project.plantAnalysis == 1 || project.plantAnalysis == 2 }">
																<a href="###">检验计划 <i class="red">未做</i></a>
															</c:if>
														</c:otherwise>
													</c:choose>
													<c:if test="${task!= null}">
														<c:if test="${task.verification==0 }"><a href="https://www.kuaizhizao.cn/complaint/queryComplaint?id=434" target="_blank"> <i class="red">#投诉${task.complaintId },未验证</i></a></c:if>
													</c:if>
													
													
													<c:if test="${project.actualStartDate == null}">
														<a href="###">是否上传合同 <i class="red">未上传</i></a>
													</c:if>
													<c:if
														test="${project.isProcess != true && (project.plantAnalysis == 1 || project.plantAnalysis == 2)}">
														<a href="###">过程检 <i class="red">未做</i></a>
													</c:if>
													<c:if test="${project.isStart != true}">
														<a target="_blank"
															href="${ctx}/meetingRecord/inputMeetingRecord?roleNo=${roleNo}&userId=${userId}&userName=${userName}&projectNo=${project.projectNo}">启动会
															<i class="red">未开</i>
														</a>
													</c:if>
													<c:if
														test="${project.isSample != true && project.sampleScheduledDate != null}">
														<a target="_blank"
															href="${ctx}/meetingRecord/inputMeetingRecord?roleNo=${roleNo}&userId=${userId}&userName=${userName}&projectNo=${project.projectNo}">样品验货会
															<i class="red">未开</i>
														</a>
													</c:if>
													<c:if
														test="${project.isProduct != true &&project.deliveryDate != null}">
														<a target="_blank"
															href="${ctx}/meetingRecord/inputMeetingRecord?roleNo=${roleNo}&userId=${userId}&userName=${userName}&projectNo=${project.projectNo}">大货验货会
															<i class="red">未开</i>
														</a>
													</c:if>
													<!-- 								<a href="###">客户投诉 <i class="red">未做</i></a> -->
													<%--    <c:if test="${project.plantAnalysis == 1 || project.plantAnalysis == 2}">
                                    <c:if test="${project.projectReportList == null || project.projectReportList.size() == 0}">
								    <a href="###"><i class="red">A/B 级项目未更新周报图</i></a>
								    </c:if>
								</c:if> --%>
													<c:choose>
														<c:when
															test="${project.status != null && project.status != ''}">
															<i class="red">${project.status}${project.delayDays}
																天</i>
														</c:when>
														<c:otherwise>
															<i>无延期</i>
														</c:otherwise>
													</c:choose>
													<button class="btn_modify"
														onclick="edit_delay('${userName}','${project.projectNo}','${project.delayDay}',
						         '<fmt:formatDate value="${project.dateDelivery}" pattern="yyyy-MM-dd"/>','${project.interpretationDocument }')">修改</button>
													<%-- <c:if
														test="${project.deliveryList != null && project.deliveryList.size() > 0}">
								              交期已修改${project.deliveryList.size()}次
								 </c:if> --%>
													<c:if
														test="${project.plantAnalysis == 1 || project.plantAnalysis == 2 || (project.status != null && project.status != '') || project.complaintList.size()>0  }">
														<c:if
															test="${fn:contains(project.projectNo,'-') == false && project.planList != null && project.planList.size() == 0}">
															<a
																href="http://117.144.21.74:33168/factory/ProductionPlan.aspx?id=${project.projectNo}"
																target="_blank"> <i class="red">无项目生产计划( <c:if
																		test="${project.plantAnalysis == 1 || project.plantAnalysis == 2}">AB级项目</c:if>
																	<c:if
																		test="${project.status != null && project.status != ''}">/有延期</c:if>
																	<c:if test="${project.complaintList.size()>0}">/有延期</c:if>
																	)
															</i>
															</a>
														</c:if>
													</c:if>
												</div> <c:if test="${project.isNoteProcess == true}">
													<span class="red table3_span">提醒：A/B级别项目交期已过一半，还没有过程检</span>
												</c:if> <c:if test="${project.unFinshedTask > 0}">
													<span class="red">${project.unFinshedTask}个投诉产生的任务未处理</span>
												</c:if> <c:if
													test="${(project.plantAnalysis == 1 || project.plantAnalysis == 2) && (project.importantTaskTotal != null && project.importantTaskFinish != null && project.importantTaskTotal > project.importantTaskFinish)}">
													<span class="red">15天重要项目进度评审会:
														(${project.importantTaskFinish == null ? '0' : project.importantTaskFinish}/${project.importantTaskTotal ==null?'0':project.importantTaskTotal})</span>
												</c:if> <!-- 查看工厂是否上传视频 --> <c:if test="${importantSelect eq '7'}">
													<c:forEach begin="1" end="${project.factoryList.size()}"
														varStatus="j" step="1">
														<div class="s10">
															<span style="font-size: 15px;"> <c:if
																	test="${j.index == project.factoryList.size()}">A合同(${project.factoryList.get(j.count-1).factoryName})</c:if>
																<c:if
																	test="${project.factoryList.size()>1 && j.index == project.factoryList.size()-1}">B合同(${project.factoryList.get(j.count-1).factoryName})</c:if>
																<c:if
																	test="${project.factoryList.size()>2 && j.index == project.factoryList.size()-2}">C合同(${project.factoryList.get(j.count-1).factoryName})</c:if>
																<c:if
																	test="${project.factoryList.size()>3 && j.index == project.factoryList.size()-3}">D合同(${project.factoryList.get(j.count-1).factoryName})</c:if>
																<c:if
																	test="${project.factoryList.size()>4 && j.index == project.factoryList.size()-4}">E合同(${project.factoryList.get(j.count-1).factoryName})</c:if>
																<c:if
																	test="${project.factoryList.size()>5 && j.index == project.factoryList.size()-5}">F合同(${project.factoryList.get(j.count-1).factoryName})</c:if>
																<c:if
																	test="${project.factoryList.size()>6 && j.index == project.factoryList.size()-6}">G合同(${project.factoryList.get(j.count-1).factoryName})</c:if>
																<c:if
																	test="${project.factoryList.size()>7 && j.index == project.factoryList.size()-7}">H合同(${project.factoryList.get(j.count-1).factoryName})</c:if>
																<c:if
																	test="${project.factoryList.size()>8 && j.index == project.factoryList.size()-8}">I合同(${project.factoryList.get(j.count-1).factoryName})</c:if>
																<c:if
																	test="${project.factoryList.size()>9 && j.index == project.factoryList.size()-9}">J合同(${project.factoryList.get(j.count-1).factoryName})</c:if>
																<c:if
																	test="${project.factoryList.size()>10 && j.index == project.factoryList.size()-10}">K合同(${project.factoryList.get(j.count-1).factoryName})</c:if>
																<c:if
																	test="${project.factoryList.size()>11 && j.index == project.factoryList.size()-11}">L合同(${project.factoryList.get(j.count-1).factoryName})</c:if>
															</span>
															<%--                                         <span style="font-size: 14px;">上传日期：<fmt:formatDate value="${project.factoryList.get(j.count-1).contractDate}" pattern="yyyy/MM/dd"/></span>				    --%>
															<c:if
																test="${project.factoryList.get(j.count-1).factoryId != null && project.factoryList.get(j.count-1).factoryId != ''}">
																<div class="copy">
																	<button class="btn bgcolor_ff0 display_block"
																		type="button"
																		onclick="copyBtn(this,'${project.projectNo}','${project.factoryList.get(j.count-1).factoryId}')">复制上传链接</button>
																	<textarea style="opacity: 0; width: 1px; height: 1px;"
																		readonly></textarea>
																</div>
															</c:if>
															<span style="font-size: 15px; color: red;"><c:if
																	test="${project.factoryList.get(j.count-1).isUploadVideo != true}">缺开工视频</c:if></span>
															<span style="font-size: 15px; color: red;"><c:if
																	test="${project.factoryList.get(j.count-1).isUploadMaterial != true}">缺材质报告</c:if></span>
														</div>

													</c:forEach>
												</c:if>
											</td>
											<input type="hidden" name="projectName" class="projectName"
												value="${project.projectName}" />
											<input type="hidden" name="projectNo" class="projectNo"
												value="${project.projectNo}" />
											<input type="hidden" name="plantAnalysisView"
												class="plantAnalysisView"
												value="${project.plantAnalysisView}" />
											<c:choose>
												<c:when
													test="${project.projectReportList != null && project.projectReportList.size() > 0}">
													<input type="hidden" name="report" class="report"
														value="${project.projectReportList.get(0).report}">
													<input type="hidden" name="reportUrl" class="reportUrl"
														value="${project.projectReportList.get(0).picUrl}">
													<input type="hidden" name="reportName" class="reportName"
														value="${project.projectReportList.get(0).reportName}">
													<input type="hidden" name="createDate" class="createDate"
														value="${project.projectReportList.get(0).createDate}">
												</c:when>
												<c:otherwise>
													<input type="hidden" name="report" class="report"
														value="${project.projectReport.report}">
													<input type="hidden" name="reportUrl" class="reportUrl"
														value="${project.projectReport.picUrl}">
													<input type="hidden" name="reportName" class="reportName"
														value="${project.projectReport.reportName}">
													<input type="hidden" name="createDate" class="createDate"
														value="${project.projectReport.createDate}">
												</c:otherwise>
											</c:choose>
										</tr>
									</tbody>
								</table>

								<div class="next_div">
									<div class="tan_comment">
										<h4>项目评论</h4>
										<textarea class="form-control"></textarea>
										<div class="btns">
											<button class="btn pull-left">关闭</button>
											<button class="btn pull-right"
												onclick="add_comment(this,'${project.projectNo}')">提交</button>
										</div>
									</div>
									<table class="table table-bordered table2">
										<tbody>
											<tr>
												<td>状态变化记录：</td>
												<td colspan="3"><c:if
														test="${project.projectPauses != null && project.projectPauses.size() > 0}">
									    项目暂停：
										<c:forEach begin="1" end="${project.projectPauses.size()}"
															varStatus="j" step="1">
															<span
																title="${project.projectPauses.get(j.count-1).pauseReason}">${project.projectPauses.get(j.count-1).pauseReason}
																<fmt:formatDate
																	value="${project.projectPauses.get(j.count-1).createDate}"
																	pattern="yyyy-MM-dd" />
															</span>
														</c:forEach>
									  项目启动：
									    <c:forEach begin="1" end="${project.projectPauses.size()}"
															varStatus="j" step="1">
															<span
																title="${project.projectPauses.get(j.count-1).startReason}">${project.projectPauses.get(j.count-1).startReason}
																<fmt:formatDate
																	value="${project.projectPauses.get(j.count-1).startDate}"
																	pattern="yyyy-MM-dd" />
															</span>
														</c:forEach>
													</c:if> <c:if test="${project.projectStatus == 5}">
									   项目取消：
									  <span
															title="${project.pauseReason == null ? '' : project.pauseReason}">${project.pauseReason == null ? '' : project.pauseReason}
															<fmt:formatDate value="${project.finishTime}"
																pattern="yyyy-MM-dd" />
														</span>
													</c:if></td>
											</tr>
											<tr>
												<td><span>样品交期:</span> <em><fmt:formatDate
															value="${project.sampleScheduledDate}"
															pattern="yyyy-MM-dd" /></em></td>
												<td><span>样品实际完成:</span> <em><fmt:formatDate
															value="${project.sampleFinishTime}" pattern="yyyy-MM-dd" /></em>
												</td>
												<td><span>大货交期:</span> <em><fmt:formatDate
															value="${project.deliveryDate}" pattern="yyyy-MM-dd" /></em></td>
												<td><span>大货实际完成日期:</span> <em><fmt:formatDate
															value="${project.finishTime}" pattern="yyyy-MM-dd" /></em></td>
											</tr>
											<tr>
												<td colspan="4">
													<h4>合同完成情况:</h4>
													<ul class="contract_completion">
														<c:forEach begin="1" end="${project.factoryList.size()}"
															varStatus="j" step="1">
															<li><span>${project.factoryList.get(j.count-1).contractNo}${project.factoryList.get(j.count-1).factoryName}</span>
																<c:if
																	test="${project.factoryList.get(j.count-1).sampleDate != null && project.factoryList.get(j.count-1).orderNature == 1}">
																	<span>样品交期：<fmt:formatDate
																			value="${project.factoryList.get(j.count-1).sampleDate}"
																			pattern="yyyy-MM-dd" /></span>
																	<span>样品实际完成日期： <c:if
																			test="${project.factoryList.get(j.count-1).sampleFinishTime != null}"> 
														${project.factoryList.get(j.count-1).sampleFinishTime}
														</c:if> <c:if
																			test="${project.factoryList.get(j.count-1).sampleFinishTime == null}"> 
														    暂无
														</c:if>
																	</span>
																</c:if> <c:if
																	test="${project.factoryList.get(j.count-1).deliveryDate != null && project.factoryList.get(j.count-1).orderNature == 1}">
																	<span>大货交期：<fmt:formatDate
																			value="${project.factoryList.get(j.count-1).deliveryDate}"
																			pattern="yyyy-MM-dd" /></span>
																	<span>大货实际完成日期： <c:if
																			test="${project.factoryList.get(j.count-1).productFinishTime != null}">
														  ${project.factoryList.get(j.count-1).productFinishTime}
														</c:if> <c:if
																			test="${project.factoryList.get(j.count-1).productFinishTime == null}"> 
														  暂无
														</c:if>
																	</span>
																</c:if> <c:if
																	test="${project.factoryList.get(j.count-1).orderNature == 2}">
																	<span>返修补货交期： <c:if
																			test="${project.factoryList.get(j.count-1).deliveryDate != null}">
																			<fmt:formatDate
																				value="${project.factoryList.get(j.count-1).deliveryDate}"
																				pattern="yyyy-MM-dd" />
																		</c:if> <c:if
																			test="${project.factoryList.get(j.count-1).sampleDate != null}">
																			<fmt:formatDate
																				value="${project.factoryList.get(j.count-1).sampleDate}"
																				pattern="yyyy-MM-dd" />
																		</c:if>
																	</span>
																	<span>返修/补货实际完成日期： <c:if
																			test="${project.factoryList.get(j.count-1).repairReplenishmentFinishTime != null}">
														${project.factoryList.get(j.count-1).repairReplenishmentFinishTime}
														</c:if> <c:if
																			test="${project.factoryList.get(j.count-1).repairReplenishmentFinishTime == null}">
														      暂无
														</c:if>
																	</span>

																</c:if></li>
														</c:forEach>
													</ul>
												</td>
											</tr>
										</tbody>
									</table>
									<table class="table table-bordered table3">
										<thead>
											<tr>
												<th class="th1">成员</th>
												<th class="th2">工厂</th>
												<th class="th3">验货预约</th>
												<th class="th4">姜工项目评论</th>
												<th class="th5">连接</th>
												<th class="th6">其他信息</th>
												<th class="th7">客户投诉</th>

											</tr>
										</thead>
										<tbody>
											<tr>
												<td rowspan="3">
												     <span>${project.masterQualityInspection == null ? '' : project.masterQualityInspection}</span>
												     <span>${project.zhijian1 == null ? '' : project.zhijian1}</span>
													<span>${project.zhijian2 == null ? '' : project.zhijian2}</span>
													<span>${project.zhijian3 == null ? '' : project.zhijian3}</span>
													<span>${project.qualityInspector1 == null ? '' : project.qualityInspector1}</span>
													<span>${project.qualityInspector2 == null ? '' : project.qualityInspector2}</span>
													<span>${project.qualityInspector3 == null ? '' : project.qualityInspector3}</span>
													<span>${project.qualityInspector4 == null ? '' : project.qualityInspector4}</span>
													<span>${project.qualityInspector5 == null ? '' : project.qualityInspector5}</span>
													<span>${project.qualityInspector6 == null ? '' : project.qualityInspector6}</span>
													<span>${project.qualityInspector7 == null ? '' : project.qualityInspector7}</span>
													<span>${project.saleName == null ? '' : project.saleName}</span>
													<span>${project.sellName == null ? '' : project.sellName}</span>
													<span>${project.purchaseName == null ? '' : project.purchaseName}</span>
													<span>${project.technician == null ? '' : project.technician}</span>
													<a onclick="synchMember('${project.projectNo}')">同步成员</a> <em>采购本周预约检验数（${project.inspectionReservationNum}）</em>
													<i>质检本周更新报告数（${projectReportList == null ? '0' : projectReportList.size()}）</i>
												</td>
												<td rowspan="3"><c:forEach begin="1"
														end="${project.factoryList.size()}" varStatus="j" step="1">
														<p>
															<a
																<c:if test="${project.factoryList.get(j.count-1).factoryName != null}">
										  href="/project/queryFactoryProject?factoryName=${project.factoryList.get(j.count-1).factoryName}" </c:if>
																target="_blank">${project.factoryList.get(j.count-1).factoryName == null ? "" : project.factoryList.get(j.count-1).factoryName}</a>
															<br /> <a class="up"
																onclick="openUrl('${project.projectNo}','${project.factoryList.get(j.count-1).factoryId}',1)">上传视频</a>
															<a class="view"
																onclick="openUrl('${project.projectNo}','${project.factoryList.get(j.count-1).factoryId}',2)">观看</a>
													</c:forEach></td>
												<td rowspan="3"><c:forEach begin="1"
														end="${project.inspectionList.size()}" varStatus="j"
														step="1">
														<span><fmt:formatDate
																value="${project.inspectionList.get(j.count-1).createDate}"
																pattern="yyyy-MM-dd" /></span>
														<em>${project.inspectionList.get(j.count-1).testType}</em>
													</c:forEach> <a target="_blank"
													href="${ctx}/inspection/toInputInspection?projectNo=${project.projectNo}&roleNo=${roleNo}&userId=${userId}&userName=${userName}">验货预约(${project.inspectionList.size()})</a>
												</td>
												<td rowspan="3"><c:forEach begin="1"
														end="${project.commentList.size()}" varStatus="j" step="1">
														<c:if
															test="${project.commentList.get(j.count-1).reviewer == 'Jiangwenlong'}">
															<p class="tabele3_p">${project.commentList.get(j.count-1).comment}</p>
															<span>${project.commentList.get(j.count-1).reviewer}</span>
															<span><fmt:formatDate
																	value="${project.commentList.get(j.count-1).createDate}"
																	pattern="yyyy-MM-dd" /></span>
														</c:if>
													</c:forEach> <c:if test="${userName == 'Jiangwenlong'}">
														<button class="add_comment" onclick="show_comment(this)">
															<span class="glyphicon glyphicon-plus"></span>
														</button>
													</c:if></td>
												<td><c:if
														test="${project.projectDemandReportList != null && project.projectDemandReportList.size() > 0}">
														<a
															href="http://117.144.21.74:33168/upload/zhongwentuzhi/${project.projectDemandReportList.get(0).drawingName}">需求汇总</a>
													</c:if> <c:if
														test="${project.complaintList != null && project.complaintList.size() > 0}">
														<c:forEach begin="1" end="${project.complaintList.size()}"
															varStatus="j" step="1">
															<c:if
																test="${project.complaintList.get(j.count-1).projectNo eq project.projectNo}">
																<a
																	href="/complaint/queryComplaint?id=${project.complaintList.get(j.count-1).id}">产品问题履历列表</a>
															</c:if>
														</c:forEach>
													</c:if></td>
												<td><span>准许出货确认单：<c:if
															test="${project.shippingApproval == 1}">已传</c:if> <c:if
															test="${project.shippingApproval == 0}">未传</c:if> <c:if
															test="${project.shippingApproval == 2}">部分未传</c:if> <c:if
															test="${project.shippingApproval == 3}">还未有检测计划</c:if></span></td>
												<td rowspan="3" class="add_tale3_td"><c:forEach
														begin="1" end="${project.complaintList.size()}"
														varStatus="j" step="1">
														<div>
															<span><fmt:formatDate
																	value="${project.complaintList.get(j.count-1).complaintDate}"
																	pattern="yyyy-MM-dd" /></span> <em>${project.complaintList.get(j.count-1).purchaseConfirmDate != null ? '采购已确认' : '采购未确认'}<fmt:formatDate
																	value="${project.complaintList.get(j.count-1).purchaseConfirmDate}"
																	pattern="yyyy-MM-dd" /></em> <em>${project.complaintList.get(j.count-1).zhijianReplyTime != null ? '质检已确认' : '质检未确认'}<fmt:formatDate
																	value="${project.complaintList.get(j.count-1).zhijianReplyTime}"
																	pattern="yyyy-MM-dd" /></em>
														</div>
													</c:forEach></td>
											</tr>
											<tr>
												<td><c:if
														test="${project.projectDrawingList != null && project.projectDrawingList.size() > 0}">
														<a
															href="http://117.144.21.74:33168/upload/zhongwentuzhi/${project.projectDrawingList[0].drawingName}">图纸</a>
													</c:if></td>
												<td></td>

											</tr>
											<tr>
												<%-- 		     <td>
								    <c:if test="${project.factoryId != null}">
							        <a href="https://www.kuaizhizao.cn/report/reportListPurchase?csgOrderId=${project.projectNo}&supplierId=${project.factoryId}&factoryId=0&realName=${userName}" target="_blank">生产进度页面</a>
							        </c:if>
							        </td> --%>
												<td><c:if
														test="${project.inspectionPlanList != null && project.inspectionPlanList.size() > 0}">
														<a
															href="http://117.144.21.74:33168/upload/po/upload/JIANYANJIHUAZJ/${project.inspectionPlanList.get(0).reportName}">检验计划
														</a>
													</c:if></td>
												<td class="collapse_td">
													<div>
														<c:if test="${project.isStart == true}">
															<span>启动会</span>
															<em>已开</em>
														</c:if>
													</div>
													<div>
														<c:if test="${project.isSample == true}">
															<span>样品验货会</span>
															<em>已开</em>
														</c:if>
													</div>
													<div>
														<c:if test="${project.isProduct == true}">
															<span>大货验货会</span>
															<em>已开</em>
														</c:if>
													</div>
													<div>
														<c:if
															test="${project.planList !=null && project.planList.size()>0}">
									        生产计划：
									         <c:forEach begin="1" end="${project.planList.size()}"
																varStatus="j" step="1">
																<p>
																	<a target="_blank"
																		href="http://117.144.21.74:33168/upload/zhongwentuzhi/${project.planList.get(j.count-1).planNode}">${project.planList.get(j.count-1).planNode}</a>
																</p>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<%--  <td>
									   <c:if test="${project.planList !=null && project.planList.size()>0}">
									        生产计划：
									         <c:forEach begin="1" end="${project.planList.size()}" varStatus="j" step="1">
									            <p><a target="_blank" href="http://117.144.21.74:33168/upload/zhongwentuzhi/${project.planList.get(j.count-1).planNode}">${project.planList.get(j.count-1).planNode}</a></p>
									         </c:forEach>
									   </c:if>									 
									 </td> --%>

											</tr>
											<c:if
												test="${project.analysisIssueList != null && project.analysisIssueList.size()>0}">
												<tr>
													<td colspan="7">质检协议中的项目问题点关键词： <c:forEach begin="1"
															end="${project.analysisIssueList.size()}" varStatus="j"
															step="1">
								        ${project.analysisIssueList.get(j.count-1).process} : ${project.analysisIssueList.get(j.count-1).issue}、
								       </c:forEach>
													</td>
												</tr>
											</c:if>
										</tbody>
									</table>
								</div>
							</c:forEach>
						</div>
					</form>
					<div class="page-box">
						总数:<span id="totalCount">${totalCount}</span>每页: <select
							id="selectPage" name="selectPage" onchange="selectPageFunc()">
							<option value="5"
								<c:if test="${project.pageSize == 5}">selected</c:if>>5</option>
							<option value="10"
								<c:if test="${project.pageSize == 10}">selected</c:if>>10</option>
							<option value="20"
								<c:if test="${project.pageSize == 20}">selected</c:if>>20</option>
							<option value="50"
								<c:if test="${project.pageSize == 50}">selected</c:if>>50</option>
						</select> 条 当前页${pageNumber}/总页:${lastNum}<span style='color: red'
							id="pageNumberSpan"></span>/<span id="countPageSpan"
							style='color: red'></span>&nbsp; <input
							style="border: 1px solid #ccc; width: 43px;">
						<button type="button"
							style="margin-right: 15px; border: 1px solid #ccc;"
							onclick="jump_page(this)">跳转</button>
						<a href="#" class='emanagergetpagea first-padding'
							onclick="searchProjectData(1)" title='第一页' class='a02'>首页</a> <a
							href="#" class='emanagergetpagea first-padding'
							onclick="seachPage(1)" title='上一页' class='a02'>上页</a> <a href="#"
							class='emanagergetpagea' onclick="seachPage(2)" title='下一页'
							class='a02'>下页</a> <a href="#" class='emanagergetpagea'
							onclick="searchProjectData(${lastNum})" title='最后一页' class='a02'>尾页</a>
						<!-- <div class="pull-right div_pur">
						<span>采购</span>
						<select id="purchase_name_task" name="purchase_name_task" onchange="selectPurchaseTask()">
							<option value="">全部采购</option>
						</select>
						<a href="###" target=_blank id="purchaseNameTask">任务完成情况查询</a>
					</div> -->
					</div>

				</div>
			</div>
</body>


<script type="text/javascript" src="../js/jquery-form.js"></script>
<script src="../js/base64.js"></script>
<script src="../layer/2.1/layer.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="../lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">

/* 日历插件*/ 
  
$(function(){
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
	})
})





</script>
<script type="text/javascript">
	$(function() {
		var roleNo = $('#roleNo').val()
		if (roleNo == 6) {
			$('#purchase_name').parent('.showHtml').hide()
		}
		if (roleNo == 5) {
			$('#documentary_name').parent('.showHtml').hide()
		}
		if (roleNo == 9 || roleNo == 69 || roleNo == 1020) {
			$('#quality_name').parent('.showHtml').hide();
		}

		ajaxSelectOption()
// 		searchProjectData(1)

		var userName = $("#userName").val();
		if (userName == null || userName == '' || userName == undefined) {
			$("#goBackHtml").hide();
		}
		
		$('#tbhtml tr').each(
				function() {
					var project_flag = $(this).find('td:nth-child(1) input').val();
					if (project_flag == 2) {
						$(this).css({"border" : "2px solid red"});
						$(this).find(".projectStatus").css({"color" : "red"})
					}
				}
			)
			/* 点击图片放大到整屏，并点击关闭按钮关闭 */
			$('.table1 tr td img').on('click',function() {
				$("#projectReportHtmlShow").html('');
				$('.big_pic').show();
				$('.big_in').show();
				$(".imgs1 pull-left").hide();
				
				//增加项目号项目名称项目等级显示
				$('.project_introduce').text($(this).parents("tr").find(".projectNo").val()+'+'+ $(this).parents("tr").find(".projectName").val() + '+' + $(this).parents("tr").find(".plantAnalysisView").val());
				
				
				var $src = $(this).attr('src');
				$('.imgs .img_big').attr('src',$src);
				// 单张项目概图大小控制开始
					var gai_img_width = $(this).width();
					var gai_img_height = $(this).height();
					console.log(gai_img_width,gai_img_height);
					 if(gai_img_width >= gai_img_height && gai_img_width < 800){
						 $('.imgs .img_big').css({'width':'800px','height':'auto'});
					}else if(gai_img_width <= gai_img_height && gai_img_height <600){
						$('.imgs .img_big').css({'width':"auto",'height':'600px'});
					}else if(gai_img_width < 800 && gai_img_height <600){
						$('.imgs .img_big').css({'max-height':'600px','max-width':"800px",'width':"auto",'height':'auto'});
					};
				// 单张项目概图大小控制结束
				var picUrl = $(this).parents("tr").find(".reportUrl").val();								
				if (picUrl != null && picUrl != ""&& picUrl != undefined&& picUrl != "null") {//有周报图
					$("#projectNoHtml").text($(this).parents("tr").find(".projectNo").val());
					$("#projectNameHtml").text($(this).parents("tr").find(".projectName").val());
					$("#reportHtml").text($(this).parents("tr").find(".report").val());
					$("#operatorHtml").text($(this).parents("tr").find(".reportName").val());
					$("#operatorDateHtml").text($(this).parents("tr").find(".createDate").val());
					var picUrls=picUrl.split(","); 
					var addhtml="";
				 	for(var i=0;i<picUrls.length;i++){ 
						var url="http://117.144.21.74:10010/uploadimg/"+picUrls[i];
						addhtml+='<li><img src="'+url+'" id="projectReportHtml"></li>';
					} 
				 	$('#totalImgs').text(picUrls.length); 
					$("#projectReportHtmlShow").append(addhtml);
					$("#projectReportHtmlShow").show();
					$(".big_in1").show();
					$(".big_in2").hide();
					// 有周报图
					var li_length = $("#projectReportHtmlShow li").length;								
					var ul_width = (li_length * 560) + 'px';
					$("#projectReportHtmlShow").css({'width':ul_width});
					
					// 根据图片数量来判断 左右按钮是显示还是隐藏开始
					if(li_length <= 1){
						$('.big_pic .btn-imgs').hide();
					};					
					// 轮播
					var key = 0;
					// 右按钮事件
					$('.big_pic .btn-imgs .img2').click(function() {
						key++;
						$('#picPage').text('1' + '/' + li_length)
						$('#currentImg').text((key+1));
						var move1 = -(560 * key);
						if (key > (li_length - 1)) {
							key = 0;
							$('#currentImg').text('1');
							$('#projectReportHtmlShow').css('left', '0');
						} else {
							$('#projectReportHtmlShow').stop().animate({'left' : move1+ 'px'}, 1000);
						}
					});

					// 左按钮事件
					$('.big_pic .btn-imgs .img1').click(function() {
						$('#picPage').text('1' + '/' + li_length)
						$('#currentImg').text(key);
						key--;					
						var move2 = -(560 * key);
						if (key < 0) {
							key = 0;
							$('#currentImg').text('1');
							$('#projectReportHtmlShow').css('left', '0');
						} else {
							$('#projectReportHtmlShow').stop().animate({'left' : move2+ 'px'}, 1000);
						}
					});
				} else {//无周报图
					
					$("#projectNoHtml").text($(this).parents("tr").find(".projectNo").val());
					$("#projectNameHtml").text($(this).parents("tr").find(".projectName").val());
					$("#reportHtml").text($(this).parents("tr").find(".report").val());
					$("#reportHtmlNo").text($(this).parents("tr").find(".report").val());
					$("#operatorHtmlNo").text($(this).parents("tr").find(".reportName").val());
					$("#operatorDateHtmlNo").text($(this).parents("tr").find(".createDate").val());

					var plantAnalysisView = $(this).parents("tr").find(".plantAnalysisView").val();
					if (plantAnalysisView == 'C') { //A/B级项目提醒上传周报图,C级项目不需要提醒
						$("#projectReportAlert").text("");
					} 
// 					else {
// 						$("#projectReportAlert").text("A/B级项目未更新周报图");
// 					}
					$(".big_in1").hide();
					$(".big_in2").show();
				}
			})
			$('.big_pic .top button').on('click', function() {
				$('.big_pic').hide();
				$('.big_in').hide();
				$('#reportHtml').text('');
				$('#projectNoHtml').text('');
				$('#projectNameHtml').text('');
				$("#projectReportAlert").text();
				$("#projectReportHtmlShow").html('');
			})
			//根据返回的值选中
			$("#selectPage option[value='" + pageSize + "']").attr("selected", "selected");
	})

	
	//上页、下一页查询
	function seachPage(num){
		num = Number(num);
		var countPage = Number($("#countPage").val());
		var totalPage = Number($('#totalPage').val());
		if(countPage == 1 && num == 1){
			return false;
		}
		if(num == 2 && countPage == totalPage){
			return false;
		}
		if(num == 1){
			$("#countPage").val(countPage-1);
// 			$('#form2').attr('action','/project/showListNew?pageNumber='+(countPage-1)+'');
			searchProjectData(countPage-1)
		}else if(num == 2){
			$("#countPage").val(countPage+1);
// 			$('#form2').attr('action','/project/showListNew?pageNumber='+(countPage+1)+'');
			searchProjectData(countPage+1)
		}
		
		
	}
	
	
	
	function searchProjectData(pageNumber) {
		$("#pageNumber").val(pageNumber);

		var inputKey = $("#inputKey").val();//关键字查询
		var projectTypeS = "";
		var projectStageS = "";
		var plantAnalysisS = "";
		var feedbackStatusS="";
		var purchase_name = $('#purchase_name').val()
		var documentary_name = $('#documentary_name').val()
		var quality_name = $('#quality_name').val()
		var technician = $('#technician').val()
		var screenType = "screenType";
		var detailStatusS = "";      //细节状态  
		var delayStatusS = "";       //延期项目

		$('input[name="projectStage"]:checked').each(function() {
			projectStageS += $(this).val() + ",";
		});
		if($('.dropdown2').find('input:first').is(':checked')){
			projectStageS = "";
		}
		

		$('input[name="plantAnalysis"]:checked').each(function() {
			plantAnalysisS += $(this).val() + ",";
		});
		if($('.dropdown3').find('input:first').is(':checked')){
			plantAnalysisS = "";
		}
		
        //状态
		$('input[name="projectType"]:checked').each(function() {
			projectTypeS += $(this).val() + ",";
		});
		if($('.dropdown1').find('input:first').is(':checked')){
			projectTypeS = "";
		}
        
        
        //细节状态
		$('input[name="detailStatus"]:checked').each(function() {
			detailStatusS += $(this).val() + ",";
		});
        
        //延期状态
		$('input[name="delayStatus"]:checked').each(function() {
			delayStatusS += $(this).val() + ",";
		});
		if($('.dropdown6').find('input:first').is(':checked')){
			delayStatusS = "";
		}
		
		
		//form搜索数据
		$('#projectTypeS').val(projectTypeS);
		$('#projectStageS').val(projectStageS);
		$('#plantAnalysisS').val(plantAnalysisS);
		$('#screenType').val(screenType);
		$('#detailStatusS').val(detailStatusS);
		$('#delayStatusS').val(delayStatusS);
		$('#inputKey_par').val(inputKey);
		$('#purchase_name_par').val(purchase_name);
		$('#documentary_name_par').val(documentary_name);
		$('#quality_name_par').val(quality_name);
		$('#technician_par').val(technician == '全部技术'?"":technician);
		
		
		var qualityReportSelect = $("#qualityReportSelect").val();
		var importantSelect = $("#importantSelect").val();
		var expectedShipmentSelect = $("#expectedShipmentSelect").val();
		

		
		
		$('#form2').attr('action','/project/showListNew?pageNumber='+pageNumber+''+
				'&projectTypeS='+projectTypeS+'&projectStageS='+projectStageS+'&plantAnalysisS='+plantAnalysisS+''+
				'&detailStatusS='+detailStatusS+'&delayStatusS='+delayStatusS+'&inputKey='+inputKey+'&purchaseName='+purchase_name+''+
				'&documentaryName='+documentary_name+'&qualityName='+quality_name+'&technician='+(technician == '全部技术'?"":technician)+'&qualityReportSelect='+qualityReportSelect+''+
				'&importantSelect='+importantSelect+'&expectedShipmentSelect='+expectedShipmentSelect+'');
		$('#form2').attr('onsubmit',false);
		//提交表单
		$('#form2').submit();
		

		// 表格中产品图图片居中
		$('#tbhtml tr').each(
				function() {
					var img_h = $(this).find('#tab_prodt_img').find('img').height();
					var padd_top = (130 - img_h) / 2;
					$(this).find('#tab_prodt_img').css({
						'padding-top' : padd_top + 'px'
					});

				})
	}
	function exitlogin() {
		window.location.href = "${ctx}/index.jsp";
	}

	function cleanSelect() {


       var userName = $('#userName').val();
       var userId = $('#userId').val();
       var roleNo = $('#roleNo').val();
       window.location = '/project/showListNew?pageNumber=1';
	}

	function qualityReportSelect(obj) {
		if($(obj).val() == '质检报告更新倒序排序'){
			$("#qualityReportSelect").val("1");
		}else if($(obj).val() == '合同交期在最近半个月内项目'){
			$("#qualityReportSelect").val("2");
		}else if($(obj).val() == '有质检要求项目按任务发布时间排序'){
			$("#qualityReportSelect").val("3");
		}else{
			$("#qualityReportSelect").val("");
		}
		if($(obj).val() == '最近两周没更新周报的A/B级项目'){
			$("#importantSelect").val("1");
		}else if($(obj).val() == '处于暂停状态 超过2周的项目'){
			$("#importantSelect").val("2");			
		}else if($(obj).val() == '过去2周取消的项目'){
			$("#importantSelect").val("3");			
		}else if($(obj).val() == '有PO 合同未签的项目，而且 PO时间距离今天超过5天'){
			$("#importantSelect").val("4");			
		}else if($(obj).val() == '处于样品完结状态 超过一个月的项目'){
			$("#importantSelect").val("5");			
		}else if($(obj).val() == '进行中有设置过延期的项目'){
			$("#importantSelect").val("6");			
		}else if($(obj).val() == '新合同上传倒序排列'){
			$("#importantSelect").val("7");			
		}else if($(obj).val() == '项目结束超过 4个月客户无后续项目'){
			$("#importantSelect").val("8");			
		}else{
			$("#importantSelect").val("");		
		}
		
		if($(obj).val() == '今后7天预计出货'){
			$("#expectedShipmentSelect").val("1");
		}else{
			$("#expectedShipmentSelect").val("");
		}
// 		searchProjectData(1);
	}

// 	function expectedShipmentSelect() {
// 		$("#expectedShipmentSelect").val("1");
// 		searchProjectData(1);
// 	}

	function ajaxSelectOption() {

	$.ajax({
		type : "post",
		url : "${ctx}/project/queryStaff.do ",
		success : function(json) {
// 			var json = eval("(" + data + ")");
			if (json.ok) {
				var purchaseList = json.data.purchase
				var saleList = json.data.sale
				var qualityList = json.data.quality
				var purchaseHtml = '<option value="">全部采购</option><option value="无采购">无采购</option>'
				var purchaseTaskHtml = '<option value="">全部采购</option>'
				var saleHtml = '<option value="">全部跟单</option><option value="无跟单">无跟单</option>'
				var qualityHtml = '<option value="">全部质检</option><option value="无质检">无质检</option>'

				for (var i = 0; i < purchaseList.length; i++) {
					purchaseHtml += '<option value="'+purchaseList[i].userName+'">'+ purchaseList[i].userName+ '</option>';
					purchaseTaskHtml += '<option value="'+purchaseList[i].userName+'">'+ purchaseList[i].userName+ '</option>'
				}
				for (var i = 0; i < saleList.length; i++) {
					saleHtml += '<option value="'+saleList[i].userName+'">'+ saleList[i].userName + '</option>'
				}
				for (var i = 0; i < qualityList.length; i++) {
					qualityHtml += '<option value="'+qualityList[i].userName+'">'+ qualityList[i].userName + '</option>'
				}
				$('#purchase_name').html(purchaseHtml);
				$("#purchase_name_task").html(purchaseTaskHtml);
				$('#documentary_name').html(saleHtml);
				$('#quality_name').html(qualityHtml);
				
				
				
				//显示选择的采购、跟单、质检信息
				if(purchaseName){
				   $('#purchase_name').find("option[value="+purchaseName+"]").attr("selected",true);
				}
			    if(documentaryName){
			       $('#documentary_name').find("option[value="+documentaryName+"]").attr("selected",true);
			    }
				if(qualityName){
				   $('#quality_name').find("option[value="+qualityName+"]").attr("selected",true);
				}
			   
			   
			}
		}
	 })
	}
	function getDetail(id) {
		if (!id) {
			return false;
		}

		$.ajax({
			url : "${ctx}/trigger/selectRecordDetails.do",
			type : "post",
			async : false,
			traditional : true,
			data : {
				'triggerId' : id
			},
			datatype : "json",
			success : function(json) {
// 				var json = eval("(" + result + ")");
				if (json.ok) {
					addTrigger()
					$('#triggerId').val(id)
					var triggerTask = json.data
					$('#roleType').val(triggerTask.roleType);
					$('#taskTitle').val(triggerTask.taskTitle)
				} else {

				}

			}

		})

	}

	function deleteDetail(id) {
		if (!id) {
			return false;
		}

		if (window.confirm('你确定要删除吗？')) {

		} else {
			return false;
		}

		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var roleNo = $("#roleNo").val();

		$.ajax({
			url : "${ctx}/trigger/deleteRecordDetails.do",
			type : "post",
			async : false,
			traditional : true,
			data : {
				'triggerId' : id
			},
			datatype : "json",
			success : function(json) {
// 				var json = eval("(" + result + ")");

				if (json.ok) {

					window.location.href = "${ctx}/trigger/queryList?userName="
							+ userName + "&roleNo=" + roleNo + "&userId="
							+ userId;

				} else {
					easyDialog.open({
						container : {
							header : 'Prompt message',
							content : json.message
						},
						overlay : false,
						autoClose : 12000
					});
				}

			}

		})
	}

	function saveDetail() {

		$('.page2').find('.ff4').removeClass('ff4').text('')

		if (!$('#projectNo').val()) {
			$('#projectNo').next().addClass('ff4').text('请输入项目号')
		}

		$('.page2').find('select[name=roleType]').each(function() {
			if ($(this).val() == 0) {
				$(this).next().addClass('ff4').text('请选择角色类型')
			}

		})

		$('.page2').find('input[name=taskTitle]').each(function() {
			if ($(this).is(':visible')) {
				if (!$(this).val()) {
					$(this).next().addClass('ff4').text('请输入任务标题')
				}

			}

		})

		if ($('.ff4').length > 0) {
			return false;
		}

		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var roleNo = $("#roleNo").val();

		$("#submitButton").attr("disabled", true).css("background-color","#999");
		$("#triggerform").ajaxSubmit({
							type : "post",
							url : "${ctx}/trigger/addRecord",
							dataType : "text",
							success : function(result) {
								var json = eval("(" + result + ")");
								$("#submitButton").css("background-color","#027CFF").removeAttr('disabled');
								if (json.ok) {
									window.location.href = "${ctx}/trigger/queryList?userName="+ userName+ "&roleNo="+ roleNo+ "&userId=" + userId;
								} else {

									easyDialog.open({
										container : {
											header : 'Prompt message',
											content : json.message
										},
										overlay : false,
										autoClose : 3000
									});

								}

							}
						})

	}

	function deleteRole(obj) {
		$(obj).parents('.form-group').remove()
	}

	function addProjectReportHtml(obj) {
		$("#projectReportNo").val(obj);
		
		$.ajax({
			url : "${ctx}/project/queryProjectFactory",
			async : false,
			traditional : true,
			data : {
				projectNo : obj
			},
			datatype : "json",
			success : function(json) {
// 				var json = eval("(" + result +")");
				  if(json.ok){                   
					var factoryList = json.data.factoryList;
					$('#factory_ul').empty();
					    for(var i=0;i<factoryList.length;i++){
					    	//工厂合同状态
					    	var state = factoryList[i].orderState == 1 ? "在做模具" : (factoryList[i].orderState == 2 ? "在打样或小批量" : (factoryList[i].orderState == 3 ? "在大批量生产" :(factoryList[i].orderState == 4 ? "因为工厂原因还未做" :(factoryList[i].orderState == 5 ? "因为我司或者客户原因暂停" : (factoryList[i].orderState == 6 ? "已经部分交货，正在继续生产" :   (factoryList[i].orderState == 7 ? "已经部分交货，目前暂停" : "未更新状态"))))));					    	
					    	var li = '<li class="text-left mt5 clearfix" filed="'+factoryList[i].id+'"><span>'+factoryList[i].factoryName+' - ' +state+
										'</span>'+
										'<select class="form-control display_inline_block">'+ 
											'<option value="1">在做模具</option>'+ 
											'<option value="2">在打样或小批量 </option>'+ 
											'<option value="3">在大批量生产</option>'+ 
											'<option value="4">因为工厂原因还未做</option>'+ 
											'<option value="5">因为我司或者客户原因暂停</option>'+ 
											'<option value="6">已经部分交货，正在继续生产</option>'+ 
											'<option value="7">已经部分交货，目前暂停</option>'+ 								
										'</select>'+ 
									   '</li>';
							 $('#factory_ul').append(li);		   
					    }				  						
				}
				
			}
		})
		
		
		$('.add_tc1').show();
	}

	function cancelReport(obj){
		$(obj).parents('.add_tc1').hide();
	}
	
	
	function addFeeBackHtml(obj,index) {
		$("#projectNoFack").val(obj);
		$('.add_tc2').show();
		$('#save_feedback').attr('onclick','addFeedback('+index+')');
		$('#add_date').attr('onclick','add_date(\''+obj+'\')');
		$.ajax({
		    type:"post",                   
		    url:"${ctx}/project/queryProjectDetail",           
		    data:{
		    	  projectNo:obj		    	  
		    	 },              
		    success:function(json){  
// 		      var json = eval("(" + data +")");
			  if(json.ok){                      
							  
				  $('#date_log').empty();
				  var projectScheduleList = json.data.projectScheduleList;
				  if(projectScheduleList){
					  for(var i=0;i<projectScheduleList.length;i++){
						  var time = formatDateTime(projectScheduleList[i].predictDate);
						  var li = '<li class="mt10 ">'+
										'<button class="btn del mr5" onclick="del_date('+projectScheduleList[i].id+',this)">删除</button>'+
										'<span>'+time+'</span>'+
									'</li>';
			                $('#date_log').append(li);		  
					  }				
				   }								 
				  
			  }
		   }
	   });  
		
	}
	
	

	//添加客户反馈更新 
	function addFeedback(index) {
		var projectNo = $("#projectNoFack").val();
		var feedback = $("#feedback").val();
		var userName = $("#userName").val();
		var detailStatus=$("#selectProjectStatus").find("option:selected").val();
		$('.add_tc2').hide();
		$.ajax({
			url : "${ctx}/feedback/addFeedback",
			type : "post",
			async : false,
			traditional : true,
			data : {
				projectNo : projectNo,
				feedback : feedback,
				userId : userId,
				roleNo : roleNo,
				userName : userName,
				detailStatus:detailStatus
			},
			datatype : "json",
			success : function(json) {
// 				var json = eval("(" + result + ")");
				if (json.ok) {
					var result = json.data;
					$("#projectNoFack").val('');
					$("#feedback").val('');					
					var divHtml = '<span>'+result.detailStr+'</span>'+					        
							      '<span>'+feedback+'</span>'+					        
								  '<p>'+result.updateDate+'</p>'+
								  '<p>'+result.userName+'</p>'+
								  '<button class="btn btn_update new_add1" onclick="addFeeBackHtml(\''+result.projectNo+'\','+index+')">已更新</button>';
					$('.table1').eq(index+1).find('.s7').empty().append(divHtml);
				} else {
					easyDialog.open({
						container : {
							header : 'Prompt message',
							content : json.message
						},
						overlay : false,
						autoClose : 12000
					});
				}
			}
		})
	}
	//添加周报
	function addProjectReport() {
		var projectNo = $("#projectReportNo").val();
		var report = $("#report").val();
		var userName = $("#userName").val();
		var userId = $("#userId").val();
		var roleNo = $("#roleNo").val();
		var addReportImg=""
		var picUrl=''
		$('#projectReportUrl').find('li').each(function(){
	        if(!picUrl){
	        	picUrl=$(this).find("input[name='url']").val();
	        }else{
	        	if($(this).find("input[name='url']").val()!=undefined && $(this).find("input[name='url']").val()!=null &&
	        			$(this).find("input[name='url']").val()!='' ){
	        		picUrl=picUrl+','+$(this).find("input[name='url']").val();
	        	}
	        }
	        	
	     })
	    var addReportImg = picUrl;
		var projectStage=$("#selectProjectStage").find("option:selected").val();
		
		
		//工厂生产状态
		var factoryList=[];
		$('#factory_ul').find('li').each(function(){
			var This = $(this);
        	var id = This.attr('filed');        	       	
        	var orderState = This.find('select').val();
        	if(!orderState){
        		return;
        	}
        	var factory = {"id":id,"orderState":orderState};
        	factoryList.push(factory);
		})
		
		
		$(".add_tc_zb").hide();
		$.ajax({
			url : "${ctx}/report/addReportPC",
			type : "post",
			async : false,
			traditional : true,
			data : {
				projectNo : projectNo,
				report : report,
				userId : userId,
				roleNo : roleNo,
				reportName : userName,
				addReportImg : addReportImg,
				projectStage:projectStage,
				factoryList : JSON.stringify(factoryList)  
			},
			datatype : "json",
			success : function(json) {
// 				var json = eval("(" + result + ")");
				if (json.ok) {
					$("#projectReportNo").val('');
					$("#report").val('');
					$("#projectReportUrl").html('<li><span class="glyphicon glyphicon-plus"></span><input type="file"  id="uploadFile"  name="files" onchange="uploadWeekPicture(this)" multiple></li>');
					searchProjectData(1);
				} else {
					easyDialog.open({
						container : {
							header : 'Prompt message',
							content : json.message
						},
						overlay : false,
						autoClose : 12000
					});
				}

			}
		})
	}

	function goBack() {
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var purchaseNameId = "";
		window.location.href = "${ctx}/user/toIndex?userId=" + userId
				+ "&roleNo=" + roleNo + "&purchaseNameId=" + purchaseNameId
				+ "&userName=" + encodeURI(encodeURI(userName));
	}

	$(".box-executive input,.box-executive em").click(
			function(event) {
				$(this).parents(".project-task-list").find(".updown-list")
						.slideUp(30).end().end().siblings(".updown-list")
						.slideDown();
				$(this).parent(".box-executive").find("em").addClass(
						"arrow-icon");
				return false;
			});

	$(".list ol li").click(function(event) {
		var game1 = $(this).text()
		console.log($(this).parents(".updown-list").siblings("input").length);
		$(this).parents(".updown-list").siblings("input").val(game1);
		foc();
	});

	function addClick() {
		$(".box-executive input,.box-executive em").click(
				function(event) {
					$(this).parents(".project-task-list").find(".updown-list")
							.slideUp(30).end().end().siblings(".updown-list")
							.slideDown();
					$(this).parent(".box-executive").find("em").addClass(
							"arrow-icon");
					return false;
				});

		$(".list ol li").click(
				function(event) {
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

	function download_file(obj) {
		var dataid = obj
		if (dataid) {
			window.location = "${ctx}/quality/download?id=" + dataid;
		}
	}
</script>
<script>



	// 项目状态，项目阶段，项目等级，等内容多选功能
	$('.dropdown button').click(function() {
		$(this).siblings('ul').toggle();
		$(this).siblings('.div_transparent').toggle();
	})
	$('.div_transparent').click(function() {
		$(this).siblings('ul').hide();
		$(this).hide();
	})




	$('.dropdown1 input').click(function(){		
		if($(this).parent().parent().hasClass('first-li')){			
				$('.dropdown1 button em').html('全部状态');
// 				$('.dropdown1 input').prop('checked',true);	
				if($(this).is(':checked')){			
					$('.dropdown1 input').prop('checked',true);			
				}else{			
					$('.dropdown1 input').prop('checked',false);
				};
		}else{
			// 多选反选
			var input_len = $('.dropdown1 li').not('.first-li').find('input:checked').length;						
			if (input_len > 6) {
				$(this).parent().parent().siblings('.first-li').find(
						'input').prop('checked', true);
				$('.dropdown1 button em').html('全部状态');
			}else{
				$(this).parent().parent().siblings('.first-li').find('input').prop('checked', false);					
				// 获取赋值
					var s = '';
					$('.dropdown1 input[name="projectType"]').each(function() {
						if ($(this).is(':checked')) {
							s += $(this).siblings('span').text() + ",";
						}
					});
					if (s != '') {
						s = s.substring(0, s.length - 1);
						$('.dropdown1 button em').html(s);
					}
			};
	
		};
	});
	

				
	$('.dropdown2 input').click(function(){
		if($(this).parent().parent().hasClass('first-li')){
			$('.dropdown2 button em').html('全部阶段');
// 			$('.dropdown2 input').prop('checked',true);	
			if($(this).is(':checked')){			
				$('.dropdown2 input').prop('checked',true);			
			}else{			
				$('.dropdown2 input').prop('checked',false);
			};
		}else{
			// 多选反选
			var input_len = $('.dropdown2 li').not('.first-li').find('input:checked').length;						
			if (input_len > 3) {
				$(this).parent().parent().siblings('.first-li').find(
						'input').prop('checked', true);
				$('.dropdown2 button em').html('全部阶段');
			}else{
				$(this).parent().parent().siblings('.first-li').find('input').prop('checked', false);					
				// 获取赋值
					var s = '';
					$('.dropdown2 input[name="projectStage"]').each(function() {
						if ($(this).is(':checked')) {
							s += $(this).siblings('span').text() + ",";
						}
					});
					if (s != '') {
						s = s.substring(0, s.length - 1);
						$('.dropdown2 button em').html(s);
					}
			};
	
		};
			
	});

	
	
	$('.dropdown3 input').click(function() {
			
		
		if($(this).parent().parent().hasClass('first-li')){
			$('.dropdown3 button em').html('全部等级');
// 			$('.dropdown3 input').prop('checked',true);		
			if($(this).is(':checked')){			
				$('.dropdown3 input').prop('checked',true);			
			}else{			
				$('.dropdown3 input').prop('checked',false);
			};
		}else{
			// 多选反选
			var input_len = $('.dropdown3 li').not('.first-li').find('input:checked').length;						
			if (input_len > 3) {
				$(this).parent().parent().siblings('.first-li').find(
						'input').prop('checked', true);
				$('.dropdown3 button em').html('全部等级');
			}else{
				$(this).parent().parent().siblings('.first-li').find('input').prop('checked', false);					
				// 获取赋值
					var s = '';
					$('.dropdown3 input[name="plantAnalysis"]').each(function() {
						if ($(this).is(':checked')) {
							s += $(this).siblings('span').text() + ",";
						}
					});
					if (s != '') {
						s = s.substring(0, s.length - 1);
						$('.dropdown3 button em').html(s);
					}
			};
	
		};
							
	});
	// 反选和赋值   
	$('.dropdown4 input').click(function() {

				if($(this).val() == '-1'){
		        	 if ($(this).is(':checked')) {
		        		 $('.dropdown4 input[name="feedback"]').each(function() {
		 					 $(this).prop('checked', true);
		 				});
		        	 }else{
		        		 $('.dropdown4 input[name="feedback"]').each(function() {
		 					 $(this).prop('checked', false);
		 				});
		        	 }
		         }
				
				
				
				var li_len = $('.dropdown4 li').length;
				var input_len = $('.dropdown4 li').not('.first-li').find('input:checked').length;		
				if (input_len > 5) {
					$(this).parent().parent().siblings('.first-li').find('input').prop('checked', true);
							
				} 
				// 获取赋值
				var s = '';
				$('.dropdown4 input[name="feedback"]').each(function() {
					if ($(this).is(':checked')) {
						s += $(this).siblings('span').text() + ",";
					}
				});
				if (s != '') {
					s = s.substring(0, s.length - 1);
					$('.dropdown4 button').html(s);
				}; 
				
	});
			
			$('.dropdown5 input').click(function() {
				if($(this).parent().parent().hasClass('first-li')){
					$('.dropdown5 button em').html('全部细节状态');
// 					$('.dropdown5 input').prop('checked',true);		
					if($(this).is(':checked')){			
						$('.dropdown5 input').prop('checked',true);			
					}else{			
						$('.dropdown5 input').prop('checked',false);
					};
				}else{
					// 多选反选
					var input_len = $('.dropdown5 li').not('.first-li').find('input:checked').length;						
					if (input_len > 3) {
						$(this).parent().parent().siblings('.first-li').find(
								'input').prop('checked', true);
						$('.dropdown5 button em').html('全部细节状态');
					}else{
						$(this).parent().parent().siblings('.first-li').find('input').prop('checked', false);					
						// 获取赋值
							var s = '';
							$('.dropdown5 input[name="detailStatus"]').each(function() {
								if ($(this).is(':checked')) {
									s += $(this).siblings('span').text() + ",";
								}
							});
							if (s != '') {
								s = s.substring(0, s.length - 1);
								$('.dropdown5 button em').html(s);
							}
					};
			
				};
				
			});
			// 反选和赋值   
// 			$('.dropdown5 .first-li input').click(function(){
// 				if($(this).is(':checked')){			
// 					$('.dropdown5 input').prop('checked',false);			
// 				}else{			
// 					$('.dropdown5 input').prop('checked',true);
// 				};
// 			});
			
			$('.dropdown6 input').click(function() {
				
				if($(this).parent().parent().hasClass('first-li')){
					$('.dropdown6 button em').html('全部');
// 					$('.dropdown6 input').prop('checked',true);		
					if($(this).is(':checked')){			
						$('.dropdown6 input').prop('checked',true);			
					}else{			
						$('.dropdown6 input').prop('checked',false);
					};
				}else{
					// 多选反选
					var input_len = $('.dropdown6 li').not('.first-li').find('input:checked').length;						
					if (input_len > 1) {
						$(this).parent().parent().siblings('.first-li').find(
								'input').prop('checked', true);
						$('.dropdown6 button em').html('全部');
					}else{
						$(this).parent().parent().siblings('.first-li').find('input').prop('checked', false);					
						// 获取赋值
							var s = '';
							$('.dropdown6 input[name="delayStatus"]').each(function() {
								if ($(this).is(':checked')) {
									s += $(this).siblings('span').text() + ",";
								}
							});
							if (s != '') {
								s = s.substring(0, s.length - 1);
								$('.dropdown6 button em').html(s);
							}
					};
			
				};
			})

			
			
	//查询采购完成任务的情况
	function selectPurchaseTask() {
		var searchName = $("#purchase_name_task").val();
		var userName = $("#userName").val();
		var roleNo = $("#roleNo").val();
		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var purchaseNameId = "";
		var href = "${ctx}/projectTask/projectTaskList?userId=" + userId
				+ "&roleNo=" + roleNo + "&purchaseNameId="
				+ encodeURI(encodeURI(purchaseNameId)) + "&userName="
				+ encodeURI(encodeURI(userName)) + "&searchName=" + searchName;
		$("#purchaseNameTask").attr("href", href);
	}
	//选择分页
	function selectPageFunc() {
		var selectPage = $("#selectPage").val();
		$("#pageSize").val(selectPage);
		searchProjectData(1)
	}
	
	
	var li_length = 0;
	var remove_length = 0;
	//打开PC版质检报告
	function openQualityReport(obj, projectNo) {
		$("#picProjectNo").val(projectNo);
		$("#qualityReportId").val(obj);
		var dataid = obj;
		$('.bg_2').show();
		$
				.ajax({
					url : "${ctx}/quality/viewQualityPc",
					type : "post",
					data : {
						id : dataid
					},
					success : function(json) {
// 						var json = eval("(" + data + ")");
						$("#qualityReportHtml").html('');
						if (json.ok) {
// 							var picUrlS = json.data.picUrls;
							var qualityPicExplainList = json.data.qualityPicExplainList;
							var createtimeView = json.data.createtimeView;
							var qualityReport = json.data;
							var qualityReportHtml = "";
							var reportHtml = '';
							reportHtml += '<div class="wrap"><div class="wrap_top"><div class="btn btn1 " onclick = "fun_b()"><span class="glyphicon glyphicon-chevron-left"></span></div>'
									+ '<div class="btn btn2 " onclick = "fun_d()"><span class="glyphicon glyphicon-chevron-right"></span></div>'
									+ '<div class="div_clear"><div class="pull-right pic_intro">'
									+'<div class=""><span>质检报告</span><button class="pull-right close" onclick="hide()">关闭</button></div>'
									+'<textarea class="form-control" name="picExplain" id="picExplain" value=""></textarea><span class="update"></span><button class="btn" onclick="addQualityPicExplain()">更新说明</button>'
									+'<div class="clearfix div1"><em>项目号</em><span>'+qualityReport.projectNo+'</span></div>'
									+'<div class="clearfix"><em>项目名</em><span>'+(qualityReport.projectNameC?qualityReport.projectNameC:"")+'</span></div>'
									+'<div class="clearfix"><em>等级</em><span>'+qualityReport.grade+'</span></div>'
									+'<div class="clearfix"><em>检验阶段</em><span>'+qualityReport.typeView+'</span></div>'
									+'<div class="clearfix"><em>上传者</em><span>'+qualityReport.user+'</span></div>'
									+'<div class="a0 div_a">'
									+'<a href="javaScript:void(0);" onclick="download_file('+qualityReport.id+')"><button>质检报告以压缩包形式导出</button></a>'
									+'<a href="javaScript:void(0);" onclick="exportWord('+qualityReport.id+')"><button>质检报告以EXCEL形式导出</button></a>'
// 									+'<a href="javaScript:void(0);" onclick=""><button>更新所有图片含备注以及质检报告文档到ERP</button></a>'
									+'</div>'
									+'</div>'
									+ '<div class="imgs pull-left"> <ul  class="clearfix" id="ul_lunbo">'

							for (var i = 0; i < qualityPicExplainList.length; i++) {
								reportHtml += '<li><a href="###"><img src="'+qualityPicExplainList[i].picName+'" alt=""><input type="hidden" name="picName" class="picName" value="'+qualityPicExplainList[i].picName+'"></input></a></li>';
							}

							reportHtml += '</ul></div></div><div class="bottom clearfix">'+ '<div class="pull-right"><span>'+ createtimeView+ '</span> <span id="picPage"></span></div></div></div>'
									+ '<div class="s_m s_m_1"></div>'
									+ '<div class="a0"> <span>本报告是：</span> <span>'+ qualityReport.typeView+ '</span></div>'+ '<div class="a0"> <span>本样品：</span> <span>'
									+ qualityReport.statusView+ '</span></div>'+ '<div class="s_m"><p>'+qualityReport.explainCause+'</p></div></div>'

							$("#qualityReportHtml").append(reportHtml);
							// 控制图片大小开始
								$('#ul_lunbo li').each(function(){
									var this_img_width = $(this).find('img').width();
									var this_img_height = $(this).find('img').height();
									if(this_img_width >= this_img_height && this_img_width < 800 ){
										$(this).find('img').css({'width':'800','height':'auto'})
									}else if(this_img_width < this_img_height && this_img_height < 600 ){
										$(this).find('img').css({'height':'600','width':'auto'})
									}else{
										$(this).find('img').css({'max-height':'600','max-width':'800','width':'auto','height':'auto'})
									}
								})
							// 控制图片大小结束
							// 计算图片个数和UL的宽度
						    li_length = $('#qualityReportHtml .wrap .imgs li').length;
						    remove_length = 800 * li_length;
							//轮播图片垂直位置						
							$('#qualityReportHtml .wrap #ul_lunbo li').each(
							function() {
								var src = $(this).find('img').attr('src');
								var img_w = $(this).find('img').width();
								var lunbo_img_h = $(this).find('img').height();
								var m_top = (550 - lunbo_img_h) / 2;
								$(this).find('a').css({'padding-top' : m_top+ 'px'});
							});
							// 轮播图 按钮事件						
							$('#qualityReportHtml .wrap .imgs ul').css({
								'width' : remove_length + 'px'
							});
							
							// 获取img  路径
							var src = $('.wrap .imgs ul li').eq(key).find(
									'input').val();
							selectPicExplainByName(src);
							$("#picName").val(src);
							$('#picPage').text('1/'+ li_length);
							document.onkeydown = jumpPage;

						} else {
							easyDialog.open({
								container : {
									header : 'Prompt message',
									content : json.message
								},
								overlay : false,
								autoClose : 1000
							});
						}
					}
				});
	}
	
	
	var key = 0;
	// 上下键盘轮播实现
	function jumpPage() {
		if (event.keyCode == 38)//上
			fun_b();
		if (event.keyCode == 40)//下
			fun_d();
	}
	
	function fun_b() {
		// 左按钮事件

		$("#picExplain").val('');
		$('#picPage').text('1' + '/' + li_length)
		key--;
		var move2 = -(800 * key);
		if (key < 0) {
			key = 0;
			$('#qualityReportHtml .wrap .imgs ul').css('left', '0');
		} else {
			$('#qualityReportHtml .wrap .imgs ul').stop().animate({'left' : move2 + 'px'}, 1000);
		}
		$('#picPage').text((key + 1) + '/' + li_length)
		// 获取img  路径
		var src = $('.wrap .imgs ul li').eq(key).find('input').val();
		$("#picName").val(src);
		selectPicExplainByName(src);

	};
	function fun_d() {
		// 右按钮事件
		$("#picExplain").val('');
		key++;
		$('#picPage').text('1' + '/' + li_length)
		var move1 = -(800 * key);
		$('#qualityReportHtml .wrap .imgs ul').css({
			'width' : remove_length + 'px'
		});
		if (key > (li_length - 1)) {
			key = 0;
			$('#qualityReportHtml .wrap .imgs ul').css('left', '0');
		} else {
			$('#qualityReportHtml .wrap .imgs ul').stop().animate({'left' : move1 + 'px'}, 1000);
		}
		;
		$('#picPage').text((key + 1) + '/' + li_length)
		// 获取img  路径
		var src = $('.wrap .imgs ul li').eq(key).find('input').val();
		$("#picName").val(src);
		selectPicExplainByName(src);
	};
	
	
</script>
<script>
	window.onload = window.onresize = function() {
		var clientWidth = document.documentElement.clientWidth;
		document.getElementsByTagName("html")[0].style.fontSize = clientWidth
				/ 768 * 40 + "px";
	}

	function download_file(obj) {
		var dataid = obj
		if (dataid) {
			window.location = "${ctx}/quality/download?id=" + dataid;
		}
	}
	function exportWord(reportId) {
		window.location = "${ctx}/exportReportExcel?reportId=" + reportId;
	}
	function hide() {
		key=0;
		$('.wrap,.bg_2').hide();
		$("#projectReportAlert").hide();
	}

	function addQualityPicExplain() {
		var qualityReportId = $("#qualityReportId").val();
		var picProjectNo = $("#picProjectNo").val();
		var picExplain = $("#picExplain").val();
		var picName = $("#picName").val();
		$.ajax({
			url : "${ctx}/qualityPic/addQualityPicExplain",
			type : "post",
			async : false,
			traditional : true,
			data : {
				qualityReportId : qualityReportId,
				picProjectNo : picProjectNo,
				picExplain : picExplain,
				picName : picName
			},
			datatype : "json",
			success : function(json) {
// 				var json = eval("(" + result + ")");
				if (json.ok) {

				} else {
					easyDialog.open({
						container : {
							header : 'Prompt message',
							content : json.message
						},
						overlay : false,
						autoClose : 1000
					});
				}

			},
			error : function() {
				easyDialog.open({
					container : {
						content : 'Update failed'
					},
					autoClose : 1000
				});
			}
		})
	}

	function selectPicExplainByName(obj) {
		var picName = obj;
		$.ajax({
			url : "${ctx}/qualityPic/selectQualityPicExplain",
			async : false,
			traditional : true,
			data : {
				picName : picName
			},
			datatype : "json",
			success : function(json) {
// 				var json = eval("(" + result + ")");
				$("#picExplain").val('');
				if (json.ok) {
					if (json.data != null && json.data != '') {
						$("#picExplain").val(json.data.picExplain);
					}
				}
			}
		})
	}
</script>
<script>
	function uploadWeekPicture(obj) {
    	var uploadFiles = $('#uploadFile')[0].files;
		var fileNames = '';
		for (var i = 0; i < uploadFiles.length; i++) {
			if (i == uploadFiles.length - 1) {
				fileNames += uploadFiles[i].name;
			} else {
				fileNames += uploadFiles[i].name + ';';
			}
		}
		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		}
		$("#fileNames").val(fileNames);
		// 先上传后获取上传文件路径
		$("#file_upload_id").ajaxSubmit({
			type : "post",
			url : "${ctx}/upload/uploadWeekPicture",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
					var data = result.data;
					var addhtml=''
					var picUrls=data.split(","); 
					for(var i=0;i<picUrls.length;i++){
						var url="http://117.144.21.74:10010/uploadimg/"+picUrls[i];
						addhtml+='<li><img src="'+url+'" alt=""><input type="hidden" name="url" value="'+picUrls[i]+'"><span class="glyphicon glyphicon-remove"></span></li>';
					}
					$('.clearfix').prepend(addhtml);
					
					/*删除当前图片*/
					$('.add_tc_zb .glyphicon-remove').click(function(){
						$(this).parent('li').remove();
					});
				}
			},
			error : function() {
				
			}
		});
	}
</script>
<script>
	// 扩展信息伸缩	
		$('#table1_tbody tr .table_toggle').click(function(){		
			
/* 			$.ajax({
				url : "${ctx}/project/queryProjectDetail",
				async : true,
				traditional : true,
				data : {
					projectNo : projectNo
				},
				datatype : "json",
				success : function(result) {
					
				}
			}) */
		//去除重复的工厂显示
		var strs = '';
		$(this).parents('.table1').next('.next_div').find('.table3').find('td:eq(1)').find('a').each(function(){
			 var str = $(this).text();
			 if(strs.indexOf(str) != -1){
				 $(this).parent().remove();
			 }else{
				 strs +=str;
			 }
		})
			
			
			$(this).parents('.table1').next('.next_div').toggle();
		});

		$('.tan_comment button').on('click',function(){
			$('.tan_comment').hide();
		});
		
		
		//弹出姜工评论窗口
		function show_comment(obj){
			$(obj).parents('.next_div').find('.tan_comment').show();
		}
		
		//保存姜工评论
		function add_comment(obj,projectNo){
			
			var comment = $(obj).parents('.tan_comment').find('textarea').val();
			if(!comment){
				easyDialog.open({
					container : {
						content : '评论内容不能为空'
					},
					overlay : false,
					autoClose : 1000
				});
				return false;
			}
			var userName = $("#userName").val();
			var roleNo = $("#roleNo").val();
			var userId = $("#userId").val();
			
			$.ajax({
				url : "${ctx}/project/projectComment",
				async : false,
				traditional : true,
				data : {
					userName : userName,
					comment : comment,
					projectNo : projectNo
				},
				datatype : "json",
				success : function(result) {
					$(obj).parents('.next_div').find('.tan_comment').hide();
					$(obj).parents('.next_div').find('.add_comment').before('<span>'+comment+'</span>');
				}
			})
		}
		/* 质量任务录入页面 */
		//'${project.projectNo}',${userId},'${userName}',${roleNo}
		function qualityTaskInput(projectNo,userId,userName,roleNo){
			window.open("/projectTask/addTask?roleNo="+roleNo+"&userId="+userId+"&userName="+userName+"&taskType=12&projectNo="+projectNo);
		}
		function cancelAdd(obj){
			$(obj).parents('.add_tc2').hide();
			$("#projectReportAlert").hide();
		}
		// 列表内部编辑项目状态
	     //根据当前状态不同，显示不同的选择
		function selectByProject(projectNo,projectStatus){
			
			$('#editProjectStatus').empty();
 			if(projectStatus == 1){
 				$('#editProjectStatus').append( '<option></option>'+
  						                        '<option value="6">打样完结</option>'+
 												'<option value="2">大货完结</option>'+
												'<option value="4">项目暂停</option>'+
												'<option value="5">项目取消</option>');
 				$('.add_tc3').find('button:last').attr('onclick','updateProjectStatus(\''+projectNo+'\')');
 				
 			 }else if(projectStatus == 2 || projectStatus == 6){
 				$('#editProjectStatus').append( '<option></option>'+
	 						                    '<option value="1">重启项目</option>'+
	 						                    '<option value="4">项目暂停</option>'+
												'<option value="5">项目取消</option>');
 				$('.add_tc3').find('button:last').attr('onclick','updateProjectStatus(\''+projectNo+'\')');
 			 }else if(projectStatus == 4){
 				$('#editProjectStatus').append(  '<option></option>'+
 						                         '<option value="1">重启项目</option>'+
												 '<option value="5">项目取消</option>');
 				$('.add_tc3').find('button:last').attr('onclick','updateProjectStatus(\''+projectNo+'\')');
 			 }else if(projectStatus == 0){
 				$('#editProjectStatus').append(  '<option></option>'+
						                         '<option value="4">项目暂停</option>'+
												 '<option value="5">项目取消</option>');
                $('.add_tc3').find('button:last').attr('onclick','updateProjectStatus(\''+projectNo+'\')');
 			 }else if(projectStatus == 5){
 				 return false;
 			 }
			
 			 var myDate = new Date();
 			//获取当前年
 			 var year=myDate.getFullYear();
 			 //获取当前月
 			 var month=myDate.getMonth()+1;
 			 //获取当前日
 			 var date=myDate.getDate(); 
 			 $('#select_date').val(year+"-"+month+"-"+date);
			
			$('.add_tc3').show();
		}
		
		function add_tc3Hide(object){
			$(object).parents('.add_tc3').hide();
		}
		
		//更改状态时筛选
		$('#editProjectStatus').change(function(){
			if($(this).val() == 6 || $(this).val() == 2){
				$('.add_tc3').find('p').hide();
				$('.add_tc3').find('textarea').hide();
			}else{
				$('.add_tc3').find('p').show();
				$('.add_tc3').find('textarea').show();
			}
		})
		
		
	/* 	// 问题汇总延期修改
		function edit_delay(userName,projectNo,contractDays,originalDeliveryDate,originalSampleScheduledDate,newPredictDate,urgentDeliveryDate,deliveryDate,sampleScheduledDate){
			if(userName == 'Jiangwenlong' || userName == 'AndsXue' || userName == 'ninazhao'){				
				$('#scheduledDays').attr('readonly',false);
			}else{
                $('#scheduledDays').attr('readonly',true);
			}
			
			$('#new_predict_date').val(newPredictDate);
			$('#urgent_delivery_date').val(urgentDeliveryDate);
			$('#contract_days').text(contractDays);
			$('#original_date').text(originalDeliveryDate?originalDeliveryDate:originalSampleScheduledDate);
			$('.add_tc4').find('button:last').attr('onclick','updateScheduledDays(\''+projectNo+'\',\''+userName+'\',\''+(deliveryDate?deliveryDate:sampleScheduledDate)+'\')');
			$('#new_delivery_date').val(originalDeliveryDate?originalDeliveryDate:originalSampleScheduledDate);
			
           //查询交期修改次数
			$.ajax({
				    type:"post",                   
				    url:"${ctx}/project/queryProjectDetail",           
				    data:{
				    	  projectNo:projectNo
				    	 },              
				    success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  var deliveryList = json.data.deliveryList;
						  if(deliveryList && deliveryList.length!=0){
							  $('#delivery_log').empty();
							  $('#delivery_log').append('<label>修改记录：</label>');
							  for(var i=0;i<deliveryList.length;i++){
								  var time = formatDateTime(deliveryList[i].newDeliveryDate);
								  if(i==0){
									  var div = '<p class="zj_delivery">第'+(i+1)+'次修改合理生产天数：'+deliveryList[i].scheduledDays+'天_'+time+'_'+deliveryList[i].createPerson+'</p>';
									  $('#delivery_log').append(div);
								  }else{
									  var div = '<p>第'+(i+1)+'次修改合理生产天数：'+deliveryList[i].scheduledDays+'天_'+time+'_'+deliveryList[i].createPerson+'</p>';
									  $('#delivery_log').append(div);
								  }							  
							  }				
						   }								 
						 
					  }else{	
						 easyDialog.open({
								container : {
									content : json.message
								},
								overlay : false,
								autoClose : 2000
							});
					  }   
				   }
			 });  		
			
			$('.add_tc4').show();
		} */
		
		
		function edit_delay(userName,projectNo,delayDays,dateDelivery,interpretationDocument){

			$('#projectNo').val(projectNo);
			$('#dateDelivery').val(dateDelivery);
			$('#delayDays').val(delayDays); 
			
			// 2019.9.18  点击修改按钮,添加弹窗拼接
			var div4 = '';
			//获取项目延期信息
			$.ajax({
			    type:"post",                   
			    url:"${ctx}/project/delayMessage",           
			    data:{
			    	  projectNo:projectNo
			    	 }, 
			    dataType : "text",
			    async : false,
			    success:function(str){  
			     var result = eval('(' + str + ')');
				  if(result.ok){
					  //var dateDelivery = result.data.dateDelivery;
					  var delayDay = result.data.delayDay;
					  var explain = result.data.explain;
					  var interpretationDocument = result.data.interpretationDocument;
					 
					  div4 += '<div class="add_tc add_tc4 add__tc_fk">'			
						  if("ninazhao"==userName){	
							  div4 +='<div class="form-group row">'
							div4 += '<label class="col-xs-4">公司同意延后交期日</label>' 
						   div4 += '<input type="hidden" name="userName" id="userName" value="'+userName+'">'
							 div4 += '<div class="col-sm-4 ">'
							div4 += '<div class="input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd" >'
							div4 +='<input class="form-control brt brt_7" size="16" type="text" id="lateDeliveryDate" name="lateDeliveryDate" field="" value=""   placeholder="选择日期" >'			
							div4 +='<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>';						
							div4 +='</div><span></span></div></div>' 	
						    }	
							div4 +='<div class="form-group row">'
							div4 +='<label class="col-xs-4">客户总延期天数</label>'
							div4 +='<div class="col-xs-6"><input type="text" class="form-control" id="delayDays" name="delayDays" value="'+delayDay+'"></div></div>'		
							div4 +='<div class="form-group row">'
							div4 +='<label class="col-xs-4">解释</label>'
							div4 +='<div class="col-xs-8"><textarea class="form-control" id="explain" name="explain" >'+explain+'</textarea></div></div>'		
							div4 +='<div class="form-group row"><div class="col-xs-8 col-xs-offset-4">'					
							div4 +='<p>参考格式:</p><p>7-31 到 8-10,客户要求暂停</p><p>8-15 到 8-20,等待客户确认图纸</p><p>8-15 到 8-20,送样到收到反馈</p><p>注意：</p><p>天数必须能对上邮件日期(会有审核).</p><p>请不要计入再合同签订前的客户原因延迟</p></div></div>'		
							div4 +='<form id="form1" class="roleform form-horizontal" action="/project/selectProjectView">'
							div4 +='<input type="hidden" id="projectNo" name="projectNo" value="'+projectNo+'">'
							div4 +='<input type="hidden" id="fileName" name="fileName">'
							div4 +='<input type="hidden" id="newFileName" name="newFileName">'								
							div4 +='<div class="form-group row add_sc row">'
							div4 +='<label class="col-xs-4">对应证据上传及更新:</label>'
							div4 +='<div class="col-xs-8"> <input type="file" name="file" class="pull-left zj_upload"  onchange="upload(this)" id="file_input">'												
							div4 +='<button class="btn btn-default sc" >点击上传</button>'
							if(interpretationDocument!=null&&interpretationDocument!=''){
							div4 +='<a target="_blank" href="/product_img/'+projectNo+'/'+interpretationDocument+'" class="file_name">证据下载</a>'
							}
							div4 +='<font id="newfile"></font></div></div>'		
							div4 +='<div class="form-group row">'
							div4 +='<label class="col-xs-4">(多个请合并压缩)</label>' 
							div4 +='</div></form>'									
							div4 +='<div class="btns2 cleaffix"><button class="pull-left" onclick="add_tc4Hide(this)">关闭</button>'
							div4 +='<button class="pull-right" onclick="updateExtensionDocument();">提交</button></div></div>'	;								 
					 
					  $('.add_div_tc5').show();
						$('.add_div_tc5').append(div4);	
						$('.add_tc4').show();
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
				  }else{	
					 easyDialog.open({
							container : {
								content : json.message
							},
							overlay : false,
							autoClose : 2000
						});
				  }   
			   }
		 }); 
		
			
			 
			
		}
		
		function add_tc4Hide(object){
			$(object).parents('.add_tc4').remove();
			$('.add_div_tc5').hide();
		};
		
		function add_tc5Hide(object){
			$(object).parents('.add_tc5').hide();
		};
		
		
		//更新项目状态
		function updateProjectStatus(projectNo){
            var userName = $('#userName').val();
	        var projectStatus = $('#editProjectStatus').val();
			var reason = $('#reason').val();
			var time = $('#select_date').val();
			if(projectStatus == 1 || projectStatus == 4 || projectStatus == 5){
				if(!reason){
					easyDialog.open({
						container : {
							content : "理由不能为空"
						},
						overlay : false,
						autoClose : 2000
					});
					return false;
				}
			}
			if(projectStatus == 2 || projectStatus == 4 || projectStatus == 5 || projectStatus == 6){
				if(!time){

					 easyDialog.open({
							container : {
								content : "时间不能为空"
							},
							overlay : false,
							autoClose : 2000
						});
					return false;
				}
			}
			

				$.ajax({
				    type:"post",                   
				    url:"${ctx}/project/updateProjectNew",           
				    data:{
				    	  projectNo:projectNo,
				    	  projectStatus:projectStatus,
				    	  reason:reason,
				    	  time:time,
				    	  userName:userName
				    	 },              
				    success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  easyDialog.open({
								container : {
									content : json.message
								},
								overlay : false,
								autoClose : 1000
							});
						  window.location.reload();
					  }else{

						 easyDialog.open({
								container : {
									content : json.message
								},
								overlay : false,
								autoClose : 2000
							});
					  }   
				   }
			 });  		
			
		}
		
		
		//更新项目生成产周期
		function updateScheduledDays(projectNo,userName,deliveryDate){
            
			//当最新交期没修改的时候，给null
			var newDeliveryDate = $('#new_delivery_date').val();
			if(newDeliveryDate == deliveryDate){
				newDeliveryDate = null;
			}
			
			if(userName == 'Jiangwenlong' || userName == 'AndsXue'  || userName == 'ninazhao'){
	

			}else{
				
// 				if(!newPredictDate){
// 					easyDialog.open({
// 						container : {
// 							content : "请输入预计出货日期"
// 						},
// 						overlay : false,
// 						autoClose : 2000
// 					});
// 					return false;
// 				}		
			}
			var newPredictDate = $('#new_predict_date').val();
			var urgentDeliveryDate = $('#urgent_delivery_date').val();
	        
				$.ajax({
				    type:"post",                   
				    url:"${ctx}/project/updateProjectNew",           
				    data:{
				    	  projectNo:projectNo,
				    	  newDeliveryDate:newDeliveryDate,
				    	  newPredictDate:newPredictDate==null?'':newPredictDate,
				    	  urgentDeliveryDate:urgentDeliveryDate == null ?'':urgentDeliveryDate
				    	 },              
				    success:function(json){  
// 				      var json = eval("(" + data +")");
					  if(json.ok){
						  window.location.reload();
					  }else{

						 easyDialog.open({
								container : {	
									content : json.message
								},
								overlay : false,
								autoClose : 2000
							});
					  }   
				   }
			 });  		
			
		}
		
		

		//同步erp成员
		function synchMember(projectNo){
			
			$.ajax({
			    type:"post",                   
			    url:"${ctx}/project/synchProjectMember",           
			    data:{
			    	  projectNo:projectNo
			    	 },              
			    success:function(json){  
// 			      var json = eval("(" + data +")");
				  if(json.ok){
                      window.location.reload();
				  }else{
					 easyDialog.open({
							container : {
								content : '同步失败'
							},
							overlay : false,
							autoClose : 2000
						});
				  }   
			   }
		 });  	
		}
		
		
		
		
		
		
		
		//增加分批交货设置
		function add_date(projectNo){
			 var deliveryDate = $('#new_date').val();	
			 if(!deliveryDate){
				 easyDialog.open({
						container : {
							content : '请输入交期'
						},
						overlay : false,
						autoClose : 2000
					});
			 }
			
			$.ajax({
			    type:"post",                   
			    url:"${ctx}/project/addProjectSchedule",           
			    data:{
			    	  projectNo:projectNo,
			    	  deliveryDate:deliveryDate
			    	 },              
			    success:function(json){  
// 			      var json = eval("(" + data +")");
				  if(json.ok){                      
					  var li = '<li class="mt10 ">'+
									'<button class="btn del mr5" onclick="del_date('+json.data+',this)">删除</button>'+
									'<span>'+deliveryDate+'</span>'+
								'</li>';
					  $('#date_log').append(li);
					  
				  }else{
					 easyDialog.open({
							container : {
								content : '保存失败'
							},
							overlay : false,
							autoClose : 2000
						});
				  }   
			   }
		 });  							
		}
		
		
		
		
		 /**
		   *删除
		   */
		  function del_date(id,obj){	
				if(!id){
					easyDialog.open({
						container : {
							content : '未查询到id'
						},
						overlay : false,
						autoClose : 2000
					}); 
					return false;
				}
				
				
				
				var btnFn = function(){
					 $.ajax({ 
						   url : "/project/deleteProjectSchedule", 
						   type: "POST", 
						   data : { 
							  id:id 
						   }, 
						   dataType:"json", 
						   success : function(json) { 
							   if(json.ok) 
							    { 
								   easyDialog.open({
										container : {
											content : '删除成功'
										},
										overlay : false,
										autoClose : 2000
									}); 
								   $(obj).parents('li').remove();
							    }else{
								   easyDialog.open({
										container : {
											content : json.message
										},
										overlay : false,
										autoClose : 2000
									}); 
							    }
						   }
					 })
			 };

				easyDialog.open({
				  container : {
				    header : '提示信息',
				    content : '删除该交期？',
				    yesFn : btnFn,
				    noFn : true
				  }
				});
			}
		
		
	//跳转选择页码
	function jump_page(obj){
		var page = $(obj).prev().val();
		if(!page){
			 easyDialog.open({
					container : {
						content : '请输入跳转页码'
					},
					overlay : false,
					autoClose : 2000
				});
			$(obj).prev().blur();
		}else{
// 			$('#form2').attr('action','/project/showListNew?pageNumber='+page+'');
			searchProjectData(page);
		}
		
	}
		 
		 
/*   window.onload=function(event){
	    var button=document.getElementsByName("copyBtn");
	    button.onclick=function(event){
	        document.getElementById("foo").select();
	        document.execCommand("copy",false,null);
	        layer.msg("复制成功",{time:2000});
	    };
  }; */
  
  /**
   *复制工厂上传链接
   */
  function copyBtn(obj,projectNo,factoryId){
	  
	 //工厂id base64加密 
	 var base64 = new Base64();
	 factoryId = base64.encode(factoryId);
	 $(obj).next().text("http://www.kuaizhizao.cn/report/reportList?csgOrderId="+projectNo+"&supplierId="+factoryId);	  
	 var $parm = $(obj).next();
	 var parm = $parm.get(0);
	 parm.select();
	 document.execCommand("copy",false,null);
	 easyDialog.open({
			container : {
				content : '复制成功'
			},
			overlay : false,
			autoClose : 2000
		});
  }
  
  
  
  //里程碑方法
  
  
  //添加一行
  $('.add_line').click(function(){
	  
// 	  var s = $('.wrap_milestones .milestone:first-child').clone(true);	  
      var tl = $('.milestone').length;
	  if(tl>9){
      	easyDialog.open({
				container : {
					content : '最多填写10个里程碑'
				},
				overlay : false,
				autoClose : 2000
			});
			flag = false;
			return fasle;
      }
	  
	  
	  var div = '<div class="milestone"><div class="row"> <label class="mr10 z-t z-t_num">里程碑1</label> <input type="text" class="form-control content" placeholder="请简要输入里程碑内容" value=""></div><div class="row"><label class="mr10 z-t">里程碑1日期</label> <div class="input-group date form_date col-sm-6 content" data-date="" data-date-format="yyyy-mm-dd"><input class="form-control brt brt_7" size="16" type="text" style="width: 157px;" placeholder="选择日期" readonly="" value=""> <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span></div></div><button class="btn btn-primary del_btn" onclick="del_milestone(this)" disabled="disabled">删除</button></div>';  
	  $('.wrap_milestones').append(div);	
	  cal_name();
	  $('.z-t_num').each(function(){
		  var this_text = $(this).text();
		  if(this_text == ' 里程碑1' || this_text == '里程碑2'){
			  $(this).parent().siblings('.del_btn').attr("disabled","disabled");
		  }else if(this_text != '里程碑1' && this_text != '里程碑2'){
			  $(this).parent().siblings('.del_btn').removeAttr("disabled");
		  }
	  })
	    
	  
	  
	  
	 //  $(this).before($(this).prev().clone());	 	   
  })

   //删除一行
   
  function del_milestone(obj){		  
	  
	  var tl = $('.milestone').length;
      if(tl<3){
      	easyDialog.open({
				container : {
					content : '请至少填写两个里程碑'
				},
				overlay : false,
				autoClose : 2000
			}); 
			// alert('至少2条里程碑')
			flag = false;
			return false;
      }
	  
   	  $(obj).parent().remove();	
	  cal_name();
  }
  //关闭里程碑修改
  function closeMilestones(){
	  $('.z-tb').hide();
  }
  
  //打开里程碑弹框
  function openMilestone(projectNo,index){	  
	  
	  
	  
	  
	  $('.z-tb').find('input[name="projectNo"]').val(projectNo);
	  $('.z-tb').find('input[name="index"]').val(index);
	  
	  $.ajax({ 
		   url : "/milestone/selectByProjectNo", 
		   type: "POST", 
		   data : { 
			   projectNo:projectNo 
		   }, 
		   dataType:"json", 
		   success : function(json) { 
			   if(json.ok) 
			    { 
				    var milestones = json.data;
				    $(".milestone:not(':first')").remove();
				    if(milestones && milestones.length>0){				    	
				    	var div = '';
				    	for(var i=0;i<milestones.length;i++){
				    		 var div = '<div class="milestone" filed1="'+milestones[i].uid+'" filed2="'+milestones[i].processInstanceId+'">'+
												'<div class="row"> '+
											'<label class="mr10 z-t z-t_num">里程碑'+(i+1)+'</label> '+
										    '<input type="text" class="form-control content" placeholder="请简要输入里程碑内容" value="'+milestones[i].milestoneName+'">'+
										'</div>'+					
										'<div class="row">'+
											'<label class="mr10 z-t">里程碑'+(i+1)+'日期</label> '+
											'<div class="input-group date form_date col-sm-6 content" data-date="" data-date-format="yyyy-mm-dd">'+
												'<input class="form-control brt brt_7" size="16" type="text" style="width: 157px;" placeholder="选择日期" readonly value="'+formatDateTime(milestones[i].milestoneDate)+'"> <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>'+
											'</div>'+
										'</div>'+	
										'<button class="btn btn-primary del_btn" onclick="del_milestone(this)">删除</button>'+	
									'</div>';
									
							$('.wrap_milestones').append(div);
													
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
				    }					    
				    $('.z-tb').show();
				    $('.z-t_num').each(function(){
				    	var thisText = $(this).text();
				    	if(thisText == '里程碑1' || thisText == '里程碑2'){
			    		 	$(this).parent().siblings('.del_btn').attr("disabled","disabled");
				    	}
				    });
				    

			    }
		   }
	 })
	  
	
  }
  
  //保存里程碑
  function addMilestones(){
		//获取里程碑
		var flag = true;
        var milestoneList = [];
        var tl = $('.milestone').length;
        if(tl<2){
        	easyDialog.open({
				container : {
					content : '请至少填写两个里程碑'
				},
				overlay : false,
				autoClose : 2000
			}); 
			// alert('至少2条里程碑')
			flag = false;
			return false;
        }
        if(tl>10){
        	easyDialog.open({
				container : {
					content : '最多填写10个里程碑'
				},
				overlay : false,
				autoClose : 2000
			});
			flag = false;
			return fasle;
        }
        var projectNo = $('.z-tb').find('input[name="projectNo"]').val();
        var index = $('.z-tb').find('input[name="index"]').val();
		$('.milestone').each(function(){
			var uid = $(this).attr('filed1');
			var processInstanceId = $(this).attr('filed2');
			if(!processInstanceId){
				processInstanceId = '';
			}
			if(!uid){
				uid = '';
			}
			var milestoneName = $(this).find('.content').val();
			var milestoneDate = $(this).find('input:last').val();
			if(!milestoneName || !milestoneDate){
				easyDialog.open({
					container : {
						content : '里程碑名和里程碑日期不能为空'
					},
					overlay : false,
					autoClose : 2000
				});
				flag = false;
				return fasle;
			}
			var milestone = {"milestoneName":milestoneName,"milestoneDate":milestoneDate,"uid":uid,"processInstanceId":processInstanceId,"projectNo":projectNo};
			milestoneList.push(milestone);
		})
		
       $.ajax({ 
		   url : "/milestone/add", 
		   type: "POST", 
		   data : { 
			   milestoneList:JSON.stringify(milestoneList) 
		   }, 
		   dataType:"json", 
		   success : function(json) { 
			   if(json.ok) 
			    { 
				   cal_milestone(Number(index),milestoneList);
				   closeMilestones();
			    }else{
				   easyDialog.open({
						container : {
							content : json.message
						},
						overlay : false,
						autoClose : 2000
					}); 
			    }
		   }
		})
  }
  
  
  
  function cal_name(){
	  $('.milestone').each(function(i){
		  $(this).find('label:first').text('里程碑'+(i+1));
		  $(this).find('label:eq(1)').text('里程碑'+(i+1)+'日期');
	  })
	  
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
  }

  //异步修改里程碑
  function cal_milestone(index,milestones){
	 $('.table1').eq(index+1).find('tr').find('td:eq(8)').empty();
	 var ss='';
	 var projectNo='';
	 for(var i=0;i<milestones.length;i++){
		  var s = '';
		  //判断当前处于里程碑什么阶段
		  var b = compareNow(''+milestones[i].milestoneDate+'');
		  if(i==0){			 
			  if(b){
				  var s = '<span>'+milestones[i].milestoneDate+'</span>'+
		          '<p>'+milestones[i].milestoneName+'</p>'; 
			  }else{
				  var s = '<span style="color: #27cb38;">'+milestones[i].milestoneDate+'</span>'+
		          '<p style="color: #27cb38;">'+milestones[i].milestoneName+'</p>';
			  }
		  }
		  if(i>0 && i< milestones.length){
			  var b1 = compareNow(''+milestones[i-1].milestoneDate+'');
			  if(b1 && !b){
				  var s = '<span style="color: #27cb38;">'+milestones[i].milestoneDate+'</span>'+
		          '<p style="color: #27cb38;">'+milestones[i].milestoneName+'</p>'; 
			  }else{
				  var s = '<span>'+milestones[i].milestoneDate+'</span>'+
		          '<p>'+milestones[i].milestoneName+'</p>';
			  }
		  }

		  ss +=s;
		  projectNo = milestones[0].projectNo;	
		  }
	 ss +='<a onclick="openMilestone(\''+projectNo+'\',\''+index+'\')">修改里程碑</a>';
	 $('.table1').eq(index+1).find('tr').find('td:eq(8)').append(ss);
  }
</script>

<script>
  // 项目列表图片垂直居中
/*     $('#table1_tbody tr').each(function(){  	 
	  var pic_width = $(this).find('img').width();
	  var pic_height = $(this).find('img').height();
	  var mg_top = (172 - pic_height)/2 + 'px';
	  $(this).find('.imgs').css({'padding-top':mg_top});
  	});  */
  	
  	
  	
  	
  	
  	
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
  		}

  	//两个string 日期比较
  	function compareDate(s1,s2){
  	  return ((new Date(s1.replace(/-/g,"\/")))>(new Date(s2.replace(/-/g,"\/"))));
  	}
  	//和当前日期比较
  	function compareNow(s){  		
  	  return ((new Date())>(new Date(s.replace(/-/g,"\/"))));
  	}
  	/*添加链接  */
  	function openUrl( projectNo, factoryId, type) {

		var base64 = new Base64();
		var factoryIdEncrypt = base64.encode(factoryId);
		if (type == 1) {
			window.open("http://www.kuaizhizao.cn/report/reportList?csgOrderId="
							+ projectNo + "&supplierId=" + factoryIdEncrypt);
		}
		
		
		if (type == 2) {
			window.open(
					"https://www.kuaizhizao.cn/report/reportListPurchase?csgOrderId="
							+ projectNo + "&supplierId=" + factoryId
							+ "&factoryId=0&realName=ninazhao");
		}
		
	}
  	
  	function updateExtensionDocument(){
  		var fileName=$("#newFileName").val();	 
	   	var projectNo=$("#projectNo").val();	 
	   	var dateDelivery=$("#dateDelivery").val();	 
	   	var delayDays=$("#delayDays").val();	 
	   	var explain=$("#explain").val();
	   	var lateDeliveryDate=$("#lateDeliveryDate").val();
	   	var userName=$("#userName").val();
	   	if(userName=="ninazhao"){
	   		$.ajax({ 
	 		   url : "/project/updateExtensionDocument", 
	 		   type: "POST", 
	 		   data : { 
	 			   projectNo:projectNo,
	 			  lateDeliveryDate:lateDeliveryDate
	 			  }, 
	 		   dataType:"json", 
	 		   success : function(json) { 
	 			   if(json.ok) 
	 			    { 
	 				   $('.add_tc4').hide(); 
	 			
	 			    }else{
	 			      layer.msg(json.message,{time:2000});
	 			    }
	 		   }
	 		})
	   	}else{
	   	if(delayDays!=0){
	   	if(fileName==null || fileName.replace(/^\s\s*/, '').replace(/\s\s*$/, '').length==0){
			alert("存在客户延期天数，要求必须上传相应证据");
			return false;
		}
	   	}
	   	
	   	explain = explain.replace(/\%/g,"%25").replace(/\#/g,"%23").replace(/\&/g,"%26").replace(/\+/g,"%2B");
       $.ajax({ 
		   url : "/project/updateExtensionDocument", 
		   type: "POST", 
		   data : { 
			   projectNo:projectNo,
			   fileName:fileName,
			   dateDelivery:dateDelivery,
			   delayDays:delayDays,
			   explain:explain
			   
		   }, 
		   dataType:"json", 
		   success : function(json) { 
			   if(json.ok) 
			    { 
				   $('.add_tc4').hide(); 
			
			    }else{
			      layer.msg(json.message,{time:2000});
			    }
		   }
		})
	   	}
 }
  	
  	
  //上传方法
	function upload(obj) {
		var n = $(obj).get(0).files[0].name;
		$('.file_name').text(n);
		
		var fileNames = $(obj).val();

		if (fileNames == null || fileNames == '' || fileNames == undefined) {
			return false;
		} else {
			
			
		}

		// 先上传后获取上传文件路径
		$('#form1').ajaxSubmit({
			type : "post",
			url : "${ctx}/project/uploadInterpretationDocument",
			dataType : "text",
			async : false,
			success : function(str) {
				var result = eval('(' + str + ')');
				if (result.ok) {
					var newFileName = result.data.newFileName;
					var fileName = result.data.originalFilename;
					$('#newFileName').val(newFileName);
					$('#fileName').val(fileName);
					document.getElementById("newfile").innerHTML=fileName;
				} else {
					layer.msg('上传失败', {
						time : 2000
					});
				}
			},
			error : function() {
				layer.msg('上传失败', {
					time : 2000
				});
				
			}
		});
	}
  
  
  
</script>
</html>






















