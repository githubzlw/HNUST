package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class InspectionPlanInfo extends PageHelper implements Serializable {
    private Integer id;

    private String projectNoId;    //项目id

    private String planName;       

    private String projectNo;

    private String addPlan;

    private String wAudit;

    private String isQuality;

    private String yAudit;

    private Integer updateNum;

    private Integer uploadNum;

    private Date updateDate;

    private Date createDate;
    
    private String initiator;
    
    private String accepter;
    
    private String taskType;
    
    private String taskStatus;
    
    private String operator;
    
    private String projectTaskId;
    
    private String meetingNo;
    
    private String sendType;
    
    
    private Integer reportCount;  //质检报告数

    private static final long serialVersionUID = 1L;

    
    
    public Integer getReportCount() {
		return reportCount;
	}

	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectNoId() {
        return projectNoId;
    }

    public void setProjectNoId(String projectNoId) {
        this.projectNoId = projectNoId == null ? null : projectNoId.trim();
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getAddPlan() {
        return addPlan;
    }

    public void setAddPlan(String addPlan) {
        this.addPlan = addPlan == null ? null : addPlan.trim();
    }

    public String getwAudit() {
        return wAudit;
    }

    public void setwAudit(String wAudit) {
        this.wAudit = wAudit == null ? null : wAudit.trim();
    }

    public String getIsQuality() {
        return isQuality;
    }

    public void setIsQuality(String isQuality) {
        this.isQuality = isQuality == null ? null : isQuality.trim();
    }

    public String getyAudit() {
        return yAudit;
    }

    public void setyAudit(String yAudit) {
        this.yAudit = yAudit == null ? null : yAudit.trim();
    }

    public Integer getUpdateNum() {
        return updateNum;
    }

    public void setUpdateNum(Integer updateNum) {
        this.updateNum = updateNum;
    }

    public Integer getUploadNum() {
        return uploadNum;
    }

    public void setUploadNum(Integer uploadNum) {
        this.uploadNum = uploadNum;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getProjectTaskId() {
		return projectTaskId;
	}

	public void setProjectTaskId(String projectTaskId) {
		this.projectTaskId = projectTaskId;
	}

	public String getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	@Override
	public String toString() {
		return "InspectionPlanInfo [id=" + id + ", projectNoId=" + projectNoId
				+ ", planName=" + planName + ", projectNo=" + projectNo
				+ ", addPlan=" + addPlan + ", wAudit=" + wAudit
				+ ", isQuality=" + isQuality + ", yAudit=" + yAudit
				+ ", updateNum=" + updateNum + ", uploadNum=" + uploadNum
				+ ", updateDate=" + updateDate + ", createDate=" + createDate
				+ ", initiator=" + initiator + ", accepter=" + accepter
				+ ", taskType=" + taskType + ", taskStatus=" + taskStatus
				+ ", operator=" + operator + ", projectTaskId=" + projectTaskId
				+ ", meetingNo=" + meetingNo + ", sendType=" + sendType
				+ ", reportCount=" + reportCount + "]";
	}
	
}