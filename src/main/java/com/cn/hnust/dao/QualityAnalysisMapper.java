package com.cn.hnust.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.QualityAnalysis;

public interface QualityAnalysisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QualityAnalysis record);

    int insertSelective(QualityAnalysis record);

    QualityAnalysis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QualityAnalysis record);

    int updateByPrimaryKey(QualityAnalysis record);
    
    
    /**
     * 根据项目号 
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return List<AnalysisIssue>
     */
    QualityAnalysis selectByProjectNo(@Param("projectNo")String projectNo);
    
    
    /**
     * 根据问题查询
     * @Title selectByIssue 
     * @Description
     * @param issue
     * @return
     * @return List<String>
     */
    List<String> selectByIssue(@Param("issue")String issue);
    /**
     * 根据问题查询
     * @Title selectByIssue 
     * @Description
     * @param issue
     * @return
     * @return List<String>
     */
    List<Map<String,Object>> selectMapByIssue(@Param("issue")String issue,@Param("inputKey")String inputKey,
    		@Param("type")Integer type,@Param("pageNumber")Integer pageNumber,@Param("pageSize")Integer pageSize);
       
}