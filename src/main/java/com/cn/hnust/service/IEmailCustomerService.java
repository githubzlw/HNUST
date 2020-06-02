package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.EmailCustomer;


public interface IEmailCustomerService {
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
  * @Title:IEmailCustomerService
  * @Description:统计页数据调取
    @author wangyang
  * @param customer
  * @param num
  * @return List<EmailCustomer>
  * @throws
  */
 List<EmailCustomer> getStatisticsPage(EmailCustomer customer,
 		String num);
 /**
  * 
  * @Title:IEmailCustomerService
  * @Description:统计页全部数据处理
    @author wangyang
  * @param customer
  * @param num
  * @return int
  * @throws
  */
 int getStatisticsPageTotal(EmailCustomer customer, String num);
 /**
  * 
  * @Title:IEmailCustomerService
  * @Description:最近1个月翻译
    @author wangyang
  * @return int
  * @throws
  */

int getTransTime(int i);
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
