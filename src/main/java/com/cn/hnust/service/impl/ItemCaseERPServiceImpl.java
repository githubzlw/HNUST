package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.daoErp.ItemCaseERPMapper;
import com.cn.hnust.pojo.FactoryFund;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.ItemCaseERPService;
@Service
public class ItemCaseERPServiceImpl implements ItemCaseERPService {

	@Autowired
	private ItemCaseERPMapper itemCaseERPMapper;
	
	@Override
	public ProjectERP selectByCaseNo(String projectNo) {
		return itemCaseERPMapper.selectByCaseNo(projectNo);
	}

	@Override
	public int getOldCustomerOff(String userName, String start) {
		
		return itemCaseERPMapper.getOldCustomerOff(userName,start);
	}

	@Override
	public int getNewCustomerBigProjectOffer(String userName, String start) {
		
		return itemCaseERPMapper.getNewCustomerBigProjectOffer(userName,start);
	}

	@Override
	public int getOldCustomerBigProjectOffer(String userName, String start) {
		
		return itemCaseERPMapper.getOldCustomerBigProjectOffer(userName,start);
	}

	@Override
	public String getProjectViolation(String userName, String start) {
		
		return itemCaseERPMapper.getProjectViolation(userName,start);
	}

	@Override
	public int proofingSuccess(String userName, String start) {
		
		return itemCaseERPMapper.proofingSuccess(userName,start);
	}

	@Override
	public int proofingFailure(String userName, String start) {
		
		return itemCaseERPMapper.proofingFailure(userName,start);
	}

	@Override
	public List<ProjectERP> getAllCaseNo(String userName, String start) {
		
		return itemCaseERPMapper.getAllCaseNo(userName,start);
	}

	@Override
	public List<ProjectERP> getAllCaseNo1(String userName, String start) {
		
		return itemCaseERPMapper.getAllCaseNo1(userName,start);
	}

	@Override
	public String getAllContractAmount(String projectNo) {
		projectNo="'"+projectNo+"'";
		return itemCaseERPMapper.getContractAmount1(projectNo);
	}
	@Override
	public int update(String projectNo) {
		int total=0;
		if(projectNo!=null&&!"".equalsIgnoreCase(projectNo)){
			total=itemCaseERPMapper.update(projectNo);
		}else{
			total=itemCaseERPMapper.clean(projectNo);
		}
		return total;
	}

	@Override
	public int findName(String caseno, String applicant,int num) {
		int num1=itemCaseERPMapper.findName(caseno,applicant,num);
		
		return num1;
	}

	@Override
	public String getApNumber() {
		String number="";
		String apnumber=itemCaseERPMapper.getApNumber();
		if(apnumber!="")
		{
			int i = Integer.parseInt(apnumber.substring(2,7));  //截取字符串
			i++;
			String x = i+"";  //截取需要的部分
			number = "AP" + x;
			
		}
		return number;
	}

	@Override
	public void insert(String apNumber) {
		
		itemCaseERPMapper.insert(apNumber);
	}

	@Override
	public ProjectERP search(ProjectERP project) {
		
		return itemCaseERPMapper.search(project);
	}

	@Override
	public void updateQuality(ProjectERP projectERP) {
		itemCaseERPMapper.updateQuality(projectERP);
		
	}

	
}
