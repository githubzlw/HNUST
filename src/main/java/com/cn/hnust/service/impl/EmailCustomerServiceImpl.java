package com.cn.hnust.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.cn.hnust.daoQuotation.EmailCustomerMapper;
import com.cn.hnust.pojo.EmailCustomer;
import com.cn.hnust.service.IEmailCustomerService;
@Service
public class EmailCustomerServiceImpl implements IEmailCustomerService{
	 @Autowired
	 private EmailCustomerMapper emailCustomerMapper;
	@Override
	public List<EmailCustomer> getNoReplayTwoMonth() {
		
		return emailCustomerMapper.getNoReplayTwoMonth();
	}
	@Override
	public List<EmailCustomer> getStatisticsPage(EmailCustomer customer,
			 	String num) {
		List<EmailCustomer>list =new ArrayList<EmailCustomer>();
		if("2".equals(num)){
			list=emailCustomerMapper.getNoReplayTwoMonth(customer);
		}else if("3".equals(num)){
			list=emailCustomerMapper.getAbandonedItems(customer);	
		}else if("4".equals(num)){
			list=emailCustomerMapper.getApprovalNoReplay(customer);	
		}else if("5".equals(num)){
			list=emailCustomerMapper.getRecentTranslationProjects(customer);	
		}else if("6".equals(num)){
			list=emailCustomerMapper.getNoQuotes(customer);	
		}else if("7".equals(num)){
			list=emailCustomerMapper.getNoOffer(customer);	
		}else if("8".equals(num)){
			list=emailCustomerMapper.getABLevelCustomer(customer);	
		}
		return list;
	}
	@Override
	public int getStatisticsPageTotal(EmailCustomer customer, String num) {
		int total=0;
		if("2".equals(num)){
			total=emailCustomerMapper.getNoReplayTwoMonthTotal(customer);
		}else if("3".equals(num)){
			total=emailCustomerMapper.getAbandonedItemsTotal(customer);
		}else if("4".equals(num)){
			total=emailCustomerMapper.getApprovalNoReplayTotal(customer);
		}else if("5".equals(num)){
			total=emailCustomerMapper.getRecentTranslationProjectsTotal(customer);
		}else if("6".equals(num)){
			total=emailCustomerMapper.getNoQuotesTotal(customer);
		}else if("7".equals(num)){
			total=emailCustomerMapper.getNoOfferTotal(customer);
		}else if("8".equals(num)){
			total=emailCustomerMapper.getABLevelCustomerTotal(customer);
		}
		return total;
	}
	@Override
	public int getTransTime(int i) {
		int num=0;
		if(i==1){
			num=emailCustomerMapper.getTransTime();
		}else if(i==2){
			num=emailCustomerMapper.getTransTime1();
		}
		return num;
	}
	@Override
	public void updateOutlookCustomers(EmailCustomer cus) {
		emailCustomerMapper.updateOutlookCustomers(cus);
		
	}
}
