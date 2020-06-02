package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ProjectReportMapper;
import com.cn.hnust.pojo.ProjectReport;
import com.cn.hnust.service.IProjectReportService;
@Service
public class IProjectReportServiceImpl implements IProjectReportService{
   
	@Autowired
	private ProjectReportMapper projectReportMapper;
	@Override
	public List<ProjectReport> selectProjectReport(String projectNo) {
		return projectReportMapper.selectProjectReport(projectNo);
	}
	@Override
	public void addProjectReport(ProjectReport projectReport) {
		projectReportMapper.insertSelective(projectReport);
	}
	@Override
	public void updateProjectReportStatus(String projectNo) {
		projectReportMapper.updateProjectReportStatus(projectNo);
	}
	/**
	 * 查询周报最近上传的时间
	 */
	@Override
	public ProjectReport selectProjectReportLatelyDate(String projectNo) {
		return projectReportMapper.selectProjectReportLatelyDate(projectNo);
	}
	/**
	 * 查询本周是否更新了周报
	 */
	@Override
	public List<ProjectReport> selectProjectReportWeek(String projectNo,int week) {
		return projectReportMapper.selectProjectReportWeek(projectNo,week);
	}
	

}
