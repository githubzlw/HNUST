package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.ProjectERP;

public interface IProjectERPService {
 
	public void addProjectERP(ProjectERP projectERP);
	
	public List<ProjectERP> selectAllProjectERP();
	
	public ProjectERP selectProjectErpByNo(String projectNo);
	
	public void updateProjectErp(ProjectERP projectErp);
	
	List<ProjectERP> selectCondition();
	
	List<ProjectERP> selectConditionFirst();
	
	List<ProjectERP> selectMeetingStatus(int conditionType,String meetingName);
	
	/**
	 * 跟单销售发出质量投诉时，触发 给采购 和 质检的解释问题和解决问题的任务
	 */
	List<ProjectERP> selectProjectQualityComplaint();
    /**
     * 项目原定交期 超期一周后 ，给采购布置一个解释情况的任务
     * @return
     */
	public List<ProjectERP> projectDeliveryDateDelay();
}
