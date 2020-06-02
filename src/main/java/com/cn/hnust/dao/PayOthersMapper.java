package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.PayOthers;

public interface PayOthersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayOthers record);

    int insertSelective(PayOthers record);

    PayOthers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayOthers record);

    int updateByPrimaryKey(PayOthers record);

	List<PayOthers> getAllPending();
/**
 * 
 * @Title:PayOthersMapper
 * @Description:修改状态
   @author wangyang
 * @param processInstanceId
 * @param num void
 * @throws
 */
	void updatePassERP(@Param("id") int id,@Param("passErp") int num);
}