package com.cn.hnust.dao;

import com.cn.hnust.pojo.QualityAnalysisTable;

public interface QualityAnalysisTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QualityAnalysisTable record);

    int insertSelective(QualityAnalysisTable record);

    QualityAnalysisTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QualityAnalysisTable record);

    int updateByPrimaryKey(QualityAnalysisTable record);

	void addProjectTask(QualityAnalysisTable qualityAnalysisTable);
}