package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.QuotationUser;

public interface IQuotationUserService {
/**
 * 
 * @Title:IQuotationUserService
 * @Description:查询报价工程师 当前的 报价数量
   @author wangyang
 * @return List<QuotationUser>
 * @throws
 */
	List<QuotationUser> getQuantityQuoted();
/**
 * @param timeCreening 
 * 
 * @Title:IQuotationUserService
 * @Description: 查询销售统计工作量
   @author wangyang
 * @return List<QuotationUser>
 * @throws
 */
List<QuotationUser> getSalesWorkload(int timeCreening);

}
