package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.FactoryQualityInspectionVideo;

public interface FactoryQualityInspectionVideoService {

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
    List<FactoryQualityInspectionVideo> selectByFactoryName(String factoryName);
    
    
    /**
     * 根据项目号查询
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return FactoryQualityInspectionVideo
     */
    List<FactoryQualityInspectionVideo> selectByProjectNo(String projectNo);
    
    
    
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
    
    
    /**
     * 根据项目号和视频类型查询
     * @Title selectByProjectNoAndType 
     * @Description
     * @param projectNo
     * @param videoType
     * @return
     * @return List<FactoryQualityInspectionVideo>
     */
    List<FactoryQualityInspectionVideo> selectByProjectNoAndType(String projectNo,Integer videoType);
}
