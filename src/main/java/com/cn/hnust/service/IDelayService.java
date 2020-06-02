package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Delay;

/***
 * 项目交期延误 Service
 * @author Administrator
 *
 */
public interface IDelayService {
    /**
     * 插入项目交期延误的信息
     * @param delay
     */
	public void insertDelay(Delay delay);
	
    /**
     * 查询项目延期信息(全部)
     * @param projectNo
     * @return
     */
    List<Delay> selectAllByProjectNo(String projectNo);
	
	/**
	 * 根据项目号查询延期信息
	 * @return
	 */
	public List<Delay> selectDelayByProjectNo(String projectNo);
	/**
	 * 根据项目号查询最大的延期时间
	 * @return
	 */
	public Delay selectMaxDelayByProjectNo(String projectNo);
	
	public Delay selectApplyDelayByProjectNo(String projectNo);
	
	public void updateDelayFlagByProjectNo(Delay delay);
	
	
	/**
	 * 批量插入延期数据
	 */
	int insertBatch(List<Delay> item);
	
	
	/**
	 * 查询延期次数
	 * @Title delayCount 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return int
	 */
	int delayCount(String projectNo,int num);
	
	
	 /**
     * 批量延期数据
     * @Title updateBatch 
     * @Description
     * @param records
     * @return
     * @return int
     */
    int updateBatch(List<Delay> item);
}
