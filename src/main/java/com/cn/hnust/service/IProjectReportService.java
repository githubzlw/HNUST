package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.ProjectReport;

public interface IProjectReportService {
    /***
     * 查询项目的汇报情况
     * @param projectNo
     * @return
     */
	public List<ProjectReport> selectProjectReport(String projectNo);
	/**
	 * 添加项目汇报情况
	 * @param projectReport
	 */
	public void addProjectReport(ProjectReport projectReport);
	
	public void updateProjectReportStatus(String projectNo);
	
	public ProjectReport selectProjectReportLatelyDate(String projectNo);
	
	/**
	 * 查询本周是否更新了周报
	 * @param week 
	 */
	public List<ProjectReport> selectProjectReportWeek(String projectNo, int week);
	
}
