package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.QuotePrice;
import com.cn.hnust.pojo.QuotePrice1;

public interface TQuotePriceService {
/**
 * 
 * @Title:TQuotePriceService
 * @Description:获取全部
   @author wangyang
 * @param num2
 * @return List<QuotePrice>
 * @throws
 */
	List<QuotePrice> getall(int num2);

/**
 * 
 * @Title:QuotePriceService
 * @Description:根据项目号获取最后的
   @author wangyang
 * @param projectNo
 * @return QuotePrice
 * @throws
 */
QuotePrice getOne(String projectNo);
/**
 * 
 * @Title:TQuotePriceService
 * @Description:查询进料检验
   @author wangyang
 * @param projectNo
 * @return List<QuotePrice1>
 * @throws
 */
List<QuotePrice> getAllQuote(String projectNo);

}
