package com.cn.hnust.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.FactoryScore;

public interface FactoryScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FactoryScore record);

    int insertSelective(FactoryScore record);

    FactoryScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FactoryScore record);

    int updateByPrimaryKey(FactoryScore record);
    
    
	/**
	 * 批量插入
	 * @Title insertBatch 
	 * @Description
	 * @return
	 * @return int
	 */
	int insertBatch(List<FactoryScore> list);
	
	
	/**
	 * 根据项目号查询是否所有工厂已打分
	 * @Title selectByProjectNo 
	 * @Description 
	 * @return
	 * @return int
	 */
	int selectByProjectNo(@Param("projectNo")String projectNo);
	
	/**
	 * 查询工厂平均分
	 * @Title selectAvgScore 
	 * @Description
	 * @param factoryName
	 * @return
	 * @return Double
	 */
    Map<String,Object> selectAvgScore(@Param("factoryName")String factoryName,@Param("queryDate")Integer queryDate);
}