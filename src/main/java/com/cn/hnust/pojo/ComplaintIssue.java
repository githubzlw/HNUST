package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.List;

public class ComplaintIssue implements Serializable {
    private Integer id;

    private Integer complaintId;   //投诉表id
 
    private String issue;         //问题
    
    private String imgList;       //问题图片
    
    private List<IssueReply> replyList;

    private static final long serialVersionUID = 1L;

    
    
    
    public String getImgList() {
		return imgList;
	}

	public void setImgList(String imgList) {
		this.imgList = imgList;
	}

	public List<IssueReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<IssueReply> replyList) {
		this.replyList = replyList;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue == null ? null : issue.trim();
    }

	@Override
	public String toString() {
		return "ComplaintIssue [id=" + id + ", complaintId=" + complaintId
				+ ", issue=" + issue + ", imgList=" + imgList + ", replyList="
				+ replyList + "]";
	}
    
    
    
}