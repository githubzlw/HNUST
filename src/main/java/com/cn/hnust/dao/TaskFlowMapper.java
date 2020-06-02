package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.TaskFlow;

public interface TaskFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskFlow record);

    int insertSelective(TaskFlow record);
    
    int insertBackId(TaskFlow record);

    TaskFlow selectByPrimaryKey(Integer id);
    
    TaskFlow selectAllDetail(Integer id);

    int updateByPrimaryKeySelective(TaskFlow record);

    int updateByPrimaryKey(TaskFlow record);
    
    List<TaskFlow> selectList(@Param("start")int start,@Param("pageSize")int pageSize);
    
    List<TaskFlow> selectAllList();
    
    int selectListCount();
    
    List<TaskFlow> checkHasKey(@Param("conditionType")int conditionType,@Param("triggerId")int triggerId);
    

    
}