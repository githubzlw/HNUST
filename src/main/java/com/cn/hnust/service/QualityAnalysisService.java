package com.cn.hnust.service;

import java.util.List;
import java.util.Map;

import com.cn.hnust.pojo.QualityAnalysis;

public interface QualityAnalysisService {
    int deleteByPrimaryKey(Integer id);

    int insert(QualityAnalysis record);
    
    int insertSelective(QualityAnalysis record);

    /**
     * 根据类型判断当前是质量分析表、还是技术分析表
     * 1：质量分析表  2：技术分析表
     * @Title insertSelective 
     * @Description 
     * @param record
     * @param type
     * @return
     * @return int
     */
    int insertSelective(QualityAnalysis record,String sender,int type);

    QualityAnalysis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QualityAnalysis record);
    
    /**
     * 根据类型判断当前是质量分析表、还是技术分析表
     * 1：质量分析表  2：技术分析表
     * @Title updateByPrimaryKeySelective 
     * @Description 
     * @param record
     * @param type
     * @return
     * @return int
     */
    int updateByPrimaryKeySelective(QualityAnalysis record,String sender,int type);

    int updateByPrimaryKey(QualityAnalysis record);
    
    
    /**
     * 根据项目号 
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return List<AnalysisIssue>
     */
    QualityAnalysis selectByProjectNo(String projectNo);
    
    
    
    /**
     * 根据问题查询
     * @Title selectByIssue 
     * @Description
     * @param issue
     * @return
     * @return List<String>
     */
    List<String> selectByIssue(String issue);
    
    
    /**
     * 根据问题查询
     * @Title selectByIssue 
     * @Description
     * @param issue
     * @return
     * @return List<String>
     */
    List<Map<String,Object>> selectMapByIssue(String issue,String inputKey,Integer type,Integer pageNumber,Integer pageSize);
}