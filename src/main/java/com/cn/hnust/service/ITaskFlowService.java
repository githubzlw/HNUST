package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.TaskFlow;



public interface ITaskFlowService {
	
	     void deleteByPrimaryKey(Integer id);

	    int insert(TaskFlow record);

	    int insertSelective(TaskFlow record);
	    
	    void insertAll(TaskFlow record);
	    
	    void updateAll(TaskFlow record);

	    TaskFlow selectByPrimaryKey(Integer id);
	    
	    TaskFlow selectAllDetail(Integer id);

	    int updateByPrimaryKeySelective(TaskFlow record);

	    int updateByPrimaryKey(TaskFlow record);
        
	    List<TaskFlow> selectList(int start,int pageSize);
	    
	    List<TaskFlow> selectAllList();
	    
	    int selectListCount();
	    
	    List<TaskFlow> checkHasKey(int conditionType,int triggerId);
	    
	    
	    
	    

	
	 
}
