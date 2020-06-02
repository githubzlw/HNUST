package com.cn.hnust.daoErp;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.QualityReport;

public interface ErpReportMapper {
  
	/**
	 * 查询erp上传的质检报告
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
     * @Title:ErpReportMapper
     * @Description:ERP传的检验计划和检验报告
       @author wangyang
     * @return List<QualityReport>
     * @throws
     */
	List<QualityReport> getAllReport(QualityReport report);
	/**
	 * 
	 * @Title:ErpReportMapper
	 * @Description:获取全部已有列表
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