package com.cn.hnust.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.TrackPlace;

public interface TrackPlaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrackPlace record);

    int insertSelective(TrackPlace record);

    TrackPlace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrackPlace record);

    int updateByPrimaryKey(TrackPlace record);
    
    /**
     * 批量保存
     * @Title insertBatch 
     * @Description
     * @param places
     * @return
     * @return int
     */
    int insertBatch(List<TrackPlace> places);
    
    /**
     * 根据行踪号删除
     * @Title deleteByTrackId 
     * @Description 
     * @param trackId
     * @return
     * @return int
     */
    int deleteByTrackId(Integer trackId);
    
    /**
     * 根据行踪号查询
     * @Title selectTrackPlaceList 
     * @Description
     * @param trackId
     * @return
     * @return List<TrackPlace>
     */
    List<TrackPlace> selectTrackPlaceList(Integer trackId);
    
    /**
     * 查询当天是否录入
     * @Title selectByDate 
     * @Description
     * @param qualityName
     * @return
     * @return int
     */
    int selectByDate(@Param("userName")String userName,@Param("place")String place,@Param("checkDate")String checkDate);
}