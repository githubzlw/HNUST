package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 项目汇报对象
 * @author Administrator
 *
 */
public class ProjectReport implements Serializable {
    private String id;

    private String projectNo;

    private String report;
    
    private String reportName;
    
    private String roleNo;
    
    private int status;// 未读，已读

    private String picUrl;

    private Date createDate;
    
    private Integer projectStage;
    
    private String projectStageView;

    private static final long serialVersionUID = 1L;
    

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report == null ? null : report.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getProjectStage() {
		return projectStage;
	}

	public void setProjectStage(Integer projectStage) {
		this.projectStage = projectStage;
	}

	public String getProjectStageView() {
		return projectStageView;
	}

	public void setProjectStageView(String projectStageView) {
		this.projectStageView = projectStageView;
	}
	
	
    
	
}