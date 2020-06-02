package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.InspectionPlanComments;

public interface InspectionPlanCommentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InspectionPlanComments record);

    int insertSelective(InspectionPlanComments record);

    InspectionPlanComments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionPlanComments record);

    int updateByPrimaryKey(InspectionPlanComments record);

	List<InspectionPlanComments> getAll(@Param("processInstanceId")String processInstanceId);
}