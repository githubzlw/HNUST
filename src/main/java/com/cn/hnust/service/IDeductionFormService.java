package com.cn.hnust.service;

import com.cn.hnust.pojo.DeductionForm;

public interface IDeductionFormService {
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
	DeductionForm getOne(String userName, String start);
/**
 * 
 * @Title:IDeductionFormService
 * @Description:录入扣分次数
   @author wangyang
 * @param deductionForm
 * @return int
 * @throws
 */
int insertSelective(DeductionForm deductionForm);
/**
 * 
 * @Title:IDeductionFormService
 * @Description:修改数据
   @author wangyang
 * @param deductionForm
 * @return int
 * @throws
 */
int update(DeductionForm deductionForm);

}
