package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProfitStatement;

public interface ProfitStatementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProfitStatement record);

    int insertSelective(ProfitStatement record);

    ProfitStatement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProfitStatement record);

    int updateByPrimaryKey(ProfitStatement record);

	int insertAll(List<ProfitStatement> profitList);

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
	List<ProfitStatement> getAll(@Param("date")String start, @Param("roleName")String roleName);
/**
 * 
 * @Title:ProfitStatementMapper
 * @Description:查询是那上一个月数据
   @author wangyang
 * @param userName
 * @param time1
 * @return ProfitStatement
 * @throws
 */
	ProfitStatement getOne(@Param("userName")String userName, @Param("date")String time1);
}