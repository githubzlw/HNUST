package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





import com.cn.hnust.dao.ComplaintInspectionReportMapper;
import com.cn.hnust.pojo.ComplaintInspectionReport;
import com.cn.hnust.service.ComplaintInspectionReportService;

@Service
public class ComplaintInspectionReportServiceImpl implements ComplaintInspectionReportService{
	@Autowired
	private ComplaintInspectionReportMapper complaintInspectionReportMapper;

	@Override
	public int insertSelective(ComplaintInspectionReport record) {
		return complaintInspectionReportMapper.insertSelective(record);
		
	}

	@Override
	public List<ComplaintInspectionReport> getAll(int id) {
		
		return complaintInspectionReportMapper.getAll(id);
	}

	@Override
	public ComplaintInspectionReport selectByPrimaryKey(int parseInt) {
		
		return complaintInspectionReportMapper.selectByPrimaryKey(parseInt);
	}

	@Override
	public void deleteByPrimaryKey(int parseInt) {
		complaintInspectionReportMapper.deleteByPrimaryKey(parseInt);
		
	}
}
