package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.TaskFlowDetail;

public interface TaskFlowDetailMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByParentId(Integer parentId);

    int insert(TaskFlowDetail record);

    int insertSelective(TaskFlowDetail record);
    
    int insertList(List<TaskFlowDetail> list);

    TaskFlowDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskFlowDetail record);

    int updateByPrimaryKey(TaskFlowDetail record);
    
    List<TaskFlowDetail> selectByParentId(Integer parentId);
    
}