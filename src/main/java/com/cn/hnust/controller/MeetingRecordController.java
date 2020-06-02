	package com.cn.hnust.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.cn.hnust.component.RpcHelper;
import com.cn.hnust.pojo.InspectionPlanInfo;
import com.cn.hnust.pojo.MeetingRecord;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectComplaint;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.ProjectInspectionReport;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.pojo.QuotePrice;
import com.cn.hnust.pojo.QuotePrice1;
import com.cn.hnust.pojo.ShippingConfirmation;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.ErpReportService;
import com.cn.hnust.service.IInspectionPlanInfoService;
import com.cn.hnust.service.IMeetingRecordService;
import com.cn.hnust.service.IProjectInspectionReportService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.IQualityReportService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.service.MQProducerService;
import com.cn.hnust.service.ProjectComplaintService;
import com.cn.hnust.service.ProjectFactoryService;
import com.cn.hnust.service.QuotePriceService;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.PropertiesUtils;
import com.cn.hnust.util.SerializeUtil;
import com.cn.hnust.util.WebCookie;

@Controller
@RequestMapping("/meetingRecord")
public class MeetingRecordController {
	@Autowired
	private IQualityReportService qrService;
	@Autowired
	private IMeetingRecordService meetingRecordService;
	@Autowired
	private IProjectInspectionReportService projectInspectionReportService;
	@Autowired
	private IProjectTaskService projectTaskService;
	@Autowired
	private ProjectFactoryService projectFactoryService;
	@Autowired
	private IInspectionPlanInfoService inspectionPlanInfoService;
	
	@Autowired
	private IUserService userService; 
	
	@Autowired
	private ProjectComplaintService projectComplaintService;
	
	@Autowired
	private MQProducerService mqProducerService;
	@Autowired
	private QuotePriceService quotePriceService;
	@Autowired
	private IProjectService projectService;
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	private static final Log LOG = LogFactory.getLog(MeetingRecordController.class);
	/**
	 * edit
	 * 修改发任务可以通过@多个人员发送任务
	 * @Title addMeetingRecord 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/addMeetingRecord")
	@ResponseBody
	public JsonResult addMeetingRecord(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
		String projectNo=request.getParameter("projectNo");
		String meetingName=request.getParameter("meetingName");
		String meetingDescribe=request.getParameter("meetingDescribe");
		String initiator=request.getParameter("initiator");
	    String fileName = request.getParameter("fileName");
	    String filePath = request.getParameter("filePath");
	    String technician = request.getParameter("technician");
	    Integer reportId = null;                                //质检报告id
	    if(StringUtils.isNotBlank(request.getParameter("reportId"))){
	    	reportId = Integer.parseInt(request.getParameter("reportId"));
	    }
		List<ProjectTask> projectTaskList=new ArrayList<ProjectTask>();
	    
		String meetingTask=request.getParameter("meetingTask");
	    
	    String meetingTask1=request.getParameter("meetingTask1");
	    
	    String meetingTask2=request.getParameter("meetingTask2");
	    
	    String meetingTask3=request.getParameter("meetingTask3");
	    
	    String meetingTask4=request.getParameter("meetingTask4");
	    
	    String meetingTask5=request.getParameter("meetingTask5");

	    String meetingTask6=request.getParameter("meetingTask6");
   
	    String meetingTask7=request.getParameter("meetingTask7");

	    String meetingNo= IdGen.uuid(); 
	    if(StringUtils.isNotBlank(meetingTask)){
	    	//任务是否需要视频
	    	Integer isVideo = 0;
	    	if(StringUtils.isNotBlank(request.getParameter("isVideo"))){
	    		isVideo = Integer.parseInt(request.getParameter("isVideo"));
	    	}
	    			
	    	
    		//去除内容中人员信息
    		String[] strs = null;
    		if(meetingTask.contains("\n")){
    			strs = meetingTask.split("\n");	
    		}else{
    			strs = meetingTask.split("\n");	
    		}
    		if(meetingTask.contains("\n\r")){
    			strs = meetingTask.split("\n\r");	
    		}else{
    			strs = meetingTask.split("\n\r");	
    		}
    		if(strs!= null){
    			for(int j = 0; j < strs.length; j++){
			    	//去除内容中人员信息
		    		String[] split = meetingTask.split("@");
		    		if(split.length <= 1){
		    			jsonResult.setOk(false);
						jsonResult.setMessage("请输入任务接受人");
						return jsonResult;
		    		}
		    		meetingTask = split[0];
			    	//接收人
			    	for (int i = 0; i < split.length; i++) {
			    		if(i > 0){
			    			ProjectTask projectTask=new ProjectTask();
					    	projectTask.setProjectNo(projectNo);
					    	projectTask.setInitiator(initiator);
					    	projectTask.setAccepter(split[i].trim());
					    	projectTask.setTaskStatus("0");
					    	projectTask.setDescription(meetingTask);
					    	projectTask.setTaskType("1");
					    	projectTask.setCreateTime(new Date());
					    	projectTask.setStartTime(new Date());
					     	projectTask.setFinishTime(getDateAfter(new Date(),2));
					    	projectTask.setMeetingNo(meetingNo);
					    	projectTask.setIsVideo(isVideo);
					    	
					    	User user=userService.findUserByName(split[i].trim());
					    	if(user!=null){
					    		projectTaskList.add(projectTask);
					    	}
			    	 }		    	
				  }	
    		  }
    	   }
	    }
	    
	    if(StringUtils.isNotBlank(meetingTask1)){
	    	//任务是否需要视频
	    	Integer isVideo1 = 0;
	    	if(StringUtils.isNotBlank(request.getParameter("isVideo1"))){
	    		isVideo1 = Integer.parseInt(request.getParameter("isVideo1"));
	    	}
	    	
	    	//去除内容中人员信息
    		String[] strs = null;
    		if(meetingTask1.contains("\n")){
    			strs = meetingTask1.split("\n");	
    		}else{
    			strs = meetingTask1.split("\n");
    		}
    		if(meetingTask1.contains("\n\r")){
    			strs = meetingTask1.split("\n\r");	
    		}else{
    			strs = meetingTask1.split("\n\r");
    		}
    		if(strs!= null){
    			for(int j = 0; j < strs.length; j++){
			    	//去除内容中人员信息
		    		String[] split = meetingTask1.split("@");
		    		if(split.length <= 1){
		    			jsonResult.setOk(false);
						jsonResult.setMessage("请输入任务接受人");
						return jsonResult;
		    		}
		    		meetingTask1 = split[0];
			    	//接收人
			    	for (int i = 0; i < split.length; i++) {	    	
			    		if(i > 0){
				    	ProjectTask projectTask=new ProjectTask();
				    	projectTask.setProjectNo(projectNo);
				    	projectTask.setInitiator(initiator);
				    	projectTask.setAccepter(split[i].trim());
				    	projectTask.setDescription(meetingTask1);
				    	projectTask.setTaskStatus("0");
				    	projectTask.setTaskType("1");
				    	projectTask.setCreateTime(new Date());
				    	projectTask.setStartTime(new Date());
				    	projectTask.setFinishTime(getDateAfter(new Date(),5));
				    	projectTask.setMeetingNo(meetingNo);
				    	projectTask.setIsVideo(isVideo1);
				    	
				    	User user=userService.findUserByName(split[i].trim());
				    	if(user!=null){
				    		projectTaskList.add(projectTask);
				    	}
			    	  }
			    	}
	            }
    	    }		
	    }
	    
	    if(StringUtils.isNotBlank(meetingTask2)){
	    	
	    	//任务是否需要视频
	    	Integer isVideo2 = 0;
	    	if(StringUtils.isNotBlank(request.getParameter("isVideo2"))){
	    		isVideo2 = Integer.parseInt(request.getParameter("isVideo2"));
	    	}
	    	//去除内容中人员信息
    		String[] strs = null;
    		if(meetingTask2.contains("\n")){
    			strs = meetingTask2.split("\n");	
    		}else{
    			strs = meetingTask2.split("\n");
    		}
    		if(meetingTask2.contains("\n\r")){
    			strs = meetingTask2.split("\n\r");	
    		}else{
    			strs = meetingTask2.split("\n\r");
    		}
    		if(strs!= null){
    			for(int j = 0; j < strs.length; j++){
			    	//去除内容中人员信息
		    		String[] split = meetingTask2.split("@");
		    		if(split.length <= 1){
		    			jsonResult.setOk(false);
						jsonResult.setMessage("请输入任务接受人");
						return jsonResult;
		    		}
		    		meetingTask2 = split[0];
			    	//接收人
			    	for (int i = 0; i < split.length; i++) {	   
			    		if(i > 0){
					    	ProjectTask projectTask=new ProjectTask();
					    	projectTask.setProjectNo(projectNo);
					    	projectTask.setTaskStatus("0");
					    	projectTask.setTaskType("1");
					    	projectTask.setInitiator(initiator);
					    	projectTask.setAccepter(split[i].trim());
					    	projectTask.setDescription(meetingTask2);
					    	projectTask.setCreateTime(new Date());
					    	projectTask.setStartTime(new Date());
					    	projectTask.setFinishTime(getDateAfter(new Date(),5));
					    	projectTask.setMeetingNo(meetingNo);
					    	projectTask.setIsVideo(isVideo2);
					    	
					    	User user=userService.findUserByName(split[i].trim());
					    	if(user!=null){
					    		projectTaskList.add(projectTask);
					    	}
			    		}
			        }
    			}
    		}	
	    }
	    
	    if(StringUtils.isNotBlank(meetingTask3)){
	    	
	    	//任务是否需要视频
	    	Integer isVideo3 = 0;
	    	if(StringUtils.isNotBlank(request.getParameter("isVideo3"))){
	    		isVideo3 = Integer.parseInt(request.getParameter("isVideo3"));
	    	}
	    	
	    	//去除内容中人员信息
    		String[] strs = null;
    		if(meetingTask3.contains("\n")){
    			strs = meetingTask3.split("\n");	
    		}else{
    			strs = meetingTask3.split("\n");
    		}
    		if(meetingTask3.contains("\n\r")){
    			strs = meetingTask3.split("\n\r");	
    		}else{
    			strs = meetingTask3.split("\n\r");
    		}
    		if(strs!= null){
    			for(int j = 0; j < strs.length; j++){
			    	//去除内容中人员信息
		    		String[] split = meetingTask3.split("@");
		    		if(split.length <= 1){
		    			jsonResult.setOk(false);
						jsonResult.setMessage("请输入任务接受人");
						return jsonResult;
		    		}
		    		meetingTask3 = split[0];
			    	//接收人
			    	for (int i = 0; i < split.length; i++) {	 
			    		if(i > 0){
				    	ProjectTask projectTask=new ProjectTask();
				    	projectTask.setProjectNo(projectNo);
				    	projectTask.setTaskStatus("0");
				    	projectTask.setTaskType("1");
				    	projectTask.setInitiator(initiator);
				    	projectTask.setAccepter(split[i].trim());
				    	projectTask.setDescription(meetingTask3);
				    	projectTask.setCreateTime(new Date());
				    	projectTask.setStartTime(new Date());
				    	projectTask.setFinishTime(getDateAfter(new Date(),5));
				    	projectTask.setMeetingNo(meetingNo);
				    	projectTask.setIsVideo(isVideo3);
				    	
				    	User user=userService.findUserByName(split[i].trim());
				    	if(user!=null){
				    		projectTaskList.add(projectTask);
				    	}
			    	 }
			       }	
    			}
    		}	
	    }
	    if(StringUtils.isNotBlank(meetingTask4)){
	    	
	    	//任务是否需要视频
	    	Integer isVideo4 = 0;
	    	if(StringUtils.isNotBlank(request.getParameter("isVideo4"))){
	    		isVideo4 = Integer.parseInt(request.getParameter("isVideo4"));
	    	}
	    	//去除内容中人员信息
    		String[] strs = null;
    		if(meetingTask4.contains("\n")){
    			strs = meetingTask4.split("\n");	
    		}else{
    			strs = meetingTask4.split("\n");
    		}
    		if(meetingTask4.contains("\n\r")){
    			strs = meetingTask4.split("\n\r");	
    		}else{
    			strs = meetingTask4.split("\n\r");
    		}
    		if(strs!= null){
	    			for(int j = 0; j < strs.length; j++){
				    	//去除内容中人员信息
			    		String[] split = meetingTask4.split("@");
			    		if(split.length <= 1){
			    			jsonResult.setOk(false);
							jsonResult.setMessage("请输入任务接受人");
							return jsonResult;
			    		}
			    		meetingTask4 = split[0];
				    	//接收人
				    	for (int i = 0; i < split.length; i++) {	 
				    	if(i>0){
				    		ProjectTask projectTask=new ProjectTask();
					    	projectTask.setProjectNo(projectNo);
					    	projectTask.setInitiator(initiator);
					    	projectTask.setTaskStatus("0");
					    	projectTask.setTaskType("1");
					    	projectTask.setAccepter(split[i].trim());
					    	projectTask.setDescription(meetingTask4);
					    	projectTask.setCreateTime(new Date());
					    	projectTask.setStartTime(new Date());
					    	projectTask.setFinishTime(getDateAfter(new Date(),5));
					    	projectTask.setMeetingNo(meetingNo);
					    	projectTask.setIsVideo(isVideo4);
					    	
					    	User user=userService.findUserByName(split[i].trim());
					    	if(user!=null){
					    		projectTaskList.add(projectTask);
					    	}
				    	}	    	
		            }
	    		}
    	  }		
	    }
	    if(StringUtils.isNotBlank(meetingTask5)){
	    	
	    	//任务是否需要视频
	    	Integer isVideo5 = 0;
	    	if(StringUtils.isNotBlank(request.getParameter("isVideo5"))){
	    		isVideo5 = Integer.parseInt(request.getParameter("isVideo5"));
	    	}
	    	//去除内容中人员信息
    		String[] strs = null;
    		if(meetingTask5.contains("\n")){
    			strs = meetingTask5.split("\n");	
    		}else{
    			strs = meetingTask5.split("\n");
    		}
    		if(meetingTask5.contains("\n\r")){
    			strs = meetingTask5.split("\n\r");	
    		}else{
    			strs = meetingTask5.split("\n\r");
    		}
    		if(strs!= null){
    			for(int j = 0; j < strs.length; j++){
		    	//去除内容中人员信息
	    		String[] split = meetingTask5.split("@");
	    		if(split.length <= 1){
	    			jsonResult.setOk(false);
					jsonResult.setMessage("请输入任务接受人");
					return jsonResult;
	    		}
	    		meetingTask5 = split[0];
		    	//接收人
		    	for (int i = 0; i < split.length; i++) {	 
			    	if(i>0){
			    		ProjectTask projectTask=new ProjectTask();
				    	projectTask.setProjectNo(projectNo);
				    	projectTask.setInitiator(initiator);
				    	projectTask.setTaskStatus("0");
				    	projectTask.setTaskType("1");
				    	projectTask.setAccepter(split[5].trim());
				    	projectTask.setDescription(meetingTask5);
				    	projectTask.setCreateTime(new Date());
				    	projectTask.setStartTime(new Date());
				    	projectTask.setFinishTime(getDateAfter(new Date(),5));
				    	projectTask.setMeetingNo(meetingNo);
				    	projectTask.setIsVideo(isVideo5);
				    	
				    	
				    	User user=userService.findUserByName(split[i].trim());
				    	if(user!=null){
				    		projectTaskList.add(projectTask);
				    	}
			    	}	    
		       }
    		}
    	  }		
	    }
	    if(StringUtils.isNotBlank(meetingTask6)){
	    	
	    	//任务是否需要视频
	    	Integer isVideo6 = 0;
	    	if(StringUtils.isNotBlank(request.getParameter("isVideo6"))){
	    		isVideo6 = Integer.parseInt(request.getParameter("isVideo6"));
	    	}
	    	
	    	//去除内容中人员信息
    		String[] strs = null;
    		if(meetingTask6.contains("\n")){
    			strs = meetingTask6.split("\n");	
    		}else{
    			strs = meetingTask6.split("\n");
    		}
    		if(meetingTask6.contains("\n\r")){
    			strs = meetingTask6.split("\n\r");	
    		}else{
    			strs = meetingTask6.split("\n\r");
    		}
    		if(strs!= null){
    			for(int j = 0; j < strs.length; j++){
			    	//去除内容中人员信息
		    		String[] split = meetingTask6.split("@");
		    		if(split.length <= 1){
		    			jsonResult.setOk(false);
						jsonResult.setMessage("请输入任务接受人");
						return jsonResult;
		    		}
		    		meetingTask6 = split[0];
			    	//接收人
			    	for (int i = 0; i < split.length; i++) {
			    		if(i>0){
			    			ProjectTask projectTask=new ProjectTask();
					    	projectTask.setProjectNo(projectNo);
					    	projectTask.setInitiator(initiator);
					    	projectTask.setTaskStatus("0");
					    	projectTask.setTaskType("1");
					    	projectTask.setAccepter(split[i].trim());
					    	projectTask.setDescription(meetingTask6);
					    	projectTask.setCreateTime(new Date());
					    	projectTask.setStartTime(new Date());
					    	projectTask.setFinishTime(getDateAfter(new Date(),5));
					    	projectTask.setMeetingNo(meetingNo);
					    	projectTask.setIsVideo(isVideo6);
					    	
					    	
					    	User user=userService.findUserByName(split[i].trim());
					    	if(user!=null){
					    		projectTaskList.add(projectTask);
					    	}
			    		}		    	
			       }
    			}
    		}	
	    }
	    if(StringUtils.isNotBlank(meetingTask7)){
	    	//任务是否需要视频
	    	Integer isVideo7 = 0;
	    	if(StringUtils.isNotBlank(request.getParameter("isVideo7"))){
	    		isVideo7 = Integer.parseInt(request.getParameter("isVideo7"));
	    	}
	    	
	    	//去除内容中人员信息
    		String[] strs = null;
    		if(meetingTask7.contains("\n")){
    			strs = meetingTask7.split("\n");	
    		}else{
    			strs = meetingTask7.split("\n");
    		}
    		if(meetingTask7.contains("\n\r")){
    			strs = meetingTask7.split("\n\r");	
    		}else{
    			strs = meetingTask7.split("\n\r");
    		}
    		if(strs!= null){
    			for(int j = 0; j < strs.length; j++){
			    	//去除内容中人员信息
		    		String[] split = meetingTask7.split("@");
		    		if(split.length <= 1){
		    			jsonResult.setOk(false);
						jsonResult.setMessage("请输入任务接受人");
						return jsonResult;
		    		}
		    		meetingTask7 = split[0];
			    	//接收人
			    	for (int i = 0; i < split.length; i++) {	 
				    	if(i>0){
				    		ProjectTask projectTask=new ProjectTask();
					    	projectTask.setProjectNo(projectNo);
					    	projectTask.setInitiator(initiator);
					    	projectTask.setTaskStatus("0");
					    	projectTask.setTaskType("1");
					    	projectTask.setAccepter(split[i].trim());
					    	projectTask.setDescription(meetingTask7);
					    	projectTask.setCreateTime(new Date());
					    	projectTask.setStartTime(new Date());
					    	projectTask.setFinishTime(getDateAfter(new Date(),5));
					    	projectTask.setMeetingNo(meetingNo);
					    	projectTask.setIsVideo(isVideo7);
					    	
					    
					    	User user=userService.findUserByName(split[i].trim());
					    	if(user!=null){
					    		projectTaskList.add(projectTask);
					    	}
				    	}	    
			    	}
    			}
    		}	
	    }
	    
	    
	    String[] meetingD = meetingDescribe.split("\n");
	    String meetingDescribeView="";
	    if(meetingD.length>0){
	    	for(String temp :meetingD){
	    		meetingDescribeView+=temp+"<br/>";
	    	}

	    }
	    
		MeetingRecord meetingRecord=new MeetingRecord();
		meetingRecord.setProjectNo(projectNo);
		meetingRecord.setMeetingName(meetingName);
		meetingRecord.setMeetingDescribe(meetingDescribeView);
		meetingRecord.setProjectTaskList(projectTaskList);
		meetingRecord.setMeetingNo(meetingNo);
		meetingRecord.setCreateDate(new Date());

 	    meetingRecord.setMeetingInputer(initiator);
 	    try {
			int flag=meetingRecordService.addMeetingRecordAndTask(meetingRecord,reportId);
		
			if(flag==0){
				if(meetingName.equals("项目启动会")){
		/*		  String projectNoId=IdGen.uuid();
				  //1.给检验员创建任务和录入一个检验记录 
				  ProjectTask projectTask=new ProjectTask();
				  projectTask.setProjectNo(projectNo);
				  projectTask.setProjectNoId(projectNoId);
			      projectTask.setInitiator("system");
			      projectTask.setTaskStatus("0");
			      projectTask.setTaskType("1");
			      projectTask.setAccepter("DeanZhang");
			      projectTask.setDescription("新项目启动了,请编辑项目检验计划! 步骤:编制检验计划-王工审核-质检补充-阳工审核");
			      projectTask.setCreateTime(new Date());
			      projectTask.setStartTime(new Date());
			      projectTask.setFinishTime(getDateAfter(new Date(),5));
			      projectTask.setMeetingNo(meetingNo);
			      projectTaskService.addProjectTask(projectTask);*/
			      //2.录入一个检验记录
		/*	      InspectionPlanInfo inspectionPlanInfo=new InspectionPlanInfo();
			      inspectionPlanInfo.setProjectNoId(projectNoId);
			      inspectionPlanInfo.setProjectNo(projectNo);
			      inspectionPlanInfo.setAddPlan("0");
			      inspectionPlanInfo.setwAudit("0");
			      inspectionPlanInfo.setIsQuality("0");
			      inspectionPlanInfo.setyAudit("0");
			      inspectionPlanInfo.setSendType("0");
			      inspectionPlanInfo.setUpdateNum(0);
			      inspectionPlanInfo.setUploadNum(0);
			      inspectionPlanInfo.setCreateDate(new Date());
			      inspectionPlanInfoService.addInspectionPlan(inspectionPlanInfo);*/
			      
			      //如果选择了技术支持，更新project
			      Project project = projectService.selectProjctDetails(projectNo);
			      if(StringUtils.isNotBlank(technician)){
			    	  project.setTechnician(technician);
			    	  projectService.updateProjectInfo(project);
			      }			      			      
				}
				jsonResult.setOk(true);
				jsonResult.setMessage("添加成功");
    			//RpcHelper.sendRequest("http://192.168.1.91:8080/ERP-NBEmail/helpServlet?action=publicComment2&className=ExternalinterfaceServlet",meetingRecord);
		        /*RpcHelper.sendRequest(reader.getProperty("erp_path")+"/helpServlet?action=publicComment2&className=ExternalinterfaceServlet",meetingRecord);
				if(projectTaskList.size()>0){
					for(int j=0;j<projectTaskList.size();j++){
						ProjectTask pt = projectTaskList.get(j);
						RpcHelper.sendRequest("",pt);
					}	
				}*/
				//String json = SerializeUtil.ObjToJson(meetingRecord);
 				//mqProducerService.sendDataToQueue("meeting", json);
				QuotePrice1 price=new QuotePrice1();
				price.setCaseNo(projectNo);
				price.setEmployeeName(initiator);
				price.setCurrentStatus(meetingName+meetingDescribeView);
				
				quotePriceService.add(price);
				
			}else{
				jsonResult.setOk(false);
				jsonResult.setMessage("添加失败");
			}
			
		} catch (Exception e) {
			jsonResult.setOk(true);
			jsonResult.setMessage("添加失败");
			e.printStackTrace();
		}finally {
			return jsonResult;
		}
	
	}
	
	@RequestMapping("/selectMeetingRecordList")
	public String selectMeetingRecordList(HttpServletRequest request,HttpServletResponse response){
    	String projectNo=request.getParameter("projectNo");
    	String userName=request.getParameter("userName");
    	String userId=request.getParameter("userId");
    	String roleNo=request.getParameter("roleNo");
    	MeetingRecord meetingRecord=new MeetingRecord();
    	meetingRecord.setProjectNo(projectNo);
    	int pageNumber;
		if(StringUtils.isNotBlank(request.getParameter("pageNumber"))){
			pageNumber=Integer.parseInt(request.getParameter("pageNumber"));//第几页,1,2
		}else{
			pageNumber=1;
		}
    	Cookie[] cookies = request.getCookies();
  	     if(cookies!=null){
  	     for(Cookie c :cookies ){
           if(c.getName().equals("name")){
          	userName=c.getValue();
           }
  	     }
  	    }
		Integer pageSize=10;
		meetingRecord.setPageSize(pageSize);
		meetingRecord.setPageNumber(pageSize*(pageNumber-1));
		
		meetingRecord.setSearchName(userName);
		User user=userService.selectUserByName(userName);
		if(StringUtils.isNotBlank(roleNo)){
			roleNo=String.valueOf(user.getRoleNo());
		}
		if(user.getRoleNo()==100||user.getRoleNo()==99||user.getRoleNo()==98||user.getRoleNo()==97){
			meetingRecord.setSearchName(null);
		}
		
		
		List<MeetingRecord> meetingRecordList=meetingRecordService.selectMeetingRecordList(meetingRecord);
		for (int i = 0; i < meetingRecordList.size(); i++) {
			int taskNum=projectTaskService.selectMeetingRecordTask(meetingRecordList.get(i).getMeetingNo()).size();
			meetingRecordList.get(i).setTaskNum(taskNum);
		}
	    Integer totalCount = meetingRecordService.selectMeetingRecordListCount(meetingRecord).size();//查询记录条数
	    int countPage = 0;
		if(totalCount!=null){
			 countPage=(totalCount-1)/10+1;
		}
		request.setAttribute("roleNo", roleNo);
	    request.setAttribute("userName", userName);
	    request.setAttribute("userId", userId);
	    request.setAttribute("pageSize", pageSize);
	    request.setAttribute("pageNumber", pageNumber);
	    request.setAttribute("totalCount", totalCount);
	    request.setAttribute("countPage", countPage);
	    request.setAttribute("meetingRecordList", meetingRecordList);
	    return "meeting_record";
	}
	
	@RequestMapping("/selectMeetingRecordDetails")
	public String selectMeetingRecordDetails(HttpServletRequest request,HttpServletResponse response){
		String userName=request.getParameter("userName");
		String userId=request.getParameter("userId");
		String roleNo=request.getParameter("roleNo");
		String meetingNo=request.getParameter("meetingNo");
		MeetingRecord meetingRecord=meetingRecordService.selectMeetingRecordByMeetingNo(meetingNo);
		//查询这个会议的任务
		List<ProjectTask> projectTaskList=projectTaskService.selectMeetingRecordTask(meetingNo);
		
		for(ProjectTask pt :projectTaskList ){
			if("1".equals(pt.getTaskStatus())){
				try {
					pt.setOperatorTimeView(DateFormat.date2String(pt.getOperatorTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
		meetingRecord.setProjectTaskList(projectTaskList);
		request.setAttribute("meetingRecord", meetingRecord);
		request.setAttribute("roleNo", roleNo);
	    request.setAttribute("userName", userName);
	    request.setAttribute("userId", userId);
	    return "meeting_record_details";
	}
	
	 public static Date getDateAfter(Date d,int day){  
	   Calendar now =Calendar.getInstance();  
	   now.setTime(d);  
	   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
	   return now.getTime();  
	 }  
	 
	 /**
	  * 跳转会议录入页面
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping("/inputMeetingRecord")
	 public String toInputMeetingRecord(HttpServletRequest request,HttpServletResponse response){
		String projectNo = request.getParameter("projectNo");
		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
		String userName=request.getParameter("userName");
		//会议类型 0：样品分析会  23：大货分析会
		String type=request.getParameter("type");
		String reportId=request.getParameter("reportId");
		
		User user=userService.selectUserByName(userName);
	    if(user!=null){
	    	userId=String.valueOf(user.getId());
	    	roleNo=String.valueOf(user.getRoleNo());
			request.setAttribute("roleNo", roleNo);
		    request.setAttribute("userId", userId);
	    }
	    if(StringUtils.isNotBlank(projectNo)){
	    	request.setAttribute("projectNo", projectNo);
	    	List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectNo);
	    	if(factoryList.size()>0){
	    	request.setAttribute("factoryList", factoryList);
	    	}else{
	    	request.setAttribute("factoryList", null);	
	    	}
	    	List<QualityReport> qualityReportList = qrService.selectByProjectNo(projectNo);
			if(qualityReportList.size()>0){
	    	request.setAttribute("qualityReportList", qualityReportList);
	    	}else{
	    	request.setAttribute("qualityReportList", null);	
	    	}
	    }
	    request.setAttribute("userName", userName);
	    
	    //用户列表
    	ArrayList<String> strs = new ArrayList<String>();
    	List<User> users = userService.queryAllUser(null);
    	for (User u : users) {
    		strs.add(u.getUserName());
		}
    	String obj = JSONArray.toJSONString(strs);
    	request.setAttribute("names", obj);
    	
    	//查询王工、姚工负责项目数量
    	int count1 = projectService.selectByTechnician("wangweiping");
    	int count2 = projectService.selectByTechnician("yaoyu");
    	request.setAttribute("count1", count1);
    	request.setAttribute("count2", count2);
    	request.setAttribute("type", type);
    	request.setAttribute("reportId", reportId);
    	
		return "input_meeting_record";
		 
	 } 
	 
	 
	 
	   /**
		 * 删除会议（nina和 录入人有权限）
		 * @Title delete 
		 * @Description
		 * @param request
		 * @param response
		 * @return
		 * @return String
		 */
		@ResponseBody
		@RequestMapping("/deleteMeeting")
		public JsonResult deleteMeeting(HttpServletRequest request,HttpServletResponse response){
			
			JsonResult jsonResult = new JsonResult();
			try {
				String meetingNo = request.getParameter("meetingNo");   //项目号
				//查询cookie中用户
				String userName = WebCookie.getUserName(request);
				if(StringUtils.isBlank(userName)){
					jsonResult.setOk(false);
					jsonResult.setMessage("请先登录");
				}				
				if(StringUtils.isNotBlank(meetingNo)){
					MeetingRecord meetingRecord = meetingRecordService.selectMeetingRecordByMeetingNo(meetingNo);
					if(userName.equals(meetingRecord.getMeetingInputer()) || userName.equals("ninazhao")){
						meetingRecordService.deleteByPrimaryKey(meetingNo);
						jsonResult.setOk(true);
						jsonResult.setMessage("已删除");
					}else{
						jsonResult.setOk(false);
						jsonResult.setMessage("你没有权限删除");
					}
				}
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage("删除失败");
				LOG.error("====会议删除失败==deleteShipping===",e);		
			}	 
			return jsonResult;
		}
	 
	 
}
