package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.MaterialList;

public interface MaterialListService {
/**
 * 
 * @Title:MaterialListService
 * @Description:添加材料名
   @author wangyang
 * @param materialList void
 * @throws
 */
	void add(List<MaterialList> materialList);
/**
 * 
 * @Title:MaterialListService
 * @Description:查看关联数据
   @author wangyang
 * @param id
 * @return List<com.cn.hnust.pojo.MaterialList>
 * @throws
 */
List<MaterialList> getall(int id);

}
