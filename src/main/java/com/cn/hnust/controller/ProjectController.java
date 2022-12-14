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
import java.time.LocalDate;
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
import com.cn.hnust.util.*;
import io.swagger.models.auth.In;
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
import org.springframework.util.Assert;
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
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * ????????????????????????
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    private static final String String = null;
    private static final int UNSOLVE = 0;  //???????????????
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


    private static final Integer SAMPLE = 0;  //????????????
    private static final Integer PRODUCT = 1; //????????????

    private static final Integer NOTDELAY = 0;   //?????????
    private static final Integer DELAY = 1;      //??????
    private static final Integer ALL = null;     //??????

    //??????????????????
    private static final Integer MATERIAL_FILE = 1;   //????????????
    private static final Integer VIDEO = 2;           //??????


    /**
     * ???????????????????????????????????????
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
        int totalCountList = 0;// ????????????
        Project project = new Project();

        Date today = new Date();

        String pageNumberStr = request.getParameter("pageNumber");
        String operatorTypeStr = request.getParameter("operatorType");
//		String roleNo = request.getParameter("roleNo"); // ????????????????????????????????????
//		String userName = request.getParameter("userName");
        String userIdStr = request.getParameter("userId");
        String inputKey = request.getParameter("inputKey");// ?????????
        String purchaseName = request.getParameter("purchase_name");// ??????
        String saleName = request.getParameter("documentary_name");// ???????????????
        String qualityName = request.getParameter("quality_name");// ??????
        String pageSizeStr = request.getParameter("pageSize");
        String screenType = request.getParameter("screenType");
	/*	String projectStage = request.getParameter("projectStage");// ????????????
		String plantAnalysis = request.getParameter("plantAnalysis");// ????????????
		String projectTypeStr = request.getParameter("projectTypeS");// ????????????
		String projectStageStr = request.getParameter("projectStageS");// ????????????(pc)
		String plantAnalysisStr = request.getParameter("plantAnalysisS");// ????????????(pc)
        String delayStatusS=request.getParameter("delayStatusS");  //??????????????????
*/        //??????????????????
//        String detailStatus = request.getParameter("detailStatusS");


        String qualityReportSelect = request.getParameter("qualityReportSelect");//??????????????????
        String expectedShipmentSelect = request.getParameter("expectedShipmentSelect");
        String importantSelect = request.getParameter("importantSelect");   //A???B??????????????????????????????
        if (StringUtils.isNotBlank(operatorTypeStr)) {
            operatorType = Integer.parseInt(operatorTypeStr);
        }
        if (StringUtils.isNotBlank(pageNumberStr)) {
            pageNumber = Integer.parseInt(pageNumberStr);// ?????????,1,2

        }
        if (StringUtils.isNotBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        Integer roleNo = null;                            // ????????????????????????????????????
        String userName = WebCookie.getUserName(request);
        if (StringUtils.isNotBlank(userName)) {
            User user = userService.findUserByName(userName);
            roleNo = user.getRoleNo();
            userIdStr = user.getId() + "";
        } else {
            jsonResult.setMessage("????????????");
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
            project.setScreenType(screenType); //pc ???????????????
            project.setInputKey(inputKey);
            project.setUserName(userName);
            project.setRoleNo(roleNo);
            project.setQualityReportSelect(qualityReportSelect);
            project.setExpectedShipmentSelect(expectedShipmentSelect);
            project.setImportantSelect(importantSelect);

            list = projectService.findProjectList(project);// ????????????
            totalCountList = projectService.findProjectListCount(project);// ??????????????????

            userId = Integer.parseInt(userIdStr);

            // ???????????????????????????????????????(??????????????????????????????)
            InspectionReservation reservation = new InspectionReservation();
            for (Project seaProject : list) {
                // 4.???????????????????????????
//				List<ProjectDrawing> projectDrawingList = projectDrawingService.selectProjectDrawingByProjectNo(seaProject.getProjectNo());
                // 5.??????????????????
//				List<ProjectInspectionReport> projectInspectionReportList = projectInspectionReportService.selectInspectionReportByProjectNo(seaProject.getProjectNo());
                //6.??????????????????
/*				List<ProjectInspectionReport> inspectionPlanList=projectInspectionReportService.selectInspectionPlanByProjectNo(seaProject.getProjectNo());
				ProjectTask projectTask=new ProjectTask();
				projectTask.setProjectNo(seaProject.getProjectNo());
				projectTask.setTaskStatus("-1");
				projectTask.setPageSize(100);
				projectTask.setPageNumber(0);*/
                //7.???????????????????????????????????????(??????/??????)
/*				List<ProjectTask> projectTaskList=projectTaskService.selectProjectTask(projectTask);
				ProjectTask finishTask=new ProjectTask();
				finishTask.setProjectNo(seaProject.getProjectNo());
				finishTask.setTaskStatus("1");
				finishTask.setPageSize(100);
				finishTask.setPageNumber(0);

				List<ProjectTask> finishTaskList=projectTaskService.selectProjectTask(finishTask);
				seaProject.setAllTask(projectTaskList.size());
				seaProject.setFinishTask(finishTaskList.size());*/
                //8.??????????????????????????????
/*				List<ProjectDrawing> demandReportList=projectDrawingService.selectProjectDemandReportByNo(seaProject.getProjectNo());
				seaProject.setProjectDemandReportList(demandReportList);
				seaProject.setInspectionPlanList(inspectionPlanList);
				seaProject.setProjectDrawingList(projectDrawingList);
				seaProject.setInspectionReportList(projectInspectionReportList);
				seaProject.setProjectTaskList(projectTaskList);*/
                // 6.?????????????????????????????????
                ProjectReport projectReport = projectReportService.selectProjectReportLatelyDate(seaProject.getProjectNo());
                seaProject.setProjectReport(projectReport);
                // 7.?????????????????????????????????
                //?????????????????????????????????,?????????????????????
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
                List<ProjectReport> projectReportList = projectReportService.selectProjectReportWeek(seaProject.getProjectNo(), week);
                seaProject.setProjectReportList(projectReportList);
                List<Feedback> feedbackList = feedbackService.selectFeedbackByProjectNo(seaProject.getProjectNo(), week);
                seaProject.setFeedbackList(feedbackList);
                //8.??????????????????
//				QualityReport qualityReport = qrService.selectNewestReportByProjectNo(seaProject.getProjectNo());
//				seaProject.setQualityReport(qualityReport);
                //9.????????????????????????????????????????????????
                List<Delay> delays = delayService.selectAllByProjectNo(seaProject.getProjectNo());
                seaProject.setDelayList(delays);
                //10.??????????????????????????????
//                reservation.setInputKey(seaProject.getProjectNo());
//                int reservationNum=inspectionReservationService.selectInspectionReservationCount(reservation);
//                seaProject.setInspectionReservationNum(reservationNum);
                // ??????????????????
                if (seaProject.getProjectStatus() != null) {
                    if (seaProject.getProjectStatus() == 1) {
                        status = "???????????????";
                    }
                    if (seaProject.getProjectStatus() == 2) {
                        status = "??????????????????";
                    }
                    if (seaProject.getProjectStatus() == 0) {
                        status = "???????????????";
                    }
                    if (seaProject.getProjectStatus() == 4) {
                        status = "????????????";
                    }
                    if (seaProject.getProjectStatus() == 5) {
                        status = "????????????";
                    }
                    if (seaProject.getProjectStatus() == 6) {
                        status = "??????????????????";
                    }
                }

                seaProject.setStatus(status);
                // ??????????????????
                //??????????????????????????????????????????????????????
//				 if(seaProject.getProjectStatus() != OrderStatusEnum.NEW_ORDER.getCode() && seaProject.getProjectStatus() != OrderStatusEnum.CANCEL_ORDER.getCode() && seaProject.getProjectStatus() != OrderStatusEnum.PAUSE_ORDER.getCode()){
//		        		Map<String, Object> map = judgeDelay(seaProject, scheduleList);
//		        		Boolean judgeDelay = (Boolean)map.get("delayType");
//		        		int delayDays = Integer.parseInt(map.get("delayDays").toString());
//		        		seaProject.setDelayDays(delayDays);
//		       		if(judgeDelay == true){
//		       			status = "?????????";
//		           		seaProject.setStatus(status);
//		           	    delayList.add(seaProject);
//		           	}
//				 }

                if (seaProject.getPlantAnalysis() != null && seaProject.getPlantAnalysis() != 0) {
                    seaProject.setPlantAnalysisView(ProjectAnalysisEnum.getEnum(seaProject.getPlantAnalysis()).getValue());
                }
                if (seaProject.getSampleScheduledDate() != null && seaProject.getDeliveryDate() == null) {//???????????????????????????
                    if (seaProject.getSampleScheduledDate() != null && today.getTime() < seaProject.getSampleScheduledDate().getTime()
                            && seaProject.getSampleScheduledDate().getTime() - today.getTime() <= 12 * 24 * 60 * 60 * 1000 && seaProject.getSampleFinish() == 0) {
                        seaProject.setFlag(1); // ?????????
                    } else if (seaProject.getSampleScheduledDate() != null && seaProject.getSampleScheduledDate().getTime() + 7 * 24 * 60 * 60 * 1000 < today.getTime() && seaProject.getSampleFinish() == 0) {
                        seaProject.setFlag(2); // ?????????
                    }
                }
                if (seaProject.getDeliveryDate() != null) {//??????????????????????????????
                    if (seaProject.getDeliveryDate() != null && today.getTime() < seaProject.getDeliveryDate().getTime()
                            && seaProject.getDeliveryDate().getTime() - today.getTime() <= 12 * 24 * 60 * 60 * 1000) {
                        seaProject.setFlag(1); // ?????????
                    } else if (seaProject.getDeliveryDate() != null && seaProject.getDeliveryDate().getTime() + 7 * 24 * 60 * 60 * 1000 < today.getTime() && seaProject.getFinish() == 0) {
                        seaProject.setFlag(2); // ?????????
//						  delayList.add(seaProject);
                    }
                }
            }

            // ?????????????????????????????????????????????????????????
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
            hashMap.put("expectedShipmentSelect", expectedShipmentSelect);
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
     * ????????????????????????
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
        int totalCountList = 0;// ????????????
        Project project = new Project();
        Date today = new Date();

        String pageNumberStr = request.getParameter("pageNumber");
        String operatorTypeStr = request.getParameter("operatorType");
        String userIdStr = request.getParameter("userId");
        String inputKey = request.getParameter("inputKey");// ?????????
        String projectType = request.getParameter("projectType");// ????????????
        String projectStage = request.getParameter("projectStage");// ????????????
        String plantAnalysis = request.getParameter("plantAnalysis");// ????????????
        String projectTypeStr = request.getParameter("projectTypeS");// ????????????
        String projectStageStr = request.getParameter("projectStageS");// ????????????(pc)
        String plantAnalysisStr = request.getParameter("plantAnalysisS");// ????????????(pc)
        String purchaseName = request.getParameter("purchase_name");// ??????
        String saleName = request.getParameter("documentary_name");// ???????????????
        String qualityName = request.getParameter("quality_name");// ??????
        String technicianStr = request.getParameter("technician");// ?????????
        String pageSizeStr = request.getParameter("pageSize");
        String screenType = request.getParameter("screenType");
        String delayStatusS = request.getParameter("delayStatusS");  //??????????????????

        //??????????????????
        String detailStatus = request.getParameter("detailStatusS");

        Integer roleNo = null;                            // ????????????????????????????????????
        String userName = WebCookie.getUserName(request);
        if (StringUtils.isNotBlank(userName)) {
            User user = userService.findUserByName(userName);
            roleNo = user.getRoleNo();
            userIdStr = user.getId() + "";
        } else {
            return "redirect:/index.jsp";
        }


        String qualityReportSelect = request.getParameter("qualityReportSelect");//??????????????????
        String expectedShipmentSelect = request.getParameter("expectedShipmentSelect");
        String importantSelect = request.getParameter("importantSelect");   //A???B??????????????????????????????
        if (StringUtils.isNotBlank(operatorTypeStr)) {
            operatorType = Integer.parseInt(operatorTypeStr);
        }
        if (StringUtils.isNotBlank(pageNumberStr)) {
            pageNumber = Integer.parseInt(pageNumberStr);// ?????????,1,2

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
            //?????????????????????(0,1,2,3) -1???????????????
            if (StringUtils.isNotBlank(plantAnalysisStr)) {//-1,0,1,2,3
                List<Integer> plantAnalysisList = new ArrayList<Integer>();
                String plantAnalysisS[] = plantAnalysisStr.split(",");
                for (int i = 0; i < plantAnalysisS.length; i++) {
                    plantAnalysisList.add(Integer.parseInt(plantAnalysisS[i]));
                }
                project.setPlantAnalysisS(plantAnalysisList);
            }
            //??????????????????
            if (StringUtils.isNotBlank(projectStageStr)) {
                String projectStageS[] = projectStageStr.split(","); //-1,0,1,2,3
                List<Integer> projectStageList = new ArrayList<Integer>();
                for (int i = 0; i < projectStageS.length; i++) {
                    projectStageList.add(Integer.parseInt(projectStageS[i]));
                    if (Integer.parseInt(projectStageS[i]) == 3) {
                        project.setStageUnUpdate(3);
                    }
                }
                project.setProjectStageS(projectStageList);
            }
            //??????????????????
            Integer projectStatus = null;
            if (StringUtils.isNotBlank(projectTypeStr)) {
                String projectTypeS[] = projectTypeStr.split(",");
                List<Integer> projectTypeList = new ArrayList<Integer>();
                for (int i = 0; i < projectTypeS.length; i++) {
                    projectTypeList.add(Integer.parseInt(projectTypeS[i]));

                    //??????????????????7???????????????????????????????????????
                    if (Integer.parseInt(projectTypeS[i]) == 7) {
                        projectStatus = 7;
                        project.setProjectStatus(projectStatus);
                    }
                }
                project.setProjectStatusS(projectTypeList);
            }
            //??????????????????
            List<Integer> delayStatusList = new ArrayList<Integer>();
            if (StringUtils.isNotBlank(delayStatusS)) {
                String delayArray[] = delayStatusS.split(",");
                for (int i = 0; i < delayArray.length; i++) {
                    delayStatusList.add(Integer.parseInt(delayArray[i]));
                }
                project.setDelayStatusS(delayStatusList);
            }

            //??????????????????
            if (StringUtils.isNotBlank(detailStatus)) {
                String detailStatusS[] = detailStatus.split(",");
                List<Integer> detailStatusList = new ArrayList<Integer>();
                for (int i = 0; i < detailStatusS.length; i++) {
                    detailStatusList.add(Integer.parseInt(detailStatusS[i]));
                    if (Integer.parseInt(detailStatusS[i]) == 0) {
                        project.setDetailUnUpdate(0);
                    }
                }
                project.setDetailStatusS(detailStatusList);
            }
            project.setSellName(saleName);
            project.setQualityName(qualityName);
            project.setTechnicianStr(technicianStr);
            project.setPurchaseName(purchaseName);
            project.setScreenType(screenType); //pc ???????????????
            project.setInputKey(inputKey);
            project.setUserName(userName);
            project.setRoleNo(roleNo);
            project.setQualityReportSelect(qualityReportSelect);
            project.setExpectedShipmentSelect(expectedShipmentSelect);
            project.setImportantSelect(importantSelect);
            list = projectService.findProjectList(project);// ????????????
            totalCountList = projectService.findProjectListCount(project);// ??????????????????
            Long end = new Date().getTime();
            LOG.info("???????????????????????????" + (end - start));
            userId = Integer.parseInt(userIdStr);
            // ???????????????????????????????????????(??????????????????????????????)
//			InspectionReservation reservation=new InspectionReservation();

            for (Project seaProject : list) {
                ProjectTask task = projectTaskService.getByProjectNo(seaProject.getProjectNo());
                if (task != null) {
                    ProjectDrawing drawing = new ProjectDrawing();
                    drawing.setProjectNo(seaProject.getProjectNo());
                    drawing.setCreateDate(task.getCreateTime());
                    List<ProjectDrawing> projectDrawingList = projectDrawingService
                            .selectByProjectNo(drawing);

                    request.setAttribute("projectDrawingList", projectDrawingList);
                }
                request.setAttribute("task", task);
                //????????????????????????
                List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(seaProject.getProjectNo());
                //??????????????????????????????????????????????????????????????????
                if ("7".equals(importantSelect)) {
                    for (ProjectFactory projectFactory : factoryList) {
                        if (projectFactory.getFactoryId() != null) {
                            List<QuoteWeeklyReport> reports = quoteWeeklyReportService.queryByCsgOrderIdAndType(seaProject.getProjectNo(), null, projectFactory.getFactoryId());
                            for (QuoteWeeklyReport quoteWeeklyReport : reports) {
                                if (quoteWeeklyReport.getFileType() == MATERIAL_FILE) {
                                    projectFactory.setIsUploadMaterial(true);
                                }
                                if (quoteWeeklyReport.getFileType() == VIDEO) {
                                    projectFactory.setIsUploadVideo(true);
                                }
                            }
                        }
                    }
                }
                seaProject.setFactoryList(factoryList);


                // 4.???????????????????????????(????????????)
                List<ProjectDrawing> projectDrawingList = projectDrawingService.selectProjectDrawingByProjectNo(seaProject.getProjectNo());
                // 5.??????????????????   (????????????)
                List<ProjectInspectionReport> projectInspectionReportList = projectInspectionReportService.selectInspectionReportByProjectNo(seaProject.getProjectNo());
                //6.??????????????????(????????????)
                List<ProjectInspectionReport> inspectionPlanList = projectInspectionReportService.selectInspectionPlanByProjectNo(seaProject.getProjectNo());
                //????????????????????????
                Map<String, Long> taskCount = projectTaskService.selectCountByStatus(seaProject.getProjectNo());
                seaProject.setAllTask(taskCount.get("allTask").intValue());
                seaProject.setPuaseTask(taskCount.get("pauseTask").intValue());
                seaProject.setFinishTask(taskCount.get("finishTask").intValue());
                seaProject.setQualityTask(taskCount.get("qualityTask").intValue());
                seaProject.setQualityFinishTask(taskCount.get("qualityFinishTask").intValue());
                //8.??????????????????????????????
                List<ProjectDrawing> demandReportList = projectDrawingService.selectProjectDemandReportByNo(seaProject.getProjectNo());
                seaProject.setProjectDemandReportList(demandReportList);
                seaProject.setInspectionPlanList(inspectionPlanList);
                seaProject.setProjectDrawingList(projectDrawingList);
                seaProject.setInspectionReportList(projectInspectionReportList);

                // 7.?????????????????????????????????
                //?????????????????????????????????,?????????????????????
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
                List<ProjectReport> projectReportList = projectReportService.selectProjectReportWeek(seaProject.getProjectNo(), week);
                seaProject.setProjectReportList(projectReportList);
                if (projectReportList != null && projectReportList.size() > 0) {
                    // ?????????????????????????????????
                    ProjectReport projectReport = projectReportService.selectProjectReportLatelyDate(seaProject.getProjectNo());
                    seaProject.setProjectReport(projectReport);
                }
                List<Feedback> feedbackList = feedbackService.selectFeedbackByProjectNo(seaProject.getProjectNo(), week);
                seaProject.setFeedbackList(feedbackList);
                //?????????????????????
                Feedback feedback = feedbackService.selectLastByProjectNo(seaProject.getProjectNo());
                seaProject.setFeedback(feedback);
                //??????????????????
                List<ProductionPlan> planList = productionPlanService.selectByProjectNo(seaProject.getProjectNo());
                seaProject.setPlanList(planList);
                //??????????????????
//		        List<DeliveryDateLog> deliveryList = deliveryDateLogService.selectDeliveryList(seaProject.getProjectNo());
//		        seaProject.setDeliveryList(deliveryList);

                //8.??????????????????
//				QualityReport qualityReport = qrService.selectNewestReportByProjectNo(seaProject.getProjectNo());
//				seaProject.setQualityReport(qualityReport);
                double amount = 0.0;
                if (seaProject.getProjectAmount() != null && !"".equalsIgnoreCase(seaProject.getProjectAmount())) {
                    try {
                        amount = Double.parseDouble(seaProject.getProjectAmount());
                    } catch (Exception e) {

                    }
                }
                List<QualityReport> qualityReportList = new ArrayList<QualityReport>();
                if (roleNo == 9 && amount > 1.4) {
                    qualityReportList = qrService.selectByProjectNo1(seaProject.getProjectNo(), userName);
                    seaProject.setQrList(qualityReportList);
                } else {
                    qualityReportList = qrService.selectByProjectNo(seaProject.getProjectNo());
                    seaProject.setQrList(qualityReportList);
                }

                List<QualityReport> erpReports = erpReportService.selectErpReportByProjectNo(seaProject.getProjectNo());
                seaProject.setErpReports(erpReports);


                //9.????????????????????????????????????????????????(????????????)
//				List<Delay> delays = delayService.selectAllByProjectNo(seaProject.getProjectNo());
//				seaProject.setDelayList(delays);
                //10.??????????????????????????????
//                reservation.setInputKey(seaProject.getProjectNo());
//                int reservationNum=inspectionReservationService.selectInspectionReservationCount(reservation);
//                seaProject.setInspectionReservationNum(reservationNum);
                //??????????????????
                List<InspectionReservation> inspectionReservations = inspectionReservationService.selectInspectionList(seaProject.getProjectNo());

                //???????????????????????????
                int shippingApproval = 3;
                int t1 = -1;
                int t2 = -1;
                for (InspectionReservation inspectionReservation2 : inspectionReservations) {
                    if ("??????".equals(inspectionReservation2.getTestType())) {
                        seaProject.setIsProcess(true);
                    }
                    //??????????????????????????????t1
                    //???????????????t2
                    if (inspectionReservation2.getShippingApproval() != null && inspectionReservation2.getShippingApproval() == 1) {
                        t1 = 1;
                    } else {
                        t2 = 0;
                    }
                }
                //?????????????????????????????????????????????????????????????????????
                if (t1 == 1 && t2 == 0) {
                    shippingApproval = 2;
                } else if (t1 == 1 && t2 == -1) {
                    shippingApproval = 1;
                } else if (t1 == -1 && t2 == 0) {
                    shippingApproval = 0;
                } else if (t1 == -1 && t2 == -1) {
                    shippingApproval = 3;
                }
                seaProject.setShippingApproval(shippingApproval);

                seaProject.setInspectionList(inspectionReservations);


                //12 ??????????????????(????????????)
                List<Comment> commentList = projectCommentService.selectProjectComment(seaProject.getProjectNo());
                seaProject.setCommentList(commentList);
                //13 ????????????????????????(????????????)
                List<ProjectComplaint> complaintList = projectComplaintService.selectByProjectNo(seaProject.getProjectNo());
                seaProject.setComplaintList(complaintList);
                int unFinshedTask = projectComplaintService.countUnFinished(seaProject.getProjectNo());
                seaProject.setUnFinshedTask(unFinshedTask);
                //14 ?????????????????????
                MeetingRecord meetingRecord = new MeetingRecord();
                meetingRecord.setProjectNo(seaProject.getProjectNo());
                List<MeetingRecord> meetingRecords = meetingRecordService.selectMeetingRecordListCount(meetingRecord);
                for (MeetingRecord meetingRecord2 : meetingRecords) {
                    if ("???????????????".equals(meetingRecord2.getMeetingName())) {
                        seaProject.setIsStart(true);
                    }
                    if ("???????????????".equals(meetingRecord2.getMeetingName())) {
                        seaProject.setIsSample(true);
                    }
                    if ("???????????????".equals(meetingRecord2.getMeetingName())) {
                        seaProject.setIsProduct(true);
                    }
                }

                //??????????????????????????????
                if (seaProject.getProjectNo().contains("-")) {
                    seaProject.setIsStart(true);
                    seaProject.setIsSample(true);
                    if (seaProject.getIsProduct() != null && seaProject.getIsProduct() != true) {
                        for (QualityReport qualityReport2 : erpReports) {
                            if (StringUtils.isNotBlank(qualityReport2.getExplainCause()) && qualityReport2.getExplainCause().contains("?????????")) {
                                seaProject.setIsProduct(true);
                            }
                        }
                        //?????????????????????????????????????????????????????????????????????
                        if (qualityReportList != null && qualityReportList.size() > 0 && qualityReportList.get(0).getStatus() == 0) {
                            seaProject.setIsProduct(true);
                        }
                    }
                }


                //15 ??????????????????
                if (seaProject.getDetailStatus() != null && seaProject.getDetailStatus() != 0) {
                    seaProject.setDetailStr(DetailStatusEnum.getEnum(seaProject.getDetailStatus()).getValue());
                }
                //16 ???????????????????????????????????????
                Date actualStartDate = seaProject.getActualStartDate();
                Date createDate = seaProject.getDateSampleUploading();
                if (createDate != null) {
                    if (actualStartDate != null) {
                        Boolean compareDate = DateFormat.compareDate(createDate, actualStartDate, 7);
                        seaProject.setIsContract(compareDate);
                    } else {
                        Boolean compareDate = DateFormat.compareDate(createDate, new Date(), 7);
                        seaProject.setIsContract(compareDate);
                    }
                }
                //17 ??????????????????????????????
                List<ProjectPause> projectPauses = projectPauseService.selectByProjectNo(seaProject.getProjectNo());
                seaProject.setProjectPauses(projectPauses);

                //18 ????????????????????????
                int contractDays = 0;
                //?????????????????????????????????????????????po????????????
                //??????????????????????????????po??????????????????????????????-????????????-7???
                if (seaProject.getOriginalSampleScheduledDate() != null && seaProject.getDateSampleUploading() != null) {
                    contractDays = DateFormat.calDays(seaProject.getOriginalSampleScheduledDate(), seaProject.getDateSampleUploading());
                } else if (seaProject.getOriginalSampleScheduledDate() != null && seaProject.getDateSampleUploading() == null && seaProject.getActualStartDate() != null) {
                    contractDays = DateFormat.calDays(seaProject.getOriginalSampleScheduledDate(), seaProject.getActualStartDate()) + 7;
                }
                //???????????????????????????????????????????????????
                if (seaProject.getOriginalDeliveryDate() != null && seaProject.getDateSampleUploading() != null) {
                    contractDays = DateFormat.calDays(seaProject.getOriginalDeliveryDate(), seaProject.getDateSampleUploading());
                } else if (seaProject.getOriginalDeliveryDate() != null && seaProject.getDateSampleUploading() == null && seaProject.getActualStartDate() != null) {
                    contractDays = DateFormat.calDays(seaProject.getOriginalDeliveryDate(), seaProject.getActualStartDate()) + 7;
                }
                seaProject.setContractDays(contractDays);

                // ??????????????????
                //??????????????????????????????????????????????????????
                if (seaProject.getProjectStatus() != OrderStatusEnum.NEW_ORDER.getCode() && seaProject.getProjectStatus() != OrderStatusEnum.CANCEL_ORDER.getCode() && seaProject.getProjectStatus() != OrderStatusEnum.PAUSE_ORDER.getCode()) {
                    Map<String, Object> map = judgeDelay(seaProject);
                    Boolean judgeDelay = (Boolean) map.get("delayType");
                    int delayDays = Integer.parseInt(map.get("delayDays").toString());
                    seaProject.setDelayDays(delayDays);
                    if (judgeDelay == true) {
                        status = "?????????";
                        seaProject.setStatus(status);
                    }
                }

                if (seaProject.getPlantAnalysis() != null && seaProject.getPlantAnalysis() != 0) {
                    seaProject.setPlantAnalysisView(ProjectAnalysisEnum.getEnum(seaProject.getPlantAnalysis()).getValue());

                    //A???B???????????????????????????????????????????????????????????????
                    if ((seaProject.getPlantAnalysis() == 1 || seaProject.getPlantAnalysis() == 2) && seaProject.getDeliveryDate() != null && seaProject.getActualStartDate() != null) {
                        long a1 = seaProject.getDeliveryDate().getTime() - seaProject.getActualStartDate().getTime();  //????????????
                        long a2 = today.getTime() - seaProject.getActualStartDate().getTime();                         //??????????????????
                        if (a1 < a2 * 2 && (seaProject.getIsProcess() == null || seaProject.getIsProcess() != true)) {
                            seaProject.setIsNoteProcess(true);
                        }
                    }
                }

                //?????????????????????
                List<AnalysisIssue> issueList = analysisIssueService.selectByProjectNo(seaProject.getProjectNo(), 0);
                seaProject.setAnalysisIssueList(issueList);
                QualityAnalysis qualityAnalysis = qualityAnalysisService.selectByProjectNo(seaProject.getProjectNo());
                seaProject.setQualityAnalysis(qualityAnalysis);
            }

            long t4 = new Date().getTime();
            LOG.info("?????????????????????" + (t4 - end));


            //????????????
            Integer lastNum = new BigDecimal(totalCountList).divide(new BigDecimal(pageSize)).setScale(0, BigDecimal.ROUND_UP).intValue();

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
            request.setAttribute("expectedShipmentSelect", expectedShipmentSelect);

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("error", e);
        }
        return "project_list_pc";
    }


    /**
     * ??????????????????
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryProjectDetail")
    public JsonResult queryProjectDetail(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        String projectNo = request.getParameter("projectNo");

        //???????????????????????????
        List<ProjectDrawing> projectDrawingList = projectDrawingService.selectProjectDrawingByProjectNo(projectNo);
        //??????????????????
        List<ProjectInspectionReport> projectInspectionReportList = projectInspectionReportService.selectInspectionReportByProjectNo(projectNo);
        //??????????????????
        List<ProjectInspectionReport> inspectionPlanList = projectInspectionReportService.selectInspectionPlanByProjectNo(projectNo);
        //??????????????????
        List<Comment> commentList = projectCommentService.selectProjectComment(projectNo);
        //????????????????????????
        List<ProjectComplaint> complaintList = projectComplaintService.selectByProjectNo(projectNo);
        int unFinshedTask = projectComplaintService.countUnFinished(projectNo);
        //??????????????????
        List<DeliveryDateLog> deliveryList = deliveryDateLogService.selectDeliveryList(projectNo);
        //????????????
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
     * ????????????????????????
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryProjectFactory")
    public JsonResult queryProjectFactory(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        String projectNo = request.getParameter("projectNo");
        //????????????
        List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectNo);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("factoryList", factoryList);
        jsonResult.setOk(true);
        jsonResult.setData(map);

        return jsonResult;
    }


    /**
     * ??????????????????
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
        Integer roleNo = null;                            // ????????????????????????????????????
        String userName = WebCookie.getUserName(request);
        if (StringUtils.isNotBlank(userName)) {
            User user = userService.findUserByName(userName);
            roleNo = user.getRoleNo();
            userId = user.getId() + "";
        } else {
            return "redirect:/index.jsp";
        }


        List<Task> taskList = new ArrayList<Task>();
        List<QualityReport> qrls = null;
        try {
            // 1.????????????????????????
            Project project = projectService.showDetails(projectNo);
            if (StringUtils.isNotBlank(project.getProjectAmount())) {
                String regEx = "[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(project.getProjectAmount());
                project.setProjectAmount(m.replaceAll("").trim());
            }


            //??????????????????????????????????????????????????????????????????????????????
            if (project != null) {
                if (!projectNo.contains("-")) {
                    project.setIsNewProject(true);
                } else {
                    String[] split = projectNo.split("-");
                    int num = 0;
                    if (split != null && split.length > 0) {
                        num = Integer.parseInt(split[1]);
                        num--;
                        String newProjectNo = split[0] + "-" + num;
                        List<ProjectComplaint> complaints = projectComplaintService.selectByProjectNo(newProjectNo);
                        if (complaints != null && complaints.size() > 0) {
                            project.setIsNewProject(true);
                        }
                    }
                }
            }
            // 2.???????????????????????????
            taskList = taskService.findTaskByProjectNo(projectNo);
            for (int i = 0; i < taskList.size(); i++) {// ??????????????????id????????????????????????
                Task task = taskList.get(i);
                // 3.???????????????????????????
                task.setTaskReportList(taskReportService.selectTaskReportByNo(task
                        .getId()));
            }
            // 4.???????????????????????????
            List<ProjectDrawing> projectDrawingList = projectDrawingService
                    .selectProjectDrawingByProjectNo(projectNo);
            // 5.??????????????????
            List<ProjectInspectionReport> projectInspectionReportList = projectInspectionReportService
                    .selectInspectionReportByProjectNo(projectNo);
            // 6.?????????????????????
            List<Comment> commentList = projectCommentService
                    .selectProjectComment(projectNo);
            // 7.?????????????????????????????????
            List<StatusEnter> statusEnterList = statusEnterService
                    .selectProjectStatusEnter(projectNo);
            for (StatusEnter statusEnter : statusEnterList) {
                if (StringUtils.isNotBlank(statusEnter.getStatusType())) {
                    String statusType = statusEnter.getStatusType();
                    String[] others = statusType.split(",");
                    statusEnter.setStatusTypeList(Arrays.asList(others));
                }
            }
            double Amount = 0.0;
            if (project.getProjectAmount() != null && !"".equalsIgnoreCase(project.getProjectAmount())) {
                try {
                    Amount = Double.parseDouble(project.getProjectAmount());
                } catch (Exception e) {

                }
            }
            // 8 ????????????
            if (Amount >= 1.4 && roleNo == 9) {
                qrls = qrService.selectByProjectNo1(projectNo, userName);
            } else {
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
                    String eid = Base64Encode.getBase64(qr.getId() + "");
                    eid = eid.replaceAll("=", "");
                    qr.setEid(eid);
                }
            }

            //9.??????????????????
            List<ProjectInspectionReport> inspectionPlanList = projectInspectionReportService.selectInspectionPlanByProjectNo(projectNo);

            //????????????
            List<ProjectSchedule> projectSchedules = projectScheduleService.selectByProjectNo(projectNo);

            //??????????????????
            List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectNo);
            //????????????
            List<FactoryQualityInspectionVideo> videos = factoryQualityInspectionVideoService.selectByProjectNo(projectNo);
            //??????????????????
            String originalProjectNo = projectNo;
            if (originalProjectNo.contains("-")) {
                originalProjectNo = originalProjectNo.split("-")[0];
            }
            List<ProjectComplaint> complaints = projectComplaintService.selectByProjectNoDim(originalProjectNo);
            //???????????????????????????
            List<AnalysisIssue> analysisIssueList = analysisIssueService.selectByProjectNo(projectNo, 0);
            for (AnalysisIssue analysisIssue : analysisIssueList) {
                if (StringUtils.isNotBlank(analysisIssue.getIssue())) {
                    List<String> issueList = qualityAnalysisService.selectByIssue(analysisIssue.getIssue());
                    analysisIssue.setOccurrenceNum(issueList != null ? issueList.size() : 0);
                }
            }
            project.setAnalysisIssueList(analysisIssueList);
            //??????????????????
            List<ProjectTask> projectTaskList = projectTaskService.selectByProjectNo(projectNo);

            //????????????
            List<AnalysisIssue> issueList = analysisIssueService.selectTop10(null);

            //????????????????????????????????????
            String originalProject = projectNo;
            String[] split = projectNo.split("-");
            List<Project> projectList = null;
            if (split.length > 1) {
                originalProject = split[0];
                Project projectQuery = new Project();
                projectQuery.setInputKey(originalProject);
                projectQuery.setPageSize(100);
                projectQuery.setPageNumber(0);
                projectList = projectService.findProjectList(projectQuery);
            }
            //??????????????????
            ProductionPlan productionPlan = productionPlanService.selectDemandByProjectNo(projectNo);
            if (productionPlan != null) {
                String node = productionPlan.getPlanNode();
                if (StringUtils.isNotBlank(node)) {
                    productionPlan.setPlanNode(URLEncoder.encode(node, "utf-8"));
                }
            }
            //????????????????????????
            List<InspectionPlan> planList = null;
            List<InspectionPlan> plans = null;
            planList = inspectionPlanService.selectByProjectNo(projectNo, null);
            plans = planList.stream().filter(distinctByKey(plan -> plan.getProductComponent())).collect(Collectors.toList());
            String projectNo1 = projectNo.substring(0, 8);
            List<QualityAndEfficiencyReport> efficiencyReportList1 = qualityAndEfficiencyReportService.getAll(projectNo1);//?????????????????????????????????
            boolean technicalAnalysisReport = false;
            boolean sampleEvaluation = false;
            boolean productionPreparation = false;
            boolean packagingScheme = false;
            boolean qualityEfficiency = false;
            boolean analysisOfConcentrator = false;
            boolean bargain = false;
            for (int i = 0; i < efficiencyReportList1.size(); i++) {
                QualityAndEfficiencyReport report = efficiencyReportList1.get(i);
                if (report.getCategory() == 1) {
                    technicalAnalysisReport = true;
                } else if (report.getCategory() == 2) {
                    sampleEvaluation = true;
                } else if (report.getCategory() == 3) {
                    productionPreparation = true;
                } else if (report.getCategory() == 4) {
                    packagingScheme = true;
                } else if (report.getCategory() == 5) {
                    qualityEfficiency = true;
                } else if (report.getCategory() == 6) {
                    analysisOfConcentrator = true;
                }

            }
            if (factoryList.size() > 0) {
                bargain = true;
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
     * ??????????????????
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

            // 1.????????????????????????
            Project project = projectService.showDetails(projectNo);
            // ????????????
            actualStartDate = DateFormat.date2String(project
                    .getActualStartDate());
            // ????????????
            sampleScheduledDate = DateFormat.date2String(project
                    .getSampleScheduledDate());
            // ????????????
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
             * ?????????????????????????????????
             */
            List<ProjectSchedule> projectSchedules = projectScheduleService.selectByProjectNo(projectNo);
            for (ProjectSchedule projectSchedule : projectSchedules) {
                //Add by polo 2018.7.19
                if (projectSchedule.getNum() == 1) {
                    request.setAttribute("projectSchedules1", projectSchedule.getPredictDate());
                }
                if (projectSchedule.getNum() == 2) {
                    request.setAttribute("projectSchedules2", projectSchedule.getPredictDate());
                }
                if (projectSchedule.getNum() == 3) {
                    request.setAttribute("projectSchedules3", projectSchedule.getPredictDate());
                }

            }

            List<Project> projectList = new ArrayList<Project>();
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
     * ????????????????????????
     *
     * @param request
     * @param response
     * @return String
     * @Title getDelay
     * @Description
     */
    @RequestMapping("/getDelay")
    public String getDelay(HttpServletRequest request, HttpServletResponse response) {
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
     * ??????????????????(??????)
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
        if (StringUtils.isNotBlank(projectSchedules1)) {
            ProjectSchedule projectSchedule = projectScheduleService.selectByProjectNoAndNum(projectNo, 1);
            if (projectSchedule == null) {
                projectSchedule = new ProjectSchedule();
                projectSchedule.setNum(1);
                projectSchedule.setPredictDate(DateUtil.StrToDate(projectSchedules1));
                projectSchedule.setCreateTime(new Date());
                projectSchedule.setProjectNo(projectNo);
                projectSchedule.setIsFinish(0);
                projectSchedule.setOriginalDate(DateUtil.StrToDate(projectSchedules1));
                projectSchedules.add(projectSchedule);
            } else {
                projectSchedule.setPredictDate(DateUtil.StrToDate(projectSchedules1));
                projectSchedule.setUpdateTime(new Date());
                projectScheduleService.updateByPrimaryKeySelective(projectSchedule);
            }
        }
        if (StringUtils.isNotBlank(projectSchedules2)) {
            ProjectSchedule projectSchedule = projectScheduleService.selectByProjectNoAndNum(projectNo, 2);
            if (projectSchedule == null) {
                projectSchedule = new ProjectSchedule();
                projectSchedule.setNum(2);
                projectSchedule.setPredictDate(DateUtil.StrToDate(projectSchedules2));
                projectSchedule.setCreateTime(new Date());
                projectSchedule.setProjectNo(projectNo);
                projectSchedule.setIsFinish(0);
                projectSchedule.setOriginalDate(DateUtil.StrToDate(projectSchedules1));
                projectSchedules.add(projectSchedule);
            } else {
                projectSchedule.setPredictDate(DateUtil.StrToDate(projectSchedules2));
                projectSchedule.setUpdateTime(new Date());
                projectScheduleService.updateByPrimaryKeySelective(projectSchedule);
            }
        }
        if (StringUtils.isNotBlank(projectSchedules3)) {
            ProjectSchedule projectSchedule = projectScheduleService.selectByProjectNoAndNum(projectNo, 3);
            if (projectSchedule == null) {
                projectSchedule = new ProjectSchedule();
                projectSchedule.setNum(3);
                projectSchedule.setPredictDate(DateUtil.StrToDate(projectSchedules3));
                projectSchedule.setCreateTime(new Date());
                projectSchedule.setProjectNo(projectNo);
                projectSchedule.setIsFinish(0);
                projectSchedule.setOriginalDate(DateUtil.StrToDate(projectSchedules1));
                projectSchedules.add(projectSchedule);
            } else {
                projectSchedule.setPredictDate(DateUtil.StrToDate(projectSchedules3));
                projectSchedule.setUpdateTime(new Date());
                projectScheduleService.updateByPrimaryKeySelective(projectSchedule);
            }
        }


//		String scheduledDate = request.getParameter("scheduledDate");// ????????????
//		String sampleScheduledDate = request.getParameter("sampleScheduledDate");
//		String actualStartDate = request.getParameter("actualStartDate");// ????????????
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

//			Project pro = projectService.selectProjctDetails(projectNo);// ????????????????????????
//			if (pro != null) {
//				if (pro.getDeliveryDate() == null) {// ?????????????????????????????????????????????
//					if(StringUtils.isNotBlank(scheduledDate)){
//						pro.setDeliveryDate(DateUtil.StrToDate(scheduledDate));// ?????????(????????????)
//					}
//				}
//				if (pro.getActualStartDate() == null) {
//					if(StringUtils.isNotBlank(actualStartDate)){
//						pro.setActualStartDate(DateUtil.StrToDate(actualStartDate));// ??????????????????
//					}
//				}
//				if (pro.getSampleScheduledDate() == null) {// ????????????
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
            if (projectSchedules != null && projectSchedules.size() > 0) {
                projectScheduleService.insertBatch(projectSchedules);
            }
            jsonResult.setOk(true);
            jsonResult.setMessage("????????????");
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
     * ????????????????????????  polo  2018.07.20
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title updateProjectSchedule
     * @Description
     */
    @RequestMapping("/updateProjectSchedule")
    @ResponseBody
    public JsonResult updateProjectSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonResult jsonResult = new JsonResult();
        try {
            String id = request.getParameter("id");
            String finishDate = request.getParameter("finishDate");
            ProjectSchedule projectSchedule = projectScheduleService.selectByPrimaryKey(Integer.parseInt(id));
            projectSchedule.setActualDate(DateUtil.StrToDate(finishDate));
            projectSchedule.setIsFinish(1);
            projectScheduleService.updateByPrimaryKeySelective(projectSchedule);
            jsonResult.setOk(true);
            jsonResult.setMessage("????????????");
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
     * ???????????????????????????
     *
     * @param request
     * @return String
     * @Title queryFinish
     * @Description
     */
    @RequestMapping("/queryFinish")
    public String queryFinish(HttpServletRequest request) {
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
     * ????????????
     *
     * @param request
     * @return String
     * @Title ??????????????????
     * @Description
     */
    @RequestMapping("/querySample")
    public String querySample(HttpServletRequest request) {
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
     * ??????????????????????????????
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
        String ipAddr = IpAddressUtil.getIpAddress(request);
        String inName = "/project/updateProjectStatus";
        JsonResult jsonResult = new JsonResult();
        String projectNo = request.getParameter("projectNo");
        String type = request.getParameter("type");
        String finishTime = request.getParameter("finishTime");
        try {
            // 1.?????????????????????????????????(projectNo)
            Project project = projectService.selectProjctDetails(projectNo);
            project.setIpAddr(ipAddr);
            project.setInterfaceName(inName);
            if (type.equals("2")) {//????????????

                if (project.getSampleScheduledDate() == null) {
                    jsonResult.setOk(false);
                    jsonResult.setMessage("????????????????????????????????? !!!");
                    return jsonResult;
                }
            } else {//????????????
                if (project.getFinish() == 1) {
                    jsonResult.setOk(false);
                    jsonResult.setMessage("??????????????????????????? !!!");
                    return jsonResult;
                }
                if (project.getDeliveryDate() == null) {
                    jsonResult.setOk(false);
                    jsonResult.setMessage("????????????????????????????????? !!!");
                    return jsonResult;
                }
            }

            // 2.???????????????????????????????????????(0????????? 1?????????)
            List<Task> taskList = taskService.findTaskByProjectNo(projectNo);
            Task task = null;
            if (taskList.size() > 0) {
                for (int i = 0; i < taskList.size(); i++) {
                    task = taskList.get(i);
                    if (task.getStatus().equals(
                            TaskStatusEnum.DEFAULT.getCode())) {// ???????????????
                        jsonResult.setOk(false);
                        jsonResult.setMessage("????????????????????????????????????");
                        return jsonResult;
                    } else {
                        if (type.equals("2")) {//????????????
                            project.setSampleFinish(1);// ??????????????????
                            project.setSampleFinishTime(DateUtil.StrToDate(finishTime));
                            project.setProjectStatus(OrderStatusEnum.SAMPLE_ORDER.getCode());
                            projectService.updateProjectInfo(project);
                        } else {//????????????
                            project.setFinish(1);// ??????????????????
                            project.setFinishTime(DateUtil.StrToDate(finishTime));
                            project.setProjectStatus(OrderStatusEnum.COMPLETE_ORDER.getCode());
                            projectService.updateProjectInfo(project);
                        }
                        jsonResult.setOk(true);
                        jsonResult.setMessage("??????????????????");
                        return jsonResult;
                    }
                }
            } else {// ??????????????????????????????????????????
                // ????????????????????????????????????????????????????????????(????????????)
                if (type.equals("2")) {//????????????
                    project.setSampleFinish(1);// ??????????????????
                    project.setSampleFinishTime(DateUtil.StrToDate(finishTime));
                    project.setProjectStatus(OrderStatusEnum.SAMPLE_ORDER.getCode());
                    projectService.updateProjectInfo(project);
                } else {//????????????
                    project.setFinish(1);// ??????????????????
                    project.setFinishTime(DateUtil.StrToDate(finishTime));
                    project.setProjectStatus(OrderStatusEnum.COMPLETE_ORDER.getCode());
                    projectService.updateProjectInfo(project);
                }

            }
            //??????????????????
            //ProjectDateTask projectDateTask=new ProjectDateTask();
            List<Project> projectList = new ArrayList<Project>();
            projectList.add(project);
            projectDateTask.syncProjectDate(projectList);
            jsonResult.setOk(true);
            jsonResult.setMessage("???????????????????????????");
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
     * ???????????????????????????(??????????????????????????????)
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
     * ????????????????????????
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

        // ??????????????????????????????
        Task updateTask = new Task();
        updateTask.setType(1);
        updateTask.setProjectNo(projectNo);
        taskService.updateTask(updateTask);

        List<Task> taskList = new ArrayList<Task>();
        Task task = new Task();
        if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// ??????????????????
            task.setProjectNo(projectNo);
            taskList = taskService.selectTaskList(task);// ????????????
        } else if (roleNo == 5) {// ??????
            task.setProjectNo(projectNo);
            taskList = taskService.selectTaskByProjectNo(task);
        } else if (roleNo == 6) {// ??????
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
     * ????????????
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
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));// ?????????,1,2
        } else {
            pageNumber = 1;
        }
        Integer pageSize = 10;
        Project project = new Project();
        project.setPageSize(pageSize);
        project.setPageNumber(pageSize * (pageNumber - 1));
        List<Project> messageCountList = new ArrayList<Project>();
        try {
            if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// ??????????????????
                messageList = projectService.selectProjectRelationTask(project);
                messageCountList = projectService
                        .selectProjectRelationTaskCount(project);
            } else if (roleNo == 5) {// ??????
                emailUserId = Integer.parseInt(request.getParameter("userId"));
                project.setEmailUserId(emailUserId);
                messageList = projectService.selectProjectRelationTask(project);
                messageCountList = projectService
                        .selectProjectRelationTaskCount(project);
            } else if (roleNo == 6) {// ??????
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
     * ????????????????????????????????????
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
            startDate = DateUtil.StrToDate(DateUtil.getTwoWeeksDate());// ????????????????????????(??????)
        }
        if (StringUtils.isNotBlank(end)) {
            endDate = DateUtil.StrToDate(end);
        } else {
            endDate = new Date();// ????????????
        }
        int roleNo = Integer.parseInt(request.getParameter("roleNo")); // ????????????????????????
        Project project = new Project();
        List<Project> list = new ArrayList<Project>();
        try {
            if (value.equals("1")) { // ????????????????????????
                if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// ????????????????????????
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    list = projectService.findDelayProjectList(project);
                } else if (roleNo == 5) {// ??????
                    Integer emailUserId = Integer.parseInt(request
                            .getParameter("userId"));// ???????????????userId
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    project.setEmailUserId(emailUserId);
                    list = projectService.findDelayProjectList(project);
                } else if (roleNo == 6) {// ??????
                    Integer purchaseId = Integer.parseInt(request
                            .getParameter("userId"));// ???????????????userId
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    project.setPurchaseId(purchaseId);
                    list = projectService.findDelayProjectPurchaseList(project);
                }
            } else if (value.equals("2")) {// ????????????????????????
                if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// ????????????????????????
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    list = projectService.findProjectReportList(project);
                } else if (roleNo == 5) {// ??????
                    Integer emailUserId = Integer.parseInt(request
                            .getParameter("userId"));// ???????????????userId
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    project.setEmailUserId(emailUserId);
                    list = projectService.findProjectReportList(project);
                } else if (roleNo == 6) {// ??????
                    Integer purchaseId = Integer.parseInt(request
                            .getParameter("userId"));// ???????????????userId
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    project.setPurchaseId(purchaseId);
                    list = projectService
                            .findProjectReportPurchaseList(project);
                }
            } else if (value.equals("3")) {// ?????????????????????????????????
                if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// ????????????????????????
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    list = projectService.findSetDeliveryTimeList(project);
                } else if (roleNo == 5) {// ??????
                    Integer emailUserId = Integer.parseInt(request
                            .getParameter("userId"));// ???????????????userId
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    project.setEmailUserId(emailUserId);
                    list = projectService.findSetDeliveryTimeList(project);
                } else if (roleNo == 6) {// ??????
                    Integer purchaseId = Integer.parseInt(request
                            .getParameter("userId"));// ???????????????userId
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    project.setPurchaseId(purchaseId);
                    list = projectService
                            .findSetDeliveryTimePurchaseList(project);
                }
            } else if (value.equals("4")) {// ????????????????????????
                if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// ????????????????????????
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    list = projectService.findProjectDelayCountList(project);
                } else if (roleNo == 5) {// ??????
                    Integer emailUserId = Integer.parseInt(request
                            .getParameter("userId"));// ???????????????userId
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    project.setEmailUserId(emailUserId);
                    list = projectService.findProjectDelayCountList(project);
                } else if (roleNo == 6) {// ??????
                    Integer purchaseId = Integer.parseInt(request
                            .getParameter("userId"));// ???????????????userId
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    project.setPurchaseId(purchaseId);
                    list = projectService
                            .findProjectDelayPurchaseCountList(project);
                }
            } else if (value.equals("5")) {// ??????????????????????????????
                if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// ????????????????????????
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    list = projectService.findProjectReportNoPicList(project);
                } else if (roleNo == 5) {// ??????
                    Integer emailUserId = Integer.parseInt(request
                            .getParameter("userId"));// ???????????????userId
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    project.setEmailUserId(emailUserId);
                    list = projectService.findProjectReportNoPicList(project);
                } else if (roleNo == 6) {// ??????
                    Integer purchaseId = Integer.parseInt(request
                            .getParameter("userId"));// ???????????????userId
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    project.setPurchaseId(purchaseId);
                    list = projectService
                            .findProjectReportNoPicPurchaseList(project);
                }
            } else {// ?????????????????????????????????
                if (roleNo == 100 || roleNo == 99 || roleNo == 98) {// ????????????????????????
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    List<Project> taskReportList = projectService
                            .findProjectNoTaskReportList(project); // ????????????????????????
                    List<Project> allList = projectService
                            .findProjectList(project); // ?????????????????????
                    list = getDiffrent(allList, taskReportList); // ???????????????????????????????????????
                } else if (roleNo == 5) {// ??????
                    Integer emailUserId = Integer.parseInt(request
                            .getParameter("userId"));// ???????????????userId
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    project.setEmailUserId(emailUserId);
                    List<Project> taskReportList = projectService
                            .findProjectNoTaskReportList(project); // ????????????????????????
                    List<Project> allList = projectService
                            .findProjectList(project); // ??????????????????????????????
                    if (taskReportList.size() > 0) {
                        list = getDiffrent(allList, taskReportList); // ???????????????????????????????????????
                    } else {
                        list = allList;
                    }
                } else if (roleNo == 6) {// ??????
                    Integer purchaseId = Integer.parseInt(request
                            .getParameter("userId"));// ???????????????userId
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);
                    project.setPurchaseId(purchaseId);
                    List<Project> taskReportList = projectService
                            .findProjectNoTaskReportPurchaseList(project); // ????????????????????????
                    List<Project> allList = projectService
                            .findPurchaseProjectList(project); // ??????????????????????????????
                    if (taskReportList.size() > 0) {
                        list = getDiffrent(allList, taskReportList); // ???????????????????????????????????????
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
     * ????????????list?????? ??????????????????????????????
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
                // ???????????????????????????????????????
                if (allProject.getProjectNo().equals(project.getProjectNo())) {
                    bool = true;
                    break;
                }
            }
            // ????????????????????????,????????????????????????????????????????????????
            if (!bool) {
                list.add(allProject);
            }
        }
        return list;
    }

    /**
     * ????????????
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
        // ????????????MIME??????
        response.setContentType(request.getServletContext().getMimeType(
                filename));
        // ??????Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename="
                + filename);
        // ???????????????????????????response??????????????????????????????
        // ?????????????????????????????????
        // ????????????
        InputStream in = new FileInputStream(fileUrl);
        OutputStream out = response.getOutputStream();
        // ?????????
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
            //String userName = request.getParameter("userName");
            String userName = request.getParameter("userName");
            // LOG.info("-----------projectComment sessionId:" + sessionId);
            // String userName = String.valueOf(request.getSession().getAttribute(sessionId));
            // String userName = SessionIdUtil.getUserName(sessionId);
            LOG.info("-----------projectComment userName:" + userName);
            if (StringUtils.isBlank(userName)) {
                userName = WebCookie.getUserName(request);
                if (userName == null) {
                    jsonResult.setOk(false);
                    jsonResult.setMessage("?????????");
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

            //???????????????id
            if (StringUtils.isNotBlank(shippingId)) {
                insertComment.setShippingId(Integer.parseInt(shippingId));
            }
            //????????????
            if (StringUtils.isNotBlank(reportId)) {
                insertComment.setQualityReportId(Integer.parseInt(reportId));
            }
            //??????????????????
            if (StringUtils.isNotBlank(complaintId)) {
                insertComment.setComplaintId(Integer.parseInt(complaintId));
                ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(complaintId));
                User user = userService.findUserByName(userName);
                if (projectComplaint.getSeriousLevel() == 5 && (user.getRoleNo() == 5 || user.getRoleNo() == 6 || user.getRoleNo() == 69 || user.getRoleNo() == 1020)) {
                    projectComplaint.setCompleteTime(new Date());
                    projectComplaintService.updateByPrimaryKeySelective(projectComplaint);
                }
            }
            projectCommentService.addProjetcComment(insertComment);
            Map<String, String> map = new HashMap<String, String>();
            map.put("userName", userName);
            map.put("createDate", DateFormat.format());
            map.put("id", insertComment.getId());
            jsonResult.setOk(true);
            jsonResult.setMessage("????????????");
            jsonResult.setData(map);
            RpcHelper.sendRequest("", insertComment);
        } catch (Exception e) {
            jsonResult.setOk(false);
            jsonResult.setMessage("????????????");
            e.printStackTrace();
            LOG.error("error", e);
        }
        return jsonResult;
    }

    /**
     * ??????id????????????
     *
     * @param request
     * @param response
     * @return JsonResult
     * @Title delComment
     * @Description
     */
    @RequestMapping("/delComment")
    @ResponseBody
    public JsonResult delComment(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            projectCommentService.deleteByPrimaryKey(request.getParameter("id"));
            jsonResult.setOk(true);
            jsonResult.setMessage("????????????");
        } else {
            jsonResult.setOk(false);
            jsonResult.setMessage("????????????");
        }
        return jsonResult;
    }

    /**
     * ??????????????????
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/pauseProject")
    @ResponseBody
    public JsonResult pauseProject(HttpServletRequest request,
                                   HttpServletResponse response) {

        String ipAddr = IpAddressUtil.getIpAddress(request);
        String inName = "/project/pauseProject";

        JsonResult jsonResult = new JsonResult();
        String projectNo = request.getParameter("projectNo");
        String checked = request.getParameter("checked");
        String pauseReason = request.getParameter("pauseReason");
        Project project = new Project();
        project.setProjectNo(projectNo);
        project.setIsPause(checked);
        project.setPauseReason(pauseReason);
        if (StringUtils.isNotBlank(checked)) {
            if ("1".equals(checked)) {
                project.setFinish(0);
                project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
            } else if ("5".equals(checked)) {
                project.setFinish(0);
                project.setProjectStatus(OrderStatusEnum.CANCEL_ORDER.getCode());
            }
        }
        try {
            project.setIpAddr(ipAddr);
            project.setInterfaceName(inName);
            projectService.updateProjectInfo(project);
            //??????????????????
            //ProjectDateTask projectDateTask=new ProjectDateTask();
//			List<Project> projectList=new ArrayList<Project>();
//			projectList.add(project);
//			projectDateTask.syncProjectDate(projectList);
            jsonResult.setOk(true);
            jsonResult.setMessage("????????????");
        } catch (Exception e) {
            jsonResult.setOk(false);
            jsonResult.setMessage("????????????");
            e.printStackTrace();
            LOG.error("error", e);
        }
        return jsonResult;
    }


    /**
     * ??????????????????????????????????????????????????????????????????
     * <p>
     * Date:2018.08.22
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateProjectNew")
    @ResponseBody
    public JsonResult updateProjectNew(HttpServletRequest request, HttpServletResponse response) {
        String ipAddr = IpAddressUtil.getIpAddress(request);
        String inName = "/project/updateProjectNew";

        JsonResult jsonResult = new JsonResult();
        String projectNo = request.getParameter("projectNo");
        String projectStatus = request.getParameter("projectStatus");
        String reason = request.getParameter("reason");
        String time = request.getParameter("time");
        String userName = WebCookie.getUserName(request);
        if (StringUtils.isBlank(userName)) {
            jsonResult.setOk(false);
            jsonResult.setMessage("???????????????");
            return jsonResult;
        }
        Project project = projectService.selectProjctDetails(projectNo);
        //???????????????????????????
        ProjectStatusLog statusLog = new ProjectStatusLog();
        try {
            if (StringUtils.isNotBlank(projectStatus)) {
                Integer status = Integer.parseInt(projectStatus);
                //????????????
                if (status == OrderStatusEnum.SAMPLE_ORDER.getCode()) {
                    project.setSampleFinish(1);
                    if (project.getDeliveryDate() != null) {
                        project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
                        statusLog.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getValue());
                    } else {
                        project.setProjectStatus(OrderStatusEnum.SAMPLE_ORDER.getCode());
                        statusLog.setProjectStatus(OrderStatusEnum.SAMPLE_ORDER.getValue());
                    }
                    project.setSampleFinish(1);
                    project.setSampleFinishTime(DateUtil.StrToDate(time));
                    project.setPauseReason(reason);

                    //??????????????????
                    statusLog.setProjectNo(projectNo);
                    statusLog.setOperator(userName);
                    statusLog.setUpdateDate(new Date());

                } else if (status == OrderStatusEnum.COMPLETE_ORDER.getCode()) {
                    //????????????
                    project.setFinish(1);
                    project.setProjectStatus(OrderStatusEnum.COMPLETE_ORDER.getCode());
                    project.setFinishTime(DateUtil.StrToDate(time));

                    //??????????????????
                    statusLog.setProjectNo(projectNo);
                    statusLog.setOperator(userName);
                    statusLog.setProjectStatus(OrderStatusEnum.COMPLETE_ORDER.getValue());
                    statusLog.setUpdateDate(new Date());
                } else if (status == OrderStatusEnum.NORMAL_ORDER.getCode() && project.getProjectStatus() == OrderStatusEnum.PAUSE_ORDER.getCode()) {
                    //??????????????????
                    project.setFinish(0);
                    project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
                    project.setPauseReason(reason);

                    //????????????????????????????????????????????????????????????
                    ProjectPause projectPause = projectPauseService.selectLastPause(projectNo);
                    if (projectPause != null) {
                        projectPause.setStartDate(DateUtil.StrToDate(time));
                        projectPause.setStartReason(reason);
                        projectPause.setIsPause(0);
                        projectPauseService.updateByPrimaryKeySelective(projectPause);
                    }


                    //??????????????????
                    statusLog.setProjectNo(projectNo);
                    statusLog.setOperator(userName);
                    statusLog.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getValue());
                    statusLog.setUpdateDate(new Date());

                } else if (status == OrderStatusEnum.NORMAL_ORDER.getCode() && project.getProjectStatus() != OrderStatusEnum.PAUSE_ORDER.getCode()) {
                    //??????????????????
                    project.setFinish(0);
                    project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
                    project.setPauseReason(reason);


                    //??????????????????
                    statusLog.setProjectNo(projectNo);
                    statusLog.setOperator(userName);
                    statusLog.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getValue());
                    statusLog.setUpdateDate(new Date());

                } else if (status == OrderStatusEnum.CANCEL_ORDER.getCode()) {
                    //????????????
                    project.setFinish(0);
                    project.setProjectStatus(OrderStatusEnum.CANCEL_ORDER.getCode());
                    project.setPauseReason(reason);
                    project.setFinishTime(DateUtil.StrToDate(time));


                    //??????????????????
                    statusLog.setProjectNo(projectNo);
                    statusLog.setOperator(userName);
                    statusLog.setProjectStatus(OrderStatusEnum.CANCEL_ORDER.getValue());
                    statusLog.setUpdateDate(new Date());

                } else if (status == OrderStatusEnum.PAUSE_ORDER.getCode()) {
                    //????????????
                    int count = projectPauseService.count(projectNo);
                    if (count > 6) {
                        jsonResult.setOk(false);
                        jsonResult.setMessage("?????????????????????7???");
                        return jsonResult;
                    }
                    project.setProjectStatus(OrderStatusEnum.PAUSE_ORDER.getCode());


                    //??????????????????
                    statusLog.setProjectNo(projectNo);
                    statusLog.setOperator(userName);
                    statusLog.setProjectStatus(OrderStatusEnum.PAUSE_ORDER.getValue());
                    statusLog.setUpdateDate(new Date());
                }
            }

            //????????????????????????????????????
            if (StringUtils.isNotBlank(request.getParameter("newPredictDate"))) {
                project.setNewPredictDate(DateUtil.StrToDate(request.getParameter("newPredictDate")));
            } else if ("".equals(request.getParameter("newPredictDate"))) {
                project.setNewPredictDate(null);
            }
            //????????????????????????????????????
            if (StringUtils.isNotBlank(request.getParameter("urgentDeliveryDate"))) {
                project.setUrgentDeliveryDate(DateUtil.StrToDate(request.getParameter("urgentDeliveryDate")));
            } else if ("".equals(request.getParameter("urgentDeliveryDate"))) {
                project.setUrgentDeliveryDate(null);
            }


            //????????????????????????????????????
            if (StringUtils.isNotBlank(request.getParameter("newDeliveryDate"))) {
                String newDeliveryDate = request.getParameter("newDeliveryDate");
//				project.setScheduledDays(days);
                //????????????po???????????????????????? ??????-po??????
                //???????????????po??????????????????????????????-????????????+7???
                Date poDate = project.getDateSampleUploading();
                Date actualStartDate = project.getActualStartDate();
                //????????????po???????????????????????? ??????-po??????
                //???????????????po??????????????????????????????-????????????+7???
                int days = 0;
                if (poDate != null) {
                    days = DateFormat.calDays(DateUtil.StrToDate(newDeliveryDate), poDate);
                } else {
                    days = DateFormat.calDays(DateUtil.StrToDate(newDeliveryDate), actualStartDate);
                    days = days + 7;
                }
                //??????????????????????????????????????????????????????????????????????????????????????????????????????
//				  String newDeliveryDate = null;
//				  if(poDate != null){
//					  newDeliveryDate = DateFormat.addDays(DateFormat.date2String(poDate), days);
//				  }else{
//					  if(actualStartDate != null){
//						  newDeliveryDate = DateFormat.addDays(DateFormat.date2String(actualStartDate), (days-7));
//					  }
//				  }

                //???????????????
                DeliveryDateLog deliveryDateLog = new DeliveryDateLog();
                deliveryDateLog.setCreatePerson(userName);
                deliveryDateLog.setCreateTime(new Date());
                deliveryDateLog.setProjectNo(projectNo);
                deliveryDateLog.setScheduledDays(days);
                if (project.getDeliveryDate() != null) {
                    //????????????
                    deliveryDateLog.setNewDeliveryDate(DateUtil.StrToDate(newDeliveryDate));
                    deliveryDateLog.setOriginalDate(project.getDeliveryDate());
                    deliveryDateLog.setSampleProduction(PRODUCT);
                    project.setDeliveryDate(DateUtil.StrToDate(newDeliveryDate));
                } else if (project.getDeliveryDate() == null && project.getSampleScheduledDate() != null) {
                    //????????????
                    deliveryDateLog.setNewDeliveryDate(DateUtil.StrToDate(newDeliveryDate));
                    deliveryDateLog.setOriginalDate(project.getSampleScheduledDate());
                    deliveryDateLog.setSampleProduction(SAMPLE);
                    project.setSampleScheduledDate(DateUtil.StrToDate(newDeliveryDate));
                }
                project.setIpAddr(ipAddr);
                project.setInterfaceName(inName);
                projectService.updateByPrimaryKey(project, deliveryDateLog);
            } else {
                project.setIpAddr(ipAddr);
                project.setInterfaceName(inName);
                projectService.updateProjectStatus(project, reason, time, statusLog);
            }

            //??????????????????
            jsonResult.setOk(true);
            jsonResult.setMessage("????????????");
        } catch (Exception e) {
            jsonResult.setOk(false);
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
            LOG.error("error", e);
        }
        return jsonResult;
    }


    /*
     * ??????????????????????????????????????????
     */
    @RequestMapping("/queryStaff")
    @ResponseBody
    public JsonResult queryStaff(HttpServletRequest request,
                                 HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();

        try {

            Map<String, List<User>> map = new HashMap<String, List<User>>();

            // ??????
            List<User> purchaseList = userService.selectUserByType(6);

            // ??????&??????
            List<User> saleList = userService.selectUserByType(5);
            Iterator<User> it = saleList.iterator();
            while (it.hasNext()) {
                User userFlag = it.next();
                if (!"??????".equals(userFlag.getJob()) && !"????????????".equals(userFlag.getJob())) {
                    it.remove();
                }
            }
            // ??????
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
     * ??????????????????
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
        String pageNumberStr = request.getParameter("pageNumber");
        int pageNumber = 1;
        if (StringUtils.isNotBlank(pageNumberStr)) {
            pageNumber = Integer.parseInt(pageNumberStr);// ?????????,1,2
        }
        String pcType = "1";
        request.setAttribute("roleNo", roleNo);
        request.setAttribute("userId", userId);
        request.setAttribute("userName", userName);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("pcType", pcType);
        return "project_list_pc";
    }

    /*
     *
     * ??????????????????
     */
    /*
     * ??????????????????????????????????????????
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
            jsonResult.setMessage("????????????");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage(e.getMessage());
            jsonResult.setOk(false);
            LOG.error("error", e);
        }

        return jsonResult;
    }

    /**
     * ???????????????
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadProductFile", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addFactoryFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String productImgName = request.getParameter("productImg");
        String projectImgNo = request.getParameter("projectImgNo");
        String path = UploadAndDownloadPathUtil.getProductImg();
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        //????????????????????????????????????????????????(???????????????form????????????)
        try {
            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload(request, path + File.separator);
            String fileName = multiFileUpload.get(productImgName);
            Project project = new Project();
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
     * ??????????????????
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/agreeDelayApply")
    @ResponseBody
    public JsonResult agreeDelayApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            String type = request.getParameter("type");
            String agreePerson = request.getParameter("userName");
            Project project = projectService.selectProjctDetails(projectNo);
//			Delay delay=delayService.selectApplyDelayByProjectNo(projectNo);
//			if(type.equals("1")){//????????????
//				//1.????????????????????????
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
//			}else if(type.equals("2")){//???????????????
//
//			}

//			delay.setIsAgree(1);
            List<Delay> delayList = delayService.selectDelayByProjectNo(projectNo);

            for (Delay delay : delayList) {
                if ("1".equals(type)) {//????????????
                    delay.setIsAgree(1);
                    delay.setAgreePerson(agreePerson);
                    delay.setAgreeTime(new Date());
                } else if ("2".equals(type)) {
                    delay.setIsAgree(2);
                    delay.setAgreePerson(agreePerson);
                    delay.setAgreeTime(new Date());
                }
            }

            delayService.updateBatch(delayList);
            List<Project> projectList = new ArrayList<Project>();
            projectList.add(project);
            projectDateTask.syncProjectDate(projectList);
            jsonResult.setOk(true);
            jsonResult.setMessage("????????????");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage(e.getMessage());
            jsonResult.setOk(false);
            LOG.error("error", e);
        }
        return jsonResult;
    }


    /**
     * ??????????????????
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addProjectTechnology")
    @ResponseBody
    public JsonResult addProjectTechnology(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            String technology = request.getParameter("technology");
            Project project = new Project();
            project.setProjectNo(projectNo);
            project.setTechnology(technology);
            projectService.updateProjectInfo(project);
            jsonResult.setOk(true);
            jsonResult.setMessage("????????????");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage(e.getMessage());
            jsonResult.setOk(false);
            LOG.error("error", e);
        }
        return jsonResult;
    }


    /**
     * ??????id???????????????
     *
     * @param request
     * @param response
     * @return JsonResult
     * @Title queryNewProject
     * @Description
     */
    @RequestMapping(value = "/queryNewProject")
    public String queryNewProject(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String roleNo = request.getParameter("roleNo");
        String userName = request.getParameter("userName");
        Integer page = 1;
        Integer pageSize = 20;
        if (StringUtils.isNotBlank(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (StringUtils.isNotBlank(request.getParameter("pageSize"))) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }

        Integer pageNumber = (page - 1) * pageSize;
        if (StringUtils.isNotBlank(userId)) {
            List<Project> projectList = projectService.selectByCreatePersonId(Integer.parseInt(userId), Integer.parseInt(roleNo), pageNumber, pageSize);
            int count = projectService.countNewProject(Integer.parseInt(userId), Integer.parseInt(roleNo));
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
     * ???????????????????????????????????????
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addProject")
    @ResponseBody
    public JsonResult addProject(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            Project project = projectService.selectProjctDetails(projectNo);
            if (project != null) {
                jsonResult.setOk(false);
                jsonResult.setMessage("??????????????????");
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

            //???????????????
            if (StringUtils.isNotBlank(predictDate)) {
                project.setNewPredictDate(DateUtil.StrToDate(predictDate));
            }

            //????????????
            if (StringUtils.isNotBlank(plantAnalysis)) {
                project.setPlantAnalysis(Integer.parseInt(plantAnalysis));
            }

            //??????????????????????????????
            if (StringUtils.isNotBlank(require)) {
                project.setRequire(Integer.parseInt(require));
            }
            //??????????????????????????????
            if (StringUtils.isNotBlank(updateInspect)) {
                project.setUpdateInspect(Integer.parseInt(updateInspect));
            }
            //??????????????????
            if (StringUtils.isNotBlank(updateDrawing)) {
                project.setUpdateDrawing(Integer.parseInt(updateDrawing));
            }

            if (user != null) {
                project.setPurchaseId(user.getId());
            }
            project.setCreatePersonId(Integer.parseInt(userId));
            project.setProjectStatus(OrderStatusEnum.NEW_ORDER.getCode());
            projectService.addProject(project);

            RpcSynPurchase rpcSyn = new RpcSynPurchase();
            rpcSyn.sendRequest(null, projectNo, purchaseName);

            jsonResult.setOk(true);
            jsonResult.setMessage("????????????");
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
     * ?????????????????????mail
     *
     * @param request
     * @param response
     * @return JsonResult
     * @Title queryMail
     * @Description
     */
    @RequestMapping(value = "/queryMail")
    @ResponseBody
    public JsonResult queryMail(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        Map<String, Object> map = new HashMap<String, Object>();
        String projectNo = request.getParameter("projectNo");
        Project project = projectService.selectProjctDetails(projectNo);
        if (StringUtils.isNotBlank(project.getSellName())) {
            User user = userService.selectUserByName(project.getSellName());
            map.put("sellEmail", user.getEmailAddress());
        }

        if (StringUtils.isNotBlank(project.getPurchaseName())) {
            User purchaseUser = userService.selectUserByName(project.getPurchaseName());
            map.put("purchaseEmail", purchaseUser.getEmailAddress());
        }

        //???????????????A???B?????????????????????
        if (project.getPlantAnalysis() != null && (project.getPlantAnalysis() == 1 || project.getPlantAnalysis() == 2)) {
            map.put("boss", "edwardfan@sourcing-cn.com");
        }

        jsonResult.setData(map);
        jsonResult.setOk(true);
        return jsonResult;
    }


    /**
     * ???????????????
     *
     * @param request
     * @param response
     * @return JsonResult
     * @Title deleteProject
     * @Description
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResult deleteProject(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            String id = request.getParameter("id");
            if (StringUtils.isNotBlank(id)) {
                projectService.deleteByPrimaryKey(id, projectNo);
                jsonResult.setOk(true);
                jsonResult.setMessage("????????????");
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage(e.getMessage());
            jsonResult.setOk(false);
            LOG.error("error", e);
        }
        return jsonResult;
    }


    //????????????
    @RequestMapping(value = "/exportDelayProject")
    public void exportDelayProject(HttpServletRequest request, HttpServletResponse response) {

        try {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int month1 = cal.get(Calendar.MONTH);
            int year1 = 0;
            cal.add(cal.MONTH, -1);
            int maxCurrentMonthDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //??????15?????????
            String d = year + "-" + month + "-01";
            //???????????????15?????????
            Date endDate = DateUtil.StrToDate(d);
            if (month1 == 12) {
                year1 = year - 1;
            } else {
                year1 = year;
            }
            String d1 = year1 + "-" + month1 + "-01";
            Date startDate = DateUtil.StrToDate(d1);
            List<Project> sampleFinishes = projectService.selectProjectByStatus1(OrderStatusEnum.SAMPLE_ORDER.getCode(), startDate, endDate);
            List<Project> productFinishs = projectService.selectProjectByStatus1(OrderStatusEnum.COMPLETE_ORDER.getCode(), startDate, endDate);
            List<Project> normals = projectService.selectProjectByStatus1(OrderStatusEnum.NORMAL_ORDER.getCode(), startDate, endDate);
            List<Project> suspension = projectService.selectProjectByStatus1(OrderStatusEnum.PAUSE_ORDER.getCode(), startDate, endDate);
            List<Project> cancellation = projectService.selectProjectByStatus1(OrderStatusEnum.CANCEL_ORDER.getCode(), startDate, endDate);

            String excelPath = ProjectStatisticsPrint.printExcel(request, sampleFinishes, productFinishs, normals, suspension, cancellation);
            File outFile = new File(excelPath);
            InputStream fis = new BufferedInputStream(new FileInputStream(outFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // ??????response
            response.reset();
            // ??????response???Header
            Date startDate1 = DateFormat.addMonth(new Date(), -2);
            String fileName = "????????????" + DateFormat.date2String(startDate) + "~" + DateFormat.date2String(endDate) + ".xls";
            fileName = URLEncoder.encode(fileName, "utf-8");                                  //????????????URLEncoder????????????????????????????????????
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
     * ??????????????????
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportCurrFinishProject")
    public void exportCurrFinishProject(HttpServletRequest request, HttpServletResponse response) {
/**
 * panda
 * ???????????????????????????????????????????????????????????????????????????
 * ????????????????????????????????????????????????????????????????????????????????????????????????+7??????
 * ????????????????????????1???1????????? ???1???8???????????? ???7????????? ?????????????????????
 * ???????????????????????????????????? ????????????????????????
 * ????????????????????????
 */
        try {

            LocalDate today = LocalDate.now();


            LocalDate beforeDate = today.minusMonths(1);

            String beginStr = beforeDate.getYear() + "-" + beforeDate.getMonthValue() + "-01";
            Date beginTime = DateUtil.StrToDate(beginStr);
            String endStr = today.getYear() + "-" + today.getMonthValue() + "-01";
            Date endTime = DateUtil.StrToDate(endStr);
            List<Project> finishList = projectService.queryFinishByTime(beginTime, endTime);
            String excelPath = ProjectStatisticsPrint.exportFinishByTimeExcel(finishList);
            File outFile = new File(excelPath);
            InputStream fis = new BufferedInputStream(new FileInputStream(outFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // ??????response
            response.reset();
            // ??????response???Header
            Date startDate1 = DateFormat.addMonth(new Date(), -2);
            String fileName = "???????????????????????????" + DateFormat.date2String(beginTime) + "~" + DateFormat.date2String(endTime) + ".xls";
            fileName = URLEncoder.encode(fileName, "utf-8");                                  //????????????URLEncoder????????????????????????????????????
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
     * ?????????????????????
     *
     * @param request
     * @param response
     * @return void
     * @Title exportProject
     * @Description
     */
    @RequestMapping(value = "/exportProject")
    public void exportProject(HttpServletRequest request, HttpServletResponse response) {
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
            InputStream fis = new BufferedInputStream(new FileInputStream(outFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // ??????response
            response.reset();
            // ??????response???Header
            Date startDate = DateFormat.addMonth(new Date(), -2);
            String fileName = "??????????????????" + DateFormat.date2String(startDate) + "~" + DateFormat.currentDate() + ".xls";
            fileName = URLEncoder.encode(fileName, "utf-8");                                  //????????????URLEncoder????????????????????????????????????
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
     * ??????????????????
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryDetail")
    public String queryDetail(HttpServletRequest request, HttpServletResponse response) {
        String projectNo = request.getParameter("projectNo");
        String roleNo = request.getParameter("roleNo");
        String userId = request.getParameter("userId");
        String userName = WebCookie.getUserName(request);  //??????

        if (StringUtils.isNotBlank(userName)) {
        } else {
            return "redirect:/index.jsp";
        }

        try {
            // 1.????????????????????????
            Project project = projectService.selectProjctDetails(projectNo);
            //????????????
            List<ProjectSchedule> projectSchedules = projectScheduleService.selectByProjectNo(projectNo);
            //??????????????????
            List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectNo);

            request.setAttribute("project", project);
            if (roleNo != null && !"".equalsIgnoreCase(roleNo)) {
                request.setAttribute("roleNo", roleNo);
            } else {
                int roleNo1 = WebCookie.getRoleNo(request);  //??????
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
     * ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????nina????????????
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/editProject")
    public JsonResult editProject(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        String ipAddr = IpAddressUtil.getIpAddress(request);
        String inName = "/project/editProject";
        try {
            String projectNo = request.getParameter("projectNo");
            String deliveryDate = request.getParameter("deliveryDate");
            String sampleScheduledDate = request.getParameter("sampleScheduledDate");
            String projectStatus = request.getParameter("projectStatus");
            String finishTime = request.getParameter("finishTime");
            String sampleFinishTime = request.getParameter("sampleFinishTime");
            String projectAmount = request.getParameter("projectAmount");

            String userName = WebCookie.getUserName(request);  //??????

            if (StringUtils.isBlank(projectNo)) {
                jsonResult.setOk(false);
                jsonResult.setMessage("?????????????????????");
                return jsonResult;
            }
            //??????
            Project project = projectService.selectProjctDetails(projectNo);
            project.setIpAddr(ipAddr);
            project.setInterfaceName(inName);
            if (StringUtils.isNotBlank(projectStatus)) {
                project.setProjectStatus(Integer.parseInt(projectStatus));
            }
            //????????????
            if (StringUtils.isNotBlank(sampleScheduledDate)) {
                project.setSampleScheduledDate(DateUtil.StrToDate(sampleScheduledDate));
                project.setOriginalSampleScheduledDate(DateUtil.StrToDate(sampleScheduledDate));
            } else {
                project.setSampleScheduledDate(null);
                project.setOriginalSampleScheduledDate(null);
            }
            //??????????????????
            if (StringUtils.isNotBlank(sampleFinishTime)) {
                project.setSampleFinish(1);
                project.setSampleFinishTime(DateUtil.StrToDate(sampleFinishTime));
            } else {
                project.setSampleFinish(0);
                project.setSampleFinishTime(null);
            }
            //????????????
            if (StringUtils.isNotBlank(deliveryDate)) {
                project.setDeliveryDate(DateUtil.StrToDate(deliveryDate));
                project.setOriginalDeliveryDate(DateUtil.StrToDate(deliveryDate));
            } else {
                project.setDeliveryDate(null);
                project.setOriginalDeliveryDate(null);
            }
            //??????????????????
            if (StringUtils.isNotBlank(finishTime)) {
                project.setFinish(1);
                project.setFinishTime(DateUtil.StrToDate(finishTime));
            } else {
                project.setFinish(0);
                project.setFinishTime(null);
            }
            if (StringUtils.isNotBlank(projectAmount)) {
                project.setProjectAmount(projectAmount);
            }

            projectService.updateByPrimaryKey(project);


            //??????????????????????????????
            String factoryList = request.getParameter("factoryList");
            if (StringUtils.isNotBlank(factoryList)) {
                List<ProjectFactory> factorys = null;
                ObjectMapper objectMapper = new ObjectMapper();
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ProjectFactory.class);
                factorys = objectMapper.readValue(factoryList, javaType);
                //???????????????????????????????????????????????????????????????????????????????????????
                for (ProjectFactory projectFactory : factorys) {
                    if (StringUtils.isNotBlank(projectFactory.getRepairReplenishmentFinishTime()) && projectFactory.getOrderNature() == 2 && projectFactory.getSampleDate() != null) {
                        projectFactory.setSampleFinishTime(projectFactory.getRepairReplenishmentFinishTime());
                    }
                    if (StringUtils.isNotBlank(projectFactory.getRepairReplenishmentFinishTime()) && projectFactory.getOrderNature() == 2 && projectFactory.getDeliveryDate() != null) {
                        projectFactory.setProductFinishTime(projectFactory.getRepairReplenishmentFinishTime());
                    }
                }
                if (factorys != null && factorys.size() > 0) {
                    projectFactoryService.updateBatch(factorys);
                }
            }


            jsonResult.setOk(true);
            jsonResult.setMessage("????????????");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage("?????????????????????Nina???" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage(e.getMessage());
        }
        return jsonResult;
    }


    /**
     * ??????????????????
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryProject")
    public JsonResult queryProject(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        String projectNo = request.getParameter("projectNo");
        Project project = projectService.selectProjctDetails(projectNo);
        List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectNo);
        //???????????????????????????
        List<AnalysisIssue> analysisIssueList = analysisIssueService.selectByProjectNo(projectNo, null);
        for (AnalysisIssue analysis : analysisIssueList) {
            if (StringUtils.isNotBlank(analysis.getIssue())) {
                List<String> issueList = qualityAnalysisService.selectByIssue(analysis.getIssue());
                analysis.setOccurrenceNum(issueList != null ? issueList.size() : 0);
            }
        }
        //????????????????????????
        List<ProjectFactory> projectFactoryList = projectFactoryService.selectByProjectNo(projectNo);
        List<QualityReport> qualityReportList = qrService.selectByProjectNo(projectNo);
        List<InspectionReservation> reserList = inspectionReservationService.getAllInspection(projectNo);

        int count = inspectionReservationService.selectInspectionReservationCountByProjectNo(projectNo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("reserList", reserList);
        map.put("project", project);
        map.put("projectCheckNumber", count + 1);
        map.put("factoryList", factoryList);
        map.put("analysisIssueList", analysisIssueList);
        map.put("projectFactoryList", projectFactoryList);
        map.put("qualityReportList", qualityReportList);
        jsonResult.setData(map);
        jsonResult.setOk(true);
        return jsonResult;
    }

    /**
     * ???????????????????????????
     *
     * @return Boolean
     * @Title judgeDelay
     * @Description
     */
    public static Map<String, Object> judgeDelay(Project project) {
        Boolean delayType = false;
        Map<String, Object> map = new HashMap<String, Object>();
        int delayDays = 0;   //????????????
        try {
//			Date deliveryDate = project.getDeliveryDate();
            //????????????????????????
//			if(scheduleList != null && scheduleList.size() > 0){
//				 for (ProjectSchedule projectSchedule : scheduleList) {
            Integer isFinish = project.getFinish();
            Date predictDate = project.getDeliveryDate();
            Integer DelayDay = project.getDelayDay();
            Date actualDate = project.getFinishTime();
            Date lastDate = project.getLateDeliveryDate();
            if (isFinish == 1 && actualDate != null && predictDate != null) {
                if (lastDate != null && !"".equals(lastDate)) {
                    if (DateFormat.calDays(actualDate, lastDate) - 7 > 0) {
                        delayType = true;
                        delayDays = DateFormat.calDays(actualDate, lastDate);
                    }

                } else {
                    if (DateFormat.calDays(actualDate, predictDate) - 7 - DelayDay > 0) {
                        delayType = true;
                        delayDays = DateFormat.calDays(actualDate, predictDate) - 7 - DelayDay;
                    }

                }
            } else if (isFinish == 0 && predictDate != null) {
                if (lastDate != null && !"".equals(lastDate)) {
                    if (DateFormat.calDays(new Date(), lastDate) > 0) {
                        delayType = true;
                        delayDays = DateFormat.calDays(new Date(), lastDate);
                    }
                } else if (DateFormat.calDays(new Date(), predictDate) - 7 - DelayDay > 0) {
                    delayType = true;
                    delayDays = DateFormat.calDays(new Date(), predictDate) - 7 - DelayDay;
                }

            }


//				 }
//			}

            //???????????????????????????????????????????????????
            //????????????????????????
            Date sampleScheduledDate = project.getSampleScheduledDate();
            if (sampleScheduledDate != null && project.getDeliveryDate() == null) {
                if (project.getSampleFinish() == 0) {
                    if (lastDate != null && !"".equals(lastDate)) {
                        if (DateFormat.calDays(new Date(), lastDate) > 0) {
                            delayType = true;
                            delayDays = DateFormat.calDays(new Date(), lastDate);
                        }
                    } else {
                        if (DateFormat.calDays(new Date(), sampleScheduledDate) - 7 - DelayDay > 0) {
                            delayType = true;
                            delayDays = DateFormat.calDays(new Date(), sampleScheduledDate) - 7 - DelayDay;
                        }
                    }
                } else if (project.getSampleFinish() == 1 && project.getSampleFinishTime() != null) {
                    if (lastDate != null && !"".equals(lastDate)) {
                        if (DateFormat.calDays(project.getSampleFinishTime(), lastDate) > 0) {
                            delayType = true;
                            delayDays = DateFormat.calDays(project.getSampleFinishTime(), lastDate);
                        }
                    } else {
                        if (DateFormat.calDays(project.getSampleFinishTime(), sampleScheduledDate) - 7 - DelayDay > 0) {
                            delayType = true;
                            delayDays = DateFormat.calDays(project.getSampleFinishTime(), sampleScheduledDate) - 7 - DelayDay;
                        }

                    }
                }
            }


            //????????????????????????????????????????????????????????????????????????
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
        String ip = addr.getHostAddress().toString(); //????????????ip
        return ip;
    }

    /**
     * ???????????????????????????
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/difficultItemListPage")
    public String difficultItemListPage(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        User User1 = userService.selectUserByName(userName);
        String userId = request.getParameter("userId");
        String roleNo = request.getParameter("roleNo");
        Project project = new Project();
        if (userId != null && !"".equals(userId)) {
            project.setEmailUserId(Integer.parseInt(userId));
        } else {
            project.setEmailUserId(User1.getId());
            if ("jerrylong".equalsIgnoreCase(userName)) {
                project.setEmailUserId(0);
            }
        }
        request.setAttribute("userName", userName);
        if ("jerrylong".equalsIgnoreCase(userName)) {
            request.setAttribute("userName", "jerrylong");
        }

        try {
            // 1.????????????????????????
            List<Project> list = projectService.getDifficultItemListPage(project);
            User user = new User();
            user.setRoleNo(5);
            List<User> userList = userService.queryByRoleNo(user);
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
     * ???????????????????????????
     *
     * @param request
     * @param response
     * @return JsonResult
     * @Title deleteProject
     * @Description
     */
    @RequestMapping(value = "/updateDifficultProject")
    @ResponseBody
    public JsonResult updateDifficultProject(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            Project project = new Project();
            String projectNo = request.getParameter("projectNo");
            String difficultProject = request.getParameter("difficultProject");
            Boolean save = projectNo.toLowerCase().contains("shs".toLowerCase());
            project.setDifficultProject(Integer.parseInt(difficultProject));
            if (save != false) {
                project.setProjectNo(projectNo);
                projectService.updateDifficultProject(project);
                jsonResult.setOk(true);
                jsonResult.setMessage("????????????");
            } else {
                String projectNo1 = "SHS" + projectNo;
                project.setProjectNo(projectNo1);
                projectService.updateDifficultProject(project);
                jsonResult.setOk(true);
                jsonResult.setMessage("????????????");
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
     * ????????????????????????
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryFactoryProject")
    public String queryFactoryProject(HttpServletRequest request, HttpServletResponse response) {

        try {
            String pageStr = request.getParameter("pageStr");
            String selectStr = request.getParameter("selectStr");  // ?????????
            String pageSizeStr = request.getParameter("pageSize");
            String factoryName = request.getParameter("factoryName");
            //???????????????0????????? 1????????? 2????????????
            String orderStatus = request.getParameter("orderStatus");
            //???????????????????????????11????????????  2????????????????????? 3??????????????????4???????????????????????????5????????????????????????????????????6???????????????????????????????????????7????????????????????????????????????
            String orderState = request.getParameter("orderState");
            //???????????????1???????????????10???????????????????????????
            String otherSelect = request.getParameter("otherSelect");

            Integer roleNo = null;                               // ????????????????????????????????????
            String userName = WebCookie.getUserName(request);
            String userId = null;
            if (StringUtils.isNotBlank(userName)) {
                User user = userService.findUserByName(userName);
                roleNo = user.getRoleNo();
                userId = user.getId() + "";
            } else {
                return "redirect:/index.jsp";
            }


            //??????
            Integer page = null;
            if (StringUtils.isNotBlank(pageStr)) {
                page = Integer.parseInt(pageStr);
            } else {
                page = 1;
            }
            //???????????????
            Integer pageSize = null;
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            } else {
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
            if (StringUtils.isNotBlank(orderStatus)) {
                projectFactoryQuery.setOrderStatus(Integer.parseInt(orderStatus));
            }
            if (StringUtils.isNotBlank(orderState)) {
                projectFactoryQuery.setOrderState(Integer.parseInt(orderState));
            }
            if (StringUtils.isNotBlank(otherSelect)) {
                projectFactoryQuery.setOtherSelect(Integer.parseInt(otherSelect));
            }
            //????????????????????????????????????
            List<ProjectFactory> factoryList = projectFactoryService.selectProjectList(projectFactoryQuery);
            String factoryId = null;
            if (factoryList != null) {
                for (int i = factoryList.size() - 1; i >= 0; i--) {
                    ProjectFactory projectFactory = factoryList.get(i);
                    Map<String, Object> map = quoteWeeklyReportService.queryGroupByFileType(projectFactory.getProjectNo(), projectFactory.getFactoryId());
                    //??????????????????????????????????????????
                    if ((map.get("videoCount") != null && !"0".equals(map.get("videoCount").toString()))) {
                        projectFactory.setIsUploadVideo(true);
                        //??????????????????(????????????10????????????????????????)
                        if ("1".equals(otherSelect)) {
                            factoryList.remove(i);
                            continue;
                        }
                    } else {
                        projectFactory.setIsUploadVideo(false);
                    }
                    //??????????????????????????????????????????
                    if ((map.get("picCount") != null && !"0".equals(map.get("picCount").toString()))) {
                        projectFactory.setIsUploadPic(true);
                    } else {
                        projectFactory.setIsUploadPic(false);
                    }
                    //????????????????????????
                    if (map.get("materialCount") != null && !"0".equals(map.get("materialCount").toString())) {
                        projectFactory.setIsUploadMaterial(true);
                    } else {
                        projectFactory.setIsUploadMaterial(false);
                    }
                    //??????????????????????????????
                    if (map.get("qualityCount") != null && !"0".equals(map.get("qualityCount").toString())) {
                        projectFactory.setIsUploadQualityReport(true);
                    } else {
                        projectFactory.setIsUploadQualityReport(false);
                    }
                    Project project = projectService.selectProjctDetails(projectFactory.getProjectNo());
                    projectFactory.setPlantAnalysis(project.getPlantAnalysis());
                    List<ProjectInspectionReport> inspectionPlanList = projectInspectionReportService.selectInspectionPlanByProjectNo(projectFactory.getProjectNo());
                    List<ProjectInspectionReport> inspectionPlanList1 = new ArrayList<ProjectInspectionReport>();
                    if (inspectionPlanList.size() > 0) {
                        inspectionPlanList1.add(inspectionPlanList.get(0));
                        projectFactory.setInspectionPlanList(inspectionPlanList1);
                    } else {
                        projectFactory.setInspectionPlanList(null);
                    }

                    List<ProjectComplaint> complaintList = projectComplaintService.selectByProjectNo(projectFactory.getProjectNo());

                    if (complaintList.size() > 0) {
                        projectFactory.setComplaintList(complaintList);
                    } else {
                        projectFactory.setComplaintList(null);
                    }
                    List<QualityReport> qualityReportList = qrService.selectByProjectNo(projectFactory.getProjectNo());
                    List<QualityReport> qualityReportList1 = new ArrayList<QualityReport>();
                    if (qualityReportList.size() > 0) {
                        qualityReportList1.add(qualityReportList.get(0));
                        projectFactory.setQualityReportList(qualityReportList1);
                    } else {
                        projectFactory.setQualityReportList(null);
                    }
                }
            }

            int factoryListCount = projectFactoryService.selectProjectListCount(projectFactoryQuery);
            request.setAttribute("factoryName", factoryName);
            request.setAttribute("factoryId", factoryId);
            request.setAttribute("selectStr", selectStr == null ? "" : selectStr);
            request.setAttribute("factoryList", factoryList);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("page", page);
            request.setAttribute("userName", userName);
            request.setAttribute("count", factoryListCount);
            request.setAttribute("orderStatus", orderStatus);
            request.setAttribute("orderState", orderState);
            request.setAttribute("otherSelect", otherSelect);
            //????????????
            Integer lastNum = new BigDecimal(factoryListCount).divide(new BigDecimal(pageSize)).setScale(0, BigDecimal.ROUND_UP).intValue();
            request.setAttribute("lastNum", lastNum);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            LOG.error("queryFactoryProject error", e);
        }


        return "factory_project_list";
    }


    /**
     * ????????????????????????
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryFactoryList")
    public String queryFactoryList(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pageStr = request.getParameter("pageStr");
            String inputKey = request.getParameter("inputKey");  // ?????????
            String purchaseName = request.getParameter("purchaseName");
            String qualityName = request.getParameter("qualityName");
            String pageSizeStr = request.getParameter("pageSize");
            //??????????????????
            String queryDateStr = request.getParameter("queryDate");
            //??????
            String sortNumStr = request.getParameter("sortNum");
            //??????(??????)
            String upOrDownStr = request.getParameter("upOrDown");


            Integer roleNo = null;                               // ????????????????????????????????????
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isNotBlank(userName)) {
                User user = userService.findUserByName(userName);
                roleNo = user.getRoleNo();
            } else {
                return "redirect:/index.jsp";
            }


            //??????
            Integer page = null;
            if (StringUtils.isNotBlank(pageStr)) {
                page = Integer.parseInt(pageStr);
            } else {
                page = 1;
            }
            //???????????????
            Integer pageSize = null;
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            } else {
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
            if (StringUtils.isNotBlank(queryDateStr)) {
                queryDate = Integer.parseInt(queryDateStr);
                projectFactoryQuery.setQueryDate(queryDate);
            } else {
                queryDate = 2;
                projectFactoryQuery.setQueryDate(2);
            }
            if (StringUtils.isNotBlank(sortNumStr)) {
                projectFactoryQuery.setSortNum(Integer.parseInt(sortNumStr));
            } else {
                projectFactoryQuery.setSortNum(null);
            }
            if (StringUtils.isNotBlank(upOrDownStr)) {
                projectFactoryQuery.setUpOrDown(Integer.parseInt(upOrDownStr));
            } else {
                projectFactoryQuery.setUpOrDown(0);
            }

            List<ProjectFactory> factoryList = projectFactoryService.selectFactoryList(projectFactoryQuery);
				/*for (ProjectFactory projectFactory : factoryList) {
					//????????????????????????(?????????)
					Double delayRate = projectService.selectRateByFactory(projectFactory.getFactoryName(),DELAY);
					if(delayRate!=null){
						delayRate = new BigDecimal(delayRate * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					}
					projectFactory.setDelayRate(delayRate);
					//????????????????????????(??????????????????)
					Double delayRateOnly = projectService.selectRateByFactoryOnly(projectFactory.getFactoryName(),DELAY);
					if(delayRateOnly!=null){
						delayRateOnly = new BigDecimal(delayRateOnly * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					}
					projectFactory.setDelayRateOnly(delayRateOnly);

					//??????????????????(?????????)
					Integer complaintCount = 0;
					if(projectFactory.getComplaintCount() != null){
						complaintCount = projectFactory.getComplaintCount();
					}
					int totalCount = projectFactoryService.selectCountByFactoryName(projectFactory.getFactoryName());
					Double complaintRate = 0.0;  //?????????
					if(totalCount!=0){
						complaintRate = new BigDecimal(complaintCount*100).divide(new BigDecimal(totalCount), BigDecimal.ROUND_HALF_UP, 2).doubleValue();
					}
					projectFactory.setComplaintRate(complaintRate);
					//??????????????????(??????????????????)
					Integer complaintCountOnly = 0;
					if(projectFactory.getComplaintCountOnly() != null){
						complaintCountOnly = projectFactory.getComplaintCountOnly();
					}
					int totalCountOnly = projectFactoryService.selectCountByFactoryNameOnly(projectFactory.getFactoryName());
					Double complaintRateOnly = 0.0;  //?????????
					if(totalCountOnly!=0){
						complaintRateOnly = new BigDecimal(complaintCountOnly*100).divide(new BigDecimal(totalCountOnly), BigDecimal.ROUND_HALF_UP, 2).doubleValue();
					}
					projectFactory.setComplaintRateOnly(complaintRateOnly);

					//??????????????????
					Map<String, Object> scoreMapper = factoryScoreService.selectAvgScore(projectFactory.getFactoryName(),queryDate);
					//?????????
					Double avgScore = 0.0;
					//????????????
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
            //????????????????????????????????????
            Integer purchaseStoreCount = 0;
            Integer qualityStoreCount = 0;
            Map<String, Long> storeCount = trackService.selectFromStore(qualityName, purchaseName, "??????", queryDate);
            if (storeCount != null) {
                if (storeCount.get("purchaseCount") != null) {
                    purchaseStoreCount = storeCount.get("purchaseCount").intValue();
                }
                if (storeCount.get("qualityCount") != null) {
                    qualityStoreCount = storeCount.get("qualityCount").intValue();
                }
            }
            //????????????????????????????????????
            Integer purchaseCompanyCount = 0;
            Integer qualityCompanyCount = 0;
            Map<String, Long> companyCount = trackService.selectFromStore(qualityName, purchaseName, "??????", queryDate);
            if (companyCount != null) {
                if (companyCount.get("purchaseCount") != null) {
                    purchaseCompanyCount = companyCount.get("purchaseCount").intValue();
                }
                if (companyCount.get("qualityCount") != null) {
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
            //????????????
            Integer lastNum = new BigDecimal(factoryListCount).divide(new BigDecimal(pageSize)).setScale(0, BigDecimal.ROUND_UP).intValue();
            request.setAttribute("lastNum", lastNum);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            LOG.error("queryFactoryList error", e);
        }
        return "factory";
    }


    /**
     * ??????????????????  polo  2018.12.28
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title updateProjectSchedule
     * @Description
     */
    @RequestMapping("/addProjectSchedule")
    @ResponseBody
    public JsonResult addProjectSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            String deliveryDate = request.getParameter("deliveryDate");
            if (deliveryDate != null) {
                ProjectSchedule projectSchedule = new ProjectSchedule();
                projectSchedule.setCreateTime(new Date());
                projectSchedule.setProjectNo(projectNo);
                projectSchedule.setPredictDate(DateUtil.StrToDate(deliveryDate));
                projectScheduleService.insertSelective(projectSchedule);
                jsonResult.setOk(true);
                jsonResult.setData(projectSchedule.getId());
            } else {
                jsonResult.setOk(false);
                jsonResult.setMessage("??????????????????");
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
     * ??????????????????  polo  2018.12.28
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title updateProjectSchedule
     * @Description
     */
    @RequestMapping("/deleteProjectSchedule")
    @ResponseBody
    public JsonResult deleteProjectSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonResult jsonResult = new JsonResult();
        try {
            String id = request.getParameter("id");
            if (id != null) {
                projectScheduleService.deleteByPrimaryKey(Integer.parseInt(id));
                jsonResult.setOk(true);
                jsonResult.setMessage("????????????");
            } else {
                jsonResult.setOk(false);
                jsonResult.setMessage("????????????id");
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
     * ?????????????????????????????????
     *
     * @param request
     * @param response
     * @return void
     * @Title exportMonthProject
     * @Description
     */
    @RequestMapping(value = "/exportMonthProject")
    public void exportMonthProject(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Project> productFinishs = projectService.selectMonthProject();
            String excelPath = ProjectStatisticsPrint.exportMonthProject(request, productFinishs);
            File outFile = new File(excelPath);
            InputStream fis = new BufferedInputStream(new FileInputStream(outFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // ??????response
            response.reset();
            // ??????response???Header
            String fileName = "????????????????????????????????????.xls";
            fileName = URLEncoder.encode(fileName, "utf-8");        //????????????URLEncoder????????????????????????????????????
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
     * ???????????????????????????????????????  2019.01.02
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title deleteAnalysis
     * @Description
     */
    @RequestMapping("/deleteAnalysis")
    @ResponseBody
    public JsonResult deleteAnalysis(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String id = request.getParameter("id");
            //???????????????1?????????????????? 2?????????????????????
            String fileType = request.getParameter("fileType");
            if (id != null) {
                QualityAnalysis qualityAnalysis = qualityAnalysisService.selectByPrimaryKey(Integer.parseInt(id));
                Map<String, Object> map = new HashMap<String, Object>();
                if ("1".equals(fileType)) {
                    qualityAnalysis.setQualityAnalysisName(null);
                    qualityAnalysis.setQualityAnalysisNewName(null);
                    qualityAnalysis.setQualityUploadTime(null);
                    qualityAnalysisService.updateByPrimaryKey(qualityAnalysis);


                    map.put("projectNo", qualityAnalysis.getProjectNo());
                    map.put("delete", "qualityAnalysisNewName");
                    String json = SerializeUtil.ObjToJson(map);
                    mqProducerService.sendDataToQueue("qualityDelete", json);
                } else if ("2".equals(fileType)) {
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
                jsonResult.setMessage("????????????");
            } else {
                jsonResult.setOk(false);
                jsonResult.setMessage("????????????id");
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
     * ????????????????????????  2019.01.02
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title deleteAnalysisIssue
     * @Description
     */
    @RequestMapping("/deleteAnalysisIssue")
    @ResponseBody
    public JsonResult deleteAnalysisIssue(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String id = request.getParameter("id");
            if (id != null) {
                analysisIssueService.deleteByPrimaryKey(Integer.parseInt(id));
                jsonResult.setOk(true);
                jsonResult.setMessage("????????????");
            } else {
                jsonResult.setOk(false);
                jsonResult.setMessage("????????????id");
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
     * ???????????????????????????????????????  2019.01.02
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title updateAnalysis
     * @Description
     */
    @RequestMapping("/updateAnalysis")
    @ResponseBody
    public JsonResult updateAnalysis(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String ids = request.getParameter("id");
            //???????????????1?????????????????? 2?????????????????????
            String fileType = request.getParameter("fileType");
            String fileNewName = request.getParameter("fileNewName");
            String fileName = request.getParameter("fileName");
            String projectNo = request.getParameter("projectNo");
            String process = request.getParameter("process");
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isBlank(userName)) {
                jsonResult.setOk(false);
                jsonResult.setMessage("????????????");
                return jsonResult;
            }
            //????????????????????????????????????????????????????????????
            QualityAnalysis qualityAnalysis = new QualityAnalysis();
            if (StringUtils.isNotBlank(ids)) {
                qualityAnalysis = qualityAnalysisService.selectByPrimaryKey(Integer.parseInt(ids));
                if (TECHNOLOGY_ANALYSIS.equals(fileType)) {
                    qualityAnalysis.setTechnologyAnalysisNewName(fileNewName);
                    qualityAnalysis.setTechnologyAnalysisName(fileName);
                    qualityAnalysis.setTechnologyUploadTime(new Date());
                    qualityAnalysis.setProcess(process);
                    qualityAnalysisService.updateByPrimaryKeySelective(qualityAnalysis, userName, 2);
                } else if (QUALITY_ANALYSIS.equals(fileType)) {
                    qualityAnalysis.setQualityAnalysisNewName(fileNewName);
                    qualityAnalysis.setQualityAnalysisName(fileName);
                    qualityAnalysis.setQualityUploadTime(new Date());
                    qualityAnalysis.setProcess(process);
                    qualityAnalysisService.updateByPrimaryKeySelective(qualityAnalysis, userName, 1);
                }
                String json = SerializeUtil.ObjToJson(qualityAnalysis);
                mqProducerService.sendDataToQueue("quality", json);

            } else {
                qualityAnalysis.setProjectNo(projectNo);
                if (TECHNOLOGY_ANALYSIS.equals(fileType)) {
                    qualityAnalysis.setTechnologyAnalysisNewName(fileNewName);
                    qualityAnalysis.setTechnologyAnalysisName(fileName);
                    qualityAnalysis.setTechnologyUploadTime(new Date());
                    qualityAnalysis.setProcess(process);
                    qualityAnalysisService.insertSelective(qualityAnalysis, userName, 2);
                    //?????????rabbitmq exchange
                    String json = SerializeUtil.ObjToJson(qualityAnalysis);
                    mqProducerService.sendDataToQueue("quality", json);

                } else if (QUALITY_ANALYSIS.equals(fileType)) {
                    qualityAnalysis.setQualityAnalysisNewName(fileNewName);
                    qualityAnalysis.setQualityAnalysisName(fileName);
                    qualityAnalysis.setQualityUploadTime(new Date());
                    qualityAnalysis.setProcess(process);
                    qualityAnalysisService.insertSelective(qualityAnalysis, userName, 1);


                    //?????????rabbitmq exchange
                    String json = SerializeUtil.ObjToJson(qualityAnalysis);
                    mqProducerService.sendDataToQueue("quality", json);
                }
            }

            //????????????????????????????????????????????????????????????
            if (StringUtils.isNotBlank(process)) {
                String[] split = process.split(",");
                if (split.length > 0) {
                    for (int i = 0; i < split.length; i++) {
                        List<AnalysisIssue> issueList = analysisIssueService.selectByProcess(split[i]);
                        for (AnalysisIssue analysisIssue : issueList) {
                            analysisIssue.setProjectNo(projectNo);
                            analysisIssue.setCreateTime(new Date());
                            analysisIssue.setIsComplaint(0);
                        }
                        if (issueList != null && issueList.size() > 0) {
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
     * ????????????  2019.01.02
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title addAnalysisIssue
     * @Description
     */
    @RequestMapping("/addAnalysisIssue")
    @ResponseBody
    public JsonResult addAnalysisIssue(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String qualityAnalysisId = request.getParameter("qualityAnalysisId");
            String projectNo = request.getParameter("projectNo");
            String issue = request.getParameter("issue");
            String process = request.getParameter("process");
            String isComplaint = request.getParameter("isComplaint");
            String complaintIdStr = request.getParameter("complaintId");
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isBlank(userName)) {
                jsonResult.setOk(false);
                jsonResult.setMessage("????????????");
                return jsonResult;
            }
            //???????????????
            AnalysisIssue analysisIssue = new AnalysisIssue();
            analysisIssue.setIssue(issue);
            analysisIssue.setProjectNo(projectNo);
            if (StringUtils.isNotBlank(qualityAnalysisId)) {
                analysisIssue.setQualityAnalysisId(Integer.parseInt(qualityAnalysisId));
            }
            if (StringUtils.isNotBlank(isComplaint)) {
                analysisIssue.setIsComplaint(Integer.parseInt(isComplaint));
            } else {
                analysisIssue.setIsComplaint(0);
            }
            if (StringUtils.isNotBlank(complaintIdStr)) {
                analysisIssue.setComplaintId(Integer.parseInt(complaintIdStr));
            }
            analysisIssue.setCreateTime(new Date());
            analysisIssue.setProcess(process);
            analysisIssueService.insertSelective(analysisIssue);
            //???????????????????????????
//	 			List<AnalysisIssue> analysisIssueList = analysisIssueService.selectByProjectNo(projectNo);
//	 			for (AnalysisIssue analysis : analysisIssueList) {
            if (StringUtils.isNotBlank(issue)) {
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
     * ????????????  2019.02.12
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title addAnalysisIssueBatch
     * @Description
     */
    @RequestMapping("/addAnalysisIssueBatch")
    @ResponseBody
    public JsonResult addAnalysisIssueBatch(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
//				String qualityAnalysisId = request.getParameter("qualityAnalysisId");
            //??????????????????
            String issueList = request.getParameter("issueList");
//				String projectNo = request.getParameter("projectNo");
            List<AnalysisIssue> issues = null;
            if (StringUtils.isNotBlank(issueList)) {
                ObjectMapper objectMapper = new ObjectMapper();
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, AnalysisIssue.class);
                issues = objectMapper.readValue(issueList, javaType);
                //???????????????????????????????????????????????????????????????????????????????????????
                for (AnalysisIssue issue : issues) {
                    issue.setCreateTime(new Date());
                    //???????????????????????????
                    if (StringUtils.isNotBlank(issue.getIssue())) {
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
     * ????????????  2019.01.02
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title updateAnalysisIssue
     * @Description
     */
    @RequestMapping("/updateAnalysisIssue")
    @ResponseBody
    public JsonResult updateAnalysisIssue(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String ids = request.getParameter("id");
            String issueReply = request.getParameter("issueReply");
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isBlank(userName)) {
                jsonResult.setOk(false);
                jsonResult.setMessage("????????????");
                return jsonResult;
            }
            User user = userService.selectUserByName(userName);
            //????????????
            if (StringUtils.isNotBlank(ids)) {
                QualityAnalysis qualityAnalysis = qualityAnalysisService.selectByPrimaryKey(Integer.parseInt(ids));
                //????????????
                if (user.getRoleNo() == 6) {
                    qualityAnalysis.setPurchaseReply(issueReply);
                }
                //????????????
                if ("wangweiping".equalsIgnoreCase(userName)) {
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
     * ??????????????????
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title selectByIssue
     * @Description
     */
    @RequestMapping("/selectByIssue")
    @ResponseBody
    public JsonResult selectByIssue(HttpServletRequest request, HttpServletResponse response) {
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
     * ????????????????????????
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title selectByIssue
     * @Description
     */
    @RequestMapping("/selectComplaintIssue")
    @ResponseBody
    public JsonResult selectComplaintIssue(HttpServletRequest request, HttpServletResponse response) {
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
     * ????????????????????????
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title selectIssueList
     * @Description
     */
    @RequestMapping("/selectIssueList")
    public String selectIssueList(HttpServletRequest request, HttpServletResponse response) {
        String issue = request.getParameter("issue");       //??????????????????
        String inputKey = request.getParameter("inputKey"); //?????? 2019.1.7
        String typeStr = request.getParameter("type");         //??????
        String pageStr = request.getParameter("pageStr");   //??????
        String pageSizeStr = request.getParameter("pageSize"); //??????
        //??????
        Integer page = null;
        if (StringUtils.isNotBlank(pageStr)) {
            page = Integer.parseInt(pageStr);
        } else {
            page = 1;
        }
        //???????????????
        Integer pageSize = null;
        if (StringUtils.isNotBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 20;
        }
        Integer pageNumber = pageSize * (page - 1);
        //?????????????????????
        Integer type = 0;
        if (StringUtils.isNotBlank(typeStr)) {
            type = Integer.parseInt(typeStr);
        }
        //??????issue?????????????????????null
        if ("".equals(issue)) {
            issue = null;
        }

        //????????????????????????????????????
        List<Map<String, Object>> list = qualityAnalysisService.selectMapByIssue(issue, inputKey, type, pageNumber, pageSize);
        for (Map<String, Object> m : list) {
            Project project = projectService.selectProjctDetails(m.get("projectNo").toString());
            m.put("projectName", project.getProjectName());
        }
        List<Map<String, Object>> countList = qualityAnalysisService.selectMapByIssue(issue, inputKey, type, null, pageSize);
        request.setAttribute("showList", list);
        request.setAttribute("issue", issue);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("page", page);
        request.setAttribute("type", type);
        Integer totalCount = (countList != null ? countList.size() : 0);
        request.setAttribute("count", totalCount);
        //????????????
        Integer lastNum = new BigDecimal(totalCount).divide(new BigDecimal(pageSize)).setScale(0, BigDecimal.ROUND_UP).intValue();
        request.setAttribute("lastNum", lastNum);

        return "quality_problem";
    }


    /**
     * ???????????????????????????
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title selectProjectView
     * @Description
     */
    @RequestMapping("/selectProjectView")
    public String selectProjectView(HttpServletRequest request, HttpServletResponse response) {
        /**
         * ??????
         */
        String purchaseName = request.getParameter("purchaseName");
        /**
         * ??????
         */
        String sellName = request.getParameter("sellName");
        String pageStr = request.getParameter("page");   //??????
        String pageSizeStr = request.getParameter("pageSize"); //??????
        String userName = WebCookie.getUserName(request);
        if (StringUtils.isBlank(userName)) {
        }
        User user = userService.selectUserByName(userName);
        //??????
        Integer page = null;
        if (StringUtils.isNotBlank(pageStr)) {
            page = Integer.parseInt(pageStr);
        } else {
            page = 1;
        }
        //???????????????
        Integer pageSize = null;
        if (StringUtils.isNotBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
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
        //pageHelper??????
        PageHelper.startPage(page, pageSize);
        List<Project> projectList = projectService.findProjectByStatus(project);
        //??????????????????
        Page<Project> pageList = (Page<Project>) projectList;

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
     * ???????????????????????????????????????
     *
     * @param request
     * @param response
     * @return JsonResult
     * @Title updateProjectView
     * @Description
     */
    @ResponseBody
    @RequestMapping("/updateProjectView")
    public JsonResult updateProjectView(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            String prevSampleDate = request.getParameter("prevSampleDate");
            String customerAttitude = request.getParameter("customerAttitude");
            Project project = new Project();
            project.setProjectNo(projectNo);
            if (StringUtils.isNotBlank(prevSampleDate)) {
                project.setPrevSampleDate(DateUtil.StrToDate(prevSampleDate));
            }
            if (StringUtils.isNotBlank(customerAttitude)) {
                project.setCustomerAttitude(customerAttitude);
            }
            projectService.updateProjectInfo(project);
            jsonResult.setOk(true);
        } catch (Exception e) {
            jsonResult.setOk(false);
            jsonResult.setMessage("????????????");
            e.printStackTrace();
        }

        return jsonResult;
    }


    /**
     * ?????????????????????
     *
     * @param keyExtractor
     * @return Predicate<T>
     * @Title distinctByKey
     * @Description
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    //??????????????????????????????
    @RequestMapping(value = "/completeTasks")
    public void completeTasks(HttpServletRequest request, HttpServletResponse response) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MONTH, -1);
            Date m = c.getTime();

            List<Project> sampleFinishes = projectService.selectCompleteTasks(OrderStatusEnum.SAMPLE_ORDER.getCode(), m);
            List<Project> productFinishs = projectService.selectCompleteTasks(OrderStatusEnum.COMPLETE_ORDER.getCode(), m);

            String excelPath = ProjectStatisticsPrint.printExcel1(request, sampleFinishes, productFinishs);
            File outFile = new File(excelPath);
            InputStream fis = new BufferedInputStream(new FileInputStream(outFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // ??????response
            response.reset();
            // ??????response???Header
            Date startDate = DateFormat.addMonth(new Date(), -2);
            String fileName = "??????????????????" + DateFormat.date2String(startDate) + "~" + DateFormat.currentDate() + ".xls";
            fileName = URLEncoder.encode(fileName, "utf-8");                                  //????????????URLEncoder????????????????????????????????????
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


    //A/B???????????????????????????
    @RequestMapping(value = "/proofingPhaseProject")
    public void proofingPhaseProject(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Project> sampleFinishes = projectService.selectProofingPhaseProject(OrderStatusEnum.NORMAL_ORDER.getCode());
            String excelPath = ProjectStatisticsPrint.printExcel1(request, sampleFinishes);
            File outFile = new File(excelPath);
            InputStream fis = new BufferedInputStream(new FileInputStream(outFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // ??????response
            response.reset();
            // ??????response???Header
            Date startDate = DateFormat.addMonth(new Date(), -2);
            String fileName = "A/B???????????????????????????" + DateFormat.date2String(startDate) + "~" + DateFormat.currentDate() + ".xls";
            fileName = URLEncoder.encode(fileName, "utf-8");                                  //????????????URLEncoder????????????????????????????????????
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
     * ??????????????????????????????
     *
     * @param request
     * @param response
     * @return JsonResult
     * @Title updateProjectView
     * @Description
     */
    @ResponseBody
    @RequestMapping("/uploadInspectionPlan")
    public JsonResult uploadInspectionPlan(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String projectTaskId = request.getParameter("projectTaskId");
            ProjectTask projectTask = projectTaskService.selectProjectTaskById(Integer.parseInt(projectTaskId));
            int num = productionPlanService.getInspectionPlan(projectTask.getStartTime(), projectTask.getProjectNo());
            if (num > 0) {
                jsonResult.setOk(true);
            } else {
                jsonResult.setOk(false);
            }
        } catch (Exception e) {
            jsonResult.setOk(false);
            jsonResult.setMessage("????????????");
            e.printStackTrace();
        }

        return jsonResult;
    }

    /**
     * ????????????????????????????????????
     *
     * @param request
     * @param response
     * @return JsonResult
     * @Title updateProjectView
     * @Description
     */
    @ResponseBody
    @RequestMapping("/updateBargain")
    public JsonResult updateBargain(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();

        String ipAddr = IpAddressUtil.getIpAddress(request);
        String inName = "/project/updateBargain";
        try {
            String projectNo = request.getParameter("projectNo");
            String id = request.getParameter("id");
            String num = request.getParameter("num");
            int number = 0;
            Project project1 = projectService.selectProjctDetails(projectNo);
            Project project = new Project();
            project.setIpAddr(ipAddr);
            project.setInterfaceName(inName);
            project.setProjectNo(projectNo);
            if ("0".equals(num)) {
                project.setProjectStatus(1);
                if (project1.getSampleFinishTime() != null) {
                    project.setSampleFinish(0);
                    project.setSampleFinishTime(project1.getSampleFinishTime());
                }
                if (project1.getFinishTime() != null) {
                    project.setFinish(0);
                    project.setFinishTime(project1.getFinishTime());
                }
                number = projectService.updateStatus(project);
                ProjectFactory factory = new ProjectFactory();
                factory.setId(Integer.parseInt(id));
                factory.setFinish(0);
                int num1 = projectFactoryService.updateStatus(factory);
            } else if ("1".equals(num)) {
                project.setProjectStatus(1);

                number = projectService.updateStatus(project);
            } else if ("2".equals(num)) {
                project.setProjectStatus(4);

                number = projectService.updateStatus(project);
            } else if ("3".equals(num)) {
                project.setProjectStatus(5);

                number = projectService.updateStatus(project);
            }
            if (number > 0) {
                jsonResult.setOk(true);
            } else {
                jsonResult.setOk(false);
            }
        } catch (Exception e) {
            jsonResult.setOk(false);
            jsonResult.setMessage("????????????");
            e.printStackTrace();
        }

        return jsonResult;
    }

    /**
     * @param request
     * @param response
     * @return JsonResult
     * @throws
     * @Title:ProjectController
     * @Description:???????????????????????????
     * @author wangyang
     */
    @ResponseBody
    @RequestMapping("/modifyState")
    public JsonResult modifyState(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();

        String ipAddr = IpAddressUtil.getIpAddress(request);
        String inName = "/project/modifyState";

        try {
            String userName = WebCookie.getUserName(request);
            String projectNo = request.getParameter("projectNo");
            String id = request.getParameter("id");
            ProjectFactory factory1 = projectFactoryService.selectByPrimaryKey(Integer.parseInt(id));
            Project project = new Project();
            project.setIpAddr(ipAddr);
            project.setInterfaceName(inName);
            project.setProjectNo(projectNo);
            ProjectFactory factory = new ProjectFactory();
            ProjectFactory factory2 = new ProjectFactory();
            factory.setId(Integer.parseInt(id));
            factory2.setId(Integer.parseInt(id));
            String rework1 = request.getParameter("rework");
            String finished1 = request.getParameter("finished");
            String finished = "";
            String rework = "";
            if ("0".equals(finished1)) {
                finished = rework1;
            } else if ("1".equals(finished1)) {
                rework = rework1;
            }
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (finished != null && !"".equalsIgnoreCase(finished)) {
                if ("1".equalsIgnoreCase(finished)) {
                    factory.setRework(factory1.getRework());
                    factory.setFinish(1);
                    if (factory1.getSampleFinishTime() == null) {
                        factory.setSampleDate(new Date());
                    }
                    if (factory1.getDeliveryDate() != null) {
                        factory.setDeliveryDate(new Date());
                    }
                    if (factory1.getModificationRecord() != null) {
                        String ModificationRecord = factory1.getModificationRecord() + ";" + userName + "???" + sdf.format(date) + "???????????????????????????";
                        factory.setModificationRecord(ModificationRecord);
                    } else {
                        String ModificationRecord = userName + "???" + sdf.format(date) + "???????????????????????????";
                        factory.setModificationRecord(ModificationRecord);
                    }
                    factory.setRework(2);
                    int num1 = projectFactoryService.updateStatus(factory);
                    //project.setFinish(1);
                    int count = projectFactoryService.getCompletedCount(project);//??????????????????????????????
                    if (count == 0) {
                        Project project1 = projectService.selectProjctDetails(projectNo);
                        if (factory1.getSampleDate() != null && factory1.getDeliveryDate() != null) {
                            project.setProjectStatus(2);
                            project.setSampleFinish(1);
                            if (project1.getSampleFinishTime() == null) {
                                project.setSampleFinishTime(new Date());
                            }
                            project.setFinish(1);
                            project.setFinishTime(new Date());
                        } else if (factory1.getDeliveryDate() != null) {
                            project.setProjectStatus(2);
                            project.setFinish(1);
                            project.setFinishTime(new Date());
                        } else if (factory1.getSampleDate() != null) {
                            project.setProjectStatus(6);
                            project.setSampleFinish(1);
                            project.setSampleFinishTime(new Date());
                        }

                        projectService.updateStatus(project);
                    }

                } else if ("0".equalsIgnoreCase(finished)) {
                    project.setProjectStatus(1);
                    factory.setFinish(0);
                    if (factory1.getModificationRecord() != null) {
                        String ModificationRecord = factory1.getModificationRecord() + ";" + userName + "???" + sdf.format(date) + "??????????????????????????????";
                        factory.setModificationRecord(ModificationRecord);
                    } else {
                        String ModificationRecord = userName + "???" + sdf.format(date) + "??????????????????????????????";
                        factory.setModificationRecord(ModificationRecord);
                    }
                    factory.setRework(2);
                    int num = projectService.updateStatus(project);
                    int num1 = projectFactoryService.updateStatus(factory);
                }
            }
            if (rework != null && !"".equalsIgnoreCase(rework)) {
                if ("1".equalsIgnoreCase(rework)) {
                    factory2.setRework(1);
                    factory2.setFinish(2);
                    int num1 = projectFactoryService.updateStatus(factory2);
                } else if ("0".equalsIgnoreCase(rework)) {
                    factory2.setRework(0);
                    factory2.setFinish(2);
                    int num1 = projectFactoryService.updateStatus(factory2);
                }
            }


            jsonResult.setOk(true);

        } catch (Exception e) {
            jsonResult.setOk(false);
            jsonResult.setMessage("????????????");
            e.printStackTrace();
        }

        return jsonResult;
    }

    /**
     * ????????????????????????
     *
     * @param
     * @param request
     * @param model
     * @return String
     * @throws IllegalStateException
     * @throws IOException
     * @Title upload
     * @Description
     */
    @ResponseBody
    @RequestMapping(value = "/uploadInterpretationDocument")
    public JsonResult uploadInterpretationDocument(HttpServletRequest request, ModelMap model) {

        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            //??????????????????
            String path = UploadAndDownloadPathUtil.getProductImg() + File.separator + projectNo + File.separator;
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_file(request, path, projectNo);
            //????????????
            String originalFilename = "";
            String newFileName = "";
            if (multiFileUpload != null && multiFileUpload.size() > 0) {
                Set<String> keySet = multiFileUpload.keySet();
                for (String key : keySet) {
                    newFileName = multiFileUpload.get(key);
                    originalFilename = key;
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("path", path + newFileName);
            map.put("originalFilename", originalFilename);
            map.put("newFileName", newFileName);
            jsonResult.setData(map);
            jsonResult.setOk(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage("????????????");
            jsonResult.setOk(false);
        }
        return jsonResult;

    }

    /**
     * @param request
     * @param response
     * @return JsonResult
     * @throws
     * @Title:ProjectController
     * @Description:??????????????????
     * @author wangyang
     */
    @ResponseBody
    @RequestMapping("/updateExtensionDocument")
    public JsonResult updateExtensionDocument(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String projectNo = request.getParameter("projectNo");
            String fileName = request.getParameter("fileName");
            String dateDelivery = request.getParameter("dateDelivery");
            String delayDays = request.getParameter("delayDays");
            String explain = request.getParameter("explain");
            String lateDeliveryDate = request.getParameter("lateDeliveryDate");
            Project project1 = projectService.selectProjectByProjectNo(projectNo);
            project1.setProjectNo(projectNo);
            if (fileName != null && !"".equalsIgnoreCase(fileName)) {
                project1.setInterpretationDocument(fileName);
            }
            if (dateDelivery != null && !"".equalsIgnoreCase(dateDelivery)) {
                project1.setDateDelivery(format.parse(dateDelivery));
            }
            if (explain != null && !"".equalsIgnoreCase(explain)) {
                project1.setExplain(explain);
                ;
            }
            if (delayDays != null && !"".equals(delayDays)) {
                project1.setDelayDay(Integer.parseInt(delayDays));
                Date dateSample = project1.getOriginalSampleScheduledDate();
                Date completionTime = project1.getOriginalDeliveryDate();
                if (dateSample != null) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(dateSample);
                    c.add(Calendar.HOUR_OF_DAY, Integer.parseInt(delayDays));
                    Date newDate = c.getTime();
                    project1.setDelayedDeliveryDate(newDate);
                }
                if (completionTime != null) {
                    Date newDate = addDate(completionTime, Integer.parseInt(delayDays));
                    project1.setCargoDelayedDeliveryDate(newDate);
                    ;
                }
            }
            if (lateDeliveryDate != null && !"".equalsIgnoreCase(lateDeliveryDate)) {
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
            jsonResult.setMessage("????????????");
            e.printStackTrace();
        }

        return jsonResult;
    }

    public static Date addDate(Date date, long day) {
        long time = date.getTime(); // ??????????????????????????????
        day = day * 24 * 60 * 60 * 1000; // ????????????????????????????????????
        time += day; // ???????????????????????????
        return new Date(time); // ???????????????????????????
    }

    /**
     * @param request
     * @param response
     * @return JsonResult
     * @throws
     * @Title:ProjectController
     * @Description:??????????????????
     * @author wangyang
     */
    @ResponseBody
    @RequestMapping("/delayMessage")
    public JsonResult delayMessage(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        String projectNo = request.getParameter("projectNo");
        Project project = projectService.selectProjctDetails(projectNo);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("dateDelivery", project.getDateDelivery());
        map.put("delayDay", project.getDelayDay());
        if (project.getExplain() != null && !"".equalsIgnoreCase(project.getExplain())) {
            map.put("explain", project.getExplain());
        } else {
            map.put("explain", "");
        }
        if (project.getInterpretationDocument() != null && !"".equalsIgnoreCase(project.getInterpretationDocument())) {
            map.put("interpretationDocument", project.getInterpretationDocument());
        } else {
            map.put("interpretationDocument", "");
        }

        jsonResult.setOk(true);
        jsonResult.setData(map);
        return jsonResult;
    }

    /**
     * @param request
     * @param response
     * @return String
     * @throws
     * @Title:ProjectController
     * @Description:???????????????????????????
     * @author wangyang
     */
    @RequestMapping("/productingProjectDetail")
    public String productingProjectDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pageStr = request.getParameter("pageStr");
            String inputKey = request.getParameter("inputKey");  // ?????????
            String purchaseName = request.getParameter("purchaseName");
            String qualityName = request.getParameter("qualityName");
            String pageSizeStr = request.getParameter("pageSize");
            //??????????????????
            String queryDateStr = request.getParameter("queryDate");
            //??????
            String sortNumStr = request.getParameter("sortNum");
            //??????(??????)
            String upOrDownStr = request.getParameter("upOrDown");


            Integer roleNo = null;                               // ????????????????????????????????????
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isNotBlank(userName)) {
                User user = userService.findUserByName(userName);
                roleNo = user.getRoleNo();
            } else {
                return "redirect:/index.jsp";
            }


            //??????
            Integer page = null;
            if (StringUtils.isNotBlank(pageStr)) {
                page = Integer.parseInt(pageStr);
            } else {
                page = 1;
            }
            //???????????????
            Integer pageSize = null;
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            } else {
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
            if (StringUtils.isNotBlank(queryDateStr)) {
                queryDate = Integer.parseInt(queryDateStr);
                projectFactoryQuery.setQueryDate(queryDate);
            } else {
                queryDate = 2;
                projectFactoryQuery.setQueryDate(2);
            }
            if (StringUtils.isNotBlank(sortNumStr)) {
                projectFactoryQuery.setSortNum(Integer.parseInt(sortNumStr));
            } else {
                projectFactoryQuery.setSortNum(null);
            }
            if (StringUtils.isNotBlank(upOrDownStr)) {
                projectFactoryQuery.setUpOrDown(Integer.parseInt(upOrDownStr));
            } else {
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
            //????????????
            Integer lastNum = new BigDecimal(factoryListCount).divide(new BigDecimal(pageSize)).setScale(0, BigDecimal.ROUND_UP).intValue();
            request.setAttribute("lastNum", lastNum);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            LOG.error("productingProjectDetail error", e);
        }

        return "producting_project_detail";
    }

    /**
     * @param request
     * @param response
     * @return String
     * @throws
     * @Title:ProjectController
     * @Description:????????????????????????????????????
     * @author wangyang
     */
    @RequestMapping("/productingProject")
    public String productingProject(HttpServletRequest request, HttpServletResponse response) {
        try {
            String factoryName = request.getParameter("factoryName");
            String pageStr = request.getParameter("pageStr");

            String pageSizeStr = request.getParameter("pageSize");
            //??????
            Integer page = null;
            if (StringUtils.isNotBlank(pageStr)) {
                page = Integer.parseInt(pageStr);
            } else {
                page = 1;
            }
            //???????????????
            Integer pageSize = null;
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            } else {
                pageSize = 50;
            }
            Project project = new Project();
            project.setFactoryName(factoryName);
            project.setPageNumber(pageSize * (page - 1));
            project.setPageSize(pageSize);
            List<Project> projectList = projectService.getProductingProject(project);
            for (int i = 0; i < projectList.size(); i++) {
                String status = "";
                Project project1 = projectList.get(i);
                Project project2 = projectService.selectProjctDetails(project1.getProjectNo());
                project1.setProjectMembers(project2.getSellName() + "/" + project2.getPurchaseName() + "/" + project2.getZhijian1() + "/" + project2.getZhijian2());
                if (project1.getProjectStatus() != OrderStatusEnum.NEW_ORDER.getCode() && project1.getProjectStatus() != OrderStatusEnum.CANCEL_ORDER.getCode() && project1.getProjectStatus() != OrderStatusEnum.PAUSE_ORDER.getCode()) {
                    Map<String, Object> map = judgeDelay(project2);
                    Boolean judgeDelay = (Boolean) map.get("delayType");
                    int delayDays = Integer.parseInt(map.get("delayDays").toString());
                    project1.setDelayDays(delayDays);
                    if (judgeDelay == true) {
                        status = "?????????";
                        project1.setStatus(status);
                    }
                }
                ProjectTask task = new ProjectTask();
                task.setTaskStatus("0");
                task.setTaskType("2");
                task.setProjectNo(project1.getProjectNo());
                int incompleteInspectionTasks = projectTaskService.getInspectionTaskNotCompleted(task);//??????????????????????????????????????????
                project1.setIncompleteInspectionTasks(incompleteInspectionTasks);
                QualityReport report = new QualityReport();
                report.setProjectNo(project1.getProjectNo());
                int inspectionReport = qrService.getReport(report);
                project1.setRecentInspectionReport(inspectionReport);
                ProjectFactory factory = new ProjectFactory();
                factory.setProjectNo(project1.getProjectNo());
                factory.setFactoryName(project1.getFactoryName());
                int contractNumber = projectFactoryService.getFactory(factory);
                project1.setContractNumber(contractNumber);
            }
            int projectCount = projectService.getProductingProjectCount(project);
            Integer lastNum = new BigDecimal(projectCount).divide(new BigDecimal(pageSize)).setScale(0, BigDecimal.ROUND_UP).intValue();
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
     * ??????????????????
     *
     * @param request
     * @param response
     * @return JsonResult
     * @throws IOException
     * @Title updateProjectSchedule
     * @Description
     */
    @RequestMapping("/updateDeliveryDateModification")
    @ResponseBody
    public JsonResult updateDeliveryDateModification(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            String num = request.getParameter("num");
            String time = request.getParameter("time");
            Project project = new Project();
            if ("1".equalsIgnoreCase(num)) {
                project.setSampleDeliveryDate(time);
            } else if ("2".equalsIgnoreCase(num)) {
                project.setDeliveryTime(time);
            }
            project.setProjectNo(projectNo);
            projectService.updateByPrimaryKeySelective(project);
            jsonResult.setOk(true);
            jsonResult.setMessage("????????????");
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
    public CommonResult SaveDataToERP(HttpServletRequest request, HttpServletResponse response) {
        CommonResult res = new CommonResult(request.getRequestURI());
        try {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour == 1) {
                getAllBargain();
            }
            List<PayOthers> payOtherList = payOtherService.getAllPending();
            for (int i = 0; i < payOtherList.size(); i++) {
                int num = itemCaseERPService.findName(payOtherList.get(i).getCaseno(), payOtherList.get(i).getApplicant(), 1);//?????????????????????ERP???????????????
                int num1 = 0;
                if ("?????????".equalsIgnoreCase(payOtherList.get(i).getPaymentType())) {
                    num1 = 1;
                } else {
                    num1 = itemCaseERPService.findName(payOtherList.get(i).getCaseno(), payOtherList.get(i).getApplicant(), 2);//?????????????????????ERP???????????????
                }
                if (num == 0) {
                    payOtherService.updatePassERP(payOtherList.get(i).getId(), 3);
                } else if (num1 == 0) {
                    payOtherService.updatePassERP(payOtherList.get(i).getId(), 2);
                    // ?????????????????????
                    DingTalkModel model = new DingTalkModel("", null, payOtherList.get(i).getProcessInstanceId(),
                            "?????????????????????????????????", payOtherList.get(i).getCaseno(), null);
                    RpcQualityNoticeToKuai.sendRequest(model);
                } else {
                    if ("agree".equalsIgnoreCase(payOtherList.get(i).getProcessInstanceResult())) {
                        FactoryFund fac = new FactoryFund();
                        fac.setCaseno(payOtherList.get(i).getCaseno());
                        fac.setName(payOtherList.get(i).getApplicant());
                        fac.setMoney(payOtherList.get(i).getApplicationAmount());
                        fac.setMoneydate(payOtherList.get(i).getMailingDate());
                        fac.setMoneytype(payOtherList.get(i).getImoneytype());
                        fac.setPayDepartment(payOtherList.get(i).getPaymentDepartment());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(payOtherList.get(i).getMailingDate());
                        String content = "??????????????????" + payOtherList.get(i).getPaymentType();
                        content = " " + content + " " + payOtherList.get(i).getApplicant() + " " + payOtherList.get(i).getPaymentInstructions() + " " + dateString;
                        fac.setContent(content);
                        fac.setState("<font color=green>???????????????</font>");
                        String apNumber = itemCaseERPService.getApNumber();//??????apnumber
                        fac.setApNumber(apNumber);
                        factoryFundService.insertAll(fac);
                        itemCaseERPService.insert(apNumber);
                        payOtherService.updatePassERP(payOtherList.get(i).getId(), 1);
                    }

                }

            }


            List<Project> projectList = projectService.getReturnItem();
            for (int i = 0; i < projectList.size(); i++) {
                Project project1 = projectList.get(i);
                String projectNo = project1.getProjectNo();
                String[] projectNo1 = projectNo.split("-");
                Project project = new Project();
                project.setProjectNo(projectNo);
                project.setProjectName(projectNo1[0]);
                Project project2 = projectService.getzhijian(project);
                if (project2 != null) {
                    String zhijain1 = project2.getZhijian1();
                    String zhijain2 = project2.getZhijian2();
                    String zhijain3 = project2.getZhijian3();
                    String zhijian = "";
                    if (zhijain1 != null && !"".equals(zhijain1)) {
                        zhijian += "," + zhijain1;
                    }
                    if (zhijain2 != null && !"".equals(zhijain2)) {
                        zhijian += "," + zhijain2;
                    }
                    if (zhijain3 != null && !"".equals(zhijain3)) {
                        zhijian += "," + zhijain3;
                    }

                    zhijian = zhijian.replaceFirst(",", "");
                    project.setHistoryInspection(zhijian);
                    projectService.updateHistoryInspection(project);
                }
            }
            ComplaintList complist1 = complaintListService.getLate();
            ProjectComplaint complaint1 = new ProjectComplaint();
            if (complist1 != null) {
                complaint1.setId(complist1.getComplaintId());
            } else {
                complaint1.setId(0);
            }
            List<ComplaintList> list1 = new ArrayList<ComplaintList>();
            List<ProjectComplaint> Complaintlist = projectComplaintService.getAll(complaint1);
            if (Complaintlist.size() > 0) {
                for (int i = 0; i < Complaintlist.size(); i++) {
                    ComplaintList complaint2 = new ComplaintList();
                    complaint2.setComplaintId(Complaintlist.get(i).getId());
                    complaint2.setCaseNo(Complaintlist.get(i).getProjectNo());
                    complaint2.setComplaintState(Complaintlist.get(i).getSeriousLevel().toString());
                    list1.add(complaint2);
                }
                complaintListService.batchAddInspectionReport(list1);
            }
            int num = 0;
            List<QuotePrice1> quoteList = new ArrayList<QuotePrice1>();
            QuotePrice1 price = quotePriceService.getLate();
            int num1 = 0;
            if (price != null) {
                num1 = price.getKuzhizao();
            }
            List<QuoteWeeklyReport> list = quoteWeeklyReportService.getAll(num1);
            for (int i = 0; i < list.size(); i++) {
                QuotePrice1 price1 = new QuotePrice1();
                price1.setCaseNo(list.get(i).getCsgOrderId());
                price1.setEmployeeName("Emily");
                price1.setKuzhizao(list.get(i).getId());
                price1.setCurrentStatus("?????????????????????");
                price1.setUploadUrl("https://www.kuaizhizao.cn" + list.get(i).getPhotoPathCompress());
                quoteList.add(price1);
            }
            if (quoteList.size() > 0) {
                quotePriceService.addAll(quoteList);
            }
            List<ProjectComplaint> complaintlist = projectComplaintService.unprocessedItems();//???????????????????????????
            itemCaseERPService.update("");
            for (int j = 0; j < complaintlist.size(); j++) {
                ProjectComplaint complaint = complaintlist.get(j);
                int total = itemCaseERPService.update(complaint.getProjectNo());
            }
            num = 1;
            QuotePrice1 price1 = quotePriceService.getLateOne();
            int num2 = 0;
            if (price1 != null) {
                num2 = price1.getDingding();
            }
            List<QuotePrice> quotePriceList = tquotePriceService.getall(num2);
            List<QuotePrice1> quotePriceList1 = new ArrayList<QuotePrice1>();
            for (int i = 0; i < quotePriceList.size(); i++) {
                QuotePrice quote = quotePriceList.get(i);
                if (quote.getUploadurl() != null && !"".equalsIgnoreCase(quote.getUploadurl())) {
                    String[] url = quote.getUploadurl().split(",");
                    for (int j = 0; j < url.length; j++) {
                        QuotePrice1 quote1 = new QuotePrice1();
                        quote1.setCaseNo(quote.getProjectNo());
                        quote1.setCurrentStatus(quote.getCurrentStatus());
                        quote1.setDingding(quote.getId());
                        quote1.setUploadUrl(url[j]);
                        quote1.setEmployeeName(quote.getEmployeName());
                        quote1.setRedirectUser(quote.getRedirectUser());
                        quotePriceList1.add(quote1);
                    }
                } else {
                    QuotePrice1 quote1 = new QuotePrice1();
                    quote1.setCaseNo(quote.getProjectNo());
                    quote1.setCurrentStatus(quote.getCurrentStatus());
                    quote1.setDingding(quote.getId());
                    quote1.setEmployeeName(quote.getEmployeName());
                    quote1.setRedirectUser(quote.getRedirectUser());
                    quotePriceList1.add(quote1);
                }
            }
            // ???????????????30???????????????
            if (quotePriceList1.size() > 0) {
                int size = quotePriceList1.size();
                int cicle = size % 10 > 0 ? size / 10 + 1 : size / 10;
                for (int i = 1; i <= cicle; i++) {
                    int limitSize = Math.min(i * 10, size);
                    List<QuotePrice1> price1List = quotePriceList1.stream().skip((i - 1) * 10).limit(limitSize).collect(Collectors.toList());
                    quotePriceService.addAll1(price1List);
                    price1List.clear();
                }

                for (QuotePrice1 quotePrice1 : quotePriceList1) {
                    if (StringUtils.isNotEmpty(quotePrice1.getRedirectUser())) {
                        ProjectERP projectERP = new ProjectERP();
                        projectERP.setProjectNo(quotePrice1.getCaseNo());
                        projectERP.setQualityInspector7(quotePrice1.getRedirectUser());
                        itemCaseERPService.updateQuality(projectERP);//????????????
                    }

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
            List<Bargain> bargainList = bargainService.getAll();//????????????50?????????????????????
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

            List<Project> projectList = projectService.getAllMaterial();
            for (int i = 0; i < projectList.size(); i++) {
                sysnByProjectErp(projectList.get(i).getProjectNo());
            }

        } catch (Exception e) {

        }


    }


    //???????????????????????????????????????
    @RequestMapping(value = "/projectExportProgress")
    public void projectExportProgress(HttpServletRequest request, HttpServletResponse response) {
        try {
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            ProjectERP projectERP = new ProjectERP();
            projectERP.setStartTime(startTime);
            projectERP.setEndTime(endTime);
            List<ProjectERP> projectErpList = itemCaseERPService.getProjectExportProgress(projectERP);//???????????????????????????????????????????????????


            //???????????????????????????
            //List<Project> allProjectExport = projectService.selectProjectExport(m);

            String excelPath = ProjectStatisticsPrint.printProjectExportProgress(request, projectErpList);
            File outFile = new File(excelPath);
            InputStream fis = new BufferedInputStream(new FileInputStream(outFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // ??????response
            response.reset();
            // ??????response???Header

            String fileName = "????????????:" + startTime + "???" + endTime + "??????????????????.xls";
            fileName = URLEncoder.encode(fileName, "utf-8");                                  //????????????URLEncoder????????????????????????????????????
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

    //???????????????????????????????????????
    @RequestMapping(value = "/ongoingProjects")
    public void ongoingProjects(HttpServletRequest request, HttpServletResponse response) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            //c.add(Calendar.DATE, -1);
            Date m = c.getTime();
            String time = format.format(m);

            System.setProperty("java.awt.headless", "true");
            //???????????????????????????
            List<Project> allProjectExport = projectService.selectProjectExport(time);


            if (CollectionUtils.isNotEmpty(allProjectExport)) {
                List<String> itemList = new ArrayList<>();

                for (Project project : allProjectExport) {
                    itemList.add(project.getProjectNo());
                }
                List<InvoiceInfo> invoiceInfos = invoiceInfoService.getPayDate(itemList);

                Map<String, InvoiceInfo> invoiceInfoMap = new HashMap<>();
                invoiceInfos.forEach(e -> invoiceInfoMap.put(e.getCaseNo(), e));
                invoiceInfos.clear();

                allProjectExport.forEach(e -> {
                    if (invoiceInfoMap.containsKey(e.getProjectNo())) {
                        e.setIfDate(invoiceInfoMap.get(e.getProjectNo()).getIfdate());
                    }
                });

                invoiceInfoMap.clear();

            }

            String excelPath = ProjectStatisticsPrint.printOngoingProjects(request, allProjectExport);
            File outFile = new File(excelPath);
            InputStream fis = new BufferedInputStream(new FileInputStream(outFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // ??????response
            response.reset();
            // ??????response???Header

            String fileName = "?????????" + DateFormat.date2String(m) + "??????,??????????????????.xls";
            fileName = URLEncoder.encode(fileName, "utf-8");                                  //????????????URLEncoder????????????????????????????????????
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
        try {

            //????????????cookie
            String name = WebCookie.getUserName(request);
            if (org.apache.commons.lang.StringUtils.isNotBlank(name)) {
                User user = userService.findUserByName(name);
                int roleNo = user.getRoleNo();
                String userId = user.getId() + "";

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
                //???????????????????????????
                ProjectComplaintQuery projectComplaintQuery = new ProjectComplaintQuery();
                projectComplaintQuery.setIsSolve(UNSOLVE);
                projectComplaintQuery.setRoleNo(roleNo);
                projectComplaintQuery.setUserId(userId);
                projectComplaintQuery.setZhijian1(name);
                projectComplaintQuery.setZhijian2(name);
                projectComplaintQuery.setZhijian3(name);

                int unFinishComplaintCount = projectComplaintService.queryCount(projectComplaintQuery);
                //??????????????????????????????
                ShippingConfirmationQuery shippingConfirmationQuery = new ShippingConfirmationQuery();
                shippingConfirmationQuery.setRoleNo(roleNo);
                shippingConfirmationQuery.setIsComplete(0);
                shippingConfirmationQuery.setUserName(name);
                int unFinishShippingCount = shippingConfirmationService.count(shippingConfirmationQuery);
                //????????????????????????
                QualityReportQuery qualityReportQuery = new QualityReportQuery();
                qualityReportQuery.setRoleNo(roleNo);
                qualityReportQuery.setUserName(name);
                qualityReportQuery.setIsComment(1); //?????????????????????
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
     * ??????ERP??????
     *
     * @param
     * @param
     * @return
     * @throws Exception
     */

    public JsonResult sysnByProjectErp(String projectNo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        ProjectFactory projectFactory = new ProjectFactory();
        ProjectERP projectErp = itemCaseERPService.selectByCaseNo(projectNo);
        Project project = projectService.selectProjctDetails(projectNo);
        project.setInterfaceName("sysnByProjectErp");
        if (projectErp != null && project != null) {
            //?????????????????????????????????
            if (project != null) {
                project.setProjectName(projectErp.getProjectNameC());
                project.setActualStartDate(projectErp.getDateSampleUploading());  //????????????
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

                //???????????????????????????????????????????????????????????????
                //??????????????????
                projectFactory.setSampleDate(projectErp.getDateSample());
                projectFactory.setContractDate(projectErp.getDateSampleUploading());

                //???????????????????????????????????????
                if (projectErp.getDateSampleUploading() != null) {
                    project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());  //??????????????????????????????
                }
                //????????????
                if (projectErp.getCompletionTime() != null) {

                    //???????????????????????????????????????????????????????????????????????????
                    if (project.getDeliveryDate() != null && projectErp.getCompletionTime().getTime() < project.getDeliveryDate().getTime()) {

                    } else {
                        project.setDeliveryDate(projectErp.getCompletionTime());
                        project.setOriginalDeliveryDate(projectErp.getCompletionTime());
                    }

                    //??????????????????????????????????????????
                    projectFactory.setDeliveryDate(projectErp.getCompletionTime());
                }
                User user = new User();
                User purchaseUser = new User();
                //1.MerchandManager1 ????????????,MerchandManager2 ??????
                //??????????????????????????????????????????????????????????????????????????????
                //Merchandising ????????????
                if (StringUtils.isNotBlank(projectErp.getMerchandising())) {
                    user = userService.selectUserByName(projectErp.getMerchandising());
                    if (user != null) {
                        project.setEmailUserId(user.getId());
                    }
                } else {
                    if (StringUtils.isNotBlank(projectErp.getMerchandManager1())) {
                        user = userService.selectUserByName(projectErp.getMerchandManager1());
                        if (user != null) {
                            project.setEmailUserId(user.getId());
                        }
                    }
                }

                if (StringUtils.isNotBlank(projectErp.getCustomerManager())) {
                    user = userService.selectUserByName(projectErp.getCustomerManager());
                    if (user != null) {
                        project.setSaleId(user.getId());
                    }
                }


                //??????????????????????????????????????????????????????????????????????????????
                if (StringUtils.isNotBlank(projectErp.getMaturePurchase())) {
                    purchaseUser = userService.selectUserByName(projectErp.getMaturePurchase());
                    if (purchaseUser != null) {
                        project.setPurchaseId(purchaseUser.getId());
                    }
                } else {
                    if (StringUtils.isNotBlank(projectErp.getMerchandManager2())) {
                        purchaseUser = userService.selectUserByName(projectErp.getMerchandManager2());
                        if (purchaseUser != null) {
                            project.setPurchaseId(purchaseUser.getId());
                        }
                    }
                }


                //??????????????????
                projectFactory.setFactoryId(projectErp.getFactoryId());
                projectFactory.setFactoryName(projectErp.getCompanyName());
                projectFactory.setProjectNo(projectNo);
                projectFactory.setCity(projectErp.getCity());
                projectFactory.setContractNo(projectErp.getContractNo());
                if (projectErp.getSupplementaryContract() != null) {
                    projectFactory.setOrderNature(projectErp.getSupplementaryContract() == 1 ? 2 : 1); //??????????????????
                } else {
                    projectFactory.setOrderNature(1);
                }

                projectFactoryService.insertSelective(projectFactory);

                projectService.updateProjectInfo(project);
            }

            return jsonResult;
        } else {
            project = new Project();
            User user = new User();
            User purchaseUser = new User();
            //1.MerchandManager1 ????????????,MerchandManager2 ??????
            if (StringUtils.isNotBlank(projectErp.getMerchandManager1())) {
                user = userService.selectUserByName(projectErp.getMerchandManager1());
                if (user != null) {
                    project.setEmailUserId(user.getId());
                }
            }
            if (StringUtils.isNotBlank(projectErp.getMerchandManager2())) {
                purchaseUser = userService.selectUserByName(projectErp.getMerchandManager2());
                if (purchaseUser != null) {
                    project.setPurchaseId(purchaseUser.getId());
                }
            }
            project.setId(IdGen.uuid());
            project.setProjectNo(projectNo);
            project.setProjectName(projectErp.getProjectNameC());
            project.setProjectNameEn(projectErp.getProjectNameE());
            project.setDeliveryDate(projectErp.getCompletionTime()); //??????
            project.setOriginalDeliveryDate(projectErp.getCompletionTime());   //??????
            project.setDeliveryStatus(0);
            project.setWarningStatus(0);
            project.setImportance(0);
            project.setFinish(0);
            project.setSampleFinish(0);
            project.setStage(0);
            project.setPoDate(projectErp.getPoDate());  //PO??????
            project.setActualStartDate(projectErp.getDateSampleUploading());  //????????????
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

            //???????????????????????????????????????????????????????????????
            //??????????????????
            projectFactory.setSampleDate(projectErp.getDateSample());
            projectFactory.setContractDate(projectErp.getDateSampleUploading());


            project.setProjectMaterialProperties(projectErp.getProjectMaterialProperties());
            if (projectErp.getDateSample() != null) {
                project.setDateSample(projectErp.getDateSample());
            }
            if (projectErp.getCompletionTime() != null) {
                project.setCompletionTime(projectErp.getCompletionTime());
            }
            if (projectErp.getMoneyDate() != null) {
                project.setMoneyDate(projectErp.getMoneyDate());
            }
            //??????????????????
            Date poDate = projectErp.getPoDate();

            //Edit by polo   2018.7.10
            //???????????????????????????????????????
            if (projectErp.getDateSampleUploading() != null) {
                project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());  //??????????????????????????????
                //?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                //???????????????????????????
                //????????????????????????????????????????????????????????????????????????
                if (projectErp.getCompletionTime() != null) {
                    //???????????????????????????????????????????????????
                    int scheduledDays = 0;
                    //????????????po???????????????????????? ??????-po??????
                    //???????????????po??????????????????????????????-????????????+7???
                    if (poDate != null) {
                        scheduledDays = DateFormat.calDays(projectErp.getCompletionTime(), poDate);
                    } else {
                        scheduledDays = DateFormat.calDays(projectErp.getCompletionTime(), projectErp.getDateSampleUploading());
                        scheduledDays = scheduledDays + 7;
                    }
                    project.setScheduledDays(scheduledDays);
                    //???????????????????????????
                    projectFactory.setDeliveryDate(projectErp.getCompletionTime());
                } else {
                    if (projectErp.getDateSample() != null) {
                        //???????????????
                        int scheduledDays = 0;
                        //????????????po???????????????????????? ??????-po??????
                        //???????????????po??????????????????????????????-????????????+7???
                        if (poDate != null) {
                            scheduledDays = DateFormat.calDays(projectErp.getDateSample(), poDate);
                        } else {
                            scheduledDays = DateFormat.calDays(projectErp.getDateSample(), projectErp.getDateSampleUploading());
                            scheduledDays = scheduledDays + 7;
                        }
                        project.setScheduledDays(scheduledDays);
                    }

                }

                //??????????????????
                projectFactory.setFactoryId(projectErp.getFactoryId());
                projectFactory.setFactoryName(projectErp.getCompanyName());
                projectFactory.setProjectNo(projectNo);
                projectFactory.setCity(projectErp.getCity());
                projectFactory.setContractNo(projectErp.getContractNo());
                if (projectErp.getSupplementaryContract() != null) {
                    projectFactory.setOrderNature(projectErp.getSupplementaryContract() == 1 ? 2 : 1); //??????????????????
                } else {
                    projectFactory.setOrderNature(1);
                }
                projectFactoryService.insertSelective(projectFactory);

                projectService.addProject(project);
                List<Project> projectList = new ArrayList<Project>();
                projectList.add(project);
                projectDateTask.syncProjectDate(projectList);
            }
        }
        return jsonResult;
    }

    @RequestMapping("/searchNoFinishProject")
    public String searchNoFinishProject(HttpServletRequest request, HttpServletResponse response) {
        String roleNo = request.getParameter("roleNo");
        String userName = request.getParameter("userName");
        try {
            Project project = new Project();
            List<Project> projects = projectService.searchNoFinishProject(project);

            request.setAttribute("list", projects);
            request.setAttribute("code", 200);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("code", 404);
            request.setAttribute("msg", e.getMessage());
            LOG.error("searchNoFinishProject,error:", e);
        }
        return "project_no_finish";
    }

    @RequestMapping("/qualityReportList")
    @ResponseBody
    public JsonResult qualityReportList(HttpServletRequest request, HttpServletResponse response) {
        String project_no = request.getParameter("project_no");
        Assert.isTrue(StringUtils.isNotBlank(project_no), "project_no null");
        try {
            List<QualityReport> list = qrService.selectByProjectNo(project_no);
            return JsonResult.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("qualityReportList,error:", e);
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping("/finishStatus")
    @ResponseBody
    public JsonResult finishStatus(HttpServletRequest request, HttpServletResponse response) {
        String project_no = request.getParameter("project_no");
        Assert.isTrue(StringUtils.isNotBlank(project_no), "project_no null");
        String finish_time = request.getParameter("finish_time");
        String save_type = request.getParameter("save_type");

        Assert.isTrue(StringUtils.isNotBlank(finish_time), "finish_time null");
        Assert.isTrue(StringUtils.isNotBlank(save_type), "save_type null");

        try {
            Project project = new Project();
            project.setProjectNo(project_no);
            project.setProjectStatus(Integer.parseInt(save_type));
            if ("6".equals(save_type)) {
                project.setSampleFinishTime(DateUtil.StrToDate(finish_time));
                projectService.updateProjectSample(project);
            } else if ("2".equals(save_type)) {
                project.setFinishTime(DateUtil.StrToDate(finish_time));
                projectService.updateProjectFinish(project);
            } else {
                return JsonResult.error("??????????????????");
            }
            return JsonResult.success("????????????");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("finishStatus,error:", e);
            return JsonResult.error(e.getMessage());
        }
    }

}
