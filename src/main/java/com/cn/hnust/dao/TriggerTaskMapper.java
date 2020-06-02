package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.TriggerTask;

public interface TriggerTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TriggerTask record);

    int insertSelective(TriggerTask record);

    TriggerTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TriggerTask record);

    int updateByPrimaryKey(TriggerTask record);
    
    List<TriggerTask> selectList(@Param("start")int start,@Param("pageSize")int pageSize);
    
    List<TriggerTask>  selectAllList();
    
    List<TriggerTask> checkHasKey(@Param("taskTitle")String taskTitle,@Param("roleType")int roleType);
    
    int selectListCount();
    
    
}