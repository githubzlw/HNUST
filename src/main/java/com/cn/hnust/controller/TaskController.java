package com.cn.hnust.controller;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.enums.TaskStatusEnum;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.Task;
import com.cn.hnust.pojo.TaskReport;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.ITaskReportService;
import com.cn.hnust.service.ITaskService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.service.ProjectFactoryService;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.JsonResult;

/***
 * 任务 Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private ITaskService taskService;
	@Autowired 
	private ITaskReportService  taskReportService;
	@Autowired 
	private IProjectTaskService  projectTaskService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ProjectFactoryService projectFactoryService;
	/**
	 * 发布部署任务
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addTask")
	@ResponseBody
	public JsonResult  addTask(HttpServletRequest request,HttpServletResponse response) {
		JsonResult JsonResult=new JsonResult();
		String projectNo =request.getParameter("projectNo");
		String taskDemand=request.getParameter("taskDemand");
		int node=Integer.parseInt(request.getParameter("node"));
		String endDate=request.getParameter("endDate");
		String operator=request.getParameter("userName");
		Task task=new Task();
		task.setId(IdGen.uuid());
		task.setProjectNo(projectNo);
		task.setTaskDemand(taskDemand);
		task.setNode(node);
		task.setStatus(0);
		task.setOperator(operator);
		task.setEndDate(java.sql.Date.valueOf(endDate));
		task.setCreateDate(new Date());
		try {
			taskService.addTask(task);
			JsonResult.setOk(true);
			JsonResult.setMessage("布置任务成功");
		} catch (Exception e) {
			JsonResult.setOk(false);
			JsonResult.setMessage("布置任务失败");
			e.printStackTrace();
		}
		return JsonResult;
	}
	/**
	 * 更新任务状态(重启任务，完成任务)
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping("/updateTask")
    @ResponseBody
	public JsonResult updateTask(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
		String taskId=request.getParameter("taskId");
		Integer flag=Integer.parseInt(request.getParameter("flag"));//判断是完成任务,还是重启任务
		ProjectTask task=new ProjectTask();
		task.setId(Integer.parseInt(taskId));
		if(flag==1){
			task.setTaskStatus(TaskStatusEnum.FINISH.getCode()+"");//任务完成,将任务状态更新为完成
		}else{
			task.setTaskStatus(TaskStatusEnum.DEFAULT.getCode()+"");//任务重启,将任务状态更新为未完成
		}
		try {
			projectTaskService.updateProjectTask(task);
			jsonResult.setOk(true);
			jsonResult.setMessage("更新成功");
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return jsonResult;
	}
    
    /***
     * 显示所有部署的任务
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/showAllTask")
    public String showAllTask(HttpServletRequest request,HttpServletResponse response){
    	List<Task> taskList=taskService.selectAllTask(null); 
    	request.setAttribute("taskList", taskList);
		return "task_list";
    } 
    /**
     * 根据任务ID查询任务详情
     * @param request
     * @param response
     * @return
     * @throws IOException 
     */
    @RequestMapping("/taskDetails")
    public void taskDetails(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	String id=request.getParameter("id");
    	String userName=request.getParameter("userName");
  	    Cookie[] cookies = request.getCookies();
	     if(cookies!=null){
	     for(Cookie c :cookies ){
	       if(c.getName().equals("name")){
	      	userName=c.getValue();
	       }
	     }
	    }
        if(StringUtils.isBlank(userName)){
  	    	response.sendRedirect("/index.jsp?purchase_history=http://117.144.21.74:10010/task/taskDetails?id="+id);
  	     }else{
  	    User user = userService.findUserByName(userName);
  	    if(user == null){
  	    	response.sendRedirect("/index.jsp?purchase_history=http://117.144.21.74:10010/task/taskDetails?id="+id);
  	    }else{
  	  	response.sendRedirect("../task/lookDetails?id="+id);
  	    
  	    }
  	     }
  	 
    }
    @RequestMapping("/lookDetails")
    public String lookDetails(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	String id=request.getParameter("id");
    	String userName=request.getParameter("userName");
    	Cookie[] cookies = request.getCookies();
    	if(cookies!=null){
    		for(Cookie c :cookies ){
    			if(c.getName().equals("name")){
    				userName=c.getValue();
    			}
    		}
    	}
    	User user = userService.findUserByName(userName);
    	ProjectTask task=null;
    	task = projectTaskService.selectProjectTaskById(Integer.parseInt(id));
    	List<TaskReport> reportList=taskReportService.selectTaskReportByNo(id);
    	if(reportList.size()>0){
    		task.setTaskReportList(reportList);
    	}    	
      	//查询项目工厂
    	List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(task.getProjectNo());
    	request.setAttribute("task", task);
    	request.setAttribute("roleNo",user.getRoleNo());
    	request.setAttribute("userId",user.getId());
    	request.setAttribute("userName",userName);
    	request.setAttribute("factoryList",factoryList);
    	return "task_detail";
    	
    }
    /***
     * 根据任务id删除任务
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delTask")
    @ResponseBody
    public JsonResult delTask(HttpServletRequest request,HttpServletResponse response){
    	JsonResult jsonResult=new JsonResult();
		String id=request.getParameter("taskId");
		try {
			projectTaskService.deleteByPrimaryKey(Integer.parseInt(id));//删除任务
//			taskService.delTaskById(id);
			taskReportService.delTaskReport(id);//删除任务汇报
			jsonResult.setOk(true);
			jsonResult.setMessage("删除成功");
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return jsonResult;
    }
}
