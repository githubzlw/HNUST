package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ComplaintIssueMapper;
import com.cn.hnust.pojo.ComplaintIssue;
import com.cn.hnust.service.ComplaintIssueService;
@Service
public class ComplaintIssueServiceImpl implements ComplaintIssueService {

	@Autowired
	private ComplaintIssueMapper complaintIssueMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return complaintIssueMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ComplaintIssue record) {
		return complaintIssueMapper.insert(record);
	}

	@Override
	public int insertSelective(ComplaintIssue record) {
		return complaintIssueMapper.insertSelective(record);
	}

	@Override
	public ComplaintIssue selectByPrimaryKey(Integer id) {
		return complaintIssueMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ComplaintIssue record) {
		return complaintIssueMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ComplaintIssue record) {
		return complaintIssueMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ComplaintIssue> selectByComplaintId(Integer complaintId) {
		return complaintIssueMapper.selectByComplaintId(complaintId);
	}

	@Override
	public int insertBatch(List<ComplaintIssue> list) {
		return complaintIssueMapper.insertBatch(list);
	}

	@Override
	public List<ComplaintIssue> selectByComplaintId1(int complaintId) {
		
		return complaintIssueMapper.selectByComplaintId1(complaintId);
	}

}
