package com.cn.hnust.service.impl;

import com.cn.hnust.daoErp.BargainMapper;
import com.cn.hnust.pojo.Bargain;
import com.cn.hnust.service.BargainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BargainServiceImpl implements BargainService {
    @Autowired
    private BargainMapper bargainMapper;

    @Override
    public List<Bargain> getAll() {
        return bargainMapper.getAll();
    }
}
