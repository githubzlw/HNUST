package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.InspectionPlanInfo;

public interface IInspectionPlanInfoService {

	List<InspectionPlanInfo> selectInspectionPlan(InspectionPlanInfo planInfo);

	int selectInspectionPlanCount(InspectionPlanInfo planInfo);
	
	void addInspectionPlan(InspectionPlanInfo planInfo);
	
	List<InspectionPlanInfo> selectInspectionPlanTask(Integer projectTaskId);
	
	List<InspectionPlanInfo> selectInspectionPlanTaskNoQuality();
	
	void updateInspectionPlanInfo(InspectionPlanInfo planInfo);

}
