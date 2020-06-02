package com.cn.hnust.service;

import java.util.List;
import java.util.Set;

import com.cn.hnust.pojo.MeetingRecord;

public interface IMeetingRecordService {

	public int addMeetingRecordAndTask(MeetingRecord meetingRecord);

	public List<MeetingRecord> selectMeetingRecordList(MeetingRecord meetingRecord);

	public List<MeetingRecord> selectMeetingRecordListCount(MeetingRecord meetingRecord);
	
	public MeetingRecord selectMeetingRecordByMeetingNo(String meetingNo);
	
	int updateFlagByProjectNo(Set<String> proSet,String meetingName);
	
	int updateaddFlagByProjectNo(Set<String> proSet,String meetingName);
	
	/**
	 * 保存会议的同时，更新质检报告表
	 * @Title addMeetingRecordAndTask 
	 * @Description TODO
	 * @param meetingRecord
	 * @param reportId
	 * @return
	 * @return int
	 */
    int addMeetingRecordAndTask(MeetingRecord meetingRecord,Integer reportId);
    
    
    
	/**
	 * 查询各类型会议
	 * @Title selectMeetings 
	 * @Description
	 * @param meetingRecord
	 * @return
	 * @return List<MeetingRecord>
	 */
	List<MeetingRecord> selectMeetings(MeetingRecord meetingRecord);
	
	
	/**
	 * 删除
	 * @Title deleteByPrimaryKey 
	 * @Description
	 * @param id
	 * @return
	 * @return int
	 */
    int deleteByPrimaryKey(String meetingNo);
	
}
