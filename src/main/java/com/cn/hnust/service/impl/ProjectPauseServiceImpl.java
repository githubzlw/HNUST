package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ProjectPauseMapper;
import com.cn.hnust.pojo.ProjectPause;
import com.cn.hnust.service.ProjectPauseService;
@Service
public class ProjectPauseServiceImpl implements ProjectPauseService {

	@Autowired
	private ProjectPauseMapper projectPauseMapper;
	
	@Override
	public int count(String projectNo) {
		return projectPauseMapper.count(projectNo);
	}

	@Override
	public List<ProjectPause> selectByProjectNo(String projectNo) {
		return projectPauseMapper.selectByProjectNo(projectNo);
	}

	@Override
	public ProjectPause selectLastPause(String projectNo) {
		return projectPauseMapper.selectLastPause(projectNo);
	}

	@Override
	public int updateByPrimaryKeySelective(ProjectPause record) {
		return projectPauseMapper.updateByPrimaryKeySelective(record);
	}

	
	
	
}
