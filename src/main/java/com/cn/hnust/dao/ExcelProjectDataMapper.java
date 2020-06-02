package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.ExcelProjectData;

public interface ExcelProjectDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExcelProjectData record);

    int insertSelective(ExcelProjectData record);

    ExcelProjectData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExcelProjectData record);

    int updateByPrimaryKey(ExcelProjectData record);
    
	List<ExcelProjectData> findAllExcelProjectData();
}