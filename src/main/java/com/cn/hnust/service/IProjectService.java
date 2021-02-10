package com.cn.hnust.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.enums.ProjectStageEnum;
import com.cn.hnust.pojo.DeliveryDateLog;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectStatusLog;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.ProjectVO;
import com.cn.hnust.pojo.QuoteWeeklyReport;

/**
 * 项目Service 接口
 *
 * @author ministrator
 */
public interface IProjectService {

    /**
     * 根据id删除
     *
     * @param id
     * @return int
     * @Title deleteByPrimaryKey
     * @Description
     */
    int deleteByPrimaryKey(String id, String projectNo);

    /**
     * 根据登录的id查询所属的项目列表(管理员和销售共用)
     *
     * @param id
     * @return
     */
    public List<Project> findProjectList(Project project);

    /**
     * 根据登录的id查询所属的项目列表(管理员和销售共用)记录数
     *
     * @param project
     * @return
     */
    public int findProjectListCount(Project project);

    /**
     * 根据登录的id查询所属的项目列表(采购)
     *
     * @param project
     * @return
     */
    public List<Project> findPurchaseProjectList(Project project);

    /**
     * 根据登录的id查询所属的项目列表(采购)(总的记录数)
     *
     * @param project
     * @return
     */
    public List<Project> findPurchaseProjectListCount(Project project);

    /**
     * 根据项目号查询项目详情
     *
     * @param projectNo
     * @return
     */
    public Project selectProjctDetails(String projectNo);

    /**
     * 查询项目详情
     *
     * @param projectNo
     * @return
     */
    public Project showDetails(String projectNo) throws ParseException;

    /**
     * 修改项目基本信息(时间交货日期,原定交货日期)
     *
     * @param project
     */
    public void updateProjectInfo(Project project);


    /**
     * 修改项目日期，允许为空
     *
     * @param record
     * @return int
     * @Title updateByPrimaryKey
     * @Description
     */
    int updateByPrimaryKey(Project record);

    /**
     * 修改项目日期，插入交期记录表，允许为空
     *
     * @param record
     * @return int
     * @Title updateByPrimaryKey
     * @Description
     */
    void updateByPrimaryKey(Project record, DeliveryDateLog deliveryDateLog) throws Exception;

    /**
     * 查询所有的项目信息
     *
     * @return
     */
    public List<Project> selectAllProject();

    /***
     * 批量添加项目信息
     * @param projectList
     */
    public void batchAddProject(List<Project> projectList);

    /**
     * 查询CRM推送的订单是否存在
     *
     * @param projectNo
     * @return
     */
    Project selectProjectByProjectNo(String projectNo);

    /**
     * 查询交期延误的项目列表 (管理员和销售共用)
     *
     * @param project
     * @return
     */
    public List<Project> findDelayProjectList(Project project);

    /**
     * 查询交期延误的项目列表(采购)
     *
     * @param project
     * @return
     */
    public List<Project> findDelayProjectPurchaseList(Project project);

    /**
     * 查询未设置交期的项目统计(销售,管理员共用)
     *
     * @param project
     * @return
     */
    public List<Project> findSetDeliveryTimeList(Project project);

    /**
     * 查询未设置交期的项目统计(采购)
     *
     * @param project
     * @return
     */
    public List<Project> findSetDeliveryTimePurchaseList(Project project);

    /**
     * 查询项目的采购汇报(管理员,销售)
     *
     * @param project
     * @return
     */
    public List<Project> findProjectReportList(Project project);

    /**
     * 查询项目的采购汇报(采购)
     *
     * @param project
     * @return
     */
    public List<Project> findProjectReportPurchaseList(Project project);

    /**
     * 统计项目缺少开工图片的项目(管理员,销售)
     *
     * @param project
     * @return
     */
    public List<Project> findProjectReportNoPicList(Project project);

    /**
     * 统计项目缺少开工图片的项目(采购)
     *
     * @param project
     * @return
     */
    public List<Project> findProjectReportNoPicPurchaseList(Project project);

    /**
     * 统计没有任务汇报的项目(管理员，销售)
     *
     * @param project
     * @return
     */
    public List<Project> findProjectNoTaskReportList(Project project);

    /**
     * 统计没有任务汇报的项目(采购)
     *
     * @param project
     * @return
     */
    public List<Project> findProjectNoTaskReportPurchaseList(Project project);

    /**
     * 查询统计延期的项目(管理员，销售)
     *
     * @param project
     * @return
     */
    public List<Project> findProjectDelayCountList(Project project);

    /**
     * 查询统计延期的项目(采购)
     *
     * @param project
     * @return
     */
    public List<Project> findProjectDelayPurchaseCountList(Project project);

    /**
     * 查询采购报告(消息中心销售)
     *
     * @param project
     * @return
     */
    public List<Project> selectProjectRelationTask(Project project);

    public List<Project> selectProjectRelationTaskCount(Project project);

    /**
     * 查询采购报告(消息中心采购)
     *
     * @param project
     * @return
     */
    public List<Project> selectPurchaseProjectRelationTask(Project project);

    public List<Project> selectPurchaseProjectRelationTaskCount(Project project);

    /**
     * 查询采购项目信息(销售进来查看是否有的信息)
     *
     * @param project
     * @return
     */
    public List<Project> findProjectReportMessage(Project project);

    /**
     * 添加项目信息
     *
     * @param project
     */
    public void addProject(Project project) throws ParseException;

    /**
     * 查询该销售下面的采购
     *
     * @param project
     * @return
     */
    public List<Project> selectProjectPurchaseList(Project project);


    public List<Project> selectProjectList(boolean hasEmailUserId, boolean hasPurchaseId);


    int updateFlogByProjectNo(Set<String> proSet);

    int updateFlogByProjectNoFirst(Set<String> proSet);

    int updateaddFlogByProjectNoFirst(Set<String> proSet);

    int updateaddFlogByProjectNo(Set<String> proSet);

    /**
     * @param projectTask
     * @return List<ProjectTask>
     * @throws
     * @Title:IProjectService
     * @Description:查看用户最近两个月项目列表
     * @author wangyang
     */
    public List<ProjectTask> selectProject(ProjectTask projectTask);

    /**
     * @param projectTask
     * @return List<ProjectTask>
     * @throws
     * @Title:IProjectService
     * @Description:查看用户最近两个月项目列表数
     * @author wangyang
     */
    public List<ProjectTask> selectProjectCount(ProjectTask projectTask);


    public void updateProjectDataSortField(Project project);

    /**
     * 大货完结条数
     *
     * @param project
     * @return
     */
    public List<Project> selectBigGoodsFinish(Project project);

    /**
     * 项目原定交期 超期一周后 ，给采购布置一个解释情况的任务
     *
     * @param proSet
     */
    public void updateProjectDeliveryDateDelay(Set<String> proSet);


    /**
     * 根据创建人id查询，用于项目录入查看
     *
     * @param userId
     * @return List<Project>
     * @Title selectByCreatePersonId
     * @Description
     */
    public List<Project> selectByCreatePersonId(Integer userId, Integer roleNo, Integer pageNumber, Integer pageSize);


    /**
     * 创建人创建项目数
     *
     * @param userId
     * @return int
     * @Title countNewProject
     * @Description
     */
    int countNewProject(Integer userId, Integer roleNo);


    /**
     * 根据用户类型查启动项目最多的
     *
     * @param type
     * @return int
     * @Title maxStartProject
     * @Description
     */
    String maxStartProject(Integer type, Integer dateType);


    /**
     * 根据各种条件查询（统计页面）
     *
     * @param project
     * @return int
     * @Title selectCount
     * @Description
     */
    List<Project> selectCount(Project project);


    /**
     * 更新项目状态
     *
     * @param project
     * @return void
     * @Title updateProjectStatus
     * @Description
     */
    void updateProjectStatus(Project project, String reason, String time, ProjectStatusLog statusLog);


    /**
     * 根据项目号查询ERP同步到本地
     *
     * @param project
     * @return void
     * @Title updateProjectByErp
     * @Description
     */
    void updateProjectByErp(String projectNo);


    /**
     * 根据状态查询延期项目列表
     *
     * @param projectStatus
     * @return List<Project>
     * @Title selectProjectByStatus
     * @Description
     */
    List<Project> selectProjectByStatus(Integer projectStatus);


    /**
     * 查询技术员进行中项目数量
     *
     * @param technician
     * @return int
     * @Title selectByTechnician
     * @Description
     */
    int selectByTechnician(String technician);


    /**
     * 查询projectVO对象
     *
     * @param projectNo
     * @return ProjectVO
     * @Title selectProjectVO
     * @Description
     */
    ProjectVO selectProjectVO(String projectNo, String factoryId);


    /**
     * 查询projectVO对象list
     *
     * @param projectNo
     * @return ProjectVO
     * @Title selectProjectVO
     * @Description
     */
    List<ProjectVO> selectProjectVOList(String factoryId);

    /**
     * 查询projectVO对象list
     *
     * @param projectNo
     * @return ProjectVO
     * @Title selectProjectVO
     * @Description
     */
    List<ProjectVO> selectProjectVOList(String factoryId, String selectStr);

    /**
     * @param project
     * @return List<Project>
     * @throws
     * @Title:IProjectService
     * @Description:查询疑难项目列表
     * @author wangyang
     */
    List<Project> getDifficultItemListPage(Project project);

    /**
     * @param parseInt
     * @param projectNo void
     * @throws
     * @Title:IProjectService
     * @Description:修改项目是否是疑难项目
     * @author wangyang
     */
    void updateDifficultProject(Project project);

    /**
     * @param project
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:查询疑难项目数
     * @author wangyang
     */
    int getNumberOfDifficultProjects(Project project);

    /**
     * @param project
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:一个月内完成的项目数量
     * @author wangyang
     */
    List<Project> getNumberOfProjectsCompletedInOneMonth(Project project);

    /**
     * @param project
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:查询延期项目数
     * @author wangyang
     */
    int getNumberOfDeferredItems(Project project);

    /**
     * @param project
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:查询超过3个月跟单项目数
     * @author wangyang
     */
    int getNumberOfDocumentaryItemsOver3Months(Project project);

    /**
     * @param project
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:查询跟单项目数
     * @author wangyang
     */
    int findAllCount(Project project);


    /**
     * 根据工厂名查询
     *
     * @param factoryName
     * @return int
     * @Title selectCountByFactory
     * @Description
     */
    Double selectRateByFactory(String factoryName, Integer delayStatus);

    /**
     * 根据工厂名查询 （单一项目）
     *
     * @param factoryName
     * @return int
     * @Title selectCountByFactory
     * @Description
     */
    Double selectRateByFactoryOnly(String factoryName, Integer delayStatus);


    /**
     * 查询最近一个月完成项目
     *
     * @return List<Project>
     * @Title selectMonthProject
     * @Description
     */
    List<Project> selectMonthProject();


    /**
     * 查询AB级项目15天无项目评审会的项目
     */
    List<Project> selectByMeetingTask();


    /**
     * 查询AB级项目每15天需要开重要项目评审会
     */
    List<Project> selectByImportantMeetingTask();

    /**
     * @param object2
     * @param plasticDeliveryPeriod
     * @param object
     * @param metalDeliveryTime
     * @param allProjectNo
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:要求完成数
     * @author wangyang
     */
    int getFinishTime(String allProjectNo, int metalDeliveryTime, int object, int plasticDeliveryPeriod, int object2);


    /**
     * 根据状态查询获取项目内容
     *
     * @param project
     * @return List<Project>
     * @Title findProjectByStatus
     * @Description
     */
    List<Project> findProjectByStatus(Project project);

    /**
     * @param mon
     * @param default1
     * @return List<Project>
     * @throws
     * @Title:IProjectService
     * @Description:查询完结项目
     * @author wangyang
     */
    List<Project> selectCompleteTasks(int num, Date m);

    /**
     * @param code
     * @param customerStartTime
     * @param customerEndTime
     * @return List<Project>
     * @throws
     * @Title:IProjectService
     * @Description:查询未转出货状态项目
     * @author wangyang
     */
    List<Project> selectSemiAnnualProject(int code, String customerStartTime,
                                          String customerEndTime);

    /**
     * @param code
     * @return List<Project>
     * @throws
     * @Title:IProjectService
     * @Description:查询A/B级打样阶段项目导出样品进行
     * @author wangyang
     */
    List<Project> selectProofingPhaseProject(int code);

    int updateByPrimaryKeySelective(Project project);

    /**
     * @param project
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:修改状态
     * @author wangyang
     */
    int updateStatus(Project project);

    /**
     * @param project
     * @return List<Project>
     * @throws
     * @Title:IProjectService
     * @Description:查询工厂对应全部合同号及项目号
     * @author wangyang
     */
    List<Project> getProductingProject(Project project);

    /**
     * @param project
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:查询工厂对应全部合同号项目数
     * @author wangyang
     */
    int getProductingProjectCount(Project project);

    /**
     * @param code
     * @param startDate
     * @param endDate
     * @return List<Project>
     * @throws
     * @Title:IProjectService
     * @Description:上月完成项目扣款列表
     * @author wangyang
     */
    List<Project> selectProjectByStatus1(int code, Date startDate, Date endDate);

    List<Project> queryFinishByTime(Date beginTime, Date endTime);

    /**
     * @param userName
     * @return String
     * @throws
     * @Title:IProjectService
     * @Description:查询质检全部项目数
     * @author wangyang
     */
    String getAll(String userName);

    /**
     * @param userName
     * @param i
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:获取对应项目数
     * @author wangyang
     */
    int getProjectS(String userName, int i);

    /**
     * @return List<Project>
     * @throws
     * @Title:IProjectService
     * @Description:获取无图纸项目
     * @author wangyang
     */
    List<Project> getNoDrawing();

    /**
     * @param projectNo
     * @return String
     * @throws
     * @Title:IProjectService
     * @Description:TODO
     * @author wangyang
     */
    Project getzhijian(Project project);

    /**
     * @return List<Project>
     * @throws
     * @Title:IProjectService
     * @Description:查询无历史质检项目
     * @author wangyang
     */
    List<Project> getReturnItem();

    /**
     * @param project void
     * @throws
     * @Title:IProjectService
     * @Description:修改成员
     * @author wangyang
     */
    void updateHistoryInspection(Project project);

    /**
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:本周下周生产完成，未安排质检的项目列表
     * @author wangyang
     */
    int getNoInspectionTask(Project project);

    /**
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:查询第一次大货，尚未安排中期检验项目
     * @author wangyang
     */
    int getNoInterimInspection(Project project);

    /**
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:本周下周生产完成，未安排质检的项目列表
     * @author wangyang
     */
    List<Project> getNoInspectionTaskList(Project project);

    /**
     * @return int
     * @throws
     * @Title:IProjectService
     * @Description:查询第一次大货，尚未安排中期检验项目
     * @author wangyang
     */
    List<Project> getNoInterimInspectionList(Project project);

    /**
     * 根据时间查看在进行中项目列表
     *
     * @param m
     * @return
     */

    List<Project> selectProjectExport(String m);

    /**
     * 切换状态发任务
     *
     * @param projectNo
     */
    void switchProjectStatus(String projectNo);

    /**
     * 获取项目无属性
     *
     * @return
     */
    List<Project> getAllMaterial();


    List<Project> searchNoFinishProject(Project project);

    int updateProjectSample(Project project);

    int updateProjectFinish(Project project);
}
