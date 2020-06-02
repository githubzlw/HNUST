package com.cn.hnust.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;







import org.springframework.stereotype.Service;

import com.cn.hnust.daoQuotation.QuotationUserMapper;
import com.cn.hnust.pojo.QuotationUser;
import com.cn.hnust.service.IQuotationUserService;
@Service
public class QuotationUserServiceImpl implements IQuotationUserService{
	@Resource
	private QuotationUserMapper quotationUserMapper;

	@Override
	public List<QuotationUser> getQuantityQuoted() {
		
		return quotationUserMapper.getQuantityQuoted();
	}

	@Override
	public List<QuotationUser> getSalesWorkload(int timeCreening) {
		return quotationUserMapper.getSalesWorkload(timeCreening);
	}

	
}
