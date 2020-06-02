package com.cn.hnust.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.QualityReportTeamMemberMapper;
import com.cn.hnust.pojo.QualityReportTeamMember;
import com.cn.hnust.service.IQualityReportTeamMemberService;
@Service
public class QualityReportTeamMemberServiceImpl implements IQualityReportTeamMemberService{
	@Resource
	private QualityReportTeamMemberMapper teamMemberMapper;

	@Override
	public void insertAll(QualityReportTeamMember member) {
		teamMemberMapper.insertAll(member);
		
	}  
}
