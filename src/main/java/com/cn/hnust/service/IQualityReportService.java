package com.cn.hnust.service;

import java.text.ParseException;
import java.util.List;

import com.cn.hnust.pojo.ErpQualityReport;
import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProjectReport;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.pojo.QualityReportQuery;

public interface IQualityReportService {


    int deleteByPrimaryKey(Integer id);

    int insert(QualityReport record);

    int insertSelective(QualityReport record) throws ParseException;

    QualityReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QualityReport record);

    int updateByPrimaryKey(QualityReport record);

    QualityReport selectByPrimaryKey(int id);

    List<QualityReport> selectByProjectNo(@Param("projectNo") String projectNo);

    /**
     * @param member void
     * @throws
     * @Title:IQualityReportTeamMemberService
     * @Description:质检报告上传表添加数据
     * @author wangyang
     */
    QualityReport selectNewestReportByProjectNo(@Param("projectNo") String projectNo);


    /**
     * 查询质检报告列表
     *
     * @param qualityReportQuery
     * @return List<QualityReport>
     * @Title selectAllReport
     * @Description
     */
    List<QualityReport> selectAllReport(QualityReportQuery qualityReportQuery);


    /**
     * 质检报告数量
     *
     * @param qualityReportQuery
     * @return int
     * @Title totalReports
     * @Description
     */
    int totalReports(QualityReportQuery qualityReportQuery);


    /**
     * 根据项目号和质检报告类型查询
     *
     * @param projectNo
     * @param type
     * @return List<QualityReport>
     * @Title selectByProjectNo
     * @Description
     */
    List<QualityReport> selectByProjectNoAndType(String projectNo, Integer type);

    /**
     * @param projectNo
     * @return int
     * @throws
     * @Title:IQualityReportService
     * @Description:查询项目是否存在样品检验后，第一次大货检验后，有客户投诉后3种情况的一种
     * @author wangyang
     */
    int inspectionPlanningCondition(String projectNo);

    /**
     * @param projectNo
     * @return QualityReport
     * @throws
     * @Title:IQualityReportService
     * @Description:根据项目号查询最后检验日期
     * @author wangyang
     */
    QualityReport selectProjectNo(String projectNo);

    /**
     * @param report
     * @return int
     * @throws
     * @Title:IProjectReportService
     * @Description:最近1个月是否上传检验报告
     * @author wangyang
     */
    public int getReport(QualityReport report);

    /**
     * @param projectNo
     * @param userName
     * @return List<QualityReport>
     * @throws
     * @Title:IQualityReportService
     * @Description:查询项目组成员质检报告
     * @author wangyang
     */
    List<QualityReport> selectByProjectNo1(String projectNo, String userName);

    List<ErpQualityReport> queryErpQualityReport(String projectNo);

	List<ErpQualityReport> queryAllQualityReport(String projectNo);

	int insertErpQualityReport(List<ErpQualityReport> reportList);

}
