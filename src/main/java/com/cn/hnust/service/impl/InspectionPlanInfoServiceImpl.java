package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.InspectionPlanInfoMapper;
import com.cn.hnust.pojo.InspectionPlanInfo;
import com.cn.hnust.service.IInspectionPlanInfoService;

@Service
public class InspectionPlanInfoServiceImpl implements IInspectionPlanInfoService{
    
	@Autowired
	private InspectionPlanInfoMapper inspectionPlanInfoMapper;
	@Override
	public List<InspectionPlanInfo> selectInspectionPlan(InspectionPlanInfo planInfo) {
		return inspectionPlanInfoMapper.selectInspectionPlan(planInfo);
	}

	@Override
	public int selectInspectionPlanCount(InspectionPlanInfo planInfo) {
		return inspectionPlanInfoMapper.selectInspectionPlanCount(planInfo);
	}

	@Override
	public void addInspectionPlan(InspectionPlanInfo planInfo) {
		inspectionPlanInfoMapper.insertSelective(planInfo);
	}

	@Override
	public List<InspectionPlanInfo> selectInspectionPlanTask(Integer projectTaskId) {
		return inspectionPlanInfoMapper.selectInspectionPlanTask(projectTaskId);
	}

	@Override
	public List<InspectionPlanInfo> selectInspectionPlanTaskNoQuality() {
		return inspectionPlanInfoMapper.selectInspectionPlanTaskNoQuality();
	}

	@Override
	public void updateInspectionPlanInfo(InspectionPlanInfo planInfo) {
		inspectionPlanInfoMapper.updateByPrimaryKeySelective(planInfo);
	}

}
