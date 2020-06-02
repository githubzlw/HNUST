package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.cn.hnust.daoReport.ProductsMapper;
import com.cn.hnust.pojo.Products;
import com.cn.hnust.service.IProductsService;
@Service
public class ProductsServiceImpl implements IProductsService{
	@Autowired
	private ProductsMapper productionsMapper;

	@Override
	public List<Products> selectSaildate(String caseno) {
		
		return productionsMapper.selectSaildate(caseno);
	}
}
