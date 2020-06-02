package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.InspectionPlanInfo;

public interface InspectionPlanInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InspectionPlanInfo record);

    int insertSelective(InspectionPlanInfo record);

    InspectionPlanInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionPlanInfo record);

    int updateByPrimaryKey(InspectionPlanInfo record);

	List<InspectionPlanInfo> selectInspectionPlan(InspectionPlanInfo planInfo);

	int selectInspectionPlanCount(InspectionPlanInfo planInfo);

	List<InspectionPlanInfo> selectInspectionPlanTask(@Param("projectTaskId")Integer projectTaskId);

	List<InspectionPlanInfo> selectInspectionPlanTaskNoQuality();
}