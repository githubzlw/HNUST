package com.cn.hnust.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.TableGenerator;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.AnalysisIssueMapper;
import com.cn.hnust.dao.ComplaintIssueMapper;
import com.cn.hnust.dao.IssueReplyMapper;
import com.cn.hnust.dao.ProjectComplaintMapper;
import com.cn.hnust.dao.ShippingConfirmationMapper;
import com.cn.hnust.pojo.AnalysisIssue;
import com.cn.hnust.pojo.ComplaintIssue;
import com.cn.hnust.pojo.IssueReply;
import com.cn.hnust.pojo.ProjectComplaint;
import com.cn.hnust.pojo.ProjectComplaintQuery;
import com.cn.hnust.pojo.ShippingConfirmation;
import com.cn.hnust.service.ProjectComplaintService;


@Service
public class ProjectComplaintServiceImpl implements ProjectComplaintService {

	@Autowired
	private ProjectComplaintMapper projectComplaintMapper;
	@Autowired
	private ComplaintIssueMapper complaintIssueMapper;
	@Autowired
	private IssueReplyMapper issueReplyMapper;
	@Autowired
	private ShippingConfirmationMapper shippingConfirmationMapper;
	@Autowired
	private AnalysisIssueMapper analysisIssueMapper;
	
	private static final int TYPE = 1;   //投诉出货单
	
	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		complaintIssueMapper.deleteByComplaintId(id);
		return projectComplaintMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProjectComplaint record) {
		return projectComplaintMapper.insert(record);
	}

	@Override
	public int insertSelective(ProjectComplaint record) {
		return projectComplaintMapper.insertSelective(record);
	}

	@Override
	public ProjectComplaint selectByPrimaryKey(Integer id) {
		return projectComplaintMapper.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(ProjectComplaint record) {
		
		//自动生成电子出货单功能移除   2018.12.18  by polo
/*		if(record!=null && record.getCompleteTime()!=null){
			//当已完成时，自动保存电子出货单
			int count = shippingConfirmationMapper.selectByComplaintId(record.getId());
			if(count == 0){
				ShippingConfirmation shippingConfirmation = new ShippingConfirmation();
				shippingConfirmation.setProjectNo(record.getProjectNo());
				int sCount = shippingConfirmationMapper.selectCountByProjectNo(record.getProjectNo(),TYPE);
				count++;
				String serialNumber = record.getProjectNo() + "TSQR" + sCount;
				shippingConfirmation.setSerialNumber(serialNumber);
				shippingConfirmation.setCreateTime(new Date());
				shippingConfirmationMapper.insertSelective(shippingConfirmation);
			}
		}*/
		
		return projectComplaintMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(ProjectComplaint record) {
		return projectComplaintMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(ProjectComplaint record) {
		return projectComplaintMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ProjectComplaint> queryComplaintList(
			ProjectComplaintQuery projectComplaintQuery) {
		return projectComplaintMapper.queryComplaintList(projectComplaintQuery);
	}

	@Override
	public int queryCount(ProjectComplaintQuery projectComplaintQuery) {
		return projectComplaintMapper.queryCount(projectComplaintQuery);
	}

	@Override
	public List<ProjectComplaint> selectByProjectNo(String projectNo) {
		return projectComplaintMapper.selectByProjectNo(projectNo);
	}

	@Override
	public int countUnFinished(String projectNo) {
		return projectComplaintMapper.countUnFinished(projectNo);
	}

	@Transactional
	@Override
	public int insertSelective(ProjectComplaint record,List<ComplaintIssue> complaintIssues) {
		int count = projectComplaintMapper.insertSelective(record);
		if(complaintIssues !=null){
			for (ComplaintIssue complaintIssue : complaintIssues) {
				complaintIssue.setComplaintId(record.getId());
			}
			complaintIssueMapper.insertBatch(complaintIssues);
		}
		return count;
	}

	
	@Transactional
	@Override
	public int updateAndInsertReply(ProjectComplaint record,List<IssueReply> replyList) {
		int count=0;
		projectComplaintMapper.updateByPrimaryKeySelective(record);
		if(replyList!=null&&replyList.size()>0){
			count = issueReplyMapper.insertBatch(replyList);
		}
		return count;
	}

	@Transactional
	@Override
	public int insertSelective(ProjectComplaint record,List<ComplaintIssue> complaintIssues, List<AnalysisIssue> issueList) {
		int count = projectComplaintMapper.insertSelective(record);
		if(complaintIssues !=null){
			for (ComplaintIssue complaintIssue : complaintIssues) {
				complaintIssue.setComplaintId(record.getId());
			}
			complaintIssueMapper.insertBatch(complaintIssues);
		}
		if(issueList!=null&&issueList.size()>0){
			for (AnalysisIssue analysisIssue : issueList) {
				analysisIssue.setComplaintId(record.getId());
			}
			analysisIssueMapper.updateBatch(issueList);
		}
		return count;
	}

	@Override
	public String getComplaintsNumber(String allProjectNo) {		
		return projectComplaintMapper.getComplaintsNumber(allProjectNo);
	}

	@Override
	public List<ProjectComplaint> selectByProjectNoDim(String projectNo) {
		return projectComplaintMapper.selectByProjectNoDim(projectNo);
	}

	@Override
	public int getQueryComplaintCount(
			ProjectComplaintQuery projectComplaintQuery) {
		
		return projectComplaintMapper.getQueryComplaintCount(projectComplaintQuery);
	}

	@Override
	public List<ProjectComplaint> unprocessedItems() {
		
		return projectComplaintMapper.unprocessedItems();
	}

	@Override
	public ProjectComplaint getLate(String projectNo) {
		
		return projectComplaintMapper.getLate(projectNo);
	}

	@Override
	public List<ProjectComplaint> getAll(ProjectComplaint complaint) {
		
		return projectComplaintMapper.getAll(complaint);
	}

	
	
}
