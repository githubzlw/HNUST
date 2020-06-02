package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.TaskReport;

public interface TaskReportMapper {
	
    int deleteByPrimaryKey(String taskId);

    int insert(TaskReport record);

    int insertSelective(TaskReport record);

    TaskReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskReport record);

    int updateByPrimaryKey(TaskReport record);
    
    List<TaskReport> selectTaskReportByNo(@Param("taskId")String taskId);

	List<TaskReport> selectTaskReportById(String taskId);
}