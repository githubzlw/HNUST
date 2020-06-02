package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.Feedback;
import com.cn.hnust.pojo.Project;
public interface IFeedbackService {

	public void addFeedback(Feedback feedback);
	
	public List<Feedback> selectFeedbackByProjectNo(String projectNo,int week);
	
	
	/**
	 * 根据项目号查询最近时间的更新
	 * @Title selectByProjectNo 
	 * @Description 
	 * @param projectNo
	 * @return
	 * @return List<Feedback>
	 */
	Feedback selectLastByProjectNo(String projectNo);
	/**
	 * 
	 * @Title:IProjectService
	 * @Description:1周未跟新跟单项目数
	   @author wangyang
	 * @param project
	 * @return int
	 * @throws
	 */
		int getNumberOfItemsNotUpdated(Project project);

	
}
