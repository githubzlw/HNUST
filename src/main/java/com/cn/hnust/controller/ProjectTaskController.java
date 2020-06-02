package com.cn.hnust.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import com.cn.hnust.component.RpcHelper;
import com.cn.hnust.pojo.FactoryQualityInspectionVideo;
import com.cn.hnust.pojo.FactoryScore;
import com.cn.hnust.pojo.InspectionReservation;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.User;
import com.cn.hnust.print.InspectionPrint;
import com.cn.hnust.print.ProjectStatisticsPrint;
import com.cn.hnust.service.IInspectionReservationService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.service.ProjectFactoryService;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.OperationFileUtil;
import com.cn.hnust.util.SerializeUtil;
import com.cn.hnust.util.UploadAndDownloadPathUtil;
import com.cn.hnust.util.WebCookie;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/projectTask")
public class ProjectTaskController {

	@Autowired
	private IProjectTaskService projectTaskService;
	
	@Autowired
	private IInspectionReservationService inspectionReservationService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ProjectFactoryService projectFactoryService;
	

	/**
	 * 项目任务列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 */
    @RequestMapping("/projectTaskList")
    public String projectTaskList(HttpServletRequest request,HttpServletResponse response) throws ParseException{
    	String searchName=request.getParameter("searchName");
    	String taskStatus=request.getParameter("taskStatus");
    	String tasktype=request.getParameter("tasktype");
    	String projectNo=request.getParameter("projectNo");
    	String userName=request.getParameter("userName");
    	String userId=request.getParameter("userId");
    	String roleNo=request.getParameter("roleNo");
    	String sendOrReceive =request.getParameter("sendOrReceive");
    	
    	int sendOrRe = 0;
    	if(StringUtils.isNotBlank(sendOrReceive)){
    		sendOrRe = Integer.parseInt(sendOrReceive);
    	}
    	
  	    if(StringUtils.isNotBlank(taskStatus)){
  	    	taskStatus=request.getParameter("taskStatus");
  	    }else{
  	    	taskStatus="0";
  	    }
    	ProjectTask projectTask=new ProjectTask();
    	projectTask.setProjectNo(projectNo);
    	projectTask.setSendOrReceive(sendOrRe);
    	projectTask.setTaskStatus(taskStatus);
    	projectTask.setSearchName(searchName);
    	projectTask.setUserName(userName);
    	projectTask.setTaskType(tasktype);
    	int pageNumber;
		if(StringUtils.isNotBlank(request.getParameter("pageNumber"))){
			pageNumber=Integer.parseInt(request.getParameter("pageNumber"));//第几页,1,2
		}else{
			pageNumber=1;
		}
		Integer pageSize=10;
		projectTask.setPageSize(pageSize);
		projectTask.setPageNumber(pageSize*(pageNumber-1));
		User user=userService.selectUserByName(userName);
		List<ProjectTask> projectTaskList=new ArrayList<ProjectTask>();
		Integer totalCount=0;
		if(user.getRoleNo()==100||user.getRoleNo()==99||user.getRoleNo()==98||user.getRoleNo()==97){
			if(sendOrRe==0||sendOrRe==1||sendOrRe==2){
				projectTask.setUserName(null);
			}
			projectTaskList=projectTaskService.selectProjectTask(projectTask);
			totalCount = projectTaskService.selectProjectTaskCount(projectTask).size();//查询记录条数
			
		}else{
		    projectTaskList=projectTaskService.selectProjectTask(projectTask);
		    totalCount = projectTaskService.selectProjectTaskCount(projectTask).size();//查询记录条数
		}
		
	    //标记任务状态
	    for (int i = 0; i < projectTaskList.size(); i++) {
	    	ProjectTask taskFlag=projectTaskList.get(i);
	    	Date nowDate=new Date();  //今天
	    	Date finishTime=taskFlag.getFinishTime(); //要求完成时间
	    	Date expectFinishTime=taskFlag.getExpectFinishTime();//预计完成时间
	    	if(expectFinishTime!=null){//如果预计完成时间不为空
	    		if(finishTime.getTime()>expectFinishTime.getTime() &&taskFlag.getTaskStatus().equals(0)){ //今天 > MAX（预计完成时间，要求完成时间）
		    		if(nowDate.getTime()>finishTime.getTime()){
		    			taskFlag.setTaskFlag("1");//不正常
		    		}else{
		    			taskFlag.setTaskFlag("0");//正常
		    		}
		    	}else{
		    		if(nowDate.getTime()>expectFinishTime.getTime()&& taskFlag.getTaskStatus().equals(0)){
		    			taskFlag.setTaskFlag("1");//不正常
		    		}else{
		    			taskFlag.setTaskFlag("0");//正常
		    		}
		    	}
	    	}else{
	    		if(finishTime !=null && nowDate.getTime()>finishTime.getTime() && taskFlag.getTaskStatus().equals(0)){
	    			taskFlag.setTaskFlag("1");//不正常
	    		}else{
	    			taskFlag.setTaskFlag("0");//正常
	    		}
	    	 }
	    	 //查询出验货预约任务详情
	    	InspectionReservation inspection=inspectionReservationService.selectInspectionReservationById(taskFlag.getProjectNoId());
	    	taskFlag.setInspectionReservation(inspection);
		   }
	   
    	    String finishRatio=""; 
    	    //3另计算及时完成率  （公式是最近 90天 按时所有完成的项目/所有完成的项目)
			float ratio=0;
			int onTimeFinish=projectTaskService.statisticsProjectTaskOnTime(userName,"1").size();
			int allFinish=projectTaskService.statisticsProjectTaskAllFinish(userName,"1").size();
			
			//任务平均用时   按时完成任务用时
			if(allFinish!=0){
				if(onTimeFinish==0){
					ratio=0;
				}else{
					ratio=(float)onTimeFinish/(float)allFinish;
				}
			}else{
				ratio=0;
			}
			
			finishRatio=String.valueOf(ratio*100)+'%';
			//计算所有任务完成的总时间/任务完成的个数  =平均按时完成用时
			int totalHours=0;//任务完成的总用时
			float averageHours=0;
			
			
			List<ProjectTask> allFinishList=projectTaskService.statisticsProjectTaskAllFinish(userName,"1");
			for (int j = 0; j < allFinishList.size(); j++) {
				ProjectTask allFinishTask=allFinishList.get(j);
				SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				if(allFinishTask.getOperatorTime() != null && allFinishTask.getCreateTime() != null){
				    String currentDate = simpleFormat.format(allFinishTask.getOperatorTime());  
				    String publishDate = simpleFormat.format(allFinishTask.getCreateTime());  
				    long from = simpleFormat.parse(currentDate).getTime();  
				    long to = simpleFormat.parse(publishDate).getTime();  
				    totalHours += (int) ((from - to)/(1000 * 60 * 60));  
				}

			}
			if(totalHours!=0 && allFinish!=0){
				averageHours=(totalHours*1.0F)/(allFinish);
			}else{
				averageHours=0;
			}
			
			
			//目前 拖得最长的任务 已经几天了（从目标完成日算起）
			float maxDate=0;
			ProjectTask maxTask=projectTaskService.selectProjectTaskMaxDate(userName);
			if(maxTask!=null){
				if(maxTask.getFinishTime().getTime()<new Date().getTime()){
					int hours = (int) ((new Date().getTime() - maxTask.getFinishTime().getTime()) / (1000*3600));
					maxDate=(hours*1.0F)/24;
				}else{
					maxDate=0;
				}
			
				
			}
		    int countPage = 0;
			if(totalCount!=null){
				 countPage=(totalCount-1)/10+1;
			}
			
			List<User>saleNameList=userService.queryByJob("销售");
			List<User>technologyList=userService.queryByJob("技术");
			User user1=new User();
			user1.setRoleNo(6);
			List<User>purchaseList=userService.queryByRoleNo(user1);
			List<User>qualitytestingList=userService.queryByJob("质检");
			request.setAttribute("saleNameList",saleNameList);
			request.setAttribute("technologyList",technologyList);
			request.setAttribute("purchaseList",purchaseList);
			request.setAttribute("qualitytestingList",qualitytestingList);
			request.setAttribute("sendOrReceive",sendOrReceive);
			request.setAttribute("maxDate", maxDate);
			request.setAttribute("averageHours",averageHours);
			request.setAttribute("finishRatio", finishRatio);
			request.setAttribute("searchName",searchName);
			request.setAttribute("roleNo", roleNo);
		    request.setAttribute("userName", userName);
		    request.setAttribute("userId", userId);
		    request.setAttribute("pageSize", pageSize);
		    request.setAttribute("pageNumber", pageNumber);
		    request.setAttribute("totalCount", totalCount);
		    request.setAttribute("countPage", countPage);
		    request.setAttribute("projectTaskList", projectTaskList);
		    request.setAttribute("taskStatus",taskStatus);
		    request.setAttribute("searchProjectNo", projectNo);
			return "project_task";	
    }
    
    
    /**
     * 任务列表
     * @Title taskList 
     * @Description
     * @param request
     * @param response
     * @return
     * @throws ParseException
     * @return String
     */
    @ResponseBody
    @RequestMapping("/taskList")
    public JsonResult taskList(HttpServletRequest request,HttpServletResponse response) throws ParseException{
    	JsonResult jsonResult = new JsonResult();
    	List<ProjectTask> tasks = new ArrayList<ProjectTask>();
		Integer pageNumber = 1;
		Integer pageSize = 10;
    	String pageNumberStr = request.getParameter("pageNumber");
		String inputKey = request.getParameter("inputKey");// 搜索词
		String purchaseName = request.getParameter("purchase_name");// 采购
		String saleName = request.getParameter("documentary_name");// 跟单、销售
		String qualityName = request.getParameter("quality_name");// 质检
		String pageSizeStr = request.getParameter("pageSize");
		String userIdStr = request.getParameter("userId");
		String projectNo = request.getParameter("projectNo");// 项目号
		if (StringUtils.isNotBlank(pageNumberStr)) {
			pageNumber = Integer.parseInt(pageNumberStr);// 第几页,1,2

		}
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		//判断是管理员，销售，采购
		Integer roleNo = null;                            
        String userName = WebCookie.getUserName(request);
        if(StringUtils.isNotBlank(userName)){
        	User user = userService.findUserByName(userName);
        	roleNo = user.getRoleNo();
        	userIdStr = user.getId()+"";
        }else{
        	jsonResult.setMessage("请先登录");
			jsonResult.setOk(false);
			return jsonResult;
        }
		
		//任务查询
		ProjectTask task = new ProjectTask();
		task.setIsPurchase(null);
		task.setTaskStatus("-1");
        task.setUserName(userName);
        task.setPageSize(100);
        task.setPageNumber(0);
        task.setPageSize(pageSize);
        task.setProjectNo(inputKey);
        if(projectNo!=null&&!"".equalsIgnoreCase(projectNo)){
        	task.setProjectNo(projectNo);	
        }
        task.setPageNumber(pageSize * (pageNumber - 1));
        if(roleNo == 100||roleNo==99||roleNo==98||roleNo==97){
        	task.setUserName(null);
        	tasks = projectTaskService.selectProjectTask(task);
        }else{
        	tasks = projectTaskService.selectProjectTask(task);
        }            
        List<ProjectTask> projectTaskCount = projectTaskService.selectProjectTaskCount(task);
		
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("totalCount", (projectTaskCount!=null&&projectTaskCount.size()>0)?projectTaskCount.size():0);
       map.put("pageNumber", pageNumber);
       map.put("pageSize", pageSize);
       map.put("taskList", tasks);
       map.put("userId", userIdStr);
       map.put("userName", userName);
       map.put("roleNo", roleNo);
       jsonResult.setData(map);

    	
    	return jsonResult;
    }
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 录入任务信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addProjectTask")
    @ResponseBody
    public JsonResult addProjectTask(HttpServletRequest request,HttpServletResponse response){
    	JsonResult jsonResult=new JsonResult();
    	String projectNo=request.getParameter("projectNo");
    	String initiator=request.getParameter("initiator");
    	String description=request.getParameter("description");
    	String finishTime=request.getParameter("finishTime");
    	String urgentReason=request.getParameter("urgentReason");
    	String taskUrl=request.getParameter("taskUrl");
    	String taskType=request.getParameter("taskType");
    	
    	Integer isVideo = 0;          //是否需要采购提供视频和交期
    	if(StringUtils.isNotBlank(request.getParameter("isVideo"))){
    		isVideo = Integer.parseInt(request.getParameter("isVideo"));
    	}
    	
    	List<ProjectTask> tasks = new ArrayList<ProjectTask>();
    	if(StringUtils.isNotBlank(description)){
    		//去除内容中人员信息
    		String[] strs = null;
    		if(description.contains("\n")){
    			strs = description.split("\n");	
    		}
    		if(description.contains("\n\r")){
    			strs = description.split("\n\r");	
    		}
    		if(strs!= null){
    			for(int j = 0; j < strs.length; j++){
    				String[] split = strs[j].split("@");
    	    		description = split[0];
    	            for (int i = 0; i < split.length; i++) {
    	            	if(i>0){
    	                    String userName = split[i].trim();
    	                	ProjectTask projectTask=new ProjectTask();
    	                	projectTask.setProjectNo(projectNo);
    	                	projectTask.setInitiator(initiator);
    	                	projectTask.setAccepter(userName);
    	                	projectTask.setDescription(description);
    	                	projectTask.setUrgentReason(urgentReason);
    	                	projectTask.setFinishTime(DateUtil.StrToDate(finishTime));
    	                	projectTask.setTaskStatus("0");
    	                	projectTask.setTaskType(taskType);
    	                	projectTask.setTaskUrl(taskUrl);
    	                	projectTask.setStartTime(new Date());
    	                	projectTask.setCreateTime(new Date());
    	                	projectTask.setIsVideo(isVideo);
    	                	tasks.add(projectTask);
    	            	}
    				}  
    			}	    		
    		}else{
    			String[] split = description.split("@");
	    		description = split[0];
	            for (int i = 0; i < split.length; i++) {
	            	if(i>0){
	                    String userName = split[i].trim();
	                	ProjectTask projectTask=new ProjectTask();
	                	projectTask.setProjectNo(projectNo);
	                	projectTask.setInitiator(initiator);
	                	projectTask.setAccepter(userName);
	                	projectTask.setDescription(description);
	                	projectTask.setUrgentReason(urgentReason);
	                	projectTask.setFinishTime(DateUtil.StrToDate(finishTime));
	                	projectTask.setTaskStatus("0");
	                	projectTask.setTaskType(taskType);
	                	projectTask.setTaskUrl(taskUrl);
	                	projectTask.setStartTime(new Date());
	                	projectTask.setCreateTime(new Date());
	                	projectTask.setIsVideo(isVideo);
	                	tasks.add(projectTask);
	            	}
				}  
    		}
 
    	}

    	try {
    		if(tasks.size() > 0){
    			projectTaskService.insertBatch(tasks);
    		}
			jsonResult.setOk(true);
			jsonResult.setData("录入成功");
			//RpcHelper.sendRequest("",projectTask);
			
			
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setData("录入失败");
			e.printStackTrace();
		}
		return jsonResult;
    }
    /**
     * 根据任务Id查询详情
     * @param request
     * @param response
     * @return
     * @throws IOException 
     */
    @RequestMapping("/selectProjectTaskById")
    public String selectProjectTaskById(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	 String userName=request.getParameter("userName");
    	 String userId=request.getParameter("userId");
    	 String roleNo=request.getParameter("roleNo");
    	 String id=request.getParameter("id");
    	 Cookie[] cookies = request.getCookies();
  	     if(cookies!=null){
  	     for(Cookie c :cookies ){
           if(c.getName().equals("name")){
          	userName=c.getValue();
           }
  	     }
  	    }
  	   
  	    if(StringUtils.isBlank(userName)){
  	    	response.sendRedirect("/index.jsp?purchase_history=http://117.144.21.74:10010/projectTask/selectProjectTaskById?id="+id);
        	return null;
  	    }
  	    User user = userService.findUserByName(userName);
  	    if(user == null){
  	    	response.sendRedirect("/index.jsp?purchase_history=http://117.144.21.74:10010/projectTask/selectProjectTaskById?id="+id);
        	return null;
  	    }
    	
    	ProjectTask projectTask=projectTaskService.selectProjectTaskById(Integer.parseInt(id));
    	 //查询出验货预约任务详情
    	InspectionReservation inspection=inspectionReservationService.selectInspectionReservationById(projectTask.getProjectNoId());
    	 //查询项目工厂
    	List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNoGroupByFactoryId(projectTask.getProjectNo());
    	
    	
    	projectTask.setInspectionReservation(inspection);
    	request.setAttribute("projectTask",projectTask);
		request.setAttribute("roleNo", user.getRoleNo());
	    request.setAttribute("userName", userName);
	    request.setAttribute("userId", user.getId());
	    request.setAttribute("factoryList", factoryList);
		return "project_task_details";
    }
    /**
     * 更新任务状态
     * @param request
     * @param response
     * @return
     */
    /*SangCaiZheng*/
    @RequestMapping("/updateProjectTask")
    @ResponseBody
    public JsonResult updateProjectTask(HttpServletRequest request,HttpServletResponse response){
	  JsonResult jsonResult=new JsonResult();
	  String projectTaskId=request.getParameter("projectTaskId");
	  String taskStatus=request.getParameter("taskStatus");
	  String userName=request.getParameter("userName");
	  String operateExplain=request.getParameter("operateExplain");
	  String productFileName=request.getParameter("productFileName");
	  String scoreList=request.getParameter("scoreList");
	  String newDate=request.getParameter("newDate");
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  Date date=null;
	  if(newDate!=null&&!"".equals(newDate)){
		  try{
          date=sdf.parse(newDate);
		  }catch(Exception e){
			  
		  }
	  }
	  Cookie[] cookies = request.getCookies();
	  if(cookies!=null){
	   for(Cookie c :cookies ){
         if(c.getName().equals("name")){
        	userName=c.getValue();
         }
	   }
	  }
	  if(StringUtils.isNotBlank(userName)){
		  userName=request.getParameter("userName"); 
	  }
      ProjectTask projectTask=projectTaskService.selectProjectTaskById(Integer.parseInt(projectTaskId));
      
      projectTask.setTaskStatus(taskStatus);
      projectTask.setOperator(userName);
      projectTask.setOperatorTime(new Date());
	  projectTask.setOperateExplain(operateExplain);
	  projectTask.setProductFileName(productFileName);
	  projectTask.setNewDate(date);
      try {
	 if(newDate!=null&&!"".equalsIgnoreCase(newDate)){
		 
		 projectTaskService.updateByPrimaryKeySelective(projectTask);
	 }else{
        if(scoreList!=null && "6".equals(projectTask.getTaskType())){
			List<FactoryScore> factoryScores= null;
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, FactoryScore.class);
			factoryScores = objectMapper.readValue(scoreList,javaType);
			for (FactoryScore score : factoryScores) {
				score.setScoreTime(new Date());
			}
			projectTaskService.updateProjectTask(projectTask, factoryScores);
        }else{
        	projectTaskService.checkNextTask(projectTask);
        }
	 }
           
		jsonResult.setOk(true);
		jsonResult.setMessage("操作成功");
		RpcHelper.sendRequest("",projectTask);
		
	} catch (Exception e) {
		jsonResult.setOk(false);
		jsonResult.setMessage("操作失败");
		e.printStackTrace();
	}
	  return jsonResult;
    }
    
    
    
    /**
     * 跟新进度
     * @Title updateProjectTask 
     * @Description
     * @param request
     * @param response
     * @return
     * @return JsonResult
     */
    @RequestMapping("/updateProgress")
    @ResponseBody
    public JsonResult updateProgress(HttpServletRequest request,HttpServletResponse response){
    	 JsonResult jsonResult=new JsonResult();
    	 try {
			 String projectTaskId=request.getParameter("id");
			 String progress=request.getParameter("progress");
			 
			 if(StringUtils.isBlank(projectTaskId)){
				jsonResult.setOk(false);
				jsonResult.setMessage("未获取到id");
			 }
			 ProjectTask projectTask=projectTaskService.selectProjectTaskById(Integer.parseInt(projectTaskId)); 
			 projectTask.setProgress(progress);
			 projectTask.setProgressDate(new Date());
			 projectTaskService.updateProjectTask(projectTask);
			 jsonResult.setOk(true);
			 jsonResult.setMessage("操作成功");
			 
			} catch (NumberFormatException e) {
				jsonResult.setOk(false);
				jsonResult.setMessage("更新失败");
				e.printStackTrace();
			}
	     return jsonResult;
    }
    
    
    
    
    
    
    /**
     * 更新任务状态
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateExpectFinishTime")
    @ResponseBody
    public JsonResult updateExpectFinishTime(HttpServletRequest request,HttpServletResponse response){
	  JsonResult jsonResult=new JsonResult();
	  String projectTaskId=request.getParameter("id");
	  String userName=request.getParameter("userName");
	  String expectFinishTime=request.getParameter("expectFinishTime");
	  Cookie[] cookies = request.getCookies();
	  if(cookies!=null){
	   for(Cookie c :cookies ){
         if(c.getName().equals("name")){
        	userName=c.getValue();
         }
	   }
	  }
	  if(StringUtils.isNotBlank(userName)){
		  userName=request.getParameter("userName"); 
	  }
      ProjectTask projectTask=new ProjectTask();
      projectTask.setId(Integer.parseInt(projectTaskId));
      projectTask.setExpectFinishTime(DateUtil.StrToDate(expectFinishTime));
      try {
		projectTaskService.updateProjectTask(projectTask);
		
		jsonResult.setOk(true);
		jsonResult.setMessage("操作成功");
		RpcHelper.sendRequest("",projectTask);
	} catch (Exception e) {
		jsonResult.setOk(false);
		jsonResult.setMessage("操作失败");
		e.printStackTrace();
	}
	  return jsonResult;
    }
    
    @RequestMapping("/statisticsProjectTask")
    public String statisticsProjectTask(HttpServletRequest request,HttpServletResponse response) throws ParseException{
    	String sign=request.getParameter("sign");
    	if(sign!=null&&!"".equalsIgnoreCase(sign)){
    		request.setAttribute("sign", sign);
    	}else{
    		sign="1";
    		request.setAttribute("sign", "1");
    	}
    	List<ProjectTask> noFinishList=new ArrayList<ProjectTask>();
    	List<ProjectTask> finishList=new ArrayList<ProjectTask>();
    	List<User> userList=userService.selectAllUser();
    	for (int i = 0; i < userList.size(); i++) {
    		User user=userList.get(i);
    		ProjectTask searchTask=new ProjectTask();
    		searchTask.setAccepter(user.getUserName());
    		searchTask.setSign(sign);
    		List<ProjectTask>existList=projectTaskService.selectProjectTaskIfExist(searchTask);
			if(null == existList || existList.size() ==0){
				continue;
			}
			
			//1.每个人的未完成数量
			List<ProjectTask> noFinishCountList=projectTaskService.statisticsProjectTaskNoFinish1(searchTask);
    		ProjectTask noFinish=new ProjectTask();
    		noFinish.setAccepter(user.getUserName());
    		noFinish.setNoFinishCount(noFinishCountList.size());
    		noFinish.setRoleNo(user.getRoleNo());
    		noFinish.setOverCount((noFinishCountList !=null && noFinishCountList.size()>0) ? noFinishCountList.get(0).getOverCount(): 0);
    		noFinishList.add(noFinish);
    		//2.统计最近30天的完成数量
    		List<ProjectTask> finishCountList=projectTaskService.statisticsProjectTaskFinish1(searchTask);
    		ProjectTask finish=new ProjectTask();
        	finish.setAccepter(user.getUserName());
        	finish.setFinishCount(finishCountList.size());
        	finish.setRoleNo(user.getRoleNo());
    		finishList.add(finish);
		}
    	List<ProjectTask> onTimeList=new ArrayList<ProjectTask>();
    	String finishRatio=""; 
    	//3另计算及时完成率  （公式是最近 90天 按时所有完成的项目/所有完成的项目） 任务平均用时
    	//4.existList
    	List<ProjectTask> existList=new ArrayList<ProjectTask>();
    	for (int i = 0; i < userList.size(); i++) {
			User user=userList.get(i);
			ProjectTask projectTask=new ProjectTask();
			float ratio=0;
			projectTask.setAccepter(user.getUserName());
			existList=projectTaskService.selectProjectTaskIfExist(projectTask);
			if(null == existList || existList.size() ==0 ){
				continue;
			}
			int onTimeFinish=projectTaskService.statisticsProjectTaskOnTime1(user.getUserName(),sign).size();
			int allFinish=projectTaskService.statisticsProjectTaskAllFinish1(user.getUserName(),sign).size();
			
			//计算完成的总时间/任务完成的个数  =平均按时完成用时(及时完成率)
			int totalHours=0;//任务完成的总用时
			float averageHours=0;
			DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			List<ProjectTask> onTimeTaskList=projectTaskService.statisticsProjectTaskOnTime1(user.getUserName(),sign);
			for (int j = 0; j < onTimeTaskList.size(); j++) {
				ProjectTask onTimeTask=onTimeTaskList.get(j);
				SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			    String currentDate = simpleFormat.format(onTimeTask.getOperatorTime());  
			    String publishDate = simpleFormat.format(onTimeTask.getCreateTime());  
			    long from = simpleFormat.parse(currentDate).getTime();  
			    long to = simpleFormat.parse(publishDate).getTime();  
			    totalHours += (int) ((from - to)/(1000 * 60 * 60));  
			}
			if(onTimeFinish!=0){
				averageHours=(totalHours*1.0F)/(onTimeFinish);	
			}
			//任务平均用时   按时完成任务用时
			if(allFinish!=0){
				if(onTimeFinish==0){
					ratio=0;
				}else{
					ratio=(float)onTimeFinish/(float)allFinish;
					ratio = new BigDecimal(ratio).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				}
			}else{
				ratio=0;
			}
			finishRatio=String.valueOf(ratio)+'%';
			projectTask.setAccepter(user.getUserName());
			projectTask.setOnTimeFinish(onTimeFinish);
			projectTask.setAllFinish(allFinish);
			projectTask.setFinishRatio(finishRatio);
			projectTask.setRoleNo(user.getRoleNo());
			projectTask.setAverageHours(Float.parseFloat(decimalFormat.format(averageHours)));
			onTimeList.add(projectTask);
		}
    	
    	//4.统计会议相关会完成的任务数量
    	List<ProjectTask> meetingRecordList=new ArrayList<ProjectTask>();
    	for (int i = 0; i < userList.size(); i++) {
			User user=userList.get(i);
			ProjectTask existTask=new ProjectTask();
			existTask.setAccepter(user.getUserName());
			existTask.setTaskType("1");
			existList=projectTaskService.selectProjectTaskIfExist(existTask);
			if(null == existList || existList.size() ==0 ){
				continue;
			}
			int meetingTaskNum=projectTaskService.selectMeetingRecordTaskNoFinish1(user.getUserName()).size();
			ProjectTask projectTask=new ProjectTask();
			projectTask.setAccepter(user.getUserName());
		    projectTask.setMeetingTaskNum(meetingTaskNum);
		    meetingRecordList.add(projectTask);
		}
    	request.setAttribute("meetingRecordList", meetingRecordList);
    	request.setAttribute("noFinishList", noFinishList);
    	request.setAttribute("finishList", finishList);
    	request.setAttribute("onTimeList", onTimeList);
		return "statistics_project_task";
    }
    
    /**
	 * 添加任务文件
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadProductTaskFile",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addFactoryFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 String projectTaskImg = request.getParameter("projectTaskImg");
		 String path = UploadAndDownloadPathUtil.getProjectTaskImg();
		 File file = new File(path);
		 if  (!file.exists()  && !file.isDirectory())       {         
				file .mkdir();     
		 }  	
		 //调用保存文件的帮助类进行保存文件(文件上传，form表单提交)
		try {
			Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload(request, path+File.separator);
			String fileName = multiFileUpload.get(projectTaskImg);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(value="/selectNoFinishProjectTask")
	@ResponseBody
	public int selectNoFinishProjectTask(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String userName=request.getParameter("userName");
		ProjectTask projectTask=new ProjectTask();
		projectTask.setAccepter(userName);
		int  noFinish=projectTaskService.statisticsProjectTaskNoFinish(projectTask).size();
		return noFinish;
	}
	
	
	
    @RequestMapping("/addTask")
    public String addTask(HttpServletRequest request,HttpServletResponse response){
    	ArrayList<String> strs = new ArrayList<String>();
    	String taskType=request.getParameter("taskType");
    	String projectNo=request.getParameter("projectNo");
    	List<User> users = userService.queryAllUser(null);
    	for (User user : users) {
    		strs.add(user.getUserName());
		}
    	String obj = JSONArray.toJSONString(strs);
    	if(taskType!=null&&!"".equalsIgnoreCase(taskType)){
    		request.setAttribute("taskType", taskType);
    	}else{
    		request.setAttribute("taskType", 0);
    	}
    	if(projectNo!=null&&!"".equalsIgnoreCase(projectNo)){
    		projectNo=projectNo.toLowerCase().replace("shs", "");
    		request.setAttribute("projectNo", projectNo);
    	}else{
    		request.setAttribute("projectNo", "");
    	}
    	request.setAttribute("names", obj);
    	return "input_project_task";
    }
    
    
    /**
     * 根据任务号查询
     * @Title selectByTaskId 
     * @Description
     * @param request
     * @param response
     * @return
     * @return JsonResult
     */
	@RequestMapping(value="/selectByTaskId")
	@ResponseBody
	public JsonResult selectByTaskId(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
		String ids = request.getParameter("id");
		if(StringUtils.isNotBlank(ids)){
			ProjectTask projectTask = projectTaskService.selectProjectTaskById(Integer.parseInt(ids));
			jsonResult.setData(projectTask);
			jsonResult.setOk(true);
		}else{
			jsonResult.setMessage("查询失败");;
			jsonResult.setOk(false);
		}		
		return jsonResult;
	}
	/**
	 * 
	 * @Title:ProjectTaskController
	 * @Description:导出任务系统查验表
	   @author wangyang
	 * @param request
	 * @param response void
	 * @throws
	 */
	@RequestMapping(value="/derivedData")
	public void derivedData(HttpServletRequest request,HttpServletResponse response){
		try {
			String time=request.getParameter("startTime1");
    		String time1=request.getParameter("endTime1");
    		String fileName=time+"-"+time1+"_task_statistics.xls";
		OutputStream os=response.getOutputStream();
		   response.reset(); //清空输入流
		   response.setHeader("Content-disposition", "attachment; filename="+fileName);// 设定输出文件头
		   response.setContentType("application/x-download");// 定义输出类型
		   response.setContentType("text/html;charset=UTF-8");
		   response.setCharacterEncoding("UTF-8");
		   WritableWorkbook workbook= Workbook.createWorkbook(os);// 建立excel文件
		   String title="未操作项目Excel";
		   WritableSheet sheet=workbook.createSheet(title, 0);
		   // 设置单元格格式					
		   WritableFont writableFont = new WritableFont(WritableFont.createFont("宋体"),11, WritableFont.NO_BOLD, false);
		   WritableCellFormat writableCellFormat = new WritableCellFormat(writableFont);
		   writableCellFormat.setWrap(true);//自动换行
        
        	
    		List<ProjectTask> noFinishList=new ArrayList<ProjectTask>();
        	List<ProjectTask> finishList=new ArrayList<ProjectTask>();
        	List<User> userList=userService.selectAllUser();
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        	for (int i = 0; i < userList.size(); i++) {
        		User user=userList.get(i);
        		ProjectTask searchTask=new ProjectTask();
        		searchTask.setAccepter(user.getUserName());
        		
        		if(time!=null&&!"".equals(time)){
        		Date date = formatter.parse(time);
        		searchTask.setStartTime(date);	
        		}
        		if(time1!=null&&!"".equalsIgnoreCase(time1)){
        			Date date = formatter.parse(time1);
        			searchTask.setFinishTime(date);
        		}
        		
        		List<ProjectTask>existList=projectTaskService.selectProjectTaskIfExist(searchTask);
    			if(null == existList || existList.size() ==0){
    				continue;
    			}
    			//1.每个人的未完成数量
    			List<ProjectTask> noFinishCountList=projectTaskService.statisticsProjectTaskNoFinish2(searchTask);
        		ProjectTask noFinish=new ProjectTask();
        		noFinish.setAccepter(user.getUserName());
        		noFinish.setNoFinishCount(noFinishCountList.size());
        		noFinish.setRoleNo(user.getRoleNo());
        		noFinish.setOverCount((noFinishCountList !=null && noFinishCountList.size()>0) ? noFinishCountList.get(0).getOverCount(): 0);
        		noFinishList.add(noFinish);
        		//2.统计最近30天的完成数量
        		List<ProjectTask> finishCountList=projectTaskService.statisticsProjectTaskFinish2(searchTask);
        		ProjectTask finish=new ProjectTask();
            	finish.setAccepter(user.getUserName());
            	finish.setFinishCount(finishCountList.size());
            	finish.setRoleNo(user.getRoleNo());
        		finishList.add(finish);
    		}
        	List<ProjectTask> onTimeList=new ArrayList<ProjectTask>();
        	String finishRatio=""; 
        	//3另计算及时完成率  （公式是最近 90天 按时所有完成的项目/所有完成的项目） 任务平均用时
        	//4.existList
        	List<ProjectTask> existList=new ArrayList<ProjectTask>();
        	for (int i = 0; i < userList.size(); i++) {
    			User user=userList.get(i);
    			ProjectTask projectTask=new ProjectTask();
    			float ratio=0;
    			projectTask.setAccepter(user.getUserName());
    			if(time!=null&&!"".equals(time)){
            		Date date = formatter.parse(time);
            		projectTask.setStartTime(date);	
            		}
            		if(time1!=null&&!"".equalsIgnoreCase(time1)){
            			Date date = formatter.parse(time1);
            			projectTask.setFinishTime(date);
            		}
    			existList=projectTaskService.selectProjectTaskIfExist(projectTask);
    			if(null == existList || existList.size() ==0 ){
    				continue;
    			}
    			int onTimeFinish=projectTaskService.statisticsProjectTaskOnTime2(projectTask).size();
    			int allFinish=projectTaskService.statisticsProjectTaskAllFinish2(projectTask).size();
    			
    			//计算完成的总时间/任务完成的个数  =平均按时完成用时(及时完成率)
    			int totalHours=0;//任务完成的总用时
    			float averageHours=0;
    			DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
    			List<ProjectTask> onTimeTaskList=projectTaskService.statisticsProjectTaskOnTime3(projectTask);
    			for (int j = 0; j < onTimeTaskList.size(); j++) {
    				ProjectTask onTimeTask=onTimeTaskList.get(j);
    				SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    			    String currentDate = simpleFormat.format(onTimeTask.getOperatorTime());  
    			    String publishDate = simpleFormat.format(onTimeTask.getCreateTime());  
    			    long from = simpleFormat.parse(currentDate).getTime();  
    			    long to = simpleFormat.parse(publishDate).getTime();  
    			    totalHours += (int) ((from - to)/(1000 * 60 * 60));  
    			}
    			if(onTimeFinish!=0){
    				averageHours=(totalHours*1.0F)/(onTimeFinish);	
    			}
    			//任务平均用时   按时完成任务用时
    			if(allFinish!=0){
    				if(onTimeFinish==0){
    					ratio=0;
    				}else{
    					ratio=(float)onTimeFinish/(float)allFinish;
    					ratio = new BigDecimal(ratio).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    				}
    			}else{
    				ratio=0;
    			}
    			finishRatio=String.valueOf(ratio)+'%';
    			projectTask.setAccepter(user.getUserName());
    			projectTask.setOnTimeFinish(onTimeFinish);
    			projectTask.setAllFinish(allFinish);
    			projectTask.setFinishRatio(finishRatio);
    			projectTask.setRoleNo(user.getRoleNo());
    			projectTask.setAverageHours(Float.parseFloat(decimalFormat.format(averageHours)));
    			onTimeList.add(projectTask);
    		}
        	
        	//4.统计会议相关会完成的任务数量
        	List<ProjectTask> meetingRecordList=new ArrayList<ProjectTask>();
        	for (int i = 0; i < userList.size(); i++) {
    			User user=userList.get(i);
    			ProjectTask existTask=new ProjectTask();
    			existTask.setAccepter(user.getUserName());
    			existTask.setTaskType("1");
    			if(time!=null&&!"".equals(time)){
            		Date date = formatter.parse(time);
            		existTask.setStartTime(date);	
            		}
            		if(time1!=null&&!"".equalsIgnoreCase(time1)){
            			Date date = formatter.parse(time1);
            			existTask.setFinishTime(date);
            		}
    			existList=projectTaskService.selectProjectTaskIfExist(existTask);
    			if(null == existList || existList.size() ==0 ){
    				continue;
    			}
    			int meetingTaskNum=projectTaskService.selectMeetingRecordTaskNoFinish2(existTask).size();
    			ProjectTask projectTask=new ProjectTask();
    			projectTask.setAccepter(user.getUserName());
    		    projectTask.setMeetingTaskNum(meetingTaskNum);
    		    meetingRecordList.add(projectTask);
    		}
        	
        	sheet.addCell(new Label(0,0,time+"至"+time1+"每个人未完成的任务统计"));
		    sheet.addCell(new Label(1,0,"姓名"));
		    sheet.addCell(new Label(2,0,"未完成数量"));
		    sheet.addCell(new Label(3,0,"超期5天未完成数量"));
		    
		    sheet.addCell(new Label(6,0,time+"至"+time1+"的完成任务数量"));
		    sheet.addCell(new Label(7,0,"姓名"));
		    sheet.addCell(new Label(8,0,"完成数量"));
		    
		    sheet.addCell(new Label(11,0,time+"至"+time1+"每个人任务的及时完成率"));
		    sheet.addCell(new Label(12,0,"姓名"));
		    sheet.addCell(new Label(13,0,"及时完成项目数"));
		    sheet.addCell(new Label(14,0,"所有完成项目数"));
		    sheet.addCell(new Label(15,0,"及时完成率"));
		    sheet.addCell(new Label(16,0,"平均任务工时"));
		    
		    sheet.addCell(new Label(19,0,time+"至"+time1+"会议相关未完成任务数量(7天)"));
		    sheet.addCell(new Label(20,0,"姓名"));
		    sheet.addCell(new Label(21,0,"未完成数量"));
		    
		    
		    for (int j = 0; j < noFinishList.size(); j++) {
		    	ProjectTask data=noFinishList.get(j);
		    	if(data!=null){
			    	sheet.addCell(new Label(1,j+1,data.getAccepter()));
			    	sheet.addCell(new Label(2,j+1,data.getNoFinishCount()+""));
			    	sheet.addCell(new Label(3,j+1,data.getOverCount()+""));
			    		
		    	}
			}
		    for (int j = 0; j < finishList.size(); j++) {
		    	ProjectTask data=finishList.get(j);
		    	if(data!=null){
			    	sheet.addCell(new Label(7,j+1,data.getAccepter()));
			    	sheet.addCell(new Label(8,j+1,data.getFinishCount()+""));
			    	
		    	}
			}
		    for (int j = 0; j < onTimeList.size(); j++) {
		    	ProjectTask data=onTimeList.get(j);
		    	if(data!=null){
			    	sheet.addCell(new Label(12,j+1,data.getAccepter()));
			    	sheet.addCell(new Label(13,j+1,data.getOnTimeFinish()+""));
			    	sheet.addCell(new Label(14,j+1,data.getAllFinish()+""));
			    	sheet.addCell(new Label(15,j+1,data.getFinishRatio()));	
			    	sheet.addCell(new Label(16,j+1,data.getAverageHours()+""));	
		    	}
			}
		    for (int j = 0; j < meetingRecordList.size(); j++) {
		    	ProjectTask data=meetingRecordList.get(j);
		    	if(data!=null){
			    	sheet.addCell(new Label(20,j+1,data.getAccepter()));
			    	sheet.addCell(new Label(21,j+1,data.getMeetingTaskNum()+""));
			    		
		    	}
			}
		    
		    workbook.write(); // 写入文件   
			workbook.close();  
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@RequestMapping("/searchAll")
    public String searchAll(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		String time=request.getParameter("startTime");
		String time1=request.getParameter("endTime");
		List<ProjectTask> noFinishList=new ArrayList<ProjectTask>();
    	List<ProjectTask> finishList=new ArrayList<ProjectTask>();
    	List<User> userList=userService.selectAllUser();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	for (int i = 0; i < userList.size(); i++) {
    		User user=userList.get(i);
    		ProjectTask searchTask=new ProjectTask();
    		searchTask.setAccepter(user.getUserName());
    		
    		if(time!=null&&!"".equals(time)){
    		Date date = formatter.parse(time);
    		searchTask.setStartTime(date);	
    		}
    		if(time1!=null&&!"".equalsIgnoreCase(time1)){
    			Date date = formatter.parse(time1);
    			searchTask.setFinishTime(date);
    		}
    		
    		List<ProjectTask>existList=projectTaskService.selectProjectTaskIfExist(searchTask);
			if(null == existList || existList.size() ==0){
				continue;
			}
			//1.每个人的未完成数量
			List<ProjectTask> noFinishCountList=projectTaskService.statisticsProjectTaskNoFinish2(searchTask);
    		ProjectTask noFinish=new ProjectTask();
    		noFinish.setAccepter(user.getUserName());
    		noFinish.setNoFinishCount(noFinishCountList.size());
    		noFinish.setRoleNo(user.getRoleNo());
    		noFinish.setOverCount((noFinishCountList !=null && noFinishCountList.size()>0) ? noFinishCountList.get(0).getOverCount(): 0);
    		noFinishList.add(noFinish);
    		//2.统计最近30天的完成数量
    		List<ProjectTask> finishCountList=projectTaskService.statisticsProjectTaskFinish2(searchTask);
    		ProjectTask finish=new ProjectTask();
        	finish.setAccepter(user.getUserName());
        	finish.setFinishCount(finishCountList.size());
        	finish.setRoleNo(user.getRoleNo());
    		finishList.add(finish);
		}
    	List<ProjectTask> onTimeList=new ArrayList<ProjectTask>();
    	String finishRatio=""; 
    	//3另计算及时完成率  （公式是最近 90天 按时所有完成的项目/所有完成的项目） 任务平均用时
    	//4.existList
    	List<ProjectTask> existList=new ArrayList<ProjectTask>();
    	for (int i = 0; i < userList.size(); i++) {
			User user=userList.get(i);
			ProjectTask projectTask=new ProjectTask();
			float ratio=0;
			projectTask.setAccepter(user.getUserName());
			if(time!=null&&!"".equals(time)){
        		Date date = formatter.parse(time);
        		projectTask.setStartTime(date);	
        		}
        		if(time1!=null&&!"".equalsIgnoreCase(time1)){
        			Date date = formatter.parse(time1);
        			projectTask.setFinishTime(date);
        		}
			existList=projectTaskService.selectProjectTaskIfExist(projectTask);
			if(null == existList || existList.size() ==0 ){
				continue;
			}
			int onTimeFinish=projectTaskService.statisticsProjectTaskOnTime2(projectTask).size();
			int allFinish=projectTaskService.statisticsProjectTaskAllFinish2(projectTask).size();
			
			//计算完成的总时间/任务完成的个数  =平均按时完成用时(及时完成率)
			int totalHours=0;//任务完成的总用时
			float averageHours=0;
			DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			List<ProjectTask> onTimeTaskList=projectTaskService.statisticsProjectTaskOnTime3(projectTask);
			for (int j = 0; j < onTimeTaskList.size(); j++) {
				ProjectTask onTimeTask=onTimeTaskList.get(j);
				SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			    String currentDate = simpleFormat.format(onTimeTask.getOperatorTime());  
			    String publishDate = simpleFormat.format(onTimeTask.getCreateTime());  
			    long from = simpleFormat.parse(currentDate).getTime();  
			    long to = simpleFormat.parse(publishDate).getTime();  
			    totalHours += (int) ((from - to)/(1000 * 60 * 60));  
			}
			if(onTimeFinish!=0){
				averageHours=(totalHours*1.0F)/(onTimeFinish);	
			}
			//任务平均用时   按时完成任务用时
			if(allFinish!=0){
				if(onTimeFinish==0){
					ratio=0;
				}else{
					ratio=(float)onTimeFinish/(float)allFinish;
					ratio = new BigDecimal(ratio).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				}
			}else{
				ratio=0;
			}
			finishRatio=String.valueOf(ratio)+'%';
			projectTask.setAccepter(user.getUserName());
			projectTask.setOnTimeFinish(onTimeFinish);
			projectTask.setAllFinish(allFinish);
			projectTask.setFinishRatio(finishRatio);
			projectTask.setRoleNo(user.getRoleNo());
			projectTask.setAverageHours(Float.parseFloat(decimalFormat.format(averageHours)));
			onTimeList.add(projectTask);
		}
    	
    	//4.统计会议相关会完成的任务数量
    	List<ProjectTask> meetingRecordList=new ArrayList<ProjectTask>();
    	for (int i = 0; i < userList.size(); i++) {
			User user=userList.get(i);
			
			ProjectTask existTask=new ProjectTask();
			existTask.setAccepter(user.getUserName());
			
			if(time!=null&&!"".equals(time)){
        		Date date = formatter.parse(time);
        		existTask.setStartTime(date);	
        		}
        		if(time1!=null&&!"".equalsIgnoreCase(time1)){
        			Date date = formatter.parse(time1);
        			existTask.setFinishTime(date);
        		}
			existList=projectTaskService.selectProjectTaskIfExist(existTask);
			
			if(null == existList || existList.size() ==0 ){
				continue;
			}
			int	meetingTaskNum=projectTaskService.selectMeetingRecordTaskNoFinish2(existTask).size();
			
			 
			ProjectTask projectTask=new ProjectTask();
			projectTask.setAccepter(user.getUserName());
		    projectTask.setMeetingTaskNum(meetingTaskNum);
		    meetingRecordList.add(projectTask);
		}
    	if(time!=null&&!"".equals(time)){
    		request.setAttribute("time", time);	
    		}
    		if(time1!=null&&!"".equalsIgnoreCase(time1)){
    			request.setAttribute("time1", time1);	
    		}
    	request.setAttribute("meetingRecordList", meetingRecordList);
    	request.setAttribute("noFinishList", noFinishList);
    	request.setAttribute("finishList", finishList);
    	request.setAttribute("onTimeList", onTimeList);
		return "statistics_project_task";
    }
	
	/**
	 * 
	 * @Title:ProjectTaskController
	 * @Description:修改跟新图纸信息
	   @author wangyang
	 * @param request
	 * @param response
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("/updateAll")
    @ResponseBody
    public JsonResult updateAll(HttpServletRequest request,HttpServletResponse response){
	  JsonResult jsonResult=new JsonResult();
	  String taskId=request.getParameter("taskId");
	  String drawingId=request.getParameter("drawingId");
	  String userName=request.getParameter("userName");
	  Date date=new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Cookie[] cookies = request.getCookies();
	  if(cookies!=null){
	   for(Cookie c :cookies ){
         if(c.getName().equals("name")){
        	userName=c.getValue();
         }
	   }
	  }
	  if(StringUtils.isNotBlank(userName)){
		  userName=request.getParameter("userName"); 
	  }
      ProjectTask projectTask=new ProjectTask();
      projectTask.setVerification(1);
      projectTask.setVerifier(userName);
      projectTask.setId(Integer.parseInt(taskId));
      projectTask.setDrawingId(drawingId);
      projectTask.setVerificationTime(formatter.format(date));
      projectTaskService.updateByPrimaryKeySelective(projectTask);
	 
	 String message="已确认,"+formatter.format(date)+userName;
           
		jsonResult.setOk(true);
		jsonResult.setMessage("操作成功");
		RpcHelper.sendRequest("",projectTask);
		
	
	  return jsonResult;
    }
    
}
