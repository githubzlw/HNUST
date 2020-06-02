package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.cn.hnust.daoErp.ComplaintListMapper;
import com.cn.hnust.pojo.ComplaintList;
import com.cn.hnust.service.ComplaintListService;
@Service
public class ComplaintListServiceImpl implements ComplaintListService{
	@Autowired
	private ComplaintListMapper complaintListMapper;

	@Override
	public ComplaintList getLate() {
		
		return complaintListMapper.getLate();
	}

	@Override
	public void batchAddInspectionReport(List<ComplaintList> list1) {
		
		complaintListMapper.batchAddInspectionReport(list1);
	}
}
