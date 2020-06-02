package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.EmailClientid;

public interface IEmailClientidService {
    /**
     * 查询所有的项目信息
     * @return
     */
	public List<EmailClientid> selectAllEmailClientid();
}
