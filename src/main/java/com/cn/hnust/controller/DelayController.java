package com.cn.hnust.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.cn.hnust.pojo.Delay;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectSchedule;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.service.IDelayService;
import com.cn.hnust.service.IProjectScheduleService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.JsonResult;

/**
 * 项目延期 Controller 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/delay")
public class DelayController {

	@Autowired
	private IDelayService delayService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IProjectScheduleService projectScheduleService;
	@Autowired
	private IProjectTaskService projectTaskService;
	/**
	 * 插入项目延误信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addDelay")
	@ResponseBody
	public JsonResult showUser(HttpServletRequest request,HttpServletResponse response){
		 JsonResult json =new JsonResult();
		 String projectSchedules1=request.getParameter("projectSchedules1");//大货第一批延期时间
		 String projectSchedules2=request.getParameter("projectSchedules2");//大货第二批延期时间
		 String projectSchedules3=request.getParameter("projectSchedules3");//大货第三批延期时间
		 String delayCause=request.getParameter("delayCause"); //外部原因(客户)
		 Integer type=Integer.parseInt(request.getParameter("checked")); //原因类型(内部，外部)
		 String userName = request.getParameter("userName");             //申请人
		 
		 String projectNo=request.getParameter("projectNo");
		 Project project=projectService.selectProjctDetails(projectNo);
		 List<ProjectSchedule> projectSchedules = projectScheduleService.selectByProjectNo(projectNo);
		 List<Delay> list = new ArrayList<Delay>();
		 List<Object> delayList = new ArrayList<Object>();
		 try {
		 if(projectSchedules != null){
			 
			 String description = "";   //任务描述
			 for (int i=0;i<projectSchedules.size();i++) {				 
				 //如果批次延期时间不为空，则进行延期申请
				if(projectSchedules.get(i).getNum() == 1 && StringUtils.isNotBlank(projectSchedules1)){	
					 Delay delay=new Delay();
					 delay.setId(IdGen.uuid());
					 if(type==0){
						 delay.setInternalCause(delayCause); 
					 }else{
						 delay.setExternalCause(delayCause);
					 }
					 delay.setType(type);
					 delay.setProjectNo(projectNo);
					 delay.setDelayDate(java.sql.Date.valueOf(projectSchedules1)); 
					 delay.setCreateDate(new Date());
					 delay.setNum(1);
					 delay.setIsAgree(0);
					 delay.setOriginalDate(projectSchedules.get(i).getPredictDate());
					 //当前延期次数
					 int delayCount = delayService.delayCount(projectNo, 1);
					 delay.setDelayCount(delayCount+1);
					 list.add(delay);
					 
					 //大货延期发送任务描述
					 description +="第一批次大货申请延期到"+projectSchedules1+",当前已延期次数"+delayCount+"次。"; 
					 delayList.add(delay.getId());
				}
				 //如果批次延期时间不为空，则进行延期申请
				if(projectSchedules.get(i).getNum() == 2 && StringUtils.isNotBlank(projectSchedules2)){	
					 Delay delay=new Delay();
					 delay.setId(IdGen.uuid());
					 if(type==0){
						 delay.setInternalCause(delayCause); 
					 }else{
						 delay.setExternalCause(delayCause);
					 }
					 delay.setType(type);
					 delay.setProjectNo(projectNo);
					 delay.setDelayDate(java.sql.Date.valueOf(projectSchedules2)); 
					 delay.setCreateDate(new Date());
					 delay.setNum(2);
					 delay.setIsAgree(0);
					 delay.setOriginalDate(projectSchedules.get(i).getPredictDate());
					 //当前延期次数
					 int delayCount = delayService.delayCount(projectNo, 2);
					 delay.setDelayCount(delayCount+1);
					 list.add(delay);
					 //大货延期发送任务描述
					 description +="第二批次大货申请延期到"+projectSchedules2+",当前已延期次数"+delayCount+"次。";
					 delayList.add(delay.getId());
				}
				 //如果批次延期时间不为空，则进行延期申请
				if(projectSchedules.get(i).getNum() == 3 && StringUtils.isNotBlank(projectSchedules3)){	
					 Delay delay=new Delay();
					 delay.setId(IdGen.uuid());
					 if(type==0){
						 delay.setInternalCause(delayCause); 
					 }else{
						 delay.setExternalCause(delayCause);
					 }
					 delay.setType(type);
					 delay.setProjectNo(projectNo);
					 delay.setDelayDate(java.sql.Date.valueOf(projectSchedules3)); 
					 delay.setCreateDate(new Date());
					 delay.setNum(3);
					 delay.setIsAgree(0);
					 delay.setOriginalDate(projectSchedules.get(i).getPredictDate());
					 //当前延期次数
					 int delayCount = delayService.delayCount(projectNo, 3);
					 delay.setDelayCount(delayCount+1);
					 list.add(delay);
					 //大货延期发送任务描述
					 description +="第三批次大货申请延期到"+projectSchedules3+",当前已延期次数"+delayCount+"次。"; 
					 delayList.add(delay.getId());
				}
			 }
			 
			 
			    //申请项目大货延期的同时给对应跟单发送任务			    
			    ProjectTask projectTask=new ProjectTask();
		    	projectTask.setProjectNo(projectNo);
		    	projectTask.setInitiator(userName);
		    	projectTask.setAccepter(project.getSellName());
		    	projectTask.setDescription(description);
		    	projectTask.setUrgentReason(delayCause);
		    	projectTask.setFinishTime(DateUtil.StrToDate(DateFormat.addDays(DateFormat.date2String(new Date()), 3)));
		    	projectTask.setTaskStatus("0");
		    	projectTask.setTaskType("3");
		    	projectTask.setStartTime(new Date());
		    	projectTask.setCreateTime(new Date());
		    	projectTask.setDelayList(JSONArray.toJSONString(delayList));
		    	projectTaskService.addProjectTask(projectTask);
		 }

		 
//		 if(project.getDeliveryDate() ==null && project.getSampleScheduledDate()==null){
//			 json.setOk(false);
//			 json.setMessage("还没有输入交期,不能延期");
//			 return json;
//		 }
//		 if(project.getDeliveryDate() !=null && project.getSampleScheduledDate()!=null){
//			 if(DateUtil.StrToDate(delayDate).getTime()<project.getDeliveryDate().getTime()){
//				 json.setOk(false);
//				 json.setMessage("输入的延期时间小于交期时间");
//				 return json; 
//			 } 
//		 }else{
//			 if(project.getDeliveryDate()!=null){
//				 if(DateUtil.StrToDate(delayDate).getTime()<project.getDeliveryDate().getTime()){
//					 json.setOk(false);
//					 json.setMessage("输入的延期时间小于交期时间");
//					 return json; 
//				 } 
//			 }
//			 if(project.getSampleScheduledDate()!=null){
//				 if(DateUtil.StrToDate(delayDate).getTime()<project.getSampleScheduledDate().getTime()){
//					 json.setOk(false);
//					 json.setMessage("输入的延期时间小于交期时间");
//					 return json; 
//				 } 
//			 } 
//		 }
		 

 		 

             if(list!=null && list.size() > 0){
    			 delayService.insertBatch(list);
             }
			 json.setOk(true);
			 json.setMessage("项目延误信息添加成功");
		} catch (Exception e) {
			json.setOk(false);
			json.setMessage("项目延误信息添加错误");
			e.printStackTrace();
		}
		return json;
	}
}
