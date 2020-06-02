package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.TaskReportMapper;
import com.cn.hnust.pojo.TaskReport;
import com.cn.hnust.service.ITaskReportService;

@Service
public class TaskReportServiceImpl implements ITaskReportService {
    
	@Autowired
	private TaskReportMapper taskReportMapper;
	@Override
	public void addTaskReport(TaskReport taskReport) {
		taskReportMapper.insertSelective(taskReport);
	}
	@Override
	public List<TaskReport> selectTaskReportByNo(String taskId) {
		return taskReportMapper.selectTaskReportByNo(taskId);
	}
	@Override
	public void delTaskReport(String taskId) {
		taskReportMapper.deleteByPrimaryKey(taskId);
	}
}
