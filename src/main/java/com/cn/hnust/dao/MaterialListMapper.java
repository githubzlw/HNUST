package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.MaterialList;

public interface MaterialListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialList record);

    int insertSelective(MaterialList record);

    MaterialList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialList record);

    int updateByPrimaryKey(MaterialList record);
/**
 * 
 * @Title:MaterialListMapper
 * @Description:添加数据
   @author wangyang
 * @param materialList void
 * @throws
 */
	void add(List<MaterialList> materialList);
/**
 * 
 * @Title:MaterialListMapper
 * @Description:查询关联数据
   @author wangyang
 * @param id
 * @return List<MaterialList>
 * @throws
 */
List<MaterialList> getall(@Param("qualityId")int id);
}