package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ProjectERPMapper;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.service.IProjectERPService;
@Service
public class ProjectERPServiceImpl implements IProjectERPService {
    
	@Autowired
	private ProjectERPMapper projectERPMapper;
	@Override
	public void addProjectERP(ProjectERP projectERP) {
		projectERPMapper.insertSelective(projectERP);
	}
	@Override
	public List<ProjectERP> selectAllProjectERP() {
		return projectERPMapper.selectAllProjectERP();
	}
	@Override
	public ProjectERP selectProjectErpByNo(String projectNo) {
		return projectERPMapper.selectProjectErpByNo(projectNo);
	}
	@Override
	public void updateProjectErp(ProjectERP projectErp) {
		projectERPMapper.updateByPrimaryKeySelective(projectErp);
	}
	@Override
	public List<ProjectERP> selectCondition() {
		
		return projectERPMapper.selectCondition();
	}
	@Override
	public List<ProjectERP> selectMeetingStatus(int conditionType,
			String meetingName) {
		return projectERPMapper.selectMeetingStatus(conditionType, meetingName);
	}
	@Override
	public List<ProjectERP> selectConditionFirst() {
		return projectERPMapper.selectConditionFirst();
	}
	/**
	 * 跟单销售发出质量投诉时，触发 给采购 和 质检的解释问题和解决问题的任务
	 */
	@Override
	public List<ProjectERP> selectProjectQualityComplaint() {
		return projectERPMapper.selectProjectQualityComplaint();
	}
	/**
	 * 项目原定交期 超期一周后 ，给采购布置一个解释情况的任务
	 */
	@Override
	public List<ProjectERP> projectDeliveryDateDelay() {
		return projectERPMapper.projectDeliveryDateDelay();
	}

}
