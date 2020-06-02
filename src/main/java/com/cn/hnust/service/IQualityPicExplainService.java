package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.QualityPicExplain;

public interface IQualityPicExplainService {

	void addQualityPicExplain(QualityPicExplain qualityPicExplain);
	
	QualityPicExplain selectQualityPicExplainById(String  picName);
	
	void updateQualityPicExplain(QualityPicExplain qualityPicExplain);
	
	void delQualityPicExplain(String picName);
	
    /**
     * 根据报告号
     * @Title queryByReportId 
     * @Description
     * @param reportId
     * @return
     * @return List<QualityPicExplain>
     */
    List<QualityPicExplain> queryByReportId(Integer reportId);
    
    
    int deleteByPrimaryKey(Integer id);
    
    
    
	/**
	 * 根据质检报告号和图片类型查询
	 * @Title queryPicByType 
	 * @Description 
	 * @param reportId
	 * @param type
	 * @return
	 * @return List<QualityPicExplain>
	 */
	List<QualityPicExplain> queryPicByType(Integer reportId,Integer type);
/**
 * 
 * @Title:IQualityPicExplainService
 * @Description:获取最后两张照片
   @author wangyang
 * @param projectNo
 * @return List<QualityPicExplain>
 * @throws
 */
	List<QualityPicExplain> getLast(String projectNo);
}
