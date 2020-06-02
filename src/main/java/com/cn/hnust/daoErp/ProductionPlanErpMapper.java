package com.cn.hnust.daoErp;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProductionPlan;


public interface ProductionPlanErpMapper {
  
	/**
	 * 查询erp的生产计划
	 * @Title selectErpReportByProjectNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<QualityReport>
	 */
	List<ProductionPlan> selectByProjectNo(@Param("projectNo")String projectNo);
	
	
	
	/**
	 * 查询erp的生产计划
	 * @Title selectErpReportByProjectNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<QualityReport>
	 */
	ProductionPlan selectDemandByProjectNo(@Param("projectNo")String projectNo);
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


	int getInspectionPlan(@Param("createDate")Date startTime, @Param("projectNo")String projectNo);
    
}