package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.daoErp.ErpReportMapper;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.service.ErpReportService;
@Service
public class ErpReportServiceImpl implements ErpReportService {

	@Autowired
	private ErpReportMapper erpReportMapper;
	
	@Override
	public List<QualityReport> selectErpReportByProjectNo(String projectNo) {
		return erpReportMapper.selectErpReportByProjectNo(projectNo);
	}

	@Override
	public List<QualityReport> getAllReport(QualityReport report) {
		
		return erpReportMapper.getAllReport(report);
	}

	@Override
	public QualityReport getAllReportDingDing() {
		
		return erpReportMapper.getAllReportDingDing();
	}

	@Override
	public void batchAddInspectionReport(List<QualityReport> qualityReport) {
		erpReportMapper.batchAddInspectionReport(qualityReport);
		
	}

}
