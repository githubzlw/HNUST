package com.cn.hnust.daoQuotation;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.QuotationNewQuotes;





public interface QuotationNewQuotesMapper {
    int deleteByPrimaryKey(Integer id);
    /**
     * @param allProjectNo 
     * 
     * @Title:IQuotationNewQuotesService
     * @Description:新的大项目
       @author wangyang
     * @return int
     * @throws
     */
    	int getNew(@Param("projectId")String allProjectNo);
    /**
     * 
     * @Title:IQuotationNewQuotesService
     * @Description:老的大项目
       @author wangyang
     * @return int
     * @throws
     */
	int getOld(@Param("projectId")String allProjectNo);
	/**
	 * 
	 * @Title:IQuotationNewQuotesService
	 * @Description:最近2个月有多少封客户的邮件没得到及时回复
	   @author wangyang
	 * @return List<QuotationNewQuotes>
	 * @throws
	 */
	List<QuotationNewQuotes> getNoReplayTwoMonth();

   


}