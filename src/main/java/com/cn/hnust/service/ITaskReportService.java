package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.TaskReport;

public interface ITaskReportService {
    /**
     * 添加任务汇报信息
     * @param taskReport
     */
	public void addTaskReport(TaskReport taskReport);
	/**
	 * 根据项目号查询任务汇报详情
	 * @param projectNo
	 */
	public List<TaskReport> selectTaskReportByNo(String projectNo);
	/**
	 * 根据任务id，删除任务汇报
	 * @param taskId
	 */
	public void delTaskReport(String taskId);
}
