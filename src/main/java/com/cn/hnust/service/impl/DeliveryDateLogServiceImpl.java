package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.DeliveryDateLogMapper;
import com.cn.hnust.pojo.DeliveryDateLog;
import com.cn.hnust.service.DeliveryDateLogService;

@Service
public class DeliveryDateLogServiceImpl implements DeliveryDateLogService {

	@Autowired
	private DeliveryDateLogMapper deliveryDateLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return deliveryDateLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DeliveryDateLog record) {
		return deliveryDateLogMapper.insert(record);
	}

	@Override
	public int insertSelective(DeliveryDateLog record) {
		return deliveryDateLogMapper.insertSelective(record);
	}

	@Override
	public DeliveryDateLog selectByPrimaryKey(Integer id) {
		return deliveryDateLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DeliveryDateLog record) {
		return deliveryDateLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DeliveryDateLog record) {
		return deliveryDateLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public int selectCount(String projectNo, Integer sampleProduction) {
		return deliveryDateLogMapper.selectCount(projectNo, sampleProduction);
	}

	@Override
	public List<DeliveryDateLog> selectDeliveryList(String projectNo) {
		return deliveryDateLogMapper.selectDeliveryList(projectNo);
	}

}
