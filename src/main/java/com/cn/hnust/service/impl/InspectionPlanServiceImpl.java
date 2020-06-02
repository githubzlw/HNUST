package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.InspectionPlanMapper;
import com.cn.hnust.pojo.InspectionPlan;
import com.cn.hnust.service.InspectionPlanService;


@Service
public class InspectionPlanServiceImpl implements InspectionPlanService {

	@Autowired
	private InspectionPlanMapper inspectionPlanMapper;
	
	@Override
	public int updateBatch(List<InspectionPlan> list) {		
		return inspectionPlanMapper.updateBatch(list);
	}

	@Override
	public List<InspectionPlan> selectByProjectNo(String projectNo,String inspectionCreateDate) {
		return inspectionPlanMapper.selectByProjectNo(projectNo,inspectionCreateDate);
	}

}
