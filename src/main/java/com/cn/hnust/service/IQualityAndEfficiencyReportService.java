package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.QualityAndEfficiencyReport;

public interface IQualityAndEfficiencyReportService {
/**
 * 
 * @Title:IQualityAndEfficiencyReportService
 * @Description:上传文件到数据库
   @author wangyang
 * @param report
 * @return int
 * @throws
 */
	int insertSelective(QualityAndEfficiencyReport report);
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
