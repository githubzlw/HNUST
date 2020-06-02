package com.cn.hnust.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.MeetingRecordMapper;
import com.cn.hnust.dao.ProjectTaskMapper;
import com.cn.hnust.dao.QualityReportMapper;
import com.cn.hnust.pojo.MeetingRecord;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.service.IMeetingRecordService;

@Service
public class MeetingRecordServiceImpl implements IMeetingRecordService {
    
	@Autowired
	private MeetingRecordMapper meetingRecordMapper;
	@Autowired
	private ProjectTaskMapper projectTaskMapper;
	@Autowired
	private QualityReportMapper qualityReportMapper;
	
	@Transactional
	@Override
	public int addMeetingRecordAndTask(MeetingRecord meetingRecord) {
		try {
			meetingRecordMapper.insertSelective(meetingRecord);
			List<ProjectTask> projectTaskList=meetingRecord.getProjectTaskList();
			for (int i = 0; i < projectTaskList.size(); i++) {
				projectTaskMapper.insertSelective(projectTaskList.get(i));
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		
	}

	@Override
	public List<MeetingRecord> selectMeetingRecordList(MeetingRecord meetingRecord) {
		return meetingRecordMapper.selectMeetingRecordList(meetingRecord);
	}

	@Override
	public List<MeetingRecord> selectMeetingRecordListCount(MeetingRecord meetingRecord) {
		return meetingRecordMapper.selectMeetingRecordListCount(meetingRecord);
	}

	@Override
	public MeetingRecord selectMeetingRecordByMeetingNo(String meetingNo) {
		return meetingRecordMapper.selectMeetingRecordByMeetingNo(meetingNo);
	}

	@Override
	public int updateFlagByProjectNo(Set<String> proSet,String meetingName) {
		
		return meetingRecordMapper.updateFlagByProjectNo(proSet,meetingName);
	}

	@Override
	public int updateaddFlagByProjectNo(Set<String> proSet, String meetingName) {
		return meetingRecordMapper.updateaddFlagByProjectNo(proSet, meetingName);
	}

	@Transactional
	@Override
	public int addMeetingRecordAndTask(MeetingRecord meetingRecord,Integer reportId) {
		
		try {
			meetingRecordMapper.insertSelective(meetingRecord);
			if(reportId!=null){
				QualityReport qualityReport = new QualityReport();
				qualityReport.setId(reportId);
				qualityReport.setMeetingNo(meetingRecord.getMeetingNo());
				qualityReportMapper.updateByPrimaryKeySelective(qualityReport);
			}
			List<ProjectTask> projectTaskList=meetingRecord.getProjectTaskList();
			for (int i = 0; i < projectTaskList.size(); i++) {
				projectTaskMapper.insertSelective(projectTaskList.get(i));
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	@Override
	public List<MeetingRecord> selectMeetings(MeetingRecord meetingRecord) {
		return meetingRecordMapper.selectMeetings(meetingRecord);
	}

	@Override
	public int deleteByPrimaryKey(String meetingNo) {
		return meetingRecordMapper.deleteByPrimaryKey(meetingNo);
	}
	
	

}
