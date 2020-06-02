package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







import com.cn.hnust.dao.ProfitStatementMapper;
import com.cn.hnust.pojo.ProfitStatement;
import com.cn.hnust.service.IProfitStatementService;

@Service
public class ProfitStatementServiceImpl implements IProfitStatementService{
	  @Autowired
	    private ProfitStatementMapper profitStatementMapper;

	@Override
	public int insertAll(List<ProfitStatement> profitList) {
		
		return profitStatementMapper.insertAll(profitList);
	}

	@Override
	public int updateAll(List<ProfitStatement> profitList) {
		
		return profitStatementMapper.updateAll(profitList);
	}

	

	@Override
	public List<ProfitStatement> getAll(String start, String roleName) {
		
		return profitStatementMapper.getAll(start,roleName);
	}

	@Override
	public ProfitStatement getOne(String userName, String time1) {
		
		return profitStatementMapper.getOne(userName,time1);
	}
}
