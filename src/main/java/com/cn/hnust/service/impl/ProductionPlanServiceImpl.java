package com.cn.hnust.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ProductionPlanMapper;
import com.cn.hnust.daoErp.ProductionPlanErpMapper;
import com.cn.hnust.pojo.ProductionPlan;
import com.cn.hnust.service.IProductionPlanService;
@Service
public class ProductionPlanServiceImpl implements IProductionPlanService {
    
	@Autowired
	private ProductionPlanMapper productionPlanMapper;
	@Autowired
	private ProductionPlanErpMapper productionPlanErpMapper;
	/**
	 * 根据项目号查询项目的生产计划
	 */
	@Override
	public List<ProductionPlan> selectProductionPlan(String projectNo) {
		return productionPlanMapper.selectProductionPlan(projectNo);
	}
	@Override
	public void addProductionPlan(ProductionPlan productionPlan) {
		productionPlanMapper.insertSelective(productionPlan);
	}
	
	@Transactional
	@Override
	public void addProductionPlanList(List<ProductionPlan> list) {
	   if(list!=null && list.size()>0){
		   
			ProductionPlan  pp = list.get(0);
			if(pp!=null && StringUtils.isNotBlank(pp.getProjectNo())){
				productionPlanMapper.deleteByProjectNo(pp.getProjectNo());	
			}
			for(int i=0;i<list.size();i++){
				ProductionPlan pps = list.get(i);
				productionPlanMapper.insertSelective(pps);
			}
		}
	}
	@Override
	public List<ProductionPlan> selectByProjectNo(String projectNo) {
		return productionPlanErpMapper.selectByProjectNo(projectNo);
	}
	@Override
	public ProductionPlan selectDemandByProjectNo(String projectNo) {
		return productionPlanErpMapper.selectDemandByProjectNo(projectNo);
	}
	@Override
	public int getInspectionPlan(Date startTime, String projectNo) {
		
		return productionPlanErpMapper.getInspectionPlan(startTime,projectNo);
	}
	
	
	

}
