package com.cn.hnust.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.StatusEnterMapper;
import com.cn.hnust.pojo.StatusEnter;
import com.cn.hnust.service.IStatusEnterService;

@Service
public class StatusEnterServiceImpl implements IStatusEnterService {
    
	@Autowired
	private StatusEnterMapper statusEnterMapper;
	/**
	 * 添加状态信息
	 */
	@Override
	public void addStatusEnter(StatusEnter statusEnter) {
		statusEnterMapper.insertSelective(statusEnter);
	}
	@Override
	public List<StatusEnter> selectProjectStatusEnter(String projectNo) {
		return statusEnterMapper.selectProjectStatusEnter(projectNo);
	}
	@Override
	public void updateQualityComplaintFlagByProjectNo(Set<String> proSet, String feekBackFlag) {
		 statusEnterMapper.updateQualityComplaintFlagByProjectNo(proSet,feekBackFlag);
	}

}
