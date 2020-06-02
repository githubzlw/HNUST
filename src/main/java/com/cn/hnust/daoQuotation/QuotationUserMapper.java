package com.cn.hnust.daoQuotation;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.QuotationUser;

public interface QuotationUserMapper {
	/**
	 * 
	 * @Title:QuotationUserMapper
	 * @Description:查询报价员工作量
	   @author wangyang
	 * @return List<QuotationUser>
	 * @throws
	 */
	
	public List<QuotationUser> getQuantityQuoted();
/**
 * 
 * @Title:QuotationUserMapper
 * @Description:查询销售工作量
   @author wangyang
 * @param timeCreening
 * @return List<QuotationUser>
 * @throws
 */
	public List<QuotationUser> getSalesWorkload(@Param("roleNo")int timeCreening);

}
