package com.cn.hnust.service;


import java.util.List;

import com.cn.hnust.pojo.QuotePrice;
import com.cn.hnust.pojo.QuotePrice1;

public interface QuotePriceService {
/**
 * 
 * @Title:QuotePriceService
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
void addAll(List<QuotePrice1> quoteList);
/**
 * 
 * @Title:QuotePriceService
 * @Description:查询钉钉最后1个数据
   @author wangyang
 * @return QuotePrice
 * @throws
 */
QuotePrice1 getLateOne();
/**
 * 
 * @Title:QuotePriceService
 * @Description:批量添加数据
   @author wangyang
 * @param quotePriceList1 void
 * @throws
 */
void addAll1(List<QuotePrice1> quotePriceList1);


}
