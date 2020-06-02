package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.QualityPicExplainMapper;
import com.cn.hnust.pojo.QualityPicExplain;
import com.cn.hnust.service.IQualityPicExplainService;

@Service
public class QualityPicExplainServiceImpl implements IQualityPicExplainService {
    
	@Autowired QualityPicExplainMapper qualityPicExplainMapper;
	@Override
	public void addQualityPicExplain(QualityPicExplain qualityPicExplain) {
		qualityPicExplainMapper.insertSelective(qualityPicExplain);
	}

	@Override
	public QualityPicExplain selectQualityPicExplainById(String  picName) {
		return qualityPicExplainMapper.selectByPrimaryKey(picName);
	}

	@Override
	public void updateQualityPicExplain(QualityPicExplain qualityPicExplain) {
		qualityPicExplainMapper.updateByPrimaryKeySelective(qualityPicExplain);
	}

	@Override
	public List<QualityPicExplain> queryByReportId(Integer reportId) {
		return qualityPicExplainMapper.queryByReportId(reportId);
	}

	@Override
	public void delQualityPicExplain(String picName) {
		qualityPicExplainMapper.delQualityPicExplain(picName);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return qualityPicExplainMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<QualityPicExplain> queryPicByType(Integer reportId, Integer type) {
		return qualityPicExplainMapper.queryPicByType(reportId, type);
	}

	@Override
	public List<QualityPicExplain> getLast(String projectNo) {
		
		return qualityPicExplainMapper.getLast(projectNo);
	}

}
