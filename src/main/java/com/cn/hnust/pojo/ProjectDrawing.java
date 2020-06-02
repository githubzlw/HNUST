package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProjectDrawing implements Serializable {
    private String id;

    private String projectNo;

    private String drawingName;

    private Date createDate;
    
    private String category;
    
    private String remark;           //备注信息
    
    private Date uploadTime;         //上传时间
    
    private String name;           //上传人
    private int complaintId;//投诉单Id
    private int verification;//是否验证
    private String verifier;//验证人
    private String verificationTime;//验证时间
    private static final long serialVersionUID = 1L;

    
    
    public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public int getVerification() {
		return verification;
	}

	public void setVerification(int verification) {
		this.verification = verification;
	}

	public String getVerifier() {
		return verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public String getVerificationTime() {
		return verificationTime;
	}

	public void setVerificationTime(String verificationTime) {
		this.verificationTime = verificationTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

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

    public String getDrawingName() {
        return drawingName;
    }

    public void setDrawingName(String drawingName) {
        this.drawingName = drawingName == null ? null : drawingName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProjectDrawing [id=" + id + ", projectNo=" + projectNo
				+ ", drawingName=" + drawingName + ", createDate=" + createDate
				+ ", category=" + category + ", remark=" + remark
				+ ", uploadTime=" + uploadTime + ", name=" + name
				+ ", complaintId=" + complaintId + ", verification="
				+ verification + ", verifier=" + verifier
				+ ", verificationTime=" + verificationTime + "]";
	}
	
	
    
}