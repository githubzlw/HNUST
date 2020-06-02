package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.Products;

public interface IProductsService {
/**
 * 
 * @Title:IProductsService
 * @Description:查看项目相关船期
   @author wangyang
 * @param caseno
 * @return List<Products>
 * @throws
 */
	List<Products> selectSaildate(String caseno);

}
