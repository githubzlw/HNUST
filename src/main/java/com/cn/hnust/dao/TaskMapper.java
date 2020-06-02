package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Task;

public interface TaskMapper {
    int deleteByPrimaryKey(String id);

    int insert(Task record);

    int insertSelective(Task record);
    /**
     * 销售
     * @param id
     * @return
     */
    Task selectByPrimaryKey(String id);
    /**
     * 采购
     * @param record
     * @return
     */
    Task selectTaskPurchaseById(String id);
    
    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
    /**
     * 根据项目号查询任务信息(销售)
     * @param task
     * @return
     */
    List<Task> selectTaskByProjectNo(Task task);
    /**
     * 根据项目号查询任务信息(采购)
     * @param task
     * @return
     */
    List<Task> selectTaskPurchaseByProjectNo(Task task);
    /**
     * 查询所有的任务
     * @return
     */
    List<Task> selectAllTask(Task task);
    /**
     * 查询任务列表
     * @param task
     * @return
     */
    List<Task> selectTaskList(Task task);
    /**
     * 根据项目号查询(项目信息)
     * @param projectNo
     * @return
     */
	List<Task> findTaskByProjectNo(String projectNo);
}