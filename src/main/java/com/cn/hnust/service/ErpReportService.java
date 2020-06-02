package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.QualityReport;

public interface ErpReportService {
	/**
	 * 查询erp上传的直接报告
	 * @Title selectErpReportByProjectNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<QualityReport>
	 */
	List<QualityReport> selectErpReportByProjectNo(@Param("projectNo")String projectNo);
    /**
     * @param report 
     * 
     * @Title:ErpReportService
     * @Description:ERP上传的检验计划和检验报告
       @author wangyang
     * @return List<QualityReport>
     * @throws
     */
	List<QualityReport> getAllReport(QualityReport report);
	/**
	 * 
	 * @Title:ErpReportService
	 * @Description:TODO
	   @author wangyang
	 * @return QualityReport
	 * @throws
	 */
	QualityReport getAllReportDingDing();
	/**
	 * 
	 * @Title:ErpReportService
	 * @Description:ERP添加数据
	   @author wangyang
	 * @param qualityReport void
	 * @throws
	 */
	void batchAddInspectionReport(List<QualityReport> qualityReport);
}
