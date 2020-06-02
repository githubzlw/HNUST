package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProjectPause implements Serializable {
    private Integer id;

    private String projectNo;   //项目号

    private Integer isPause;    //是否暂停

    private String pauseReason; //暂停原因

    private Date createDate;    //创建时间

    private Date startDate;     //重启时间
    
    private String startReason; //重启理由

    private static final long serialVersionUID = 1L;

    
    
    public String getStartReason() {
		return startReason;
	}

	public void setStartReason(String startReason) {
		this.startReason = startReason;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public Integer getIsPause() {
        return isPause;
    }

    public void setIsPause(Integer isPause) {
        this.isPause = isPause;
    }

    public String getPauseReason() {
        return pauseReason;
    }

    public void setPauseReason(String pauseReason) {
        this.pauseReason = pauseReason == null ? null : pauseReason.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

	@Override
	public String toString() {
		return "ProjectPause [id=" + id + ", projectNo=" + projectNo
				+ ", isPause=" + isPause + ", pauseReason=" + pauseReason
				+ ", createDate=" + createDate + ", startDate=" + startDate
				+ ", startReason=" + startReason + "]";
	}
    
    
    
}