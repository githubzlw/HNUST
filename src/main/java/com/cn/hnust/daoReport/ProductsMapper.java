package com.cn.hnust.daoReport;

import java.util.List;

import com.cn.hnust.pojo.Products;

public interface ProductsMapper {
/**
 * 
 * @Title:ProductsMapper
 * @Description:查找数据
   @author wangyang
 * @param caseno
 * @return List<Products>
 * @throws
 */
	List<Products> selectSaildate(String caseno);

}
