package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






import com.cn.hnust.dao.QualityAndEfficiencyReportMapper;
import com.cn.hnust.pojo.QualityAndEfficiencyReport;
import com.cn.hnust.service.IQualityAndEfficiencyReportService;

@Service
public class QualityAndEfficiencyReportServiceImpl implements IQualityAndEfficiencyReportService{
	@Autowired
	private  QualityAndEfficiencyReportMapper  qualityAndEfficiencyReportMapper;

	@Override
	public int insertSelective(QualityAndEfficiencyReport report) {
		
		return qualityAndEfficiencyReportMapper.insertSelective(report);
	}

	@Override
	public List<QualityAndEfficiencyReport> getAll(String projectNo) {
		
		return qualityAndEfficiencyReportMapper.getAll(projectNo);
	}

	

	
}
