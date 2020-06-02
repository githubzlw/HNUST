package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.EmailClientid;

public interface EmailClientidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmailClientid record);

    int insertSelective(EmailClientid record);

    EmailClientid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmailClientid record);

    int updateByPrimaryKey(EmailClientid record);
    /***
     * 查询所有的信息
     * @return
     */
    List<EmailClientid> selectAllEmailClientid();
}