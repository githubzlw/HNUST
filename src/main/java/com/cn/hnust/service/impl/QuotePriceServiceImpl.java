package com.cn.hnust.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;







import com.cn.hnust.daoErp.QuotePriceServiceMapper;
import com.cn.hnust.pojo.QuotePrice;
import com.cn.hnust.pojo.QuotePrice1;
import com.cn.hnust.service.QuotePriceService;

@Service
public class QuotePriceServiceImpl implements QuotePriceService {
	@Resource
	private QuotePriceServiceMapper QuotePriceServiceMapper;

	@Override
	public void add(QuotePrice1 price) {
		QuotePriceServiceMapper.add(price);
		
	}

	@Override
	public QuotePrice1 getLate() {
		
		return QuotePriceServiceMapper.getLate();
	}

	@Override
	public void addAll(List<QuotePrice1> quoteList) {
		
		 QuotePriceServiceMapper.addAll(quoteList);
	}

	@Override
	public QuotePrice1 getLateOne() {
		
		return QuotePriceServiceMapper.getLateOne();
	}

	@Override
	public void addAll1(List<QuotePrice1> quotePriceList1) {
		QuotePriceServiceMapper.addAll1(quotePriceList1);
		
	}


	
	

}
