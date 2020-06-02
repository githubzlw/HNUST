package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.FactoryQualityInspectionVideo;

public interface FactoryQualityInspectionVideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FactoryQualityInspectionVideo record);

    int insertSelective(FactoryQualityInspectionVideo record);

    FactoryQualityInspectionVideo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FactoryQualityInspectionVideo record);

    int updateByPrimaryKey(FactoryQualityInspectionVideo record);
    
    /**
     * 根据工厂名查询
     * @Title selectByFactoryName 
     * @Description
     * @return
     * @return FactoryQualityInspectionVideo
     */
    List<FactoryQualityInspectionVideo> selectByFactoryName(@Param("factoryName")String factoryName);
    /**
     * 根据项目号查询
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return FactoryQualityInspectionVideo
     */
    List<FactoryQualityInspectionVideo> selectByProjectNo(@Param("projectNo")String projectNo);
    
    
    /**
     * 根据项目号和视频类型查询
     * @Title selectByProjectNoAndType 
     * @Description
     * @param projectNo
     * @param videoType
     * @return
     * @return List<FactoryQualityInspectionVideo>
     */
    List<FactoryQualityInspectionVideo> selectByProjectNoAndType(@Param("projectNo")String projectNo,@Param("videoType")Integer videoType);
    
    /**
     * 批量插入视频
     * @Title insertPhotosBatch 
     * @Description
     * @param reports
     * @return void
     */
    void insertBatch(List<FactoryQualityInspectionVideo> videos);
    
    
    
    
    /**
     * 根据项目号查询
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return FactoryQualityInspectionVideo
     */
    List<FactoryQualityInspectionVideo> selectByTaskId(Integer taskId);
}