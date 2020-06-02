package com.cn.hnust.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.daoErp.FactoryFundMapper;
import com.cn.hnust.pojo.FactoryFund;
import com.cn.hnust.service.FactoryFundService;

@Service
public class FactoryFundServiceImpl implements FactoryFundService{

	@Autowired
	private FactoryFundMapper factoryFundMapper;

	@Override
	public void insertAll(FactoryFund fac) {
		
		factoryFundMapper.insertAll(fac);
	}

	
}
