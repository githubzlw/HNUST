package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.ProfitStatement;

public interface IProfitStatementService {
/**
 * 
 * @Title:IProfitStatementService
 * @Description:批量添加评分
   @author wangyang
 * @param profitList
 * @return int
 * @throws
 */
	int insertAll(List<ProfitStatement> profitList);
/**
 * 
 * @Title:IProfitStatementService
 * @Description:修改利润表
   @author wangyang
 * @param profitList
 * @return int
 * @throws
 */
int updateAll(List<ProfitStatement> profitList);
/**
 * 
 * @Title:IProfitStatementService
 * @Description:查询排名
   @author wangyang
 * @param start
 * @param string
 * @return List<ProfitStatement>
 * @throws
 */
List<ProfitStatement> getAll(String start, String string);
/**
 * 
 * @Title:IProfitStatementService
 * @Description:查询上一个月数据
   @author wangyang
 * @param userName
 * @param time1
 * @return ProfitStatement
 * @throws
 */
ProfitStatement getOne(String userName, String time1);

}
