package com.cn.hnust.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.ProjectVO;

public interface ProjectMapper {
    int deleteByPrimaryKey(String id);

    int insert(Project record);

    int insertSelective(Project record);

    /**
     * 根据登录的用户UserId查询所需项目
     *
     * @param userId
     * @return
     */
    List<Project> findProjectList(Project project);

    /**
     * 根据登录的用户UserId查询采购相关的项目
     *
     * @param project
     * @return
     */
    List<Project> findPurchaseProjectList(Project project);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    /**
     * 根据项目号查询项目详情
     *
     * @param projectNo
     * @return
     */
    Project selectProjctDetails(@Param("projectNo") String projectNo);

    /**
     * 查询所有的项目
     *
     * @return
     */
    List<Project> selectAllProject();

    /**
     * 批量添加项目信息
     *
     * @param projectList
     */
    void batchAddProject(List<Project> projectList);

    /**
     * 查询CRM推送给来的订单信息是否存在
     *
     * @param projectNo
     * @return
     */
    Project selectProjectByProjectNo(@Param("projectNo") String projectNo);

    /**
     * 查询交期变更的项目列表(管理员和销售共用)
     *
     * @param project
     * @return
     */
    List<Project> findDelayProjectList(Project project);

    /**
     * 查询交期变更的项目列表(采购)
     *
     * @param project
     * @return
     */
    List<Project> findDelayProjectPurchaseList(Project project);

    /**
     * 查询未设置实际交期的项目
     *
     * @param project
     * @return
     */
    List<Project> findSetDeliveryTimeList(Project project);

    /**
     * 查询项目采购计划统计(管理员,销售)
     *
     * @param project
     * @return
     */
    List<Project> findProjectReportList(Project project);

    /**
     * 查询项目采购计划统计(采购)
     *
     * @param project
     * @return
     */
    List<Project> findProjectReportPurchaseList(Project project);

    /**
     * 缺少开工图片的项目(管理员,销售)
     *
     * @param project
     * @return
     */
    List<Project> findProjectReportNoPicList(Project project);

    /**
     * 统计缺少开工图片的项目(采购)
     *
     * @param project
     * @return
     */
    List<Project> findProjectReportNoPicPurchaseList(Project project);

    /**
     * 统计缺少任务汇报的项目
     *
     * @param project
     * @return
     */
    List<Project> findProjectNoTaskReportList(Project project);

    /**
     * 统计查詢延期的项目(管理员,销售)
     *
     * @param project
     * @return
     */
    List<Project> findProjectDelayCountList(Project project);

    /**
     * 统计查詢延期的项目(采购)
     *
     * @param project
     * @return
     */
    List<Project> findProjectDelayPurchaseCountList(Project project);

    /**
     * 查询未设置交期的项目统计(采购)
     *
     * @param project
     * @return
     */
    List<Project> findSetDeliveryTimePurchaseList(Project project);

    /**
     * 统计查询没有任务汇报的项目
     *
     * @param project
     * @return
     */
    List<Project> findProjectNoTaskReportPurchaseList(Project project);

    /**
     * 消息中心(销售)
     *
     * @param project
     * @return
     */
    List<Project> selectProjectRelationTask(Project project);

    /**
     * 消息中心(采购)
     *
     * @param project
     * @return
     */
    List<Project> selectPurchaseProjectRelationTask(Project project);

    /**
     * 销售进来查看
     *
     * @param project
     * @return
     */
    List<Project> findProjectReportMessage(Project project);

    /**
     * 查询该销售下的采购
     *
     * @param project
     * @return
     */
    List<Project> selectProjectPurchaseList(Project project);

    /**
     * 查询记录条数销售登录
     *
     * @param project
     * @return
     */
    int findProjectListCount(Project project);

    /**
     * 查询记录条数采购登录
     *
     * @param project
     * @return
     */
    List<Project> findPurchaseProjectListCount(Project project);

    List<Project> selectProjectRelationTaskCount(Project project);

    List<Project> selectPurchaseProjectRelationTaskCount(Project project);

    List<Project> selectProjectList(
            @Param("hasEmailUserId") boolean hasEmailUserId,
            @Param("hasPurchaseId") boolean hasPurchaseId);

    int updateFlogByProjectNo(@Param("set") Set<String> proSet);

    int updateFlogByProjectNoFirst(@Param("set") Set<String> proSet);

    int updateaddFlogByProjectNoFirst(@Param("set") Set<String> proSet);

    int updateaddFlogByProjectNo(@Param("set") Set<String> proSet);

    /**
     * @param projectTask
     * @return List<ProjectTask>
     * @throws
     * @Title:ProjectMapper
     * @Description:最近两个月项目列表
     * @author wangyang
     */
    List<ProjectTask> selectProject(ProjectTask projectTask);

    /**
     * @param projectTask
     * @return List<ProjectTask>
     * @throws
     * @Title:ProjectMapper
     * @Description:
     * @author wangyang
     */
    List<ProjectTask> selectProjectCount(ProjectTask projectTask);

    void updateProjectDataSortField(Project project);

    List<Project> selectBigGoodsFinish(Project project);

    /**
     * 项目原定交期 超期一周后 ，给采购布置一个解释情况的任务
     *
     * @param proSet
     * @return
     */
    void updateProjectDeliveryDateDelay(@Param("set") Set<String> proSet);


    /**
     * 根据创建人id查询，用于项目录入查看
     *
     * @param userId
     * @return List<Project>
     * @Title selectByCreatePersonId
     * @Description
     */
    List<Project> selectByCreatePersonId(@Param("createPersonId") Integer userId, @Param("roleNo") Integer roleNo, @Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);


    /**
     * 创建人创建项目数
     *
     * @param userId
     * @return int
     * @Title countNewProject
     * @Description
     */
    int countNewProject(@Param("createPersonId") Integer userId, @Param("roleNo") Integer roleNo);


    /**
     * 根据用户类型查启动项目最多的
     *
     * @param type
     * @return int
     * @Title maxStartProject
     * @Description
     */
    String maxStartProject(@Param("type") Integer type, @Param("dateType") Integer dateType);


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
     * 根据状态查询延期项目列表
     *
     * @param projectStatus
     * @return List<Project>
     * @Title selectProjectByStatus
     * @Description
     */
    List<Project> selectProjectByStatus(@Param("projectStatus") Integer projectStatus);


    /**
     * 查询技术员进行中项目数量
     *
     * @param technician
     * @return int
     * @Title selectByTechnician
     * @Description
     */
    int selectByTechnician(@Param("technician") String technician);


    /**
     * 查询projectVO对象
     *
     * @param projectNo
     * @return ProjectVO
     * @Title selectProjectVO
     * @Description
     */
    ProjectVO selectProjectVO(@Param("projectNo") String projectNo, @Param("factoryId") String factoryId);


    /**
     * 查询projectVO对象list
     *
     * @param projectNo
     * @return ProjectVO
     * @Title selectProjectVO
     * @Description
     */
    List<ProjectVO> selectProjectVOList(@Param("factoryId") String factoryId, @Param("selectStr") String selectStr);


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
     * @Title:ProjectMapper
     * @Description:修改项目是否是疑难项目
     * @author wangyang
     */
    void updateDifficultProject(Project project);

    /**
     * @param project
     * @return int
     * @throws
     * @Title:ProjectMapper
     * @Description:查询疑难项目数
     * @author wangyang
     */
    int getNumberOfDifficultProjects(Project project);

    /**
     * @param project
     * @return int
     * @throws
     * @Title:ProjectMapper
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
     * 根据工厂名查询（全部项目）
     *
     * @param factoryName
     * @return int
     * @Title selectCountByFactory
     * @Description
     */
    Double selectRateByFactory(@Param("factoryName") String factoryName, @Param("delayStatus") Integer delayStatus);

    /**
     * 根据工厂名查询 （单一项目）
     *
     * @param factoryName
     * @return int
     * @Title selectCountByFactory
     * @Description
     */
    Double selectRateByFactoryOnly(@Param("factoryName") String factoryName, @Param("delayStatus") Integer delayStatus);


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
     * @param allProjectNo
     * @param metalDeliveryTime
     * @param object
     * @param plasticDeliveryPeriod
     * @param object2
     * @return int
     * @throws
     * @Title:ProjectMapper
     * @Description:获取交期分数
     * @author wangyang
     */
    int getFinishTime(@Param("projectNo") String allProjectNo, @Param("metalDeliveryTime") int metalDeliveryTime,
                      @Param("metalDeliveryTime1") int object, @Param("plasticDeliveryPeriod") int plasticDeliveryPeriod, @Param("plasticDeliveryPeriod1") int object2);


    /**
     * 根据原始项目号查询
     *
     * @param projectNo
     * @return List<Project>
     * @Title selectByProjectDim
     * @Description
     */
    List<Project> selectByProjectDim(String projectNo);


    List<Project> selectByProjectList();

    int updateByNoPic(@Param("projectNo") String projectNo, @Param("productImg") String productImg);


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
     * @param num
     * @return List<Project>
     * @throws
     * @Title:ProjectMapper
     * @Description:TODO
     * @author wangyang
     */
    List<Project> selectCompleteTasks(@Param("projectStatus") int num, @Param("dateSample") Date mon);

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
    List<Project> selectSemiAnnualProject(@Param("projectStatus") int code, @Param("purchaseName") String purchaseUser, @Param("sellName") String saleUser);

    /**
     * @param code
     * @return List<Project>
     * @throws
     * @Title:ProjectMapper
     * @Description:查询A/B级打样阶段项目导出样品进行
     * @author wangyang
     */
    List<Project> selectProofingPhaseProject(int code);

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
    List<Project> selectProjectByStatus1(@Param("projectStatus") int code, @Param("createTime") Date startDate, @Param("finishTime") Date endDate);


    List<Project> queryFinishByTime(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    /**
     * @param userName
     * @return int
     * @throws
     * @Title:ProjectMapper
     * @Description:获取打样项目数
     * @author wangyang
     */
    int getProofingProject(@Param("zhijian1") String userName);

    /**
     * @param userName
     * @return int
     * @throws
     * @Title:ProjectMapper
     * @Description:获取量产项目数
     * @author wangyang
     */
    int getMassProductionProject(@Param("zhijian1") String userName);

    /**
     * @param userName
     * @return int
     * @throws
     * @Title:ProjectMapper
     * @Description:获取全部项目号
     * @author wangyang
     */
    String getAll(@Param("zhijian1") String userName);

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
     * @Title:ProjectMapper
     * @Description:查询质检
     * @author wangyang
     */
    Project getAllZhiJian(Project project);

    /**
     * @return List<Project>
     * @throws
     * @Title:ProjectMapper
     * @Description:查询无历史质检项目
     * @author wangyang
     */
    List<Project> getReturnItem();

    /**
     * @param project void
     * @throws
     * @Title:ProjectMapper
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


    List<Project> selectProjectExport(@Param("startTime") String m);

    /**
     * 获取项目无属性
     *
     * @return
     */
    List<Project> getAllMaterial();

    int insertProjectSyncLog(Project project);

    List<Project> searchNoFinishProject(Project project);

    int updateProjectSample(Project project);

    int updateProjectFinish(Project project);
}