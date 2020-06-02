package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ComplaintIssue;
import com.cn.hnust.pojo.IssueReply;
import com.cn.hnust.pojo.ProjectComplaint;

public interface IssueReplyService {
    int deleteByPrimaryKey(Integer id);

    int insert(IssueReply record);

    int insertSelective(IssueReply record);

    IssueReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IssueReply record);
    
    int updateByPrimaryKeySelective(IssueReply record,ProjectComplaint projectComplaint);

    int updateByPrimaryKey(IssueReply record);
    
    
	/**
	 * 批量插入
	 * @Title insertBatch 
	 * @Description
	 * @return
	 * @return int
	 */
	int insertBatch(List<IssueReply> list);
	
	
	/**
	 * 根据问题id查询
	 * @Title queryByIssueId 
	 * @Description 
	 * @return
	 * @return List<IssueReply>
	 */
	List<IssueReply> queryByIssueId(Integer issueId);
	
	
	
	
	
	/**
	 * 查询整改问题是否回复
	 */
	IssueReply queryReply(Integer issueId,Integer type);
}