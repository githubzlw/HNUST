package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.PayOthersMapper;
import com.cn.hnust.pojo.PayOthers;
import com.cn.hnust.service.PayOtherService;
@Service
public class PayOtherServiceImpl implements PayOtherService{
	@Autowired
	private PayOthersMapper payOtherMapper;

	@Override
	public List<PayOthers> getAllPending() {
		
		return payOtherMapper.getAllPending();
	}

	@Override
	public void updatePassERP(int id, int num) {
		payOtherMapper.updatePassERP(id,num);
		
	}
}
