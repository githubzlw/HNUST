package com.cn.hnust.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.MeetingRecord;

public interface MeetingRecordMapper {
    int deleteByPrimaryKey(String meetingNo);

    int insert(MeetingRecord record);

    int insertSelective(MeetingRecord record);

    MeetingRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetingRecord record);

    int updateByPrimaryKey(MeetingRecord record);
    
	List<MeetingRecord> selectMeetingRecordList(MeetingRecord meetingRecord);

	List<MeetingRecord> selectMeetingRecordListCount(MeetingRecord meetingRecord);

	MeetingRecord selectMeetingRecordByMeetingNo(String meetingNo);
	
	int updateFlagByProjectNo(@Param("set") Set<String> proSet,@Param("meetingName")String meetingName);
	
	int updateaddFlagByProjectNo(@Param("set") Set<String> proSet,@Param("meetingName")String meetingName);
	
	List<MeetingRecord> selectAll();
	
	
	/**
	 * 查询各类型会议
	 * @Title selectMeetings 
	 * @Description
	 * @param meetingRecord
	 * @return
	 * @return List<MeetingRecord>
	 */
	List<MeetingRecord> selectMeetings(MeetingRecord meetingRecord);
}