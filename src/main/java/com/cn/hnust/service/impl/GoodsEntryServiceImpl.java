package com.cn.hnust.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.GoodsEntryMapper;
import com.cn.hnust.pojo.GoodsEntry;
import com.cn.hnust.service.GoodsEntryService;
import com.google.common.collect.Lists;

@Service
public class GoodsEntryServiceImpl implements GoodsEntryService {
	private static String UN_CLAIM = "尚未认领";
	@Autowired
	private GoodsEntryMapper goodsEntryMapper;

	@Override
	public List<GoodsEntry> listEntry(String sTime, String eTime) {
		// TODO Auto-generated method stub
		List<GoodsEntry> listEntry = goodsEntryMapper.listEntry(sTime, eTime);
		unClaim(listEntry);
		return listEntry;
	}

	@Override
	public List<GoodsEntry> listEntryByCheckNumber(List<String> checkNumbers) {
		if(CollectionUtils.isEmpty(checkNumbers)) {
			return Lists.newArrayList();
		}
		List<GoodsEntry> listEntry = goodsEntryMapper.listEntryByCheckNumber(checkNumbers);
		unClaim(listEntry);
		return listEntry;
	}
	
	private void unClaim(List<GoodsEntry> listEntry) {
		listEntry = CollectionUtils.isEmpty(listEntry) ? Lists.newArrayList() : listEntry;
		listEntry.stream().forEach(l->{
			if(!"COMPLETED".equals(l.getStatus())) {
				l.setClaimUser(UN_CLAIM);
				l.setTextCount(UN_CLAIM);
				l.setGoodsCount(UN_CLAIM);
				l.setBagCount(UN_CLAIM);
				l.setGoodsUnit(UN_CLAIM);
				l.setProjectNumber(UN_CLAIM);
			}
			String[] goodsImg = l.getGoodsImg().split(",");
			l.setImgs(Arrays.asList(goodsImg));
			l.setGoodsImg(goodsImg[0]);
			
		});
	}
}
