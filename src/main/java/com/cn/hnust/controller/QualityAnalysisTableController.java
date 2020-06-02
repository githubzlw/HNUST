package com.cn.hnust.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.component.RpcHelper1;
import com.cn.hnust.pojo.MeetingRecord;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectDrawing;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.ProjectInspectionReport;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.QualityAnalysisTable;
import com.cn.hnust.pojo.Task;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IMeetingRecordService;
import com.cn.hnust.service.IProjectDrawingService;
import com.cn.hnust.service.IProjectERPService;
import com.cn.hnust.service.IProjectInspectionReportService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IQualityAnalysisTableService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.HttpClient;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.PropertiesUtils;




@Controller
@RequestMapping("/qualityAnalysisTable")
public class QualityAnalysisTableController {
	@Autowired
	private IQualityAnalysisTableService qualityAnalysisTableService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IProjectService projectService;
	
	@Resource
	private RpcHelper1 rpcHelper1;
	@Autowired
	private IProjectERPService projectERPService;
	
	@Autowired
	private IProjectDrawingService projectDrawingService;
	@Autowired
	private IProjectInspectionReportService projectInspectionReportService;
	@Autowired
	private IMeetingRecordService meetingRecordService;
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");

   
	/**
	 * 查看登录用户最近2个月跟单项目列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/listItems")
    public String projectList(HttpServletRequest request,HttpServletResponse response) throws ParseException{
        String userName=request.getParameter("userName");
    	String userId=request.getParameter("userId");
    	String roleNo=request.getParameter("roleNo");
    	Cookie[] cookies = request.getCookies();
  	     if(cookies!=null){
  	     for(Cookie c :cookies ){
           if(c.getName().equals("name")){
          	userName=c.getValue();
           }
  	     }
  	    }
  	   
    	ProjectTask projectTask=new ProjectTask();
    	projectTask.setUserName(userName);
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
		if(StringUtils.isNotBlank(roleNo)){
			roleNo=String.valueOf(user.getRoleNo());
		}
		Integer totalCount=0;
		if(user.getRoleNo()==100||user.getRoleNo()==99||user.getRoleNo()==98||user.getRoleNo()==97){
			projectTask.setUserName(null);
			projectTaskList=projectService.selectProject(projectTask);
			totalCount = projectService.selectProjectCount(projectTask).size();//查询记录条数
		}else if(user.getRoleNo()==100||user.getRoleNo()==99||user.getRoleNo()==98||user.getRoleNo()==97||"7".equals(roleNo)){
			projectTask.setUserName(null);
			projectTaskList=projectService.selectProject(projectTask);
			totalCount = projectService.selectProjectCount(projectTask).size();//查询记录条数
		}else{
		    projectTaskList=projectService.selectProject(projectTask);
		    totalCount = projectService.selectProjectCount(projectTask).size();//查询记录条数
		}
		
	    
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
		    request.setAttribute("projectTaskList", projectTaskList);
		   return "list_items";	
    }
	/**
	 * 质量分析录入页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 * 
	 */
	@RequestMapping("/qualityAnalysis")
    public String qualityAnalysis(HttpServletRequest request,HttpServletResponse response) throws ParseException{
        String userName=request.getParameter("userName");
    	String userId=request.getParameter("userId");
    	String projectNo=request.getParameter("projectNo");
    	Cookie[] cookies = request.getCookies();
  	     if(cookies!=null){
  	     for(Cookie c :cookies ){
           if(c.getName().equals("name")){
          	userName=c.getValue();
           }
  	     }
  	    }
  	   request.setAttribute("projectNo", projectNo);
  	 request.setAttribute("userName", userName);
  	 request.setAttribute("userId", userId);
		   return "quality_analysis";	
    }	
	/**
	 * 质量分析数据录入
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping("/addQualityAnalysis")
	 public JsonResult addQualityAnalysis(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		JsonResult jsonResult=new JsonResult();
		String userName=request.getParameter("userName");
		String userId=request.getParameter("userId");
		String projectId=request.getParameter("projectNo");
		String quantityDrawings=request.getParameter("quantityDrawings");
		String bomTable=request.getParameter("bomTable");
		String maxPrecisionRequiremen=request.getParameter("maxPrecisionRequiremen");
		String precisionGrade=request.getParameter("precisionGrade");
		String maxRoughnessRequirement=request.getParameter("maxRoughnessRequirement");
		String surfaceTreatment=request.getParameter("surfaceTreatment");
		String materialsAbroad=request.getParameter("materialsAbroad");
		String correspondingNationalStandard=request.getParameter("correspondingNationalStandard");
		String listStandards=request.getParameter("listStandards");
		String standardNotFound=request.getParameter("standardNotFound");
		String annotation=request.getParameter("annotation");
		String assemblyRelationship=request.getParameter("assemblyRelationship");
		String heatTreatmentRequirements=request.getParameter("heatTreatmentRequirements");
		String customerRequirements=request.getParameter("customerRequirements");
		String designDefects=request.getParameter("designDefects");
		String recommendedProcess=request.getParameter("recommendedProcess");
		String suggestCommunicationProblems=request.getParameter("suggestCommunicationProblems");
		int bomTable1=0;
		if(bomTable!=null&&!"".equals(bomTable)){
			bomTable1=Integer.parseInt(bomTable);
		 }
		int quantityDrawings1=0;
		if(quantityDrawings!=null&&!"".equals(quantityDrawings)){
			quantityDrawings1=Integer.parseInt(quantityDrawings);
		}
		int annotation1=0;
		if(bomTable!=null&&!"".equals(annotation)){
			annotation1=Integer.parseInt(annotation);
		}
		int assemblyRelationship1=0;
		if(assemblyRelationship!=null&&!"".equals(assemblyRelationship)){
			assemblyRelationship1=Integer.parseInt(assemblyRelationship);
		}
		Date day=new Date();
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String str=sdf.format(day); 
	    QualityAnalysisTable qualityAnalysisTable =new QualityAnalysisTable();
	    qualityAnalysisTable.setAnnotation(annotation1);
	    qualityAnalysisTable.setAssemblyRelationship(assemblyRelationship1);
	    qualityAnalysisTable.setBomTable(bomTable1);
	    qualityAnalysisTable.setCorrespondingNationalStandard(correspondingNationalStandard);
	    qualityAnalysisTable.setCustomerRequirements(customerRequirements);
	    qualityAnalysisTable.setDesignDefects(designDefects);
	    qualityAnalysisTable.setHeatTreatmentRequirements(heatTreatmentRequirements);
	    qualityAnalysisTable.setListStandards(listStandards);
	    qualityAnalysisTable.setMaterialsAbroad(materialsAbroad);
	    qualityAnalysisTable.setMaxPrecisionRequiremen(maxPrecisionRequiremen);
	    qualityAnalysisTable.setPrecisionGrade(precisionGrade);
	    qualityAnalysisTable.setProjectId(projectId);
	    qualityAnalysisTable.setQuantityDrawings(quantityDrawings1);
	    qualityAnalysisTable.setRecommendedProcess(recommendedProcess);
	    qualityAnalysisTable.setStandardNotFound(standardNotFound);
	    qualityAnalysisTable.setSuggestCommunicationProblems(suggestCommunicationProblems);
	    qualityAnalysisTable.setSurfaceTreatment(surfaceTreatment);
	    qualityAnalysisTable.setUserName(userName);
	    qualityAnalysisTable.setCreateTime(str);
	    qualityAnalysisTable.setMaxRoughnessRequirement(maxRoughnessRequirement);
		try {
			qualityAnalysisTableService.addProjectTask(qualityAnalysisTable);
			jsonResult.setOk(true);
			jsonResult.setData("录入成功");
			rpcHelper1.sendRequest("", qualityAnalysisTable);
			
			
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setData("录入失败");
			e.printStackTrace();
		}
		return jsonResult;	
	}	
	/**
	 * 查看项目详情页
	 *  @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	
	@RequestMapping("/detailsPage")
	public String detailsPage(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		String userName=request.getParameter("userName");
		String userId=request.getParameter("userId");
		String projectNo=request.getParameter("projectNo");
		String roleNo = request.getParameter("roleNo");
		Map<String, String> param=new HashMap<String, String>();
		param.put("projectNo", projectNo);
		String projectRequirements=HttpClient.sendPost(reader.getProperty("nbmail_path")+"/helpServlet?action=getContent&className=ExternalinterfaceServlet",param);
		
		List<Task> taskList=new ArrayList<Task>();
		//1.查询项目详细信息
		Project project = projectService.showDetails(projectNo);
		//2.查询项目详细信息
		ProjectERP project1 = projectERPService.selectProjectErpByNo(projectNo);
		//4.查询项目的图纸信息
		
		List<ProjectDrawing> projectDrawingList=projectDrawingService.selectProjectDrawingByProjectNo(projectNo);
		//5.质检报告信息
		List<ProjectInspectionReport> projectInspectionReportList=projectInspectionReportService.selectInspectionReportByProjectNo(projectNo);
		project.setTaskList(taskList);
	    project.setProjectDrawingList(projectDrawingList);
		project.setInspectionReportList(projectInspectionReportList);
		request.setAttribute("project", project);
		request.setAttribute("project1", project1);
		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userId", userId);
		request.setAttribute("userName",userName);
		request.setAttribute("projectRequirements",projectRequirements);
		return "project_detail1";	
	}	
	/**
	 * 
	 * @Title:QualityAnalysisTableController
	 * @Description:查询项目全部会议
	   @author wangyang
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException JsonResult
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/queryMeeting")
	 public String queryMeeting(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		String caseno=request.getParameter("caseno");
		String str="";
		MeetingRecord meetingRecord =new MeetingRecord();
		meetingRecord.setProjectNo(caseno);
		meetingRecord.setPageSize(10);
		meetingRecord.setPageNumber(0);
		List<MeetingRecord>list=meetingRecordService.selectMeetingRecordList(meetingRecord);
		if(list.size()>0){
			for(MeetingRecord record :list){
			Date time=record.getCreateDate();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String meetingName=record.getMeetingName();	
			String meetingDescribe =record.getMeetingDescribe();
			str+="<tr><td width='150'><span>";
			str+=sf.format(time);
			str+="</span></td><td width='120'><span>";
			str+=meetingName;
			str+="</span></td><td width='250'><span>";
			str+=meetingDescribe;
			str+="</span></td></tr>";
			
			}
		}
		
		
		
		return str;	
	}	

}
