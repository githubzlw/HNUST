package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MeetingRecord extends PageHelper implements Serializable {
    private Integer id;

    private String meetingNo;

    private String projectNo;

    private String meetingName;

    private String meetingDescribe;

    private Date createDate;

    private String meetingInputer;
    
    private List<ProjectTask> ProjectTaskList;
    
    private int taskNum;//任务数
    
    private String searchName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeetingNo() {
        return meetingNo;
    }

    public void setMeetingNo(String meetingNo) {
        this.meetingNo = meetingNo == null ? null : meetingNo.trim();
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName == null ? null : meetingName.trim();
    }

    public String getMeetingDescribe() {
        return meetingDescribe;
    }

    public void setMeetingDescribe(String meetingDescribe) {
        this.meetingDescribe = meetingDescribe == null ? null : meetingDescribe.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMeetingInputer() {
        return meetingInputer;
    }

    public void setMeetingInputer(String meetingInputer) {
        this.meetingInputer = meetingInputer == null ? null : meetingInputer.trim();
    }

	public List<ProjectTask> getProjectTaskList() {
		return ProjectTaskList;
	}

	public void setProjectTaskList(List<ProjectTask> projectTaskList) {
		ProjectTaskList = projectTaskList;
	}

	public int getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(int taskNum) {
		this.taskNum = taskNum;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

}