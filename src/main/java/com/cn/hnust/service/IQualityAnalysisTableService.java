package com.cn.hnust.service;


import com.cn.hnust.pojo.QualityAnalysisTable;

public interface IQualityAnalysisTableService {
	/**
     * 将内容保存数据库
     * @param qualityAnalysisTable
     */
	public void addProjectTask(QualityAnalysisTable qualityAnalysisTable);

}
