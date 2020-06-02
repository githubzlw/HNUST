package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProjectInspectionReport;

public interface ProjectInspectionReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProjectInspectionReport record);

    int insertSelective(ProjectInspectionReport record);

    ProjectInspectionReport selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProjectInspectionReport record);

    int updateByPrimaryKey(ProjectInspectionReport record);

	List<ProjectInspectionReport> selectAllInspectionReport();

	void batchAddInspectionReport(List<ProjectInspectionReport> projectInspectionReportList);

	List<ProjectInspectionReport> selectInspectionReportByProjectNo(String projectNo);

	List<ProjectInspectionReport> selectInspectionPlanByProjectNo(String projectNo);
  /**
   * 
   * @Title:ProjectInspectionReportMapper
   * @Description:获取最大号id
     @author wangyang
   * @return ProjectInspectionReport
   * @throws
   */
	ProjectInspectionReport getLast();
  /**
   * 
   * @Title:ProjectInspectionReportMapper
   * @Description:获取检验计划
     @author wangyang
   * @param projectNo
   * @return List<ProjectInspectionReport>
   * @throws
   */
ProjectInspectionReport getOne(@Param("projectNo")String projectNo);
/**
 * 
 * @Title:IProjectInspectionReportService
 * @Description:查询钉钉审批检验计划未处理
   @author wangyang
 * @param insreport
 * @return List<ProjectInspectionReport>
 * @throws
 */
List<ProjectInspectionReport> getall(ProjectInspectionReport insreport);

List<ProjectInspectionReport> selectInspectionPlanByProjectNo1(@Param("projectNo")String projectNo);
   
}