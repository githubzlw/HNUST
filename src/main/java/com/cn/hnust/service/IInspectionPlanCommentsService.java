package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.InspectionPlanComments;

public interface IInspectionPlanCommentsService {
/**
 * 
 * @Title:IInspectionPlanCommentsService
 * @Description:获取检验计划评价
   @author wangyang
 * @param processInstanceId
 * @return List<InspectionPlanComments>
 * @throws
 */
	List<InspectionPlanComments> getAll(String processInstanceId);

}
