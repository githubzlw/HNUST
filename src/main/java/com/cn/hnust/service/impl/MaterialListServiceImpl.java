package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.MaterialListMapper;
import com.cn.hnust.pojo.MaterialList;
import com.cn.hnust.service.MaterialListService;

@Service
public class MaterialListServiceImpl implements MaterialListService{
	@Autowired
	private MaterialListMapper materialListMapper;

	@Override
	public void add(List<MaterialList> materialList) {
		materialListMapper.add(materialList);
	}

	@Override
	public List<MaterialList> getall(int id) {
		
		return materialListMapper.getall(id);
	}
}
