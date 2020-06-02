package com.cn.hnust.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.daoErp.InvoiceInfoMapper;
import com.cn.hnust.pojo.InvoiceInfo;
import com.cn.hnust.service.InvoiceInfoService;
@Service
public class InvoiceInfoServiceImpl implements InvoiceInfoService {

    @Autowired
    private InvoiceInfoMapper invoiceInfoMapper;

	
	public List<InvoiceInfo> selectFirstDate(List<String> item) {
		return invoiceInfoMapper.selectFirstDate(item);
	}


	@Override
	public String getAllMoney(String userName, String start) {
		
		return invoiceInfoMapper.getAllMoney(userName,start);
	}


	@Override
	public InvoiceInfo getAll(String userName, String start) {
		
		return invoiceInfoMapper.getAll(userName,start);
	}

	
	@Override
	public InvoiceInfo selectCountByStatus(InvoiceInfo info) {
		return invoiceInfoMapper.selectCountByStatus(info);
	}



}
