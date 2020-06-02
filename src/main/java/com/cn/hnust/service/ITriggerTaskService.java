package com.cn.hnust.service;

import java.util.List;


import com.cn.hnust.pojo.TriggerTask;

public interface ITriggerTaskService {
	
	int deleteByPrimaryKey(Integer id);

    int insert(TriggerTask record);

    int insertSelective(TriggerTask record);

    TriggerTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TriggerTask record);

    int updateByPrimaryKey(TriggerTask record);
    
    List<TriggerTask> selectList(int start,int pageSize);
    
    List<TriggerTask> selectAllList();
    
    List<TriggerTask> checkHasKey(String taskTitle,int roleType);
    
    
    int selectListCount();

}
