package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProjectReport;

public interface ProjectReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectReport record);

    int insertSelective(ProjectReport record);

    ProjectReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectReport record);

    int updateByPrimaryKey(ProjectReport record);
    /***
     * 查询项目的汇报情况
     * @param projectNo
     * @return
     */
    public List<ProjectReport> selectProjectReport(String projectNo);
    
    public void updateProjectReportStatus(String projectNo);
    /**
     * 查询周报上传的最近时间
     * @param projectNo
     * @return
     */
	ProjectReport selectProjectReportLatelyDate(@Param("projectNo")String projectNo);
    /**
     * 查询本周是否更新了周报
     * @param projectNo
     * @param week 
     * @return
     */
	List<ProjectReport> selectProjectReportWeek(@Param("projectNo")String projectNo, @Param("week")int week);
	

}