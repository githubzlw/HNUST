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
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.hnust.component.RpcQualityNoticeToKuai;
import com.cn.hnust.pojo.*;
import com.cn.hnust.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.component.RpcHelper;
import com.cn.hnust.component.RpcSynPurchase;
import com.cn.hnust.enums.DetailStatusEnum;
import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.enums.ProjectAnalysisEnum;
import com.cn.hnust.enums.ProjectStageEnum;
import com.cn.hnust.enums.ProjectStatusEnum;
import com.cn.hnust.enums.QualityStatusEnum;
import com.cn.hnust.enums.QualityTypeEnum;
import com.cn.hnust.enums.TaskStatusEnum;
import com.cn.hnust.print.ProjectPrint;
import com.cn.hnust.print.ProjectStatisticsPrint;
import com.cn.hnust.service.impl.UserServiceImpl;
import com.cn.hnust.service.impl.task.ProjectDateTask;
import com.cn.hnust.util.Base64Encode;
import com.cn.hnust.util.CommonResult;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.OperationFileUtil;
import com.cn.hnust.util.SerializeUtil;
import com.cn.hnust.util.UploadAndDownloadPathUtil;
import com.cn.hnust.util.WebCookie;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 主要项目处理方法
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

	private static final String String = null;
	private static final int UNSOLVE = 0;  //未完成投诉
	private static final Log LOG = LogFactory.getLog(ProjectController.class);
	private static final String QUALITY_ANALYSIS = "1";
	private static final String TECHNOLOGY_ANALYSIS = "2";
	@Autowired
	private IQualityAndEfficiencyReportService qualityAndEfficiencyReportService;
	@Autowired
	private IQualityReportService qualityReportService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IProjectReportService projectReportService;
	@Autowired
	private ShippingConfirmationService shippingConfirmationService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private ITaskReportService taskReportService;
	@Autowired
	private IProjectDrawingService projectDrawingService;
	@Autowired
	private IProjectInspectionReportService projectInspectionReportService;
	@Autowired
	private IProductionPlanService productionPlanService;
	@Autowired
	private IProjectCommentService projectCommentService;
	@Autowired
	private IStatusEnterService statusEnterService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private IQualityReportService qrService;
	@Autowired
	private IDelayService delayService;
	@Autowired
    private IProjectTaskService projectTaskService;
	@Autowired
	private ProjectDateTask projectDateTask;
	@Autowired
	private IFeedbackService feedbackService;
	@Autowired
	private IInspectionReservationService inspectionReservationService;
	@Autowired
	private IProjectScheduleService projectScheduleService;
	@Autowired
	private ProjectComplaintService projectComplaintService;	
	@Autowired
	private IMeetingRecordService meetingRecordService;
	@Autowired
	private ErpReportService erpReportService;
	@Autowired
	private ProjectPauseService projectPauseService;
	@Autowired
	private ProjectFactoryService projectFactoryService;
	@Autowired
	private FactoryQualityInspectionVideoService factoryQualityInspectionVideoService;
	@Autowired
	private DeliveryDateLogService deliveryDateLogService;
	@Autowired
	private QualityAnalysisService qualityAnalysisService;
	@Autowired
	private AnalysisIssueService analysisIssueService;
	@Autowired
	private FactoryScoreService factoryScoreService;
	@Autowired
	private TrackService trackService;
	@Autowired
	private QuoteWeeklyReportService quoteWeeklyReportService;
	@Autowired
	private InspectionPlanService inspectionPlanService;
	@Autowired
	private MQProducerService mqProducerService;
	@Autowired
	private QuotePriceService quotePriceService;
	
	@Autowired
	private TQuotePriceService tquotePriceService;
	@Autowired
	private ItemCaseERPService itemCaseERPService;
	@Autowired
	private ComplaintListService complaintListService;
	@Autowired
	private PayOtherService payOtherService;
	@Autowired
	private FactoryFundService factoryFundService;
	@Autowired
	private BargainService bargainService;
	@Autowired
	private InvoiceInfoService invoiceInfoService;

	
	private static final Integer SAMPLE = 0;  //样品查询
	private static final Integer PRODUCT = 1; //大货查询
	
	private static final Integer NOTDELAY = 0;   //非延期
	private static final Integer DELAY = 1;      //延期
	private static final Integer ALL = null;     //所有
	
	//上传文件分类
	private static final Integer MATERIAL_FILE = 1;   //材质证明
	private static final Integer VIDEO = 2;           //视频

	
	
	/**
	 * 查询登录用户所属的项目列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/showList")
	@ResponseBody
	public JsonResult showUser(HttpServletRequest request,
			HttpServletResponse response) {

		String status = "";
		JsonResult jsonResult = new JsonResult();
		Integer operatorType = 0;
		Integer pageNumber = 1;
		Integer pageSize = 8;
		Integer userId = null;
		List<Project> list = null;
		List<Task> taskList = new ArrayList<Task>();
		List<ProjectTask> tasks = new ArrayList<ProjectTask>();
		List<Project> delayList = new ArrayList<Project>();
		List<Project> messageList = new ArrayList<Project>();
		int totalCountList = 0;// 总记录数
		Project project = new Project();

		Date today = new Date();

		String pageNumberStr = request.getParameter("pageNumber");
		String operatorTypeStr = request.getParameter("operatorType");
//		String roleNo = request.getParameter("roleNo"); // 判断是管理员，销售，采购
//		String userName = request.getParameter("userName");
		String userIdStr = request.getParameter("userId");
		String inputKey = request.getParameter("inputKey");// 搜索词
		String purchaseName = request.getParameter("purchase_name");// 采购
		String saleName = request.getParameter("documentary_name");// 跟单、销售
		String qualityName = request.getParameter("quality_name");// 质检
		String pageSizeStr = request.getParameter("pageSize");
        String screenType=request.getParameter("screenType");
	/*	String projectStage = request.getParameter("projectStage");// 项目阶段
		String plantAnalysis = request.getParameter("plantAnalysis");// 项目等级
		String projectTypeStr = request.getParameter("projectTypeS");// 项目状态
		String projectStageStr = request.getParameter("projectStageS");// 项目阶段(pc)
		String plantAnalysisStr = request.getParameter("plantAnalysisS");// 项目等级(pc)
        String delayStatusS=request.getParameter("delayStatusS");  //延期筛选查询       
*/        //细节状态查询
//        String detailStatus = request.getParameter("detailStatusS"); 
        
        
        String qualityReportSelect=request.getParameter("qualityReportSelect");//质检报告搜索
        String expectedShipmentSelect=request.getParameter("expectedShipmentSelect");
        String importantSelect=request.getParameter("importantSelect");   //A、B级项目两周未更新周报
		if (StringUtils.isNotBlank(operatorTypeStr)) {
			operatorType = Integer.parseInt(operatorTypeStr);
		}
		if (StringUtils.isNotBlank(pageNumberStr)) {
			pageNumber = Integer.parseInt(pageNumberStr);// 第几页,1,2

		}
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		Integer roleNo = null;                            // 判断是管理员，销售，采购
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
		
		

		try {

			if (StringUtils.isNotBlank(inputKey)) {
				inputKey = URLDecoder.decode(inputKey, "UTF-8");
			}		
			project.setPageSize(pageSize);
			project.setPageNumber(pageSize * (pageNumber - 1));
            
			project.setSellName(saleName);
			project.setQualityName(qualityName);
			project.setPurchaseName(purchaseName);
			project.setScreenType(screenType); //pc 端页面筛选
			project.setInputKey(inputKey);
			project.setUserName(userName);
			project.setRoleNo(roleNo);
			project.setQualityReportSelect(qualityReportSelect);
			project.setExpectedShipmentSelect(expectedShipmentSelect);
			project.setImportantSelect(importantSelect);
			
			list = projectService.findProjectList(project);// 项目列表
			totalCountList = projectService.findProjectListCount(project);// 查询记录条数
			
			userId = Integer.parseInt(userIdStr);

			// 将任务列表添加到项目信息中(判断该项目是否有任务)
			InspectionReservation reservation=new InspectionReservation();
			for (Project seaProject : list) {
				// 4.查询项目的图纸信息
//				List<ProjectDrawing> projectDrawingList = projectDrawingService.selectProjectDrawingByProjectNo(seaProject.getProjectNo());
				// 5.质检报告信息
//				List<ProjectInspectionReport> projectInspectionReportList = projectInspectionReportService.selectInspectionReportByProjectNo(seaProject.getProjectNo());
				//6.查询检验计划
/*				List<ProjectInspectionReport> inspectionPlanList=projectInspectionReportService.selectInspectionPlanByProjectNo(seaProject.getProjectNo());
				ProjectTask projectTask=new ProjectTask();
				projectTask.setProjectNo(seaProject.getProjectNo());
				projectTask.setTaskStatus("-1");
				projectTask.setPageSize(100);
				projectTask.setPageNumber(0);*/
				//7.检查这个项目是否有关联任务(完成/所有)
/*				List<ProjectTask> projectTaskList=projectTaskService.selectProjectTask(projectTask);
				ProjectTask finishTask=new ProjectTask();
				finishTask.setProjectNo(seaProject.getProjectNo());
				finishTask.setTaskStatus("1");
				finishTask.setPageSize(100);
				finishTask.setPageNumber(0);
				
				List<ProjectTask> finishTaskList=projectTaskService.selectProjectTask(finishTask);
				seaProject.setAllTask(projectTaskList.size());
				seaProject.setFinishTask(finishTaskList.size());*/
				//8.查询项目需求图纸报告
/*				List<ProjectDrawing> demandReportList=projectDrawingService.selectProjectDemandReportByNo(seaProject.getProjectNo());
				seaProject.setProjectDemandReportList(demandReportList);
				seaProject.setInspectionPlanList(inspectionPlanList);
				seaProject.setProjectDrawingList(projectDrawingList);
				seaProject.setInspectionReportList(projectInspectionReportList);
				seaProject.setProjectTaskList(projectTaskList);*/
				// 6.查询周报更新最近的时间
				ProjectReport projectReport = projectReportService.selectProjectReportLatelyDate(seaProject.getProjectNo());
				seaProject.setProjectReport(projectReport);
				// 7.查询本周是否上传的周报
				//判断当前时间是否是周一,是周一不是周一
				Calendar cal=Calendar.getInstance();
				cal.setTime(new Date()); 
				int week=cal.get(Calendar.DAY_OF_WEEK)-1;
				List<ProjectReport> projectReportList = projectReportService.selectProjectReportWeek(seaProject.getProjectNo(),week);
				seaProject.setProjectReportList(projectReportList);
				List<Feedback> feedbackList=feedbackService.selectFeedbackByProjectNo(seaProject.getProjectNo(),week);
				seaProject.setFeedbackList(feedbackList);
				//8.查询质检上传
//				QualityReport qualityReport = qrService.selectNewestReportByProjectNo(seaProject.getProjectNo());
//				seaProject.setQualityReport(qualityReport);
				//9.查询该项目最近一次申请延期的信息
				List<Delay> delays = delayService.selectAllByProjectNo(seaProject.getProjectNo());
				seaProject.setDelayList(delays);
                //10.查询验货预约任务次数
//                reservation.setInputKey(seaProject.getProjectNo());
//                int reservationNum=inspectionReservationService.selectInspectionReservationCount(reservation);
//                seaProject.setInspectionReservationNum(reservationNum);
				// 判断项目状态                  
                if(seaProject.getProjectStatus()!=null){
					if(seaProject.getProjectStatus()==1){
						status = "进行中项目";
					}
					if(seaProject.getProjectStatus()==2){
						status = "大货完结项目";							
					}
					if(seaProject.getProjectStatus()==0){
						status = "新立项项目";
					}
					if(seaProject.getProjectStatus()==4){
						status = "暂停项目";
					}
					if(seaProject.getProjectStatus()==5){
						status = "取消项目";
					}
					if(seaProject.getProjectStatus()==6){
						status = "样品完结项目";
					}
				}

				seaProject.setStatus(status);
                // 判断项目状态
                //新项目和取消项目和暂停项目不加入延期
//				 if(seaProject.getProjectStatus() != OrderStatusEnum.NEW_ORDER.getCode() && seaProject.getProjectStatus() != OrderStatusEnum.CANCEL_ORDER.getCode() && seaProject.getProjectStatus() != OrderStatusEnum.PAUSE_ORDER.getCode()){
//		        		Map<String, Object> map = judgeDelay(seaProject, scheduleList);
//		        		Boolean judgeDelay = (Boolean)map.get("delayType");
//		        		int delayDays = Integer.parseInt(map.get("delayDays").toString());
//		        		seaProject.setDelayDays(delayDays);
//		       		if(judgeDelay == true){
//		       			status = "有延期";
//		           		seaProject.setStatus(status);
//		           	    delayList.add(seaProject);
//		           	}
//				 } 
                
				if (seaProject.getPlantAnalysis()!=null && seaProject.getPlantAnalysis() != 0) {
					seaProject.setPlantAnalysisView(ProjectAnalysisEnum.getEnum(seaProject.getPlantAnalysis()).getValue());
				}
				if(seaProject.getSampleScheduledDate()!=null && seaProject.getDeliveryDate()==null){//样品交期快到期提醒
					  if (seaProject.getSampleScheduledDate() != null&& today.getTime() < seaProject.getSampleScheduledDate().getTime()
								&& seaProject.getSampleScheduledDate().getTime()- today.getTime() <= 12 * 24 * 60 * 60* 1000 && seaProject.getSampleFinish() == 0) {
						  seaProject.setFlag(1); // 快到期
					  } else if (seaProject.getSampleScheduledDate() != null&& seaProject.getSampleScheduledDate().getTime()+7*24*60*60*1000< today.getTime() && seaProject.getSampleFinish() == 0) {
						  seaProject.setFlag(2); // 已延期
					  } 
					}
					if(seaProject.getDeliveryDate()!=null){//大货交期快到期了提醒
					  if (seaProject.getDeliveryDate() != null&& today.getTime() < seaProject.getDeliveryDate().getTime()
								&& seaProject.getDeliveryDate().getTime()- today.getTime() <= 12 * 24 * 60 * 60* 1000) {
						  seaProject.setFlag(1); // 快到期
					  } else if (seaProject.getDeliveryDate() != null&& seaProject.getDeliveryDate().getTime()+7*24*60*60*1000 < today.getTime() && seaProject.getFinish() == 0) {
						  seaProject.setFlag(2); // 已延期
//						  delayList.add(seaProject);
					  } 	
					}
			}

			// 销售进来判断采购是否提供了新的采购计划
			if ("5".equals(roleNo)) {
				messageList = projectService.findProjectReportMessage(project);
			}

//			int projectNum = 0;
			int delayNum = 0;
			int messageNum = 0;

//			if (totalCountList != null && totalCountList.size() > 0) {
//				projectNum = totalCountList.size();
//			}
//			if (delayList != null && delayList.size() > 0) {
//				delayNum = delayList.size();
//			}
			if (messageList != null && messageList.size() > 0) {
				messageNum = messageList.size();
			}

			Map<String, Object> hashMap = new HashMap<String, Object>();

			hashMap.put("taskList", tasks);
			hashMap.put("list", list);
			hashMap.put("userId", userId);
			hashMap.put("roleNo", roleNo);
			hashMap.put("userName", userName);
			hashMap.put("projectNum", totalCountList);
			hashMap.put("delayNum", delayNum);
			hashMap.put("messageNum", messageNum);
			hashMap.put("pageSize", pageSize);
			hashMap.put("pageNumber", pageNumber);
			hashMap.put("totalCount", totalCountList);
			hashMap.put("operatorType", operatorType);
			hashMap.put("qualityReportSelect", qualityReportSelect);
			hashMap.put("expectedShipmentSelect",expectedShipmentSelect);
			jsonResult.setData(hashMap);
			jsonResult.setOk(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMessage(e.getMessage());
			jsonResult.setOk(false);
			LOG.error("error", e);
		} finally {
			return jsonResult;
		}

	}
	
	
	
	
	
	
	
	
	/**
	 * 查询列表项目数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws 
	 */
	@RequestMapping("/showListNew")
	public String showList(HttpServletRequest request,
			HttpServletResponse response) {

		Long start = new Date().getTime();
		
		String status = "";
		Integer operatorType = 0;
		Integer pageNumber = 1;
		Integer pageSize = 50;
		Integer userId = null;
		List<Project> list = null;
		List<Task> taskList = new ArrayList<Task>();
//		List<Project> delayList = new ArrayList<Project>();
//		List<Project> messageList = new ArrayList<Project>();
		int totalCountList = 0;// 总记录数
		Project project = new Project();
        Date today = new Date();

		String pageNumberStr = request.getParameter("pageNumber");
		String operatorTypeStr = request.getParameter("operatorType");
		String userIdStr = request.getParameter("userId");
		String inputKey = request.getParameter("inputKey");// 搜索词
		String projectType = request.getParameter("projectType");// 项目状态
		String projectStage = request.getParameter("projectStage");// 项目阶段
		String plantAnalysis = request.getParameter("plantAnalysis");// 项目等级
		String projectTypeStr = request.getParameter("projectTypeS");// 项目状态
		String projectStageStr = request.getParameter("projectStageS");// 项目阶段(pc)
		String plantAnalysisStr = request.getParameter("plantAnalysisS");// 项目等级(pc)
		String purchaseName = request.getParameter("purchase_name");// 采购
		String saleName = request.getParameter("documentary_name");// 跟单、销售
		String qualityName = request.getParameter("quality_name");// 质检
		String technicianStr = request.getParameter("technician");// 技术员
		String pageSizeStr = request.getParameter("pageSize");
        String screenType=request.getParameter("screenType");
        String delayStatusS=request.getParameter("delayStatusS");  //延期筛选查询
        
        //细节状态查询
        String detailStatus = request.getParameter("detailStatusS");
        
        Integer roleNo = null;                            // 判断是管理员，销售，采购
        String userName = WebCookie.getUserName(request);
        if(StringUtils.isNotBlank(userName)){
        	User user = userService.findUserByName(userName);
        	roleNo = user.getRoleNo();
        	userIdStr = user.getId()+"";
        }else{
        	return "redirect:/index.jsp";
        }
    
        
        String qualityReportSelect=request.getParameter("qualityReportSelect");//质检报告搜索
        String expectedShipmentSelect=request.getParameter("expectedShipmentSelect");
        String importantSelect=request.getParameter("importantSelect");   //A、B级项目两周未更新周报
		if (StringUtils.isNotBlank(operatorTypeStr)) {
			operatorType = Integer.parseInt(operatorTypeStr);
		}
		if (StringUtils.isNotBlank(pageNumberStr)) {
			pageNumber = Integer.parseInt(pageNumberStr);// 第几页,1,2

		}
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}

		try {

			if (StringUtils.isNotBlank(inputKey)) {
				inputKey = URLDecoder.decode(inputKey, "UTF-8");
			}

			project.setPageSize(pageSize);
			project.setPageNumber(pageSize * (pageNumber - 1));

			if (StringUtils.isNotBlank(projectType)) {
				project.setProjectType(Integer.parseInt(projectType));
			}
			if (StringUtils.isNotBlank(projectStage)) {
				project.setProjectStage(Integer.parseInt(projectStage));
			}
			if (StringUtils.isNotBlank(plantAnalysis)) {
				project.setPlantAnalysis(Integer.parseInt(plantAnalysis));
			}
			//项目等级的组合(0,1,2,3) -1代表全部的
            if(StringUtils.isNotBlank(plantAnalysisStr)){//-1,0,1,2,3
            	List<Integer> plantAnalysisList=new ArrayList<Integer>();
            	String plantAnalysisS[]=plantAnalysisStr.split(",");
            	for (int i = 0; i < plantAnalysisS.length; i++) {
            		plantAnalysisList.add(Integer.parseInt(plantAnalysisS[i]));
                }
            	project.setPlantAnalysisS(plantAnalysisList);
            }
            //项目阶段组合
            if(StringUtils.isNotBlank(projectStageStr)){
            	String projectStageS[]=projectStageStr.split(","); //-1,0,1,2,3
            	List<Integer> projectStageList=new ArrayList<Integer>();
            	for (int i = 0; i < projectStageS.length; i++) {
            		projectStageList.add(Integer.parseInt(projectStageS[i]));
            		if(Integer.parseInt(projectStageS[i]) == 3){
            			project.setStageUnUpdate(3);
            		}
                }
            	project.setProjectStageS(projectStageList);
            }
            //项目状态组合
            Integer projectStatus = null;
            if(StringUtils.isNotBlank(projectTypeStr)){
            	String projectTypeS[]=projectTypeStr.split(",");
            	List<Integer> projectTypeList=new ArrayList<Integer>();
            	for (int i = 0; i < projectTypeS.length; i++) {
            		projectTypeList.add(Integer.parseInt(projectTypeS[i]));
            		
            		//如果选择的为7，则是查看最近一周完成项目
            		if(Integer.parseInt(projectTypeS[i]) == 7){
            			projectStatus = 7;
            			project.setProjectStatus(projectStatus);
            		}
                }
            	project.setProjectStatusS(projectTypeList);
            }
            //延期筛选组合
            List<Integer> delayStatusList = new ArrayList<Integer>();
            if(StringUtils.isNotBlank(delayStatusS)){
            	String delayArray[]=delayStatusS.split(",");            	
            	for (int i = 0; i < delayArray.length; i++) {
            		delayStatusList.add(Integer.parseInt(delayArray[i]));
            	}
            	project.setDelayStatusS(delayStatusList);
            }
            
            //详情状态组合
            if(StringUtils.isNotBlank(detailStatus)){
            	String detailStatusS[]=detailStatus.split(",");
            	List<Integer> detailStatusList=new ArrayList<Integer>();
            	for (int i = 0; i < detailStatusS.length; i++) {
            		detailStatusList.add(Integer.parseInt(detailStatusS[i]));
            		if(Integer.parseInt(detailStatusS[i]) == 0){
            			project.setDetailUnUpdate(0);
            		}
                }
            	project.setDetailStatusS(detailStatusList);
            }
            project.setSellName(saleName);
			project.setQualityName(qualityName);
			project.setTechnicianStr(technicianStr);
			project.setPurchaseName(purchaseName);
			project.setScreenType(screenType); //pc 端页面筛选
			project.setInputKey(inputKey);
			project.setUserName(userName);
			project.setRoleNo(roleNo);
			project.setQualityReportSelect(qualityReportSelect);
			project.setExpectedShipmentSelect(expectedShipmentSelect);
			project.setImportantSelect(importantSelect);
			list = projectService.findProjectList(project);// 项目列表		
			totalCountList = projectService.findProjectListCount(project);// 查询记录条数
		    Long end = new Date().getTime();
			LOG.info("数据库查询总耗时："+(end-start));					
			userId = Integer.parseInt(userIdStr);
			// 将任务列表添加到项目信息中(判断该项目是否有任务)
//			InspectionReservation reservation=new InspectionReservation();
			
			for (Project seaProject : list) {		
				ProjectTask task= projectTaskService.getByProjectNo(seaProject.getProjectNo());
				if(task!=null){
					ProjectDrawing drawing=new ProjectDrawing();
					drawing.setProjectNo(seaProject.getProjectNo());
					drawing.setCreateDate(task.getCreateTime());
				List<ProjectDrawing> projectDrawingList = projectDrawingService
						.selectByProjectNo(drawing);
				
				request.setAttribute("projectDrawingList", projectDrawingList);
				}
				request.setAttribute("task", task);
				//查询项目下单工厂
				List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(seaProject.getProjectNo());				
				//当选择新合同上传倒序排列时，获取上传进度情况
				if("7".equals(importantSelect)){
					for (ProjectFactory projectFactory : factoryList) {
						if(projectFactory.getFactoryId()!=null){
							List<QuoteWeeklyReport> reports = quoteWeeklyReportService.queryByCsgOrderIdAndType(seaProject.getProjectNo(), null, projectFactory.getFactoryId());
					        for (QuoteWeeklyReport quoteWeeklyReport : reports) {
								if(quoteWeeklyReport.getFileType() == MATERIAL_FILE){
									projectFactory.setIsUploadMaterial(true);
								}
								if(quoteWeeklyReport.getFileType() == VIDEO){
									projectFactory.setIsUploadVideo(true);
								}
							}
						}				
					}
				}
				seaProject.setFactoryList(factoryList);
				
								
				// 4.查询项目的图纸信息(可后加载)
				List<ProjectDrawing> projectDrawingList = projectDrawingService.selectProjectDrawingByProjectNo(seaProject.getProjectNo());
				// 5.质检报告信息   (可后加载)
				List<ProjectInspectionReport> projectInspectionReportList = projectInspectionReportService.selectInspectionReportByProjectNo(seaProject.getProjectNo());
				//6.查询检验计划(可后加载)
				List<ProjectInspectionReport> inspectionPlanList=projectInspectionReportService.selectInspectionPlanByProjectNo(seaProject.getProjectNo());
				//查询各状态任务数
				Map<String, Long> taskCount = projectTaskService.selectCountByStatus(seaProject.getProjectNo());
				seaProject.setAllTask(taskCount.get("allTask").intValue());
				seaProject.setPuaseTask(taskCount.get("pauseTask").intValue());
				seaProject.setFinishTask(taskCount.get("finishTask").intValue());
				seaProject.setQualityTask(taskCount.get("qualityTask").intValue());
				seaProject.setQualityFinishTask(taskCount.get("qualityFinishTask").intValue());
				//8.查询项目需求图纸报告
				List<ProjectDrawing> demandReportList=projectDrawingService.selectProjectDemandReportByNo(seaProject.getProjectNo());
				seaProject.setProjectDemandReportList(demandReportList);
				seaProject.setInspectionPlanList(inspectionPlanList);
				seaProject.setProjectDrawingList(projectDrawingList);
				seaProject.setInspectionReportList(projectInspectionReportList);

				// 7.查询本周是否上传的周报
				//判断当前时间是否是周一,是周一不是周一
				Calendar cal=Calendar.getInstance();
				cal.setTime(new Date()); 
				int week=cal.get(Calendar.DAY_OF_WEEK)-1;
				List<ProjectReport> projectReportList = projectReportService.selectProjectReportWeek(seaProject.getProjectNo(),week);
				seaProject.setProjectReportList(projectReportList);
				if(projectReportList!= null && projectReportList.size()>0){
					// 查询周报更新最近的时间
					ProjectReport projectReport = projectReportService.selectProjectReportLatelyDate(seaProject.getProjectNo());
					seaProject.setProjectReport(projectReport);
				}
				List<Feedback> feedbackList=feedbackService.selectFeedbackByProjectNo(seaProject.getProjectNo(),week);
				seaProject.setFeedbackList(feedbackList);
				//查询最近的反馈
				Feedback feedback = feedbackService.selectLastByProjectNo(seaProject.getProjectNo());
				seaProject.setFeedback(feedback);
				//查询生产计划
				List<ProductionPlan> planList = productionPlanService.selectByProjectNo(seaProject.getProjectNo());
				seaProject.setPlanList(planList);
		        //查询修改交期
//		        List<DeliveryDateLog> deliveryList = deliveryDateLogService.selectDeliveryList(seaProject.getProjectNo());
//		        seaProject.setDeliveryList(deliveryList);
				
				//8.查询质检上传
//				QualityReport qualityReport = qrService.selectNewestReportByProjectNo(seaProject.getProjectNo());
//				seaProject.setQualityReport(qualityReport);
				double amount=0.0;
				if(seaProject.getProjectAmount()!=null&&!"".equalsIgnoreCase(seaProject.getProjectAmount())){
					try{
					amount=Double.parseDouble(seaProject.getProjectAmount());
					}catch(Exception e){
						
					}
				}
				List<QualityReport> qualityReportList=new ArrayList<QualityReport>();
				if(roleNo==9&&amount>1.4){
				qualityReportList = qrService.selectByProjectNo1(seaProject.getProjectNo(),userName);
				seaProject.setQrList(qualityReportList);	
				}else{
				qualityReportList = qrService.selectByProjectNo(seaProject.getProjectNo());
				seaProject.setQrList(qualityReportList);
				}
				
				List<QualityReport> erpReports = erpReportService.selectErpReportByProjectNo(seaProject.getProjectNo());
				seaProject.setErpReports(erpReports);

				
				//9.查询该项目最近一次申请延期的信息(可后加载)
//				List<Delay> delays = delayService.selectAllByProjectNo(seaProject.getProjectNo());
//				seaProject.setDelayList(delays);
                //10.查询验货预约任务次数
//                reservation.setInputKey(seaProject.getProjectNo());
//                int reservationNum=inspectionReservationService.selectInspectionReservationCount(reservation);
//                seaProject.setInspectionReservationNum(reservationNum);
                //查询验货预约
                List<InspectionReservation> inspectionReservations = inspectionReservationService.selectInspectionList(seaProject.getProjectNo());

                //查询是否包含过程检
                int shippingApproval = 3; 
            	int t1 = -1;
            	int t2 = -1;
                for (InspectionReservation inspectionReservation2 : inspectionReservations) {
					if("过程".equals(inspectionReservation2.getTestType())){
						seaProject.setIsProcess(true);
					}				
					//当准许出货确认后赋值t1
					//未确认赋值t2
					if(inspectionReservation2.getShippingApproval() != null && inspectionReservation2.getShippingApproval() == 1){
						t1 = 1;
					}else{
						t2 = 0;
					}
				}
                //判断当前准许出货确认是全部确认、部分、都未确认
                if(t1 == 1 && t2 == 0){
                	shippingApproval = 2;
                }else if(t1 == 1 && t2 == -1){
                	shippingApproval = 1;
                }else if(t1 == -1 && t2 == 0){
                	shippingApproval = 0;
                }else if(t1 == -1 && t2 == -1){
                	shippingApproval = 3;
                }
                seaProject.setShippingApproval(shippingApproval);
                
                seaProject.setInspectionList(inspectionReservations);
                
                
                 //12 查询评论数据(可后加载)
                List<Comment> commentList = projectCommentService.selectProjectComment(seaProject.getProjectNo());
                seaProject.setCommentList(commentList);
                //13 查询项目客户投诉(可后加载)
                List<ProjectComplaint> complaintList = projectComplaintService.selectByProjectNo(seaProject.getProjectNo());
                seaProject.setComplaintList(complaintList);
                int unFinshedTask = projectComplaintService.countUnFinished(seaProject.getProjectNo());
                seaProject.setUnFinshedTask(unFinshedTask);
                //14 查询项目启动会
                MeetingRecord meetingRecord = new MeetingRecord();
                meetingRecord.setProjectNo(seaProject.getProjectNo());
                List<MeetingRecord> meetingRecords = meetingRecordService.selectMeetingRecordListCount(meetingRecord);
                for (MeetingRecord meetingRecord2 : meetingRecords) {
                	if("项目启动会".equals(meetingRecord2.getMeetingName())){
                		seaProject.setIsStart(true);
                	}
                	if("样品验货会".equals(meetingRecord2.getMeetingName())){
						seaProject.setIsSample(true);
					}
					if("大货验货会".equals(meetingRecord2.getMeetingName())){
						seaProject.setIsProduct(true);
					}
				}
                
                //查询是否属于返单项目
                if(seaProject.getProjectNo().contains("-")){
                	seaProject.setIsStart(true);
                	seaProject.setIsSample(true);
                	if(seaProject.getIsProduct() != null && seaProject.getIsProduct() != true){
                		for (QualityReport qualityReport2 : erpReports) {   					
        					if(StringUtils.isNotBlank(qualityReport2.getExplainCause()) && qualityReport2.getExplainCause().contains("没问题")){    						
        						seaProject.setIsProduct(true);
        					}
        				}
        				//如果质检报告已发并且无问题，则不需要大货验货会
        				if(qualityReportList!= null && qualityReportList.size()> 0 && qualityReportList.get(0).getStatus() == 0){
        					seaProject.setIsProduct(true);
        				}
                   }    				
                }
                
                
                
                //15 细节状态内容
                if(seaProject.getDetailStatus() != null && seaProject.getDetailStatus() != 0){
                	seaProject.setDetailStr(DetailStatusEnum.getEnum(seaProject.getDetailStatus()).getValue());
                }
                //16 判断是否属于一周内制作合同
                Date actualStartDate = seaProject.getActualStartDate();
                Date createDate = seaProject.getDateSampleUploading();
                if(createDate != null){
                	if(actualStartDate != null){
                	    Boolean compareDate = DateFormat.compareDate(createDate, actualStartDate, 7);	
                	    seaProject.setIsContract(compareDate);              	    
                	}else{
                		Boolean compareDate = DateFormat.compareDate(createDate, new Date(), 7);	
                	    seaProject.setIsContract(compareDate);              	    
                	}
                }                
                //17 查询暂停项目暂停列表
                List<ProjectPause> projectPauses = projectPauseService.selectByProjectNo(seaProject.getProjectNo());
                seaProject.setProjectPauses(projectPauses);
                
                //18 查询项目合同交期
                int contractDays = 0;
                //只存在样品合同，计算样品交期和po日期天数
                //存在样品交期和不存在po日期，则计算样品交期-合同时间-7天              
                if(seaProject.getOriginalSampleScheduledDate() != null && seaProject.getDateSampleUploading() != null){
                	contractDays = DateFormat.calDays(seaProject.getOriginalSampleScheduledDate(), seaProject.getDateSampleUploading());
                }else if(seaProject.getOriginalSampleScheduledDate() != null && seaProject.getDateSampleUploading() == null && seaProject.getActualStartDate() != null){
                	contractDays = DateFormat.calDays(seaProject.getOriginalSampleScheduledDate(), seaProject.getActualStartDate()) + 7;
                }
                 //如果存在大货合同，则以大货合同计算
                if(seaProject.getOriginalDeliveryDate() != null && seaProject.getDateSampleUploading() != null){
                	contractDays = DateFormat.calDays(seaProject.getOriginalDeliveryDate(), seaProject.getDateSampleUploading());
                }else if(seaProject.getOriginalDeliveryDate() != null && seaProject.getDateSampleUploading() == null && seaProject.getActualStartDate() != null){
                	contractDays = DateFormat.calDays(seaProject.getOriginalDeliveryDate(), seaProject.getActualStartDate()) + 7;
                }
                seaProject.setContractDays(contractDays);
                
                 // 判断项目状态
                 //新项目和取消项目和暂停项目不加入延期
				 if(seaProject.getProjectStatus() != OrderStatusEnum.NEW_ORDER.getCode() && seaProject.getProjectStatus() != OrderStatusEnum.CANCEL_ORDER.getCode() && seaProject.getProjectStatus() != OrderStatusEnum.PAUSE_ORDER.getCode()){
             		Map<String, Object> map = judgeDelay(seaProject);
             		Boolean judgeDelay = (Boolean)map.get("delayType");
             		int delayDays = Integer.parseInt(map.get("delayDays").toString());
             		seaProject.setDelayDays(delayDays);
            		if(judgeDelay == true){
            			status = "有延期";
                		seaProject.setStatus(status);
                	}
				 } 

				if (seaProject.getPlantAnalysis()!=null && seaProject.getPlantAnalysis() != 0) {
					seaProject.setPlantAnalysisView(ProjectAnalysisEnum.getEnum(seaProject.getPlantAnalysis()).getValue());
					
					//A、B级项目如果交期过了一半还未过程检，给予提示
					if((seaProject.getPlantAnalysis() == 1 || seaProject.getPlantAnalysis() == 2) && seaProject.getDeliveryDate() != null && seaProject.getActualStartDate() != null){
						long a1 = seaProject.getDeliveryDate().getTime()-seaProject.getActualStartDate().getTime();  //总共时间
						long a2 = today.getTime()-seaProject.getActualStartDate().getTime();                         //当前已用时间
						if(a1 < a2*2  && (seaProject.getIsProcess() == null || seaProject.getIsProcess() != true)){
							seaProject.setIsNoteProcess(true);
						}
					}
				}
				
				//查询项目关键词
				List<AnalysisIssue> issueList = analysisIssueService.selectByProjectNo(seaProject.getProjectNo(), 0);
				seaProject.setAnalysisIssueList(issueList);
				QualityAnalysis qualityAnalysis = qualityAnalysisService.selectByProjectNo(seaProject.getProjectNo());
				seaProject.setQualityAnalysis(qualityAnalysis);
				}
		
			long t4 = new Date().getTime();
			LOG.info("其他查询耗时："+(t4-end));
		
			
			//计算尾页
			Integer lastNum = new BigDecimal(totalCountList).divide(new BigDecimal(pageSize)).setScale(0,BigDecimal.ROUND_UP).intValue();
			
			request.setAttribute("importantSelect", importantSelect);
			request.setAttribute("project", project);
			request.setAttribute("taskList", taskList);
			request.setAttribute("list", list);
			request.setAttribute("userId", userId);
			request.setAttribute("roleNo", roleNo);
			request.setAttribute("userName", userName);
			request.setAttribute("projectNum", totalCountList);
			request.setAttribute("projectType", projectType);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("totalCount", totalCountList);
			request.setAttribute("lastNum", lastNum);
			request.setAttribute("operatorType", operatorType);
			request.setAttribute("qualityReportSelect", qualityReportSelect);
			request.setAttribute("expectedShipmentSelect",expectedShipmentSelect);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("error", e);
		} 
             return "project_list_pc"; 
	}

	
	
	
	/**
	 * 查询项目详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryProjectDetail")
	public JsonResult queryProjectDetail(HttpServletRequest request,HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		String projectNo = request.getParameter("projectNo");
		
		//查询项目的图纸信息
		List<ProjectDrawing> projectDrawingList = projectDrawingService.selectProjectDrawingByProjectNo(projectNo);
		//质检报告信息  
		List<ProjectInspectionReport> projectInspectionReportList = projectInspectionReportService.selectInspectionReportByProjectNo(projectNo);
		//查询检验计划
		List<ProjectInspectionReport> inspectionPlanList=projectInspectionReportService.selectInspectionPlanByProjectNo(projectNo);
		//查询评论数据
        List<Comment> commentList = projectCommentService.selectProjectComment(projectNo);
        //查询项目客户投诉
        List<ProjectComplaint> complaintList = projectComplaintService.selectByProjectNo(projectNo);
        int unFinshedTask = projectComplaintService.countUnFinished(projectNo);
        //查询修改交期
        List<DeliveryDateLog> deliveryList = deliveryDateLogService.selectDeliveryList(projectNo);
        //查询分批
        List<ProjectSchedule> projectScheduleList = projectScheduleService.selectByProjectNo(projectNo);
        
		
        
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectDrawingList", projectDrawingList);
        map.put("projectInspectionReportList", projectInspectionReportList);
        map.put("inspectionPlanList", inspectionPlanList);
        map.put("commentList", commentList);
        map.put("complaintList", complaintList);
        map.put("unFinshedTask", unFinshedTask);
        map.put("deliveryList", deliveryList);
        map.put("projectScheduleList", projectScheduleList);
        
        jsonResult.setOk(true);
        jsonResult.setData(map);
        
		return jsonResult;
	}
	
	
	/**
	 * 查询项目工厂信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryProjectFactory")
	public JsonResult queryProjectFactory(HttpServletRequest request,HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		String projectNo = request.getParameter("projectNo");
        //查询工厂
        List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectNo);
      
        Map<String, Object> map = new HashMap<String, Object>();     
        map.put("factoryList", factoryList);
        jsonResult.setOk(true);
        jsonResult.setData(map);
        
		return jsonResult;
	}
	
	
	
	
	

	/**
	 * 查询项目详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/showDetails")
	public String showDetails(HttpServletRequest request,
			HttpServletResponse response) {
		String projectNo = request.getParameter("projectNo");
		String userId = request.getParameter("userId");
		Integer roleNo = null;                            // 判断是管理员，销售，采购
        String userName = WebCookie.getUserName(request);
        if(StringUtils.isNotBlank(userName)){
        	User user = userService.findUserByName(userName);
        	roleNo = user.getRoleNo();
        	userId = user.getId()+"";
        }else{
        	return "redirect:/index.jsp";
        }
		
		
		List<Task> taskList = new ArrayList<Task>();
		List<QualityReport> qrls = null;
		try {
		// 1.查询项目详细信息
		Project project = projectService.showDetails(projectNo);	
		if(StringUtils.isNotBlank(project.getProjectAmount())){
			String regEx="[^0-9]";  
			Pattern p = Pattern.compile(regEx);  
			Matcher m = p.matcher(project.getProjectAmount());  
			project.setProjectAmount(m.replaceAll("").trim());
		}
		
		
		//判断项目是否属于新项目、如果上一单有投诉，算作新项目
		if(project!=null){
			if(!projectNo.contains("-")){
				project.setIsNewProject(true);
			}else{
			    String[] split = projectNo.split("-");
			    int num = 0;
			    if(split!=null&&split.length>0){
			    	num = Integer.parseInt(split[1]);
			    	num--;
			    	String newProjectNo = split[0]+"-"+num; 
			    	List<ProjectComplaint> complaints = projectComplaintService.selectByProjectNo(newProjectNo);
			    	if(complaints!=null&&complaints.size()>0){
			    		project.setIsNewProject(true);
			    	}
			    }			    
			}			
		}
		// 2.查询项目的任务信息
		taskList = taskService.findTaskByProjectNo(projectNo);
		for (int i = 0; i < taskList.size(); i++) {// 遍历任务根据id查询任务汇报进展
			Task task = taskList.get(i);
			// 3.查询任务的汇报进展
			task.setTaskReportList(taskReportService.selectTaskReportByNo(task
					.getId()));
		}
		// 4.查询项目的图纸信息
		List<ProjectDrawing> projectDrawingList = projectDrawingService
				.selectProjectDrawingByProjectNo(projectNo);
		// 5.质检报告信息
		List<ProjectInspectionReport> projectInspectionReportList = projectInspectionReportService
				.selectInspectionReportByProjectNo(projectNo);
		// 6.查询项目留信息
		List<Comment> commentList = projectCommentService
				.selectProjectComment(projectNo);
		// 7.查询销售输入的状态信息
		List<StatusEnter> statusEnterList = statusEnterService
				.selectProjectStatusEnter(projectNo);
		for (StatusEnter statusEnter : statusEnterList) {
			if (StringUtils.isNotBlank(statusEnter.getStatusType())) {
				String statusType = statusEnter.getStatusType();
				String[] others = statusType.split(",");
				statusEnter.setStatusTypeList(Arrays.asList(others));
			}
		  }
             double Amount=0.0;
             if(project.getProjectAmount()!=null&&!"".equalsIgnoreCase(project.getProjectAmount())){
            	 try{
            	 Amount=Double.parseDouble(project.getProjectAmount());
            	 }catch(Exception e){
            		 
            	 }
             }
		    // 8 质检报告
		    if(Amount>=1.4 &&roleNo==9){
		    qrls = qrService.selectByProjectNo1(projectNo,userName);	
		    }else{
		    qrls = qrService.selectByProjectNo(projectNo);
		    }
			if (qrls != null && qrls.size() > 0) {
				for (QualityReport qr : qrls) {
					StringBuilder detailView = new StringBuilder("[");
					detailView.append(qr.getProjectNo()).append("]");
					detailView.append(
							QualityTypeEnum.getEnum(qr.getType()).getValue())
							.append(",");
					detailView.append(
							QualityStatusEnum.getEnum(qr.getStatus())
									.getValue()).append(",[");
					detailView.append(qr.getUser()).append("/")
							.append(DateFormat.date2String(qr.getCreatetime())).append("]");
					qr.setDetailView(detailView.toString());
					String eid= Base64Encode.getBase64(qr.getId()+"");
					eid=eid.replaceAll("=", "");
					qr.setEid(eid);
				}
			}

        //9.查询检验计划
		List<ProjectInspectionReport> inspectionPlanList=projectInspectionReportService.selectInspectionPlanByProjectNo(projectNo);
		
		//大货交期
		List<ProjectSchedule> projectSchedules = projectScheduleService.selectByProjectNo(projectNo);
		
		//下单工厂列表
		List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectNo);
		//质检视频
		List<FactoryQualityInspectionVideo> videos = factoryQualityInspectionVideoService.selectByProjectNo(projectNo);
		//查询客户投诉
		String originalProjectNo = projectNo;
		if(originalProjectNo.contains("-")){
			 originalProjectNo = originalProjectNo.split("-")[0];
		}	  
		List<ProjectComplaint> complaints = projectComplaintService.selectByProjectNoDim(originalProjectNo);
		//查询问题出现的次数
		List<AnalysisIssue> analysisIssueList = analysisIssueService.selectByProjectNo(projectNo, 0);
		for (AnalysisIssue analysisIssue : analysisIssueList) {
			if(StringUtils.isNotBlank(analysisIssue.getIssue())){
				List<String> issueList = qualityAnalysisService.selectByIssue(analysisIssue.getIssue());
				analysisIssue.setOccurrenceNum(issueList != null ? issueList.size() : 0);
			}
		}
		project.setAnalysisIssueList(analysisIssueList);
		//查询项目任务
		List<ProjectTask> projectTaskList = projectTaskService.selectByProjectNo(projectNo);
		
		//查询问题
		List<AnalysisIssue> issueList = analysisIssueService.selectTop10(null);
		
		//查询历史项目个数和原项目
		String originalProject = projectNo;
		String[] split = projectNo.split("-");
		List<Project> projectList = null;
		if(split.length > 1){			
			originalProject = split[0];
		    Project projectQuery = new Project();
		    projectQuery.setInputKey(originalProject);
		    projectQuery.setPageSize(100);
		    projectQuery.setPageNumber(0);
		    projectList = projectService.findProjectList(projectQuery);
		}
		//查询需求汇总
		ProductionPlan productionPlan = productionPlanService.selectDemandByProjectNo(projectNo);
		if(productionPlan!=null){
			String node = productionPlan.getPlanNode();
			if(StringUtils.isNotBlank(node)){
				productionPlan.setPlanNode(URLEncoder.encode(node,"utf-8"));
			}
		}			
		//查询检验计划详情
		List<InspectionPlan> planList = null;
		List<InspectionPlan> plans = null;
		planList = inspectionPlanService.selectByProjectNo(projectNo, null);
		plans = planList.stream().filter(distinctByKey(plan->plan.getProductComponent())).collect(Collectors.toList());
		String projectNo1=projectNo.substring(0,8);
		List<QualityAndEfficiencyReport> efficiencyReportList1=qualityAndEfficiencyReportService.getAll(projectNo1);//查询历史是否已上传文件
		boolean technicalAnalysisReport=false;
		boolean sampleEvaluation=false;
		boolean productionPreparation=false;
		boolean packagingScheme=false;
		boolean qualityEfficiency=false;
		boolean analysisOfConcentrator=false;
		boolean bargain=false;
		for(int i=0;i<efficiencyReportList1.size();i++){
			QualityAndEfficiencyReport report=efficiencyReportList1.get(i);
			if(report.getCategory()==1){
				technicalAnalysisReport=true;
			}else if(report.getCategory()==2){
				sampleEvaluation=true;
			}else if(report.getCategory()==3){
				productionPreparation=true;
			}else if(report.getCategory()==4){
				packagingScheme=true;
			}else if(report.getCategory()==5){
				qualityEfficiency=true;
			}else if(report.getCategory()==6){
				analysisOfConcentrator=true;
			}
			
		}
		if(factoryList.size()>0){
			bargain=true;
		}
		request.setAttribute("bargain", bargain);
		request.setAttribute("technicalAnalysisReport", technicalAnalysisReport);
		request.setAttribute("sampleEvaluation", sampleEvaluation);
		request.setAttribute("productionPreparation", productionPreparation);
		request.setAttribute("packagingScheme", packagingScheme);
		request.setAttribute("qualityEfficiency", qualityEfficiency);
		request.setAttribute("analysisOfConcentrator", analysisOfConcentrator);
		project.setProjectTaskList(projectTaskList);
		project.setQrList(qrls);
		project.setTaskList(taskList);
		project.setCommentList(commentList);
		project.setProjectDrawingList(projectDrawingList);
		project.setInspectionReportList(projectInspectionReportList);
		project.setInspectionPlanList(inspectionPlanList);
		project.setStatusEnterList(statusEnterList);
		request.setAttribute("project", project);
		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.setAttribute("projectSchedules", projectSchedules);
		request.setAttribute("factoryList", factoryList);
		request.setAttribute("videos", videos);
		request.setAttribute("complaints", complaints);
		request.setAttribute("issueList", issueList);
		request.setAttribute("originalProject", originalProject);
		request.setAttribute("projectList", projectList);
		request.setAttribute("productionPlan", productionPlan);
		request.setAttribute("planList", planList);
		request.setAttribute("plans", plans);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "project_detail";
	}

	/**
	 * 编辑项目详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/editDetails")
	public String editDetails(HttpServletRequest request,
			HttpServletResponse response) {
		String projectNo = request.getParameter("projectNo");
		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String purchaseNameId = request.getParameter("purchaseNameId");
		String actualStartDate = "";
		String sampleScheduledDate = "";
		String scheduledDate = "";

		try {
			// List<Task> taskList=new ArrayList<Task>();

			// 1.查询项目详细信息
			Project project = projectService.showDetails(projectNo);
			// 开工日期
			actualStartDate = DateFormat.date2String(project
					.getActualStartDate());
			// 样品交期
			sampleScheduledDate = DateFormat.date2String(project
					.getSampleScheduledDate());
			// 大货交期
			scheduledDate = DateFormat.date2String(project.getScheduledDate());

			List<ProductionPlan> planList = project.getPlanList();
			if (planList != null && planList.size() > 0) {
				for (int i = 0; i < planList.size(); i++) {
					ProductionPlan pp = planList.get(i);
					pp.setPlanDate2String(DateFormat.date2String(pp
							.getPlanDate()));
				}
			}
			
			/**
			 * 根据项目号查询大货交期
			 */
			List<ProjectSchedule> projectSchedules = projectScheduleService.selectByProjectNo(projectNo);
			for (ProjectSchedule projectSchedule : projectSchedules) {
				//Add by polo 2018.7.19
				if(projectSchedule.getNum() == 1){
					request.setAttribute("projectSchedules1", projectSchedule.getPredictDate());
				}
				if(projectSchedule.getNum() == 2){
					request.setAttribute("projectSchedules2", projectSchedule.getPredictDate());
				}
				if(projectSchedule.getNum() == 3){
					request.setAttribute("projectSchedules3", projectSchedule.getPredictDate());
				}

			}
			
			List<Project> projectList=new ArrayList<Project>();
			projectList.add(project);
			projectDateTask.syncProjectDate(projectList);
			request.setAttribute("project", project);
			request.setAttribute("actualStartDate", actualStartDate);
			request.setAttribute("sampleScheduledDate", sampleScheduledDate);
			request.setAttribute("scheduledDate", scheduledDate);

			request.setAttribute("roleNo", roleNo);
			request.setAttribute("userId", userId);
			request.setAttribute("userName", userName);
			request.setAttribute("purchaseNameId", purchaseNameId);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("error", e);
		}

		return "project_plan";

	}

	/**
	 * 跳转延期录入界面
	 * @Title getDelay 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/getDelay")
	public String getDelay(HttpServletRequest request,HttpServletResponse response){
		String projectNo = request.getParameter("projectNo");
		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		List<ProjectSchedule> projectSchedules = projectScheduleService.selectByProjectNo(projectNo);
		
		
		request.setAttribute("projectNo", projectNo);
		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.setAttribute("projectSchedules", projectSchedules);
		return "delay";
	}
	
	
	
	/**
	 * 更新项目信息(交期)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/updateProject")
	@ResponseBody
	public JsonResult updateProject(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JsonResult jsonResult = new JsonResult();
		String projectNo = request.getParameter("projectNo");
		String projectSchedules1 = request.getParameter("projectSchedules1");
		String projectSchedules2 = request.getParameter("projectSchedules2");
		String projectSchedules3 = request.getParameter("projectSchedules3");
		
		List<ProjectSchedule> projectSchedules = new ArrayList<ProjectSchedule>();
		if(StringUtils.isNotBlank(projectSchedules1)){
			ProjectSchedule projectSchedule = projectScheduleService.selectByProjectNoAndNum(projectNo, 1);
			if(projectSchedule == null){
				projectSchedule = new ProjectSchedule();
				projectSchedule.setNum(1);
				projectSchedule.setPredictDate(DateUtil.StrToDate(projectSchedules1));
				projectSchedule.setCreateTime(new Date());
				projectSchedule.setProjectNo(projectNo);
				projectSchedule.setIsFinish(0);
				projectSchedule.setOriginalDate(DateUtil.StrToDate(projectSchedules1));
				projectSchedules.add(projectSchedule);
			}else{
				projectSchedule.setPredictDate(DateUtil.StrToDate(projectSchedules1));
				projectSchedule.setUpdateTime(new Date());
				projectScheduleService.updateByPrimaryKeySelective(projectSchedule);
			}			
		}
		if(StringUtils.isNotBlank(projectSchedules2)){
			ProjectSchedule projectSchedule = projectScheduleService.selectByProjectNoAndNum(projectNo, 2);
			if(projectSchedule == null){
				projectSchedule = new ProjectSchedule();
				projectSchedule.setNum(2);
				projectSchedule.setPredictDate(DateUtil.StrToDate(projectSchedules2));
				projectSchedule.setCreateTime(new Date());
				projectSchedule.setProjectNo(projectNo);
				projectSchedule.setIsFinish(0);
				projectSchedule.setOriginalDate(DateUtil.StrToDate(projectSchedules1));
				projectSchedules.add(projectSchedule);
			}else{
				projectSchedule.setPredictDate(DateUtil.StrToDate(projectSchedules2));
				projectSchedule.setUpdateTime(new Date());
				projectScheduleService.updateByPrimaryKeySelective(projectSchedule);
			}			
		}
		if(StringUtils.isNotBlank(projectSchedules3)){
			ProjectSchedule projectSchedule = projectScheduleService.selectByProjectNoAndNum(projectNo, 3);
			if(projectSchedule == null){
				projectSchedule = new ProjectSchedule();
				projectSchedule.setNum(3);
				projectSchedule.setPredictDate(DateUtil.StrToDate(projectSchedules3));
				projectSchedule.setCreateTime(new Date());
				projectSchedule.setProjectNo(projectNo);
				projectSchedule.setIsFinish(0);
				projectSchedule.setOriginalDate(DateUtil.StrToDate(projectSchedules1));
				projectSchedules.add(projectSchedule);
			}else{
				projectSchedule.setPredictDate(DateUtil.StrToDate(projectSchedules3));
				projectSchedule.setUpdateTime(new Date());
				projectScheduleService.updateByPrimaryKeySelective(projectSchedule);
			}			
		}
		
		
		
//		String scheduledDate = request.getParameter("scheduledDate");// 大货交期
//		String sampleScheduledDate = request.getParameter("sampleScheduledDate");
//		String actualStartDate = request.getParameter("actualStartDate");// 开工日期
//		List<ProductionPlan> planList = new ArrayList<ProductionPlan>();
//		ProductionPlan productionPlan1;
//		ProductionPlan productionPlan2;

//		String node = request.getParameter("node");
//		String nodeDate = request.getParameter("nodeDate");
//		String node1 = request.getParameter("node1");
//		String nodeDate1 = request.getParameter("nodeDate1");

//		if (StringUtils.isNotBlank(node) && StringUtils.isNotBlank(nodeDate)) {
//			productionPlan1 = new ProductionPlan();
//			productionPlan1.setId(IdGen.uuid());
//			productionPlan1.setPlanNode(node);
//			productionPlan1.setPlanDate(java.sql.Date.valueOf(nodeDate));
//			productionPlan1.setProjectNo(projectNo);
//			productionPlan1.setCreateDate(new Date());
//			planList.add(productionPlan1);
//		}
//		if (StringUtils.isNotBlank(node1) && StringUtils.isNotBlank(nodeDate1)) {
//			productionPlan2 = new ProductionPlan();
//			productionPlan2.setId(IdGen.uuid());
//			productionPlan2.setPlanNode(node1);
//			productionPlan2.setPlanDate(java.sql.Date.valueOf(nodeDate1));
//			productionPlan2.setProjectNo(projectNo);
//			productionPlan2.setCreateDate(new Date());
//			planList.add(productionPlan2);
//		}

		try {

//			productionPlanService.addProductionPlanList(planList);

//			Project pro = projectService.selectProjctDetails(projectNo);// 查询交期是否存在
//			if (pro != null) {
//				if (pro.getDeliveryDate() == null) {// 判断交期是否存在，不存在就添加
//					if(StringUtils.isNotBlank(scheduledDate)){
//						pro.setDeliveryDate(DateUtil.StrToDate(scheduledDate));// 新交期(大货交期)
//					}
//				}
//				if (pro.getActualStartDate() == null) {
//					if(StringUtils.isNotBlank(actualStartDate)){
//						pro.setActualStartDate(DateUtil.StrToDate(actualStartDate));// 实际开工日期
//					}
//				}
//				if (pro.getSampleScheduledDate() == null) {// 样品交期
//					if(StringUtils.isNotBlank(sampleScheduledDate)){
//						pro.setSampleScheduledDate(DateUtil.StrToDate(sampleScheduledDate));
//					}
//				}
//			}
//			projectService.updateProjectInfo(pro);
	        //ProjectDateTask projectDateTask=new ProjectDateTask();
//			List<Project> projectList=new ArrayList<Project>();
//			projectList.add(pro);
//			projectDateTask.syncProjectDate(projectList);
			if(projectSchedules!=null && projectSchedules.size() > 0){
				projectScheduleService.insertBatch(projectSchedules);
			}
			jsonResult.setOk(true);
			jsonResult.setMessage("设置成功");
			return jsonResult;
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage(e.getMessage());
			LOG.error("error", e);
		} finally {
			return jsonResult;
		}
	}

	

	/**
	 * 更新分批大货状态  polo  2018.07.20
	 * @Title updateProjectSchedule 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @return JsonResult
	 */
	@RequestMapping("/updateProjectSchedule")
	@ResponseBody
	public JsonResult updateProjectSchedule(HttpServletRequest request,HttpServletResponse response) throws IOException {
		JsonResult jsonResult = new JsonResult();
		try {
			String id = request.getParameter("id");
			String finishDate = request.getParameter("finishDate");
			ProjectSchedule projectSchedule = projectScheduleService.selectByPrimaryKey(Integer.parseInt(id));
			projectSchedule.setActualDate(DateUtil.StrToDate(finishDate));
			projectSchedule.setIsFinish(1);
			projectScheduleService.updateByPrimaryKeySelective(projectSchedule);
			jsonResult.setOk(true);
			jsonResult.setMessage("更新成功");
			return jsonResult;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage(e.getMessage());
			LOG.error("error", e);
			return jsonResult;
		}
	}
	
	
	
	/**
	 * 查询已大货完结列表
	 * @Title queryFinish 
	 * @Description 
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("/queryFinish")
	public String queryFinish(HttpServletRequest request){
		String projectNo = request.getParameter("projectNo");
		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		List<ProjectSchedule> projectSchedules = projectScheduleService.selectByProjectNo(projectNo);
		Project project = projectService.selectProjctDetails(projectNo);
		List<Delay> list = delayService.selectAllByProjectNo(projectNo);
		
		request.setAttribute("projectNo", projectNo);
		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.setAttribute("projectSchedules", projectSchedules);
		request.setAttribute("project", project);
		request.setAttribute("delayList", list);
		request.setAttribute("nowDate", new Date());
		
		return "project_finish";
	}
	
	/**
	 * 查询项目
	 * @Title 样品完结页面 
	 * @Description 
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("/querySample")
	public String querySample(HttpServletRequest request){
		String projectNo = request.getParameter("projectNo");
		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		Project project = projectService.selectProjctDetails(projectNo);
		
		request.setAttribute("projectNo", projectNo);
		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.setAttribute("project", project);
		request.setAttribute("nowDate", new Date());
		
		return "sample_finish";
	}
	
	
	
	/**
	 * 更新项目状态是否交货
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/updateProjectStatus")
	@ResponseBody
	public JsonResult updateProjectStatus(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		String projectNo = request.getParameter("projectNo");
		String type=request.getParameter("type");
		String finishTime = request.getParameter("finishTime");
		try {
			// 1.查询该项目号的项目信息(projectNo)
			Project project = projectService.selectProjctDetails(projectNo);
			if(type.equals("2")){//样品交货

				if (project.getSampleScheduledDate() == null) {
					jsonResult.setOk(false);
					jsonResult.setMessage("该项目样品交期还未设置 !!!");
					return jsonResult;
				}
			}else{//大货交货
				if (project.getFinish() == 1 ) {
					jsonResult.setOk(false);
					jsonResult.setMessage("该项目大货已经完结 !!!");
					return jsonResult;
				}
				if (project.getDeliveryDate() == null) {
					jsonResult.setOk(false);
					jsonResult.setMessage("该项目大货交期还未设置 !!!");
					return jsonResult;
				}
			}
			
			// 2.查询该项目所对应任务的状态(0未完成 1已完成)
			List<Task> taskList = taskService.findTaskByProjectNo(projectNo);
			Task task = null;
			if (taskList.size() > 0) {
				for (int i = 0; i < taskList.size(); i++) {
					task = taskList.get(i);
					if (task.getStatus().equals(
							TaskStatusEnum.DEFAULT.getCode())) {// 任务未完成
						jsonResult.setOk(false);
						jsonResult.setMessage("请完成该项目所对应的任务");
						return jsonResult;
					} else {
						if(type.equals("2")){//样品交货
							project.setSampleFinish(1);// 样品项目完成
							project.setSampleFinishTime(DateUtil.StrToDate(finishTime));
							project.setProjectStatus(OrderStatusEnum.SAMPLE_ORDER.getCode());
							projectService.updateProjectInfo(project);
						}else{//大货交货
							project.setFinish(1);// 大货项目完成
							project.setFinishTime(DateUtil.StrToDate(finishTime));
							project.setProjectStatus(OrderStatusEnum.COMPLETE_ORDER.getCode());
							projectService.updateProjectInfo(project);
						}
						jsonResult.setOk(true);
						jsonResult.setMessage("项目完结成功");
						return jsonResult;
					}
				}
			} else {// 该项目没有任务也可以完结交货
				// 项目对应的任务完成，就可以修改项目的状态(完成状态)
				if(type.equals("2")){//样品交货
					project.setSampleFinish(1);// 样品项目完成
					project.setSampleFinishTime(DateUtil.StrToDate(finishTime));
					project.setProjectStatus(OrderStatusEnum.SAMPLE_ORDER.getCode());
					projectService.updateProjectInfo(project);
				}else{//大货交货
					project.setFinish(1);// 大货项目完成
					project.setFinishTime(DateUtil.StrToDate(finishTime));
					project.setProjectStatus(OrderStatusEnum.COMPLETE_ORDER.getCode());
					projectService.updateProjectInfo(project);
				}
				
			}
			//更新状态字段
			//ProjectDateTask projectDateTask=new ProjectDateTask();
			List<Project> projectList=new ArrayList<Project>();
			projectList.add(project);
			projectDateTask.syncProjectDate(projectList);
			jsonResult.setOk(true);
			jsonResult.setMessage("项目没任务可以完结");
			return jsonResult;
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage(e.getMessage());
			e.printStackTrace();
			LOG.error("error", e);
		} finally {
			return jsonResult;
		}
	}
	


	/**
	 * 显示公司所有的项目(可以根据采购进行筛选)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/showAllList")
	public String showAllList(HttpServletRequest request,
			HttpServletResponse response) {
		String roleNo = request.getParameter("roleNo");
		List<Project> allList = projectService.selectAllProject();
		request.setAttribute("allList", allList);
		request.setAttribute("roleNo", roleNo);
		return "task_allocation";
	}

	/**
	 * 项目相关联的任务
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/projectTask")
	public String projectTask(HttpServletRequest request,
			HttpServletResponse response) {
		String projectNo = request.getParameter("projectNo");
		Integer roleNo = Integer.parseInt(request.getParameter("roleNo"));
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String userName = request.getParameter("userName");

		// 将消息更新成已读状态
		Task updateTask = new Task();
		updateTask.setType(1);
		updateTask.setProjectNo(projectNo);
		taskService.updateTask(updateTask);

		List<Task> taskList = new ArrayList<Task>();
		Task task = new Task();
		if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// 如果是管理员
			task.setProjectNo(projectNo);
			taskList = taskService.selectTaskList(task);// 任务列表
		} else if (roleNo == 5) {// 销售
			task.setProjectNo(projectNo);
			taskList = taskService.selectTaskByProjectNo(task);
		} else if (roleNo == 6) {// 采购
			task.setProjectNo(projectNo);
			taskList = taskService.selectTaskPurchaseByProjectNo(task);
		}

		request.setAttribute("taskList", taskList);
		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userName", userName);
		request.setAttribute("userId", userId);

		return "task_info";
	}

	/**
	 * 消息中心
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/message")
	@ResponseBody
	public JsonResult projectMessageCenter(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		List<Project> messageList = new ArrayList<Project>();
		Integer roleNo = Integer.parseInt(request.getParameter("roleNo"));
		String userName = request.getParameter("userName");
		Integer userIdS = Integer.parseInt(request.getParameter("userId"));
		int emailUserId = 0;
		int purchaseId = 0;
		int pageNumber;
		if (StringUtils.isNotBlank(request.getParameter("pageNumber"))) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));// 第几页,1,2
		} else {
			pageNumber = 1;
		}
		Integer pageSize = 10;
		Project project = new Project();
		project.setPageSize(pageSize);
		project.setPageNumber(pageSize * (pageNumber - 1));
		List<Project> messageCountList = new ArrayList<Project>();
		try {
			if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// 如果是管理员
				messageList = projectService.selectProjectRelationTask(project);
				messageCountList = projectService
						.selectProjectRelationTaskCount(project);
			} else if (roleNo == 5) {// 销售
				emailUserId = Integer.parseInt(request.getParameter("userId"));
				project.setEmailUserId(emailUserId);
				messageList = projectService.selectProjectRelationTask(project);
				messageCountList = projectService
						.selectProjectRelationTaskCount(project);
			} else if (roleNo == 6) {// 采购
				purchaseId = Integer.parseInt(request.getParameter("userId"));
				project.setPurchaseId(purchaseId);
				messageList = projectService
						.selectPurchaseProjectRelationTask(project);
				messageCountList = projectService
						.selectPurchaseProjectRelationTaskCount(project);
			}
			Map<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("messageList", messageList);
			hashMap.put("userId", userIdS);
			hashMap.put("roleNo", roleNo);
			hashMap.put("userName", userName);
			hashMap.put("pageSize", pageSize);
			hashMap.put("pageNumber", pageNumber);
			hashMap.put("totalCount", messageCountList.size());
			jsonResult.setData(hashMap);
			jsonResult.setOk(true);
		} catch (NumberFormatException e) {
			jsonResult.setMessage(e.getMessage());
			jsonResult.setOk(false);
			e.printStackTrace();
			LOG.error("error", e);
		}
		return jsonResult;
	}

	/**
	 * 统计两周内交期变更的项目
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/projectStatistics")
	@ResponseBody
	public JsonResult statisticsAllList(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		String start = request.getParameter("startDate");
		String end = request.getParameter("endDate");
		String value = request.getParameter("value");
		Date startDate = null;
		Date endDate = null;
		if (StringUtils.isNotBlank(start)) {
			startDate = DateUtil.StrToDate(start);
		} else {
			startDate = DateUtil.StrToDate(DateUtil.getTwoWeeksDate());// 得到前两周的时间(默认)
		}
		if (StringUtils.isNotBlank(end)) {
			endDate = DateUtil.StrToDate(end);
		} else {
			endDate = new Date();// 结束时间
		}
		int roleNo = Integer.parseInt(request.getParameter("roleNo")); // 判断是否是管理员
		Project project = new Project();
		List<Project> list = new ArrayList<Project>();
		try {
			if (value.equals("1")) { // 统计交期变更项目
				if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// 判断是否是管理员
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					list = projectService.findDelayProjectList(project);
				} else if (roleNo == 5) {// 销售
					Integer emailUserId = Integer.parseInt(request
							.getParameter("userId"));// 拿到用户的userId
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					project.setEmailUserId(emailUserId);
					list = projectService.findDelayProjectList(project);
				} else if (roleNo == 6) {// 采购
					Integer purchaseId = Integer.parseInt(request
							.getParameter("userId"));// 拿到用户的userId
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					project.setPurchaseId(purchaseId);
					list = projectService.findDelayProjectPurchaseList(project);
				}
			} else if (value.equals("2")) {// 统计采购情况汇报
				if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// 判断是否是管理员
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					list = projectService.findProjectReportList(project);
				} else if (roleNo == 5) {// 销售
					Integer emailUserId = Integer.parseInt(request
							.getParameter("userId"));// 拿到用户的userId
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					project.setEmailUserId(emailUserId);
					list = projectService.findProjectReportList(project);
				} else if (roleNo == 6) {// 采购
					Integer purchaseId = Integer.parseInt(request
							.getParameter("userId"));// 拿到用户的userId
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					project.setPurchaseId(purchaseId);
					list = projectService
							.findProjectReportPurchaseList(project);
				}
			} else if (value.equals("3")) {// 统计未设定实际交期项目
				if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// 判断是否是管理员
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					list = projectService.findSetDeliveryTimeList(project);
				} else if (roleNo == 5) {// 销售
					Integer emailUserId = Integer.parseInt(request
							.getParameter("userId"));// 拿到用户的userId
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					project.setEmailUserId(emailUserId);
					list = projectService.findSetDeliveryTimeList(project);
				} else if (roleNo == 6) {// 采购
					Integer purchaseId = Integer.parseInt(request
							.getParameter("userId"));// 拿到用户的userId
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					project.setPurchaseId(purchaseId);
					list = projectService
							.findSetDeliveryTimePurchaseList(project);
				}
			} else if (value.equals("4")) {// 统计出货延期项目
				if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// 判断是否是管理员
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					list = projectService.findProjectDelayCountList(project);
				} else if (roleNo == 5) {// 销售
					Integer emailUserId = Integer.parseInt(request
							.getParameter("userId"));// 拿到用户的userId
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					project.setEmailUserId(emailUserId);
					list = projectService.findProjectDelayCountList(project);
				} else if (roleNo == 6) {// 采购
					Integer purchaseId = Integer.parseInt(request
							.getParameter("userId"));// 拿到用户的userId
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					project.setPurchaseId(purchaseId);
					list = projectService
							.findProjectDelayPurchaseCountList(project);
				}
			} else if (value.equals("5")) {// 统计缺少开工图片项目
				if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// 判断是否是管理员
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					list = projectService.findProjectReportNoPicList(project);
				} else if (roleNo == 5) {// 销售
					Integer emailUserId = Integer.parseInt(request
							.getParameter("userId"));// 拿到用户的userId
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					project.setEmailUserId(emailUserId);
					list = projectService.findProjectReportNoPicList(project);
				} else if (roleNo == 6) {// 采购
					Integer purchaseId = Integer.parseInt(request
							.getParameter("userId"));// 拿到用户的userId
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					project.setPurchaseId(purchaseId);
					list = projectService
							.findProjectReportNoPicPurchaseList(project);
				}
			} else {// 统计缺少任务汇报的项目
				if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// 判断是否是管理员
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					List<Project> taskReportList = projectService
							.findProjectNoTaskReportList(project); // 有任务汇报的项目
					List<Project> allList = projectService
							.findProjectList(project); // 得到所有的项目
					list = getDiffrent(allList, taskReportList); // 对比得到没有任务汇报的项目
				} else if (roleNo == 5) {// 销售
					Integer emailUserId = Integer.parseInt(request
							.getParameter("userId"));// 拿到用户的userId
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					project.setEmailUserId(emailUserId);
					List<Project> taskReportList = projectService
							.findProjectNoTaskReportList(project); // 有任务汇报的项目
					List<Project> allList = projectService
							.findProjectList(project); // 得到该采购所有的项目
					if (taskReportList.size() > 0) {
						list = getDiffrent(allList, taskReportList); // 对比得到没有任务汇报的项目
					} else {
						list = allList;
					}
				} else if (roleNo == 6) {// 采购
					Integer purchaseId = Integer.parseInt(request
							.getParameter("userId"));// 拿到用户的userId
					project.setStartDate(startDate);
					project.setEndDate(endDate);
					project.setPurchaseId(purchaseId);
					List<Project> taskReportList = projectService
							.findProjectNoTaskReportPurchaseList(project); // 有任务汇报的项目
					List<Project> allList = projectService
							.findPurchaseProjectList(project); // 得到该采购所有的项目
					if (taskReportList.size() > 0) {
						list = getDiffrent(allList, taskReportList); // 对比得到没有任务汇报的项目
					} else {
						list = allList;
					}
				}
			}
			jsonResult.setOk(true);
			jsonResult.setData(list);
			jsonResult.setComment(String.valueOf(roleNo));
		} catch (NumberFormatException e) {
			jsonResult.setOk(false);
			jsonResult.setData(e.getMessage());
			e.printStackTrace();
			LOG.error("error", e);
		}
		return jsonResult;
	}

	/**
	 * 对比两个list集合 重新得到一个新的集合
	 * 
	 * @param allProjectList
	 * @param taskReporProjecttList
	 * @return
	 */
	private static List<Project> getDiffrent(List<Project> allProjectList,
			List<Project> taskReporProjecttList) {
		List<Project> list = new ArrayList<Project>();
		for (int i = 0; i < allProjectList.size(); i++) {
			Project allProject = allProjectList.get(i);
			Project project = null;
			boolean bool = false;
			for (int j = 0; j < taskReporProjecttList.size(); j++) {
				project = taskReporProjecttList.get(j);
				// 项目号匹配成功不做任何操作
				if (allProject.getProjectNo().equals(project.getProjectNo())) {
					bool = true;
					break;
				}
			}
			// 项目号匹配不成功,将不匹配的项目号添加到项目表里面
			if (!bool) {
				list.add(allProject);
			}
		}
		return list;
	}

	/**
	 * 文件下载
	 * 
	 * @param request
	 * @param response
	 * @param fileUrl
	 * @throws Exception
	 */
	@RequestMapping(value = "/download")
	public void download(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("fileUrl") String fileUrl) throws Exception {
		String filename = "/usr/local/apache-tomcat-7.0.72/webapps/uploadimg/"
				+ fileUrl;
		// 设置文件MIME类型
		response.setContentType(request.getServletContext().getMimeType(
				filename));
		// 设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename="
				+ filename);
		// 读取目标文件，通过response将目标文件写到客户端
		// 获取目标文件的绝对路径
		// 读取文件
		InputStream in = new FileInputStream(fileUrl);
		OutputStream out = response.getOutputStream();
		// 写文件
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		in.close();
		out.close();
	}

	@RequestMapping("/projectComment")
	@ResponseBody
	public JsonResult projectComment(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
	 try {
		String userName = request.getParameter("userName");
		if(StringUtils.isBlank(userName)){
			userName = WebCookie.getUserName(request);
			if(userName == null){
				jsonResult.setOk(false);
				jsonResult.setMessage("请登录");
				return jsonResult;
//				userName = getIpuser(request);			
			}
		}
		String projectNo = request.getParameter("projectNo");
		String comment = request.getParameter("comment");
		String reportId = request.getParameter("reportId");
		String shippingId = request.getParameter("shippingId");
		String complaintId = request.getParameter("complaintId");
		String fileName = request.getParameter("fileName");
		String newFileName = request.getParameter("newFileName");
		Comment insertComment = new Comment();
		insertComment.setId(IdGen.uuid());
		insertComment.setReviewer(userName);
		insertComment.setProjectNo(projectNo);
		insertComment.setComment(comment);
		insertComment.setFileName(fileName);
		insertComment.setNewFileName(newFileName);
		insertComment.setCreateDate(new Date());
		
		//电子出货单id
		if(StringUtils.isNotBlank(shippingId)){
		    insertComment.setShippingId(Integer.parseInt(shippingId));
		}
		//质检报告
		if(StringUtils.isNotBlank(reportId)){
			insertComment.setQualityReportId(Integer.parseInt(reportId));
		}
		//投诉技术回复
		if(StringUtils.isNotBlank(complaintId)){
			insertComment.setComplaintId(Integer.parseInt(complaintId));
			ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(complaintId));
			User user =userService.findUserByName(userName);
			if(projectComplaint.getSeriousLevel()==5&&(user.getRoleNo()==5 ||user.getRoleNo()==6||user.getRoleNo()==69||user.getRoleNo()==1020)){
				projectComplaint.setCompleteTime(new Date());
				projectComplaintService.updateByPrimaryKeySelective(projectComplaint);
			}
		 }
		    projectCommentService.addProjetcComment(insertComment);
			Map<String,String> map = new HashMap<String, String>();
			map.put("userName",userName);
			map.put("createDate",DateFormat.format());
			map.put("id",insertComment.getId());
			jsonResult.setOk(true);
			jsonResult.setMessage("留言成功");
			jsonResult.setData(map);
			RpcHelper.sendRequest("", insertComment);
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage("留言失败");
			e.printStackTrace();
			LOG.error("error", e);
		}
		return jsonResult;
	}
	
	/**
	 * 根据id删除评论
	 * @Title delComment 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */
	@RequestMapping("/delComment")
	@ResponseBody
	public JsonResult delComment(HttpServletRequest request,HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
	    if(StringUtils.isNotBlank(request.getParameter("id"))){
	    	projectCommentService.deleteByPrimaryKey(request.getParameter("id"));
	    	jsonResult.setOk(true);
			jsonResult.setMessage("删除成功");
	    }else{
	    	jsonResult.setOk(false);
			jsonResult.setMessage("删除失败");
	    }
	    return jsonResult;
	}
	/**
	 * 暂停启动项目
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/pauseProject")
	@ResponseBody
	public JsonResult pauseProject(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		String projectNo = request.getParameter("projectNo");
		String checked = request.getParameter("checked");
		String pauseReason = request.getParameter("pauseReason");
		Project project = new Project();
		project.setProjectNo(projectNo);
		project.setIsPause(checked);
		project.setPauseReason(pauseReason);
		if(StringUtils.isNotBlank(checked)){
			if("1".equals(checked)){
			    project.setFinish(0);
			    project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
			}else if("5".equals(checked)){
				project.setFinish(0);
			    project.setProjectStatus(OrderStatusEnum.CANCEL_ORDER.getCode());
			}
		}
		try {
			projectService.updateProjectInfo(project);
			//更新状态字段
			//ProjectDateTask projectDateTask=new ProjectDateTask();
//			List<Project> projectList=new ArrayList<Project>();
//			projectList.add(project);
//			projectDateTask.syncProjectDate(projectList);
			jsonResult.setOk(true);
			jsonResult.setMessage("操作成功");
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage("操作失败");
			e.printStackTrace();
			LOG.error("error", e);
		}
		return jsonResult;
	}

	
	
	/**
	 * 用于更新项目样品完结、大货完结、暂停启动项目
	 * 
	 * Date:2018.08.22
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateProjectNew")
	@ResponseBody
	public JsonResult updateProjectNew(HttpServletRequest request,HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		String projectNo = request.getParameter("projectNo");
		String projectStatus = request.getParameter("projectStatus");
		String reason = request.getParameter("reason");
		String time = request.getParameter("time");
		String userName = WebCookie.getUserName(request);
		 if(StringUtils.isBlank(userName)){
			 jsonResult.setOk(false);
			 jsonResult.setMessage("您还未登录");
			 return jsonResult;
		 }
		Project project = projectService.selectProjctDetails(projectNo);
		//项目状态改变日志表
		ProjectStatusLog statusLog = new ProjectStatusLog();
		try {
		if(StringUtils.isNotBlank(projectStatus)){
			Integer status = Integer.parseInt(projectStatus);
			//样品完结
			if(status == OrderStatusEnum.SAMPLE_ORDER.getCode()){
			    project.setSampleFinish(1);
			    if(project.getDeliveryDate() != null){
				    project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
				    statusLog.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getValue());
			    }else{
			    	 project.setProjectStatus(OrderStatusEnum.SAMPLE_ORDER.getCode());
			    	 statusLog.setProjectStatus(OrderStatusEnum.SAMPLE_ORDER.getValue());
			    }
			    project.setSampleFinish(1);
				project.setSampleFinishTime(DateUtil.StrToDate(time));
				project.setPauseReason(reason);
				
				//状态改变日志
				statusLog.setProjectNo(projectNo);
				statusLog.setOperator(userName);				
				statusLog.setUpdateDate(new Date());
				
			}else if(status == OrderStatusEnum.COMPLETE_ORDER.getCode()){
				//大货完结
			    project.setFinish(1);
			    project.setProjectStatus(OrderStatusEnum.COMPLETE_ORDER.getCode());
				project.setFinishTime(DateUtil.StrToDate(time));
		
				//状态改变日志
				statusLog.setProjectNo(projectNo);
				statusLog.setOperator(userName);
				statusLog.setProjectStatus(OrderStatusEnum.COMPLETE_ORDER.getValue());
				statusLog.setUpdateDate(new Date());			
			}else if(status == OrderStatusEnum.NORMAL_ORDER.getCode() && project.getProjectStatus() == OrderStatusEnum.PAUSE_ORDER.getCode()){
			    //暂停项目启动
				project.setFinish(0);
			    project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
				project.setPauseReason(reason);
				
				//如果是暂停项目启动查询最新暂停的记录更新
				ProjectPause projectPause = projectPauseService.selectLastPause(projectNo);
				if(projectPause != null){
					projectPause.setStartDate(DateUtil.StrToDate(time));
					projectPause.setStartReason(reason);
					projectPause.setIsPause(0);
					projectPauseService.updateByPrimaryKeySelective(projectPause);
				}
				
				
				//状态改变日志
				statusLog.setProjectNo(projectNo);
				statusLog.setOperator(userName);
				statusLog.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getValue());
				statusLog.setUpdateDate(new Date());
				
			}else if(status == OrderStatusEnum.NORMAL_ORDER.getCode() && project.getProjectStatus() != OrderStatusEnum.PAUSE_ORDER.getCode()){
			    //项目其他启动
				project.setFinish(0);
			    project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
				project.setPauseReason(reason);
				
				
				//状态改变日志
				statusLog.setProjectNo(projectNo);
				statusLog.setOperator(userName);
				statusLog.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getValue());
				statusLog.setUpdateDate(new Date());
				
			}else if(status == OrderStatusEnum.CANCEL_ORDER.getCode()){
				//项目取消
				project.setFinish(0);
			    project.setProjectStatus(OrderStatusEnum.CANCEL_ORDER.getCode());
				project.setPauseReason(reason);
				project.setFinishTime(DateUtil.StrToDate(time));
				
				
				//状态改变日志
				statusLog.setProjectNo(projectNo);
				statusLog.setOperator(userName);
				statusLog.setProjectStatus(OrderStatusEnum.CANCEL_ORDER.getValue());
				statusLog.setUpdateDate(new Date());
				
			}else if(status == OrderStatusEnum.PAUSE_ORDER.getCode()){
				//项目暂停
				int count = projectPauseService.count(projectNo);
				if(count > 6){
					jsonResult.setOk(false);
					jsonResult.setMessage("项目最多可暂停7次");
					return jsonResult;
				}
				project.setProjectStatus(OrderStatusEnum.PAUSE_ORDER.getCode());
				
				
				//状态改变日志
				statusLog.setProjectNo(projectNo);
				statusLog.setOperator(userName);
				statusLog.setProjectStatus(OrderStatusEnum.PAUSE_ORDER.getValue());
				statusLog.setUpdateDate(new Date());
			}
		}
		
		    //项目成员给出预计出货日期
			if(StringUtils.isNotBlank(request.getParameter("newPredictDate"))){
				project.setNewPredictDate(DateUtil.StrToDate(request.getParameter("newPredictDate")));
			}else if("".equals(request.getParameter("newPredictDate"))){
				project.setNewPredictDate(null);
			}
			//项目成员给出紧急出货日期
			if(StringUtils.isNotBlank(request.getParameter("urgentDeliveryDate"))){
				project.setUrgentDeliveryDate(DateUtil.StrToDate(request.getParameter("urgentDeliveryDate")));				
			}else if("".equals(request.getParameter("urgentDeliveryDate"))){
				project.setUrgentDeliveryDate(null);
			}
		
		
			//姜工给出项目预计生产时间
			if(StringUtils.isNotBlank(request.getParameter("newDeliveryDate"))){
				String newDeliveryDate = request.getParameter("newDeliveryDate");
//				project.setScheduledDays(days);
				  //如果存在po日期，生产天数为 交期-po日期
				  //如果不存在po日期，生产天数为交期-合同日期+7天
				  Date poDate = project.getDateSampleUploading();
				  Date actualStartDate = project.getActualStartDate();
				  //如果存在po日期，生产天数为 交期-po日期
				  //如果不存在po日期，生产天数为交期-合同日期+7天
				  int days = 0;
				  if(poDate != null){
					  days = DateFormat.calDays(DateUtil.StrToDate(newDeliveryDate), poDate);
				  }else{
					  days = DateFormat.calDays(DateUtil.StrToDate(newDeliveryDate), actualStartDate);
					  days = days + 7;
				  }
				  //查询项目的大货交期时间。如果大货交期为空，紧急出货时间为样品出货时间
//				  String newDeliveryDate = null;
//				  if(poDate != null){
//					  newDeliveryDate = DateFormat.addDays(DateFormat.date2String(poDate), days);
//				  }else{
//					  if(actualStartDate != null){
//						  newDeliveryDate = DateFormat.addDays(DateFormat.date2String(actualStartDate), (days-7));
//					  }
//				  }
	  
				  //交期修改表
				  DeliveryDateLog deliveryDateLog = new DeliveryDateLog();
					  deliveryDateLog.setCreatePerson(userName);
					  deliveryDateLog.setCreateTime(new Date());
					  deliveryDateLog.setProjectNo(projectNo);	
					  deliveryDateLog.setScheduledDays(days);
				  if(project.getDeliveryDate() != null){					 
                         //大货交期				
						 deliveryDateLog.setNewDeliveryDate(DateUtil.StrToDate(newDeliveryDate));
						 deliveryDateLog.setOriginalDate(project.getDeliveryDate());
						 deliveryDateLog.setSampleProduction(PRODUCT);
						 project.setDeliveryDate(DateUtil.StrToDate(newDeliveryDate));
				  }else if(project.getDeliveryDate() == null && project.getSampleScheduledDate() != null){
					     //样品交期				
						 deliveryDateLog.setNewDeliveryDate(DateUtil.StrToDate(newDeliveryDate));
						 deliveryDateLog.setOriginalDate(project.getSampleScheduledDate());
						 deliveryDateLog.setSampleProduction(SAMPLE);
						 project.setSampleScheduledDate(DateUtil.StrToDate(newDeliveryDate));
				  }			  
				  projectService.updateByPrimaryKey(project, deliveryDateLog);
			}else{
				projectService.updateProjectStatus(project,reason,time,statusLog);
			}
	
			//更新状态字段
			jsonResult.setOk(true);
			jsonResult.setMessage("操作成功");
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage(e.getMessage());
			e.printStackTrace();
			LOG.error("error", e);
		}
		return jsonResult;
	}
	
	
	
	
	
	
	
	
	
	/*
	 * 查询所有质检、采购、跟单人员
	 */
	@RequestMapping("/queryStaff")
	@ResponseBody
	public JsonResult queryStaff(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();

		try {

			Map<String, List<User>> map = new HashMap<String, List<User>>();

			// 采购
			List<User> purchaseList = userService.selectUserByType(6);
			
			// 跟单&销售
			List<User> saleList = userService.selectUserByType(5);
			Iterator<User> it = saleList.iterator();
			while(it.hasNext()){
			    User userFlag = it.next();
			    if(!"跟单".equals(userFlag.getJob()) && !"所有角色".equals(userFlag.getJob())){
			        it.remove();
			    }
			}
			// 质检
			List<User> qualityTestList = userService.selectUserByType(9);
			

			map.put("purchase", purchaseList);
			map.put("sale", saleList);
			map.put("quality", qualityTestList);

			jsonResult.setData(map);
			jsonResult.setOk(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMessage(e.getMessage());
			jsonResult.setOk(false);
			LOG.error("error", e);

		}

		return jsonResult;
	}

	/**
	 * 查询项目详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryListPc")
	public String queryListPc(HttpServletRequest request,
			HttpServletResponse response) {

		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
        String pageNumberStr=request.getParameter("pageNumber");
        int pageNumber=1;
        if (StringUtils.isNotBlank(pageNumberStr)) {
			pageNumber = Integer.parseInt(pageNumberStr);// 第几页,1,2
		}
        String pcType="1";
		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.setAttribute("pageNumber",pageNumber);
		request.setAttribute("pcType", pcType);
		return "project_list_pc";
	}

	/*
	 * 
	 * 修改项目金额
	 */
	/*
	 * 查询所有质检、采购、跟单人员
	 */
	@RequestMapping("/editAmout")
	@ResponseBody
	public JsonResult editAmout(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();

		try {
			String projectNo = request.getParameter("projectNo");
			String projectAmout = request.getParameter("projectAmout");
			Project project = new Project();
			project.setProjectNo(projectNo);
			project.setProjectAmount(projectAmout);
			projectService.updateProjectInfo(project);
			jsonResult.setOk(true);
			jsonResult.setMessage("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMessage(e.getMessage());
			jsonResult.setOk(false);
			LOG.error("error", e);
		}

		return jsonResult;
	}
	
	/**
	 * 添加产品图
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadProductFile",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addFactoryFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 String productImgName = request.getParameter("productImg");
		 String projectImgNo=request.getParameter("projectImgNo");
		 String path = UploadAndDownloadPathUtil.getProductImg();
		 File file = new File(path);
		 if  (!file.exists()  && !file .isDirectory())       {         
				file .mkdir();     
		 }  	
		 //调用保存文件的帮助类进行保存文件(文件上传，form表单提交)
		try {
			Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload(request, path+File.separator);
			String fileName = multiFileUpload.get(productImgName);
			Project project=new Project();
			project.setProjectNo(projectImgNo);
			project.setProductImg(fileName);
			projectService.updateProjectInfo(project);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * 姜工同意延期
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/agreeDelayApply")
	@ResponseBody
	public JsonResult agreeDelayApply(HttpServletRequest request,HttpServletResponse response) throws Exception{
		   JsonResult jsonResult = new JsonResult();
		try {
			String projectNo = request.getParameter("projectNo");
			String type=request.getParameter("type");
			String agreePerson = request.getParameter("userName");
			Project project=projectService.selectProjctDetails(projectNo);
//			Delay delay=delayService.selectApplyDelayByProjectNo(projectNo);
//			if(type.equals("1")){//同意延期
//				//1.判断延期哪个交期
//				if(project.getDeliveryDate()!=null && project.getSampleScheduledDate()==null){
//					project.setDeliveryDate(delay.getDelayDate());
//				}
//				if(project.getSampleScheduledDate()!=null && project.getDeliveryDate()==null){
//					project.setSampleScheduledDate(delay.getDelayDate());
//				}
//				if(project.getSampleScheduledDate()!=null && project.getDeliveryDate()!=null){
//					if(project.getSampleScheduledDate().getTime()>project.getDeliveryDate().getTime()){
//						project.setSampleScheduledDate(delay.getDelayDate());
//					}else{
//						project.setDeliveryDate(delay.getDelayDate());
//					}
//				}
//				project.setProjectNo(projectNo);		
//				projectService.updateProjectInfo(project);
//			}else if(type.equals("2")){//不同意延期
//				
//			}
			
//			delay.setIsAgree(1);
			List<Delay> delayList = delayService.selectDelayByProjectNo(projectNo);
			
				for (Delay delay : delayList) {
					if("1".equals(type)){//同意延期
					    delay.setIsAgree(1);
					    delay.setAgreePerson(agreePerson);
					    delay.setAgreeTime(new Date());
					}else if("2".equals(type)){
						delay.setIsAgree(2);
					    delay.setAgreePerson(agreePerson);
					    delay.setAgreeTime(new Date());
				    }  
			     }
			
			delayService.updateBatch(delayList);
			List<Project> projectList=new ArrayList<Project>();
			projectList.add(project);
			projectDateTask.syncProjectDate(projectList);
			jsonResult.setOk(true);
			jsonResult.setMessage("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMessage(e.getMessage());
			jsonResult.setOk(false);
			LOG.error("error", e);
		}
		return jsonResult;
	}
	
	
	/**
	 * 添加技术指导
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addProjectTechnology")
	@ResponseBody
	public JsonResult addProjectTechnology(HttpServletRequest request,HttpServletResponse response) throws Exception{
		   JsonResult jsonResult = new JsonResult();
		try {
			String projectNo = request.getParameter("projectNo");		
			String technology=request.getParameter("technology");
			Project project=new Project();
			project.setProjectNo(projectNo);
			project.setTechnology(technology);
			projectService.updateProjectInfo(project);
			jsonResult.setOk(true);
			jsonResult.setMessage("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMessage(e.getMessage());
			jsonResult.setOk(false);
			LOG.error("error", e);
		}
		return jsonResult;
	}
	
	
	/**
	 * 根据id查询新项目
	 * @Title queryNewProject 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */
	@RequestMapping(value="/queryNewProject")
	public String queryNewProject(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		String roleNo = request.getParameter("roleNo");
		String userName = request.getParameter("userName");
		Integer page = 1;
		Integer pageSize = 20;		
		if(StringUtils.isNotBlank(request.getParameter("page"))){
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(StringUtils.isNotBlank(request.getParameter("pageSize"))){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}

		Integer pageNumber = (page-1)*pageSize;		
		if(StringUtils.isNotBlank(userId)){
			List<Project> projectList = projectService.selectByCreatePersonId(Integer.parseInt(userId),Integer.parseInt(roleNo),pageNumber,pageSize);
			int count = projectService.countNewProject(Integer.parseInt(userId),Integer.parseInt(roleNo));
			List<User> userList = userService.selectAllUser();
			request.setAttribute("roleNo", roleNo);
			request.setAttribute("userName", userName);
			request.setAttribute("userId", userId);
			request.setAttribute("projectList", projectList);
			request.setAttribute("userList", userList);
			request.setAttribute("count", count);
			request.setAttribute("countPage", page);
			request.setAttribute("pageSize", pageSize);
			Integer totalPage = new BigDecimal(count).divide(new BigDecimal(pageSize)).setScale(0, BigDecimal.ROUND_UP).intValue();
			request.setAttribute("totalPage", totalPage);
		}
		
		return "new_project_entry";
	}
	
	
	
	/**
	 * 跟单录入项目（新项目录入）
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addProject")
	@ResponseBody
	public JsonResult addProject(HttpServletRequest request,HttpServletResponse response){
		 JsonResult jsonResult = new JsonResult();
		 try {
			 String projectNo = request.getParameter("projectNo");	
			 Project project = projectService.selectProjctDetails(projectNo);
			 if(project != null){
				 jsonResult.setOk(false);
				 jsonResult.setMessage("该项目已存在");
				 return jsonResult;
			 }
			 String userName = request.getParameter("userName");
			 String amount = request.getParameter("amount");
			 String startDate = request.getParameter("startDate");
			 String purchaseName = request.getParameter("purchaseName");
			 String userId = request.getParameter("userId");		 
			 String sellId = request.getParameter("sellId");
			 String zhijian = request.getParameter("zhijian");
			 String require = request.getParameter("require");
			 String updateInspect = request.getParameter("updateInspect");
			 String updateDrawing = request.getParameter("updateDrawing");
			 String plantAnalysis = request.getParameter("plantAnalysis");
			 String predictDate = request.getParameter("predictDate");
			 User user = userService.selectUserByName(purchaseName);
			 
			 project = new Project();
			 project.setProjectNo(projectNo);
			 project.setId(IdGen.uuid());
			 project.setCreateDate(new Date());
			 project.setProjectAmount(amount);
			 project.setDateSampleUploading(java.sql.Date.valueOf(startDate));
			 project.setEmailUserId(Integer.parseInt(sellId));
			 project.setZhijian1(zhijian);
			 project.setUserName(userName);
			 project.setPurchaseName(purchaseName);
			 
			 //预计交货期
			 if(StringUtils.isNotBlank(predictDate)){
				 project.setNewPredictDate(DateUtil.StrToDate(predictDate)); 
			 }

			 //项目等级
			 if(StringUtils.isNotBlank(plantAnalysis)){
				 project.setPlantAnalysis(Integer.parseInt(plantAnalysis));
			 }
			 
			 //需要采购确认需求变更
			 if(StringUtils.isNotBlank(require)){
				 project.setRequire(Integer.parseInt(require));
			 }
			 //需要检验更新检验计划 
			 if(StringUtils.isNotBlank(updateInspect)){
				 project.setUpdateInspect(Integer.parseInt(updateInspect));
			 }
			 //需要更新图纸
			 if(StringUtils.isNotBlank(updateDrawing)){
				 project.setUpdateDrawing(Integer.parseInt(updateDrawing));
			 }
			 
			 if(user != null){
				 project.setPurchaseId(user.getId());
			 }
			 project.setCreatePersonId(Integer.parseInt(userId));
			 project.setProjectStatus(OrderStatusEnum.NEW_ORDER.getCode());
			 projectService.addProject(project);
			 
			 RpcSynPurchase rpcSyn = new RpcSynPurchase();
			 rpcSyn.sendRequest(null, projectNo, purchaseName);
			 
			 jsonResult.setOk(true);
			 jsonResult.setMessage("操作成功");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			jsonResult.setMessage(e.getMessage());
			jsonResult.setOk(false);
			LOG.error("error", e);
		} catch (ParseException e) {
			e.printStackTrace();
			jsonResult.setMessage(e.getMessage());
			jsonResult.setOk(false);
			LOG.error("error", e);
		}
		 
		 return jsonResult;
	}
	
	
	/**
	 * 查询项目组成员mail
	 * @Title queryMail 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */
	@RequestMapping(value="/queryMail")
	@ResponseBody
	public JsonResult queryMail(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
		Map<String, Object> map = new HashMap<String, Object>();
		String projectNo = request.getParameter("projectNo");
		Project project = projectService.selectProjctDetails(projectNo);
		if(StringUtils.isNotBlank(project.getSellName())){
		    User user = userService.selectUserByName(project.getSellName());
		    map.put("sellEmail", user.getEmailAddress());
		}
        
		if(StringUtils.isNotBlank(project.getPurchaseName())){
		    User purchaseUser = userService.selectUserByName(project.getPurchaseName()); 
		    map.put("purchaseEmail", purchaseUser.getEmailAddress());
		}
		
		//项目等级为A、B级需要抄送老板
		if(project.getPlantAnalysis() != null && (project.getPlantAnalysis() == 1 || project.getPlantAnalysis() == 2)){
			map.put("boss", "edwardfan@sourcing-cn.com");
		}
		
		jsonResult.setData(map);
		jsonResult.setOk(true);
		return jsonResult;
	}
	
	
	
	/**
	 * 删除新项目
	 * @Title deleteProject 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */	
	@RequestMapping(value="/delete")
	@ResponseBody
	public JsonResult deleteProject(HttpServletRequest request,HttpServletResponse response){
		 JsonResult jsonResult = new JsonResult();
		 try {
			 String projectNo = request.getParameter("projectNo");	
		     String id = request.getParameter("id");	
		     if(StringUtils.isNotBlank(id)){
		    	 projectService.deleteByPrimaryKey(id, projectNo);
		    	 jsonResult.setOk(true);
				 jsonResult.setMessage("删除成功");
		     }		     
		     
		 } catch (Exception e) {
				e.printStackTrace();
				jsonResult.setMessage(e.getMessage());
				jsonResult.setOk(false);
				LOG.error("error", e);
		}
		 return jsonResult;
	}
	
	
	
	
	
	//导出报告
	@RequestMapping(value="/exportDelayProject")
	public void exportDelayProject(HttpServletRequest request,HttpServletResponse response){
		
        try {
        	 Calendar cal= Calendar.getInstance();  
        	 int year = cal.get(Calendar.YEAR);
        	 int month = cal.get(Calendar.MONTH)+1;
        	 int month1=cal.get(Calendar.MONTH);
        	 int year1=0;
             cal.add(cal.MONTH,-1);
             int maxCurrentMonthDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
              //获取15号日期
             String d = year + "-" + month + "-01";
             //获取上个月15号日期
            Date endDate = DateUtil.StrToDate(d);
            if (month1==12){
            	year1=year-1;
            }else{
            	year1=year;
            }
            String d1 = year1 + "-" + month1 + "-01";
            Date startDate = DateUtil.StrToDate(d1);
			List<Project> sampleFinishes = projectService.selectProjectByStatus1(OrderStatusEnum.SAMPLE_ORDER.getCode(),startDate,endDate);
			List<Project> productFinishs = projectService.selectProjectByStatus1(OrderStatusEnum.COMPLETE_ORDER.getCode(),startDate,endDate);
			List<Project> normals = projectService.selectProjectByStatus1(OrderStatusEnum.NORMAL_ORDER.getCode(),startDate,endDate);
			List<Project> suspension = projectService.selectProjectByStatus1(OrderStatusEnum.PAUSE_ORDER.getCode(),startDate,endDate);
			List<Project> cancellation = projectService.selectProjectByStatus1(OrderStatusEnum.CANCEL_ORDER.getCode(),startDate,endDate);

			String excelPath = ProjectStatisticsPrint.printExcel(request, sampleFinishes, productFinishs, normals,suspension,cancellation);
			File outFile = new File(excelPath);  
			InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));  
			byte[] buffer = new byte[fis.available()];  
			fis.read(buffer);  
			fis.close();  
			// 清空response  
			response.reset();  
			// 设置response的Header  
			Date startDate1 = DateFormat.addMonth(new Date(), -2);
			String fileName = "延期项目"+DateFormat.date2String(startDate)+"~"+DateFormat.date2String(endDate)+".xls";
			fileName = URLEncoder.encode(fileName, "utf-8");                                  //这里要用URLEncoder转下才能正确显示中文名称  
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);  
			response.addHeader("Content-Length", "" + outFile.length());  
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
			response.setContentType("application/octet-stream");  
			toClient.write(buffer);  
			toClient.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	
	
	    /**
	     * 导出生产中项目
	     * @Title exportProject 
	     * @Description 
	     * @param request
	     * @param response
	     * @return void
	     */
		@RequestMapping(value="/exportProject")
		public void exportProject(HttpServletRequest request,HttpServletResponse response){
			try {
				Project project = new Project();
				List<Integer> list = new ArrayList<Integer>();
				list.add(OrderStatusEnum.NORMAL_ORDER.getCode());
				list.add(OrderStatusEnum.SAMPLE_ORDER.getCode());
				project.setProjectStatusS(list);
				project.setUserName(WebCookie.getUserName(request));
				project.setRoleNo(WebCookie.getRoleNo(request));
                List<Project> projectList = projectService.findProjectByStatus(project);
				String excelPath = ProjectPrint.printExcel(request, projectList);
				File outFile = new File(excelPath);  
				InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));  
				byte[] buffer = new byte[fis.available()];  
				fis.read(buffer);  
				fis.close();  
				// 清空response  
				response.reset();  
				// 设置response的Header  
				Date startDate = DateFormat.addMonth(new Date(), -2);
				String fileName = "项目时间管理"+DateFormat.date2String(startDate)+"~"+DateFormat.currentDate()+".xls";
				fileName = URLEncoder.encode(fileName, "utf-8");                                  //这里要用URLEncoder转下才能正确显示中文名称  
				response.addHeader("Content-Disposition", "attachment;filename=" + fileName);  
				response.addHeader("Content-Length", "" + outFile.length());  
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
				response.setContentType("application/octet-stream");  
				toClient.write(buffer);  
				toClient.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}	
	
	
	/**
	 * 查询项目详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryDetail")
	public String queryDetail(HttpServletRequest request,HttpServletResponse response) {
		String projectNo = request.getParameter("projectNo");
		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
	    String userName = WebCookie.getUserName(request);  //用户
	    
	    if(StringUtils.isNotBlank(userName)){
        }else{
        	return "redirect:/index.jsp";
        }          
		
		try {
		// 1.查询项目详细信息
		Project project = projectService.selectProjctDetails(projectNo);		
		//大货交期
		List<ProjectSchedule> projectSchedules = projectScheduleService.selectByProjectNo(projectNo);
		//工厂合同信息
		List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectNo);
		
		request.setAttribute("project", project);
		if(roleNo!=null&&!"".equalsIgnoreCase(roleNo)){
			request.setAttribute("roleNo", roleNo);
		}else{
			int roleNo1 = WebCookie.getRoleNo(request);  //用户
			request.setAttribute("roleNo", roleNo1);	
		}
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.setAttribute("projectSchedules", projectSchedules);
		request.setAttribute("factoryList", factoryList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit_project";
	}

	
	
	
	
	
	
	
	
	
	/**
	 * 更新项目情况（大货交期，样品交期，项目状态，大货完成时间，样品完成时间修改（仅nina使用））
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/editProject")
	public JsonResult editProject(HttpServletRequest request,HttpServletResponse response){
		 JsonResult jsonResult = new JsonResult();
	     try {
			 String projectNo = request.getParameter("projectNo");
			 String deliveryDate = request.getParameter("deliveryDate");
			 String sampleScheduledDate = request.getParameter("sampleScheduledDate");
			 String projectStatus = request.getParameter("projectStatus");
			 String finishTime = request.getParameter("finishTime");
			 String sampleFinishTime = request.getParameter("sampleFinishTime");			 
			 String projectAmount = request.getParameter("projectAmount");
			 
			 String userName = WebCookie.getUserName(request);  //用户
			 
			 if(StringUtils.isBlank(projectNo)){
				 jsonResult.setOk(false);
				 jsonResult.setMessage("未获取到项目号");
				 return jsonResult;
			 }
			 //项目
			 Project project = projectService.selectProjctDetails(projectNo);			 
			 if(StringUtils.isNotBlank(projectStatus)){
				 project.setProjectStatus(Integer.parseInt(projectStatus));
			 }
			 //样品交期
			 if(StringUtils.isNotBlank(sampleScheduledDate)){				
				 project.setSampleScheduledDate(DateUtil.StrToDate(sampleScheduledDate));
				 project.setOriginalSampleScheduledDate(DateUtil.StrToDate(sampleScheduledDate));
			 }else{
				 project.setSampleScheduledDate(null);
				 project.setOriginalSampleScheduledDate(null);
			 }
			 //样品完成时间
			 if(StringUtils.isNotBlank(sampleFinishTime)){				
				 project.setSampleFinish(1);
				 project.setSampleFinishTime(DateUtil.StrToDate(sampleFinishTime));				 
			 }else{
				 project.setSampleFinish(0);
				 project.setSampleFinishTime(null);
			 }
			 //项目交期
			 if(StringUtils.isNotBlank(deliveryDate)){
				 project.setDeliveryDate(DateUtil.StrToDate(deliveryDate));		
				 project.setOriginalDeliveryDate(DateUtil.StrToDate(deliveryDate));
			 }else{
				 project.setDeliveryDate(null);		
				 project.setOriginalDeliveryDate(null);
			 }			 
			 //项目完成时间
			 if(StringUtils.isNotBlank(finishTime)){
				 project.setFinish(1);
				 project.setFinishTime(DateUtil.StrToDate(finishTime));
			 }else{
				 project.setFinish(0);
				 project.setFinishTime(null);
			 }
			 if(StringUtils.isNotBlank(projectAmount)){
				 project.setProjectAmount(projectAmount);
			 }
		
			 projectService.updateByPrimaryKey(project);			 
			 
			 
			//批量更新工厂生产状态
			String factoryList = request.getParameter("factoryList");
			if(StringUtils.isNotBlank(factoryList)){
				List<ProjectFactory> factorys = null;
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ProjectFactory.class);
				factorys = objectMapper.readValue(factoryList,javaType);
				//当返修补货完结时，对应样品合同或者大货合同也需要有完结时间
				for (ProjectFactory projectFactory : factorys) {
					if(StringUtils.isNotBlank(projectFactory.getRepairReplenishmentFinishTime()) && projectFactory.getOrderNature() == 2 && projectFactory.getSampleDate() != null){
						projectFactory.setSampleFinishTime(projectFactory.getRepairReplenishmentFinishTime());
					}
					if(StringUtils.isNotBlank(projectFactory.getRepairReplenishmentFinishTime()) && projectFactory.getOrderNature() == 2 && projectFactory.getDeliveryDate() != null){
						projectFactory.setProductFinishTime(projectFactory.getRepairReplenishmentFinishTime());
					}
				}
				if(factorys!=null&&factorys.size()>0){
					projectFactoryService.updateBatch(factorys);
				}	
			}
			 
			
			 jsonResult.setOk(true);
			 jsonResult.setMessage("保存成功");
			 
		} catch (NumberFormatException e) {
			 e.printStackTrace();
			 jsonResult.setOk(false);
			 jsonResult.setMessage("更新项目失败（Nina）"+e.getMessage());
		}catch (Exception e) {
			 e.printStackTrace();
			 jsonResult.setOk(false);
			 jsonResult.setMessage(e.getMessage());
		}
	    return jsonResult;
	}
	
	
	
	/**
	 * 查询项目详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryProject")
	public JsonResult queryProject(HttpServletRequest request,HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		String projectNo = request.getParameter("projectNo");
        Project project = projectService.selectProjctDetails(projectNo);
        List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectNo);
        //查询问题出现的次数
		List<AnalysisIssue> analysisIssueList = analysisIssueService.selectByProjectNo(projectNo,null);
		for (AnalysisIssue analysis : analysisIssueList) {
			if(StringUtils.isNotBlank(analysis.getIssue())){
				List<String> issueList = qualityAnalysisService.selectByIssue(analysis.getIssue());
				analysis.setOccurrenceNum(issueList != null ? issueList.size() : 0);
			}
		}
		//查询下单工厂列表
		List<ProjectFactory> projectFactoryList = projectFactoryService.selectByProjectNo(projectNo);
		List<QualityReport> qualityReportList = qrService.selectByProjectNo(projectNo);
		List<InspectionReservation>reserList=inspectionReservationService.getAllInspection(projectNo);
		
		int count = inspectionReservationService.selectInspectionReservationCountByProjectNo(projectNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reserList", reserList);
        map.put("project", project);
        map.put("projectCheckNumber", count+1);
        map.put("factoryList", factoryList);
        map.put("analysisIssueList", analysisIssueList);
        map.put("projectFactoryList", projectFactoryList);
        map.put("qualityReportList", qualityReportList);
        jsonResult.setData(map);
        jsonResult.setOk(true);
		return jsonResult;
	}
	/**
	 * 判断项目是否有延期
	 * @Title judgeDelay 
	 * @Description
	 * @return
	 * @return Boolean
	 */
	public static Map<String,Object> judgeDelay(Project project){
		Boolean delayType = false;
		Map<String,Object> map = new HashMap<String, Object>();
		int delayDays = 0;   //延期时间
		try {
//			Date deliveryDate = project.getDeliveryDate();			
			//判断大货是否延期
//			if(scheduleList != null && scheduleList.size() > 0){
//				 for (ProjectSchedule projectSchedule : scheduleList) {
					 Integer isFinish = project.getFinish();
					 Date predictDate = project.getDeliveryDate();
					 Integer DelayDay = project.getDelayDay();
					 Date actualDate = project.getFinishTime();
					 Date lastDate = project.getLateDeliveryDate();
					 if(isFinish == 1 && actualDate != null && predictDate != null){
						if(lastDate!=null&&!"".equals(lastDate)){
							if(DateFormat.calDays(actualDate, lastDate)-7>0){
								delayType = true;
								delayDays = DateFormat.calDays(actualDate, lastDate);
							}
									
						}else{ 
							if(DateFormat.calDays(actualDate, predictDate)-7-DelayDay>0){
								delayType = true;
								delayDays = DateFormat.calDays(actualDate, predictDate)-7-DelayDay;
							}
						
						}
					 }else if(isFinish == 0 && predictDate != null){
						 if(lastDate!=null&&!"".equals(lastDate)){
							 if(DateFormat.calDays(new Date(), lastDate)>0){
									delayType = true;
									delayDays = DateFormat.calDays(new Date(), lastDate);
							    } 
						 }else
							 if(DateFormat.calDays(new Date(), predictDate)-7-DelayDay>0){
									delayType = true;
									delayDays = DateFormat.calDays(new Date(), predictDate)-7-DelayDay;
							    } 
							
						 }
					 
					 				 
//				 }		 
//			}
			
			//如果已有大货交期则不管样品是否延期 
			//判断样品是否延期
			Date sampleScheduledDate = project.getSampleScheduledDate();
			if(sampleScheduledDate != null && project.getDeliveryDate() == null){
				if(project.getSampleFinish() == 0){
					if(lastDate!=null&&!"".equals(lastDate)){
						if(DateFormat.calDays(new Date(), lastDate)>0){
							delayType = true;
							delayDays = DateFormat.calDays(new Date(), lastDate);
					    }	
					}else{
					if(DateFormat.calDays(new Date(), sampleScheduledDate)-7-DelayDay>0){
						delayType = true;
						delayDays = DateFormat.calDays(new Date(), sampleScheduledDate)-7-DelayDay;
				    }
					}
				}else if(project.getSampleFinish() == 1 && project.getSampleFinishTime() != null){
					if(lastDate!=null&&!"".equals(lastDate)){
						if(DateFormat.calDays(project.getSampleFinishTime(), lastDate)>0){
							delayType = true;
							delayDays = DateFormat.calDays(project.getSampleFinishTime(), lastDate);
					    }	
					}else{
						if(DateFormat.calDays(project.getSampleFinishTime(), sampleScheduledDate)-7-DelayDay>0){
							delayType = true;
							delayDays = DateFormat.calDays(project.getSampleFinishTime(), sampleScheduledDate)-7-DelayDay;
					    }
					
					}
				}
			}
			
			
			 //如果存在新交货期，则根据新交货期判断项目是否延期
//			 if(deliveryDate!= null && project.getProjectStatus() == OrderStatusEnum.NORMAL_ORDER.getCode()){
//				 if(new Date().getTime() > deliveryDate.getTime()){
//						delayType = true;
//						delayDays = DateFormat.calDays(new Date(), deliveryDate);
//				 }else{
//					 delayType = false;
//					 delayDays=0;
//				 }
//			 }else if(deliveryDate!= null && project.getProjectStatus() == OrderStatusEnum.SAMPLE_ORDER.getCode()){
//				 if(project.getSampleFinishTime().getTime() > deliveryDate.getTime()){
//						delayType = true;
//						delayDays = DateFormat.calDays(project.getSampleFinishTime(), deliveryDate);
//				 }else{
//					 delayType = false;
//					 delayDays=0;
//				 }
//			 }else if(deliveryDate!= null && project.getProjectStatus() == OrderStatusEnum.COMPLETE_ORDER.getCode()){
//				 if(project.getFinishTime() !=null && project.getFinishTime().getTime() > deliveryDate.getTime()){
//					 delayType = true;
//					 delayDays = DateFormat.calDays(project.getFinishTime(), deliveryDate);
//				 }else{
//					 delayType = false;
//					 delayDays=0;
//				 }
//			 }
			 map.put("delayType", delayType);
			 map.put("delayDays", delayDays);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	 public static String getIpuser(HttpServletRequest request) throws UnknownHostException {
		 InetAddress addr = InetAddress.getLocalHost();  
         String ip=addr.getHostAddress().toString(); //获取本机ip  
         return ip;
	 }
	   /**
		 * 查询疑难项目列表页
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("/difficultItemListPage")
		public String difficultItemListPage(HttpServletRequest request,HttpServletResponse response) {
			String userName = request.getParameter("userName");
			User User1=userService.selectUserByName(userName);
			String userId = request.getParameter("userId");
			String roleNo = request.getParameter("roleNo");
			Project project=new Project();
			if(userId!=null&&!"".equals(userId)){
				project.setEmailUserId(Integer.parseInt(userId));
			}else{
			  project.setEmailUserId(User1.getId());
			  if("jerrylong".equalsIgnoreCase(userName)){
					project.setEmailUserId(0);
				}
			}
			request.setAttribute("userName", userName);
			if("jerrylong".equalsIgnoreCase(userName)){
				request.setAttribute("userName", "jerrylong");
			}
			
          try {
			// 1.查询项目详细信息
			List<Project> list = projectService.getDifficultItemListPage(project);
			User user=new User();
			user.setRoleNo(5);
			List<User>userList=userService.queryByRoleNo(user);
			request.setAttribute("list", list);
			request.setAttribute("userList", userList);
			
			request.setAttribute("userId", User1.getId());
			request.setAttribute("roleNo", roleNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "difficult_project_list";
		}
		
		/**
		 * 修改是否是疑难项目
		 * @Title deleteProject 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @return JsonResult
		 */	
		@RequestMapping(value="/updateDifficultProject")
		@ResponseBody
		public JsonResult updateDifficultProject(HttpServletRequest request,HttpServletResponse response){
			 JsonResult jsonResult = new JsonResult();
			 try {
				 Project project=new Project();
				 String projectNo = request.getParameter("projectNo");	
			     String difficultProject = request.getParameter("difficultProject");
			     Boolean save=projectNo.toLowerCase().contains("shs".toLowerCase());
			     project.setDifficultProject(Integer.parseInt(difficultProject));
			     if(save!=false){
			    	 project.setProjectNo(projectNo);
			    	 projectService.updateDifficultProject(project);
			    	 jsonResult.setOk(true);
					 jsonResult.setMessage("修改成功");
			     }else{
			    	 String projectNo1="SHS"+projectNo;
			    	 project.setProjectNo(projectNo1);
			    	 projectService.updateDifficultProject(project);
			    	 jsonResult.setOk(true);
					 jsonResult.setMessage("修改成功");
			     }		     
			     
			 } catch (Exception e) {
					e.printStackTrace();
					jsonResult.setMessage(e.getMessage());
					jsonResult.setOk(false);
					LOG.error("error", e);
			}
			 return jsonResult;
		}
	 
	 
		/**
		 * 查询工厂项目列表
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("/queryFactoryProject")
		public String queryFactoryProject(HttpServletRequest request,HttpServletResponse response) {
						
			try {
				String pageStr = request.getParameter("pageStr"); 
				String selectStr = request.getParameter("selectStr");  // 搜索词
				String pageSizeStr = request.getParameter("pageSize");
				String factoryName = request.getParameter("factoryName");
				//项目性质（0：样品 1：大货 2：补货）
				String orderStatus = request.getParameter("orderStatus");
				//项目状态（合同状态11在做模具  2在打样或小批量 3在大批量生产4因为工厂原因还未做5因为我司或者客户原因暂停6已经部分交货，正在继续生产7已经部分交货，目前暂停）
				String orderState = request.getParameter("orderState");  
				//其他筛选（1：上传合同10天后，无开工视频）
				String otherSelect = request.getParameter("otherSelect"); 
				
				Integer roleNo = null;                               // 判断是管理员，销售，采购
				String userName = WebCookie.getUserName(request);
				String userId = null;
				if(StringUtils.isNotBlank(userName)){
					User user = userService.findUserByName(userName);
					roleNo = user.getRoleNo();
					userId= user.getId() + "";
				}else{
					return "redirect:/index.jsp";
				}
				
				
				//页数
				Integer page = null;
				if(StringUtils.isNotBlank(pageStr)){
					page = Integer.parseInt(pageStr);
				}else{
					page = 1;
				}
				//每页显示数
				Integer pageSize = null;
				if(StringUtils.isNotBlank(pageSizeStr)){
					pageSize = Integer.parseInt(pageSizeStr);
				}else{
					pageSize = 100;
				}
				ProjectFactoryQuery projectFactoryQuery = new ProjectFactoryQuery();
				projectFactoryQuery.setInputKey(selectStr);
				projectFactoryQuery.setPageNumber(pageSize * (page - 1));
				projectFactoryQuery.setPageSize(pageSize);
				projectFactoryQuery.setUserId(userId);
				projectFactoryQuery.setRoleNo(roleNo);
				projectFactoryQuery.setUserName(userName);
				projectFactoryQuery.setFactoryName(factoryName);
				if(StringUtils.isNotBlank(orderStatus)){
					projectFactoryQuery.setOrderStatus(Integer.parseInt(orderStatus));
				}
				if(StringUtils.isNotBlank(orderState)){
					projectFactoryQuery.setOrderState(Integer.parseInt(orderState));
				}				
				if(StringUtils.isNotBlank(otherSelect)){
					projectFactoryQuery.setOtherSelect(Integer.parseInt(otherSelect));
				}				
				//根据工厂名查询进行中项目
				List<ProjectFactory> factoryList = projectFactoryService.selectProjectList(projectFactoryQuery);
				String factoryId = null;
				if(factoryList!=null){
					for (int i = factoryList.size()-1; i >=0; i--) {
						ProjectFactory projectFactory = factoryList.get(i);	
					    Map<String, Object> map = quoteWeeklyReportService.queryGroupByFileType(projectFactory.getProjectNo(),projectFactory.getFactoryId());
						//如果上传开工视频，代表已上传
						if((map.get("videoCount")!=null && !"0".equals(map.get("videoCount").toString()))){
							projectFactory.setIsUploadVideo(true);
							//如果选择的是(上传合同10天后，无开工视频)
							if("1".equals(otherSelect)){
								factoryList.remove(i);
								continue;
							}
						}else{
							projectFactory.setIsUploadVideo(false);
						}
						//如果上传开工照片，代表已上传
						if((map.get("picCount")!=null && !"0".equals(map.get("picCount").toString()))){
							projectFactory.setIsUploadPic(true);
						}else{
							projectFactory.setIsUploadPic(false);
						}
						//材质证明是否上传
						if(map.get("materialCount")!=null && !"0".equals(map.get("materialCount").toString())){
							projectFactory.setIsUploadMaterial(true);
						}else{
							projectFactory.setIsUploadMaterial(false);
						}
						//质检报告证明是否上传
						if(map.get("qualityCount")!=null && !"0".equals(map.get("qualityCount").toString())){
							projectFactory.setIsUploadQualityReport(true);
						}else{
							projectFactory.setIsUploadQualityReport(false);
						}
					 Project project= projectService.selectProjctDetails(projectFactory.getProjectNo());	
					 projectFactory.setPlantAnalysis(project.getPlantAnalysis());
					 List<ProjectInspectionReport> inspectionPlanList=projectInspectionReportService.selectInspectionPlanByProjectNo(projectFactory.getProjectNo());	 
					 List<ProjectInspectionReport> inspectionPlanList1=new ArrayList<ProjectInspectionReport>();
					 if(inspectionPlanList.size()>0){
					 inspectionPlanList1.add(inspectionPlanList.get(0));
					 projectFactory.setInspectionPlanList(inspectionPlanList1);
					 }else{
						 projectFactory.setInspectionPlanList(null); 
					 }
					 
					 List<ProjectComplaint> complaintList=projectComplaintService.selectByProjectNo(projectFactory.getProjectNo());
					 
					 if(complaintList.size()>0){
					projectFactory.setComplaintList(complaintList);
					 }else{
						 projectFactory.setComplaintList(null);	 
					 }
					 List<QualityReport> qualityReportList=qrService.selectByProjectNo(projectFactory.getProjectNo());
					 List<QualityReport> qualityReportList1=new ArrayList<QualityReport>();
					 if(qualityReportList.size()>0){
					qualityReportList1.add(qualityReportList.get(0));
					 projectFactory.setQualityReportList(qualityReportList1);
					 }else{
				     projectFactory.setQualityReportList(null);	 
					 }
					}
				}

				int factoryListCount = projectFactoryService.selectProjectListCount(projectFactoryQuery);
				request.setAttribute("factoryName", factoryName);
				request.setAttribute("factoryId", factoryId);
				request.setAttribute("selectStr", selectStr==null?"":selectStr);
				request.setAttribute("factoryList", factoryList);				
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("page", page);
				request.setAttribute("userName", userName);
				request.setAttribute("count", factoryListCount);
				request.setAttribute("orderStatus", orderStatus);
				request.setAttribute("orderState", orderState);
				request.setAttribute("otherSelect", otherSelect);
				//计算尾页
				Integer lastNum = new BigDecimal(factoryListCount).divide(new BigDecimal(pageSize)).setScale(0,BigDecimal.ROUND_UP).intValue();
				request.setAttribute("lastNum", lastNum);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				LOG.error("queryFactoryProject error", e);
			}
			
			
			return "factory_project_list";
		}
		
		
		
		
		/**
		 * 查询工厂项目列表
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("/queryFactoryList")
		public String queryFactoryList(HttpServletRequest request,HttpServletResponse response) {
			try {
				String pageStr = request.getParameter("pageStr"); 
				String inputKey = request.getParameter("inputKey");  // 搜索词
				String purchaseName = request.getParameter("purchaseName");
				String qualityName = request.getParameter("qualityName");
				String pageSizeStr = request.getParameter("pageSize");
				//根据时间筛选
				String queryDateStr = request.getParameter("queryDate");
				//排序
				String sortNumStr = request.getParameter("sortNum");
				//排序(上下)
				String upOrDownStr = request.getParameter("upOrDown");
	
				
				
				Integer roleNo = null;                               // 判断是管理员，销售，采购
				String userName = WebCookie.getUserName(request);
				if(StringUtils.isNotBlank(userName)){
					User user = userService.findUserByName(userName);
					roleNo = user.getRoleNo();
				}else{
					return "redirect:/index.jsp";
				}
				
				
				//页数
				Integer page = null;
				if(StringUtils.isNotBlank(pageStr)){
					page = Integer.parseInt(pageStr);
				}else{
					page = 1;
				}
				//每页显示数
				Integer pageSize = null;
				if(StringUtils.isNotBlank(pageSizeStr)){
					pageSize = Integer.parseInt(pageSizeStr);
				}else{
					pageSize = 100;
				}
				ProjectFactoryQuery projectFactoryQuery = new ProjectFactoryQuery();
				projectFactoryQuery.setInputKey(inputKey);
				projectFactoryQuery.setPageNumber(pageSize * (page - 1));
				projectFactoryQuery.setPageSize(pageSize);
				projectFactoryQuery.setRoleNo(roleNo);
				projectFactoryQuery.setQualityName(qualityName);
				projectFactoryQuery.setPurchaseName(purchaseName);
				Integer queryDate = null;
				if(StringUtils.isNotBlank(queryDateStr)){
					queryDate = Integer.parseInt(queryDateStr);
					projectFactoryQuery.setQueryDate(queryDate);
				}else{
					queryDate= 2;
					projectFactoryQuery.setQueryDate(2);
				}
				if(StringUtils.isNotBlank(sortNumStr)){
					projectFactoryQuery.setSortNum(Integer.parseInt(sortNumStr));
				}else{
					projectFactoryQuery.setSortNum(null);
				}
				if(StringUtils.isNotBlank(upOrDownStr)){
					projectFactoryQuery.setUpOrDown(Integer.parseInt(upOrDownStr));
				}else{
					projectFactoryQuery.setUpOrDown(0);
				}
				
				List<ProjectFactory> factoryList = projectFactoryService.selectFactoryList(projectFactoryQuery);
				/*for (ProjectFactory projectFactory : factoryList) {
					//计算项目延期比例(全项目)
					Double delayRate = projectService.selectRateByFactory(projectFactory.getFactoryName(),DELAY);
					if(delayRate!=null){
						delayRate = new BigDecimal(delayRate * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					}
					projectFactory.setDelayRate(delayRate);
					//计算项目延期比例(单一工厂项目)
					Double delayRateOnly = projectService.selectRateByFactoryOnly(projectFactory.getFactoryName(),DELAY);
					if(delayRateOnly!=null){
						delayRateOnly = new BigDecimal(delayRateOnly * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					}
					projectFactory.setDelayRateOnly(delayRateOnly);
					
					//计算投诉比例(全项目)
					Integer complaintCount = 0;
					if(projectFactory.getComplaintCount() != null){
						complaintCount = projectFactory.getComplaintCount();
					}
					int totalCount = projectFactoryService.selectCountByFactoryName(projectFactory.getFactoryName());
					Double complaintRate = 0.0;  //投诉率
					if(totalCount!=0){
						complaintRate = new BigDecimal(complaintCount*100).divide(new BigDecimal(totalCount), BigDecimal.ROUND_HALF_UP, 2).doubleValue();						
					}
					projectFactory.setComplaintRate(complaintRate);
					//计算投诉比例(单一工厂项目)
					Integer complaintCountOnly = 0;
					if(projectFactory.getComplaintCountOnly() != null){
						complaintCountOnly = projectFactory.getComplaintCountOnly();
					}
					int totalCountOnly = projectFactoryService.selectCountByFactoryNameOnly(projectFactory.getFactoryName());
					Double complaintRateOnly = 0.0;  //投诉率
					if(totalCountOnly!=0){
						complaintRateOnly = new BigDecimal(complaintCountOnly*100).divide(new BigDecimal(totalCountOnly), BigDecimal.ROUND_HALF_UP, 2).doubleValue();						
					}
					projectFactory.setComplaintRateOnly(complaintRateOnly);
					
					//计算工厂评分
					Map<String, Object> scoreMapper = factoryScoreService.selectAvgScore(projectFactory.getFactoryName(),queryDate);
					//平均分
					Double avgScore = 0.0;
					//评分数量
					Integer scoreCount = null;
					if(scoreMapper!=null){
						if(scoreMapper.get("avgScore") != null){
							avgScore = Double.parseDouble(scoreMapper.get("avgScore").toString());
						}
						avgScore = new BigDecimal(avgScore).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
						if(scoreMapper.get("scoreCount") != null){
							scoreCount = Integer.parseInt(scoreMapper.get("scoreCount").toString());
						}
					}	
					projectFactory.setAvgScore(avgScore);
					projectFactory.setScoreCount(scoreCount);
				}*/

				//Collections.sort(factoryList,new DelaySort());
				
				int factoryListCount = projectFactoryService.selectFactoryListCount(projectFactoryQuery);
			    //查询质检、采购去仓库次数
				Integer purchaseStoreCount = 0;
				Integer qualityStoreCount = 0;
				Map<String, Long> storeCount = trackService.selectFromStore(qualityName, purchaseName,"仓库",queryDate);
				if(storeCount!=null){
					if(storeCount.get("purchaseCount")!=null){
						purchaseStoreCount = storeCount.get("purchaseCount").intValue();
					}
					if(storeCount.get("qualityCount")!=null){
						qualityStoreCount = storeCount.get("qualityCount").intValue();
					}					
				}
				//查询质检、采购去公司次数
				Integer purchaseCompanyCount = 0;
				Integer qualityCompanyCount = 0;
				Map<String, Long> companyCount = trackService.selectFromStore(qualityName, purchaseName,"公司",queryDate);
				if(companyCount!=null){
					if(companyCount.get("purchaseCount")!=null){
						purchaseCompanyCount = companyCount.get("purchaseCount").intValue();
					}
					if(companyCount.get("qualityCount")!=null){
						qualityCompanyCount = companyCount.get("qualityCount").intValue();
					}					
				}
				
				
				request.setAttribute("factoryList", factoryList);				
				request.setAttribute("inputKey", inputKey);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("page", page);
				request.setAttribute("userName", userName);
				request.setAttribute("qualityName", qualityName);
				request.setAttribute("purchaseName", purchaseName);
				request.setAttribute("count", factoryListCount);
				request.setAttribute("purchaseStoreCount", purchaseStoreCount);
				request.setAttribute("qualityStoreCount", qualityStoreCount);
				request.setAttribute("purchaseCompanyCount", purchaseCompanyCount);
				request.setAttribute("qualityCompanyCount", qualityCompanyCount);
				request.setAttribute("queryDate", queryDate);
				request.setAttribute("sortNum", sortNumStr);
				request.setAttribute("upOrDown", upOrDownStr);
				//计算尾页
				Integer lastNum = new BigDecimal(factoryListCount).divide(new BigDecimal(pageSize)).setScale(0,BigDecimal.ROUND_UP).intValue();
				request.setAttribute("lastNum", lastNum);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
				LOG.error("queryFactoryList error", e);
			}
			return "factory";
		}
	 
		
		
		
		
		
		
		/**
		 * 设置分批交期  polo  2018.12.28
		 * @Title updateProjectSchedule 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 * @return JsonResult
		 */
		@RequestMapping("/addProjectSchedule")
		@ResponseBody
		public JsonResult addProjectSchedule(HttpServletRequest request,HttpServletResponse response) throws IOException {
			JsonResult jsonResult = new JsonResult();
			try {
				String projectNo = request.getParameter("projectNo");
				String deliveryDate = request.getParameter("deliveryDate");
				if(deliveryDate != null){
					ProjectSchedule projectSchedule = new ProjectSchedule();
					projectSchedule.setCreateTime(new Date());
					projectSchedule.setProjectNo(projectNo);
					projectSchedule.setPredictDate(DateUtil.StrToDate(deliveryDate));
					projectScheduleService.insertSelective(projectSchedule);
					jsonResult.setOk(true);
					jsonResult.setData(projectSchedule.getId());
				}else{
					jsonResult.setOk(false);
					jsonResult.setMessage("未获取到时间");
				}
				return jsonResult;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error("error", e);
				return jsonResult;
			}
		}
		
		
		
		
		/**
		 * 设置分批交期  polo  2018.12.28
		 * @Title updateProjectSchedule 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 * @return JsonResult
		 */
		@RequestMapping("/deleteProjectSchedule")
		@ResponseBody
		public JsonResult deleteProjectSchedule(HttpServletRequest request,HttpServletResponse response) throws IOException {
			JsonResult jsonResult = new JsonResult();
			try {
				String id = request.getParameter("id");
				if(id != null){
					projectScheduleService.deleteByPrimaryKey(Integer.parseInt(id));
					jsonResult.setOk(true);
					jsonResult.setMessage("删除成功");
				}else{
					jsonResult.setOk(false);
					jsonResult.setMessage("未获取到id");
				}
				return jsonResult;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error("error", e);
				return jsonResult;
			}
	}
		
		
		
		
		
		
		
		
		/**
		 * 导出最近一个月完成项目
		 * @Title exportMonthProject 
		 * @Description
		 * @param request
		 * @param response
		 * @return void
		 */
		@RequestMapping(value="/exportMonthProject")
		public void exportMonthProject(HttpServletRequest request,HttpServletResponse response){
			
	        try {
				List<Project> productFinishs = projectService.selectMonthProject();
				String excelPath = ProjectStatisticsPrint.exportMonthProject(request, productFinishs);
				File outFile = new File(excelPath);  
				InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));  
				byte[] buffer = new byte[fis.available()];  
				fis.read(buffer);  
				fis.close();  
				// 清空response  
				response.reset();  
				// 设置response的Header  
				String fileName = "最近一个月完成的项目导出.xls";
				fileName = URLEncoder.encode(fileName, "utf-8");        //这里要用URLEncoder转下才能正确显示中文名称  
				response.addHeader("Content-Disposition", "attachment;filename=" + fileName);  
				response.addHeader("Content-Length", "" + outFile.length());  
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
				response.setContentType("application/octet-stream");  
				toClient.write(buffer);  
				toClient.flush();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}		
		
		
		
		
		/**
		 * 删除质量分析表、技术分析表  2019.01.02
		 * @Title deleteAnalysis 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 * @return JsonResult
		 */
		@RequestMapping("/deleteAnalysis")
		@ResponseBody
		public JsonResult deleteAnalysis(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
				String id = request.getParameter("id");
			     //文件类型（1：质量分析会 2：技术分析会）
		 		String fileType = request.getParameter("fileType");
				if(id != null){
					QualityAnalysis qualityAnalysis = qualityAnalysisService.selectByPrimaryKey(Integer.parseInt(id));
					Map<String,Object> map = new HashMap<String, Object>();
					if("1".equals(fileType)){
						qualityAnalysis.setQualityAnalysisName(null);
						qualityAnalysis.setQualityAnalysisNewName(null);
						qualityAnalysis.setQualityUploadTime(null);
						qualityAnalysisService.updateByPrimaryKey(qualityAnalysis);
						
						
						map.put("projectNo", qualityAnalysis.getProjectNo());
						map.put("delete", "qualityAnalysisNewName");
						String json = SerializeUtil.ObjToJson(map);
			 			mqProducerService.sendDataToQueue("qualityDelete", json);
					}else if("2".equals(fileType)){
						qualityAnalysis.setTechnologyAnalysisName(null);
						qualityAnalysis.setTechnologyAnalysisNewName(null);
						qualityAnalysis.setTechnologyUploadTime(null);
						qualityAnalysisService.updateByPrimaryKey(qualityAnalysis);
						
						map.put("projectNo", qualityAnalysis.getProjectNo());
						map.put("delete", "technologyAnalysisNewName");
						String json = SerializeUtil.ObjToJson(map);
			 			mqProducerService.sendDataToQueue("qualityDelete", json);
					}
					jsonResult.setOk(true);
					jsonResult.setMessage("删除成功");
				}else{
					jsonResult.setOk(false);
					jsonResult.setMessage("未获取到id");
				}
				return jsonResult;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error("error", e);
				return jsonResult;
			}
	}
		/**
		 * 删除可能出现问题  2019.01.02
		 * @Title deleteAnalysisIssue 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 * @return JsonResult
		 */
		@RequestMapping("/deleteAnalysisIssue")
		@ResponseBody
		public JsonResult deleteAnalysisIssue(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
				String id = request.getParameter("id");
				if(id != null){
					analysisIssueService.deleteByPrimaryKey(Integer.parseInt(id));
					jsonResult.setOk(true);
					jsonResult.setMessage("删除成功");
				}else{
					jsonResult.setOk(false);
					jsonResult.setMessage("未获取到id");
				}
				return jsonResult;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error("error", e);
				return jsonResult;
			}
		}
		
		
		
		/**
		 * 删除质量分析表、技术分析表  2019.01.02
		 * @Title updateAnalysis 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 * @return JsonResult
		 */
		@RequestMapping("/updateAnalysis")
		@ResponseBody
		public JsonResult updateAnalysis(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
				String ids = request.getParameter("id");
			     //文件类型（1：质量分析会 2：技术分析会）
		 		String fileType = request.getParameter("fileType");
		 		String fileNewName = request.getParameter("fileNewName");
		 		String fileName = request.getParameter("fileName");
		 		String projectNo = request.getParameter("projectNo");
		 		String process = request.getParameter("process");
		 		String userName = WebCookie.getUserName(request);
		 		if(StringUtils.isBlank(userName)){
					jsonResult.setOk(false);
					jsonResult.setMessage("请先登录");
					return jsonResult;
				}	
		 		//如果已经存在，则进行更新，不存在，则插入
		 		QualityAnalysis qualityAnalysis = new QualityAnalysis();
		 		if(StringUtils.isNotBlank(ids)){
		 			qualityAnalysis = qualityAnalysisService.selectByPrimaryKey(Integer.parseInt(ids));				 			
		 			if(TECHNOLOGY_ANALYSIS.equals(fileType)){
		 				qualityAnalysis.setTechnologyAnalysisNewName(fileNewName);
		 				qualityAnalysis.setTechnologyAnalysisName(fileName);
		 				qualityAnalysis.setTechnologyUploadTime(new Date());
		 				qualityAnalysis.setProcess(process);
		 				qualityAnalysisService.updateByPrimaryKeySelective(qualityAnalysis, userName, 2);		 				
		 			}else if(QUALITY_ANALYSIS.equals(fileType)){
		 				qualityAnalysis.setQualityAnalysisNewName(fileNewName);
		 				qualityAnalysis.setQualityAnalysisName(fileName);
		 				qualityAnalysis.setQualityUploadTime(new Date());
		 				qualityAnalysis.setProcess(process);
		 				qualityAnalysisService.updateByPrimaryKeySelective(qualityAnalysis, userName, 1);		 				
		 			}
		 			String json = SerializeUtil.ObjToJson(qualityAnalysis);
		 			mqProducerService.sendDataToQueue("quality", json);
		 			
		 		}else{
		 			qualityAnalysis.setProjectNo(projectNo);
		 			if(TECHNOLOGY_ANALYSIS.equals(fileType)){
		 				qualityAnalysis.setTechnologyAnalysisNewName(fileNewName);
		 				qualityAnalysis.setTechnologyAnalysisName(fileName);
		 				qualityAnalysis.setTechnologyUploadTime(new Date());
		 				qualityAnalysis.setProcess(process);
		 				qualityAnalysisService.insertSelective(qualityAnalysis, userName, 2);
		 				//发送到rabbitmq exchange
		 				String json = SerializeUtil.ObjToJson(qualityAnalysis);
			 			mqProducerService.sendDataToQueue("quality", json);
			 			
		 			}else if(QUALITY_ANALYSIS.equals(fileType)){
		 				qualityAnalysis.setQualityAnalysisNewName(fileNewName);
		 				qualityAnalysis.setQualityAnalysisName(fileName);
		 				qualityAnalysis.setQualityUploadTime(new Date());
		 				qualityAnalysis.setProcess(process);
		 				qualityAnalysisService.insertSelective(qualityAnalysis, userName, 1);
		 				
		 				
		 				//发送到rabbitmq exchange
		 				String json = SerializeUtil.ObjToJson(qualityAnalysis);
			 			mqProducerService.sendDataToQueue("quality", json);
		 			}		 			
		 		}	
		 		
		 		//上传质量分析表时，保存已经存在的工艺问题
		 		if(StringUtils.isNotBlank(process)){
		 			String[] split = process.split(",");
		 			if(split.length>0){
		 				for (int i = 0; i < split.length; i++) {
		 					List<AnalysisIssue> issueList = analysisIssueService.selectByProcess(split[i]);
		 					for (AnalysisIssue analysisIssue : issueList) {
		 						analysisIssue.setProjectNo(projectNo);
		 						analysisIssue.setCreateTime(new Date());
		 						analysisIssue.setIsComplaint(0);
							}
		 					if(issueList!=null&&issueList.size()>0){
			 					analysisIssueService.insertBatch(issueList);
		 					}
						}
		 				
		 			}
		 		}
		 		
		 		
		 		jsonResult.setOk(true);
				return jsonResult;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error("error", e);
				return jsonResult;
			}
	}
		
		/**
		 * 添加问题  2019.01.02
		 * @Title addAnalysisIssue 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 * @return JsonResult
		 */
		@RequestMapping("/addAnalysisIssue")
		@ResponseBody
		public JsonResult addAnalysisIssue(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
				String qualityAnalysisId = request.getParameter("qualityAnalysisId");			    
		 		String projectNo = request.getParameter("projectNo");
		 		String issue = request.getParameter("issue");
		 		String process = request.getParameter("process");
		 		String isComplaint = request.getParameter("isComplaint");
		 		String complaintIdStr = request.getParameter("complaintId");
		 		String userName = WebCookie.getUserName(request);
		 		if(StringUtils.isBlank(userName)){
					jsonResult.setOk(false);
					jsonResult.setMessage("请先登录");
					return jsonResult;
				}	
		 		//保存数据库		 		
	 			AnalysisIssue analysisIssue = new AnalysisIssue();
	 			analysisIssue.setIssue(issue);
	 			analysisIssue.setProjectNo(projectNo);
	 			if(StringUtils.isNotBlank(qualityAnalysisId)){
	 			  analysisIssue.setQualityAnalysisId(Integer.parseInt(qualityAnalysisId));
	 			}
	 			if(StringUtils.isNotBlank(isComplaint)){
	 				analysisIssue.setIsComplaint(Integer.parseInt(isComplaint));
	 			}else{
	 				analysisIssue.setIsComplaint(0);
	 			}
	 			if(StringUtils.isNotBlank(complaintIdStr)){
	 				analysisIssue.setComplaintId(Integer.parseInt(complaintIdStr));
	 			}
	 			analysisIssue.setCreateTime(new Date());
	 			analysisIssue.setProcess(process);
	 			analysisIssueService.insertSelective(analysisIssue);
	 			//查询问题出现的次数
//	 			List<AnalysisIssue> analysisIssueList = analysisIssueService.selectByProjectNo(projectNo);
//	 			for (AnalysisIssue analysis : analysisIssueList) {
	 				if(StringUtils.isNotBlank(issue)){
	 					List<String> issueList = qualityAnalysisService.selectByIssue(issue);
	 					analysisIssue.setOccurrenceNum(issueList != null ? issueList.size() : 0);
	 				}
//	 			}	 				 			
	 			
		 		jsonResult.setOk(true);
		 		jsonResult.setData(analysisIssue);
				return jsonResult;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error("error", e);
				return jsonResult;
			}
	}
		
		/**
		 * 问题回复  2019.02.12
		 * @Title addAnalysisIssueBatch 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 * @return JsonResult
		 */
		@RequestMapping("/addAnalysisIssueBatch")
		@ResponseBody
		public JsonResult addAnalysisIssueBatch(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
//				String qualityAnalysisId = request.getParameter("qualityAnalysisId");			    
				//批量保存问题
				String issueList = request.getParameter("issueList");
//				String projectNo = request.getParameter("projectNo");
				List<AnalysisIssue> issues = null;
				if(StringUtils.isNotBlank(issueList)){				
					ObjectMapper objectMapper = new ObjectMapper();
					JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, AnalysisIssue.class);
					issues = objectMapper.readValue(issueList,javaType);
					//当返修补货完结时，对应样品合同或者大货合同也需要有完结时间
					for (AnalysisIssue issue : issues) {
					    issue.setCreateTime(new Date());
					    //查询问题出现的次数
					    if(StringUtils.isNotBlank(issue.getIssue())){
		 					List<String> list = qualityAnalysisService.selectByIssue(issue.getIssue());
		 					issue.setOccurrenceNum(list != null ? list.size() : 0);
		 				}
					}
					analysisIssueService.insertBatch(issues);	 				 
				}
				
				
				
		 		jsonResult.setOk(true);
		 		jsonResult.setData(issues);
				return jsonResult;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error(" <<<<<addAnalysisIssueBatch error>>>>>>>", e);
				return jsonResult;
			} catch (JsonParseException e) {				
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error("<<<<<addAnalysisIssueBatch error>>>>>>>", e);
				return jsonResult;
			} catch (JsonMappingException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error("<<<<<addAnalysisIssueBatch error>>>>>>>", e);
				return jsonResult;
			} catch (IOException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error("<<<<<addAnalysisIssueBatch error>>>>>>>", e);
				return jsonResult;
			}
	}	
		
		
		
		/**
		 * 问题回复  2019.01.02
		 * @Title updateAnalysisIssue 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 * @return JsonResult
		 */
		@RequestMapping("/updateAnalysisIssue")
		@ResponseBody
		public JsonResult updateAnalysisIssue(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
				String ids = request.getParameter("id");			    
		 		String issueReply = request.getParameter("issueReply");
		 		String userName = WebCookie.getUserName(request);
		 		if(StringUtils.isBlank(userName)){
					jsonResult.setOk(false);
					jsonResult.setMessage("请先登录");
					return jsonResult;
				}	
		 		User user = userService.selectUserByName(userName);
		 		//进行更新
		 		if(StringUtils.isNotBlank(ids)){
		 			QualityAnalysis qualityAnalysis = qualityAnalysisService.selectByPrimaryKey(Integer.parseInt(ids));
		 			//采购回复
		 			if(user.getRoleNo() == 6){
		 				qualityAnalysis.setPurchaseReply(issueReply);
		 			}
		 			//技术回复
		 			if("wangweiping".equalsIgnoreCase(userName)){
		 				qualityAnalysis.setTechnicianReply1(issueReply);
		 			}		 			
		 			qualityAnalysisService.updateByPrimaryKeySelective(qualityAnalysis);
		 		}
		 		jsonResult.setOk(true);
				return jsonResult;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error("error", e);
				return jsonResult;
			}
	}

		
		/**
		 * 查询问题标签
		 * @Title selectByIssue 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 * @return JsonResult
		 */
		@RequestMapping("/selectByIssue")
		@ResponseBody
		public JsonResult selectByIssue(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
				String issue = request.getParameter("issue");
				String process = request.getParameter("process");
				List<AnalysisIssue> issueList = analysisIssueService.selectByProcessAndIssue(process, issue);
				jsonResult.setData(issueList);
				jsonResult.setOk(true);
				return jsonResult;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error("error", e);
				return jsonResult;
			}
		}
		/**
		 * 查询投诉问题标签
		 * @Title selectByIssue 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 * @return JsonResult
		 */
		@RequestMapping("/selectComplaintIssue")
		@ResponseBody
		public JsonResult selectComplaintIssue(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
				List<AnalysisIssue> issueList = analysisIssueService.selectComplaintIssue();
				jsonResult.setData(issueList);
				jsonResult.setOk(true);
				return jsonResult;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
				jsonResult.setMessage(e.getMessage());
				LOG.error("selectComplaintIssue error", e);
				return jsonResult;
			}
		}
		
		
		
		/**
		 * 查询问题标签列表
		 * @Title selectIssueList 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 * @return JsonResult
		 */
		@RequestMapping("/selectIssueList")
		public String selectIssueList(HttpServletRequest request,HttpServletResponse response){
				String issue = request.getParameter("issue");       //问题、搜索词
				String inputKey = request.getParameter("inputKey"); //无效 2019.1.7
				String typeStr = request.getParameter("type");         //类型
				String pageStr = request.getParameter("pageStr");   //页码
				String pageSizeStr = request.getParameter("pageSize"); //页数
				//页数
				Integer page = null;
				if(StringUtils.isNotBlank(pageStr)){
					page = Integer.parseInt(pageStr);
				}else{
					page = 1;
				}
				//每页显示数
				Integer pageSize = null;
				if(StringUtils.isNotBlank(pageSizeStr)){
					pageSize = Integer.parseInt(pageSizeStr);
				}else{
					pageSize = 20;
				}
				Integer pageNumber = pageSize * (page - 1);
				//关键词来源类型
				Integer type = 0;
				if(StringUtils.isNotBlank(typeStr)){
					type = Integer.parseInt(typeStr);
				}
				//如果issue为空值，默认为null
				if("".equals(issue)){
					issue = null;
				}
				
				//查询出来的类型，加项目号
				List<Map<String, Object>> list = qualityAnalysisService.selectMapByIssue(issue,inputKey,type,pageNumber,pageSize);
				for (Map<String, Object> m : list) {
					Project project = projectService.selectProjctDetails(m.get("projectNo").toString());
					m.put("projectName", project.getProjectName());
				}
				List<Map<String, Object>> countList = qualityAnalysisService.selectMapByIssue(issue,inputKey,type,null,pageSize);
				request.setAttribute("showList", list);
				request.setAttribute("issue", issue);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("page", page);
				request.setAttribute("type", type);
				Integer totalCount = (countList!=null?countList.size():0);
				request.setAttribute("count", totalCount);
				//计算尾页
				Integer lastNum = new BigDecimal(totalCount).divide(new BigDecimal(pageSize)).setScale(0,BigDecimal.ROUND_UP).intValue();
				request.setAttribute("lastNum", lastNum);
				
              return "quality_problem";
		}
		
		
		
		
		/**
		 * 查询进行中项目列表
		 * @Title selectProjectView 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 * @return JsonResult
		 */
		@RequestMapping("/selectProjectView")
		public String selectProjectView(HttpServletRequest request,HttpServletResponse response){
                /**
                 * 采购
                 */
                String purchaseName = request.getParameter("purchaseName");
                /**
                 * 销售
                 */
                String sellName = request.getParameter("sellName");
				String pageStr = request.getParameter("page");   //页码
				String pageSizeStr = request.getParameter("pageSize"); //页数
				String userName = WebCookie.getUserName(request);
		 		if(StringUtils.isBlank(userName)){
				}	
		 		User user = userService.selectUserByName(userName);
				//页数
				Integer page = null;
				if(StringUtils.isNotBlank(pageStr)){
					page = Integer.parseInt(pageStr);
				}else{
					page = 1;
				}
				//每页显示数
				Integer pageSize = null;
				if(StringUtils.isNotBlank(pageSizeStr)){
					pageSize = Integer.parseInt(pageSizeStr);
				}else{
					pageSize = 50;
				}
		
				Project project = new Project();
				List<Integer> list = new ArrayList<Integer>();
				list.add(OrderStatusEnum.NORMAL_ORDER.getCode());
//				list.add(OrderStatusEnum.SAMPLE_ORDER.getCode());
				project.setProjectStatusS(list);
				project.setUserName(WebCookie.getUserName(request));
				project.setRoleNo(WebCookie.getRoleNo(request));
				project.setPurchaseName(purchaseName);
				project.setSellName(sellName);
				//pageHelper分页
				PageHelper.startPage(page,pageSize);
                List<Project> projectList = projectService.findProjectByStatus(project);
                //获取分页信息
                Page<Project> pageList = (Page<Project>)projectList;
				
                request.setAttribute("user", user);
                request.setAttribute("projectList", projectList);
				request.setAttribute("pageList", pageList);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("page", page);
				request.setAttribute("total", pageList.getTotal());
				request.setAttribute("documentaryName", sellName);
				request.setAttribute("purchaseName", purchaseName);
				
              return "project_view";
		}
		
		
		
		/**
		 * 更新上次送样日期和项目状态
		 * @Title updateProjectView 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @return JsonResult
		 */
		@ResponseBody
		@RequestMapping("/updateProjectView")
		public JsonResult updateProjectView(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
				String projectNo = request.getParameter("projectNo");
				String prevSampleDate = request.getParameter("prevSampleDate");
				String customerAttitude = request.getParameter("customerAttitude");
				Project project = new Project();
				project.setProjectNo(projectNo);
				if(StringUtils.isNotBlank(prevSampleDate)){
					project.setPrevSampleDate(DateUtil.StrToDate(prevSampleDate));
				}
				if(StringUtils.isNotBlank(customerAttitude)){
					project.setCustomerAttitude(customerAttitude);
				}
				projectService.updateProjectInfo(project);
				jsonResult.setOk(true);			
			} catch (Exception e) {
				jsonResult.setOk(false);
				jsonResult.setMessage("更新失败");
				e.printStackTrace();
			}
			
			return jsonResult;
		}
		
		
		
		/**
		 * 针对对象去去重
		 * @Title distinctByKey 
		 * @Description 
		 * @param keyExtractor
		 * @return
		 * @return Predicate<T>
		 */
		private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
		        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
		 }
		
		//导出按时完成任务项目
		@RequestMapping(value="/completeTasks")
		public void completeTasks(HttpServletRequest request,HttpServletResponse response){
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		        Calendar c = Calendar.getInstance();
		        c.setTime(new Date());
		        c.add(Calendar.MONTH, -1);
		        Date m = c.getTime();
		       
				List<Project> sampleFinishes = projectService.selectCompleteTasks(OrderStatusEnum.SAMPLE_ORDER.getCode(),m);
				List<Project> productFinishs = projectService.selectCompleteTasks(OrderStatusEnum.COMPLETE_ORDER.getCode(),m);
				
				String excelPath = ProjectStatisticsPrint.printExcel1(request, sampleFinishes, productFinishs);
				File outFile = new File(excelPath);  
				InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));  
				byte[] buffer = new byte[fis.available()];  
				fis.read(buffer);  
				fis.close();  
				// 清空response  
				response.reset();  
				// 设置response的Header  
				Date startDate = DateFormat.addMonth(new Date(), -2);
				String fileName = "按时完成项目"+DateFormat.date2String(startDate)+"~"+DateFormat.currentDate()+".xls";
				fileName = URLEncoder.encode(fileName, "utf-8");                                  //这里要用URLEncoder转下才能正确显示中文名称  
				response.addHeader("Content-Disposition", "attachment;filename=" + fileName);  
				response.addHeader("Content-Length", "" + outFile.length());  
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
				response.setContentType("application/octet-stream");  
				toClient.write(buffer);  
				toClient.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//A/B级打样阶段项目导出
		@RequestMapping(value="/proofingPhaseProject")
		public void proofingPhaseProject(HttpServletRequest request,HttpServletResponse response){
			
	        try {
				List<Project> sampleFinishes = projectService.selectProofingPhaseProject(OrderStatusEnum.NORMAL_ORDER.getCode());
				String excelPath = ProjectStatisticsPrint.printExcel1(request, sampleFinishes);
				File outFile = new File(excelPath);  
				InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));  
				byte[] buffer = new byte[fis.available()];  
				fis.read(buffer);  
				fis.close();  
				// 清空response  
				response.reset();  
				// 设置response的Header  
				Date startDate = DateFormat.addMonth(new Date(), -2);
				String fileName = "A/B级打样阶段项目导出"+DateFormat.date2String(startDate)+"~"+DateFormat.currentDate()+".xls";
				fileName = URLEncoder.encode(fileName, "utf-8");                                  //这里要用URLEncoder转下才能正确显示中文名称  
				response.addHeader("Content-Disposition", "attachment;filename=" + fileName);  
				response.addHeader("Content-Length", "" + outFile.length());  
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
				response.setContentType("application/octet-stream");  
				toClient.write(buffer);  
				toClient.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}	
		
		/**
		 * 查询检验计划是否上传
		 * @Title updateProjectView 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @return JsonResult
		 */
		@ResponseBody
		@RequestMapping("/uploadInspectionPlan")
		public JsonResult uploadInspectionPlan(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
				String projectTaskId = request.getParameter("projectTaskId");
				ProjectTask projectTask=projectTaskService.selectProjectTaskById(Integer.parseInt(projectTaskId));
				int num=productionPlanService.getInspectionPlan(projectTask.getStartTime(),projectTask.getProjectNo());
				if(num>0){
				jsonResult.setOk(true);
				}else {
					jsonResult.setOk(false);	
				}
			} catch (Exception e) {
				jsonResult.setOk(false);
				jsonResult.setMessage("更新失败");
				e.printStackTrace();
			}
			
			return jsonResult;
		}
		/**
		 * 将已完成项目修改为进行中
		 * @Title updateProjectView 
		 * @Description 
		 * @param request
		 * @param response
		 * @return
		 * @return JsonResult
		 */
		@ResponseBody
		@RequestMapping("/updateBargain")
		public JsonResult updateBargain(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
				String projectNo = request.getParameter("projectNo");
				String id = request.getParameter("id");
				String num = request.getParameter("num");
				int number=0;
				Project project1=projectService.selectProjctDetails(projectNo);
				Project project=new Project();
				project.setProjectNo(projectNo);
				if("0".equals(num)){
				project.setProjectStatus(1);
				if(project1.getSampleFinishTime()!=null){
					project.setSampleFinish(0);
					project.setSampleFinishTime(project1.getSampleFinishTime());	
				}
				if(project1.getFinishTime()!=null){
					project.setFinish(0);
					project.setFinishTime(project1.getFinishTime());
				}
				number=projectService.updateStatus(project);
				ProjectFactory factory=new ProjectFactory();
				factory.setId(Integer.parseInt(id));
				factory.setFinish(0);
				int num1=projectFactoryService.updateStatus(factory);
				}else if("1".equals(num)){
					project.setProjectStatus(1);
					
					number=projectService.updateStatus(project);	
				}else if("2".equals(num)){
				    project.setProjectStatus(4);
				    
					number=projectService.updateStatus(project);	
				}else if("3".equals(num)){
					project.setProjectStatus(5);
					
					number=projectService.updateStatus(project);	
				}
				if(number>0){
					jsonResult.setOk(true);
				}else {
					jsonResult.setOk(false);	
				}
			} catch (Exception e) {
				jsonResult.setOk(false);
				jsonResult.setMessage("更新失败");
				e.printStackTrace();
			}
			
			return jsonResult;
		}
		/**
		 * 
		 * @Title:ProjectController
		 * @Description:修改项目或合同状态
		   @author wangyang
		 * @param request
		 * @param response
		 * @return JsonResult
		 * @throws
		 */
		@ResponseBody
		@RequestMapping("/modifyState")
		public JsonResult modifyState(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
				 String userName = WebCookie.getUserName(request);
				String projectNo = request.getParameter("projectNo");
				String id = request.getParameter("id");
				ProjectFactory factory1=projectFactoryService.selectByPrimaryKey(Integer.parseInt(id));
				Project project=new Project();
				project.setProjectNo(projectNo);
                ProjectFactory factory=new ProjectFactory();
                ProjectFactory factory2=new ProjectFactory();
				factory.setId(Integer.parseInt(id));
				factory2.setId(Integer.parseInt(id));
				String rework1 = request.getParameter("rework");
				String finished1 = request.getParameter("finished");
				String finished="";
				String rework="";
				if("0".equals(finished1)){
					finished=rework1;
				}else if("1".equals(finished1)){
					rework=rework1;
				} 
				Date date=new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
				if(finished!=null&&!"".equalsIgnoreCase(finished)){
				if("1".equalsIgnoreCase(finished)){
					factory.setRework(factory1.getRework());
					factory.setFinish(1);
					if(factory1.getSampleFinishTime()==null){
					factory.setSampleDate(new Date());
					}
					if(factory1.getDeliveryDate()!=null){
					factory.setDeliveryDate(new Date());
					}
					if(factory1.getModificationRecord()!=null){
					String ModificationRecord=factory1.getModificationRecord()+";"+ userName +"于"+sdf.format(date)+"修改合同状态为完成";
					factory.setModificationRecord(ModificationRecord);
					}else{
						String ModificationRecord= userName +"于"+sdf.format(date)+"修改合同状态为完成";
						factory.setModificationRecord(ModificationRecord);	
					}
					factory.setRework(2);
					int num1=projectFactoryService.updateStatus(factory);
					//project.setFinish(1);
					int count=projectFactoryService.getCompletedCount(project);//获取项目未完工合同数
					if(count==0){
						Project project1=projectService.selectProjctDetails(projectNo);
						if(factory1.getSampleDate()!=null&&factory1.getDeliveryDate()!=null){
						project.setProjectStatus(2);
						project.setSampleFinish(1);
						if(project1.getSampleFinishTime()==null){
						project.setSampleFinishTime(new Date());
						}
						project.setFinish(1);
						project.setFinishTime(new Date());
						}else if(factory1.getDeliveryDate()!=null){
						project.setProjectStatus(2);
						project.setFinish(1);
						project.setFinishTime(new Date());
						}else if(factory1.getSampleDate()!=null){
							project.setProjectStatus(6);
							project.setSampleFinish(1);
							project.setSampleFinishTime(new Date());
						}
						
						projectService.updateStatus(project);
					}
					
				}else if("0".equalsIgnoreCase(finished)){
					project.setProjectStatus(1);
					factory.setFinish(0);
					if(factory1.getModificationRecord()!=null){
					String ModificationRecord=factory1.getModificationRecord()+";"+ userName +"于"+sdf.format(date)+"修改合同状态为进行中";
					factory.setModificationRecord(ModificationRecord);
					}else{
					String ModificationRecord= userName +"于"+sdf.format(date)+"修改合同状态为进行中";
					factory.setModificationRecord(ModificationRecord);	
					}
					factory.setRework(2);
					int num=projectService.updateStatus(project);
					int num1=projectFactoryService.updateStatus(factory);
				}
				}
				if(rework!=null&&!"".equalsIgnoreCase(rework)){
					if("1".equalsIgnoreCase(rework)){
						factory2.setRework(1);
						factory2.setFinish(2);
						int num1=projectFactoryService.updateStatus(factory2);
					}else if("0".equalsIgnoreCase(rework)){
						factory2.setRework(0);
						factory2.setFinish(2);
						int num1=projectFactoryService.updateStatus(factory2);
					}
				}
				
				
				
				jsonResult.setOk(true);
				
			} catch (Exception e) {
				jsonResult.setOk(false);
				jsonResult.setMessage("更新失败");
				e.printStackTrace();
			}
			
			return jsonResult;
		}
		
		/**
		 * 获取投诉上传内容
		 * @Title upload 
		 * @Description
		 * @param
		 * @param request
		 * @param model
		 * @return
		 * @throws IllegalStateException
		 * @throws IOException
		 * @return String
		 */
		@ResponseBody
	    @RequestMapping(value = "/uploadInterpretationDocument")  
	    public JsonResult uploadInterpretationDocument(HttpServletRequest request, ModelMap model){  
	        
		    JsonResult jsonResult = new JsonResult();
			try {      
				String projectNo = request.getParameter("projectNo");
				//获取相对路径
				String path = UploadAndDownloadPathUtil.getProductImg() + File.separator + projectNo + File.separator;
				File dirFile = new File(path);  
				if(!dirFile.exists()){
					dirFile.mkdirs();
				}	
			
				Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_file(request, path,projectNo);
				//原始名称  
				String originalFilename = "";
				String newFileName = "";			
				if (multiFileUpload != null && multiFileUpload.size() > 0) {
					 Set<String> keySet = multiFileUpload.keySet();
					   for (String key : keySet) {	
						   newFileName = multiFileUpload.get(key);  
						   originalFilename = key;				        
					   }
				}			
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("path", path+newFileName);
				map.put("originalFilename", originalFilename);
				map.put("newFileName", newFileName);
				jsonResult.setData(map);
				jsonResult.setOk(true);
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.setMessage("上传失败");
				jsonResult.setOk(false);
			}
	        return jsonResult;
	        
	    }
		/**
		 * 
		 * @Title:ProjectController
		 * @Description:修改延期文件
		   @author wangyang
		 * @param request
		 * @param response
		 * @return JsonResult
		 * @throws
		 */
		@ResponseBody
		@RequestMapping("/updateExtensionDocument")
		public JsonResult updateExtensionDocument(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult = new JsonResult();
			try {
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String projectNo = request.getParameter("projectNo");
			    String fileName = request.getParameter("fileName");
				String dateDelivery = request.getParameter("dateDelivery");
				String delayDays = request.getParameter("delayDays");
				String explain = request.getParameter("explain");
				String lateDeliveryDate = request.getParameter("lateDeliveryDate");
				Project project1=projectService.selectProjectByProjectNo(projectNo);
				project1.setProjectNo(projectNo);
				if(fileName!=null&&!"".equalsIgnoreCase(fileName)){
					project1.setInterpretationDocument(fileName);
				}
				if(dateDelivery!=null&&!"".equalsIgnoreCase(dateDelivery)){
					project1.setDateDelivery(format.parse(dateDelivery));
				}
				if(explain!=null&&!"".equalsIgnoreCase(explain)){
					project1.setExplain(explain);;
				}
				if(delayDays!=null&&!"".equals(delayDays)){
					project1.setDelayDay(Integer.parseInt(delayDays));
					Date dateSample=project1.getOriginalSampleScheduledDate();
					Date completionTime=project1.getOriginalDeliveryDate();
					if(dateSample!=null){
					Calendar c = Calendar.getInstance();
				    c.setTime(dateSample);
				    c.add(Calendar.HOUR_OF_DAY,Integer.parseInt(delayDays));
				    Date newDate = c.getTime();
				    project1.setDelayedDeliveryDate(newDate);
					}
					if(completionTime!=null){
						Date newDate = addDate(completionTime, Integer.parseInt(delayDays));
					    project1.setCargoDelayedDeliveryDate(newDate);;
						}
				}
				if(lateDeliveryDate!=null&&!"".equalsIgnoreCase(lateDeliveryDate)){
					  SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");         
					  Date date = null;    
					  try {    
					           date = format1.parse(lateDeliveryDate);   
					  } catch (ParseException e) {    
					             e.printStackTrace();    
					  }   
					project1.setLateDeliveryDate(date);
				}
				projectService.updateProjectInfo(project1);
				jsonResult.setOk(true);			
			} catch (Exception e) {
				jsonResult.setOk(false);
				jsonResult.setMessage("更新失败");
				e.printStackTrace();
			}
			
			return jsonResult;
		}
		 public static Date addDate(Date date,long day) {
		        long time = date.getTime(); // 得到指定日期的毫秒数
		        day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		        time+=day; // 相加得到新的毫秒数
		        return new Date(time); // 将毫秒数转换成日期
		    }
		 /**
		  * 
		  * @Title:ProjectController
		  * @Description:获取延期信息
		    @author wangyang
		  * @param request
		  * @param response
		  * @return JsonResult
		  * @throws
		  */
		   @ResponseBody
			@RequestMapping("/delayMessage")
			public JsonResult delayMessage(HttpServletRequest request,HttpServletResponse response) {
				JsonResult jsonResult = new JsonResult();
				String projectNo = request.getParameter("projectNo");
				Project project = projectService.selectProjctDetails(projectNo);
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("dateDelivery", project.getDateDelivery());
		        map.put("delayDay", project.getDelayDay());
		        if(project.getExplain()!=null&&!"".equalsIgnoreCase(project.getExplain())){
		        map.put("explain", project.getExplain());
		        }else{
		        	map.put("explain", "");	
		        }
		        if(project.getInterpretationDocument()!=null&&!"".equalsIgnoreCase(project.getInterpretationDocument())){
		        	map.put("interpretationDocument", project.getInterpretationDocument());
			        }else{
			        map.put("interpretationDocument", "");	
			        }
		        
		        jsonResult.setOk(true);
		        jsonResult.setData(map);
		        return jsonResult;
			}
		   
		   /**
		    * 
		    * @Title:ProjectController
		    * @Description:生产工厂及项目列表
		      @author wangyang
		    * @param request
		    * @param response
		    * @return String
		    * @throws
		    */
		   @RequestMapping("/productingProjectDetail")
			public String productingProjectDetail(HttpServletRequest request,HttpServletResponse response) {
			   try {
					String pageStr = request.getParameter("pageStr"); 
					String inputKey = request.getParameter("inputKey");  // 搜索词
					String purchaseName = request.getParameter("purchaseName");
					String qualityName = request.getParameter("qualityName");
					String pageSizeStr = request.getParameter("pageSize");
					//根据时间筛选
					String queryDateStr = request.getParameter("queryDate");
					//排序
					String sortNumStr = request.getParameter("sortNum");
					//排序(上下)
					String upOrDownStr = request.getParameter("upOrDown");
		
					
					
					Integer roleNo = null;                               // 判断是管理员，销售，采购
					String userName = WebCookie.getUserName(request);
					if(StringUtils.isNotBlank(userName)){
						User user = userService.findUserByName(userName);
						roleNo = user.getRoleNo();
					}else{
						return "redirect:/index.jsp";
					}
					
					
					//页数
					Integer page = null;
					if(StringUtils.isNotBlank(pageStr)){
						page = Integer.parseInt(pageStr);
					}else{
						page = 1;
					}
					//每页显示数
					Integer pageSize = null;
					if(StringUtils.isNotBlank(pageSizeStr)){
						pageSize = Integer.parseInt(pageSizeStr);
					}else{
						pageSize = 50;
					}
					ProjectFactoryQuery projectFactoryQuery = new ProjectFactoryQuery();
					projectFactoryQuery.setInputKey(inputKey);
					projectFactoryQuery.setPageNumber(pageSize * (page - 1));
					projectFactoryQuery.setPageSize(pageSize);
					projectFactoryQuery.setRoleNo(roleNo);
					projectFactoryQuery.setQualityName(qualityName);
					projectFactoryQuery.setPurchaseName(purchaseName);
					Integer queryDate = null;
					if(StringUtils.isNotBlank(queryDateStr)){
						queryDate = Integer.parseInt(queryDateStr);
						projectFactoryQuery.setQueryDate(queryDate);
					}else{
						queryDate= 2;
						projectFactoryQuery.setQueryDate(2);
					}
					if(StringUtils.isNotBlank(sortNumStr)){
						projectFactoryQuery.setSortNum(Integer.parseInt(sortNumStr));
					}else{
						projectFactoryQuery.setSortNum(null);
					}
					if(StringUtils.isNotBlank(upOrDownStr)){
						projectFactoryQuery.setUpOrDown(Integer.parseInt(upOrDownStr));
					}else{
						projectFactoryQuery.setUpOrDown(0);
					}
					List<ProjectFactory> factoryList = projectFactoryService.selectFactoryList(projectFactoryQuery);
					int factoryListCount = projectFactoryService.selectFactoryListCount(projectFactoryQuery);
				    request.setAttribute("factoryList", factoryList);				
					request.setAttribute("inputKey", inputKey);
					request.setAttribute("pageSize", pageSize);
					request.setAttribute("page", page);
					request.setAttribute("userName", userName);
					request.setAttribute("qualityName", qualityName);
					request.setAttribute("purchaseName", purchaseName);
					request.setAttribute("count", factoryListCount);
					request.setAttribute("queryDate", queryDate);
					request.setAttribute("sortNum", sortNumStr);
					request.setAttribute("upOrDown", upOrDownStr);
					//计算尾页
					Integer lastNum = new BigDecimal(factoryListCount).divide(new BigDecimal(pageSize)).setScale(0,BigDecimal.ROUND_UP).intValue();
					request.setAttribute("lastNum", lastNum);
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
					LOG.error("productingProjectDetail error", e);
				}
				
				return "producting_project_detail";
			}
		   /**
		    * 
		    * @Title:ProjectController
		    * @Description:生产工厂及项目列表详情页
		      @author wangyang
		    * @param request
		    * @param response
		    * @return String
		    * @throws
		    */
		   @RequestMapping("/productingProject")
			public String productingProject(HttpServletRequest request,HttpServletResponse response) {
				try {
					String factoryName = request.getParameter("factoryName");
					String pageStr = request.getParameter("pageStr"); 
					
					String pageSizeStr = request.getParameter("pageSize");
					//页数
					Integer page = null;
					if(StringUtils.isNotBlank(pageStr)){
						page = Integer.parseInt(pageStr);
					}else{
						page = 1;
					}
					//每页显示数
					Integer pageSize = null;
					if(StringUtils.isNotBlank(pageSizeStr)){
						pageSize = Integer.parseInt(pageSizeStr);
					}else{
						pageSize = 50;
					}
					Project project=new Project();
					project.setFactoryName(factoryName);
					project.setPageNumber(pageSize * (page - 1));
					project.setPageSize(pageSize);
				    List<Project>projectList=projectService.getProductingProject(project);
				    for(int i=0;i<projectList.size();i++){
				    	String status="";
				    	Project project1=projectList.get(i);
				    	Project project2 = projectService.selectProjctDetails(project1.getProjectNo());
				    	project1.setProjectMembers(project2.getSellName()+"/"+project2.getPurchaseName()+"/"+project2.getZhijian1()+"/"+project2.getZhijian2());
				    	if(project1.getProjectStatus() != OrderStatusEnum.NEW_ORDER.getCode() && project1.getProjectStatus() != OrderStatusEnum.CANCEL_ORDER.getCode() && project1.getProjectStatus() != OrderStatusEnum.PAUSE_ORDER.getCode()){
		             		Map<String, Object> map = judgeDelay(project2);
		             		Boolean judgeDelay = (Boolean)map.get("delayType");
		             		int delayDays = Integer.parseInt(map.get("delayDays").toString());
		             		project1.setDelayDays(delayDays);
		            		if(judgeDelay == true){
		            			status = "有延期";
		            			project1.setStatus(status);
		                	}
						 } 
				    	ProjectTask task=new ProjectTask();
				    	task.setTaskStatus("0");
				    	task.setTaskType("2");
				    	task.setProjectNo(project1.getProjectNo());
				    	int incompleteInspectionTasks=projectTaskService.getInspectionTaskNotCompleted(task);//根据项目号查询未完成检验任务
				    	project1.setIncompleteInspectionTasks(incompleteInspectionTasks);
				    	QualityReport report=new QualityReport();
				    	report.setProjectNo(project1.getProjectNo());
				    	int inspectionReport=qrService.getReport(report);
				    	project1.setRecentInspectionReport(inspectionReport);
				    	ProjectFactory factory=new ProjectFactory();
				    	factory.setProjectNo(project1.getProjectNo());
				    	factory.setFactoryName(project1.getFactoryName());
				    	int contractNumber=projectFactoryService.getFactory(factory);
				    	project1.setContractNumber(contractNumber);
				    }
				    int projectCount=projectService.getProductingProjectCount(project);
				    Integer lastNum = new BigDecimal(projectCount).divide(new BigDecimal(pageSize)).setScale(0,BigDecimal.ROUND_UP).intValue();
					request.setAttribute("lastNum", lastNum);
					request.setAttribute("page", page);
				    request.setAttribute("pageSize", pageSize);
				    request.setAttribute("projectList", projectList);
					request.setAttribute("factoryName", factoryName);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					LOG.error("productingProject error", e);
				}
				
				return "producting_project";
			}
		   
		   
		   
		   /**
			 * 修改预计交期
			 * @Title updateProjectSchedule 
			 * @Description 
			 * @param request
			 * @param response
			 * @return
			 * @throws IOException
			 * @return JsonResult
			 */
			@RequestMapping("/updateDeliveryDateModification")
			@ResponseBody
			public JsonResult updateDeliveryDateModification(HttpServletRequest request,HttpServletResponse response) throws IOException {
				JsonResult jsonResult = new JsonResult();
				try {
					String projectNo = request.getParameter("projectNo");
					String num = request.getParameter("num");
					String time = request.getParameter("time");
					Project project = new Project();
					if("1".equalsIgnoreCase(num)){
					project.setSampleDeliveryDate(time);
					}else if("2".equalsIgnoreCase(num)){
						project.setDeliveryTime(time);	
					}
					project.setProjectNo(projectNo);
					projectService.updateByPrimaryKeySelective(project);
					jsonResult.setOk(true);
					jsonResult.setMessage("更新成功");
					return jsonResult;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					jsonResult.setOk(false);
					jsonResult.setMessage(e.getMessage());
					LOG.error("error", e);
					return jsonResult;
				}
			}
			
			
			@ResponseBody
			   @RequestMapping("/SaveDataToERP")
				public CommonResult SaveDataToERP(HttpServletRequest request,HttpServletResponse response) {
				   CommonResult res = new CommonResult(request.getRequestURI());
				  try{
					  Calendar calendar =  Calendar.getInstance();
					  int hour = calendar.get(Calendar.HOUR_OF_DAY);
                      if(hour==1){
                          getAllBargain();
                      }
                      List<PayOthers>payOtherList=payOtherService.getAllPending();
					  for(int i=0;i<payOtherList.size();i++){
						 int num= itemCaseERPService.findName(payOtherList.get(i).getCaseno(),payOtherList.get(i).getApplicant(),1);//查看成员是否在ERP成员列表中
						 int num1=0;
						 if("差旅费".equalsIgnoreCase(payOtherList.get(i).getPaymentType())){
						 	num1=1;
						 }else {
							 num1 = itemCaseERPService.findName(payOtherList.get(i).getCaseno(), payOtherList.get(i).getApplicant(), 2);//查看成员是否在ERP成员列表中
						 }
						 if(num==0){
							 payOtherService.updatePassERP(payOtherList.get(i).getId(),3);
						 }else if(num1==0){
							 payOtherService.updatePassERP(payOtherList.get(i).getId(),2);
							 // 发送消息给钉钉
							 DingTalkModel model = new DingTalkModel("", null, payOtherList.get(i).getProcessInstanceId(),
									 "申请成员不是项目组成员", payOtherList.get(i).getCaseno(), null);
							 RpcQualityNoticeToKuai.sendRequest(model);
						 }else{
							if("agree".equalsIgnoreCase(payOtherList.get(i).getProcessInstanceResult())) {
                                FactoryFund fac = new FactoryFund();
                                fac.setCaseno(payOtherList.get(i).getCaseno());
                                fac.setName(payOtherList.get(i).getApplicant());
                                fac.setMoney(payOtherList.get(i).getApplicationAmount());
                                fac.setMoneydate(payOtherList.get(i).getMailingDate());
                                fac.setMoneytype(payOtherList.get(i).getImoneytype());
                                fac.setPayDepartment(payOtherList.get(i).getPaymentDepartment());
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                String dateString = formatter.format(payOtherList.get(i).getMailingDate());
                                String content = "该笔款项为：" + payOtherList.get(i).getPaymentType();
                                content = " " + content + " " + payOtherList.get(i).getApplicant() + " " + payOtherList.get(i).getPaymentInstructions() + " " + dateString;
                                fac.setContent(content);
                                fac.setState("<font color=green>已完成款项</font>");
                                String apNumber = itemCaseERPService.getApNumber();//获取apnumber
                                fac.setApNumber(apNumber);
                                factoryFundService.insertAll(fac);
                                itemCaseERPService.insert(apNumber);
                                payOtherService.updatePassERP(payOtherList.get(i).getId(), 1);
                            }
							
						 }
						  
					  }
					  
					  
					  List<Project>projectList=projectService.getReturnItem();
						for(int i=0;i<projectList.size();i++){
							Project project1=projectList.get(i);
							String projectNo=project1.getProjectNo();
							String []projectNo1=projectNo.split("-");
				         	Project project=new Project();
				         	project.setProjectNo(projectNo);
				         	project.setProjectName(projectNo1[0]);
				         	Project project2=projectService.getzhijian(project);
				         	if(project2!=null){
				         	String zhijain1=project2.getZhijian1();
				         	String zhijain2=project2.getZhijian2();
				         	String zhijain3=project2.getZhijian3();
				         	String zhijian="";
				         	if(zhijain1!=null&&!"".equals(zhijain1)){
				         		zhijian+=","+zhijain1;
				         	}
				         	if(zhijain2!=null&&!"".equals(zhijain2)){
				         		zhijian+=","+zhijain2;
				         	}
				         	if(zhijain3!=null&&!"".equals(zhijain3)){
				         		zhijian+=","+zhijain3;
				         	}
				         	
				         	zhijian=zhijian.replaceFirst(",", "");
				         	project.setHistoryInspection(zhijian);
				         	projectService.updateHistoryInspection(project);
				         	}
						}
					  ComplaintList complist1=complaintListService.getLate();
						ProjectComplaint complaint1=new ProjectComplaint();
						if(complist1!=null){
							complaint1.setId(complist1.getComplaintId());
						}else{
							complaint1.setId(0);
						}
						List<ComplaintList>list1=new ArrayList<ComplaintList>();
						List<ProjectComplaint>Complaintlist=projectComplaintService.getAll(complaint1);
						 if(Complaintlist.size()>0){
						for(int i=0;i<Complaintlist.size();i++){
							ComplaintList complaint2=new ComplaintList();
							complaint2.setComplaintId(Complaintlist.get(i).getId());
							complaint2.setCaseNo(Complaintlist.get(i).getProjectNo());
							complaint2.setComplaintState(Complaintlist.get(i).getSeriousLevel().toString());
							list1.add(complaint2);
						}
						complaintListService.batchAddInspectionReport(list1);
						} 
					 int num=0;
				   List<QuotePrice1>quoteList=new ArrayList<QuotePrice1>();
					 QuotePrice1 price=quotePriceService.getLate();
					 int num1=0;
					 if(price!=null){
						 num1= price.getKuzhizao();
					 }
					 List<QuoteWeeklyReport>list=quoteWeeklyReportService.getAll(num1);
					 for(int i=0;i<list.size();i++){
						 QuotePrice1 price1=new QuotePrice1();
						 price1.setCaseNo(list.get(i).getCsgOrderId());
						 price1.setEmployeeName("Emily"); 
						 price1.setKuzhizao(list.get(i).getId());
						 price1.setCurrentStatus("开工视频和料图");
						 price1.setUploadUrl("https://www.kuaizhizao.cn"+list.get(i).getPhotoPathCompress());
						 quoteList.add(price1);
					 }
					 if(quoteList.size()>0){
						 quotePriceService.addAll(quoteList); 
					 }
					List<ProjectComplaint>complaintlist=projectComplaintService.unprocessedItems();//查询投诉未处理项目
					itemCaseERPService.update("");
					 for(int j=0;j<complaintlist.size();j++){
						 ProjectComplaint complaint=complaintlist.get(j);
						 int total=itemCaseERPService.update(complaint.getProjectNo());
					 }
					 num=1;
					 QuotePrice1 price1=quotePriceService.getLateOne();
					 int num2=0;
					 if(price1!=null){
						 num2= price1.getDingding();
					 }
					List<QuotePrice> quotePriceList=tquotePriceService.getall(num2);
					List<QuotePrice1>quotePriceList1=new ArrayList<QuotePrice1>();
					for(int i=0;i<quotePriceList.size();i++){
						QuotePrice quote=quotePriceList.get(i);
						if(quote.getUploadurl()!=null&&!"".equalsIgnoreCase(quote.getUploadurl())){
							String[]url=quote.getUploadurl().split(",");
							for(int j=0;j<url.length;j++){
								QuotePrice1 quote1=new QuotePrice1();
								quote1.setCaseNo(quote.getProjectNo());
								quote1.setCurrentStatus(quote.getCurrentStatus());
								quote1.setDingding(quote.getId());
								quote1.setUploadUrl(url[j]);
								quote1.setEmployeeName(quote.getEmployeName());
								quotePriceList1.add(quote1);	
							}
						}else{
							QuotePrice1 quote1=new QuotePrice1();
							quote1.setCaseNo(quote.getProjectNo());
							quote1.setCurrentStatus(quote.getCurrentStatus());
							quote1.setDingding(quote.getId());
							quote1.setEmployeeName(quote.getEmployeName());
							quotePriceList1.add(quote1);
						}
					}
					// 每次不超过30个数据插入
                      if (quotePriceList1.size() > 0) {
                          int size = quotePriceList1.size();
                          int cicle = size % 30 > 0 ? size / 30 + 1 : size / 30;
                          for (int i = 1; i <= cicle; i++) {
                              int limitSize = Math.min(i * 30, size);
                              List<QuotePrice1> price1List = quotePriceList1.stream().skip((i - 1) * 30).limit(limitSize).collect(Collectors.toList());
                              quotePriceService.addAll1(price1List);
                              price1List.clear();
                          }
                      }
					
					res.setCode(200);
			        return res;
			        } catch (Exception var14) {
				  	var14.printStackTrace();
				  	LOG.error("SaveDataToERP error,", var14);
			            res.failed(request.getRequestURI() + " error " + var14.toString());
			            return res;
			        }	
				}

    @ResponseBody
    @RequestMapping("/getAllBargain")
    private void getAllBargain() {
			    try {
                    List<Bargain> bargainList = bargainService.getAll();//获取最近50条上传合同记录
                    for (int i = 0; i < bargainList.size(); i++) {
                        ProjectFactory factory1 = new ProjectFactory();
                        factory1.setContractNo(bargainList.get(i).getBargainNo());
                        factory1.setProjectNo(bargainList.get(i).getCaseNo());
                        ProjectFactory factory = projectFactoryService.getAllBargain(factory1);
                        if (factory != null) {
                        } else {
                            sysnByProjectErp(bargainList.get(i).getCaseNo());
                        }
                    }

					List<Project>projectList=projectService.getAllMaterial();
					for(int i = 0; i < projectList.size(); i++){
						sysnByProjectErp(projectList.get(i).getProjectNo());
					}

                }catch(Exception e){

                }



    }


    //导出正在进行中项目任务项目
	@RequestMapping(value="/projectExportProgress")
	public void projectExportProgress(HttpServletRequest request,HttpServletResponse response){
		try {
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			ProjectERP projectERP=new ProjectERP();
			projectERP.setStartTime(startTime);
			projectERP.setEndTime(endTime);
			List<ProjectERP>projectErpList=itemCaseERPService.getProjectExportProgress(projectERP);//获取起始时间到截止时间付款工厂信息



            //获取进行中项目列表
			//List<Project> allProjectExport = projectService.selectProjectExport(m);

			String excelPath = ProjectStatisticsPrint.printProjectExportProgress(request, projectErpList);
			File outFile = new File(excelPath);
			InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header

			String fileName = "起始时间:"+startTime+"至"+endTime+"项目付款信息.xls";
			fileName = URLEncoder.encode(fileName, "utf-8");                                  //这里要用URLEncoder转下才能正确显示中文名称
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Content-Length", "" + outFile.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//导出正在进行中项目任务项目
	@RequestMapping(value="/ongoingProjects")
	public void ongoingProjects(HttpServletRequest request,HttpServletResponse response){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			//c.add(Calendar.DATE, -1);
			Date m = c.getTime();
            String time=format.format(m);

            System.setProperty ("java.awt.headless", "true");
			//获取进行中项目列表
			List<Project> allProjectExport = projectService.selectProjectExport(time);


			if(CollectionUtils.isNotEmpty(allProjectExport)){
				List<String> itemList = new ArrayList<>();

				for(Project project : allProjectExport){
					itemList.add(project.getProjectNo());
				}
				List<InvoiceInfo> invoiceInfos = invoiceInfoService.getPayDate(itemList);

				Map<String,InvoiceInfo>  invoiceInfoMap = new HashMap<>();
				invoiceInfos.forEach(e-> invoiceInfoMap.put(e.getCaseNo(),e));
				invoiceInfos.clear();

				allProjectExport.forEach(e->{
					if(invoiceInfoMap.containsKey(e.getProjectNo())){
						e.setIfDate(invoiceInfoMap.get(e.getProjectNo()).getIfdate());
					}
				});

				invoiceInfoMap.clear();

			}

			String excelPath = ProjectStatisticsPrint.printOngoingProjects(request, allProjectExport);
			File outFile = new File(excelPath);
			InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header

			String fileName = "截止到"+DateFormat.date2String(m)+"之前,在进行中项目.xls";
			fileName = URLEncoder.encode(fileName, "utf-8");                                  //这里要用URLEncoder转下才能正确显示中文名称
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Content-Length", "" + outFile.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			LOG.error("ongoingProjects", e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			LOG.error("ongoingProjects", e);
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("ongoingProjects", e);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ongoingProjects", e);
		}
	}

	@RequestMapping("/selectAll")
	@ResponseBody
	public JsonResult selectAll(HttpServletRequest request,
							   HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		try{

			//登录保存cookie
			String name = WebCookie.getUserName(request);
			if(org.apache.commons.lang.StringUtils.isNotBlank(name)) {
				User user = userService.findUserByName(name);
                int roleNo=user.getRoleNo();
				String userId=user.getId()+"";

				Map<String, Object> hashMap = new HashMap<String, Object>();
				Integer noFinishCount = 0;
				ProjectTask projectTask = new ProjectTask();
				projectTask.setUserName(name);
				projectTask.setTaskStatus("0");
				projectTask.setSendOrReceive(2);
				List<ProjectTask> projectTaskNoFinish = projectTaskService.selectProjectTaskCount(projectTask);
				if (projectTaskNoFinish != null) {
					noFinishCount = projectTaskNoFinish.size();
				}
				//查询未完成投诉数量
				ProjectComplaintQuery projectComplaintQuery = new ProjectComplaintQuery();
				projectComplaintQuery.setIsSolve(UNSOLVE);
				projectComplaintQuery.setRoleNo(roleNo);
				projectComplaintQuery.setUserId(userId);
				projectComplaintQuery.setZhijian1(name);
				projectComplaintQuery.setZhijian2(name);
				projectComplaintQuery.setZhijian3(name);

				int unFinishComplaintCount = projectComplaintService.queryCount(projectComplaintQuery);
				//查询出货单未填写数量
				ShippingConfirmationQuery shippingConfirmationQuery = new ShippingConfirmationQuery();
				shippingConfirmationQuery.setRoleNo(roleNo);
				shippingConfirmationQuery.setIsComplete(0);
				shippingConfirmationQuery.setUserName(name);
				int unFinishShippingCount = shippingConfirmationService.count(shippingConfirmationQuery);
				//查询质检报告数量
				QualityReportQuery qualityReportQuery = new QualityReportQuery();
				qualityReportQuery.setRoleNo(roleNo);
				qualityReportQuery.setUserName(name);
				qualityReportQuery.setIsComment(1); //查询未评论数量
				int qualityCount = qualityReportService.totalReports(qualityReportQuery);


				hashMap.put("noFinishCount", noFinishCount);
				hashMap.put("unFinishComplaintCount", unFinishComplaintCount);
				hashMap.put("unFinishShippingCount", unFinishShippingCount);
				hashMap.put("qualityCount", qualityCount);
				jsonResult.setData(hashMap);
				jsonResult.setOk(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMessage(e.getMessage());
			jsonResult.setOk(false);
			LOG.error("error", e);
		} finally {
			return jsonResult;
		}

	}

	/**
	 * 同步ERP项目
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */

	public JsonResult sysnByProjectErp(String projectNo) throws Exception{
		JsonResult jsonResult = new JsonResult();
		ProjectFactory projectFactory = new ProjectFactory();
		ProjectERP projectErp=itemCaseERPService.selectByCaseNo(projectNo);
		Project project = projectService.selectProjctDetails(projectNo);
		if(projectErp != null && project != null){
			//项目不为空时，更新项目
			if(project != null){
				project.setProjectName(projectErp.getProjectNameC());
				project.setActualStartDate(projectErp.getDateSampleUploading());  //开工日期
				project.setSampleScheduledDate(projectErp.getDateSample());
				project.setOriginalSampleScheduledDate(projectErp.getDateSample());
				project.setCompanyName(projectErp.getCompanyName());
				project.setZhijian1(projectErp.getZhijian1());
				project.setZhijian2(projectErp.getZhijian2());
				project.setZhijian3(projectErp.getZhijian3());
				project.setPlantAnalysis(projectErp.getPlantAnalysis());
				project.setProjectMaterialProperties(projectErp.getProjectMaterialProperties());
				project.setMoneyDate(projectErp.getMoneyDate());
				project.setCustomerName(projectErp.getCustomerName());
				project.setProjectNameEn(projectErp.getProjectNameE());
				project.setCustomerGrade(projectErp.getCustomerGrade());

				//默认给样品交期，如果存在大货，保存大货交期
				//录入合同日期
				projectFactory.setSampleDate(projectErp.getDateSample());
				projectFactory.setContractDate(projectErp.getDateSampleUploading());

				//当开工时间为空时不进行插入
				if(projectErp.getDateSampleUploading() != null){
					project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());  //初始导入正常进行项目
				}
				//大货交期
				if(projectErp.getCompletionTime() != null){

					//如果新合同交期小于原交期，则不更新，反之则更新交期
					if(project.getDeliveryDate() != null && projectErp.getCompletionTime().getTime() < project.getDeliveryDate().getTime()){

					}else{
						project.setDeliveryDate(projectErp.getCompletionTime());
						project.setOriginalDeliveryDate(projectErp.getCompletionTime());
					}

					//工厂项目表保存交期和合同日期
					projectFactory.setDeliveryDate(projectErp.getCompletionTime());
				}
				User user=new User();
				User purchaseUser =new User();
				//1.MerchandManager1 跟单销售,MerchandManager2 采购
				//如果存在成熟跟单，保存成熟跟单，不存在，保存原始跟单
				//Merchandising 成熟跟单
				if(StringUtils.isNotBlank(projectErp.getMerchandising())){
					user=userService.selectUserByName(projectErp.getMerchandising());
					if(user!=null){
						project.setEmailUserId(user.getId());
					}
				}else{
					if(StringUtils.isNotBlank(projectErp.getMerchandManager1())){
						user=userService.selectUserByName(projectErp.getMerchandManager1());
						if(user!=null){
							project.setEmailUserId(user.getId());
						}
					}
				}

				if(StringUtils.isNotBlank(projectErp.getCustomerManager())){
					user=userService.selectUserByName(projectErp.getCustomerManager());
					if(user!=null){
						project.setSaleId(user.getId());
					}
				}



				//如果存在成熟采购，保存成熟采购，不存在，保存原始采购
				if(StringUtils.isNotBlank(projectErp.getMaturePurchase())){
					purchaseUser=userService.selectUserByName(projectErp.getMaturePurchase());
					if(purchaseUser!=null){
						project.setPurchaseId(purchaseUser.getId());
					}
				}else{
					if(StringUtils.isNotBlank(projectErp.getMerchandManager2())){
						purchaseUser=userService.selectUserByName(projectErp.getMerchandManager2());
						if(purchaseUser!=null){
							project.setPurchaseId(purchaseUser.getId());
						}
					}
				}


				//保存下单工厂
				projectFactory.setFactoryId(projectErp.getFactoryId());
				projectFactory.setFactoryName(projectErp.getCompanyName());
				projectFactory.setProjectNo(projectNo);
				projectFactory.setCity(projectErp.getCity());
				projectFactory.setContractNo(projectErp.getContractNo());
				if(projectErp.getSupplementaryContract() != null){
					projectFactory.setOrderNature(projectErp.getSupplementaryContract() == 1 ? 2 : 1); //补货或者正常
				}else{
					projectFactory.setOrderNature(1);
				}

				projectFactoryService.insertSelective(projectFactory);

				projectService.updateProjectInfo(project);
			}

			return jsonResult;
		}else{
			project = new Project();
			User user=new User();
			User purchaseUser =new User();
			//1.MerchandManager1 跟单销售,MerchandManager2 采购
			if(StringUtils.isNotBlank(projectErp.getMerchandManager1())){
				user=userService.selectUserByName(projectErp.getMerchandManager1());
				if(user!=null){
					project.setEmailUserId(user.getId());
				}
			}
			if(StringUtils.isNotBlank(projectErp.getMerchandManager2())){
				purchaseUser=userService.selectUserByName(projectErp.getMerchandManager2());
				if(purchaseUser!=null){
					project.setPurchaseId(purchaseUser.getId());
				}
			}
			project.setId(IdGen.uuid());
			project.setProjectNo(projectNo);
			project.setProjectName(projectErp.getProjectNameC());
			project.setProjectNameEn(projectErp.getProjectNameE());
			project.setDeliveryDate(projectErp.getCompletionTime()); //交期
			project.setOriginalDeliveryDate(projectErp.getCompletionTime());   //交期
			project.setDeliveryStatus(0);
			project.setWarningStatus(0);
			project.setImportance(0);
			project.setFinish(0);
			project.setSampleFinish(0);
			project.setStage(0);
			project.setPoDate(projectErp.getPoDate());  //PO日期
			project.setActualStartDate(projectErp.getDateSampleUploading());  //开工日期
			project.setScheduledDate(null);
			project.setSampleScheduledDate(projectErp.getDateSample());
			project.setOriginalSampleScheduledDate(projectErp.getDateSample());
			project.setCompanyName(projectErp.getCompanyName());
			project.setCreateDate(new Date());
			project.setPlantAnalysis(projectErp.getPlantAnalysis());
			project.setDetailStatus(0);
			project.setSampleFinishTime(null);
			project.setZhijian1(projectErp.getZhijian1());
			project.setZhijian2(projectErp.getZhijian2());
			project.setZhijian3(projectErp.getZhijian3());
			project.setCustomerName(projectErp.getCustomerName());
			project.setDateSampleUploading(projectErp.getDateSampleUploading());
			project.setCustomerGrade(projectErp.getCustomerGrade());

			//默认给样品交期，如果存在大货，保存大货交期
			//录入合同日期
			projectFactory.setSampleDate(projectErp.getDateSample());
			projectFactory.setContractDate(projectErp.getDateSampleUploading());


			project.setProjectMaterialProperties(projectErp.getProjectMaterialProperties());
			if(projectErp.getDateSample() != null){
				project.setDateSample(projectErp.getDateSample());
			}
			if(projectErp.getCompletionTime()!= null){
				project.setCompletionTime(projectErp.getCompletionTime());
			}
			if(projectErp.getMoneyDate()!=null){
				project.setMoneyDate(projectErp.getMoneyDate());
			}
			//项目启动日期
			Date poDate = projectErp.getPoDate();

			//Edit by polo   2018.7.10
			//当开工时间为空时不进行插入
			if(projectErp.getDateSampleUploading() != null){
				project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());  //初始导入正常进行项目
				//如果存在大货交期，生产周期根据大货交期去计算，如果不存在大货，生产周期根据样品交期计算
				//如果交期时间不为空
				//录入首次大货日期，如果存在第一次大货时间，则更新
				if(projectErp.getCompletionTime() != null){
					//如果存在大货交期，计算生成周期时间
					int scheduledDays = 0;
					//如果存在po日期，生产天数为 交期-po日期
					//如果不存在po日期，生产天数为交期-合同日期+7天
					if(poDate != null){
						scheduledDays = DateFormat.calDays(projectErp.getCompletionTime(), poDate);
					}else{
						scheduledDays = DateFormat.calDays(projectErp.getCompletionTime(), projectErp.getDateSampleUploading());
						scheduledDays = scheduledDays + 7;
					}
					project.setScheduledDays(scheduledDays);
					//工厂项目表保存交期
					projectFactory.setDeliveryDate(projectErp.getCompletionTime());
				}else{
					if(projectErp.getDateSample() != null){
						//紧急交货期
						int scheduledDays = 0;
						//如果存在po日期，生产天数为 交期-po日期
						//如果不存在po日期，生产天数为交期-合同日期+7天
						if(poDate != null){
							scheduledDays = DateFormat.calDays(projectErp.getDateSample(), poDate);
						}else{
							scheduledDays = DateFormat.calDays(projectErp.getDateSample(), projectErp.getDateSampleUploading());
							scheduledDays = scheduledDays + 7;
						}
						project.setScheduledDays(scheduledDays);
					}

				}

				//保存下单工厂
				projectFactory.setFactoryId(projectErp.getFactoryId());
				projectFactory.setFactoryName(projectErp.getCompanyName());
				projectFactory.setProjectNo(projectNo);
				projectFactory.setCity(projectErp.getCity());
				projectFactory.setContractNo(projectErp.getContractNo());
				if(projectErp.getSupplementaryContract()!=null){
					projectFactory.setOrderNature(projectErp.getSupplementaryContract() == 1 ? 2 : 1); //补货或者正常
				}else{
					projectFactory.setOrderNature(1);
				}
				projectFactoryService.insertSelective(projectFactory);

				projectService.addProject(project);
				List<Project> projectList=new ArrayList<Project>();
				projectList.add(project);
				projectDateTask.syncProjectDate(projectList);
			}
		}


		return jsonResult;
	}

}
