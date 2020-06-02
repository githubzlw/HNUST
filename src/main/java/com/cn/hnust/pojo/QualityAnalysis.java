package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class QualityAnalysis implements Serializable {
    private Integer id;

    private String projectNo;             //项目号

    private String technicianReply1;      //技术王工回复
 
    private String technicianReply2;      //技术总工回复

    private String purchaseReply;         //采购回复

    private Date qualityUploadTime;       //质量分析表上传时间

    private String qualityAnalysisName;   //质量分析表名

    private String technologyAnalysisName; //技术分析表名

    private String qualityAnalysisNewName; //质量分析表存储名

    private String technologyAnalysisNewName;//技术分析表存储名
    
    private Date technologyUploadTime;       //技术分析表上传时间
    
    private String process;
    
    private static final long serialVersionUID = 1L;

    
    
    public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public Date getQualityUploadTime() {
		return qualityUploadTime;
	}

	public void setQualityUploadTime(Date qualityUploadTime) {
		this.qualityUploadTime = qualityUploadTime;
	}

	public Date getTechnologyUploadTime() {
		return technologyUploadTime;
	}

	public void setTechnologyUploadTime(Date technologyUploadTime) {
		this.technologyUploadTime = technologyUploadTime;
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

    public String getTechnicianReply1() {
        return technicianReply1;
    }

    public void setTechnicianReply1(String technicianReply1) {
        this.technicianReply1 = technicianReply1 == null ? null : technicianReply1.trim();
    }

    public String getTechnicianReply2() {
        return technicianReply2;
    }

    public void setTechnicianReply2(String technicianReply2) {
        this.technicianReply2 = technicianReply2 == null ? null : technicianReply2.trim();
    }

    public String getPurchaseReply() {
        return purchaseReply;
    }

    public void setPurchaseReply(String purchaseReply) {
        this.purchaseReply = purchaseReply == null ? null : purchaseReply.trim();
    }

    public String getQualityAnalysisName() {
        return qualityAnalysisName;
    }

    public void setQualityAnalysisName(String qualityAnalysisName) {
        this.qualityAnalysisName = qualityAnalysisName == null ? null : qualityAnalysisName.trim();
    }

    public String getTechnologyAnalysisName() {
        return technologyAnalysisName;
    }

    public void setTechnologyAnalysisName(String technologyAnalysisName) {
        this.technologyAnalysisName = technologyAnalysisName == null ? null : technologyAnalysisName.trim();
    }

    public String getQualityAnalysisNewName() {
        return qualityAnalysisNewName;
    }

    public void setQualityAnalysisNewName(String qualityAnalysisNewName) {
        this.qualityAnalysisNewName = qualityAnalysisNewName == null ? null : qualityAnalysisNewName.trim();
    }

    public String getTechnologyAnalysisNewName() {
        return technologyAnalysisNewName;
    }

    public void setTechnologyAnalysisNewName(String technologyAnalysisNewName) {
        this.technologyAnalysisNewName = technologyAnalysisNewName == null ? null : technologyAnalysisNewName.trim();
    }

	@Override
	public String toString() {
		return "QualityAnalysis [id=" + id + ", projectNo=" + projectNo
				+ ", technicianReply1=" + technicianReply1
				+ ", technicianReply2=" + technicianReply2 + ", purchaseReply="
				+ purchaseReply + ", qualityUploadTime=" + qualityUploadTime
				+ ", qualityAnalysisName=" + qualityAnalysisName
				+ ", technologyAnalysisName=" + technologyAnalysisName
				+ ", qualityAnalysisNewName=" + qualityAnalysisNewName
				+ ", technologyAnalysisNewName=" + technologyAnalysisNewName
				+ ", technologyUploadTime=" + technologyUploadTime
				+ ", process=" + process + "]";
	}
    
    
}