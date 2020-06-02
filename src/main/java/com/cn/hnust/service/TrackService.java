package com.cn.hnust.service;

import java.util.List;
import java.util.Map;

import com.cn.hnust.pojo.Track;
import com.cn.hnust.pojo.TrackPlace;
import com.cn.hnust.pojo.TrackQuery;

public interface TrackService {
    int deleteByPrimaryKey(Integer id);

    int insert(Track record);

    int insertSelective(Track record);

    Track selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrackQuery record);

    int updateByPrimaryKey(Track record);
    
    /**
     * 插入的同时，保存地址
     * @Title insertSelective 
     * @Description
     * @param record
     * @param placeList
     * @return
     * @return int
     */
    int insertBatch(List<TrackQuery> list);
    
    
    
    /**
     * 根据质检和采购查询去仓库次数
     * @Title selectFromStore 
     * @Description
     * @param qualityName
     * @param purchaseName
     * @return
     * @return List<Map<String,Integer>>
     */
    Map<String,Long> selectFromStore(String qualityName,String purchaseName,String place,Integer queryDate);
    
    
    /**
     * 查询行踪
     * @Title selectByDate 
     * @Description 
     * @param tarckQuery
     * @return
     * @return List<TrackQuery>
     */
    List<TrackQuery> selectByDate(TrackQuery tarckQuery);
}