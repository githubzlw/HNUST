package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.InspectionPlanCommentsMapper;
import com.cn.hnust.pojo.InspectionPlanComments;
import com.cn.hnust.service.IInspectionPlanCommentsService;
@Service
public class InspectionPlanCommentsServiceImpl implements IInspectionPlanCommentsService{
	@Autowired
	private InspectionPlanCommentsMapper inspectionPlanCommentsMapper;
	@Override
	public List<InspectionPlanComments> getAll(String processInstanceId) {
		
		return inspectionPlanCommentsMapper.getAll(processInstanceId);
	}

}
