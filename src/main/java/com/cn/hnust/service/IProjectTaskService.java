package com.cn.hnust.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.cn.hnust.pojo.FactoryScore;
import com.cn.hnust.pojo.ProjectTask;

public interface IProjectTaskService {

    public int deleteByPrimaryKey(Integer id);
	
	public void addProjectTask(ProjectTask projectTask);
	
	public List<ProjectTask> selectProjectTask(ProjectTask projectTask);
	
	public List<ProjectTask> selectProjectTaskCount(ProjectTask projectTask);
    /**
     * 查看详情界面
     * @param id
     */
	public ProjectTask selectProjectTaskById(Integer id);
	
	public void updateProjectTask(ProjectTask projectTask);
	
	public List<ProjectTask> statisticsProjectTaskNoFinish(ProjectTask projectTask);

	public List<ProjectTask> statisticsProjectTaskFinish(ProjectTask projectTask);
	
	public List<ProjectTask> statisticsProjectTaskOnTime(String acceptor,String sign);
	
	public List<ProjectTask> statisticsProjectTaskAllFinish(String acceptor,String sign);
	
	public List<ProjectTask> selectMeetingRecordTask(String meetingNo);
	
	/**
	 * 统计会议任务未完成的数量每个人
	 * @param acceptor
	 * @return
	 */
	public List<ProjectTask> selectMeetingRecordTaskNoFinish(String acceptor);
	/**
	 * 查询该用户是否存在
	 * @param acceptor
	 * @return
	 */
	public List<ProjectTask> selectProjectTaskIfExist(ProjectTask projectTask);
	
	public ProjectTask selectProjectTaskMaxDate(String userName);
	
	int hasAlreadyTask(int triggerId,int userId,String projectNo);
	
    int insertSelective(ProjectTask pro);
    
    void checkNextTask(ProjectTask pro);
    
	List<ProjectTask> selectByQualityId(Integer qualityId);
	
	int deleteByQualityId(Integer qualityId);
	
	
	
	/**
	 * 批量插入
	 * @Title insertBatch 
	 * @Description
	 * @return
	 * @return int
	 */
	int insertBatch(List<ProjectTask> items)throws ParseException;
	
	
	/**
	 * 根据项目号查询各状态下任务数
	 * @Title selectCountByStatus 
	 * @Description
	 * @return
	 * @return Map<String,Integer>
	 */
	Map<String,Long> selectCountByStatus(String projectNo);
	
	
	
	/**
	 * 根据项目号查询任务
	 * @Title selectByProjectNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<ProjectTask>
	 */
	List<ProjectTask> selectByProjectNo(String projectNo);
	
	
	void updateProjectTask(ProjectTask projectTask,List<FactoryScore> scoreList);
    /**
     * 
     * @Title:IProjectTaskService
     * @Description:查看图纸变更
       @author wangyang
     * @param projectNo
     * @return ProjectTask
     * @throws
     */
	public ProjectTask getDrawingAlteration(String projectNo);
    /**
     * 
     * @Title:IProjectTaskService
     * @Description:查询最近30天完成任务，从2019-8-8开始
       @author wangyang
     * @param searchTask
     * @return List<ProjectTask>
     * @throws
     */
	public List<ProjectTask> statisticsProjectTaskFinish1(ProjectTask searchTask);
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
	
	public List<ProjectTask> statisticsProjectTaskOnTime1(String userName, String sign);
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
	public List<ProjectTask> statisticsProjectTaskAllFinish1(String userName,
			String sign);
/**
 * 
 * @Title:IProjectTaskService
 * @Description:统计会议任务未完成的数量每个人,从2019-8-8开始
   @author wangyang
 * @param userName
 * @return List<ProjectTask>
 * @throws
 */
	public List<ProjectTask> selectMeetingRecordTaskNoFinish1(String userName);
/**
 * 
 * @Title:IProjectTaskService
 * @Description:每个人未完成的任务
   @author wangyang
 * @param searchTask
 * @return List<ProjectTask>
 * @throws
 */
public List<ProjectTask> statisticsProjectTaskNoFinish1(ProjectTask searchTask);
/**
 * 
 * @Title:IProjectTaskService
 * @Description:修改数据
   @author wangyang
 * @param projectTask void
 * @throws
 */
public void updateByPrimaryKeySelective(ProjectTask projectTask);
/**
 * 
 * @Title:IProjectTaskService
 * @Description:修改状态
   @author wangyang
 * @param task void
 * @throws
 */
public void updateStatus(ProjectTask task);
/**
 * 
 * @Title:IProjectTaskService
 * @Description:查询未完成任务数量
   @author wangyang
 * @return Integer
 * @throws
 */
public Integer getProjectTaskCount();
/**
 * 
 * @Title:IProjectTaskService
 * @Description:根据项目号查询未完成检验任务
   @author wangyang
 * @param projectNo
 * @return int
 * @throws
 */
public int getInspectionTaskNotCompleted(ProjectTask task);
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
 * @Title:IProjectTaskService
 * @Description:查询是否存在要验证图纸跟新要求
   @author wangyang
 * @param projectNo
 * @return ProjectTask
 * @throws
 */
public ProjectTask getByProjectNo(String projectNo);

   
}
