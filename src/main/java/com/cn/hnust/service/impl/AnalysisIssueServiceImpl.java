package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.AnalysisIssueMapper;
import com.cn.hnust.pojo.AnalysisIssue;
import com.cn.hnust.service.AnalysisIssueService;

@Service
public class AnalysisIssueServiceImpl implements AnalysisIssueService {

	@Autowired
	private AnalysisIssueMapper analysisIssueMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {		
		return analysisIssueMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AnalysisIssue record) {
		return analysisIssueMapper.insert(record);
	}

	@Override
	public int insertSelective(AnalysisIssue record) {
		return analysisIssueMapper.insertSelective(record);
	}

	@Override
	public AnalysisIssue selectByPrimaryKey(Integer id) {
		return analysisIssueMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AnalysisIssue record) {
		return analysisIssueMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AnalysisIssue record) {
		return analysisIssueMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<AnalysisIssue> selectByProjectNo(String projectNo,Integer isComplaint) {
		return analysisIssueMapper.selectByProjectNo(projectNo,isComplaint);
	}

	@Override
	public List<AnalysisIssue> selectTop10(String issue) {
		return analysisIssueMapper.selectTop10(issue);
	}

	@Override
	public int insertBatch(List<AnalysisIssue> list) {
		return analysisIssueMapper.insertBatch(list);
	}

	@Override
	public List<AnalysisIssue> selectComplaintIssue() {
		return analysisIssueMapper.selectComplaintIssue();
	}

	@Override
	public List<AnalysisIssue> selectByProcess(String process) {
		return analysisIssueMapper.selectByProcess(process);
	}

	@Override
	public List<AnalysisIssue> selectByProcessAndIssue(String process,
			String issue) {
		return analysisIssueMapper.selectByProcessAndIssue(process, issue);
	}

	@Override
	public List<AnalysisIssue> selectByComplaintId(Integer complaintId) {
		return analysisIssueMapper.selectByComplaintId(complaintId);
	}

}
