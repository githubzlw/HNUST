package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.RoleBindDetail;

public interface RoleBindDetailMapper {
	
    int deleteByPrimaryKey(Integer id);
    
    int deleteByParentId(@Param("parentId")Integer parentId);

    int insert(RoleBindDetail record);

    int insertSelective(RoleBindDetail record);

    RoleBindDetail selectByPrimaryKey(Integer id);
    
    List<RoleBindDetail> selectByParentId(@Param("parentId")Integer parentId);
    
    List<RoleBindDetail> selectAllDetail();
    
   int deleteAllDetail();
   
    
   
    List<RoleBindDetail> selectCondition(@Param("hasEmailUserId")boolean hasEmailUserId,@Param("hasPurchaseId")boolean hasPurchaseId);
    
    List<RoleBindDetail> selectConditionFirst();

    int updateByPrimaryKeySelective(RoleBindDetail record);

    int updateByPrimaryKey(RoleBindDetail record);
    
    int insertList(List<RoleBindDetail> roleBindDetails);
    
    List<RoleBindDetail> selectMeetingStatus(@Param("conditionType")int conditionType,@Param("meetingName")String meetingName);
    
    /**
	 * 检查是否有下一个任务
	 * @param indexNum
	 * @param taskId
	 * @Param projectNo
	 * @return RoleBindDetail
	 */
    RoleBindDetail checkNextTask(@Param("indexNum")int indexNum,@Param("id")int id);
    
    List<RoleBindDetail> selectListByRoleType(@Param("roleType")int roleType);
    
}