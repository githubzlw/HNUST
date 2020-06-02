package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.QuotePriceMapper;
import com.cn.hnust.pojo.QuotePrice;
import com.cn.hnust.pojo.QuotePrice1;
import com.cn.hnust.service.TQuotePriceService;
@Service
public class TQuotePriceServiceImpl implements TQuotePriceService{
	@Autowired
	private QuotePriceMapper quotePriceMapper;
	@Override
	public List<QuotePrice> getall(int num2) {
		
		return quotePriceMapper.getall(num2);
	}
	@Override
	public QuotePrice getOne(String projectNo) {
		
		return quotePriceMapper.getOne(projectNo);
	}
	@Override
	public List<QuotePrice> getAllQuote(String projectNo) {
		
		return quotePriceMapper.getAllQuote(projectNo);
	}
}
