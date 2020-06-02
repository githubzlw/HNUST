package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.QuotePrice;
import com.cn.hnust.pojo.QuotePrice1;

public interface QuotePriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuotePrice record);

    int insertSelective(QuotePrice record);

    QuotePrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuotePrice record);

    int updateByPrimaryKey(QuotePrice record);

	List<QuotePrice> getall(@Param("id")int num2);

	QuotePrice getOne(@Param("projectNo")String projectNo);

	List<QuotePrice> getAllQuote(String projectNo);
}