package com.cn.hnust.daoErp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.InvoiceInfo;

public interface InvoiceInfoMapper {
  
	
	/**
	 * 查询erp最早发票时间（用于判断是否新客户）
	 * @Title selectFirstDate 
	 * @Description
	 * @return
	 * @return List<InvoiceInfo>
	 */
	List<InvoiceInfo> selectFirstDate(List<String> item);
	/**
	 * 
	 * @Title:InvoiceInfoService
	 * @Description:查询利润
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return double
	 * @throws
	 */
	String getAllMoney(@Param("customercode")String userName, @Param("iCaseNo")String start);
	/**
	 * 
	 * @Title:InvoiceInfoMapper
	 * @Description:查询数据
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return InvoiceInfo
	 * @throws
	 */
	InvoiceInfo getAll(@Param("customercode")String userName, @Param("iCaseNo")String start);
	/**
	 * 
	 * @Title:InvoiceInfoMapper
	 * @Description:获取数据
	   @author wangyang
	 * @param info
	 * @return InvoiceInfo
	 * @throws
	 */
	InvoiceInfo selectCountByStatus(InvoiceInfo info);
	/**
	 * 
	 * @Title:InvoiceInfoMapper
	 * @Description:获取数据
	   @author wangyang
	 * @param info
	 * @return InvoiceInfo
	 * @throws
	 */
	InvoiceInfo getOne(InvoiceInfo info);
	
}
