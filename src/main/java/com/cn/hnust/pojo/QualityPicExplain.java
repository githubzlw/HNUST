package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class QualityPicExplain implements Serializable {
    private Integer id;

    private String projectNo;

    private String picName;
    
    private String picNameShow;

    private Integer qualityReportId;

    private String picExplain;

    private Date createDate;
    
    private Integer picType;   //图片类型 1细节图片 2不良品 3材质证明 4包装图5检验表格

    private static final long serialVersionUID = 1L;

    
    
    
    @Override
	public String toString() {
		return "QualityPicExplain [id=" + id + ", projectNo=" + projectNo
				+ ", picName=" + picName + ", picNameShow=" + picNameShow
				+ ", qualityReportId=" + qualityReportId + ", picExplain="
				+ picExplain + ", createDate=" + createDate + ", picType="
				+ picType + "]";
	}

	public Integer getPicType() {
		return picType;
	}

	public void setPicType(Integer picType) {
		this.picType = picType;
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

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }

    public Integer getQualityReportId() {
        return qualityReportId;
    }

    public void setQualityReportId(Integer qualityReportId) {
        this.qualityReportId = qualityReportId;
    }

    public String getPicExplain() {
        return picExplain;
    }

    public void setPicExplain(String picExplain) {
        this.picExplain = picExplain == null ? null : picExplain.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public String getPicNameShow() {
		return picNameShow;
	}

	public void setPicNameShow(String picNameShow) {
		this.picNameShow = picNameShow;
	}
    
}