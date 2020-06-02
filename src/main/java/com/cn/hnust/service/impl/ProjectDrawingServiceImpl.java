package com.cn.hnust.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ProjectDrawingMapper;
import com.cn.hnust.pojo.ProjectDrawing;
import com.cn.hnust.service.IProjectDrawingService;

@Service
public class ProjectDrawingServiceImpl implements IProjectDrawingService{
    
	@Autowired
	private ProjectDrawingMapper projectDrawingMapper;
	
	@Transactional
	@Override
	public void batchAddProjectDrawing(List<ProjectDrawing> projectDrawingList) {
		projectDrawingMapper.batchAddProjectDrawing(projectDrawingList);
	}

	@Override
	public List<ProjectDrawing> selectAllProjectDrawing() {
		return projectDrawingMapper.selectAllProjectDrawing();
	}

	@Override
	public List<ProjectDrawing> selectProjectDrawingByProjectNo(String projectNo) {
		return projectDrawingMapper.selectProjectDrawingByProjectNo(projectNo);
	}

	@Override
	public void addProjectDrawing(ProjectDrawing projectDrawing) {
		projectDrawingMapper.insertSelective(projectDrawing);
	}

	@Override
	public List<ProjectDrawing> selectProjectDemandReportByNo(String projectNo) {
		return projectDrawingMapper.selectProjectDemandReportByNo(projectNo);
	}

	@Override
	public List<ProjectDrawing> selectByProjectNo(ProjectDrawing drawing) {
		
		return projectDrawingMapper.selectByProjectNo(drawing);
	}

}
