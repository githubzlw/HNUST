package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.ProjectInspectionReport;

public interface IProjectInspectionReportService {

	List<ProjectInspectionReport> selectAllInspectionReport();

	void batchAddInspectionReport(List<ProjectInspectionReport> projectInspectionReportList);

	List<ProjectInspectionReport> selectInspectionReportByProjectNo(String projectNo);

	List<ProjectInspectionReport> selectInspectionPlanByProjectNo(String projectNo);

	void addProjectInspectionReport(ProjectInspectionReport insertProject)throws Exception;
  /**
   * 
   * @Title:IProjectInspectionReportService
   * @Description:获取客户最大编号
     @author wangyang
   * @return ProjectInspectionReport
   * @throws
   */
	ProjectInspectionReport getLast();
/**
 * 
 * @Title:IProjectInspectionReportService
 * @Description:插入数据
   @author wangyang
 * @param inspectionReport1 void
 * @throws
 */
void insertSelective(ProjectInspectionReport inspectionReport1);
/**
 * 
 * @Title:IProjectInspectionReportService
 * @Description:根据项目号查询检验计划
   @author wangyang
 * @param projectNo
 * @return List<ProjectInspectionReport>
 * @throws
 */
ProjectInspectionReport getOne(String projectNo);
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

List<ProjectInspectionReport> selectInspectionPlanByProjectNo1(String projectNo);


}
