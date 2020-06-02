package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ComplaintIssue;

public interface ComplaintIssueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComplaintIssue record);

    int insertSelective(ComplaintIssue record);

    ComplaintIssue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComplaintIssue record);

    int updateByPrimaryKey(ComplaintIssue record);
    
    
    /**
     * 根据投诉id查询
     * @Title selectByComplaintId 
     * @Description
     * @param complaintId
     * @return
     * @return List<ComplaintIssue>
     */
    List<ComplaintIssue> selectByComplaintId(@Param("complaintId")Integer complaintId);
    
    
	/**
	 * 批量插入
	 * @Title insertBatch 
	 * @Description
	 * @return
	 * @return int
	 */
	int insertBatch(List<ComplaintIssue> list);
	
	/**
	 * 根据投诉表id删除
	 * @Title deleteByComplaintId 
	 * @Description TODO
	 * @param complaintId
	 * @return
	 * @return int
	 */
	int deleteByComplaintId(@Param("complaintId")Integer complaintId);
	/**
	 * 
	 * @Title:ComplaintIssueService
	 * @Description:查询整改意见
	   @author wangyang
	 * @param parseInt
	 * @return List<ComplaintIssue>
	 * @throws
	 */
	List<ComplaintIssue> selectByComplaintId1(int complaintId);
  
}