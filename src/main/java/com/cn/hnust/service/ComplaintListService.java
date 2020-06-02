package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.ComplaintList;

public interface ComplaintListService {
/**
 * 
 * @Title:ComplaintListService
 * @Description:获取最终数据
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
