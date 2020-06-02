package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProjectERP;


public interface ProjectERPMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProjectERP record);

    int insertSelective(ProjectERP record);

    ProjectERP selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProjectERP record);

    int updateByPrimaryKey(ProjectERP record);
    
    List<ProjectERP> selectAllProjectERP();

	ProjectERP selectProjectErpByNo(String projectNo);
	
	List<ProjectERP> selectCondition();
	
	List<ProjectERP> selectConditionFirst();
	
    List<ProjectERP> selectMeetingStatus(@Param("conditionType")int conditionType,@Param("meetingName")String meetingName);
    //跟单销售发出质量投诉时，触发 给采购 和 质检的解释问题和解决问题的任务
	List<ProjectERP> selectProjectQualityComplaint();
    /**
     * 项目原定交期 超期一周后 ，给采购布置一个解释情况的任务
     * @return
     */
	List<ProjectERP> projectDeliveryDateDelay();
}