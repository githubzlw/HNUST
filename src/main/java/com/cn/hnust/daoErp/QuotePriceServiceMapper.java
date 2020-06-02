package com.cn.hnust.daoErp;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.QuotePrice;
import com.cn.hnust.pojo.QuotePrice1;

public interface QuotePriceServiceMapper {
/**
 * 
 * @Title:QuotePriceServiceMapper
 * @Description:添加数据
   @author wangyang
 * @param price void
 * @throws
 */

	void add(QuotePrice1 price);
	/**
	 * 
	 * @Title:QuotePriceService
	 * @Description:查询跟新最后1个数据
	   @author wangyang
	 * @return QuotePrice
	 * @throws
	 */
QuotePrice1 getLate();
/**
 * 
 * @Title:QuotePriceService
 * @Description:添加数据
   @author wangyang
 * @param quoteList void
 * @throws
 */
	void addAll(@Param("list")List<QuotePrice1> quoteList);
	/**
	 * 
	 * @Title:QuotePriceService
	 * @Description:查询dingding最后1个数据
	   @author wangyang
	 * @return QuotePrice
	 * @throws
	 */
QuotePrice1 getLateOne();
/**
 * 
 * @Title:QuotePriceService
 * @Description:添加数据
   @author wangyang
 * @param quoteList void
 * @throws
 */
	void addAll1(@Param("list")List<QuotePrice1> quotePriceList1);
	/**
	 * 
	 * @Title:QuotePriceServiceMapper
	 * @Description:根据项目号获取最后的
	   @author wangyang
	 * @param projectNo
	 * @return QuotePrice
	 * @throws
	 */
QuotePrice getOne(@Param("projectNo")String projectNo);
	

}
