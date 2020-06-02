package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.AnalysisIssue;

public interface AnalysisIssueService {
    int deleteByPrimaryKey(Integer id);

    int insert(AnalysisIssue record);

    int insertSelective(AnalysisIssue record);

    AnalysisIssue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnalysisIssue record);

    int updateByPrimaryKey(AnalysisIssue record);
    
    /**
     * 根据项目号 
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return List<AnalysisIssue>
     */
    List<AnalysisIssue> selectByProjectNo(String projectNo,Integer isComplaint);
    
    
    /**
     * 根据问题查询10条数据
     * @Title selectTop10 
     * @Description 
     * @param issue
     * @return
     * @return List<String>
     */
    List<AnalysisIssue> selectTop10(String issue);
    
    
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
    List<AnalysisIssue> selectByProcess(String process);
    
    /**
     * 根据项目号 
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return List<AnalysisIssue>
     */
    List<AnalysisIssue> selectByProcessAndIssue(String process,String issue);
    
    /**
     * 根据项目号 
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return List<AnalysisIssue>
     */
    List<AnalysisIssue> selectByComplaintId(Integer complaintId);
    
}