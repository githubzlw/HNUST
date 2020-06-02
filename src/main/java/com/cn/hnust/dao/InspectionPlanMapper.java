package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.InspectionPlan;

public interface InspectionPlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InspectionPlan record);

    int insertSelective(InspectionPlan record);

    InspectionPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionPlan record);

    int updateByPrimaryKey(InspectionPlan record);
    
    
    /**
     * 批量插入
     * @Title insertBatch 
     * @Description
     * @param list
     * @return
     * @return int
     */
    int insertBatch(List<InspectionPlan> list);
    
    
    /**
     * 批量更新
     * @Title updateBatch 
     * @Description
     * @param list
     * @return
     * @return int
     */
    int updateBatch(List<InspectionPlan> list);
    
    
    /**
     * 根据项目号查询
     * @Title selectByProjectNo 
     * @Description 
     * @param projectNo
     * @return
     * @return List<InspectionPlan>
     */
    List<InspectionPlan> selectByProjectNo(@Param("projectNo")String projectNo,@Param("inspectionCreateDate")String inspectionCreateDate);
}