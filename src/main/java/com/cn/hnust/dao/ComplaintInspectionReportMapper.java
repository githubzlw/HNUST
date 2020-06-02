package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.ComplaintInspectionReport;

public interface ComplaintInspectionReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComplaintInspectionReport record);

    int insertSelective(ComplaintInspectionReport record);

    ComplaintInspectionReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComplaintInspectionReport record);

    int updateByPrimaryKey(ComplaintInspectionReport record);

	List<ComplaintInspectionReport> getAll(int id);
}