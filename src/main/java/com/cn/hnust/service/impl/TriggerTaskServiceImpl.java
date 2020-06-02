package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.TriggerTaskMapper;
import com.cn.hnust.pojo.TriggerTask;
import com.cn.hnust.service.ITriggerTaskService;

@Service
public class TriggerTaskServiceImpl implements ITriggerTaskService {

	@Resource
	private TriggerTaskMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TriggerTask record) {
	
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TriggerTask record) {
		return mapper.insertSelective(record);
	}

	@Override
	public TriggerTask selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id) ;
	}

	@Override
	public int updateByPrimaryKeySelective(TriggerTask record) {

		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TriggerTask record) {
		
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TriggerTask> selectList(int start, int pageSize) {

		return mapper.selectList(start, pageSize);
	}

	@Override
	public int selectListCount() {
		return mapper.selectListCount();
	}

	@Override
	public List<TriggerTask> checkHasKey(String taskTitle, int roleType) {
		return mapper.checkHasKey(taskTitle, roleType);
	}

	@Override
	public List<TriggerTask> selectAllList() {
		return mapper.selectAllList();
	}
	
	

}
