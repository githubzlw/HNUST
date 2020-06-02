package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.TaskMapper;
import com.cn.hnust.pojo.Task;
import com.cn.hnust.service.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService {
      
	@Autowired
	private TaskMapper taskMapper;
	@Override
	public List<Task> selectTaskByProjectNo(Task task) {
		return taskMapper.selectTaskByProjectNo(task);
	}
	@Override
	public void addTask(Task task) {
		taskMapper.insertSelective(task);
	}
	@Override
	public void updateTask(Task task) {
	   taskMapper.updateByPrimaryKeySelective(task);
	}
	@Override
	public List<Task> selectAllTask(Task task) {
		return taskMapper.selectAllTask(task);
	}
	
	
	
	
	@Override
	public Task selectTaskById(String id) {
		return taskMapper.selectByPrimaryKey(id);
	}
	@Override
	public void delTaskById(String id) {
		taskMapper.deleteByPrimaryKey(id);
	}
	@Override
	public List<Task> selectTaskPurchaseByProjectNo(Task task) {
		return taskMapper.selectTaskPurchaseByProjectNo(task);
	}
	@Override
	public Task selectTaskPurchaseById(String id) {
		return taskMapper.selectTaskPurchaseById(id);
	}
	/**
	 * 根据项目号查询任务信息(详情页显示)
	 */
	@Override
	public List<Task> findTaskByProjectNo(String projectNo) {
		return taskMapper.findTaskByProjectNo(projectNo);
	}
	@Override
	public List<Task> selectTaskList(Task task) {
		return taskMapper.selectTaskList(task);
	}
}
