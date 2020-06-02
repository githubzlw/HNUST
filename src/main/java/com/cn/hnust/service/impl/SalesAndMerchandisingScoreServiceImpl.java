package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;






import org.springframework.stereotype.Service;

import com.cn.hnust.dao.SalesAndMerchandisingScoreMapper;
import com.cn.hnust.pojo.SalesAndMerchandisingScore;
import com.cn.hnust.service.ISalesAndMerchandisingScoreService;
@Service
public class SalesAndMerchandisingScoreServiceImpl implements ISalesAndMerchandisingScoreService{
	@Resource
	private SalesAndMerchandisingScoreMapper salesAndMerchandisingScoreMapper;

	@Override
	public List<SalesAndMerchandisingScore> selectAllScore(
			SalesAndMerchandisingScore score) {
		
		return salesAndMerchandisingScoreMapper.selectAllScore(score);
	}

	@Override
	public int insertAll(List<SalesAndMerchandisingScore> scoreList) {
		
		return salesAndMerchandisingScoreMapper.insertAll(scoreList);
	}

	@Override
	public SalesAndMerchandisingScore getAll(String start) {
		
		return salesAndMerchandisingScoreMapper.getAll(start);
	}

	@Override
	public int updateAll(List<SalesAndMerchandisingScore> scoreList) {
		
		return salesAndMerchandisingScoreMapper.updateAll(scoreList);
	}

	@Override
	public void updateOne(SalesAndMerchandisingScore score) {
		
		salesAndMerchandisingScoreMapper.updateOne(score);
	}

	@Override
	public void updateAllScoreSale(String start) {
		salesAndMerchandisingScoreMapper.updateAllScoreSale(start);
		
	}

	@Override
	public void updateAllScoreDocumentary(String start) {
		salesAndMerchandisingScoreMapper.updateAllScoreDocumentary(start);
		
	}

	@Override
	public int updateOneProcess(SalesAndMerchandisingScore score) {
		
		return salesAndMerchandisingScoreMapper.updateOneProcess(score);
	}
}
