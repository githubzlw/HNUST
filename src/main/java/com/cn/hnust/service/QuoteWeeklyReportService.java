package com.cn.hnust.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.QuoteWeeklyReport;
import com.cn.hnust.pojo.QuoteWeeklyReportQuery;


public interface QuoteWeeklyReportService {

    QuoteWeeklyReport selectByPrimaryKey(Integer id);

    /**
     * 根据类型和阶段查询报告
     * @param csgOrderId
     * @param
     * @param
     * @return
     */
    List<QuoteWeeklyReport> queryByCsgOrderIdAndType(String csgOrderId,Integer fileType,String factoryId);



    /**
     * 根据reportTypeId查询所有报告
     * @Title queryByOrderId
     * @Description
     * @return
     * @return List<QuoteWeeklyReport>
     */
    List<QuoteWeeklyReport> queryByReportTypeId(Integer reportTypeId);


    /**
     * 查询最近上传日期
     * @param csgOrderId
     * @param factoryId
     * @return
     */
    String queryMaxUploadDate(String csgOrderId,String factoryId);


    /**
     * 查询所有报告
     * @return
     */
    List<QuoteWeeklyReport> selectAll(QuoteWeeklyReportQuery quoteWeeklyReportQuery);
    
    
    
    /**
     * 查询项目号列表
     * @Title queryProjects 
     * @Description
     * @param pageNumber
     * @param pageSize
     * @return
     * @return List<String>
     */
    List<String> queryProjects(Integer pageNumber,Integer pageSize);
    
    
    /**
     * 查询最近上传日期
     * @param csgOrderId
     * @param factoryId
     * @return
     */
    Map<String,Object> queryGroupByFileType(String csgOrderId,String factoryId);
    /**
     * @param num 
     * 
     * @Title:QuoteWeeklyReportService
     * @Description:查询数据
       @author wangyang
     * @return List<QuoteWeeklyReportService>
     * @throws
     */
	List<QuoteWeeklyReport> getAll(int num);
}