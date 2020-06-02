<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>

<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${ctx}/css/test.css">
    <script src="../js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
    <title>最近两个月客户项目列表</title>
    <style type="text/css">
.tab_conbox{border: 1px solid #999;border-top: none;margin: 0 0;
padding: 10px;min-height:600px;}
.tab_con {padding:12px;font-size: 14px; line-height:175%;}
.easyui-table{width:100%;border-right:1px solid #A0DAFB;border-bottom:1px solid #A0DAFB; text-align:center;}
.easyui-table th,.easyui-table td{border-left:1px solid #A0DAFB;border-top:1px solid #A0DAFB; text-align:center;}
.easyui-table th{background:#e2e2e2;}
.require{text-align:center;padding:15px 5px 5px;border-top:1px #e2e2e2 solid;}
.tab_xinix{width:100%;}
.tab_xinix td{padding:8px 20px;font-size: 14px; text-align:left;}
.select_blank{background-color:#027CFF;padding: 7px 12px;text-decoration:none;
 color:#fff;border-radius: 2px;font-size: 15px;}

.select_blank:hover,.select_blank:hover{text-decoration:none;background-color:#4362C5;color:#fff;}
.fen{padding:0 20px 20px 20px;}
.page-box{padding-left:20px;padding-bottom:20px;}
</style>
</head>
<body class="list-bgcolor">
	<div class="main-container">
		<div><h3>最近两个月客户项目列表
		<a class="select_blank" target="_blank" href="/user/toIndex?userId=${userId}&roleNo=${roleNo}&purchaseNameId=${purchaseNameId}&userName=${userName}">返回功能选择页</a>
		</h3></div>
		
		   <p>
		      <div class="fen">
      
	  
   
		<table id="tt" class="easyui-table" title="Load Data"
					 border="0"
					cellspacing="0" cellpadding="0">
					<thead>
						<tr>
						
						    <th field="time" width="100" align="right">项目号</th>
							 <th field="large" width="100" align="center">项目名</th>
							<th field="customer1" width="100" align="left">立项时间</th>
						     <th field="customer2" width="100" align="left">质量分析表</th>
						     <th field="customer3" width="100" align="left">详情</th>
						</tr>
						
							<tr>
				<c:forEach items="${projectTaskList}" var="projectTask">
			                <!-- 接收到的任务 -->
			                
			                <c:if test="${roleNo!=100 and roleNo!=99 and roleNo!=98 and roleNo!=97}"> 
					             <tr class="project-flag">
					                
					                <td width="100">
					                    <div>${projectTask.projectNo}</div>
					                 </td>
					                 <td width="100">${projectTask.projectName}</td>
					               <td  width="100">
					                  <fmt:formatDate value="${projectTask.createDate}" pattern="yyyy-MM-dd"/> 
					                </td>
					                
					               <td  width="100">
					               <c:if test="${projectTask.createTime !=''}">
					            ${projectTask.createTime} </c:if>
					             <c:if test="${projectTask.createTime==''}">
					             <a href="${ctx}/qualityAnalysisTable/qualityAnalysis?userId=${userId}&userName=${userName}&projectNo=${projectTask.projectNo}">金属模板录入</a> </c:if>
					               &nbsp; &nbsp; &nbsp;<a href="" class="zs">塑料模板录入</a>
					               </td>
					                <td width="100" class="mession-status">
					                    <div> 
					                      <a href="${ctx}/qualityAnalysisTable/detailsPage?userId=${userId}&userName=${userName}&projectNo=${projectTask.projectNo}" target=_blank>详情操作</a>
		                                </div>
					                </td>
					            </tr>
				            </c:if>
				            
				           
				            <!-- 企业管理员查看所有用户 -->
				            <c:if test="${roleNo!=100 or roleNo!=99 or roleNo!=98 or roleNo!=97}"> 
					              <tr class="project-flag">
					                
					                <td width="100">
					                    <div>${projectTask.projectNo}</div>
					                 </td>
					                 <td width="100">${projectTask.projectName}</td>
					               <td  width="100">
					                  <fmt:formatDate value="${projectTask.createDate}" pattern="yyyy-MM-dd"/> 
					                </td>
					                
					               <td  width="100">
					               <c:if test="${projectTask.createTime !=''}">
					             ${projectTask.createTime} </c:if>
					             <c:if test="${projectTask.createTime==''}">
					                 <a href="${ctx}/qualityAnalysisTable/qualityAnalysis?userId=${userId}&userName=${userName}&projectNo=${projectTask.projectNo}" target=_blank>请录入</a> </c:if>
					               </td>
					                <td width="100" class="mession-status">
					                    <div> 
					                      <a href="${ctx}/qualityAnalysisTable/detailsPage?userId=${userId}&userName=${userName}&projectNo=${projectTask.projectNo}" target=_blank>详情操作</a>
		                                </div>
					                </td>
					            </tr>
				            </c:if>
			            </c:forEach>
						
                              </tr>
							 
					
					</thead>
				</table>	  
   </div>		    
			<div class="page-box">总数:&nbsp;${totalCount}&nbsp;每页:&nbsp;10条  &nbsp;当前页/总页:<span style='color:red'>${pageNumber}</span>/<span style='color:red'>${countPage}</span>&nbsp; 
		    <a href="#" class='emanagergetpagea first-padding' onclick="searchProjectData(1)" title='第一页' class='a02'>首页</a>
		    <a href="#" class='emanagergetpagea first-padding' onclick="searchProjectData(2)" title='上一页(第1页)' class='a02'>上页</a>
		    <a href="#" class='emanagergetpagea' onclick="searchProjectData(3)" title='下一页(第3页)' class='a02'>下页</a>
		    <a href="#" class='emanagergetpagea' onclick="searchProjectData(4)" title='最后一页' class='a02'>尾页</a>
	        </div>
	        <input type="hidden" id="pageNumber" name="pageNumber" value="${pageNumber}" >
	        <input type="hidden" id="countPage" name="countPage" value="${countPage}" >
        </div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$('.project-flag').each(function(i){
		var project_flag=$(this).find('input[name="taskFlag"]').val();
		if(project_flag==1){
			//$(this).css("border","1px solid red");
			$(this).css("border","1px solid red").find("td").css("border-bottom","none").end().find("td:first").css("border-left","none");
			$(this).prev("tr").find("td").css("border-bottom","none");
		}
	})
})
$(function(){
	var userName=$("#userName").val();
	if(userName==null || userName=='' ||userName==undefined ||userName=="null"){
		$("#goBackHtml").hide();
	}
})


//进入项目统计界面
$("#projectSummary").click(function(){
	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 window.location.href="${ctx}/jsp/project-summary.jsp?roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
})

//进入会议列表
function projectMeetingList(){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	window.location.href="${ctx}/meetingRecord/selectMeetingRecordList?roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
}
//进入录入任务页面
function addProjectTask(){
	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 window.location.href="${ctx}/jsp/input_project_task.jsp?roleNo="+roleNo+"&userId="+userId+"&userName="+userName;
}
function statisticsProjectTask(){
	 window.location.href="${ctx}/projectTask/statisticsProjectTask";
}

function goBack(){
	var roleNo=$("#roleNo").val();
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var purchaseNameId="";
	window.location.href="${ctx}/jsp/project_list.jsp?userId="+userId+"&roleNo="+roleNo+"&purchaseNameId="+purchaseNameId+"&userName="+encodeURI(encodeURI(userName));	
}

function selectOnchange(obj){
	var taskStatus=$(obj).find('option:selected').val();
	$("#taskStatus").val(taskStatus);
}

//筛选列表
function searchProjectData(obj){
	 var searchName=$("#searchName").val();
	 var projectNo=$("#searchProjectNo").val();
	 var pageNumber=$("#pageNumber").val();
	 var countPage=$("#countPage").val();
	 var taskStatus=$("#taskStatus").val();
	 var type=obj;
	 var roleNo=$("#roleNo").val();
	 var userId=$("#userId").val();
	 var userName=$("#userName").val();
	 // 1 第一页  2.上一页  3.下一页 4.尾页
	 if(type==1){
		 pageNumber=1; 
	 }
	 if(type==2){//上一页
		 if(pageNumber==1){
			 pageNumber=1
		 }else{
			 pageNumber= Number(pageNumber)-1;
		 }
	 }
	 if(type==3){//下一页
		 if(pageNumber==countPage){
			 pageNumber=countPage;
		 }else{
			 pageNumber=Number(pageNumber)+1; 
		 }
	 }
	 if(type==4){//尾页
		 pageNumber=countPage;
	 }
	 window.location.href="${ctx}/qualityAnalysisTable/listItems?roleNo="+roleNo+"&searchName="+searchName+"&pageNumber="+pageNumber+"&taskStatus="+taskStatus+"&userName="+userName+"&roleNo="+roleNo+"&userId="+userId;
}
</script>
<script>
	
</script>
</html>













