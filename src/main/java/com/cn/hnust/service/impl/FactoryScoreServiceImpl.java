package com.cn.hnust.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.FactoryScoreMapper;
import com.cn.hnust.pojo.FactoryScore;
import com.cn.hnust.service.FactoryScoreService;

@Service
public class FactoryScoreServiceImpl implements FactoryScoreService {

	@Autowired
	private FactoryScoreMapper factoryScoreMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return factoryScoreMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(FactoryScore record) {	
		return factoryScoreMapper.insertSelective(record);
	}

	@Override
	public FactoryScore selectByPrimaryKey(Integer id) {
		return factoryScoreMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(FactoryScore record) {
		return factoryScoreMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertBatch(List<FactoryScore> list) {
		return factoryScoreMapper.insertBatch(list);
	}

	@Override
	public int selectByProjectNo(String projectNo) {
		return factoryScoreMapper.selectByProjectNo(projectNo);
	}

	@Override
	public Map<String,Object> selectAvgScore(String factoryName,Integer queryDate) {
		return factoryScoreMapper.selectAvgScore(factoryName,queryDate);
	}

}
