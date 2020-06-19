package com.cn.hnust.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.cn.hnust.pojo.ProjectTask;

public interface ProjectTaskMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteByQualityId(Integer qualityId);
    
    /**
     * 根据项目号删除任务
     * @Title deleteByProjectId 
     * @Description
     * @param qualityId
     * @return
     * @return int
     */
    int deleteByProjectNo(String projectNo);

    int insert(ProjectTask record);

    int insertSelective(ProjectTask record);

    ProjectTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectTask record);

    int updateByPrimaryKey(ProjectTask record);

	List<ProjectTask> selectProjectTask(ProjectTask projectTask);

	List<ProjectTask> selectProjectTaskCount(ProjectTask projectTask);
    /**
     * 统计每个人未完成的数量
     * @return
     */
	List<ProjectTask> statisticsProjectTaskNoFinish(ProjectTask projectTask);
    /**
     * 统计最近30天完成数量
     * @return
     */
	List<ProjectTask> statisticsProjectTaskFinish(ProjectTask projectTask);

	List<ProjectTask> statisticsProjectTaskOnTime(@Param("accepter")String acceptor,@Param("sign")String sign);

	List<ProjectTask> statisticsProjectTaskAllFinish(@Param("accepter")String acceptor,@Param("sign")String sign);

	List<ProjectTask> selectMeetingRecordTask(String meetingNo);
    /**
     * 统计会议任务未完成的数量
     * @param acceptor
     * @return
     */
	List<ProjectTask> selectMeetingRecordTaskNoFinish(String acceptor);

	List<ProjectTask> selectProjectTaskIfExist(ProjectTask projectTask);
    /**
     *查询拓展最久的任务时间
     * @param userName
     * @return
     */
	ProjectTask selectProjectTaskMaxDate(@Param("userName")String userName);
	
	
	int hasAlreadyTask(@Param("triggerId")int triggerId,@Param("userId")int userId,@Param("projectNo")String projectNo);
	
	List<ProjectTask> selectAll();
	
	List<ProjectTask> selectByQualityId(@Param("qualityId")Integer qualityId);
	
	/**
	 * 批量插入
	 * @Title insertBatch 
	 * @Description
	 * @return
	 * @return int
	 */
	int insertBatch(List<ProjectTask> list);
	
	/**
	 * 根据项目号查询各状态下任务数
	 * @Title selectCountByStatus 
	 * @Description
	 * @return
	 * @return Map<String,Integer>
	 */
	Map<String,Long> selectCountByStatus(@Param("projectNo")String projectNo);
	
	
	/**
	 * 根据项目号查询任务
	 * @Title selectByProjectNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<ProjectTask>
	 */
	List<ProjectTask> selectByProjectNo(@Param("projectNo")String projectNo);
	 /**
     * 
     * @Title:IProjectTaskService
     * @Description:查看图纸变更
       @author wangyang
     * @param projectNo
     * @return ProjectTask
     * @throws
     */
	ProjectTask getDrawingAlteration(@Param("projectNo")String projectNo);
	/**
     * 
     * @Title:IProjectTaskService
     * @Description:查询最近30天完成任务，从2019-8-8开始
       @author wangyang
     * @param searchTask
     * @return List<ProjectTask>
     * @throws
     */
	List<ProjectTask> statisticsProjectTaskFinish1(ProjectTask searchTask);
	/**
	    * 
	    * @Title:IProjectTaskService
	    * @Description:准时完成项目数量，从2019-8-8开始
	      @author wangyang
	    * @param userName
	    * @param sign
	    * @return List<ProjectTask>
	    * @throws
	    */
	List<ProjectTask> statisticsProjectTaskOnTime1(@Param("accepter")String acceptor,@Param("sign")String sign);
	/**
	    * 
	    * @Title:IProjectTaskService
	    * @Description:完成项目数量，从2019-8-8开始
	      @author wangyang
	    * @param userName
	    * @param sign
	    * @return List<ProjectTask>
	    * @throws
	    */
	List<ProjectTask> statisticsProjectTaskAllFinish1(@Param("accepter")String acceptor,@Param("sign")String sign);
	/**
	 * 
	 * @Title:IProjectTaskService
	 * @Description:统计会议任务未完成的数量每个人,从2019-8-8开始
	   @author wangyang
	 * @param userName
	 * @return List<ProjectTask>
	 * @throws
	 */
	List<ProjectTask> selectMeetingRecordTaskNoFinish1(String userName);
	/**
	 * 
	 * @Title:IProjectTaskService
	 * @Description:每个人未完成的任务
	   @author wangyang
	 * @param searchTask
	 * @return List<ProjectTask>
	 * @throws
	 */
	List<ProjectTask> statisticsProjectTaskNoFinish1(ProjectTask searchTask);
	/**
	 * 
	 * @Title:IProjectTaskService
	 * @Description:修改状态
	   @author wangyang
	 * @param task void
	 * @throws
	 */
	void updateStatus(ProjectTask task);
	/**
	 * 
	 * @Title:IProjectTaskService
	 * @Description:查询未完成任务数量
	   @author wangyang
	 * @return Integer
	 * @throws
	 */
	Integer getProjectTaskCount();
	/**
	 * 
	 * @Title:IProjectTaskService
	 * @Description:根据项目号查询未完成检验任务
	   @author wangyang
	 * @param projectNo
	 * @return int
	 * @throws
	 */
	int getInspectionTaskNotCompleted(ProjectTask task);
	/**
 * 
 * @Title:IProjectTaskService
 * @Description:查询每个人的未完成数量
   @author wangyang
 * @param searchTask
 * @return List<ProjectTask>
 * @throws
 */
public List<ProjectTask> statisticsProjectTaskNoFinish2(ProjectTask searchTask);
/**
 * 
 * @Title:IProjectTaskService
 * @Description:统计最近30天的完成数量
   @author wangyang
 * @param searchTask
 * @return List<ProjectTask>
 * @throws
 */
public List<ProjectTask> statisticsProjectTaskFinish2(ProjectTask searchTask);
/**
 * 
 * @Title:IProjectTaskService
 * @Description:准时完成项目数量
   @author wangyang
 * @param searchTask
 * @return List<ProjectTask>
 * @throws
 */
public JSONArray statisticsProjectTaskOnTime2(ProjectTask projectTask);
/**
 * 
 * @Title:IProjectTaskService
 * @Description:全部完成项目数量
   @author wangyang
 * @param searchTask
 * @return List<ProjectTask>
 * @throws
 */
public JSONArray statisticsProjectTaskAllFinish2(ProjectTask projectTask);
/**
 * 
 * @Title:IProjectTaskService
 * @Description:
   @author wangyang
 * @param searchTask
 * @return List<ProjectTask>
 * @throws
 */
public List<ProjectTask> statisticsProjectTaskOnTime3(ProjectTask projectTask);
/**
 * 
 * @Title:IProjectTaskService
 * @Description:会议相关未完成任务数量
   @author wangyang
 * @param searchTask
 * @return List<ProjectTask>
 * @throws
 */
public JSONArray selectMeetingRecordTaskNoFinish2(ProjectTask existTask);
/**
 * 
 * @Title:ProjectTaskMapper
 * @Description:修改逻辑
   @author wangyang
 * @param projectNo
 * @return ProjectTask
 * @throws
 */
ProjectTask getByProjectNo(@Param("projectNo")String projectNo);

	/**
	 *查询是否已有制图任务
	 * @param s
	 * @return
	 */

    ProjectTask getByDescription(@Param("description")String s);
}