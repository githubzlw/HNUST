package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.QuotationNewQuotes;

public interface IQuotationNewQuotesService {
/**
 * @param allProjectNo 
 * 
 * @Title:IQuotationNewQuotesService
 * @Description:新的大项目
   @author wangyang
 * @return int
 * @throws
 */
	int getNew(String allProjectNo);
/**
 * 
 * @Title:IQuotationNewQuotesService
 * @Description:老的大项目
   @author wangyang
 * @return int
 * @throws
 */
	int getOld(String allProjectNo);


}
