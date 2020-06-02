package com.cn.hnust.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProductionPlan;

public interface IProductionPlanService {
	/***
	 * 根据项目号查询项目的生产计划
	 * @param projectNo
	 * @return
	 */
	public List<ProductionPlan> selectProductionPlan(String projectNo);
	/**
	 * 添加项目的工作计划
	 * @param productionPlan
	 */
	public void addProductionPlan(ProductionPlan productionPlan);
	
	/*
	 * 添加项目工作计划
	 * @param List<ProductionPlan>
	 */
	public void addProductionPlanList (List<ProductionPlan> list);
	
	
	
	/**
	 * 查询erp的生成计划
	 * @Title selectErpReportByProjectNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<QualityReport>
	 */
	List<ProductionPlan> selectByProjectNo(String projectNo);
	
	
	
	/**
	 * 查询erp的生产计划
	 * @Title selectErpReportByProjectNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<QualityReport>
	 */
	ProductionPlan selectDemandByProjectNo(String projectNo);
	/**
	 * 
	 * @Title:IProductionPlanService
	 * @Description:查看检验计划是否已上传
	   @author wangyang
	 * @param startTime
	 * @param projectNo
	 * @return int
	 * @throws
	 */
	public int getInspectionPlan(Date startTime, java.lang.String projectNo);
}
