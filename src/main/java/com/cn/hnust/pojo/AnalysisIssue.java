package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class AnalysisIssue implements Serializable {
    private Integer id;   

    private Integer occurrenceNum;           //问题出现次数
 
    private String projectNo;                //项目号

    private String issue;                    //问题 

    private Integer qualityAnalysisId;       //质量分析表id
    
    private Date createTime;          //上传时间
    
    private String process;           //工艺
    
    private Integer isComplaint;      //是否是投诉问题 0：否 1：是

    private Integer complaintId;       //投诉表id
    
    private static final long serialVersionUID = 1L;

    
    
    
    
    public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}

	public Integer getIsComplaint() {
		return isComplaint;
	}

	public void setIsComplaint(Integer isComplaint) {
		this.isComplaint = isComplaint;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOccurrenceNum() {
        return occurrenceNum;
    }

    public void setOccurrenceNum(Integer occurrenceNum) {
        this.occurrenceNum = occurrenceNum;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue == null ? null : issue.trim();
    }

    public Integer getQualityAnalysisId() {
        return qualityAnalysisId;
    }

    public void setQualityAnalysisId(Integer qualityAnalysisId) {
        this.qualityAnalysisId = qualityAnalysisId;
    }

	@Override
	public String toString() {
		return "AnalysisIssue [id=" + id + ", occurrenceNum=" + occurrenceNum
				+ ", projectNo=" + projectNo + ", issue=" + issue
				+ ", qualityAnalysisId=" + qualityAnalysisId + ", createTime="
				+ createTime + ", process=" + process + ", isComplaint="
				+ isComplaint + ", complaintId=" + complaintId + "]";
	}
    
    
}