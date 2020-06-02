package com.cn.hnust.service.impl;


import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;

import com.cn.hnust.dao.QualityAnalysisTableMapper;
import com.cn.hnust.pojo.QualityAnalysisTable;
import com.cn.hnust.service.IQualityAnalysisTableService;

@Service
public class QualityAnalysisTableService implements IQualityAnalysisTableService{
	@Autowired
	private QualityAnalysisTableMapper qualityAnalysisTableMapper;
	@Override
	public void addProjectTask(QualityAnalysisTable qualityAnalysisTable) {
		qualityAnalysisTableMapper.addProjectTask(qualityAnalysisTable);
		
	}

}
