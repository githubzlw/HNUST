package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.MeetingRecord;
import com.cn.hnust.pojo.RoleBindDetail;
import com.cn.hnust.pojo.RoleBindList;

public interface IRoleBindListService {

	 int deleteByPrimaryKey(Integer id);
	
	 int insert(RoleBindList record);

	 int insertSelective(RoleBindList record);

	 RoleBindList selectByPrimaryKey(Integer id);

	 int updateByPrimaryKeySelective(RoleBindList record);

	 int updateByPrimaryKey(RoleBindList record);

	 List<RoleBindList> selectRoleBindList(String projectNo,int start, int pageSize);
	 
	 List<RoleBindDetail> selectCondition(boolean hasEmailUserId,boolean hasPurchaseId);
	 
	 List<RoleBindDetail> selectConditionFirst();
	 
	 List<RoleBindDetail> selectMeetingStatus(int conditionType,String meetingName);

	 int selectRoleBindListCount(String projectNo);
	
	 int insertAll(RoleBindList record);
	 
	 List<RoleBindDetail> selectByParentId(Integer parentId);
	 
	 List<RoleBindDetail> selectAllDetail();
	 
	 int updateAllDetail(List<RoleBindDetail> list);
	 
	 int updateAll(RoleBindList record);
	 
	 int deleteAll(Integer id);
	 
	 
	 
}
