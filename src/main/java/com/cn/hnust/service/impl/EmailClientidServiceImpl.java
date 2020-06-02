package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.EmailClientidMapper;
import com.cn.hnust.pojo.EmailClientid;
import com.cn.hnust.service.IEmailClientidService;

@Service
public class EmailClientidServiceImpl implements IEmailClientidService{

	 @Autowired
	 private EmailClientidMapper emailClientidMapper;
	/**
	 * 查询所有的信息
	 */
	@Override
	public List<EmailClientid> selectAllEmailClientid() {
		return emailClientidMapper.selectAllEmailClientid();
	}

}
