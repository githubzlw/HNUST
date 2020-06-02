package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ComplaintIssue;

public interface ComplaintIssueService {
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
    List<ComplaintIssue> selectByComplaintId(Integer complaintId);
    
	/**
	 * 批量插入
	 * @Title insertBatch 
	 * @Description
	 * @return
	 * @return int
	 */
	int insertBatch(List<ComplaintIssue> list);
/**
 * 
 * @Title:ComplaintIssueService
 * @Description:查询整改意见
   @author wangyang
 * @param parseInt
 * @return List<ComplaintIssue>
 * @throws
 */
	List<ComplaintIssue> selectByComplaintId1(int parseInt);
}