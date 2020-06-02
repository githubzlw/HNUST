package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.IssueReply;

public interface IssueReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IssueReply record);

    int insertSelective(IssueReply record);

    IssueReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IssueReply record);

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
	 * 
	 * @Title queryByIssueId 
	 * @Description 
	 * @return
	 * @return List<IssueReply>
	 */
	List<IssueReply> queryByIssueId(@Param("issueId")Integer issueId);
	
	
	/**
	 * 根据问题和类型查询
	 */
	IssueReply queryReply(@Param("issueId")Integer issueId,@Param("type")Integer type);
}