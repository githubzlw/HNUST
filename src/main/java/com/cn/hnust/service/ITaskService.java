package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Task;

/**
 * 
 * @author Administrator
 *
 */
public interface ITaskService {
    /**
     * 根据项目号查询任务信息(销售)
     * @param projectNo
     * @return
     */
	public List<Task> selectTaskByProjectNo(Task task);
	/**
	 * 根据项目号查询任务信息(采购)
	 * @param purchaseTask
	 * @return
	 */
	public List<Task> selectTaskPurchaseByProjectNo(Task purchaseTask);
	
	/**
	 * 发布添加任务信息
	 * @param task
	 */
	public void addTask(Task task);
	/**
	 * 更新任务状态（重启任务，完成任务）
	 * @param task
	 */
	public void updateTask(Task task);
	/***
	 * 查询所有的任务
	 * @param task
	 * @return
	 */
	public List<Task> selectAllTask(Task task);
	/**
	 * 查询任务列表
	 * @param task
	 * @return
	 */
	public List<Task> selectTaskList(Task task);
	/**
	 * 根据任务Id查询任务详情(销售)
	 * @param taskId
	 * @return
	 */
	public Task selectTaskById(String id);
	/**
	 * 查询任务详情(采购)
	 * @param id
	 * @return
	 */
	public Task selectTaskPurchaseById(String id);
	/**
	 * 
	 * @param id
	 */
	public void delTaskById(String id);
	/**
	 * 根据项目号查询任务信息(详情页显示)
	 * @param projectNo
	 * @return
	 */
	public List<Task> findTaskByProjectNo(String projectNo);
	
}
