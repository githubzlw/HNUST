package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.FeedbackMapper;
import com.cn.hnust.pojo.Feedback;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.service.IFeedbackService;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

	@Autowired
	private FeedbackMapper feedbackMapper;
	
	@Override
	public void addFeedback(Feedback feedback) {
		feedbackMapper.insertSelective(feedback);
	}

	@Override
	public List<Feedback> selectFeedbackByProjectNo(String projectNo,int week) {
		return feedbackMapper.selectFeedbackByProjectNo(projectNo,week);
	}

	@Override
	public Feedback selectLastByProjectNo(String projectNo) {
		return feedbackMapper.selectLastByProjectNo(projectNo);
	}

	@Override
	public int getNumberOfItemsNotUpdated(Project project) {
		
		return feedbackMapper.getNumberOfItemsNotUpdated(project);
	}


}
