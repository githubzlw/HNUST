package com.cn.hnust.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.DeductionFormMapper;
import com.cn.hnust.pojo.DeductionForm;
import com.cn.hnust.service.IDeductionFormService;

@Service
public class DeductionFormServiceImpl implements IDeductionFormService{
	@Autowired
	private DeductionFormMapper deductionFormMapper;

	@Override
	public DeductionForm getOne(String userName, String start) {
		
		return deductionFormMapper.getOne(userName,start);
	}

	@Override
	public int insertSelective(DeductionForm deductionForm) {
		
		return deductionFormMapper.insertSelective(deductionForm);
	}

	@Override
	public int update(DeductionForm deductionForm) {
		
		return deductionFormMapper.update(deductionForm);
	}
}
