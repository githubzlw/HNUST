package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.ProductionPlan;

public interface ProductionPlanMapper {
	
    int deleteByPrimaryKey(Integer id);
    
    int deleteByProjectNo(String projectNo);

    int insert(ProductionPlan record);

    int insertSelective(ProductionPlan record);

    ProductionPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductionPlan record);

    int updateByPrimaryKey(ProductionPlan record);
    /**
     * 根据项目号查询生产计划
     * @param projectNo
     * @return
     */
    public List<ProductionPlan> selectProductionPlan(String projectNo);
    
}