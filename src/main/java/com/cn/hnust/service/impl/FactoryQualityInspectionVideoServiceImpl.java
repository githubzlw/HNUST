package com.cn.hnust.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;










import com.cn.hnust.dao.FactoryQualityInspectionVideoMapper;
import com.cn.hnust.dao.ProjectTaskMapper;
import com.cn.hnust.pojo.FactoryQualityInspectionVideo;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.service.FactoryQualityInspectionVideoService;

@Service
public class FactoryQualityInspectionVideoServiceImpl implements FactoryQualityInspectionVideoService{
	@Autowired
	private FactoryQualityInspectionVideoMapper factoryQualityInspectionVideoMapper;
	@Autowired
	private ProjectTaskMapper projectTaskMapper;
	private static final int VIDEO_UPLOAD = 1;
	

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return factoryQualityInspectionVideoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(FactoryQualityInspectionVideo record) {
		return factoryQualityInspectionVideoMapper.insert(record);
	}

	@Override
	public int insertSelective(FactoryQualityInspectionVideo record) {
		return factoryQualityInspectionVideoMapper.insertSelective(record);
	}

	@Override
	public FactoryQualityInspectionVideo selectByPrimaryKey(Integer id) {
		return factoryQualityInspectionVideoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(FactoryQualityInspectionVideo record) {
		return factoryQualityInspectionVideoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(FactoryQualityInspectionVideo record) {
		return factoryQualityInspectionVideoMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<FactoryQualityInspectionVideo> selectByFactoryName(
			String factoryName) {
		return factoryQualityInspectionVideoMapper.selectByFactoryName(factoryName);
	}

	@Transactional
	@Override
	public void insertBatch(List<FactoryQualityInspectionVideo> videos) {
		factoryQualityInspectionVideoMapper.insertBatch(videos);
		//如果存在任务id，更新任务视频上传状态
		Integer taskId = null;
		for (FactoryQualityInspectionVideo factoryQualityInspectionVideo : videos) {
			taskId = factoryQualityInspectionVideo.getTaskId();
		}
		if(taskId!=null){
			ProjectTask projectTask = new ProjectTask();
			projectTask.setId(taskId);
			projectTask.setIsVideoUpload(VIDEO_UPLOAD);
			projectTaskMapper.updateByPrimaryKeySelective(projectTask);
		}

	}

	@Override
	public List<FactoryQualityInspectionVideo> selectByProjectNo(
			String projectNo) {
		return factoryQualityInspectionVideoMapper.selectByProjectNo(projectNo);
	}

	@Override
	public List<FactoryQualityInspectionVideo> selectByTaskId(Integer taskId) {
		return factoryQualityInspectionVideoMapper.selectByTaskId(taskId);
	}

	@Override
	public List<FactoryQualityInspectionVideo> selectByProjectNoAndType(
			String projectNo, Integer videoType) {
		return factoryQualityInspectionVideoMapper.selectByProjectNoAndType(projectNo, videoType);
	}
}
