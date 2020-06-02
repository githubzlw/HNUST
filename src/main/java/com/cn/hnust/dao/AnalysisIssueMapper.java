package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.AnalysisIssue;

public interface AnalysisIssueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnalysisIssue record);

    int insertSelective(AnalysisIssue record);

    AnalysisIssue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnalysisIssue record);

    int updateByPrimaryKey(AnalysisIssue record);
    
    /**
     * 批量插入
     * @Title insertBatch 
     * @Description
     * @param list
     * @return
     * @return int
     */
    int insertBatch(List<AnalysisIssue> list);
    
    /**
     * 批量更新
     * @Title insertBatch 
     * @Description
     * @param list
     * @return
     * @return int
     */
    int updateBatch(List<AnalysisIssue> list);
    
    /**
     * 根据项目号 
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return List<AnalysisIssue>
     */
    List<AnalysisIssue> selectByProjectNo(@Param("projectNo")String projectNo,@Param("isComplaint")Integer isComplaint);
    
    
    /**
     * 根据问题查询10条数据
     * @Title selectTop10 
     * @Description 
     * @param issue
     * @return
     * @return List<String>
     */
    List<AnalysisIssue> selectTop10(@Param("issue")String issue);
    
    
    /**
     * 查询投诉问题
     * @Title selectComplaintIssue 
     * @Description
     * @return
     * @return List<AnalysisIssue>
     */
    List<AnalysisIssue> selectComplaintIssue();
    
    
    /**
     * 根据项目号 
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return List<AnalysisIssue>
     */
    List<AnalysisIssue> selectByProcess(@Param("process")String process);
    /**
     * 根据项目号 
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return List<AnalysisIssue>
     */
    List<AnalysisIssue> selectByProcessAndIssue(@Param("process")String process,@Param("issue")String issue);
    
    /**
     * 根据项目号 
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return List<AnalysisIssue>
     */
    List<AnalysisIssue> selectByComplaintId(@Param("complaintId")Integer complaintId);
}