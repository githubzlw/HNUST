package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.ExcelProjectData;

public interface IExcelProjectDataService {
   
	/**
	 * 查询所有的Excel数据信息
	 * @return
	 */
   public List<ExcelProjectData> findAllExcelProjectData();
}
