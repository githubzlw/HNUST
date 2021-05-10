package com.cn.hnust.controller;

import com.cn.hnust.enums.IssueReplyTypelEnum;
import com.cn.hnust.enums.QualityStatusEnum;
import com.cn.hnust.enums.QualityTypeEnum;
import com.cn.hnust.enums.TaskStatusEnum;
import com.cn.hnust.pojo.*;
import com.cn.hnust.service.*;
import com.cn.hnust.util.*;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 客户投诉
 *
 * @author Administratordetail
 */
@Controller
@RequestMapping("/complaint")
public class ProjectComplaintController {
    @Autowired
    private IProductionPlanService productionPlanService;
    @Autowired
    private InspectionPlanService inspectionPlanService;
    @Autowired
    private IQualityPicExplainService qualityPicExplainService;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private ProjectComplaintService projectComplaintService;
    @Autowired
    private IProjectTaskService projectTaskService;
    @Autowired
    private IssueReplyService issueReplyService;
    @Autowired
    private ComplaintIssueService complaintIssueService;
    @Autowired
    private IQualityReportService qualityReportService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProjectInspectionReportService projectInspectionReportService;
    @Autowired
    private ShippingConfirmationService shippingConfirmationService;
    @Autowired
    private IMeetingRecordService meetingRecordService;
    @Autowired
    private AnalysisIssueService analysisIssueService;
    @Autowired
    private QualityAnalysisService qualityAnalysisService;
    @Autowired
    private FactoryQualityInspectionVideoService factoryQualityInspectionVideoService;
    @Autowired
    private ProjectFactoryService projectFactoryService;
    @Autowired
    private IProjectCommentService projectCommentService;
    @Autowired
    private IProductsService productsService;
    @Autowired
    ComplaintInspectionReportService complaintInspectionReportService;
    @Autowired
    private static ProjectComplaintService projectComplaintService1;

    private static final Log LOG = LogFactory.getLog(ProjectComplaintController.class);
    private static PropertiesUtils reader = new PropertiesUtils("config.properties");
    private static final int SAMPLE = 0;           //样品
    private static final int PRODUCT = 1;          //大货
    private static final int LAST = 3;             //终期检验
    private static final int SALES = 0;            //销售
    private static final int PURCHASE = 1;         //采购
    private static final int QUALITY_LEADER = 2;  //质检总监
    private static final int PURCHASE_LEADER = 3; //采购总监
    private static final int BOSS = 4;         //老板
    private static final int PRODUCT_VIDEO = 4;  //产品360视频

    private OkHttpClient client = new OkHttpClient.Builder().connectTimeout(600, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();


    private static volatile AtomicLong atomicLong = new AtomicLong(0);


    @PostConstruct
    public void beforeInit() {
        projectComplaintService1 = projectComplaintService;

    }

    /**
     * 查询投诉列表
     *
     * @param request
     * @param response
     * @return String
     * @Title queryList
     * @Description
     */
    @RequestMapping("/queryList")
    public String queryList(HttpServletRequest request, HttpServletResponse response) {

        try {
            String pageStr = request.getParameter("pageStr");
            String userIdStr = request.getParameter("userId");
            String inputKey = request.getParameter("inputKey");// 搜索词
            String pageSizeStr = request.getParameter("pageSize");
            String solve = request.getParameter("solve");
            String purchasingScreening = request.getParameter("purchasingScreening");
            String documentaryScreening = request.getParameter("documentaryScreening");
            String seriousLevelStr = request.getParameter("seriousLevelStr");
            Integer roleNo = null;                            // 判断是管理员，销售，采购
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isNotBlank(userName)) {
                User user = userService.findUserByName(userName);
                roleNo = user.getRoleNo();
                userIdStr = user.getId() + "";
            } else {
                return "redirect:/index.jsp";
            }
            User user = userService.findUserByName(userName);

            //页数
            Integer page = null;
            if (StringUtils.isNotBlank(pageStr)) {
                page = Integer.parseInt(pageStr);
            } else {
                page = 1;
            }
            //每页显示数
            Integer pageSize = null;
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            } else {
                pageSize = 100;
            }
            ProjectComplaintQuery projectComplaintQuery = new ProjectComplaintQuery();
            projectComplaintQuery.setPageSize(pageSize);
            projectComplaintQuery.setPageNumber(pageSize * (page - 1));
            projectComplaintQuery.setRoleNo(roleNo);
            projectComplaintQuery.setUserId(userIdStr);
            projectComplaintQuery.setInputKey(inputKey);
            projectComplaintQuery.setZhijian1(userName);
            if (purchasingScreening != null && !"".equalsIgnoreCase(purchasingScreening)
                    && !"-1".equalsIgnoreCase(purchasingScreening)) {
                projectComplaintQuery.setPurchaseName(purchasingScreening);
            }
            if (documentaryScreening != null && !"".equalsIgnoreCase(documentaryScreening)
                    && !"-1".equalsIgnoreCase(documentaryScreening)) {
                projectComplaintQuery.setSellName(documentaryScreening);
            }

            if (StringUtils.isNotBlank(solve)) {
                projectComplaintQuery.setIsSolve(Integer.parseInt(solve));
            } else {
                projectComplaintQuery.setIsSolve(0);
            }
            if (StringUtils.isNotBlank(request.getParameter("projectNo"))) {
                projectComplaintQuery.setInputKey(request.getParameter("projectNo"));
            }

            if (seriousLevelStr != null && !"".equalsIgnoreCase(seriousLevelStr) && !"-1".equalsIgnoreCase(seriousLevelStr)) {
                projectComplaintQuery.setSeriousLevel(Integer.parseInt(seriousLevelStr));
                request.setAttribute("seriousLevelStr", Integer.parseInt(seriousLevelStr));
            } else {
                request.setAttribute("seriousLevelStr", -1);
            }

            List<ProjectComplaint> complaintList = projectComplaintService.queryComplaintList(projectComplaintQuery);
            complaintList.forEach(c -> {
                List<Comment> comments = projectCommentService.selectByComplaintId(c.getId());
                if (comments != null && comments.size() > 0) {
                    c.setTechnicianReplyTime(comments.get(0).getCreateDate());
                }
            });
            //客户投诉关联任务(已取消会议投诉录入方式)
//			for (ProjectComplaint projectComplaint : complaintList) {
//				if(StringUtils.isNotBlank(projectComplaint.getMeetingNo())){
//					List<ProjectTask> tasks = projectTaskService.selectMeetingRecordTask(projectComplaint.getMeetingNo());
//					projectComplaint.setTasks(tasks);
//				}
//			}
            List<User> saleUser = userService.queryByJob("采购");
            List<User> documentaryUser = userService.queryByJob("跟单");
            int totalCount = projectComplaintService.queryCount(projectComplaintQuery);

            request.setAttribute("user", user);
            request.setAttribute("saleUser", saleUser);
            request.setAttribute("documentaryUser", documentaryUser);
            request.setAttribute("complaintList", complaintList);
            request.setAttribute("userId", userIdStr);
            request.setAttribute("roleNo", roleNo);
            request.setAttribute("inputKey", inputKey);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("page", page);
            request.setAttribute("userName", userName);
            request.setAttribute("count", totalCount);
            request.setAttribute("solve", solve == null ? 0 : solve);
            request.setAttribute("purchasingScreening", purchasingScreening == null ? "-1" : purchasingScreening);
            request.setAttribute("documentaryScreening", documentaryScreening == null ? "-1" : documentaryScreening);
            //计算尾页
            Integer lastNum = new BigDecimal(totalCount).divide(new BigDecimal(pageSize)).setScale(0, BigDecimal.ROUND_UP).intValue();
            request.setAttribute("lastNum", lastNum);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "customer_complaint_B";
    }


    /**
     * 插入客户投诉信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addComplaint")
    @ResponseBody
    public JsonResult addComplaint(HttpServletRequest request, HttpServletResponse response) {
        JsonResult json = new JsonResult();

        try {
            String projectNo = request.getParameter("projectNo");
            String userName = request.getParameter("userName");
            String complaintContent = request.getParameter("complaintContent");
            String fileName = request.getParameter("fileName");
            String filePath = request.getParameter("filePath");
            String seriousLevel = request.getParameter("seriousLevel");
            String issueList = request.getParameter("issueList");
            String taskAttachment = request.getParameter("taskAttachment1");
            String pic_up = request.getParameter("pic_up");
            String taskBrief = request.getParameter("taskBrief1");
            String projectStage = request.getParameter("projectStage");
            if (StringUtils.isNotBlank(projectNo)) {
                Project project = projectService.selectProjctDetails(projectNo);
                if (project == null) {
                    json.setOk(false);
                    json.setMessage("该项目还未录入");
                    return json;
                } else {

                    //如果不是项目组成员，提醒
                    if (!(userName.equalsIgnoreCase(project.getSellName()) || userName.equalsIgnoreCase(project.getPurchaseName()) || userName.equalsIgnoreCase(project.getZhijian1())
                            || userName.equalsIgnoreCase(project.getZhijian1()) || userName.equalsIgnoreCase(project.getZhijian1()) || userName.equalsIgnoreCase("ninazhao")
                    )) {
                        json.setOk(false);
                        json.setMessage("你不是该项目组成员，请先ERP添加。");
                        return json;
                    }


                    ProjectComplaint projectComplaint = new ProjectComplaint();
                    projectComplaint.setProjectNo(projectNo);
                    projectComplaint.setComplaintContent(complaintContent);
                    projectComplaint.setCreatePerson(userName);
                    projectComplaint.setIsPurchase(0);
                    projectComplaint.setIsSell(0);
                    projectComplaint.setPurchaseId(project.getPurchaseId());
                    projectComplaint.setSellId(project.getEmailUserId());
                    projectComplaint.setFileName(fileName);
                    projectComplaint.setFilePath(filePath);
                    projectComplaint.setCreateTime(new Date());
                    if (StringUtils.isNotBlank(seriousLevel)) {
                        projectComplaint.setSeriousLevel(Integer.parseInt(seriousLevel));
                    }
                    if ("是".equalsIgnoreCase(pic_up)) {
                        projectComplaint.setPicUp(1);
                        projectComplaint.setTaskBrief(taskBrief);
                        projectComplaint.setTaskAttachment(taskAttachment);
                    }

                    //项目阶段
                    if (StringUtils.isNotBlank(projectStage)) {
                        projectComplaint.setProjectStage(Integer.parseInt(projectStage));
                    }

                    //投诉问题列表
                    List<ComplaintIssue> complaintIssues = null;
                    ObjectMapper objectMapper = new ObjectMapper();
                    JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ComplaintIssue.class);
                    complaintIssues = objectMapper.readValue(issueList, javaType);

                    //批量更新问题标签表 complaint_id
                    String issues = request.getParameter("issues");
                    List<AnalysisIssue> analysisIssueList = null;
                    if (StringUtils.isNotBlank(issues)) {
                        ObjectMapper objectMapper1 = new ObjectMapper();
                        JavaType javaType1 = objectMapper1.getTypeFactory().constructParametricType(List.class, AnalysisIssue.class);
                        analysisIssueList = objectMapper.readValue(issues, javaType1);
                    }
                    projectComplaintService.insertSelective(projectComplaint, complaintIssues, analysisIssueList);
                    if ("是".equalsIgnoreCase(pic_up)) {
                        ProjectTask task = new ProjectTask();
                        task.setAccepter("chengminghui");
                        task.setInitiator("system");
                        task.setProjectNo(projectNo);
                        task.setDescription("客户变更图纸， 需要重新出图");
                        task.setTaskStatus("0");
                        task.setTaskType("5");
                        task.setStartTime(new Date());
                        task.setCreateTime(new Date());
                        task.setFinishTime(getFinishDate());
                        task.setComplaintId(projectComplaint.getId());
                        projectTaskService.addProjectTask(task);
                    }
                    Project project1 = projectService.selectProjectByProjectNo(projectNo);
                    ProjectTask qualityTask = new ProjectTask();
                    if (project1.getZhijian1() != null && !"".equalsIgnoreCase(project1.getZhijian1())) {
                        qualityTask.setAccepter(project1.getZhijian1());
                    } else if (project1.getZhijian2() != null && !"".equalsIgnoreCase(project1.getZhijian2())) {
                        qualityTask.setAccepter(project1.getZhijian2());
                    } else {
                        qualityTask.setAccepter(userName);
                    }
                    qualityTask.setInitiator("system");
                    qualityTask.setProjectNo(projectNo);
                    qualityTask.setDescription("需要在ERP系统上更新检验计划");
                    qualityTask.setTaskStatus("0");
                    qualityTask.setTaskType("13");
                    qualityTask.setStartTime(new Date());
                    qualityTask.setCreateTime(new Date());
                    qualityTask.setFinishTime(getFinishDate());
                    projectTaskService.addProjectTask(qualityTask);
                    String userName1 = WebCookie.getUserName(request);
                    User user = userService.findUserByName(userName1);
                    DingTalkThread.ComplaintOut(projectComplaint.getId(), user.getDingTalkId());
                    json.setOk(true);
                    json.setData(projectComplaint.getId());
                    return json;
                }
            } else {
                json.setOk(false);
                json.setMessage("项目号不能为空");
                return json;
            }

        } catch (Exception e) {
            json.setOk(false);
            json.setMessage("客户投诉添加错误");
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 更新项目投诉
     *
     * @param request
     * @param response
     * @return JsonResult
     * @Title showUser
     * @Description
     */
    @RequestMapping("/updateComplaint")
    @ResponseBody
    public JsonResult updateComplaint(HttpServletRequest request, HttpServletResponse response) {
        JsonResult json = new JsonResult();
        String id = request.getParameter("id");
        String num = request.getParameter("num");
        //更新类型
        //1:更新投诉内容 （杨工操作）
        //2:更新成本
        //3:采购确认
        //4:跟单确认
        //6:质量总监确认
        String type = request.getParameter("type");
        try {
            if (StringUtils.isNotBlank(id)) {
                ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
                if ("1".equals(type)) {
                    String complaintDate = request.getParameter("complaintDate");
                    String complaintContent = request.getParameter("complaintContent");
                    String fileName = request.getParameter("fileName");
                    String filePath = request.getParameter("filePath");
                    projectComplaint.setComplaintDate(java.sql.Date.valueOf(complaintDate));
                    projectComplaint.setComplaintContent(complaintContent);
                    projectComplaint.setFileName(fileName);
                    projectComplaint.setFilePath(filePath);
                } else if ("2".equals(type)) {
                    String costAnalysis = request.getParameter("costAnalysis");
                    projectComplaint.setCostAnalysis(costAnalysis);
                } else if ("3".equals(type)) {
                    String userId = request.getParameter("userId");
                    String purchaseContent = request.getParameter("purchaseContent");
                    String isPurchase = request.getParameter("isPurchase");
                    projectComplaint.setPurchaseId(Integer.parseInt(userId));
                    projectComplaint.setPurchaseContent(purchaseContent);
                    projectComplaint.setIsPurchase(Integer.parseInt(isPurchase));
                    projectComplaint.setPurchaseConfirmDate(new Date());
                } else if ("4".equals(type)) {
                    String userId = request.getParameter("userId");
                    String sellContent = request.getParameter("sellContent");
                    String isSell = request.getParameter("isSell");
                    projectComplaint.setSellId(Integer.parseInt(userId));
                    projectComplaint.setSellContent(sellContent);
                    projectComplaint.setIsSell(Integer.parseInt(isSell));
                    projectComplaint.setSellConfirmDate(new Date());
                } else if ("5".equals(type)) {
                    projectComplaint.setPurchaseLeaderConfirmDate(new Date());
                    projectComplaint.setPurchaseLeaderConfirm(1);
                } else if ("6".equals(type)) {
                    if ("0".equalsIgnoreCase(num)) {
                        projectComplaint.setInspectionLeaderConfirmDate(new Date());
                        projectComplaint.setInspectionLeaderConfirm(1);
                    } else if ("1".equalsIgnoreCase(num)) {
                        projectComplaint.setInspectionLeaderConfirmDate(new Date());
                        projectComplaint.setInspectionLeaderConfirm(0);
                    }
                }
                projectComplaintService.updateByPrimaryKeySelective(projectComplaint);

                json.setOk(true);
                json.setMessage("修改成功");
            } else {
                json.setOk(false);
                json.setMessage("修改id不能为空");
                return json;
            }
        } catch (NumberFormatException e) {
            json.setOk(false);
            json.setMessage("修改失败");
            e.printStackTrace();
        }
        return json;

    }

    /**
     * 下载
     *
     * @param request
     * @param response
     * @return ResponseEntity<byte [ ]>
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @Title fileDownload
     * @Description
     */
    @RequestMapping(value = "download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(id);
            ResponseEntity<byte[]> download = OperationFileUtil.download(projectComplaint.getFilePath(), projectComplaint.getFileName());
            return download;
        } else {
            throw new RuntimeException("未获取到路径");
        }
    }


    /**
     * 查询投诉详情
     *
     * @param request
     * @param response
     * @return String
     * @Title queryList
     * @Description
     */
    @RequestMapping("/queryComplaint")
    public String queryComplaint(HttpServletRequest request, HttpServletResponse response) {

        try {
            String id = request.getParameter("id");      //项目号
            String type = request.getParameter("type");  //判断是否是微信分享发出
            Integer existPurchaseReply = 0;              //是否存在采购回复0 否 1 是
            Integer existInspectionReply = 0;            //是否存在质检回复0 否 1 是
            Integer existTechnicianReply = 0;            //是否存在技术回复0 否 1 是
            Integer existRectificationReply = 0;         //是否存在整改回复0 否 1 是
            //查询cookie中用户
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isNotBlank(userName)) {
                User user = userService.selectUserByName(userName);
                request.setAttribute("user", user);
            }
            //查询技术回复
            List<Comment> comments = projectCommentService.selectByComplaintId(Integer.parseInt(id));
            if (comments != null && comments.size() > 0) {
                existTechnicianReply = 1;
            }
            //邮件正文内容
            //n个问题点+客户问题正文
            StringBuffer mailBody = new StringBuffer();
            int index = 0;
            if (StringUtils.isNotBlank(request.getParameter("id"))) {
                ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
                //查询采购跟单最终回复
                List<Comment> comments1 = projectCommentService.selectByComplaintId1(Integer.parseInt(id));
                //投诉问题
                List<ComplaintIssue> issueList = complaintIssueService.selectByComplaintId(Integer.parseInt(id));
                int existRectificationReply1 = 0;
                int existRectificationReply2 = 0;
                if (projectComplaint.getSeriousLevel() != 5) {
                    for (ComplaintIssue complaintIssue : issueList) {

                        List<IssueReply> replyList = complaintIssue.getReplyList();
                        for (IssueReply issueReply : replyList) {
                            //判断是否存在各类型回复消息
                            if (issueReply.getReplyType() == IssueReplyTypelEnum.PURCHASE_REPLY.getCode()) {
                                existPurchaseReply = 1;
                            }
                            if (issueReply.getReplyType() == IssueReplyTypelEnum.INSPECTION_REPLY.getCode()) {
                                existInspectionReply = 1;
                            }
                            if (issueReply.getReplyType() == IssueReplyTypelEnum.TECHNICIAN_REPLY.getCode()) {
                                existTechnicianReply = 1;
                            }

                            if (issueReply.getReplyType() == IssueReplyTypelEnum.RECTIFICATION_REPLY.getCode() && issueReply.getQualification() == "合格") {
                                existRectificationReply1 = 1;
                            } else if (issueReply.getReplyType() == IssueReplyTypelEnum.RECTIFICATION_REPLY.getCode()) {
                                existRectificationReply2 = 2;
                            }

                        }
                        if (projectComplaint.getInspectionLeaderConfirm() > 0) {
                            if (existRectificationReply1 == 1 && existRectificationReply2 == 0) {
                                existRectificationReply = 1;
                            }
                        } else {
                            if (existRectificationReply2 == 2) {
                                existRectificationReply = 1;
                            }
                        }
                        index++;
                        mailBody.append("问题" + index + ":");
                        mailBody.append(complaintIssue.getIssue());
                    }
                    if (projectComplaint.getComplaintContent() != null && !"".equalsIgnoreCase(projectComplaint.getComplaintContent())) {
                        existPurchaseReply = 1;
                    }
                    mailBody.append(projectComplaint.getComplaintContent());
                } else {
                    if (comments1.size() == 0) {
                        existPurchaseReply = 1;
                        existInspectionReply = 1;
                    }
                }
                //质检报告
                List<QualityReport> qualityReports = qualityReportService.selectByProjectNo(projectComplaint.getProjectNo());
                if (qualityReports != null && qualityReports.size() > 0) {
                    for (QualityReport qr : qualityReports) {
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
                    }

                }
                //项目详情
                Project project = projectService.selectProjctDetails(projectComplaint.getProjectNo());
                //查询检验计划
                List<ProjectInspectionReport> inspectionPlanList = projectInspectionReportService.selectInspectionPlanByProjectNo(projectComplaint.getProjectNo());
                //查询项目相关任务
                List<ProjectTask> tasks = projectTaskService.selectByProjectNo(projectComplaint.getProjectNo());
                //查询质量、技术关键词
                List<AnalysisIssue> analysisIssueList = analysisIssueService.selectByComplaintId(Integer.parseInt(id));
                QualityAnalysis qualityAnalysis = qualityAnalysisService.selectByProjectNo(projectComplaint.getProjectNo());
                //查询项目所有工厂
                List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectComplaint.getProjectNo());
                //查询问题出现的次数
                for (AnalysisIssue analysisIssue : analysisIssueList) {
                    if (StringUtils.isNotBlank(analysisIssue.getIssue())) {
                        List<String> issues = qualityAnalysisService.selectByIssue(analysisIssue.getIssue());
                        analysisIssue.setOccurrenceNum(issues != null ? issues.size() : 0);
                    }
                }
                int preliminaryVerification = 0;
                if (existPurchaseReply == 1 && existInspectionReply == 1 && existTechnicianReply == 1) {
                    preliminaryVerification = 1;
                }
                //查询问题
                List<AnalysisIssue> list = analysisIssueService.selectTop10(null);
                List<ComplaintInspectionReport> complaintInspectionReport = complaintInspectionReportService.getAll(Integer.parseInt(id));
                request.setAttribute("projectComplaint", projectComplaint);
                request.setAttribute("issueList", issueList);
                request.setAttribute("qualityReports", qualityReports);
                request.setAttribute("project", project);
                request.setAttribute("existPurchaseReply", existPurchaseReply);
                request.setAttribute("existInspectionReply", existInspectionReply);
                request.setAttribute("existTechnicianReply", existTechnicianReply);
                request.setAttribute("existRectificationReply", existRectificationReply);
                request.setAttribute("preliminaryVerification", preliminaryVerification);
                request.setAttribute("type", type);
                request.setAttribute("mailBody", mailBody);
                request.setAttribute("inspectionPlanList", inspectionPlanList);
                request.setAttribute("tasks", tasks);
                request.setAttribute("analysisIssueList", analysisIssueList);
                request.setAttribute("qualityAnalysis", qualityAnalysis);
                request.setAttribute("factoryList", factoryList);
                request.setAttribute("list", list);
                if (comments.size() > 0) {
                    request.setAttribute("comments", comments);
                } else {
                    request.setAttribute("comments", null);
                }

                request.setAttribute("userName", userName);
                if (comments1.size() > 0) {
                    request.setAttribute("comments1", comments1);
                } else {
                    request.setAttribute("comments1", null);
                }
                if (complaintInspectionReport.size() > 0) {
                    request.setAttribute("complaintInspectionReport", complaintInspectionReport);
                } else {
                    request.setAttribute("complaintInspectionReport", null);
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "customer_complaint_B_detail";
    }


    /**
     * 跳转采购录入信息页面
     *
     * @param request
     * @param response
     * @return String
     * @Title purchaseReply
     * @Description
     */
    @RequestMapping("/purchaseReply")
    public String purchaseReply(HttpServletRequest request, HttpServletResponse response) {

        try {
            String id = request.getParameter("id");   //项目号
            //查询cookie中用户
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isNotBlank(userName)) {
                User user = userService.selectUserByName(userName);
                request.setAttribute("user", user);
            }


            if (StringUtils.isNotBlank(request.getParameter("id"))) {
                ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
                //投诉问题
                List<ComplaintIssue> issueList = complaintIssueService.selectByComplaintId(Integer.parseInt(id));
                //项目详情
                Project project = projectService.selectProjctDetails(projectComplaint.getProjectNo());

                request.setAttribute("projectComplaint", projectComplaint);
                request.setAttribute("issueList", issueList);
                request.setAttribute("project", project);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "purchase_entry_reply";
    }

    /**
     * 跳转质检录入信息页面
     *
     * @param request
     * @param response
     * @return String
     * @Title inspectionReply
     * @Description
     */
    @RequestMapping("/inspectionReply")
    public String inspectionReply(HttpServletRequest request, HttpServletResponse response) {

        try {
            String id = request.getParameter("id");   //项目号
            //查询cookie中用户
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isNotBlank(userName)) {
                User user = userService.selectUserByName(userName);
                request.setAttribute("user", user);
            }


            if (StringUtils.isNotBlank(request.getParameter("id"))) {
                ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
                //投诉问题
                List<ComplaintIssue> issueList = complaintIssueService.selectByComplaintId(Integer.parseInt(id));
                //项目详情
                Project project = projectService.selectProjctDetails(projectComplaint.getProjectNo());

                request.setAttribute("projectComplaint", projectComplaint);
                request.setAttribute("issueList", issueList);
                request.setAttribute("project", project);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "quality_reply";
    }


    /**
     * 跳转技术录入信息页面
     *
     * @param request
     * @param response
     * @return String
     * @Title technicianReply
     * @Description
     */
    @RequestMapping("/technicianReply")
    public String technicianReply(HttpServletRequest request, HttpServletResponse response) {

        try {
            String id = request.getParameter("id");   //项目号
            //查询cookie中用户
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isNotBlank(userName)) {
                User user = userService.selectUserByName(userName);
                request.setAttribute("user", user);
            }

            //投诉问题id
            Integer issueId = null;
            if (StringUtils.isNotBlank(request.getParameter("id"))) {
                ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
                //投诉问题
                List<ComplaintIssue> issueList = complaintIssueService.selectByComplaintId(Integer.parseInt(id));
                if (issueList != null && issueList.size() > 0) {
                    issueId = issueList.get(0).getId();
                }
                //项目详情
                Project project = projectService.selectProjctDetails(projectComplaint.getProjectNo());
                //查询技术回复
                List<Comment> comments = projectCommentService.selectByComplaintId(Integer.parseInt(id));

                request.setAttribute("projectComplaint", projectComplaint);
                request.setAttribute("issueId", issueId);
                request.setAttribute("project", project);
                request.setAttribute("comments", comments);
                request.setAttribute("userName", userName);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "technology_entry_reply";
    }

    /**
     * 跳转整改结果录入信息页面
     *
     * @param request
     * @param response
     * @return String
     * @Title remediaReply
     * @Description
     */
    @RequestMapping("/remediaReply")
    public String remediaReply(HttpServletRequest request, HttpServletResponse response) {
        String url = "";
        try {
            String id = request.getParameter("id");   //项目号
            String num = request.getParameter("num");   //项目号
            //查询cookie中用户
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isNotBlank(userName)) {
                User user = userService.selectUserByName(userName);
                request.setAttribute("user", user);
                if (user.getRoleNo() == 5 || user.getRoleNo() == 6 || user.getRoleNo() == 1020) {
                    url = "purchase_remedia_entry_reply";
                } else if (user.getRoleNo() == 9) {
                    url = "remedia_entry_reply";
                }
                if (user.getRoleNo() == 100 && "1".equalsIgnoreCase(num)) {
                    url = "purchase_remedia_entry_reply";
                } else if (user.getRoleNo() == 100 && "2".equalsIgnoreCase(num)) {
                    url = "remedia_entry_reply";
                }
            }


            if (StringUtils.isNotBlank(request.getParameter("id"))) {
                ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
                if ("1".equalsIgnoreCase(num)) {
                    //投诉问题
                    List<ComplaintIssue> issueList = complaintIssueService.selectByComplaintId(Integer.parseInt(id));
                    request.setAttribute("issueList", issueList);
                }
                if ("2".equalsIgnoreCase(num)) {
                    //投诉问题
                    List<ComplaintIssue> issueList = complaintIssueService.selectByComplaintId1(Integer.parseInt(id));
                    request.setAttribute("issueList", issueList);
                }
                //项目详情
                Project project = projectService.selectProjctDetails(projectComplaint.getProjectNo());
                List<Comment> comments1 = projectCommentService.selectByComplaintId1(Integer.parseInt(id));
                request.setAttribute("projectComplaint", projectComplaint);

                request.setAttribute("project", project);
                request.setAttribute("comments", comments1);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return url;
    }


    /**
     * 删除投诉表
     *
     * @param request
     * @param response
     * @return String
     * @Title delete
     * @Description
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(HttpServletRequest request, HttpServletResponse response) {

        JsonResult jsonResult = new JsonResult();
        try {
            String id = request.getParameter("id");   //项目号
            //查询cookie中用户
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isBlank(userName)) {
                jsonResult.setOk(false);
                jsonResult.setMessage("请先登录");
            }
            if (StringUtils.isNotBlank(id)) {
                ProjectComplaint projectComplaint = projectComplaintService.selectByPrimaryKey(Integer.parseInt(id));
                if (userName.equals(projectComplaint.getCreatePerson()) || userName.equals("ninazhao")) {
                    projectComplaintService.deleteByPrimaryKey(Integer.parseInt(id));
                    jsonResult.setOk(true);
                    jsonResult.setMessage("已删除");
                } else {
                    jsonResult.setOk(false);
                    jsonResult.setMessage("你没有权限删除");
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage("删除失败");
            LOG.error("====客户投诉删除失败=====");

        }
        return jsonResult;
    }


    /**
     * 插入回复信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addReply")
    @ResponseBody
    public JsonResult addReply(HttpServletRequest request, HttpServletResponse response) {
        JsonResult json = new JsonResult();

        try {
            //投诉表id
            String complaintId = request.getParameter("complaintId");
            //回复json集合
            String replyList = request.getParameter("replyList");
            //成本分析
            String costAnalysis = request.getParameter("costAnalysis");
            //采购原因分析
            String purchaseContent = request.getParameter("purchaseContent");
            //采购填预计完成日期
            String predictCompleteTime = request.getParameter("predictCompleteTime");
            //回复人类型（1：采购回复  2：质检回复 3：技术回复 4：整改回复）
            String replyType = request.getParameter("replyType");
            //质检原因分析
            String inspectionContent = request.getParameter("inspectionContent");
            //用户roleNo
            String roleNo = request.getParameter("roleNo");

            //查询cookie中用户
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isBlank(complaintId)) {
                json.setOk(false);
                json.setMessage("投诉获取失败");
                return json;
            }
            ProjectComplaint projectComplaint = new ProjectComplaint();
            projectComplaint.setId(Integer.parseInt(complaintId));

            //根据回复类型判断
            Integer type = null;
            if (StringUtils.isNotBlank(replyType)) {
                type = Integer.parseInt(replyType);
                if (type == IssueReplyTypelEnum.PURCHASE_REPLY.getCode()) {
                    projectComplaint.setCostAnalysis(costAnalysis);
                    projectComplaint.setPurchaseContent(purchaseContent);
                    projectComplaint.setPredictCompleteTime(DateUtil.StrToDate(predictCompleteTime));
                    projectComplaint.setPurchaseConfirmDate(new Date());
                } else if (type == IssueReplyTypelEnum.INSPECTION_REPLY.getCode()) {
                    projectComplaint.setInspectionContent(inspectionContent);
                    projectComplaint.setZhijianReplyTime(new Date());
                } else if (type == IssueReplyTypelEnum.TECHNICIAN_REPLY.getCode()) {
                    projectComplaint.setTechnicianReplyTime(new Date());
                } else if (type == IssueReplyTypelEnum.RECTIFICATION_REPLY.getCode()) {
                    if ("9".equals(roleNo)) {
                        // projectComplaint.setCompleteTime(new Date());
                        projectComplaint.setRectificationZhijianReply(userName);
                        projectComplaint.setRectificationZhijianTime(new Date());
                    } else {
                        projectComplaint.setRectificationSellPurchaseReply(userName);
                        projectComplaint.setRectificationSellPurchaseTime(new Date());
                    }
                    projectComplaintService.updateByPrimaryKeySelective(projectComplaint);
                }
            }
            if (StringUtils.isNotBlank(userName)) {
                boolean save = true;
                //投诉问题列表
                List<IssueReply> issueReplyList = null;
                ObjectMapper objectMapper = new ObjectMapper();
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, IssueReply.class);
                issueReplyList = objectMapper.readValue(replyList, javaType);
                for (IssueReply issueReply : issueReplyList) {
                    //根据id查询整改是否已回复，当已存在，则进行更新  不存在，则插入
                    IssueReply reply = issueReplyService.queryReply(issueReply.getIssueId(), type);
                    if (reply != null) {
                        if (type == IssueReplyTypelEnum.RECTIFICATION_REPLY.getCode()) {
                            reply.setQualification(issueReply.getQualification());
                            if (!"合格".equalsIgnoreCase(issueReply.getQualification())) {
                                save = false;
                            }
                            reply.setReplyContent(issueReply.getReplyContent());
                            reply.setReplyTime(new Date());
                        } else if (type == IssueReplyTypelEnum.TECHNICIAN_REPLY.getCode()) {
                            reply.setFileName(issueReply.getFileName());
                            reply.setFilePath(issueReply.getFilePath());
                            reply.setReplyTime(new Date());
                        } else {
                            reply.setReplyContent(issueReply.getReplyContent());
                            reply.setReplyTime(new Date());
                        }
                        issueReplyService.updateByPrimaryKeySelective(reply);
                    } else {
                        if (!"合格".equalsIgnoreCase(issueReply.getQualification())) {
                            save = false;
                        }
                        issueReplyService.insertSelective(issueReply);
                    }
                }
                if (issueReplyList.size() == 0) {
                    save = false;
                }

                if (save != false) {
                    projectComplaint.setCompleteTime(new Date());
                }
                projectComplaintService.updateByPrimaryKeySelective(projectComplaint);

            } else {
                json.setOk(false);
                json.setMessage("请先登录");
                return json;
            }

        } catch (Exception e) {
            json.setOk(false);
            json.setMessage("保存失败");
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 技术上传报告下载
     *
     * @param request
     * @param response
     * @return ResponseEntity<byte [ ]>
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @Title fileDownload
     * @Description
     */
    @RequestMapping(value = "technicianFile")
    public ResponseEntity<byte[]> technicianFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            IssueReply issueReply = issueReplyService.selectByPrimaryKey(id);
            ResponseEntity<byte[]> download = OperationFileUtil.download(issueReply.getFilePath(), issueReply.getFileName());
            return download;
        } else {
            throw new RuntimeException("未获取到路径");
        }
    }


    /**
     * 上传投诉问题照片
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/uploadIssuePic")
    @ResponseBody
    public JsonResult uploadIssuePic(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            String path = UploadAndDownloadPathUtil.getComplaintPath() + File.separator + projectNo;
            File file = new File(path);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdir();
            }
            //调用保存文件的帮助类进行保存文件(文件上传，form表单提交)
            String picNames = "";
            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload(request, path + File.separator);
            if (!(multiFileUpload == null || multiFileUpload.size() == 0)) {
                Set<String> keySet = multiFileUpload.keySet();
                for (String key : keySet) {
                    String pic = multiFileUpload.get(key);
                    picNames += pic + ",";
                }
                if (picNames.endsWith(",")) {
                    picNames = picNames.substring(0, picNames.length() - 1);
                }
            }
            jsonResult.setData(picNames);
            jsonResult.setOk(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage(e.getMessage());
        }
        return jsonResult;
    }


    /**
     * 图片文件上传 (BASE64)
     */
    @ResponseBody
    @RequestMapping("fileUploadPicture")
    public JsonResult fileUploadPicture(String imgdata, HttpServletRequest request) {

        JsonResult jsonResult = new JsonResult();
        BASE64Decoder decoder = new BASE64Decoder();
        FileOutputStream fos = null;
        try {
            String fileName = request.getParameter("fileName");
            //获取相对路径
            String path = UploadAndDownloadPathUtil.getComplaintPath();
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            //新的图片名称
            String newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            String imgPath = path + newFileName;
            // new一个文件对象用来保存图片，默认保存当前工程根目录
            File imageFile = new File(imgPath);
            // 创建输出流
            fos = new FileOutputStream(imageFile);
            // 获得一个图片文件流，我这里是从flex中传过来的
            byte[] result = decoder.decodeBuffer(imgdata.split(",")[1]);//解码
            for (int i = 0; i < result.length; ++i) {
                if (result[i] < 0) {// 调整异常数据
                    result[i] += 256;
                }
            }
            fos.write(result);
            fos.flush();

            jsonResult.setData(newFileName);
            jsonResult.setMessage("上传成功");
            jsonResult.setOk(true);

        } catch (Exception e1) {
            LOG.error("上传图片失败", e1);
            jsonResult.setMessage("上传失败");
            jsonResult.setOk(false);
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonResult;
    }


    /**
     * 用于投诉上传进度条显示
     *
     * @param session
     * @return Progress
     * @Title initCreateInfo
     * @Description
     */
    @RequestMapping(value = "/progressStatus", method = RequestMethod.POST)
    @ResponseBody
    public Progress initCreateInfo(HttpSession session) {

        Progress status = (Progress) session.getAttribute("status");
        if (status == null) {
            return status;
        }
        return status;
    }


    /**
     * 查询出货单列表
     *
     * @param request
     * @param response
     * @return String
     * @Title queryShippingList
     * @Description
     */
    @RequestMapping("/queryShippingList")
    public String queryShippingList(HttpServletRequest request, HttpServletResponse response) {

        try {
            String pageStr = request.getParameter("pageStr");
            String inputKey = request.getParameter("inputKey");// 搜索词
            String pageSizeStr = request.getParameter("pageSize");
            String sampleOrProductStr = request.getParameter("sampleOrProduct");
            String isCompleteStr = request.getParameter("isComplete");
            Integer roleNo = null;                            // 判断是管理员，销售，采购
            // String userName = WebCookie.getUserName(request);
            String userName = SessionIdUtil.getUserName(request.getSession().getId());
            if (StringUtils.isNotBlank(userName)) {
                User user = userService.findUserByName(userName);
                roleNo = user.getRoleNo();
            } else {
                return "redirect:/index.jsp";
            }


            //页数
            Integer page = null;
            if (StringUtils.isNotBlank(pageStr)) {
                page = Integer.parseInt(pageStr);
            } else {
                page = 1;
            }
            //每页显示数
            Integer pageSize = null;
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            } else {
                pageSize = 100;
            }
            ShippingConfirmationQuery shippingConfirmationQuery = new ShippingConfirmationQuery();
            shippingConfirmationQuery.setPageSize(pageSize);
            shippingConfirmationQuery.setPageNumber(pageSize * (page - 1));
            shippingConfirmationQuery.setRoleNo(roleNo);
            shippingConfirmationQuery.setUserName(userName);
            shippingConfirmationQuery.setInputKey(inputKey);
            //判断样品还是大货
            int sampleOrProduct = -1;
            //判断是否完成
            int isComplete = 0;
            if (StringUtils.isNotBlank(sampleOrProductStr)) {
                sampleOrProduct = Integer.parseInt(sampleOrProductStr);
            }
            if (StringUtils.isNotBlank(isCompleteStr)) {
                isComplete = Integer.parseInt(isCompleteStr);
            }
            shippingConfirmationQuery.setSampleOrProduct(sampleOrProduct);
            shippingConfirmationQuery.setIsComplete(isComplete);
            List<ShippingConfirmation> shippings = shippingConfirmationService.selectByProjectNo(shippingConfirmationQuery);
            for (ShippingConfirmation shippingConfirmation : shippings) {
                //查询投诉(该项目历史投诉)
                String projectNo = shippingConfirmation.getProjectNo();
                if (projectNo.contains("-")) {
                    projectNo = projectNo.split("-")[0];
                }
                //根据投诉历史是否完成，判断是否能够出货
                //查询该项目所有历史投诉
                ProjectComplaintQuery projectComplaintQuery = new ProjectComplaintQuery();
                projectComplaintQuery.setInputKey(projectNo);
                projectComplaintQuery.setRoleNo(100);
                projectComplaintQuery.setPageNumber(-1);
                //List<ProjectComplaint> complaintList = projectComplaintService.queryComplaintList(projectComplaintQuery);
                Boolean isComplaintComplete = true;
					 /*for (ProjectComplaint projectComplaint2 : complaintList) {
						 Date completeTime = projectComplaint2.getCompleteTime();
						 if(completeTime == null){
							 isComplaintComplete = false;
						 }
					 }*/
                int complaintCount = projectComplaintService.getQueryComplaintCount(projectComplaintQuery);
                if (complaintCount > 0) {
                    isComplaintComplete = false;
                }

                //查询是否终检没问题
                Boolean isSampleNoProblem = false;
                Boolean isProductNoProblem = false;
                Boolean isProductNoDingDing = false;
                List<QualityReport> reports = qualityReportService.selectByProjectNo(shippingConfirmation.getProjectNo());
                for (QualityReport qualityReport : reports) {
                    if (qualityReport.getType() == SAMPLE) {
                        //如果是样品出货，查询出货证明
                        if (shippingConfirmation.getSampleOrProduct() != null && shippingConfirmation.getSampleOrProduct() == SAMPLE && (qualityReport.getStatus() == 0 || qualityReport.getStatus() == 1)) {
                            isSampleNoProblem = true;
                        }
                    }
                    //如果是终期检验
                    if (qualityReport.getType() == LAST && shippingConfirmation.getSampleOrProduct() != null && shippingConfirmation.getSampleOrProduct() == PRODUCT && (qualityReport.getStatus() == 0 || qualityReport.getStatus() == 1)) {
                        isProductNoProblem = true;
                    }
                    //如果是终期检验
                    if (qualityReport.getProcessInstanceId() != null && "COMPLETED".toLowerCase().equalsIgnoreCase(qualityReport.getDingdingStatus()) && "agree".equalsIgnoreCase(qualityReport.getDingdingResult())) {
                        isProductNoDingDing = true;
                    } else if (qualityReport.getProcessInstanceId() != null) {
                        isProductNoDingDing = false;
                    } else {
                        isProductNoDingDing = true;
                    }
                }
                //查询产品360度视频
                List<FactoryQualityInspectionVideo> videoList = factoryQualityInspectionVideoService.selectByProjectNoAndType(projectNo, PRODUCT_VIDEO);
                //判断是否允许签名
                Boolean isSign = false;
                if (shippingConfirmation.getFirstPerson() != null && shippingConfirmation.getSecondPerson() != null
                        && shippingConfirmation.getThirdPerson() != null
                        && isComplaintComplete == true
                ) {
                    if (shippingConfirmation.getSampleOrProduct() == 1 && (isProductNoProblem == true || (isProductNoProblem == false && StringUtils.isNotBlank(shippingConfirmation.getShipmentAgreement())))) {
                        if (isProductNoDingDing == true) {
                            isSign = true;
                        }
                    }
                    if ((shippingConfirmation.getSampleOrProduct() == 0 && videoList != null && videoList.size() > 0)) {
                        if ((shippingConfirmation.getSampleFileName() != null) || isSampleNoProblem == true) {
                            if (isProductNoDingDing == true) {
                                isSign = true;
                            }
                        }
                    }
                }
                //针对之前的出货单，不进行拦截（id 239）
                if (shippingConfirmation.getId() <= 239) {
                    isSign = true;
                }
                shippingConfirmation.setIsSign(isSign);
            }
            int totalCount = shippingConfirmationService.count(shippingConfirmationQuery);
            request.setAttribute("shippings", shippings);
            request.setAttribute("inputKey", inputKey);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("page", page);
            request.setAttribute("userName", userName);
            request.setAttribute("count", totalCount);
            request.setAttribute("sampleOrProduct", sampleOrProduct);
            request.setAttribute("isComplete", isComplete);
            request.setAttribute("isCompleteStr", isCompleteStr);
            //计算尾页
            Integer lastNum = new BigDecimal(totalCount).divide(new BigDecimal(pageSize)).setScale(0, BigDecimal.ROUND_UP).intValue();
            request.setAttribute("lastNum", lastNum);
            request.setAttribute("sessionId", request.getSession().getId());

            LOG.info("-----------queryShippingList userName:" + userName);


        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "confirm_list";
    }


    /**
     * 保存出运信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addShippingConfirmation")
    @ResponseBody
    public JsonResult addShippingConfirmation(HttpServletRequest request, HttpServletResponse response) {
        JsonResult json = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isBlank(userName)) {
                json.setOk(false);
                json.setMessage("您还未登录");
                return json;
            }

            if (StringUtils.isNotBlank(projectNo)) {
                Project project = projectService.selectProjctDetails(projectNo);
                if (project == null) {
                    json.setOk(false);
                    json.setMessage("该项目还未录入");
                    return json;
                } else {
                    ShippingConfirmation shippingConfirmation = new ShippingConfirmation();
                    shippingConfirmation.setProjectNo(projectNo);
                    shippingConfirmation.setCreatePerson(userName);
                    String caseno = projectNo.substring(3, projectNo.length());
                    String sateDate = "";

                    Project project1 = projectService.selectProjctDetails(projectNo);
                    List<Products> list = productsService.selectSaildate(caseno);
                    if (list.size() == 3) {
                        sateDate = list.get(2).getSailingDate() + "/" + list.get(1).getSailingDate() + "/" + list.get(0).getSailingDate();
                    } else if (list.size() == 2) {
                        sateDate = list.get(1).getSailingDate() + "/" + list.get(0).getSailingDate();
                    } else if (list.size() == 1) {
                        sateDate = list.get(0).getSailingDate();
                    }
                    if (sateDate != null && !"".equalsIgnoreCase(sateDate)) {
                        project1.setSailingDate(sateDate);
                    }
                    int num = projectService.updateByPrimaryKeySelective(project1);
                    shippingConfirmationService.insertSelective(shippingConfirmation);

                    DingTalkThread.setShippingConfirmation(shippingConfirmation.getId());
                    json.setOk(true);
                    json.setData(shippingConfirmation.getId());
                    return json;
                }
            } else {
                json.setOk(false);
                json.setMessage("项目号不能为空");
                return json;
            }

        } catch (Exception e) {
            json.setOk(false);
            json.setMessage("出货单添加错误");
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 跳转出运信息页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/toAdd/{type}")
    public String toAdd(HttpServletRequest request, @PathVariable Integer type) {
        String userName = WebCookie.getUserName(request);
        if (StringUtils.isBlank(userName)) {
            return "redirect:/index.jsp";
        }
        //根据id查询
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
            Project project = projectService.selectProjectByProjectNo(shippingConfirmation.getProjectNo());
            request.setAttribute("shippingConfirmation", shippingConfirmation);
            request.setAttribute("project", project);
            //质检录入页面查询质检报告
            if (type == 2) {
                List<QualityReport> reports = qualityReportService.selectByProjectNo(shippingConfirmation.getProjectNo());
                request.setAttribute("reports", reports);
            }
            if (type == 3) {
                MeetingRecord meetingRecord = new MeetingRecord();
                meetingRecord.setProjectNo(shippingConfirmation.getProjectNo());
                meetingRecord.setMeetingName("质量分析会");
                List<MeetingRecord> meetings = meetingRecordService.selectMeetings(meetingRecord);
                request.setAttribute("meetings", meetings);
            }

        }
        if (type == 1) {
            return "confirm_list_first";
        } else if (type == 2) {
            return "confirm_list_second";
        } else if (type == 3) {
            return "confirm_list_three";
        } else if (type == 4) {
            return "confirm_list_four";
        } else {
            return "confirm_list_share";
        }
    }

    /**
     * 保存出运信息
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/addFirst")
    public JsonResult addFirst(HttpServletRequest request, HttpServletResponse response) {
        JsonResult json = new JsonResult();
        try {
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isBlank(userName)) {
                json.setOk(false);
                json.setMessage("您还未登录");
                return json;
            }
            //根据id查询
            if (StringUtils.isNotBlank(request.getParameter("id"))) {

                String customerName = request.getParameter("customerName"); //客户名
                String productName = request.getParameter("productName");   //产品名
                String projectAmount = request.getParameter("projectAmount"); //项目金额
                String productNumber = request.getParameter("productNumber"); //本次发货数量
                ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
                String projectNo = shippingConfirmation.getProjectNo();
                if (productNumber != null && !"".equalsIgnoreCase(productNumber)) {
                    shippingConfirmation.setProductNumber(Integer.parseInt(productNumber));
                }
                if (StringUtils.isNotBlank(customerName)) {
                    shippingConfirmation.setCustomerName(customerName);
                }
                if (StringUtils.isNotBlank(productName)) {
                    shippingConfirmation.setProductName(productName);
                }
                if (StringUtils.isNotBlank(request.getParameter("sampleOrProduct"))) {
                    shippingConfirmation.setSampleOrProduct(Integer.parseInt(request.getParameter("sampleOrProduct")));
                }
                if (StringUtils.isNotBlank(request.getParameter("isPlastic"))) {
                    shippingConfirmation.setIsPlastic(Integer.parseInt(request.getParameter("isPlastic")));
                }
                if (StringUtils.isNotBlank(request.getParameter("isBossConfirm"))) {
                    shippingConfirmation.setIsBossConfirm(Integer.parseInt(request.getParameter("isBossConfirm")));
                }
                if (StringUtils.isNotBlank(request.getParameter("shippingWay"))) {
                    shippingConfirmation.setShippingWay(request.getParameter("shippingWay"));
                }
                if (StringUtils.isNotBlank(request.getParameter("checkQty"))) {
                    shippingConfirmation.setCheckQty(request.getParameter("checkQty"));
                }
                if (StringUtils.isNotBlank(request.getParameter("orders"))) {
                    shippingConfirmation.setOrders(request.getParameter("orders"));
                }
                if (StringUtils.isNotBlank(request.getParameter("boxNumber"))) {
                    shippingConfirmation.setBoxNumber(request.getParameter("boxNumber"));
                }
                if (StringUtils.isNotBlank(request.getParameter("openQty"))) {
                    shippingConfirmation.setOpenQty(request.getParameter("openQty"));
                }
                if (StringUtils.isNotBlank(request.getParameter("spendTime"))) {
                    shippingConfirmation.setSpendTime(request.getParameter("spendTime"));
                }
                if (StringUtils.isNotBlank(request.getParameter("checkConclusion"))) {
                    shippingConfirmation.setCheckConclusion(request.getParameter("checkConclusion"));
                }
                if (StringUtils.isNotBlank(request.getParameter("noExecuted"))) {
                    shippingConfirmation.setNoExecuted(request.getParameter("noExecuted"));
                }
                if (StringUtils.isNotBlank(request.getParameter("concessiveAccept"))) {
                    shippingConfirmation.setConcessiveAccept(request.getParameter("concessiveAccept"));
                }
                if (StringUtils.isNotBlank(request.getParameter("meetingConclusion"))) {
                    shippingConfirmation.setMeetingConclusion(request.getParameter("meetingConclusion"));
                }
                if (StringUtils.isNotBlank(request.getParameter("notPaid"))) {
                    shippingConfirmation.setNotPaid(request.getParameter("notPaid"));
                }
                if (StringUtils.isNotBlank(request.getParameter("scale"))) {
                    shippingConfirmation.setScale(request.getParameter("scale"));
                }
                if (StringUtils.isNotBlank(request.getParameter("customerConfirmWay"))) {
                    shippingConfirmation.setCustomerConfirmWay(request.getParameter("customerConfirmWay"));
                }
                if (StringUtils.isNotBlank(request.getParameter("isQualityReportEn"))) {
                    shippingConfirmation.setIsQualityReportEn(Integer.parseInt(request.getParameter("isQualityReportEn")));
                }
                if (StringUtils.isNotBlank(request.getParameter("drawbackProduct"))) {
                    shippingConfirmation.setDrawbackProduct(request.getParameter("drawbackProduct"));
                }
                if (StringUtils.isNotBlank(request.getParameter("drawbackRate"))) {
                    shippingConfirmation.setDrawbackRate(request.getParameter("drawbackRate"));
                }
                if (StringUtils.isNotBlank(request.getParameter("shippingFee"))) {
                    shippingConfirmation.setShippingFee(request.getParameter("shippingFee"));
                }
                if (StringUtils.isNotBlank(request.getParameter("salesWorry"))) {
                    shippingConfirmation.setSalesWorry(request.getParameter("salesWorry"));
                }
                if (StringUtils.isNotBlank(request.getParameter("productChanges"))) {
                    shippingConfirmation.setProductChanges(Integer.parseInt(request.getParameter("productChanges")));
                }
                if (StringUtils.isNotBlank(request.getParameter("weight"))) {
                    shippingConfirmation.setWeight(request.getParameter("weight"));
                }
                if (StringUtils.isNotBlank(request.getParameter("shippingInformation"))) {
                    shippingConfirmation.setShippingInformation(request.getParameter("shippingInformation"));
                }

                //根据当前步骤更新
                if (StringUtils.isNotBlank(request.getParameter("step"))) {
                    String step = request.getParameter("step");
                    switch (step) {
                        case "first":
                            shippingConfirmation.setFirstPerson(userName);
                            shippingConfirmation.setFirstTime(new Date());
                            break;
                        case "second":
                            shippingConfirmation.setSecondPerson(userName);
                            shippingConfirmation.setSecondTime(new Date());
                            break;
                        case "third":
                            shippingConfirmation.setThirdPerson(userName);
                            shippingConfirmation.setThirdTime(new Date());
                            break;
                        case "fourth":
                            shippingConfirmation.setFourthPerson(userName);
                            shippingConfirmation.setFourthTime(new Date());
                            break;
                        default:
                            break;
                    }
                }


                //更新签名数据
                if (StringUtils.isNotBlank(request.getParameter("type"))) {
                    Integer type = Integer.parseInt(request.getParameter("type"));
                    Integer num = Integer.parseInt(request.getParameter("num"));
                    switch (type) {
                        case SALES:
                            shippingConfirmation.setSalesConfirm(userName);
                            shippingConfirmation.setSalesConfirmTime(new Date());
                            break;
                        case PURCHASE:
                            shippingConfirmation.setPurchaseConfirm(userName);
                            shippingConfirmation.setPurchaseConfirmTime(new Date());
                            break;
                        case QUALITY_LEADER:
                            shippingConfirmation.setQualityLeaderConfirm(userName);
                            shippingConfirmation.setQualityLeaderConfirmTime(new Date());
                            shippingConfirmation.setQualityLeaderConfirmId(num);
                            break;
                        case PURCHASE_LEADER:
                            shippingConfirmation.setPurchaseLeaderConfirm(userName);
                            shippingConfirmation.setPurchaseLeaderConfirmTime(new Date());
                            shippingConfirmation.setPurchaseLeaderConfirmId(num);
                            break;
                        case BOSS:
                            shippingConfirmation.setBossConfirm(userName);
                            shippingConfirmation.setBossConfirmTime(new Date());
                            shippingConfirmation.setBossConfirmId(num);
                            break;
                        default:
                            break;
                    }
                }


                Project project = new Project();
                project.setProjectAmount(projectAmount);
                project.setProjectNo(projectNo);
                projectService.updateProjectInfo(project);
                shippingConfirmationService.updateByPrimaryKeySelective(shippingConfirmation, userName);
                json.setOk(true);
                json.setMessage("保存成功");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            json.setOk(false);
            json.setMessage("出货单保存失败");
        } catch (ParseException e) {
            e.printStackTrace();
            json.setOk(false);
            json.setMessage("出货单保存失败");
        }
        return json;
    }


    /**
     * 跳转出运信息页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/detail")
    public String detail(HttpServletRequest request) {
        // String userName = WebCookie.getUserName(request);
            /*String sessionId = request.getParameter("sessionId");
            LOG.error("sessionId:" + sessionId);
            String userName = String.valueOf(request.getSession().getAttribute(sessionId));
            LOG.error("userName:" + userName);*/
        String userName = request.getParameter("userName");
        String page = "";
        if (org.apache.commons.lang3.StringUtils.isBlank(userName)) {
            page = "error";
            return page;
        }
        try {

            //根据id查询
            if (StringUtils.isNotBlank(request.getParameter("id"))) {
                if (StringUtils.isNotBlank(request.getParameter("dingTalkId"))) {
                    String idStr = request.getParameter("id");
                    int id = Integer.parseInt(idStr);
                    QualityReport qr = qualityReportService.selectByPrimaryKey(id);
                    String conclusion = qr.getConclusion();
                    //去除conclusion内换行
                    if (StringUtils.isNotBlank(conclusion)) {
                        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                        Matcher m = p.matcher(conclusion);
                        conclusion = m.replaceAll("");
                        qr.setConclusion(conclusion);
                    }
                    List<ProjectTask> ptList = projectTaskService.selectByQualityId(id);
                    if (ptList != null) {
                        qr.setProjectTaskList(ptList);
                    }
                    qr.setTypeView(QualityTypeEnum.getEnum(qr.getType()).getValue());
                    qr.setStatusView(QualityStatusEnum.getEnum(qr.getStatus()).getValue());

                    qr.setCreatetimeView(DateFormat.date2String(qr.getCreatetime()));

                    List<QualityPicExplain> picList = qualityPicExplainService.queryByReportId(id);
                    Project project = projectService.selectProjctDetails(qr.getProjectNo());

                    //项目号内特殊字符处理
                    String projectName = project.getProjectName();
                    if (StringUtils.isNotBlank(projectName)) {
                        project.setProjectName(projectName.replace("\"", ""));
                    }

                    //查询评论
                    List<Comment> comments = projectCommentService.selectByReportId(id);
                    //查询采购布置任务情况
                    String purchaseTask = "";
                    List<ProjectTask> tasks = projectTaskService.selectByQualityId(id);
                    if (tasks != null && tasks.size() > 0) {
                        int tl = tasks.size();
                        if ((TaskStatusEnum.DEFAULT.getCode() + "").equals(tasks.get(tl - 1).getTaskStatus())) {
                            purchaseTask = "未完成";
                        } else if ((TaskStatusEnum.FINISH.getCode() + "").equals(tasks.get(tl - 1).getTaskStatus())) {
                            purchaseTask = "已完成  " + (tasks.get(tl - 1).getOperateExplain() == null ? "" : tasks.get(tl - 1).getOperateExplain());
                        } else if ((TaskStatusEnum.PAUSE.getCode() + "").equals(tasks.get(tl - 1).getTaskStatus())) {
                            purchaseTask = "已暂停";
                        } else if ((TaskStatusEnum.CANCEL.getCode() + "").equals(tasks.get(tl - 1).getTaskStatus())) {
                            purchaseTask = "已取消";
                        }
                    }
                    if (StringUtils.isNotBlank(userName)) {
                        User user = userService.selectUserByName(userName);
                        request.setAttribute("user", user);
                    }
                    //查询下单工厂列表
                    List<ProjectFactory> projectFactoryList = projectFactoryService.selectByProjectNo(qr.getProjectNo());
                    //根据工厂名去除重复工厂
                    projectFactoryList = projectFactoryList.stream().filter(distinctByKey(factory -> factory.getFactoryName())).collect(Collectors.toList());
                    //质检视频
                    List<FactoryQualityInspectionVideo> videos = factoryQualityInspectionVideoService.selectByProjectNo(qr.getProjectNo());
                    //查询需求汇总
                    ProductionPlan productionPlan = productionPlanService.selectDemandByProjectNo(qr.getProjectNo());
                    if (productionPlan != null) {
                        String node = productionPlan.getPlanNode();
                        if (StringUtils.isNotBlank(node)) {
                            productionPlan.setPlanNode(URLEncoder.encode(node, "utf-8"));
                        }
                    }
                    //查询检验计划完成情况
                    List<InspectionPlan> planList = null;
                    List<InspectionPlan> plans = null;
                    if (qr.getInspectionCreateDate() != null) {
                        planList = inspectionPlanService.selectByProjectNo(qr.getProjectNo(), qr.getInspectionCreateDate());
                        plans = planList.stream().filter(distinctByKey(plan -> plan.getProductComponent())).collect(Collectors.toList());
                    }
                    request.setAttribute("qualityReport", qr);
                    request.setAttribute("picList", picList);
                    request.setAttribute("project", project);
                    request.setAttribute("comments", comments);
                    request.setAttribute("userName", userName);
                    request.setAttribute("purchaseTask", purchaseTask);
                    request.setAttribute("projectFactoryList", projectFactoryList);
                    request.setAttribute("videos", videos);
                    request.setAttribute("productionPlan", productionPlan);
                    request.setAttribute("planList", planList);
                    request.setAttribute("plans", plans);
                    page = "share_report";
                } else {
                    String work = "";
                    if (StringUtils.isNotBlank(request.getParameter("work"))) {
                        work = "confirm_list_share-print";
                    } else {
                        work = "confirm_list_share";
                    }

                    ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));


                    // 获取钉钉审批数据 // shipping_confirmation
                    List<ShippingConfirmation> shippingConfirmations = shippingConfirmationService.queryShippingConfirmationByNo(shippingConfirmation.getProjectNo());

                    if (CollectionUtils.isNotEmpty(shippingConfirmations)) {
                        Map<Integer, List<ShippingConfirmation>> map = shippingConfirmations.stream().collect(Collectors.groupingBy(ShippingConfirmation::getIsComplete));
                        request.setAttribute("no_complete", 0);
                        request.setAttribute("is_complete", 0);
                        map.forEach((k, v) -> {
                            if (k == 0) {
                                request.setAttribute("no_complete", CollectionUtils.isNotEmpty(v) ? v.size() : 0);
                            } else if (k == 1) {
                                request.setAttribute("is_complete", CollectionUtils.isNotEmpty(v) ? v.size() : 0);
                            }
                        });
                        shippingConfirmations.clear();
                        map.clear();
                    } else {
                        request.setAttribute("no_complete", 0);
                        request.setAttribute("is_complete", 0);
                    }


                    request.setAttribute("shippingConfirmation", shippingConfirmation);
                    ShippingConfirmation shippingConfirmation1 = new ShippingConfirmation();
                    if (shippingConfirmation != null) {
                        //判断当前出货次数
                        Integer count = shippingConfirmationService.selectCountByProjectNoAndType(shippingConfirmation.getProjectNo(), PRODUCT);
                        //获取项目金额
                        Double amount = 0.00;
                        String projectAmount = shippingConfirmation.getProjectAmount();
                        if (projectAmount != null && !"".equalsIgnoreCase(projectAmount)) {
                            //String regEx="[^0-9]";
                            //Pattern p = Pattern.compile(regEx);
                            //Matcher m = p.matcher(projectAmount);
                            //projectAmount = m.replaceAll("").trim();
                            amount = Double.parseDouble(projectAmount);
                        }
                        //判断是否需要老板签字
                        //1、非塑料类样品 出货   2、金额大于 1万美元的第一次 大货出货
                        if ((shippingConfirmation.getIsPlastic() == 0 && shippingConfirmation.getSampleOrProduct() == SAMPLE) && (amount > 1) || ((shippingConfirmation.getSampleOrProduct() == PRODUCT) && count == 1 && !(shippingConfirmation.getProjectNo().contains("-"))) && (amount > 1)) {
                            request.setAttribute("bossConfirm", true);
                        }
                        if (amount > 1.5) {
                            request.setAttribute("bossConfirm", true);
                        }

                        //查询项目
                        Project project = projectService.selectProjectByProjectNo(shippingConfirmation.getProjectNo());
                        //查询投诉(该项目历史投诉)
                        String projectNo = shippingConfirmation.getProjectNo();
                        if (projectNo.contains("-")) {
                            projectNo = projectNo.split("-")[0];
                        }
                        //根据投诉历史是否完成，判断是否能够出货
                        //查询该项目所有历史投诉
                        ProjectComplaintQuery projectComplaintQuery = new ProjectComplaintQuery();
                        projectComplaintQuery.setInputKey(projectNo);
                        projectComplaintQuery.setRoleNo(100);
                        projectComplaintQuery.setPageNumber(-1);
                        projectComplaintQuery.setSeriousLevel(-1);
                        List<ProjectComplaint> complaintList = projectComplaintService.queryComplaintList(projectComplaintQuery);
                        Boolean isComplaintComplete = true;
                        for (ProjectComplaint projectComplaint2 : complaintList) {
                            Date completeTime = projectComplaint2.getCompleteTime();
                            if (completeTime == null) {
                                isComplaintComplete = false;
                            }

                            request.setAttribute("bossConfirm", true);

                        }

                        //查询项目相关任务
                        List<ProjectTask> tasks = projectTaskService.selectByProjectNo(shippingConfirmation.getProjectNo());
                        //查询质量、技术关键词
                        List<AnalysisIssue> analysisIssueList = analysisIssueService.selectByProjectNo(shippingConfirmation.getProjectNo(), 1);
                        QualityAnalysis qualityAnalysis = qualityAnalysisService.selectByProjectNo(shippingConfirmation.getProjectNo());
                        //查询质检报告
                        Boolean isSampleCheck = false;
                        //查询是否终检没问题
                        Boolean isSampleNoProblem = false;
                        Boolean isProductNoProblem = false;
                        Boolean isProductNoDingDing = false;
                        //终检有问题，是否上传图纸、视频
                        String productFileName = null;
                        String operateExplain = null;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        List<QualityReport> reports = qualityReportService.selectByProjectNo(shippingConfirmation.getProjectNo());
                        List<QualityReport> reports1 = new ArrayList<QualityReport>();
                        List<QualityReport> reports2 = new ArrayList<QualityReport>();
                        for (QualityReport qualityReport : reports) {
                            if (qualityReport.getType() == SAMPLE) {
                                isSampleCheck = true;
                                //如果是样品出货，查询出货证明
                                if (shippingConfirmation.getSampleOrProduct() == SAMPLE && (qualityReport.getStatus() == 0 || qualityReport.getStatus() == 1)) {
                                    isSampleNoProblem = true;
                                }
                            }

                            //如果是终期检验
                            if (qualityReport.getProcessInstanceId() != null && "COMPLETED".toLowerCase().equalsIgnoreCase(qualityReport.getDingdingStatus()) && "agree".equalsIgnoreCase(qualityReport.getDingdingResult())) {
                                isProductNoDingDing = true;
                            } else if (qualityReport.getProcessInstanceId() != null && shippingConfirmation.getSampleOrProduct() == PRODUCT) {
                                Date date1 = format.parse("2020-05-01 00:00:01");
                                if (date1.before(qualityReport.getCreatetime())) {
                                    isProductNoDingDing = false;
                                }
                            } else {
                                isProductNoDingDing = true;
                            }
                            if (isProductNoDingDing == false) {
                                reports2.add(qualityReport);
                            }


                            //如果是终期检验
                            if (qualityReport.getType() == LAST && shippingConfirmation.getSampleOrProduct() == PRODUCT && (qualityReport.getStatus() == 0 || qualityReport.getStatus() == 1)) {
                                isProductNoProblem = true;
                            }
                            //如果是终期检验
                            if (qualityReport.getType() == LAST && shippingConfirmation.getSampleOrProduct() == PRODUCT && qualityReport.getStatus() == 2) {
                                operateExplain = qualityReport.getOperateExplain();
                                productFileName = qualityReport.getProductFileName();
                            }
                            StringBuilder detailView = new StringBuilder("[");
                            detailView.append(qualityReport.getProjectNo()).append("]");
                            detailView.append(
                                    QualityTypeEnum.getEnum(qualityReport.getType()).getValue())
                                    .append(",");
                            detailView.append(
                                    QualityStatusEnum.getEnum(qualityReport.getStatus())
                                            .getValue()).append(",[");
                            detailView.append(qualityReport.getUser()).append("/")
                                    .append(DateFormat.date2String(qualityReport.getCreatetime())).append("]");
                            qualityReport.setDetailView(detailView.toString());
                            if (qualityReport.getDetailView().toLowerCase().contains("有问题".toLowerCase())) {
                                List<Comment> comments = projectCommentService.selectByReportId(qualityReport.getId());
                                if (comments.size() > 0) {
                                    qualityReport.setComment(comments);
                                } else {
                                    qualityReport.setComment(null);
                                }
                                reports1.add(qualityReport);
                            }
                        }
                        ProjectTask task2 = projectTaskService.getDrawingAlteration(shippingConfirmation.getProjectNo());
                        if (task2 != null && (amount > 1)) {
                            request.setAttribute("bossConfirm", true);
                        }
                        if ((project.getCustomerGrade() == 1 || project.getCustomerGrade() == 2 || project.getPlantAnalysis() == 1 || project.getPlantAnalysis() == 2) && (amount > 1)) {
                            request.setAttribute("bossConfirm", true);
                        }
                        //查询产品360度视频
                        List<FactoryQualityInspectionVideo> videoList = factoryQualityInspectionVideoService.selectByProjectNoAndType(shippingConfirmation.getProjectNo(), PRODUCT_VIDEO);
                        //查询项目所有工厂
                        List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(shippingConfirmation.getProjectNo());
                        //判断是否允许签名
                        Boolean isSign = false;
                        if (shippingConfirmation.getFirstPerson() != null && shippingConfirmation.getSecondPerson() != null
                                && shippingConfirmation.getThirdPerson() != null
                                && isComplaintComplete == true
							/*	 &&(					
									 (shippingConfirmation.getSampleOrProduct() == 1 && (isProductNoProblem == true || (isProductNoProblem == false && StringUtils.isNotBlank(shippingConfirmation.getShipmentAgreement()))))
								     ||
								     ((shippingConfirmation.getSampleOrProduct() == 0 && videoList!=null && videoList.size() > 0) || shippingConfirmation.getSampleOrProduct() == 1)  
								   )	*/
                        ) {
                            if (shippingConfirmation.getSampleOrProduct() == 1 && (isProductNoProblem == true || (isProductNoProblem == false && StringUtils.isNotBlank(shippingConfirmation.getShipmentAgreement())) || StringUtils.isNotBlank(productFileName))) {
                                if (isProductNoDingDing == true) {
                                    isSign = true;
                                }
                            }
                            if ((shippingConfirmation.getSampleOrProduct() == 0 && videoList != null && videoList.size() > 0)) {
                                if ((shippingConfirmation.getSampleFileName() != null) || isSampleNoProblem == true) {
                                    if (isProductNoDingDing == true) {
                                        isSign = true;
                                    }
                                }
                            }
                        }

                        //针对之前的出货单，不进行拦截（id 239）
                        if (shippingConfirmation.getId() <= 239) {
                            isSign = true;
                        }
                        //查询留言
                        List<Comment> comments = projectCommentService.selectByShippingId(shippingConfirmation.getId());
						/* if(shippingConfirmation1.getId()!=null ){
							 if(shippingConfirmation1.getId()>0){
							 shippingConfirmationService.updateByPrimaryKeySelective(shippingConfirmation1);
						 }
						 }*/
                        List<QualityPicExplain> picList = qualityPicExplainService.getLast(shippingConfirmation.getProjectNo());
                        request.setAttribute("project", project);
                        request.setAttribute("userName", userName != null ? userName.toLowerCase() : "");
                        request.setAttribute("isComplaintComplete", isComplaintComplete);
                        request.setAttribute("complaintList", complaintList);
                        request.setAttribute("picList", picList);
                        if (reports1.size() > 0) {
                            request.setAttribute("qualityReports1", reports1);
                        } else {
                            request.setAttribute("qualityReports1", null);
                        }
                        if (reports2.size() > 0) {
                            request.setAttribute("qualityReports2", reports2);
                        } else {
                            request.setAttribute("qualityReports2", null);
                        }

                        request.setAttribute("tasks", tasks);
                        request.setAttribute("analysisIssueList", analysisIssueList);
                        request.setAttribute("qualityAnalysis", qualityAnalysis);
                        request.setAttribute("isSampleCheck", isSampleCheck);
                        request.setAttribute("qualityReports", reports);
                        request.setAttribute("videoList", videoList);
                        request.setAttribute("factoryList", factoryList);
                        request.setAttribute("isSampleNoProblem", isSampleNoProblem);
                        request.setAttribute("isProductNoProblem", isProductNoProblem);
                        request.setAttribute("isProductNoDingDing", isProductNoDingDing);
                        request.setAttribute("productFileName", productFileName);
                        request.setAttribute("operateExplain", operateExplain);
                        request.setAttribute("isSign", isSign);
                        request.setAttribute("comments", comments);
                        request.setAttribute("id", Integer.parseInt(request.getParameter("id")));
                        page = work;
                    } else {
                        page = "error";
                    }
                }
            } else {
                page = "error";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }


    /**
     * 删除投诉表
     *
     * @param request
     * @param response
     * @return String
     * @Title delete
     * @Description
     */
    @ResponseBody
    @RequestMapping("/deleteShipping")
    public JsonResult deleteShipping(HttpServletRequest request, HttpServletResponse response) {

        JsonResult jsonResult = new JsonResult();
        try {
            String id = request.getParameter("id");   //项目号
            //查询cookie中用户
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isBlank(userName)) {
                jsonResult.setOk(false);
                jsonResult.setMessage("请先登录");
            }
            if (StringUtils.isNotBlank(id)) {
                ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectByPrimaryKey(Integer.parseInt(id));
                if (userName.equalsIgnoreCase(shippingConfirmation.getCreatePerson()) || userName.equalsIgnoreCase("ninazhao")) {
                    shippingConfirmationService.deleteByPrimaryKey(Integer.parseInt(id));
                    jsonResult.setOk(true);
                    jsonResult.setMessage("已删除");
                } else {
                    jsonResult.setOk(false);
                    jsonResult.setMessage("你没有权限删除");
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage("删除失败");
            LOG.error("====电子出货单删除失败==deleteShipping===", e);

        }
        return jsonResult;
    }


    /**
     * 样品检验上传
     *
     * @param request
     * @param response
     * @return JsonResult
     * @Title upload
     * @Description
     */
    @RequestMapping("/sampleUpload")
    @ResponseBody
    public JsonResult sampleUpload(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String ids = request.getParameter("id");
            String projectNo = request.getParameter("projectNo");
            String drawingName = request.getParameter("fileName");
            String path1 = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator;
            String path = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator + "sample" + File.separator;

            File file1 = new File(path1);
            File file = new File(path);
            if (!file1.exists() && !file1.isDirectory()) {
                file1.mkdir();
            }
            if (!file.exists() && !file.isDirectory()) {
                file.mkdir();
            }

            //根据文件名获取上传文件的位置  数据库保存原始文件名称
            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
            String fileName = "";
            if (!(multiFileUpload == null || multiFileUpload.size() == 0)) {
                fileName = multiFileUpload.get(drawingName);
            }

            String userName = WebCookie.getUserName(request);
            if (StringUtils.isBlank(userName)) {
                jsonResult.setOk(false);
                jsonResult.setMessage("请先登录");
                return jsonResult;
            }

            //更新样品上传
            ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectByPrimaryKey(Integer.parseInt(ids));
            if (shippingConfirmation != null) {
                shippingConfirmation.setSampleFileOriginalName(drawingName);
                shippingConfirmation.setSampleFileName(fileName);
                shippingConfirmationService.updateByPrimaryKeySelective(shippingConfirmation, userName);
            }

            jsonResult.setOk(true);
            jsonResult.setData(fileName);
            return jsonResult;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage("上传失败");
            return jsonResult;
        } catch (IOException e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage("上传失败");
            return jsonResult;
        } catch (ParseException e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage("上传失败");
            return jsonResult;
        }
    }


    /**
     * 出货证明上传
     *
     * @param request
     * @param response
     * @return JsonResult
     * @Title upload
     * @Description
     */
    @RequestMapping("/shipmentAgreementUpload")
    @ResponseBody
    public JsonResult shipmentAgreementUpload(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String ids = request.getParameter("id");
            String projectNo = request.getParameter("projectNo");
            String drawingName = request.getParameter("fileName");
            String path1 = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator;
            String path = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator + "shipment" + File.separator;

            File file1 = new File(path1);
            File file = new File(path);
            if (!file1.exists() && !file1.isDirectory()) {
                file1.mkdir();
            }
            if (!file.exists() && !file.isDirectory()) {
                file.mkdir();
            }

            //根据文件名获取上传文件的位置  数据库保存原始文件名称
            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
            String fileName = "";
            if (!(multiFileUpload == null || multiFileUpload.size() == 0)) {
                fileName = multiFileUpload.get(drawingName);
            }

            String userName = WebCookie.getUserName(request);
            if (StringUtils.isBlank(userName)) {
                jsonResult.setOk(false);
                jsonResult.setMessage("请先登录");
                return jsonResult;
            }

            //更新样品上传
            ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectByPrimaryKey(Integer.parseInt(ids));
            if (shippingConfirmation != null) {
                shippingConfirmation.setShipmentAgreement(fileName);
                shippingConfirmationService.updateByPrimaryKeySelective(shippingConfirmation, userName);
            }

            jsonResult.setOk(true);
            jsonResult.setData(fileName);
            return jsonResult;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage("上传失败");
            return jsonResult;
        } catch (IOException e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage("上传失败");
            return jsonResult;
        } catch (ParseException e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage("上传失败");
            return jsonResult;
        }
    }


    /**
     * 获取投诉检验报告上传内容
     *
     * @param file
     * @param request
     * @param model
     * @return String
     * @throws IllegalStateException
     * @throws IOException
     * @Title upload
     * @Description
     */
    @ResponseBody
    @RequestMapping(value = "/uploadComplaintInspectionReport")
    public JsonResult uploadComplaintInspectionReport(HttpServletRequest request, ModelMap model) {
        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            String projectComplaintId = request.getParameter("projectComplaintId");
            String userName = WebCookie.getUserName(request);
            //获取相对路径
            String path = UploadAndDownloadPathUtil.getComplaintPath() + File.separator + projectNo + File.separator;
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
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
            ComplaintInspectionReport report = new ComplaintInspectionReport();
            report.setComplaintId(Integer.parseInt(projectComplaintId));
            report.setCreateDate(new Date());
            report.setFileName(originalFilename);
            report.setNewFileName(newFileName);
            report.setProjectNo(projectNo);
            report.setReviewer(userName);
            int id = complaintInspectionReportService.insertSelective(report);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("path", path + newFileName);
            map.put("originalFilename", originalFilename);
            map.put("newFileName", newFileName);
            map.put("projectNo", projectNo);
            map.put("createDate", new Date());
            map.put("userName", userName);
            map.put("projectComplaintId", projectComplaintId);
            map.put("id", report.getId());
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
     * 获取投诉上传内容
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
    @RequestMapping(value = "/uploadComplaint")
    public JsonResult uploadComplaint(HttpServletRequest request, ModelMap model) {

        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            //获取相对路径
            String path = UploadAndDownloadPathUtil.getComplaintPath() + File.separator + projectNo + File.separator;
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
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
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("path", path + newFileName);
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
     * 针对对象去去重
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

    private Date getFinishDate() {

        Calendar ca = Calendar.getInstance();

        ca.add(Calendar.DATE, 7);

        int day = ca.get(Calendar.DAY_OF_WEEK);

        if (day == 7 || day == 1) {
            ca.add(Calendar.DATE, 2);
        }
        Date finishTime = ca.getTime();

        return finishTime;

    }


    @ResponseBody
    @RequestMapping(value = "/updateDeliveryConfirmation")
    public JsonResult updateDeliveryConfirmation(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        try {
            String id = request.getParameter("id");
            String process_instance_id = DingTalkThread.sendDeliveryConfirmation(id);
            ShippingConfirmation projectComplaint = new ShippingConfirmation();
            projectComplaint.setId(Integer.parseInt(id));
            projectComplaint.setDeliveryConfirmation(1);
            projectComplaint.setProcessInstanceId(process_instance_id);
            shippingConfirmationService.updateByPrimaryKeySelective1(projectComplaint);
            jsonResult.setOk(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage("上传失败");
            jsonResult.setOk(false);
        }
        return jsonResult;
    }


    @ResponseBody
    @RequestMapping(value = "/updateVerifyComplaint")
    public JsonResult updateVerifyComplaint(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        try {
            String id = request.getParameter("id");
            String verifyComplaint = request.getParameter("verifyComplaint");


            ProjectComplaint projectComplaint = new ProjectComplaint();
            projectComplaint.setId(Integer.parseInt(id));
            projectComplaint.setVerifyComplaint(Integer.parseInt(verifyComplaint));
            projectComplaintService1.updateByPrimaryKeySelective(projectComplaint);

            jsonResult.setOk(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage("上传失败");
            jsonResult.setOk(false);
        }
        return jsonResult;
    }


    @RequestMapping(value = "/syncDingDing")
    @ResponseBody
    public JsonResult syncDingDing() {
        JsonResult jsonResult = new JsonResult();
        try {
            long count = atomicLong.addAndGet(1);
            if (count % 50 == 0) {
                client = new OkHttpClient.Builder().connectTimeout(600, TimeUnit.SECONDS)
                        .readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();
            }
            String ipUrl = "https://www.kuaizhizao.cn/Ding-Talk/getdeliveryConfirmation?cursor=0";
            Request request = new Request.Builder().addHeader("Connection", "close").addHeader("Accept", "*/*")
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)").get().url(ipUrl).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                jsonResult.setOk(true);
            } else {
                jsonResult.setOk(false);
            }


        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage("执行失败");
            jsonResult.setOk(false);
        }
        return jsonResult;
    }


    public static void updateQualityComplaint(Integer id,
                                              String process_instance_id) {
        ProjectComplaint projectComplaint = new ProjectComplaint();
        projectComplaint.setId(id);
        projectComplaint.setProcessInstanceId(process_instance_id);
        projectComplaintService1.updateByPrimaryKeySelective(projectComplaint);
    }
}
