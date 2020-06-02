package com.cn.hnust.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Track;
import com.cn.hnust.pojo.TrackQuery;

public interface TrackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Track record);

    int insertSelective(Track record);

    Track selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrackQuery record);

    int updateByPrimaryKey(Track record);       
    
    /**
     * 根据质检报告id删除
     * @Title deleteByPrimaryKey 
     * @Description 
     * @param reportId
     * @return
     * @return int
     */
    int deleteByReportId(Integer reportId);
    
    
    /**
     * 根据质检和采购查询去仓库次数
     * @Title selectFromStore 
     * @Description
     * @param qualityName
     * @param purchaseName
     * @return
     * @return List<Map<String,Integer>>
     */
    Map<String,Long> selectFromStore(@Param("qualityName")String qualityName,@Param("purchaseName")String purchaseName,@Param("place")String place,@Param("queryDate")Integer queryDate);

    /**
     * 查询行踪
     * @Title selectByDate 
     * @Description 
     * @param tarckQuery
     * @return
     * @return List<TrackQuery>
     */
    List<TrackQuery> selectByDate(TrackQuery tarckQuery);
    
    
    /**
     * 
     * @Title insertBatch 
     * @Description 批量插入
     * @param track
     * @return
     * @return int
     */
    int insertBatch(List<TrackQuery> track);
    
}