package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.Comment;

public interface IProjectCommentService {
	
	public void addProjetcComment(Comment comment);
	
	public List<Comment> selectProjectComment(String projectNo);
	
	
	
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
     * 删除
     * @Title deleteByPrimaryKey 
     * @Description
     * @param id
     * @return
     * @return int
     */
    int deleteByPrimaryKey(String id);
    
    
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
     * 
     * @Title:IProjectCommentService
     * @Description:查看销售跟单最终回复
       @author wangyang
     * @param parseInt
     * @return List<Comment>
     * @throws
     */
	public List<Comment> selectByComplaintId1(int parseInt);  
}
