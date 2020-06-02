package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ProjectMapper;
import com.cn.hnust.dao.ProjectScheduleMapper;
import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectSchedule;
import com.cn.hnust.service.IProjectScheduleService;

@Service
public class ProjectScheduleServiceImpl implements IProjectScheduleService {

	@Autowired
	private ProjectScheduleMapper projectScheduleMapper;	
	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return projectScheduleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProjectSchedule record) {
		return projectScheduleMapper.insert(record);
	}

	@Override
	public int insertSelective(ProjectSchedule record) {
		return projectScheduleMapper.insertSelective(record);
	}

	@Override
	public ProjectSchedule selectByPrimaryKey(Integer id) {
		return projectScheduleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ProjectSchedule record) {
		//当更新批次为最后一批时，更新项目状态为已完成
		List<ProjectSchedule> projectSchedules = projectScheduleMapper.selectByProjectNo(record.getProjectNo());
		if(projectSchedules!=null&& projectSchedules.size() == record.getNum()){
			Project project = new Project();
			if(record.getIsFinish() == 1){
				project.setProjectNo(record.getProjectNo());
				project.setProjectStatus(OrderStatusEnum.COMPLETE_ORDER.getCode());
				project.setFinish(1);
				projectMapper.updateByPrimaryKeySelective(project);
			}
		}
		
		return projectScheduleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ProjectSchedule record) {
		return projectScheduleMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insertBatch(List<ProjectSchedule> item) {
		return projectScheduleMapper.insertBatch(item);
	}

	@Override
	public int updateBatch(List<ProjectSchedule> item) {
		return projectScheduleMapper.updateBatch(item);
	}

	@Override
	public List<ProjectSchedule> selectByProjectNo(String projectNo) {
		return projectScheduleMapper.selectByProjectNo(projectNo);
	}

	@Override
	public ProjectSchedule selectByProjectNoAndNum(String projectNo, Integer num) {
		return projectScheduleMapper.selectByProjectNoAndNum(projectNo, num);
	}

}
