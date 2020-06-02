package com.cn.hnust.daoQuotation;

import java.util.List;

import com.cn.hnust.pojo.EmailCustomer;

public interface EmailCustomerMapper {
	/**
	 * 
	 * @Title:IQuotationNewQuotesService
	 * @Description:最近2个月有多少封客户的邮件没得到及时回复
	   @author wangyang
	 * @return List<QuotationNewQuotes>
	 * @throws
	 */
	List<EmailCustomer> getNoReplayTwoMonth();
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:最近2个月有多少封客户的邮件没得到及时回复, 点开后 显示 相关客户名
	   @author wangyang
	 * @param customer
	 * @param num
	 * @return List<EmailCustomer>
	 * @throws
	 */
		List<EmailCustomer> getNoReplayTwoMonth(EmailCustomer customer);
		/**
		 * 
		 * @Title:EmailCustomerMapper
		 * @Description:最近2个月有多少封客户的邮件没得到及时回复
		   @author wangyang
		 * @param customer
		 * @param num
		 * @return List<EmailCustomer>
		 * @throws
		 */
		int getNoReplayTwoMonthTotal(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:2个月内放弃的A/B级项目
	   @author wangyang
	 * @param customer
	 * @return List<EmailCustomer>
	 * @throws
	 */
		List<EmailCustomer> getAbandonedItems(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:2个月内放弃的A/B级项目
	   @author wangyang
	 * @param customer
	 * @return int
	 * @throws
	 */
		int getAbandonedItemsTotal(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:2个月内立项未回复客户信息
	   @author wangyang
	 * @param customer
	 * @return List<EmailCustomer>
	 * @throws
	 */
	List<EmailCustomer> getApprovalNoReplay(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:2个月内立项未回复客户数
	   @author wangyang
	 * @param customer
	 * @return int
	 * @throws
	 */
	int getApprovalNoReplayTotal(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:查看最近翻译
	   @author wangyang
	 * @param customer
	 * @return List<EmailCustomer>
	 * @throws
	 */
	List<EmailCustomer> getRecentTranslationProjects(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:查看最近翻译数
	   @author wangyang
	 * @param customer
	 * @return List<EmailCustomer>
	 * @throws
	 */
	int getRecentTranslationProjectsTotal(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:最近1个月平均翻译时间
	   @author wangyang
	 * @return int
	 * @throws
	 */
	int getTransTime();
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description报价无报价员
	   @author wangyang
	 * @param customer
	 * @return List<EmailCustomer>
	 * @throws
	 */
	List<EmailCustomer> getNoQuotes(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description报价无报价员数
	   @author wangyang
	 * @param customer
	 * @return List<EmailCustomer>
	 * @throws
	 */
	int getNoQuotesTotal(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:有报价员超过5天无报价
	   @author wangyang
	 * @param customer
	 * @return List<EmailCustomer>
	 * @throws
	 */
	List<EmailCustomer> getNoOffer(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:有报价员超过5天无报价
	   @author wangyang
	 * @param customer
	 * @return List<EmailCustomer>
	 * @throws
	 */
	int getNoOfferTotal(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:每月A/B级客户
	   @author wangyang
	 * @param customer
	 * @return List<EmailCustomer>
	 * @throws
	 */
	List<EmailCustomer> getABLevelCustomer(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:每月A/B级客户数
	   @author wangyang
	 * @param customer
	 * @return List<EmailCustomer>
	 * @throws
	 */
	int getABLevelCustomerTotal(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:获取待总裁指示项目
	   @author wangyang
	 * @param userId
	 * @return int
	 * @throws
	 */
	int getPresidentApprovalTotal(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:你有X个 项目 状态不正确
	   @author wangyang
	 * @param customer
	 * @return int
	 * @throws
	 */
	int getincorrectProjectStatusTotal(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:有X个 项目需要 更新状态
	   @author wangyang
	 * @param customer
	 * @return int
	 * @throws
	 */
	int getprojectUpdateStatusTotal(EmailCustomer customer);
	/**
	 * 
	 * @Title:EmailCustomerMapper
	 * @Description:最近1个月平均翻译时间
	   @author wangyang
	 * @return int
	 * @throws
	 */
	int getTransTime1();
	/**
	 * 
	 * @Title:IEmailCustomerService
	 * @Description:修改是否为outlook客户
	   @author wangyang
	 * @param cus void
	 * @throws
	 */
	void updateOutlookCustomers(EmailCustomer cus);
	  
}
