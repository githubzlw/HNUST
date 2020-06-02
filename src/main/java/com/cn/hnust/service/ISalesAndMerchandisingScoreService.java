package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.SalesAndMerchandisingScore;

public interface ISalesAndMerchandisingScoreService {
/**
 * 
 * @Title:ISalesAndMerchandisingScoreService
 * @Description:查询销售跟单评分列表
   @author wangyang
 * @param score
 * @return List<SalesAndMerchandisingScore>
 * @throws
 */
	List<SalesAndMerchandisingScore> selectAllScore(
			SalesAndMerchandisingScore score);
/**
 * 
 * @Title:ISalesAndMerchandisingScoreService
 * @Description:批量添加数据
   @author wangyang
 * @param scoreList
 * @return int
 * @throws
 */
int insertAll(List<SalesAndMerchandisingScore> scoreList);
/**
 * 
 * @Title:ISalesAndMerchandisingScoreService
 * @Description:查询该月数据是否存在
   @author wangyang
 * @param start
 * @return SalesAndMerchandisingScore
 * @throws
 */
SalesAndMerchandisingScore getAll(String start);
/**
 * 
 * @Title:ISalesAndMerchandisingScoreService
 * @Description:修改数据
   @author wangyang
 * @param scoreList
 * @return int
 * @throws
 */
int updateAll(List<SalesAndMerchandisingScore> scoreList);
/**
 *
 * @Title:ISalesAndMerchandisingScoreService
 * @Description:修改排名分数
   @author wangyang
 * @param score void
 * @throws
 */
void updateOne(SalesAndMerchandisingScore score);
/**
 * 
 * @Title:ISalesAndMerchandisingScoreService
 * @Description:修改销售总分
   @author wangyang
 * @param start void
 * @throws
 */
void updateAllScoreSale(String start);
/**
 * 
 * @Title:ISalesAndMerchandisingScoreService
 * @Description:修改跟单总分
   @author wangyang
 * @param start void
 * @throws
 */
void updateAllScoreDocumentary(String start);
/**
 * 
 * @Title:ISalesAndMerchandisingScoreService
 * @Description:修改扣款项
   @author wangyang
 * @param score
 * @return int
 * @throws
 */
int updateOneProcess(SalesAndMerchandisingScore score);

}
