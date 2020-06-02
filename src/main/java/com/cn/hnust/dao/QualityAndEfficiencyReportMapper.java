package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.QualityAndEfficiencyReport;

public interface QualityAndEfficiencyReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QualityAndEfficiencyReport record);

    int insertSelective(QualityAndEfficiencyReport record);

    QualityAndEfficiencyReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QualityAndEfficiencyReport record);

    int updateByPrimaryKey(QualityAndEfficiencyReport record);
    /**
     * 
     * @Title:IQualityAndEfficiencyReportService
     * @Description:查询预备报告
       @author wangyang
     * @param projectNo
     * @return List<QualityAndEfficiencyReport>
     * @throws
     */
	List<QualityAndEfficiencyReport> getAll(String projectNo);
}