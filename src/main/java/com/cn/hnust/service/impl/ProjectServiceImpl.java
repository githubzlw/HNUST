package com.cn.hnust.service.impl;

import java.text.ParseException;
import java.util.*;

import com.cn.hnust.controller.DingTalkThread;
import com.cn.hnust.util.ERPStatusUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.component.RpcSendNoticeToKuai;
import com.cn.hnust.controller.ProjectERPController;
import com.cn.hnust.dao.DelayMapper;
import com.cn.hnust.dao.DeliveryDateLogMapper;
import com.cn.hnust.dao.EmailUserMapper;
import com.cn.hnust.dao.IUserDao;
import com.cn.hnust.dao.ProductionPlanMapper;
import com.cn.hnust.dao.ProjectERPMapper;
import com.cn.hnust.dao.ProjectMapper;
import com.cn.hnust.dao.ProjectPauseMapper;
import com.cn.hnust.dao.ProjectReportMapper;
import com.cn.hnust.dao.ProjectStatusLogMapper;
import com.cn.hnust.dao.ProjectTaskMapper;
import com.cn.hnust.daoErp.ItemCaseERPMapper;
import com.cn.hnust.daoQuickpart.QuoteWeeklyReportMapper;
import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.enums.ProjectStageEnum;
import com.cn.hnust.pojo.Delay;
import com.cn.hnust.pojo.DeliveryDateLog;
import com.cn.hnust.pojo.ProductionPlan;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.ProjectPause;
import com.cn.hnust.pojo.ProjectReport;
import com.cn.hnust.pojo.ProjectStatusLog;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.ProjectVO;
import com.cn.hnust.pojo.QuoteWeeklyReport;
import com.cn.hnust.pojo.QuoteWeeklyReportQuery;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.QuoteWeeklyReportService;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;

@Service
public class ProjectServiceImpl implements IProjectService, QuoteWeeklyReportService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private DelayMapper delayMapper;
    @Autowired
    private ProductionPlanMapper productionPlanMapper;
    @Autowired
    private ProjectReportMapper projectReportMapper;
    @Autowired
    private ProjectTaskMapper projectTaskMapper;
    @Autowired
    private ProjectPauseMapper projectPauseMapper;
    @Autowired
    private ItemCaseERPMapper itemCaseERPMapper;
    @Autowired
    private ProjectERPMapper projectERPMapper;
    @Autowired
    private ProjectStatusLogMapper projectStatusLogMapper;
    @Autowired
    private DeliveryDateLogMapper deliveryDateLogMapper;
    @Autowired
    private QuoteWeeklyReportMapper quoteWeeklyReportMapper;


    private static final Log LOG = LogFactory.getLog(ProjectServiceImpl.class);

    /**
     * 查询项目列表根据登录用户的UserId;
     */
    @Override
    public List<Project> findProjectList(Project project) {
        return projectMapper.findProjectList(project);
    }

    /**
     * 根据项目号查询项目详情
     */
    @Override
    public Project selectProjctDetails(String projectNo) {
        return projectMapper.selectProjctDetails(projectNo);
    }

    /**
     * 显示项目详情信息
     *
     * @throws ParseException
     */
    @Transactional
    @Override
    public Project showDetails(String projectNo) throws ParseException {
        Project project = projectMapper.selectProjctDetails(projectNo);
        //查询项目的延期信息
        List<Delay> delayList = delayMapper.selectDelayByProjectNo(projectNo);
        //查询项目的生产计划
        List<ProductionPlan> planList = productionPlanMapper.selectProductionPlan(projectNo);
        //查询项目汇报情况
        List<ProjectReport> reportList = projectReportMapper.selectProjectReport(projectNo);

        if (reportList != null && reportList.size() > 0) {
            for (ProjectReport pr : reportList) {
                if (pr.getProjectStage() != null) {
                    pr.setProjectStageView(ProjectStageEnum.getEnum(pr.getProjectStage()).getValue());
                }
            }
        }

        //查询项目合同交期
        int contractDays = 0;
        //只存在样品合同，计算样品交期和po日期天数
        //存在样品交期和不存在po日期，则计算样品交期-合同时间-7天              
        if (project.getSampleScheduledDate() != null && project.getDateSampleUploading() != null) {
            contractDays = DateFormat.calDays(project.getSampleScheduledDate(), project.getDateSampleUploading());
        } else if (project.getSampleScheduledDate() != null && project.getDateSampleUploading() == null && project.getActualStartDate() != null) {
            contractDays = DateFormat.calDays(project.getSampleScheduledDate(), project.getActualStartDate()) - 7;
        }
        //如果存在大货合同，则以大货合同计算
        if (project.getDeliveryDate() != null && project.getDateSampleUploading() != null) {
            contractDays = DateFormat.calDays(project.getDeliveryDate(), project.getDateSampleUploading());
        } else if (project.getDeliveryDate() != null && project.getDateSampleUploading() == null && project.getActualStartDate() != null) {
            contractDays = DateFormat.calDays(project.getDeliveryDate(), project.getActualStartDate()) - 7;
        }
        project.setContractDays(contractDays);


        project.setDelayList(delayList);
        project.setPlanList(planList);
        project.setReportList(reportList);
        return project;
    }

    @Override
    public void updateProjectInfo(Project project) {
        if (StringUtils.isBlank(project.getInterfaceName())) {
            project.setInterfaceName(Arrays.toString(Thread.currentThread().getStackTrace()));
        }
        projectMapper.insertProjectSyncLog(project);
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public List<Project> selectAllProject() {
        return projectMapper.selectAllProject();
    }

    /**
     * 批量添加数据
     */
    @Override
    public void batchAddProject(List<Project> projectList) {
        projectMapper.batchAddProject(projectList);
    }

    @Override
    public Project selectProjectByProjectNo(String projectNo) {
        return projectMapper.selectProjectByProjectNo(projectNo);
    }

    /**
     * 查询交期延误的项目
     */
    @Override
    public List<Project> findDelayProjectList(Project project) {
        return projectMapper.findDelayProjectList(project);
    }

    /**
     * 统计没有设置交期的项目
     */
    @Override
    public List<Project> findSetDeliveryTimeList(Project project) {
        return projectMapper.findSetDeliveryTimeList(project);
    }

    /**
     * 统计项目汇报
     */
    @Override
    public List<Project> findProjectReportList(Project project) {
        return projectMapper.findProjectReportList(project);
    }

    /**
     * 统计没有项目汇报图片的项目
     */
    @Override
    public List<Project> findProjectReportNoPicList(Project project) {
        return projectMapper.findProjectReportNoPicList(project);
    }

    /**
     * 统计缺少任务汇报的项目
     */
    @Override
    public List<Project> findProjectNoTaskReportList(Project project) {
        return projectMapper.findProjectNoTaskReportList(project);
    }

    /**
     * 统计延期的项目列表
     */
    @Override
    public List<Project> findProjectDelayCountList(Project project) {
        return projectMapper.findProjectDelayCountList(project);
    }

    /**
     * 采购登录成功后查询采购列表
     */
    @Override
    public List<Project> findPurchaseProjectList(Project project) {
        return projectMapper.findPurchaseProjectList(project);
    }

    /**
     * 采购交期变更的项目统计
     */
    @Override
    public List<Project> findDelayProjectPurchaseList(Project project) {
        return projectMapper.findDelayProjectPurchaseList(project);
    }

    /**
     * 采购统计未设定实际交期的项目(采购)
     */
    @Override
    public List<Project> findSetDeliveryTimePurchaseList(Project project) {
        return projectMapper.findSetDeliveryTimePurchaseList(project);
    }

    /**
     * 统计出货延期的项目(采购)
     */
    @Override
    public List<Project> findProjectDelayPurchaseCountList(Project project) {
        return projectMapper.findProjectDelayPurchaseCountList(project);
    }

    /**
     * 统计项目的采购情况汇报(采购)
     */
    @Override
    public List<Project> findProjectReportPurchaseList(Project project) {
        return projectMapper.findProjectReportPurchaseList(project);
    }

    /**
     * 统计项目缺少开工图片的项目(采购)
     */
    @Override
    public List<Project> findProjectReportNoPicPurchaseList(Project project) {
        return projectMapper.findProjectReportNoPicPurchaseList(project);
    }

    @Override
    public List<Project> findProjectNoTaskReportPurchaseList(Project project) {
        return projectMapper.findProjectNoTaskReportPurchaseList(project);
    }

    /**
     * 查询采购报告(消息中心销售)
     */
    @Override
    public List<Project> selectProjectRelationTask(Project project) {
        return projectMapper.selectProjectRelationTask(project);
    }

    /**
     * 查询采购报告(消息中心采购)
     */
    @Override
    public List<Project> selectPurchaseProjectRelationTask(Project project) {
        return projectMapper.selectPurchaseProjectRelationTask(project);
    }

    /**
     * 销售进来查看是否有新的采购项目汇报
     */
    @Override
    public List<Project> findProjectReportMessage(Project project) {
        return projectMapper.findProjectReportMessage(project);
    }

    @Transactional
    @Override
    public void addProject(Project project) throws ParseException {

        if (StringUtils.isBlank(project.getInterfaceName())) {
            project.setInterfaceName(Arrays.toString(Thread.currentThread().getStackTrace()));
        }
        projectMapper.insertProjectSyncLog(project);
        //添加返单项目，移植初始项目的产品图片
        if (project != null) {
            String projectNo = project.getProjectNo();
            if (projectNo.contains("-")) {
                String originalProjectNo = projectNo.split("-")[0];
//			  Project originalProject = projectMapper.selectProjctDetails(originalProjectNo);
                List<Project> projectList = projectMapper.selectByProjectDim(originalProjectNo);
                //如果存在项目图，则继承（查询该项目所有翻单）
                if (projectList != null) {
                    projectList.forEach(p -> {
                        if (StringUtils.isNotBlank(p.getProductImg())) {
                            project.setProductImg(p.getProductImg());
                        }
                    });
                }
            }
        }
        projectMapper.insertSelective(project);

        //图纸更新需求（新项目启动录入时）
        if (project.getUpdateDrawing() != null && project.getUpdateDrawing() == 1) {
            ProjectTask projectTask = new ProjectTask();
            projectTask.setProjectNo(project.getProjectNo());
            projectTask.setInitiator(project.getUserName());
            projectTask.setAccepter("zhangqun");
            projectTask.setDescription("需要更新图纸");
            projectTask.setUrgentReason("");
            projectTask.setFinishTime(DateUtil.StrToDate(DateFormat.addDays(DateFormat.currentDate(), 3)));
            projectTask.setTaskStatus("0");
            projectTask.setTaskType("0");
            projectTask.setTaskUrl("");
            projectTask.setStartTime(new Date());
            projectTask.setCreateTime(new Date());
            projectTask.setProjectNoId(project.getId());
            projectTaskMapper.insertSelective(projectTask);
            User user = userDao.findUserByName(projectTask.getAccepter());
            projectTask.setDingTalkId(user.getDingTalkId());
            RpcSendNoticeToKuai.sendRequest(projectTask);
        }
        //采购确认需求变更（新项目启动录入时）
        if (project.getRequire() != null && project.getRequire() == 1) {
            ProjectTask projectTask = new ProjectTask();
            projectTask.setProjectNo(project.getProjectNo());
            projectTask.setInitiator(project.getUserName());
            projectTask.setAccepter(project.getPurchaseName());
            projectTask.setDescription("需要采购确认需求变更");
            projectTask.setUrgentReason("");
            projectTask.setFinishTime(DateUtil.StrToDate(DateFormat.addDays(DateFormat.currentDate(), 3)));
            projectTask.setTaskStatus("0");
            projectTask.setTaskType("0");
            projectTask.setTaskUrl("");
            projectTask.setStartTime(new Date());
            projectTask.setCreateTime(new Date());
            projectTask.setProjectNoId(project.getId());
            projectTaskMapper.insertSelective(projectTask);
            User user = userDao.findUserByName(projectTask.getAccepter());
            projectTask.setDingTalkId(user.getDingTalkId());
            RpcSendNoticeToKuai.sendRequest(projectTask);
        }

        //检验计划更新（新项目启动录入时）
        if (project.getUpdateInspect() != null && project.getUpdateInspect() == 1) {
            ProjectTask projectTask = new ProjectTask();
            projectTask.setProjectNo(project.getProjectNo());
            projectTask.setInitiator(project.getUserName());
            projectTask.setAccepter(project.getZhijian1());
            projectTask.setDescription("需要检验更新检验计划");
            projectTask.setUrgentReason("");
            projectTask.setFinishTime(DateUtil.StrToDate(DateFormat.addDays(DateFormat.currentDate(), 3)));
            projectTask.setTaskStatus("0");
            projectTask.setTaskType("0");
            projectTask.setTaskUrl("");
            projectTask.setStartTime(new Date());
            projectTask.setCreateTime(new Date());
            projectTask.setProjectNoId(project.getId());
            projectTaskMapper.insertSelective(projectTask);
            User user = userDao.findUserByName(projectTask.getAccepter());
            projectTask.setDingTalkId(user.getDingTalkId());
            RpcSendNoticeToKuai.sendRequest(projectTask);
        }


    }

    /**
     * 查询该销售下所属的采购
     */
    @Override
    public List<Project> selectProjectPurchaseList(Project project) {
        return projectMapper.selectProjectPurchaseList(project);
    }

    /**
     *
     */
    @Override
    public int findProjectListCount(Project project) {
        return projectMapper.findProjectListCount(project);
    }

    @Override
    public List<Project> findPurchaseProjectListCount(Project project) {

        return projectMapper.findPurchaseProjectListCount(project);
    }

    @Override
    public List<Project> selectProjectRelationTaskCount(Project project) {

        return projectMapper.selectProjectRelationTaskCount(project);
    }

    @Override
    public List<Project> selectPurchaseProjectRelationTaskCount(Project project) {

        return projectMapper.selectPurchaseProjectRelationTaskCount(project);
    }

    @Override
    public List<Project> selectProjectList(boolean hasEmailUserId,
                                           boolean hasPurchaseId) {

        return projectMapper.selectProjectList(hasEmailUserId, hasPurchaseId);
    }

    @Override
    public int updateFlogByProjectNo(Set<String> proSet) {
        return projectMapper.updateFlogByProjectNo(proSet);
    }


    @Override
    public int updateaddFlogByProjectNo(Set<String> proSet) {
        return projectMapper.updateaddFlogByProjectNo(proSet);
    }

    @Override
    public List<ProjectTask> selectProject(ProjectTask projectTask) {

        return projectMapper.selectProject(projectTask);
    }

    @Override
    public List<ProjectTask> selectProjectCount(ProjectTask projectTask) {

        return projectMapper.selectProjectCount(projectTask);
    }

    @Override
    public int updateFlogByProjectNoFirst(Set<String> proSet) {
        return projectMapper.updateFlogByProjectNoFirst(proSet);
    }

    @Override
    public int updateaddFlogByProjectNoFirst(Set<String> proSet) {
        return projectMapper.updateaddFlogByProjectNoFirst(proSet);
    }

    /**
     * 更新排序字段的优先级
     */
    @Override
    public void updateProjectDataSortField(Project project) {
        projectMapper.updateProjectDataSortField(project);
    }

    @Override
    public List<Project> selectBigGoodsFinish(Project project) {
        return projectMapper.selectBigGoodsFinish(project);
    }

    /**
     * 项目原定交期 超期一周后 ，给采购布置一个解释情况的任务
     */
    @Override
    public void updateProjectDeliveryDateDelay(Set<String> proSet) {
        projectMapper.updateProjectDeliveryDateDelay(proSet);
    }

    @Override
    public List<Project> selectByCreatePersonId(Integer userId, Integer roleNo, Integer pageNumber, Integer pageSize) {
        return projectMapper.selectByCreatePersonId(userId, roleNo, pageNumber, pageSize);
    }

    @Override
    public int countNewProject(Integer userId, Integer roleNo) {
        return projectMapper.countNewProject(userId, roleNo);
    }

    @Override
    public String maxStartProject(Integer type, Integer dateType) {
        return projectMapper.maxStartProject(type, dateType);
    }

    @Override
    public List<Project> selectCount(Project project) {
        return projectMapper.selectCount(project);
    }

    @Transactional
    @Override
    public void updateProjectStatus(Project project, String reason, String time, ProjectStatusLog statusLog) {
        if (StringUtils.isBlank(project.getInterfaceName())) {
            project.setInterfaceName(Arrays.toString(Thread.currentThread().getStackTrace()));
        }
        projectMapper.insertProjectSyncLog(project);
        //itemCaseERPMapper.updateItemCaseStatus(project);
        projectMapper.updateByPrimaryKeySelective(project);
        if (project.getProjectStatus() == OrderStatusEnum.PAUSE_ORDER.getCode()) {
            ProjectPause projectPause = new ProjectPause();
            projectPause.setCreateDate(DateUtil.StrToDate(time));
            projectPause.setIsPause(1);
            projectPause.setPauseReason(reason);
            projectPause.setProjectNo(project.getProjectNo());
            projectPauseMapper.insertSelective(projectPause);
        }


        if (statusLog != null && StringUtils.isNotBlank(statusLog.getProjectStatus())) {
            projectStatusLogMapper.insertSelective(statusLog);
        }

    }

    /**
     * 同步erp采购、质检、跟单
     */
    @Transactional
    @Override
    public void updateProjectByErp(String projectNo) {
        ProjectERP erp = itemCaseERPMapper.selectByCaseNo(projectNo);
        Project project = projectMapper.selectProjctDetails(projectNo);
        User user = new User();
        User purchaseUser = new User();
        //1.MerchandManager1 跟单销售,MerchandManager2 采购
        //如果存在成熟跟单，保存成熟跟单，不存在，保存原始跟单
        //Merchandising 成熟跟单
        if (erp != null) {
            if (StringUtils.isNotBlank(erp.getMerchandising())) {
                user = userDao.selectUserByName(erp.getMerchandising());
                if (user != null) {
                    project.setEmailUserId(user.getId());
                }
            } else {
                if (StringUtils.isNotBlank(erp.getMerchandManager1())) {
                    user = userDao.selectUserByName(erp.getMerchandManager1());
                    if (user != null) {
                        project.setEmailUserId(user.getId());
                    }
                }
            }
            if (StringUtils.isNotBlank(erp.getCustomerManager())) {
                user = userDao.selectUserByName(erp.getCustomerManager());
                if (user != null) {
                    project.setSaleId(user.getId());
                }
            }


            //如果存在成熟采购，保存成熟采购，不存在，保存原始采购
            if (StringUtils.isNotBlank(erp.getMaturePurchase())) {
                purchaseUser = userDao.selectUserByName(erp.getMaturePurchase());

                if (purchaseUser != null) {
                    project.setPurchaseId(purchaseUser.getId());
                }
            } else if (StringUtils.isNotBlank(erp.getOriginalPurchase())) {
                purchaseUser = userDao.selectUserByName(erp.getOriginalPurchase());

                if (purchaseUser != null) {
                    project.setPurchaseId(purchaseUser.getId());
                }
            } else {
                if (StringUtils.isNotBlank(erp.getMerchandManager2())) {
                    purchaseUser = userDao.selectUserByName(erp.getMerchandManager2());
                    if (purchaseUser != null) {
                        project.setPurchaseId(purchaseUser.getId());
                    }
                } else {
                    project.setPurchaseId(0);
                }
            }


            //同步ERP技术员  2018.11.08
            if (StringUtils.isNotBlank(erp.getTechnician())) {
                project.setTechnician(erp.getTechnician());
            }

            //同步客户名
            if (StringUtils.isNotBlank(erp.getCustomerName())) {
                project.setCustomerName(erp.getCustomerName());
            }


            project.setZhijian1(erp.getZhijian1() == null ? "" : erp.getZhijian1());
            project.setZhijian2(erp.getZhijian2() == null ? "" : erp.getZhijian2());
            project.setZhijian3(erp.getZhijian3() == null ? "" : erp.getZhijian3());
            project.setMasterQualityInspection(erp.getMasterQualityInspection() == null ? "" : erp.getMasterQualityInspection());
            project.setQualityInspector1(erp.getQualityInspector1() == null ? "" : erp.getQualityInspector1());
            project.setQualityInspector2(erp.getQualityInspector2() == null ? "" : erp.getQualityInspector2());
            project.setQualityInspector3(erp.getQualityInspector3() == null ? "" : erp.getQualityInspector3());
            project.setQualityInspector4(erp.getQualityInspector4() == null ? "" : erp.getQualityInspector4());
            project.setQualityInspector5(erp.getQualityInspector5() == null ? "" : erp.getQualityInspector5());
            project.setQualityInspector6(erp.getQualityInspector6() == null ? "" : erp.getQualityInspector6());
            project.setQualityInspector7(erp.getQualityInspector7() == null ? "" : erp.getQualityInspector7());
            project.setPlantAnalysis(erp.getPlantAnalysis());
            LOG.info("==============项目详情================" + erp);

            projectMapper.updateByPrimaryKeySelective(project);
        }
    }

    @Override
    public List<Project> selectProjectByStatus(Integer projectStatus) {
        return projectMapper.selectProjectByStatus(projectStatus);
    }

    /**
     * 删除项目的同时，删除任务
     */
    @Transactional
    @Override
    public int deleteByPrimaryKey(String id, String projectNo) {
        projectTaskMapper.deleteByProjectNo(projectNo);
        return projectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Project record) {
        if (StringUtils.isBlank(record.getInterfaceName())) {
            record.setInterfaceName(Arrays.toString(Thread.currentThread().getStackTrace()));
        }
        projectMapper.insertProjectSyncLog(record);
        Project project = new Project();
        project.setProjectNo(record.getProjectNo());
        project.setProjectStatus(ERPStatusUtil.changeToERPStatus(record.getProjectStatus()));
        itemCaseERPMapper.updateItemCaseStatus(project);
        return projectMapper.updateByPrimaryKey(record);
    }

    @Override
    public int selectByTechnician(String technician) {
        return projectMapper.selectByTechnician(technician);
    }

    @Override
    public ProjectVO selectProjectVO(String projectNo, String factoryId) {
        return projectMapper.selectProjectVO(projectNo, factoryId);
    }

    @Override
    public List<ProjectVO> selectProjectVOList(String factoryId) {
        return projectMapper.selectProjectVOList(factoryId, null);
    }

    @Override
    public List<ProjectVO> selectProjectVOList(String factoryId,
                                               String selectStr) {
        return projectMapper.selectProjectVOList(factoryId, selectStr);
    }

    @Override
    public List<Project> getDifficultItemListPage(Project project) {

        return projectMapper.getDifficultItemListPage(project);
    }

    @Override
    public void updateDifficultProject(Project project) {

        projectMapper.updateDifficultProject(project);
    }

    @Override
    public int getNumberOfDifficultProjects(Project project) {

        return projectMapper.getNumberOfDifficultProjects(project);
    }

    @Override
    public List<Project> getNumberOfProjectsCompletedInOneMonth(Project project) {

        return projectMapper.getNumberOfProjectsCompletedInOneMonth(project);
    }

    @Override
    public int getNumberOfDeferredItems(Project project) {

        return projectMapper.getNumberOfDeferredItems(project);
    }

    @Override
    public int getNumberOfDocumentaryItemsOver3Months(Project project) {

        return projectMapper.getNumberOfDocumentaryItemsOver3Months(project);
    }

    @Override
    public int findAllCount(Project project) {

        return projectMapper.findAllCount(project);
    }


    @Transactional
    @Override
    public void updateByPrimaryKey(Project record, DeliveryDateLog deliveryDateLog) throws Exception {
        if (record != null && deliveryDateLog != null && deliveryDateLog.getNewDeliveryDate() != null) {
            //判断当前类型交期已修改次数，最多只能修改三次
            int count = deliveryDateLogMapper.selectCount(record.getProjectNo(), deliveryDateLog.getSampleProduction());
            if (count > 2) {
                throw new Exception("项目最多只能修改3次交期");
            } else {
                deliveryDateLogMapper.insertSelective(deliveryDateLog);
            }
        }
        if (StringUtils.isBlank(record.getInterfaceName())) {
            record.setInterfaceName(Arrays.toString(Thread.currentThread().getStackTrace()));
        }
        projectMapper.insertProjectSyncLog(record);
        // itemCaseERPMapper.updateItemCaseStatus(record);
        projectMapper.updateByPrimaryKey(record);
    }

    @Override
    public Double selectRateByFactory(String factoryName, Integer delayStatus) {
        return projectMapper.selectRateByFactory(factoryName, delayStatus);
    }

    @Override
    public List<Project> selectMonthProject() {
        return projectMapper.selectMonthProject();
    }

    @Override
    public List<Project> selectByMeetingTask() {
        return projectMapper.selectByMeetingTask();
    }

    @Override
    public QuoteWeeklyReport selectByPrimaryKey(Integer id) {
        return quoteWeeklyReportMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<QuoteWeeklyReport> queryByCsgOrderIdAndType(String csgOrderId, Integer fileType, String factoryId) {
        return quoteWeeklyReportMapper.queryByCsgOrderIdAndType(csgOrderId, fileType, factoryId);
    }

    @Override
    public List<QuoteWeeklyReport> queryByReportTypeId(Integer reportTypeId) {
        return quoteWeeklyReportMapper.queryByReportTypeId(reportTypeId);
    }

    @Override
    public String queryMaxUploadDate(String csgOrderId, String factoryId) {
        return quoteWeeklyReportMapper.queryMaxUploadDate(csgOrderId, factoryId);
    }

    @Override
    public List<QuoteWeeklyReport> selectAll(QuoteWeeklyReportQuery quoteWeeklyReportQuery) {
        return quoteWeeklyReportMapper.selectAll(quoteWeeklyReportQuery);
    }

    @Override
    public List<String> queryProjects(Integer pageNumber, Integer pageSize) {
        return quoteWeeklyReportMapper.queryProjects(pageNumber, pageSize);
    }

    @Override
    public Double selectRateByFactoryOnly(String factoryName,
                                          Integer delayStatus) {
        return projectMapper.selectRateByFactoryOnly(factoryName, delayStatus);
    }

    @Override
    public Map<String, Object> queryGroupByFileType(String csgOrderId, String factoryId) {
        return quoteWeeklyReportMapper.queryGroupByFileType(csgOrderId, factoryId);
    }

    @Override
    public List<Project> selectByImportantMeetingTask() {
        return projectMapper.selectByImportantMeetingTask();
    }

    @Override
    public int getFinishTime(String allProjectNo, int metalDeliveryTime,
                             int object, int plasticDeliveryPeriod, int object2) {

        return projectMapper.getFinishTime(allProjectNo, metalDeliveryTime, object, plasticDeliveryPeriod, object2);
    }

    @Override
    public List<Project> findProjectByStatus(Project project) {
        return projectMapper.findProjectByStatus(project);
    }

    @Override
    public List<Project> selectCompleteTasks(int num, Date mon) {

        return projectMapper.selectCompleteTasks(num, mon);
    }

    @Override
    public List<Project> selectSemiAnnualProject(int code, String purchaseUser,
                                                 String saleUser) {

        return projectMapper.selectSemiAnnualProject(code, purchaseUser, saleUser);
    }

    @Override
    public List<Project> selectProofingPhaseProject(int code) {

        return projectMapper.selectProofingPhaseProject(code);
    }

    @Override
    public int updateByPrimaryKeySelective(Project project) {

        return projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public int updateStatus(Project project) {
        if (StringUtils.isBlank(project.getInterfaceName())) {
            project.setInterfaceName(Arrays.toString(Thread.currentThread().getStackTrace()));
        }
        projectMapper.insertProjectSyncLog(project);
        return projectMapper.updateStatus(project);
    }

    @Override
    public List<Project> getProductingProject(Project project) {

        return projectMapper.getProductingProject(project);
    }

    @Override
    public int getProductingProjectCount(Project project) {

        return projectMapper.getProductingProjectCount(project);
    }

    @Override
    public List<Project> selectProjectByStatus1(int code, Date startDate,
                                                Date endDate) {

        return projectMapper.selectProjectByStatus1(code, startDate, endDate);
    }


    @Override
    public List<Project> queryFinishByTime(Date beginTime, Date endTime) {
        return projectMapper.queryFinishByTime(beginTime, endTime);
    }


    @Override
    public List<QuoteWeeklyReport> getAll(int num) {

        return quoteWeeklyReportMapper.getAll(num);
    }

    @Override
    public int getProjectS(String userName, int i) {
        int num = 0;
        if (i == 1) {
            num = projectMapper.getProofingProject(userName);
        } else if (i == 2) {
            num = projectMapper.getMassProductionProject(userName);
        }
        return num;
    }

    @Override
    public String getAll(String userName) {

        return projectMapper.getAll(userName);
    }

    @Override
    public List<Project> getNoDrawing() {

        return projectMapper.getNoDrawing();
    }

    @Override
    public Project getzhijian(Project project) {


        return projectMapper.getAllZhiJian(project);
    }

    @Override
    public List<Project> getReturnItem() {

        return projectMapper.getReturnItem();
    }

    @Override
    public void updateHistoryInspection(Project project) {
        projectMapper.updateHistoryInspection(project);

    }

    @Override
    public int getNoInspectionTask(Project project) {


        return projectMapper.getNoInspectionTask(project);
    }

    @Override
    public int getNoInterimInspection(Project project) {


        return projectMapper.getNoInterimInspection(project);
    }

    @Override
    public List<Project> getNoInspectionTaskList(Project project) {

        return projectMapper.getNoInspectionTaskList(project);
    }

    @Override
    public List<Project> getNoInterimInspectionList(Project project) {

        return projectMapper.getNoInterimInspectionList(project);
    }

    @Override
    public List<Project> selectProjectExport(String m) {
        return projectMapper.selectProjectExport(m);
    }

    @Override
    public void switchProjectStatus(String projectNo) {
        try {
            ProjectTask projectTask1 = projectTaskMapper.getByDescription(projectNo + "启动了，需要制作受控图");
            if (projectTask1 == null) {
                ProjectTask projectTask = new ProjectTask();
                projectTask.setProjectNo(projectNo);
                projectTask.setInitiator("system");
                projectTask.setAccepter("zhanglei");
                projectTask.setDescription(projectNo + "启动了，需要制作受控图");
                projectTask.setUrgentReason("");
                projectTask.setFinishTime(DateUtil.StrToDate(DateFormat.addDays(DateFormat.currentDate(), 7)));
                projectTask.setTaskStatus("0");
                projectTask.setTaskType("0");
                projectTask.setTaskUrl("");
                projectTask.setStartTime(new Date());
                projectTask.setCreateTime(new Date());
                projectTaskMapper.insertSelective(projectTask);
                User user = userDao.findUserByName(projectTask.getAccepter());
                projectTask.setDingTalkId(user.getDingTalkId());
                RpcSendNoticeToKuai.sendRequest(projectTask);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public List<Project> getAllMaterial() {
        return projectMapper.getAllMaterial();
    }


}
