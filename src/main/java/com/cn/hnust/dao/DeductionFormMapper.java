package com.cn.hnust.dao;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.DeductionForm;

public interface DeductionFormMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeductionForm record);

    int insertSelective(DeductionForm record);

    DeductionForm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeductionForm record);

    int updateByPrimaryKey(DeductionForm record);
    /**
     * 
     * @Title:IDeductionFormService
     * @Description:获取当月扣分次数
       @author wangyang
     * @param userName
     * @param start
     * @return int
     * @throws
     */
    DeductionForm getOne(@Param("name")String userName, @Param("date")String start);
/**
 * 
 * @Title:DeductionFormMapper
 * @Description:修改数据
   @author wangyang
 * @param deductionForm
 * @return int
 * @throws
 */
	int update(DeductionForm deductionForm);
}