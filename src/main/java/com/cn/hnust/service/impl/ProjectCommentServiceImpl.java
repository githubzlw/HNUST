package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.component.RpcQualityNoticeToKuai;
import com.cn.hnust.dao.CommentMapper;
import com.cn.hnust.dao.ProjectMapper;
import com.cn.hnust.pojo.Comment;
import com.cn.hnust.pojo.DingTalkModel;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.service.IProjectCommentService;

@Service
public class ProjectCommentServiceImpl implements IProjectCommentService {

	@Autowired
	private  CommentMapper commentMapper;
	@Autowired
	private  ProjectMapper projectMapper;

	@Transactional
	@Override
	public void addProjetcComment(Comment comment) {
		commentMapper.insertSelective(comment);
		
		//质检报告回复时，对应人员发送提醒消息
		if(comment.getQualityReportId()!=null){
			Project project = projectMapper.selectProjctDetails(comment.getProjectNo());
			if(!comment.getReviewer().equalsIgnoreCase(project.getSellName())){
				DingTalkModel model = new DingTalkModel(comment.getQualityReportId().toString(), project.getSellName(),project.getSellDingTalkId(),
						comment.getComment(), project.getProjectNo(), "notice");
				RpcQualityNoticeToKuai.sendRequest(model);
			}
			if(!comment.getReviewer().equalsIgnoreCase(project.getPurchaseName())){
				DingTalkModel model = new DingTalkModel(comment.getQualityReportId().toString(), project.getPurchaseName(),project.getPurchaseDingTalkId(),
						comment.getComment(), project.getProjectNo(), "notice");
				RpcQualityNoticeToKuai.sendRequest(model);
			}			
		}
	}

	@Override
	public List<Comment> selectProjectComment(String projectNo) {
		return commentMapper.selectProjectComment(projectNo);
	}

	@Override
	public List<Comment> selectByReportId(Integer reportId) {
		return commentMapper.selectByReportId(reportId);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return commentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Comment> selectByShippingId(Integer shippingId) {
		return commentMapper.selectByShippingId(shippingId);
	}

	@Override
	public List<Comment> selectByComplaintId(Integer complaintId) {
		return commentMapper.selectByComplaintId(complaintId);
	}

	@Override
	public List<Comment> selectByComplaintId1(int parseInt) {
		
		return commentMapper.selectByComplaintId1(parseInt);
	}
	
}
