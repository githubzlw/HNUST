package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProjectComplaint;
import com.cn.hnust.pojo.ProjectComplaintQuery;

public interface ProjectComplaintMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectComplaint record);

    int insertSelective(ProjectComplaint record);

    ProjectComplaint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectComplaint record);

    int updateByPrimaryKeyWithBLOBs(ProjectComplaint record);

    int updateByPrimaryKey(ProjectComplaint record);
    
    
    /**
     * 根据项目号查询
     * @Title selectByProjectNo 
     * @Description 
     * @param projectNo
     * @return
     * @return List<ProjectComplaint>
     */
    List<ProjectComplaint> selectByProjectNo(String projectNo);
    /**
     * 根据项目号模糊查询
     * @Title selectByProjectNoDim 
     * @Description 
     * @param projectNo
     * @return
     * @return List<ProjectComplaint>
     */
    List<ProjectComplaint> selectByProjectNoDim(String projectNo);
    /**
     * 查询客户投诉列表
     * @Title complaintList 
     * @Description
     * @param projectComplaintQuery
     * @return
     * @return List<ProjectComplaint>
     */
    List<ProjectComplaint> queryComplaintList(ProjectComplaintQuery projectComplaintQuery);
    
    /**
     * 查询客户投诉总数
     * @Title queryCount 
     * @Description
     * @param projectComplaintQuery
     * @return
     * @return int
     */
    int queryCount(ProjectComplaintQuery projectComplaintQuery);
    
    
    
    /**
     * 查询投诉任务未处理的数量
     * @Title countUnFinished 
     * @Description
     * @param projectNo
     * @return
     * @return int
     */
    int countUnFinished(String projectNo);
    /**
     * 
     * @Title:ComplaintIssueMapper
     * @Description:获取质量投诉次数
       @author wangyang
     * @param allProjectNo
     * @return int
     * @throws
     */
    String getComplaintsNumber(@Param("projectNo")String allProjectNo);
    /**
     * @return 
     * 
     * @Title:ProjectComplaintMapper
     * @Description:查询项目投诉列表
       @author wangyang
     * @param complaint void
     * @throws
     */
	List<ProjectComplaint> getComplaintsList(ProjectComplaint complaint);
	/**
	 * 
	 * @Title:ProjectComplaintService
	 * @Description:查询是否存在投诉未处理完
	   @author wangyang
	 * @param projectComplaintQuery
	 * @return int
	 * @throws
	 */
	int getQueryComplaintCount(ProjectComplaintQuery projectComplaintQuery);
/**
 * 
 * @Title:ProjectComplaintMapper
 * @Description:查询未处理投诉逻辑
   @author wangyang
 * @return List<ProjectComplaint>
 * @throws
 */
	List<ProjectComplaint> unprocessedItems();
	/**
	 * 
	 * @Title:ProjectComplaintService
	 * @Description:根据项目号查询最后投诉
	   @author wangyang
	 * @param projectNo
	 * @return ProjectComplaint
	 * @throws
	 */
ProjectComplaint getLate(@Param("projectNo")String projectNo);
/**
 * 
 * @Title:ProjectComplaintMapper
 * @Description:获取指定编号之上全部
   @author wangyang
 * @param complaint
 * @return List<ProjectComplaint>
 * @throws
 */
	List<ProjectComplaint> getAll(ProjectComplaint complaint);
    
}