package com.cn.hnust.service.impl;

import java.net.URLEncoder;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.InspectionPlanMapper;
import com.cn.hnust.dao.ProjectInspectionReportMapper;
import com.cn.hnust.excel.ReadInspectionExcel;
import com.cn.hnust.pojo.InspectionPlan;
import com.cn.hnust.pojo.ProjectInspectionReport;
import com.cn.hnust.service.IProjectInspectionReportService;

@Service
public class ProjectInspectionReportServiceImpl implements IProjectInspectionReportService {
    @Autowired
    private ProjectInspectionReportMapper projectInspectionReportMapper;
    @Autowired
    private InspectionPlanMapper inspectionPlanMapper;
	@Override
	public List<ProjectInspectionReport> selectAllInspectionReport() {
		return projectInspectionReportMapper.selectAllInspectionReport();
	}
	@Transactional
	@Override
	public void batchAddInspectionReport(List<ProjectInspectionReport> projectInspectionReportList) {
		projectInspectionReportMapper.batchAddInspectionReport(projectInspectionReportList);
	}
	@Override
	public List<ProjectInspectionReport> selectInspectionReportByProjectNo(String projectNo) {
		return projectInspectionReportMapper.selectInspectionReportByProjectNo(projectNo);
	}
	@Override
	public List<ProjectInspectionReport> selectInspectionPlanByProjectNo(String projectNo) {
		return projectInspectionReportMapper.selectInspectionPlanByProjectNo(projectNo);
	}
	
	@Transactional
	@Override
	public void addProjectInspectionReport(ProjectInspectionReport insertProject) throws Exception {
		projectInspectionReportMapper.insertSelective(insertProject);
		String reportName = insertProject.getReportName();
		if(StringUtils.isNotBlank(reportName)){
			reportName = URLEncoder.encode(reportName, "utf-8");
			try {
				ReadInspectionExcel read = new ReadInspectionExcel("http://117.144.21.74:33168/upload/po/upload/JIANYANJIHUAZJ/"+reportName);
				List<InspectionPlan> excelContents = read.readExcelContent();
				excelContents.forEach(plan->{
					plan.setProjectNo(insertProject.getProjectNo());
					plan.setCreateDate(insertProject.getCreateDate());
				});
				if(excelContents!=null&&excelContents.size()>0){
					inspectionPlanMapper.insertBatch(excelContents);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
	}
	@Override
	public ProjectInspectionReport getLast() {
		
		return projectInspectionReportMapper.getLast();
	}
	@Override
	public void insertSelective(ProjectInspectionReport inspectionReport1) {
		projectInspectionReportMapper.insertSelective(inspectionReport1);
		
	}
	@Override
	public ProjectInspectionReport getOne(String projectNo) {
		
		return projectInspectionReportMapper.getOne(projectNo);
	}
	@Override
	public List<ProjectInspectionReport> getall(
			ProjectInspectionReport insreport) {
		
		return projectInspectionReportMapper.getall(insreport);
	}
	@Override
	public List<ProjectInspectionReport> selectInspectionPlanByProjectNo1(
			String projectNo) {
		
		return projectInspectionReportMapper.selectInspectionPlanByProjectNo1(projectNo);
	}
	

}
