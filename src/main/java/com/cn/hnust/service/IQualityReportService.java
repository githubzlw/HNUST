package com.cn.hnust.service;

import java.text.ParseException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProjectReport;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.pojo.QualityReportQuery;

public interface IQualityReportService {
	
	
	    int deleteByPrimaryKey(Integer id);

	    int insert(QualityReport record);

	    int insertSelective(QualityReport record)throws ParseException;

	    QualityReport selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(QualityReport record);

	    int updateByPrimaryKey(QualityReport record);
	    
	    QualityReport selectByPrimaryKey(int id);
	    
	    List<QualityReport> selectByProjectNo(@Param("projectNo")String projectNo);
	    /**
	     * 
	     * @Title:IQualityReportTeamMemberService
	     * @Description:质检报告上传表添加数据
	       @author wangyang
	     * @param member void
	     * @throws
	     */
		QualityReport selectNewestReportByProjectNo(@Param("projectNo")String projectNo);

		
		
		/**
		 * 查询质检报告列表
		 * @Title selectAllReport 
		 * @Description
		 * @param qualityReportQuery
		 * @return
		 * @return List<QualityReport>
		 */
		List<QualityReport> selectAllReport(QualityReportQuery qualityReportQuery);
		
		
		
		/**
		 * 质检报告数量
		 * @Title totalReports 
		 * @Description
		 * @param qualityReportQuery
		 * @return
		 * @return int
		 */
		int totalReports(QualityReportQuery qualityReportQuery);
		
		
		/**
		 * 根据项目号和质检报告类型查询
		 * @Title selectByProjectNo 
		 * @Description
		 * @param projectNo
		 * @param type
		 * @return
		 * @return List<QualityReport>
		 */
		List<QualityReport> selectByProjectNoAndType(String projectNo,Integer type);
/**
 * 
 * @Title:IQualityReportService
 * @Description:查询项目是否存在样品检验后，第一次大货检验后，有客户投诉后3种情况的一种
   @author wangyang
 * @param projectNo
 * @return int
 * @throws
 */
		int inspectionPlanningCondition(String projectNo);
/**
 * 
 * @Title:IQualityReportService
 * @Description:根据项目号查询最后检验日期
   @author wangyang
 * @param projectNo
 * @return QualityReport
 * @throws
 */
QualityReport selectProjectNo(String projectNo);
/**
 * 
 * @Title:IProjectReportService
 * @Description:最近1个月是否上传检验报告
   @author wangyang
 * @param report
 * @return int
 * @throws
 */
public int getReport(QualityReport report);
/**
 * 
 * @Title:IQualityReportService
 * @Description:查询项目组成员质检报告
   @author wangyang
 * @param projectNo
 * @param userName
 * @return List<QualityReport>
 * @throws
 */
List<QualityReport> selectByProjectNo1(String projectNo,
		String userName);
      
}
