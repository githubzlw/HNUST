package com.cn.hnust.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.StatusEnter;

public interface StatusEnterMapper {
    int insert(StatusEnter record);

    int insertSelective(StatusEnter record);
    
    List<StatusEnter>selectProjectStatusEnter(String projectNo);

	void updateQualityComplaintFlagByProjectNo(@Param("set") Set<String> proSetSet,@Param("feekBackFlag")String feekBackFlag);
}