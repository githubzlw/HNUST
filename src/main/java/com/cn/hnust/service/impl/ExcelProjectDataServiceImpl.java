package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ExcelProjectDataMapper;
import com.cn.hnust.pojo.ExcelProjectData;
import com.cn.hnust.service.IExcelProjectDataService;

@Service
public class ExcelProjectDataServiceImpl implements IExcelProjectDataService{
    
	@Autowired
	private ExcelProjectDataMapper excelProjectDataMapper;
	@Override
	public List<ExcelProjectData> findAllExcelProjectData() {
		return excelProjectDataMapper.findAllExcelProjectData();
	}

}
