package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.InspectionPlan;

public interface InspectionPlanService {
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
    List<InspectionPlan> selectByProjectNo(String projectNo,String inspectionCreateDate);
}
