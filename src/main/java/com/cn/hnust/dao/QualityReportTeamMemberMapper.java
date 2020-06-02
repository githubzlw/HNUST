package com.cn.hnust.dao;

import com.cn.hnust.pojo.QualityReportTeamMember;

public interface QualityReportTeamMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QualityReportTeamMember record);

    int insertSelective(QualityReportTeamMember record);

    QualityReportTeamMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QualityReportTeamMember record);

    int updateByPrimaryKey(QualityReportTeamMember record);

	void insertAll(QualityReportTeamMember member);
}