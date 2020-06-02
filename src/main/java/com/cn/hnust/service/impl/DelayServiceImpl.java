package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.DelayMapper;
import com.cn.hnust.pojo.Delay;
import com.cn.hnust.service.IDelayService;

@Service
public class DelayServiceImpl implements IDelayService{
   
	
	@Autowired
	private DelayMapper delayMapper;
	/**
	 * 添加项目延期信息
	 */
	@Override
	public void insertDelay(Delay delay) {
		delayMapper.insertSelective(delay);
	}
	/**
	 * 查询项目延期信息
	 */
	@Override
	public List<Delay> selectDelayByProjectNo(String projectNo) {
		return delayMapper.selectDelayByProjectNo(projectNo);
	}
	@Override
	public Delay selectMaxDelayByProjectNo(String projectNo) {
		return delayMapper.selectMaxDelayByProjectNo(projectNo);
	}
	@Override
	public Delay selectApplyDelayByProjectNo(String projectNo) {
		return delayMapper.selectApplyDelayByProjectNo(projectNo);
	}
	@Override
	public void updateDelayFlagByProjectNo(Delay delay) {
		 delayMapper.updateByPrimaryKeySelective(delay);
	}
	@Override
	public int insertBatch(List<Delay> item) {
		return delayMapper.insertBatch(item);
	}
	@Override
	public int delayCount(String projectNo, int num) {		
		return delayMapper.delayCount(projectNo, num);
	}
	@Override
	public List<Delay> selectAllByProjectNo(String projectNo) {
		return delayMapper.selectAllByProjectNo(projectNo);
	}
	@Override
	public int updateBatch(List<Delay> item) {
		return delayMapper.updateBatch(item);
	}

}
