package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.ComplaintInspectionReport;

public interface ComplaintInspectionReportService {
/**
 * @return 
 * 
 * @Title:ComplaintInspectionReportService
 * @Description:添加检验后报告
   @author wangyang
 * @param report void
 * @throws
 */
	int insertSelective(ComplaintInspectionReport report);
/**
 * 
 * @Title:ComplaintInspectionReportService
 * @Description:获取全部重做后质检报告
   @author wangyang
 * @param parseInt
 * @return List<ComplaintInspectionReport>
 * @throws
 */
List<ComplaintInspectionReport> getAll(int parseInt);
/**
 * 
 * @Title:ComplaintInspectionReportService
 * @Description:根据id查询
   @author wangyang
 * @param parseInt
 * @return ComplaintInspectionReport
 * @throws
 */
ComplaintInspectionReport selectByPrimaryKey(int parseInt);
/**
 * 
 * @Title:ComplaintInspectionReportService
 * @Description:根据id删除
   @author wangyang
 * @param parseInt void
 * @throws
 */
void deleteByPrimaryKey(int parseInt);

}
