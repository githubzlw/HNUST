package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.GoodsEntry;

public interface GoodsEntryMapper {
	
	/**列表
	 * @return
	 */
	List<GoodsEntry> listEntry(@Param("sTime")String sTime,@Param("eTime")String eTime);
	
	List<GoodsEntry> listEntryByCheckNumber(@Param("checkNumbers")List<String> checkNumbers);

}
