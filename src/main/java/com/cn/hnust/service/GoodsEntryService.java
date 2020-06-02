package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.GoodsEntry;

public interface GoodsEntryService {
	
	List<GoodsEntry> listEntry(String sTime,String eTime);
	
	List<GoodsEntry> listEntryByCheckNumber(List<String> checkNumbers);

}
