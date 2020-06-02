package com.cn.hnust.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.IssueReplyMapper;
import com.cn.hnust.dao.ProjectComplaintMapper;
import com.cn.hnust.pojo.IssueReply;
import com.cn.hnust.pojo.ProjectComplaint;
import com.cn.hnust.service.IssueReplyService;
import com.cn.hnust.service.ProjectComplaintService;


@Service
public class IssueReplyServiceImpl implements IssueReplyService {

	@Autowired
	private IssueReplyMapper issueReplyMapper;
	@Autowired
	private ProjectComplaintMapper projectComplaintMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return issueReplyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(IssueReply record) {
		return issueReplyMapper.insert(record);
	}

	@Override
	public int insertSelective(IssueReply record) {
		return issueReplyMapper.insertSelective(record);
	}

	@Override
	public IssueReply selectByPrimaryKey(Integer id) {
		return issueReplyMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(IssueReply record) {
		return issueReplyMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(IssueReply record) {
		return issueReplyMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insertBatch(List<IssueReply> list) {
		return issueReplyMapper.insertBatch(list);
	}

	@Override
	public List<IssueReply> queryByIssueId(Integer issueId) {
		return issueReplyMapper.queryByIssueId(issueId);
	}

	@Override
	public IssueReply queryReply(Integer issueId,Integer type) {
		return issueReplyMapper.queryReply(issueId,type);
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(IssueReply record,
			ProjectComplaint projectComplaint) {
		projectComplaintMapper.updateByPrimaryKeySelective(projectComplaint);
		return issueReplyMapper.updateByPrimaryKeySelective(record);
	}

}
