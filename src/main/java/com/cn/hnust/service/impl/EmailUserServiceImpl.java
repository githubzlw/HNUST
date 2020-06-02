package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.EmailUserMapper;
import com.cn.hnust.pojo.EmailUser;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IEmailUserService;

@Service
public class EmailUserServiceImpl implements IEmailUserService {

	@Autowired
	private EmailUserMapper emailUserMapper;
	
	@Override
	public List<EmailUser> queryAll() {
		return emailUserMapper.queryAll();
	}

	@Override
	public List<EmailUser> queryQualityTesting() {
		
		return emailUserMapper.queryQualityTesting();
	}

	@Override
	public List<EmailUser> getAverageCompletionTime(EmailUser user) {
		
		return emailUserMapper.getAverageCompletionTime(user);
	}

	@Override
	public List<EmailUser> getQualityProblem(EmailUser user) {
		
		return emailUserMapper.getQualityProblem(user);
	}

	@Override
	public List<EmailUser> getSuccessRateProject(EmailUser user) {
		
		return emailUserMapper.getSuccessRateProject(user);
	}

	@Override
	public List<EmailUser> getAllReport(EmailUser user) {
		
		return emailUserMapper.getAllReport(user);
	}

}
