package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.DeliveryDateLog;

public interface DeliveryDateLogService {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryDateLog record);

    int insertSelective(DeliveryDateLog record);

    DeliveryDateLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliveryDateLog record);

    int updateByPrimaryKey(DeliveryDateLog record);
    
    
    /**
     * 根据项目号和类型查询
     * @Title selectCount 
     * @Description
     * @param projectNo
     * @param sampleProduction
     * @return
     * @return int
     */
    int selectCount(@Param("projectNo")String projectNo,@Param("sampleProduction")Integer sampleProduction);
    
    
    
    /**
     * 根据项目号查询
     * @Title selectDeliveryList 
     * @Description
     * @param projectNo
     * @return
     * @return List<DeliveryDateLog>
     */
    List<DeliveryDateLog> selectDeliveryList(@Param("projectNo")String projectNo);
}