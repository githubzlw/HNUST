package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.QualityPicExplain;

public interface QualityPicExplainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QualityPicExplain record);

    int insertSelective(QualityPicExplain record);

    QualityPicExplain selectByPrimaryKey(String picName);

    int updateByPrimaryKeySelective(QualityPicExplain record);

    int updateByPrimaryKey(QualityPicExplain record);
    
    
    /**
     * 根据报告号
     * @Title queryByReportId 
     * @Description
     * @param reportId
     * @return
     * @return List<QualityPicExplain>
     */
    List<QualityPicExplain> queryByReportId(Integer reportId);

	void delQualityPicExplain(String picName);
	
	/**
	 * 根据质检报告号和图片类型查询
	 * @Title queryPicByType 
	 * @Description 
	 * @param reportId
	 * @param type
	 * @return
	 * @return List<QualityPicExplain>
	 */
	List<QualityPicExplain> queryPicByType(@Param("qualityReportId")Integer reportId,@Param("picType")Integer type);
/**
 * 
 * @Title:QualityPicExplainMapper
 * @Description:获取最后一份质检报告前两张图片
   @author wangyang
 * @param projectNo
 * @return List<QualityPicExplain>
 * @throws
 */
	List<QualityPicExplain> getLast(@Param("projectNo")String projectNo);
}