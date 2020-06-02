package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.Comment;

public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    List<Comment> selectProjectComment(String projectNo);
    
    /**
     * 根据质检报告id查询
     * @Title selectByReportId 
     * @Description 
     * @param reportId
     * @return
     * @return List<Comment>
     */
    List<Comment> selectByReportId(Integer reportId);   
    
    
    /**
     * 根据质检报告id查询
     * @Title selectByShippingId 
     * @Description 
     * @param shippingId
     * @return
     * @return List<Comment>
     */
    List<Comment> selectByShippingId(Integer shippingId);  
    
    /**
     * 根据投诉报告id查询
     * @Title selectByComplaintId 
     * @Description 
     * @param complaintId
     * @return
     * @return List<Comment>
     */
    List<Comment> selectByComplaintId(Integer complaintId);
    /**
     * 根据投诉报告id查询
     * @Title selectByComplaintId 
     * @Description 
     * @param complaintId
     * @return
     * @return List<Comment>
     */
	List<Comment> selectByComplaintId1(Integer complaintId);  
}