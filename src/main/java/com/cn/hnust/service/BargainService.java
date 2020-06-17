package com.cn.hnust.service;

import com.cn.hnust.pojo.Bargain;

import java.util.List;

public interface BargainService {
    /**
     * 获取最近50条数据
     * @return
     */
    List<Bargain> getAll();
}
