package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 销售录入状态
 * @author chenlun
 *
 */
public class StatusEnter implements Serializable {
    private String id;

    private String projectNo;

    private String statusType;

    private String feedback;

    private String details;

    private String fileUrl;

    private Date createDate;

    private String operator;
    
    private String type;

    private List<String> statusTypeList;
    
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType == null ? null : statusType.trim();
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback == null ? null : feedback.trim();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

	public List<String> getStatusTypeList() {
		return statusTypeList;
	}

	public void setStatusTypeList(List<String> statusTypeList) {
		this.statusTypeList = statusTypeList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}