package com.cn.hnust.daoErp;

import java.util.List;

import com.cn.hnust.pojo.ComplaintList;

public interface ComplaintListMapper {
/**
 * 
 * @Title:ComplaintListMapper
 * @Description:获取最后数据
   @author wangyang
 * @return ComplaintList
 * @throws
 */
	ComplaintList getLate();
	/**
	 * 
	 * @Title:ComplaintListService
	 * @Description:添加数据
	   @author wangyang
	 * @param list1 void
	 * @throws
	 */
void batchAddInspectionReport(List<ComplaintList> list1);

}
